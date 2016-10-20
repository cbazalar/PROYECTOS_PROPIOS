CREATE OR REPLACE PACKAGE "PRE_PKG_PROCE" AS
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  W_FILAS    NUMBER := 1000;
  /***************************************************************************
  Descripcion       : Crea ofertas alternativas
  Fecha Creacion    : 01/09/2009
    Autor              : Cristhian Roman
    Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo

  ***************************************************************************/
  PROCEDURE pre_pr_crea_ofert_alter
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

/***************************************************************************
  Descripcion       : Elimina ofertas
  Fecha Creacion    : 15/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta

***************************************************************************/
  PROCEDURE pre_pr_elimi_ofert
  (
    psNumeroSecuencia     VARCHAR2,
    psCodigoUsuario       VARCHAR2
  );

/***************************************************************************
  Descripcion       : Elimina CUV
  Fecha Creacion    : 15/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta

***************************************************************************/
  PROCEDURE pre_pr_elimi_codig_venta
  (
    psOidOferta           VARCHAR2,
    psOidDetaOferta       VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  );

/***************************************************************************
  Descripcion       : Modifica los datos de CUV
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta
              psNombCampo       : Campo a Modificar
              psValorCampo      : Valor del Campo a Modificar
              psUsuario         : Usuario

***************************************************************************/
  PROCEDURE pre_pr_modif_codig_venta
  (
    psOidOferta           VARCHAR2,
    psOidDetaOferta       VARCHAR2,
    psOidCatalogo         VARCHAR2,
    psIndicadorCUV        VARCHAR2,
    psIndicadorPagina     VARCHAR2,
    psIndicadorCatalogo   VARCHAR2,
    psIndicadorContable   VARCHAR2,
    psIndicadorFactor     VARCHAR2,
    psIndicadorRanking    VARCHAR2,
    psValorCUV            VARCHAR2,
    psValorPagina         VARCHAR2,
    psValorCatalogo       VARCHAR2,
    psValorContable       VARCHAR2,
    psValorFactor         VARCHAR2,
    psValorRanking        VARCHAR2,
    psUsuario             VARCHAR2,
    psNumeroSecuencia     VARCHAR2,
    psIndicadorValTextoBreve    VARCHAR2,
    psIndicadorIndDigitable     VARCHAR2,
    psIndicadorIndImprimible    VARCHAR2,
    psIndicadorImpCosteEsta     VARCHAR2,
    psIndicadorNumUnidEstim     VARCHAR2,
    psIndicadorImpVenNetaEstim  VARCHAR2,
    psIndicadorTipoOferta       VARCHAR2,
    psValorValTextoBreve        VARCHAR2,
    psValorIndDigitable         VARCHAR2,
    psValorIndImprimible        VARCHAR2,
    psValorImpCosteEsta         VARCHAR2,
    psValorNumUnidEstim         VARCHAR2,
    psValorImpVenNetaEstim      VARCHAR2,
    psValorTipoOferta           VARCHAR2
  );

/***************************************************************************
  Descripcion       : Modifica los datos de Oferta
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta
              psNombCampo       : Campo a Modificar
              psValorCampo      : Valor del Campo a Modificar
              psUsuario         : Usuario

***************************************************************************/
 PROCEDURE pre_pr_modif_ofert
  (
    psCodPais             VARCHAR2,
    psIndicadorCatalogo   VARCHAR2,
    psIndicadorFormaPago  VARCHAR2,
    psCodigoCatalogo      VARCHAR2,
    psCodigoFormaPago     VARCHAR2,
    psUsuario             VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  );

/***************************************************************************
  Descripcion       : Asigna una venta exclusiva
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta            : Oid de Oferta
              psOidTipoCliente       : Oid Tipo Cliente
              psOidSubTipoCliente    : Oid Sub Tipo Cliente
              psOidTipoClasificacion : Oid Tipo Clasificacion
              psOidClasificacion     : Oid Clasificacion
              psOidEstatusCliente    : Oid Estatus Cliente
              psCodRegion            : Codigo Region
              psCodZona              : Codigo Zona

***************************************************************************/
  PROCEDURE pre_pr_asign_venta_exclu
  (
    psCodPais              VARCHAR2,
    psOidTipoCliente       VARCHAR2,
    psOidSubTipoCliente    VARCHAR2,
    psOidTipoClasificacion VARCHAR2,
    psOidClasificacion     VARCHAR2,
    psOidEstatusCliente    VARCHAR2,
    psCodRegion            VARCHAR2,
    psCodZona              VARCHAR2,
    psNumeroSecuencia      VARCHAR2
  );

/***************************************************************************
  Descripcion       : Copia Ofertas
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta    : Oid de Oferta
              psCodPeriodo   : Codigo de Periodo
              psIndReordenar : Indicador de Reordenar Ofertas

***************************************************************************/
  PROCEDURE pre_pr_copia_ofert
  (
    psCodPeriodo          VARCHAR2,
    psIndReordenar        VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  );

/***************************************************************************
  Descripcion       : Inserta en la tabla temporal PRE_TMP_OIDS_OFERT
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              pnOidOferta    : Oid de Oferta

***************************************************************************/
  PROCEDURE pre_pr_inser_tempo_ofert
  (
    pnOidOferta     NUMBER
  );

/***************************************************************************
  Descripcion       : Inserta en las tablas la copia de loa oferta
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros      :
            pncabmat       : Cabecera Matriz

***************************************************************************/
  PROCEDURE pre_pr_inser_copia_ofert
  (
    pncabmat       NUMBER,
    pnIndReordenar       VARCHAR2
  );

/***************************************************************************
  Descripcion       : Valida si existen ofertas Facturadas
  Fecha Creacion    : 12/10/2010
    Autor           : José Luis Rodríguez

***************************************************************************/
  PROCEDURE pre_pr_valid_ofert_factu
  (
    psNumeroSecuencia     VARCHAR2,
    p_val_error       OUT VARCHAR2
  );

/***************************************************************************
  Descripcion       : Valida la carga de estimados
  Fecha Creacion    : 31/03/2013
    Autor           : Sebastian Guerra

***************************************************************************/
  PROCEDURE pre_pr_valid_carga_estim
  (
  pnCodigoUsuario    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Actualizar carga de estimados
  Fecha Creacion    : 01/04/2013
    Autor           : Sebastian Guerra

***************************************************************************/
  PROCEDURE pre_pr_actua_carga_estim
  (
   psCodigoPais         VARCHAR2,
   pnCodigoUsuario      VARCHAR2,
   psNombreArchivo      VARCHAR2
  );

/***************************************************************************
  Descripcion       : Valida la carga de modificaciones masivas
  Fecha Creacion    : 19/02/2014
    Autor           : Aurelio Oviedo
***************************************************************************/
  PROCEDURE PRE_PR_VALID_CARGA_MODIF_MASIV
  (
  pnCodigoUsuario    VARCHAR2
  );

/***************************************************************************
  Descripcion       : Actualizar carga de modificaciones masivas
  Fecha Creacion    : 20/02/2014
    Autor           : Aurelio Oviedo
***************************************************************************/
  PROCEDURE PRE_PR_ACTUA_CARGA_MODIF_MASIV
  (
   psCodigoPais         VARCHAR2,
   pnCodigoUsuario      VARCHAR2
  );

/***************************************************************************
Descripcion       : Valida Restricciones en Productos de Ofertas SICC
Fecha Creacion    : 18/09/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRE_FN_VALID_RESTR_OFERT
  (pnOidTipoOferta    NUMBER,
   psCodigoProducto   VARCHAR2,
   pnOidEstategia     NUMBER,
   pnPrecioCatalogo   NUMBER,
   pnPrecioPosic      NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Valida Restricciones en Productos de Recuperacion SICC
Fecha Creacion    : 18/09/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRE_FN_VALID_PRODU_RECUP
  (pnOidMatriPrinc    NUMBER,
   pnOidMatriRecup    NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Envio de correo electronico en Matriz Carga Planit
Fecha Creacion    : 11/08/2015
Autor             : Richard Argomedo
***************************************************************************/
PROCEDURE int_pre_enema_carg_matr_plan
(
  lpnumeroLote     VARCHAR2,
  pscodigopais     VARCHAR2,
  pserrorLog       VARCHAR2
);

/***************************************************************************
Descripcion       : Generación de data para Reporte Configuración Ofertas (Concurso)
Fecha Creacion    : 02/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRE_PR_REPOR_CONFI_OFERT_CONCU (psCodigoPeriodo VARCHAR2);

/***************************************************************************
Descripcion       : Generación de data para Reporte Configuración Ofertas (N)
Fecha Creacion    : 02/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRE_PR_REPOR_CONFI_OFERT_N (psCodigoPeriodo VARCHAR2);

/**************************************************************************
Descripcion       : Realiza el proceso de Registro Automatico de Matriz de 
                    Precios
Fecha Creacion    : 04/01/2016
Parametros Entrada:
  pnOidPeriodo     :  Oid Periodo
  pnOidCatalogo    :  Oid Catalogo
  pnOidCabecera    :  Oid Cabecera Matriz
  psCodigoUsuario  :  Codigo Usuario
  
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE PRE_PR_REGIS_AUTOM(pnOidPeriodo    NUMBER,
                             pnOidCatalogo   NUMBER,
                             pnOidCabecera   NUMBER,
                             psCodigoUsuario VARCHAR2);
                             
/**************************************************************************
Descripcion       : Realiza el proceso de Renombrar Matriz de Facturación 
Fecha Creacion    : 13/01/2016
Parámetros Entrada:
  psCodigoPeriodoOrigen   :  Codigo Periodo Origen
  psCodigoPeriodoDestino  :  Codigo Periodo Destino
  psCodigoUsuario         :  Codigo Usuario
  
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_RENOM_MATRI_FACTU(psCodigoPeriodoOrigen VARCHAR2,
                                   psCodigoPeriodoDestino VARCHAR2,
                                   psCodigoUsuario VARCHAR2);
                                   
/**************************************************************************
Descripcion       : Realiza el proceso de Eliminar Matriz de Facturación 
Fecha Creacion    : 14/01/2016
Parámetros Entrada:
  psCodigoPeriodo   :  Codigo Periodo
  psCodigoUsuario   :  Codigo Usuario
  psCodigoCatalago  :  Codigo Catálogo
  
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_ELIMI_MATRI_FACTU(psCodigoPeriodo  VARCHAR2,
                                   psCodigoUsuario  VARCHAR2,
                                   psCodigoCatalago VARCHAR2);
                             
END pre_pkg_proce;
/
CREATE OR REPLACE PACKAGE BODY "PRE_PKG_PROCE" AS

  /***************************************************************************
  Descripcion       : Crea ofertas alternativas
  Fecha Creacion    : 01/09/2009
    Autor              : Cristhian Roman
    Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo

  ***************************************************************************/
  PROCEDURE pre_pr_crea_ofert_alter
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  )

   IS
    CURSOR c_creaoferta IS
 SELECT c.oid_deta_ofer,
             d.cod_sap,
             DECODE(h.cod_sap_alte,d.cod_sap, h.cod_sap_PPAL, h.cod_sap_alte) cod_sap_alte,
             a.oid_cabe,
             b.ocat_oid_cata,
             c.val_fact_repe,
             c.imp_prec_cata,
             c.imp_prec_posi,
             c.imp_cost_esta,
             e.oid_matr_fact,
             c.tofe_oid_tipo_ofer,
             c.num_pagi_cata,
             c.fopa_oid_form_pago
        FROM pre_matri_factu_cabec a,
             pre_ofert             b,
             pre_ofert_detal       c,
             mae_produ             d,
             pre_matri_factu       e,
             cra_perio             f,
             seg_perio_corpo       g,
             pre_prod_alter_ice    h,
             MAE_PRODU MP2
       WHERE a.oid_cabe = b.mfca_oid_cabe
         AND b.oid_ofer = c.ofer_oid_ofer
         AND c.prod_oid_prod = d.oid_prod
         AND a.oid_cabe = e.mfca_oid_cabe
         AND c.oid_deta_ofer = e.ofde_oid_deta_ofer
         AND a.perd_oid_peri = f.oid_peri
         AND f.peri_oid_peri = g.oid_peri
         AND g.cod_peri = pscodigoperiodo
         --AND h.cod_peri = pscodigoperiodo
         AND (d.cod_sap = h.cod_sap_ppal or d.cod_sap = h.cod_sap_alte)
         AND C.VAL_CODI_VENT NOT IN
                      (
                SELECT POD.VAL_CODI_VENT FROM pre_matri_codig_alter ca
                , PRE_MATRI_FACTU pmf
                , PRE_MATRI_FACTU pmf1
                , pre_ofert_detal pod
                , pre_ofert_detal pod1
                , mae_produ mp
                , mae_produ mp1
                , pre_matri_factu_cabec pmfc
                , cra_perio cp
                , seg_perio_corpo corpo
                where CA.MAFA_OID_COD_PPAL=PMF.OID_MATR_FACT
                and CA.MAFA_OID_COD_ALTE=PMF1.OID_MATR_FACT
                and PMF.OFDE_OID_DETA_OFER=POD.OID_DETA_OFER
                and PMF1.OFDE_OID_DETA_OFER=POD1.OID_DETA_OFER
                and POD.PROD_OID_PROD=MP.OID_PROD
                and POD1.PROD_OID_PROD=MP1.OID_PROD
                and PMF.MFCA_OID_CABE=PMFC.OID_CABE
                and PMF1.MFCA_OID_CABE=PMFC.OID_CABE
                and PMFC.PERD_OID_PERI=CP.OID_PERI
                and CP.PERI_OID_PERI=CORPO.OID_PERI
                and CORPO.COD_PERI=pscodigoperiodo
             )
         AND C.VAL_CODI_VENT NOT IN
                      (
                SELECT POD1.VAL_CODI_VENT FROM pre_matri_codig_alter ca
                , PRE_MATRI_FACTU pmf
                , PRE_MATRI_FACTU pmf1
                , pre_ofert_detal pod
                , pre_ofert_detal pod1
                , mae_produ mp
                , mae_produ mp1
                , pre_matri_factu_cabec pmfc
                , cra_perio cp
                , seg_perio_corpo corpo
                where CA.MAFA_OID_COD_PPAL=PMF.OID_MATR_FACT
                and CA.MAFA_OID_COD_ALTE=PMF1.OID_MATR_FACT
                and PMF.OFDE_OID_DETA_OFER=POD.OID_DETA_OFER
                and PMF1.OFDE_OID_DETA_OFER=POD1.OID_DETA_OFER
                and POD.PROD_OID_PROD=MP.OID_PROD
                and POD1.PROD_OID_PROD=MP1.OID_PROD
                and PMF.MFCA_OID_CABE=PMFC.OID_CABE
                and PMF1.MFCA_OID_CABE=PMFC.OID_CABE
                and PMFC.PERD_OID_PERI=CP.OID_PERI
                and CP.PERI_OID_PERI=CORPO.OID_PERI
                and CORPO.COD_PERI=pscodigoperiodo
             )
         AND DECODE(h.cod_sap_alte,d.cod_sap, h.cod_sap_PPAL, h.cod_sap_alte)=MP2.COD_SAP;

    TYPE t_oid_deta_ofer IS TABLE OF pre_ofert_detal.oid_deta_ofer%TYPE;
    TYPE t_cod_sap IS TABLE OF mae_produ.cod_sap%TYPE;
    TYPE t_cod_sap_alte IS TABLE OF pre_prod_alter_ice.cod_sap_alte%TYPE;
    TYPE t_oid_cabe IS TABLE OF pre_matri_factu_cabec.oid_cabe%TYPE;

    TYPE t_ocat_oid_cata IS TABLE OF pre_ofert.ocat_oid_cata%TYPE;
    TYPE t_val_fact_repe IS TABLE OF pre_ofert_detal.val_fact_repe%TYPE;
    TYPE t_imp_prec_cata IS TABLE OF pre_ofert_detal.imp_prec_cata%TYPE;
    TYPE t_imp_prec_posi IS TABLE OF pre_ofert_detal.imp_prec_posi%TYPE;
    TYPE t_imp_cost_esta IS TABLE OF pre_ofert_detal.imp_cost_esta%TYPE;
    TYPE t_oid_matr_fact IS TABLE OF pre_matri_factu.oid_matr_fact%TYPE;
    TYPE t_tofe_oid_tipo_ofer IS TABLE OF pre_ofert_detal.tofe_oid_tipo_ofer%TYPE;
    TYPE t_num_pagi_cata IS TABLE OF pre_ofert_detal.num_pagi_cata%TYPE;
    TYPE t_fopa_oid_form_pago IS TABLE OF pre_ofert_detal.fopa_oid_form_pago%TYPE;

    v_oid_deta_ofer      t_oid_deta_ofer;
    v_cod_sap            t_cod_sap;
    v_cod_sap_alte       t_cod_sap_alte;
    v_oid_cabe           t_oid_cabe;
    v_ocat_oid_cata      t_ocat_oid_cata;
    v_val_fact_repe      t_val_fact_repe;
    v_imp_prec_cata      t_imp_prec_cata;
    v_imp_prec_posi      t_imp_prec_posi;
    v_imp_cost_esta      t_imp_cost_esta;
    v_oid_matr_fact      t_oid_matr_fact;
    v_tofe_oid_tipo_ofer t_tofe_oid_tipo_ofer;
    v_num_pagi_cata      t_num_pagi_cata;
    v_fopa_oid_form_pago t_fopa_oid_form_pago;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    lstmp_oid_ofer      pre_ofert.oid_ofer%TYPE;
    lstmp_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;
    lstmp_oid_matr_fact pre_matri_factu.oid_matr_fact%TYPE;

    lnoidestrategia    pre_estra.oid_estr%TYPE;
    lnoidacceso        seg_acces.oid_acce%TYPE;
    lnoidcondpromocion pre_condi_promo.oid_cond_prom%TYPE;
    lnoidciclovida     pre_ciclo_vida.oid_cicl_vida%TYPE;

  BEGIN

    /*ALternativo Individual*/
    SELECT oid_estr INTO lnoidestrategia FROM pre_estra WHERE cod_estr = '016';

    /*BellCenter*/
    SELECT oid_acce
      INTO lnoidacceso
      FROM seg_acces
     WHERE cod_acce = 'BL'
       AND cana_oid_cana = 2001;

    /*SIN CONDICION*/
    SELECT oid_cond_prom INTO lnoidcondpromocion FROM pre_condi_promo WHERE cod_cond_prom = '00';

    /*GENERICO*/
    SELECT oid_cicl_vida INTO lnoidciclovida FROM pre_ciclo_vida WHERE cod_cicl_vida = '00';

    OPEN c_creaoferta;
    LOOP
      FETCH c_creaoferta BULK COLLECT
        INTO v_oid_deta_ofer, v_cod_sap, v_cod_sap_alte, v_oid_cabe, v_ocat_oid_cata, v_val_fact_repe, v_imp_prec_cata, v_imp_prec_posi, v_imp_cost_esta, v_oid_matr_fact, v_tofe_oid_tipo_ofer, v_num_pagi_cata, v_fopa_oid_form_pago LIMIT rows;

      IF v_cod_sap.COUNT > 0 THEN

        FOR i IN v_cod_sap.FIRST .. v_cod_sap.LAST LOOP

          SELECT pre_ofer_seq.NEXTVAL INTO lstmp_oid_ofer FROM dual;

          SELECT pre_ofde_seq.NEXTVAL INTO lstmp_oid_deta_ofer FROM dual;

          SELECT pre_mafa_seq.NEXTVAL INTO lstmp_oid_matr_fact FROM dual;

          --Insercion en PRE_OFERT
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
            (lstmp_oid_ofer,
             lnoidestrategia,
             (SELECT nvl(MAX(num_ofer),
                         0) + 1
                FROM pre_ofert
               WHERE mfca_oid_cabe = v_oid_cabe(i)
                 AND coes_oid_estr = lnoidestrategia),
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             lnoidacceso,
             NULL,
             v_oid_cabe(i),
             1,
             0,
             0,
             1,
             0,
             0,
             v_ocat_oid_cata(i));
          --      MFCA_OID_CABE, COES_OID_ESTR, NUM_OFER
          --Insercion en PRE_OFERT_DETAL

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
             num_orde_deta)
          VALUES
            (lstmp_oid_deta_ofer,
             lstmp_oid_ofer,
             (SELECT oid_prod FROM mae_produ WHERE cod_sap = v_cod_sap_alte(i)),
             1,
             NULL,
             0,
             'MF',
             v_val_fact_repe(i),
             NULL,
             1,
             v_imp_prec_cata(i),
             v_imp_prec_posi(i),
             v_imp_cost_esta(i),
             0,
             v_num_pagi_cata(i),
             v_ocat_oid_cata(i),
             v_tofe_oid_tipo_ofer(i),
             lnoidciclovida,
             lnoidcondpromocion,
             v_fopa_oid_form_pago(i),
             NULL,
             0,
             0,
             1,
             1,
             1,
             (SELECT nvl(MAX(val_codi_vent),
                         0) + 1
                FROM pre_ofert_detal
               WHERE ofer_oid_ofer IN
                     (SELECT oid_ofer FROM pre_ofert WHERE mfca_oid_cabe = v_oid_cabe(i))),
             NULL,
             SYSDATE,
             v_imp_prec_cata(i) / v_val_fact_repe(i),
             NULL,
             NULL,
             NULL,
             NULL);

          --Insercion en PRE_MATRI_FACTU

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
            (lstmp_oid_matr_fact,
             '1',
             v_oid_cabe(i),
             lstmp_oid_deta_ofer,
             SYSDATE,
             NULL,
             NULL,
             NULL,
             NULL);

          --Insercion en PRE_MATRI_CODIG_ALTER

          INSERT INTO pre_matri_codig_alter
            (oid_matr_ater,
             mafa_oid_cod_ppal,
             mafa_oid_cod_alte,
             num_orde,
             ind_mens)
          VALUES
            (pre_mtca_seq.NEXTVAL,
             v_oid_matr_fact(i),
             lstmp_oid_matr_fact,
             1,
             0);

        END LOOP;

      END IF;

      EXIT WHEN c_creaoferta %NOTFOUND;
    END LOOP;

    CLOSE c_creaoferta;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PRE_PR_CREA_OFERT_ALTER: ' || ls_sqlerrm);

  END pre_pr_crea_ofert_alter;

