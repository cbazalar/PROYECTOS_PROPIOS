CREATE OR REPLACE PACKAGE flx_pkg_proce_selec
IS

   ln_sqlcode   NUMBER (10);
   ls_sqlerrm   VARCHAR2 (1500);
   w_filas      NUMBER          := 5000;

   /**************************************************************************
   Descripcion       :    Proceso que se encarga de evaluar a las consultoras, y
                          determinar quienes participan del nuevo modelo de flexipago
   Fecha Creacion    :    01/06/2013
   Autor             :    Ivan Tocto
   Parametros Entrada:
      psCodigoPais        Codigo del Pais
      psCampanyaCierre    Campaña de Cierre
      psUsuario           Usuario que ejecuta el proceso
   ***************************************************************************/
   PROCEDURE flx_pr_evalu_consu (
      pscodigopais        VARCHAR2,
      pscampanyacierre    VARCHAR2,
      psusuario           VARCHAR2
   );

   /**************************************************************************
   Descripcion       :    Devuelve el peso de una variable en base al grupo y nombre
   Fecha Creacion    :    01/06/2013
   Autor             :    Ivan Tocto
   Parametros Entrada:
      pnOidGrupo          OID del grupo
      psNombreVariable    Nombre de la varialbe
   Parametros Salida :
               Peso de la variable
   ***************************************************************************/
   FUNCTION flx_fn_obtie_peso_varia (
      pnoidgrupo         flx_grupo.oid_grup%TYPE,
      psnombrevariable   flx_varia.nom_vari%TYPE
   )
      RETURN NUMBER;

   /**************************************************************************
   Descripcion       :    Devuelve el periodo al que corresponde la campaña
   Fecha Creacion    :    01/06/2013
   Autor             :    Ivan Tocto
   Parametros Entrada:
      psCampanya            Campaña
   Parametros Salida :
               Periodo al que pertenece la campaña (1, 2, 3)
   ***************************************************************************/
   FUNCTION flx_fn_obtie_perio (
      psCampanya    VARCHAR2
   )
      RETURN NUMBER;

   /**************************************************************************
   Descripcion            :    Proceso que se encarga de calcular las variables de cuenta corrientes 
                                      del nuevo modelo de flexipago
   Fecha Creacion     :    05/11/2013
   Autor                    :    Sebastian Guerra
   Parametros Entrada:
      psCodigoPais              Codigo del pais
      psCampanyaCierre    Campaña de cierre -- Campaña que varia
      pscampanyacierreregion      CampañaActual -- Campaña que NO varia
   ***************************************************************************/
   PROCEDURE flx_pr_calcu_varia_cuent_corri (
      pscodigopais             VARCHAR2,
      pscampanyacierre    VARCHAR2,
      pscampanyacierreregion   VARCHAR2
   );

   /**************************************************************************
   Descripcion            :    Proceso que se encarga de calcular las variables de cuenta corrientes
                                      del nuevo modelo de flexipago
   Fecha Creacion     :    04/03/2014
   Autor                    :    Ivan Tocto
   Parametros Entrada:
      psCodigoPais              Codigo del pais
      psCampanyaCierre    Campaña de cierre
      pnCampanyasAtras      Cantidad de campañas hacia atraz
   ***************************************************************************/
   PROCEDURE flx_pr_calcu_varia_ctact_canca (
      pscodigopais             VARCHAR2,
      pscampanyacierre    VARCHAR2,
      pnCampanyasAtras  NUMBER
   );

    /***************************************************************************
    Descripcion       : Valida la carga manual de lineas de credito
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_VALID_LINEA_CREDI(
        psCodigoPais VARCHAR2,
        psCodigoUsuario VARCHAR2);

    /***************************************************************************
    Descripcion       : Realiza la carga de lineas de credito
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_CARGA_LINEA_CREDI(
        psCodigoPeriodo VARCHAR2,
        psCodigoUsuario VARCHAR2);

    /***************************************************************************
    Descripcion       : Valida la carga manual de consultoras a deshabilitar
    Fecha Creacion    : 17/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_VALID_CONSU_DESHA(
        psCodigoPais VARCHAR2,
        psCodigoUsuario VARCHAR2);

    /***************************************************************************
    Descripcion       : Realiza la carga de lineas de consultoras a deshabilitar
    Fecha Creacion    : 17/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_CARGA_CONSU_DESHA(
        psCodigoPeriodo VARCHAR2,
        psCodigoUsuario VARCHAR2);

END flx_pkg_proce_selec;
/

CREATE OR REPLACE PACKAGE BODY flx_pkg_proce_selec
IS

   /**************************************************************************
   Descripcion       :    Proceso que se encarga de evaluar a las consultoras, y
                          determinar quienes participan del nuevo modelo de flexipago
   Fecha Creacion    :    11/12/2013
   Autor                   :    Sebastian Guerra
   Parametros Entrada:
      psCodigoPais        Codigo del Pais
      psCampanyaCierre   Campaña de Cierre
      psUsuario           Usuario que ejecuta el proceso
   ***************************************************************************/
   PROCEDURE flx_pr_evalu_consu (
      pscodigopais        VARCHAR2,
      pscampanyacierre    VARCHAR2,
      psusuario           VARCHAR2
   )
   IS
      CURSOR c_consu_eval
      IS
         SELECT 
                cam_cerr,
          cod_clie,
                vlc_frec_cop1_anoa,
                vlc_frec_cop2_anoa,
                vlc_frec_cop3_anoa,
                vcc_prob_incu,
                vcc_camp_eval,
                vcc_camp_comu,
                vcc_camp_fact,
                vcc_pedi_base,
                vcc_camp_pblc,
                vcc_esta_proc,
                vpb_prom_vdcp_1erp,
                vpb_prom_vdcp_2dop,
                vpb_prom_vdcp_3erp,
                vpb_prvd_vcmn_anpc,
                vpb_prvd_vcmn_18ca,
                vpb_prvd_vcmn_apcz,
                vpb_pvdc_p1zo,
                vpb_pvdc_p2zo,
                vpb_pvdc_p3zo,
                vcc_line_cred,
                vpi_edad_clie,
                (SELECT F.COD_REGI
                          FROM ZON_TERRI B,
                               ZON_TERRI_ADMIN C,
                               ZON_SECCI D,
                               ZON_ZONA E,
                               ZON_REGIO F,
                               MAE_CLIEN_UNIDA_ADMIN H
                         WHERE     H.CLIE_OID_CLIE = (SELECT OID_CLIE
                                                        FROM MAE_CLIEN
                                                       WHERE COD_CLIE = FLX.COD_CLIE)
                               AND H.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
                               AND C.TERR_OID_TERR = B.OID_TERR
                               AND D.OID_SECC = C.ZSCC_OID_SECC
                               AND E.OID_ZONA = D.ZZON_OID_ZONA
                               AND F.OID_REGI = E.ZORG_OID_REGI
                               AND C.IND_BORR = 0
                               AND H.IND_ACTI = '1') vpi_codi_regi,                
                vpi_nuan_belc,
                cod_more,
                cod_morc,
                cod_esre,
                cod_esrc,
                vcc_indi_apro,
                vcc_esta_prea,
                vpi_num_unid_vend,
                vpi_frec_ult9_camp,
                vpi_pda_ult6_camp,
                vpi_max_pul3_camp,
                vpi_pdp_ult3_camp,
                vpi_pro_dul3_camp,
                vpi_ult_da00_ult6_camp,
                vpi_num_camp_d168_ult9_camp,
                vpb_prvd_vcmn_09ca,
                vcc_indi_regz,
                vcc_indi_rcgz,
                vcp_comp_pago,
                vce_codi_cali,
                VPI_INDI_SEGM_NUEV,
                VPI_INDI_SEGM_CON1,
                VPI_INDI_SEGM_CON2,
                VPI_INDI_INCO,
                VPI_INDI_SEGM_TOPS,
                VPI_PROM_VNTA_ULT3_CAMP,
                VPI_PROM_VNTA_ULT9_CAMP,
                VPI_PROM_VND_ULT6C,
                VPI_CONS_ULT6_CAMP,
                VPI_IND_DIAS_ATRA_0021,
                VPI_NUM_CAMP_DA42_ULT9_CAMP,
                VPI_FLG_DA168_ULT9_CAMP,
                VPI_VAL_ABON_ADIA,
                VPI_PRI_DA00_ULT3_CAMP,
                VPI_ULT_DA21_ULT9_CAMP,
                VPI_ULT_DA00_ULT9_CAMP,
                VPI_CONS_3ULT_CAMP,
                VPI_INDI_SEGU,
                VPI_NUM_DIAS_PAGO,
                VPI_NUM_CAMP_DA21_ULT9_CAMP,
                VPI_VAL_SALD_VENC,
                VPI_PRO_DUL9_CAMP,
                VPI_FLG_DA21_ULT9_CAMP                
           FROM flx_varia_calcu_model FLX
          WHERE cam_cerr = pscampanyacierre
          AND
          (SELECT   
                F.COD_REGI|| E.COD_ZONA
            FROM ZON_TERRI B,
                 ZON_TERRI_ADMIN C,
                 ZON_SECCI D,
                 ZON_ZONA E,
                 ZON_REGIO F,
                 MAE_CLIEN_UNIDA_ADMIN H
           WHERE H.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = FLX.COD_CLIE)
             AND H.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI           
             AND C.TERR_OID_TERR = B.OID_TERR
             AND D.OID_SECC = C.ZSCC_OID_SECC
             AND E.OID_ZONA = D.ZZON_OID_ZONA
             AND F.OID_REGI = E.ZORG_OID_REGI
             AND C.IND_BORR = 0
             AND H.IND_ACTI = '1')  NOT IN (SELECT  EX.COD_REGI||EX.COD_ZONA FROM FLX_ZONAS_EXCLU EX);
          

      TYPE t_consu_eval IS TABLE OF c_consu_eval%ROWTYPE;
      lv_tab_consu_eval            t_consu_eval;
      
      CURSOR c_grupovariable IS
      SELECT gv.grup_oid_grup ||
                   va.nom_vari gru_vari,
                   gv.val_peso 
      FROM flx_grupo_varia gv, 
                 flx_varia va
      WHERE gv.vari_oid_vari = va.oid_vari and
                   gv.est_regi=1 and 
                   va.est_regi =1;
             
      TYPE grupovariable IS RECORD(
      grupovariable  flx_varia.nom_vari%type,
      valorpeso        flx_grupo_varia.val_peso%type);

      TYPE grupovariabletab IS TABLE OF grupovariable;
      grupovariablerecord grupovariabletab;
      
      TYPE pesoVariable IS TABLE OF flx_grupo_varia.val_peso%type INDEX BY flx_varia.nom_vari%type;
      valor_peso pesoVariable;

      estado_no_participa          VARCHAR2 (1)   := '0';-- Consultora que No participa del modelo de otorgamiento.
      estado_si_participa          VARCHAR2 (1)   := '1';-- Consultora que Si   participa del modelo de otorgamiento.
      estado_pre_aprobada          VARCHAR2 (1)   := '2'; -- Consultora PRE Aprobada por el Modelo
      estado_pre_rechazada         VARCHAR2 (1)   := '3'; -- Consultora PRE Rechazada por el modelo
      estado_aprobada              VARCHAR2 (1)   := '4';-- Consultora que Si   participa del modelo de otorgamiento y fue Aprobada.
      estado_rechazada              VARCHAR2 (1)   := '5';-- Consultora que Si   participa del modelo de otorgamiento y fue Rechazada
      formato_numero               VARCHAR2 (19)  := '999999999999.999999';
      lsCampanyaAnterior           VARCHAR2 (6);
      lsCampanyaMenos9           VARCHAR2 (6);
      lsCampanyaMenosN           VARCHAR2 (6);
      lsCampanyaMenos2           VARCHAR2 (6);
      lsCampanyaInicio18           VARCHAR2 (6);
      lsCampanyaInicioN            VARCHAR2 (6);
      lsCampanyaCierreMas1         VARCHAR2 (6);
      lsCampanyaCierreMas2         VARCHAR2 (6);
      lsCampanyaCierreMas3         VARCHAR2 (6);
      lsCampInicPerioUno           VARCHAR2 (6);
      lsCampFinaPerioUno           VARCHAR2 (6);
      lsCampInicPerioDos           VARCHAR2 (6);
      lsCampFinaPerioDos           VARCHAR2 (6);
      lsCampInicPerioTres          VARCHAR2 (6);
      lsCampFinaPerioTres          VARCHAR2 (6);
      lnOidCampanyaInicio18        NUMBER;
      lnOidCampanyaInicioN         NUMBER;
      lnOidCampanyaMenos2          NUMBER;
      lnOidCampanyaCierre          NUMBER;
      lnOidCampanyaCierreMas1      NUMBER;
      lnOidCampanyaCierreMas2      NUMBER;
      lnOidCampanyaCierreMas3      NUMBER;
      lnCantidad                   NUMBER;
      lnCantidadPedidosnCam        NUMBER;
      lnCantidadHabil           NUMBER;
      lnCantidadRechazada           NUMBER;
      lnOidGrupo                   NUMBER;
      lnAlfa                       NUMBER (18, 6);
      lnProbInc                    NUMBER (18, 6);
      lnPedidoBase                 NUMBER (18, 6);
      lnParamNroCampanyasEvaluar   NUMBER;
      lnParamNroCampanyasConPedido NUMBER;
      lnProbIncumpMax              NUMBER (18, 6);--El valor permitido para ser aprobada o no por el Comportamiento de Pago--
      lnCantidadPedidosCp          NUMBER;
      lnFrecuenciaUltima9Camp          NUMBER(2);
      lnSumaValorSegmento          NUMBER (18, 6);
      lnPeriodo                    NUMBER;
      lnVarCons1                   NUMBER (18, 6);
      lnVarCons2                   NUMBER (18, 6);
      lnVarCons3                   NUMBER (18, 6);

      lnParamDescuentoMinimo             NUMBER (18, 6);
      lnParamDescuentoMaximo             NUMBER (18, 6);
      lnLineadeCredito             NUMBER (18, 6);
      lnLineadeCreditoPre          NUMBER (18, 6); 
      lnPromediodeVenta            NUMBER (18, 6);
      lnPromedioDeVentaEntero   NUMBER;
      lnParamFactLdcMin               NUMBER (18, 6);

      lnsumaProducto               NUMBER (18, 6);
     
     lnLineadeCreditoMax          NUMBER(18, 6);
     lnParamPbMinimo              NUMBER(18, 6);
     lnCasoPB                     NUMBER(18, 6);
     lnParamCantidadCampRech      NUMBER;
     nroCampRechazadas                NUMBER;

    vpi_pro_edad_clie     NUMBER(18, 6); 
    vpi_pro_nuan_belc    NUMBER(18, 6);
    vpi_pro_num_unid_vend    NUMBER(18, 6);
    vpi_pro_pdp_ult3_camp    NUMBER(18, 6);
    vpi_pro_pda_ult6_camp    NUMBER(18, 6);
    vpi_pro_max_pul3_camp    NUMBER(18, 6);
    vpi_pro_pro_dul3_camp    NUMBER(18, 6);
    vpi_pro_ult_da00_ult6_camp    NUMBER(18, 6);
    vpi_pro_num_d168_ult9_camp    NUMBER(18, 6);

    vpi_pro_indi_segm_nuev    number(18, 6);
    vpi_pro_indi_segm_con1    number(18, 6);
    vpi_pro_indi_segm_con2    number(18, 6);
    vpi_pro_indi_inco    number(18, 6);
    vpi_pro_indi_segm_tops    number(18, 6);
    vpi_pro_frec_ult9_camp    number(18, 6);
    vpi_pro_prom_vnta_ult3_camp    number(18, 6);
    vpi_pro_prom_vnta_ult9_camp    number(18, 6);
    vpi_pro_prom_vnd_ult6c    number(18, 6);
    vpi_pro_num_camp_da42_u9cam    number(18, 6);
    vpi_pro_val_abon_adia    number(18, 6);
    vpi_pro_pri_da00_ult3_camp    number(18, 6);
    vpi_pro_ult_da21_ult9_camp    number(18, 6);
    vpi_pro_ult_da00_ult9_camp    number(18, 6);
            
    vpi_pro_num_camp_da21_u9cam number(18, 6);
    vpi_pro_pro_dul9_camp number(18, 6);
    vpi_pro_num_dias_pago number(18, 6);
    vpi_pro_val_sald_venc number(18, 6);
    
    vpi_dev_edad_clie    NUMBER(18, 6); 
    vpi_dev_nuan_belc    NUMBER(18, 6);
    vpi_dev_num_unid_vend    NUMBER(18, 6);
    vpi_dev_pdp_ult3_camp    NUMBER(18, 6);
    vpi_dev_pda_ult6_camp    NUMBER(18, 6);
    vpi_dev_max_pul3_camp    NUMBER(18, 6);
    vpi_dev_pro_dul3_camp    NUMBER(18, 6);
    vpi_dev_ult_da00_ult6_camp    NUMBER(18, 6);
    vpi_dev_num_d168_ult9_camp    NUMBER(18, 6);
     
    vpi_dev_indi_segm_nuev    number(18, 6);
    vpi_dev_indi_segm_con1    number(18, 6);
    vpi_dev_indi_segm_con2    number(18, 6);
    vpi_dev_indi_inco    number(18, 6);
    vpi_dev_indi_segm_tops    number(18, 6);
    vpi_dev_frec_ult9_camp    number(18, 6);
    vpi_dev_prom_vnta_ult3_camp    number(18, 6);
    vpi_dev_prom_vnta_ult9_camp    number(18, 6);
    vpi_dev_prom_vnd_ult6c    number(18, 6);
    vpi_dev_num_camp_da42_u9cam    number(18, 6);
    vpi_dev_val_abon_adia    number(18, 6);
    vpi_dev_pri_da00_ult3_camp    number(18, 6);
    vpi_dev_ult_da21_ult9_camp    number(18, 6);
    vpi_dev_ult_da00_ult9_camp    number(18, 6);

    vpi_dev_num_camp_da21_u9cam number(18, 6);
    vpi_dev_pro_dul9_camp number(18, 6);
    vpi_dev_num_dias_pago number(18, 6);
    vpi_dev_val_sald_venc number(18, 6);

    flagCampanyaAnteriorRechazada NUMBER;
    flagInsertHabil VARCHAR2(1) := '0';
    flagActivaCampanyaAnterior VARCHAR2(1) := '0';
     
    lnParamFormulaPI VARCHAR2(1);
    lnFlagRegistradaDeshabilitar NUMBER;
    
   BEGIN
      -- PARAMETROS --
      SELECT TO_NUMBER (val_para, formato_numero)
        INTO lnParamNroCampanyasEvaluar
        FROM flx_param
        WHERE cod_grup = '01' AND cod_para = '001' AND EST_REGI = 1;
      --4/9--
      SELECT TO_NUMBER (val_para, formato_numero)
        INTO lnParamNroCampanyasConPedido
        FROM flx_param
        WHERE cod_grup = '01' AND cod_para = '002' AND EST_REGI = 1;

      SELECT TO_NUMBER (val_para, formato_numero)
        INTO lnProbIncumpMax
        FROM flx_param
       WHERE cod_grup = '01' AND cod_para = '003' AND EST_REGI = 1;

      --Factor Linea credito minimo--
      SELECT TO_BINARY_DOUBLE(val_para, formato_numero) --
        INTO lnParamFactLdcMin
        FROM flx_param
        Where cod_grup='05'
        And cod_para='003' AND EST_REGI = 1;

       --Descuento Minimo Pedido Base
      SELECT TO_BINARY_DOUBLE(val_para, formato_numero)--
        INTO lnParamDescuentoMinimo
        FROM flx_param
        Where cod_grup='03'
        And cod_para='001' AND EST_REGI = 1;

       --Descuento Maximo Pedido Base
      SELECT TO_BINARY_DOUBLE(val_para, formato_numero)--
        INTO lnParamDescuentoMaximo
        FROM flx_param
        Where cod_grup='03'
        And cod_para='002' AND EST_REGI = 1;

        -- Formula a usar para el calculo de la PI
      SELECT val_para
      INTO lnParamFormulaPI
        FROM flx_param
        Where cod_grup='10'
        And cod_para='001' AND EST_REGI = 1;
      ---------------------------------------------------------------

      --Pedido Base Mnimo--
      SELECT TO_BINARY_DOUBLE(val_para, formato_numero)
      INTO lnParamPbMinimo
      FROM FLX_PARAM
      Where cod_para='001'
      And   cod_grup='08' AND EST_REGI = 1;

      --Cantidad de campañaas a mantenerse rechazada
      SELECT TO_NUMBER(val_para)
      INTO lnParamCantidadCampRech
      FROM FLX_PARAM
      Where cod_para='001'
      And   cod_grup='09' AND EST_REGI = 1;
      
      select
        avg(vpi_edad_clie) vpi_pro_edad_clie, 
        avg(vpi_nuan_belc) vpi_pro_nuan_belc,
        avg(vpi_num_unid_vend) vpi_pro_num_unid_vend,
        avg(vpi_pdp_ult3_camp) vpi_pro_pdp_ult3_camp,
        avg(vpi_pda_ult6_camp) vpi_pro_pda_ult6_camp,
        avg(vpi_max_pul3_camp) vpi_pro_max_pul3_camp,
        avg(vpi_pro_dul3_camp) vpi_pro_pro_dul3_camp,
        avg(nvl(vpi_ult_da00_ult6_camp,0)) vpi_pro_ult_da00_ult6_camp,
        avg(vpi_num_camp_d168_ult9_camp) vpi_pro_num_d168_ult9_camp,
        avg(vpi_indi_segm_nuev) vpi_pro_indi_segm_nuev,
        avg(vpi_indi_segm_con1) vpi_pro_indi_segm_con1,
        avg(vpi_indi_segm_con2) vpi_pro_indi_segm_con2,
        avg(vpi_indi_inco) vpi_pro_indi_inco,
        avg(vpi_indi_segm_tops) vpi_pro_indi_segm_tops,
        avg(vpi_frec_ult9_camp) vpi_pro_frec_ult9_camp,
        avg(vpi_prom_vnta_ult3_camp) vpi_pro_prom_vnta_ult3_camp,
        avg(vpi_prom_vnta_ult9_camp) vpi_pro_prom_vnta_ult9_camp,
        avg(vpi_prom_vnd_ult6c) vpi_pro_prom_vnd_ult6c,
        avg(vpi_num_camp_da42_ult9_camp) vpi_pro_num_camp_da42_u9cam,
        avg(vpi_val_abon_adia) vpi_pro_val_abon_adia,
        avg(vpi_pri_da00_ult3_camp) vpi_pro_pri_da00_ult3_camp,
        avg(vpi_ult_da21_ult9_camp) vpi_pro_ult_da21_ult9_camp,
        avg(vpi_ult_da00_ult9_camp) vpi_pro_ult_da00_ult9_camp,
        
        avg(vpi_num_camp_da21_ult9_camp) vpi_pro_num_camp_da21_u9cam,
        avg(vpi_pro_dul9_camp) vpi_pro_pro_dul9_camp,
        avg(vpi_num_dias_pago) vpi_pro_num_dias_pago,
        avg(vpi_val_sald_venc) vpi_pro_val_sald_venc,
        
        stddev(vpi_edad_clie) vpi_dev_edad_clie,
        stddev(vpi_nuan_belc) vpi_dev_nuan_belc,
        stddev(vpi_num_unid_vend) vpi_dev_num_unid_vend,
        stddev(vpi_pdp_ult3_camp) vpi_dev_pdp_ult3_camp,
        stddev(vpi_pda_ult6_camp) vpi_dev_pda_ult6_camp,
        stddev(vpi_max_pul3_camp) vpi_dev_max_pul3_camp,
        stddev(vpi_pro_dul3_camp) vpi_dev_pro_dul3_camp,
        stddev(nvl(vpi_ult_da00_ult6_camp,0)) vpi_dev_ult_da00_ult6_camp,
        stddev(vpi_num_camp_d168_ult9_camp) vpi_dev_num_d168_ult9_camp,
        stddev(vpi_indi_segm_nuev) vpi_dev_indi_segm_nuev,
        stddev(vpi_indi_segm_con1) vpi_dev_indi_segm_con1,
        stddev(vpi_indi_segm_con2) vpi_dev_indi_segm_con2,
        stddev(vpi_indi_inco) vpi_dev_indi_inco,
        stddev(vpi_indi_segm_tops) vpi_dev_indi_segm_tops,
        stddev(vpi_frec_ult9_camp) vpi_dev_frec_ult9_camp,
        stddev(vpi_prom_vnta_ult3_camp) vpi_dev_prom_vnta_ult3_camp,
        stddev(vpi_prom_vnta_ult9_camp) vpi_dev_prom_vnta_ult9_camp,
        stddev(vpi_prom_vnd_ult6c) vpi_dev_prom_vnd_ult6c,
        stddev(vpi_num_camp_da42_ult9_camp) vpi_dev_num_camp_da42_u9cam,
        stddev(vpi_val_abon_adia) vpi_dev_val_abon_adia,
        stddev(vpi_pri_da00_ult3_camp) vpi_dev_pri_da00_ult3_camp,
        stddev(vpi_ult_da21_ult9_camp) vpi_dev_ult_da21_ult9_camp,
        stddev(vpi_ult_da00_ult9_camp) vpi_dev_ult_da00_ult9_camp,
        
        stddev(vpi_num_camp_da21_ult9_camp) vpi_dev_num_camp_da21_u9cam,
        stddev(vpi_pro_dul9_camp) vpi_dev_pro_dul9_camp,
        stddev(vpi_num_dias_pago) vpi_dev_num_dias_pago,
        stddev(vpi_val_sald_venc) vpi_dev_val_sald_venc
        
      into
        vpi_pro_edad_clie, 
        vpi_pro_nuan_belc,
        vpi_pro_num_unid_vend,
        vpi_pro_pdp_ult3_camp,
        vpi_pro_pda_ult6_camp,
        vpi_pro_max_pul3_camp,
        vpi_pro_pro_dul3_camp,
        vpi_pro_ult_da00_ult6_camp,
        vpi_pro_num_d168_ult9_camp,
        vpi_pro_indi_segm_nuev,
        vpi_pro_indi_segm_con1,
        vpi_pro_indi_segm_con2,
        vpi_pro_indi_inco,
        vpi_pro_indi_segm_tops,
        vpi_pro_frec_ult9_camp,
        vpi_pro_prom_vnta_ult3_camp,
        vpi_pro_prom_vnta_ult9_camp,
        vpi_pro_prom_vnd_ult6c,
        vpi_pro_num_camp_da42_u9cam,
        vpi_pro_val_abon_adia,
        vpi_pro_pri_da00_ult3_camp,
        vpi_pro_ult_da21_ult9_camp,
        vpi_pro_ult_da00_ult9_camp,        
        
        vpi_pro_num_camp_da21_u9cam,
        vpi_pro_pro_dul9_camp,
        vpi_pro_num_dias_pago,
        vpi_pro_val_sald_venc,
        
        vpi_dev_edad_clie,
        vpi_dev_nuan_belc,
        vpi_dev_num_unid_vend,
        vpi_dev_pdp_ult3_camp,
        vpi_dev_pda_ult6_camp,
        vpi_dev_max_pul3_camp,
        vpi_dev_pro_dul3_camp,
        vpi_dev_ult_da00_ult6_camp,
        vpi_dev_num_d168_ult9_camp,
        vpi_dev_indi_segm_nuev,
        vpi_dev_indi_segm_con1,
        vpi_dev_indi_segm_con2,
        vpi_dev_indi_inco,
        vpi_dev_indi_segm_tops,
        vpi_dev_frec_ult9_camp,
        vpi_dev_prom_vnta_ult3_camp,
        vpi_dev_prom_vnta_ult9_camp,
        vpi_dev_prom_vnd_ult6c,
        vpi_dev_num_camp_da42_u9cam,
        vpi_dev_val_abon_adia,
        vpi_dev_pri_da00_ult3_camp,
        vpi_dev_ult_da21_ult9_camp,
        vpi_dev_ult_da00_ult9_camp,
        
        vpi_dev_num_camp_da21_u9cam,
        vpi_dev_pro_dul9_camp,
        vpi_dev_num_dias_pago,
        vpi_dev_val_sald_venc
        
      from flx_varia_calcu_model FLX
      where cam_cerr = pscampanyacierre
          AND
          (SELECT   
                F.COD_REGI|| E.COD_ZONA
            FROM ZON_TERRI B,
                 ZON_TERRI_ADMIN C,
                 ZON_SECCI D,
                 ZON_ZONA E,
                 ZON_REGIO F,
                 MAE_CLIEN_UNIDA_ADMIN H
           WHERE H.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = FLX.COD_CLIE)
             AND H.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI           
             AND C.TERR_OID_TERR = B.OID_TERR
             AND D.OID_SECC = C.ZSCC_OID_SECC
             AND E.OID_ZONA = D.ZZON_OID_ZONA
             AND F.OID_REGI = E.ZORG_OID_REGI
             AND C.IND_BORR = 0
             AND H.IND_ACTI = '1')  NOT IN (SELECT  EX.COD_REGI||EX.COD_ZONA FROM FLX_ZONAS_EXCLU EX);
      
      lsCampanyaAnterior       := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre,-1);
      lsCampanyaMenos9       := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre,-9);
      lsCampanyaMenosN       := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre,-lnParamCantidadCampRech);
      lsCampanyaMenos2       := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre,-2);
      lsCampanyaInicio18       := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,pscampanyacierre,-18);
      lsCampanyaInicioN        := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,pscampanyacierre,-lnParamNroCampanyasEvaluar);
      lsCampanyaCierreMas1    := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre, 1);
      lsCampanyaCierreMas2    := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre, 2);
      lsCampanyaCierreMas3    := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre, 3);

      lnOidCampanyaInicio18    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaInicio18);
      lnOidCampanyaCierre     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (pscampanyacierre);
      lnOidCampanyaMenos2     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaMenos2);
      lnOidCampanyaInicioN     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaInicioN);
      lnOidCampanyaCierreMas1 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaCierreMas1);
      lnOidCampanyaCierreMas2 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaCierreMas2);
      lnOidCampanyaCierreMas3 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2 (lsCampanyaCierreMas3);

      -- CAMPANIA DE INICIO Y FIN DE PERIODO PARA EL CALCULO DEL PEDIDO BASE
      lsCampInicPerioUno  := SUBSTR (lsCampanyaCierreMas2, 1, 4) || '01';
      lsCampFinaPerioUno  := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, lsCampInicPerioUno,5);
      lsCampInicPerioDos  := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, lsCampInicPerioUno,6);
      lsCampFinaPerioDos  := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampInicPerioUno,11);
      lsCampInicPerioTres := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampInicPerioUno,12);
      lsCampFinaPerioTres := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampInicPerioUno,17);
      -- --

      lnPeriodo := flx_fn_obtie_perio (lsCampanyaCierreMas2);

      -- Limpiamos los valores calculados anteriormente de los campos calculados en un proceso anterior--
      -- --
      UPDATE   FLX_VARIA_CALCU_MODEL
      SET
      vcc_camp_cier = null,
      vcc_prob_incu = null,
      vcc_camp_comu = null,
      vcc_camp_eval = null,
      vcc_camp_pblc = null,
      vcc_pedi_base = 0,
      vcc_line_cred = 0,
      vcc_esta_proc = estado_no_participa,
      vcc_camp_fact = null,
      cod_esrc = null,
      cod_morc = null,
      cod_esre = null,
      cod_more = null,
      vcc_indi_apro = null,
      usu_modi = null,
      fec_modi = null,
      vcc_esta_prea = null
      WHERE cam_cerr = pscampanyacierre;
      -- --
      OPEN c_grupovariable;
      
      LOOP
        FETCH c_grupovariable BULK COLLECT
          INTO grupovariablerecord LIMIT w_filas;
        IF grupovariablerecord.count > 0 THEN
          FOR x IN grupovariablerecord.first .. grupovariablerecord.last
          LOOP            
            valor_peso(grupovariablerecord(x).grupovariable)  := grupovariablerecord(x).valorpeso;
          END LOOP;
        END IF;
        EXIT WHEN c_grupovariable%NOTFOUND;
      END LOOP;
      
      CLOSE c_grupovariable;
        
      OPEN c_consu_eval;

      LOOP
         FETCH c_consu_eval
         BULK COLLECT INTO lv_tab_consu_eval LIMIT w_filas;

         IF lv_tab_consu_eval.COUNT > 0 THEN
            FOR i IN lv_tab_consu_eval.FIRST..  lv_tab_consu_eval.LAST
            LOOP
                
               -- (1) EVALUACION DE CONSULTORAS POR CAMPANIA --

                flagInsertHabil := '0';
                flagActivaCampanyaAnterior := '0';

               -- Verificamos si la consultora ha participado del modelo en la campania anterior(Habiles)
                SELECT COUNT(*)
                 INTO lnCantidad
                FROM flx_consu_habil_flexi
                WHERE cod_pais = pscodigopais 
                AND cod_clie = lv_tab_consu_eval (i).cod_clie
                -- Se toma cierre+1 debido a que el campo cod_peri_fact es posterior (si cierre = 201403, fact= 201405, por lo tanto fact antrior=201404) 
                AND cod_peri_fact = lsCampanyaCierreMas1 
                AND ind_habi = 1;
               
                -- Verificamos si la consultora ha sido rechazada por la Gz la campania anterior(desaprobada)
                SELECT COUNT(*) INTO flagCampanyAanteriorRechazada
                FROM FLX_VARIA_CALCU_MODEL
                WHERE COD_CLIE =  lv_tab_consu_eval (i).COD_CLIE
                AND CAM_CERR = lsCampanyaAnterior
                AND VCC_ESTA_PROC = ESTADO_RECHAZADA
                AND VCC_INDI_REGZ = 1;
                
                -- Verificamos si la consultora esta registrada para ser deshabilitada
                SELECT COUNT(*)
                INTO lnFlagRegistradaDeshabilitar 
                FROM FLX_REGIS_MANUA_CONDES
                WHERE COD_PAIS = psCodigoPais
                AND CAM_PROC = lsCampanyaCierreMas2
                AND COD_CLIE = lv_tab_consu_eval (i).cod_clie;
                
                IF lnFlagRegistradaDeshabilitar > 0 THEN
                    flagCampanyAanteriorRechazada := 1;
                END IF;                
                -- --
                
                -- Nro de campañas que ya fue rechazada
                SELECT COUNT(*) INTO nroCampRechazadas
                FROM FLX_VARIA_CALCU_MODEL
                WHERE COD_CLIE =  lv_tab_consu_eval (i).cod_clie
                AND CAM_CERR >=  lsCampanyaMenosN
                AND CAM_CERR<= lsCampanyaAnterior
                AND VCC_ESTA_PROC = ESTADO_RECHAZADA
                AND VCC_INDI_REGZ = 1;

               IF (lnCantidad > 0 AND flagCampanyAanteriorRechazada<>1)  THEN
               
                  -- Aqui Participaron del proceso anterior,                                                                           
                 -- Por lo tanto verificamos si pasaron por lo menos 1 pedido en las ultimas 18 campañas
                  SELECT COUNT (1)
                    INTO lnCantidad
                    FROM ped_solic_cabec ps,
                         mae_clien mc,
                         ped_tipo_solic_pais tsp,
                         ped_tipo_solic ts
                   WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                     AND ps.clie_oid_clie = mc.oid_clie
                     AND ps.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                     AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                     AND ps.perd_oid_peri > lnOidCampanyaInicio18
                     AND ps.perd_oid_peri <= lnOidCampanyaCierre
                     AND ts.cod_tipo_soli = 'SOC';
                   
                  IF lnCantidad > 0 THEN                    
                     lv_tab_consu_eval (i).vcc_esta_proc := estado_si_participa;                  
                  ELSE
                     lv_tab_consu_eval (i).vcc_esta_proc := estado_no_participa;                  
                     lv_tab_consu_eval (i).vcc_indi_apro := 'N';
                  END IF;
                       
               -- Si la consultora fue rehazada en la campaña anterior, hay que mantenerla rechazada por N campañas (9 parametro)
               ELSIF (flagcampanyaanteriorrechazada = 1) AND (nroCampRechazadas < lnParamCantidadCampRech) THEN
                    lv_tab_consu_eval (i).VCC_INDI_REGZ := '1'; -- rechazada
                    lv_tab_consu_eval (i).VCC_INDI_RCGZ := '0'; -- no recomendada
                    lv_tab_consu_eval (i).VCC_INDI_APRO := 'R'; -- RECHAZADA
                    lv_tab_consu_eval (i).VCC_ESTA_PROC := '5'; -- rechazada por el modelo

               ELSE
               --Si no participo del proceso de la camapaña anterior o ya cumplió el rechazo de 9 campañas entonces:--
                 
                -- Verificamos si es  ACTIVA:  es decir, Verificamos si por lo menos paso 1 pedido en las ultimas 2 campañas
                  SELECT COUNT (1)
                    INTO lnCantidad
                    FROM ped_solic_cabec ps,
                         mae_clien mc,
                         ped_tipo_solic_pais tsp,
                         ped_tipo_solic ts
                   WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                     AND ps.clie_oid_clie = mc.oid_clie
                     AND ps.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                     AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                     AND ps.perd_oid_peri > lnOidCampanyaMenos2
                     AND ps.perd_oid_peri <= lnOidCampanyaCierre
                     AND ts.cod_tipo_soli = 'SOC';
                  -- -- 
                 ------------------------
                 
                   -- obtenemos la cantida de pedidos en 9 campañas
                  SELECT COUNT (1)
                    INTO lnCantidadPedidosnCam
                    FROM ped_solic_cabec ps,
                         mae_clien mc,
                         ped_tipo_solic_pais tsp,
                         ped_tipo_solic ts
                   WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                     AND ps.clie_oid_clie = mc.oid_clie
                     AND ps.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                     AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                     AND ps.perd_oid_peri > lnOidCampanyaInicioN
                     AND ps.perd_oid_peri <= lnOidCampanyaCierre
                     AND ts.cod_tipo_soli = 'SOC';
                    -- -- 
                 -------------------------------------                                                                           
                 IF (lnCantidad > 0 AND  (lnCantidadPedidosnCam >= lnParamNroCampanyasConPedido) )THEN                    
                     lv_tab_consu_eval (i).vcc_esta_proc := estado_si_participa;
                  ELSE
                     lv_tab_consu_eval (i).vcc_esta_proc := estado_no_participa;
                     lv_tab_consu_eval (i).vcc_indi_apro := 'N';
                  END IF;
               
               END IF;
               -- FIN (1) --

               IF lv_tab_consu_eval (i).vcc_esta_proc = estado_si_participa THEN
               -- (2) CALCULO DE LA PROBABILIDAD DE INCUMPLIMIENTO --

                       IF lnCantidadPedidosnCam>0 THEN
               -- Obtenemos el oid del grupo y el valor alfa
               --Alfa: el valor de alfa depende del codigo de region.

                SELECT gp.oid_grup, gp.val_cons
                INTO lnOidGrupo, lnAlfa
                FROM flx_grupo gp, flx_grupo_regio gr, zon_regio zr
                WHERE gp.oid_grup = gr.grup_oid_grup
                AND gr.zorg_oid_regi = zr.oid_regi
                        AND zr.cod_regi = lv_tab_consu_eval (i).vpi_codi_regi
                        AND gp.est_regi=1
                        AND gr.est_regi=1;

                --calculo de la suma de los productos--
                lnsumaProducto:= lnAlfa
                          + NVL(valor_peso(lnoidgrupo|| 'EDAD') * ((lv_tab_consu_eval (i).vpi_edad_clie - vpi_pro_edad_clie) / vpi_dev_edad_clie), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'ANTIGUEDAD') * ((lv_tab_consu_eval (i).vpi_nuan_belc - vpi_pro_nuan_belc) / vpi_dev_nuan_belc), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'UNIDADESVENDIDAS')* ((lv_tab_consu_eval (i).vpi_num_unid_vend - vpi_pro_num_unid_vend) / vpi_dev_num_unid_vend), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'PDPULT3CAMP')* ((lv_tab_consu_eval (i).vpi_pdp_ult3_camp - vpi_pro_pdp_ult3_camp) / vpi_dev_pdp_ult3_camp), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'PDAULT6CAMP')* ((lv_tab_consu_eval (i).vpi_pda_ult6_camp - vpi_pro_pda_ult6_camp) / vpi_dev_pda_ult6_camp), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'MAXPULT3CAMP')* ((lv_tab_consu_eval (i).vpi_max_pul3_camp - vpi_pro_max_pul3_camp) / vpi_dev_max_pul3_camp), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'MDULT3CAMP')* ((lv_tab_consu_eval (i).vpi_pro_dul3_camp - vpi_pro_pro_dul3_camp) / vpi_dev_pro_dul3_camp), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'EARLIESTPASTDUERECORD6')* ((nvl(lv_tab_consu_eval (i).vpi_ult_da00_ult6_camp, 0) - vpi_pro_ult_da00_ult6_camp) / vpi_dev_ult_da00_ult6_camp), 0)
                          + NVL(valor_peso(lnoidgrupo|| 'CAMPANIAS9CONDA168')* ((lv_tab_consu_eval (i).vpi_num_camp_d168_ult9_camp - vpi_pro_num_d168_ult9_camp) / vpi_dev_num_d168_ult9_camp), 0)
                          
                          + NVL(valor_peso(lnoidgrupo|| 'SEGMENTONUEVAS')* ((lv_tab_consu_eval (i).VPI_INDI_SEGM_NUEV - vpi_pro_indi_segm_nuev) / vpi_dev_indi_segm_nuev),0)
                          + NVL(valor_peso(lnoidgrupo|| 'SEGMENTOCONSTANTES1')* ((lv_tab_consu_eval (i).VPI_INDI_SEGM_CON1 - vpi_pro_indi_segm_con1) / vpi_dev_indi_segm_con1),0)
                          + NVL(valor_peso(lnoidgrupo|| 'SEGMENTOCONSTANTES2')* ((lv_tab_consu_eval (i).VPI_INDI_SEGM_CON2 - vpi_pro_indi_segm_con2) / vpi_dev_indi_segm_con2),0)
                          + NVL(valor_peso(lnoidgrupo|| 'SEGMENTOINCONSTANTES')* ((lv_tab_consu_eval (i).VPI_INDI_INCO - vpi_pro_indi_inco) / vpi_dev_indi_inco),0)
                          + NVL(valor_peso(lnoidgrupo|| 'SEGMENTOTOPS')* ((lv_tab_consu_eval (i).VPI_INDI_SEGM_TOPS - vpi_pro_indi_segm_tops) / vpi_dev_indi_segm_tops),0)
                          + NVL(valor_peso(lnoidgrupo|| 'FRECULT9CAMP')* ((lv_tab_consu_eval (i).VPI_FREC_ULT9_CAMP - vpi_pro_frec_ult9_camp) / vpi_dev_frec_ult9_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'VNDULT3CAMP')* ((lv_tab_consu_eval (i).VPI_PROM_VNTA_ULT3_CAMP - vpi_pro_prom_vnta_ult3_camp) / vpi_dev_prom_vnta_ult3_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'VNULT9CAMP')* ((lv_tab_consu_eval (i).VPI_PROM_VNTA_ULT9_CAMP - vpi_pro_prom_vnta_ult9_camp) / vpi_dev_prom_vnta_ult9_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'VNDULT6CAMP')* ((lv_tab_consu_eval (i).VPI_PROM_VND_ULT6C - vpi_pro_prom_vnd_ult6c) / vpi_dev_prom_vnd_ult6c),0)
                          + NVL(valor_peso(lnoidgrupo|| 'CAMPANIAS9CONDA42')* ((lv_tab_consu_eval (i).VPI_NUM_CAMP_DA42_ULT9_CAMP - vpi_pro_num_camp_da42_u9cam) / vpi_dev_num_camp_da42_u9cam),0)
                          + NVL(valor_peso(lnoidgrupo|| 'ABONOALDIA')* ((lv_tab_consu_eval (i).VPI_VAL_ABON_ADIA - vpi_pro_val_abon_adia) / vpi_dev_val_abon_adia),0)
                          + NVL(valor_peso(lnoidgrupo|| 'OLDESTPASTDUERECORD3')* ((lv_tab_consu_eval (i).VPI_PRI_DA00_ULT3_CAMP - vpi_pro_pri_da00_ult3_camp) / vpi_dev_pri_da00_ult3_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'DEPM21')* ((lv_tab_consu_eval (i).VPI_ULT_DA21_ULT9_CAMP - vpi_pro_ult_da21_ult9_camp) / vpi_dev_ult_da21_ult9_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'EARLIESTPASTDUERECORD9')* ((lv_tab_consu_eval (i).VPI_ULT_DA00_ULT9_CAMP - vpi_pro_ult_da00_ult9_camp) / vpi_dev_ult_da00_ult9_camp),0)

                          + NVL(valor_peso(lnoidgrupo|| 'CAMPANYAS9CON_DA21')* ((lv_tab_consu_eval (i).VPI_NUM_CAMP_DA21_ULT9_CAMP - vpi_pro_num_camp_da21_u9cam) / vpi_dev_num_camp_da21_u9cam),0)
                          + NVL(valor_peso(lnoidgrupo|| 'MDULT9CAMP')* ((lv_tab_consu_eval (i).VPI_PRO_DUL9_CAMP - vpi_pro_pro_dul9_camp) / vpi_dev_pro_dul9_camp),0)
                          + NVL(valor_peso(lnoidgrupo|| 'DIADEPAGO')* ((lv_tab_consu_eval (i).VPI_NUM_DIAS_PAGO - vpi_pro_num_dias_pago) / vpi_dev_num_dias_pago),0)
                          + NVL(valor_peso(lnoidgrupo|| 'SALDOVENCIDO')* ((lv_tab_consu_eval (i).VPI_VAL_SALD_VENC - vpi_pro_val_sald_venc) / vpi_dev_val_sald_venc),0);


                            IF lv_tab_consu_eval (i).VPI_CONS_ULT6_CAMP = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'CONS6CAMP_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'CONS6CAMP_0'),0);
                            END IF;

                            IF lv_tab_consu_eval (i).VPI_IND_DIAS_ATRA_0021 = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DA21_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DA21_0'),0);
                            END IF;
                          
                            IF lv_tab_consu_eval (i).VPI_FLG_DA168_ULT9_CAMP = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DEPM168D_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DEPM168D_0'),0);
                            END IF;
                            
                            
                            IF lv_tab_consu_eval (i).VPI_INDI_SEGU = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'FLAGINSCSEGURO_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'FLAGINSCSEGURO_0'),0);
                            END IF;
                            
                            IF lv_tab_consu_eval (i).VPI_CONS_3ULT_CAMP = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'CONSC3CAMP_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'CONSC3CAMP_0'),0);
                            END IF;
                            
                            IF lv_tab_consu_eval (i).VPI_FLG_DA21_ULT9_CAMP = '1' THEN
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DEPM21D_1'),0);
                            ELSE
                                lnsumaProducto := lnsumaProducto + NVL(valor_peso(lnoidgrupo|| 'DEPM21D_0'),0);
                            END IF;

                lnProbInc := exp(lnsumaProducto)/(exp(lnsumaProducto)+1);

                            IF lnParamFormulaPI != '1' THEN
                                lnProbInc := 1 - lnProbInc; 
                            END IF;

                       ELSE
                            -- TOMAMOS EL PI DE LA CAMPAÑA ANTERIOR
                           SELECT vcc_prob_incu
                             INTO lnProbInc
                             FROM flx_varia_calcu_model
                            WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                              AND cam_cerr = lsCampanyaAnterior;                        
                       END IF;
                       
                lv_tab_consu_eval (i).vcc_prob_incu := lnProbInc;

                       -- Campania de evaluacion la PI
                       lv_tab_consu_eval (i).vcc_camp_eval := lsCampanyaCierreMas1;
                       -- Campania de comunicacion
                       lv_tab_consu_eval (i).vcc_camp_comu := lsCampanyaCierreMas1;
                       -- Campania de facturacion
                       lv_tab_consu_eval (i).vcc_camp_fact := lsCampanyaCierreMas2;

                        -- Verificamos si la consultora es habil en la campaña anterior
                        -- Verificamos la campaña de facturacion que se registro en la campaña de cierre menos 1
                        /*
                        --Cierre Anterior
                        cierre 04
                        evalu 05
                        comu 05
                        fact  06  <---------

                        --Cierre Actual
                        cierre 05
                        evalu 06
                        comu 06
                        fact  07
                        */

                        SELECT COUNT(*)
                           INTO lnCantidadHabil
                          FROM flx_consu_habil_flexi
                         WHERE cod_pais = pscodigopais AND 
                                      cod_clie = lv_tab_consu_eval (i).cod_clie AND 
                                      cod_peri_fact = lsCampanyaCierreMas1 AND
                                      ind_habi = 1;
                    
                        IF (lnProbInc <= lnProbIncumpMax OR lnCantidadHabil > 0) THEN
                            lv_tab_consu_eval (i).vcc_esta_proc := estado_aprobada;
                            lv_tab_consu_eval (i).cod_esre := '01';
                            lv_tab_consu_eval (i).cod_more := '01';
                            lv_tab_consu_eval (i).vcc_indi_apro := 'A';                                

                            flagInsertHabil := '1';
                        
                        ELSIF lnProbInc > lnProbIncumpMax  THEN
                            lv_tab_consu_eval (i).vcc_esta_proc := estado_pre_rechazada;
                            lv_tab_consu_eval (i).cod_esrc := '01';
                            lv_tab_consu_eval (i).cod_morc := '01';
                            lv_tab_consu_eval (i).vcc_indi_apro := 'N';
                        
                    END IF;
               -- FIN (2) --

               -- (3) CALCULO DEL PEDIDO BASE -----------------------------------------------------------------------------------------------------------------
               lnPedidoBase := 0;
                       
               -- Verificamos en que periodo esta la Campania DE CIERRE +3 CX+3
               IF lnPeriodo = 1 THEN
                  --Se encuentra en el periodo 1
                  -- Verificamos si ya tiene pedido base
                  BEGIN
                     SELECT vcc_pedi_base
                       INTO lnPedidoBase
                       FROM flx_varia_calcu_model
                      WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                        AND vcc_camp_pblc >= lsCampInicPerioUno
                        AND vcc_camp_pblc <= lsCampFinaPerioUno
                        AND ROWNUM = 1;
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        lnPedidoBase := 0;
                  END;
               ELSIF lnPeriodo = 2 THEN
                  --Se encuentra en el periodo 2
                  -- Verificamos si ya tiene pedido base
                  BEGIN
                     SELECT vcc_pedi_base
                       INTO lnPedidoBase
                       FROM flx_varia_calcu_model
                      WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                        AND vcc_camp_pblc >= lsCampInicPerioDos
                        AND vcc_camp_pblc <= lsCampFinaPerioDos
                        AND ROWNUM = 1;
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        lnPedidoBase := 0;
                  END;
               ELSIF lnPeriodo = 3 THEN
                  --Se encuentra en el periodo 3
                  -- Verificamos si ya tiene pedido base
                  BEGIN
                     SELECT vcc_pedi_base
                       INTO lnPedidoBase
                       FROM flx_varia_calcu_model
                      WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                        AND vcc_camp_pblc >= lsCampInicPerioTres
                        AND vcc_camp_pblc <= lsCampFinaPerioTres
                        AND ROWNUM = 1;
                  EXCEPTION
                     WHEN NO_DATA_FOUND
                     THEN
                        lnPedidoBase := 0;
                  END;
               END IF;

               -- --
               IF lnPedidoBase = 0 THEN
                  -- NO TIENE PEDIDO BASE

                  -- Se calcula el pedido base para la CX+3

                  -- Obtenemos la cantidad de pedidos de la campania de evaluacion

                  IF lnPeriodo=1 THEN

                       lnCantidadPedidosCp:=lv_tab_consu_eval (i).VLC_FREC_COP1_ANOA;

                  ELSIF lnPeriodo=2 THEN

                       lnCantidadPedidosCp:= lv_tab_consu_eval (i).VLC_FREC_COP2_ANOA;

                  ELSIF lnPeriodo=3 THEN

                       lnCantidadPedidosCp:= lv_tab_consu_eval (i).VLC_FREC_COP3_ANOA;

                  END IF;

                  -- Obtenemos la cantidad de pedidos del ANIO PASADO