/***************************************************************************
  Descripcion       : Elimina ofertas
  Fecha Creacion    : 15/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta
***************************************************************************/
PROCEDURE pre_pr_elimi_ofert(
    psNumeroSecuencia     VARCHAR2,
    psCodigoUsuario       VARCHAR2
  )
 IS

   CURSOR c_oferta IS
     SELECT p.oid_ofer       OID_OFERTA,
            p.oid_deta_ofer  OID_DETA_OFERTA,
            p.cod_vent       COD_VENTA
       FROM pre_tmp_ofert_modif p
      WHERE p.num_secu_usua = psNumeroSecuencia;

   TYPE ofertas IS RECORD(
     oid_ofer       pre_ofert.oid_ofer%TYPE,
     oid_deta_ofer  pre_ofert_detal.oid_deta_ofer%TYPE,
     cod_vent       pre_ofert_detal.val_codi_vent%TYPE
   );

    TYPE ofertasTab IS TABLE OF ofertas;
    ofertasRecord ofertasTab;

 BEGIN

    OPEN c_oferta;
      LOOP
        FETCH c_oferta BULK COLLECT INTO ofertasRecord LIMIT W_FILAS;
          IF ofertasRecord.COUNT > 0 THEN
            FOR x IN ofertasRecord.FIRST .. ofertasRecord.LAST LOOP

                DELETE FROM pre_matri_reemp
                 WHERE mafa_oid_cod_ppal IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_reemp
                 WHERE mafa_oid_cod_reem IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_recup
                 WHERE mafa_oid_cod_ppal IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_recup
                 WHERE mafa_oid_cod_recu IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_codig_alter
                 WHERE mafa_oid_cod_ppal IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_codig_alter
                 WHERE mafa_oid_cod_alte IN (SELECT oid_matr_fact
                                               FROM pre_matri_factu
                                              where ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                                             FROM pre_ofert_detal
                                                                            WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer));

                DELETE FROM pre_matri_factu
                 WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer
                                                FROM pre_ofert_detal
                                               WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer);

                DELETE FROM pre_rango_promo
                 WHERE pomo_oid_prom IN (SELECT oid_prom
                                           FROM pre_promo
                                          WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer);

                DELETE FROM pre_promo WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer;

                DELETE FROM pre_ofert_detal WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer;

                DELETE FROM pre_grupo_ofert WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer;

                DELETE FROM pre_venta_exclu WHERE ofer_oid_ofer = ofertasRecord(x).oid_ofer;

                DELETE FROM pre_ofert WHERE oid_ofer = ofertasRecord(x).oid_ofer;

                -- Se elimina tambien de la tabla temporal
                DELETE FROM pre_tmp_ofert_modif
                 WHERE oid_ofer = ofertasRecord(x).oid_ofer
                   AND num_secu_usua = psNumeroSecuencia;
                   
                --datos de auditoria
                insert into PRE_HISTO_ACTUA_CODIG_VENTA
                      (OID_OFER,
                       OID_DETA_OFER,
                       NOM_CAMP_MODI,
                       VAL_CAMP_ANTE,
                       VAL_CAMP_NUEV,
                       USU_MODI,
                       FEC_MODI)
                    values
                      (ofertasRecord(x).oid_ofer, ofertasRecord(x).oid_deta_ofer, 'ELIMINADO', ofertasRecord(x).cod_vent, NULL,psCodigoUsuario, SYSDATE);

            END LOOP;
          END IF;
        EXIT WHEN (c_oferta%NOTFOUND);
      END LOOP;
   	CLOSE c_oferta;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     raise_application_error(-20123,'ERROR PRE_PR_ELIMI_OFERT: ' || ls_sqlerrm);

 END pre_pr_elimi_ofert;

/***************************************************************************
  Descripcion       : Elimina CUV
  Fecha Creacion    : 15/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta

***************************************************************************/
 PROCEDURE pre_pr_elimi_codig_venta
  (
    psOidOferta           VARCHAR2,
    psOidDetaOferta       VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  )
 IS
   vnOidOferta      NUMBER;
   vnOidDetaOferta  NUMBER;
   vnContador       NUMBER;

 BEGIN

   vnOidOferta     := to_number(psOidOferta);
   vnOidDetaOferta := to_number(psOidDetaOferta);

   SELECT COUNT(1)
     INTO vnContador
     FROM pre_matri_factu
    WHERE ofde_oid_deta_ofer = vnOidDetaOferta
      AND ind_matr_fact = 1;

   IF (vnContador = 0) THEN

     Delete from pre_matri_reemp where MAFA_OID_COD_PPAL in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

Delete from pre_matri_reemp where MAFA_OID_COD_REEM in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

Delete from pre_matri_recup where MAFA_OID_COD_PPAL in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

Delete from pre_matri_recup where MAFA_OID_COD_RECU in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

Delete from pre_matri_codig_alter where MAFA_OID_COD_PPAL in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

Delete from pre_matri_codig_alter where MAFA_OID_COD_ALTE in (
Select  oid_matr_fact  from pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta);

     DELETE FROM pre_matri_factu WHERE ofde_oid_deta_ofer = vnOidDetaOferta;

     UPDATE pre_ofert a
        SET a.ind_codi_vent_gene = 0,
            a.ind_matr_fact_gene = 0
      WHERE oid_ofer = vnOidOferta;

     UPDATE pre_ofert_detal a
        SET a.ind_codi_vent_gene = 0,
            a.ind_matr_fact_gene = 0,
            a.val_codi_vent = null
      WHERE oid_deta_ofer = vnOidDetaOferta;

    UPDATE pre_tmp_ofert_modif a
       SET a.cod_vent = NULL
     WHERE a.oid_ofer = vnOidOferta
       AND a.oid_deta_ofer = vnOidDetaOferta
       AND a.num_secu_usua = psNumeroSecuencia;
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     raise_application_error(-20123,'ERROR PRE_PR_ELIMI_CODIG_VENTA: ' || ls_sqlerrm);

 END pre_pr_elimi_codig_venta;

/***************************************************************************
  Descripcion       : Modifica los datos de CUV
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta
              psNombCampo       : Campo a Modificar
              psValorCampo      : Valor del Campo a Modificar
              psUsuario         : Usuario

***************************************************************************/
 PROCEDURE pre_pr_modif_codig_venta
  (
    psOidOferta           VARCHAR2,
    psOidDetaOferta       VARCHAR2,
    psOidCatalogo         VARCHAR2,
    psIndicadorCUV        VARCHAR2,
    psIndicadorPagina     VARCHAR2,
    psIndicadorCatalogo   VARCHAR2,
    psIndicadorContable   VARCHAR2,
    psIndicadorFactor     VARCHAR2,
    psIndicadorRanking    VARCHAR2,
    psValorCUV            VARCHAR2,
    psValorPagina         VARCHAR2,
    psValorCatalogo       VARCHAR2,
    psValorContable       VARCHAR2,
    psValorFactor         VARCHAR2,
    psValorRanking        VARCHAR2,
    psUsuario             VARCHAR2,
    psNumeroSecuencia     VARCHAR2,
    psIndicadorValTextoBreve    VARCHAR2,
    psIndicadorIndDigitable     VARCHAR2,
    psIndicadorIndImprimible    VARCHAR2,
    psIndicadorImpCosteEsta     VARCHAR2,
    psIndicadorNumUnidEstim     VARCHAR2,
    psIndicadorImpVenNetaEstim  VARCHAR2,
    psIndicadorTipoOferta       VARCHAR2,
    psValorValTextoBreve        VARCHAR2,
    psValorIndDigitable         VARCHAR2,
    psValorIndImprimible        VARCHAR2,
    psValorImpCosteEsta         VARCHAR2,
    psValorNumUnidEstim         VARCHAR2,
    psValorImpVenNetaEstim      VARCHAR2,
    psValorTipoOferta           VARCHAR2
  )
 IS
   vnOidOferta        NUMBER;
   vnOidDetaOferta    NUMBER;
   vnOidCatalogo      NUMBER;
   vsCodVentaAnt      VARCHAR2(20);
   vnNumPaginaAnt     NUMBER;
   vnPreCatalogoAnt   NUMBER;
   vnPreContableAnt   NUMBER;
   vnFacRepeticionAnt NUMBER;
   vnRankingAnt       NUMBER;
   vsNumPaginaAnt     VARCHAR2(20);
   vsPreCatalogoAnt   VARCHAR2(20);
   vsPreContableAnt   VARCHAR2(20);
   vsFacRepeticionAnt VARCHAR2(20);
   vsRankingAnt       VARCHAR2(20);

   vnNumPaginaAct     NUMBER;
   vnPreCatalogoAct   NUMBER;
   vnPreContableAct   NUMBER;
   vnFacRepeticionAct NUMBER;
   vnRankingAct       NUMBER;
   
   --Nuevos
   vnIndDigitableAnt        pre_ofert_detal.ind_digi%TYPE;
   vnIndImprimibleAnt       pre_ofert_detal.ind_impr_gp%TYPE;
   vnImpCosteEstaAnt        pre_ofert_detal.imp_cost_esta%TYPE;
   vnNumUnidEstimAnt        pre_ofert_detal.num_unid_esti%TYPE;
   vnImpVenNetaEstimAnt     pre_ofert_detal.imp_vent_neta_esti%TYPE;
   vnOidTipoOfertaAnt       pre_ofert_detal.tofe_oid_tipo_ofer%TYPE;
   
   vsValTextoBreveAnt       VARCHAR2(20);
   vsIndDigitableAnt        VARCHAR2(20);
   vsIndImprimibleAnt       VARCHAR2(20);
   vsImpCosteEstaAnt        VARCHAR2(20);
   vsNumUnidEstimAnt        VARCHAR2(20);
   vsImpVenNetaEstimAnt     VARCHAR2(20);
   vsOidTipoOfertaAnt       VARCHAR2(20);
   
   vnIndDigitableAct        pre_ofert_detal.ind_digi%TYPE;
   vnIndImprimibleAct       pre_ofert_detal.ind_impr_gp%TYPE;
   vnImpCosteEstaAct        pre_ofert_detal.imp_cost_esta%TYPE;
   vnNumUnidEstimAct        pre_ofert_detal.num_unid_esti%TYPE;
   vnImpVenNetaEstimAct     pre_ofert_detal.imp_vent_neta_esti%TYPE;
   vnOidTipoOfertaAct       pre_ofert_detal.tofe_oid_tipo_ofer%TYPE;

 BEGIN
   vnOidOferta        := to_number(psOidOferta);
   vnOidDetaOferta    := to_number(psOidDetaOferta);
   vnOidCatalogo      := to_number(psOidCatalogo);
   vnNumPaginaAct     := to_number(psValorPagina);
   vnPreCatalogoAct   := to_number(psValorCatalogo,'9999999999.99');
   vnPreContableAct   := to_number(psValorContable,'9999999999.99');
   vnFacRepeticionAct := to_number(psValorFactor);
   vnRankingAct       := to_number(psValorRanking);
   
   --Nuevos
   vnIndDigitableAct    := to_number(psValorIndDigitable);
   vnIndImprimibleAct   := to_number(psValorIndImprimible);
   vnImpCosteEstaAct    := to_number(psValorImpCosteEsta,'9999999999.99');
   vnNumUnidEstimAct    := to_number(psValorNumUnidEstim);
   vnImpVenNetaEstimAct := to_number(psValorImpVenNetaEstim,'9999999999999.99');


   -- Se obtinen los valores actules
   SELECT p.val_codi_vent,
          p.num_pagi_cata,
          p.imp_prec_cata,
          p.imp_prec_posi,
          p.val_fact_repe,
          p.num_posi_rank,
          substr(p.val_text_brev, 0, 20),
          p.ind_digi,
          p.ind_impr_gp,
          p.imp_cost_esta,
          p.num_unid_esti,
          p.imp_vent_neta_esti,
          p.tofe_oid_tipo_ofer
     INTO vsCodVentaAnt,
          vnNumPaginaAnt,
          vnPreCatalogoAnt,
          vnPreContableAnt,
          vnFacRepeticionAnt,
          vnRankingAnt,
          vsValTextoBreveAnt,
          vnIndDigitableAnt,
          vnIndImprimibleAnt,
          vnImpCosteEstaAnt,
          vnNumUnidEstimAnt,
          vnImpVenNetaEstimAnt,
          vnOidTipoOfertaAnt
     FROM pre_ofert_detal p
    WHERE p.ofer_oid_ofer = vnOidOferta
      AND p.oid_deta_ofer = vnOidDetaOferta;

   vsNumPaginaAnt     := to_char(vnNumPaginaAnt);
   vsPreCatalogoAnt   := to_char(vnPreCatalogoAnt);
   vsPreContableAnt   := to_char(vnPreContableAnt);
   vsFacRepeticionAnt := to_char(vnFacRepeticionAnt);
   vsRankingAnt       := to_char(vnRankingAnt);
   
   --Nuevos
   vsIndDigitableAnt    := to_char(vnIndDigitableAnt);
   vsIndImprimibleAnt   := to_char(vnIndImprimibleAnt);
   vsImpCosteEstaAnt    := to_char(vnImpCosteEstaAnt);
   vsNumUnidEstimAnt    := to_char(vnNumUnidEstimAnt);
   vsImpVenNetaEstimAnt := to_char(vnImpVenNetaEstimAnt);
   vsOidTipoOfertaAnt   := to_char(vnOidTipoOfertaAnt);

   -- Se actualiza campo CUV
   IF ( psIndicadorCUV = 'S') THEN
     UPDATE pre_ofert_detal
        SET val_codi_vent = psValorCUV
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET cod_vent = psValorCUV
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'VAL_CODI_VENT', vsCodVentaAnt, psValorCUV, psUsuario );
   END IF;

   -- Se actualiza campo Pagina
   IF ( psIndicadorPagina = 'S') THEN
     UPDATE pre_ofert_detal
        SET num_pagi_cata = vnNumPaginaAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

     -- Se actualiza en la tabla temproal
     UPDATE pre_tmp_ofert_modif
        SET num_pagi_cata = vnNumPaginaAct
      WHERE oid_ofer = vnOidOferta
        AND oid_deta_ofer = vnOidDetaOferta
        AND oid_cata = vnOidCatalogo
        AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'NUM_PAGI_CATA', vsNumPaginaAnt, psValorPagina, psUsuario );
   END IF;

   -- Se actualiza campo Precio Catalogo
   IF ( psIndicadorCatalogo = 'S') THEN
     UPDATE pre_ofert_detal
        SET imp_prec_cata = vnPreCatalogoAct
      WHERE oid_deta_ofer =vnOidDetaOferta;

     UPDATE pre_ofert_detal
        SET precio_unitario = imp_prec_cata/val_fact_repe
       WHERE oid_deta_ofer = vnOidDetaOferta;

     -- Se actualiza en la tabla temproal
     UPDATE pre_tmp_ofert_modif
        SET imp_prec_cata = vnPreCatalogoAct
      WHERE oid_ofer = vnOidOferta
        AND oid_deta_ofer = vnOidDetaOferta
        AND oid_cata = vnOidCatalogo
        AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IMP_PREC_CATA', vsPreCatalogoAnt, psValorCatalogo, psUsuario );
   END IF;

   -- Se actualiza campo Precio Contable
   IF ( psIndicadorContable = 'S') THEN
     UPDATE pre_ofert_detal
        SET imp_prec_posi = vnPreContableAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

     -- Se actualiza en la tabla temproal
     UPDATE pre_tmp_ofert_modif
        SET imp_prec_posi = vnPreContableAct
      WHERE oid_ofer = vnOidOferta
        AND oid_deta_ofer = vnOidDetaOferta
        AND oid_cata = vnOidCatalogo
        AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IMP_PREC_POSI', vsPreContableAnt, psValorContable, psUsuario );
   END IF;

   -- Se actualiza campo Factor Repeticion
   IF ( psIndicadorFactor = 'S') THEN
     UPDATE pre_ofert_detal
        SET val_fact_repe = vnFacRepeticionAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

     UPDATE pre_ofert_detal
        SET precio_unitario = imp_prec_cata/val_fact_repe
      WHERE oid_deta_ofer = vnOidDetaOferta;

     -- Se actualiza en la tabla temproal
     UPDATE pre_tmp_ofert_modif
        SET val_fact_repe = vnFacRepeticionAct
      WHERE oid_ofer = vnOidOferta
        AND oid_deta_ofer = vnOidDetaOferta
        AND oid_cata = vnOidCatalogo
        AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'VAL_FACT_REPE', vsFacRepeticionAnt, psValorFactor, psUsuario );
   END IF;

   -- Se actualiza campo Ranking
   IF ( psIndicadorRanking = 'S') THEN
     UPDATE pre_ofert_detal
        SET num_posi_rank = vnRankingAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

     -- Se actualiza en la tabla temproal
     UPDATE pre_tmp_ofert_modif
        SET num_posi_rank = vnRankingAct
      WHERE oid_ofer = vnOidOferta
        AND oid_deta_ofer = vnOidDetaOferta
        AND oid_cata = vnOidCatalogo
        AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'NUM_POSI_RANK', vsRankingAnt, psValorRanking, psUsuario );
   END IF;
   
   
   
   
   -- Se actualiza campo TEXTO BREVE
   IF ( psIndicadorValTextoBreve = 'S') THEN
     UPDATE pre_ofert_detal
        SET VAL_TEXT_BREV = psValorValTextoBreve
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET VAL_TEXT_BREV = psValorValTextoBreve
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'VAL_TEXT_BREV', vsValTextoBreveAnt, substr(psValorValTextoBreve, 0, 20), psUsuario );
   END IF;
   
   -- Se actualiza campo Digitable
   IF ( psIndicadorIndDigitable = 'S') THEN
     UPDATE pre_ofert_detal
        SET IND_DIGI = vnIndDigitableAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET IND_DIGI = vnIndDigitableAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IND_DIGI', vsIndDigitableAnt, psValorIndDigitable, psUsuario );
   END IF;
   
   -- Se actualiza campo Imprimible
   IF ( psIndicadorIndDigitable = 'S') THEN
     UPDATE pre_ofert_detal
        SET IND_IMPR_GP = vnIndImprimibleAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET IND_IMPR_GP = vnIndImprimibleAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IND_IMPR_GP', vsIndImprimibleAnt, psValorIndImprimible, psUsuario );
   END IF;
   
   -- Se actualiza campo Coste Estandar
   IF ( psIndicadorImpCosteEsta = 'S') THEN
     UPDATE pre_ofert_detal
        SET IMP_COST_ESTA = vnImpCosteEstaAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET IMP_COST_ESTA = vnImpCosteEstaAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IMP_COST_ESTA', vsImpCosteEstaAnt, psValorImpCosteEsta, psUsuario );
   END IF;
   
   -- Se actualiza campo Numero Unidades Estimadas
   IF ( psIndicadorNumUnidEstim = 'S') THEN
     UPDATE pre_ofert_detal
        SET NUM_UNID_ESTI = vnNumUnidEstimAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET NUM_UNID_ESTI = vnNumUnidEstimAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'NUM_UNID_ESTI', vsNumUnidEstimAnt, psValorNumUnidEstim, psUsuario );
   END IF;
   
   -- Se actualiza campo Venta Neta Estimada
   IF ( psIndicadorImpVenNetaEstim = 'S') THEN
     UPDATE pre_ofert_detal
        SET IMP_VENT_NETA_ESTI = vnImpVenNetaEstimAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET IMP_VENT_NETA_ESTI = vnImpVenNetaEstimAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'IMP_VENT_NETA_ESTI', vsImpVenNetaEstimAnt, psValorImpVenNetaEstim, psUsuario );
   END IF;
   
   -- Se actualiza campo Tipo Oferta
   IF ( psIndicadorTipoOferta = 'S') THEN
     SELECT OID_TIPO_OFER
       INTO vnOidTipoOfertaAct
       FROM PRE_TIPO_OFERT
      WHERE COD_TIPO_OFER = psValorTipoOferta;
     
     UPDATE pre_ofert_detal
        SET TOFE_OID_TIPO_OFER = vnOidTipoOfertaAct
      WHERE oid_deta_ofer = vnOidDetaOferta;

      -- Se actualiza en la tabla temproal
      UPDATE pre_tmp_ofert_modif
         SET TOFE_OID_TIPO_OFER = vnOidTipoOfertaAct
       WHERE oid_ofer = vnOidOferta
         AND oid_deta_ofer = vnOidDetaOferta
         AND oid_cata = vnOidCatalogo
         AND num_secu_usua = psNumeroSecuencia;

     -- Se inserta en la tabla de auditoria
     INSERT INTO pre_histo_actua_codig_venta
       ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
     VALUES
       ( vnOidOferta, vnOidDetaOferta, 'TOFE_OID_TIPO_OFER', vsOidTipoOfertaAnt, to_char(vnOidTipoOfertaAct), psUsuario );
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     raise_application_error(-20123,'ERROR PRE_PR_MODIF_CODIG_VENTA: ' || ls_sqlerrm);

 END pre_pr_modif_codig_venta;

/***************************************************************************
  Descripcion       : Modifica los datos de Oferta
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta       : Oid de Oferta
              psOidDetaOferta   : Oid de Detalle Oferta
              psNombCampo       : Campo a Modificar
              psValorCampo      : Valor del Campo a Modificar
              psUsuario         : Usuario

***************************************************************************/
 PROCEDURE pre_pr_modif_ofert
  (
    psCodPais             VARCHAR2,
    psIndicadorCatalogo   VARCHAR2,
    psIndicadorFormaPago  VARCHAR2,
    psCodigoCatalogo      VARCHAR2,
    psCodigoFormaPago     VARCHAR2,
    psUsuario             VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  )
 IS
   vnOidPais          NUMBER;
   vnOidOferta        NUMBER;
   vnOidDetaOferta    NUMBER;
   vnOidCatalogoAnt   NUMBER;
   vmOidFormaPagoAnt  NUMBER;
   vsOidCatalogoAnt   VARCHAR2(20);
   vsOidFormaPagoAnt  VARCHAR2(20);
   vnOidCatalogoAct   NUMBER;
   vsDesCatalogoAct   VARCHAR2(40);
   vnOidFormaPagoAct  NUMBER;
   vsOidCatalogoAct   VARCHAR2(20);
   vsOidFormaPagoAct  VARCHAR2(20);

   CURSOR c_oferta IS
     SELECT p.oid_ofer       OID_OFERTA,
            p.oid_deta_ofer  OID_DETA_OFERTA
       FROM pre_tmp_ofert_modif p
      WHERE p.num_secu_usua = psNumeroSecuencia;

   TYPE ofertas IS RECORD(
     oid_ofer       pre_ofert.oid_ofer%TYPE,
     oid_deta_ofer  pre_ofert_detal.oid_deta_ofer%TYPE
   );

    TYPE ofertasTab IS TABLE OF ofertas;
    ofertasRecord ofertasTab;

 BEGIN

   vnOidPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);

   OPEN c_oferta;
     LOOP
       FETCH c_oferta BULK COLLECT INTO ofertasRecord LIMIT W_FILAS;
         IF ofertasRecord.COUNT > 0 THEN
           FOR x IN ofertasRecord.FIRST .. ofertasRecord.LAST LOOP

             vnOidOferta     := ofertasRecord(x).oid_ofer;
             vnOidDetaOferta := ofertasRecord(x).oid_deta_ofer;

             SELECT p.ocat_oid_cata,
                    p.fopa_oid_form_pago
               INTO vnOidCatalogoAnt,
                    vmOidFormaPagoAnt
               FROM pre_ofert p
              WHERE p.oid_ofer = vnOidOferta;

             vsOidCatalogoAnt := to_char(vnOidCatalogoAnt);
             vsOidFormaPagoAnt := to_char(vmOidFormaPagoAnt);

             -- Se actualiza campo Catalogo
             IF ( psIndicadorCatalogo = 'S') THEN
               SELECT c.oid_cata,
                      c.des_cata
                 INTO vnOidCatalogoAct,
                      vsDesCatalogoAct
                 FROM pre_catal c
                WHERE c.cod_cata = to_number(psCodigoCatalogo)
                  AND c.pais_oid_pais = vnOidPais;

               vsOidCatalogoAct := to_char(vnOidCatalogoAct);

               UPDATE pre_ofert_detal
                  SET ocat_oid_catal = vnOidCatalogoAct
                WHERE ofer_oid_ofer = vnOidOferta;

               UPDATE pre_ofert
                  SET ocat_oid_cata = vnOidCatalogoAct
                WHERE oid_ofer = vnOidOferta;

               -- Se actualiza en la tabla temproal
               UPDATE pre_tmp_ofert_modif
                  SET oid_cata = vnOidCatalogoAct,
                      cod_cata = psCodigoCatalogo,
                      des_cata = vsDesCatalogoAct
                WHERE oid_ofer = vnOidOferta
                  AND num_secu_usua = to_number(psNumeroSecuencia);

               -- Se inserta en la tabla de auditoria
               INSERT INTO pre_histo_actua_codig_venta
               ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
               VALUES
               ( vnOidOferta, vnOidDetaOferta, 'OCAT_OID_CATAL', vsOidCatalogoAnt, vsOidCatalogoAct, psUsuario );
             END IF;

             -- Se actualiza campo Forma Pago
             IF ( psIndicadorFormaPago = 'S') THEN
               SELECT f.oid_form_pago
                 INTO vnOidFormaPagoAct
                 FROM bel_forma_pago f
                WHERE f.cod_form_pago = psCodigoFormaPago
                  AND f.pais_oid_pais = vnOidPais;

               vsOidFormaPagoAct := to_char(vnOidFormaPagoAct);

               UPDATE pre_ofert_detal
                  SET fopa_oid_form_pago = vnOidFormaPagoAct
                WHERE ofer_oid_ofer = vnOidOferta;

               UPDATE pre_ofert
                  SET fopa_oid_form_pago = vnOidFormaPagoAct
                WHERE oid_ofer = vnOidOferta;

               -- Se actualiza en la tabla temproal
               UPDATE pre_tmp_ofert_modif
                  SET oid_form_pago = vnOidFormaPagoAct
                WHERE oid_ofer = vnOidOferta
                  AND num_secu_usua = psNumeroSecuencia;

               -- Se inserta en la tabla de auditoria
               INSERT INTO pre_histo_actua_codig_venta
               ( oid_ofer, oid_deta_ofer, nom_camp_modi, val_camp_ante, val_camp_nuev, usu_modi )
               VALUES
               ( vnOidOferta, vnOidDetaOferta, 'FOPA_OID_FORM_PAGO', vsOidFormaPagoAnt, vsOidFormaPagoAct, psUsuario );
             END IF;

           END LOOP;
         END IF;
       EXIT WHEN (c_oferta%NOTFOUND);
     END LOOP;
   CLOSE c_oferta;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     raise_application_error(-20123,'ERROR PRE_PR_MODIF_OFERT: ' || ls_sqlerrm);

 END pre_pr_modif_ofert;

/***************************************************************************
  Descripcion       : Asigna una venta exclusiva
  Fecha Creacion    : 18/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta            : Oid de Oferta
              psCodTipoCliente       : Codigo Tipo Cliente
              psCodSubTipoCliente    : Codigo Sub Tipo Cliente
              psCodTipoClasificacion : Codigo Tipo Clasificacion
              psCodClasificacion     : Codigo Clasificacion
              psCodEstatusCliente    : Codigo Estatus Cliente
              psCodRegion            : Codigo Region
              psCodZona              : Codigo Zona

***************************************************************************/
  PROCEDURE pre_pr_asign_venta_exclu
  (
    psCodPais              VARCHAR2,
    psOidTipoCliente       VARCHAR2,
    psOidSubTipoCliente    VARCHAR2,
    psOidTipoClasificacion VARCHAR2,
    psOidClasificacion     VARCHAR2,
    psOidEstatusCliente    VARCHAR2,
    psCodRegion            VARCHAR2,
    psCodZona              VARCHAR2,
    psNumeroSecuencia      VARCHAR2
  )
 IS
    vnOidOferta            NUMBER;
    vnOidTipoCliente       NUMBER;
    vnOidSubTipoCliente    NUMBER;
    vnOidTipoClasificacion NUMBER;
    vnOidClasificacion     NUMBER;
    vnOidEstatusCliente    NUMBER;
    vnOidRegion            NUMBER;
    vnOidZona              NUMBER;
    vnContadorRegistro     NUMBER;
    vsCodRegion            VARCHAR2(2);
    vsCodZona              VARCHAR2(4);
    vsSqlConsulta          VARCHAR2(400);

   CURSOR c_oferta IS
     SELECT p.oid_ofer       OID_OFERTA,
            p.oid_deta_ofer  OID_DETA_OFERTA
       FROM pre_tmp_ofert_modif p
      WHERE p.num_secu_usua = psNumeroSecuencia;

   TYPE ofertas IS RECORD(
     oid_ofer       pre_ofert.oid_ofer%TYPE,
     oid_deta_ofer  pre_ofert_detal.oid_deta_ofer%TYPE
   );

    TYPE ofertasTab IS TABLE OF ofertas;
    ofertasRecord ofertasTab;

 BEGIN

   vnOidTipoCliente       := psOidTipoCliente;
   vnOidSubTipoCliente    := psOidSubTipoCliente;
   vnOidTipoClasificacion := psOidTipoClasificacion;
   vnOidClasificacion     := psOidClasificacion;
   vnOidEstatusCliente    := psOidEstatusCliente;
   vsCodRegion            := psCodRegion;
   vsCodZona              := psCodZona;

   OPEN c_oferta;
     LOOP
       FETCH c_oferta BULK COLLECT INTO ofertasRecord LIMIT W_FILAS;
         IF ofertasRecord.COUNT > 0 THEN
           FOR x IN ofertasRecord.FIRST .. ofertasRecord.LAST LOOP

             vnOidOferta := ofertasRecord(x).oid_ofer;

             vsSqlConsulta := 'SELECT COUNT(1) FROM pre_venta_exclu p WHERE p.ofer_oid_ofer = ' ||vnOidOferta;

             IF ( vnOidTipoCliente IS NOT NULL) THEN
               vsSqlConsulta := vsSqlConsulta || ' AND p.ticl_oid_tipo_clie = ' || vnOidTipoCliente;
             ELSE
               vsSqlConsulta := vsSqlConsulta || ' AND p.ticl_oid_tipo_clie IS NULL ';
             END IF;

             IF ( vnOidSubTipoCliente IS NOT NULL) THEN
               vsSqlConsulta := vsSqlConsulta || ' AND p.sbti_oid_subt_clie = ' || vnOidSubTipoCliente;
             ELSE
               vsSqlConsulta := vsSqlConsulta || ' AND p.sbti_oid_subt_clie IS NULL ';
             END IF;

             IF ( vnOidTipoClasificacion IS NOT NULL) THEN
               vsSqlConsulta := vsSqlConsulta || ' AND p.tccl_oid_tipo_clas = ' || vnOidTipoClasificacion;
             ELSE
               vsSqlConsulta := vsSqlConsulta || ' AND p.tccl_oid_tipo_clas IS NULL ';
             END IF;

             IF ( vnOidClasificacion IS NOT NULL) THEN
               vsSqlConsulta := vsSqlConsulta || ' AND p.clas_oid_clas = ' || vnOidClasificacion;
             ELSE
               vsSqlConsulta := vsSqlConsulta || ' AND p.clas_oid_clas IS NULL ';
             END IF;

             IF ( vnOidEstatusCliente IS NOT NULL) THEN
               vsSqlConsulta := vsSqlConsulta || ' AND p.esta_oid_esta_clie = ' || vnOidEstatusCliente;
             ELSE
               vsSqlConsulta := vsSqlConsulta || ' AND p.esta_oid_esta_clie IS NULL ';
             END IF;

             IF (vsCodRegion IS NOT NULL) THEN
               vnOidRegion := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION( psCodPais, 'T', 'VD',psCodRegion );
               vsSqlConsulta := vsSqlConsulta || ' AND p.zorg_oid_regi = ' || vnOidRegion;
             ELSE
               vnOidRegion := NULL;
               vsSqlConsulta := vsSqlConsulta || ' AND p.zorg_oid_regi IS NULL ';
             END IF;

             IF((vsCodRegion IS NOT NULL) AND (vsCodZona IS NOT NULL) )THEN
               vnOidZona   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA( psCodPais, 'T', 'VD',psCodRegion, psCodZona );
               vsSqlConsulta := vsSqlConsulta || ' AND p.zzon_oid_zona = ' || vnOidZona;
             ELSE
               vnOidZona := NULL;
               vsSqlConsulta := vsSqlConsulta || ' AND p.zzon_oid_zona IS NULL ';
             END IF;

             vnContadorRegistro := 0;

             EXECUTE IMMEDIATE vsSqlConsulta INTO vnContadorRegistro;

             IF (vnContadorRegistro = 0) THEN
               INSERT INTO pre_venta_exclu
               ( oid_vent_excl, ofer_oid_ofer, tccl_oid_tipo_clas, ticl_oid_tipo_clie,
                 clas_oid_clas, esta_oid_esta_clie, sbti_oid_subt_clie, val_esta_2,
                 zorg_oid_regi, zzon_oid_zona )
               VALUES
               ( pre_pven_seq.nextval, vnOidOferta, vnOidTipoClasificacion, vnOidTipoCliente,
                 vnOidClasificacion, vnOidEstatusCliente, vnOidSubTipoCliente, NULL,
                 vnOidRegion, vnOidZona );
             END IF;

           END LOOP;
         END IF;
       EXIT WHEN (c_oferta%NOTFOUND);
     END LOOP;
   CLOSE c_oferta;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(SQLERRM, 1, 250);
     raise_application_error(-20123,'ERROR PRE_PR_ASIGN_VENTA_EXCLU: ' || ls_sqlerrm);

 END pre_pr_asign_venta_exclu;

/***************************************************************************
  Descripcion       : Copia Ofertas
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              psOidOferta    : Oid de Oferta
              psCodPeriodo   : Codigo de Periodo
              psIndReordenar : Indicador de Reordenar Ofertas

***************************************************************************/
  PROCEDURE pre_pr_copia_ofert
  (
    psCodPeriodo          VARCHAR2,
    psIndReordenar        VARCHAR2,
    psNumeroSecuencia     VARCHAR2
  )
  IS
    CURSOR c_oferta IS
      SELECT DISTINCT p.oid_ofer
        FROM pre_tmp_ofert_modif p
       WHERE p.num_secu_usua = psNumeroSecuencia;

    TYPE insDatosCur IS RECORD(
      v_oid_ofer      pre_ofert.oid_ofer%TYPE);

    TYPE insDatosCurtab IS TABLE OF insDatosCur;
    insDatosCurrecord insDatosCurtab;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    vsOidPerido     NUMBER;
    vncabmat        NUMBER;
    vnContador      NUMBER;

  BEGIN

    vsOidPerido := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

    vnContador := 0;
    SELECT count(1)
      INTO vnContador
      FROM pre_matri_factu_cabec
     WHERE perd_oid_peri = vsOidPerido;

    IF vnContador = 0 THEN
      SELECT pre_mafa_seq.nextval
        INTO vncabmat
        FROM dual;

      INSERT INTO pre_matri_factu_cabec
      ( oid_cabe, perd_oid_peri, num_pedi_esti, num_unid_esti, num_clie_esti, tota_mont_vent_neta,
        val_tipo_camb, ind_matr_fact_gene, ind_regi_esta_gene, ind_matr_fact, mone_oid_mone  )
      VALUES
      ( vncabmat, vsOidPerido, 0, 0, 0, 0, 1, 0, 0, 0, null);
    ELSE
      SELECT oid_cabe
        INTO vncabmat
        FROM pre_matri_factu_cabec
       WHERE perd_oid_peri = vsOidPerido;
    END IF;

    OPEN c_oferta;
      LOOP
        FETCH c_oferta BULK COLLECT
        INTO insDatosCurrecord LIMIT rows;

        IF insDatosCurrecord.COUNT > 0 THEN
          FOR i IN insDatosCurrecord.FIRST .. insDatosCurrecord.LAST LOOP

            -- Llenando la tabla temporal
            pre_pr_inser_tempo_ofert( insDatosCurrecord(i).v_oid_ofer );

            -- Insertando en las tablas finales
            pre_pr_inser_copia_ofert( vncabmat,psIndReordenar );

            -- Si el indicador de reordenar ofertas esta activado:
            /*IF( psIndReordenar = 'S' ) THEN
              UPDATE pre_ofert_detal
                 SET val_codi_vent = to_char(nvl((select max(val_codi_vent) from pre_ofert_detal where ofer_oid_ofer in (select oid_ofer from pre_ofert where mfca_oid_cabe=vncabmat)),95000) + rownum)--insDatosCurrecord(i).v_num_fila)
               WHERE oid_deta_ofer IN (SELECT oid_nuev FROM pre_tmp_oids_ofert WHERE attr_enti = 'PRE_OFERT_DETAL');
*/
              /* UPDATE pre_tmp_ofert_modif
                  SET cod_vent =  to_char(95001 + rownum)--insDatosCurrecord(i).v_num_fila)
                WHERE oid_ofer IN (SELECT oid_ofer FROM pre_tmp_oids_ofert WHERE attr_enti = 'PRE_OFERT');
--                WHERE oid_ofer = insDatosCurrecord(i).v_oid_ofer;

            END IF;
*/
          END LOOP;
        END IF;

      EXIT WHEN c_oferta %NOTFOUND;
    END LOOP;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,'ERROR PRE_PR_COPIA_OFERT: ' || ls_sqlerrm);

 END pre_pr_copia_ofert;

 /***************************************************************************
  Descripcion       : Inserta en la tabla temporal PRE_TMP_OIDS_OFERT
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              pnOidOferta    : Oid de Oferta

***************************************************************************/
 PROCEDURE pre_pr_inser_tempo_ofert
  (
    pnOidOferta     NUMBER
  )
  IS

  BEGIN

    Delete from pre_tmp_oids_ofert;

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_ofer,
             pre_ofer_seq.nextval,
             'PRE_OFERT'
        FROM pre_ofert
       WHERE oid_ofer = pnOidOferta );

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_grup_ofer,
             pre_gofe_seq.nextval,
             'PRE_GRUPO_OFERT'
        FROM pre_grupo_ofert
       WHERE ofer_oid_ofer = pnOidOferta );

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_deta_ofer,
             pre_ofde_seq.nextval,
             'PRE_OFERT_DETAL'
        FROM pre_ofert_detal
       WHERE ofer_oid_ofer = pnOidOferta );

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_prom,
             pre_pomo_seq.nextval,
             'PRE_PROMO'
        FROM pre_promo
       WHERE ofer_oid_ofer = pnOidOferta );

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_rang_prom,
             pre_rapr_seq.nextval,
             'PRE_RANGO_PROMO'
        FROM pre_rango_promo
       WHERE pomo_oid_prom IN (SELECT oid_prom FROM pre_promo WHERE ofer_oid_ofer = pnOidOferta ));

    INSERT INTO pre_tmp_oids_ofert
    ( SELECT oid_matr_fact,
             pre_mafa_seq.nextval,
             'PRE_MATRI_FACTU'
        FROM pre_matri_factu
       WHERE ofde_oid_deta_ofer IN (SELECT oid_deta_ofer FROM pre_ofert_detal WHERE ofer_oid_ofer = pnOidOferta ));

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,'ERROR PRE_PR_INSER_TEMPO_OFERT: ' || ls_sqlerrm);

 END pre_pr_inser_tempo_ofert;