--                           lnCantidadPedidosAp:=lv_tab_consu_eval (i).VLC_FREC_COP1_ANOA+ lv_tab_consu_eval (i).VLC_FREC_COP2_ANOA + lv_tab_consu_eval (i).VLC_FREC_COP3_ANOA;
                          lnFrecuenciaUltima9Camp:= lv_tab_consu_eval (i).VPI_FREC_ULT9_CAMP;

                  -- lnCantidadPedidosCp: pedidos en la campania del periodo de analisis--
                          -- lnFrecuenciaUltima9Camp: pedidos en las ultimas 9 campañas--
                          IF lnCantidadPedidosCp > 3 AND lnFrecuenciaUltima9Camp > 4 THEN --caso 1
                  lnCasoPB:=1;

                    /* VPB_PROM_VDCP_1ERP:    Promedio de venta demandada de los pedidos realizados en el primer periodo del anio pasado
                                            cerrado sin tomar en cuenta el mayor pedido del periodo de la consultora.*/
                    /* vpb_prvd_vcmn_anpc: Promedio de venta demandada a valor catálogo mn de las 18 campanias con pedido
                                           de la consultora en el año pasado cerrado.*/
                    /* vpb_prvd_vcmn_18ca: Promedio de venta demandada a valor catalogo mn de las últimas 18 campañas de la
                                            consultora.*/
                    /*vlc_prom_vdvc_ul18_cazo: Promedio de venta demandada a valor catálogo mn de las últimas 18 campanias con
                                               paso de pedido de la zona de la consultora.*/
                     IF lnPeriodo    = 1 THEN

                                if lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons1   :=  null;
                                else
                                    lnvarcons1   :=  (lv_tab_consu_eval(i).vpb_prom_vdcp_1erp / lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc);  --p1--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons1 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons1 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons1 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else lnvarcons1 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     ELSIF lnPeriodo = 2 THEN

                                if lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons2   :=  null;
                                else
                                    lnvarcons2   :=  (lv_tab_consu_eval(i).vpb_prom_vdcp_2dop / lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc);  --p2--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons2 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons2 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons2 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else   lnvarcons2 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     ELSIF lnPeriodo = 3 THEN

                                if lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons3   :=  null;
                                else
                                    lnvarcons3   :=  (lv_tab_consu_eval(i).vpb_prom_vdcp_3erp / lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc);  --p3--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons3 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons3 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons3 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else   lnvarcons3 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     END IF;

                     /*VPB_PROM_VDCP_1ERP:    Promedio de venta demandada de los pedidos realizados en el primer periodo del año pasado
                                              cerrado sin tomar en cuenta el mayor pedido del periodo de la consultora.*/
                     /*vpb_prvd_vcmn_apcz: Promedio de venta demandada a valor catálogo mn de las 18 campañas con pedido
                                                de la zona de la consultora en el año pasado cerrado.*/
                     /*vpb_prvd_vcmn_18ca: Promedio de venta demandada a valor catalogo mn de las últimas 18 campañas de la consultora.*/

                     /*vlc_prom_vdvc_ul18_cazo: Promedio de venta demandada a valor catálogo mn de las últimas 18 campañas con
                                               paso de pedido de la zona de la consultora.*/
                     /* vpb_prvd_vcmn_anpc: Promedio de venta demandada a valor catálogo mn de las 18 campanias con pedido
                                           de la consultora en el año pasado cerrado.*/                          
                  ELSIF lnCantidadPedidosCp <= 3 AND lnFrecuenciaUltima9Camp > 4 THEN --caso 2--
                     lnCasoPB:=2;
                     IF lnPeriodo = 1    THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons1   :=  null;
                                else
                                    lnvarcons1   :=  (lv_tab_consu_eval (i).vpb_pvdc_p1zo / lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p1--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons1 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons1 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons1 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else   lnvarcons1 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     ELSIF lnPeriodo = 2 THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons2   :=  null;
                                else
                                    lnvarcons2   :=  (lv_tab_consu_eval (i).vpb_pvdc_p2zo / lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p2--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons2 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons2 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons2 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else   lnvarcons2 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     ELSIF lnPeriodo = 3 THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons3   :=  null;
                                else
                                    lnvarcons3   :=  (lv_tab_consu_eval (i).vpb_pvdc_p3zo / lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p3--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons3 = null then lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons3 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               when lnvarcons3 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                               else   lnvarcons3 * lv_tab_consu_eval(i).vpb_prvd_vcmn_09ca
                                                           end;

                     END IF;


                     /*vpb_pvdc_p1zo:      Promedio de venta los pedidos realizados en el primer periodo del año pasado cerrado sin
                                           tomar en cuenta el mayor pedido del periodo de la zona de la consultora.*/
                     /*vpb_prvd_vcmn_anpc: Promedio de venta demandada a valor catálogo mn de las 18 campañas con pedido */
                     /*vpb_prvd_vcmn_18ca: Promedio de venta demandada a valor catalogo mn de las últimas 18 campañas de la
                                            consultora.*/
                    /*vlc_prom_vdvc_ul18_cazo: Promedio de venta demandada a valor catálogo mn de las últimas 18 campañas con
                                               paso de pedido de la zona de la consultora.*/
                          ELSIF lnCantidadPedidosCp > 3 AND lnFrecuenciaUltima9Camp <= 4 THEN --caso 3--
                  lnCasoPB:=3;
                     IF lnPeriodo = 1    THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons1   :=  null;
                                else
                                    lnvarcons1   :=  (lv_tab_consu_eval (i).vpb_prom_vdcp_1erp/ lv_tab_consu_eval (i).vpb_prvd_vcmn_anpc);  --p1--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons1 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons1 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons1 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons1 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     ELSIF lnPeriodo = 2 THEN

                                if lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons2   :=  null;
                                else
                                    lnvarcons2   :=  (lv_tab_consu_eval (i).vpb_prom_vdcp_2dop / lv_tab_consu_eval (i).vpb_prvd_vcmn_anpc);  --p2--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons2 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons2 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons2 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons2 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     ELSIF lnPeriodo = 3 THEN

                                if lv_tab_consu_eval(i).vpb_prvd_vcmn_anpc = 0 then
                                    lnvarcons3   :=  null;
                                else
                                    lnvarcons3   :=  (lv_tab_consu_eval (i).vpb_prom_vdcp_3erp/ lv_tab_consu_eval (i).vpb_prvd_vcmn_anpc);  --p3--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons3 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons3 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons3 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons3 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     END IF;
                     /* vpb_pvdc_p1zo:         Promedio de venta los pedidos realizados en el primer periodo del año pasado cerrado sin
                                               tomar en cuenta el mayor pedido del periodo de la zona de la consultora.*/
                     /* vpb_prvd_vcmn_apcz:     Promedio de venta demandada a valor catálogo mn de las 18 campañas con pedido
                                               de la zona de la consultora en el año pasado cerrado.*/
                     /*vpb_prvd_vcmn_18ca:      Promedio de venta demandada a valor catalogo mn de las últimas 18 campañas de la consultora. */
                     /*vlc_prom_vdvc_ul18_cazo: Promedio de venta demandada a valor catálogo mn de las últimas 18 campañas con
                                               paso de pedido de la zona de la consultora.*/

                          ELSIF lnCantidadPedidosCp <= 3 AND lnFrecuenciaUltima9Camp <= 4 THEN --caso 4--
                  lnCasoPB:=4;
                     IF lnPeriodo = 1   THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons1   :=  null;
                                else
                                    lnvarcons1   :=  (lv_tab_consu_eval (i).vpb_pvdc_p1zo/ lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p1--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons1 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons1 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons1 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons1 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     ELSIF lnPeriodo = 2 THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons2   :=  null;
                                else
                                    lnvarcons2   :=  (lv_tab_consu_eval (i).vpb_pvdc_p2zo / lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p2--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons2 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons2 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons2 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons2 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     ELSIF lnPeriodo = 3 THEN

                                if lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz = 0 then
                                    lnvarcons3   :=  null;
                                else
                                    lnvarcons3   :=  (lv_tab_consu_eval (i).vpb_pvdc_p3zo/ lv_tab_consu_eval (i).vpb_prvd_vcmn_apcz);  --p3--
                                end if;
                                
                                lnpedidobase := case 
                                                               when lnvarcons3 = null then lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons3 <= lnParamDescuentoMinimo then lnParamDescuentoMinimo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               when lnvarcons3 >= lnParamDescuentoMaximo then lnParamDescuentoMaximo * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                               else   lnvarcons3 * lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca
                                                           end;

                     END IF;
                  END IF;

                  /* lnCalculoPb : el decimal se redondea a la mitad mas cercano */
                          lnpedidobase := (trunc(lnpedidobase/10)*10) + 10;
                  
                  -- Validando Pedido Base Minimo--
                  -- --
                          IF lnPedidoBase < lnParamPbMinimo THEN
                  
                     lv_tab_consu_eval (i).vcc_pedi_base :=lnParamPbMinimo;
                     
                          ELSIF lnPedidoBase >= lnParamPbMinimo THEN
                  
                             lv_tab_consu_eval (i).vcc_pedi_base := lnPedidoBase;   
                  
                  END IF;

               ELSE -- PB mayor que cero--
                  lv_tab_consu_eval (i).vcc_pedi_base := lnPedidoBase;
               END IF;


                       lv_tab_consu_eval (i).vcc_camp_pblc := lsCampanyaCierreMas2;
               -- FIN (3) --

               -- (4) CALCULO DE LA LINEA DE CREDITO --
               lnLineadeCredito := 0;

                       -- Verificamos si la cliente ya esta inscrita en el programa
                select count(1)
                into lnCantidad
                from flx_consu_habil_flexi f
                where f.cod_pais = pscodigopais
                and f.cod_clie = lv_tab_consu_eval (i).cod_clie
                and f.ind_Acti = '1'
                AND f.cod_peri_fact = lv_tab_consu_eval (i).vcc_camp_comu;
               -- --

               IF lnCantidad > 0 THEN
                   --Ya tiene linea de credito y se mantiene
                    BEGIN
                        Select VAL_LINE_CRED
                        into lnLineadeCredito
                        from flx_consu_habil_flexi f
                        where f.cod_pais = pscodigopais
                        and f.cod_clie = lv_tab_consu_eval (i).cod_clie
                        and f.ind_acti = '1'
                        AND f.cod_peri_fact = lv_tab_consu_eval (i).vcc_camp_comu;
                    
                                flagActivaCampanyaAnterior := '1';
                            
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            lnLineadeCredito := 0;
                    END;
                    
                            IF lnLineadeCredito < lnParamFactLdcMin THEN
                                lnLineadeCredito := lnParamFactLdcMin;
                            END IF;
                            
                    -- Verificamos si tiene LDC precargada
                    BEGIN
                        SELECT VAL_LIDC 
                        INTO lnLineadeCreditoPre
                        FROM FLX_REGIS_MANUA_LINDC 
                        WHERE COD_PAIS = psCodigoPais 
                        AND CAM_PROC = lv_tab_consu_eval (i).vcc_camp_fact 
                        AND COD_CLIE = lv_tab_consu_eval (i).cod_clie;
                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            lnLineadeCreditoPre := -1;
                    END;                    
                    -- --
                    
                    IF lnLineadeCreditoPre > -1 THEN
                        lnLineadeCredito := lnLineadeCreditoPre;
                    END IF;

                    lv_tab_consu_eval (i).vcc_line_cred := lnLineadeCredito;
                    -- --
               ELSE
               
                   IF lnPeriodo = 1 THEN
                      --Se encuentra en el periodo 1
                      -- Verificamos si ya tiene pedido base
                      BEGIN
                         SELECT vcc_line_cred
                           INTO lnLineadeCredito
                           FROM flx_varia_calcu_model
                          WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                            AND vcc_camp_pblc >= lsCampInicPerioUno
                            AND vcc_camp_pblc <= lsCampFinaPerioUno
                            AND ROWNUM = 1;
                      EXCEPTION
                         WHEN NO_DATA_FOUND
                         THEN
                            lnLineadeCredito := 0;
                      END;
                   ELSIF lnPeriodo = 2 THEN
                      --Se encuentra en el periodo 2
                      -- Verificamos si ya tiene pedido base
                      BEGIN
                         SELECT vcc_line_cred
                           INTO lnLineadeCredito
                           FROM flx_varia_calcu_model
                          WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                            AND vcc_camp_pblc >= lsCampInicPerioDos
                            AND vcc_camp_pblc <= lsCampFinaPerioDos
                            AND ROWNUM = 1;
                      EXCEPTION
                         WHEN NO_DATA_FOUND
                         THEN
                            lnLineadeCredito := 0;
                      END;
                   ELSIF lnPeriodo = 3 THEN
                      --Se encuentra en el periodo 3
                      -- Verificamos si ya tiene pedido base
                      BEGIN
                         SELECT vcc_line_cred
                           INTO lnLineadeCredito
                           FROM flx_varia_calcu_model
                          WHERE cod_clie = lv_tab_consu_eval (i).cod_clie
                            AND vcc_camp_pblc >= lsCampInicPerioTres
                            AND vcc_camp_pblc <= lsCampFinaPerioTres
                            AND ROWNUM = 1;
                      EXCEPTION
                         WHEN NO_DATA_FOUND
                         THEN
                  lnLineadeCredito := 0;
                      END;
                   END IF;
               
                  IF lnLineadeCredito = 0 THEN

                  -- NO TIENE LINEA DE CREDITO
                  -- Se calcula la Linea de Credito para la CX+3
                  -- Se obtiene el Promedio de venta y se guarda en la variable: lnPromedioDeVenta
                          -- vpb_prvd_vcmn_18ca: Promedio de venta demandada a valor catalogo mn de las últimas 18 campañas de la consultora.

                  lnPromedioDeVentaEntero := round(lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca);
                  
                  lnPromedioDeVenta := lv_tab_consu_eval (i).vpb_prvd_vcmn_18ca;
                  lnSumaValorSegmento := 0;

                SELECT
                (SELECT NVL(SUM(VAL_SEGM), 0) FROM FLX_PARAM_RGLDC WHERE EST_REGI = 1 AND VAL_HAST <= lnPromedioDeVentaEntero) +
                NVL((SELECT (lnPromedioDeVentaEntero - VAL_DESD)*VAL_FACT FROM FLX_PARAM_RGLDC WHERE EST_REGI = 1 AND lnPromedioDeVentaEntero+0.1 BETWEEN VAL_DESD AND VAL_HAST), 0)
                INTO lnSumaValorSegmento
                FROM DUAL; 

                          lnLineadeCredito := TRUNC(lnSumaValorSegmento/10)*10; --REDONDEAMOS A LA DECENA INFERIOR
                  
                          IF lnLineadeCredito < lnParamFactLdcMin THEN
                            lnLineadeCredito := lnParamFactLdcMin;
                          END IF;
                          
                  END IF;
                    

                  lv_tab_consu_eval (i).vcc_line_cred := lnLineadeCredito;

               END IF;
               END IF;

            IF flagInsertHabil = '1' AND lv_tab_consu_eval (i).vcc_esta_proc = estado_aprobada  THEN
                           INSERT INTO flx_consu_habil_flexi (
                                COD_PAIS,
                                COD_CLIE,
                                COD_PERI_FACT,
                                COD_PERI_COMU,
                                VAL_LINE_CRED,
                                VAL_PEDI_BASE,
                                IND_CALI_COMP,
                                IND_HABI,
                                IND_ACTI,
                                USU_DIGI,
                                FEC_DIGI,
                                IND_CALI_EXPE_FLEX)
                            VALUES (
                                pscodigopais,
                                lv_tab_consu_eval (i).cod_clie,
                                lv_tab_consu_eval (i).vcc_camp_fact,
                                lv_tab_consu_eval (i).vcc_camp_comu,
                                lv_tab_consu_eval (i).vcc_line_cred,
                                lv_tab_consu_eval (i).vcc_pedi_base,
                                lv_tab_consu_eval (i).vcp_comp_pago,
                                1,
                                flagActivaCampanyaAnterior,
                                psusuario,
                                sysdate,
                                lv_tab_consu_eval (i).vce_codi_cali);
            END IF;

               -- Actualizamos la tabla con los valores calculados
               UPDATE   FLX_VARIA_CALCU_MODEL
               SET
               vcc_camp_cier = lv_tab_consu_eval (i).cam_cerr,
               vcc_prob_incu = lv_tab_consu_eval (i).vcc_prob_incu,
               vcc_camp_comu = lv_tab_consu_eval (i).vcc_camp_comu,
               vcc_camp_eval = lv_tab_consu_eval (i).vcc_camp_eval,
               vcc_camp_pblc = lv_tab_consu_eval (i).vcc_camp_pblc,
               vcc_pedi_base = lv_tab_consu_eval (i).vcc_pedi_base,
               vcc_line_cred = lv_tab_consu_eval (i).vcc_line_cred,
               vcc_esta_proc = lv_tab_consu_eval (i).vcc_esta_proc,
               vcc_camp_fact = lv_tab_consu_eval (i).vcc_camp_fact,
               cod_esrc = lv_tab_consu_eval (i).cod_esrc,
               cod_morc = lv_tab_consu_eval (i).cod_morc,
               cod_esre = lv_tab_consu_eval (i).cod_esre,
               cod_more = lv_tab_consu_eval (i).cod_more,
               vcc_indi_apro = lv_tab_consu_eval (i).vcc_indi_apro,
               vcc_esta_prea = lv_tab_consu_eval (i).vcc_esta_proc,
               vcc_indi_regz = lv_tab_consu_eval (i).vcc_indi_regz,
               vcc_indi_rcgz = lv_tab_consu_eval (i).vcc_indi_rcgz,          
               vpi_codi_regi = lv_tab_consu_eval (i).vpi_codi_regi,          
               usu_modi = psUsuario, 
               fec_modi = sysdate
               WHERE cam_cerr = lv_tab_consu_eval (i).cam_cerr AND 
                            cod_clie = lv_tab_consu_eval (i).cod_clie;
               -- --               
            END LOOP;
         END IF;

         EXIT WHEN c_consu_eval%NOTFOUND;
      END LOOP;

      CLOSE c_consu_eval;


   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,'FLX_PR_EVALU_CONSU: ' || ls_sqlerrm);
   END flx_pr_evalu_consu;

   /**************************************************************************
   Descripcion       :    Devuelve el peso de una variable en base al grupo y nombre
   Fecha Creacion    :    01/06/2013
   Autor             :    Ivan Tocto
   Parametros Entrada:
      pnOidGrupo          OID del grupo
      psNombreVariable    Nombre de la varialbe
   Parametros Salida :
               Peso de la variable
   ***************************************************************************/
   FUNCTION flx_fn_obtie_peso_varia (
      pnoidgrupo         flx_grupo.oid_grup%TYPE,
      psnombrevariable   flx_varia.nom_vari%TYPE
   )
      RETURN NUMBER
   IS
      lnpesovariable   flx_grupo_varia.val_peso%TYPE;
   BEGIN
      SELECT gv.val_peso
        INTO lnpesovariable
        FROM flx_grupo_varia gv, flx_varia va
       WHERE gv.vari_oid_vari = va.oid_vari
         AND gv.grup_oid_grup = pnoidgrupo
         AND va.nom_vari = psnombrevariable
         AND gv.est_Regi = 1
         AND va.est_Regi = 1;

      RETURN lnpesovariable;
   END flx_fn_obtie_peso_varia;

   /**************************************************************************
   Descripcion       :    Devuelve el periodo al que corresponde la campaña
   Fecha Creacion    :    01/06/2013
   Autor             :    Ivan Tocto
   Parametros Entrada:
      psCampanya            Campaña
   Parametros Salida :
               Periodo al que pertenece la campaña (1, 2, 3)
   ***************************************************************************/
   FUNCTION flx_fn_obtie_perio (pscampanya VARCHAR2)
      RETURN NUMBER 
   IS
      ln_periodo   NUMBER;
   BEGIN
      ln_periodo := 0;

      BEGIN
         SELECT oid_para
           INTO ln_periodo
           FROM flx_param
          WHERE cod_grup = '02'
            AND cod_para = '001'
            AND EST_REGI = 1
            AND TO_NUMBER (SUBSTR (val_para, 2, 2)) <=TO_NUMBER (SUBSTR (pscampanya, 5, 2))
            AND TO_NUMBER (SUBSTR (val_para, 6, 2)) >=TO_NUMBER (SUBSTR (pscampanya, 5, 2));

         ln_periodo := 1;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            ln_periodo := 0;
      END;

      IF ln_periodo = 0
      THEN
         BEGIN
            SELECT oid_para
              INTO ln_periodo
              FROM flx_param
             WHERE cod_grup = '02'
               AND cod_para = '002'
               AND EST_REGI = 1
               AND TO_NUMBER (SUBSTR (val_para, 2, 2)) <=TO_NUMBER (SUBSTR (pscampanya, 5, 2))
               AND TO_NUMBER (SUBSTR (val_para, 6, 2)) >=TO_NUMBER (SUBSTR (pscampanya, 5, 2));

            ln_periodo := 2;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               ln_periodo := 0;
         END;

         IF ln_periodo = 0
         THEN
            BEGIN
               SELECT oid_para
                 INTO ln_periodo
                 FROM flx_param
                WHERE cod_grup = '02'
                  AND cod_para = '003'
                  AND EST_REGI = 1
                  AND TO_NUMBER (SUBSTR (val_para, 2, 2)) <=TO_NUMBER (SUBSTR (pscampanya, 5, 2))
                  AND TO_NUMBER (SUBSTR (val_para, 6, 2)) >=TO_NUMBER (SUBSTR (pscampanya, 5, 2));

               ln_periodo := 3;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  ln_periodo := 0;
            END;
         END IF;
      END IF;

      RETURN ln_periodo;
   END flx_fn_obtie_perio;
   
   /**************************************************************************
   Descripcion            :    Proceso que se encarga de calcular las variables de cuenta corrientes 
                                      del nuevo modelo de flexipago
   Fecha Creacion     :    05/11/2013
   Autor                    :    Sebastian Guerra
   Parametros Entrada:
      psCodigoPais              Codigo del Pais
      psCampanyaCierre    Campaña de Cierre
      psUsuario                   Usuario que ejecuta el proceso
   ***************************************************************************/
   PROCEDURE flx_pr_calcu_varia_cuent_corri (
      pscodigopais             VARCHAR2,
      pscampanyacierre    VARCHAR2,
      pscampanyacierreregion    VARCHAR2
   )
   IS
   
   lsCampanyaInicio             VARCHAR2(6);
   lsOidCampanyaInicio       NUMBER;
   lsOidCampanyaCierre      NUMBER;
   lsOidCampanyaCierreRegion      NUMBER;
   sqlAlterSession                VARCHAR2(100);
   sqlQuery                          VARCHAR2(100);
   lsCampanyaCierreMenos1             VARCHAR2(6);
   lnOidCampanyaCierreMenos1        NUMBER;
   
   lsCampanyaPagoMenos2             VARCHAR2(6);
   lnOidCampanyaPagoMenos2        NUMBER;

   lsCampanyaPagoMenos5             VARCHAR2(6);
   lnOidCampanyaPagoMenos5        NUMBER;

   lsCampanyaPagoMenos8             VARCHAR2(6);
   lnOidCampanyaPagoMenos8        NUMBER;

   BEGIN
   
    lsCampanyaInicio            := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,pscampanyacierre, -9);
    lsOidCampanyaInicio      := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanyaInicio);
    lsOidCampanyaCierre     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscampanyacierre);
    lsOidCampanyaCierreRegion     := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscampanyacierreregion);
    
    lsCampanyaCierreMenos1 := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,pscampanyacierre, -1);
    lnOidCampanyaCierreMenos1 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanyaCierreMenos1);
    
   lsCampanyaPagoMenos2 := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampanyaCierreMenos1, -2);
   lnOidCampanyaPagoMenos2 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanyaPagoMenos2);

   lsCampanyaPagoMenos5 := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampanyaCierreMenos1, -5);
   lnOidCampanyaPagoMenos5 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanyaPagoMenos5);

   lsCampanyaPagoMenos8 := gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais,lsCampanyaCierreMenos1, -8);
   lnOidCampanyaPagoMenos8 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanyaPagoMenos8);

    sqlQuery := 'truncate table flx_tempo_cuent_corri_cargo';
    execute immediate (sqlQuery);
    sqlQuery := 'truncate table flx_tempo_cuent_corri_abono';
    execute immediate (sqlQuery);
    sqlQuery := 'truncate table flx_tempo_cuent_corri_merg';
    execute immediate (sqlQuery);
    sqlQuery := 'truncate table flx_varia_cuent_corri_base';
    execute immediate (sqlQuery);
    
    sqlAlterSession := 'alter session set skip_unusable_indexes = true';
    execute immediate (sqlAlterSession);

    sqlAlterSession := 'alter index tempo_cuent_corri_cargo_idx unusable';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index tempo_cuent_corri_carg1_idx unusable';
    execute immediate (sqlAlterSession);
       
    insert into flx_tempo_cuent_corri_cargo(
        num_iden_carg,
        oid_clie,
        num_orde_cuot,
        fec_carg,
        cam_orig,
        num_bole_desp,
        fec_venc,
        imp_movi,
        cam_pago,
        oid_regi)
    select
      mcc.oid_movi_cc num_iden_carg,
      mcc.clie_oid_clie oid_clie,
      mcc.num_orde_cuot,      
      trunc(mcc.fec_docu) fec_carg,
      psc.perd_oid_peri cam_orig, 
      to_char(psc.val_nume_soli) num_bole_desp,
      trunc(mcc.fec_venc) fec_venc,
      mcc.imp_movi,
      mcc.perd_oid_peri cam_pago,
      mcc.zorg_oid_regi oid_regi
    from 
      ccc_movim_cuent_corri mcc,
      ped_solic_cabec psc,
      ccc_proce pr,
      ccc_subpr su
    where mcc.soca_oid_soli_cabe = psc.oid_soli_cabe
       and mcc.subp_oid_subp_crea = su.oid_subp
       and pr.oid_proc = su.ccpr_oid_proc
       and mcc.subp_oid_subp_crea = 2001
       and mcc.imp_movi > 0
       and psc.perd_oid_peri >= lsOidCampanyaInicio
       and psc.perd_oid_peri < lsOidCampanyaCierre
    union all
    select 
       mcc.oid_movi_cc num_iden_carg,
       mcc.clie_oid_clie oid_clie,
       mcc.num_orde_cuot,       
       trunc(mcc.fec_docu) fec_carg, 
       mcc.perd_oid_peri cam_orig, 
       '           ' num_bole_desp,
       trunc(mcc.fec_venc) fec_venc, 
       mcc.imp_movi,
       mcc.perd_oid_peri cam_pago,
       mcc.zorg_oid_regi oid_regi
    from 
       ccc_movim_cuent_corri mcc,
       ccc_proce pr,
       ccc_subpr su
    where mcc.subp_oid_subp_crea = su.oid_subp
       and pr.oid_proc = su.ccpr_oid_proc
       and mcc.subp_oid_subp_crea <> 2001
       and mcc.imp_movi > 0
       and mcc.perd_oid_peri >= lsOidCampanyaInicio
       and mcc.perd_oid_peri < lsOidCampanyaCierre;
    
    sqlAlterSession := 'alter index tempo_cuent_corri_cargo_idx rebuild online';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index tempo_cuent_corri_carg1_idx rebuild online';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index tempo_cuent_corri_abono_idx unusable';
    execute immediate (sqlAlterSession);
    
    insert into flx_tempo_cuent_corri_abono(
        num_iden_abon,
        cod_tipo_abon_datm,
        num_iden_carg,
        num_orde_cuot,
        oid_clie,
        fec_pago_banc,
        fec_proc,
        imp_pago,
        cam_abon,
        imp_pend)
    select
        abo.oid_movi_abon num_iden_abon, 
        case
        when abo.ind_tipo_abon_nmon = 0 then
         '001'
        else
         '002'             
        end cod_tipo_abon_datm,  
        mcc.oid_movi_cc num_iden_carg,
        mcc.num_orde_cuot,
        mcc.clie_oid_clie oid_clie,
        case
        when  abo.ind_tipo_abon_nmon = 0 then
         trunc(abo.fec_apli) 
        else 
         null
        end fec_pago_banc,
        trunc(abo.fec_proc)  fec_proc,
        abs(abo.imp_abon) imp_pago, 
        (select min(spc.cod_peri)
        from cra_perio cp,
                seg_perio_corpo spc
        where cp.peri_oid_peri = spc.oid_peri 
            and trunc(cp.fec_inic) <= trunc(abo.fec_proc)
            and trunc(cp.fec_fina) >= trunc(abo.fec_proc)) cam_abon,
        mcc.imp_pend
    from 
        ccc_aplic_abono_cargo abo,
        ccc_movim_cuent_corri mcc,
        ccc_proce pr,
        ccc_subpr su
    where mcc.subp_oid_subp_crea = su.oid_subp
        and abo.mvcc_oid_movi_carg = mcc.oid_movi_cc
        and abo.clie_oid_clie = mcc.clie_oid_clie
        and pr.oid_proc = su.ccpr_oid_proc
        and mcc.perd_oid_peri >= lsOidCampanyaInicio
        and mcc.perd_oid_peri < lsOidCampanyaCierre;
    
    sqlAlterSession := 'alter index tempo_cuent_corri_abono_idx rebuild online';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index tempo_cuent_corri_merg_idx unusable';
    execute immediate (sqlAlterSession);
    
    insert into flx_tempo_cuent_corri_merg(
        oid_clie,
        num_iden_carg,
        num_orde_cuot,
        fec_pago,
        fec_pago_banc,
        fec_proc,
        imp_pago,
        cam_pago,
        fec_carg,
        fec_venc,
        fec_cier,
        ind_marc_abon)
    select 
      car.oid_clie oid_clie,
      car.num_iden_carg,
      car.num_orde_cuot,
      case when abo.fec_pago_banc is null then 
          abo.fec_proc 
      else 
          abo.fec_pago_banc 
      end fec_pago,
      abo.fec_pago_banc,
      abo.fec_proc,
      nvl(abo.imp_pago, 0) imp_pago,
      car.cam_pago,
      car.fec_carg,
      car.fec_venc,
      (select max(con.fec_cier)
          from fac_contr_cierr con,
               fac_tipos_cierr tip,
               cra_perio cra,
               seg_perio_corpo cor,
               seg_pais pai,
               zon_regio reg
         where con.tcie_oid_tipo_cier = tip.oid_tipo_cier
           and cra.oid_peri = con.perd_oid_peri
           and cor.oid_peri = cra.peri_oid_peri
           and pai.oid_pais = cra.pais_oid_pais
           and reg.oid_regi = con.zorg_oid_regi
           and tip.cod_tipo_cier = 'R'
           and pai.cod_pais = pscodigopais
           and reg.oid_regi = car.oid_regi
           and con.perd_oid_peri = lsOidCampanyaCierreRegion
      ) fec_cier,
      (case when (case when abo.fec_pago_banc is null then 
                              abo.fec_proc 
                          else 
                              abo.fec_pago_banc 
                          end) <= car.fec_venc then 
          1
       else 
          0
       end) ind_marc_abon
    from flx_tempo_cuent_corri_cargo car, 
            flx_tempo_cuent_corri_abono abo
    where car.oid_clie = abo.oid_clie(+)
      and car.num_iden_carg = abo.num_iden_carg(+)
      and car.num_orde_cuot = abo.num_orde_cuot(+);
        
    sqlAlterSession := 'alter index tempo_cuent_corri_merg_idx rebuild online';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index flx_varia_ctacte_basei_idx unusable';
    execute immediate (sqlAlterSession);
    
    insert into flx_varia_cuent_corri_base (
        oid_clie, 
        cam_pago, 
        cam_cier,
        val_carg,
        val_abon,
        val_abon_adia,
        val_abon_venc,
        val_sald_venc,
        val_sald_xven,
        val_sald,
        ind_marc_carg,
        num_dias_atra,
        num_dias_pago,
        fec_carg, 
        fec_venc, 
        fec_pago)
    select 
      cal.oid_clie,
      cal.cam_pago,
      pscampanyacierre cam_cier,
      cal.val_carg,
      cal.val_abon,
      cal.val_abon_adia,
      cal.val_abon_venc,
      cal.val_sald_venc,
      cal.val_sald_xven,
      cal.val_sald,
      cal.ind_marc_carg,
      (case when cal.ind_marc_carg =1 then  
            case when (cal.fec_pago -  cal.fec_venc) < 0 then
                0
            else
                (cal.fec_pago -  cal.fec_venc)
            end 
       else 
            case when (cal.fec_cier -  cal.fec_venc) < 0 then 
                0
            else
                (cal.fec_cier-  cal.fec_venc)
            end
       end) num_dias_atra,
      (case when cal.ind_marc_carg =1 then 
            case when (cal.fec_pago - cal.fec_carg) < 0 then 
                0
            else
                (cal.fec_pago - cal.fec_carg)
            end 
       else 
            case when (cal.fec_cier -cal.fec_carg) < 0 then 
                0
            else
                (cal.fec_cier - cal.fec_carg)
            end
       end) num_dias_pago, 
      cal.fec_carg,
      cal.fec_venc,
       (case when cal.ind_marc_carg =1 then  
      cal.fec_pago
        else 
            cal.fec_cier
       end) fec_pago
    from(
    select car.oid_clie, 
                  car.cam_pago,
                  car.imp_movi val_carg,
              (abo.abo_adia + abo.abo_venc) val_abon,
              abo.abo_adia val_abon_adia,
              abo.abo_venc val_abon_venc,
              (car.imp_movi - abo.abo_adia) val_sald_venc,
                  (-1 * abo.abo_venc) val_sald_xven, 
              (car.imp_movi - (abo.abo_adia + abo.abo_venc)) val_sald,
                  case when car.imp_movi - (abo.abo_adia + abo.abo_venc) <= 0 then 
                      1
                  else 
                      0 
                  end ind_marc_carg, 
                  car.fec_carg,
              car.fec_venc,
                  abo.fec_pago,
                  abo.fec_cier
      from (select  
                    oid_clie, 
                        cam_pago, 
                        min (fec_carg) fec_carg, 
                    max (fec_venc) fec_venc, 
                    sum (imp_movi) imp_movi
                from flx_tempo_cuent_corri_cargo 
                    group by oid_clie, cam_pago) car, 
              (select   
                    oid_clie,
                        cam_pago,
                        max(fec_pago) fec_pago,
                        min(fec_carg) fec_carg,
                    max(fec_venc) fec_venc,
                        max(fec_cier) fec_cier,
                        sum(case when ind_marc_abon=1 then
                                imp_pago
                            else
                                0
                            end) abo_adia,
                        sum(case when ind_marc_abon=0 then
                                imp_pago
                            else
                                0
                            end) abo_venc
                from flx_tempo_cuent_corri_merg 
                    group by oid_clie, cam_pago) abo
     where car.oid_clie = abo.oid_clie and 
                    car.cam_pago = abo.cam_pago
    ) cal;
    
    sqlAlterSession := 'alter index flx_varia_ctacte_basei_idx rebuild online';
    execute immediate (sqlAlterSession);
        
    sqlAlterSession := 'alter index flx_varia_ctacte_idx unusable';
    execute immediate (sqlAlterSession);

    sqlAlterSession := 'alter index flx_varia_ctacte_camclie_idx unusable';
    execute immediate (sqlAlterSession);
    
    insert into flx_varia_cuent_corri (
        oid_clie, 
        cam_pago, 
        cam_cier,
        val_carg,
        val_abon,
        val_abon_adia,
        val_abon_venc,
        val_sald_venc,
        val_sald_xven,
        val_sald,
        ind_marc_carg,
        num_dias_atra,
        num_dias_pago,
        fec_carg, 
        fec_venc, 
        fec_pago)
    select         
        oid_clie, 
        cam_pago, 
        cam_cier,
        val_carg,
        val_abon,
        val_abon_adia,
        val_abon_venc,
        val_sald_venc,
        val_sald_xven,
        val_sald,
        ind_marc_carg,
        num_dias_atra,
        num_dias_pago,
        fec_carg, 
        fec_venc, 
        fec_pago
        from flx_varia_cuent_corri_base 
        where cam_pago =  lnOidCampanyaCierreMenos1;
        
    insert into flx_varia_cuent_corri (
        oid_clie, 
        cam_pago, 
        cam_cier)
    select distinct         
        oid_clie, 
        lnOidCampanyaCierreMenos1, 
        pscampanyacierre
    from(        
        select oid_clie
        from flx_varia_cuent_corri_base 
        where cam_pago <>  lnOidCampanyaCierreMenos1
        minus
        select oid_clie
            from flx_varia_cuent_corri_base
            where cam_pago =  lnOidCampanyaCierreMenos1);
        
    sqlAlterSession := 'alter index flx_varia_ctacte_idx rebuild online';
    execute immediate (sqlAlterSession);
    
    sqlAlterSession := 'alter index flx_varia_ctacte_camclie_idx rebuild online';
    execute immediate (sqlAlterSession);
        
    sqlAlterSession := 'analyze table flx_varia_cuent_corri_base compute statistics';
    execute immediate (sqlAlterSession);

    sqlAlterSession := 'analyze table flx_varia_cuent_corri compute statistics';
    execute immediate (sqlAlterSession);

    update flx_varia_cuent_corri vcc 
    set
    vcc.ind_dias_atra_0001=
      (case when vcc.num_dias_atra between 1 and 20 then 1 else 0 end),
    vcc.ind_dias_atra_0021=
      (case when vcc.num_dias_atra between 21 and 41 then 1 else 0 end),
    vcc.ind_dias_atra_0042=
      (case when vcc.num_dias_atra between 42 and 62 then 1 else 0 end),
    vcc.ind_dias_atra_0063=
      (case when vcc.num_dias_atra between 63 and 104 then 1 else 0 end),
    vcc.ind_dias_atra_0105=
      (case when vcc.num_dias_atra between 105 and 167 then 1 else 0 end),
    vcc.ind_dias_atra_0168=
      (case when vcc.num_dias_atra >= 168 then 1 else 0 end),
    vcc.ult_da01_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 0
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.ult_da21_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 20
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.ult_da42_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 41
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.ult_da63_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 62
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.ult_da105_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 104
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.ult_da168_ult9_camp=
        nvl((select num_dias_atra 
                from flx_varia_cuent_corri_base a 
             where a.oid_clie = vcc.oid_clie and  
                        a.cam_pago = (select max (vc.cam_pago)
                                                    from flx_varia_cuent_corri_base vc
                                                 where vc.oid_clie = vcc.oid_clie
                                                     and vc.num_dias_atra > 167
                                                     and vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago)), 0),
    vcc.flg_da01_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and 
                         vc.num_dias_atra > 0 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0) > 0
      then 1 else 0 end),
    vcc.flg_da21_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and 
                         vc.num_dias_atra > 20 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0) > 0
      then 1 else 0 end),
    vcc.flg_da42_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and
                         vc.num_dias_atra > 41 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0) > 0
      then 1 else 0 end),
    vcc.flg_da63_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and 
                         vc.num_dias_atra > 62 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago) , 0) > 0
      then 1 else 0 end),
    vcc.flg_da105_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and
                         vc.num_dias_atra > 104 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0) > 0
      then 1 else 0 end),
    vcc.flg_da168_ult9_camp=
      (case when nvl((select max (vc.cam_pago)
                         from flx_varia_cuent_corri_base vc
                         where vc.oid_clie = vcc.oid_clie and
                         vc.num_dias_atra > 167 and 
                         vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0) > 0
      then 1 else 0 end),
    vcc.pdp_ult3_camp=
      nvl((select avg (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago), 0),
    vcc.pdp_ult6_camp=
      nvl((select avg (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago), 0),
    vcc.pdp_ult9_camp=
      nvl((select avg (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc 
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.pda_ult3_camp=
      nvl((select avg (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago), 0),
    vcc.pda_ult6_camp=
      nvl((select avg (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago), 0),
    vcc.pda_ult9_camp=
      nvl((select avg (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.max_pul3_camp=
      nvl((select max (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and 
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago), 0),
    vcc.max_pul6_camp=
      nvl((select max (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago), 0),
    vcc.max_pul9_camp=
      nvl((select max (vc.num_dias_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.max_aul3_camp=
      nvl((select max (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago), 0),
    vcc.max_aul6_camp=
      nvl((select max (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago), 0),
    vcc.max_aul9_camp=
      nvl((select max (vc.num_dias_atra)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.pro_dul3_camp=
      nvl((select avg (vc.val_sald_venc)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago), 0),
    vcc.pro_dul6_camp=
      nvl((select avg (vc.val_sald_venc)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago), 0),
    vcc.pro_dul9_camp=
      nvl((select avg (vc.val_sald_venc)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.pri_da00_ult3_camp=
      (select min (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago),
    vcc.pri_da00_ult6_camp=
      (select min (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago),
    vcc.pri_da00_ult9_camp=
      (select min (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago),
    vcc.ult_da00_ult3_camp=
      (select max (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos2 and vcc.cam_pago),
    vcc.ult_da00_ult6_camp=
      (select max (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos5 and vcc.cam_pago),
    vcc.ult_da00_ult9_camp=
      (select max (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago),
    vcc.num_camp_da01_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra > 0 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.num_camp_da21_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra >= 21 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.num_camp_da42_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra >= 42 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.num_camp_da63_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra >= 63 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.num_camp_d105_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra >= 105 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0),
    vcc.num_camp_d168_ult9_camp=
      nvl((select count (vc.cam_pago)
      from flx_varia_cuent_corri_base vc
      where vc.oid_clie = vcc.oid_clie and
      vc.num_dias_atra >= 168 and 
      vc.cam_pago between lnOidCampanyaPagoMenos8 and vcc.cam_pago), 0)
      where vcc.cam_cier = pscampanyacierre;
    
    sqlQuery := 'truncate table flx_tempo_cuent_corri_cargo';
    execute immediate (sqlQuery);
    sqlQuery := 'truncate table flx_tempo_cuent_corri_abono';
    execute immediate (sqlQuery);
    sqlQuery := 'truncate table flx_tempo_cuent_corri_merg';
    execute immediate (sqlQuery);
    
   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,'FLX_PR_CALCU_VARIA_CUENT_CORRI: ' || ls_sqlerrm);
   END flx_pr_calcu_varia_cuent_corri;

   /**************************************************************************
   Descripcion            :    Proceso que se encarga de calcular las variables de cuenta corrientes
                                      del nuevo modelo de flexipago
   Fecha Creacion     :    04/03/2014
   Autor                    :    Ivan Tocto
   Parametros Entrada:
      psCodigoPais              Codigo del pais
      psCampanyaCierre    Campaña de cierre
      pnCampanyasAtras      Cantidad de campañas hacia atraz
   ***************************************************************************/   
   PROCEDURE flx_pr_calcu_varia_ctact_canca (
      pscodigopais             VARCHAR2,
      pscampanyacierre    VARCHAR2,
      pnCampanyasAtras  NUMBER
   )
   IS

   lsCampanyaCierreActual   VARCHAR2(6);
   sqlQuery                          VARCHAR2(100);

   BEGIN

    -- limpiamos la tabla, esta solo debe de tener data de las ultimas pnCampanyasAtras campañas
    sqlQuery := 'truncate table flx_varia_cuent_corri';
    execute immediate (sqlQuery);

    -- invocamos al stored que calcula los valores por campaña, N campañas    
    FOR i IN 1..pnCampanyasAtras
    LOOP
       
        SELECT gen_pkg_gener.gen_fn_perio_nsigu (pscodigopais, pscampanyacierre, (-pnCampanyasAtras + i))         
        INTO lsCampanyaCierreActual FROM DUAL;
        
        flx_pr_calcu_varia_cuent_corri(pscodigopais, lsCampanyaCierreActual, pscampanyacierre);
        
    END LOOP;    
    -- -- 

   EXCEPTION
      WHEN OTHERS
      THEN
         ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
         raise_application_error (-20123,'FLX_PR_CALCU_VARIA_CTACT_CANCA: ' || ls_sqlerrm);
         
   END flx_pr_calcu_varia_ctact_canca;

    /***************************************************************************
    Descripcion       : Valida la carga manual de lineas de credito
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_VALID_LINEA_CREDI(
        psCodigoPais VARCHAR2,
        psCodigoUsuario VARCHAR2) IS

        CURSOR C_LDCS IS
        SELECT
        NUM_FILA,
        CAM_PROC,
        COD_CLIE,
        VAL_LIDC
        FROM FLX_TEMPO_REGIS_MANUA_LINDC
        WHERE USU_DIGI = psCodigoUsuario;

        TYPE tipoLDC IS RECORD
        (
            numeroFila      FLX_TEMPO_REGIS_MANUA_LINDC.NUM_FILA%TYPE,
            campanya        FLX_TEMPO_REGIS_MANUA_LINDC.CAM_PROC%TYPE,
            codigoCliente   FLX_TEMPO_REGIS_MANUA_LINDC.COD_CLIE%TYPE,
            lineaCredito    FLX_TEMPO_REGIS_MANUA_LINDC.VAL_LIDC%TYPE
        );

        TYPE tipoLDCTab  IS TABLE OF tipoLDC;
        tipoLDCRecordN tipoLDCTab;

        lsMensajeError            FLX_TEMPO_REGIS_MANUA_LINDC.MEN_ERRO%TYPE;
        lnNumeroFila              FLX_TEMPO_REGIS_MANUA_LINDC.NUM_FILA%TYPE;
        lnTempo   NUMBER(18, 6);
        lsTempo VARCHAR2(15);
        lsCampanyaFacturacion VARCHAR2(6);

    BEGIN

        SELECT COD_PERI
        INTO lsCampanyaFacturacion
        FROM BAS_CTRL_FACT CTRL                                    
        WHERE CTRL.COD_PAIS = psCodigoPais                             
        AND CTRL.sta_camp = '0'                          
        AND CTRL.ind_camp_act = '1';

      OPEN C_LDCS;
      LOOP
        FETCH C_LDCS BULK COLLECT INTO tipoLDCRecordN LIMIT W_FILAS;
        IF tipoLDCRecordN.COUNT > 0 THEN
          FOR x IN tipoLDCRecordN.FIRST .. tipoLDCRecordN.LAST LOOP

            lnNumeroFila   := tipoLDCRecordN(x).numeroFila;
            lsMensajeError := '';

            -- (1) Validar que la cliente exista
            BEGIN
                SELECT cod_clie 
                INTO lsTempo
                FROM MAE_CLIEN WHERE COD_CLIE = tipoLDCRecordN(x).codigoCliente; 
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    lsMensajeError := lsMensajeError||'Código de cliente no existe, ';
            END;
            
            -- (2) Validar que la linea de credito sea numerico
            BEGIN
                lnTempo := TO_NUMBER(tipoLDCRecordN(x).lineaCredito, '999999999999.999999');
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError := lsMensajeError||'Linea de crédito no es numérico, ';
            END;
            -- --

            -- (3) Validar que la campaña sea mayor que la campaña de facturacion
            IF tipoLDCRecordN(x).campanya < lsCampanyaFacturacion THEN
                lsMensajeError := lsMensajeError||'La campaña debe de ser mayor a la campaña de facturación, ';
            END IF;
            -- --
            
            -- (4) Validar duplicado
            SELECT COUNT(*)
            INTO lnTempo
            FROM FLX_TEMPO_REGIS_MANUA_LINDC
            WHERE COD_CLIE = tipoLDCRecordN(x).codigoCliente
            AND CAM_PROC = tipoLDCRecordN(x).campanya;

            IF lnTempo > 1 THEN
                lsMensajeError := lsMensajeError||'Existe más de un registro con los mísmos valores, ';
            END IF;
            -- --

            IF(length(lsMensajeError) > 0) THEN
               lsMensajeError := substr(lsMensajeError,1,length(lsMensajeError)-2);
               UPDATE FLX_TEMPO_REGIS_MANUA_LINDC
               SET EST_REGI = '0',
               MEN_ERRO = lsMensajeError
               WHERE USU_DIGI = psCodigoUsuario
               AND NUM_FILA = tipoLDCRecordN(x).numeroFila;
            END IF;

          END LOOP;
        END IF;
      EXIT WHEN C_LDCS%NOTFOUND;
      END LOOP;
      CLOSE C_LDCS;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_VALID_LINEA_CREDI: '||ls_sqlerrm);
    END FLX_PR_VALID_LINEA_CREDI;

    /***************************************************************************
    Descripcion       : Realiza la carga de lineas de credito
    Fecha Creacion    : 10/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_CARGA_LINEA_CREDI(
        psCodigoPeriodo VARCHAR2,
        psCodigoUsuario VARCHAR2) IS
    BEGIN

        DELETE FLX_REGIS_MANUA_LINDC WHERE CAM_PROC = psCodigoPeriodo;

        INSERT INTO FLX_REGIS_MANUA_LINDC(
            COD_PAIS,
            CAM_PROC,
            COD_CLIE,
            VAL_LIDC,
            USU_DIGI,
            FEC_DIGI)
        SELECT
        COD_PAIS,
        CAM_PROC,
        COD_CLIE,
        TO_NUMBER(VAL_LIDC, '999999999999.999999'),
        psCodigoUsuario,
        SYSDATE
        FROM FLX_TEMPO_REGIS_MANUA_LINDC
        WHERE EST_REGI = '1'
        AND USU_DIGI = psCodigoUsuario;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_CARGA_LINEA_CREDI: '||ls_sqlerrm);
    END FLX_PR_CARGA_LINEA_CREDI;

    /***************************************************************************
    Descripcion       : Valida la carga manual de consultoras a deshabilitar
    Fecha Creacion    : 17/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_VALID_CONSU_DESHA(
        psCodigoPais VARCHAR2,
        psCodigoUsuario VARCHAR2) IS

        CURSOR C_ConsulDeshab IS
        SELECT
        NUM_FILA,
        CAM_PROC,
        COD_CLIE
        FROM FLX_TEMPO_REGIS_MANUA_CONDES
        WHERE USU_DIGI = psCodigoUsuario;

        TYPE tipoConsulDeshab IS RECORD
        (
            numeroFila      FLX_TEMPO_REGIS_MANUA_CONDES.NUM_FILA%TYPE,
            campanya        FLX_TEMPO_REGIS_MANUA_CONDES.CAM_PROC%TYPE,
            codigoCliente   FLX_TEMPO_REGIS_MANUA_CONDES.COD_CLIE%TYPE
        );

        TYPE tipoConsulDeshabTab  IS TABLE OF tipoConsulDeshab;
        tipoConsulDeshabRecordN tipoConsulDeshabTab;

        lsMensajeError            FLX_TEMPO_REGIS_MANUA_CONDES.MEN_ERRO%TYPE;
        lnNumeroFila              FLX_TEMPO_REGIS_MANUA_CONDES.NUM_FILA%TYPE;
        lnTempo   NUMBER(18, 6);
        lsTempo VARCHAR2(15);
        lsCampanyaFacturacion VARCHAR2(6);

    BEGIN

        SELECT COD_PERI
        INTO lsCampanyaFacturacion
        FROM BAS_CTRL_FACT CTRL                                    
        WHERE CTRL.COD_PAIS = psCodigoPais                             
        AND CTRL.sta_camp = '0'                          
        AND CTRL.ind_camp_act = '1';

      OPEN C_ConsulDeshab;
      LOOP
        FETCH C_ConsulDeshab BULK COLLECT INTO tipoConsulDeshabRecordN LIMIT W_FILAS;
        IF tipoConsulDeshabRecordN.COUNT > 0 THEN
          FOR x IN tipoConsulDeshabRecordN.FIRST .. tipoConsulDeshabRecordN.LAST LOOP

            lnNumeroFila   := tipoConsulDeshabRecordN(x).numeroFila;
            lsMensajeError := '';

            -- (1) Validar que la cliente exista
            BEGIN
                SELECT cod_clie 
                INTO lsTempo
                FROM MAE_CLIEN WHERE COD_CLIE = tipoConsulDeshabRecordN(x).codigoCliente; 
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    lsMensajeError := lsMensajeError||'Código de cliente no existe, ';
            END;
            
            -- (2) Validar que la campaña sea mayor que la campaña de facturacion
            IF tipoConsulDeshabRecordN(x).campanya < lsCampanyaFacturacion THEN
                lsMensajeError := lsMensajeError||'La campaña debe de ser mayor a la campaña de facturación, ';
            END IF;
            -- --
            
            -- (3) Validar duplicado
            SELECT COUNT(*)
            INTO lnTempo
            FROM FLX_TEMPO_REGIS_MANUA_CONDES
            WHERE COD_CLIE = tipoConsulDeshabRecordN(x).codigoCliente
            AND CAM_PROC = tipoConsulDeshabRecordN(x).campanya;

            IF lnTempo > 1 THEN
                lsMensajeError := lsMensajeError||'Existe más de un registro con los mísmos valores, ';
            END IF;
            -- --

            IF(length(lsMensajeError) > 0) THEN
               lsMensajeError := substr(lsMensajeError,1,length(lsMensajeError)-2);
               UPDATE FLX_TEMPO_REGIS_MANUA_CONDES
               SET EST_REGI = '0',
               MEN_ERRO = lsMensajeError
               WHERE USU_DIGI = psCodigoUsuario
               AND NUM_FILA = tipoConsulDeshabRecordN(x).numeroFila;
            END IF;

          END LOOP;
        END IF;
      EXIT WHEN C_ConsulDeshab%NOTFOUND;
      END LOOP;
      CLOSE C_ConsulDeshab;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_VALID_CONSU_DESHA: '||ls_sqlerrm);
    END FLX_PR_VALID_CONSU_DESHA;

    /***************************************************************************
    Descripcion       : Realiza la carga de lineas de consultoras a deshabilitar
    Fecha Creacion    : 17/03/2015
    Autor             : Ivan Tocto
    ***************************************************************************/
    PROCEDURE FLX_PR_CARGA_CONSU_DESHA(
        psCodigoPeriodo VARCHAR2,
        psCodigoUsuario VARCHAR2) IS
    BEGIN

        DELETE FLX_REGIS_MANUA_CONDES WHERE CAM_PROC = psCodigoPeriodo;

        INSERT INTO FLX_REGIS_MANUA_CONDES(
            COD_PAIS,
            CAM_PROC,
            COD_CLIE,
            USU_DIGI,
            FEC_DIGI)
        SELECT
        COD_PAIS,
        CAM_PROC,
        COD_CLIE,
        psCodigoUsuario,
        SYSDATE
        FROM FLX_TEMPO_REGIS_MANUA_CONDES
        WHERE EST_REGI = '1'
        AND USU_DIGI = psCodigoUsuario;

    EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := SUBSTR(SQLERRM,1,150);
           RAISE_APPLICATION_ERROR(-20123, 'ERROR FLX_PR_CARGA_CONSU_DESHA: '||ls_sqlerrm);
    END FLX_PR_CARGA_CONSU_DESHA;

END flx_pkg_proce_selec;
/