/***************************************************************************
  Descripcion       : Inserta en las tablas la copia de loa oferta
  Fecha Creacion    : 22/02/2010
    Autor           : José Luis Rodríguez
    Parametros        :
              pncabmat       : Cabecera Matriz

***************************************************************************/
 PROCEDURE pre_pr_inser_copia_ofert
  (
    pncabmat             NUMBER,
    pnIndReordenar       VARCHAR2
  )
 IS

 BEGIN

   --  Insertando en PRE_OFERT
   INSERT INTO PRE_OFERT
   (       oid_ofer,
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
           ocat_oid_cata )
   (SELECT b.oid_nuev,
           a.coes_oid_estr,
           nvl((SELECT (MAX(c.num_ofer) + 1) FROM pre_ofert c where c.mfca_oid_cabe = pncabmat),1),
           a.num_orde,
           a.num_grup,
           a.num_grup_cndt,
           a.num_grup_cond,
           a.val_cond_g1_cndt,
           a.val_cond_g2_cndo,
           a.num_paqu,
           a.num_prim_posi_rank,
           a.num_ulti_posi_rank,
           a.fopa_oid_form_pago,
           a.sbac_oid_sbac,
           a.argv_oid_argu_vent,
           a.acce_oid_acce,
           pncabmat,
           a.ind_codi_vent_gene,
           a.ind_desp_compl,
           a.ind_desp_auto,
           a.ind_matr_fact_gene,
           a.ind_recu_obli,
           a.ind_regi_esta_gene,
           a.ocat_oid_cata
      FROM pre_ofert a,
           pre_tmp_oids_ofert b
     WHERE a.oid_ofer = b.oid_ante
       AND b.attr_enti = 'PRE_OFERT');

   --  Insertando en PRE_GRUPO_OFERT
   INSERT INTO pre_grupo_ofert
   (       oid_grup_ofer,
           ofer_oid_ofer,
           num_grup,
           cod_fact_cuad,
           cues_oid_ind_cuad_tipo_estr,
           ind_cndt,
           ind_cndo,
           ind_grup )
   (SELECT b.oid_nuev,
           (SELECT c.oid_nuev FROM pre_tmp_oids_ofert c WHERE c.oid_ante = a.ofer_oid_ofer AND c.attr_enti = 'PRE_OFERT'),
           a.num_grup,
           a.cod_fact_cuad,
           a.cues_oid_ind_cuad_tipo_estr,
           a.ind_cndt,
           a.ind_cndo,
           a.ind_grup
      FROM pre_grupo_ofert a,
           pre_tmp_oids_ofert b
     WHERE a.oid_grup_ofer = b.oid_ante
       AND b.attr_enti = 'PRE_GRUPO_OFERT'
       AND a.ofer_oid_ofer IN ( SELECT d.oid_ante FROM pre_tmp_oids_ofert d WHERE d.attr_enti = 'PRE_OFERT'));

   --  Insertando en PRE_GRUPO_OFERT
   INSERT INTO pre_ofert_detal
   (       oid_deta_ofer,
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
           num_orde_deta )
   (SELECT b.oid_nuev,
           (SELECT d.oid_nuev FROM pre_tmp_oids_ofert d WHERE d.oid_ante = a.ofer_oid_ofer AND d.attr_enti = 'PRE_OFERT'),
           a.prod_oid_prod,
           a.num_line_ofer,
           a.val_text_brev,
           a.num_unid_esti,
           a.cod_orig,
           a.val_fact_repe,
           a.num_posi_rank,
           a.ind_prod_prin,
           a.imp_prec_cata,
           a.imp_prec_posi,
           a.imp_cost_esta,
           a.imp_vent_neta_esti,
           a.num_pagi_cata,
           a.ocat_oid_catal,
           a.tofe_oid_tipo_ofer,
           a.civi_oid_ciclo_vida,
           a.cndp_oid_cond_prom,
           a.fopa_oid_form_pago,
           (SELECT e.oid_nuev FROM pre_tmp_oids_ofert e WHERE e.oid_ante = a.gofe_oid_grup_ofer AND e.attr_enti = 'PRE_GRUPO_OFERT'),
           a.ind_digi,
           a.ind_impr_gp,
           a.ind_codi_vent_gene,
           a.ind_matr_fact_gene,
           a.val_posi_pagi,
           decode(pnIndReordenar,'S',to_char(nvl((select max(val_codi_vent) from pre_ofert_detal where ofer_oid_ofer in (select oid_ofer from pre_ofert where mfca_oid_cabe=pncabmat)),95000) + rownum),a.val_codi_vent),
           a.val_cent,
           a.fec_ulti_actu,
           a.precio_unitario,
           a.num_punt_fijo,
           a.vari_oid_vari,
           a.prfi_oid_prog_fide,
           a.num_orde_deta
      FROM pre_ofert_detal a,
           pre_tmp_oids_ofert b
     WHERE a.oid_deta_ofer = b.oid_ante
       AND b.attr_enti = 'PRE_OFERT_DETAL'
       AND a.ofer_oid_ofer IN (SELECT c.oid_ante FROM pre_tmp_oids_ofert c WHERE c.attr_enti = 'PRE_OFERT'));

   --  Insertando en PRE_PROMO
   INSERT INTO pre_promo
   (       oid_prom,
           ofer_oid_ofer,
           num_cond,
           val_fact_cuad,
           icpr_oid_indi_cuad_prom )
   (SELECT b.oid_nuev,
           (SELECT d.oid_nuev FROM pre_tmp_oids_ofert d WHERE  d.oid_ante = ofer_oid_ofer AND d.attr_enti = 'PRE_OFERT'),
           a.num_cond,
           a.val_fact_cuad,
           a.icpr_oid_indi_cuad_prom
      FROM pre_promo a,
           pre_tmp_oids_ofert b
     WHERE a.oid_prom = b.oid_ante
       AND b.attr_enti = 'PRE_PROMO'
       AND a.ofer_oid_ofer IN ( SELECT c.oid_ante FROM pre_tmp_oids_ofert c WHERE c.attr_enti = 'PRE_OFERT'));

   --  Insertando en PRE_RANGO_PROMO
   INSERT INTO pre_rango_promo
   (       oid_rang_prom,
           ocat_oid_cata,
           pomo_oid_prom,
           cod_tipo_rang,
           num_rang_inte,
           val_desd,
           val_hast,
           ind_excl )
  (SELECT  b.oid_nuev,
           a.ocat_oid_cata,
           (SELECT e.oid_nuev FROM pre_tmp_oids_ofert e WHERE e.oid_ante = a.pomo_oid_prom AND e.attr_enti = 'PRE_PROMO'),
           a.cod_tipo_rang,
           a.num_rang_inte,
           a.val_desd,
           a.val_hast,
           a.ind_excl
      FROM pre_rango_promo a,
           pre_tmp_oids_ofert b
     WHERE a.oid_rang_prom = b.oid_ante
       AND b.attr_enti = 'PRE_RANGO_PROMO'
       AND a.pomo_oid_prom IN ( SELECT c.oid_prom FROM pre_promo c WHERE c.ofer_oid_ofer IN (SELECT d.oid_ante FROM pre_tmp_oids_ofert d WHERE d.attr_enti = 'PRE_OFERT')));

   --  Insertando en PRE_MATRI_FACTU
   INSERT INTO pre_matri_factu
   (       oid_matr_fact,
           cod_esta,
           mfca_oid_cabe,
           ofde_oid_deta_ofer,
           fec_ulti_actu,
           num_punt_fijo,
           vari_oid_vari,
           prfi_oid_prog_fide,
           ind_matr_fact )
   (SELECT b.oid_nuev,
           a.cod_esta,
           pncabmat,
           (SELECT d.oid_nuev FROM pre_tmp_oids_ofert d WHERE  d.oid_ante = a.ofde_oid_deta_ofer AND d.attr_enti = 'PRE_OFERT_DETAL'),
           a.fec_ulti_actu,
           a.num_punt_fijo,
           a.vari_oid_vari,
           a.prfi_oid_prog_fide,
           0
      FROM pre_matri_factu a,
           pre_tmp_oids_ofert b
     WHERE a.oid_matr_fact = b.oid_ante
       AND b.attr_enti = 'PRE_MATRI_FACTU'
       AND a.ofde_oid_deta_ofer IN (SELECT c.oid_deta_ofer FROM pre_ofert_detal c WHERE c.ofer_oid_ofer IN (SELECT d.oid_ante FROM pre_tmp_oids_ofert d WHERE d.attr_enti = 'PRE_OFERT')));

 EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,'ERROR PRE_PR_INSER_COPIA_OFERT: ' || ls_sqlerrm);

 END pre_pr_inser_copia_ofert;

/***************************************************************************
  Descripcion       : Valida si existen ofertas Facturadas
  Fecha Creacion    : 12/10/2010
    Autor           : José Luis Rodríguez

***************************************************************************/
  PROCEDURE pre_pr_valid_ofert_factu
  (
    psNumeroSecuencia     VARCHAR2,
    p_val_error       OUT VARCHAR2
  )
  IS
    vnContador       NUMBER;

   CURSOR c_oferta IS
     SELECT p.oid_ofer       OID_OFERTA,
            p.oid_deta_ofer  OID_DETA_OFERTA
       FROM pre_tmp_ofert_modif p
      WHERE p.num_secu_usua = psNumeroSecuencia;

   TYPE ofertas IS RECORD(
     oid_ofer       pre_ofert.oid_ofer%TYPE,
     oid_deta_ofer  pre_ofert_detal.oid_deta_ofer%TYPE
   );

    TYPE ofertasTab IS TABLE OF ofertas;
    ofertasRecord ofertasTab;
  BEGIN
    p_val_error := '0';
    vnContador      := 0;

    OPEN c_oferta;
      LOOP
        FETCH c_oferta BULK COLLECT INTO ofertasRecord LIMIT W_FILAS;
          IF ofertasRecord.COUNT > 0 THEN
            FOR x IN ofertasRecord.FIRST .. ofertasRecord.LAST LOOP

              IF (p_val_error = '0') THEN
                SELECT COUNT(1)
                  INTO vnContador
                  FROM pre_matri_factu
                 WHERE ofde_oid_deta_ofer = ofertasRecord(x).oid_deta_ofer
                   AND ind_matr_fact = 1;
              END IF;

              IF (vnContador > 0 AND p_val_error = '0' ) THEN
                p_val_error := '1';
              END IF;

            END LOOP;
          END IF;
        EXIT WHEN (c_oferta%NOTFOUND);
      END LOOP;
   	CLOSE c_oferta;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,'ERROR PRE_PR_VALID_OFERT_FACTU: ' || ls_sqlerrm);

 END pre_pr_valid_ofert_factu;

/***************************************************************************
Descripcion       : Valida la carga de estimados
Fecha Creacion    : 31/03/2013
Autor             : Sebastian Guerra
***************************************************************************/
PROCEDURE PRE_PR_VALID_CARGA_ESTIM
    (pnCodigoUsuario    VARCHAR2)
IS

CURSOR C_ESTIMADAS IS
SELECT NUM_FILA, CAMP_PROC, COD_SAP, COD_TIPO_OFER, COD_CICL_VIDA, UNI_ESTI,
       VEN_NETA_EST, PRE_CATA, PRE_POSI, VAL_CATA, NUM_PAGI, VAL_POSI,
       VAL_ESTR, COS_ESTA, MEN_ERRO
  FROM PRE_TEMPO_ESTIM_VENTA C
 WHERE COD_USUA = pnCodigoUsuario;

  TYPE interfazEstimadas IS RECORD
  (
    numeroFila              PRE_TEMPO_ESTIM_VENTA.NUM_FILA%TYPE,
    campanhia               PRE_TEMPO_ESTIM_VENTA.CAMP_PROC%TYPE,
    codSAP                  PRE_TEMPO_ESTIM_VENTA.COD_SAP%TYPE,
    codTipoOferta           PRE_TEMPO_ESTIM_VENTA.COD_TIPO_OFER%TYPE,
    codCicloVida            PRE_TEMPO_ESTIM_VENTA.COD_CICL_VIDA%TYPE,
    unidadesEstimadas       PRE_TEMPO_ESTIM_VENTA.UNI_ESTI%TYPE,
    ventaNetaEstimada       PRE_TEMPO_ESTIM_VENTA.VEN_NETA_EST%TYPE,
    precioCatalogo          PRE_TEMPO_ESTIM_VENTA.PRE_CATA%TYPE,
    precioPosicionamiento   PRE_TEMPO_ESTIM_VENTA.PRE_POSI%TYPE,
    catalogo                PRE_TEMPO_ESTIM_VENTA.VAL_CATA%TYPE,
    numeroPagina            PRE_TEMPO_ESTIM_VENTA.NUM_PAGI%TYPE,
    posicion                PRE_TEMPO_ESTIM_VENTA.VAL_POSI%TYPE,
    estrategia              PRE_TEMPO_ESTIM_VENTA.VAL_ESTR%TYPE,
    costeEstandar           PRE_TEMPO_ESTIM_VENTA.COS_ESTA%TYPE,
    mensajeError            PRE_TEMPO_ESTIM_VENTA.MEN_ERRO%TYPE
  );

  TYPE interfazEstimadasTab  IS TABLE OF interfazEstimadas;
  interfazRecordN interfazEstimadasTab;

  lsMensajeError            PRE_TEMPO_ESTIM_VENTA.MEN_ERRO%TYPE;
  lnNumeroFila              PRE_TEMPO_ESTIM_VENTA.NUM_FILA%TYPE;
  lnOcurrencias             NUMBER(12);
  lnValidacion              NUMBER(12);

BEGIN

  OPEN C_ESTIMADAS;
  LOOP
    FETCH C_ESTIMADAS BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN
      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        lnNumeroFila   :=interfazRecordN(x).numeroFila;
        lsMensajeError :='';

        --(1), Validamos si existe la campaña
        IF(interfazRecordN(x).campanhia IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Campaña no Existe,';
        ELSE
          BEGIN
            SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(interfazRecordN(x).campanhia)
              INTO lnvalidacion
              FROM DUAL;
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Campaña no Existe,';
          END;
        END IF;

        --(2), Validamos si existe el producto
        IF(interfazRecordN(x).codSAP IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Producto no Existe,';
        ELSE
            SELECT COUNT (1)
              INTO lnOcurrencias
              FROM MAE_PRODU
             WHERE COD_SAP = interfazRecordN(x).codSAP;
          IF(lnOcurrencias = 0) THEN
              lsMensajeError :=lsMensajeError || 'Producto no Existe,';
          END IF;

        END IF;

        --(3), Validamos si existe el tipo de oferta
        IF(interfazRecordN(x).codTipoOferta IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Tipo de Oferta no Existe,';
        ELSE
            SELECT COUNT (1)
              INTO lnOcurrencias
              FROM PRE_TIPO_OFERT
             WHERE COD_TIPO_OFER = interfazRecordN(x).codTipoOferta;
          IF(lnOcurrencias = 0) THEN
            lsMensajeError :=lsMensajeError || 'Tipo de Oferta no Existe,';
          END IF;
        END IF;

        --(4), Validamos si existe el ciclo de vida
        IF(interfazRecordN(x).codCicloVida IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Ciclo de Vida no Existe,';
        ELSE
            SELECT COUNT (1)
              INTO lnOcurrencias
              FROM PRE_CICLO_VIDA
             WHERE to_number(COD_CICL_VIDA) = to_number(interfazRecordN(x).codCicloVida);
          IF(lnOcurrencias = 0) THEN
            lsMensajeError :=lsMensajeError || 'Ciclo de Vida no Existe,';
          END IF;
        END IF;

        --(5), Validamos si la unidades estimada son numerica
        IF(interfazRecordN(x).unidadesEstimadas IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Unidades Estimadas sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).unidadesEstimadas, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Unidades Estimadas sólo puede contener valores numéricos,';
          END;
        END IF;

        --(6), Validamos si la venta neta estimada es numérica
        IF(interfazRecordN(x).ventaNetaEstimada IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Venta Neta Estimada sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).ventaNetaEstimada, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Venta Neta Estimada sólo puede contener valores numéricos,';
          END;
        END IF;

        --(7), Validamos si el precio catalogo es numérico
        IF(interfazRecordN(x).precioCatalogo IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Precio Catálogo sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).precioCatalogo, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Precio Catálogo sólo puede contener valores numéricos,';
          END;
        END IF;

        --(8), Validamos si el posicionamiento es numérico
        IF(interfazRecordN(x).precioPosicionamiento IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Precio Posicionamiento sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).precioPosicionamiento, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Precio Posicionamiento sólo puede contener valores numéricos,';
          END;
        END IF;

        --(9), Validamos si el catalogo existe
        IF(interfazRecordN(x).catalogo IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Catálogo no Existe,';
        ELSE
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM PRE_CATAL
             WHERE COD_CATA = interfazRecordN(x).catalogo;
          IF(lnOcurrencias = 0) THEN
            lsMensajeError :=lsMensajeError || 'Catálogo no Existe,';
          END IF;
        END IF;

        --(10), Validamos si el número de págin es numérico
        IF(interfazRecordN(x).numeroPagina IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Número de Página sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).numeroPagina, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Número de Página sólo puede contener valores numéricos,';
          END;
        END IF;

        --(11), Validamos si la posición es numérica
        IF(interfazRecordN(x).posicion IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Posición sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).posicion, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Posición sólo puede contener valores numéricos,';
          END;
        END IF;

        --(12), Validamos si la estrategia es numérica
        IF(interfazRecordN(x).estrategia IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Estrategia no Existe,';
        ELSE
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM PRE_TIPO_ESTRA
             WHERE COD_TIPO_ESTR = interfazRecordN(x).estrategia;
          IF(lnOcurrencias = 0) THEN
            lsMensajeError :=lsMensajeError || 'Estrategia no Existe,';
          END IF;
        END IF;

        --(13), Validamos si el coste estándar es numérico
        IF(interfazRecordN(x).costeEstandar IS NULL) THEN
          lsMensajeError :=lsMensajeError || 'Coste Estándar sólo puede contener valores numéricos,';
        ELSE
          BEGIN
            lnValidacion:=TO_NUMBER(interfazRecordN(x).costeEstandar, '999999999.99');
          EXCEPTION
            WHEN OTHERS THEN
            lsMensajeError :=lsMensajeError || 'Coste Estándar sólo puede contener valores numéricos,';
          END;
        END IF;

        BEGIN
            SELECT COUNT(*) INTO lnOcurrencias
              FROM PRE_ESTIM_VENTA
            WHERE PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(interfazRecordN(x).campanhia)
               AND PROD_OID_PROD = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PRODU(interfazRecordN(x).codSAP)
               AND TOFE_OID_TIPO_OFER IN(SELECT OID_TIPO_OFER FROM PRE_TIPO_OFERT WHERE COD_TIPO_OFER = interfazRecordN(x).codTipoOferta)
               AND CIVI_OID_CICL_VIDA IN(SELECT OID_CICL_VIDA FROM PRE_CICLO_VIDA WHERE COD_CICL_VIDA = interfazRecordN(x).codCicloVida);
            IF(lnOcurrencias>0) THEN
              lsMensajeError :=lsMensajeError || 'Ya existe el registro,';
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
              lsMensajeError:=lsMensajeError;
        END;

        IF(length(lsMensajeError) > 0) THEN
           lsMensajeError:=substr(lsMensajeError,1,length(lsMensajeError)-1);
           UPDATE PRE_TEMPO_ESTIM_VENTA
              SET EST_REGI = 0, MEN_ERRO = lsMensajeError
            WHERE COD_USUA = pnCodigoUsuario AND NUM_FILA = interfazRecordN(x).numeroFila;
        END IF;

        END LOOP;
     END IF;
     EXIT WHEN C_ESTIMADAS%NOTFOUND;
  END LOOP;
  CLOSE C_ESTIMADAS;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'PRE_PR_VALID_CARGA_ESTIM: ('|| lnNumeroFila || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END PRE_PR_VALID_CARGA_ESTIM;

/***************************************************************************
Descripcion       : Actualizar carga de estimados
Fecha Creacion    : 01/04/2013
Autor             : Sebastian Guerra
***************************************************************************/
PROCEDURE PRE_PR_ACTUA_CARGA_ESTIM
  (psCodigoPais        VARCHAR2,
   pnCodigoUsuario     VARCHAR2,
   psNombreArchivo     VARCHAR2)
IS

CURSOR C_ESTIMADAS IS
SELECT NUM_FILA, CAMP_PROC, COD_SAP, COD_TIPO_OFER, COD_CICL_VIDA, UNI_ESTI,
       VEN_NETA_EST, PRE_CATA, PRE_POSI, VAL_CATA, NUM_PAGI, VAL_POSI,
       VAL_ESTR, COS_ESTA
  FROM PRE_TEMPO_ESTIM_VENTA C
 WHERE EST_REGI = 1 AND COD_USUA = pnCodigoUsuario;

  TYPE interfazEstimadas IS RECORD
  (
    numeroFila              PRE_TEMPO_ESTIM_VENTA.NUM_FILA%TYPE,
    campanhia               PRE_TEMPO_ESTIM_VENTA.CAMP_PROC%TYPE,
    codSAP                  PRE_TEMPO_ESTIM_VENTA.COD_SAP%TYPE,
    codTipoOferta           PRE_TEMPO_ESTIM_VENTA.COD_TIPO_OFER%TYPE,
    codCicloVida            PRE_TEMPO_ESTIM_VENTA.COD_CICL_VIDA%TYPE,
    unidadesEstimadas       PRE_TEMPO_ESTIM_VENTA.UNI_ESTI%TYPE,
    ventaNetaEstimada       PRE_TEMPO_ESTIM_VENTA.VEN_NETA_EST%TYPE,
    precioCatalogo          PRE_TEMPO_ESTIM_VENTA.PRE_CATA%TYPE,
    precioPosicionamiento   PRE_TEMPO_ESTIM_VENTA.PRE_POSI%TYPE,
    catalogo                PRE_TEMPO_ESTIM_VENTA.VAL_CATA%TYPE,
    numeroPagina            PRE_TEMPO_ESTIM_VENTA.NUM_PAGI%TYPE,
    posicion                PRE_TEMPO_ESTIM_VENTA.VAL_POSI%TYPE,
    estrategia              PRE_TEMPO_ESTIM_VENTA.VAL_ESTR%TYPE,
    costeEstandar           PRE_TEMPO_ESTIM_VENTA.COS_ESTA%TYPE
  );

  TYPE interfazEstimadasTab  IS TABLE OF interfazEstimadas;
  interfazRecordN interfazEstimadasTab;

  lnNumeroFila              PRE_TEMPO_ESTIM_VENTA.NUM_FILA%TYPE;

BEGIN

  OPEN C_ESTIMADAS;
  LOOP
    FETCH C_ESTIMADAS BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN
      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        lnNumeroFila   :=interfazRecordN(x).numeroFila;

        INSERT INTO PRE_ESTIM_VENTA(
        OID_ESTIM_VENT,
        NUM_UNID_ESTI,
        NUM_PAGI_CATA,
        IMP_PREC_CATA,
        IMP_VENT_NETA_ESTI,
        IMP_PREC_POSI,
        PROD_OID_PROD,
        PERD_OID_PERI,
        FOPA_OID_FORM_PAGO,
        ARGV_OID_ARGU_VENT,
        CNDP_OID_COND_PROM,
        COES_OID_ESTR,
        MONE_OID_MONE,
        TOFE_OID_TIPO_OFER,
        CIVI_OID_CICL_VIDA,
        OCAT_OID_CATA,
        VAL_POSI_PAGI,
        VAL_CENT,
        IND_OFER_GENE,
        ACCE_OID_ACCE,
        SBAC_OID_SBAC,
        VAL_COST_ESTA,
        VARI_OID_VARI,
        PRFI_OID_PROG_FIDE,
        USU_CREA,
        FEC_CREA,
        NOM_ARCH
        )
        VALUES(
        PRE_ESVN_SEQ.nextval,
        interfazRecordN(X).unidadesEstimadas,
        interfazRecordN(X).numeroPagina,
        interfazRecordN(X).precioCatalogo / 100,
        interfazRecordN(X).ventaNetaEstimada / 100,
        interfazRecordN(X).precioPosicionamiento / 100,
        (
        SELECT OID_PROD
          FROM MAE_PRODU
         WHERE COD_SAP = interfazRecordN(X).codSAP
        ),
        (
        SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(interfazRecordN(x).campanhia)
          FROM DUAL
        ),
        NULL,
        (
        SELECT A.OID_ARGU_VENT
          FROM PRE_ARGUM_VENTA A, PRE_TIPO_ESTRA C
         WHERE A.TIES_OID_TIPO_ESTR = C.OID_TIPO_ESTR
           AND C.COD_TIPO_ESTR = interfazRecordN(X).estrategia
        ),
        2005,
        (
        SELECT oid_estr
          FROM pre_estra aa
         WHERE aa.ties_oid_tipo_estr = (select xx.oid_tipo_estr from pre_tipo_estra xx where xx.cod_tipo_estr=interfazRecordN(X).estrategia)
         and aa.tipr_oid_tipo_prod=1
         and aa.ind_cv_digi=1
        ),
        (
        SELECT mone_oid_mone
          FROM seg_pais
         WHERE cod_pais = psCodigoPais
        ),
        (
        SELECT oid_tipo_ofer
          FROM pre_tipo_ofert
         WHERE cana_oid_cana=2001 and cod_tipo_ofer = interfazRecordN(X).codTipoOferta
        ),
        (
        SELECT oid_cicl_vida
          FROM pre_ciclo_vida
         WHERE cod_cicl_vida = interfazRecordN(X).codCicloVida
        ),
        (
        SELECT oid_cata
          FROM pre_catal
         WHERE cod_cata = interfazRecordN(X).catalogo
        ),
        interfazRecordN(X).posicion,
        NULL,
        0,
        NULL,
        NULL,
        interfazRecordN(X).costeEstandar,
        NULL,
        NULL,
        pnCodigoUsuario,
        SYSDATE,
        psNombreArchivo
        );
        COMMIT;

        END LOOP;
     END IF;
     EXIT WHEN C_ESTIMADAS%NOTFOUND;
  END LOOP;
  CLOSE C_ESTIMADAS;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'PRE_PR_ACTUA_CARGA_ESTIM: ('|| lnNumeroFila || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END PRE_PR_ACTUA_CARGA_ESTIM;

/***************************************************************************
Descripcion       : Valida la carga de modificaciones masivas
Fecha Creacion    : 19/02/2014
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_VALID_CARGA_MODIF_MASIV
    (pnCodigoUsuario    VARCHAR2)
IS

CURSOR C_MODIF_MASIVAS IS
SELECT NUM_FILA, COD_TIPO_MODI, OID_DETAL_OFERT,
            VAL_VALOR, COD_CAMP, COD_CUV, COD_SAP,
            DES_TIPO_MODI, MEN_ERRO
  FROM PRE_TEMPO_MODIF_MASIV C
 WHERE COD_USUA = pnCodigoUsuario;

  TYPE interfazMasivas IS RECORD
  (
    numeroFila              PRE_TEMPO_MODIF_MASIV.NUM_FILA%TYPE,
    codTipoModi              PRE_TEMPO_MODIF_MASIV.COD_TIPO_MODI%TYPE,
    oidDetalOfer              PRE_TEMPO_MODIF_MASIV.OID_DETAL_OFERT%TYPE,
    valor              PRE_TEMPO_MODIF_MASIV.VAL_VALOR%TYPE,
    campana              PRE_TEMPO_MODIF_MASIV.COD_CAMP%TYPE,
    codCUV              PRE_TEMPO_MODIF_MASIV.COD_CUV%TYPE,
    codSAP              PRE_TEMPO_MODIF_MASIV.COD_SAP%TYPE,
    descTipoModi              PRE_TEMPO_MODIF_MASIV.DES_TIPO_MODI%TYPE,
    mensajeError              PRE_TEMPO_MODIF_MASIV.MEN_ERRO%TYPE
  );

  TYPE interfazMasivasTab  IS TABLE OF interfazMasivas;
  interfazRecordN interfazMasivasTab;

  lsMensajeError            PRE_TEMPO_MODIF_MASIV.MEN_ERRO%TYPE;
  lnNumeroFila              PRE_TEMPO_MODIF_MASIV.NUM_FILA%TYPE;
  lnOcurrencias             NUMBER(12);
  lnValidacion              NUMBER(12);

BEGIN

  OPEN C_MODIF_MASIVAS;
  LOOP
    FETCH C_MODIF_MASIVAS BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN
      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        lnNumeroFila   :=interfazRecordN(x).numeroFila;
        lsMensajeError :='';

        --(1) Validamos si existe el OidDetalleOferta
        IF (interfazRecordN(x).oidDetalOfer IS NULL) THEN
          lsMensajeError := lsMensajeError || 'No Existe OID de Oferta,';
        ELSE
            BEGIN
                SELECT (interfazRecordN(x).oidDetalOfer * 1)
                    INTO lnOcurrencias
                   FROM DUAL;

                    SELECT COUNT(1)
                        INTO lnOcurrencias
                       FROM PRE_MATRI_FACTU_CABEC A,
                                CRA_PERIO B,
                                SEG_PERIO_CORPO C,
                                PRE_OFERT D,
                                PRE_OFERT_DETAL E,
                                MAE_PRODU F
                     WHERE A.PERD_OID_PERI = B.OID_PERI
                         AND B.PERI_OID_PERI = C.OID_PERI
                         AND E.PROD_OID_PROD = F.OID_PROD
                         AND A.OID_CABE = D.MFCA_OID_CABE
                         AND D.OID_OFER = E.OFER_OID_OFER
                         AND E.OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;

                    UPDATE PRE_TEMPO_MODIF_MASIV
                          SET COD_CAMP = (SELECT C.COD_PERI
                                                         FROM PRE_MATRI_FACTU_CABEC A,
                                                                  CRA_PERIO B,
                                                                  SEG_PERIO_CORPO C,
                                                                  PRE_OFERT D,
                                                                  PRE_OFERT_DETAL E,
                                                                  MAE_PRODU F
                                                       WHERE A.PERD_OID_PERI = B.OID_PERI
                                                           AND B.PERI_OID_PERI = C.OID_PERI
                                                           AND E.PROD_OID_PROD = F.OID_PROD
                                                           AND A.OID_CABE = D.MFCA_OID_CABE
                                                           AND D.OID_OFER = E.OFER_OID_OFER
                                                           AND E.OID_DETA_OFER = interfazRecordN(x).oidDetalOfer),
                                 COD_CUV = (SELECT E.VAL_CODI_VENT
                                                       FROM PRE_MATRI_FACTU_CABEC A,
                                                                CRA_PERIO B,
                                                                SEG_PERIO_CORPO C,
                                                                PRE_OFERT D,
                                                                PRE_OFERT_DETAL E,
                                                                MAE_PRODU F
                                                     WHERE A.PERD_OID_PERI = B.OID_PERI
                                                         AND B.PERI_OID_PERI = C.OID_PERI
                                                         AND E.PROD_OID_PROD = F.OID_PROD
                                                         AND A.OID_CABE = D.MFCA_OID_CABE
                                                         AND D.OID_OFER = E.OFER_OID_OFER
                                                         AND E.OID_DETA_OFER = interfazRecordN(x).oidDetalOfer),
                                  COD_SAP = (SELECT F.COD_SAP
                                                       FROM PRE_MATRI_FACTU_CABEC A,
                                                                CRA_PERIO B,
                                                                SEG_PERIO_CORPO C,
                                                                PRE_OFERT D,
                                                                PRE_OFERT_DETAL E,
                                                                MAE_PRODU F
                                                     WHERE A.PERD_OID_PERI = B.OID_PERI
                                                         AND B.PERI_OID_PERI = C.OID_PERI
                                                         AND E.PROD_OID_PROD = F.OID_PROD
                                                         AND A.OID_CABE = D.MFCA_OID_CABE
                                                         AND D.OID_OFER = E.OFER_OID_OFER
                                                         AND E.OID_DETA_OFER = interfazRecordN(x).oidDetalOfer)
                        WHERE NUM_FILA = interfazRecordN(x).numeroFila;

            IF lnOcurrencias = 0 THEN
                lsMensajeError :=lsMensajeError || 'No Existe OID de Oferta,';
            END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'No Existe OID de Oferta,';
            END;
        END IF;

        --(2) Validamos si existe el Codigo Tipo Modificacion
        IF (interfazRecordN(x).codTipoModi IS NULL) THEN
          lsMensajeError := lsMensajeError || 'No Existe el Tipo de Modificación,';
        ELSE
            SELECT COUNT(1)
                INTO lnOcurrencias
               FROM PRE_TIPO_MODIF
             WHERE COD_TIPO_MODI = interfazRecordN(x).codTipoModi;

            IF lnOcurrencias = 0 THEN
                lsMensajeError :=lsMensajeError || 'No Existe el Tipo de Modificación,';
            END IF;
        END IF;

        --(3) Validamos si el Codigo Tipo Modificacion = ACC
        IF (interfazRecordN(x).codTipoModi = 'ACC') THEN
            SELECT COUNT(1)
                INTO lnOcurrencias
              FROM SEG_ACCES
            WHERE COD_ACCE = interfazRecordN(x).valor;
     
            IF (interfazRecordN(x).valor is not null) then
               lnOcurrencias := NULL; 
            END IF;   
            
            IF lnOcurrencias = 0 THEN                
                lsMensajeError :=lsMensajeError || 'NoS Existe el Acceso,';
            END IF;
            
            
        END IF;

        --(4) Validamos si el Codigo Tipo Modificacion = CUV
        IF (interfazRecordN(x).codTipoModi = 'CUV') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;

            IF length(interfazRecordN(x).valor) != 5 THEN
                lsMensajeError :=lsMensajeError || 'El CUV ingresado no es válido,';
            END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'Valor no es numérico,';
            END;
        END IF;

        --(5) Validamos si el Codigo Tipo Modificacion = PAG
        IF (interfazRecordN(x).codTipoModi = 'PAG') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;

                IF length(interfazRecordN(x).valor) > 3 THEN
                    lsMensajeError :=lsMensajeError || 'Número de página no válido,';
                END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'Valor no es numérico,';
            END;
        END IF;

        --(6) Validamos si el Codigo Tipo Modificacion = PCA o PCC
        IF (interfazRecordN(x).codTipoModi = 'PCA' OR interfazRecordN(x).codTipoModi = 'PCC') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'El monto no es válido,';
            END;
        END IF;

        --(7) Validamos si el Codigo Tipo Modificacion = TOF
        IF (interfazRecordN(x).codTipoModi = 'TOF') THEN
            SELECT COUNT(1)
                INTO lnOcurrencias
              FROM PRE_TIPO_OFERT
            WHERE COD_TIPO_OFER = interfazRecordN(x).valor;

            IF lnOcurrencias = 0 THEN
                lsMensajeError :=lsMensajeError || 'No Existe el Tipo de Oferta,';
            END IF;
        END IF;

        --(8) Validamos si el Codigo Tipo Modificacion = SAP
        IF (interfazRecordN(x).codTipoModi = 'SAP') THEN
            SELECT COUNT(1)
                INTO lnOcurrencias
              FROM MAE_PRODU
            WHERE COD_SAP = interfazRecordN(x).valor;

            IF lnOcurrencias = 0 THEN
                lsMensajeError :=lsMensajeError || 'No Existe el Código de Producto,';
            END IF;
        END IF;

        --(9) Validamos si el Codigo Tipo Modificacion = DIG
        IF (interfazRecordN(x).codTipoModi = 'DIG') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;

                IF ( length(interfazRecordN(x).valor) <> 1 or
                     lnOcurrencias > 1)
                  THEN
                    lsMensajeError :=lsMensajeError || 'Indicador digitable no valido,';
                END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'Valor no es numerico,';
            END;
        END IF;
        --(10) Validamos si el Codigo Tipo Modificacion = IMP
         IF (interfazRecordN(x).codTipoModi = 'IMP') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;

                IF ( length(interfazRecordN(x).valor) <> 1 or
                     lnOcurrencias > 1)
                  THEN
                    lsMensajeError :=lsMensajeError || 'Indicador imprimible no valido,';
                END IF;
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'Valor no es numerico,';
            END;
        END IF;
        -- (11) Validamos si el Codigo Tipo Modificacion = RNK  
         IF (interfazRecordN(x).codTipoModi = 'RNK') THEN
            BEGIN
                lnOcurrencias := NULL;

                SELECT (interfazRecordN(x).valor * 1)
                    INTO lnOcurrencias
                  FROM DUAL;                
            EXCEPTION
                WHEN OTHERS THEN
                    lsMensajeError :=lsMensajeError || 'Valor no es numérico,';
            END;
        END IF;
        -- (12) Validamos si el Codigo Tipo Modificacion = EST  
         IF (interfazRecordN(x).codTipoModi = 'EST') THEN
            SELECT COUNT(1)
                INTO lnOcurrencias
              FROM PRE_ESTRA
            WHERE COD_ESTR = interfazRecordN(x).valor;

            IF lnOcurrencias = 0 THEN
                lsMensajeError :=lsMensajeError || 'No Existe la Estrategia,';
            END IF;
        END IF;

        
        
        /*BEGIN
            SELECT COUNT(*) INTO lnOcurrencias
              FROM PRE_ESTIM_VENTA
            WHERE PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(interfazRecordN(x).campanhia)
               AND PROD_OID_PROD = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PRODU(interfazRecordN(x).codSAP)
               AND TOFE_OID_TIPO_OFER IN(SELECT OID_TIPO_OFER FROM PRE_TIPO_OFERT WHERE COD_TIPO_OFER = interfazRecordN(x).codTipoOferta)
               AND CIVI_OID_CICL_VIDA IN(SELECT OID_CICL_VIDA FROM PRE_CICLO_VIDA WHERE COD_CICL_VIDA = interfazRecordN(x).codCicloVida);
            IF(lnOcurrencias>0) THEN
              lsMensajeError :=lsMensajeError || 'Ya existe el registro,';
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
              lsMensajeError:=lsMensajeError;
        END;*/

        IF(length(lsMensajeError) > 0) THEN
           lsMensajeError := substr(lsMensajeError, 1, length(lsMensajeError)-1);

           UPDATE PRE_TEMPO_MODIF_MASIV
                 SET MEN_ERRO = lsMensajeError
            WHERE COD_USUA = pnCodigoUsuario
                 AND NUM_FILA = interfazRecordN(x).numeroFila;
        END IF;

        END LOOP;
     END IF;
     EXIT WHEN C_MODIF_MASIVAS%NOTFOUND;
  END LOOP;
  CLOSE C_MODIF_MASIVAS;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'PRE_PR_VALID_CARGA_MODIF_MASIV: ('|| lnNumeroFila || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END PRE_PR_VALID_CARGA_MODIF_MASIV;

/***************************************************************************
Descripcion       : Actualizar carga de modificaciones masivas
Fecha Creacion    : 20/02/2014
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_ACTUA_CARGA_MODIF_MASIV
  (psCodigoPais        VARCHAR2,
   pnCodigoUsuario     VARCHAR2)
IS

CURSOR C_MODIF_MASIVAS IS
SELECT NUM_FILA, COD_TIPO_MODI, OID_DETAL_OFERT,
            VAL_VALOR, COD_CAMP, COD_CUV, COD_SAP,
            DES_TIPO_MODI, MEN_ERRO, COD_USUA
  FROM PRE_TEMPO_MODIF_MASIV C
 WHERE COD_USUA = pnCodigoUsuario;

  TYPE interfazMasivas IS RECORD
  (
    numeroFila              PRE_TEMPO_MODIF_MASIV.NUM_FILA%TYPE,
    codTipoModi              PRE_TEMPO_MODIF_MASIV.COD_TIPO_MODI%TYPE,
    oidDetalOfer              PRE_TEMPO_MODIF_MASIV.OID_DETAL_OFERT%TYPE,
    valor              PRE_TEMPO_MODIF_MASIV.VAL_VALOR%TYPE,
    campana              PRE_TEMPO_MODIF_MASIV.COD_CAMP%TYPE,
    codCUV              PRE_TEMPO_MODIF_MASIV.COD_CUV%TYPE,
    codSAP              PRE_TEMPO_MODIF_MASIV.COD_SAP%TYPE,
    descTipoModi              PRE_TEMPO_MODIF_MASIV.DES_TIPO_MODI%TYPE,
    mensajeError              PRE_TEMPO_MODIF_MASIV.MEN_ERRO%TYPE,
    codUsuario                  PRE_TEMPO_MODIF_MASIV.COD_USUA%TYPE
  );

  TYPE interfazMasivasTab  IS TABLE OF interfazMasivas;
  interfazRecordN interfazMasivasTab;

  lnNumeroFila              PRE_TEMPO_MODIF_MASIV.NUM_FILA%TYPE;

BEGIN

  OPEN C_MODIF_MASIVAS;
  LOOP
    FETCH C_MODIF_MASIVAS BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN
      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        lnNumeroFila   :=interfazRecordN(x).numeroFila;

        IF interfazRecordN(x).codTipoModi = 'ACC' THEN
            UPDATE PRE_OFERT
                  SET ACCE_OID_ACCE = (SELECT OID_ACCE
                                                         FROM SEG_ACCES
                                                       WHERE COD_ACCE = interfazRecordN(x).valor)
              WHERE OID_OFER = (SELECT OFER_OID_OFER
                                                FROM PRE_OFERT_DETAL
                                              WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer);
        END IF;

        IF interfazRecordN(x).codTipoModi = 'CUV' THEN
            UPDATE PRE_OFERT_DETAL
                  SET VAL_CODI_VENT = interfazRecordN(x).valor
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'PAG' THEN
            UPDATE PRE_OFERT_DETAL
                  SET NUM_PAGI_CATA = interfazRecordN(x).valor
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'PCA' THEN
            UPDATE PRE_OFERT_DETAL
                  SET IMP_PREC_CATA = interfazRecordN(x).valor / 100
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;

            UPDATE PRE_OFERT_DETAL
                  SET PRECIO_UNITARIO = IMP_PREC_CATA / VAL_FACT_REPE
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'PCC' THEN
            UPDATE PRE_OFERT_DETAL
                  SET IMP_PREC_POSI = interfazRecordN(x).valor / 100
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'TOF' THEN
            UPDATE PRE_OFERT_DETAL
                  SET TOFE_OID_TIPO_OFER = (SELECT OID_TIPO_OFER
                                                                  FROM PRE_TIPO_OFERT
                                                                WHERE COD_TIPO_OFER = interfazRecordN(x).valor)
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'SAP' THEN
            UPDATE PRE_OFERT_DETAL
                  SET PROD_OID_PROD = (SELECT OID_PROD
                                                          FROM MAE_PRODU
                                                        WHERE COD_SAP = interfazRecordN(x).valor)
              WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;

        IF interfazRecordN(x).codTipoModi = 'DIG' THEN
            UPDATE PRE_OFERT_DETAL
                  SET IND_DIGI = interfazRecordN(x).valor
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;
        
        IF interfazRecordN(x).codTipoModi = 'IMP' THEN
            UPDATE PRE_OFERT_DETAL
                  SET IND_IMPR_GP = interfazRecordN(x).valor
             WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer;
        END IF;
        
        IF interfazRecordN(x).codTipoModi = 'RNK' THEN          
            UPDATE PRE_OFERT_DETAL 
               SET num_posi_rank=interfazRecordN(x).valor
               WHERE OID_DETA_OFER = interfazRecordN(x).oidDetalOfer; 
        END IF;
        
        IF interfazRecordN(x).codTipoModi = 'EST' THEN
            UPDATE PRE_OFERT
                  SET coes_oid_estr = ( SELECT OID_ESTR
                                      FROM PRE_ESTRA
                                      WHERE COD_ESTR = interfazRecordN(x).valor) ,
                   num_ofer = num_ofer+1000  
             WHERE oid_ofer=(select ofer_oid_ofer from pre_ofert_detal where oid_deta_ofer=interfazRecordN(x).oidDetalOfer);
        END IF;
                         
        INSERT INTO PRE_TEMPO_MODIF_MASIV_AUDIT(
                    COD_TIPO_MODI,
                    OID_DETAL_OFERT,
                    VAL_VALOR,
                    COD_USUA,
                    FEC_HORA_REGI)
        VALUES (
                    interfazRecordN(x).codTipoModi,
                    interfazRecordN(x).oidDetalOfer,
                    interfazRecordN(x).valor,
                    interfazRecordN(x).codUsuario,
                    SYSDATE);

        END LOOP;
     END IF;
     EXIT WHEN C_MODIF_MASIVAS%NOTFOUND;
  END LOOP;
  CLOSE C_MODIF_MASIVAS;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'PRE_PR_ACTUA_CARGA_MODIF_MASIV: ('|| lnNumeroFila || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END PRE_PR_ACTUA_CARGA_MODIF_MASIV;

/***************************************************************************
Descripcion       : Valida Restricciones en Productos de Ofertas SICC
Fecha Creacion    : 18/09/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRE_FN_VALID_RESTR_OFERT
  (pnOidTipoOferta    NUMBER,
   psCodigoProducto   VARCHAR2,
   pnOidEstategia     NUMBER,
   pnPrecioCatalogo   NUMBER,
   pnPrecioPosic      NUMBER) RETURN VARCHAR2
IS
  lnOcurrencias       NUMBER;
  lnOcurrencias1       NUMBER;
  lnOcurrencias2       NUMBER;
  lnOcurrencias3       NUMBER;
  lnOcurrencias4       NUMBER;
  lnOcurrencias5       NUMBER;
  lnOcurrencias6       NUMBER;
  lnOcurrencias7       NUMBER;
  lnOcurrencias8       NUMBER;


  lbValidacion1       BOOLEAN;
  lbValidacion2       BOOLEAN;
  lbValidacion4       BOOLEAN;
  lbValidacion5       BOOLEAN;
  lbValidacion6       BOOLEAN;
  lbValidacion7       BOOLEAN;
  lbValidacion8       BOOLEAN;

  lsMsgValidacion1    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion2    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion4    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion5    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion6    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion7    PRE_VALID_MATRI.DES_VALI%TYPE;
  lsMsgValidacion8    PRE_VALID_MATRI.DES_VALI%TYPE;
BEGIN
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM PRE_VALID_MATRI
   WHERE IND_ACTI = 1;

  --Si no tiene validaciones activas, se termina la ejecucion
  IF(lnOcurrencias = 0) THEN
    RETURN '';
  END IF;

  FOR y IN (SELECT OID_VALI_MATR, DES_MENS
              FROM PRE_VALID_MATRI
             WHERE IND_ACTI = 1
             ORDER BY 1) LOOP

    IF(y.OID_VALI_MATR = 1) THEN
      lbValidacion1 := TRUE;
      lsMsgValidacion1 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 2) THEN
      lbValidacion2 := TRUE;
      lsMsgValidacion2 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 4) THEN
      lbValidacion4 := TRUE;
      lsMsgValidacion4 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 5) THEN
      lbValidacion5 := TRUE;
      lsMsgValidacion5 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 6) THEN
      lbValidacion6 := TRUE;
      lsMsgValidacion6 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 7) THEN
      lbValidacion7 := TRUE;
      lsMsgValidacion7 := y.DES_MENS;
    END IF;

    IF(y.OID_VALI_MATR = 8) THEN
      lbValidacion8 := TRUE;
      lsMsgValidacion8 := y.DES_MENS;
    END IF;

  END LOOP;

  --Validacion 1: Negocio, Marca
  IF(lbValidacion1) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias1
      FROM PRE_VALID_MARCA_NEGOC val
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta;



    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_MARCA_NEGOC val,
           MAE_PRODU pro
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND pro.COD_SAP = psCodigoProducto
       AND pro.MAPR_OID_MARC_PROD = val.OID_MARC_PROD
       AND pro.NEGO_OID_NEGO = val.OID_NEGO
       ;



    IF(lnOcurrencias = 0) and (lnOcurrencias1>0) THEN
      RETURN lsMsgValidacion1;
    END IF;
  END IF;

  --Validacion 2: Negocio, Forma de Venta, Marca
  IF(lbValidacion2) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias2
      FROM PRE_VALID_MARCA_NEGOC_FV val ,
           PRE_TIPO_OFERT ofe
     WHERE ofe.OID_TIPO_OFER = pnOidTipoOferta
       AND val.VAL_FORM_VENT = ofe.VAL_FORM_VENT;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_MARCA_NEGOC_FV val,
           PRE_TIPO_OFERT ofe,
           MAE_PRODU pro
     WHERE ofe.OID_TIPO_OFER = pnOidTipoOferta
       AND pro.COD_SAP = psCodigoProducto
       AND val.VAL_FORM_VENT = ofe.VAL_FORM_VENT
       AND pro.MAPR_OID_MARC_PROD = val.OID_MARC_PROD
       AND pro.NEGO_OID_NEGO = val.OID_NEGO;

    IF lnOcurrencias = 0 and lnOcurrencias2>0 THEN
      RETURN lsMsgValidacion2;
    END IF;
  END IF;

  --Validacion 4: Tipo de Oferta
  IF(lbValidacion4) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias4
      FROM PRE_VALID_PREC2 val ,
           PRE_TIPO_OFERT ofe
     WHERE val.tofe_oid_tipo_ofer = pnOidTipoOferta
     ;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_PREC2 val
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND ((val.ind_gratis = 1 AND pnPrecioCatalogo = 0) OR
            (val.ind_gratis = 2 AND pnPrecioCatalogo > 0) OR
            (val.ind_gratis NOT IN (1,2) ));

    IF(lnOcurrencias = 0 and lnOcurrencias4>0) THEN
      RETURN lsMsgValidacion4;
    END IF;
  END IF;

  --Validacion 5: Tipo de Oferta, Precio Contable, Precio Unitario
  IF(lbValidacion5) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias5
      FROM PRE_VALID_PREC1 val ,
           PRE_TIPO_OFERT ofe
     WHERE val.tofe_oid_tipo_ofer = pnOidTipoOferta
     ;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_PREC1 val
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND pnPrecioCatalogo = pnPrecioPosic;

    IF(lnOcurrencias = 0 and lnOcurrencias5>0) THEN
      RETURN lsMsgValidacion5;
    END IF;
  END IF;

  --Validacion 6: Tipo de Producto
  IF(lbValidacion6) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias6
      FROM PRE_VALID_TIPO_PRODU val ,
           PRE_TIPO_OFERT ofe
     WHERE val.tofe_oid_tipo_ofer = pnOidTipoOferta
     ;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_TIPO_PRODU val,
           MAE_PRODU pro
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND pro.COD_SAP = psCodigoProducto
       AND pro.VAL_JERA_3 = val.VAL_TIPO_PROD;

    IF(lnOcurrencias = 0 and lnOcurrencias6>0) THEN
      RETURN lsMsgValidacion6;
    END IF;
  END IF;

  --Validacion 7: Tipo de Oferta, Precio Catálogo, Precio Unitario, Negocio
  IF(lbValidacion7) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias7
      FROM PRE_VALID_CODIG_SAP val ,
           PRE_TIPO_OFERT ofe
     WHERE val.tofe_oid_tipo_ofer = pnOidTipoOferta
     ;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_CODIG_SAP val,
           MAE_PRODU pro
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND pro.COD_SAP = psCodigoProducto
       AND pro.COD_SAP LIKE val.VAL_CODI_SAP || '%';

    IF(lnOcurrencias = 0 and lnOcurrencias7>0) THEN
      RETURN lsMsgValidacion7;
    END IF;
  END IF;


  --Validacion 8: Estrategia, Tipo de Oferta
  IF(lbValidacion8) THEN

    SELECT COUNT(1)
      INTO lnOcurrencias8
      FROM PRE_VALID_ESTRA val ,
           PRE_TIPO_OFERT ofe
     WHERE val.tofe_oid_tipo_ofer = pnOidTipoOferta
     ;

    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PRE_VALID_ESTRA val
     WHERE val.TOFE_OID_TIPO_OFER = pnOidTipoOferta
       AND val.OID_ESTR = pnOidEstategia;

    IF(lnOcurrencias = 0 and lnOcurrencias8>0) THEN
      RETURN lsMsgValidacion8;
    END IF;
  END IF;

  RETURN '';

EXCEPTION
WHEN OTHERS THEN
    RETURN 'Error al realizar las validaciones de las Restricciones';
END PRE_FN_VALID_RESTR_OFERT;

/***************************************************************************
Descripcion       : Valida Restricciones en Productos de Recuperacion SICC
Fecha Creacion    : 18/09/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRE_FN_VALID_PRODU_RECUP
  (pnOidMatriPrinc    NUMBER,
   pnOidMatriRecup    NUMBER) RETURN VARCHAR2
IS
  lnOcurrencias       NUMBER;
  lsMsgValidacion3    PRE_VALID_MATRI.DES_VALI%TYPE;

BEGIN
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM PRE_VALID_MATRI
   WHERE OID_VALI_MATR = 3
     AND IND_ACTI = 1;

  --Si no tiene validaciones activas, se termina la ejecucion
  IF(lnOcurrencias = 0) THEN
    RETURN '';
  END IF;

  --Obtenemos la descripcion del Mensaje a Mostrar
  SELECT DES_MENS
    INTO lsMsgValidacion3
    FROM PRE_VALID_MATRI
   WHERE OID_VALI_MATR = 3;

  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM PRE_MATRI_FACTU pri,
         PRE_OFERT_DETAL det,
         PRE_TIPO_OFERT tip,
         PRE_MATRI_FACTU rec,
         PRE_OFERT_DETAL detc
   WHERE pri.OFDE_OID_DETA_OFER = det.OID_DETA_OFER
     AND det.TOFE_OID_TIPO_OFER = tip.OID_TIPO_OFER
     AND rec.OFDE_OID_DETA_OFER = detc.OID_DETA_OFER
     AND pri.oid_matr_fact = pnOidMatriPrinc
     AND rec.oid_matr_fact = pnOidMatriRecup
     AND detc.TOFE_OID_TIPO_OFER = tip.TOFE_OID_TIPO_OFER_RECU;

  IF(lnOcurrencias = 0) THEN
      RETURN lsMsgValidacion3;
  END IF;

  RETURN '';

EXCEPTION
WHEN OTHERS THEN
    RETURN 'Error al realiza las validacion del Producto de Recuperacion';
END PRE_FN_VALID_PRODU_RECUP;

/***************************************************************************
Descripcion       : Envio de correo electronico en Matriz Carga Planit
Fecha Creacion    : 10/08/2015
Autor             : Richard Argomedo
***************************************************************************/
PROCEDURE int_pre_enema_carg_matr_plan
  (
    lpnumeroLote     VARCHAR2,
    pscodigopais     VARCHAR2,
    pserrorLog       VARCHAR2
    
  ) IS
  
    regreporte bas_repor_param%ROWTYPE;
  
    l_mail_conn utl_smtp.connection;
  
    lssubject       VARCHAR2(500);
    lslinea         VARCHAR2(4000);
    lnregistro     NUMBER;
  
    CURSOR cursorhistlotes IS
      SELECT num_lote, nom_arch, num_regi, nom_camp, obs_erro
      FROM PRE_ERRO_PLANI 
      WHERE num_lote = lpnumeroLote ORDER BY num_regi;
  
  BEGIN
      SELECT COUNT(1)
      INTO lnregistro
      FROM PRE_ERRO_PLANI 
      WHERE num_lote = lpnumeroLote;
  
      SELECT *
        INTO regreporte
        FROM bas_repor_param
       WHERE pais_cod_pais = pscodigopais
       AND nom_repo = 'InterfazPRECargaMatrizPlanit';
  
    IF(pserrorLog IS NULL) THEN
      /*SELECT COUNT(1)
      INTO lnregistro
      FROM PRE_ERRO_PLANI 
      WHERE num_lote = lpnumeroLote;
  
      SELECT *
        INTO regreporte
        FROM bas_repor_param
       WHERE pais_cod_pais = pscodigopais
         AND nom_repo = 'InterfazPRECargaMatrizPlanit';*/
   
      IF (lnregistro > 0) THEN
      
        lssubject := 'Error '||regreporte.val_subj||', '||pscodigopais||' numeroLote: '||lpnumeroLote;    
        l_mail_conn := log_email.begin_mail(sender     => regreporte.ema_orig,
                                            recipients => regreporte.ema_copi,
                                            subject    => lssubject,
                                            mime_type  => 'text/html');
      
        lslinea := lslinea ||
                   '<html><head><meta content="text/html charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
        lslinea := lslinea ||
                   '<table border="0" cellpadding="0" cellspacing="0">';
        lslinea := lslinea ||
                   '<tbody><tr><td><font face="Arial" size="2">Se detallan la lista de errores del procesamiento de los archivos:</font></td></tr><tr><td></td></tr>';
        lslinea := lslinea ||
                   '<tr><td width="95%"><table border="1" cellpadding="0" cellspacing="0"><tr>';
        lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>LOTE</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>NOMBRE DE ARCHIVO</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>NRO REG</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>NOMBRE DEL CAMPO</b></font></td>';
        lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>DESCRIPCION DE ERRORES</b></font></td></tr>';
      
        log_email.write_text(l_mail_conn, lslinea);
      
      
        FOR clote IN cursorhistlotes
        LOOP
          lslinea := '<tr>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     clote.num_lote || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     clote.nom_arch || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     clote.num_regi || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     clote.nom_camp || '</font></td>';
          lslinea := lslinea ||
                     '<td align="center"><font face="Arial" size="2">' ||
                     clote.obs_erro || '</font></td>';
          lslinea := lslinea ||                  
                     '</tr>';
        
          log_email.write_text(l_mail_conn, lslinea);
         
        END LOOP;
          
        lslinea := '</table></td></tr><tr><td></td></tr>';
    
        lslinea := lslinea ||'<tr><td></td></tr>';
    
        lslinea := lslinea ||
                 '<tr><td><br/><br/><br/><br/><font face="Arial" size="2"><strong>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada. </strong></font><br/><br/><br/><br/></td></tr>';
        lslinea := lslinea || '</tbody></table></body></html>';
      
        log_email.write_text(l_mail_conn, lslinea);
    
        ELSE
          lssubject := regreporte.val_subj||', '||pscodigopais||' numeroLote: '||lpnumeroLote;    
          l_mail_conn := log_email.begin_mail(sender     => regreporte.ema_orig,
                                            recipients => regreporte.ema_copi,
                                            subject    => lssubject,
                                            mime_type  => 'text/html');
                                            
          lslinea := lslinea ||
                   '<font face="Arial" size="2">El proceso se realizo correctamente. Con el numero de lote: '||lpnumeroLote|| '</font>';
          lslinea := lslinea ||
                   '<br/><br/><br/><br/><font face="Arial" size="2"><strong>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada. </strong></font><br/><br/><br/><br/>';
                   
          log_email.write_text(l_mail_conn, lslinea);
      END IF;
        log_email.end_mail(conn => l_mail_conn);
       
    ELSE
      lssubject := 'Error '||regreporte.val_subj||', '||pscodigopais||' numeroLote: '||lpnumeroLote;    
      l_mail_conn := log_email.begin_mail(sender     => regreporte.ema_orig,
                                          recipients => regreporte.ema_copi,
                                          subject    => lssubject,
                                          mime_type  => 'text/html');
      
      lslinea := lslinea ||
                   '<html><head><meta content="text/html charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
      lslinea := lslinea ||
                   '<table border="0" cellpadding="0" cellspacing="0">';
      lslinea := lslinea ||
                   '<tbody><tr><td><font face="Arial" size="2">Ocurrio un error al procesar los archivos, se ajunta el error:</font></td></tr><tr><td></td></tr>';
      lslinea := lslinea ||
                   '<tr><td width="95%"><table border="1" cellpadding="0" cellspacing="0"><tr>';
      lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>LOTE</b></font></td>';
      lslinea := lslinea ||
                   '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>DESCRIPCION DE ERRORES</b></font></td></tr>';            
      lslinea := lslinea || '<tr><td align="center"><font face="Arial" size="2">' || lpnumeroLote || '</td>'; 
      lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' || pserrorLog || '</td>'; 
      lslinea := lslinea ||                  
                     '</tr></tbody></table>';
      lslinea := lslinea ||
                   '<br/><br/><br/><br/><font face="Arial" size="2"><strong>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada. </strong></font><br/><br/><br/><br/>';
      lslinea := lslinea || '</body></html>';       
               
      log_email.write_text(l_mail_conn, lslinea);      
      log_email.end_mail(conn => l_mail_conn);   
       /*dbms_output.put_line(lslinea);*/
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pre_enema_carg_matr_plan: **' || ls_sqlerrm);
  END int_pre_enema_carg_matr_plan;
  
  
/***************************************************************************
Descripcion       : Generación de data para Reporte Configuración Ofertas (Concurso)
Fecha Creacion    : 02/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRE_PR_REPOR_CONFI_OFERT_CONCU (psCodigoPeriodo VARCHAR2) IS
 CURSOR cursorConcurso IS
   SELECT DISTINCT I.OID_NIVE_OFER,
                C.COD_PERI,
                G.DES_CATA,
                I.NUM_PAGI,
                DECODE(I.TIP_NIVE,
                       '1',
                       'Niveles de Precios',
                       '2',
                       'Niveles de Concursos',
                       '') TIP_NIVE,
                DECODE(I.TIP_CUAD, '1', 'Unidades', '2', 'Monto', '') TIP_CUAD,
                G.OID_CATA,
                I.TIP_NIVE COD_TIP_NIVE,
                I.TIP_CUAD COD_TIP_CUAD,
                I.NUM_LOTE_PLAN
    FROM CRA_PERIO B, SEG_PERIO_CORPO C, PRE_CATAL G, PRE_NIVEL_OFERT I
   WHERE I.PERD_OID_PERI = B.OID_PERI
     AND B.PERI_OID_PERI = C.OID_PERI
     AND I.OCAT_OID_CATA = G.OID_CATA
     AND C.COD_PERI = psCodigoPeriodo
   ORDER BY I.OID_NIVE_OFER ;  
  
   TYPE concursoTab IS TABLE OF cursorConcurso%ROWTYPE;
   concursoRecord           concursoTab;
   lnOidNivelOferta         PRE_NIVEL_OFERT.OID_NIVE_OFER%TYPE;
   lbEncontroRangos         BOOLEAN;
   lbEncontroRangosGratis   BOOLEAN;
   lbEncontroComponentes    BOOLEAN;
   lnNumeroSecuencia        NUMBER;
   lnRecorrer               NUMBER;
   
   CURSOR cursorRangos(vnOidNivelOferta NUMBER) IS
     SELECT 
         OID_NIVE_OFER_RANG,
         VAL_RANG_INFE,
         VAL_RANG_SUPE,
         VAL_PREC_UNIT
     FROM PRE_NIVEL_OFERT_RANGO
     WHERE NIOF_OID_NIVE_OFER = vnOidNivelOferta
     ORDER BY OID_NIVE_OFER_RANG;
   
   CURSOR cursorRangosGratis(vnOidNivelOfertaGratis NUMBER) IS 
     SELECT A.OID_NIVE_OFER_GRAT,
         A.OID_DETA_OFER,
         B.VAL_CODI_VENT,
         C.COD_SAP,
         D.VAL_I18N           DES_PROD,
         B.NUM_PAGI_CATA,
         E.COD_TIPO_OFER,
         B.PRECIO_UNITARIO,
         A.VAL_UNID
    FROM PRE_NIVEL_OFERT_GRATI A,
         PRE_OFERT_DETAL       B,
         MAE_PRODU             C,
         GEN_I18N_SICC_PAIS    D,
         PRE_TIPO_OFERT        E
   WHERE A.NIOF_OID_NIVE_OFER_RANG = vnOidNivelOfertaGratis
     AND A.OID_DETA_OFER = B.OID_DETA_OFER
     AND B.PROD_OID_PROD = C.OID_PROD
     AND C.OID_PROD = D.VAL_OID
     AND D.ATTR_ENTI = 'MAE_PRODU'
     AND B.TOFE_OID_TIPO_OFER = E.OID_TIPO_OFER
   ORDER BY A.NUM_SECU;
  
   CURSOR cursorComponentes(vnOidNivelOferta NUMBER) IS
     SELECT I.OID_DETA_OFER,
         I.VAL_CODI_VENT,
         K.COD_SAP,
         INT_PKG_RECLA.GEN_FN_DESC_PROD(K.OID_PROD) DES_PROD,
         A.DES_CATA,
         I.NUM_PAGI_CATA,
         V.COD_TIPO_OFER,
         I.IMP_PREC_CATA,
         I.IND_DIGI,
         I.IND_IMPR_GP
      FROM MAE_PRODU             K,
           PRE_OFERT_DETAL       I,
           PRE_CATAL             A,
           PRE_TIPO_OFERT        V,
           PRE_NIVEL_OFERT_PRODU B
     WHERE B.OID_DETA_OFER = I.OID_DETA_OFER
       AND B.NIOF_OID_NIVE_OFER = vnOidNivelOferta
       AND I.OCAT_OID_CATAL = A.OID_CATA
       AND I.PROD_OID_PROD = K.OID_PROD
       AND I.TOFE_OID_TIPO_OFER = V.OID_TIPO_OFER;
   
BEGIN
  EXECUTE IMMEDIATE 'truncate table PRE_REPOR_CONFI_OFERT_CONCU';
  OPEN cursorConcurso;
     LOOP
       FETCH cursorConcurso BULK COLLECT INTO concursoRecord LIMIT W_FILAS;
         IF concursoRecord.COUNT > 0 THEN
           FOR x IN concursoRecord.FIRST .. concursoRecord.LAST LOOP
               lnOidNivelOferta := concursoRecord(x).OID_NIVE_OFER;
               lnNumeroSecuencia := 0;            
               lbEncontroRangos := FALSE;
               lbEncontroComponentes := FALSE;
               
               FOR cRangos IN cursorRangos(lnOidNivelOferta) LOOP
                   lbEncontroRangos := TRUE;
                   lbEncontroRangosGratis := FALSE;    
                   
                   FOR cRangosGratis IN cursorRangosGratis(cRangos.OID_NIVE_OFER_RANG) LOOP
                       lbEncontroRangosGratis := TRUE;    
                       lnNumeroSecuencia := lnNumeroSecuencia + 1;
                       INSERT INTO PRE_REPOR_CONFI_OFERT_CONCU(
                          oid_nive_ofer, 
                          num_secu, 
                          cod_peri, 
                          des_cata, 
                          tip_nive, 
                          tip_cuad, 
                          num_pagi, 
                          oid_nive_ofer_rang, 
                          val_rang_infe, 
                          val_rang_supe, 
                          val_prec_unit, 
                          val_codi_vent, 
                          cod_sap, 
                          des_prod, 
                          cod_tipo_ofer, 
                          val_unid
                          )
                       VALUES (
                          lnOidNivelOferta,
                          lnNumeroSecuencia,
                          psCodigoPeriodo,
                          concursoRecord(x).DES_CATA,
                          concursoRecord(x).tip_nive,
                          concursoRecord(x).tip_cuad,
                          concursoRecord(x).num_pagi,  
                          cRangos.Oid_Nive_Ofer_Rang,
                          cRangos.Val_Rang_Infe,
                          cRangos.Val_Rang_Supe,
                          cRangos.Val_Prec_Unit,
                          cRangosGratis.Val_Codi_Vent,
                          cRangosGratis.Cod_Sap,
                          cRangosGratis.Des_Prod,
                          cRangosGratis.Cod_Tipo_Ofer,
                          cRangosGratis.Val_Unid
                          );
               
                  END LOOP;
                  
                  IF (NOT lbEncontroRangosGratis) THEN 
                      lnNumeroSecuencia := lnNumeroSecuencia + 1;
                      INSERT INTO PRE_REPOR_CONFI_OFERT_CONCU(
                              oid_nive_ofer, 
                              num_secu, 
                              cod_peri, 
                              des_cata, 
                              tip_nive, 
                              tip_cuad, 
                              num_pagi, 
                              oid_nive_ofer_rang, 
                              val_rang_infe, 
                              val_rang_supe, 
                              val_prec_unit
                              )
                           VALUES (
                              lnOidNivelOferta,
                              lnNumeroSecuencia,
                              psCodigoPeriodo,
                              concursoRecord(x).DES_CATA,
                              concursoRecord(x).tip_nive,
                              concursoRecord(x).tip_cuad,
                              concursoRecord(x).num_pagi,  
                              cRangos.Oid_Nive_Ofer_Rang,
                              cRangos.Val_Rang_Infe,
                              cRangos.Val_Rang_Supe,
                              cRangos.Val_Prec_Unit
                              );
                   END IF;
               END LOOP;
               
               lnRecorrer := 0;
               FOR cComponentes IN cursorComponentes(lnOidNivelOferta) LOOP
                   lbEncontroComponentes := TRUE;    
                   lnRecorrer := lnRecorrer + 1;
                   IF (lnRecorrer <= lnNumeroSecuencia) THEN
                      UPDATE PRE_REPOR_CONFI_OFERT_CONCU x
                      SET val_codi_vent_apoy = cComponentes.Val_Codi_Vent, 
                          cod_sap_apoy = cComponentes.Cod_Sap, 
                          des_prod_apoy = cComponentes.Des_Prod, 
                          num_pagi_cata = cComponentes.Num_Pagi_Cata, 
                          cod_tipo_ofer_apoy = cComponentes.Cod_Tipo_Ofer,
                          IMP_PREC_CATA = cComponentes.IMP_PREC_CATA,
                          IND_DIGI = cComponentes.IND_DIGI,
                          IND_IMPR_GP = cComponentes.IND_IMPR_GP
                      WHERE x.OID_NIVE_OFER = lnOidNivelOferta
                        AND x.num_secu = lnRecorrer;
                    ELSE 
                      INSERT INTO PRE_REPOR_CONFI_OFERT_CONCU(
                              oid_nive_ofer, 
                              num_secu, 
                              cod_peri, 
                              des_cata, 
                              tip_nive, 
                              tip_cuad, 
                              num_pagi, 
                              val_codi_vent_apoy, 
                              cod_sap_apoy, 
                              des_prod_apoy, 
                              num_pagi_cata, 
                              cod_tipo_ofer_apoy,
                              IMP_PREC_CATA,
                              IND_DIGI,
                              IND_IMPR_GP)
                           VALUES (
                              lnOidNivelOferta,
                              lnRecorrer,
                              psCodigoPeriodo,
                              concursoRecord(x).DES_CATA,
                              concursoRecord(x).tip_nive,
                              concursoRecord(x).tip_cuad,
                              concursoRecord(x).num_pagi,  
                              cComponentes.Val_Codi_Vent,
                              cComponentes.Cod_Sap,
                              cComponentes.Des_Prod,
                              cComponentes.Num_Pagi_Cata,
                              cComponentes.Cod_Tipo_Ofer,
                              cComponentes.IMP_PREC_CATA,
                              cComponentes.IND_DIGI,
                              cComponentes.IND_IMPR_GP
                              );
                   END IF;
               
               END LOOP;
               lnNumeroSecuencia := lnNumeroSecuencia + lnRecorrer;
               
               IF ((NOT lbEncontroRangos) AND (NOT lbEncontroComponentes)) THEN
                  lnNumeroSecuencia := lnNumeroSecuencia + 1;
                  INSERT INTO PRE_REPOR_CONFI_OFERT_CONCU(
                        oid_nive_ofer, 
                        num_secu, 
                        cod_peri, 
                        des_cata, 
                        tip_nive, 
                        tip_cuad, 
                        num_pagi)
                     VALUES (
                        lnOidNivelOferta,
                        lnNumeroSecuencia,
                        psCodigoPeriodo,
                        concursoRecord(x).DES_CATA,
                        concursoRecord(x).tip_nive,
                        concursoRecord(x).tip_cuad,
                        concursoRecord(x).num_pagi
                        );
             
               END IF;
               
               
           END LOOP;
         END IF;
       EXIT WHEN (cursorConcurso%NOTFOUND);
     END LOOP;
  CLOSE cursorConcurso;
  
 
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR PRE_PR_REPOR_CONFI_OFERT_CONCU: ' || ls_sqlerrm);
END PRE_PR_REPOR_CONFI_OFERT_CONCU;

/***************************************************************************
Descripcion       : Generación de data para Reporte Configuración Ofertas (N)
Fecha Creacion    : 02/11/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRE_PR_REPOR_CONFI_OFERT_N (psCodigoPeriodo VARCHAR2) IS
 CURSOR cursorConcurso IS
   SELECT DISTINCT 
      I.OID_NX_OFER OID_NIVE_OFER,
      C.COD_PERI,
      G.DES_CATA,
      I.NUM_PAGI,
      DECODE(I.TIP_NIVE,
             '1',
             'Niveles de Precios',
             '2',
             'Niveles de Concursos',
             '') TIP_NIVE,
      DECODE(I.TIP_CUAD, '1', 'Unidades', '2', 'Monto', '') TIP_CUAD,
      G.OID_CATA,
      I.TIP_NIVE COD_TIP_NIVE,
      I.TIP_CUAD COD_TIP_CUAD,
      I.NUM_LOTE_PLAN
  FROM CRA_PERIO B, SEG_PERIO_CORPO C, PRE_CATAL G, PRE_NX_OFERT I
 WHERE I.PERD_OID_PERI = B.OID_PERI
   AND B.PERI_OID_PERI = C.OID_PERI
   AND I.OCAT_OID_CATA = G.OID_CATA
   AND C.COD_PERI = psCodigoPeriodo
  ORDER BY I.OID_NX_OFER;
  
   TYPE concursoTab IS TABLE OF cursorConcurso%ROWTYPE;
   concursoRecord           concursoTab;
   lnOidNivelOferta         PRE_NX_OFERT.OID_NX_OFER%TYPE;
   lbEncontroRangos         BOOLEAN;
   lbEncontroRangosGratis   BOOLEAN;
   lbEncontroComponentes    BOOLEAN;
   lnNumeroSecuencia        NUMBER;
   lnRecorrer               NUMBER;
   
   CURSOR cursorRangos(vnOidNivelOferta NUMBER) IS
    SELECT 
         OID_NX_OFER_RANG OID_NIVE_OFER_RANG,
         VAL_FACT_REPE,
         VAL_PREC_UNIT
    FROM PRE_NX_OFERT_RANGO
     WHERE NIOF_OID_NX_OFER = vnOidNivelOferta
     ORDER BY OID_NX_OFER_RANG;
   
   CURSOR cursorRangosGratis(vnOidNivelOfertaGratis NUMBER) IS 
     SELECT A.OID_NX_OFER_GRAT OID_NIVE_OFER_GRAT,
         A.OID_DETA_OFER,
         B.VAL_CODI_VENT,
         C.COD_SAP,
         D.VAL_I18N         DES_PROD,
         B.NUM_PAGI_CATA,
         E.COD_TIPO_OFER,
         B.PRECIO_UNITARIO,
         A.VAL_UNID
    FROM PRE_NX_OFERT_GRATI A,
         PRE_OFERT_DETAL    B,
         MAE_PRODU          C,
         GEN_I18N_SICC_PAIS D,
         PRE_TIPO_OFERT     E
   WHERE A.NIOF_OID_NX_OFER_RANG = vnOidNivelOfertaGratis
     AND A.OID_DETA_OFER = B.OID_DETA_OFER
     AND B.PROD_OID_PROD = C.OID_PROD
     AND C.OID_PROD = D.VAL_OID
     AND D.ATTR_ENTI = 'MAE_PRODU'
     AND B.TOFE_OID_TIPO_OFER = E.OID_TIPO_OFER
   ORDER BY A.NUM_SECU;
   
   CURSOR cursorComponentes(vnOidNivelOferta NUMBER) IS
     SELECT I.OID_DETA_OFER,
       I.VAL_CODI_VENT,
       K.COD_SAP,
       INT_PKG_RECLA.GEN_FN_DESC_PROD(K.OID_PROD) DES_PROD,
       A.DES_CATA,
       I.NUM_PAGI_CATA,
       V.COD_TIPO_OFER,
       I.VAL_FACT_REPE,
       I.IMP_PREC_CATA,
       I.IND_DIGI,
       I.IND_IMPR_GP
      FROM MAE_PRODU          K,
           PRE_OFERT_DETAL    I,
           PRE_CATAL          A,
           PRE_TIPO_OFERT     V,
           PRE_NX_OFERT_PRODU B
     WHERE B.OID_DETA_OFER = I.OID_DETA_OFER
       AND B.NIOF_OID_NX_OFER = vnOidNivelOferta
       AND I.OCAT_OID_CATAL = A.OID_CATA
       AND I.PROD_OID_PROD = K.OID_PROD
       AND I.TOFE_OID_TIPO_OFER = V.OID_TIPO_OFER;
       
    
   
BEGIN
  EXECUTE IMMEDIATE 'truncate table PRE_REPOR_CONFI_OFERT_N';
  OPEN cursorConcurso;
     LOOP
       FETCH cursorConcurso BULK COLLECT INTO concursoRecord LIMIT W_FILAS;
         IF concursoRecord.COUNT > 0 THEN
           FOR x IN concursoRecord.FIRST .. concursoRecord.LAST LOOP
               lnOidNivelOferta := concursoRecord(x).OID_NIVE_OFER;
               lnNumeroSecuencia := 0;            
               lbEncontroRangos := FALSE;
               lbEncontroComponentes := FALSE;
               
               FOR cRangos IN cursorRangos(lnOidNivelOferta) LOOP
                   lbEncontroRangos := TRUE;
                   lbEncontroRangosGratis := FALSE;    
                   
                   FOR cRangosGratis IN cursorRangosGratis(cRangos.OID_NIVE_OFER_RANG) LOOP
                       lbEncontroRangosGratis := TRUE;    
                       lnNumeroSecuencia := lnNumeroSecuencia + 1;
                       INSERT INTO PRE_REPOR_CONFI_OFERT_N(
                          oid_nive_ofer, 
                          num_secu, 
                          cod_peri, 
                          des_cata, 
                          tip_nive, 
                          tip_cuad, 
                          num_pagi, 
                          oid_nive_ofer_rang, 
                          val_fact_repe, 
                          val_prec_unit, 
                          val_codi_vent, 
                          cod_sap, 
                          des_prod, 
                          cod_tipo_ofer, 
                          val_unid,
                          val_prec_matr
                          )
                       VALUES (
                          lnOidNivelOferta,
                          lnNumeroSecuencia,
                          psCodigoPeriodo,
                          concursoRecord(x).DES_CATA,
                          concursoRecord(x).tip_nive,
                          concursoRecord(x).tip_cuad,
                          concursoRecord(x).num_pagi,  
                          cRangos.Oid_Nive_Ofer_Rang,
                          cRangos.Val_Fact_Repe,
                          cRangos.Val_Prec_Unit,
                          cRangosGratis.Val_Codi_Vent,
                          cRangosGratis.Cod_Sap,
                          cRangosGratis.Des_Prod,
                          cRangosGratis.Cod_Tipo_Ofer,
                          cRangosGratis.Val_Unid,
                          cRangosGratis.Precio_Unitario
                          );
               
                  END LOOP;
                  
                  IF (NOT lbEncontroRangosGratis) THEN 
                      lnNumeroSecuencia := lnNumeroSecuencia + 1;
                      INSERT INTO PRE_REPOR_CONFI_OFERT_N(
                              oid_nive_ofer, 
                              num_secu, 
                              cod_peri, 
                              des_cata, 
                              tip_nive, 
                              tip_cuad, 
                              num_pagi, 
                              oid_nive_ofer_rang, 
                              val_fact_repe, 
                              val_prec_unit
                              )
                           VALUES (
                              lnOidNivelOferta,
                              lnNumeroSecuencia,
                              psCodigoPeriodo,
                              concursoRecord(x).DES_CATA,
                              concursoRecord(x).tip_nive,
                              concursoRecord(x).tip_cuad,
                              concursoRecord(x).num_pagi,  
                              cRangos.Oid_Nive_Ofer_Rang,
                              cRangos.Val_Fact_Repe,
                              cRangos.Val_Prec_Unit
                              );
                   END IF;
               END LOOP;
               
               lnRecorrer := 0;
               FOR cComponentes IN cursorComponentes(lnOidNivelOferta) LOOP
                   lbEncontroComponentes := TRUE;    
                   lnRecorrer := lnRecorrer + 1;
                   IF (lnRecorrer <= lnNumeroSecuencia) THEN
                      UPDATE PRE_REPOR_CONFI_OFERT_N x
                      SET val_codi_vent_apoy = cComponentes.Val_Codi_Vent, 
                          cod_sap_apoy = cComponentes.Cod_Sap, 
                          des_prod_apoy = cComponentes.Des_Prod, 
                          num_pagi_cata = cComponentes.Num_Pagi_Cata, 
                          cod_tipo_ofer_apoy = cComponentes.Cod_Tipo_Ofer,
                          val_fact_repe_apoy = cComponentes.Val_Fact_Repe,
                          IMP_PREC_CATA = cComponentes.IMP_PREC_CATA,
                          IND_DIGI = cComponentes.IND_DIGI,
                          IND_IMPR_GP = cComponentes.IND_IMPR_GP 
                      WHERE x.OID_NIVE_OFER = lnOidNivelOferta
                        AND x.num_secu = lnRecorrer;
                    ELSE 
                      INSERT INTO PRE_REPOR_CONFI_OFERT_N(
                              oid_nive_ofer, 
                              num_secu, 
                              cod_peri, 
                              des_cata, 
                              tip_nive, 
                              tip_cuad, 
                              num_pagi, 
                              val_codi_vent_apoy, 
                              cod_sap_apoy, 
                              des_prod_apoy, 
                              num_pagi_cata, 
                              cod_tipo_ofer_apoy,
                              val_fact_repe_apoy,
                              IMP_PREC_CATA,
                              IND_DIGI,
                              IND_IMPR_GP)
                           VALUES (
                              lnOidNivelOferta,
                              lnRecorrer,
                              psCodigoPeriodo,
                              concursoRecord(x).DES_CATA,
                              concursoRecord(x).tip_nive,
                              concursoRecord(x).tip_cuad,
                              concursoRecord(x).num_pagi,  
                              cComponentes.Val_Codi_Vent,
                              cComponentes.Cod_Sap,
                              cComponentes.Des_Prod,
                              cComponentes.Num_Pagi_Cata,
                              cComponentes.Cod_Tipo_Ofer,
                              cComponentes.Val_Fact_Repe,
                              cComponentes.IMP_PREC_CATA,
                              cComponentes.IND_DIGI,
                              cComponentes.IND_IMPR_GP
                              );
                   END IF;
               
               END LOOP;
               lnNumeroSecuencia := lnNumeroSecuencia + lnRecorrer;
               
               IF ((NOT lbEncontroRangos) AND (NOT lbEncontroComponentes)) THEN
                  lnNumeroSecuencia := lnNumeroSecuencia + 1;
                  INSERT INTO PRE_REPOR_CONFI_OFERT_N(
                        oid_nive_ofer, 
                        num_secu, 
                        cod_peri, 
                        des_cata, 
                        tip_nive, 
                        tip_cuad, 
                        num_pagi)
                     VALUES (
                        lnOidNivelOferta,
                        lnNumeroSecuencia,
                        psCodigoPeriodo,
                        concursoRecord(x).DES_CATA,
                        concursoRecord(x).tip_nive,
                        concursoRecord(x).tip_cuad,
                        concursoRecord(x).num_pagi
                        );
             
               END IF;
               
               
           END LOOP;
         END IF;
       EXIT WHEN (cursorConcurso%NOTFOUND);
     END LOOP;
  CLOSE cursorConcurso;
  
 
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR PRE_PR_REPOR_CONFI_OFERT_N: ' || ls_sqlerrm);
END PRE_PR_REPOR_CONFI_OFERT_N;

/**************************************************************************
Descripcion       : Realiza el proceso de Registro Automatico de Matriz de 
                    Precios
Fecha Creacion    : 04/01/2016
Parametros Entrada:
  pnOidPeriodo     :  Oid Periodo
  pnOidCatalogo    :  Oid Catalogo
  pnOidCabecera    :  Oid Cabecera Matriz
  psCodigoUsuario  :  Codigo Usuario
  
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE PRE_PR_REGIS_AUTOM(pnOidPeriodo    NUMBER,
                             pnOidCatalogo   NUMBER,
                             pnOidCabecera   NUMBER,
                             psCodigoUsuario VARCHAR2) IS
                             
  CURSOR c_pedidos IS
    SELECT CRA_PERIO.PAIS_OID_PAIS,  
           CRA_PERIO.MARC_OID_MARC, 
           CRA_PERIO.CANA_OID_CANA,  
           SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI, 
           PRE_ESTIM_VENTA.PERD_OID_PERI, 
           MAE_PRODU.OID_PROD, 
           MAE_PRODU.DES_CORT, 
           PRE_ESTIM_VENTA.TOFE_OID_TIPO_OFER, 
           PRE_ESTIM_VENTA.CIVI_OID_CICL_VIDA, 
           PRE_ESTIM_VENTA.NUM_UNID_ESTI, 
           PRE_ESTIM_VENTA.OCAT_OID_CATA, 
           PRE_ESTIM_VENTA.NUM_PAGI_CATA, 
           PRE_ESTIM_VENTA.VAL_POSI_PAGI, 
           PRE_ESTIM_VENTA.IMP_PREC_CATA, 
           PRE_ESTIM_VENTA.ARGV_OID_ARGU_VENT, 
           PRE_ESTIM_VENTA.IMP_PREC_POSI, 
           PRE_ESTIM_VENTA.CNDP_OID_COND_PROM, 
           PRE_ESTIM_VENTA.IMP_VENT_NETA_ESTI, 
           PRE_ESTIM_VENTA.MONE_OID_MONE, 
           PRE_ESTIM_VENTA.VAL_COST_ESTA, 
           PRE_ESTIM_VENTA.FOPA_OID_FORM_PAGO, 
           PRE_ESTIM_VENTA.VAL_CENT, 
           PRE_ESTIM_VENTA.OID_ESTIM_VENT, 
           PRE_ESTIM_VENTA.ACCE_OID_ACCE, 
           PRE_ESTIM_VENTA.SBAC_OID_SBAC, 
           PRE_ESTIM_VENTA.PRFI_OID_PROG_FIDE, 
           PRE_ESTIM_VENTA.VARI_OID_VARI 
      FROM PRE_ESTIM_VENTA, MAE_PRODU, CRA_PERIO, SEG_PERIO_CORPO
     WHERE PRE_ESTIM_VENTA.PROD_OID_PROD = MAE_PRODU.OID_PROD
       AND (PRE_ESTIM_VENTA.IND_OFER_GENE = 0 or
           pre_estim_venta.ind_ofer_gene is null)
       AND PRE_ESTIM_VENTA.PERD_OID_PERI = CRA_PERIO.OID_PERI
       AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
       AND PRE_ESTIM_VENTA.PERD_OID_PERI IN
           (SELECT CRA_PERIO.OID_PERI
              FROM CRA_PERIO, PRE_MATRI_FACTU_CABEC
             WHERE PRE_MATRI_FACTU_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI
               AND PRE_MATRI_FACTU_CABEC.OID_CABE = pnOidCabecera)
       AND PRE_ESTIM_VENTA.ARGV_OID_ARGU_VENT IN
           (SELECT PRE_ARGUM_VENTA.OID_ARGU_VENT
              FROM PRE_ARGUM_VENTA, PRE_TIPO_ESTRA
             WHERE PRE_ARGUM_VENTA.TIES_OID_TIPO_ESTR =
                   PRE_TIPO_ESTRA.OID_TIPO_ESTR
               AND PRE_TIPO_ESTRA.COD_TIPO_ESTR = 1)
       AND (pnOidCatalogo IS NULL OR (PRE_ESTIM_VENTA.OCAT_OID_CATA = pnOidCatalogo))
     ORDER BY PRE_ESTIM_VENTA.OCAT_OID_CATA,
              PRE_ESTIM_VENTA.NUM_PAGI_CATA,
              PRE_ESTIM_VENTA.VAL_POSI_PAGI,
              MAE_PRODU.DES_CORT;
  
  --se define un tipo de dato tipo Tabla de Registros de los pedidos
  TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  pedidoReg RegTab;
  
  lnMonto            NUMBER;
  lnMonto_cata_aten  NUMBER;
  lnMonto_forma_cata NUMBER;
  lnOcurrencias      NUMBER;
  lnTotUnids         NUMBER;
  
  lnOidOferta        PRE_OFERT.OID_OFER%TYPE;
  lnNumOferta        PRE_OFERT.NUM_OFER%TYPE;
  lnOidEstrategia    PRE_ESTRA.OID_ESTR%TYPE;
  lnFactorRepe       PRE_ARGUM_VENTA.VAL_FACT_REPE%TYPE;

BEGIN
  
  --Obtenemos el Oid de la primera Estrategia Individual
  SELECT OID_ESTR
    INTO lnOidEstrategia
    FROM (
          select ES.OID_ESTR
            from PRE_ESTRA ES
           where ES.TIES_OID_TIPO_ESTR = 1
           ORDER BY ES.OID_ESTR)
    WHERE ROWNUM = 1;       
            
  OPEN c_pedidos;
  LOOP
    FETCH c_pedidos BULK COLLECT
      INTO pedidoReg LIMIT W_FILAS;
    
    IF pedidoReg.COUNT > 0 THEN
      FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
        
        --CREAR ENTIDAD OFERTA
        lnOidOferta := PRE_OFER_SEQ.NEXTVAL;
        --lnNumOferta := obtenerNumOfertaSecuencial(oidEstrategia, lnOidCabecera);
        
        --Obtenemos el Numero de Oferta a Crear
        SELECT NVL(MAX(NUM_OFER), 0) 
          INTO lnNumOferta
          FROM PRE_OFERT po 
         WHERE po.COES_OID_ESTR = lnOidEstrategia
           AND po.MFCA_OID_CABE = pnOidCabecera;
        
        lnNumOferta := lnNumOferta + 1;
        
        INSERT INTO PRE_OFERT
          (OID_OFER,
           COES_OID_ESTR,
           NUM_OFER,
           ARGV_OID_ARGU_VENT,
           MFCA_OID_CABE,
           OCAT_OID_CATA,
           FOPA_OID_FORM_PAGO)
        VALUES 
          (lnOidOferta,
           lnOidEstrategia,
           lnNumOferta,
           pedidoReg(x).ARGV_OID_ARGU_VENT,
           pnOidCabecera,
           pedidoReg(x).OCAT_OID_CATA,
           pedidoReg(x).FOPA_OID_FORM_PAGO);
        
        --CREAR ENTIDAD DETALLE OFERTA
        SELECT VAL_FACT_REPE
          INTO lnFactorRepe
          FROM PRE_ARGUM_VENTA
         WHERE OID_ARGU_VENT = pedidoReg(x).ARGV_OID_ARGU_VENT;
         
        INSERT INTO PRE_OFERT_DETAL
          (OID_DETA_OFER,
           OFER_OID_OFER,
           PROD_OID_PROD,
           NUM_LINE_OFER,
           VAL_FACT_REPE,
           TOFE_OID_TIPO_OFER,
           CIVI_OID_CICLO_VIDA,
           GOFE_OID_GRUP_OFER,
           OCAT_OID_CATAL,
           CNDP_OID_COND_PROM,
           --PROD_OID_PROD,
           VAL_TEXT_BREV,
           NUM_UNID_ESTI,
           NUM_POSI_RANK,
           IND_PROD_PRIN,
           IND_IMPR_GP,
           IND_DIGI,
           COD_ORIG,
           IMP_PREC_CATA,
           IMP_PREC_POSI,
           IMP_COST_ESTA,
           IMP_VENT_NETA_ESTI,
           NUM_PAGI_CATA,
           VAL_POSI_PAGI,
           VAL_CENT,
           PRFI_OID_PROG_FIDE,
           VARI_OID_VARI)
        VALUES 
          (PRE_OFDE_SEQ.NEXTVAL,
           lnOidOferta,
           (SELECT OID_PROD FROM MAE_PRODU WHERE PAIS_OID_PAIS = pedidoReg(x).PAIS_OID_PAIS
                                             AND OID_PROD = pedidoReg(x).OID_PROD),
           1,
           lnFactorRepe,
           pedidoReg(x).TOFE_OID_TIPO_OFER,
           pedidoReg(x).CIVI_OID_CICL_VIDA,
           NULL,                         
           pedidoReg(x).OCAT_OID_CATA,
           pedidoReg(x).CNDP_OID_COND_PROM,
           --pedidoReg(x).OID_PROD,
           pedidoReg(x).DES_CORT,
           pedidoReg(x).NUM_UNID_ESTI,
           NULL,
           1,
           1,
           1,
           'Es',
           pedidoReg(x).IMP_PREC_CATA,
           pedidoReg(x).IMP_PREC_POSI,
           pedidoReg(x).VAL_COST_ESTA,
           pedidoReg(x).IMP_VENT_NETA_ESTI,
           pedidoReg(x).NUM_PAGI_CATA,
           pedidoReg(x).VAL_POSI_PAGI,
           pedidoReg(x).VAL_CENT,
           pedidoReg(x).PRFI_OID_PROG_FIDE,
           pedidoReg(x).VARI_OID_VARI);
        
        --ACTUALIZAR ENTIDAD ESTIMADO VENTA
        UPDATE PRE_ESTIM_VENTA
           SET IND_OFER_GENE = 1
         WHERE OID_ESTIM_VENT = pedidoReg(x).OID_ESTIM_VENT;
                
      END LOOP;
    END IF;
    
    EXIT WHEN c_pedidos%NOTFOUND;
  END LOOP;
  CLOSE c_pedidos;
  
  ped_pkg_cuadr_ofert.ped_pr_asignar_cuv(pnOidPeriodo, pnOidCatalogo);
  
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR PRE_PR_REGIS_AUTOM: (' ||
                            ln_sqlcode || ')' || ls_sqlerrm);
END PRE_PR_REGIS_AUTOM;

/**************************************************************************
Descripcion       : Realiza el proceso de Renombrar Matriz de Facturación 
Fecha Creacion    : 13/01/2016
Parámetros Entrada:
  psCodigoPeriodoOrigen   :  Codigo Periodo Origen
  psCodigoPeriodoDestino  :  Codigo Periodo Destino
  psCodigoUsuario         :  Codigo Usuario
  
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_RENOM_MATRI_FACTU(psCodigoPeriodoOrigen VARCHAR2,
                                   psCodigoPeriodoDestino VARCHAR2,
                                   psCodigoUsuario VARCHAR2) IS
  
  lnOidPeriodoOrigen            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidPeriodoDestino           SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidCabecMatrizFactOrigen    PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
  lnOidCabecMatrizFactDestino   PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
  
BEGIN
  
    --Obtenemos el Oid del Periodo de Origen y Destino
    lnOidPeriodoOrigen := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodoOrigen);
    lnOidPeriodoDestino := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodoDestino);

    --Obtenemos el Oid de la Cabecera de la Matriz Facturación de ORIGEN 
    BEGIN
        SELECT OID_CABE
          INTO lnOidCabecMatrizFactOrigen
          FROM PRE_MATRI_FACTU_CABEC
         WHERE PERD_OID_PERI = lnOidPeriodoOrigen;
    EXCEPTION 
        WHEN NO_DATA_FOUND THEN
            lnOidCabecMatrizFactOrigen := '';
    END;
      
    --Obtenemos el Oid de la Cabecera de la Matriz Facturación de DESTINO 
    SELECT OID_CABE
      INTO lnOidCabecMatrizFactDestino
      FROM PRE_MATRI_FACTU_CABEC
     WHERE PERD_OID_PERI = lnOidPeriodoDestino;
       
    UPDATE PRE_MATRI_FACTU
       SET MFCA_OID_CABE = lnOidCabecMatrizFactDestino
     WHERE MFCA_OID_CABE = lnOidCabecMatrizFactOrigen;
     
    --Actualiza la Matriz de Origen por la de Destino
    UPDATE PRE_OFERT
       SET MFCA_OID_CABE = lnOidCabecMatrizFactDestino
     WHERE MFCA_OID_CABE = lnOidCabecMatrizFactOrigen;
     
    UPDATE PRE_NIVEL_OFERT
       SET PERD_OID_PERI = lnOidPeriodoDestino
     WHERE PERD_OID_PERI = lnOidPeriodoOrigen;

    UPDATE PRE_NX_OFERT
       SET PERD_OID_PERI = lnOidPeriodoDestino
     WHERE PERD_OID_PERI = lnOidPeriodoOrigen;
    
    --Se inserta la auditoria correspondiente
    INSERT INTO PRE_AUDIT_RENOM_MATRI_FACTU 
    VALUES (psCodigoPeriodoOrigen, psCodigoPeriodoDestino, psCodigoUsuario, SYSDATE);
  
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR PRE_PR_RENOM_MATRI_FACTU: (' || ln_sqlcode || ')' || ls_sqlerrm);
    
END PRE_PR_RENOM_MATRI_FACTU;

/**************************************************************************
Descripcion       : Realiza el proceso de Eliminar Matriz de Facturación 
Fecha Creacion    : 14/01/2016
Parámetros Entrada:
  psCodigoPeriodo   :  Codigo Periodo
  psCodigoUsuario   :  Codigo Usuario
  psCodigoCatalago   :  Codigo Catálogo
  
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE PRE_PR_ELIMI_MATRI_FACTU(psCodigoPeriodo  VARCHAR2,
                                   psCodigoUsuario  VARCHAR2,
                                   psCodigoCatalago VARCHAR2) IS
  
  lnOidPeriodo            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidCabecMatrizFact    PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
  
  lnOidCatalogo           PRE_OFERT.OCAT_OID_CATA%TYPE;
  
  CURSOR c_obtieneOidPreOfer (p_oidPeriodo VARCHAR2, p_oidCatalogo PRE_OFERT.OCAT_OID_CATA%TYPE) IS
    SELECT X.OID_OFER
      FROM PRE_OFERT X, PRE_MATRI_FACTU_CABEC Y
     WHERE Y.OID_CABE = X.MFCA_OID_CABE
       AND Y.PERD_OID_PERI = p_oidPeriodo
       AND (p_oidCatalogo IS NULL OR X.OCAT_OID_CATA = p_oidCatalogo);
       
  TYPE obtieneOidPreOfer IS RECORD (
    oidOfer PRE_OFERT.OID_OFER%TYPE
  );

  TYPE obtieneOidPreOferTab IS TABLE OF obtieneOidPreOfer;
  obtieneOidPreOferRecordN obtieneOidPreOferTab;
  
  CURSOR c_obtieneOidPreOferDetal(oidOfer PRE_OFERT.OID_OFER%TYPE) IS
    SELECT OID_DETA_OFER 
      FROM PRE_OFERT_DETAL
     WHERE OFER_OID_OFER = oidOfer
       AND COD_ORIG <> 'MAV';
     
  CURSOR c_obtieneOidPrePromo(oidOfer PRE_OFERT.OID_OFER%TYPE) IS
    SELECT OID_PROM 
      FROM PRE_PROMO
     WHERE OFER_OID_OFER = oidOfer;
     
  CURSOR c_obtieneOidNiveOfer(oidPeriodo SEG_PERIO_CORPO.OID_PERI%TYPE, oidCatalogo PRE_OFERT.OCAT_OID_CATA%TYPE)IS
    SELECT OID_NIVE_OFER
      FROM PRE_NIVEL_OFERT
     WHERE PERD_OID_PERI = oidPeriodo
       AND (oidCatalogo IS NULL OR OCAT_OID_CATA = oidCatalogo);
     
  CURSOR c_obtieneOidNiveOferRan(oidNiveOfer PRE_NIVEL_OFERT.OID_NIVE_OFER%TYPE)IS
    SELECT OID_NIVE_OFER_RANG
      FROM PRE_NIVEL_OFERT_RANGO
     WHERE NIOF_OID_NIVE_OFER = oidNiveOfer;
     
  CURSOR c_obtieneOidNxOfer(oidPeriodo SEG_PERIO_CORPO.OID_PERI%TYPE, oidCatalogo PRE_OFERT.OCAT_OID_CATA%TYPE)IS
    SELECT OID_NX_OFER
      FROM PRE_NX_OFERT
     WHERE PERD_OID_PERI = oidPeriodo
       AND (oidCatalogo IS NULL OR OCAT_OID_CATA = oidCatalogo);

  CURSOR c_obtieneOidNxOferRang(oidNxOfer PRE_NX_OFERT.OID_NX_OFER%TYPE)IS
    SELECT OID_NX_OFER_RANG 
      FROM PRE_NX_OFERT_RANGO
     WHERE NIOF_OID_NX_OFER = oidNxOfer;

BEGIN
  
    --Obtenemos el Oid del Periodo
    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    
    BEGIN
        SELECT OID_CATA
          INTO lnOidCatalogo
          FROM PRE_CATAL
         WHERE COD_CATA = psCodigoCatalago;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            lnOidCatalogo := '';
    END;   
    
    OPEN c_obtieneOidPreOfer (lnOidPeriodo, lnOidCatalogo);
    LOOP
    FETCH c_obtieneOidPreOfer BULK COLLECT INTO obtieneOidPreOferRecordN LIMIT W_FILAS;
        IF obtieneOidPreOferRecordN.COUNT > 0 THEN
            FOR x IN obtieneOidPreOferRecordN.FIRST .. obtieneOidPreOferRecordN.LAST LOOP    
                FOR cursor_obtieneOidPreOferDetal IN c_obtieneOidPreOferDetal(obtieneOidPreOferRecordN(x).oidOfer) LOOP
                    DELETE PRE_MATRI_FACTU
                     WHERE OFDE_OID_DETA_OFER = cursor_obtieneOidPreOferDetal.OID_DETA_OFER;
                END LOOP;
                
                FOR cursor_obtieneOidPrePromo IN c_obtieneOidPrePromo(obtieneOidPreOferRecordN(x).oidOfer) LOOP
                    DELETE PRE_RANGO_PROMO
                     WHERE POMO_OID_PROM = cursor_obtieneOidPrePromo.OID_PROM;
                END LOOP;
                
                DELETE PRE_PROMO
                 WHERE OFER_OID_OFER = obtieneOidPreOferRecordN(x).oidOfer;
            END LOOP;
        END IF;
        
        EXIT WHEN c_obtieneOidPreOfer%NOTFOUND;
    END LOOP;
    CLOSE c_obtieneOidPreOfer;
    
    FOR cursor_obtieneOidNiveOfer IN c_obtieneOidNiveOfer(lnOidPeriodo, lnOidCatalogo) LOOP
        DELETE PRE_NIVEL_OFERT_PRODU
         WHERE NIOF_OID_NIVE_OFER = cursor_obtieneOidNiveOfer.OID_NIVE_OFER;
         
        FOR cursor_obtieneOidNiveOferRan IN c_obtieneOidNiveOferRan(cursor_obtieneOidNiveOfer.OID_NIVE_OFER) LOOP
            DELETE PRE_NIVEL_OFERT_GRATI
             WHERE NIOF_OID_NIVE_OFER_RANG = cursor_obtieneOidNiveOferRan.OID_NIVE_OFER_RANG;
        END LOOP;

        DELETE PRE_NIVEL_OFERT_RANGO
         WHERE NIOF_OID_NIVE_OFER = cursor_obtieneOidNiveOfer.OID_NIVE_OFER;
    END LOOP;
    
    FOR cursor_obtieneOidNxOfer IN c_obtieneOidNxOfer(lnOidPeriodo, lnOidCatalogo) LOOP
        DELETE PRE_NX_OFERT_PRODU
         WHERE NIOF_OID_NX_OFER = cursor_obtieneOidNxOfer.OID_NX_OFER;

        FOR cursor_obtieneOidNxOferRang IN c_obtieneOidNxOferRang(cursor_obtieneOidNxOfer.OID_NX_OFER) LOOP
            DELETE PRE_NX_OFERT_GRATI
             WHERE NIOF_OID_NX_OFER_RANG = cursor_obtieneOidNxOferRang.OID_NX_OFER_RANG;
        END LOOP;

        DELETE PRE_NX_OFERT_RANGO
         WHERE NIOF_OID_NX_OFER = cursor_obtieneOidNxOfer.OID_NX_OFER;
    END LOOP;
    
    OPEN c_obtieneOidPreOfer(lnOidPeriodo, lnOidCatalogo);
    LOOP
    FETCH c_obtieneOidPreOfer BULK COLLECT INTO obtieneOidPreOferRecordN LIMIT W_FILAS;
        IF obtieneOidPreOferRecordN.COUNT > 0 THEN
            FOR x IN obtieneOidPreOferRecordN.FIRST .. obtieneOidPreOferRecordN.LAST LOOP
                DELETE PRE_OFERT_DETAL
                 WHERE OFER_OID_OFER = obtieneOidPreOferRecordN(x).oidOfer
                   AND COD_ORIG <> 'MAV';

                DELETE PRE_GRUPO_OFERT
                 WHERE OFER_OID_OFER = obtieneOidPreOferRecordN(x).oidOfer;

                DELETE PRE_OFERT
                 WHERE OID_OFER = obtieneOidPreOferRecordN(x).oidOfer;
            END LOOP;
        END IF;
        
        EXIT WHEN c_obtieneOidPreOfer%NOTFOUND;
    END LOOP;
    CLOSE c_obtieneOidPreOfer;
    
    FOR cursor_obtieneOidNiveOfer IN c_obtieneOidNiveOfer(lnOidPeriodo, lnOidCatalogo) LOOP
        DELETE PRE_NIVEL_OFERT
         WHERE OID_NIVE_OFER = cursor_obtieneOidNiveOfer.OID_NIVE_OFER;
    END LOOP;

    FOR cursor_obtieneOidNxOfer IN c_obtieneOidNxOfer(lnOidPeriodo, lnOidCatalogo) LOOP
        DELETE PRE_NX_OFERT
         WHERE OID_NX_OFER = cursor_obtieneOidNxOfer.OID_NX_OFER;
    END LOOP;
  
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR PRE_PR_ELIMI_MATRI_FACTU: (' || ln_sqlcode || ')' || ls_sqlerrm);
    
END PRE_PR_ELIMI_MATRI_FACTU;
  
END pre_pkg_proce;
/
