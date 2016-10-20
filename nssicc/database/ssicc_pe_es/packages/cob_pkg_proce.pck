CREATE OR REPLACE PACKAGE COB_PKG_PROCE IS

   /**************************************************************************
  Descripcion       : Proceso Principal de la Asignacion de la Cartera
                           este proceso inserta en la tabla COB_ASIGN_CARTE la
                           cartera respectiva a los parametros ingresados.
                          Se realiza lo siguiente :
                              - Selecciona la cartera en base a los parametros ingresados
                              - Asigna la cartera a los usuarios cobradores
                              - Realiza el balanceo de la cartera

  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Etapa de Deuda,
            Codigo Periodo
            Codigo Region,
            Codigo Zona ,
            Fecha,
            Usuario
  ***************************************************************************/
      PROCEDURE COB_PR_ASIGN_CARTE(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_fec_asign VARCHAR2,
      p_cod_usua cob_detal_asign_carte.usu_crea%TYPE);

   /**************************************************************************
      Descripcion       : Proceso Actualizacion de Cartera
                          Este proceso actualiza los importes cancelados y pendientes de
                          los cargos de las cartera que no halla cerrado su gestion.
     Fecha Creacion    : 22/02/2008
     Parametros Entrada :
               Codigo Pais
               Codigo Sociedad
               Codigo Usuario

     22/04/2010  : Se incluye actualizacion de nueva tabla  COB_DETAL_MOVIM_CARTE
   **************************************************************************************/
   PROCEDURE COB_PR_ACTUA_CARTE(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_usua cob_detal_asign_carte.usu_crea%type);

   /**************************************************************************
      Descripcion       : Proceso Generacion de Cronograma
     Fecha Creacion    : 22/02/2008
     Parametros Entrada :
               Codigo Pais
               Codigo Sociedad
               Codigo Periodo
               Codigo Usuario
   ***************************************************************************/
   PROCEDURE COB_PR_GENER_CRONO_CARTE(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_peri  seg_perio_corpo.cod_peri%TYPE,
      p_cod_usu   cob_usuar_cobra_pais.cod_usua_cobr%TYPE );

   /**************************************************************************
      Descripcion       : Proceso que valida y registra fechas de excepcion
                        para la Generacion de Cronograma
     Fecha Creacion    : 22/02/2008
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
   PROCEDURE COB_PR_VALID_REGIS_FECHA_EXCEP(
      p_cod_pais                   seg_pais.cod_pais%TYPE,
      p_cod_socie                 seg_socie.cod_soci%TYPE,
      p_cod_peri                   seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud        cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                   zon_regio.cod_regi%type,
      p_cod_zona                  zon_zona.cod_zona%type,
      p_cod_usuario              cob_detal_asign_carte.usu_crea%TYPE,
      p_tip_exce                    cob_crono_carte_excep.tip_exce%type,
      p_str_fec_exce                VARCHAR2);

 PROCEDURE COB_PR_INSER_GESTI_CARTE(
  p_cod_cart                       IN   cob_gesti_cobra_pais.cod_cart%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda.cod_etap_deud%TYPE,
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                       IN   cob_gesti_cobra_pais.cod_clie%TYPE,
  p_tip_acci                       IN   cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
  p_cod_acc                        IN   cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
  p_val_obse                       IN   cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
  p_fecha                          IN   VARCHAR2 DEFAULT NULL,
  p_imp_pago                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_periodo                    IN   VARCHAR2);

    /**************************************************************************
      Descripcion       : Proceso de Insercion de Gestiones de Cobranza
     Fecha Creacion    : 22/02/2008
     Parametros Entrada :
               Codigo Cartera
               Codigo Cliente
               Tipo de Accion
               Codigo de Accion
               Observaciones
               Fecha
               Importe pago
   ***************************************************************************/
   PROCEDURE COB_PR_INSER_GESTI_COBRA(
      p_cod_cart                     cob_gesti_cobra_pais.cod_cart%TYPE,
      p_cod_clie                      cob_gesti_cobra_pais.cod_clie%TYPE,
      p_tip_acci                      cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
      p_cod_acc                      cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
      p_val_obse                      cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
      p_fecha                          VARCHAR2 DEFAULT NULL,
      p_imp_pago                     VARCHAR2 DEFAULT NULL);

 /**************************************************************************
  Descripcion       : Proceso de Insercion de Gestiones de Cartera
  Fecha Creacion    : 01/06/2009
  Parametros Entrada :
   Codigo Cartera
   Codigo Cliente
   Tipo de Accion
   Codigo de Accion
   Observaciones
   Fecha
   Importe pago
 ***************************************************************************/

 PROCEDURE COB_PR_INSER_GESTI_EJECU(
  p_cod_cart                       IN   cob_gesti_cobra_pais.cod_cart%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda.cod_etap_deud%TYPE,
  p_cod_peri                       IN   cob_detal_asign_carte.cod_peri%TYPE,
  p_cod_regi_clie                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_zona_clie                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                       IN   cob_gesti_cobra_pais.cod_clie%TYPE,
  p_tip_acci                       IN   cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
  p_cod_acc                        IN   cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
  p_val_obse                       IN   cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
  p_fecha                          IN   VARCHAR2 DEFAULT NULL,
  p_imp_pago                       IN   VARCHAR2 DEFAULT NULL);

 /**************************************************************************
  Descripcion       : Proceso de Actualizacion de Telefonos
  Fecha Creacion    : 01/06/2009
  Parametros Entrada :
   Codigo Cartera
   Codigo Etapa
   Codigo Cobrador
   Codigo Cliente
 ***************************************************************************/
 PROCEDURE COB_PR_ACTUA_TELEF_DEUDO(
      p_cod_cart                     cob_gesti_cobra_pais.cod_cart%TYPE,
      p_cod_etap_deud          cob_etapa_deuda.cod_etap_deud%TYPE,
      p_cod_clie                      cob_gesti_cobra_pais.cod_clie%TYPE,
      p_cod_tipo_tele             mae_tipo_comun.cod_tipo_comu%TYPE,
      p_num_tele                     cob_detal_asign_carte.num_tele_fijo%TYPE);

    /**************************************************************************
      Descripcion       : Proceso de Rebaja de Cartera Deudora
     Fecha Creacion    : 01/06/2009
     Parametros Entrada :
               Codigo Cartera
               Codigo Etapa
               Codigo Cobrador
               Codigo Cliente
   ***************************************************************************/
    PROCEDURE COB_PR_REBAJ_TEMPO_CARTE_DEUDO(
      p_cod_cart                cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_etap_deud      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usua_cobr      cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_clie                 mae_clien.cod_clie%TYPE);

    /**************************************************************************
      Descripcion       : Proceso de Rebaja de Cartera Deudora
     Fecha Creacion    : 01/06/2009
     Parametros Entrada :
               Codigo Cartera
               Codigo Etapa
               Codigo Cobrador
               Codigo Cliente
   ***************************************************************************/
    PROCEDURE COB_PR_REBAJ_DEFIN_CARTE_DEUDO(
      p_cod_cart                cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_etap_deud      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usua_cobr      cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_clie                 mae_clien.cod_clie%TYPE);

 PROCEDURE COB_PR_ASIGN_CARTE_AUTOM(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_modu                       IN   fin_proce_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_erro                       OUT  VARCHAR2);

   /**************************************************************************
   Descripcion       : Proceso de Asignacion Automatica de la Cartera
                              - Validacion de Cartera
                              - Seleccion de Cartera
                              - Excepciones de Cartera
                              - Asignacion de Cartera

   Fecha Creacion    : 24/03/2010
   Parametros Entrada :
            Codigo Pais,
            Codigo Usuario,
            Codigo Modulo,
            Codigo Proceso
  ***************************************************************************/
 PROCEDURE COB_PR_ASIGN_CARTE_AUTOM(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE,
  p_num_lote_asign               OUT  NUMBER,
  p_cod_erro                     OUT  VARCHAR2);

   FUNCTION cob_fn_valid_crono_carte(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2;

   FUNCTION cob_fn_valid_usuar_super(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2;

   FUNCTION cob_fn_valid_cobra_carte(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2;

   FUNCTION cob_fn_valid_porce_asign(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2;

   PROCEDURE cob_pr_asign_excep_monto_minim(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                             IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                   IN      seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_excep_monto_maxim(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN       seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN       cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_excep_sinte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN       cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN       seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_excep_clasi_clien(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN       seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT  VARCHAR2);

 PROCEDURE COB_PR_BLOQU_AUTOM_DIAS_ATRAS;
 
 PROCEDURE COB_PR_BLOQU_DESBL_DIAS_ATRAS;
 
   PROCEDURE COB_PR_ELIMI_CARTE_ASIGN(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN      seg_usuar.use_usua%TYPE);

 PROCEDURE COB_PR_ELIMI_CARTE_ASIGN(
  p_cod_cart                     IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE COB_PR_DEPUR_CARTE_AUTOM;

 PROCEDURE COB_PR_DEPUR_CARTE_ASIGN(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE COB_PR_GENER_REPOR_GZONA_CSV(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE);

 PROCEDURE COB_PR_GENER_REPOR_GZONA_HTML(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB);

 PROCEDURE COB_PR_GENER_REPOR_FFVV1_CLOB(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB);

 PROCEDURE COB_PR_GENER_REPOR_GZDEU_CLOB(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB);

 PROCEDURE COB_PR_GENER_INFOR_GEREN_FFVV;

 PROCEDURE COB_PR_GENER_INFOR_GEREN_REGIO(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_val_mail                       IN   mae_clien_comun.val_text_comu%TYPE);

 PROCEDURE COB_PR_GENER_INFOR_GEREN_ZONA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_val_mail                       IN   mae_clien_comun.val_text_comu%TYPE);

 PROCEDURE COB_PR_GENER_INFOR_SEGUI_COBRA(
  p_fec_proc                     IN    VARCHAR2);

 PROCEDURE COB_PR_ENVIO_CARTE_AUTOM(
  p_fec_proc                     IN    VARCHAR2 DEFAULT NULL,
  p_cod_usua                     IN    VARCHAR2 DEFAULT USER);

 PROCEDURE COB_PR_ENVIO_CARTE_CORPO(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE COB_PR_ENVIO_SALDO_AUTOM;

 PROCEDURE COB_PR_PROCE_DIARI;

 PROCEDURE COB_PR_GENER_CRONO_CARTE_AUTOM(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 PROCEDURE COB_PR_GENER_CRONO_CARTE_CAMPA(
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER);

 FUNCTION COB_FN_OBTIE_FECHA_FACTU_ZONAS(
  p_cod_peri                   IN   cra_perio.oid_peri%TYPE,  
  p_cod_regi                   IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                   IN   zon_zona.cod_zona%TYPE)
 RETURN DATE;

 FUNCTION COB_FN_OBTIE_FECHA_GENER_CARTE(
  p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                   IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                   IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                   IN   zon_zona.cod_zona%TYPE)
 RETURN DATE;

 FUNCTION cob_fn_valid_etapa_minim_deuda(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE)
 RETURN NUMBER;
 
 FUNCTION cob_fn_obtie_fecha_gener_etmin(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE;

 FUNCTION cob_fn_obtie_fecha_gener_etnor(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE;

 FUNCTION cob_fn_obtie_fecha_cierr_anter(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE;
 
 FUNCTION cob_fn_obtie_fecha_gener_excep(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                IN   DATE)
 RETURN DATE;

 FUNCTION cob_fn_obtie_fecha_cierr_excep(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_cier_cart                IN   DATE)
 RETURN DATE;
        
 FUNCTION COB_FN_OBTIE_FECHA_CIERR_CARTE(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                IN   DATE)
 RETURN DATE;

 PROCEDURE COB_PR_ACTUA_CRONO_CARTE(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_tipo_exce                    IN   VARCHAR2,
  p_fec_exce                     IN   DATE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE);

 PROCEDURE COB_PR_VALID_MODIF_CRONO_MASIV(
  p_num_lote                     IN   cob_carga_modif_crono_carte.num_lote%TYPE,
  p_ind_erro                     OUT   NUMBER);   

 PROCEDURE COB_PR_PROCE_MODIF_CRONO_MASIV(
  p_num_lote                     IN   cob_carga_modif_crono_carte.num_lote%TYPE);
      
 FUNCTION cob_pr_gener_fila_tabla_html(
  p_valo                           IN   VARCHAR2,
  p_anch                           IN   VARCHAR2,
  P_font                           IN   NUMBER DEFAULT 1,
  p_size                           IN   NUMBER DEFAULT 2,
  p_ind_cent                       IN   NUMBER DEFAULT 0,
  p_ind_negr                       IN   NUMBER DEFAULT 0)
 RETURN VARCHAR2;

/******************************************************************************
  Descripcion             : COB_PR_GENER_REPOR_CREDT
                                   Proceso que genera la data para el Reporte Histórico de Operación Crediticia
  Fecha Creacion      : 10/02/2014
  Parametros Entrada:
                          psMesAnnio  : mes y año del proceso
                          psFechaInicioMesAnnio: primer dia del mes del año
                          psFechaFinMesAnnio: ultimo dia del mes del año
  Autor             : Sebastian Guerra
 *******************************************************************************/
  PROCEDURE COB_PR_GENER_REPOR_CREDT(
      psMesAnnio                          VARCHAR2,
      psFechaInicioMesAnnio        VARCHAR2,
      psFechaFinMesAnnio            VARCHAR2
      );

/***************************************************************************
  Descripcion           : Genera archivo TXT correspondiente al Reporte Histórico de Operación Crediticia
  Fecha Creacion    : 10/02/2014
  Autor                   : Sebastian Guerra
  Parametros :
              psCodigoPais : Codigo pais
              psNombreArchivo : Nombre del archivo
              psTitulo : Titulo del documento
              psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
  PROCEDURE COB_PR_GENER_REPOR_CREDT_TXT(
      psCodigoPais                     VARCHAR2,
      psNombreArchivo              VARCHAR2,
      psTitulo                              VARCHAR2,
      psDirectorio              OUT  VARCHAR2
      );

/***************************************************************************
Descripcion       : Inserta Data en Tabla COB_GESTI_COBRA_PAIS de tabla temporal

Fecha Creacion    : 14/05/2014
Autor             : Gonzalo Javier Huertas Agurto

***************************************************************************/
PROCEDURE COB_PR_INSER_CARGA_MASIV_GESTI(psCodigoPais                     VARCHAR2,
                                         psFlagIngresado                  OUT VARCHAR2);

END COB_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY COB_PKG_PROCE IS

 /* Declaracion de variables */
 ln_sqlcode                        NUMBER(10);
 ls_sqlerrm                        VARCHAR2(1500);
 w_filas                           NUMBER:=5000;

 -- Declaracion de Tipos --
 TYPE t_tab_cob_detal_asign_carte  IS TABLE OF cob_detal_asign_carte%ROWTYPE;
 TYPE t_tab_cod_clie               IS TABLE OF mae_clien.cod_clie%TYPE;
 TYPE t_tab_oid_clie               IS TABLE OF mae_clien.oid_clie%TYPE;
 TYPE t_tab_cod_cart               IS TABLE OF cob_cabec_asign_carte.cod_cart%TYPE;
 TYPE t_tab_cod_usua_cobr          IS TABLE OF cob_usuar_cobra_pais.cod_usua_cobr%TYPE;

 -- Declaracion de Variables --
 lv_user_dummy                     VARCHAR2(100):='USER_DUMMY';
 lv_id_proc_ejec                   NUMBER(12);
 lv_des_log                        VARCHAR2(2500);
 gv_des_log                        VARCHAR2(2500);
 lv_cod_modu                       fin_modul.cod_modu%TYPE:='COB';
 gv_cod_modu                       fin_modul.cod_modu%TYPE:='COB';
 gv_cod_proc_asig_carte_indi       fin_proce_modul.cod_proc%TYPE:='1508';
 lv_cod_proc_log                   fin_proce_modul.cod_proc%TYPE;
 lv_cod_proc_gene_cron_auto        fin_proce_modul.cod_proc%TYPE:='1509';
 lv_cod_proc_gene_camp_auto        fin_proce_modul.cod_proc%TYPE:='1510';
 lv_cod_proc_gene_cron             fin_proce_modul.cod_proc%TYPE:='01';
 lv_cod_proc_asig_cart             fin_proce_modul.cod_proc%TYPE:='02';
 lv_cod_proc_actu_cart             fin_proce_modul.cod_proc%TYPE:='03';
 lv_cod_proc_cron_exce             fin_proce_modul.cod_proc%TYPE:='10';
 --   lv_cod_proc_liqu_cart fin_proce_modul.cod_proc%TYPE:='04';
 --   lv_cod_proc_actu_std  fin_proce_modul.cod_proc%TYPE:='05';
 --   lv_cod_proc_cron_refr fin_proce_modul.cod_proc%TYPE:='11';

 gc_cod_modu                       CONSTANT   VARCHAR2(3):='COB';
 gc_cod_proc_asig_auto             CONSTANT   VARCHAR2(8):='20010910';
 gc_cod_usua                       CONSTANT   VARCHAR2(15):='COBRANZAS';
 gc_mail_envio_proce               CONSTANT   VARCHAR2(30):='lia.florencio@belcorp.biz';


 lv_sqlcode                        NUMBER(10);
 lv_sqlerrm                        VARCHAR2(1000);
 gv_sqlerrm                        VARCHAR2(1000);
 lv_cont                           NUMBER(12);
 lv_start_time                     NUMBER;
 lv_end_time                       NUMBER;

 -- Excepciones --
 gv_reco_trac                      FIN_PKG_GENER.error_rt;
 e_exis_proc_ejec                  EXCEPTION;
 ge_erro_no_docu                   EXCEPTION;

 PROCEDURE cob_pr_crea_crono_carte(
  p_cod_pais seg_pais.cod_pais%TYPE,
  p_cod_socie seg_socie.cod_soci%TYPE,
  p_cod_peri  seg_perio_corpo.cod_peri%TYPE,
  p_cod_usu   cob_usuar_cobra_pais.cod_usua_cobr%TYPE );

 PROCEDURE cob_pr_gener_crono_xperi(
  p_cod_pais seg_pais.cod_pais%TYPE,
  p_cod_socie seg_socie.cod_soci%TYPE,
  p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri seg_perio_corpo.cod_peri%TYPE,
  p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE);

 PROCEDURE cob_pr_gener_crono_xdias(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE );


   PROCEDURE cob_pr_gener_crono_xdias_secc2(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE);

      PROCEDURE cob_pr_obtie_excep(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi  zon_regio.cod_regi%type,
      p_cod_zona   zon_zona.cod_zona%type,
      p_tip_exce  cob_crono_carte_excep.tip_exce%type,
      p_ind_exep OUT NUMBER,
      p_fec_exce OUT DATE);

   PROCEDURE cob_pr_refre_crono(
      p_cod_pais       seg_pais.cod_pais%TYPE,
      p_cod_socie      seg_socie.cod_soci%TYPE,
      p_cod_peri       seg_perio_corpo.cod_peri%TYPE,
      p_cod_usu        cob_usuar_cobra_pais.cod_usua_cobr%TYPE);

   FUNCTION cob_fn_obtie_fecha_inici_factu(
      p_oid_peri                     cra_crono.perd_oid_peri%TYPE,
      p_cod_regi                    zon_regio.cod_regi%type,
      p_cod_zona                   zon_zona.cod_zona%type )
   RETURN date;

   FUNCTION cob_fn_obtie_fecha_util (
      p_fecha                         DATE,
      p_acep_lune                  NUMBER)
   RETURN date;

   FUNCTION cob_fn_obtie_etapa_anter(
      p_cod_pais                     seg_pais.cod_pais%TYPE,
      p_cod_socie                    seg_socie.cod_soci%TYPE,
      p_cod_etapa_ref            cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_sec_etapa_ref             cob_etapa_deuda_pais.num_secu_etap%TYPE )
   RETURN VARCHAR2;

   FUNCTION cob_fn_obtie_fecier_etapa(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etapa_ref  cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi  zon_regio.cod_regi%TYPE,
      p_cod_zona   zon_zona.cod_zona%TYPE )
   RETURN DATE;

    FUNCTION cob_fn_ajust_fecha_a1530(
      p_fecha date,
      p_num_dias_15 cob_etapa_deuda_pais.num_dias_ajus_cier_15%type,
      p_num_dias_30 cob_etapa_deuda_pais.num_dias_ajus_cier_30%type)
   RETURN DATE;

   PROCEDURE cob_pr_asign_carte_manua(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_cod_usua cob_detal_asign_carte.usu_crea%TYPE);

   PROCEDURE cob_pr_obtie_zonas_terri_admin(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_fec_gene_cart cob_crono_carte.fec_gene_cart%TYPE);

   PROCEDURE cob_pr_obtie_clien_unida_admin(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud    cob_etapa_deuda_pais.cod_etap_deud%TYPE);

     PROCEDURE cob_pr_asign_movim_cuent_clien(
      p_oid_soci        seg_socie.cod_soci%TYPE,
      p_oid_peri        cra_perio.oid_peri%TYPE,
      p_cod_cart       cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier        cob_cabec_asign_carte.fec_cier%TYPE,
      p_cod_usua      cob_usuar_cobra_pais.cod_usua_cobr%TYPE);

  PROCEDURE cob_pr_asign_carte_super
     ( p_cod_pais seg_pais.cod_pais%TYPE,
       p_cod_soci seg_socie.cod_soci%TYPE,
       p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
       p_cod_cart Cob_Cabec_Asign_Carte.Cod_Cart%TYPE);

   PROCEDURE cob_pr_obtie_impo_deuda_medio(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_Zona zon_zona.cod_zona%TYPE,
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      p_imp_cart_tot OUT NUMBER,
      p_imp_deud_med OUT NUMBER,
      p_num_cob OUT NUMBER);

   PROCEDURE cob_pr_obtie_cuota_asign(
     p_cod_cart IN cob_detal_asign_carte.cod_cart%TYPE,
     p_cod_clie  OUT mae_clien.cod_clie%TYPE,
     p_imp_pend OUT NUMBER);

   PROCEDURE cob_pr_asign_carte_clien(
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie mae_clien.cod_clie%TYPE,
      p_cod_usu cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig OUT NUMBER);

     FUNCTION cob_fn_obtie_cuota_asign_defec(
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif NUMBER)
   RETURN VARCHAR2;

   FUNCTION cob_fn_obtie_cuota_asign_exces(
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif NUMBER)
   RETURN VARCHAR2;

 PROCEDURE cob_pr_asign_carte_indiv(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_num_lote                       IN   cob_cabec_asign_carte.num_lote_asign%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                  IN   cob_crono_carte.fec_gene_cart%TYPE,
  p_ind_tipo_asig                  IN   cob_param_gener.val_para%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_cart                       OUT  cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_erro                       OUT  VARCHAR2);

   PROCEDURE cob_pr_valid_ejecu_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

    FUNCTION cob_fn_valid_ejecu_indiv(
      p_cod_pais                             seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             zon_regio.cod_regi%TYPE,
      p_cod_zona                            zon_zona.cod_zona%TYPE,
      p_nomb_func                          VARCHAR2)
   RETURN VARCHAR2;

   PROCEDURE cob_pr_selec_asign_carte (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_fec_gene_cart                       IN      cob_crono_carte.fec_gene_cart%TYPE,
      p_ind_tipo_asig                     IN      cob_param_gener.val_para%TYPE,
      p_cod_usua                            IN      seg_usuar.cod_usua%TYPE,
      p_cod_cart                            OUT      cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier                             OUT   DATE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_selec_unida_admin (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_erro                           OUT   VARCHAR2);

   PROCEDURE cob_pr_selec_carte_movim (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      cra_perio.oid_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_fec_gene_cart                       IN cob_crono_carte.fec_gene_cart%TYPE,
      p_cod_cart                             IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN       seg_usuar.cod_usua%TYPE,
      p_fec_cier                             OUT   VARCHAR2,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_selec_datos_clien (
      p_cod_pais                              IN     seg_pais.cod_pais%TYPE,
      p_cod_cart                             IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_selec_carte_clien(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_cart                             IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN       seg_usuar.cod_usua%TYPE,
      p_cod_erro                           OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_excep_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT VARCHAR2);

   PROCEDURE cob_pr_selec_carte_movim_venci (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN       cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN       seg_usuar.cod_usua%TYPE,
      p_fec_cier                             OUT  VARCHAR2,
      p_cod_erro                           OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_excep_carte_indiv(
      p_cod_pais                             IN seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_nomb_func                          IN VARCHAR2,
      p_cod_erro                             OUT VARCHAR2);

   PROCEDURE cob_pr_ejecu_asign_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier                             IN      DATE,
      p_ind_tipo_asig                    IN       cob_param_gener.val_para%TYPE,
      p_cod_usua                            IN      cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_carte_cuota_eante(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN         seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier                             IN         cob_cabec_asign_carte.fec_cier%TYPE,
      p_cod_usua                            IN         seg_usuar.use_usua%TYPE,
      p_cod_erro                            OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_cuota_epost(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usua                            IN         seg_usuar.use_usua%TYPE,
      p_cod_erro                            OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_fija(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_fija_sinba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_carte_fija_conba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_carte_fija_cobra(
      p_cod_pais                            IN         seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                 IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                            IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                           IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                           IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                           OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_gener(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_carte_gener_sinba(
      p_cod_pais                            IN         seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                 IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                            IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                           IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                           IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                           OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_gener_conba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2);

   PROCEDURE cob_pr_asign_carte_gener_cobra(
      p_cod_pais                             IN        seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN        cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT     VARCHAR2);

   PROCEDURE cob_pr_asign_carte_balan_clien(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_cart                            IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_erro                            OUT      VARCHAR2);

   PROCEDURE cob_pr_asign_carte_balan_zona(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_cart                             IN        cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                             OUT     VARCHAR2);

   PROCEDURE cob_pr_asign_carte_super(
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE);

 PROCEDURE cob_pr_bloqu_consu_carte(
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE);

 PROCEDURE cob_pr_gener_carte_asign(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_num_lote                       IN   cob_cabec_asign_carte.num_lote_asign%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                  IN   cob_crono_carte.fec_gene_cart%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_fec_cier                       IN   DATE,
  p_cod_usua                       IN   seg_usuar.cod_usua%TYPE);

  PROCEDURE cob_pr_obtie_clien_asign(
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie                             OUT   mae_clien.cod_clie%TYPE,
      p_imp_pend                           OUT   NUMBER);

   PROCEDURE cob_pr_asign_carte_clien_cobra(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                   IN         cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie                                    IN         mae_clien.cod_clie%TYPE,
      p_cod_usu                                     IN        cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig                          OUT     NUMBER);

  FUNCTION cob_fn_obtie_clien_asign_defec(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                   IN        cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                                           IN        NUMBER)
   RETURN VARCHAR2;

   FUNCTION cob_fn_obtie_clien_asign_exces(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                  IN         cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                                          IN         NUMBER)
    RETURN VARCHAR2;

   PROCEDURE cob_pr_obtie_zona_asign(
      p_cod_cart                      IN cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_zona                       OUT mae_clien.cod_clie%TYPE,
      p_imp_pend                      OUT NUMBER);

   PROCEDURE cob_pr_asign_carte_zona(
      p_cod_cart                     IN    cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_zona                     IN    zon_zona.cod_zona%TYPE,
      p_cod_usu                       IN    cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig            OUT NUMBER);

   FUNCTION cob_fn_obtie_zona_asign_defec(
      p_cod_cart                     IN    cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                              IN   NUMBER)
   RETURN VARCHAR2;

   FUNCTION cob_fn_obtie_zona_asign_exces(
      p_cod_cart                      IN    cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                               IN   NUMBER)
   RETURN VARCHAR2;

   FUNCTION cob_fn_obtie_cobra_asign_carte
   RETURN NUMBER;

   PROCEDURE cob_pr_asign_carte_email_ejecu;

   PROCEDURE cob_pr_asign_carte_email_super;

 PROCEDURE COB_PR_GENER_CRONO_CARTE(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_socie                      IN   seg_socie.cod_soci%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_usu                        IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
 IS

  lv_existen_reg                   NUMBER(11);
  lv_ind_gene_crono                VARCHAR2(2);
 BEGIN

  lv_ind_gene_crono := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('TipoGeneracionCronograma');
   
  IF lv_ind_gene_crono IS NULL THEN
     
      lv_cod_proc_log := lv_cod_proc_gene_cron;
      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, p_cod_usu, lv_id_proc_ejec);

      lv_des_log:='Inicio  COB_PR_GENER_CRONO_CARTE    parametros:';
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);

      lv_des_log:='Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie || ' Cod Peri: ' || p_cod_peri;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);

  /*
  Verifica si el cronograma ya tiene carteras asignadas para determinar
  si refresca o crea el cronograma de nuevo
  */
  SELECT COUNT(*)
  INTO lv_existen_reg
  FROM  cob_crono_carte cro
  WHERE  cro.cod_pais = p_cod_pais
    AND cro.cod_soci = p_cod_socie
    AND cro.cod_peri = p_cod_peri
    AND cro.ind_gene_cart = 1;

   IF  lv_existen_reg > 0 THEN
               lv_des_log:= 'Ya existe cronograma con cartera asignada, se refresca cronograma...';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);
              cob_pkg_proce.cob_pr_refre_crono(p_cod_pais, p_cod_socie, p_cod_peri, p_cod_usu);
           ELSE
               -- se invoca a rutina que crea cronograma
    cob_pkg_proce.cob_pr_crea_crono_carte(p_cod_pais, p_cod_socie, p_cod_peri, p_cod_usu);
   END IF;

           lv_des_log:= 'Fin  COB_PR_GENER_CRONO_CARTE   generacion cronograma.';
           fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                             lv_id_proc_ejec, lv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, 2);

  ELSE
  
   -- Generacion por el nuevo cronograma
   COB_PR_GENER_CRONO_CARTE_CAMPA(p_cod_peri,p_cod_usu);
  
  END IF;
    
 END COB_PR_GENER_CRONO_CARTE;

      PROCEDURE cob_pr_crea_crono_carte(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_socie                      IN   seg_socie.cod_soci%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_usu                        IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
      IS

          lv_ind_dias_calen  cob_pais_socie_param.ind_dias_cale%type;
          lv_ind_dias_crono  COB_PAIS_SOCIE_PARAM.ind_dias_cron%type;
          lv_ind_calen_sec   COB_PAIS_SOCIE_PARAM.ind_cale_secu%type;
          lv_cod_etapa_ini   COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
          lv_cod_etapa_ant   COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
          lv_edad_etapa_ini  cob_etapa_deuda_pais.val_edad_inic%type;
          lv_existen_reg     number(11);

       CURSOR c_etapas
       IS
       SELECT eta.cod_etap_deud, eta.num_secu_etap
       FROM cob_etapa_deuda_pais eta
       WHERE  eta.cod_pais = p_cod_pais
       AND    eta.cod_soci = p_cod_socie
       AND    eta.val_edad_inic >= lv_edad_etapa_ini
       ORDER BY eta.val_edad_inic;

      BEGIN

      lv_des_log:='Inicio  cob_pr_crea_crono_carte    parametros:';
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);

      lv_des_log:='Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie || ' Cod Peri: ' || p_cod_peri;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);
   /*
   Verifica que el cronograma tiene carteras
    asignadas.
   */
   SELECT  count(*)
   INTO lv_existen_reg
   FROM  cob_crono_carte cro
   WHERE cro.cod_pais = p_cod_pais
     AND cro.cod_soci = p_cod_socie
     AND cro.cod_peri = p_cod_peri
     AND cro.ind_gene_cart = 1;

           -- Verifica si ya se genero el cronograma
           select count(*)
           into lv_existen_reg
           from  cob_crono_carte cro
           where  cro.cod_pais = p_cod_pais
           and    cro.cod_soci = p_cod_socie
           and    cro.cod_peri = p_cod_peri
           and    cro.ind_gene_cart = 1;

           if  lv_existen_reg > 0 then
               lv_des_log:= 'Error. Ya existe cronograma con cartera asignada.';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);
               return;
           end if;
           select count(*)
           into lv_existen_reg
           from  cob_crono_carte cro
           where  cro.cod_pais = p_cod_pais
           and    cro.cod_soci = p_cod_socie
           and    cro.cod_peri = p_cod_peri;

           if  lv_existen_reg > 0 then
               lv_des_log:= 'Se elimina cronograma previo';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);

    DELETE FROM cob_crono_carte cro
    WHERE cro.cod_pais = p_cod_pais
      AND cro.cod_soci = p_cod_socie
      AND cro.cod_peri = p_cod_peri;

   END IF;

         -- obtiene parametros para el pais
         SELECT  pais.ind_dias_cron, pais.ind_dias_cale,
                      pais.ind_cale_secu,  pais.cod_etap_inic
         INTO    lv_ind_dias_crono, lv_ind_dias_calen,
                   lv_ind_calen_sec,  lv_cod_etapa_ini
         FROM COB_PAIS_SOCIE_PARAM pais
         WHERE pais.cod_pais = p_cod_pais
         AND   pais.cod_soci = p_cod_socie;

   -- Obtiene la edad de la etapa inicial
           SELECT etp.val_edad_inic
           INTO   lv_edad_etapa_ini
           FROM   cob_etapa_deuda_pais etp
           WHERE  etp.cod_pais = p_cod_pais
           AND    etp.cod_soci = p_cod_socie
           AND    etp.cod_etap_deud = lv_cod_etapa_ini;

           IF  lv_ind_dias_calen = lv_ind_dias_crono THEN
               lv_des_log:= 'Error parametros errados: indicador dias calen.';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);
               RETURN;
           END IF;

           FOR v_etapas IN c_etapas  LOOP

   -- Obtiene el codigo de la etapa de deuda anterior
              IF   v_etapas.cod_etap_deud = lv_cod_etapa_ini THEN
                   lv_cod_etapa_ant := v_etapas.cod_etap_deud;
              ELSE
                   lv_cod_etapa_ant := cob_fn_obtie_etapa_anter(p_cod_pais, p_cod_socie,
                                       v_etapas.cod_etap_deud, v_etapas.num_secu_etap);
              END IF;

               IF   lv_ind_dias_crono = 1 THEN
     -- Generacion en Base a Dias Cronograma
     cob_pkg_proce.cob_pr_gener_crono_xperi(p_cod_pais, p_cod_socie, v_etapas.cod_etap_deud, p_cod_peri, lv_cod_etapa_ant, p_cod_usu);

               ELSE
     -- Generacion en Base a Dias Calendario
                    IF  lv_cod_etapa_ini = v_etapas.cod_etap_deud
      OR  lv_ind_calen_sec = 0 THEN
      cob_pkg_proce.cob_pr_gener_crono_xdias(p_cod_pais, p_cod_socie, v_etapas.cod_etap_deud, p_cod_peri, lv_cod_etapa_ant, p_cod_usu);
                    ELSE
      cob_pkg_proce.cob_pr_gener_crono_xdias_secc2(p_cod_pais, p_cod_socie, v_etapas.cod_etap_deud, p_cod_peri, lv_cod_etapa_ant, p_cod_usu);
                    END IF;
               END IF;

           END LOOP;

           lv_des_log:= 'Fin  cob_pr_crea_crono_carte   generacion cronograma.';
           fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                             lv_id_proc_ejec, lv_des_log);

      END cob_pr_crea_crono_carte;

   PROCEDURE cob_pr_gener_crono_xperi(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
      IS
          lv_oid_peri        cra_perio.oid_peri%TYPE;
          lv_oid_peri_gen    cra_perio.oid_peri%TYPE;
          lv_cod_peri_gen    seg_perio_corpo.cod_peri%TYPE;
          lv_oid_peri_x      cra_perio.oid_peri%TYPE;
          lv_cod_peri_x      seg_perio_corpo.cod_peri%TYPE;
          lv_fec_ini_fac     date;
          lv_fec_gen_cart    date;
          lv_fec_ini_gest    date;
          lv_fec_comp_pago   date;
          lv_fec_cierre      date;
          lv_fec_cierre_ant      date;
          lv_ind_dias_calen  COB_PAIS_SOCIE_PARAM.ind_dias_cale%type;
          lv_ind_dias_crono  COB_PAIS_SOCIE_PARAM.ind_dias_cron%type;
          lv_ind_calen_sec   COB_PAIS_SOCIE_PARAM.ind_cale_secu%type;
          lv_cod_etapa_ini   COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
          lv_val_edad_inic   cob_etapa_deuda_pais.val_edad_inic%type;
          lv_ind_dias_gracia cob_etapa_deuda_pais.ind_dias_grac%type;
          lv_num_dias_gracia cob_etapa_deuda_pais.num_dias_grac%type;
          lv_num_dias_grac_cier cob_etapa_deuda_pais.num_dias_grac_cier%type;
          lv_num_periodos    cob_etapa_deuda_pais.num_peri%type;
          lv_num_dias_gest   cob_etapa_deuda_pais.num_dias_gest%type;
          lv_num_dias_comp   cob_etapa_deuda_pais.num_dias_comp%type;
          lv_num_dias_cierr  cob_etapa_deuda_pais.num_dias_cier%type;
          lv_ind_peri_comp   cob_etapa_deuda_pais.ind_peri_comp%type;
          lv_ind_peri_cier   cob_etapa_deuda_pais.ind_peri_cier%type;
          lv_num_peri_comp   cob_etapa_deuda_pais.num_peri_comp%type;
          lv_num_peri_cier   cob_etapa_deuda_pais.num_peri_cier%type;
          lv_ind_ajus_cier   cob_etapa_deuda_pais.ind_ajus_cier%type;
          lv_num_dias_ajus_cier_15 cob_etapa_deuda_pais.num_dias_ajus_cier_15%type;
          lv_num_dias_ajus_cier_30 cob_etapa_deuda_pais.num_dias_ajus_cier_30%type;
          lv_ind_gene_lune   cob_etapa_deuda_pais.ind_gene_lune%type;


          lv_ind_excep       number(1);
          lv_fec_excep          date;
          lv_ind_aviso_sec_etapa  number(1) := 0;
          lv_num_reg_ins        NUMBER(9);

          reg_cob_crono_carte cob_crono_carte%ROWTYPE;

/*  Constantes para tipo de excepciones de fechas: */
         /* excepcion para fecha de cierre */
          lv_cier     cob_crono_carte_excep.tip_exce%type := 'CIE';
         /* excepcion para fecha de generacion de cartera */
          lv_gene     cob_crono_carte_excep.tip_exce%type := 'GEN';

          /*  determina la relacion de Unidades Administ. involucradas en la etapa */
          /*
          CURSOR c_relacion_ua
             IS
                  SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_etapa_cobra_ua ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                  AND   ua.cod_soci = p_cod_socie
                  AND   ua.cod_etap_deud = p_cod_etap_deud
                  AND   ua.cod_zona = zon.cod_zona(+)
                  AND  ua.cod_regi = reg.cod_regi
                  ORDER BY ua.cod_regi, ua.cod_zona;
             */
             CURSOR c_relacion_ua
             IS
                SELECT *
                FROM
                   ((SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                      FROM
                        cob_etapa_cobra_ua ua,
                        zon_regio reg,
                      ( SELECT cod_zona, oid_zona
                         FROM  zon_zona
                         WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                       AND   ua.cod_soci = p_cod_socie
                       AND   ua.cod_etap_deud = p_cod_etap_deud
                       AND   ua.cod_zona = zon.cod_zona(+)
                       AND  ua.cod_regi = reg.cod_regi)
                  UNION
                   (SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                     FROM  cob_param_asign_zonas_cobra ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                     WHERE ua.cod_pais = p_cod_pais
                     AND   ua.cod_etap_deud = p_cod_etap_deud
                     AND   ua.cod_zona = zon.cod_zona(+)
                     AND  ua.cod_regi = reg.cod_regi))
                  ORDER BY cod_regi, cod_zona;

      BEGIN

          lv_des_log:='Inicio cob_pr_gener_crono_xperi Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie ||
                      ' Cod Etapa: ' || p_cod_etap_deud || ' Cod Peri: ' || p_cod_peri ||
                      ' Cod etapa anter: ' || p_cod_etap_deud_ant || ' Cod usuar: ' || p_cod_usu;
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_des_log);

           -- obtiene oid de campa?a
           lv_oid_peri:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, p_cod_peri);

           -- obtiene parametros para el pais
           SELECT  pais.ind_dias_cron, pais.ind_dias_cale,
                   pais.ind_cale_secu,  pais.cod_etap_inic
           INTO    lv_ind_dias_crono, lv_ind_dias_calen,
                   lv_ind_calen_sec,  lv_cod_etapa_ini
           FROM COB_PAIS_SOCIE_PARAM pais
           where pais.cod_pais = p_cod_pais
           and   pais.cod_soci = p_cod_socie;

           -- obtiene parametros para la etapa
           SELECT pais.val_edad_inic, pais.ind_dias_grac,
                  pais.num_dias_grac, pais.num_peri,
                  pais.num_dias_gest, pais.num_dias_comp, pais.num_dias_cier,
                  pais.ind_peri_comp, pais.ind_peri_cier,
                  pais.num_peri_comp, pais.num_peri_cier, pais.num_dias_grac_cier,
                  pais.ind_ajus_cier, pais.num_dias_ajus_cier_15,
                  pais.num_dias_ajus_cier_30, pais.ind_gene_lune
           INTO   lv_val_edad_inic, lv_ind_dias_gracia,
                  lv_num_dias_gracia, lv_num_periodos,
                  lv_num_dias_gest, lv_num_dias_comp, lv_num_dias_cierr,
                  lv_ind_peri_comp, lv_ind_peri_cier,
                  lv_num_peri_comp, lv_num_peri_cier, lv_num_dias_grac_cier,
                  lv_ind_ajus_cier, lv_num_dias_ajus_cier_15,
                  lv_num_dias_ajus_cier_30, lv_ind_gene_lune
           FROM  cob_etapa_deuda_pais pais
           where pais.cod_pais = p_cod_pais
           and   pais.cod_soci = p_cod_socie
           and   pais.cod_etap_deud = p_cod_etap_deud;

           if  lv_ind_dias_crono <> 1 then
               lv_des_log:= 'Error gen_crono_xperi parametros errados: indicador dias calen ';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                          lv_id_proc_ejec, lv_des_log);
               raise_application_error(-20010, lv_des_log);
               return;
           end if;

           -- si el indic dias de gracia esta desactivado,  se fuerza el numero
           -- de dias de gracia  a  cero.
           if  lv_ind_dias_gracia = 0 then
               lv_num_dias_gracia := 0;
           end if;

            -- calcula campa?a x+n
            lv_cod_peri_gen := cob_pkg_gener.cob_fn_calcu_perio_nsgte(p_cod_peri, lv_num_periodos );
            -- obtiene oid de campa?a x+n
            lv_oid_peri_gen :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, lv_cod_peri_gen);

--       dbms_output.put_line( 'p_cod_peri: ' || p_cod_peri || ' nva camp: ' || lv_cod_peri_gen || 'oid: ' || lv_oid_peri_gen  ) ;
         lv_num_reg_ins := 0;

         FOR v_relacion_ua IN c_relacion_ua LOOP
       /* Obtiene fecha de facturacion inicial de campa?a procesada (X) */
             lv_fec_ini_fac := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri, v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);
/* Determina fecha de generacion de cartera */
             /* verifica si existe fecha de generacion excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_gene,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de generacion excepcional  */
                 lv_fec_gen_cart := lv_fec_excep;
             else

       /* Obtiene fecha de facturacion inicial de campa?a X+n */
             lv_fec_gen_cart := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri_gen, v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);

             lv_fec_gen_cart := lv_fec_gen_cart + lv_num_dias_gracia;
             /* verifica si es sabado o domingo,  de ser asi  ubica siguiente dia util */
             lv_fec_gen_cart := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune );

/* Si no es etapa inicial,  verifica que la fecha de generacion
   sea posterior a la fecha de cierre de la etapa anterior */
             if  p_cod_etap_deud <> p_cod_etap_deud_ant then
                 lv_fec_cierre_ant := cob_fn_obtie_fecier_etapa (p_cod_pais, p_cod_socie,
                                      p_cod_etap_deud_ant, p_cod_peri, v_relacion_ua.cod_regi,
                                      v_relacion_ua.cod_zona);
                   if  lv_fec_cierre_ant >= lv_fec_gen_cart  then
                           if   lv_ind_aviso_sec_etapa = 0  then
                                lv_ind_aviso_sec_etapa := 1;
                                lv_des_log:= 'xperi, Ajusta fec gene' || lv_fec_gen_cart || ' Etapa: ' || p_cod_etap_deud || ' Region: ' || v_relacion_ua.cod_regi || ' Zona: ' || v_relacion_ua.cod_zona;
                       fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu,
                                               lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);
                   end if;
                           lv_fec_gen_cart  := lv_fec_cierre_ant + 1;
                           lv_fec_gen_cart  :=  cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune );
                       end if;
                   end if;
             end if;

             lv_fec_ini_gest  := lv_fec_gen_cart + lv_num_dias_gest;
             lv_fec_ini_gest := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_ini_gest, 1);
             /* calcula fecha compromiso de pago por numero de dias o por numero de periodos */
             /* segun defina el indicador ind_peri_comp  */
             if  lv_ind_peri_comp = 1 then
                 -- calcula campa?a x+n
                 lv_cod_peri_x := cob_pkg_gener.cob_fn_calcu_perio_nsgte(p_cod_peri, lv_num_peri_comp );
                 -- obtiene oid de campa?a x+n
                 lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, lv_cod_peri_x);

               /* Obtiene fecha de facturacion inicial de campa?a X+n que se usa como fecha de compromiso de pago*/
                 lv_fec_comp_pago := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri_x, v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);
             else
                 /* calculo por numero de dias*/
             lv_fec_comp_pago := lv_fec_ini_gest + lv_num_dias_comp;
             end if;
             /* verifica si existe fecha de cierre excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_cier,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de cierre excepcional  */
                 lv_fec_cierre := lv_fec_excep;
             else
             /* calcula fecha de cierre de manera regular segun parametros */
             /* por numero de dias o por numero de periodos segun defina el indicador ind_peri_cier */
                 if  lv_ind_peri_cier = 1 then
                     -- calcula campa?a x+n
                     lv_cod_peri_x := cob_pkg_gener.cob_fn_calcu_perio_nsgte(p_cod_peri, lv_num_peri_cier );
                     -- obtiene oid de campa?a x+n
                     lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, lv_cod_peri_x);

                   /* Obtiene fecha de facturacion inicial de campa?a X+n que se usa como fecha de cierre */
                     lv_fec_cierre := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri_x, v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);
                   /* a?ade dias de gracia de estar vigente */
                     if  lv_num_dias_grac_cier > 0 then
                         lv_fec_cierre := lv_fec_cierre + lv_num_dias_grac_cier;
                         lv_fec_cierre    := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_cierre, 1);
                     end if;

                 else
                     /* calculo por numero de dias*/
                     lv_fec_cierre    := lv_fec_ini_gest + lv_num_dias_cierr + lv_num_dias_grac_cier ;
                     lv_fec_cierre    := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_cierre, 1);
                 end if;
                 if  lv_ind_ajus_cier = 1 then
                     lv_fec_cierre  := cob_fn_ajust_fecha_a1530(lv_fec_cierre, lv_num_dias_ajus_cier_15, lv_num_dias_ajus_cier_30);
                 end if;

             end if;
--             dbms_output.put_line('  ');
--             DBMS_OUTPUT.put_line('Cod Regi: ' || v_relacion_ua.cod_regi || ' Cod Zona: ' ||  v_relacion_ua.cod_zona );
--             dbms_output.put_line('lv_fec_ini_fac: ' || lv_fec_ini_fac || ' lv_fec_gen_cart: ' || lv_fec_gen_cart ) ;
--             dbms_output.put_line('lv_fec_ini_gest: ' || lv_fec_ini_gest || ' lv_fec_comp_pago: ' || lv_fec_comp_pago || ' lv_fec_cierre: ' || lv_fec_cierre ) ;
--             dbms_output.put_line('fecha: ' || lv_fec_gen_cart || ' sec: ' || lv_dia_sec );


      lv_des_log:= 'Cod Regi: '        || v_relacion_ua.cod_regi || ' Cod Zona: '              || v_relacion_ua.cod_zona  ||
                   'lv_fec_ini_fac: '  || lv_fec_ini_fac         || ' lv_fec_gen_cart: '       || lv_fec_gen_cart   ||
                   'lv_fec_ini_gest: ' || lv_fec_ini_gest        || ' lv_fec_comp_pago: '      || lv_fec_comp_pago ||
                   'lv_fec_cierre: '   || lv_fec_cierre          ||  'lv_fec_gen_cart_fecha: ' || lv_fec_gen_cart ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                          lv_id_proc_ejec, lv_des_log);




             reg_cob_crono_carte.cod_pais := p_cod_pais;
             reg_cob_crono_carte.cod_soci := p_cod_socie;
             reg_cob_crono_carte.cod_peri := p_cod_peri;
             reg_cob_crono_carte.cod_etap_deud := p_cod_etap_deud;
             reg_cob_crono_carte.cod_regi := v_relacion_ua.cod_regi;
             reg_cob_crono_carte.cod_zona := v_relacion_ua.cod_zona;
             reg_cob_crono_carte.fec_inic_fact := lv_fec_ini_fac;
             reg_cob_crono_carte.fec_gene_cart := lv_fec_gen_cart;
             reg_cob_crono_carte.fec_inic_gest := lv_fec_ini_gest;
             reg_cob_crono_carte.fec_comp_pago := lv_fec_comp_pago;
             reg_cob_crono_carte.fec_cier := lv_fec_cierre;
             reg_cob_crono_carte.val_dias_plaz := lv_fec_cierre - lv_fec_ini_gest;
             reg_cob_crono_carte.usu_crea := p_cod_usu;
             reg_cob_crono_carte.usu_modi := p_cod_usu;
             reg_cob_crono_carte.fec_crea := SYSDATE;
             reg_cob_crono_carte.fec_modi := SYSDATE;
             reg_cob_crono_carte.ind_gene_cart := 0;
             reg_cob_crono_carte.ind_gene_cier := 0;

             INSERT INTO cob_crono_carte VALUES reg_cob_crono_carte;
             lv_num_reg_ins := lv_num_reg_ins + 1 ;
         end loop;

      lv_des_log:= 'Fin  cob_pr_gener_crono_xperi. Nro. reg. creados: ' || lv_num_reg_ins ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                          lv_id_proc_ejec, lv_des_log);

   END cob_pr_gener_crono_xperi;


   PROCEDURE cob_pr_gener_crono_xdias(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
      IS
          lv_oid_peri        cra_perio.oid_peri%TYPE;
          lv_fec_ini_fac     date;
          lv_fec_gen_cart    date;
          lv_fec_ini_gest    date;
          lv_fec_comp_pago   date;
          lv_fec_cierre      date;
          lv_fec_cierre_ant  date;
--          lv_dia_sec         number(2);
          lv_ind_dias_calen  COB_PAIS_SOCIE_PARAM.ind_dias_cale%type;
          lv_ind_dias_crono  COB_PAIS_SOCIE_PARAM.ind_dias_cron%type;
          lv_ind_calen_sec   COB_PAIS_SOCIE_PARAM.ind_cale_secu%type;
          lv_cod_etapa_ini   COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
          lv_val_edad_inic   cob_etapa_deuda_pais.val_edad_inic%type;
          lv_ind_dias_gracia cob_etapa_deuda_pais.ind_dias_grac%type;
          lv_num_dias_gracia cob_etapa_deuda_pais.num_dias_grac%type;
          lv_num_dias_grac_cier cob_etapa_deuda_pais.num_dias_grac_cier%type;
          lv_num_dias_gest   cob_etapa_deuda_pais.num_dias_gest%type;
          lv_num_dias_comp   cob_etapa_deuda_pais.num_dias_comp%type;
          lv_num_dias_cierr  cob_etapa_deuda_pais.num_dias_cier%type;
          lv_ind_ajus_cier   cob_etapa_deuda_pais.ind_ajus_cier%type;
          lv_num_dias_ajus_cier_15 cob_etapa_deuda_pais.num_dias_ajus_cier_15%type;
          lv_num_dias_ajus_cier_30 cob_etapa_deuda_pais.num_dias_ajus_cier_30%type;
          lv_ind_gene_lune   cob_etapa_deuda_pais.ind_gene_lune%type;

          lv_ind_excep       number(1);
          lv_fec_excep       date;
          lv_ind_aviso_sec_etapa  number(1) := 0;
          lv_num_reg_ins          NUMBER(9);

          reg_cob_crono_carte cob_crono_carte%ROWTYPE;

/*  Constantes para tipo de excepciones de fechas: */
         /* excepcion para fecha de cierre */
          lv_cier     cob_crono_carte_excep.tip_exce%type := 'CIE';
         /* excepcion para fecha de generacion de cartera */
          lv_gene     cob_crono_carte_excep.tip_exce%type := 'GEN';

          /*  determina la relacion de Unidades Administ. involucrtadas en la etapa
          CURSOR c_relacion_ua
             IS
                  SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_etapa_cobra_ua ua,
                        zon_regio reg,
                        ( select cod_zona, oid_zona from  zon_zona where ind_acti = 1) zon
             where ua.cod_pais = p_cod_pais
             and   ua.cod_soci = p_cod_socie
             and   ua.cod_etap_deud = p_cod_etap_deud
             and   ua.COD_ZONA = zon.COD_ZONA(+)
             and  UA.COD_REGI = reg.COD_REGI
             order by ua.cod_regi, ua.cod_zona;*/
             CURSOR c_relacion_ua
             IS
             SELECT *
             FROM
             ((SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_etapa_cobra_ua ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                  AND   ua.cod_soci = p_cod_socie
                  AND   ua.cod_etap_deud = p_cod_etap_deud
                  AND   ua.cod_zona = zon.cod_zona(+)
                  AND  ua.cod_regi = reg.cod_regi)
             UNION
                 (SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_param_asign_zonas_cobra ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                  AND   ua.cod_etap_deud = p_cod_etap_deud
                  AND   ua.cod_zona = zon.cod_zona(+)
                  AND  ua.cod_regi = reg.cod_regi))
             ORDER BY cod_regi, cod_zona;

      BEGIN

          lv_des_log:='Inicio  cob_pr_gener_crono_xdias    Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie ||
                      ' Cod Etapa: ' || p_cod_etap_deud || ' Cod Peri: ' || p_cod_peri ||
                      ' Cod etapa anter: ' || p_cod_etap_deud_ant || ' Cod usuar: ' || p_cod_usu;
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_des_log);

           -- obtiene oid de campa?a
           lv_oid_peri:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, p_cod_peri);
        --   dbms_output.put_line( 'LV_OID_peri : ' || LV_OID_peri  ) ;

           -- obtiene parametros para el pais
           SELECT  pais.ind_dias_cron, pais.ind_dias_cale,
                   pais.ind_cale_secu,  pais.cod_etap_inic
           INTO    lv_ind_dias_crono, lv_ind_dias_calen,
                   lv_ind_calen_sec,  lv_cod_etapa_ini
           FROM COB_PAIS_SOCIE_PARAM pais
           WHERE pais.cod_pais = p_cod_pais
           AND   pais.cod_soci = p_cod_socie;

           -- obtiene parametros para la etapa
           SELECT pais.val_edad_inic,
                  pais.ind_dias_grac, pais.num_dias_grac,
                  pais.num_dias_gest, pais.num_dias_comp, pais.num_dias_cier, pais.num_dias_grac_cier,
                  pais.ind_ajus_cier, pais.num_dias_ajus_cier_15,
                  pais.num_dias_ajus_cier_30, pais.ind_gene_lune
           INTO   lv_val_edad_inic,
                  lv_ind_dias_gracia, lv_num_dias_gracia,
                  lv_num_dias_gest, lv_num_dias_comp, lv_num_dias_cierr, lv_num_dias_grac_cier,
                  lv_ind_ajus_cier, lv_num_dias_ajus_cier_15,
                  lv_num_dias_ajus_cier_30, lv_ind_gene_lune
           FROM cob_etapa_deuda_pais pais
           where pais.cod_pais = p_cod_pais
           and   pais.cod_soci = p_cod_socie
           and   pais.cod_etap_deud = p_cod_etap_deud;

           if  lv_ind_dias_calen = lv_ind_dias_crono then
               lv_des_log := 'Error gen_crono_xdias parametros errados: indicador dias calen.';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                         lv_id_proc_ejec, lv_des_log);
               raise_application_error(-20010, lv_des_log);
               return;
           end if;

           -- si el indic dias de gracia esta desactivado,  se fuerza el numero
           -- de dias de gracia  a  cero.
           if  lv_ind_dias_gracia = 0 then
               lv_num_dias_gracia := 0;
           end if;

--           DBMS_OUTPUT.put_line('INICIA gen_crono_xdias: ' || p_cod_etap_deud );

         lv_num_reg_ins := 0;

         FOR v_relacion_ua IN c_relacion_ua LOOP
       /* Obtiene fecha de facturacion inicial de campa?a procesada (X) */
             lv_fec_ini_fac := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri,
                            v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);
/* Determina fecha de generacion de cartera */
             /* verifica si existe fecha de generacion excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_gene,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de generacion excepcional  */
                 lv_fec_gen_cart := lv_fec_excep;
             else

       /* calcula fecha de generacion de cartera en base a
            fecha de facturacion de campa?a procesada */
             lv_fec_gen_cart := lv_fec_ini_fac + lv_val_edad_inic + lv_num_dias_gracia;

                 -- verifica que no sea sabado ni domingo o  lunes segun parametria
                 lv_fec_gen_cart := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune);

/* Si no es etapa inicial,  verifica que la fecha de generacion calculada
   sea posterior a la fecha de cierre de la etapa anterior */
             if p_cod_etap_deud <> p_cod_etap_deud_ant then
                 lv_fec_cierre_ant := cob_fn_obtie_fecier_etapa (p_cod_pais, p_cod_socie,
                                      p_cod_etap_deud_ant, p_cod_peri, v_relacion_ua.cod_regi,
                                      v_relacion_ua.cod_zona);
                   if  lv_fec_cierre_ant >= lv_fec_gen_cart  then
                         if   lv_ind_aviso_sec_etapa = 0  then
                              lv_ind_aviso_sec_etapa := 1;
                              lv_des_log:= 'xdias, Ajusta fec gene' || lv_fec_gen_cart || ' Etapa: ' || p_cod_etap_deud || ' Region: ' || v_relacion_ua.cod_regi || ' Zona: ' || v_relacion_ua.cod_zona;
                       fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu,
                                               lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);
                       end if;
                         lv_fec_gen_cart  := lv_fec_cierre_ant + 1;
                         lv_fec_gen_cart  :=  cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune );
                     end if;
                   end if;
             end if;

             lv_fec_ini_gest  := lv_fec_gen_cart + lv_num_dias_gest;
             lv_fec_ini_gest := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_ini_gest, 1 );
             lv_fec_comp_pago := lv_fec_ini_gest + lv_num_dias_comp;
             /* verifica si existe fecha de cierre excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_cier,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de cierre excepcional  */
                 lv_fec_cierre := lv_fec_excep;
             else
             /* calcula fecha de cierre de manera regular segun parametros */
                 lv_fec_cierre    := lv_fec_ini_gest + lv_num_dias_cierr + lv_num_dias_grac_cier;
                 lv_fec_cierre    := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_cierre, 1 );
                 if  lv_ind_ajus_cier = 1 then
                     lv_fec_cierre  := cob_fn_ajust_fecha_a1530(lv_fec_cierre, lv_num_dias_ajus_cier_15, lv_num_dias_ajus_cier_30);
                 end if;
             end if;

--             dbms_output.put_line('  ');
--             DBMS_OUTPUT.put_line('Cod Regi: ' || v_relacion_ua.cod_regi || ' Cod Zona: ' ||  v_relacion_ua.cod_zona );
--             dbms_output.put_line('lv_fec_ini_fac: ' || lv_fec_ini_fac || ' lv_fec_gen_cart: ' || lv_fec_gen_cart ) ;
--             dbms_output.put_line('lv_fec_ini_gest: ' || lv_fec_ini_gest || ' lv_fec_comp_pago: ' || lv_fec_comp_pago || ' lv_fec_cierre: ' || lv_fec_cierre ) ;
--             dbms_output.put_line('fecha: ' || lv_fec_gen_cart || ' sec: ' || lv_dia_sec );

             reg_cob_crono_carte.cod_pais := p_cod_pais;
             reg_cob_crono_carte.cod_soci := p_cod_socie;
             reg_cob_crono_carte.cod_peri := p_cod_peri;
             reg_cob_crono_carte.cod_etap_deud := p_cod_etap_deud;
             reg_cob_crono_carte.cod_regi := v_relacion_ua.cod_regi;
             reg_cob_crono_carte.cod_zona := v_relacion_ua.cod_zona;
             reg_cob_crono_carte.fec_inic_fact := lv_fec_ini_fac;
             reg_cob_crono_carte.fec_gene_cart := lv_fec_gen_cart;
             reg_cob_crono_carte.fec_inic_gest := lv_fec_ini_gest;
             reg_cob_crono_carte.fec_comp_pago := lv_fec_comp_pago;
             reg_cob_crono_carte.fec_cier := lv_fec_cierre;
             reg_cob_crono_carte.val_dias_plaz := lv_fec_cierre - lv_fec_ini_gest;
             reg_cob_crono_carte.usu_crea := p_cod_usu;
             reg_cob_crono_carte.usu_modi := p_cod_usu;
             reg_cob_crono_carte.fec_crea := SYSDATE;
             reg_cob_crono_carte.fec_modi := SYSDATE;
             reg_cob_crono_carte.ind_gene_cart := 0;
             reg_cob_crono_carte.ind_gene_cier := 0;

             INSERT INTO cob_crono_carte VALUES reg_cob_crono_carte;
             lv_num_reg_ins := lv_num_reg_ins + 1;
         end loop;
         lv_des_log := 'Fin rutina  cob_pr_gener_crono_xdias. Nro. reg. creados: ' || lv_num_reg_ins;
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                    lv_id_proc_ejec, lv_des_log);
      END cob_pr_gener_crono_xdias;

   PROCEDURE cob_pr_gener_crono_xdias_secc2(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud_ant cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_usu           cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
      IS
          lv_oid_peri        cra_perio.oid_peri%TYPE;
          lv_existen_reg     number(11);
          lv_fec_ini_fac     date;
          lv_fec_gen_cart    date;
          lv_fec_ini_gest    date;
          lv_fec_comp_pago   date;
          lv_fec_cierre      date;
          lv_fec_cierre_ant  date;
          lv_cod_etap_ant    cob_etapa_deuda_pais.cod_etap_deud%type;
          lv_val_edad_inic   cob_etapa_deuda_pais.val_edad_inic%type;
          lv_ind_dias_gracia cob_etapa_deuda_pais.ind_dias_grac%type;
          lv_num_dias_gracia cob_etapa_deuda_pais.num_dias_grac%type;
          lv_num_dias_grac_cier cob_etapa_deuda_pais.num_dias_grac_cier%type;
          lv_num_dias_gest   cob_etapa_deuda_pais.num_dias_gest%type;
          lv_num_dias_comp   cob_etapa_deuda_pais.num_dias_comp%type;
          lv_num_dias_cierr  cob_etapa_deuda_pais.num_dias_cier%type;
          lv_ind_ajus_cier   cob_etapa_deuda_pais.ind_ajus_cier%type;
          lv_num_dias_ajus_cier_15 cob_etapa_deuda_pais.num_dias_ajus_cier_15%type;
          lv_num_dias_ajus_cier_30 cob_etapa_deuda_pais.num_dias_ajus_cier_30%type;
          lv_ind_gene_lune   cob_etapa_deuda_pais.ind_gene_lune%type;

          lv_ind_excep                              NUMBER(1);
          lv_fec_excep                              DATE;
          lv_ind_aviso_sec_etapa             NUMBER(1) := 0;
          lv_num_reg_ins                          NUMBER(9);
          lv_ind_asig_regi                         cob_param_gener.val_para%TYPE;
          reg_cob_crono_carte cob_crono_carte%ROWTYPE;

/*  Constantes para tipo de excepciones de fechas: */
         /* excepcion para fecha de cierre */
          lv_cier     cob_crono_carte_excep.tip_exce%type := 'CIE';
         /* excepcion para fecha de generacion de cartera */
          lv_gene     cob_crono_carte_excep.tip_exce%type := 'GEN';

          /*  determina la relacion de Unidades Administ. involucrtadas en la etapa
          CURSOR c_relacion_ua
             IS
                  SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_etapa_cobra_ua ua,
                        zon_regio reg,
                        ( select cod_zona, oid_zona from  zon_zona where ind_acti = 1) zon
             where ua.cod_pais = p_cod_pais
             and   ua.cod_soci = p_cod_socie
             and   ua.cod_etap_deud = p_cod_etap_deud
             and   ua.COD_ZONA = zon.COD_ZONA(+)
             and  UA.COD_REGI = reg.COD_REGI
             order by ua.cod_regi, ua.cod_zona;*/
           CURSOR c_relacion_ua
             IS
             SELECT *
             FROM
             ((SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_etapa_cobra_ua ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                  AND   ua.cod_soci = p_cod_socie
                  AND   ua.cod_etap_deud = p_cod_etap_deud
                  AND   ua.cod_zona = zon.cod_zona(+)
                  AND  ua.cod_regi = reg.cod_regi)
             UNION
                 (SELECT DISTINCT ua.cod_etap_deud, ua.cod_regi, reg.oid_regi,
                         ua.cod_zona, zon.oid_zona
                  FROM  cob_param_asign_zonas_cobra ua,
                        zon_regio reg,
                        ( SELECT cod_zona, oid_zona
                           FROM  zon_zona
                          WHERE ind_acti = 1) zon
                  WHERE ua.cod_pais = p_cod_pais
                  AND   ua.cod_etap_deud = p_cod_etap_deud
                  AND   ua.cod_zona = zon.cod_zona(+)
                  AND  ua.cod_regi = reg.cod_regi))
             ORDER BY cod_regi, cod_zona;


      BEGIN
          lv_ind_asig_regi := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IND_ASIG_REGI');
          lv_des_log:='Inicio  cob_pr_gener_crono_xdias_secc2  Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie ||
                      ' Cod Etapa: ' || p_cod_etap_deud || ' Cod Peri: ' || p_cod_peri ||
                      ' Cod etapa anter: ' || p_cod_etap_deud_ant || ' Cod usuar: ' || p_cod_usu;
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_des_log);


           -- obtiene oid de campa?a
           lv_oid_peri:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, p_cod_peri);

/*  GENERA CRONOGRAMA
    Para etapas que se concatenan a la fecha de cierre de la Etapa  Anterior */

           -- determina etapa anterior
           select etp.cod_etap_deud
           into   lv_cod_etap_ant
           from cob_etapa_deuda_pais etp
           where etp.val_edad_inic = ( select max(et1.val_edad_inic)
                                       from cob_etapa_deuda_pais et1
                                       where  et1.cod_pais = p_cod_pais
                                       and    et1.cod_soci = p_cod_socie
                                       and    et1.val_edad_inic < (select et2.val_edad_inic
                                                                   from   cob_etapa_deuda_pais et2
                                                                   where  et2.cod_pais = p_cod_pais
                                                                   and    et2.cod_soci = p_cod_socie
                                                                   and    et2.cod_etap_deud = p_cod_etap_deud));


           -- obtiene parametros para la etapa
           SELECT pais.val_edad_inic,
                  pais.ind_dias_grac, pais.num_dias_grac,
                  pais.num_dias_gest, pais.num_dias_comp, pais.num_dias_cier, pais.num_dias_grac_cier,
                  pais.ind_ajus_cier, pais.num_dias_ajus_cier_15,
                  pais.num_dias_ajus_cier_30, pais.ind_gene_lune
           INTO   lv_val_edad_inic,
                  lv_ind_dias_gracia, lv_num_dias_gracia,
                  lv_num_dias_gest, lv_num_dias_comp, lv_num_dias_cierr, lv_num_dias_grac_cier,
                  lv_ind_ajus_cier, lv_num_dias_ajus_cier_15,
                  lv_num_dias_ajus_cier_30, lv_ind_gene_lune
           FROM cob_etapa_deuda_pais pais
           where pais.cod_pais = p_cod_pais
           and   pais.cod_soci = p_cod_socie
           and   pais.cod_etap_deud = p_cod_etap_deud;


           -- si el indic dias de gracia esta desactivado,  se fuerza el numero
           -- de dias de gracia  a  cero.
           if  lv_ind_dias_gracia = 0 then
               lv_num_dias_gracia := 0;
           end if;
--           DBMS_OUTPUT.put_line('inicia gen_crono_xdias_sec2: ' || p_cod_etap_deud );

         lv_num_reg_ins := 0;

         FOR v_relacion_ua IN c_relacion_ua LOOP
             /* Obtiene fecha de facturacion inicial de campa?a procesada (X) */
             lv_fec_ini_fac := cob_pkg_proce.cob_fn_obtie_fecha_inici_factu(lv_oid_peri,
                                  v_relacion_ua.cod_regi, v_relacion_ua.cod_zona);


/* Determina fecha de generacion de cartera */
             /* verifica si existe fecha de generacion excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_gene,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de generacion excepcional  */
                 lv_fec_gen_cart := lv_fec_excep;
             else
             /* obtiene fecha de cierre para la region zona en la etapa anterior */
             lv_fec_cierre_ant := null;
             lv_existen_reg := 0;
             if  v_relacion_ua.cod_zona is not NULL
               AND v_relacion_ua.cod_zona <> lv_ind_asig_regi THEN
             --AND v_relacion_ua.cod_zona <> '0000' then
--                     DBMS_OUTPUT.put_line('Por Zona especifica: ' || v_relacion_ua.cod_zona );
                 select count(*)
                 into   lv_existen_reg
                 from cob_crono_carte cro
                 where  cro.cod_pais = p_cod_pais
                 and    cro.cod_soci = p_cod_socie
                 and    cro.cod_peri = p_cod_peri
                 and    cro.cod_etap_deud = lv_cod_etap_ant
                 and    cro.cod_regi = v_relacion_ua.cod_regi
                 and    cro.cod_zona = v_relacion_ua.cod_zona;

                 if lv_existen_reg > 0 then
                     select cro.fec_cier
                     into   lv_fec_cierre_ant
                     from cob_crono_carte cro
                     where  cro.cod_pais = p_cod_pais
                     and    cro.cod_soci = p_cod_socie
                     and    cro.cod_peri = p_cod_peri
                     and    cro.cod_etap_deud = lv_cod_etap_ant
                     and    cro.cod_regi = v_relacion_ua.cod_regi
                     and    cro.cod_zona = v_relacion_ua.cod_zona;
--                         DBMS_OUTPUT.put_line('Fecha por Zona especifica: ' || lv_fec_cierre_ant );
                 end if;
             else
--                     DBMS_OUTPUT.put_line('Solo por Region: ' || v_relacion_ua.cod_regi );
                 select count(*)
                 into   lv_existen_reg
                 from cob_crono_carte cro
                 where  cro.cod_pais = p_cod_pais
                 and    cro.cod_soci = p_cod_socie
                 and    cro.cod_peri = p_cod_peri
                 and    cro.cod_etap_deud = lv_cod_etap_ant
                 and    cro.cod_regi = v_relacion_ua.cod_regi
                 --and    cro.cod_zona = '0000'; ---   is null;
                 and    cro.cod_zona = lv_ind_asig_regi;

                 if  lv_existen_reg > 0 then
                     select cro.fec_cier
                     into   lv_fec_cierre_ant
                     from cob_crono_carte cro
                     where  cro.cod_pais = p_cod_pais
                     and    cro.cod_soci = p_cod_socie
                     and    cro.cod_peri = p_cod_peri
                     and    cro.cod_etap_deud = lv_cod_etap_ant
                     and    cro.cod_regi = v_relacion_ua.cod_regi
                     --and    cro.cod_zona = '0000';  ---  is null;
                     and    cro.cod_zona = lv_ind_asig_regi;
--                     DBMS_OUTPUT.put_line('Fecha solo por Region: ' || lv_fec_cierre_ant );
                 end if;
             end if;
             if  lv_fec_cierre_ant is null then
                 select max(cro.fec_cier)
                 into   lv_fec_cierre_ant
                 from cob_crono_carte cro
                 where  cro.cod_pais = p_cod_pais
                 and    cro.cod_soci = p_cod_socie
                 and    cro.cod_peri = p_cod_peri
                 and    cro.cod_etap_deud = lv_cod_etap_ant
                 and    cro.cod_regi = v_relacion_ua.cod_regi;
--                     DBMS_OUTPUT.put_line('Si Fecha quedo null ...: ' || lv_fec_cierre_ant );
             end if;
             if  lv_fec_cierre_ant is null THEN
                     lv_des_log := 'Error no se encontro fec cierre etapa anterior: ' || lv_cod_etap_ant || ' Cod Reg: ' ||  v_relacion_ua.cod_regi;
                     fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                                lv_id_proc_ejec, lv_des_log);
                     raise_application_error(-20010, lv_des_log);
                     return;
             end if;
       /* calcula fecha de generacion de cartera en base a
               fecha de cierre de la etapa anterior  */
             lv_fec_gen_cart := lv_fec_cierre_ant + 1 + lv_num_dias_gracia;
--                 DBMS_OUTPUT.put_line('Fecha gen preliminar (con dias de gracia): ' || lv_fec_gen_cart );
             -- verifica que no sea sabado ni domingo
                 lv_fec_gen_cart := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune);
--                 DBMS_OUTPUT.put_line('Fecha gen util: ' || lv_fec_gen_cart );
/* Si no es etapa inicial,  verifica que la fecha de generacion calculada
   sea posterior a la fecha de cierre de la etapa anterior */
             if p_cod_etap_deud <> p_cod_etap_deud_ant then
--                    DBMS_OUTPUT.put_line('parametros funcion: pais: ' || p_cod_pais || ' socie: '||
--                                                     p_cod_socie || ' etapa anter: ' || p_cod_etap_deud_ant ||
--                                                     ' periodo: ' || p_cod_peri || ' region: ' || v_relacion_ua.cod_regi ||
--                                                     ' zona: ' || v_relacion_ua.cod_zona );
                 lv_fec_cierre_ant := cob_fn_obtie_fecier_etapa (p_cod_pais, p_cod_socie,
                                      p_cod_etap_deud_ant, p_cod_peri, v_relacion_ua.cod_regi,
                                      v_relacion_ua.cod_zona);
--                     DBMS_OUTPUT.put_line('Fecha ant  segun funcion: ' || lv_fec_cierre_ant );
                   if  lv_fec_cierre_ant >= lv_fec_gen_cart  then

                       if  lv_ind_aviso_sec_etapa = 0 then
                           lv_ind_aviso_sec_etapa := 1;
                           lv_des_log:= 'secc2, Ajusta fec gene' || lv_fec_gen_cart || ' Etapa: ' || p_cod_etap_deud || ' Region: ' || v_relacion_ua.cod_regi || ' Zona: ' || v_relacion_ua.cod_zona;
                           fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu,
                                                   lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);
                       end if;
                       lv_fec_gen_cart  := lv_fec_cierre_ant + 1;
--      dbms_output.put_line( '2  secc2 invoca rutina dia util   fecha gen: ' || lv_fec_gen_cart  || ' indic lunes: ' || lv_ind_gene_lune|| ' region:  ' || v_relacion_ua.cod_regi) ;
                           lv_fec_gen_cart  := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_gen_cart, lv_ind_gene_lune);
--                           DBMS_OUTPUT.put_line('Fecha gen ajustada segun funcion: ' || lv_fec_gen_cart );
                       end if;
                   end if;
             end if;

             -- calcula las siguientes fechas segun parametros
             lv_fec_ini_gest  := lv_fec_gen_cart + lv_num_dias_gest;
             lv_fec_ini_gest := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_ini_gest, 1 );
             lv_fec_comp_pago := lv_fec_ini_gest + lv_num_dias_comp;
             /* verifica si existe fecha de cierre excepcional  definida por usuario */
             cob_pkg_proce.cob_pr_obtie_excep (p_cod_pais, p_cod_socie, p_cod_peri,
                                             p_cod_etap_deud,  v_relacion_ua.cod_regi,
                                             v_relacion_ua.cod_zona, lv_cier,
                                             lv_ind_excep, lv_fec_excep);
             if  lv_ind_excep = 1 then
             /* se utiliza  fecha de cierre excepcional  */
                 lv_fec_cierre := lv_fec_excep;
             else
             /* calcula fecha de cierre de manera regular segun parametros */
                 lv_fec_cierre    := lv_fec_ini_gest + lv_num_dias_cierr + lv_num_dias_grac_cier;
                 lv_fec_cierre    := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fec_cierre, 1 );
                 if  lv_ind_ajus_cier = 1 then
                     lv_fec_cierre  := cob_fn_ajust_fecha_a1530(lv_fec_cierre, lv_num_dias_ajus_cier_15, lv_num_dias_ajus_cier_30);
                 end if;
             end if;

             reg_cob_crono_carte.cod_pais := p_cod_pais;
             reg_cob_crono_carte.cod_soci := p_cod_socie;
             reg_cob_crono_carte.cod_peri := p_cod_peri;
             reg_cob_crono_carte.cod_etap_deud := p_cod_etap_deud;
             reg_cob_crono_carte.cod_regi := v_relacion_ua.cod_regi;
             reg_cob_crono_carte.cod_zona := v_relacion_ua.cod_zona;
             reg_cob_crono_carte.fec_inic_fact := lv_fec_ini_fac;
             reg_cob_crono_carte.fec_gene_cart := lv_fec_gen_cart;
             reg_cob_crono_carte.fec_inic_gest := lv_fec_ini_gest;
             reg_cob_crono_carte.fec_comp_pago := lv_fec_comp_pago;
             reg_cob_crono_carte.fec_cier := lv_fec_cierre;
             reg_cob_crono_carte.val_dias_plaz := lv_fec_cierre - lv_fec_ini_gest;
             reg_cob_crono_carte.usu_crea := p_cod_usu;
             reg_cob_crono_carte.usu_modi := p_cod_usu;
             reg_cob_crono_carte.fec_crea := SYSDATE;
             reg_cob_crono_carte.fec_modi := SYSDATE;
             reg_cob_crono_carte.ind_gene_cart := 0;
             reg_cob_crono_carte.ind_gene_cier := 0;

             INSERT INTO cob_crono_carte VALUES reg_cob_crono_carte;
             lv_num_reg_ins := lv_num_reg_ins + 1;
         END LOOP;
         lv_des_log:= 'Fin rutina   cob_pr_gener_crono_xdias_secc2 Nro. reg. creados: ' || lv_num_reg_ins;

         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                    lv_id_proc_ejec, lv_des_log);
      END cob_pr_gener_crono_xdias_secc2;

     PROCEDURE cob_pr_obtie_excep(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi  zon_regio.cod_regi%TYPE,
      p_cod_zona   zon_zona.cod_zona%TYPE,
      p_tip_exce  cob_crono_carte_excep.tip_exce%type,
      p_ind_exep OUT NUMBER,
      p_fec_exce OUT date)
   IS

     /* Busca excepciones al cronograma */
     lv_existe_reg    NUMBER(11);
     lv_fec_exce     DATE;
   BEGIN
        p_ind_exep := 0;
        p_fec_exce := NULL;

        SELECT COUNT(*)
        INTO lv_existe_reg
        FROM cob_crono_carte_excep ex
        WHERE ex.cod_pais = p_cod_pais
        AND   ex.cod_soci = p_cod_socie
        AND   ex.cod_peri = p_cod_peri
        AND   ex.cod_etap_deud = p_cod_etap_deud
        AND   ex.cod_regi = p_cod_regi
        AND   ex.cod_zona = p_cod_zona
        AND   ex.tip_exce = p_tip_exce;

        IF lv_existe_reg > 0 THEN
            SELECT ex.fec_exce
            INTO lv_fec_exce
            FROM cob_crono_carte_excep ex
            WHERE ex.cod_pais = p_cod_pais
            AND   ex.cod_soci = p_cod_socie
            AND   ex.cod_peri = p_cod_peri
            AND   ex.cod_etap_deud = p_cod_etap_deud
            AND   ex.cod_regi = p_cod_regi
            AND   ex.cod_zona = p_cod_zona
            AND   ex.tip_exce = p_tip_exce ;

            p_fec_exce := lv_fec_exce;
            p_ind_exep  := 1;
        END IF;

   END cob_pr_obtie_excep;


 PROCEDURE COB_PR_VALID_REGIS_FECHA_EXCEP (
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi  zon_regio.cod_regi%type,
      p_cod_zona   zon_zona.cod_zona%type,
      p_cod_usuario cob_detal_asign_carte.usu_crea%TYPE,
      p_tip_exce      cob_crono_carte_excep.tip_exce%type,
      p_str_fec_exce      VARCHAR2)
 IS
   /* Valida y registra fecha de cierre de finida por el usuario */

      lv_fecha_exce     DATE;
      lv_existe_reg     number(11);
      lv_fecha_util     date;
      lv_fecha_orig     date;
      lv_flg_error      number(1);
      lv_msg_error      varchar2(80);

      lv_ind_dias_calen  COB_PAIS_SOCIE_PARAM.ind_dias_cale%type;
      lv_ind_dias_crono  COB_PAIS_SOCIE_PARAM.ind_dias_cron%type;
      lv_ind_calen_sec   COB_PAIS_SOCIE_PARAM.ind_cale_secu%type;
      lv_cod_etapa_ini   COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
      lv_secu_etap       COB_ETAPA_DEUDA_PAIS.Num_Secu_Etap%type;
      lv_secu_etap_ante  COB_ETAPA_DEUDA_PAIS.Num_Secu_Etap%type;
      lv_cod_etapa_ante  COB_PAIS_SOCIE_PARAM.cod_etap_inic%type;
      lv_ind_gene_cart   cob_crono_carte.ind_gene_cart%type;
      lv_ind_gene_cier   cob_crono_carte.ind_gene_cier%type;
      lv_fec_cier_ante   date;

      lv_ind_gene_lune   cob_etapa_deuda_pais.ind_gene_lune%type;

/*  Constantes para tipo de excepciones de fechas: */
         /* excepcion para fecha de cierre */
          lv_cier     cob_crono_carte_excep.tip_exce%type := 'CIE';
         /* excepcion para fecha de generacion de cartera */
          lv_gene     cob_crono_carte_excep.tip_exce%type := 'GEN';

 
 lv_ind_gene_crono              VARCHAR2(2);
 
 BEGIN
     
  lv_ind_gene_crono := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('TipoGeneracionCronograma');
   
  IF lv_ind_gene_crono IS NULL THEN
   
      lv_cod_proc_log := lv_cod_proc_cron_exce;
      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, p_cod_usuario , lv_id_proc_ejec);
--      DBMS_OUTPUT.put_line('Val fecha excep:  cod proc: ' || lv_cod_proc_log || ' nro proceso: '  || lv_id_proc_ejec  );
      lv_des_log:='Inicio  cob_pr_valid_regis_fecha_excep    Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie ||
                  ' Cod Etapa: ' || p_cod_etap_deud || ' Cod Peri: ' || p_cod_peri ||
                  ' Cod regi: ' || p_cod_regi || ' Cod zona: ' || p_cod_zona || ' Usuario: ' || p_cod_usuario ||
                  ' Tipo Excep: ' || p_tip_exce || ' Fecha excep: ' || p_str_fec_exce;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                 lv_id_proc_ejec, lv_des_log);
--       DBMS_OUTPUT.put_line('Val fecha excep:  cod proc: ' || lv_cod_proc_log || ' nro proceso: '  || lv_id_proc_ejec || ' mensaje: ' || lv_des_log );
      lv_flg_error := 0;

--      convierte fecha de excepcion que se recibe en variable tipo varchar
--      a variable de tipo date
      lv_fecha_exce := to_date(p_str_fec_exce, 'DD/MM/YYYY');

      select cro.ind_gene_cart, cro.ind_gene_cier
      into   lv_ind_gene_cart, lv_ind_gene_cier
      from cob_crono_carte cro
      where     cro.cod_pais      = p_cod_pais
      and       cro.cod_soci      = p_cod_socie
      and       cro.cod_peri      = p_cod_peri
      and       cro.cod_etap_deud = p_cod_etap_deud
      and       cro.cod_regi      = p_cod_regi
      and       cro.cod_zona      = p_cod_zona;

      if  lv_ind_gene_cart = 1 and p_tip_exce = lv_gene then
         lv_flg_error := 1;
         lv_msg_error := 'Ya se asigno cartera,  no es posible modificar fecha Asignacion';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_msg_error);
         raise_application_error(-20003, lv_msg_error);
      end if;

      if  lv_ind_gene_cier = 1 and p_tip_exce = lv_cier then
         lv_flg_error := 1;
         lv_msg_error := 'Ya se liquido cartera,  no es posible modificar fecha Cierre';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_msg_error);
         raise_application_error(-20004, lv_msg_error);
      end if;

   /*  se fuerza  indicador  permitior generar cartera en lunes */
      lv_ind_gene_lune := 1;
      lv_fecha_util := cob_pkg_proce.cob_fn_obtie_fecha_util(lv_fecha_exce, lv_ind_gene_lune);
      if lv_fecha_exce <> lv_fecha_util then
         lv_flg_error := 1;
--         DBMS_OUTPUT.put_line('Error fecha no es valida' );
         lv_msg_error := 'Fecha no es valida,  debe ser dia util';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_msg_error);
         raise_application_error(-20005, lv_msg_error);
      end if;
      select count(*)
      into lv_existe_reg
      from cob_crono_carte_excep ex
      where ex.cod_pais = p_cod_pais
      and   ex.cod_soci = p_cod_socie
      and   ex.cod_peri = p_cod_peri
      and   ex.cod_etap_deud = p_cod_etap_deud
      and   ex.cod_regi = p_cod_regi
      and   ex.cod_zona = p_cod_zona
      and   ex.tip_exce = p_tip_exce;

      if  lv_existe_reg > 0 then
          select ex.fec_exce
          into lv_fecha_orig
          from cob_crono_carte_excep ex
          where ex.cod_pais = p_cod_pais
          and   ex.cod_soci = p_cod_socie
          and   ex.cod_peri = p_cod_peri
          and   ex.cod_etap_deud = p_cod_etap_deud
          and   ex.cod_regi = p_cod_regi
          and   ex.cod_zona = p_cod_zona
          and   ex.tip_exce = p_tip_exce;
          if  lv_fecha_orig = lv_fecha_exce then
              lv_flg_error := 1;
              DBMS_OUTPUT.put_line('Fecha ya registrada' );
              lv_msg_error := 'Fecha ya registrada';
              fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                         lv_id_proc_ejec, lv_msg_error);
              raise_application_error(-20006, lv_msg_error);
--              return;
          end if;
      end if;

/* valida nueva fecha segun parametros y cronograma */

      if  p_tip_exce = lv_gene then
      /*  si lo que se esta modificando es fecha de generacion de cartera */
      /*  verifcar vs.  fecha de cierre de etapa anterior */
           -- obtiene parametros para el pais
          SELECT  pais.ind_dias_cron, pais.ind_dias_cale,
                  pais.ind_cale_secu,  pais.cod_etap_inic
          INTO    lv_ind_dias_crono, lv_ind_dias_calen,
                  lv_ind_calen_sec,  lv_cod_etapa_ini
          FROM COB_PAIS_SOCIE_PARAM pais
          where pais.cod_pais = p_cod_pais
          and   pais.cod_soci = p_cod_socie;

          if  p_cod_etap_deud <> lv_cod_etapa_ini then
              select etp.num_secu_etap
              into   lv_secu_etap
              from  cob_etapa_deuda_pais etp
              where   etp.cod_pais      = p_cod_pais
              and     etp.cod_soci      = p_cod_socie
              and     etp.cod_etap_deud = p_cod_etap_deud;

              lv_secu_etap_ante := lv_secu_etap - 1;
              select etp.cod_etap_deud
              into   lv_cod_etapa_ante
              from  cob_etapa_deuda_pais etp
              where   etp.cod_pais      = p_cod_pais
              and     etp.cod_soci      = p_cod_socie
              and     etp.num_secu_etap = lv_secu_etap_ante;

              BEGIN
              SELECT cro.fec_cier
              INTO   lv_fec_cier_ante
              FROM cob_crono_carte cro
              WHERE     cro.cod_pais      = p_cod_pais
              AND       cro.cod_soci      = p_cod_socie
              AND       cro.cod_peri      = p_cod_peri
              AND       cro.cod_etap_deud = lv_cod_etapa_ante
              AND       cro.cod_regi      = p_cod_regi
              AND       cro.cod_zona      = p_cod_zona;
                EXCEPTION
                WHEN no_data_found THEN
                      SELECT max(cro.fec_cier)
                      INTO   lv_fec_cier_ante
                      FROM cob_crono_carte cro
                      WHERE     cro.cod_pais      = p_cod_pais
                      AND       cro.cod_soci      = p_cod_socie
                      AND       cro.cod_peri      = p_cod_peri
                      AND       cro.cod_etap_deud = lv_cod_etapa_ante
                      AND       cro.cod_regi      = p_cod_regi;
              END;

              IF  lv_fec_cier_ante >= lv_fecha_exce then
                  lv_flg_error := 1;
                  lv_msg_error := 'Fecha debe ser posterior a fecha de cierre de etapa anterior';
                  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                             lv_id_proc_ejec, lv_msg_error);
                  raise_application_error(-20007, lv_msg_error);
              END IF;
          END IF;
      end if;

      if lv_flg_error = 0 then
          if  lv_existe_reg > 0 then
              update cob_crono_carte_excep ex
                  set  ex.fec_exce = lv_fecha_exce,
               ex.usu_modi = p_cod_usuario,
                   ex.fec_modi = sysdate
              where ex.cod_pais = p_cod_pais
              and   ex.cod_soci = p_cod_socie
              and   ex.cod_peri = p_cod_peri
              and   ex.cod_etap_deud = p_cod_etap_deud
              and   ex.cod_regi = p_cod_regi
              and   ex.cod_zona = p_cod_zona
              and   ex.tip_exce = p_tip_exce;
         else
              insert into cob_crono_carte_excep ex
            (cod_pais, cod_soci, cod_peri, cod_etap_deud, cod_regi,
             cod_zona, usu_crea, fec_crea, usu_modi,
                     fec_modi, tip_exce, fec_exce)
               values(p_cod_pais, p_cod_socie, p_cod_peri, p_cod_etap_deud,
                      p_cod_regi, p_cod_zona, p_cod_usuario, sysdate,
                          p_cod_usuario, sysdate, p_tip_exce, lv_fecha_exce);
         end if;

         cob_pkg_proce.cob_pr_refre_crono (p_cod_pais, p_cod_socie, p_cod_peri, p_cod_usuario);

         lv_des_log:='Fin llamada  rutina  cob_pr_refre_crono';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_des_log);

         IF p_cod_etap_deud = 'VEN'
         AND p_tip_exce = lv_cier THEN
             --dbms_output.put_line('invoca ventas  year: ' || extract(YEAR FROM lv_fecha_exce ));
             lv_des_log:='Invoca cob_pkg_repor_estad.COB_PR_CRONO_VENTA_FECHA_EXCEP';
             fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                        lv_id_proc_ejec, lv_des_log);
             cob_pkg_repor_estad.COB_PR_CRONO_VENTA_FECHA_EXCEP(p_cod_pais, p_cod_socie, p_cod_peri, p_cod_regi,
                                                                p_cod_zona, p_cod_usuario, to_char(lv_fecha_exce, 'DD/MM/YYYY') );
         END IF;

        IF p_tip_exce = lv_cier THEN
            lv_cier := '';
        END IF;

         lv_des_log:='Fin rutina  cob_pr_valid_regis_fecha_excep';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                     lv_id_proc_ejec, lv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, 2);
      else
         raise_application_error(-20008, lv_msg_error );
      end if;
  
  ELSE
    
   COB_PR_ACTUA_CRONO_CARTE(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_tip_exce,TO_DATE(p_str_fec_exce,'DD/MM/YYYY'),p_cod_usuario);
     
  END IF;
      
 END COB_PR_VALID_REGIS_FECHA_EXCEP;

   FUNCTION cob_fn_obtie_fecha_inici_factu(
      p_oid_peri  cra_crono.perd_oid_peri%TYPE,
      p_cod_regi  zon_regio.cod_regi%type,
      p_cod_zona   zon_zona.cod_zona%type )
   RETURN date
   IS
         lv_fec_ini_fac                     DATE;
         lv_ind_asig_regi                  cob_param_gener.val_para%TYPE ;

   BEGIN
      lv_ind_asig_regi    := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IND_ASIG_REGI');
      --IF   p_cod_zona = '0000' THEN --- is null then
      IF p_cod_zona = lv_ind_asig_regi THEN
         /* Obtiene fecha de facturacion inicial de campa?a X+n */
         SELECT MAX(cro.fec_inic)
         INTO  lv_fec_ini_fac
         FROM
            cra_crono cro,
            cra_activ act,
            zon_regio reg,
            zon_zona zon
         WHERE cro.perd_oid_peri = p_oid_peri
              AND   cro.cact_oid_acti = act.oid_acti
              AND   act.cod_acti = 'FA'
              AND   zon.oid_zona = cro.zzon_oid_zona
              AND   reg.oid_regi = zon.zorg_oid_regi
              AND   zon.ind_acti = 1
              AND   reg.cod_regi = p_cod_regi;
      ELSE

         SELECT MAX(cro.fec_inic)
         INTO   lv_fec_ini_fac
         FROM
            cra_crono cro,
            cra_activ act,
            zon_zona zon
         WHERE  cro.perd_oid_peri = p_oid_peri
              AND    cro.cact_oid_acti = act.oid_acti
              AND    act.cod_acti = 'FA'
              AND    cro.zzon_oid_zona = zon.oid_zona
             -- AND    zon.ind_acti = 1
              AND    zon.cod_zona = p_cod_zona;
             END IF;
             RETURN lv_fec_ini_fac;

   END cob_fn_obtie_fecha_inici_factu;

   FUNCTION cob_fn_obtie_fecha_util(
      p_fecha date,
      p_acep_lune number )
   RETURN date
   IS
     lv_sec_dia      number(2);
     lv_feriado      number(3);
     lv_flag_salir   number(1);
--     lv_fecha_x      date;
     lv_fecha_util   date;
         BEGIN
--           dbms_output.put_line( 'Inicio  fecha util Fec Orig : ' || p_fecha || ' indic lunes: ' || p_acep_lune) ;
           lv_fecha_util := p_fecha;
           lv_flag_salir := 1;
           loop
                lv_sec_dia := to_char(lv_fecha_util, 'd');
                case lv_sec_dia
                     when 7 then
                                begin
                                 lv_fecha_util := lv_fecha_util + 2;
                                 lv_flag_salir := 0;
                                end;
                     when 1 then
                                begin
                                 lv_fecha_util := lv_fecha_util + 1;
                                 lv_flag_salir := 0;
                                end;
                     when 2 then
                          begin
                            if p_acep_lune = 1 then
                               lv_fecha_util := lv_fecha_util;
                            else
                               lv_fecha_util := lv_fecha_util + 1;
                               lv_flag_salir := 0;
                            end if;
                          end;
                     else   lv_fecha_util := lv_fecha_util;
                end case;
                select count(*)
                into lv_feriado
                from cob_feria fe
                where fe.fec_feri = lv_fecha_util;
                if  lv_feriado > 0 then
                    lv_fecha_util := lv_fecha_util + 1;
                    lv_flag_salir := 0;
                else
                    if lv_flag_salir = 0 then
                       lv_flag_salir := 1;
                else
                    exit;
                end if;
                end if;
             end loop;
--             dbms_output.put_line( 'Fin fecha util  Fec Orig : ' || p_fecha || ' Fecha util: ' || lv_fecha_util) ;
           return lv_fecha_util;
   end cob_fn_obtie_fecha_util;


   FUNCTION cob_fn_obtie_etapa_anter(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etapa_ref  cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_sec_etapa_ref  cob_etapa_deuda_pais.num_secu_etap%type )
   RETURN varchar2
   is
      /*  Dada una etapa de referencia,  obtiene el codigo de la etapa anterior */
     lv_cod_etapa_ant   cob_etapa_deuda_pais.cod_etap_deud%TYPE;
     lv_sec_etapa_ant   cob_etapa_deuda_pais.num_secu_etap%type;
     lv_sec_etapa_ref   cob_etapa_deuda_pais.num_secu_etap%type;
   begin

--    dbms_output.put_line( 'p_cod_pais: ' || p_cod_pais || ' Socie: ' || p_cod_socie || ' cod_etapa_ref: ' || p_cod_etapa_ref  || ' sec_etapa_ref: '  || p_sec_etapa_ref  ) ;

        if  p_sec_etapa_ref is null then
            select etp.num_secu_etap
            into lv_sec_etapa_ref
            from  cob_etapa_deuda_pais etp
            where etp.cod_pais = p_cod_pais
            and   etp.cod_soci = p_cod_socie
            and   etp.cod_etap_deud = p_cod_etapa_ref;
        else
            lv_sec_etapa_ref := p_sec_etapa_ref;
        end if;

        if  lv_sec_etapa_ref <= 1 then
            lv_cod_etapa_ant := p_cod_etapa_ref;
        else
            lv_sec_etapa_ant := lv_sec_etapa_ref - 1;
--        dbms_output.put_line( 'sec_etapa_ref: ' || lv_sec_etapa_ref || ' sec etapa ant: ' || lv_sec_etapa_ant ) ;
            select etp.cod_etap_deud
            into lv_cod_etapa_ant
            from  cob_etapa_deuda_pais etp
            where etp.cod_pais = p_cod_pais
            and   etp.cod_soci = p_cod_socie
            and   etp.num_secu_etap = lv_sec_etapa_ant;
        end if;
        return lv_cod_etapa_ant;
   end cob_fn_obtie_etapa_anter;


   FUNCTION cob_fn_obtie_fecier_etapa(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_socie seg_socie.cod_soci%TYPE,
      p_cod_etapa_ref  cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi   zon_regio.cod_regi%type,
      p_cod_zona   zon_zona.cod_zona%type )
   RETURN DATE
   IS

      /*  Obtiene la fecha de cierre  de una Etapa-Periodo-Region-Zona */
      lv_fec_cierr                           DATE;
      lv_existen_reg                       NUMBER(12);
      lv_ind_asig_regi                    cob_param_gener.val_para%TYPE;

   BEGIN
      lv_ind_asig_regi   :=COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IND_ASIG_REGI');
      /* obtiene fecha de cierre para la region zona de la etapa referida */
      lv_fec_cierr := null;
      lv_existen_reg := 0;

      IF  p_cod_zona IS NOT NULL
         --AND p_cod_zona <> '0000' THEN
         AND p_cod_zona <> lv_ind_asig_regi THEN

         -- DBMS_OUTPUT.put_line('Por Zona especifica: ' || p_cod_zona );
         SELECT COUNT(*)
         INTO   lv_existen_reg
         FROM  cob_crono_carte cro
         WHERE cro.cod_pais = p_cod_pais
              AND   cro.cod_soci = p_cod_socie
              AND   cro.cod_peri = p_cod_peri
              AND   cro.cod_etap_deud = p_cod_etapa_ref
              AND   cro.cod_regi = p_cod_regi
              AND   cro.cod_zona = p_cod_zona;

         IF lv_existen_reg > 0 THEN

            SELECT cro.fec_cier
            INTO lv_fec_cierr
            FROM  cob_crono_carte cro
            WHERE cro.cod_pais = p_cod_pais
                 AND cro.cod_soci = p_cod_socie
                 AND cro.cod_peri = p_cod_peri
                 AND cro.cod_etap_deud = p_cod_etapa_ref
                 AND cro.cod_regi = p_cod_regi
                 AND cro.cod_zona = p_cod_zona;
                 -- DBMS_OUTPUT.put_line('Fecha por Zona especifica: ' || lv_fec_cierr );
         END IF;

      ELSE
         --DBMS_OUTPUT.put_line('Solo por Region: ' || p_cod_regi );
         SELECT COUNT(*)
         INTO   lv_existen_reg
         FROM cob_crono_carte cro
         WHERE  cro.cod_pais = p_cod_pais
                AND    cro.cod_soci = p_cod_socie
                AND    cro.cod_peri = p_cod_peri
                AND    cro.cod_etap_deud = p_cod_etapa_ref
                AND    cro.cod_regi = p_cod_regi
                AND  cro.cod_zona =lv_ind_asig_regi;
                --AND    cro.cod_zona = '0000'; ---   is null;

         IF  lv_existen_reg > 0 THEN

            SELECT cro.fec_cier
            INTO   lv_fec_cierr
            FROM cob_crono_carte cro
            WHERE cro.cod_pais = p_cod_pais
                 AND cro.cod_soci = p_cod_socie
                 AND cro.cod_peri = p_cod_peri
                 AND cro.cod_etap_deud = p_cod_etapa_ref
                 AND cro.cod_regi = p_cod_regi
                 AND  cro.cod_zona =lv_ind_asig_regi;
                 --AND cro.cod_zona = '0000';  ---  is null;

             -- DBMS_OUTPUT.put_line('Fecha solo por Region: ' || lv_fec_cierr );
         END IF;

      END IF;

                 if  lv_fec_cierr is null then
                     select max(cro.fec_cier)
                     into   lv_fec_cierr
                     from cob_crono_carte cro
                     where  cro.cod_pais = p_cod_pais
                     and    cro.cod_soci = p_cod_socie
                     and    cro.cod_peri = p_cod_peri
                     and    cro.cod_etap_deud = p_cod_etapa_ref
                     and    cro.cod_regi = p_cod_regi;

--                     DBMS_OUTPUT.put_line('Si Fecha quedo null ...: ' || lv_fec_cierr );
                 end if;

                 if  lv_fec_cierr is null then
                     lv_des_log := 'cob_fn_obtie_fecier_etapa Error no se encontro fec cierre etapa: ' || p_cod_etapa_ref || ' Cod Reg: ' ||  p_cod_regi;
                     fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log,
                                                                lv_id_proc_ejec, lv_des_log);
                     raise_application_error(-20010, lv_des_log);
--                     return;
                 end if;

         return  lv_fec_cierr;

   end cob_fn_obtie_fecier_etapa;

  /*  ajusta fecha a quincen  o fin de mes segun parametria */
   FUNCTION cob_fn_ajust_fecha_a1530(
      p_fecha        date,
      p_num_dias_15  cob_etapa_deuda_pais.num_dias_ajus_cier_15%type,
      p_num_dias_30  cob_etapa_deuda_pais.num_dias_ajus_cier_30%type)
   RETURN date
   is
   lv_dia            number (2);
   lv_fecha_work     date;
   lv_fecha_fin_mes  date;
   lv_dia_fin_mes    number(2);
   lv_fecha_str      varchar2(10);
   begin

   lv_dia := EXTRACT(DAY FROM p_fecha);
   lv_fecha_work := p_fecha;
--   dbms_output.put_line('fecha WORK: '|| lv_fecha_worK || ' DIAS15: ' || p_num_dias_15 || ' lv_dias: ' || lv_dia);
   if  lv_dia <= 15 THEN
       if  15  -  lv_dia <= p_num_dias_15  THEN
           lv_fecha_str  := '15/' || extract(month from p_fecha) || '/' || extract(year from p_fecha);
           lv_fecha_work := to_date(lv_fecha_str, 'dd/mm/yyyy') ;
--           dbms_output.put_line('lv_fecha_work: '|| lv_fecha_work);
           lv_fecha_work := cob_fn_obtie_fecha_util(lv_fecha_work, 1);
       end if;
   else
       lv_fecha_fin_mes := last_day(p_fecha);
       lv_dia_fin_mes   := extract(day from lv_fecha_fin_mes);
       if  lv_dia_fin_mes - lv_dia <=  p_num_dias_30  then
           lv_fecha_work  :=  lv_fecha_fin_mes;
           lv_fecha_work := cob_fn_obtie_fecha_util(lv_fecha_work, 1);
       end if;
   end if;
--      dbms_output.put_line('fecha :' || p_fecha ||  'fecha ajustada: '|| lv_fecha_work);
   return lv_fecha_work;
   end  cob_fn_ajust_fecha_a1530;



   PROCEDURE cob_pr_refre_crono(
      p_cod_pais       seg_pais.cod_pais%TYPE,
      p_cod_socie      seg_socie.cod_soci%TYPE,
      p_cod_peri       seg_perio_corpo.cod_peri%TYPE,
      p_cod_usu        cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
   is

   /* refresca cronograma incorporando los cambios de fechas */
   /* que se hubieran realizado en el cronograma de operaciones */

      lv_fec_gene_new       date;
      lv_fec_cier_new       date;

      CURSOR c_crono_orig
         IS
         SELECT cro.*
         FROM  cob_crono_carte_orig cro
         WHERE cro.cod_pais = p_cod_pais
         AND   cro.cod_soci = p_cod_socie
         AND   cro.cod_peri = p_cod_peri
         AND   cro.ind_gene_cart = 1;

   BEGIN

      lv_des_log:='Inicio  cob_pr_refre_crono    Refresca Crono  Cod pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_socie || ' Cod peri: ' || p_cod_peri || ' Cod usu: ' || p_cod_usu  ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);


--        DBMS_OUTPUT.put_line('Elimina cronograma original' );
        DELETE cob_crono_carte_orig ori
        WHERE ori.cod_pais = p_cod_pais
        AND   ori.cod_soci = p_cod_socie
        AND   ori.cod_peri = p_cod_peri;

--        DBMS_OUTPUT.put_line('Recarga cronograma original' );
        INSERT INTO cob_crono_carte_orig(
        cod_pais, cod_soci, cod_peri, cod_etap_deud, cod_regi, cod_zona, fec_inic_fact, fec_gene_cart,
        fec_inic_gest, fec_comp_pago, fec_cier, val_dias_plaz, usu_crea, fec_crea, usu_modi, fec_modi, ind_gene_cart,
        ind_gene_cier, cod_cart)
        SELECT cod_pais, cod_soci, cod_peri, cod_etap_deud, cod_regi, cod_zona, fec_inic_fact, fec_gene_cart,
        fec_inic_gest, fec_comp_pago, fec_cier, val_dias_plaz, usu_crea, fec_crea, usu_modi, fec_modi, ind_gene_cart,
        ind_gene_cier, cod_cart
        FROM cob_crono_carte cro
        WHERE  cro.cod_pais = p_cod_pais
        AND   cro.cod_soci = p_cod_socie
        AND   cro.cod_peri = p_cod_peri;

--        DBMS_OUTPUT.put_line('Elimina cronograma ' );
        DELETE cob_crono_carte cro
        WHERE cro.cod_pais = p_cod_pais
        AND   cro.cod_soci = p_cod_socie
        AND   cro.cod_peri = p_cod_peri;

   --     DBMS_OUTPUT.put_line('Regenera cronograma ' );
        lv_des_log:='Regenera Crono:  Invoca cob_pr_crea_crono_carte ';
        fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);

        cob_pkg_proce.cob_pr_crea_crono_carte(p_cod_pais, p_cod_socie, p_cod_peri, p_cod_usu );
   --     DBMS_OUTPUT.put_line('RETORNO de Regenera cronograma ' );
        lv_des_log:='Regenera Crono:  Retorno de  cob_pr_crea_crono_carte ';
        fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);

   FOR  v_crono_orig in c_crono_orig LOOP
        /* determinar si cambio la fecha */
        select crox.fec_gene_cart, crox.fec_cier
        into   lv_fec_gene_new, lv_fec_cier_new
        from  cob_crono_carte crox
        where crox.cod_pais = p_cod_pais
        AND   crox.cod_soci = p_cod_socie
        AND   crox.cod_peri = p_cod_peri
        AND   crox.cod_etap_deud = v_crono_orig.cod_etap_deud
        AND   crox.cod_regi      = v_crono_orig.cod_regi
        AND   crox.cod_zona = v_crono_orig.cod_zona;

        if   v_crono_orig.ind_gene_cier =  1 then
--                  DBMS_OUTPUT.put_line('Actualiza solo cronograma (a los valores originales)' );
            UPDATE cob_crono_carte crox
            SET
              crox.fec_inic_fact = v_crono_orig.fec_inic_fact,
              crox.fec_gene_cart = v_crono_orig.fec_gene_cart,
              crox.fec_inic_gest = v_crono_orig.fec_inic_gest,
              crox.fec_comp_pago = v_crono_orig.fec_comp_pago,
              crox.fec_cier = v_crono_orig.fec_cier,
              crox.val_dias_plaz = v_crono_orig.val_dias_plaz,
              crox.usu_crea = v_crono_orig.usu_crea,
              crox.fec_crea = v_crono_orig.fec_crea,
              crox.usu_modi = v_crono_orig.usu_modi,
              crox.fec_modi = v_crono_orig.fec_modi,
              crox.ind_gene_cart = v_crono_orig.ind_gene_cart,
              crox.ind_gene_cier = v_crono_orig.ind_gene_cier,
              crox.cod_cart = v_crono_orig.cod_cart
            WHERE crox.cod_pais = p_cod_pais
            AND   crox.cod_soci = p_cod_socie
            AND   crox.cod_peri = p_cod_peri
            AND   crox.cod_etap_deud = v_crono_orig.cod_etap_deud
            AND   crox.cod_regi      = v_crono_orig.cod_regi
            AND   crox.cod_zona = v_crono_orig.cod_zona;
       else
            if   lv_fec_cier_new <> v_crono_orig.fec_cier then
              --    DBMS_OUTPUT.put_line('Actualiza cartera y luego cronograma, solo fecha cierre' );
                  update cob_cabec_asign_carte cab
                  set    cab.fec_cier      = lv_fec_cier_new
                  where  cab.cod_cart      = v_crono_orig.cod_cart
                  and    cab.cod_pais      = p_cod_pais
                  and    cab.cod_soci      = p_cod_socie
                  and    cab.cod_peri      = p_cod_peri
                  and    cab.cod_etap_deud = v_crono_orig.cod_etap_deud
                  and    cab.cod_regi      = v_crono_orig.cod_regi
                  and    cab.cod_zona      = v_crono_orig.cod_zona;

                  update cob_detal_asign_carte det
                  set    det.fec_cier      = lv_fec_cier_new
                  where  det.cod_cart      = v_crono_orig.cod_cart
                  and    det.cod_pais      = p_cod_pais
                  and    det.cod_soci      = p_cod_socie
                  and    det.cod_peri      = p_cod_peri
                  and    det.cod_etap_deud = v_crono_orig.cod_etap_deud
                  and    det.cod_regi      = v_crono_orig.cod_regi
                  and    det.cod_zona      = v_crono_orig.cod_zona;

                  UPDATE  cob_carte_movim_cuent_clien mov
                  SET mov.fec_cier = lv_fec_cier_new
                  WHERE mov.cod_cart = v_crono_orig.cod_cart;

                  UPDATE  cob_detal_movim_carte dm
                  SET dm.fec_cier = lv_fec_cier_new
                  WHERE dm.cod_cart = v_crono_orig.cod_cart;

                  UPDATE cob_crono_carte crox
                  SET
                      crox.fec_inic_fact = v_crono_orig.fec_inic_fact,
                      crox.fec_gene_cart = v_crono_orig.fec_gene_cart,
                      crox.fec_inic_gest = v_crono_orig.fec_inic_gest,
                      crox.fec_comp_pago = v_crono_orig.fec_comp_pago,
                 --     crox.fec_cier = v_crono_orig.fec_cier,
                      crox.val_dias_plaz = lv_fec_cier_new - v_crono_orig.fec_inic_gest,
                      crox.usu_crea = v_crono_orig.usu_crea,
                      crox.fec_crea = v_crono_orig.fec_crea,
                 --     crox.usu_modi = v_crono_orig.usu_modi,
                 --     crox.fec_modi = v_crono_orig.fec_modi,
                      crox.ind_gene_cart = v_crono_orig.ind_gene_cart,
                      crox.ind_gene_cier = v_crono_orig.ind_gene_cier,
                      crox.cod_cart = v_crono_orig.cod_cart
                  WHERE crox.cod_pais = p_cod_pais
                  AND   crox.cod_soci = p_cod_socie
                  AND   crox.cod_peri = p_cod_peri
                  AND   crox.cod_etap_deud = v_crono_orig.cod_etap_deud
                  AND   crox.cod_regi      = v_crono_orig.cod_regi
                  AND   crox.cod_zona = v_crono_orig.cod_zona;

            else
      --                  DBMS_OUTPUT.put_line('Actualiza solo cronograma' );
                  UPDATE cob_crono_carte crox
                  SET
                    crox.fec_inic_fact = v_crono_orig.fec_inic_fact,
                    crox.fec_gene_cart = v_crono_orig.fec_gene_cart,
                    crox.fec_inic_gest = v_crono_orig.fec_inic_gest,
                    crox.fec_comp_pago = v_crono_orig.fec_comp_pago,
                    crox.fec_cier = v_crono_orig.fec_cier,
                    crox.val_dias_plaz = v_crono_orig.val_dias_plaz,
                    crox.usu_crea = v_crono_orig.usu_crea,
                    crox.fec_crea = v_crono_orig.fec_crea,
                    crox.usu_modi = v_crono_orig.usu_modi,
                    crox.fec_modi = v_crono_orig.fec_modi,
                    crox.ind_gene_cart = v_crono_orig.ind_gene_cart,
                    crox.ind_gene_cier = v_crono_orig.ind_gene_cier,
                    crox.cod_cart = v_crono_orig.cod_cart
                  WHERE crox.cod_pais = p_cod_pais
                  AND   crox.cod_soci = p_cod_socie
                  AND   crox.cod_peri = p_cod_peri
                  AND   crox.cod_etap_deud = v_crono_orig.cod_etap_deud
                  AND   crox.cod_regi      = v_crono_orig.cod_regi
                  AND   crox.cod_zona = v_crono_orig.cod_zona;
            end if;
       end if;
   END LOOP;
   --DBMS_OUTPUT.put_line('fin loop actualiza cronograma datos de asignados ' );
   lv_des_log:='Fin rutina  cob_pr_refre_crono   Refresca Crono';
   fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais, p_cod_socie, lv_cod_modu, lv_cod_proc_log, lv_id_proc_ejec, lv_des_log);

   END cob_pr_refre_crono;


   /**************************************************************************
  Descripcion       : Proceso Principal de la Asignacion de la Cartera
                           este proceso inserta en la tabla COB_DETAL_ASIGN_CARTE la
                           cartera respectiva a los parametros ingresados.
                          Se realiza lo siguiente :
                              - Selecciona la cartera en base a los parametros ingresados
                              - Asigna la cartera a los usuarios cobradores
                              - Realiza el balanceo de la cartera

  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Etapa de Deuda,
            Codigo Periodo
            Codigo Region,
            Codigo Zona ,
            Codigo Usuario
  ***************************************************************************/
   PROCEDURE COB_PR_ASIGN_CARTE(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_fec_asign VARCHAR2,
      p_cod_usua cob_detal_asign_carte.usu_crea%TYPE)
   IS
   CURSOR c_cart(
      p_cod_pais              seg_pais.cod_pais%TYPE,
      p_cod_soci              seg_socie.cod_soci%TYPE,
      p_cod_etap_deud    cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri              seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi              zon_regio.cod_regi%TYPE,
      p_cod_zona             zon_zona.cod_zona%TYPE,
      p_fec_asign             VARCHAR2)
   IS
      SELECT c.cod_etap_deud, c.cod_peri,c.cod_regi , c.cod_zona
      FROM cob_crono_Carte c
      WHERE c.cod_pais = p_cod_pais
      AND c.cod_soci = p_cod_soci
      AND c.cod_etap_deud = DECODE(p_cod_etap_deud,NULL,c.cod_etap_deud,p_cod_etap_deud)
      AND c.cod_peri=DECODE(p_cod_peri,NULL,c.cod_peri,p_cod_peri)
      AND c.cod_regi=DECODE(p_cod_regi,NULL,c.cod_regi,'00',c.cod_regi,p_cod_regi)
      AND c.cod_zona=DECODE(p_cod_zona,NULL,c.cod_zona,'0000',c.cod_zona,p_cod_zona)
      AND c.Ind_Gene_Cart=0
      AND c.cod_cart IS NULL
      AND (( c.fec_gene_cart = TO_DATE(p_fec_asign,'DD/MM/YYYY')
                 AND p_fec_asign IS NOT NULL)
                 OR
                 ( c.fec_gene_cart <= TRUNC(SYSDATE)
                 AND p_fec_asign IS NULL));

    lv_ind_asig_cart_ante        cob_param_gener.val_para%TYPE;

   BEGIN

    lv_ind_asig_cart_ante := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('AsignacionCarteraAnterior');

    IF lv_ind_asig_cart_ante IS NOT NULL THEN

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_CARTE_GENER_PROCE_ASIGN');

      FOR v_Cart IN c_Cart(
                                         p_cod_pais,
                                         p_cod_soci,
                                         p_cod_etap_deud,
                                         p_cod_peri,
                                         p_cod_regi ,
                                         p_cod_zona,
                                         p_fec_asign) LOOP
         COB_PR_ASIGN_CARTE_MANUA(p_cod_pais,p_cod_soci,v_Cart.cod_etap_deud,v_Cart.cod_peri, v_Cart.cod_regi ,v_Cart.cod_zona,p_cod_usua);
      END LOOP;

    ELSE

     CCC_PKG_PROCE.CCC_PR_PROCE_EXECU_SCRIP;

   END IF;

   END COB_PR_ASIGN_CARTE;

   PROCEDURE cob_pr_asign_carte_manua(
      p_cod_pais                            seg_pais.cod_pais%TYPE,
      p_cod_soci                            seg_socie.cod_soci%TYPE,
      p_cod_etap_deud                 cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                            seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                            zon_regio.cod_regi%TYPE,
      p_cod_zona                           zon_zona.cod_zona%TYPE,
      p_cod_usua                           cob_detal_asign_carte.usu_crea%TYPE)
   IS
      lv_oid_pais                           seg_pais.oid_pais%TYPE;
      lv_oid_soci                           seg_socie.oid_soci%TYPE;
      lv_oid_peri                           cra_perio.oid_peri%TYPE;
      lv_cod_clie                           mae_clien.cod_clie%TYPE;
      lv_cod_zona                         zon_zona.cod_zona%TYPE;
      lv_cod_regi                          zon_regio.cod_regi%TYPE;
      lv_cod_cart                         cob_detal_asign_carte.cod_cart%TYPE;

      lv_fec_cier                          DATE;
      lv_fec_gene_cart                  DATE;
      lv_imp_deud_asig_total        NUMBER(12, 2);
      lv_imp_cart_tot                   NUMBER(12,2);
      lv_imp_deud_media              NUMBER(12, 2);
      lv_imp_pend                        NUMBER(12, 2);
      lv_num_cob                         NUMBER(12);
      lv_dif                                  NUMBER(12, 2);
      lv_ind_asig                           NUMBER(12);
      lv_imp_deud_asig                 NUMBER(12,2);
      lv_mont                               NUMBER(12,2);
      lv_cont_mov                           NUMBER(12):=0;
      lv_cont_det                           NUMBER(12):=0;
      lv_ind_asig_anter                NUMBER(12);
      lv_ind_gen_cart                   NUMBER(1);

      y NUMBER(3);

      -- exceptiones
      e_rz_asig_ejec                       EXCEPTION;
      e_rz_no_regis_crono               EXCEPTION;
      e_rz_asign_carte                     EXCEPTION;
      e_cart_sin_deu                       EXCEPTION;
      e_cart_inco                             EXCEPTION;
      e_no_regis_super                     EXCEPTION;
      e_actu_crono                           EXCEPTION;

      v_tab_detal_asign_carte                       t_tab_cob_detal_asign_carte;
      reg_cob_cabec_asign_carte                   cob_cabec_asign_carte%ROWTYPE;
      reg_cob_carte_gener_proce                  cob_carte_gener_proce_asign%ROWTYPE;
      lv_ind_ejec_proc                                    NUMBER(12);

      TYPE t_rec_usu_cobr IS RECORD(
         id               NUMBER(3),
         cod_usu     VARCHAR2(20),
         imp_asign   NUMBER(12, 2),
         ind_asign    NUMBER(1));

      TYPE t_tab_usu_cobr IS TABLE OF t_rec_usu_cobr;
      v_tab_usu_cobr t_tab_usu_cobr;

      v_tab_usu_cobr_temp t_tab_usu_cobr:= t_tab_usu_cobr();

      PRAGMA AUTONOMOUS_TRANSACTION;

   BEGIN

      SELECT COUNT(*)
      INTO lv_ind_ejec_proc
      FROM fin_proce_ejecu f
      WHERE f.cod_proc=lv_cod_proc_asig_cart
      AND f.ind_ejec='S';

      IF lv_ind_ejec_proc>0 THEN
          RAISE e_rz_asig_ejec;
      END IF;

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_DETAL_ASIGN_CARTE');

      lv_cod_zona:=NVL(p_cod_zona,'0000');
      lv_cod_regi:=NVL(p_cod_regi,'00');

      lv_des_log:=NULL;

      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,p_cod_usua,lv_id_proc_ejec);

      lv_cod_cart:=p_cod_peri || p_cod_etap_deud || 'R' || lv_cod_regi || 'Z' || lv_cod_zona || to_char(SYSDATE,'DDMMHHMI');

      lv_fec_gene_cart:=trunc(SYSDATE);

      lv_des_log:='Generacion de la cartera: ' || lv_cod_cart;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);
      lv_des_log:=' Inicio del proceso ' ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

      lv_oid_pais:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);
      lv_oid_soci := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_SOCIE(p_cod_soci);
      lv_oid_peri:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais,p_cod_peri);

      -- Recupera la fecha de cierre de la Cartera para la etapa desde
      -- el cronograma generado previamente
      -- segun el proceso sea por region o por region y zona.

      /**** Asignacion por unidad administrativa ****/
      BEGIN
         SELECT cro.fec_cier, cro.ind_gene_cart
         INTO lv_fec_cier, lv_ind_gen_cart
         FROM cob_crono_carte cro
         WHERE cro.cod_pais  = p_cod_pais
              AND  cro.cod_soci = p_cod_soci
              AND  cro.cod_peri = p_cod_peri
              AND  cro.cod_etap_deud = p_cod_etap_deud
              AND  cro.cod_regi = lv_cod_regi
              AND  cro.cod_zona =  lv_cod_zona;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            RAISE e_rz_no_regis_crono;
      END;

      -- Exception Cartera Ya Asignada --
      IF  lv_ind_gen_cart <> 0 THEN
         RAISE e_rz_asign_carte;
      END IF;

      /***************************************************************************
      /* Carga la tabla  temporal COB_TEMPO_ZON_TERRI_ADMIN
         Se insertan  los zon_terri_Admin que corresponda
        a las unidades administrativas ingresadas por parametro      */
      lv_start_time := DBMS_UTILITY.get_time;
      cob_pr_obtie_zonas_terri_admin(p_cod_pais,p_cod_soci,p_cod_etap_deud,lv_cod_regi,lv_cod_zona,lv_fec_gene_cart);
      lv_end_time := DBMS_UTILITY.get_time;

      lv_des_log:='Termino Seleccion de Unidades Administrativas =>Tiempo : ' || to_char(lv_end_time-lv_start_time);
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

      /************************************************************************************************

      /*****************************************************************************************************
      /* Carga la tabla temporal COB_TEMPO_ASIGN_CARTE_CLIE
         Se insertan los clientes perteneciente a los zon_Terri_Admin de
        la tabla temporal COB_TEMPO_ZON_TERRI_ADMIN y que su deuda sea mayor a cero.*/
      lv_start_time := DBMS_UTILITY.get_time;
      COB_PR_OBTIE_CLIEN_UNIDA_ADMIN(p_cod_pais,p_cod_soci,p_cod_etap_deud);
      lv_end_time := DBMS_UTILITY.get_time;

      lv_des_log:='Termino Seleccion clientes con deuda => Tiempo : ' || to_char(lv_end_time-lv_start_time);
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
      /********************************************************************************/

      /*********************************************************************************/
       --  Crea cabecera COB_CABEC_ASIGN_CARTE
        reg_cob_cabec_asign_carte.cod_cart := lv_cod_cart;
        reg_cob_cabec_asign_carte.cod_pais := p_cod_pais;
        reg_cob_cabec_asign_carte.cod_soci := p_cod_soci;
        reg_cob_cabec_asign_carte.cod_etap_deud := p_cod_etap_deud;
        reg_cob_cabec_asign_carte.cod_peri := p_cod_peri;
        reg_cob_cabec_asign_carte.cod_regi := lv_cod_regi;
        reg_cob_cabec_asign_carte.cod_zona := lv_cod_zona;
        reg_cob_cabec_asign_carte.fec_asig := trunc(SYSDATE);
        reg_cob_cabec_asign_carte.fec_cier := lv_fec_cier;
        reg_cob_cabec_asign_carte.usu_crea := p_cod_usua;
        reg_cob_cabec_asign_carte.fec_crea := SYSDATE;
        reg_cob_cabec_asign_carte.usu_modi := p_cod_usua;
        reg_cob_cabec_asign_carte.fec_modi := SYSDATE;

        INSERT INTO cob_cabec_asign_carte VALUES reg_cob_cabec_asign_carte;
      /******************************************************************************/

     -- Seleccion y carga en la tabla COB_DETAL_ASIGN_CARTE de las cuotas  a asignar
      lv_start_time := DBMS_UTILITY.get_time;

      lv_des_log:='Inicia de la carga de Cartera por Movimientos ';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      cob_pr_asign_movim_cuent_clien(lv_oid_soci,lv_oid_peri,lv_cod_cart,lv_fec_cier,p_cod_usua);

      lv_end_time := DBMS_UTILITY.get_time;

      SELECT COUNT(*)
      INTO lv_cont_mov
      FROM cob_carte_movim_cuent_clien ccc
      WHERE ccc.cod_cart=lv_cod_cart;

      lv_des_log:='Fin de la carga de Cartera por Movimientos => Tiempo : ' || to_char(lv_end_time-lv_start_time) || ' Cantidad de Movimientos :  ' || lv_cont;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      -- No existen Deudores para la Cartera.
      IF lv_cont_mov <= 0 THEN
         --RAISE e_cart_sin_deu;
         UPDATE cob_crono_carte cro
         SET  cro.ind_gene_cart = 2
         WHERE cro.cod_pais = p_cod_pais
         AND   cro.cod_soci = p_cod_soci
         AND   cro.cod_peri = p_cod_peri
         AND   cro.cod_etap_deud = p_cod_etap_deud
         AND   cro.cod_regi      = p_cod_regi
         AND   lv_cod_zona  = cro.cod_zona;

         lv_des_log:='Cartera sin deudores: ' || lv_cont;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

         lv_des_log:='Fin del Proceso Correctamente';
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 9);

         GOTO  cart_sin_deud;
      END IF;

      lv_start_time := DBMS_UTILITY.get_time;

      lv_des_log:='Inicio de la carga de cartera por clientes';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      WITH temp1
         AS
            (SELECT
                ccc.cod_cart,
                ccc.oid_clie,
                MIN(ccc.num_iden_cuot) num_iden_cuot,
                MIN(ccc.fec_docu) fec_docu,
                MIN(ccc.fec_asig) fec_asign,
                SUM(ccc.imp_deud_orig) imp_deud_orig,
                SUM(ccc.imp_deud_asig) imp_deud_asign,
                SUM(ccc.imp_deud_pend) imp_deud_pend,
                SUM(ccc.imp_deud_canc) imp_deud_canc,
                SUM(ccc.imp_pago_banc) imp_pago_banc,
                SUM(ccc.imp_pago_otro) imp_pago_otro
             FROM cob_carte_movim_cuent_clien ccc
             WHERE ccc.cod_cart=lv_cod_cart
             GROUP BY ccc.cod_cart, ccc.oid_clie)
             SELECT
                lv_cod_cart,
                p_cod_pais,
                p_cod_soci,
                p_cod_peri,
                p_cod_etap_deud,
                acc.cod_regi,
                acc.des_regi,
                lv_cod_zona,
                acc.cod_secc,
                acc.cod_terr,
                mcc.oid_clie,
                mc.cod_clie,
                TRIM(mc.val_nom1) || ' ' ||
                TRIM(mc.val_nom2) || ' ' ||
                TRIM(mc.val_ape1) || ' ' ||
                TRIM(mc.val_ape2),
                FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_DOCUM_IDENT(lv_oid_pais,mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_DOCUM_IDENT(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(mcc.oid_clie),
                NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mcc.oid_clie,'DES_DPTO'),' '),
                NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mcc.oid_clie,'DES_PROV'),' '),
                NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mcc.oid_clie,'DES_DIST'),' ' ),
                FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_CLIEN(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mcc.oid_clie,'TF'),
                FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mcc.oid_clie,'TT'),
                FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mcc.oid_clie,'TM'),
                NULL,
                NULL,
                NULL,
                NVL(FIN_PKG_GENER.FIN_FN_OBTIE_BOLET_DESPA_CAMPA(lv_oid_peri,mcc.oid_clie),mcc.num_iden_cuot),
                NVL(FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_PEDID_CAMPA(lv_oid_peri,mcc.oid_clie),mcc.fec_docu),
                NULL,
                trunc(SYSDATE),
                lv_fec_cier,
                NULL,
                0,
                0,
                NULL,
                mcc.imp_deud_orig,
                mcc.imp_deud_pend,
                0,
                0,
                lv_user_dummy,
                NULL,
                NULL,
                p_cod_usua,
                SYSDATE,
                p_cod_usua,
                SYSDATE,
                0,
                0,
                mcc.imp_deud_pend,
                0,
                acc.cod_zona,
                acc.cod_regi,
                acc.des_regi,
                0,
                0,
                0,
                0,
                NULL,
                0,
                'NGE',
                'NGE',
                FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_CLIEN_REFER(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_CAMPA_PRIME_PEDID(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(mcc.oid_clie),
                FIN_PKG_GENER.FIN_FN_OBTIE_NOMBR_GEREN_ZONA(acc.cod_zona),
                FIN_PKG_GENER.FIN_FN_OBTIE_NOMBR_LIDER_SECCI(acc.cod_zona,acc.cod_secc),
                mc.cod_digi_ctrl,
                NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mcc.oid_clie,'DES_URBA'),' '),
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(mcc.oid_clie,'ML'),
                NULL--FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_POSTA(mcc.oid_clie)
           BULK COLLECT INTO v_tab_detal_asign_carte
           FROM
                   cob_tempo_asign_carte_clien acc,
                   temp1 mcc,
                   mae_clien mc
           WHERE mcc.oid_clie=acc.oid_clie
           AND mcc.oid_clie=mc.oid_clie
           AND acc.oid_clie = mcc.oid_clie;


      FORALL x IN v_tab_detal_asign_carte.FIRST .. v_tab_detal_asign_carte.LAST
         INSERT INTO cob_tempo_detal_asign_carte VALUES v_tab_detal_asign_carte (x);

      SELECT COUNT(carte.cod_clie)
      INTO lv_cont_det
      FROM cob_tempo_detal_asign_carte carte
      WHERE carte.cod_cart=lv_cod_cart;

      lv_end_time := DBMS_UTILITY.get_time;

      lv_des_log:='Fin de la carga de cartera por clientes => Tiempo :' || to_char(lv_end_time-lv_start_time) || ' Cantidad de Clientes :  ' || lv_cont;
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      -- Exception : Cartera Inconsistente
      IF lv_cont_det<=0 THEN
         RAISE e_cart_inco;
      END IF;

      --------------------------------------------------------------------------------------

      cob_pr_asign_carte_super(p_cod_pais,p_cod_soci,p_cod_etap_deud,lv_cod_Cart);

      SELECT COUNT(carte.cod_clie),SUM(carte.imp_deud_asig)
      INTO lv_cont , lv_mont
      FROM cob_tempo_detal_asign_carte carte
      WHERE carte.cod_cart = lv_cod_cart
      AND carte.cod_usua_cobr = lv_user_dummy;

      lv_des_log:='Cartera por asignar : Cantidad de Consultoras : ' || lv_cont || ' Monto Total por Asignar ' || lv_mont;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      -- Calcular el Importe de Deuda Medio
      cob_pr_obtie_impo_deuda_medio(p_cod_pais,p_cod_soci,p_cod_etap_deud,lv_cod_regi,lv_cod_zona,lv_cod_cart,lv_imp_cart_tot, lv_imp_deud_media, lv_num_cob);

      -- Obtener los usuarios de cobranza programados
      IF lv_cod_regi<>'00' THEN
         SELECT rownum, cp.cod_usua_cobr, 0, 0
         BULK COLLECT INTO v_tab_usu_cobr
         FROM cob_etapa_cobra_ua ua,
                    cob_usuar_cobra_pais cp
         WHERE ua.cod_usua_cobr=cp.cod_usua_cobr
          AND cp.cod_pais = p_cod_pais
         AND cp.cod_soci = p_cod_soci
         AND ua.cod_pais = p_cod_pais
         AND ua.cod_soci = p_cod_soci
         AND ua.cod_etap_deud = p_cod_etap_deud
         AND ua.cod_regi = lv_cod_regi
         AND ua.cod_zona=lv_cod_zona
         AND cp.ind_supe=0
         AND cp.ind_acti=1;

      ELSE
         SELECT rownum, cod_usua_cobr, 0, 0
         BULK COLLECT INTO v_tab_usu_cobr
         FROM
            (SELECT DISTINCT ua.cod_usua_cobr
             FROM cob_etapa_cobra_ua ua,
                       cob_usuar_cobra_pais cp
         WHERE ua.cod_usua_cobr=cp.cod_usua_cobr
          AND cp.cod_pais = p_cod_pais
         AND cp.cod_soci = p_cod_soci
         AND ua.cod_pais = p_cod_pais
         AND ua.cod_soci = p_cod_soci
         AND ua.cod_etap_deud = p_cod_etap_deud
         AND cp.ind_supe=0
         AND cp.ind_acti=1);
      END IF;

      --  Crea cabecera COB_CABEC_ASIGN_CARTE
      UPDATE cob_cabec_asign_carte c
      SET
        c.tot_impo_deud_asig = lv_imp_cart_tot,
        c.num_usua_cob = lv_num_cob,
        c.imp_deud_medi = lv_imp_deud_media,
        c.tot_impo_deud_canc = 0
      WHERE c.cod_cart=lv_cod_cart
      AND c.cod_pais=p_cod_pais
      AND c.cod_soci=p_cod_soci
      AND c.cod_etap_deud=p_cod_etap_deud
      AND c.cod_peri = p_cod_peri
      AND c.cod_regi = p_cod_regi
      AND c.cod_zona = lv_cod_zona;

      -- Seleccionar numero de cobradores
      IF lv_num_cob > 1 THEN

         -- Asignacion con balanceo
         lv_start_time := DBMS_UTILITY.get_time;

         lv_des_log:='Inicio Asignar la cartera Con balanceo ';
        fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

         -- Carga de Cobradores
         FOR x IN 1 .. lv_num_cob  LOOP
            v_tab_usu_cobr_temp.EXTEND;
         END LOOP;

         -- Inicio de la asignacion de cartera con balanceo
         LOOP

            -- Obteniendo la cantidad de cuotas pendientes de asignar
            SELECT COUNT(*)
            INTO lv_ind_asig
            FROM cob_tempo_detal_asign_carte c
            WHERE c.cod_cart = lv_cod_cart
            AND c.cod_usua_cobr = lv_user_dummy;

            IF  lv_ind_asig_anter = lv_ind_asig THEN
               lv_des_log:='ERROR : No esta avanzando el proceso de asignacion';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
            END IF;

            lv_ind_asig_anter := lv_ind_asig;

            IF lv_ind_asig > 0 THEN
               --Hay cuotas pendientes por asignar
               FOR x IN v_tab_usu_cobr.FIRST .. v_tab_usu_cobr.LAST LOOP
                  --dbms_output.put_line('Usuario : ' || v_tab_usu_cobr(x).cod_usu);
                  IF v_tab_usu_cobr(x).ind_asign = 0 THEN
                     --dbms_output.put_line('Usuario disponible');
                     lv_imp_deud_asig_total:=v_tab_usu_cobr(x).imp_asign;
                     --dbms_output.put_line('Importe Asignado ' || lv_imp_deud_asig_total);

                     -- Busqueda de la cuota de mayor importe
                     --dbms_output.put_line('Buscando Cuota de Mayor Importe');
                     BEGIN
                        cob_pr_obtie_cuota_asign( lv_cod_cart,  lv_cod_clie,lv_imp_pend);
                        --dbms_output.put_line('Importe Cuota Mayor ' || v_imp_pend );
                     EXCEPTION
                        WHEN no_data_found THEN
                       EXIT;
                       --dbms_output.put_line('No se encontraron Cuotas .... :$');
                  END;

                  IF lv_imp_deud_asig_total + lv_imp_pend <= lv_imp_deud_media THEN
                     -- Asignacion Normal
                     --dbms_output.put_line('Asig Normal');
                     cob_pr_asign_carte_clien(lv_cod_cart,lv_cod_clie,v_tab_usu_cobr(x).cod_usu,lv_imp_deud_asig);
                     v_tab_usu_cobr(x).imp_asign :=v_tab_usu_cobr(x).imp_asign +lv_imp_deud_asig;
                  ELSE
                     -- Asignacion por Excepcion
                     --dbms_output.put_line('Asig Excepcion');
                     IF lv_imp_deud_asig_total < lv_imp_deud_media THEN
                        lv_dif := lv_imp_deud_media - lv_imp_deud_asig_total;
                       -- dbms_output.put_line('Diferencia : ' || v_dif);

                        IF MOD(v_tab_usu_cobr(x).id, 2) = 0 THEN
                         --Usuario Par - Asignacion por exceso
                           --dbms_output.put_line('Asig Exceso');
                           lv_cod_clie:=cob_fn_obtie_cuota_asign_exces(lv_cod_cart,lv_dif);
                           --dbms_output.put_line('Cliente Asignado : ' || v_cod_clie);
                           v_tab_usu_cobr(x).ind_asign := 1;
                        ELSE
                        --Usuario Impar - Asignacion por defecto
                           --dbms_output.put_line('Asig Defecto');
                           lv_cod_clie:=cob_fn_obtie_cuota_asign_defec(lv_cod_cart,lv_dif);
                           --dbms_output.put_line('Cliente Asignado : ' || lv_cod_clie);
                           --v_tab_usu_cobr(x).ind_asign := 1;
                        END IF;

                         cob_pr_asign_carte_clien(lv_cod_cart,lv_cod_clie,v_tab_usu_cobr(x).cod_usu,lv_imp_deud_asig);
                         --dbms_output.put_line('Monto Asignado por Excepcion: ' || lv_imp_deud_asig);
                         v_tab_usu_cobr(x).imp_asign :=v_tab_usu_cobr(x).imp_asign +lv_imp_deud_asig;
                         --dbms_output.put_line('Importe Asignado Final ' || v_tab_usu_cobr(x).imp_asign);
                      END IF;
                  END IF;
               END IF;

               lv_start_time:= DBMS_UTILITY.get_time;
               --DBMS_OUTPUT.PUT_LINE('inicio loop asign : ' || to_char(end_time-start_time));

               /*
               FOR x IN v_tab_usu_cobr_temp.FIRST .. v_tab_usu_cobr_temp.LAST  LOOP
                 v_tab_usu_cobr(x):=v_tab_usu_cobr_temp(x);
               END LOOP;
               */
               lv_end_time := DBMS_UTILITY.get_time;
               --DBMS_OUTPUT.PUT_LINE('fin loop asign : ' || to_char(end_time-start_time));

            END LOOP;

            /* invierte orden de los cobradores */
               y:=1;
               FOR x IN REVERSE v_tab_usu_cobr.FIRST .. v_tab_usu_cobr.LAST  LOOP
                  --dbms_output.put_line(x);
                  v_tab_usu_cobr_temp(y):=v_tab_usu_cobr(x);
                  dbms_output.put_line(y);
                  y:=y+1;
                  --dbms_output.put_line(y);
               END LOOP;

               v_tab_usu_cobr:=v_tab_usu_cobr_temp;

         ELSE
            EXIT;
         END IF;
      END LOOP;

       lv_end_time := DBMS_UTILITY.get_time;

       lv_des_log:='Fin Asignar la cartera Con balanceo ' || to_char(lv_end_time-lv_start_time);
       FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

    ELSE

       --Asignacion de Cartera Sin Balanceo
       lv_start_time := DBMS_UTILITY.get_time;

       UPDATE cob_tempo_detal_asign_carte c
       SET c.cod_usua_cobr = v_tab_usu_cobr(1).cod_usu
       WHERE c.cod_cart= lv_cod_cart
       AND c.cod_usua_cobr = lv_user_dummy --- IS NULL
       AND c.ind_st=0
       AND c.ind_im=0;

       lv_end_time := DBMS_UTILITY.get_time;

         lv_des_log:='Asignar la cartera Sin balanceo ' || to_char(lv_end_time-lv_start_time);
        fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

    END IF;

    --Generando la Cartera Asignada
    INSERT INTO cob_detal_asign_carte
      SELECT *
      FROM cob_tempo_detal_asign_carte;

    -- Actualiza indicador de cartera generada en Cronograma
      BEGIN
         UPDATE cob_crono_carte cro
         SET  cro.ind_gene_cart = 1,
                    cro.cod_cart = lv_cod_cart
         WHERE cro.cod_pais = p_cod_pais
         AND   cro.cod_soci = p_cod_soci
         AND   cro.cod_peri = p_cod_peri
         AND   cro.cod_etap_deud = p_cod_etap_deud
         AND   cro.cod_regi      = p_cod_regi
         AND   lv_cod_zona = cro.cod_zona;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            RAISE e_actu_crono;
      END;

      lv_des_log:='Fin del proceso';
      FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 2);

      reg_cob_carte_gener_proce.cod_pais := p_cod_pais;
      reg_cob_carte_gener_proce.cod_soci:=p_cod_soci;
      reg_cob_carte_gener_proce.cod_etap_deud:=p_cod_etap_deud;
      reg_cob_carte_gener_proce.cod_peri:=p_cod_peri;
      reg_cob_carte_gener_proce.cod_regi:=p_cod_regi;
      reg_cob_carte_gener_proce.cod_zona:=p_cod_zona;
      reg_cob_carte_gener_proce.cod_usua:=p_cod_usua;

      INSERT INTO cob_carte_gener_proce_asign VALUES reg_cob_carte_gener_proce;

      <<cart_sin_deud>>
      COMMIT;

   -- Manejo de excepciones --
   EXCEPTION
     WHEN e_rz_asig_ejec THEN
       ROLLBACK;
       RAISE_APPLICATION_ERROR(-20123, 'Asignacion de Cartera en Ejecucion: ');

      WHEN e_rz_no_regis_crono THEN
         lv_des_log:='Region Zona no registrada en cronograma ' || ' Region : ' ||p_cod_regi || ' Zona : ' || lv_cod_zona;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

         lv_des_log:='Fin del proceso de manera erronea';
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 9);

         ROLLBACK;

      WHEN   e_rz_asign_carte THEN
         lv_des_log:='Ya se Asigno Cartera para la Region ' || p_cod_regi || ' Zona ' || lv_cod_zona;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

         lv_des_log:='Fin del proceso de manera erronea';
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 9);

         ROLLBACK;

      WHEN e_cart_inco THEN
         lv_des_log:='Cartera inconsistente: ' || lv_cont;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

        lv_des_log:='Fin del proceso de manera erronea';
        FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
        FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 9);
        ROLLBACK;

      WHEN e_actu_crono THEN
         lv_des_log:='Error al actualizar indicador en el cronograma' ;
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

         lv_des_log:='Fin del proceso de manera erronea';
        FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
        FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_Cart,lv_id_proc_ejec, 9);
         ROLLBACK;

      WHEN OTHERS THEN

         lv_des_log:='Fin del proceso de manera erronea';
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

         lv_sqlcode := SQLCODE;
         lv_sqlerrm := SUBSTR(SQLERRM,1,250);

         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_sqlerrm);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_Cod_proc_asig_cart,lv_id_proc_ejec, 9);

         ROLLBACK;
         RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_ASIGN_CARTE_MANUA: '|| lv_sqlerrm);

   END cob_pr_asign_carte_manua;

  /**************************************************************************
  Descripcion       :  Proceso Auxiliar de la Asignacion de la Cartera
                            Este proceso inserta en la tabla temporal COB_TEMPO_ZON_TERRI_ADMIN
                            los oid de los territorios administrativos a considerar en la Asignacion
                           de la Cartera
  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Etapa de Deuda,
            Codigo Region,
            Codigo Zona
  ***************************************************************************/
 PROCEDURE cob_pr_obtie_zonas_terri_admin(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_fec_gene_cart cob_crono_carte.fec_gene_cart%TYPE)
   IS

      v_cont  NUMBER(12):=0;

   BEGIN

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_ZON_TERRI_ADMIN_ASIG');

      lv_des_log:=NULL;

      IF p_cod_regi ='00' THEN

       /***** Inicio Asignacion por Cronograma   *****/
       lv_des_log:='Asignacion por Cronograma';
       FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

       INSERT INTO cob_tempo_zon_terri_admin_asig
               (SELECT zta.oid_terr_admi
                FROM zon_regio zr,
                     zon_zona zz,
                     zon_secci zs,
                     zon_terri_admin zta
                WHERE zr.oid_regi=zz.zorg_oid_regi
                AND zz.oid_zona=zs.zzon_oid_zona
                AND zta.zscc_oid_secc = zs.oid_secc
                AND zz.cod_zona  IN
                  (
                     SELECT zz.cod_zona
                     FROM cob_crono_Carte cc,
                                zon_zona zz,
                                zon_regio zr
                     WHERE cc.cod_zona='0000'
                     AND zz.zorg_oid_regi=zr.oid_regi
                     AND cc.cod_regi=zr.cod_regi
                     AND cc.fec_gene_cart=trunc(p_fec_gene_cart)
                     UNION
                     SELECT cc.cod_Zona
                     FROM cob_crono_Carte cc
                     WHERE cc.ind_gene_cart=0
                     AND cc.fec_gene_cart=trunc(p_fec_gene_cart)));

      ELSE
          /***** Inicio Asignacion por Unidad Administrativa   *****/

          lv_des_log:='Asignacion por Unidades Administrativas : ' ||  v_cont ;
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

         IF p_cod_zona = '0000' THEN
            INSERT INTO cob_tempo_zon_terri_admin_asig
               (SELECT zta.oid_terr_admi
                FROM zon_regio zr,
                          zon_zona zz,
                          zon_secci zs,
                         zon_terri_admin zta
                WHERE zr.oid_regi=zz.zorg_oid_regi
                AND zr.cod_regi=p_cod_regi
                AND zz.oid_zona=zs.zzon_oid_zona
                AND zta.zscc_oid_secc = zs.oid_secc
                AND zz.cod_zona NOT IN
                        (SELECT NVL(u.cod_zona,0)
                         FROM cob_etapa_cobra_ua u
                         WHERE u.cod_pais=p_cod_pais
                         AND u.cod_soci=p_cod_soci
                         AND u.cod_etap_deud = p_cod_etap_deud
                         AND u.cod_regi=p_cod_regi));
         ELSE
            INSERT INTO cob_tempo_zon_terri_admin_asig
               (SELECT zta.oid_terr_admi
                FROM zon_regio zr,
                          zon_zona zz,
                          zon_secci zs,
                          zon_terri_admin zta
                WHERE zr.oid_regi=zz.zorg_oid_regi
                AND zr.cod_regi=p_cod_regi
                AND zz.oid_zona=zs.zzon_oid_zona
                AND zta.zscc_oid_secc = zs.oid_secc
                AND zz.cod_zona  IN
                   (SELECT u.cod_zona
                    FROM cob_etapa_cobra_ua u
                    WHERE u.cod_pais=p_cod_pais
                    AND u.cod_soci=p_cod_soci
                    AND u.cod_etap_deud = p_cod_etap_deud
                    AND u.cod_regi=p_cod_regi
                    AND u.cod_zona=p_cod_zona));
         END IF;
         /***** Fin Asignacion por Unidad Administrativa   *****/
      END IF;

      SELECT COUNT(*)
      INTO v_cont
      FROM cob_tempo_zon_terri_admin_asig;

      lv_des_log:='Cantidad de territorios administrativos : ' ||  v_cont ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

   END cob_pr_obtie_zonas_terri_admin;

   /**************************************************************************
  Descripcion       : Proceso Auxiliar de la Asignacion de la Cartera
                           Este proceso inserta en la tabla temporal COB_TEMPO_ASIGN_CARTE_CLIE
                           los clientes deudores a considerar en la Asignacion de la Cartera.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Region,
            Codigo Zona
  ***************************************************************************/
   PROCEDURE cob_pr_obtie_clien_unida_admin(
      p_cod_pais               seg_pais.cod_pais%TYPE,
      p_cod_soci               seg_socie.cod_soci%TYPE,
      p_cod_etap_deud    cob_etapa_deuda_pais.cod_etap_deud%TYPE)
   IS

   TYPE t_tab_tempo_asign_Carte_clie IS TABLE OF cob_tempo_asign_carte_clien%ROWTYPE;
   v_tab_tempo_asign_Carte_clie t_tab_tempo_asign_carte_clie;

   v_cont                                  NUMBER(12):=0;
   v_ind_unid_admi_mvv         cob_etapa_deuda_pais.Ind_Unid_Admi_Mvcc%TYPE;

   BEGIN

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_ASIGN_CARTE_CLIEN');

      lv_des_log:=NULL;

      SELECT COUNT(*)
      INTO v_cont
      FROM cob_tempo_zon_terri_admin_asig;

      SELECT etapa.ind_unid_admi_mvcc
      INTO v_ind_unid_admi_mvv
      FROM cob_etapa_deuda_pais etapa
      WHERE etapa.cod_pais = p_cod_pais
      AND etapa.cod_soci=p_cod_soci
      AND etapa.cod_etap_deud=p_cod_etap_deud;

      IF v_ind_unid_admi_mvv = 0 THEN
         -- Se considera la Unidad Administrativa Activa
         BEGIN
            lv_des_log:='Por Unidad Administrativa Activa';
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);
         END;

         SELECT  zr.cod_regi ,
                        zr.des_regi ,
                        zz.cod_zona,
                        zs.cod_secc,
                        zt.cod_terr,
                        mcua.clie_oid_clie
          BULK COLLECT INTO v_tab_tempo_asign_Carte_clie
          FROM zon_zona zz,
                     zon_regio zr,
                     zon_secci zs,
                     zon_terri zt,
                     zon_terri_admin zta,
                     mae_clien_unida_admin mcua
          WHERE  zz.zorg_oid_regi=zr.oid_regi
          AND zz.oid_zona=zs.zzon_oid_zona
          AND zta.zscc_oid_secc=zs.oid_secc
          AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
          AND zt.oid_terr=zta.terr_oid_terr
          AND mcua.ind_Acti=1
          AND EXISTS (SELECT NULL
                                    FROM cob_tempo_zon_terri_admin_asig c
                                    WHERE c.oid_zon_terri_admin=mcua.ztad_oid_terr_admi)
          AND 0 < NVL((SELECT SUM(m.imp_pend)
                                  FROM ccc_movim_cuent_corri m
                                  WHERE m.clie_oid_clie = mcua.clie_oid_clie
                                  AND m.imp_pend <>0),0);
         ELSE

            BEGIN
               lv_des_log:='Por Unidad Administrativa Cuenta Corriente';
               fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);
            END;

            SELECT
                        zr.cod_regi ,
                        zr.des_regi ,
                        zz.cod_zona,
                        zs.cod_secc,
                        zt.cod_terr,
                        mcua.clie_oid_clie
          BULK COLLECT INTO v_tab_tempo_asign_Carte_clie
          FROM zon_zona zz,
                     zon_regio zr,
                     zon_secci zs,
                     zon_terri zt,
                     zon_terri_admin zta,
                     mae_clien_unida_admin mcua,
                     ccc_movim_cuent_corri mcc
          WHERE  zz.zorg_oid_regi=zr.oid_regi
          AND zz.oid_zona=zs.zzon_oid_zona
          AND zta.zscc_oid_secc=zs.oid_secc
          AND mcua.ztad_oid_terr_admi=zta.oid_terr_admi
          AND zt.oid_terr=zta.terr_oid_terr
          AND mcua.clie_oid_clie=mcc.clie_oid_clie
          AND mcua.ztad_oid_terr_admi=mcc.ztad_oid_terr_admi
          AND mcc.imp_pend > 0
          AND EXISTS (SELECT NULL
                                    FROM cob_tempo_zon_terri_admin_asig c
                                    WHERE c.oid_zon_terri_admin=mcua.ztad_oid_terr_admi)
          AND 0 < NVL((SELECT SUM(m.imp_pend)
                                  FROM ccc_movim_cuent_corri m
                                  WHERE m.clie_oid_clie = mcua.clie_oid_clie
                                  AND m.imp_pend <>0),0)
          GROUP BY
                 zr.cod_regi ,
                 zr.des_regi ,
                 zz.cod_zona,
                 zs.cod_secc,
                 zt.cod_terr,
                 mcua.clie_oid_clie;

         END IF;


      FORALL x IN v_tab_tempo_asign_Carte_clie.FIRST .. v_tab_tempo_asign_Carte_clie.LAST
         INSERT INTO cob_tempo_asign_carte_clien VALUES v_tab_tempo_asign_Carte_clie (x);

      SELECT COUNT(DISTINCT c.oid_clie)
      INTO v_cont
      FROM cob_tempo_asign_carte_clien c;

      BEGIN
         lv_des_log:='Cantidad de consultoras morosas : ' ||  v_cont ;
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);
      END;

   END cob_pr_obtie_clien_unida_admin;

   /**************************************************************************
  Descripcion       : Proceso Auxiliar de la Asignacion de la Cartera
                           Este proceso inserta en la tabla temporal COB_TEMPO_ASIGN_CARTE_CLIE
                           los clientes deudores a considerar en la Asignacion de la Cartera.
  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Region,
            Codigo Zona
  ***************************************************************************/
   PROCEDURE cob_pr_asign_movim_cuent_clien(
      p_oid_soci        seg_socie.cod_soci%TYPE,
      p_oid_peri        cra_perio.oid_peri%TYPE,
      p_cod_cart       cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier        cob_cabec_asign_carte.fec_cier%TYPE,
      p_cod_usua      cob_usuar_cobra_pais.cod_usua_cobr%TYPE)
   IS

      TYPE t_tab_cob_carte_mvcc_clien IS TABLE OF cob_carte_movim_cuent_clien%ROWTYPE;
      v_tab_cob_carte_mvcc_clien  t_tab_cob_carte_mvcc_clien;
      v_tab_cob_carte_cfrac_clien t_tab_cob_carte_mvcc_clien;

   BEGIN
      SELECT
             p_cod_cart,            -- COD_CART
             p_oid_peri,            -- OID_PERI
             mcc.clie_oid_clie,      -- OID_CLIE
             mcc.oid_movi_cc,        -- MVCC_OID_MOVI_CC
             mcc.num_iden_cuot,      -- NUM_IDEN_CUOT
             mcc.fec_docu,          -- FEC_DOCU
             mcc.fec_venc,          -- FEC_VENC
             trunc(SYSDATE),        -- FEC_ASIG
             p_fec_cier,            -- FEC_CIER
             mcc.imp_movi,          -- IMP_DEUD_ORIG
             mcc.imp_pend,          -- IMP_DEUD_ASIG
             mcc.imp_pend,          -- IMP_DEUD_PEND
             0,                      -- IMP_DEUD_CANC
             0,                      -- IMP_PAGO_BANC
             NULL,                  -- FEC_ULT_PAGO_BANC
             0,                      -- IMP_PAGO_OTRO
             mcc.val_ulti_nume_hist,  -- VAL_ULTI_NUME_HIST
             mcc.val_ulti_nume_hist,  -- VAL_ASIG_NUME_HIST
             0,                        -- IND_ERRO
             p_cod_usua,              -- USU_CREA
             SYSDATE,                  -- FEC_CREA
             p_cod_usua,              -- USU_MODI
             SYSDATE,                  -- FEC_MODI
             1                         -- IND_DEUD_PEND
        BULK COLLECT INTO v_tab_cob_carte_mvcc_clien
        FROM ccc_movim_cuent_corri mcc,
             cob_tempo_asign_carte_clien acc
       WHERE acc.oid_clie=mcc.clie_oid_clie
       AND mcc.soci_oid_soci = p_oid_soci
       AND mcc.perd_oid_peri = p_oid_peri
       AND mcc.imp_pend > 0
         AND NOT EXISTS
           (SELECT 1
           FROM ccc_movim_cargo_fracc f
           WHERE f.oid_movi_cc = mcc.oid_movi_cc)
          AND NOT EXISTS
           ( SELECT NULL
              FROM cob_carte_movim_clien_excep e
              WHERE e.oid_movi_cc = mcc.oid_movi_cc);

      FORALL x IN v_tab_cob_carte_mvcc_clien.FIRST .. v_tab_cob_carte_mvcc_clien.LAST
         INSERT INTO cob_carte_movim_cuent_clien VALUES v_tab_cob_carte_mvcc_clien (x);

      SELECT
             p_cod_cart,            -- COD_CART
             p_oid_peri,            -- OID_PERI
             mcc.clie_oid_clie,      -- OID_CLIE
             mcc.oid_movi_cc,        -- MVCC_OID_MOVI_CC
             mcc.num_iden_cuot,      -- NUM_IDEN_CUOT
             mcc.fec_docu,          -- FEC_DOCU
             mcc.fec_venc,          -- FEC_VENC
             trunc(SYSDATE),        -- FEC_ASIG
             p_fec_cier,            -- FEC_CIER
             mcc.imp_movi,          -- IMP_DEUD_ORIG
             mcc.imp_pend,          -- IMP_DEUD_ASIG
             mcc.imp_pend,          -- IMP_DEUD_PEND
             0,                      -- IMP_DEUD_CANC
             0,                      -- IMP_PAGO_BANC
             NULL,                  -- FEC_ULT_PAGO_BANC
             0,                      -- IMP_PAGO_OTRO
             mcc.val_ulti_nume_hist,  -- VAL_ULTI_NUME_HIST
             mcc.val_ulti_nume_hist,  -- VAL_ASIG_NUME_HIST
             0,                        -- IND_ERRO
             p_cod_usua,              -- USU_CREA
             SYSDATE,                  -- FEC_CREA
             p_cod_usua,              -- USU_MODI
             SYSDATE,                  -- FEC_MODI
             1                         -- IND_DEUD_PEND
        BULK COLLECT INTO v_tab_cob_carte_cfrac_clien
        FROM ccc_movim_cuent_corri mcc,
                   cob_tempo_asign_carte_clien acc
       WHERE acc.oid_clie=mcc.clie_oid_clie
       AND mcc.soci_oid_soci = p_oid_soci
       AND mcc.imp_pend > 0
       AND EXISTS
           (SELECT 1
           FROM ccc_movim_cargo_fracc f
           WHERE f.oid_movi_cc=mcc.oid_movi_cc
           AND f.oid_peri=p_oid_peri)
         AND NOT EXISTS
           ( SELECT NULL
              FROM cob_carte_movim_clien_excep e
              WHERE e.oid_movi_cc = mcc.oid_movi_cc);

      FORALL x IN v_tab_cob_carte_cfrac_clien.FIRST .. v_tab_cob_carte_cfrac_clien.LAST
         INSERT INTO cob_carte_movim_cuent_clien VALUES v_tab_cob_carte_cfrac_clien (x);

   END cob_pr_asign_movim_cuent_clien;

   PROCEDURE cob_pr_asign_carte_super
     ( p_cod_pais seg_pais.cod_pais%TYPE,
       p_cod_soci seg_socie.cod_soci%TYPE,
       p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
       p_cod_cart Cob_Cabec_Asign_Carte.Cod_Cart%TYPE)
   IS

      lv_imp_desd                       cob_etapa_deuda_pais.imp_desd%TYPE;
      lv_imp_hast                        cob_etapa_deuda_pais.imp_hast%TYPE;
      lv_ind_telf                          cob_etapa_deuda_pais.ind_telf%TYPE;
      lv_edad_inic                        cob_etapa_deuda_pais.val_edad_inic%TYPE;
      lv_edad_fina                       cob_etapa_deuda_pais.val_edad_fina%TYPE;
      lv_cod_usu_supe                 cob_usuar_cobra_pais.cod_usua_cobr%TYPE;
      lv_oid_tele_fijo                  mae_tipo_comun.oid_tipo_comu%TYPE;
      lv_oid_tele_trab                 mae_tipo_comun.oid_tipo_comu%TYPE;
      lv_oid_tele_movi                 mae_tipo_comun.oid_tipo_comu%TYPE;

      v_tab_cod_clie                    t_Tab_cod_clie;
      v_tab_oid_clie                     t_tab_oid_clie;

      -- exceptiones
      e_no_regis_super EXCEPTION;

   BEGIN

      --Asignando al supervisor la deudas menores
      lv_start_time := DBMS_UTILITY.get_time;

      -- Recupera caracteristicas de la Etapa de Deuda por procesar
      SELECT d.cod_usua_supe ,d.imp_desd , d.imp_hast,d.ind_telf,d.val_edad_inic , d.val_edad_fina
      INTO lv_cod_usu_supe,lv_imp_desd, lv_imp_hast,lv_ind_telf,lv_edad_inic,lv_edad_fina
      FROM cob_etapa_deuda_pais d
      WHERE d.cod_pais=p_cod_pais
      AND d.cod_soci=p_cod_soci
      AND d.cod_etap_deud=p_cod_etap_deud;

      -- Validando que exista el usuario Supervisor
      IF lv_cod_usu_supe IS NULL THEN
            RAISE e_no_regis_super;
      END IF;

      -- recupera los oid de los tipos de comunicacion utilizados en la etapa
      SELECT mae_tipo_comun.oid_tipo_comu
      INTO lv_oid_tele_fijo
      FROM mae_tipo_comun
      WHERE mae_tipo_comun.cod_tipo_comu = 'TF';

      SELECT mae_tipo_comun.oid_tipo_comu
      INTO lv_oid_tele_Trab
      FROM mae_tipo_comun
      WHERE mae_tipo_comun.cod_tipo_comu = 'TT';

      SELECT mae_tipo_comun.oid_tipo_comu
      INTO lv_oid_tele_movi
      FROM mae_tipo_comun
      WHERE mae_tipo_comun.cod_tipo_comu = 'TM';

      lv_des_log:='Usuario supervisor: ' || lv_cod_usu_supe;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_Cart,lv_id_proc_ejec,lv_des_log);

      SELECT t.cod_clie
      BULK COLLECT INTO v_tab_cod_clie
      FROM cob_tempo_detal_asign_carte t
      WHERE t.cod_cart=p_cod_cart
      HAVING ((SUM(t.imp_deud_asig) < lv_imp_desd)
      OR (SUM(t.imp_deud_asig) > lv_imp_hast))
      GROUP BY t.cod_clie;

      FORALL x IN v_tab_cod_clie.FIRST .. v_tab_cod_clie.LAST
         UPDATE cob_tempo_detal_asign_carte d
         SET d.cod_usua_cobr=lv_cod_usu_supe,
                 d.ind_im=1
         WHERE d.cod_clie = v_tab_cod_clie(x)
         AND d.cod_cart=p_cod_cart;

      lv_end_time := DBMS_UTILITY.get_time;

     SELECT COUNT(DISTINCT carte.cod_clie)
     INTO lv_cont
     FROM cob_tempo_detal_asign_carte carte
     WHERE carte.ind_im=1
     AND carte.cod_usua_cobr=lv_cod_usu_supe
     AND carte.cod_cart=p_cod_cart;

     lv_des_log:='Asignacion a Supervisor las deudas menores : Cantidad  de Consultoras ' || lv_cont || '  Tiempo :  ' ||to_char(lv_end_time-lv_start_time);
     fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      ------------------------------------------------------------------------------------------------------
      --Asignando al supervisor la deudas sin telefono

      IF lv_ind_telf=1 THEN
         lv_start_time := DBMS_UTILITY.get_time;

         SELECT t.oid_clie
         BULK COLLECT INTO v_tab_oid_clie
         FROM cob_tempo_detal_asign_carte t
         WHERE t.cod_cart=p_cod_cart
         AND NOT EXISTS (SELECT NULL
                                        FROM MAE_CLIEN_COMUN maeco
                                        WHERE maeco.clie_oid_clie = t.oid_clie
                                        AND maeco.ticm_oid_tipo_comu IN (
                                                          lv_oid_tele_fijo,
                                             lv_oid_tele_trab,
                                             lv_oid_tele_movi ));

         FORALL x IN v_tab_oid_clie.FIRST .. v_tab_oid_clie.LAST
            UPDATE cob_tempo_detal_asign_carte d
            SET d.cod_usua_cobr=lv_cod_usu_supe,
                    d.ind_st=1
            WHERE d.oid_clie = v_tab_oid_clie(x)
            AND d.cod_cart=p_cod_cart;

         lv_end_time := DBMS_UTILITY.get_time;

         SELECT COUNT(DISTINCT carte.cod_clie)
         INTO lv_cont
         FROM cob_tempo_detal_asign_carte carte
         WHERE carte.ind_st=1
         AND carte.cod_usua_cobr=lv_cod_usu_supe
         AND carte.cod_cart=p_cod_cart;

         lv_des_log:='Asignacion a Supervisor las deudas Sin Telefono : Cantidad  Consultoras ' || lv_cont || '  Tiempo :  ' ||to_char(lv_end_time-lv_start_time);
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);
      END IF;

   EXCEPTION
      WHEN e_no_regis_super THEN
         lv_des_log:='No existe usuario supervisor: ';
         FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

        RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_ASIGN_CARTE_SUPER: No Existe Usuario Supervisor');

   END cob_pr_asign_carte_super;

    /**************************************************************************
  Descripcion       : Proceso Auxiliar de la Asignacion de la Cartera
                           Este proceso calcula el IDM
                           Importe de Deuda Medio  = Importe Total de Cartera / Numero de Cobradores

  Fecha Creacion    : 22/02/2008
  Parametros Entrada :
            Codigo Pais,
            Codigo Sociedad,
            Codigo Region,
            Codigo Zona
   Parametos de Salida :
            Importe Total de Cartera,
            Importe de Deuda Medio,
            Numero de Cobradores
  ***************************************************************************/
   PROCEDURE cob_pr_obtie_impo_deuda_medio(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_etap_deud cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi zon_regio.cod_regi%TYPE,
      p_cod_zona zon_zona.cod_zona%TYPE,
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      p_imp_cart_tot OUT NUMBER,
      p_imp_deud_med OUT NUMBER,
      p_num_cob OUT NUMBER)
   IS
      v_imp_cart_total NUMBER(12,2);
      v_cant_usu_tele  NUMBER(12);
      v_imp_deud_media NUMBER(12,2);
      lv_des_log VARCHAR2(100);

      e_no_exis_cobr  EXCEPTION;
   BEGIN


      SELECT SUM(c.imp_deud_asig)
      INTO v_imp_cart_total
      FROM cob_tempo_detal_asign_carte c
      WHERE c.cod_cart = p_cod_cart
      AND c.cod_usua_cobr = lv_user_dummy;  -- IS NULL;

       IF p_cod_regi <>'00' THEN
          SELECT COUNT(DISTINCT ua.cod_usua_cobr)
          INTO v_cant_usu_tele
          FROM cob_etapa_cobra_ua ua,
                    cob_usuar_cobra_pais cp
          WHERE ua.cod_usua_cobr=cp.cod_usua_cobr
          AND cp.cod_pais= p_cod_pais
          AND cp.cod_soci= p_cod_soci
          AND ua.cod_pais = p_cod_pais
          AND ua.cod_soci = p_cod_soci
          AND ua.cod_etap_deud = p_cod_etap_deud
          AND ua.cod_regi = p_cod_regi
          AND  ua.cod_zona=p_cod_zona
          AND cp.ind_supe=0;

      ELSE

         SELECT COUNT(DISTINCT ua.cod_usua_cobr)
         INTO v_cant_usu_tele
         FROM cob_etapa_cobra_ua ua,
                    cob_usuar_cobra_pais cp
         WHERE ua.cod_usua_cobr=cp.cod_usua_cobr
         AND cp.cod_pais= p_cod_pais
          AND cp.cod_soci= p_cod_soci
          AND ua.cod_pais = p_cod_pais
         AND ua.cod_soci = p_cod_soci
         AND ua.cod_etap_deud = p_cod_etap_deud
         AND cp.ind_supe=0;

     END IF;

     IF v_cant_usu_tele= 0 THEN
        RAISE e_no_exis_cobr;
     END IF;

      lv_des_log:='Cartera Total : ' || v_imp_cart_total;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='Cantidad de Usuarios: ' || v_cant_usu_tele;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      v_imp_deud_media := v_imp_cart_total / v_cant_usu_tele;

      lv_des_log:='IDM: ' || v_imp_deud_media;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

      p_imp_cart_tot := v_imp_cart_total;
      p_imp_deud_med :=  v_imp_deud_media;
      p_num_cob := v_cant_usu_tele ;
   EXCEPTION
     WHEN e_no_exis_cobr THEN
          lv_des_log:='No Existen Cobradores: ';
          FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_asig_cart,lv_id_proc_ejec,lv_des_log);

          RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_OBTIE_IMPO_DEUDA_MEDIO: No Existen Cobradores');
   END cob_pr_obtie_impo_deuda_medio;

  /**************************************************************************
      Descripcion       : Proceso Auxiliar de la Asignacion de la Cartera
                          Este proceso obtiene el cliente y el monto, el cual tiene  el mayor monto
                          de cartera por asignar.
     Fecha Creacion    : 22/02/2008
     Parametros Entrada :
               Codigo Cartera
   Parametros Salida :
               Codigo Cliente
               Importe Pendiente
     ***************************************************************************/
   PROCEDURE cob_pr_obtie_cuota_asign(
      p_cod_cart                      IN cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie                       OUT mae_clien.cod_clie%TYPE,
      p_imp_pend                      OUT NUMBER)
   IS

   BEGIN
      SELECT cod_clie, imp_pend
      INTO p_cod_clie, p_imp_pend
      FROM
         (SELECT c.cod_clie,
                       SUM(c.imp_deud_asig) imp_pend
          FROM cob_tempo_detal_asign_carte c
          WHERE c.cod_cart = p_cod_cart
          AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
          GROUP BY c.cod_clie
          ORDER BY 2 DESC)
      WHERE rownum = 1;

   END cob_pr_obtie_cuota_asign;

   PROCEDURE cob_pr_asign_carte_clien(
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie mae_clien.cod_clie%TYPE,
      p_cod_usu cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig OUT NUMBER)
   IS
   BEGIN

      SELECT SUM(c.imp_deud_asig)
      INTO p_imp_deud_asig
      FROM cob_tempo_detal_asign_carte c
      WHERE c.cod_cart= p_cod_cart
      AND c.cod_clie = p_cod_clie;

      UPDATE cob_tempo_detal_asign_carte c
      SET c.cod_usua_cobr = p_cod_usu
      WHERE c.cod_cart= p_cod_cart
      AND c.cod_clie = p_cod_clie;

   END cob_pr_asign_carte_clien;

   FUNCTION cob_fn_obtie_cuota_asign_defec( p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
                                                              pn_dif NUMBER)
   RETURN VARCHAR2
   IS
      v_cod_clie mae_clien.cod_clie%TYPE;
   BEGIN
      BEGIN
         SELECT cod_clie
         INTO v_cod_clie
         FROM
            (SELECT c.cod_clie,
                          SUM(c.imp_deud_asig) imp_pend
             FROM cob_tempo_detal_asign_carte c
             WHERE c.cod_cart = p_cod_cart
                  AND c.cod_usua_cobr = lv_user_dummy -- IS NULL
             HAVING SUM(c.imp_deud_asig) <= pn_dif
             GROUP BY c.cod_clie
             ORDER BY 2 DESC)
         WHERE rownum = 1;
      EXCEPTION
         WHEN no_data_found THEN
            SELECT cod_clie
            INTO v_cod_clie
            FROM
               (SELECT c.cod_clie,
                             SUM(c.imp_deud_asig) imp_pend
                             FROM cob_tempo_detal_asign_carte c
                             WHERE c.cod_cart = p_cod_cart
                                 AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                             HAVING SUM(c.imp_deud_asig) >=pn_dif
                             GROUP BY c.cod_clie
                             ORDER BY 2 ASC)
                WHERE rownum = 1;
      END;

      RETURN v_cod_clie;
   END  cob_fn_obtie_cuota_asign_defec;

   FUNCTION cob_fn_obtie_cuota_asign_exces(
      p_cod_cart cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif NUMBER)
    RETURN VARCHAR2
    IS
      v_cod_clie mae_clien.cod_clie%TYPE;
      BEGIN
         BEGIN
            SELECT cod_clie
            INTO v_cod_clie
            FROM
               (SELECT c.cod_clie,
                             SUM(c.imp_deud_asig) imp_pend
                 FROM cob_tempo_detal_asign_carte c
                 WHERE c.cod_cart = p_cod_cart
                      AND c.cod_usua_cobr = lv_user_dummy --IS NULL
                  HAVING SUM(c.imp_deud_asig) >= pn_dif
                  GROUP BY c.cod_clie
                  ORDER BY 2 ASC)
            WHERE rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
               SELECT cod_clie
               INTO v_cod_clie
               FROM
                  (SELECT c.cod_clie,
                                 SUM(c.imp_deud_asig) imp_pend
                   FROM cob_tempo_detal_asign_carte c
                   WHERE c.cod_cart = p_cod_cart
                       AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                   HAVING SUM(c.imp_deud_asig) < pn_dif
                   GROUP BY c.cod_clie
                   ORDER BY 2 DESC)
                WHERE rownum = 1;
          END;
      RETURN v_cod_clie;
   END cob_fn_obtie_cuota_asign_exces;

 PROCEDURE COB_PR_ACTUA_CARTE(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE,
  p_cod_usua                       IN   cob_detal_asign_carte.usu_crea%type)
 IS

 --   Cursor  para seleccionar detalle  de cuotas  por  actualizar de tabla original
 CURSOR c_cart
 IS
  SELECT
   car.cod_cart,car.oid_clie,
   car.mvcc_oid_movi_cc, car.imp_deud_asig, car.imp_deud_canc,
   car.fec_asig, car.val_ulti_nume_hist, car.fec_cier,
   car.fec_ult_pago_banc, car.ind_erro
  FROM
   cob_carte_movim_cuent_clien car,
   ccc_movim_cuent_corri  mcc
  WHERE car.mvcc_oid_movi_cc = mcc.oid_movi_cc
    AND car.oid_clie=mcc.clie_oid_clie
    AND car.val_ulti_nume_hist < mcc.val_ulti_nume_hist
    AND car.ind_deud_pend = 1 ;

 --   Cursor  para seleccionar detalle  de cuotas  por  actualizar de  nueva tabla
 CURSOR c_dmov
 IS
  SELECT
   car.cod_cart,
   car.oid_clie,
   car.mvcc_oid_movi_cc,
   car.imp_deud_asig,
   car.imp_deud_canc,
   car.fec_asig,
   car.val_ulti_nume_hist,
   car.fec_cier,
   car.fec_ult_pago_banc, car.ind_erro
  FROM
   cob_detal_movim_carte car,
   ccc_movim_cuent_corri  mcc
  WHERE car.mvcc_oid_movi_cc = mcc.oid_movi_cc
    AND car.oid_clie=mcc.clie_oid_clie
    AND car.val_ulti_nume_hist < mcc.val_ulti_nume_hist
    AND car.ind_deud_pend = 1 ;


   lv_cod_cart                     cob_detal_asign_carte.cod_cart%TYPE;
   lv_imp_deud_asig                cob_detal_asign_carte.imp_deud_asig%type;
   --   v_oid_soci                 seg_socie.oid_soci%TYPE;
   lv_imp_deud_canc_ccc            NUMBER(12,2);
   lv_imp_deud_canc_car            NUMBER(12,2);
   lv_ind_error_car                NUMBER(1);
   lv_imp_deud_canc                NUMBER(12,2);

   -- Variables para cargar desde ccc_movim_cuent_corri
   lv_fec_pago_x                   DATE;
   lv_imp_pago_x                   NUMBER(12,2);
   lv_oid_subp_ulti_c              ccc_movim_cuent_corri.subp_oid_subp_ulti%type;
   lv_ind_actu                     ccc_param_gener.val_para%TYPE;

   -- Variables para cargar desde ccc_histo_movim_cc
   lv_fec_pago_banc_h              DATE;
   lv_fec_pago_otro_h              DATE;
   lv_imp_pago_banc_h              NUMBER(12,2);
   lv_imp_pago_otro_h              NUMBER(12,2);
   lv_num_hist_x                   NUMBER(3);
   lv_num_hist_banc_h              NUMBER(3);
   lv_num_hist_otro_h              NUMBER(3);
   lv_ultimo_num_hist              NUMBER(3);
   lv_es_pago_banco                NUMBER(12);
   lv_cont_bucle                   NUMBER(12);
   lv_ind_error                    NUMBER(1);

 BEGIN

  lv_des_log:=NULL;

  fin_pkg_gener.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,p_cod_usua,lv_id_proc_ejec);
  lv_des_log:= trunc(SYSDATE) || ' Inicia Actua Carte --> invoca actualizacion de repor estad ' ;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec,lv_des_log);

  lv_ind_actu := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IndicadorActualizacionV2');

  IF lv_ind_actu = 'S' THEN
   COB_PKG_REPOR_ESTAD.COB_PR_CARGA_ESTAD_RECUP_COBRA(p_cod_pais,p_cod_soci);
   COB_PKG_REPOR_ESTAD.COB_PR_ACTUA_ESTAD_RECUP_COBRA;
  ELSE
  COB_PKG_REPOR_ESTAD.COB_PR_CARGA_ESTAD_RECUP_COBRA(p_cod_pais,p_cod_soci);
  COB_PKG_REPOR_ESTAD.COB_PR_ACTUA_ESTAD_RECUP_COBRA(p_cod_pais,p_cod_soci);
  END IF;

  lv_cont_bucle := 0;

  lv_des_log:= ' Inicia update carte_movim ' ;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec,lv_des_log);

  FOR v_cart IN c_Cart LOOP

   lv_cod_cart := v_cart.cod_cart;
   lv_imp_deud_canc_car := v_cart.imp_deud_canc;
   lv_ind_error_car := v_cart.ind_erro;
   lv_imp_deud_asig := v_cart.imp_deud_asig;
   lv_ultimo_num_hist := 0;

   SELECT
    mcc.imp_paga - mcc.imp_movi + v_cart.imp_deud_asig,
    mcc.imp_pago,
    TRUNC(mcc.fec_ulti_movi),
    mcc.subp_oid_subp_ulti,
    mcc.val_ulti_nume_hist
   INTO
    lv_imp_deud_canc_ccc,
    lv_imp_pago_x,
    lv_fec_pago_x,
    lv_oid_subp_ulti_c,
    lv_num_hist_x
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.oid_movi_cc=v_cart.mvcc_oid_movi_cc;

   -- Obteniendo el total de los pagos bancarios en el Historico
   SELECT NVL(SUM(hcc.imp_pago),0) , MAX(hcc.fec_movi), NVL(MAX(hcc.num_hist),0)
   INTO lv_imp_pago_banc_h, lv_fec_pago_banc_h, lv_num_hist_banc_h
   FROM ccc_histo_movim_cc hcc
   WHERE hcc.mvcc_oid_movi_cc=v_cart.mvcc_oid_movi_cc
     AND   hcc.num_hist <> 0
     AND EXISTS (
         SELECT NULL
         FROM cob_subpr_pago_banca spg
         WHERE spg.ind_acti = 1
         AND spg.cb_oid_subp = hcc.subp_oid_subp
         AND spg.cod_pais = p_cod_pais )
     AND   hcc.num_hist > v_cart.val_ulti_nume_hist
     AND   hcc.fec_movi <= v_cart.fec_cier;

         -- Obteniendo el total de otros abonos  en el Historico
         SELECT NVL(SUM(hcc2.imp_pago),0), MAX(hcc2.fec_movi), NVL(MAX(hcc2.num_hist),0)
         INTO lv_imp_pago_otro_h, lv_fec_pago_otro_h, lv_num_hist_otro_h
         FROM ccc_histo_movim_cc hcc2
         WHERE hcc2.mvcc_oid_movi_cc=v_cart.mvcc_oid_movi_cc
         AND   hcc2.num_hist <> 0
         AND NOT EXISTS (SELECT NULL FROM cob_subpr_pago_banca spg2
                                        WHERE spg2.ind_acti = 1
                                        AND spg2.cb_oid_subp = hcc2.subp_oid_subp
                                        AND spg2.cod_pais = p_cod_pais  )
         AND   hcc2.num_hist > v_cart.val_ulti_nume_hist
         AND   hcc2.fec_movi <= v_cart.fec_cier;

         -- Determina si el ultimo subproceso de ccc_movim_cuent_corri (v_oid_subp_ulti_c)
         --  corresponde a pago bancario
         SELECT COUNT(*)
         INTO lv_es_pago_banco
         FROM cob_subpr_pago_banca spg3
         WHERE spg3.ind_acti = 1
         AND spg3.cod_pais = p_cod_pais
         AND spg3.cb_oid_subp = lv_oid_subp_ulti_c;

         -- Acumula ultimo pago registrado en ccc_movim_cuent_corri segun sea pago bancario o no
         -- y ademas el pago haya sido hecho antes de la fecha de cierre de la cartera
         IF lv_fec_pago_x <= v_cart.fec_cier THEN
            lv_ultimo_num_hist := lv_num_hist_x;
             IF lv_es_pago_banco > 0 THEN
                lv_imp_pago_banc_h := lv_imp_pago_banc_h + lv_imp_pago_x;
                IF  lv_fec_pago_banc_h IS NULL THEN
                    lv_fec_pago_banc_h := lv_fec_pago_x;
                ELSE
                    IF  lv_fec_pago_banc_h < lv_fec_pago_x THEN
                        lv_fec_pago_banc_h := lv_fec_pago_x;
                    END IF;
                END IF;
             ELSE
                lv_imp_pago_otro_h := lv_imp_pago_otro_h + lv_imp_pago_x;
                lv_fec_pago_otro_h := lv_fec_pago_x;
             END IF;
         ELSE

         -- si pago de ccc_movim_cuent_corri fue posterior a la fecha de cierre de la cartera
         -- se trabajan solo datos de ccc_hist_movim_cc
            IF  lv_imp_pago_banc_h > 0 or lv_imp_pago_otro_h > 0 then
                 IF   lv_num_hist_banc_h > lv_num_hist_otro_h then
                      lv_ultimo_num_hist := lv_num_hist_banc_h;
                 ELSE
                      lv_ultimo_num_hist := lv_num_hist_otro_h;
                 END IF;
             END IF;
         END IF;

         -- si no hubieron nuevos pagos bancarios,  se conserva la ultima fecha de pago bancario
         IF  lv_imp_pago_banc_h = 0 THEN
             lv_fec_pago_banc_h := v_cart.fec_ult_pago_banc;
         END IF;


         -- considerar solo pagos bancarios
         --   v_imp_deud_canc := v_imp_deud_canc - v_imp_pago_otro_h;

         IF lv_ultimo_num_hist > v_cart.val_ulti_nume_hist THEN
            lv_imp_deud_canc := lv_imp_deud_canc_car + lv_imp_pago_banc_h + lv_imp_pago_otro_h;
            IF  lv_ind_error_car = 0 THEN
                IF  lv_imp_deud_canc_ccc < lv_imp_deud_canc THEN
                    --dbms_output.put_line('Error 1: ' || v_cart.mvcc_oid_movi_cc || ' ' || lv_imp_deud_canc_ccc || ' can_c: ' ||  lv_imp_deud_canc_car || ' ' || lv_imp_pago_banc_h || ' ' || lv_imp_pago_otro_h);
                    lv_ind_error := 1;
                ELSE
                    IF  lv_imp_deud_asig < lv_imp_deud_canc THEN
                        --dbms_output.put_line('Error 2: ' || v_cart.mvcc_oid_movi_cc || ' ' || lv_imp_deud_asig || ' canc: ' ||  lv_imp_deud_canc);
                        lv_ind_error := 2;
                    ELSE
                        lv_ind_error := 0;
                    END IF;
                END IF;
             ELSE
                  lv_ind_error := lv_ind_error_car;
             END IF;

             -- Actualizando la Carte de Movimientos
             UPDATE cob_carte_movim_cuent_clien c
             SET c.imp_deud_canc=lv_imp_deud_canc,
                 c.imp_pago_banc = c.imp_pago_banc + lv_imp_pago_banc_h,
                 c.imp_pago_otro = c.imp_pago_otro + lv_imp_pago_otro_h,
                 c.fec_ult_pago_banc = lv_fec_pago_banc_h,
                 c.val_ulti_nume_hist = lv_ultimo_num_hist,
                 c.imp_deud_pend = c.imp_deud_asig - lv_imp_deud_canc,
                 c.usu_modi = p_cod_usua,
                 c.fec_modi = SYSDATE,
                 c.ind_erro = lv_ind_error,
                 c.ind_deud_pend = CASE
                                     WHEN c.imp_deud_asig - lv_imp_deud_canc  <= 0
                                     THEN  0
                                     ELSE 1
                                     END
             WHERE c.mvcc_oid_movi_cc=v_cart.mvcc_oid_movi_cc
             AND   c.cod_cart = lv_cod_cart;

             --Actualizando la Cartera de Clientes
             UPDATE cob_detal_asign_carte d
             SET
                    ( d.imp_deud_canc, d.imp_pago_banc ,d.imp_pago_otro ,
                      d.fec_ult_pago_banc ,d.imp_deud_pend ) =
                       (SELECT SUM(c.imp_deud_canc),SUM(c.imp_pago_banc), SUM(c.imp_pago_otro),
                         MAX(c.fec_ult_pago_banc),SUM(c.imp_deud_pend)
                        FROM cob_carte_movim_cuent_clien c
                        WHERE c.cod_cart=lv_cod_cart
                        AND c.oid_clie=v_cart.oid_clie
                        GROUP BY c.oid_clie),
                    d.usu_modi = p_cod_usua,
                    d.fec_modi = SYSDATE
             WHERE d.oid_clie=v_cart.oid_clie
             AND d.cod_cart = lv_cod_cart;

         END IF;

         lv_cont_bucle := lv_cont_bucle + 1;

      END LOOP;

      -- Actualizando los compromisos de Pago
      UPDATE cob_detal_asign_carte d
      SET d.ind_comp_pago=2
      WHERE d.ind_comp_pago=1
      AND d.fec_comp_pago < trunc(SYSDATE)
      AND d.imp_deud_pend > d.imp_comp_pago;


      lv_des_log:= ' Inicia update detal_movim ' ;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec,lv_des_log);
      lv_cont_bucle := 0;
      FOR v_dmov IN c_dmov  LOOP

         lv_cod_cart := v_dmov.cod_cart;
         lv_imp_deud_canc_car := v_dmov.imp_deud_canc;
         lv_ind_error_car := v_dmov.ind_erro;
         lv_imp_deud_asig := v_dmov.imp_deud_asig;
         lv_ultimo_num_hist := 0;

         SELECT mcc.imp_paga - mcc.imp_movi + v_dmov.imp_deud_asig,
                      mcc.imp_pago, TRUNC(mcc.fec_ulti_movi), mcc.subp_oid_subp_ulti,
                      mcc.val_ulti_nume_hist
         INTO lv_imp_deud_canc_ccc, lv_imp_pago_x, lv_fec_pago_x, lv_oid_subp_ulti_c,
                   lv_num_hist_x
         FROM ccc_movim_cuent_corri mcc
         WHERE mcc.oid_movi_cc=v_dmov.mvcc_oid_movi_cc;

         -- Obteniendo el total de los pagos bancarios en el Historico
         SELECT NVL(SUM(hcc.imp_pago),0) , MAX(hcc.fec_movi), NVL(MAX(hcc.num_hist),0)
         INTO lv_imp_pago_banc_h, lv_fec_pago_banc_h, lv_num_hist_banc_h
         FROM ccc_histo_movim_cc hcc
         WHERE hcc.mvcc_oid_movi_cc=v_dmov.mvcc_oid_movi_cc
         AND   hcc.num_hist <> 0
         AND EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = hcc.subp_oid_subp
                     AND spg.cod_pais = p_cod_pais )
         AND   hcc.num_hist > v_dmov.val_ulti_nume_hist
         AND   hcc.fec_movi <= v_dmov.fec_cier;

         -- Obteniendo el total de otros abonos  en el Historico
         SELECT NVL(SUM(hcc2.imp_pago),0), MAX(hcc2.fec_movi), NVL(MAX(hcc2.num_hist),0)
         INTO lv_imp_pago_otro_h, lv_fec_pago_otro_h, lv_num_hist_otro_h
         FROM ccc_histo_movim_cc hcc2
         WHERE hcc2.mvcc_oid_movi_cc=v_dmov.mvcc_oid_movi_cc
         AND   hcc2.num_hist <> 0
         AND NOT EXISTS (SELECT NULL FROM cob_subpr_pago_banca spg2
                                        WHERE spg2.ind_acti = 1
                                        AND spg2.cb_oid_subp = hcc2.subp_oid_subp
                                        AND spg2.cod_pais = p_cod_pais  )
         AND   hcc2.num_hist > v_dmov.val_ulti_nume_hist
         AND   hcc2.fec_movi <= v_dmov.fec_cier;

         -- Determina si el ultimo subproceso de ccc_movim_cuent_corri (v_oid_subp_ulti_c)
         --  corresponde a pago bancario
         SELECT COUNT(*)
         INTO lv_es_pago_banco
         FROM cob_subpr_pago_banca spg3
         WHERE spg3.ind_acti = 1
         AND spg3.cod_pais = p_cod_pais
         AND spg3.cb_oid_subp = lv_oid_subp_ulti_c;

         -- Acumula ultimo pago registrado en ccc_movim_cuent_corri segun sea pago bancario o no
         -- y ademas el pago haya sido hecho antes de la fecha de cierre de la cartera
         IF lv_fec_pago_x <= v_dmov.fec_cier THEN
            lv_ultimo_num_hist := lv_num_hist_x;
             IF lv_es_pago_banco > 0 THEN
                lv_imp_pago_banc_h := lv_imp_pago_banc_h + lv_imp_pago_x;
                IF  lv_fec_pago_banc_h IS NULL THEN
                    lv_fec_pago_banc_h := lv_fec_pago_x;
                ELSE
                    IF  lv_fec_pago_banc_h < lv_fec_pago_x THEN
                        lv_fec_pago_banc_h := lv_fec_pago_x;
                    END IF;
                END IF;
             ELSE
                lv_imp_pago_otro_h := lv_imp_pago_otro_h + lv_imp_pago_x;
                lv_fec_pago_otro_h := lv_fec_pago_x;
             END IF;
         ELSE

         -- si pago de ccc_movim_cuent_corri fue posterior a la fecha de cierre de la cartera
         -- se trabajan solo datos de ccc_hist_movim_cc
            IF  lv_imp_pago_banc_h > 0 or lv_imp_pago_otro_h > 0 then
                 IF   lv_num_hist_banc_h > lv_num_hist_otro_h then
                      lv_ultimo_num_hist := lv_num_hist_banc_h;
                 ELSE
                      lv_ultimo_num_hist := lv_num_hist_otro_h;
                 END IF;
             END IF;
         END IF;

         -- si no hubieron nuevos pagos bancarios,  se conserva la ultima fecha de pago bancario
         IF  lv_imp_pago_banc_h = 0 THEN
             lv_fec_pago_banc_h := v_dmov.fec_ult_pago_banc;
         END IF;


         --   se omite :  -- considerar solo pagos bancarios
         --   v_imp_deud_canc := v_imp_deud_canc - v_imp_pago_otro_h;

         IF lv_ultimo_num_hist > v_dmov.val_ulti_nume_hist THEN
            lv_imp_deud_canc := lv_imp_deud_canc_car + lv_imp_pago_banc_h + lv_imp_pago_otro_h;
            IF  lv_ind_error_car = 0 THEN
                IF  lv_imp_deud_canc_ccc < lv_imp_deud_canc THEN
                    --dbms_output.put_line('Error 1: ' || v_dmov.mvcc_oid_movi_cc || ' ' || lv_imp_deud_canc_ccc || ' can_c: ' ||  lv_imp_deud_canc_car || ' ' || lv_imp_pago_banc_h || ' ' || lv_imp_pago_otro_h);
                    lv_ind_error := 1;
                ELSE
                    IF  lv_imp_deud_asig < lv_imp_deud_canc THEN
                        --dbms_output.put_line('Error 2: ' || v_dmov.mvcc_oid_movi_cc || ' ' || lv_imp_deud_asig || ' canc: ' ||  lv_imp_deud_canc);
                        lv_ind_error := 2;
                    ELSE
                        lv_ind_error := 0;
                    END IF;
                END IF;
             ELSE
                  lv_ind_error := lv_ind_error_car;
             END IF;

             -- Actualizando el detalle de Movimientos
             UPDATE cob_detal_movim_carte c
             SET c.imp_deud_canc= lv_imp_deud_canc,
                 c.imp_pago_banc = c.imp_pago_banc + lv_imp_pago_banc_h,
                 c.imp_pago_otro = c.imp_pago_otro + lv_imp_pago_otro_h,
                 c.fec_ult_pago_banc = lv_fec_pago_banc_h,
                 c.val_ulti_nume_hist = lv_ultimo_num_hist,
                 c.imp_deud_pend = c.imp_deud_asig - lv_imp_deud_canc,
                 c.usu_modi = p_cod_usua,
                 c.fec_modi = SYSDATE,
                 c.ind_erro = lv_ind_error,
                 c.Ind_Deud_Pend = CASE
                                     WHEN c.imp_deud_asig - lv_imp_deud_canc  <= 0
                                     THEN  0
                                     ELSE  c.Ind_Deud_Pend
                                     END
             WHERE c.mvcc_oid_movi_cc=v_dmov.mvcc_oid_movi_cc
             AND   c.cod_cart = lv_cod_cart;

             --lv_des_log:= ' Reg. Act. en cob_detal_movim_carte: '  || SQL%ROWCOUNT  || '  oid_movi: ' || v_dmov.mvcc_oid_movi_cc ;
             --fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec,lv_des_log);

    --Actualizando la Cartera de Clientes
    UPDATE cob_detal_asign_carte d
    SET
     ( d.imp_deud_canc, d.imp_pago_banc ,d.imp_pago_otro ,
       d.fec_ult_pago_banc ,d.imp_deud_pend ) =
     (SELECT SUM(c.imp_deud_canc),SUM(c.imp_pago_banc), SUM(c.imp_pago_otro),
             MAX(c.fec_ult_pago_banc),SUM(c.imp_deud_pend)
      FROM cob_detal_movim_carte c
      WHERE c.cod_cart= lv_cod_cart
        AND c.oid_clie=v_dmov.oid_clie
      GROUP BY c.oid_clie),
      d.usu_modi = p_cod_usua,
      d.fec_modi = SYSDATE
    WHERE d.oid_clie = v_dmov.oid_clie
      AND d.cod_cart = lv_cod_cart;

   END IF;

   lv_cont_bucle := lv_cont_bucle + 1;

  END LOOP;



  lv_des_log:='Fin del Proceso de Actualizacion de Cartera ';
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec,lv_des_log);
  fin_pkg_gener.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais,p_cod_soci,lv_cod_modu,lv_cod_proc_actu_cart,lv_id_proc_ejec, 2);


 END COB_PR_ACTUA_CARTE;

 PROCEDURE COB_PR_INSER_GESTI_CARTE(
  p_cod_cart                       IN   cob_gesti_cobra_pais.cod_cart%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda.cod_etap_deud%TYPE,
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                       IN   cob_gesti_cobra_pais.cod_clie%TYPE,
  p_tip_acci                       IN   cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
  p_cod_acc                        IN   cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
  p_val_obse                       IN   cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
  p_fecha                          IN   VARCHAR2 DEFAULT NULL,
  p_imp_pago                       IN   VARCHAR2 DEFAULT NULL,
  p_cod_periodo                    IN   VARCHAR2)
 IS

  lv_reg_cob_gesti_cobra_pais      cob_gesti_cobra_pais%ROWTYPE;
  lv_ind_gest_supe                 cob_accio_cobra_pais.ind_acci_supe%TYPE;
  lv_tipo_acci_cobr                cob_accio_cobra_pais.tipo_acci_cobr%TYPE;
  lv_ind_cart_reba                 cob_accio_cobra_pais.ind_cart_reba%TYPE;
  lv_cod_usua_cobr_supe            cob_usuar_cobra_pais.cod_usua_cobr%TYPE;

 BEGIN

  lv_reg_cob_gesti_cobra_pais.cod_cart := p_cod_cart;
  lv_reg_cob_gesti_cobra_pais.cod_usua_cobr_pais := p_cod_usua_cobr;
  lv_reg_cob_gesti_cobra_pais.cod_clie := p_cod_clie;
  lv_reg_cob_gesti_cobra_pais.Cod_Etap_Deud := p_cod_etap_deud; 
  lv_reg_cob_gesti_cobra_pais.cod_peri := p_cod_periodo;
  
  SELECT SEQ_COB_NUM_ORDE.NEXTVAL
  INTO lv_reg_cob_gesti_cobra_pais.num_orde
  FROM dual;

  lv_reg_cob_gesti_cobra_pais.tip_acci_cobr := p_tip_acci;
  lv_reg_cob_gesti_cobra_pais.cod_acci_cobr := p_cod_acc;
  lv_reg_cob_gesti_cobra_pais.fec_gest := to_char(trunc(SYSDATE));
  lv_reg_cob_gesti_cobra_pais.val_hora_gest := TO_CHAR(SYSDATE,'HH24:MI:SS');
  lv_reg_cob_gesti_cobra_pais.val_obse := TRIM(p_val_obse);
  lv_reg_cob_gesti_cobra_pais.fec_acci_cobr := NVL(TO_DATE(p_fecha,'DD/MM/YYYY'),NULL);
  lv_reg_cob_gesti_cobra_pais.ppa_imp_pago := NVL(TO_NUMBER(p_imp_pago,'999999.99'),NULL);

  BEGIN

   SELECT ac.tipo_acci_cobr,ac.Ind_Gest_Supe,ac.ind_repr , ac.ind_comp_pago, ac.ind_cart_reba
   INTO lv_tipo_acci_cobr,lv_ind_gest_supe,lv_reg_cob_gesti_cobra_pais.ind_repr, lv_reg_cob_gesti_cobra_pais.ind_comp_pago, lv_ind_cart_reba
   FROM cob_accio_cobra_pais ac
   WHERE ac.cod_etap_deud=p_cod_etap_deud
     AND ac.cod_acci_cobr=p_cod_acc;


   --  Validando si la Gestion es Administrativa
   IF lv_tipo_acci_cobr <> 'A' THEN

    UPDATE cob_detal_asign_carte d
    SET
     d.ind_cart_gest = 1,
     d.ind_Comp_Pago = lv_reg_cob_gesti_cobra_pais.ind_comp_pago,
     d.fec_comp_pago =lv_reg_cob_gesti_cobra_pais.fec_acci_cobr,
     d.imp_comp_pago = lv_reg_cob_gesti_cobra_pais.ppa_imp_pago,
     d.cod_ulti_gest_cobr = p_cod_acc,
     d.ind_gest_supe = lv_ind_gest_supe,
     d.val_obse_ulti_gest = p_val_obse
    WHERE d.cod_clie=p_cod_clie
      AND d.cod_cart=p_cod_cart;

   ELSE

    -- La gestion es administrativa
    IF lv_ind_cart_reba = 0 THEN

     UPDATE cob_detal_asign_carte d
     SET
      d.ind_cart_gest=1,
      d.cod_ulti_gest_admi=p_cod_acc,
      d.ind_gest_supe=lv_ind_gest_supe
     WHERE d.cod_clie=p_cod_clie
       AND d.cod_cart=p_cod_cart;
           ELSE

              SELECT c.cod_usua_cobr
              INTO lv_cod_usua_cobr_supe
              FROM cob_usuar_cobra_pais c
              WHERE c.ind_supe=1;

              UPDATE cob_detal_asign_carte d
              SET d.ind_cart_gest=1,
                      d.cod_ulti_gest_admi=p_cod_acc,
                      d.ind_gest_supe=lv_ind_gest_supe,
                      d.cod_usua_cobr=lv_cod_usua_cobr_supe,
                      d.Ind_Cart_Supe=2
              WHERE d.cod_clie=p_cod_clie
              AND d.cod_cart=p_cod_cart;

           END IF;

      END IF;

      EXCEPTION
       WHEN OTHERS THEN
         NULL;
      END;

      INSERT INTO cob_gesti_cobra_pais VALUES lv_reg_cob_gesti_cobra_pais;

 END COB_PR_INSER_GESTI_CARTE;

 PROCEDURE COB_PR_INSER_GESTI_COBRA(
  p_cod_cart                       IN   cob_gesti_cobra_pais.cod_cart%TYPE,
  p_cod_clie                       IN   cob_gesti_cobra_pais.cod_clie%TYPE,
  p_tip_acci                       IN   cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
  p_cod_acc                        IN   cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
  p_val_obse                       IN   cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
  p_fecha                          IN   VARCHAR2 DEFAULT NULL,
  p_imp_pago                       IN   VARCHAR2 DEFAULT NULL)
 IS

  v_reg_cob_gesti_cobra_pais       cob_gesti_cobra_pais%ROWTYPE;
  v_ind_gest_supe                  cob_accio_cobra_pais.ind_acci_supe%TYPE;
  v_tipo_acci_cobr                 cob_accio_cobra_pais.tipo_acci_cobr%TYPE;

 BEGIN

  v_reg_cob_gesti_cobra_pais.cod_cart:=p_cod_cart;
  v_reg_cob_gesti_cobra_pais.cod_clie:=p_cod_clie;

  SELECT SEQ_COB_NUM_ORDE.NEXTVAL
  INTO v_reg_cob_gesti_cobra_pais.num_orde
  FROM dual;

  v_reg_cob_gesti_cobra_pais.tip_acci_cobr :=p_tip_acci;
  v_reg_cob_gesti_cobra_pais.cod_acci_cobr :=p_cod_acc;
  v_reg_cob_gesti_cobra_pais.fec_gest :=to_char(trunc(SYSDATE));
  v_reg_cob_gesti_cobra_pais.val_hora_gest :=TO_CHAR(SYSDATE,'HH24:MI:SS');
  v_reg_cob_gesti_cobra_pais.val_obse :=TRIM(p_val_obse);

  --  Validando si la Gestion es Administrativa
  SELECT ac.tipo_acci_cobr,ac.Ind_Gest_Supe
  INTO v_tipo_acci_cobr,v_ind_gest_supe
  FROM cob_accio_cobra_pais ac
  WHERE ac.cod_acci_cobr=p_cod_acc;

  IF v_tipo_acci_cobr<>'A' THEN

   IF p_cod_acc='VOL' THEN

    v_reg_cob_gesti_cobra_pais.Ind_Repr:=1;
    v_reg_cob_gesti_cobra_pais.ind_comp_pago:=0;

    IF p_fecha IS NOT NULL THEN
     v_reg_cob_gesti_cobra_pais.fec_acci_cobr:=TO_DATE(p_fecha,'DD/MM/YYYY');
    END IF;

   ELSIF

    p_cod_acc='PPA' THEN
    v_reg_cob_gesti_cobra_pais.ind_comp_pago:=1;
    v_reg_cob_gesti_cobra_pais.Ind_Repr:=0;
    v_reg_cob_gesti_cobra_pais.fec_acci_cobr:=TO_DATE(p_fecha,'DD/MM/YYYY');
    v_reg_cob_gesti_cobra_pais.ppa_imp_pago:=TO_NUMBER(p_imp_pago,'999999.99');

   ELSE
    v_reg_cob_gesti_cobra_pais.Ind_Repr:=0;
    v_reg_cob_gesti_cobra_pais.ind_comp_pago:=0;
   END IF;

   UPDATE cob_detal_asign_carte d
   SET
    d.ind_cart_gest = 1,
    d.Ind_Comp_Pago = v_reg_cob_gesti_cobra_pais.ind_comp_pago,
    d.fec_comp_pago =
       CASE
        WHEN p_cod_acc='PPA' THEN to_date(p_fecha,'DD/MM/YYYY')
       ELSE
        NULL
       END,
    d.imp_comp_pago =
       CASE
        WHEN p_cod_acc='PPA' THEN d.imp_deud_pend - TO_NUMBER(p_imp_pago,'999999.99')
        ELSE
           NULL
       END,
    d.cod_ulti_gest_cobr=p_cod_acc,
    d.cod_ulti_gest_admi='NGE',
    d.ind_gest_supe=v_ind_gest_supe,
    d.val_obse_ulti_gest = p_val_obse
   WHERE d.cod_clie=p_cod_clie
     AND d.cod_cart=p_cod_cart;

  ELSE

   v_reg_cob_gesti_cobra_pais.Ind_Repr:=0;
   v_reg_cob_gesti_cobra_pais.ind_comp_pago:=0;

   UPDATE cob_detal_asign_carte d
   SET
    d.ind_cart_gest = 1,
    d.cod_ulti_gest_admi = p_cod_acc,
    d.ind_gest_supe = v_ind_gest_supe,
    d.val_obse_ulti_gest = p_val_obse
   WHERE d.cod_clie = p_cod_clie
     AND d.cod_cart = p_cod_cart;

  END IF;

  INSERT INTO cob_gesti_cobra_pais VALUES v_reg_cob_gesti_cobra_pais;

 END COB_PR_INSER_GESTI_COBRA;

 PROCEDURE COB_PR_INSER_GESTI_EJECU(
  p_cod_cart                       IN   cob_gesti_cobra_pais.cod_cart%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda.cod_etap_deud%TYPE,
  p_cod_peri                       IN   cob_detal_asign_carte.cod_peri%TYPE,
  p_cod_regi_clie                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_zona_clie                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_usua_cobr                  IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                       IN   cob_gesti_cobra_pais.cod_clie%TYPE,
  p_tip_acci                       IN   cob_gesti_cobra_pais.tip_acci_cobr%TYPE,
  p_cod_acc                        IN   cob_gesti_cobra_pais.cod_acci_cobr%TYPE,
  p_val_obse                       IN   cob_gesti_cobra_pais.val_obse%TYPE DEFAULT NULL,
  p_fecha                          IN   VARCHAR2 DEFAULT NULL,
  p_imp_pago                       IN   VARCHAR2 DEFAULT NULL)
 IS
  lv_reg_cob_gesti_cobra_pais           cob_gesti_cobra_pais%ROWTYPE;
  lv_ind_gest_supe                      cob_accio_cobra_pais.ind_acci_supe%TYPE;
  lv_tipo_acci_cobr                     cob_accio_cobra_pais.tipo_acci_cobr%TYPE;
  lv_ind_cart_reba                      cob_accio_cobra_pais.ind_cart_reba%TYPE;
  lv_cod_usua_cobr_supe                 cob_usuar_cobra_pais.cod_usua_cobr%TYPE;

 BEGIN

  lv_reg_cob_gesti_cobra_pais.cod_cart := p_cod_cart;
  lv_reg_cob_gesti_cobra_pais.cod_etap_deud := p_cod_etap_deud;
  lv_reg_cob_gesti_cobra_pais.cod_peri := p_cod_peri;
  lv_reg_cob_gesti_cobra_pais.cod_regi_clie := p_cod_regi_clie;
  lv_reg_cob_gesti_cobra_pais.cod_zona_clie := p_cod_zona_clie;
  lv_reg_cob_gesti_cobra_pais.cod_usua_cobr_pais := p_cod_usua_cobr;
  lv_reg_cob_gesti_cobra_pais.cod_clie := p_cod_clie;


  SELECT seq_cob_num_orde.NEXTVAL
  INTO lv_reg_cob_gesti_cobra_pais.num_orde
  FROM dual;

  lv_reg_cob_gesti_cobra_pais.tip_acci_cobr :=p_tip_acci;
  lv_reg_cob_gesti_cobra_pais.cod_acci_cobr :=p_cod_acc;
  lv_reg_cob_gesti_cobra_pais.fec_gest :=to_char(trunc(SYSDATE));
  lv_reg_cob_gesti_cobra_pais.val_hora_gest :=TO_CHAR(SYSDATE,'HH24:MI:SS');
  lv_reg_cob_gesti_cobra_pais.val_obse :=TRIM(p_val_obse);
  lv_reg_cob_gesti_cobra_pais.fec_acci_cobr:=NVL(TO_DATE(p_fecha,'DD/MM/YYYY'),NULL);
  lv_reg_cob_gesti_cobra_pais.ppa_imp_pago:=NVL(TO_NUMBER(p_imp_pago,'999999.99'),NULL);

  BEGIN

   SELECT ac.tipo_acci_cobr,ac.Ind_Gest_Supe,ac.ind_repr , ac.ind_comp_pago, ac.ind_cart_reba
   INTO lv_tipo_acci_cobr,lv_ind_gest_supe,lv_reg_cob_gesti_cobra_pais.ind_repr, lv_reg_cob_gesti_cobra_pais.ind_comp_pago, lv_ind_cart_reba
   FROM cob_accio_cobra_pais ac
   WHERE ac.cod_etap_deud=p_cod_etap_deud
     AND ac.cod_acci_cobr=p_cod_acc;


  --  Validando si la Gestion es Administrativa
  IF lv_tipo_acci_cobr <> 'A' THEN

   UPDATE cob_detal_asign_carte d
   SET
    d.ind_cart_gest = 1,
    d.ind_Comp_Pago = lv_reg_cob_gesti_cobra_pais.ind_comp_pago,
    d.fec_comp_pago = lv_reg_cob_gesti_cobra_pais.fec_acci_cobr,
    d.imp_comp_pago = lv_reg_cob_gesti_cobra_pais.ppa_imp_pago,
    d.cod_ulti_gest_cobr = p_cod_acc,
    d.ind_gest_supe = lv_ind_gest_supe,
    d.val_obse_ulti_gest = p_val_obse
   WHERE d.cod_clie = p_cod_clie
     AND d.cod_cart = p_cod_cart;

  ELSE

   -- La gestion es administrativa
   IF lv_ind_cart_reba = 0 THEN

    UPDATE cob_detal_asign_carte d
    SET
     d.ind_cart_gest = 1,
     d.cod_ulti_gest_admi = p_cod_acc,
     d.ind_gest_supe = lv_ind_gest_supe,
     d.val_obse_ulti_gest = p_val_obse
    WHERE d.cod_clie=p_cod_clie
      AND d.cod_cart=p_cod_cart;

   ELSE

    SELECT c.cod_usua_cobr
    INTO lv_cod_usua_cobr_supe
    FROM cob_usuar_cobra_pais c
    WHERE c.ind_supe = 1;

    UPDATE cob_detal_asign_carte d
    SET
     d.ind_cart_gest = 1,
     d.cod_ulti_gest_admi = p_cod_acc,
     d.ind_gest_supe = lv_ind_gest_supe,
     d.cod_usua_cobr = lv_cod_usua_cobr_supe,
     d.ind_cart_supe = 2,
     d.val_obse_ulti_gest = p_val_obse
    WHERE d.cod_clie = p_cod_clie
      AND d.cod_cart = p_cod_cart;

   END IF;

  END IF;

 EXCEPTION
  WHEN OTHERS THEN
   NULL;
 END;

  INSERT INTO cob_gesti_cobra_pais VALUES lv_reg_cob_gesti_cobra_pais;

 END COB_PR_INSER_GESTI_EJECU;

    PROCEDURE COB_PR_ACTUA_TELEF_DEUDO(
      p_cod_cart                     cob_gesti_cobra_pais.cod_cart%TYPE,
      p_cod_etap_deud          cob_etapa_deuda.cod_etap_deud%TYPE,
      p_cod_clie                      cob_gesti_cobra_pais.cod_clie%TYPE,
      p_cod_tipo_tele             mae_tipo_comun.cod_tipo_comu%TYPE,
      p_num_tele                     cob_detal_asign_carte.num_tele_fijo%TYPE)
    IS
    BEGIN

       IF p_cod_tipo_tele='TF' THEN
          UPDATE cob_detal_asign_carte d
          SET d.num_tele_fijo=p_num_tele
          WHERE d.cod_cart=p_cod_cart
          AND d.cod_etap_deud=p_cod_etap_deud
          AND d.cod_clie= p_cod_clie;

       ELSIF p_cod_tipo_tele='TT' THEN

              UPDATE cob_detal_asign_carte d
              SET d.num_tele_trab=p_num_tele
              WHERE d.cod_cart=p_cod_cart
              AND d.cod_etap_deud=p_cod_etap_deud
              AND d.cod_clie= p_cod_clie;

        ELSIF p_cod_tipo_tele='TM' THEN

              UPDATE cob_detal_asign_carte d
              SET d.num_tele_movi=p_num_tele
              WHERE d.cod_cart=p_cod_cart
              AND d.cod_etap_deud=p_cod_etap_deud
              AND d.cod_clie= p_cod_clie;

         END IF;

    END COB_PR_ACTUA_TELEF_DEUDO;

 PROCEDURE COB_PR_REBAJ_TEMPO_CARTE_DEUDO(
  p_cod_cart                     IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_usua_cobr                IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE)
 IS
 
  lv_cod_usua_supe               cob_usuar_cobra_pais.cod_usua_cobr%TYPE;
  lv_reg_cob_gesti_cobra_pais    cob_gesti_cobra_pais%ROWTYPE;
    
 BEGIN
  
  BEGIN
    
   SELECT NVL(ed.cod_usua_supe,'COBREBAJA')
   INTO lv_cod_usua_supe
   FROM cob_etapa_deuda_pais ed
   WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  EXCEPTION
  
   WHEN no_data_found THEN
    lv_cod_usua_supe := 'REBAJA'; 
        
  END;

  UPDATE cob_detal_asign_carte d
  SET 
   d.cod_usua_cobr=lv_cod_usua_supe,
   d.Ind_Cart_Supe=2,
   d.Ind_Gest_Supe=3
  WHERE d.cod_cart=p_cod_cart
    AND d.cod_etap_deud=p_cod_etap_deud
    AND d.cod_clie=p_cod_clie;

  lv_reg_cob_gesti_cobra_pais.cod_cart := p_cod_cart;
  lv_reg_cob_gesti_cobra_pais.cod_usua_cobr_pais := p_cod_usua_cobr;
  lv_reg_cob_gesti_cobra_pais.cod_clie := p_cod_clie;

  SELECT SEQ_COB_NUM_ORDE.NEXTVAL
  INTO lv_reg_cob_gesti_cobra_pais.num_orde
  FROM dual;

  SELECT cap.tipo_acci_cobr , cap.cod_acci_cobr
  INTO lv_reg_cob_gesti_cobra_pais.tip_acci_cobr, lv_reg_cob_gesti_cobra_pais.cod_acci_cobr
  FROM cob_accio_cobra_pais cap
  WHERE cap.cod_etap_deud=p_cod_etap_deud
    AND cap.ind_cart_reba=1;

  lv_reg_cob_gesti_cobra_pais.fec_gest :=to_char(trunc(SYSDATE));
  lv_reg_cob_gesti_cobra_pais.val_hora_gest :=TO_CHAR(SYSDATE,'HH24:MI:SS');

  INSERT INTO cob_gesti_cobra_pais VALUES lv_reg_cob_gesti_cobra_pais;

 END COB_PR_REBAJ_TEMPO_CARTE_DEUDO;

 PROCEDURE COB_PR_REBAJ_DEFIN_CARTE_DEUDO(
  p_cod_cart                     IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_usua_cobr                IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE)
 IS

  lv_cod_usua_supe               cob_usuar_cobra_pais.cod_usua_cobr%TYPE;
  lv_oid_clie                    mae_clien.oid_clie%TYPE;
  lv_reg_cob_gesti_cobra_pais    cob_gesti_cobra_pais%ROWTYPE;

 BEGIN
  
  SELECT NVL(ed.cod_usua_supe,'COBREBAJA')
  INTO lv_cod_usua_supe
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  UPDATE cob_detal_asign_carte d
  SET
   d.cod_usua_cobr = lv_cod_usua_supe,
   d.Ind_Cart_Supe = 2,
   d.Ind_Gest_Supe = 3
  WHERE d.cod_cart = p_cod_cart
    AND d.cod_etap_deud = p_cod_etap_deud
    AND d.cod_clie = p_cod_clie;

  lv_reg_cob_gesti_cobra_pais.cod_cart := p_cod_cart;
  lv_reg_cob_gesti_cobra_pais.cod_usua_cobr_pais := p_cod_usua_cobr;
  lv_reg_cob_gesti_cobra_pais.cod_clie := p_cod_clie;

  SELECT SEQ_COB_NUM_ORDE.NEXTVAL
  INTO lv_reg_cob_gesti_cobra_pais.num_orde
  FROM dual;

  SELECT cap.tipo_acci_cobr , cap.cod_acci_cobr
  INTO lv_reg_cob_gesti_cobra_pais.tip_acci_cobr, lv_reg_cob_gesti_cobra_pais.cod_acci_cobr
  FROM cob_accio_cobra_pais cap
  WHERE cap.cod_etap_deud = p_cod_etap_deud
    AND cap.ind_cart_reba = 1;

  lv_reg_cob_gesti_cobra_pais.fec_gest :=to_char(trunc(SYSDATE));
  lv_reg_cob_gesti_cobra_pais.val_hora_gest :=TO_CHAR(SYSDATE,'HH24:MI:SS');

  INSERT INTO cob_gesti_cobra_pais VALUES lv_reg_cob_gesti_cobra_pais;

  SELECT mc.oid_clie
  INTO lv_oid_clie
  FROM mae_clien mc
  WHERE mc.cod_clie = p_cod_clie;

  INSERT INTO cob_carte_movim_clien_excep
   SELECT
    m.mvcc_oid_movi_cc,
    p_cod_etap_deud,
    p_cod_cart,
    lv_oid_clie,
    USER,
    SYSDATE
   FROM cob_carte_movim_cuent_clien m
   WHERE m.cod_cart = p_cod_cart
     AND m.oid_clie = lv_oid_clie;

  INSERT INTO cob_carte_movim_clien_excep
   SELECT
    dm.mvcc_oid_movi_cc,
    p_cod_etap_deud,
    p_cod_cart,
    lv_oid_clie,
    USER,
    SYSDATE
   FROM cob_detal_movim_carte dm
   WHERE dm.cod_cart = p_cod_cart
     AND dm.oid_clie = lv_oid_clie;

 END COB_PR_REBAJ_DEFIN_CARTE_DEUDO;

 PROCEDURE COB_PR_ASIGN_CARTE_AUTOM(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_modu                       IN   fin_proce_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS
 CURSOR c_cart
 IS
  SELECT
     c.cod_etap_deud,
     c.cod_peri,
     c.cod_regi,
     c.cod_zona,
     c.fec_gene_cart
  FROM
     cob_crono_carte c
  WHERE c.fec_gene_cart <= TRUNC(SYSDATE)
    AND c.ind_gene_cart = 0
    AND c.cod_cart IS NULL
  ORDER BY cod_peri ASC, cod_regi ASC ,cod_zona DESC;

 /*
 CURSOR c_etap
 IS
  SELECT
     dp.cod_etap_deud,
     dp.val_edad_inic,
     dp.val_edad_fina
  FROM
     cob_etapa_deuda_pais dp
  WHERE dp.ind_acti=1
  ORDER BY dp.num_secu_etap DESC;
 */

  lv_cod_pais                       seg_pais.cod_pais%TYPE;
  lv_cod_modu                       fin_proce_modul.cod_modu%TYPE;
  lv_cod_proc                       fin_proce_modul.cod_proc%TYPE;
  lv_ind_tipo_asig                  cob_param_gener.val_para%TYPE;
  lv_id_proc_ejec_actu              fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_cod_cart                       cob_cabec_asign_carte.cod_cart%TYPE;
  reg_cob_carte_asign_proce         cob_tempo_carte_asign_proce%ROWTYPE;
  lv_num_lote_asign                 cob_cabec_asign_carte.num_lote_asign%TYPE;

 BEGIN

  p_cod_erro:= 'procesoCOBAsignacionCarteraForm.exito.proceso';

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_modu := gc_cod_modu;
  lv_cod_proc := gc_cod_proc_asig_auto;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,p_cod_usua,lv_id_proc_ejec_actu,p_cod_erro);

  lv_num_lote_asign := COB_PKG_GENER.COB_FN_OBTIE_NUMER_LOTE;

  lv_des_log:='Inicio Asignacion Automatica: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  IF p_cod_erro IS NOT NULL THEN
   GOTO errors;
  END IF;

  lv_des_log:='Obteniendo el Tipo de Asignacion: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  SELECT COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IND_TIPO_ASIG')
  INTO lv_ind_tipo_asig
  FROM DUAL;

  IF lv_ind_tipo_asig IS NULL THEN
   p_cod_erro:='procesoCOBAsignacionCarteraForm.error.indicadorTipoAsignacion.no.configurado';
   GOTO errors;
  END IF;

  DELETE FROM cob_tempo_carte_asign_proce;

  --IF lv_ind_tipo_asig='C' THEN

   -- Asignacion de Cartera en base al Cronograma de Cartera
   lv_des_log:='Asignacion Por Cronograma' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   lv_des_log:='Obteniendo las Carteras a Asignarse' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FOR v_cart IN c_cart LOOP

    lv_des_log:=' *** Inicio Asignando Etapa : ' || v_cart.cod_etap_deud ||
                                ' Campa?a ' ||  v_cart.cod_peri ||
                                ' Region ' || v_cart.cod_regi ||
                                ' Zona ' || v_cart.cod_zona ||
                                ' Fecha Generacion ' || v_cart.fec_gene_cart;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    cob_pr_asign_carte_indiv(
                  lv_cod_pais,
                  v_cart.cod_etap_deud,
                  lv_num_lote_asign,
                  v_cart.cod_peri,
                  v_cart.cod_regi,
                  v_cart.cod_zona,
                  v_cart.fec_gene_cart,
                  lv_ind_tipo_asig,
                  p_cod_usua,
                  lv_cod_cart,
                  p_cod_erro);


    IF p_cod_erro IS NOT NULL THEN
     GOTO errors;
    END IF;

    lv_des_log:=' *** Fin Asignacion';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 1);

    reg_cob_carte_asign_proce.cod_cart := lv_cod_cart;
    reg_cob_carte_asign_proce.usu_crea := p_cod_usua;

    INSERT INTO cob_tempo_carte_asign_proce VALUES reg_cob_carte_asign_proce;

    SELECT COUNT(*)
    INTO lv_cont
    FROM cob_tempo_carte_asign_proce;

    lv_des_log:='Carteras asignadas **** '  || lv_cont;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   END LOOP;

   lv_des_log:='Carteras asignadas' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 1);

  /*
  ELSIF lv_ind_tipo_asig='V' THEN

   -- Asignacion de Cartera en base a la edad de las fechas de vencimiento
   lv_des_log:='Asignacion Por Fechas de Vencimiento' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FOR v_etap IN c_etap LOOP

    lv_des_log:='   Asignando Etapa : ' ||  v_etap.cod_etap_deud ;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    cob_pr_asign_carte_indiv(
                  lv_cod_pais,
                  p_num_lote_asign,
                  v_etap.cod_etap_deud,
                  'T',
                  'T',
                  'T',
                  lv_ind_tipo_asig,
                  p_cod_usua,
                  lv_cod_cart,
                  p_cod_erro);

    IF p_cod_erro IS NOT NULL THEN
     GOTO errors;
    END IF;

   END LOOP;


  ELSE

   lv_des_log:='Tipo de Asignacion Configurado Incorrectamente' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 9);

  END IF;
  */

  lv_des_log:='Fin Asignacion Automatica: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_Cod_proc,lv_id_proc_ejec_actu, 2);

  p_cod_erro:= 'procesoCOBAsignacionCarteraForm.exito.proceso';

  lv_des_log:='Carteras asignadas ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  --cob_pr_asign_carte_email_ejecu;
  --cob_pr_asign_carte_email_super;

  lv_des_log:='Se enviaron los emails a los Ejecutivos y a los Supervisores ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  /*
  INT_PKG_COB.INT_PR_COB_GENER_INFOR_COBRA(p_cod_usua);
  lv_des_log:='Se envio la informacion de Cobranza a los Cobradore ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,p_cod_modu,P_cod_proc,lv_id_proc_ejec_actu,lv_des_log);
  */

  RETURN;

  <<errors>>

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 9);

 EXCEPTION

  WHEN OTHERS THEN
   RAISE_APPLICATION_ERROR(-20123, 'Ha ocurrido un error');

 END COB_PR_ASIGN_CARTE_AUTOM;

 PROCEDURE COB_PR_ASIGN_CARTE_AUTOM(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE,
  p_num_lote_asign               OUT  NUMBER,
  p_cod_erro                     OUT  VARCHAR2)
 IS

 CURSOR c_cart
 IS
  SELECT
     c.cod_etap_deud,
     c.cod_peri,
     c.cod_regi,
     c.cod_zona,
     c.fec_gene_cart
  FROM
     cob_crono_carte c
  WHERE c.fec_gene_cart <= TRUNC(SYSDATE)
    AND c.ind_gene_cart = 0
    AND c.cod_cart IS NULL
  ORDER BY cod_peri ASC, cod_regi ASC ,cod_zona DESC;

 /*
 CURSOR c_etap
 IS
  SELECT
     dp.cod_etap_deud,
     dp.val_edad_inic,
     dp.val_edad_fina
  FROM
     cob_etapa_deuda_pais dp
  WHERE dp.ind_acti=1
  ORDER BY dp.num_secu_etap DESC;
 */

  lv_cod_pais                       seg_pais.cod_pais%TYPE;
  lv_cod_modu                       fin_proce_modul.cod_modu%TYPE;
  lv_cod_proc                       fin_proce_modul.cod_proc%TYPE;
  lv_ind_tipo_asig                  cob_param_gener.val_para%TYPE;
  lv_id_proc_ejec_actu              fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_cod_cart                       cob_cabec_asign_carte.cod_cart%TYPE;
  reg_cob_carte_asign_proce         cob_tempo_carte_asign_proce%ROWTYPE;

 BEGIN

  p_cod_erro:= 'procesoCOBAsignacionCarteraForm.exito.proceso';

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_modu := gc_cod_modu;
  lv_cod_proc := gc_cod_proc_asig_auto;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,p_cod_usua,lv_id_proc_ejec_actu,p_cod_erro);

  p_num_lote_asign := COB_PKG_GENER.COB_FN_OBTIE_NUMER_LOTE;

  lv_des_log:='Inicio Asignacion Automatica: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  IF p_cod_erro IS NOT NULL THEN
   GOTO errors;
  END IF;

  lv_des_log:='Obteniendo el Tipo de Asignacion: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  SELECT COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IND_TIPO_ASIG')
  INTO lv_ind_tipo_asig
  FROM DUAL;

  IF lv_ind_tipo_asig IS NULL THEN
   p_cod_erro:='procesoCOBAsignacionCarteraForm.error.indicadorTipoAsignacion.no.configurado';
   GOTO errors;
  END IF;

  DELETE FROM cob_tempo_carte_asign_proce;

  --IF lv_ind_tipo_asig='C' THEN

   -- Asignacion de Cartera en base al Cronograma de Cartera
   lv_des_log:='Asignacion Por Cronograma' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   lv_des_log:='Obteniendo las Carteras a Asignarse' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FOR v_cart IN c_cart LOOP

    lv_des_log:=' *** Inicio Asignando Etapa : ' || v_cart.cod_etap_deud ||
                                ' Campa?a ' ||  v_cart.cod_peri ||
                                ' Region ' || v_cart.cod_regi ||
                                ' Zona ' || v_cart.cod_zona ||
                                ' Fecha Generacion ' || v_cart.fec_gene_cart;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    cob_pr_asign_carte_indiv(
                  lv_cod_pais,
                  v_cart.cod_etap_deud,
                  p_num_lote_asign,
                  v_cart.cod_peri,
                  v_cart.cod_regi,
                  v_cart.cod_zona,
                  v_cart.fec_gene_cart,
                  lv_ind_tipo_asig,
                  p_cod_usua,
                  lv_cod_cart,
                  p_cod_erro);


    IF p_cod_erro IS NOT NULL THEN
     GOTO errors;
    END IF;

    lv_des_log:=' *** Fin Asignacion';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 1);

    reg_cob_carte_asign_proce.cod_cart := lv_cod_cart;
    reg_cob_carte_asign_proce.usu_crea := p_cod_usua;

    INSERT INTO cob_tempo_carte_asign_proce VALUES reg_cob_carte_asign_proce;

    SELECT COUNT(*)
    INTO lv_cont
    FROM cob_tempo_carte_asign_proce;

    lv_des_log:='Carteras asignadas **** '  || lv_cont;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   END LOOP;

   lv_des_log:='Carteras asignadas' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 1);

  /*
  ELSIF lv_ind_tipo_asig='V' THEN

   -- Asignacion de Cartera en base a la edad de las fechas de vencimiento
   lv_des_log:='Asignacion Por Fechas de Vencimiento' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FOR v_etap IN c_etap LOOP

    lv_des_log:='   Asignando Etapa : ' ||  v_etap.cod_etap_deud ;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

    cob_pr_asign_carte_indiv(
                  lv_cod_pais,
                  p_num_lote_asign,
                  v_etap.cod_etap_deud,
                  'T',
                  'T',
                  'T',
                  lv_ind_tipo_asig,
                  p_cod_usua,
                  lv_cod_cart,
                  p_cod_erro);

    IF p_cod_erro IS NOT NULL THEN
     GOTO errors;
    END IF;

   END LOOP;


  ELSE

   lv_des_log:='Tipo de Asignacion Configurado Incorrectamente' ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

   FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 9);

  END IF;
  */

  lv_des_log:='Fin Asignacion Automatica: ' ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_Cod_proc,lv_id_proc_ejec_actu, 2);

  p_cod_erro:= 'procesoCOBAsignacionCarteraForm.exito.proceso';

  lv_des_log:='Carteras asignadas ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  --cob_pr_asign_carte_email_ejecu;
  --cob_pr_asign_carte_email_super;

  lv_des_log:='Se enviaron los emails a los Ejecutivos y a los Supervisores ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu,lv_des_log);

  /*
  INT_PKG_COB.INT_PR_COB_GENER_INFOR_COBRA(p_cod_usua);
  lv_des_log:='Se envio la informacion de Cobranza a los Cobradore ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,p_cod_modu,P_cod_proc,lv_id_proc_ejec_actu,lv_des_log);
  */

  RETURN;

  <<errors>>

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(lv_cod_pais,lv_cod_modu,lv_cod_proc,lv_id_proc_ejec_actu, 9);

 EXCEPTION

  WHEN OTHERS THEN
   RAISE_APPLICATION_ERROR(-20123, 'Ha ocurrido un error');

 END COB_PR_ASIGN_CARTE_AUTOM;

 PROCEDURE cob_pr_asign_carte_indiv(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_num_lote                       IN   cob_cabec_asign_carte.num_lote_asign%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                  IN   cob_crono_carte.fec_gene_cart%TYPE,
  p_ind_tipo_asig                  IN   cob_param_gener.val_para%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_cart                       OUT  cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  lv_cod_cart                  cob_cabec_asign_carte.cod_cart%TYPE;
  lv_fec_cier                    DATE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,p_cod_usua,lv_id_proc_ejec,p_cod_erro);

  lv_des_log:='cob_pr_asign_carte_indiv : [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_peri || ',' ||
                            p_cod_regi || ',' ||
                            p_cod_zona  || ',' ||
                            p_fec_gene_cart || ',' ||
                            p_ind_tipo_asig || ',' ||
                            p_cod_usua || ']';

      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      /* Subproceso que ejecuta las Validaciones de Cartera */
      cob_pr_valid_ejecu_carte(
         p_cod_pais,
         p_cod_etap_deud,
         p_cod_peri,
         p_cod_regi,
         p_cod_zona,
         p_cod_erro);

      IF p_cod_erro IS NOT NULL THEN
         GOTO errors;
      END IF;

      /* Subproceso que realiza la Seleccion  de Cartera */
      cob_pr_selec_asign_carte(
         p_cod_pais,
         p_cod_etap_deud,
         p_cod_peri,
         p_cod_regi,
         p_cod_zona,
         p_fec_gene_cart,
         p_ind_tipo_asig,
         p_cod_usua,
         lv_cod_cart,
         lv_fec_cier,
         p_cod_erro);

      IF p_cod_erro IS NOT NULL THEN
         GOTO errors;
      END IF;

      p_cod_cart := lv_cod_cart;

      /* Subproceso que realiza las Excepcion de Cartera */
      cob_pr_asign_excep_carte(p_cod_pais,p_cod_etap_deud,lv_cod_cart,p_cod_erro);
      IF p_cod_erro IS NOT NULL THEN
         GOTO errors;
      END IF;

      /* Subproceso que realiza la Asignacion  de Cartera */
      cob_pr_ejecu_asign_carte(p_cod_pais,p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,lv_cod_cart,lv_fec_cier,p_ind_tipo_asig,p_cod_usua,p_cod_erro);
       IF p_cod_erro IS NOT NULL THEN
         GOTO errors;
      END IF;

      /* Bloqueo de Consultoras en Base a la etapa de deuda */
      cob_pr_bloqu_consu_carte(p_cod_etap_deud,lv_cod_cart);

      /* Generacion de las carteras */
      cob_pr_gener_carte_asign(p_cod_pais,p_cod_etap_deud,p_num_lote,p_cod_peri,p_cod_regi,p_cod_zona,p_fec_gene_cart,lv_cod_cart,lv_fec_cier,p_cod_usua);

      lv_des_log:='Fin del proceso';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec, 2);

      COMMIT;
      RETURN;

      <<errors>>
      lv_des_log:='Fin del proceso de manera erronea';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec, 9);

      ROLLBACK;

   EXCEPTION
      WHEN OTHERS THEN
         ROLLBACK;
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_indiv;

   PROCEDURE cob_pr_valid_ejecu_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      CURSOR c_vali
      IS
         SELECT v.val_nomb_prog, v.val_mens_vali
         FROM cob_param_asign_carte_valid v
         WHERE v.cod_pais = p_cod_pais
         AND v.ind_acti=1
         ORDER BY v.num_orde_vali ASC;

   BEGIN

      lv_des_log:=NULL;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='*** INICIO Validaciones de Cartera ***';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FOR v_vali IN c_vali LOOP

         p_cod_erro:=cob_fn_valid_ejecu_indiv(p_cod_pais,p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,v_vali.val_nomb_prog);
         dbms_output.put_line( 'Error ' || p_cod_erro);
         IF p_cod_erro IS NOT NULL THEN

            EXIT;

         END IF;

      END LOOP;

      lv_des_log:='*** FIN Validaciones de Cartera ***';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   END cob_pr_valid_ejecu_carte;

   FUNCTION cob_fn_valid_ejecu_indiv(
      p_cod_pais                             seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             zon_regio.cod_regi%TYPE,
      p_cod_zona                            zon_zona.cod_zona%TYPE,
      p_nomb_func                          VARCHAR2)
   RETURN VARCHAR2
   IS

       PRAGMA AUTONOMOUS_TRANSACTION;

      lv_cod_erro  VARCHAR2(250);
      lv_sql            VARCHAR2(250);
   BEGIN

      lv_sql:='SELECT ' || p_nomb_func || '(:1,:2,:3,:4,:5) FROM DUAL';

      EXECUTE IMMEDIATE lv_sql
      INTO lv_cod_erro
      USING IN p_cod_pais, IN p_cod_etap_deud, IN p_cod_peri, IN p_cod_regi, IN p_cod_zona;

      RETURN lv_cod_erro;

   END cob_fn_valid_ejecu_indiv;

   /**************************************************************************
      Descripcion       : Funcion que valida el que la cartera a asignar este registrada
                                  correctamente en el cronograma
                                  para la etapa
     Fecha Creacion    : 18/02/2010
     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION cob_fn_valid_crono_carte(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2
   IS

      lv_cont_vali                 NUMBER(12);
      lv_cod_erro                 VARCHAR2(250):=NULL;

   BEGIN

      lv_des_log:='    Inicio Validacion Cronograma de Cartera ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      SELECT COUNT(*)
      INTO lv_cont_vali
      FROM cob_crono_carte cro
      WHERE cro.cod_pais = p_cod_pais
           AND cro.cod_etap_deud=p_cod_etap_deud
           AND cro.cod_peri = p_cod_peri
           AND cro.cod_regi = p_cod_regi
           AND cro.cod_zona = p_cod_zona
           AND cro.fec_cier IS NOT NULL;

      IF lv_cont_vali<>1 THEN
         lv_cod_erro := 'procesoCOBAsignacionCarteraForm.error.validacion.cronograma';

         lv_des_log:='    !!! ERROR Validacion Cronograma de Cartera ';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
      END IF;

      lv_des_log:='    Fin Validacion Cronograma de Cartera ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      RETURN lv_cod_erro;

   END cob_fn_valid_crono_carte;

    /**************************************************************************
      Descripcion       : Funcion que valida el usuario Supervisor
                                  para la etapa
     Fecha Creacion    : 18/02/2010
     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION cob_fn_valid_usuar_super(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2
   IS

      lv_cont_vali                                   NUMBER(12);
      lv_cod_erro                                   VARCHAR2(250);

   BEGIN

      lv_des_log:='    Inicio Validacion Usuario Supervisor : [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_peri || ',' ||
                            p_cod_regi || ',' ||
                            p_cod_zona  || ']';

      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       SELECT COUNT(*)
       INTO lv_cont_vali
       FROM cob_etapa_deuda_pais ed
       WHERE ed.cod_pais = p_cod_pais
       AND ed.cod_etap_deud=p_cod_etap_deud
       AND ed.cod_usua_supe IS NOT NULL;

       IF lv_cont_vali<>1 THEN

          lv_cod_erro:= 'procesoCOBAsignacionCarteraForm.error.validacion.supervisor';
          lv_des_log:='    !!! ERROR :No existe usuario supervisor para la etapa' ;
          FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       END IF;

      lv_des_log:='    Fin Validacion Usuario Supervisor ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      RETURN lv_cod_erro;

   END cob_fn_valid_usuar_super;

   /**************************************************************************
      Descripcion       : Funcion que valida los cobradores de la cartera
     Fecha Creacion    : 18/02/2010
     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION cob_fn_valid_cobra_carte(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2
   IS

      lv_cont_vali_bala                 NUMBER(12);
      lv_cont_vali_fija                  NUMBER(12);

      lv_cod_erro                          VARCHAR2(250):=NULL;

   BEGIN

      lv_des_log:='    Inicio Validacion Cobradores de Cartera';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       SELECT COUNT(*)
       INTO lv_cont_vali_bala
       FROM cob_param_asign_carte_cobra cob
       WHERE cob.cod_pais = p_cod_pais
       AND cob.cod_etap_deud= p_cod_etap_deud;

       IF lv_cont_vali_bala > 0 THEN

          lv_des_log:='    Cantidad de Cobradores Asignacion Generica : ' || lv_cont_vali_bala ;
          FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       ELSE

           SELECT COUNT(*)
           INTO lv_cont_vali_fija
           FROM cob_param_asign_zonas_cobra cob
           WHERE cob.cod_pais = p_cod_pais
           AND cob.ind_acti = 1
           AND cob.cod_etap_deud= p_cod_etap_deud
           AND p_cod_peri=p_cod_peri
           AND p_cod_regi=p_cod_regi
           AND p_cod_zona=p_cod_zona;

           IF lv_cont_vali_fija>0  THEN

              lv_des_log:='    Cantidad de Cobradores Fijos : ' || lv_cont_vali_fija ;
              FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

           ELSE

              lv_cod_erro:='procesoCOBAsignacionCarteraForm.error.validacion.cobradores';

              lv_des_log:='    ERROR :No Existen Cobradores para la Cartera' ;
              FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

           END IF;

       END IF;

      RETURN lv_cod_erro;

      lv_des_log:='    Fin Validacion cob_fn_valid_cobra_carte ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   END cob_fn_valid_cobra_carte;

   FUNCTION cob_fn_valid_porce_asign(
      p_cod_pais                         IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud              IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                         IN   seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                         IN   zon_regio.cod_regi%TYPE,
      p_cod_zona                        IN   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2
   IS


      lv_cod_erro                                        VARCHAR2(250);
      lv_cant_cobr_fija                              NUMBER(12):=0;
      lv_imp_porc_asig_fija                        NUMBER(12):=0;
      lv_cant_cobr_gene                              NUMBER(12):=0;
      lv_imp_porc_asig_gene                        NUMBER(12):=0;

   BEGIN

      lv_des_log:='    Inicio Validacion Porcentajes Asignacion : [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_peri || ',' ||
                            p_cod_regi || ',' ||
                            p_cod_zona  || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      -- Asignacion Fija
      SELECT COUNT(*) , SUM(zc.imp_porc_asig)
      INTO lv_cant_cobr_fija,lv_imp_porc_asig_fija
      FROM cob_param_asign_zonas_cobra zc
      WHERE zc.cod_pais = p_cod_pais
       AND zc.cod_etap_deud = p_cod_etap_deud
       AND p_cod_peri=p_cod_peri
       AND zc.cod_regi=p_cod_regi
       AND zc.cod_zona=p_cod_zona;

      IF  (lv_cant_cobr_fija> 0)  AND (lv_imp_porc_asig_fija <> 100) THEN
         lv_cod_erro:='procesoCOBAsignacionCarteraForm.error.validacion.porcentajes' ;

         lv_des_log:='    ERROR :Los Porcentajes de Asignacion Fija no suman el 100% , suman ' || lv_imp_porc_asig_fija ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      END IF;

      -- Asignacion Generica
      SELECT COUNT(*), SUM(cb.imp_porc_asig)
      INTO lv_cant_cobr_gene,lv_imp_porc_asig_gene
      FROM cob_param_asign_carte_cobra cb
      WHERE cb.cod_pais = p_cod_pais
       AND cb.cod_etap_deud=p_cod_etap_deud
       AND p_cod_peri=p_cod_peri
       AND p_cod_regi=p_cod_regi
       AND p_cod_zona=p_cod_zona;

      IF (lv_cant_cobr_gene > 0) AND  (lv_imp_porc_asig_gene <> 100) THEN

            lv_cod_erro:='procesoCOBAsignacionCarteraForm.error.validacion.porcentajes' ;
            lv_des_log:='    ERROR :Los Porcentajes de Asignacion Generica no suman el 100%, suman ' || lv_imp_porc_asig_gene ;

            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      END IF;

      lv_des_log:='    Fin Validacion Porcentajes Asignacion ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      RETURN lv_cod_erro;

   END cob_fn_valid_porce_asign;

   PROCEDURE cob_pr_selec_asign_carte (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_fec_gene_cart                       IN  cob_crono_carte.fec_gene_cart%TYPE,
      p_ind_tipo_asig                     IN      cob_param_gener.val_para%TYPE,
      p_cod_usua                            IN      seg_usuar.cod_usua%TYPE,
      p_cod_cart                            OUT   cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier                             OUT   DATE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

   BEGIN

      lv_des_log:=NULL;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='*** INICIO Seleccion de Cartera ***';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      p_cod_cart:=p_cod_peri || p_cod_etap_deud || 'R' || p_cod_regi || 'Z' || p_cod_zona || SUBSTR(to_char(SYSTIMESTAMP,'HHMIFFFF'),1,8);

      lv_des_log:='   Codigo de Cartera Generado : ' ||  p_cod_cart;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

    IF p_ind_tipo_asig='C' THEN
       cob_pr_selec_unida_admin(p_cod_pais, p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_cod_erro);

       IF p_cod_erro IS NOT NULL THEN
          RAISE ge_erro_no_docu;
       END IF;

       cob_pr_selec_carte_movim(p_cod_pais, p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_fec_gene_cart,p_cod_cart,p_cod_usua,p_fec_cier,p_cod_erro);

       IF p_cod_erro IS NOT NULL THEN
          RAISE ge_erro_no_docu;
       END IF;

    ELSE

       p_cod_cart:=p_cod_peri || p_cod_etap_deud || to_char(SYSDATE,'DDMMHHMI');

       cob_pr_selec_carte_movim_venci(p_cod_pais,p_cod_etap_deud,p_cod_cart,p_cod_usua,p_fec_cier,p_cod_erro);

       IF p_cod_erro IS NOT NULL THEN
          RAISE ge_erro_no_docu;
       END IF;

    END IF;

    cob_pr_selec_datos_clien(p_cod_pais,p_cod_cart,p_cod_erro);
    cob_pr_selec_carte_clien(p_cod_pais,p_cod_cart,p_cod_usua,p_cod_erro);

    IF p_cod_erro IS NOT NULL THEN
       RAISE ge_erro_no_docu;
     END IF;

    lv_des_log:='*** FIN Seleccion de Cartera ***';
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
     WHEN ge_erro_no_docu THEN
        p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_selec_asign_carte;

 PROCEDURE cob_pr_selec_unida_admin (
  p_cod_pais                     IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_cod_erro                     OUT  VARCHAR2)
 IS

  lv_cant_ztad                       NUMBER(12);

 BEGIN

  lv_des_log:='   Inicio Seleccionar Unidades Administrativas [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_regi || ',' ||
                            p_cod_zona || ']';

  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_ZON_TERRI_ADMIN_ASIG');

  IF p_cod_zona='T' THEN

   INSERT INTO cob_tempo_zon_terri_admin_asig
    SELECT
     zta.oid_terr_admi
    FROM
     zon_regio zr,
     zon_zona zz,
     zon_secci zs,
     zon_terri zt,
     zon_terri_admin zta
    WHERE zr.oid_regi = zz.zorg_oid_regi
      AND zz.oid_zona = zs.zzon_oid_zona
      AND zs.oid_secc = zta.zscc_oid_secc
      AND zt.oid_terr = zta.terr_oid_terr
      AND zt.ind_borr=0
      AND zr.cod_regi = p_cod_regi
      AND zz.cod_zona NOT IN (
       SELECT cro.cod_zona
       FROM cob_crono_carte cro
       WHERE cro.cod_pais = p_cod_pais
         AND cro.cod_etap_deud = p_cod_etap_deud
         AND cro.cod_peri = p_cod_peri
         AND cro.cod_regi=p_cod_regi
         AND cro.cod_zona <> p_cod_zona)                      
         AND NOT EXISTS (
          SELECT NULL
          FROM cob_param_zonas_nocri zn
          WHERE zn.cod_pais = p_cod_pais
            AND zn.ind_acti = 1
            AND zn.cod_etap_deud = p_cod_etap_deud
            AND zn.cod_zona = zz.cod_zona)
         AND NOT EXISTS (
           SELECT NULL
           FROM cob_param_secci_nocri sn
           WHERE sn.ind_acti = 1
             AND sn.cod_etap_deud = p_cod_etap_deud
             AND sn.cod_zona = zz.cod_zona
             AND sn.cod_secc = zs.cod_secc);

  ELSE

   INSERT INTO cob_tempo_zon_terri_admin_asig
    SELECT
      zta.oid_terr_admi
    FROM
      zon_regio zr,
      zon_zona zz,
      zon_secci zs,
      zon_terri zt,
      zon_terri_admin zta
    WHERE zr.oid_regi = zz.zorg_oid_regi
      AND zz.oid_zona = zs.zzon_oid_zona
      AND zta.zscc_oid_secc = zs.oid_secc
      AND zta.terr_oid_terr = zt.oid_terr
      AND zt.ind_borr=0
      AND zr.cod_regi = p_cod_regi
      AND zz.cod_zona = p_cod_zona
      AND NOT EXISTS (
           SELECT NULL
           FROM cob_param_secci_nocri sn
           WHERE sn.ind_acti = 1
             AND sn.cod_etap_deud = p_cod_etap_deud
             AND sn.cod_zona = zz.cod_zona
             AND sn.cod_secc = zs.cod_secc); 

  END IF;

  SELECT COUNT(*)
  INTO lv_cant_ztad
  FROM cob_tempo_zon_terri_admin_asig;

  lv_des_log:='   Cantidad OID Zon Terri Admin : ' || lv_cant_ztad;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  lv_des_log:='   Fin Seleccionar Unidades Administrativas';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

 EXCEPTION
 
  WHEN OTHERS THEN
  
   gv_sqlerrm := SUBSTR(SQLERRM,1,250);
   lv_des_log:='   !!! Error ' || gv_sqlerrm ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

 END cob_pr_selec_unida_admin;

   PROCEDURE cob_pr_selec_carte_movim (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      cra_perio.oid_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                       IN cob_crono_carte.fec_gene_cart%TYPE,
      p_cod_cart                             IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN       seg_usuar.cod_usua%TYPE,
      p_fec_cier                             OUT   VARCHAR2,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_oid_peri                           cra_perio.oid_peri%TYPE;
      lv_cant_movim                     NUMBER(12);
      lv_cant_ztad                       NUMBER(12);
  lv_ind_asig_fina                 NUMBER(1):=0;

  lv_fec_gene_cart_maxi            cob_crono_carte.fec_gene_cart%type;
  lv_fec_gene_cart                 cob_crono_carte.fec_gene_cart%type;
  lv_edad_inic                     cob_etapa_deuda_pais.val_edad_inic%TYPE;

   BEGIN

      lv_des_log:='   Inicio Seleccionar Movimientos de Deuda [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_peri || ',' ||
                            p_cod_regi || ',' ||
                            p_cod_zona  || ',' ||
                  p_fec_gene_cart || ',' ||
                            p_cod_cart || ',' ||
                            p_cod_usua || ']';

      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  -- Validando si hoy es el ultimo dia de la asignacion --
  SELECT MAX(cro.fec_gene_cart)
  INTO lv_fec_gene_cart_maxi
  FROM cob_crono_carte cro
  WHERE cro.cod_etap_deud = p_cod_etap_deud
           AND cro.cod_peri = p_cod_peri
           AND cro.cod_regi = p_cod_regi
           AND cro.cod_zona = p_cod_zona;

  SELECT cro.fec_cier, cro.fec_gene_cart, ed.val_edad_inic
  INTO p_fec_cier,lv_fec_gene_cart,lv_edad_inic
  FROM cob_crono_carte cro,
       cob_etapa_deuda_pais ed
  WHERE cro.cod_etap_deud = ed.cod_etap_deud
    AND cro.cod_pais = p_cod_pais
    AND cro.cod_etap_deud = p_cod_etap_deud
    AND cro.cod_peri = p_cod_peri
    AND cro.cod_regi = p_cod_regi
    AND cro.cod_zona = p_cod_zona
    AND cro.fec_gene_cart = p_fec_gene_cart;

  lv_des_log:='   Fecha de Asignaci?n M?xima : ' || lv_fec_gene_cart_maxi;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  lv_des_log:='   Fecha de Asignaci?n : ' || p_fec_gene_cart;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  IF p_fec_gene_cart = lv_fec_gene_cart_maxi THEN

   lv_ind_asig_fina := 1;

  END if;

      lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais,p_cod_peri);

      SELECT COUNT(*)
      INTO lv_cant_ztad
      FROM cob_tempo_zon_terri_admin_asig;

      lv_des_log:='   Cantidad de OID Terri Admin : ' || lv_cant_ztad;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_asign_antic_clien');
      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_detal_movim_carte');

  IF lv_ind_asig_fina = 0 THEN

   lv_des_log:='   Asignacion Anticipada';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   DELETE FROM cob_tempo_asign_antic_clien;

   INSERT INTO COB_TEMPO_ASIGN_ANTIC_CLIEN
    SELECT mcc.clie_oid_clie
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.subp_oid_subp_crea = 2001
      AND mcc.imp_pend > 0
      AND mcc.perd_oid_peri = lv_oid_peri
      AND TRUNC(sysdate) - mcc.fec_docu >= lv_edad_inic
      AND NOT EXISTS
       ( SELECT NULL
        FROM cob_detal_asign_carte d
        WHERE d.cod_etap_deud = p_cod_etap_deud
          AND d.cod_peri = p_cod_peri
           AND d.oid_clie = mcc.clie_oid_clie )
      AND NOT EXISTS
       ( SELECT NULL
         FROM cob_detal_asign_carte d
         WHERE d.cod_peri = p_cod_peri
           AND d.fec_cier >= TRUNC(SYSDATE)
           AND d.oid_clie = mcc.clie_oid_clie )
     GROUP BY mcc.clie_oid_clie;

   INSERT INTO cob_tempo_detal_movim_carte
    SELECT
     p_cod_pais,                          --COD_PAIS
     p_cod_etap_deud,                --COD_ETAP_DEUD
     p_cod_peri,                          --COD_PERI
     p_cod_regi,                          --COD_REGI
     p_cod_zona,                          --COD_ZONA
     p_cod_cart,                          --COD_CART
     mcc.clie_oid_clie,                   --OID_CLIE
     mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
     mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
     mcc.fec_docu,                        --FEC_DOCU
     mcc.fec_venc,                        --FEC_VENC
     TRUNC(SYSDATE),                      --FEC_ASIG
     p_fec_cier,                         --FEC_CIER
     mcc.imp_movi,                        --IMP_DEUD_ORIG
     mcc.imp_pend,                        --IMP_DEUD_ASIG
     mcc.imp_pend,                        --IMP_DEUD_PEND
     0,                                   --IMP_DEUD_CANC
     0,                                   --IMP_PAGO_BANC
     NULL,                                --FEC_ULT_PAGO_BANC
     0,                                   --IMP_PAGO_OTRO
     mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
     mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
     0,                                     -- IND_CUOT_ETAP_ANTE
     0,                                     -- IND_CUOT_ETAP_POST
     0,                                   --IND_ERRO
     NULL,                                --COD_TIPO_EXCE
     p_cod_usua,                          --USU_CREA
     SYSDATE,                             --FEC_CREA
     p_cod_usua,                          --USU_MODI
     SYSDATE,                             --FEC_MODI
     1,                                    --IND_DEUD_PEND
     0                                     -- IND_DEUD_SUPE
    FROM
     cob_tempo_zon_terri_admin_asig zta,
     mae_clien_unida_admin mcua,
     ccc_movim_cuent_corri mcc,
     cob_tempo_asign_antic_clien aac
    WHERE mcua.ztad_oid_terr_admi=zta.oid_zon_terri_admin
      AND mcua.ind_Acti=1
      AND mcc.clie_oid_clie = mcua.clie_oid_clie
      AND mcc.clie_oid_clie = aac.oid_clie
      AND mcua.clie_oid_clie = aac.oid_clie
      AND mcc.perd_oid_peri= lv_oid_peri
      AND mcc.imp_pend > 0
      AND NOT EXISTS
              ( SELECT NULL
                FROM cob_carte_movim_clien_excep e
                WHERE e.oid_movi_cc = mcc.oid_movi_cc)
      AND NOT EXISTS (
               SELECT NULL
               FROM cob_detal_asign_carte da
               WHERE da.oid_clie = mcc.clie_oid_clie
                 AND da.cod_etap_deud = p_cod_etap_deud
                 AND da.cod_peri = p_cod_peri);

      ELSE


      lv_des_log := '   Asignacion Final';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      INSERT INTO cob_tempo_detal_movim_carte
         SELECT
            p_cod_pais,                          --COD_PAIS
            p_cod_etap_deud,                --COD_ETAP_DEUD
            p_cod_peri,                          --COD_PERI
            p_cod_regi,                          --COD_REGI
            p_cod_zona,                          --COD_ZONA
            p_cod_cart,                          --COD_CART
            mcc.clie_oid_clie,                   --OID_CLIE
            mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
            mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
            mcc.fec_docu,                        --FEC_DOCU
            mcc.fec_venc,                        --FEC_VENC
            TRUNC(SYSDATE),                      --FEC_ASIG
            p_fec_cier,                         --FEC_CIER
            mcc.imp_movi,                        --IMP_DEUD_ORIG
            mcc.imp_pend,                        --IMP_DEUD_ASIG
            mcc.imp_pend,                        --IMP_DEUD_PEND
            0,                                   --IMP_DEUD_CANC
            0,                                   --IMP_PAGO_BANC
            NULL,                                --FEC_ULT_PAGO_BANC
            0,                                   --IMP_PAGO_OTRO
            mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
            mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
            0,                                     -- IND_CUOT_ETAP_ANTE
            0,                                     -- IND_CUOT_ETAP_POST
            0,                                   --IND_ERRO
            NULL,                                --COD_TIPO_EXCE
            p_cod_usua,                          --USU_CREA
            SYSDATE,                             --FEC_CREA
            p_cod_usua,                          --USU_MODI
            SYSDATE,                             --FEC_MODI
            1,                                    --IND_DEUD_PEND
            0                                     -- IND_DEUD_SUPE
         FROM
            cob_tempo_zon_terri_admin_asig zta,
            mae_clien_unida_admin mcua,
            ccc_movim_cuent_corri mcc
         WHERE  mcua.ztad_oid_terr_admi=zta.oid_zon_terri_admin
              AND mcua.ind_Acti=1
              AND mcc.clie_oid_clie = mcua.clie_oid_clie
              AND mcc.perd_oid_peri= lv_oid_peri
              AND mcc.imp_pend > 0
              AND NOT EXISTS
              ( SELECT NULL
                FROM cob_carte_movim_clien_excep e
                WHERE e.oid_movi_cc = mcc.oid_movi_cc)
              AND NOT EXISTS (
               SELECT NULL
               FROM cob_detal_asign_carte da
               WHERE da.oid_clie = mcc.clie_oid_clie
                 AND da.cod_etap_deud = p_cod_etap_deud
                 AND da.cod_peri = p_cod_peri)
              AND NOT EXISTS
              ( SELECT NULL
                 FROM cob_detal_asign_carte d
                 WHERE d.cod_peri = p_cod_peri
                   AND d.fec_cier >= TRUNC(SYSDATE)
                   AND d.oid_clie = mcc.clie_oid_clie );

          END IF;

      SELECT COUNT(*)
      INTO lv_cant_movim
      FROM cob_tempo_detal_movim_carte;

      IF lv_cant_movim = 0 THEN
         lv_des_log:='   !!! No Existen Consultoras Deudoras :' || lv_cant_movim;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
      END IF;

      lv_des_log:='   Cantidad de Movimientos seleccionados :' || lv_cant_movim;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='   Fin Seleccionar Movimientos de Deuda';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_selec_carte_movim;

 PROCEDURE cob_pr_selec_datos_clien(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

 BEGIN

  FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('fin_tempo_datos_clien');

  INSERT INTO fin_tempo_datos_clien
   SELECT
    zr.cod_regi,
    zr.des_regi,
    zz.cod_zona,
    zs.cod_secc,
    zt.cod_terr,
    mc.cod_clie ,
    mc.oid_clie,
    mc.cod_digi_ctrl,
    NVL(TRIM(mc.val_nom1),'') || ' ' ||
    NVL(TRIM(mc.val_nom2),'') || ' ' ||
    NVL(TRIM(mc.val_ape1),'') || ' ' ||
    NVL(TRIM(mc.val_ape2),'') nom_clie,
    mci.num_docu_iden,
    gen.val_i18n val_esta_clie,
    (SELECT zon_valor_estru_geopo.des_geog
     FROM zon_valor_estru_geopo
     WHERE zon_valor_estru_geopo.orde_1 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,1,6)))
          AND zon_valor_estru_geopo.orde_2 IS NULL
          AND zon_valor_estru_geopo.orde_3 IS NULL
          AND zon_valor_estru_geopo.orde_4 IS NULL
          AND ROWNUM = 1) des_dpto,
    (SELECT zon_valor_estru_geopo.des_geog
     FROM zon_valor_estru_geopo
     WHERE zon_valor_estru_geopo.orde_1 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,1,6)))
         AND zon_valor_estru_geopo.orde_2 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,7,6)))
         AND zon_valor_estru_geopo.orde_3 IS NULL
         AND zon_valor_estru_geopo.orde_4 IS NULL
         AND ROWNUM = 1) des_prov,
    (SELECT zon_valor_estru_geopo.des_geog
     FROM zon_valor_estru_geopo
     WHERE zon_valor_estru_geopo.orde_1 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,1,6)))
          AND zon_valor_estru_geopo.orde_2 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,7,6)))
          AND zon_valor_estru_geopo.orde_3 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,13,6)))
          AND zon_valor_estru_geopo.orde_4 IS NULL
          AND ROWNUM = 1) des_dist,
    (SELECT zon_valor_estru_geopo.des_geog
     FROM zon_valor_estru_geopo
     WHERE zon_valor_estru_geopo.orde_1 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,1,6)))
          AND zon_valor_estru_geopo.orde_2 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,7,6)))
          AND zon_valor_estru_geopo.orde_3 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,13,6)))
          AND zon_valor_estru_geopo.orde_4 = LTRIM(RTRIM(SUBSTR(mcd.COD_UNID_GEOG,19,6)))
          AND ROWNUM = 1) || mcd.val_barr des_urba,
         (NVL(TRIM(stv.des_abrv_tipo_via),' ') ||' '||
          NVL(TRIM(mcd.val_nomb_via),' ') ) ||' '||
          TRIM(mcd.num_ppal) ||' '||
          TRIM(mcd.val_inte) ||' '||
          TRIM(mcd.val_manz) ||' '||
          TRIM(mcd.val_lote) ||' '||
          TRIM(mcd.val_Km) val_dire,
          TRIM(mcd.val_obse) || ' '  val_dire_refe,
      tf.val_text_comu num_tele_fijo,
      tt.val_text_comu num_tele_trab,
      tm.val_text_comu num_tele_movi,
      ml.val_text_comu val_mail,
      mcd.val_cod_post
    FROM
     (SELECT det.oid_clie
      FROM cob_tempo_detal_movim_carte det
      WHERE det.cod_cart= p_cod_cart
      GROUP BY det.oid_clie) car,
     mae_clien_unida_admin mcua,
     zon_terri_admin zta,
     zon_terri zt,
     zon_secci zs,
     zon_zona zz,
     zon_regio zr,
     mae_clien mc,
     mae_clien_datos_adici mcda,
     mae_clien_ident mci,
     seg_tipo_via stv,
     mae_clien_direc mcd,
     mae_estat_clien mec,
     gen_i18n_sicc_comun gen,
     (SELECT tf.clie_oid_clie, tf.val_text_comu
      FROM mae_clien_comun tf
      WHERE  tf.ticm_oid_tipo_comu=1) tf,
     (SELECT tf.clie_oid_clie, tf.val_text_comu
      FROM mae_clien_comun tf
      WHERE tf.ticm_oid_tipo_comu=7) tt,
     (SELECT tf.clie_oid_clie, tf.val_text_comu
      FROM mae_clien_comun tf
      WHERE tf.ticm_oid_tipo_comu=6) tm,
     (SELECT tf.clie_oid_clie, tf.val_text_comu
      FROM mae_clien_comun tf
      WHERE tf.ticm_oid_tipo_comu=3) ml
   WHERE car.oid_clie = mc.oid_clie
     AND car.oid_clie = mcua.clie_oid_clie
     AND mcua.ind_acti = 1
     AND mcua.clie_oid_clie = mc.oid_clie
     AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
     AND zta.zscc_oid_secc = zs.oid_secc
     AND zta.terr_oid_terr = zt.oid_terr
     AND zs.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND mc.oid_clie = mci.clie_oid_clie
     AND car.oid_clie = mci.clie_oid_clie
     AND car.oid_clie = mci.clie_oid_clie
     AND mci.val_iden_docu_prin=1
     AND mcd.clie_oid_clie = mc.oid_clie
     AND stv.oid_tipo_via = mcd.tivi_oid_tipo_VIa
     AND mcd.ind_dire_ppal = 1
     AND mcd.ind_elim = 0
     AND mcda.clie_oid_clie = mc.oid_clie
     AND car.oid_clie = mc.oid_clie
     AND mcda.esta_oid_esta_clie = mec.oid_esta_clie
     AND gen.val_oid = mec.oid_esta_clie
     AND gen.attr_enti = 'MAE_ESTAT_CLIEN'
     AND mc.oid_clie = tf.clie_oid_clie(+)
     AND mc.oid_clie = tt.clie_oid_clie(+)
     AND mc.oid_clie = tm.clie_oid_clie(+)
     AND mc.oid_clie = ml.clie_oid_clie(+);

 EXCEPTION

  WHEN OTHERS THEN
   gv_sqlerrm := SUBSTR(SQLERRM,1,250);
   lv_des_log:='   !!! Error ' || gv_sqlerrm ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

 END cob_pr_selec_datos_clien;

 PROCEDURE cob_pr_selec_carte_clien(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_usua                       IN   seg_usuar.cod_usua%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  lv_cant_deud                     NUMBER(12);
  lv_oid_pais                      seg_pais.oid_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;

 BEGIN

  lv_des_log:='    Inicio Seleccionar Deudoras : [' ||
                            p_cod_pais || ',' ||
                            p_cod_cart || ',' ||
                            p_cod_usua || ']';

  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_detal_asign_carte');

  lv_oid_pais:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_cod_pais);

  lv_cod_soci:= COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('COD_SOCI');

  INSERT INTO cob_tempo_detal_asign_carte
   WITH temp1 AS
    (SELECT
      ccc.cod_pais,
      ccc.cod_etap_deud,
      ccc.cod_regi,
      ccc.cod_cart,
      ccc.cod_zona,
      ccc.oid_clie,
      MIN(ccc.cod_peri) cod_peri,
      MAX(ccc.fec_cier) fec_cier,
      MIN(ccc.num_iden_cuot) num_iden_cuot,
                MIN(ccc.fec_docu) fec_docu,
                MIN(ccc.fec_venc) fec_venc,
                SUM(ccc.imp_deud_orig) imp_deud_orig,
                SUM(ccc.imp_deud_asig) imp_deud_asign,
                SUM(ccc.imp_deud_pend) imp_deud_pend,
                SUM(ccc.imp_deud_canc) imp_deud_canc,
                SUM(ccc.imp_pago_banc) imp_pago_banc,
                SUM(ccc.imp_pago_otro) imp_pago_otro
             FROM cob_tempo_detal_movim_carte ccc
             WHERE ccc.cod_cart  = p_cod_cart
             GROUP BY
                ccc.cod_pais,
                ccc.cod_etap_deud,
                ccc.cod_regi,
                ccc.cod_cart,
                ccc.cod_zona,
                ccc.oid_clie)
             SELECT
                mcc.cod_cart,                     --COD_CART            VARCHAR2(25) not null
                mcc.cod_pais,                       --COD_PAIS            VARCHAR2(3) not null
                lv_cod_soci,                           --COD_SOCI            VARCHAR2(4) not null
                mcc.cod_peri,                       --COD_PERI            VARCHAR2(6) not null
                mcc.cod_etap_deud,            --COD_ETAP_DEUD       VARCHAR2(4) not null
                mcc.cod_regi,                     --COD_REGI            VARCHAR2(2) not null
                fin.des_regi,                       --DES_REGI            VARCHAR2(40)
                mcc.cod_zona,                     --COD_ZONA            VARCHAR2(4) not null
                fin.cod_secc,                     --COD_SECC            VARCHAR2(1)
                fin.cod_terr,                      --COD_TERR            NUMBER(6)
                mcc.oid_clie,                     --OID_CLIE            NUMBER(12) not null
                fin.cod_clie,                     --COD_CLIE            VARCHAR2(15) not null
                fin.nom_clie,              --NOM_CLIE            VARCHAR2(100)
                FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_DOCUM_IDENT(lv_oid_pais,mcc.oid_clie),             --COD_TIPO_DOCU_IDEN  VARCHAR2(2)
                FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_DOCUM_IDENT(mcc.oid_clie),  --DES_TIPO_DOCU_IDEN  VARCHAR2(110)
                fin.num_docu_iden,    --NUM_DOCU_IDEN       VARCHAR2(30)
                fin.des_dpto,        --DES_DPTO            VARCHAR2(50)
                fin.des_prov,        --DES_PROV            VARCHAR2(50)
                fin.des_dist,        --DES_DIST            VARCHAR2(50)
                fin.val_dire,          --VAL_DIRE            VARCHAR2(150)
                fin.num_tele_fijo,    --NUM_TELE_FIJO       VARCHAR2(100)
                fin.num_tele_trab,    --NUM_TELE_TRAB       VARCHAR2(100)
                fin.num_tele_movi,    --NUM_TELE_MOVI       VARCHAR2(100)
                NULL,                        --COD_TIPO_CARG_ABON  VARCHAR2(3)
                NULL,                        --DES_TIPO_CARG_ABON  VARCHAR2(110)
                NULL,                        --MVCC_OID_MOVI_CC    NUMBER(12)
                mcc.num_iden_cuot,     --NUM_IDEN_CUOT       NUMBER(10)
                mcc.fec_docu,                        --FEC_DOCU            DATE
                mcc.fec_venc,                        --FEC_VENC            DATE
                trunc(SYSDATE),       --FEC_ASIG            DATE
                mcc.fec_cier,                     --FEC_CIER            DATE
                NULL,                                                                                                                  --FEC_ULT_PAGO_BANC   DATE
                0,                                                                                                                     --IMP_PAGO_BANC       NUMBER(122)
                0,                                                                                                                     --IMP_PAGO_OTRO       NUMBER(122)
                trunc(SYSDATE) - mcc.fec_venc,                                                                                                                  --NUM_DIAS_MORA       NUMBER(3)
                mcc.imp_deud_orig,                                                                                                     --IMP_DEUD_ORIG       NUMBER(122)
                mcc.imp_deud_pend,                                                                                                     --IMP_DEUD_ASIG       NUMBER(122)
                0,                                                                                                                     --IMP_DEUD_CANC       NUMBER(122)
                0,                                                                                                                     --IMP_PORC_RECU       NUMBER(122)
                lv_user_dummy,                                                                                                         --COD_USUA_COBR       VARCHAR2(100)
                NULL,                                                                                                                  --VAL_ULTI_NUME_HIST  NUMBER(3)
                NULL,                                                                                                                  --VAL_ASIG_NUME_HIST  NUMBER(3)
                p_cod_usua,                                                                                                            --USU_CREA            VARCHAR2(20)
                SYSDATE,                                                                                                               --FEC_CREA            DATE
                p_cod_usua,                                                                                                            --USU_MODI            VARCHAR2(20)
                SYSDATE,                                                                                                               --FEC_MODI            DATE
                0,                                                                                                                     --IND_ST              NUMBER(1)
                0,                                                                                                                     --IND_IM              NUMBER(1)
                mcc.imp_deud_pend,                                                                                                     --IMP_DEUD_PEND       NUMBER(122)
                0,                                                                                                                     --IND_ERRO            NUMBER(1)
                fin.cod_zona,                                                                                                          --COD_ZONA_CLIE       VARCHAR2(4)
                fin.cod_regi,                                                                                                          --COD_REGI_CLIE       VARCHAR2(4)
                fin.des_regi,                                                                                                          --DES_REGI_CLIE       VARCHAR2(40)
                0,                                                                                                                     --IND_CART_SUPE       NUMBER(1)
                0,                                                                                                                     --IND_CART_GEST       NUMBER(1)
                0,                                                                                                                     --IND_GEST_SUPE       NUMBER(1)
                0,                                                                                                                     --IND_COMP_PAGO       NUMBER(1)
                NULL,                                                                                                                  --FEC_COMP_PAGO       DATE
                0,                                                                                                                     --IMP_COMP_PAGO       NUMBER(122)
                'NGE',                                                                                                                 --COD_ULTI_GEST_COBR  VARCHAR2(3)
                'NGE',                                                                                                                 --COD_ULTI_GEST_ADMI  VARCHAR2(3)
                fin.val_dire_refe,     --VAL_DIRE_REFE       VARCHAR2(250)
                fin.val_esta_clie,                                                                                                     --VAL_DESC_ESTA_CLIEN VARCHAR2(50)
                FIN_PKG_GENER.FIN_FN_OBTIE_CAMPA_PRIME_PEDID(mcc.oid_clie),      --COD_CAMP_PRIM_PEDI  VARCHAR2(6)
                FIN_PKG_GENER.FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(mcc.oid_clie),     --COD_CAMP_ULTI_PEDI  VARCHAR2(6)
                FIN_PKG_GENER.FIN_FN_OBTIE_NOMBR_GEREN_ZONA(fin.cod_zona),     --VAL_NOM_GERE_ZONA   VARCHAR2(250)
                FIN_PKG_GENER.FIN_FN_OBTIE_NOMBR_LIDER_SECCI(fin.cod_zona,fin.cod_secc), --VAL_NOM_LIDE_SECC   VARCHAR2(250)
                fin.cod_digi_ctrl,                                                                                                      --COD_DIGI_CTRL       VARCHAR2(3)
                fin.des_urba,                                                                                                           --DES_URBA            VARCHAR2(50)
                NULL,                                                                                                                  --NUM_TELE_AUX1       VARCHAR2(100)
                NULL,                                                                                                                  --NUM_TELE_AUX2       VARCHAR2(100)
                NULL,                                                                                                                   --NUM_TELE_AUX3       VARCHAR2(100)
                NULL,
                NULL,
                fin.val_mail,
                fin.val_cod_post -- CODIGO POSTAL
     FROM
      temp1 mcc,
      fin_tempo_datos_clien fin
     WHERE mcc.oid_clie = fin.oid_clie(+);

  SELECT COUNT(*)
  INTO lv_cant_deud
  FROM cob_tempo_detal_asign_carte det
  WHERE det.cod_cart = p_cod_cart;

  lv_des_log:='   Cantidad de Deudoras ' ||  lv_cant_deud;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  lv_des_log:='   Inicio Seleccionar Deudoras';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  SELECT COUNT(*)
  INTO lv_cont
  FROM cob_tempo_detal_asign_carte car;

  lv_des_log:='Cartera Cliente ' || lv_cont;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  SELECT COUNT(*)
  INTO lv_cont
  FROM
   ( SELECT car.cod_clie
     FROM cob_tempo_detal_asign_carte car
     HAVING COUNT(*) > 1
     GROUP BY car.cod_clie);

  lv_des_log:='Clientes Duplicados ' || lv_cont;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

 EXCEPTION

  WHEN OTHERS THEN
   gv_sqlerrm := SUBSTR(SQLERRM,1,250);
   lv_des_log:='   !!! Error ' || gv_sqlerrm ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

 END cob_pr_selec_carte_clien;

   PROCEDURE cob_pr_selec_carte_movim_venci (
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN       cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN       seg_usuar.cod_usua%TYPE,
      p_fec_cier                             OUT   VARCHAR2,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_ind_asig_zona                                cob_etapa_deuda_pais.ind_asig_zona%TYPE;
      lv_num_dias_gest                               cob_etapa_deuda_pais.num_dias_gest%TYPE;
      lv_num_dias_venc                               cob_etapa_deuda_pais.num_dias_venc%TYPE;
      lv_num_dias_espe                               cob_etapa_deuda_pais.num_dias_espe%TYPE;
      lv_val_edad_fina                                cob_etapa_deuda_pais.val_edad_fina%TYPE;
      lv_num_secu_etap                               cob_etapa_deuda_pais.num_secu_etap%TYPE;
      lv_num_dias_espe_etap_post             cob_etapa_deuda_pais.num_dias_espe%TYPE;
      lv_num_cobr                                        NUMBER(3);
      lv_cant_movim                                     NUMBER(12);
      e_no_exis_deud                                   EXCEPTION;

   BEGIN

      lv_des_log:='   Inicio Seleccionar Movimientos de Deuda x Vencimiento [' ||
                            p_cod_pais || ',' ||
                            p_cod_etap_deud || ',' ||
                            p_cod_cart || ',' ||
                            p_cod_usua || ']';

      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_detal_movim_carte');

      SELECT  ed.ind_asig_zona, ed.num_dias_gest,ed.num_secu_etap,ed.num_dias_venc , ed.num_dias_espe, ed.val_edad_fina
      INTO lv_ind_asig_zona,lv_num_dias_gest,lv_num_secu_etap,lv_num_dias_venc, lv_num_dias_espe, lv_val_edad_fina
      FROM cob_etapa_deuda_pais ed
      WHERE ed.cod_etap_deud=   p_cod_etap_deud;

      BEGIN
         SELECT dp.num_dias_espe
         INTO lv_num_dias_espe_etap_post
         FROM cob_etapa_deuda_pais dp
         WHERE dp.num_secu_etap = lv_num_secu_etap + 1;

         IF lv_num_dias_espe_etap_post < lv_val_edad_fina THEN
              lv_num_dias_espe_etap_post:=  lv_val_edad_fina;
         END IF;

      EXCEPTION
          WHEN no_data_found THEN
              lv_num_dias_espe_etap_post:= lv_val_edad_fina;
      END;

       p_fec_cier:= TRUNC(SYSDATE) + lv_num_dias_gest;

       -- Obteniendo el Numero de Cobradores --
       SELECT COUNT(*)
       INTO lv_num_cobr
       FROM cob_param_asign_carte_cobra cob
       WHERE cob.cod_pais = p_cod_pais
       AND cob.cod_etap_deud = p_cod_etap_deud;

       lv_des_log:='   Numero Cobradores : ' || lv_num_cobr;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       lv_des_log:='   Numero Dias Vencimiento : ' || lv_num_dias_venc;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       lv_des_log:='   Numero Dias Espera : ' || lv_num_dias_espe;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       lv_des_log:='   Numero Dias Espera Etapa Posterior : ' || lv_num_dias_espe_etap_post;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      CASE
         WHEN lv_ind_asig_zona='E' AND lv_num_cobr > 0 THEN

            lv_des_log:='   Excepto Zonas No Criticas y con Asignacion Generica';
            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

            INSERT INTO cob_tempo_detal_movim_carte
               SELECT
                  p_cod_pais,                          --COD_PAIS
                  p_cod_etap_deud,                --COD_ETAP_DEUD
                  spc.cod_peri,                          --COD_PERI
                  zr.cod_regi,                            --COD_REGI
                  'T',                                      --COD_ZONA
                  p_cod_cart,                          --COD_CART
                  mcc.clie_oid_clie,                   --OID_CLIE
                  mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
                  mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
                  mcc.fec_docu,                        --FEC_DOCU
                  mcc.fec_venc,                        --FEC_VENC
                  TRUNC(SYSDATE),                      --FEC_ASIG
                  p_fec_cier,                         --FEC_CIER
                  mcc.imp_movi,                        --IMP_DEUD_ORIG
                  mcc.imp_pend,                        --IMP_DEUD_ASIG
                  mcc.imp_pend,                        --IMP_DEUD_PEND
                  0,                                   --IMP_DEUD_CANC
                  0,                                   --IMP_PAGO_BANC
                  NULL,                                --FEC_ULT_PAGO_BANC
                  0,                                   --IMP_PAGO_OTRO
                  mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
                  mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
                  0,                                     -- IND_CUOT_ETAP_ANTE
                  0,                                     -- IND_CUOT_ETAP_POST
                  0,                                   --IND_ERRO
                  NULL,                                --COD_TIPO_EXCE
                  p_cod_usua,                          --USU_CREA
                  SYSDATE,                             --FEC_CREA
                  p_cod_usua,                          --USU_MODI
                  SYSDATE,                              --FEC_MODI
                  1,                                     --IND_DEUD_PEND
                  0                                      --IND_DEUD_SUPE
               FROM ccc_movim_cuent_corri mcc,
                           cra_perio cp,
                           seg_perio_corpo spc,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
               WHERE mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND mcc.clie_oid_clie = mcua.clie_oid_clie
               AND mcua.ind_acti = 1
               AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcc.imp_pend > 0
               AND TRUNC(SYSDATE) - mcc.fec_venc  >= lv_num_dias_venc
               AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_num_dias_espe
               AND TRUNC(SYSDATE) - mcc.fec_docu  <= lv_num_dias_espe_etap_post
               AND NOT EXISTS (
                                      SELECT NULL
                                      FROM cob_param_zonas_nocri zn
                                      WHERE zn.cod_pais = p_cod_pais
                                           AND zn.ind_acti = 1
                                           AND zn.cod_etap_deud = p_cod_etap_deud
                                           AND zn.cod_zona = zz.cod_zona)
               AND NOT EXISTS
                 (SELECT NULL
                  FROM cob_detal_movim_carte det
                  WHERE det.mvcc_oid_movi_cc = mcc.oid_movi_cc
                  AND det.fec_cier >= trunc(SYSDATE));

         WHEN lv_ind_asig_zona='E' AND lv_num_cobr = 0 THEN

            lv_des_log:='   Excepto Zonas No Criticas y Sin Asignacion Generica';
            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

            INSERT INTO cob_tempo_detal_movim_carte
               SELECT
                  p_cod_pais,                          --COD_PAIS
                  p_cod_etap_deud,                --COD_ETAP_DEUD
                  spc.cod_peri,                          --COD_PERI
                  zr.cod_regi,                            --COD_REGI
                  'T',                            --COD_ZONA
                  p_cod_cart,                          --COD_CART
                  mcc.clie_oid_clie,                   --OID_CLIE
                  mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
                  mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
                  mcc.fec_docu,                        --FEC_DOCU
                  mcc.fec_venc,                        --FEC_VENC
                  TRUNC(SYSDATE),                      --FEC_ASIG
                  p_fec_cier,                         --FEC_CIER
                  mcc.imp_movi,                        --IMP_DEUD_ORIG
                  mcc.imp_pend,                        --IMP_DEUD_ASIG
                  mcc.imp_pend,                        --IMP_DEUD_PEND
                  0,                                   --IMP_DEUD_CANC
                  0,                                   --IMP_PAGO_BANC
                  NULL,                                --FEC_ULT_PAGO_BANC
                  0,                                   --IMP_PAGO_OTRO
                  mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
                  mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
                  0,                                     -- IND_CUOT_ETAP_ANTE
                  0,                                     -- IND_CUOT_ETAP_POST
                  0,                                   --IND_ERRO
                  NULL,                                --COD_TIPO_EXCE
                  p_cod_usua,                          --USU_CREA
                  SYSDATE,                             --FEC_CREA
                  p_cod_usua,                          --USU_MODI
                  SYSDATE,                             --FEC_MODI
                  1,                                    --IND_DEUD_PEND
                  0                                     --IND_DEUD_SUPE
               FROM ccc_movim_cuent_corri mcc,
                           cra_perio cp,
                           seg_perio_corpo spc,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
               WHERE mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND mcc.clie_oid_clie = mcua.clie_oid_clie
               AND mcua.ind_acti = 1
               AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcc.imp_pend > 0
               AND TRUNC(SYSDATE) - mcc.fec_venc  >= lv_num_dias_venc
               AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_num_dias_espe
               AND TRUNC(SYSDATE) - mcc.fec_docu  <= lv_num_dias_espe_etap_post
               AND NOT EXISTS (
                                      SELECT NULL
                                      FROM cob_param_zonas_nocri zn
                                      WHERE zn.cod_pais = p_cod_pais
                                           AND zn.ind_acti = 1
                                           AND zn.cod_etap_deud = p_cod_etap_deud
                                           AND zn.cod_zona = zz.cod_zona)
                AND EXISTS (
                  SELECT NULL
                  FROM cob_param_asign_zonas_cobra c
                  WHERE c.cod_regi = zr.cod_regi
                  AND zz.cod_zona = DECODE(c.cod_zona,'T',zz.cod_zona,c.cod_zona))
               AND NOT EXISTS
                 (SELECT NULL
                  FROM cob_detal_movim_carte det
                  WHERE det.mvcc_oid_movi_cc = mcc.oid_movi_cc
                  AND det.fec_cier >= trunc(SYSDATE));

           WHEN lv_ind_asig_zona='C' THEN

              -- Solo Zonas No Criticas --
              lv_des_log:='   Solo Zonas No Criticas';

              FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

              INSERT INTO cob_tempo_detal_movim_carte
               SELECT
                  p_cod_pais,                          --COD_PAIS
                  p_cod_etap_deud,                --COD_ETAP_DEUD
                  spc.cod_peri,                          --COD_PERI
                  zr.cod_regi,                            --COD_REGI
                  'T',                            --COD_ZONA
                  p_cod_cart,                          --COD_CART
                  mcc.clie_oid_clie,                   --OID_CLIE
                  mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
                  mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
                  mcc.fec_docu,                        --FEC_DOCU
                  mcc.fec_venc,                        --FEC_VENC
                  TRUNC(SYSDATE),                      --FEC_ASIG
                  p_fec_cier,                         --FEC_CIER
                  mcc.imp_movi,                        --IMP_DEUD_ORIG
                  mcc.imp_pend,                        --IMP_DEUD_ASIG
                  mcc.imp_pend,                        --IMP_DEUD_PEND
                  0,                                   --IMP_DEUD_CANC
                  0,                                   --IMP_PAGO_BANC
                  NULL,                                --FEC_ULT_PAGO_BANC
                  0,                                   --IMP_PAGO_OTRO
                  mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
                  mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
                  0,                                     -- IND_CUOT_ETAP_ANTE
                  0,                                     -- IND_CUOT_ETAP_POST
                  0,                                   --IND_ERRO
                  NULL,                                --COD_TIPO_EXCE
                  p_cod_usua,                          --USU_CREA
                  SYSDATE,                             --FEC_CREA
                  p_cod_usua,                          --USU_MODI
                  SYSDATE,                             --FEC_MODI
                  1,                                    --IND_DEUD_PEND
                  0                                     --IND_DEUD_SUPE
               FROM ccc_movim_cuent_corri mcc,
                           cra_perio cp,
                           seg_perio_corpo spc,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
               WHERE mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND mcc.clie_oid_clie = mcua.clie_oid_clie
               AND mcua.ind_acti = 1
               AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcc.imp_pend > 0
               AND TRUNC(SYSDATE) - mcc.fec_venc  >= lv_num_dias_venc
               AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_num_dias_espe
               AND TRUNC(SYSDATE) - mcc.fec_docu  <= lv_num_dias_espe_etap_post
               AND EXISTS (
                                      SELECT NULL
                                      FROM cob_param_zonas_nocri zn
                                      WHERE zn.cod_pais = p_cod_pais
                                           AND zn.ind_acti = 1
                                           AND zn.cod_etap_deud = p_cod_etap_deud
                                           AND zn.cod_zona = zz.cod_zona)
               AND NOT EXISTS
                 (SELECT NULL
                  FROM cob_detal_movim_carte det
                  WHERE det.mvcc_oid_movi_cc = mcc.oid_movi_cc
                  AND det.fec_cier >= trunc(SYSDATE));

           WHEN lv_ind_asig_zona='T' AND lv_num_cobr > 0 THEN

              lv_des_log:='   Todas las Zonas y Con Asignacion Generica';
              FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

              -- Todas las Zonas  --
              INSERT INTO cob_tempo_detal_movim_carte
               SELECT
                  p_cod_pais,                          --COD_PAIS
                  p_cod_etap_deud,                --COD_ETAP_DEUD
                  spc.cod_peri,                          --COD_PERI
                  zr.cod_regi,                            --COD_REGI
                  'T',                            --COD_ZONA
                  p_cod_cart,                          --COD_CART
                  mcc.clie_oid_clie,                   --OID_CLIE
                  mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
                  mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
                  mcc.fec_docu,                        --FEC_DOCU
                  mcc.fec_venc,                        --FEC_VENC
                  TRUNC(SYSDATE),                      --FEC_ASIG
                  p_fec_cier,                         --FEC_CIER
                  mcc.imp_movi,                        --IMP_DEUD_ORIG
                  mcc.imp_pend,                        --IMP_DEUD_ASIG
                  mcc.imp_pend,                        --IMP_DEUD_PEND
                  0,                                   --IMP_DEUD_CANC
                  0,                                   --IMP_PAGO_BANC
                  NULL,                                --FEC_ULT_PAGO_BANC
                  0,                                   --IMP_PAGO_OTRO
                  mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
                  mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
                  0,                                     -- IND_CUOT_ETAP_ANTE
                  0,                                     -- IND_CUOT_ETAP_POST
                  0,                                   --IND_ERRO
                  NULL,                                --COD_TIPO_EXCE
                  p_cod_usua,                          --USU_CREA
                  SYSDATE,                             --FEC_CREA
                  p_cod_usua,                          --USU_MODI
                  SYSDATE,                             --FEC_MODI
                  1,                                    --IND_DEUD_PEND
                  0                                     --IND_DEUD_SUPE
               FROM ccc_movim_cuent_corri mcc,
                           cra_perio cp,
                           seg_perio_corpo spc,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
               WHERE mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND mcc.clie_oid_clie = mcua.clie_oid_clie
               AND mcua.ind_acti = 1
               AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcc.imp_pend > 0
               AND TRUNC(SYSDATE) - mcc.fec_venc  >= lv_num_dias_venc
               AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_num_dias_espe
               AND TRUNC(SYSDATE) - mcc.fec_docu  <= lv_num_dias_espe_etap_post
               AND NOT EXISTS
                 (SELECT NULL
                  FROM cob_detal_movim_carte det
                  WHERE det.mvcc_oid_movi_cc = mcc.oid_movi_cc
                  AND det.fec_cier >= trunc(SYSDATE));

      WHEN lv_ind_asig_zona='T' AND lv_num_cobr = 0 THEN

              lv_des_log:='   Todas las Zonas y Sin Asignacion Generica';
              FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

              -- Todas las Zonas  --
              INSERT INTO cob_tempo_detal_movim_carte
               SELECT
                  p_cod_pais,                          --COD_PAIS
                  p_cod_etap_deud,                --COD_ETAP_DEUD
                  spc.cod_peri,                          --COD_PERI
                  zr.cod_regi,                            --COD_REGI
                  'T',                            --COD_ZONA
                  p_cod_cart,                          --COD_CART
                  mcc.clie_oid_clie,                   --OID_CLIE
                  mcc.oid_movi_cc,                     --MVCC_OID_MOVI_CC
                  mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
                  mcc.fec_docu,                        --FEC_DOCU
                  mcc.fec_venc,                        --FEC_VENC
                  TRUNC(SYSDATE),                      --FEC_ASIG
                  p_fec_cier,                         --FEC_CIER
                  mcc.imp_movi,                        --IMP_DEUD_ORIG
                  mcc.imp_pend,                        --IMP_DEUD_ASIG
                  mcc.imp_pend,                        --IMP_DEUD_PEND
                  0,                                   --IMP_DEUD_CANC
                  0,                                   --IMP_PAGO_BANC
                  NULL,                                --FEC_ULT_PAGO_BANC
                  0,                                   --IMP_PAGO_OTRO
                  mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
                  mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
                  0,                                     -- IND_CUOT_ETAP_ANTE
                  0,                                     -- IND_CUOT_ETAP_POST
                  0,                                   --IND_ERRO
                  NULL,                                --COD_TIPO_EXCE
                  p_cod_usua,                          --USU_CREA
                  SYSDATE,                             --FEC_CREA
                  p_cod_usua,                          --USU_MODI
                  SYSDATE,                             --FEC_MODI
                  1,                                    --IND_DEUD_PEND
                  0                                     -- IND_DEUD_SUPE
               FROM ccc_movim_cuent_corri mcc,
                           cra_perio cp,
                           seg_perio_corpo spc,
                           mae_clien_unida_admin mcua,
                           zon_terri_admin zta,
                           zon_secci zs,
                           zon_zona zz,
                           zon_regio zr
               WHERE mcc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = spc.oid_peri
               AND mcc.clie_oid_clie = mcua.clie_oid_clie
               AND mcua.ind_acti = 1
               AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcc.imp_pend > 0
               AND TRUNC(SYSDATE) - mcc.fec_venc  >= lv_num_dias_venc
               AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_num_dias_espe
               AND TRUNC(SYSDATE) - mcc.fec_docu  <= lv_num_dias_espe_etap_post
               AND EXISTS (
                  SELECT NULL
                  FROM cob_param_asign_zonas_cobra c
                  WHERE c.cod_regi = zr.cod_regi
                  AND zz.cod_zona = DECODE(c.cod_zona,'T',zz.cod_zona,c.cod_zona))
               AND NOT EXISTS
                 (SELECT NULL
                  FROM cob_detal_movim_carte det
                  WHERE det.mvcc_oid_movi_cc = mcc.oid_movi_cc
                  AND det.fec_cier >= trunc(SYSDATE));

      ELSE

         lv_des_log:='   Parametria Incorrecta en el campo Asignacion de Zonas' ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      END CASE;

      SELECT COUNT(*)
      INTO lv_cant_movim
      FROM cob_tempo_detal_movim_carte;

      IF lv_cant_movim = 0 THEN
         lv_des_log:='   !!! No Existen Consultoras Deudoras :' || lv_cant_movim;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         RAISE e_no_exis_deud;

      END IF;

      lv_des_log:='   Cantidad de Movimientos seleccionados :' || lv_cant_movim;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='   Fin Seleccionar Movimientos de Deuda';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN e_no_exis_deud THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Se cancela la asignacion porque no hay Consultoras Deudoras' ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_selec_carte_movim_venci;

   PROCEDURE cob_pr_asign_excep_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                             IN     cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT    VARCHAR2)
   IS
      lv_cod_usua_super                           seg_usuar.use_usua%TYPE;

   CURSOR c_exce
      IS
         SELECT ex.val_nomb_prog, ex.cod_tipo_exce, ex.val_mens_exce
         FROM cob_param_asign_carte_excep ex
         WHERE ex.cod_pais = p_cod_pais
         AND ex.cod_etap_deud = p_cod_etap_deud
         AND ex.ind_acti = 1
         ORDER BY ex.num_orde_exce ASC;

   BEGIN

      lv_des_log:=NULL;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='*** INICIO Excepciones de Cartera ***';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      SELECT dp.cod_usua_supe
      INTO lv_cod_usua_super
      FROM cob_etapa_deuda_pais dp
      WHERE dp.cod_pais = p_cod_pais
      AND dp.cod_etap_deud = p_cod_etap_deud;

      lv_des_log:='    Usuario Supervisor : ' || lv_cod_usua_super ;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      FOR v_exce IN c_exce LOOP

         cob_pr_asign_excep_carte_indiv(p_cod_pais,p_cod_etap_deud, p_cod_cart,lv_cod_usua_super,v_exce.cod_tipo_exce,v_exce.val_nomb_prog,p_cod_erro);

         IF p_cod_erro IS NOT NULL THEN

            EXIT;

         END IF;

      END LOOP;

      lv_des_log:=' *** FIN Excepciones de Cartera ***';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   END cob_pr_asign_excep_carte;

   PROCEDURE cob_pr_asign_excep_carte_indiv(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN      seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN     cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_nomb_func                          IN    VARCHAR2,
      p_cod_erro                             OUT VARCHAR2)
   IS

      lv_sql            VARCHAR2(250);

   BEGIN

      lv_sql:='BEGIN ' || p_nomb_func || '(:1,:2,:3,:4,:5,:6); END;';

      EXECUTE IMMEDIATE lv_sql
      USING IN p_cod_pais, IN p_cod_etap_deud,IN p_cod_cart,IN p_cod_usua_supe, IN p_cod_tipo_exec, OUT p_cod_erro;

   END cob_pr_asign_excep_carte_indiv;

   PROCEDURE cob_pr_asign_excep_monto_minim(
      p_cod_pais                             IN   seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN   cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN   seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN   cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT  VARCHAR2)
   IS

      lv_imp_desd  NUMBER(12,2);
      p_cod_modu                 VARCHAR2(3):='COB';
      p_cod_proc                    VARCHAR2(4):='1508';

   BEGIN

       lv_des_log:='    Inicio Excepciones Por Monto Minimo';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,p_cod_modu,P_cod_proc,lv_id_proc_ejec,lv_des_log);

       SELECT dp.imp_desd
       INTO lv_imp_desd
       FROM cob_etapa_deuda_pais dp
       WHERE dp.cod_pais = p_cod_pais
       AND dp.cod_etap_deud = p_cod_etap_deud;

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_tipo_exec = p_cod_tipo_exec,
              car.cod_usua_cobr = p_cod_usua_supe,
              car.ind_cart_supe = 1,
              car.ind_im = 1
      WHERE car.cod_cart = p_cod_cart
      AND car.imp_deud_asig < lv_imp_desd
      AND car.cod_tipo_exec IS NULL;

       lv_des_log:='   Deudoras Asignadas a Supervisor ' || SQL%ROWCOUNT;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,p_cod_modu,P_cod_proc,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='    Fin Excepciones Por Monto Minimo ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,p_cod_modu,P_cod_proc,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_excep_monto_minim;

   PROCEDURE cob_pr_asign_excep_monto_maxim(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN      seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT  VARCHAR2)
   IS

      lv_imp_hast   NUMBER(12,2);

   BEGIN

       lv_des_log:='    Inicio Excepciones Por Monto Maximo';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

       SELECT dp.imp_hast
       INTO lv_imp_hast
       FROM cob_etapa_deuda_pais dp
       WHERE dp.cod_pais = p_cod_pais
       AND dp.cod_etap_deud = p_cod_etap_deud;

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_tipo_exec = p_cod_tipo_exec,
              car.cod_usua_cobr = p_cod_usua_supe,
               car.ind_cart_supe = 1
      WHERE car.cod_cart = p_cod_cart
      AND car.imp_deud_asig > lv_imp_hast
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='    Deudoras Asignadas a Supervisor ' || SQL%ROWCOUNT;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='    Fin Excepciones Por Monto Maximo ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_excep_monto_maxim;

   PROCEDURE cob_pr_asign_excep_sinte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN       seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT  VARCHAR2)
   IS

   BEGIN

       lv_des_log:='   Inicio Excepcion Deudoras Sin Telefono';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_tipo_exec = p_cod_tipo_exec ,
              car.cod_usua_cobr = p_cod_usua_supe,
               car.ind_cart_supe = 1,
               car.ind_st = 1
      WHERE car.cod_etap_deud= p_cod_etap_deud
      AND car.cod_cart = p_cod_cart
      AND TRIM(car.num_tele_fijo) IS NULL
      AND TRIM(car.num_tele_trab) IS NULL
      AND TRIM(car.num_tele_movi) IS NULL
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='   Deudoras Asignadas a Supervisor ' || SQL%ROWCOUNT;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='   Fin Excepcion Deudoras Sin Telefono';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_excep_sinte;

   PROCEDURE cob_pr_asign_excep_clasi_clien(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua_supe                  IN       seg_usuar.use_usua%TYPE,
      p_cod_tipo_exec                   IN      cob_param_asign_carte_excep.cod_tipo_exce%TYPE,
      p_cod_erro                            OUT  VARCHAR2)
   IS

   BEGIN

       lv_des_log:='   Inicio Excepcion Clasificacion Cliente';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_tipo_exec = p_cod_tipo_exec ,
              car.cod_usua_cobr = p_cod_usua_supe,
               car.ind_cart_supe = 1
      WHERE EXISTS
        ( SELECT NULL
          FROM
             mae_clien_tipo_subti ,
             mae_clien_clasi,
             mae_tipo_clien,
             mae_subti_clien,
             mae_tipo_clasi_clien,
             mae_clasi,
             cob_param_excep_clasi_clien
          WHERE mae_clien_tipo_subti.oid_clie_tipo_subt = mae_clien_clasi.ctsu_oid_clie_tipo_subt
               AND mae_tipo_clien.oid_tipo_clie = mae_clien_tipo_subti.ticl_oid_tipo_clie
               AND mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie
               AND mae_subti_clien.oid_subt_clie = mae_clien_tipo_subti.sbti_oid_subt_clie
               AND mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clasi_clien.oid_tipo_clas = mae_clien_clasi.tccl_oid_tipo_clasi
               AND mae_tipo_clasi_clien.oid_tipo_clas = mae_clasi.tccl_oid_tipo_clas
               AND mae_clasi.oid_clas = mae_clien_clasi.clas_oid_clas
               AND mae_clien_tipo_subti.ind_ppal = 1
               AND mae_clien_clasi.ind_ppal = 1
               AND cob_param_excep_clasi_clien.oid_tipo_clie = mae_tipo_clien.oid_tipo_clie
               AND cob_param_excep_clasi_clien.oid_subt_clie = mae_subti_clien.oid_subt_clie
               AND cob_param_excep_clasi_clien.oid_tipo_clas_clie = mae_tipo_clasi_clien.oid_tipo_clas
               AND cob_param_excep_clasi_clien.oid_clas_clie = mae_clasi.oid_clas
               AND cob_param_excep_clasi_clien.cod_pais = p_cod_pais
               AND cob_param_excep_clasi_clien.cod_etap_deud = p_cod_etap_deud
               AND car.cod_cart = p_cod_cart
               AND mae_clien_tipo_subti.clie_oid_clie = car.oid_clie);

      lv_des_log:='   Deudoras Asignadas a Supervisor ' || SQL%ROWCOUNT;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='   Fin Excepcion Excepcion Clasificacion Cliente';
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_excep_clasi_clien;

   PROCEDURE cob_pr_ejecu_asign_carte(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_fec_cier                             IN      DATE,
      p_ind_tipo_asig                     IN     cob_param_gener.val_para%TYPE,
      p_cod_usua                            IN      cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      CURSOR c_zonas IS
         SELECT DISTINCT  zc.cod_zona
         FROM cob_param_asign_zonas_cobra zc
         WHERE zc.cod_pais = p_cod_pais
              AND zc.ind_acti = 1
              AND zc.cod_etap_deud = p_cod_etap_deud
              AND zc.cod_regi  = p_cod_regi
              AND zc.cod_zona NOT IN (
                                 SELECT cro.cod_zona
                                 FROM cob_crono_carte cro
                                 WHERE cro.cod_pais = p_cod_pais
                                       AND cro.cod_etap_deud = p_cod_etap_deud
                                       AND cro.cod_peri = p_cod_peri
                                       AND cro.cod_regi = p_cod_regi
                                       AND cro.cod_zona <> 'T')
         ORDER BY TO_NUMBER(DECODE(zc.cod_zona,'T','9999', zc.cod_zona)) ASC;

      CURSOR c_unid_admi_venc IS
         SELECT zc.cod_regi,  zc.cod_zona
         FROM cob_param_asign_zonas_cobra zc
         WHERE zc.cod_pais = p_cod_pais
              AND zc.cod_etap_deud = p_cod_etap_deud
              AND zc.ind_acti = 1
         ORDER BY TO_NUMBER(DECODE(zc.cod_zona,'T','9999', zc.cod_zona)) ASC;

      lv_cant_cobr_fijo                              NUMBER(12);
      lv_cant_cobr_bala                              NUMBER(12);
      lv_imp_cart_tota                               NUMBER(12,2);
      lv_ind_asig_movi_etap_ante             NUMBER(1);
      lv_ind_asig_movi_etap_post             NUMBER(1);

   BEGIN

      lv_des_log:=NULL;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='*** INICIO Asignacion de Cartera';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      SELECT ed.ind_asig_movi_etap_ante , ed.ind_asig_movi_etap_post
      INTO lv_ind_asig_movi_etap_ante , lv_ind_asig_movi_etap_post
      FROM cob_etapa_deuda_pais ed
      WHERE ed.cod_pais = p_cod_pais
           AND ed.cod_etap_deud = p_cod_etap_deud;

      IF lv_ind_asig_movi_etap_ante = 1 THEN
         lv_des_log:='      Reasignacion Cuotas Etapas Anteriores';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         cob_pr_asign_carte_cuota_eante(p_cod_pais,p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_cod_cart,p_fec_cier,p_cod_usua,p_cod_erro);
      END IF;

      IF lv_ind_asig_movi_etap_post = 1 THEN
         lv_des_log:='      Asignacion Cuotas Etapas Posteriores';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         cob_pr_asign_carte_cuota_epost(p_cod_pais,p_cod_etap_deud,p_cod_usua,p_cod_erro);
      END IF;

      CASE

         WHEN p_ind_tipo_asig = 'C' AND p_cod_zona = 'T' THEN

            FOR  v_zonas IN c_zonas  LOOP
               -- Asignacion de Cartera Fija
               cob_pr_asign_carte_fija(p_cod_pais,p_cod_etap_deud,p_cod_regi, v_zonas.cod_zona, p_cod_cart,p_cod_erro);
            END LOOP;

         WHEN  p_ind_tipo_asig = 'C' AND p_cod_zona <> 'T' THEN

             SELECT COUNT(*)
             INTO lv_cant_cobr_fijo
             FROM cob_param_asign_zonas_cobra zc
             WHERE zc.cod_pais = p_cod_pais
             AND zc.ind_acti = 1
             AND zc.cod_etap_deud = p_cod_etap_deud
             AND zc.cod_regi  = p_cod_regi
             AND zc.cod_zona = p_cod_zona;

             IF lv_cant_cobr_fijo > 0 THEN
                -- Asignacion de Cartera Fija
               cob_pr_asign_carte_fija(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);
             END IF;

         ELSE

            FOR  v_unid_admi_venc IN c_unid_admi_venc  LOOP
                  cob_pr_asign_carte_fija(p_cod_pais,p_cod_etap_deud,v_unid_admi_venc.cod_regi, v_unid_admi_venc.cod_zona, p_cod_cart,p_cod_erro);
            END LOOP;

      END CASE;

      SELECT COUNT(*)
      INTO lv_cant_cobr_bala
      FROM cob_param_asign_carte_cobra ac
      WHERE ac.cod_pais = p_cod_pais
      AND ac.cod_etap_deud = p_cod_etap_deud
      AND ac.ind_acti = 1;

      -- Importe Total x Asignar --
      SELECT SUM(car.imp_deud_asig)
      INTO lv_imp_cart_tota
      FROM cob_tempo_detal_asign_carte car
      WHERE car.cod_cart = p_cod_cart
      AND car.cod_usua_cobr = lv_user_dummy
      AND car.cod_tipo_exec IS NULL;


      IF ( lv_imp_cart_tota> 0) THEN

         IF (lv_cant_cobr_bala > 0 ) THEN

            IF p_ind_tipo_asig ='V' THEN

               UPDATE cob_tempo_detal_asign_carte
               SET cod_regi='T',
                       cod_zona='T';

            END IF;

            cob_pr_asign_carte_gener(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

         ELSE
            lv_des_log:='    !!! ERROR No hay cobradores para la Asignacion Generica';
            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
            p_cod_erro  := 'procesoCOBAsignacionCarteraForm.error.no.documentado';
         END IF;

      END IF;

      -- Asignacion Cartera Supervisor --
      cob_pr_asign_carte_super(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_cod_cart);

      lv_des_log:='*** FIN Asignacion de Cartera';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_ejecu_asign_carte;

 PROCEDURE cob_pr_asign_carte_cuota_eante(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_fec_cier                       IN   cob_cabec_asign_carte.fec_cier%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  lv_num_secu_etap                   NUMBER(2);

 BEGIN

  FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_detal_movim_reasi');

  SELECT a.num_secu_etap
  INTO lv_num_secu_etap
  FROM cob_etapa_deuda_pais a
  WHERE a.cod_etap_deud = p_cod_etap_deud;

  INSERT INTO cob_tempo_detal_movim_reasi
   SELECT
    p_cod_pais,                          --COD_PAIS
    mcc.cod_etap_deud,                --COD_ETAP_DEUD
    mcc.cod_peri,                          --COD_PERI
    mcc.cod_regi,                           --COD_REGI
    mcc.cod_zona,                           --COD_ZONA
    mcc.cod_cart,                          --COD_CART
    mcc.oid_clie,                   --OID_CLIE
    mcc.mvcc_oid_movi_cc,                     --MVCC_OID_MOVI_CC
    mcc.num_iden_cuot,                   --NUM_IDEN_CUOT
    mcc.fec_docu,                        --FEC_DOCU
    mcc.fec_venc,                        --FEC_VENC
    TRUNC(SYSDATE),                      --FEC_ASIG
    p_fec_cier,                         --FEC_CIER
    mcc.imp_deud_orig,             --IMP_DEUD_ORIG
    mcc.imp_deud_pend,             --IMP_DEUD_ASIG
    mcc.imp_deud_pend,             --IMP_DEUD_PEND
    0,                                   --IMP_DEUD_CANC
    0,                                   --IMP_PAGO_BANC
    NULL,                                --FEC_ULT_PAGO_BANC
    0,                                   --IMP_PAGO_OTRO
    mcc.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
    mcc.val_ulti_nume_hist,              --VAL_ASIG_NUME_HIST
    1,                                     -- IND_CUOT_ETAP_ANTE
    0,                                     -- IND_CUOT_ETAP_POST
    0,                                   --IND_ERRO
    NULL,                                --COD_TIPO_EXCE
    p_cod_usua,                          --USU_CREA
    SYSDATE,                             --FEC_CREA
    p_cod_usua,                          --USU_MODI
    SYSDATE,                             --FEC_MODI
    1,                                    --IND_DEUD_PEND
    0
   FROM
    cob_detal_movim_carte mcc,
    cob_etapa_deuda_pais ed
   WHERE mcc.fec_cier > TRUNC(SYSDATE)
     AND  mcc.ind_deud_pend = 1
     AND mcc.cod_etap_deud = ed.cod_etap_deud
     AND ed.num_secu_etap < lv_num_secu_etap
     AND EXISTS (
        SELECT NULL
        FROM cob_tempo_detal_asign_carte det
        WHERE det.cod_cart = p_cod_cart
          AND det.oid_clie = mcc.oid_clie
          AND det.cod_tipo_exec IS NULL)
     AND NOT EXISTS (
        SELECT NULL
        FROM cob_tempo_detal_movim_carte cmc
        WHERE cmc.cod_cart = p_cod_cart
          AND cmc.mvcc_oid_movi_cc = mcc.mvcc_oid_movi_cc);

  INSERT INTO cob_detal_movim_asign_eante
   SELECT
    tdm.cod_pais,
    tdm.cod_etap_deud,
    tdm.cod_cart,
    p_cod_etap_deud,
    p_cod_cart,
    tdm.oid_clie,
    tdm.mvcc_oid_movi_cc
   FROM
    cob_tempo_detal_movim_reasi tdm;

  lv_des_log:='   Cuotas Reasignadas ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  -- Actualizando las Cuotas Reasignadas
  UPDATE cob_detal_movim_carte dcm
  SET
   dcm.fec_cier = TRUNC(SYSDATE)-1,
   dcm.ind_cuot_etap_post = 1
  WHERE (dcm.cod_cart , dcm.mvcc_oid_movi_cc) IN
   (SELECT t4.cod_cart_orgi, t4.mvcc_oid_movi_cc
    FROM cob_detal_movim_asign_eante t4
    WHERE t4.cod_etap_deud_dest = p_cod_etap_deud
      AND t4.cod_cart_dest = p_cod_cart);

  UPDATE cob_tempo_detal_movim_reasi dmr
  SET
   dmr.cod_etap_deud = p_cod_etap_deud,
   dmr.cod_peri = p_cod_peri,
   dmr.cod_regi = p_cod_regi,
   dmr.cod_zona = p_cod_zona,
   dmr.cod_cart = p_cod_cart,
   dmr.ind_cuot_etap_ante = 1;

  -- Asignando las cuotas desde Etapas Anteriores
  INSERT INTO cob_detal_movim_carte
   SELECT *
   FROM cob_tempo_detal_movim_reasi;

 EXCEPTION
  WHEN OTHERS THEN
   gv_sqlerrm := SUBSTR(SQLERRM,1,250);
   lv_des_log:='   !!! Error ' || gv_sqlerrm ;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

 END cob_pr_asign_carte_cuota_eante;

 PROCEDURE cob_pr_asign_carte_cuota_epost(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  lv_num_secu_etap                   NUMBER(2);

 BEGIN

  FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('cob_tempo_detal_movim_reasi');

      SELECT a.num_secu_etap
      INTO lv_num_secu_etap
      FROM cob_etapa_deuda_pais a
      WHERE a.cod_pais = p_cod_pais
      AND a.cod_etap_deud = p_cod_etap_deud;

      INSERT INTO cob_tempo_detal_movim_reasi
      WITH temp1 AS
         (SELECT
            tmc.cod_pais,                          --COD_PAIS
            edp.num_secu_etap,                     -- NUM_SECU_ETAP
            det.cod_etap_deud,                     --COD_ETAP_DEUD
            det.cod_peri,                          --COD_PERI
            det.cod_regi,                          --COD_REGI
            det.cod_zona,                          --COD_ZONA
            det.cod_cart,                          --COD_CART
            tmc.oid_clie,                          --OID_CLIE
            tmc.mvcc_oid_movi_cc,                  --MVCC_OID_MOVI_CC
            tmc.num_iden_cuot,                     --NUM_IDEN_CUOT
            tmc.fec_docu,                          --FEC_DOCU
            tmc.fec_venc,                          --FEC_VENC
            tmc.fec_cier,                          --FEC_CIER
            tmc.imp_deud_orig,                     --IMP_DEUD_ORIG
            tmc.imp_deud_pend   imp_deud_asig,     --IMP_DEUD_ASIG
            tmc.imp_deud_pend,                     --IMP_DEUD_PEND
            tmc.val_ulti_nume_hist,                --VAL_ULTI_NUME_HIST
            tmc.val_ulti_nume_hist val_asig_nume_hist,  --VAL_ASIG_NUME_HIST
            tmc.ind_deud_pend,                           --IND_DEUD_PEND
            0
         FROM
            cob_tempo_detal_movim_carte tmc,
            cob_detal_asign_carte det,
            cob_etapa_deuda_pais edp
         WHERE det.cod_etap_deud = edp.cod_etap_deud
             AND edp.num_secu_etap > lv_num_secu_etap
             AND det.oid_clie = tmc.oid_clie
             AND det.cod_tipo_exec IS NULL
             AND det.fec_cier > trunc(SYSDATE)
             AND det.imp_deud_pend > 0),
           temp2 AS
             ( SELECT t1.oid_clie, MAX(num_secu_etap) num_secu_etap_maxi
             FROM temp1 t1
             GROUP BY t1.oid_clie)
             SELECT
               t1.cod_pais,                          --COD_PAIS
               t1.cod_etap_deud,                --COD_ETAP_DEUD
               t1.cod_peri,                          --COD_PERI
               t1.cod_regi,                           --COD_REGI
               t1.cod_zona,                           --COD_ZONA
               t1.cod_cart,                          --COD_CART
               t1.oid_clie,                   --OID_CLIE
               t1.mvcc_oid_movi_cc,                     --MVCC_OID_MOVI_CC
               t1.num_iden_cuot,                   --NUM_IDEN_CUOT
               t1.fec_docu,                        --FEC_DOCU
               t1.fec_venc,                        --FEC_VENC
               TRUNC(SYSDATE),                      --FEC_ASIG
               t1.fec_cier,                         --FEC_CIER
               t1.imp_deud_orig,             --IMP_DEUD_ORIG
               t1.imp_deud_asig,             --IMP_DEUD_ASIG
               t1.imp_deud_pend,             --IMP_DEUD_PEND
               0,                                   --IMP_DEUD_CANC
               0,                                   --IMP_PAGO_BANC
               NULL,                                --FEC_ULT_PAGO_BANC
               0,                                   --IMP_PAGO_OTRO
               t1.val_ulti_nume_hist,              --VAL_ULTI_NUME_HIST
               t1.val_asig_nume_hist,              --VAL_ASIG_NUME_HIST
               1,                                     -- IND_CUOT_ETAP_ANTE
               0,                                     -- IND_CUOT_ETAP_POST
               0,                                   --IND_ERRO
               NULL,                                --COD_TIPO_EXCE
               p_cod_usua,                          --USU_CREA
               SYSDATE,                             --FEC_CREA
               p_cod_usua,                          --USU_MODI
               SYSDATE,                             --FEC_MODI
               t1.ind_deud_pend,                     --IND_DEUD_PEND
               0                                     -- IND SUPE
             FROM temp1 t1,
                        temp2 t2
             WHERE t1.num_secu_etap = t2.num_secu_etap_maxi
             AND t1.oid_clie = t2.oid_clie;

       lv_des_log:='   Cuotas Reasignadas ' || SQL%ROWCOUNT;
       FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

     -- Eliminando las Cuotas de las Cartera por Asignarse
     DELETE FROM cob_tempo_detal_movim_carte tdm
        WHERE EXISTS
           (SELECT NULL
             FROM cob_tempo_detal_movim_reasi dmr
             WHERE dmr.mvcc_oid_movi_cc = tdm.mvcc_oid_movi_cc);

     -- Eliminado a los Clientes
     DELETE FROM cob_tempo_detal_asign_carte tac
        WHERE EXISTS
           (SELECT NULL
             FROM cob_tempo_detal_movim_reasi dmr
             WHERE dmr.oid_clie = tac.oid_clie);

      -- Reasignando las cuotas a la Etapa Posterior
      INSERT INTO cob_detal_movim_carte
         SELECT *
         FROM cob_tempo_detal_movim_reasi;

       -- Actualizando las Cabeceras --
       UPDATE cob_detal_asign_carte car
       SET (car.imp_deud_orig,car.imp_deud_asig,car.imp_deud_canc, car.imp_deud_pend) =
                 (SELECT
                     SUM(det.imp_deud_orig),
                     SUM(det.imp_deud_asig),
                     SUM(det.imp_deud_canc),
                     SUM(det.imp_deud_pend)
                  FROM cob_detal_movim_carte  det
                  WHERE det.cod_cart = car.cod_cart
                     AND det.oid_clie = car.oid_clie)
       WHERE EXISTS(
                          SELECT NULL
                          FROM cob_tempo_detal_movim_reasi
                          WHERE cod_cart = car.cod_cart
                          AND oid_clie = car.oid_clie);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_cuota_epost;

   PROCEDURE cob_pr_asign_carte_fija(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS
      lv_cant_cobr                                   NUMBER(3);

   BEGIN

      lv_des_log:='    Inicio Asignacion Fija : [ ' || p_cod_etap_deud || ',' || p_cod_regi || ',' || p_cod_zona || ',' || p_cod_cart || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      -- Obteniendo la Cantidad de Cobradores
       SELECT COUNT(*)
         INTO lv_cant_cobr
         FROM cob_param_asign_zonas_cobra cob
         WHERE cob.cod_pais = p_cod_pais
              AND cob.cod_etap_deud = p_cod_etap_deud
              AND cob.cod_regi = p_cod_regi
              AND cob.cod_zona = p_cod_zona
              AND cob.ind_acti = 1;

      lv_des_log:='    Cantidad de Cobradores : ' || lv_cant_cobr;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      CASE
        WHEN lv_cant_cobr = 1 THEN

           cob_pr_asign_carte_fija_sinba(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

        WHEN lv_cant_cobr > 1 THEN

           cob_pr_asign_carte_fija_conba(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

        ELSE
          lv_des_log:='    La parametria de Cobradores no es correcta';
          FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      END CASE;

      lv_des_log:='    Fin Asignacion Fija';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_fija;

   PROCEDURE cob_pr_asign_carte_fija_sinba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_cod_cobr                                    cob_usuar_cobra_pais.cod_usua_cobr%TYPE;

   BEGIN

      lv_des_log:='       Inicio Asignacion Fija Sin Balanceo: [ ' || p_cod_etap_deud || ',' || p_cod_regi || ',' || p_cod_zona || ',' || p_cod_cart || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

     SELECT cob.cod_usua_cobr
      INTO lv_cod_cobr
      FROM cob_param_asign_zonas_cobra cob
      WHERE cob.cod_pais = p_cod_pais
           AND cob.cod_etap_deud = p_cod_etap_deud
           AND cob.cod_regi = p_cod_regi
           AND cob.cod_zona = p_cod_zona
           AND cob.ind_acti = 1;

      lv_des_log:='       Codigo Cobrador : ' || lv_cod_cobr ;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_usua_cobr = lv_cod_cobr
      WHERE car.cod_pais = p_cod_pais
      AND car.cod_etap_deud = p_cod_etap_deud
      AND car.cod_regi = p_cod_regi
      AND DECODE(p_cod_zona,'T',car.cod_zona,car.cod_zona_clie) = p_cod_zona
      AND car.cod_cart = p_cod_cart
      AND car.cod_usua_cobr = lv_user_dummy
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='      Deudoras Asignadas ' || SQL%ROWCOUNT;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='       Fin Asignacion Fija Sin Balanceo';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='      !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_fija_sinba;

   PROCEDURE cob_pr_asign_carte_fija_conba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

         lv_ind_tipo_bala                            cob_param_gener.val_para%TYPE;

   BEGIN

      lv_des_log:='    Inicio Asignacion Fija Con Balanceo';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      cob_pr_asign_carte_fija_cobra(p_cod_pais, p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

      SELECT ed.ind_tipo_bala
      INTO lv_ind_tipo_bala
      FROM cob_etapa_deuda_pais ed
      WHERE ed.cod_pais = p_cod_pais
      AND ed.cod_etap_deud = p_cod_etap_deud;

      IF lv_ind_tipo_bala = 'C' THEN

         lv_des_log:='    Balanceo x Cliente';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         cob_pr_asign_carte_balan_clien(p_cod_pais,p_cod_cart,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_erro);

      ELSIF lv_ind_tipo_bala = 'Z' THEN
         lv_des_log:='    Balanceo x Zona : [ ' || p_cod_etap_deud || ',' || p_cod_regi || ',' || p_cod_zona || ',' || p_cod_cart || ']';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

        cob_pr_asign_carte_balan_zona(p_cod_pais,p_cod_cart,p_cod_erro);

       ELSE

         lv_des_log:='    Indicador de Balanceo No Configurado Correctamente';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      END IF;

      lv_des_log:='    Fin Asignacion Fija Con Balanceo';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_fija_conba;

   PROCEDURE cob_pr_asign_carte_fija_cobra(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN       cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT    VARCHAR2)
   IS

      lv_imp_cart_tota                               NUMBER(12,2);

   BEGIN

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_ASIGN_CARTE_COBRA');

      -- Importe Total x Asignar --
      SELECT SUM(car.imp_deud_asig)
      INTO lv_imp_cart_tota
      FROM cob_tempo_detal_asign_carte car
      WHERE car.cod_etap_deud = p_cod_etap_deud
      AND car.cod_regi = p_cod_regi
      AND DECODE(p_cod_zona, 'T',car.cod_zona,car.cod_zona_clie) = p_cod_zona
      AND car.cod_usua_cobr = lv_user_dummy
      AND car.cod_cart = p_cod_cart
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='   Cantidad Total por Asignar  : ' || lv_imp_cart_tota;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      INSERT INTO cob_tempo_asign_carte_cobra
      SELECT
         cod_usua_cobr,
         val_cant_cart_asign,
         imp_cart_asign,
         imp_esti_cart_asign,
         ind_asig
      FROM
       (SELECT
            dbms_random.value cid,
            azc.cod_usua_cobr,
            0  val_cant_cart_asign,
            0  imp_cart_asign,
            ROUND(azc.imp_porc_asig*lv_imp_cart_tota/100,2) imp_esti_cart_asign,
            0 ind_asig
         FROM cob_param_asign_zonas_cobra azc
         WHERE azc.cod_pais = p_cod_pais
              AND azc.cod_etap_deud = p_cod_etap_deud
              AND azc.cod_regi = p_cod_regi
              AND azc.cod_zona = p_cod_zona
              AND azc.ind_acti=1)
      ORDER BY cid ASC;

       lv_des_log:='   Cobradores Cargados ' || SQL%ROWCOUNT;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_fija_cobra;

   PROCEDURE cob_pr_asign_carte_gener(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_cant_cobr                                      NUMBER(3);

   BEGIN

      lv_des_log:='    Inicio Asignacion Generica : [ ' || p_cod_etap_deud || ',' ||  p_cod_cart || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      -- Obteniendo la Cantidad de Cobradores
      SELECT COUNT(*)
      INTO lv_cant_cobr
      FROM cob_param_asign_carte_cobra cob
      WHERE cob.cod_pais = p_cod_pais
           AND cob.cod_etap_deud = p_cod_etap_deud
           AND cob.ind_acti = 1;

      lv_des_log:='    Cantidad de Cobradores : ' || lv_cant_cobr;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      CASE
        WHEN lv_cant_cobr = 1 THEN

           cob_pr_asign_carte_gener_sinba(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

        WHEN lv_cant_cobr > 1 THEN

           cob_pr_asign_carte_gener_conba(p_cod_pais,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,p_cod_erro);

        ELSE
          lv_des_log:='    La parametria de Cobradores no es correcta';
          FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      END CASE;

      lv_des_log:='    Fin Asignacion Generica';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_gener;

   PROCEDURE cob_pr_asign_carte_gener_sinba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_cod_cobr                                    cob_usuar_cobra_pais.cod_usua_cobr%TYPE;

   BEGIN

      lv_des_log:='       Inicio Asignacion Generica Sin Balanceo: [ ' ||
         p_cod_etap_deud || ',' ||
         p_cod_regi || ',' ||
         p_cod_zona || ',' ||
         p_cod_cart || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

     SELECT cob.cod_usua_cobr
      INTO lv_cod_cobr
      FROM cob_param_asign_carte_cobra cob
      WHERE cob.cod_pais = p_cod_pais
           AND cob.cod_etap_deud = p_cod_etap_deud
           AND cob.ind_acti = 1;

      lv_des_log:='       Codigo Cobrador : ' || lv_cod_cobr ;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      UPDATE cob_tempo_detal_asign_carte car
      SET car.cod_usua_cobr = lv_cod_cobr
      WHERE car.cod_pais = p_cod_pais
      AND car.cod_etap_deud = p_cod_etap_deud
      AND car.cod_regi = p_cod_regi
      AND DECODE(p_cod_zona,'T',car.cod_zona,car.cod_zona_clie) = p_cod_zona
      AND car.cod_cart = p_cod_cart
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='      Deudoras Asignadas ' || SQL%ROWCOUNT;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_des_log:='       Fin Asignacion Generica Sin Balanceo';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='      !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_gener_sinba;

   PROCEDURE cob_pr_asign_carte_gener_conba(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_ind_tipo_bala                            cob_param_gener.val_para%TYPE;

   BEGIN

      lv_des_log:='    Inicio Asignacion Generica con Balanceo: [ ' || p_cod_etap_deud || ',' || p_cod_regi || ',' || p_cod_zona || ',' || p_cod_cart || ']';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      cob_pr_asign_carte_gener_cobra(p_cod_pais,p_cod_etap_deud,p_cod_cart,p_cod_erro);

      SELECT ed.ind_tipo_bala
      INTO lv_ind_tipo_bala
      FROM cob_etapa_deuda_pais ed
      WHERE ed.cod_pais = p_cod_pais
      AND ed.cod_etap_deud = p_cod_etap_deud;

      IF lv_ind_tipo_bala = 'C' THEN
         lv_des_log:='    Balanceo x Cliente';
               FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         cob_pr_asign_carte_balan_clien(p_cod_pais,p_cod_cart,p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_erro);

      ELSIF lv_ind_tipo_bala = 'Z' THEN
           lv_des_log:='    Balanceo x Zona';
               FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

           cob_pr_asign_carte_balan_zona(p_cod_pais,p_cod_cart,p_cod_erro);

      ELSE

         lv_des_log:='    Indicador de Balanceo No Configurado Correctamente';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      END IF;

      lv_des_log:='    Fin Asignacion Generica con Balanceo ';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_gener_conba;

   PROCEDURE cob_pr_asign_carte_gener_cobra(
      p_cod_pais                             IN        seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN        cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_cart                            IN         cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                            OUT     VARCHAR2)
   IS

      lv_imp_cart_tota                     NUMBER(12,2);

   BEGIN

      FIN_PKG_GENER.FIN_PR_TRUNC_TABLE('COB_TEMPO_ASIGN_CARTE_COBRA');

      -- Importe Total x Asignar --
      SELECT SUM(car.imp_deud_asig)
      INTO lv_imp_cart_tota
      FROM cob_tempo_detal_asign_carte car
      WHERE car.cod_etap_deud = p_cod_etap_deud
           AND car.cod_cart = p_cod_cart
      AND ( car.cod_usua_cobr = lv_user_dummy  OR car.cod_usua_cobr IN
          ( SELECT  pac.cod_usua_cobr FROM cob_param_asign_carte_cobra pac
            WHERE pac.cod_etap_deud = p_cod_etap_deud
            AND pac.cod_pais = p_cod_pais
            AND pac.ind_acti = 1 )  )
      AND car.cod_tipo_exec IS NULL;

      lv_des_log:='Cantidad Total por Asignar  : ' || lv_imp_cart_tota;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      IF lv_imp_cart_tota > 0 THEN

         INSERT INTO cob_tempo_asign_carte_cobra
            SELECT
               acc.cod_usua_cobr,
               NVL(( SELECT COUNT(*)
                             FROM cob_tempo_detal_asign_carte car
                          WHERE car.cod_cart = p_cod_cart
                               AND   car.cod_usua_cobr = acc.cod_usua_cobr),0)  val_cant_cart_asign,
               NVL(( SELECT SUM(car.imp_deud_asig)
                             FROM cob_tempo_detal_asign_carte car
                          WHERE car.cod_cart = p_cod_cart
                               AND   car.cod_usua_cobr = acc.cod_usua_cobr),0) imp_cart_asign,
               ROUND(acc.imp_porc_asig*lv_imp_cart_tota/100,2) imp_esti_cart_asign,
               0 ind_asig
            FROM cob_param_asign_carte_cobra acc
            WHERE acc.cod_pais = p_cod_pais
                 AND acc.cod_etap_deud = p_cod_etap_deud
                 AND acc.ind_acti=1;

         lv_des_log:='   Cobradores Cargados ' || SQL%ROWCOUNT;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         UPDATE cob_tempo_asign_carte_cobra tmc
         SET tmc.ind_asig = 1
         WHERE  tmc.imp_cart_asign >= tmc.imp_esti_cart_asign;

      ELSE
         lv_des_log:='      No Hay Cartera Generica por Asignar';
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_gener_cobra;

   PROCEDURE cob_pr_asign_carte_balan_clien(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_erro                            OUT   VARCHAR2)
   IS

      lv_imp_deud_asig_total                         NUMBER(12,2);
      lv_cod_clie                                              mae_clien.cod_clie%TYPE;
      lv_imp_pend                                            NUMBER(12,2);
      lv_imp_deud_asig                                   NUMBER(12,2);
      lv_dif                                                      NUMBER(12,2);
      lv_cant_cuot_pend_asig                        NUMBER(12):=0;
      lv_cant_cuot_pend_asig_ante              NUMBER(12):=0;
      lv_cant_cobr_asig_cart                        NUMBER(12):=0;
      lv_cont_asign                                         NUMBER(12):=0;
      e_asign_erro                                          EXCEPTION;

      CURSOR c_cob
      IS
         SELECT
                       rownum id,
                       cob.cod_usua_cobr,
                       cob.val_cant_cart_asign,
                       cob.imp_esti_cart_asign,
                       cob.imp_cart_asign,
                       cob.ind_asig
         FROM  cob_tempo_asign_carte_cobra cob
         WHERE cob.ind_asig=0
         ORDER BY  5 , 3 ASC;

   BEGIN

      -- Obteniendo la cantidad de cuotas pendientes de asignar
      SELECT COUNT(*)
      INTO lv_cant_cuot_pend_asig
      FROM cob_tempo_detal_asign_carte c
      WHERE c.cod_etap_deud = p_cod_etap_deud
           AND c.cod_regi = p_cod_regi
           AND DECODE(p_cod_zona,'T',c.cod_zona,c.cod_zona_clie) = p_cod_zona
           AND c.cod_cart = p_cod_cart
           AND c.cod_usua_cobr = lv_user_dummy
           AND c.cod_tipo_exec IS NULL;

      lv_des_log:='   Consultoras Pendientes x Asignar Inicial : ' || lv_cant_cuot_pend_asig;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      LOOP

         lv_cont_asign := lv_cont_asign + 1;

         lv_cant_cuot_pend_asig_ante:= lv_cant_cuot_pend_asig;

         -- Obteniendo la cantidad de cuotas pendientes de asignar
         SELECT COUNT(*)
         INTO lv_cant_cuot_pend_asig
         FROM cob_tempo_detal_asign_carte c
         WHERE c.cod_cart = p_cod_cart
         AND c.cod_usua_cobr = lv_user_dummy
         AND c.cod_etap_deud = p_cod_etap_deud
         AND c.cod_regi = p_cod_regi
         AND DECODE(p_cod_zona,'T',c.cod_zona,c.cod_zona_clie) = p_cod_zona
         AND c.cod_tipo_exec IS NULL;

         lv_des_log:='   Consultoras Pendientes x Asignar  : ' || lv_cant_cuot_pend_asig;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

         IF MOD(lv_cont_asign,25) = 0 THEN
            lv_des_log:='Consultoras Pendientes x Asignar  : ' || lv_cant_cuot_pend_asig;
            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

            IF lv_cant_cuot_pend_asig = lv_cant_cuot_pend_asig_ante  THEN
               lv_des_log:='   Consultoras Pendientes x Asignar  Anterior: ' || lv_cant_cuot_pend_asig_ante;
               FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

               RAISE e_asign_erro;

            END IF;

         END IF;

         IF lv_cant_cuot_pend_asig > 0 THEN

            --Hay cuotas pendientes por asignar
            FOR  v_cob IN c_cob LOOP

               --IF MOD(lv_cont_asign,25) = 0 THEN
                   --lv_des_log:='Cobrador   : ' || v_cob.cod_usua_cobr;
                   --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                  --lv_des_log:='Importe Asignado  : ' || v_cob.imp_cart_asign;
                  --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                 --lv_des_log:='Importe Estimado  : ' || v_cob.imp_esti_cart_asign;
                 --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
                --END IF;
                --*/


               FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

               lv_cant_cobr_asig_cart:= cob_fn_obtie_cobra_asign_carte;

               lv_des_log:='   Cantidad de Cobradores ' || lv_cant_cobr_asig_cart;

               IF lv_cant_cobr_asig_cart > 1 THEN

                  lv_imp_deud_asig_total:=v_cob.imp_cart_asign;

                  -- Busqueda de la cuota de mayor importe
                  BEGIN
                     cob_pr_obtie_clien_asign(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,  lv_cod_clie,lv_imp_pend);

                     --lv_des_log:='Obtiene el Cliente de Mayor Importe  : ' || lv_cod_clie || ' Importe ' || lv_imp_pend;
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                  EXCEPTION
                        WHEN no_data_found THEN
                           lv_des_log:='   !!! No se encontraron Cuotas por Asignar';
                           FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
                           EXIT;
                  END;

                  IF lv_imp_deud_asig_total + lv_imp_pend <= v_cob.imp_esti_cart_asign THEN
                  -- Asignacion Normal
                     --lv_des_log:='Asignacion Normal';
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                     cob_pr_asign_carte_clien_cobra(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,lv_cod_clie,v_cob.cod_usua_cobr,lv_imp_deud_asig);
                     v_cob.imp_cart_asign :=v_cob.imp_cart_asign +lv_imp_deud_asig;

                     UPDATE cob_tempo_asign_carte_cobra cob
                     SET cob.imp_cart_asign = cob.imp_cart_asign + lv_imp_deud_asig
                     WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                  ELSE
                     -- Asignacion por Excepcion
                     --lv_des_log:='Asignacion por Excepcion  : Deuda Total Asignada ' ||
                     --                        lv_imp_deud_asig_total || ' Cartera Estimada  ' || v_cob.imp_esti_cart_asign ;
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                     IF lv_imp_deud_asig_total < v_cob.imp_esti_cart_asign THEN
                        lv_dif := v_cob.imp_esti_cart_asign - lv_imp_deud_asig_total;

                       --lv_des_log:=' Diferencia ' || lv_dif;
                       --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                        IF MOD(v_cob.id, 2) = 0 THEN
                         --Usuario Par - Asignacion por exceso
                         --lv_des_log:=' *** Exceso  : ';
                         --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                           lv_cod_clie:=cob_fn_obtie_clien_asign_exces(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,lv_dif);

                           v_cob.ind_asig := 1;

                           UPDATE cob_tempo_asign_carte_cobra cob
                           SET cob.ind_asig=1
                           WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                        ELSE
                        --Usuario Impar - Asignacion por defecto
                           --lv_des_log:=' *** Defecto  : ';
                           --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                           lv_cod_clie:=cob_fn_obtie_clien_asign_defec(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,lv_dif);
                           --v_tab_usu_cobr(x).ind_asign := 1;
                        END IF;

                         cob_pr_asign_carte_clien_cobra(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,lv_cod_clie,v_cob.cod_usua_cobr,lv_imp_deud_asig);
                         v_cob.imp_cart_asign :=v_cob.imp_cart_asign +lv_imp_deud_asig;

                         UPDATE cob_tempo_asign_carte_cobra cob
                         SET cob.imp_cart_asign = cob.imp_cart_asign + lv_imp_deud_asig
                         WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                     ELSE

                           lv_des_log:=' Asigancion Maloi ';
                           FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                          -- Asigna al mayor
                          cob_pr_asign_carte_clien_cobra(p_cod_etap_deud,p_cod_regi,p_cod_zona,p_cod_cart,lv_cod_clie,v_cob.cod_usua_cobr,lv_imp_deud_asig);
                          v_cob.imp_cart_asign :=v_cob.imp_cart_asign +lv_imp_deud_asig;

                           UPDATE cob_tempo_asign_carte_cobra cob
                           SET cob.imp_cart_asign = cob.imp_cart_asign + lv_imp_deud_asig,
                                  cob.ind_asig = 1
                           WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;


                      END IF;



                  END IF;


               ELSE

                 lv_des_log:='    Asignacion Sin Balanceo , Solo queda un Cobrador : ' || v_cob.cod_usua_cobr;
                 FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                  UPDATE cob_tempo_detal_asign_carte car
                         SET car.cod_usua_cobr = v_cob.cod_usua_cobr
                  WHERE car.cod_pais = p_cod_pais
                       AND car.cod_etap_deud = p_cod_etap_deud
                       AND car.cod_regi = p_cod_regi
                       AND DECODE(p_cod_zona,'T',car.cod_zona,car.cod_zona_clie) = p_cod_zona
                       AND car.cod_cart = p_cod_cart
                       AND car.cod_usua_cobr = lv_user_dummy
                       AND car.cod_tipo_exec IS NULL;

                  UPDATE cob_tempo_asign_carte_cobra cob
                         SET cob.ind_asig=1
                   WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

               END IF;

            END LOOP;

         ELSE
            EXIT;
         END IF;
      END LOOP;

   EXCEPTION
     WHEN e_asign_erro THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error en el algoritmo de asignacion de cartera ' ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_balan_clien;

   PROCEDURE cob_pr_asign_carte_balan_zona(
      p_cod_pais                             IN         seg_pais.cod_pais%TYPE,
      p_cod_cart                             IN        cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_erro                             OUT     VARCHAR2)
   IS

     lv_imp_cart_tota                              NUMBER(12,2);
     lv_imp_deud_asig_total                    NUMBER(12,2);
     lv_cod_zona                                       zon_zona.cod_zona%TYPE;
     lv_imp_pend                                       NUMBER(12,2);
     lv_imp_deud_asig                             NUMBER(12,2);
     lv_dif                                                NUMBER(12,2);
     lv_cont_asign                                   NUMBER(12):=0;
     lv_cant_zona_asig                           NUMBER(12):=0;
     lv_imp_cart_asign_log                      NUMBER(12,2);

      CURSOR c_cob
      IS
         SELECT
                       rownum id,
                       cob.cod_usua_cobr,
                       cob.val_cant_cart_asign,
                       cob.imp_esti_cart_asign,
                       cob.imp_cart_asign,
                       cob.ind_asig
         FROM  cob_tempo_asign_carte_cobra cob
         WHERE cob.ind_asig=0
         ORDER BY  5 , 3 ASC;

   BEGIN

      -- Cantidad de Zonas x Asignar --
      SELECT COUNT(DISTINCT car.cod_zona_clie)
      INTO lv_cant_zona_asig
      FROM cob_tempo_detal_asign_carte car
      WHERE car.cod_cart = p_cod_cart
      AND car.cod_usua_cobr = lv_user_dummy;

      lv_des_log:='Cantidad Zonas por Asignar  : ' || lv_cant_zona_asig;
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      -- Inicio de la asignacion de cartera con balanceo
      LOOP

         lv_cont_asign := lv_cont_asign + 1;

         -- Obteniendo la cantidad de zonas pendientes de asignar
         SELECT COUNT(DISTINCT car.cod_zona_clie)
         INTO lv_cant_zona_asig
         FROM cob_tempo_detal_asign_carte car
         WHERE car.cod_cart = p_cod_cart
         AND car.cod_usua_cobr = lv_user_dummy;

         IF MOD(lv_cant_zona_asig,25) = 0 THEN
            lv_des_log:='Zonas Pendientes x Asignar  : ' || lv_cant_zona_asig;
            FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         END IF;

         IF lv_cant_zona_asig > 0 THEN

            --Hay zonas pendientes por asignar
            FOR  v_cob IN c_cob LOOP

               --lv_des_log:='Cobrador   : ' || v_cob.cod_usua_cobr;
               --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

               --lv_des_log:='Importe Asignado  : ' || v_cob.imp_cart_asign;
               --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

               --lv_des_log:='Importe Estimado  : ' || v_cob.imp_esti_cart_asign;
               --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

               IF v_cob.ind_asig = 0 THEN

                  lv_imp_deud_asig_total:=v_cob.imp_cart_asign;

                  -- Busqueda de la zona de mayor importe
                  BEGIN
                     cob_pr_obtie_zona_asign( p_cod_cart,  lv_cod_zona,lv_imp_pend);

                     --lv_des_log:='Obtiene la Zonas de Mayor Importe  : ' || lv_cod_zona || ' Importe ' || lv_imp_pend;
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                  EXCEPTION
                        WHEN no_data_found THEN
                           EXIT;
                  END;

                  IF lv_imp_deud_asig_total + lv_imp_pend <= v_cob.imp_esti_cart_asign THEN
                     -- Asignacion Normal
                     --lv_des_log:='Asignacion Normal  : ';
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                     cob_pr_asign_carte_zona(p_cod_cart,lv_cod_zona,v_cob.cod_usua_cobr,lv_imp_deud_asig);
                     v_cob.imp_cart_asign :=v_cob.imp_cart_asign +lv_imp_deud_asig;

                     UPDATE cob_tempo_asign_carte_cobra cob
                     SET cob.imp_cart_asign = cob.imp_cart_asign + lv_imp_deud_asig
                     WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                     SELECT SUM(cob.imp_cart_asign)
                     INTO lv_imp_cart_asign_log
                     FROM cob_tempo_asign_carte_cobra cob
                     WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                     --lv_des_log:=' Cartera Asignada ' || lv_imp_cart_asign_log ;
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                  ELSE
                     --lv_des_log:='Asignacion por Excepcion  : ';
                     --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                     -- Asignacion por Excepcion
                     IF lv_imp_deud_asig_total < v_cob.imp_esti_cart_asign THEN
                        lv_dif := v_cob.imp_esti_cart_asign - lv_imp_deud_asig_total;

                        --lv_des_log:=' Diferencia ' || lv_dif;
                        --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                        IF MOD(v_cob.id, 2) = 0 THEN

                          --lv_des_log:=' *** Exceso  : ';
                          --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                         --Usuario Par - Asignacion por exceso
                           lv_cod_zona:=cob_fn_obtie_zona_asign_exces(p_cod_cart,lv_dif);

                           --lv_des_log:='Zona Asignada  : ' || lv_cod_zona ;
                          --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                           v_cob.ind_asig := 1;

                           UPDATE cob_tempo_asign_carte_cobra cob
                           SET cob.ind_asig=1
                           WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                        ELSE
                           --lv_des_log:=' *** Defecto  : ';
                           --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
                        --Usuario Impar - Asignacion por defecto
                           lv_cod_zona:=cob_fn_obtie_zona_asign_defec(p_cod_cart,lv_dif);

                           --lv_des_log:='Zona Asignada  : ' || lv_cod_zona ;
                          --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
                           --v_tab_usu_cobr(x).ind_asign := 1;

                          SELECT SUM(x.imp_deud_asig)
                          INTO lv_imp_cart_asign_log
                          FROM cob_tempo_detal_asign_carte x
                          WHERE x.cod_cart = p_cod_cart
                          AND x.cod_zona_clie = lv_cod_zona
                          AND x.cod_tipo_exec IS NULL;

                           --lv_des_log:=' Monto Zona Asignado ' || lv_imp_cart_asign_log ;
                           --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                        END IF;

                         cob_pr_asign_carte_zona(p_cod_cart,lv_cod_zona,v_cob.cod_usua_cobr,lv_imp_deud_asig);
                         v_cob.imp_cart_asign :=v_cob.imp_cart_asign +lv_imp_deud_asig;

                         UPDATE cob_tempo_asign_carte_cobra cob
                         SET cob.imp_cart_asign = cob.imp_cart_asign + lv_imp_deud_asig
                         WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                           SELECT SUM(cob.imp_cart_asign)
                           INTO lv_imp_cart_asign_log
                           FROM cob_tempo_asign_carte_cobra cob
                           WHERE cob.cod_usua_cobr = v_cob.cod_usua_cobr;

                           --lv_des_log:=' Cartera Asignada Cob ' || lv_imp_cart_asign_log ;
                           --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                           lv_imp_cart_asign_log:=0;

                           SELECT SUM(det.imp_deud_asig)
                           INTO lv_imp_cart_asign_log
                           FROM cob_tempo_detal_asign_carte det
                           WHERE det.cod_cart =  p_cod_cart
                           AND det.cod_usua_cobr = v_cob.cod_usua_cobr;

                           --lv_des_log:=' Cartera Asignada Detal ' || lv_imp_cart_asign_log ;
                           --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                           -- Importe Total x Asignar --
      SELECT SUM(car.imp_deud_asig)
      INTO lv_imp_cart_tota
      FROM cob_tempo_detal_asign_carte car
      WHERE car.cod_cart = p_cod_cart
      AND car.cod_usua_cobr = lv_user_dummy;

      --lv_des_log:='Cantidad Total por Asignar  : ' || lv_imp_cart_tota;
      --FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

                      END IF;
                  END IF;
               END IF;

            END LOOP;

         ELSE
            EXIT;
         END IF;
      END LOOP;

    EXCEPTION
      WHEN OTHERS THEN
         gv_sqlerrm := SUBSTR(SQLERRM,1,250);
         lv_des_log:='   !!! Error ' || gv_sqlerrm ;
         FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);
         p_cod_erro:='procesoCOBAsignacionCarteraForm.error.no.documentado';

   END cob_pr_asign_carte_balan_zona;

 PROCEDURE cob_pr_asign_carte_super(
  p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
  p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
  p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE)
 IS

 CURSOR c_cart
 IS
  (SELECT
    cod_cart,
    cod_clie,
    cod_usua_cobr
   FROM
    (SELECT
      car.cod_cart,
      car.cod_clie,
      sup.val_cant_deud,
      sup.cod_usua_cobr
     FROM
      cob_tempo_detal_asign_carte car,
      cob_param_asign_carte_super sup
     WHERE car.cod_etap_deud = p_cod_etap_deud
       AND car.cod_peri = p_cod_peri
       AND car.cod_regi = p_cod_regi
       AND car.cod_zona = p_cod_zona
       AND car.cod_cart = p_cod_cart
       AND car.cod_etap_deud = sup.cod_etap_deud
       AND car.cod_regi = sup.cod_regi
       AND car.imp_deud_asig >= DECODE(sup.imp_desd,NULL, car.imp_deud_asig, sup.imp_desd)
       AND car.imp_deud_asig <= DECODE(sup.imp_hast,NULL, car.imp_deud_asig, sup.imp_hast)
       AND car.cod_zona_clie = DECODE(sup.cod_zona,'T',car.cod_zona_clie,NULL,car.cod_zona_clie,sup.cod_zona)
       AND car.cod_tipo_exec IS NOT NULL
       ORDER BY car.imp_deud_asig DESC)
      WHERE ROWNUM <= val_cant_deud);

  lv_tab_cod_cart                  t_tab_cod_cart;
  lv_tab_cod_clie                  t_tab_cod_clie;
  lv_tab_cod_usua_cobr             t_tab_cod_usua_cobr;

 BEGIN

  OPEN c_cart;
   LOOP
    FETCH c_cart BULK COLLECT INTO lv_tab_cod_cart, lv_tab_cod_clie,lv_tab_cod_usua_cobr LIMIT 1000;

     IF lv_tab_cod_clie.COUNT >0 THEN

      FORALL i IN 1 .. lv_tab_cod_clie.COUNT

       UPDATE cob_tempo_detal_asign_carte td
       SET td.cod_usua_cobr = lv_tab_cod_usua_cobr(i)
       WHERE td.cod_cart = lv_tab_cod_cart(i)
         AND td.cod_clie = lv_tab_cod_clie(i);

     END IF;
    EXIT WHEN c_cart%NOTFOUND;
   END LOOP;
  CLOSE c_cart;

  UPDATE cob_tempo_detal_movim_carte dm
  SET dm.ind_supe = 1
  WHERE (dm.cod_cart,dm.oid_clie) IN
   (SELECT td.cod_cart,td.oid_clie
    FROM
     cob_tempo_detal_asign_carte td,
     cob_usuar_cobra_pais uc
    WHERE td.cod_usua_cobr = uc.cod_usua_cobr
      AND uc.ind_supe = 1);

 END cob_pr_asign_carte_super;

 PROCEDURE cob_pr_bloqu_consu_carte(
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE)
 IS

  lv_oid_tipo_bloq_cons            mae_tipo_bloqu.oid_tipo_bloq%TYPE;
  lv_desc_etap                     cob_etapa_deuda_pais.val_desc%TYPE;

 BEGIN

  SELECT ed.val_desc ,ed.oid_tipo_bloq_cons
  INTO lv_desc_etap,lv_oid_tipo_bloq_cons
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  IF lv_oid_tipo_bloq_cons IS NOT NULL THEN 

   INSERT INTO mae_clien_bloqu(
     OID_BLOQ,
     CLIE_OID_CLIE,
     TIBQ_OID_TIPO_BLOQ,
     FEC_BLOQ,
     VAL_MOTI_BLOQ,
     VAL_USUA_BLOQ,
     OBS_BLOQ,
     FEC_DESB,
     VAL_USUA_DESB,
     MAAB_OID_VALO_ACCI_BLOQ,
     MAAB_OID_VALO_ACCI_DESB,
     OBS_DESB)
    SELECT
     mae_clbl_seq.NEXTVAL,          -- oid_bloq  number(12)
     oid_clie,                            --clie_oid_clie  number(12)
     lv_oid_tipo_bloq_cons,                       --tibq_oid_tipo_bloq  number(12)
     TRUNC(SYSDATE),                   --fec_bloq  date
     'BLOQUEO MASIVO POR DEUDA EN COBRANZA', --val_moti_bloq  varchar2(50)
     gc_cod_usua,                                   --val_usua_bloq  varchar2(20)
     'BLOQUEO MASIVO POR DEUDA EN ETAPA ' || lv_desc_etap, --obs_bloq  varchar2(100)
     NULL,                                        --fec_desb  date
     NULL,                                        --val_usua_desb  varchar2(20)
     1000,                                        --maab_oid_valo_acci_bloq  number(12)
     NULL,                                         --maab_oid_valo_acci_desb  number(12)
     NULL                                          --obs_desb  varchar2(100)
    FROM
     (SELECT dc.oid_clie
      FROM cob_tempo_detal_asign_carte dc
      WHERE dc.ind_cart_supe = 0
       AND dc.cod_cart = p_cod_cart
       AND NOT EXISTS (
        SELECT NULL
        FROM mae_clien_bloqu b
        WHERE b.clie_oid_clie = dc.oid_clie
        AND b.tibq_oid_tipo_bloq = lv_oid_tipo_bloq_cons
       ));

  END IF;
  
 EXCEPTION

  WHEN no_data_found THEN
   NULL;

 END cob_pr_bloqu_consu_carte;

 PROCEDURE cob_pr_gener_carte_asign(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                  IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_num_lote                       IN   cob_cabec_asign_carte.num_lote_asign%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                  IN   cob_crono_carte.fec_gene_cart%TYPE,
  p_cod_cart                       IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_fec_cier                       IN   DATE,
  p_cod_usua                       IN   seg_usuar.cod_usua%TYPE)
 IS

  reg_cob_cabec_asign_carte      cob_cabec_asign_carte%ROWTYPE;
  lv_cod_soci                                 seg_socie.cod_soci%TYPE;

 BEGIN

  lv_des_log:='*** INICIO Generacion de la Cartera';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  lv_des_log:='       Generando Detalle Movimiento de Cartera';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

  INSERT INTO cob_detal_movim_carte
         SELECT *
         FROM cob_tempo_detal_movim_carte mcc;

      INSERT INTO cob_detal_movim_carte_histo
         SELECT *
         FROM cob_tempo_detal_movim_carte mcc;

      lv_des_log:='       Generando Cartera de Clientes';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

        SELECT COUNT(*)
        INTO lv_cont
         FROM cob_tempo_detal_asign_carte car;

        lv_des_log:='Cartera Cliente ' || lv_cont;
        FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

        SELECT COUNT(*)
        INTO lv_cont
        FROM (
        SELECT car.cod_clie
        FROM cob_tempo_detal_asign_carte car
        HAVING COUNT(*) > 1
        GROUP BY car.cod_clie);

        lv_des_log:='Clientes Duplicados ' || lv_cont;
        FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      INSERT INTO cob_detal_asign_carte_histo
         SELECT *
         FROM cob_tempo_detal_asign_carte car
         WHERE car.cod_cart = p_cod_cart;

       COMMIT;

      INSERT INTO cob_detal_asign_carte
         SELECT *
         FROM cob_tempo_detal_asign_carte car
         WHERE car.cod_cart = p_cod_cart;


      lv_des_log:='       Generando Cabecera de la Cartera';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

      lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('COD_SOCI');

      -- Genera La Cabecera de la Cartera
        reg_cob_cabec_asign_carte.cod_pais := p_cod_pais;
        reg_cob_cabec_asign_carte.cod_soci := lv_cod_soci;
        reg_cob_cabec_asign_carte.cod_etap_deud := p_cod_etap_deud;
        reg_cob_cabec_asign_carte.num_lote_asign := p_num_lote;
        reg_cob_cabec_asign_carte.cod_peri := p_cod_peri;
        reg_cob_cabec_asign_carte.cod_regi := p_cod_regi;
        reg_cob_cabec_asign_carte.cod_zona := p_cod_zona;
        reg_cob_cabec_asign_carte.cod_cart := p_cod_cart;
        reg_cob_cabec_asign_carte.fec_asig := trunc(SYSDATE);
        reg_cob_cabec_asign_carte.ind_envi_cart := 0;
        reg_cob_cabec_asign_carte.ind_envi_liqu := 0;
        reg_cob_cabec_asign_carte.ind_gene_liqu := 0;
        reg_cob_cabec_asign_carte.fec_cier := p_fec_cier;
        reg_cob_cabec_asign_carte.usu_crea := p_cod_usua;
        reg_cob_cabec_asign_carte.fec_crea := SYSDATE;
        reg_cob_cabec_asign_carte.usu_modi := p_cod_usua;
        reg_cob_cabec_asign_carte.fec_modi := SYSDATE;

        INSERT INTO cob_cabec_asign_carte VALUES reg_cob_cabec_asign_carte;

        INSERT INTO cob_cabec_asign_carte_histo VALUES reg_cob_cabec_asign_carte;

        lv_des_log:='       Actualizando el Cronograma de Carteras';
        FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

        UPDATE cob_crono_carte cro
        SET  cro.ind_gene_cart = 1,
                 cro.cod_cart = p_cod_cart
        WHERE cro.cod_pais = p_cod_pais
        AND   cro.cod_peri = p_cod_peri
        AND   cro.cod_etap_deud = p_cod_etap_deud
        AND   cro.cod_regi      = p_cod_regi
        AND   cro.cod_zona = p_cod_zona
        AND cro.fec_gene_cart = p_fec_gene_cart;

      lv_des_log:='*** FIN Generacion de la Cartera';
      FIN_PKG_GENER.FIN_PR_ACTUA_PROCE_LOG(p_cod_pais,gv_cod_modu,gv_cod_proc_asig_carte_indi,lv_id_proc_ejec,lv_des_log);

   END cob_pr_gener_carte_asign;

   PROCEDURE cob_pr_obtie_clien_asign(
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie                             OUT mae_clien.cod_clie%TYPE,
      p_imp_pend                           OUT NUMBER)
   IS

   BEGIN
      SELECT cod_clie, imp_pend
      INTO p_cod_clie, p_imp_pend
      FROM
         (SELECT c.cod_clie,
                       SUM(c.imp_deud_asig) imp_pend
          FROM cob_tempo_detal_asign_carte c
          WHERE c.cod_etap_deud = p_cod_etap_deud
          AND c.cod_regi = p_cod_regi
          AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
          AND c.cod_cart = p_cod_cart
          AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
          AND c.cod_tipo_exec IS NULL
          GROUP BY c.cod_clie
          ORDER BY 2 DESC)
      WHERE ROWNUM = 1;

   END cob_pr_obtie_clien_asign;

   PROCEDURE cob_pr_asign_carte_clien_cobra(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                   IN         cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_clie                                    IN         mae_clien.cod_clie%TYPE,
      p_cod_usu                                     IN        cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig                          OUT     NUMBER)
   IS
   BEGIN

      SELECT SUM(c.imp_deud_asig)
      INTO p_imp_deud_asig
      FROM cob_tempo_detal_asign_carte c
      WHERE c.cod_etap_deud = p_cod_etap_deud
          AND c.cod_regi = p_cod_regi
          AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
          AND c.cod_cart= p_cod_cart
          AND c.cod_clie = p_cod_clie;

      UPDATE cob_tempo_detal_asign_carte c
      SET c.cod_usua_cobr = p_cod_usu
      WHERE c.cod_etap_deud = p_cod_etap_deud
          AND c.cod_regi = p_cod_regi
          AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
          AND c.cod_cart= p_cod_cart
          AND c.cod_clie = p_cod_clie;

   END cob_pr_asign_carte_clien_cobra;

   FUNCTION cob_fn_obtie_clien_asign_defec(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                   IN        cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                                           IN        NUMBER)
   RETURN VARCHAR2
   IS
      v_cod_clie mae_clien.cod_clie%TYPE;
   BEGIN
      BEGIN
         SELECT cod_clie
         INTO v_cod_clie
         FROM
            (SELECT c.cod_clie,
                          SUM(c.imp_deud_asig) imp_pend
             FROM cob_tempo_detal_asign_carte c
             WHERE c.cod_etap_deud = p_cod_etap_deud
                  AND c.cod_regi = p_cod_regi
                  AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
                   AND c.cod_cart = p_cod_cart
                  AND c.cod_usua_cobr = lv_user_dummy -- IS NULL
             HAVING SUM(c.imp_deud_asig) <= pn_dif
             GROUP BY c.cod_clie
             ORDER BY 2 DESC)
         WHERE rownum = 1;
      EXCEPTION
         WHEN no_data_found THEN
            SELECT cod_clie
            INTO v_cod_clie
            FROM
               (SELECT c.cod_clie,
                             SUM(c.imp_deud_asig) imp_pend
                             FROM cob_tempo_detal_asign_carte c
                             WHERE c.cod_etap_deud = p_cod_etap_deud
                                  AND c.cod_regi = p_cod_regi
                                  AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
                                  AND c.cod_cart = p_cod_cart
                                 AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                             HAVING SUM(c.imp_deud_asig) >=pn_dif
                             GROUP BY c.cod_clie
                             ORDER BY 2 ASC)
                WHERE rownum = 1;
      END;

      RETURN v_cod_clie;
   END  cob_fn_obtie_clien_asign_defec;

   FUNCTION cob_fn_obtie_clien_asign_exces(
      p_cod_etap_deud                        IN         cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_regi                                   IN         zon_regio.cod_regi%TYPE,
      p_cod_zona                                  IN         zon_zona.cod_zona%TYPE,
      p_cod_cart                                  IN         cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                                          IN         NUMBER)
    RETURN VARCHAR2
    IS
      v_cod_clie mae_clien.cod_clie%TYPE;
      BEGIN
         BEGIN
            SELECT cod_clie
            INTO v_cod_clie
            FROM
               (SELECT c.cod_clie,
                             SUM(c.imp_deud_asig) imp_pend
                 FROM cob_tempo_detal_asign_carte c
                 WHERE c.cod_etap_deud = p_cod_etap_deud
                      AND c.cod_regi = p_cod_regi
                      AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
                      AND c.cod_cart = p_cod_cart
                      AND c.cod_usua_cobr = lv_user_dummy --IS NULL
                  HAVING SUM(c.imp_deud_asig) >= pn_dif
                  GROUP BY c.cod_clie
                  ORDER BY 2 ASC)
            WHERE rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
               SELECT cod_clie
               INTO v_cod_clie
               FROM
                  (SELECT c.cod_clie,
                                 SUM(c.imp_deud_asig) imp_pend
                   FROM cob_tempo_detal_asign_carte c
                   WHERE c.cod_etap_deud = p_cod_etap_deud
                        AND c.cod_regi = p_cod_regi
                        AND DECODE(p_cod_zona,'T',c.cod_zona, c.cod_zona_clie) = p_cod_zona
                       AND c.cod_cart = p_cod_cart
                       AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                   HAVING SUM(c.imp_deud_asig) < pn_dif
                   GROUP BY c.cod_clie
                   ORDER BY 2 DESC)
                WHERE rownum = 1;
          END;
      RETURN v_cod_clie;
   END cob_fn_obtie_clien_asign_exces;

   PROCEDURE cob_pr_obtie_zona_asign(
      p_cod_cart                      IN cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_zona                       OUT mae_clien.cod_clie%TYPE,
      p_imp_pend                      OUT NUMBER)
   IS

   BEGIN
      SELECT cod_zona_clie, imp_pend
      INTO p_cod_zona, p_imp_pend
      FROM
         (SELECT c.cod_zona_clie,
                       SUM(c.imp_deud_asig) imp_pend
          FROM cob_tempo_detal_asign_carte c
          WHERE c.cod_cart = p_cod_cart
          AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
          GROUP BY c.cod_zona_clie
          ORDER BY 2 DESC)
      WHERE rownum = 1;

   END cob_pr_obtie_zona_asign;

   PROCEDURE cob_pr_asign_carte_zona(
      p_cod_cart                     IN    cob_detal_asign_carte.cod_cart%TYPE,
      p_cod_zona                     IN    zon_zona.cod_zona%TYPE,
      p_cod_usu                       IN    cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
      p_imp_deud_asig            OUT NUMBER)
   IS
   BEGIN

      SELECT SUM(c.imp_deud_asig)
      INTO p_imp_deud_asig
      FROM cob_tempo_detal_asign_carte c
      WHERE c.cod_cart= p_cod_cart
      AND c.cod_zona_clie = p_cod_zona
      AND c.cod_tipo_exec IS NULL;

      UPDATE cob_tempo_detal_asign_carte c
      SET c.cod_usua_cobr = p_cod_usu
      WHERE c.cod_cart= p_cod_cart
      AND c.cod_zona_clie = p_cod_zona
      AND c.cod_tipo_exec IS NULL;

   END cob_pr_asign_carte_zona;

   FUNCTION cob_fn_obtie_zona_asign_defec(
      p_cod_cart                   IN  cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                           IN  NUMBER)
   RETURN VARCHAR2
   IS
      v_cod_zona                   zon_zona.cod_zona%TYPE;
   BEGIN
      BEGIN
         SELECT cod_zona_clie
         INTO v_cod_zona
         FROM
            (SELECT c.cod_zona_clie,
                          SUM(c.imp_deud_asig) imp_pend
             FROM cob_tempo_detal_asign_carte c
             WHERE c.cod_cart = p_cod_cart
                  AND c.cod_usua_cobr = lv_user_dummy -- IS NULL
             HAVING SUM(c.imp_deud_asig) <= pn_dif
             GROUP BY c.cod_zona_clie
             ORDER BY 2 DESC)
         WHERE rownum = 1;
      EXCEPTION
         WHEN no_data_found THEN
            SELECT cod_zona_clie
            INTO v_cod_zona
            FROM
               (SELECT c.cod_zona_clie,
                             SUM(c.imp_deud_asig) imp_pend
                             FROM cob_tempo_detal_asign_carte c
                             WHERE c.cod_cart = p_cod_cart
                                 AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                             HAVING SUM(c.imp_deud_asig) >=pn_dif
                             GROUP BY c.cod_zona_clie
                             ORDER BY 2 ASC)
                WHERE rownum = 1;
      END;

      RETURN v_cod_zona;
   END  cob_fn_obtie_zona_asign_defec;

   FUNCTION cob_fn_obtie_zona_asign_exces(
      p_cod_cart                          IN   cob_detal_asign_carte.cod_cart%TYPE,
      pn_dif                                   IN  NUMBER)
    RETURN VARCHAR2
    IS
      v_cod_zona                           zon_zona.cod_zona%TYPE;
      BEGIN
         BEGIN
            SELECT cod_zona_clie
            INTO v_cod_zona
            FROM
               (SELECT c.cod_zona_clie,
                             SUM(c.imp_deud_asig) imp_pend
                 FROM cob_tempo_detal_asign_carte c
                 WHERE c.cod_cart = p_cod_cart
                      AND c.cod_usua_cobr = lv_user_dummy --IS NULL
                  HAVING SUM(c.imp_deud_asig) >= pn_dif
                  GROUP BY c.cod_zona_clie
                  ORDER BY 2 ASC)
            WHERE rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
               SELECT cod_zona_clie
               INTO v_cod_zona
               FROM
                  (SELECT c.cod_zona_clie,
                                 SUM(c.imp_deud_asig) imp_pend
                   FROM cob_tempo_detal_asign_carte c
                   WHERE c.cod_cart = p_cod_cart
                       AND c.cod_usua_cobr = lv_user_dummy  -- IS NULL
                   HAVING SUM(c.imp_deud_asig) >=pn_dif
                   GROUP BY c.cod_zona_clie
                   ORDER BY 2 ASC)
                WHERE rownum = 1;
          END;
      RETURN v_cod_zona;
   END cob_fn_obtie_zona_asign_exces;

   FUNCTION cob_fn_obtie_cobra_asign_carte
   RETURN NUMBER
   IS
     lv_cant                                NUMBER;
   BEGIN
         SELECT COUNT(*)
         INTO lv_cant
         FROM  cob_tempo_asign_carte_cobra cob
         WHERE cob.ind_asig=0;

         RETURN lv_cant;

   END cob_fn_obtie_cobra_asign_carte;

   PROCEDURE cob_pr_asign_carte_email_ejecu
   IS
      lv_mail_conn                           utl_smtp.connection;
      lv_linea                                   VARCHAR2(400);

      CURSOR c_cobr_asig
      IS
         SELECT
            usu.cod_usua_cobr,
            usu.val_nomb_usua_cobr,
            usu.val_mail
         FROM
            cob_tempo_carte_asign_proce tem,
            cob_detal_asign_carte car,
            cob_usuar_cobra_pais usu
        WHERE tem.cod_cart = car.cod_cart
             AND car.cod_usua_cobr =  usu.cod_usua_cobr
             AND car.cod_tipo_exec IS NULL
        GROUP BY
            usu.cod_usua_cobr,
            usu.val_nomb_usua_cobr,
            usu.val_mail;

      CURSOR c_cart_asig(
        p_cod_usua_cobr   cob_etapa_deuda_pais.cod_etap_deud%TYPE)
      IS
         SELECT
            edp.val_desc etapa,
            car.cod_peri ,
            car.cod_regi ,
            car.cod_zona ,
            usu.cod_usua_cobr,
            usu.val_nomb_usua_cobr,
            usu.val_mail,
            SUM(car.imp_deud_asig) imp_asig_carte,
            COUNT(*) val_cant_carte
         FROM
            cob_tempo_carte_asign_proce tem,
            cob_detal_asign_carte car,
            cob_usuar_cobra_pais usu,
            cob_etapa_deuda_pais edp
        WHERE tem.cod_cart = car.cod_cart
             AND car.cod_usua_cobr =  usu.cod_usua_cobr
             AND car.cod_etap_deud = edp.cod_etap_deud
             AND car.cod_usua_cobr = p_cod_usua_cobr
        GROUP BY
           edp.val_desc,
            car.cod_peri ,
            car.cod_regi ,
            car.cod_zona ,
            usu.cod_usua_cobr,
            usu.val_nomb_usua_cobr,
            usu.val_mail
         ORDER BY 1,2,3,4 ASC;

   BEGIN

      FOR v_cobr_asig IN c_cobr_asig LOOP

         lv_mail_conn := ssicc_comun.log_email.begin_mail('jflorencio@belcorp.biz',
         v_cobr_asig.val_mail,' Asignacion de Carteras - Ejecutivo','text/html' );

         ssicc_comun.log_email.write_text(lv_mail_conn,'<html><body>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">');
         ssicc_comun.log_email.write_text(lv_mail_conn, '<tr>');
         ssicc_comun.log_email.write_text(lv_mail_conn, 'Hola ' || v_cobr_asig.val_nomb_usua_cobr  );
         ssicc_comun.log_email.write_text(lv_mail_conn, '<tr>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>');
         ssicc_comun.log_email.write_text(lv_mail_conn, 'Se generaron las siguientes asignaciones de cartera : ' );
         ssicc_comun.log_email.write_text(lv_mail_conn,'</b></font></td></tr></table>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<tr width="80%"><td width="40%"><font face="Arial" size="2"><b>ETAPA </b></font></td><td width="40%"><font face="Arial" size="2"><b>PERIODO </b></font></td><td width="40%"><font face="Arial" size="2"><b>REGION </b></font></td><td width="40%"><font face="Arial" size="2"><b>ZONA </b></font></td><td width="20%"><font face="Arial" size="2"><b>CANTIDAD </b></font></td><td width="40%"><font face="Arial" size="2"><b>IMPORTE </b></font></td></tr>');

         FOR v_cart_asig IN c_cart_asig(v_cobr_asig.cod_usua_cobr) LOOP

             ssicc_comun.log_email.write_text(lv_mail_conn,'<tr>');

             IF v_cart_asig.etapa IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.etapa)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.cod_peri IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_peri)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.cod_regi IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_regi)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.cod_zona IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_zona)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.val_cant_carte IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.val_cant_carte)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

            IF v_cart_asig.imp_asig_carte IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.imp_asig_carte)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             ssicc_comun.log_email.write_text(lv_mail_conn,'</tr>');

          END LOOP;

       ssicc_comun.log_email.write_text(lv_mail_conn,'</table>');
       ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
      ssicc_comun.log_email.write_text(lv_mail_conn,'<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>');
       ssicc_comun.log_email.end_mail( lv_mail_conn);

       END LOOP;

   EXCEPTION
       WHEN OTHERS THEN
          NULL;
   END cob_pr_asign_carte_email_ejecu;

   PROCEDURE cob_pr_asign_carte_email_super
   IS
      lv_mail_conn                           utl_smtp.connection;
      lv_linea                                   VARCHAR2(400);

      CURSOR c_etap_asig
      IS
         SELECT
           edp.cod_etap_deud,
           edp.val_desc,
           usu.val_mail
        FROM
           cob_tempo_carte_asign_proce tem,
           cob_detal_asign_carte car,
           cob_etapa_deuda_pais edp,
           cob_usuar_cobra_pais usu
        WHERE tem.cod_cart = car.cod_cart
             AND car.cod_etap_deud = edp.cod_etap_deud
             AND usu.cod_usua_cobr = edp.cod_usua_supe
        GROUP BY edp.cod_etap_deud, edp.val_desc,usu.val_mail;

      CURSOR c_cart_asig(
        p_cod_etap_deud   cob_etapa_deuda_pais.cod_etap_deud%TYPE)
      IS
         SELECT
            car.cod_peri ,
            car.cod_regi ,
            car.cod_zona ,
            usu.val_nomb_usua_cobr,
            SUM(car.imp_deud_asig) imp_asig_carte,
            COUNT(*) val_cant_carte
         FROM
            cob_tempo_carte_asign_proce tem,
            cob_detal_asign_carte car,
            cob_usuar_cobra_pais usu
        WHERE tem.cod_cart = car.cod_cart
             AND car.cod_usua_cobr =  usu.cod_usua_cobr
             AND car.cod_etap_deud = p_cod_etap_deud
        GROUP BY car.cod_peri , car.cod_regi , car.cod_zona , usu.val_nomb_usua_cobr
        ORDER BY 1,2,3,4 ASC;

   BEGIN

      FOR v_etap_asig IN c_etap_asig LOOP

         lv_mail_conn := ssicc_comun.log_email.begin_mail('jflorencio@belcorp.biz',
         v_etap_asig.val_mail,' Asignacion de Cartera Etapa  ' || v_etap_asig.val_desc,'text/html' );

         ssicc_comun.log_email.write_text(lv_mail_conn,'<html><body>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>');
         ssicc_comun.log_email.write_text(lv_mail_conn, 'Se generaron las siguientes asignaciones de cartera : ' );
         ssicc_comun.log_email.write_text(lv_mail_conn,'</b></font></td></tr></table>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">');
         ssicc_comun.log_email.write_text(lv_mail_conn,'<tr width="80%"><td width="40%"><font face="Arial" size="2"><b>PERIODO </b></font></td><td width="40%"><font face="Arial" size="2"><b>REGION </b></font></td><td width="40%"><font face="Arial" size="2"><b>ZONA </b></font></td><td width="20%"><font face="Arial" size="2"><b>CANTIDAD </b></font></td><td width="40%"><font face="Arial" size="2"><b>IMPORTE </b></font></td><td width="40%"><font face="Arial" size="2"><b>COBRADOR </b></font></td></tr>');
          FOR v_cart_asig IN c_cart_asig(v_etap_asig.cod_etap_deud) LOOP

             ssicc_comun.log_email.write_text(lv_mail_conn,'<tr>');

             IF v_cart_asig.cod_peri IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_peri)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.cod_regi IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_regi)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.cod_zona IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.cod_zona)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

             IF v_cart_asig.val_cant_carte IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.val_cant_carte)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;

            IF v_cart_asig.imp_asig_carte IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.imp_asig_carte)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea || utl_tcp.CRLF);
             END IF;


             IF v_cart_asig.val_nomb_usua_cobr IS NOT NULL THEN
                lv_linea := '<td ><font face="Arial" size="2">'||TRIM(v_cart_asig.val_nomb_usua_cobr)||'</font></td>';
                ssicc_comun.log_email.write_text( lv_mail_conn, lv_linea);
             END IF;

             ssicc_comun.log_email.write_text(lv_mail_conn,'</tr>');

          END LOOP;

       ssicc_comun.log_email.write_text(lv_mail_conn,'</table>');
       ssicc_comun.log_email.write_text(lv_mail_conn,'<br>');
      ssicc_comun.log_email.write_text(lv_mail_conn,'<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>');
       ssicc_comun.log_email.end_mail( lv_mail_conn);

       END LOOP;

   EXCEPTION
       WHEN OTHERS THEN
          NULL;
   END cob_pr_asign_carte_email_super;

 PROCEDURE COB_PR_BLOQU_AUTOM_DIAS_ATRAS
 IS
 
  lv_num_lote                      NUMBER(15);
  lv_sald_mini                     NUMBER(15,2);
  lv_dias_atra                     NUMBER(5);
  lv_codi_bloq                     mae_tipo_bloqu.cod_tipo_bloq%TYPE;
  lv_oid_tipo_bloq                 NUMBER(12);
  lv_oid_valo_acci_bloq            NUMBER(12);
  
 BEGIN 
 
  lv_num_lote := COB_PKG_GENER.COB_FN_OBTIE_NUMER_LOTE;
  lv_sald_mini := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('BloqAutoSaldMini'));
  lv_dias_atra := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('BloqAutoDiasAtra'));
  lv_codi_bloq := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('BloqAutoCodiBloq');
      
  --Obteniendo el Tipo De Bloqueo Financiero
  SELECT mtb.oid_tipo_bloq
  INTO lv_oid_tipo_bloq
  FROM mae_tipo_bloqu mtb
  WHERE mtb.cod_tipo_bloq = lv_codi_bloq;

  --Obteniendo el Valor Accion de Bloqueo
  SELECT vab.oid_valo_acci_bloq
  INTO lv_oid_valo_acci_bloq
  FROM mae_valor_accio_bloqu vab
  WHERE vab.cod_valo_bloq = 'A';
  
  -- Identificando las Deudas con Dias de Atras
  INSERT INTO cob_clien_bloqu_dias_atras
   SELECT 
    lv_num_lote,
    mcc.clie_oid_clie,
    mcc.oid_movi_cc,
    TRUNC(SYSDATE) - mcc.fec_docu,
    mcc.imp_pend,
    TRUNC(SYSDATE),
    NULL,
    1
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.imp_pend > lv_sald_mini
     AND TRUNC(SYSDATE) - mcc.fec_docu >= lv_dias_atra
     AND NOT EXISTS (
            SELECT NULL
            FROM mae_clien_bloqu mcb
            WHERE mcb.clie_oid_clie = mcc.clie_oid_clie
              AND mcb.fec_desb IS NULL
              AND mcb.tibq_oid_tipo_bloq= lv_oid_tipo_bloq)
     AND NOT EXISTS (
      SELECT NULL
      FROM cob_clien_bloqu_dias_atras cb
      WHERE cb.oid_movi_cc = mcc.oid_movi_cc);         
              
  INSERT INTO mae_clien_bloqu
   (oid_bloq,
    clie_oid_clie,
    tibq_oid_tipo_bloq,
    fec_bloq,
    val_moti_bloq,
    val_usua_bloq,
    obs_bloq,
    fec_desb,
    val_usua_desb,
    maab_oid_valo_acci_bloq,
    maab_oid_valo_acci_desb,
    obs_desb)
    SELECT
     mae_clbl_seq.NEXTVAL,          -- oid_bloq  number(12)
     oid_clie,                            --clie_oid_clie  number(12)
     lv_oid_tipo_bloq,                       --tibq_oid_tipo_bloq  number(12)
     TRUNC(SYSDATE),                   --fec_bloq  date
     'BLOQUEO AUTOMATICO POR DIAS DE ATRASO', --val_moti_bloq  varchar2(50)
     USER,                                   --val_usua_bloq  varchar2(20)
     'BLOQUEO AUTOMATICO POR DIAS DE ATRASO', --obs_bloq  varchar2(100)
     NULL,                                        --fec_desb  date
     NULL,                                        --val_usua_desb  varchar2(20)
     lv_oid_valo_acci_bloq,               --maab_oid_valo_acci_bloq  number(12)
     NULL,                                         --maab_oid_valo_acci_desb  number(12)
     NULL                                             --obs_desb  varchar2(100)
    FROM
     (SELECT cb.oid_clie
      FROM cob_clien_bloqu_dias_atras cb
      WHERE cb.num_lote = lv_num_lote
      GROUP BY oid_clie);
            
 END COB_PR_BLOQU_AUTOM_DIAS_ATRAS; 
 
 PROCEDURE COB_PR_BLOQU_DESBL_DIAS_ATRAS
 IS
 
  lv_dias_pago                     NUMBER(5);
  lv_codi_bloq                     mae_tipo_bloqu.cod_tipo_bloq%TYPE;
  lv_oid_tipo_bloq                 NUMBER(12);
  lv_oid_valo_acci_bloq            NUMBER(12);
  
 BEGIN 
 
  lv_dias_pago := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DesbAutoDiasPago'));
  lv_codi_bloq := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('BloqAutoCodiBloq');
    
  --Obteniendo el Tipo De Bloqueo Financiero
  SELECT mtb.oid_tipo_bloq
  INTO lv_oid_tipo_bloq
  FROM mae_tipo_bloqu mtb
  WHERE mtb.cod_tipo_bloq = lv_codi_bloq;

  --Obteniendo el Valor Accion de Bloqueo
  SELECT vab.oid_valo_acci_bloq
  INTO lv_oid_valo_acci_bloq
  FROM mae_valor_accio_bloqu vab
  WHERE vab.cod_valo_bloq = 'A';
  
  UPDATE cob_clien_bloqu_dias_atras cb
  SET 
   cb.ind_bloq = 0,
   cb.fec_desb = trunc(SYSDATE)
  WHERE EXISTS (
      SELECT NULL 
       FROM ccc_movim_cuent_corri mcc,
            cob_clien_bloqu_dias_atras ch
       WHERE mcc.clie_oid_clie = ch.oid_clie
         AND mcc.oid_movi_cc = cb.oid_movi_cc
         AND mcc.clie_oid_clie = cb.oid_clie
         AND mcc.imp_pend = 0
         AND mcc.fec_ulti_movi - mcc.fec_docu <= lv_dias_pago)
     AND NOT EXISTS (
        SELECT NULL
        FROM mae_clien_datos_adici mcdi
        WHERE mcdi.esta_oid_esta_clie = 7
          AND mcdi.clie_oid_clie = cb.oid_clie);
                
  UPDATE mae_clien_bloqu mcb
  SET 
   mcb.fec_desb = trunc(SYSDATE),
   mcb.maab_oid_valo_acci_desb = lv_oid_valo_acci_bloq,
   mcb.val_usua_desb = USER,
   mcb.obs_desb = 'DESBLOQUEO AUTOMATICO POR SALDO CERO' 
  WHERE mcb.tibq_oid_tipo_bloq = lv_oid_tipo_bloq
    AND mcb.fec_desb IS NULL  
    AND mcb.clie_oid_clie IN (
          SELECT cb.oid_clie
          FROM cob_clien_bloqu_dias_atras cb
          HAVING SUM(cb.ind_bloq) = 0       
          GROUP BY cb.oid_clie);
            
 END COB_PR_BLOQU_DESBL_DIAS_ATRAS; 
 
 PROCEDURE COB_PR_ELIMI_CARTE_ASIGN(
  p_cod_pais                     IN   seg_pais.cod_pais%TYPE,
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_cod_cart                     IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE)
 IS

 BEGIN

  INSERT INTO  cob_detal_movim_carte_elimi
   SELECT *
   FROM cob_detal_movim_carte dmc
   WHERE dmc.cod_pais = p_cod_pais
     AND dmc.cod_etap_deud = p_cod_etap_deud
     AND dmc.cod_peri = p_cod_peri
     AND dmc.cod_regi = p_cod_regi
     AND dmc.cod_zona = p_cod_zona
     AND dmc.cod_cart = p_cod_cart;

  DELETE FROM cob_detal_movim_carte dmc
      WHERE dmc.cod_pais = p_cod_pais
           AND dmc.cod_etap_deud = p_cod_etap_deud
           AND dmc.cod_peri = p_cod_peri
           AND dmc.cod_regi = p_cod_regi
           AND dmc.cod_zona = p_cod_zona
           AND dmc.cod_cart = p_cod_cart;

      INSERT INTO cob_detal_asign_carte_elimi
         SELECT *
         FROM cob_detal_asign_carte dac
      WHERE dac.cod_pais = p_cod_pais
           AND dac.cod_etap_deud = p_cod_etap_deud
           AND dac.cod_peri = p_cod_peri
           AND dac.cod_regi = p_cod_regi
           AND dac.cod_zona = p_cod_zona
           AND dac.cod_cart = p_cod_cart;

  DELETE FROM cob_detal_asign_carte dac
  WHERE dac.cod_pais = p_cod_pais
    AND dac.cod_etap_deud = p_cod_etap_deud
    AND dac.cod_peri = p_cod_peri
    AND dac.cod_regi = p_cod_regi
    AND dac.cod_zona = p_cod_zona
    AND dac.cod_cart = p_cod_cart;

  INSERT INTO cob_cabec_asign_carte_elimi
   SELECT *
   FROM cob_cabec_asign_carte cas
   WHERE cas.cod_pais = p_cod_pais
     AND cas.cod_etap_deud = p_cod_etap_deud
     AND cas.cod_peri = p_cod_peri
     AND cas.cod_regi = p_cod_regi
     AND cas.cod_zona = p_cod_zona
     AND cas.cod_cart = p_cod_cart;

  UPDATE cob_cabec_asign_carte_elimi cel
  SET
   cel.usu_crea = p_cod_usua,
   cel.fec_crea = SYSDATE,
   cel.usu_modi = p_cod_usua,
   cel.fec_modi = SYSDATE
  WHERE cel.cod_pais = p_cod_pais
    AND cel.cod_etap_deud = p_cod_etap_deud
    AND cel.cod_peri = p_cod_peri
    AND cel.cod_regi = p_cod_regi
    AND cel.cod_zona = p_cod_zona
    AND cel.cod_cart = p_cod_cart;

  DELETE FROM cob_cabec_asign_carte cas
  WHERE cas.cod_pais = p_cod_pais
    AND cas.cod_etap_deud = p_cod_etap_deud
    AND cas.cod_peri = p_cod_peri
    AND cas.cod_regi = p_cod_regi
    AND cas.cod_zona = p_cod_zona
    AND cas.cod_cart = p_cod_cart;

 END COB_PR_ELIMI_CARTE_ASIGN;

 PROCEDURE COB_PR_ELIMI_CARTE_ASIGN(
  p_cod_cart                     IN   cob_cabec_asign_carte.cod_cart%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

 BEGIN

  INSERT INTO  cob_detal_movim_carte_elimi
   SELECT *
   FROM cob_detal_movim_carte dmc
   WHERE dmc.cod_cart = p_cod_cart;

  DELETE FROM cob_detal_movim_carte dmc
  WHERE dmc.cod_cart = p_cod_cart;

  INSERT INTO cob_detal_asign_carte_elimi
   SELECT *
   FROM cob_detal_asign_carte dac
   WHERE dac.cod_cart = p_cod_cart;

  DELETE FROM cob_detal_asign_carte dac
  WHERE dac.cod_cart = p_cod_cart;

  INSERT INTO cob_cabec_asign_carte_elimi
   SELECT *
   FROM cob_cabec_asign_carte cas
   WHERE cas.cod_cart = p_cod_cart;

  UPDATE cob_cabec_asign_carte_elimi cel
  SET
   cel.usu_crea = p_cod_usua,
   cel.fec_crea = SYSDATE,
   cel.usu_modi = p_cod_usua,
   cel.fec_modi = SYSDATE
  WHERE cel.cod_cart = p_cod_cart;

  DELETE FROM cob_cabec_asign_carte cas
  WHERE cas.cod_cart = p_cod_cart;

 END COB_PR_ELIMI_CARTE_ASIGN;

 PROCEDURE COB_PR_DEPUR_CARTE_AUTOM
 IS


  lv_dias_depu_carte                NUMBER(5);

  CURSOR c_cart_depu
  IS
   SELECT c.cod_cart
   FROM cob_cabec_asign_carte c
   WHERE TRUNC(SYSDATE) - c.fec_cier >= lv_dias_depu_carte;

 BEGIN

  lv_dias_depu_carte := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('DiasDepuracionCartera'),'99999');

  IF lv_dias_depu_carte IS NOT NULL THEN

   FOR v_cart_depu IN c_cart_depu LOOP

    INSERT INTO cob_cabec_asign_carte_depur
     SELECT * FROM cob_cabec_asign_carte c
     WHERE c.cod_cart = v_cart_depu.cod_cart;

    INSERT INTO cob_detal_asign_carte_depur
     SELECT * FROM cob_detal_asign_carte d
     WHERE d.cod_cart = v_cart_depu.cod_cart;

    INSERT INTO cob_detal_movim_carte_depur
     SELECT * FROM cob_detal_movim_carte dm
     WHERE dm.cod_cart = v_cart_depu.cod_cart;

    INSERT INTO cob_carte_movim_cuent_depur
     SELECT * FROM cob_carte_movim_cuent_clien cm
     WHERE cm.cod_cart = v_cart_depu.cod_cart;

    DELETE FROM cob_cabec_asign_carte c
    WHERE c.cod_cart = v_cart_depu.cod_cart;

    DELETE FROM cob_detal_asign_carte d
    WHERE d.cod_cart = v_cart_depu.cod_cart;

    DELETE FROM cob_detal_movim_carte dm
    WHERE dm.cod_cart = v_cart_depu.cod_cart;

    DELETE FROM cob_carte_movim_cuent_clien cm
    WHERE cm.cod_cart = v_cart_depu.cod_cart;

    UPDATE cob_cabec_asign_carte c
    SET c.fec_modi = SYSDATE,
        c.usu_modi = USER
    WHERE c.cod_cart = v_cart_depu.cod_cart;

    COMMIT;

   END LOOP;

  END IF;

 END COB_PR_DEPUR_CARTE_AUTOM;

 PROCEDURE COB_PR_DEPUR_CARTE_ASIGN(
      p_cod_pais                             IN      seg_pais.cod_pais%TYPE,
      p_cod_etap_deud                  IN      cob_etapa_deuda_pais.cod_etap_deud%TYPE,
      p_cod_peri                             IN      seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                             IN      zon_regio.cod_regi%TYPE,
      p_cod_zona                            IN      zon_zona.cod_zona%TYPE,
      p_cod_cart                            IN      cob_cabec_asign_carte.cod_cart%TYPE,
      p_cod_usua                            IN      seg_usuar.use_usua%TYPE)
 IS

   BEGIN

     INSERT INTO  cob_detal_movim_carte_depur
      SELECT *
      FROM cob_detal_movim_carte dmc
      WHERE dmc.cod_pais = p_cod_pais
           AND dmc.cod_etap_deud = p_cod_etap_deud
           AND dmc.cod_peri = p_cod_peri
           AND dmc.cod_regi = p_cod_regi
           AND dmc.cod_zona = p_cod_zona
           AND dmc.cod_cart = p_cod_cart;

      DELETE FROM cob_detal_movim_carte dmc
      WHERE dmc.cod_pais = p_cod_pais
           AND dmc.cod_etap_deud = p_cod_etap_deud
           AND dmc.cod_peri = p_cod_peri
           AND dmc.cod_regi = p_cod_regi
           AND dmc.cod_zona = p_cod_zona
           AND dmc.cod_cart = p_cod_cart;

      INSERT INTO cob_detal_asign_carte_depur
         SELECT *
         FROM cob_detal_asign_carte dac
      WHERE dac.cod_pais = p_cod_pais
           AND dac.cod_etap_deud = p_cod_etap_deud
           AND dac.cod_peri = p_cod_peri
           AND dac.cod_regi = p_cod_regi
           AND dac.cod_zona = p_cod_zona
           AND dac.cod_cart = p_cod_cart;

      DELETE FROM cob_detal_asign_carte dac
      WHERE dac.cod_pais = p_cod_pais
           AND dac.cod_etap_deud = p_cod_etap_deud
           AND dac.cod_peri = p_cod_peri
           AND dac.cod_regi = p_cod_regi
           AND dac.cod_zona = p_cod_zona
           AND dac.cod_cart = p_cod_cart;

      INSERT INTO cob_cabec_asign_carte_depur
         SELECT *
         FROM cob_cabec_asign_carte cas
         WHERE cas.cod_pais = p_cod_pais
           AND cas.cod_etap_deud = p_cod_etap_deud
           AND cas.cod_peri = p_cod_peri
           AND cas.cod_regi = p_cod_regi
           AND cas.cod_zona = p_cod_zona
           AND cas.cod_cart = p_cod_cart;

      UPDATE cob_cabec_asign_carte_depur cde
      SET cde.usu_crea = p_cod_usua,
              cde.fec_crea = SYSDATE,
              cde.usu_modi = p_cod_usua,
              cde.fec_modi = SYSDATE
      WHERE cde.cod_pais = p_cod_pais
           AND cde.cod_etap_deud = p_cod_etap_deud
           AND cde.cod_peri = p_cod_peri
           AND cde.cod_regi = p_cod_regi
           AND cde.cod_zona = p_cod_zona
           AND cde.cod_cart = p_cod_cart;


      DELETE FROM cob_cabec_asign_carte cas
      WHERE cas.cod_pais = p_cod_pais
           AND cas.cod_etap_deud = p_cod_etap_deud
           AND cas.cod_peri = p_cod_peri
           AND cas.cod_regi = p_cod_regi
           AND cas.cod_zona = p_cod_zona
           AND cas.cod_cart = p_cod_cart;

   END COB_PR_DEPUR_CARTE_ASIGN;

 PROCEDURE COB_PR_GENER_REPOR_GZONA_CSV(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE)
 IS

  w_filas         NUMBER := 5000;
  v_handle        utl_file.file_type;
  lslinea         VARCHAR2(400);

  CURSOR c_gere_zona
  IS
  WITH TEMP1
  AS
  ( SELECT
     est.oid_peri,
     est.cod_peri,
     est.des_regi,
     est.des_zona,
     est.cod_secc,
     mc.oid_clie,
     mc.cod_clie,
     TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
     est.imp_fact_neto,
     est.cob_dias_31,
     est.imp_sald_pend,
     est.fec_cier_vent,
     mcc.fec_docu,
     mcc.fec_ulti_movi
    FROM
     cob_repor_estad_recup_cobra est,
     ccc_movim_cuent_corri mcc,
     mae_clien mc
    WHERE est.oid_clie=mc.oid_clie
      AND est.oid_movi_cc = mcc.oid_movi_cc
      AND est.cod_peri = p_cod_peri
      AND est.imp_pend > 0
      AND est.cod_regi = p_cod_regi
      AND est.cod_zona = p_cod_zona)
    SELECT
     cod_secc,
     nom_clie,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') val_tele_fijo,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') val_tele_trab,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele_movi,
     FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
     SUM(imp_fact_neto) imp_fact_neto,
     SUM(cob_dias_31) cob_dias_31,
     SUM(imp_sald_pend)imp_sald_pend,
     ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2) por_recu,
     MAX(fec_docu) fec_carg,
     TO_CHAR(SYSDATE,'DD/MM/YYYY') fec_ulti_actu,
     TRUNC(SYSDATE) - MAX(fec_docu) num_dias_gest,
     MAX(TRUNC(fec_ulti_movi))  ulti_fec_pago,
     'Si' fact_camp_actu,
     'No' cons_excl
    FROM TEMP1
    GROUP BY
     oid_peri,
     cod_peri,
     des_regi,
     des_zona,
     cod_secc,
     oid_clie,
     cod_clie,
     nom_clie;

  TYPE tab_gere_zona IS TABLE OF c_gere_zona%ROWTYPE;
  reg_tab_gere_zona tab_gere_zona;

  lbCabecera BOOLEAN;

 BEGIN

  lbCabecera := true;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

   v_handle := utl_file.fopen('XLS_REPORTS', 'test.csv', 'W');


  OPEN c_gere_zona;
  LOOP
   FETCH c_gere_zona BULK COLLECT INTO reg_tab_gere_zona LIMIT w_filas;
   IF reg_tab_gere_zona.count >= 0 THEN
    FOR x IN reg_tab_gere_zona.first .. reg_tab_gere_zona.last
     LOOP
      IF lbCabecera THEN
       lslinea :=   '"Seccion",' ||
                    '"Nombre Consultora",' ||
                    '"Cedula",' ||
                    '"Direccion",' ||
                    '"Telefono Fijo",' ||
                    '"Telefono Trabajo",' ||
                    '"Telefono Movil",' ||
                    '"Estatus",' ||
                    '"Venta por Cobrar"",' ||
                    '"Recuperacion a 31 dias",' ||
                    '"Saldo",' ||
                    '"% Recuperacion",' ||
                    '"Fecha Cargo",' ||
                    '"Fecha Actualizacion Saldo",' ||
                    '"Dias Gestion",' ||
                    '"Ultima Fecha de Pago",' ||
                    '"Facturo Campa?a Actual?",' ||
                    '"Excluida ?"';

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
          END IF;

              lslinea :=  '=T("'|| reg_tab_gere_zona(x).cod_secc || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).nom_clie || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).num_docu_iden || '")' || ',' ||
                                 '"'|| replace(reg_tab_gere_zona(x).val_dire,'-',' ') || '"' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).val_tele_fijo || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).val_tele_trab || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).val_tele_movi || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).val_esta || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).imp_fact_neto || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).cob_dias_31 || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).imp_sald_pend || '")' || ',' ||
                                 '=T("'|| reg_tab_gere_zona(x).por_recu || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).fec_carg || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).fec_ulti_actu || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).num_dias_gest || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).ulti_fec_pago || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).fact_camp_actu || '")' || ',' ||
                                '=T("'|| reg_tab_gere_zona(x).cons_excl || '")';



                    utl_file.put_line(v_handle,lslinea);
                END LOOP;
              END IF;
          EXIT WHEN c_gere_zona%NOTFOUND;
        END LOOP;
        CLOSE c_gere_zona;

    utl_file.fclose(v_handle);

 END COB_PR_GENER_REPOR_GZONA_CSV;

 PROCEDURE COB_PR_GENER_REPOR_GZONA_HTML(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB)
 IS

  lv_handle                         utl_file.file_type;
  filehandler                      utl_file.file_type;
  lslinea                          VARCHAR2(32767);
  lsdeta                           VARCHAR2(32767);
  lv_clob_exce                     CLOB;
  lv_file_name                     VARCHAR2(250);

 CURSOR c_gere_zona
 IS
  WITH TEMP1
  AS
  ( SELECT
     est.oid_peri,
     est.cod_peri,
     est.des_regi,
     est.des_zona,
     est.cod_secc,
     mc.oid_clie,
     mc.cod_clie,
     TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
     est.imp_fact_neto,
     est.cob_dias_31,
     est.imp_sald_pend,
     est.fec_cier_vent,
     mcc.fec_docu,
     mcc.fec_ulti_movi
    FROM
     cob_repor_estad_recup_cobra est,
     ccc_movim_cuent_corri mcc,
     mae_clien mc
    WHERE est.oid_clie=mc.oid_clie
      AND est.oid_movi_cc = mcc.oid_movi_cc
      AND est.cod_peri = p_cod_peri
      AND est.imp_pend > 0
      AND est.cod_regi = p_cod_regi
      AND est.cod_zona = p_cod_zona)
    SELECT
     cod_secc,
     nom_clie,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') val_tele_fijo,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') val_tele_trab,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele_movi,
     FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
     SUM(imp_fact_neto) imp_fact_neto,
     SUM(cob_dias_31) cob_dias_31,
     SUM(imp_sald_pend)imp_sald_pend,
     ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2) por_recu,
     MAX(fec_docu) fec_carg,
     TO_CHAR(SYSDATE,'DD/MM/YYYY') fec_ulti_actu,
     TRUNC(SYSDATE) - MAX(fec_docu) num_dias_gest,
     MAX(TRUNC(fec_ulti_movi))  ulti_fec_pago,
     'Si' fact_camp_actu,
     'No' cons_excl
    FROM TEMP1
    GROUP BY
     oid_peri,
     cod_peri,
     des_regi,
     des_zona,
     cod_secc,
     oid_clie,
     cod_clie,
     nom_clie;

  lbCabecera BOOLEAN;

 BEGIN

  lbCabecera := true;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

  lv_file_name := 'test.html';
  lv_handle := utl_file.fopen('XLS_REPORTS', lv_file_name, 'W');

  lslinea := '<html><body>';
  lslinea := lslinea || '<table style="text-align: left; width: 65%" border="1" cellpadding="0" cellspacing="0">';
  lslinea := lslinea || '<tr width="100%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Seccion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Nombre Consultora',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cedula',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Direccion',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefono Fijo',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefono Trabajo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefono Movil',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Estatus',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Recuperacion a 31 dias',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% Recuperacion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Cargo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Actualizacion Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Dias Gestion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Ultima Fecha de Pago',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Facturo Campa?a Actual?',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Excluida ?',10,2,2,1,1) ||
                                   '</tr>';

  utl_file.put_line(lv_handle,lslinea);

  FOR v_gere_zona IN c_gere_zona LOOP

   lsdeta := '<tr width="100%">';
   utl_file.put_line(lv_handle,lsdeta);
   lsdeta := COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cod_secc,10,2,2,1,0);
   utl_file.put_line(lv_handle,lsdeta);
   lsdeta := COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.nom_clie,20,2,2,1,0);
   utl_file.put_line(lv_handle,lsdeta);
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_docu_iden,20,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele_fijo,15,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele_trab,15,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele_movi,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_esta,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_fact_neto,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cob_dias_31,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_sald_pend,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.por_recu,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_carg,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_ulti_actu,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_dias_gest,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ulti_fec_pago,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fact_camp_actu,10,2,2,1,0) ||
              --                    COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cons_excl,10,2,2,1,0) ||

                         -- lsdeta:=        '</tr>';
                           --  utl_file.put_line(v_handle,lsdeta);

   --dbms_output.put_line(lsdeta);


   END LOOP;

   lslinea := '</table></body></html>';
   utl_file.put_line(lv_handle,lslinea);
   utl_file.fclose(lv_handle);


  filehandler := utl_file.fopen('XLS_REPORTS', lv_file_name, 'R');

  IF utl_file.is_open(filehandler) THEN
   LOOP
    BEGIN
     utl_file.get_line(filehandler, lslinea);
     lv_clob_exce := lv_clob_exce || lslinea || UTL_TCP.CRLF;
    EXCEPTION WHEN no_data_found THEN
     EXIT;
    END;
   END LOOP;
  END IF;

  p_clob_file := lv_clob_exce;

  utl_file.fclose_all();

 END COB_PR_GENER_REPOR_GZONA_HTML;

 PROCEDURE COB_PR_GENER_REPOR_FFVV1_CLOB(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB)
 IS

  lsdeta                           VARCHAR2(32767);
  lv_clob_exce                     CLOB;
  lv_imp_deud_desd                 NUMBER(12,2);

  CURSOR c_gere_zona(
   p_mont_deud_desd NUMBER)
  IS
   WITH TEMP1
  AS
  ( SELECT
     est.oid_peri,
     est.cod_peri,
     est.des_regi,
     est.des_zona,
     est.cod_secc,
     mc.oid_clie,
     mc.cod_clie,
     TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
     est.imp_fact,
     est.imp_abon_nmon,
     est.imp_carg_dire,
     est.imp_fact_neto,
     est.cob_dias_31,
     est.imp_sald_pend_sac,
     est.fec_cier_vent,
     mcc.fec_docu,
     CASE
      WHEN mcc.val_ulti_nume_hist > 0 THEN
       mcc.fec_ulti_movi
      ELSE
       NULL
     END fec_ulti_movi
    FROM
     cob_repor_estad_recup_cobra est,
     ccc_movim_cuent_corri mcc,
     mae_clien mc
    WHERE est.oid_clie=mc.oid_clie
      AND est.oid_movi_cc = mcc.oid_movi_cc
      AND est.cod_peri = p_cod_peri
      AND est.imp_sald_pend_sac >= p_mont_deud_desd
      AND est.cod_regi = p_cod_regi
      AND est.cod_zona = p_cod_zona)
    SELECT
     cod_secc,
     cod_clie cod_clie,
     nom_clie,
     --FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele,
     --FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
     --TO_CHAR(SUM(imp_fact),'9,999,999,999') imp_fact,
     TO_CHAR(SUM(imp_fact_neto),'9,999,999,999') imp_fact_neto,
     TO_CHAR(SUM(imp_carg_dire),'9,999,999,999') imp_carg_dire,
     TO_CHAR(SUM(imp_abon_nmon),'9,999,999,999') imp_abon_nmon,
     TO_CHAR(SUM(cob_dias_31),'9,999,999,999') cob_dias_31,
     TO_CHAR(SUM(imp_sald_pend_sac),'9,999,999,999')imp_sald_pend
     /*
     CASE
      WHEN SUM(imp_fact_neto) > 0 THEN
       ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2)
      ELSE 0
     END por_recu,
     TO_CHAR(MAX(fec_docu),'DD/MM/YYYY') fec_carg,
     TO_CHAR(SYSDATE,'DD/MM/YYYY') fec_ulti_actu,
     TO_CHAR(MAX(TRUNC(fec_ulti_movi)),'DD/MM/YYYY')  ulti_fec_pago
     */
    FROM TEMP1
    GROUP BY
     oid_peri,
     cod_peri,
     des_regi,
     des_zona,
     cod_secc,
     oid_clie,
     cod_clie,
     nom_clie
   ORDER BY cod_secc ASC, imp_sald_pend DESC;

 BEGIN

  lv_imp_deud_desd := TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('ImporteDeudaReporteFFVV'));

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  DBMS_LOB.createtemporary(lv_clob_exce, FALSE);
  lv_clob_exce := '<html><body>' ||
            '<table style="text-align: left; width: 65%" border="1" cellpadding="0" cellspacing="0">' ||
            '<tr bgcolor="#AB82FF" width="100%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Seccion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Codigo',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Nombre Consultora',20,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('Direccion',20,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefonos',20,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('Estatus',10,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('Venta Facturada',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cargos Directos',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Abonos No Monetarios',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Recuperacion a 31 dias',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo Impago',10,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('% Recuperacion',10,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('Fecha Factura',10,2,2,1,1) ||
                                     --COB_PR_GENER_FILA_TABLA_HTML('Ultima Fecha de Pago',10,2,2,1,1) ||
                                   '</tr>';


  FOR v_gere_zona IN c_gere_zona(lv_imp_deud_desd) LOOP

   lsdeta := '<tr width="100%">' ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cod_secc,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cod_clie,15,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.nom_clie,20,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_dire,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele,20,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_esta,10,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_fact,15,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_fact_neto,15,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_carg_dire,15,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_abon_nmon,15,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cob_dias_31,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_sald_pend,10,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.por_recu,10,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_carg,10,2,2,1,0) ||
          --COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ulti_fec_pago,10,2,2,1,0) ||

                                    '</tr>';
   lv_clob_exce := lv_clob_exce || lsdeta;
   --DBMS_LOB.writeappend(lv_clob_exce, LENGTH(lsdeta), lsdeta);

  END LOOP;

  lv_clob_exce := lv_clob_exce || '</table></body></html>';

  p_clob_file := lv_clob_exce;

 END COB_PR_GENER_REPOR_FFVV1_CLOB;

 PROCEDURE COB_PR_GENER_REPOR_GZDEU_CLOB(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB)
 IS

  lsdeta                           VARCHAR2(32767);
  lv_clob_exce                     CLOB;
  lv_imp_deud_desd                 NUMBER(12,2);
  lv_cod_peri_sigu                 VARCHAR2(6);

  CURSOR c_gere_zona(
   p_mont_deud_desd NUMBER)
  IS
  WITH TEMP1
  AS
  ( SELECT
     est.oid_peri,
     est.cod_peri,
     est.des_regi,
     est.des_zona,
     est.cod_secc,
     mc.oid_clie,
     mc.cod_clie,
     TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
     est.imp_fact_neto,
     est.cob_dias_31,
     est.imp_sald_pend_sac,
     est.fec_cier_vent,
     mcc.fec_docu,
     mcc.fec_ulti_movi
    FROM
     cob_repor_estad_recup_cobra est,
     ccc_movim_cuent_corri mcc,
     mae_clien mc
    WHERE est.oid_clie=mc.oid_clie
      AND est.oid_movi_cc = mcc.oid_movi_cc
      AND est.cod_peri = p_cod_peri
      AND est.imp_sald_pend_sac >= p_mont_deud_desd
      AND est.cod_regi = p_cod_regi
      AND est.cod_zona = p_cod_zona)
    SELECT
     cod_secc,
     nom_clie,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele,
     FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
     TO_CHAR(SUM(imp_fact_neto),'9,999,999,999') imp_fact_neto,
     TO_CHAR(SUM(cob_dias_31),'9,999,999,999') cob_dias_31,
     TO_CHAR(SUM(imp_sald_pend_sac),'9,999,999,999')imp_sald_pend,
     CASE
      WHEN SUM(imp_fact_neto) > 0 THEN
       ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2)
      ELSE 0
     END por_recu,
     TO_CHAR(MAX(fec_docu),'DD/MM/YYYY') fec_carg,
     TO_CHAR(SYSDATE,'DD/MM/YYYY') fec_ulti_actu,
     TRUNC(SYSDATE) - MAX(fec_docu) num_dias_gest,
     TO_CHAR(MAX(TRUNC(fec_ulti_movi)),'DD/MM/YYYY')  ulti_fec_pago,
     'Si' fact_camp_actu,
     'No' cons_excl,
     CASE
      WHEN
       (SELECT COUNT(*)
        FROM flx_gener_finan_consu_flexi ff
        WHERE ff.oid_clie = t1.oid_clie
          AND ff.cod_peri < t1.cod_peri) > 0 THEN 'Si'
       ELSE 'No'
      END ind_util_flex,
      NVL((SELECT SUM(mcc.imp_pend)
       FROM
        flx_cuota_flexi_factu_detal fd,
        ccc_movim_cuent_corri mcc
       WHERE mcc.oid_movi_cc = fd.oid_movi_carg_flex
       AND fd.oid_clie = mcc.clie_oid_clie
       AND fd.oid_clie = t1.oid_clie
       AND fd.oid_peri_cuot_flex = t1.oid_peri),0) imp_cuot_flex_ante,
     NVL((SELECT SUM(ff.val_mont_flex_fina_proy)
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.cod_peri = t1.cod_peri
     AND ff.oid_clie = t1.oid_clie
     AND ff.cod_moti_rech IS NULL),0) imp_flex_fina_proy,
     CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(t1.oid_clie,t1.cod_peri) imp_deud_camp_ante,
     CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(t1.oid_clie,lv_cod_peri_sigu) imp_deud_tota
    FROM TEMP1 t1
    GROUP BY
     oid_peri,
     cod_peri,
     des_regi,
     des_zona,
     cod_secc,
     oid_clie,
     cod_clie,
     nom_clie
   ORDER BY cod_secc ASC, num_dias_gest DESC;

 BEGIN

  lv_imp_deud_desd := NVL(TO_NUMBER(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('ImporteDeudaReporteFFVV')),0);
  lv_cod_peri_sigu := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(p_cod_peri,1);

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  DBMS_LOB.createtemporary(lv_clob_exce, FALSE);
  lv_clob_exce := '<html><body>' ||
            '<table style="text-align: left; width: 65%" border="1" cellpadding="0" cellspacing="0">' ||
            '<tr bgcolor="#AB82FF" width="100%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Seccion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Nombre Consultora',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cedula',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Direccion',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefonos',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Estatus',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Recuperacion a 31 dias',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% Recuperacion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Cargo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Actualizacion Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Dias Gestion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Ultima Fecha de Pago',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Facturo Campa?a Actual?',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Excluida ?',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Uso Flex Anteriormente',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cuotas Flex Camp Actu',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Valor Posible Finan Camp Actu',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo Camp Ante',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo Total',10,2,2,1,1) ||
                                   '</tr>';


  FOR v_gere_zona IN c_gere_zona(lv_imp_deud_desd) LOOP

   lsdeta := '<tr width="100%">' ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cod_secc,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.nom_clie,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_docu_iden,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_dire,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_esta,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_fact_neto,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cob_dias_31,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_sald_pend,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.por_recu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_carg,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_ulti_actu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_dias_gest,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ulti_fec_pago,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fact_camp_actu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cons_excl,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ind_util_flex,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_cuot_flex_ante ,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_flex_fina_proy,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_deud_camp_ante,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_deud_tota,10,2,2,1,0) ||
                                    '</tr>';
   lv_clob_exce := lv_clob_exce || lsdeta;
   --DBMS_LOB.writeappend(lv_clob_exce, LENGTH(lsdeta), lsdeta);

  END LOOP;

  lv_clob_exce := lv_clob_exce || '</table></body></html>';

  p_clob_file := lv_clob_exce;

 END COB_PR_GENER_REPOR_GZDEU_CLOB;

 PROCEDURE COB_PR_GENER_REPOR_GZTOT_CLOB(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_clob_file                      OUT   CLOB)
 IS

  lsdeta                           VARCHAR2(32767);
  lv_clob_exce                     CLOB;
  lv_cod_peri_sigu                 VARCHAR2(6);

  CURSOR c_gere_zona
  IS
  WITH TEMP1
  AS
  ( SELECT
     est.oid_peri,
     est.cod_peri,
     est.des_regi,
     est.des_zona,
     est.cod_secc,
     mc.oid_clie,
     mc.cod_clie,
     TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2) nom_clie,
     est.imp_fact_neto,
     est.cob_dias_31,
     est.imp_sald_pend_sac,
     est.fec_cier_vent,
     mcc.fec_docu,
     mcc.fec_ulti_movi
    FROM
     cob_repor_estad_recup_cobra est,
     ccc_movim_cuent_corri mcc,
     mae_clien mc
    WHERE est.oid_clie=mc.oid_clie
      AND est.oid_movi_cc = mcc.oid_movi_cc
      AND est.cod_peri = p_cod_peri
      AND est.cod_regi = p_cod_regi
      AND est.cod_zona = p_cod_zona)
    SELECT
     cod_secc,
     nom_clie,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_DOCUM_IDENT(oid_clie) num_docu_iden,
     FIN_PKG_GENER.FIN_FN_OBTIE_DIREC_COMPL_CLIEN(oid_clie) val_dire,
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TF') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TT') || '-' ||
     FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(oid_clie,'TM') val_tele,
     FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(oid_clie) val_esta,
     TO_CHAR(SUM(imp_fact_neto),'9,999,999,999') imp_fact_neto,
     TO_CHAR(SUM(cob_dias_31),'9,999,999,999') cob_dias_31,
     TO_CHAR(SUM(imp_sald_pend_sac),'9,999,999,999')imp_sald_pend,
     CASE
      WHEN SUM(imp_fact_neto) > 0 THEN
       ROUND(SUM(cob_dias_31)*100/SUM(imp_fact_neto),2)
      ELSE 0
     END por_recu,
     TO_CHAR(MAX(fec_docu),'DD/MM/YYYY') fec_carg,
     TO_CHAR(SYSDATE,'DD/MM/YYYY') fec_ulti_actu,
     TRUNC(SYSDATE) - MAX(fec_docu) num_dias_gest,
     TO_CHAR(MAX(TRUNC(fec_ulti_movi)),'DD/MM/YYYY')  ulti_fec_pago,
     'Si' fact_camp_actu,
     'No' cons_excl,
     CASE
      WHEN
       (SELECT COUNT(*)
        FROM flx_gener_finan_consu_flexi ff
        WHERE ff.oid_clie = t1.oid_clie
          AND ff.cod_peri < t1.cod_peri) > 0 THEN 'Si'
       ELSE 'No'
      END ind_util_flex,
      NVL((SELECT SUM(mcc.imp_pend)
       FROM
        flx_cuota_flexi_factu_detal fd,
        ccc_movim_cuent_corri mcc
       WHERE mcc.oid_movi_cc = fd.oid_movi_carg_flex
       AND fd.oid_clie = mcc.clie_oid_clie
       AND fd.oid_clie = t1.oid_clie
       AND fd.oid_peri_cuot_flex = t1.oid_peri),0) imp_cuot_flex_ante,
     NVL((SELECT SUM(ff.val_mont_flex_fina_proy)
     FROM flx_gener_finan_consu_flexi ff
     WHERE ff.cod_peri = t1.cod_peri
     AND ff.oid_clie = t1.oid_clie
     AND ff.cod_moti_rech IS NULL),0) imp_flex_fina_proy,
     CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(t1.oid_clie,t1.cod_peri) imp_deud_camp_ante,
     CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(t1.oid_clie,lv_cod_peri_sigu) imp_deud_tota
    FROM temp1 t1
    GROUP BY
     oid_peri,
     cod_peri,
     des_regi,
     des_zona,
     cod_secc,
     oid_clie,
     cod_clie,
     nom_clie
   ORDER BY cod_secc ASC, num_dias_gest DESC;

 BEGIN

  lv_cod_peri_sigu := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(p_cod_peri,1);

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  DBMS_LOB.createtemporary(lv_clob_exce, FALSE);
  lv_clob_exce := '<html><body>' ||
            '<table style="text-align: left; width: 65%" border="1" cellpadding="0" cellspacing="0">' ||
            '<tr bgcolor="#AB82FF" width="100%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Seccion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Nombre Consultora',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cedula',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Direccion',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Telefonos',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Estatus',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Recuperacion a 31 dias',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% Recuperacion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Cargo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Fecha Actualizacion Saldo',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Dias Gestion',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Ultima Fecha de Pago',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Facturo Campa?a Actual?',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Excluida ?',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Uso Flex Anteriormente',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Cuotas Flex Camp Actu',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Valor Posible Finan Camp Actu',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo Camp Ante',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo Total',10,2,2,1,1) ||
                                   '</tr>';


  FOR v_gere_zona IN c_gere_zona LOOP

   lsdeta := '<tr width="100%">' ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cod_secc,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.nom_clie,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_docu_iden,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_dire,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_tele,20,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.val_esta,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_fact_neto,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cob_dias_31,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_sald_pend,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.por_recu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_carg,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fec_ulti_actu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.num_dias_gest,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ulti_fec_pago,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.fact_camp_actu,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.cons_excl,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.ind_util_flex,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_cuot_flex_ante ,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_flex_fina_proy,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_deud_camp_ante,10,2,2,1,0) ||
          COB_PR_GENER_FILA_TABLA_HTML(v_gere_zona.imp_deud_tota,10,2,2,1,0) ||
                                    '</tr>';

   lv_clob_exce := lv_clob_exce || lsdeta;
   --DBMS_LOB.writeappend(lv_clob_exce, LENGTH(lsdeta), lsdeta);
  END LOOP;

  lv_clob_exce := lv_clob_exce || '</table></body></html>';

  p_clob_file := lv_clob_exce;

 END COB_PR_GENER_REPOR_GZTOT_CLOB;

 PROCEDURE COB_PR_GENER_INFOR_GEREN_FFVV
 IS

  lv_num_dias_inic                 NUMBER(12);
  lv_num_dias_fina                 NUMBER(12);
  lv_mail_confi                    cob_param_gener.val_para%TYPE;
  lv_subj_mens                     VARCHAR2(100);
  lv_cuer_mens                     CLOB;

 CURSOR c_regi
 IS
  SELECT
   spc.cod_peri,
   zr.cod_regi,
   dv.val_emai
  FROM
   cra_crono cr,
   cra_activ ca,
   cra_perio cp,
   seg_perio_corpo spc,
   zon_zona zz,
   zon_regio zr,
   (SELECT zr.cod_regi, mcc.val_text_comu val_emai
    FROM
     zon_regio zr,
     mae_clien mc,
     mae_clien_comun mcc,
     mae_tipo_comun mtc
    WHERE zr.clie_oid_clie = mc.oid_clie
      AND mc.oid_clie = mcc.clie_oid_clie
      AND mtc.oid_tipo_comu = mcc.ticm_oid_tipo_comu
      AND mtc.cod_tipo_comu = 'ML') dv
   WHERE cr.cact_oid_acti = ca.oid_acti
     AND cr.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND cr.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND ca.cod_acti = 'FA'
     AND zr.cod_regi = dv.cod_regi
     AND zr.ind_ofic IS NULL
   HAVING TRUNC(SYSDATE) - MAX(cr.fec_inic) >= lv_num_dias_inic
      AND TRUNC(SYSDATE) - MAX(cr.fec_inic) <= lv_num_dias_fina
   GROUP BY  spc.cod_peri, zr.cod_regi,dv.val_emai
   ORDER BY 1,2 ASC;

 CURSOR c_zona
 IS
  SELECT
   spc.cod_peri,
   zr.cod_regi,
   zz.cod_zona,
   dv.val_emai
  FROM
   cra_crono cr,
   cra_activ ca,
   cra_perio cp,
   seg_perio_corpo spc,
   zon_zona zz,
   zon_regio zr,
   (( SELECT zr.cod_regi, zz.cod_zona,mc.cod_clie, mcc.val_text_comu val_emai
      FROM
       zon_regio zr,
       zon_zona zz,
       mae_clien mc,
       mae_clien_comun mcc,
       mae_tipo_comun mtc
      WHERE zz.clie_oid_clie = mc.oid_clie
        AND mc.oid_clie = mcc.clie_oid_clie
        AND mtc.oid_tipo_comu = mcc.ticm_oid_tipo_comu
        AND mtc.cod_tipo_comu = 'ML'
        AND zz.ind_ofic IS NULL
        AND zr.oid_regi = zz.zorg_oid_regi)
      UNION
     (
       SELECT
        zr.cod_regi,
        zz.cod_zona,
        mc.cod_clie,
        mcc.val_text_comu val_emai
       FROM
        zon_zona zz,
        zon_regio zr,
        mae_clien mc,
        mae_clien_comun mcc,
        mae_tipo_comun mtc
       WHERE zz.zorg_oid_regi = zr.oid_regi
         AND zr.clie_oid_clie = mc.oid_clie
         AND mc.oid_clie = mcc.clie_oid_clie
         AND mtc.oid_tipo_comu = mcc.ticm_oid_tipo_comu
         AND mtc.cod_tipo_comu = 'ML'
         AND zz.clie_oid_clie IS NULL
         AND zz.ind_acti = 1
         AND zz.ind_borr = 0
         AND zz.ind_ofic IS NULL)) dv
   WHERE cr.cact_oid_acti = ca.oid_acti
     AND cr.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND cr.perd_oid_peri = cp.oid_peri
     AND cp.peri_oid_peri = spc.oid_peri
     AND zr.cod_regi = dv.cod_regi
     AND zz.cod_zona = dv.cod_zona
     AND ca.cod_acti = 'FA'
     AND zr.cod_regi = dv.cod_regi
     AND (SELECT COUNT(*)
          FROM
           cob_repor_estad_recup_cobra c
          WHERE c.cod_peri = spc.cod_peri
            AND c.cod_regi = zr.cod_regi
            AND c.cod_zona = zz.cod_zona) > 0 
   HAVING TRUNC(SYSDATE) - MAX(cr.fec_inic) >= lv_num_dias_inic
      AND TRUNC(SYSDATE) - MAX(cr.fec_inic) <= lv_num_dias_fina
   GROUP BY  spc.cod_peri, zr.cod_regi,zz.cod_zona,dv.val_emai
   ORDER BY 1,2,3 ASC;

 BEGIN

  lv_num_dias_inic := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('NumeroDiasInicioReporteFFVV');
  lv_num_dias_fina := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('NumeroDiasFinReporteFFVV');

  COB_PKG_REPOR_ESTAD.COB_PR_ACTUA_ESTAD_RECUP_COBRA;

  FOR v_regi IN c_regi LOOP
   COB_PKG_PROCE.COB_PR_GENER_INFOR_GEREN_REGIO(v_regi.cod_peri,v_regi.cod_regi,v_regi.val_emai);
  END LOOP;

  FOR v_zona IN c_zona LOOP
   COB_PKG_PROCE.COB_PR_GENER_INFOR_GEREN_ZONA(v_zona.cod_peri,v_zona.cod_regi,v_zona.cod_zona,v_zona.val_emai);
  END LOOP;

  BEGIN

   SELECT c.val_para
   INTO lv_mail_confi
   FROM cob_param_gener c
   WHERE c.cod_para = 'EmailConfirmacionFFVV';

   lv_subj_mens := 'Confirmacion Envio Reportes Cobranzas FFVV';

   lv_cuer_mens := lv_cuer_mens || '<html><body>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr>';
   lv_cuer_mens := lv_cuer_mens || '<b>Estimado Usuario </b>';
   lv_cuer_mens := lv_cuer_mens || '</tr>';
   lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || '   El reporte fue enviado exitosamente desde SSICC' ;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table></body></html>';

   -- Enviando Email de Confirmacion --
   FIN_PKG_SEND_MAIL.SEND_MAIL(gc_mail_envio_proce,
                            lv_mail_confi,
                            lv_subj_mens,
                            lv_cuer_mens,
                            NULL,
                            NULL,'text/html');

  EXCEPTION
   WHEN OTHERS THEN
    NULL;
  END;
 END COB_PR_GENER_INFOR_GEREN_FFVV;

 PROCEDURE COB_PR_GENER_INFOR_GEREN_REGIO(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_val_mail                       IN   mae_clien_comun.val_text_comu%TYPE)
 IS

  lv_subj_mens                     VARCHAR2(100);
  lv_cuer_mens                     CLOB;

  CURSOR c_zona_regi(
   pc_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
   pc_cod_regi                     IN   zon_regio.cod_regi%TYPE)
  IS
   SELECT
    c.cod_peri,
    c.cod_zona,
    TO_CHAR(SUM(c.imp_fact_neto),'9,999,999,999.99') imp_fact_neto,
    TO_CHAR(SUM(c.cob_dias_31),'9,999,999,999.99') cob_31,
    CASE
     WHEN SUM(c.imp_fact_neto) > 0 THEN
      TO_CHAR(ROUND((SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100),2),'9,999,999,999.99')
     ELSE TO_CHAR('0','99.99')
    END porc_recu,
    TO_CHAR(SUM(c.imp_sald_pend_sac),'9,999,999,999.99') imp_sald_pend,
    TO_CHAR(MIN(c.fec_cier_31),'DD/MM/YYYY') fec_inic_cier
   FROM cob_repor_estad_recup_cobra c
   WHERE c.cod_peri = pc_cod_peri
     AND c.cod_regi = pc_cod_regi
   GROUP BY c.cod_peri, c.des_regi, c.cod_zona
   ORDER BY c.cod_zona ASC;

  lv_imp_fact_neto               VARCHAR2(15);
  lv_cob_31                      VARCHAR2(15);
  lv_porc_recu                   VARCHAR2(10);
  lv_fec_inic_cier               VARCHAR2(10);
  lv_cod_pais                    VARCHAR2(3);

  lv_copi_repo                   cob_param_gener.val_para%TYPE;
  lv_ind_most_fech_cier          cob_param_gener.val_para%TYPE;
  lv_emai_gere_apoy              mae_clien_comun.val_text_comu%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');

  SELECT
   TO_CHAR(SUM(c.imp_fact_neto),'9,999,999,999') imp_fact_neto,
   TO_CHAR(SUM(c.cob_dias_31),'9,999,999,999') cob_31,
   CASE
    WHEN SUM(c.imp_fact_neto) > 0 THEN
     TO_CHAR(ROUND((SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100),2),'99.99')
    ELSE TO_CHAR('0.00')
   END porc_recu,
   TO_CHAR(MIN(c.fec_cier_31),'DD/MM/YYYY') fec_inic_cier
  INTO
   lv_imp_fact_neto,
   lv_cob_31,
   lv_porc_recu,
   lv_fec_inic_cier
  FROM cob_repor_estad_recup_cobra c
  WHERE c.cod_peri = p_cod_peri
    AND c.cod_regi = p_cod_regi
  GROUP BY c.cod_peri,c.cod_regi;

  -- Adicionando a la Gerente Regional de apoyo Fijo --
  lv_emai_gere_apoy := COB_PKG_GENER.COB_FN_OBTIE_EMAIL_GEREN_APOYO(p_cod_regi);
  lv_ind_most_fech_cier := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('MostrarFechaCierreReporteFFVV');

  IF lv_emai_gere_apoy IS NOT NULL THEN
   lv_copi_repo := lv_emai_gere_apoy || ';' || COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('EmailCopiaReporteFFVV');
  ELSE
   lv_copi_repo := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('EmailCopiaReporteFFVV');
  END IF;

  lv_subj_mens := lv_cod_pais || ' Recuperacion de Cobranza C ' || p_cod_peri ||
                   ' Region ' || p_cod_regi;

   --lv_cuer_mens := '';
   lv_cuer_mens := lv_cuer_mens || '<html><body>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr>';
   lv_cuer_mens := lv_cuer_mens || '<b>Estimada Gerente Regional</b>';
   lv_cuer_mens := lv_cuer_mens || '</tr>';
   lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 156px;"><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || 'A continuacion se detalla el % de recuperacion de cobranza de tu region. ' ;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';
   lv_cuer_mens := lv_cuer_mens || '<br>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';

   IF lv_ind_most_fech_cier = '1' THEN

   lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('PERIODO',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('VENTA A COBRAR',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('COBRANZA A 31 DIAS',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% RECUPERACION',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('INICIO FECHA CIERRE',10,2,2,1,1) ||
                                   '</tr>';

   lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                   COB_PR_GENER_FILA_TABLA_HTML(p_cod_peri,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_imp_fact_neto,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_cob_31,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_porc_recu,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_fec_inic_cier,10,2,2,1,0) ||
                                  '</tr>';
   ELSE

    lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('PERIODO',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('VENTA A COBRAR',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('COBRANZA A 31 DIAS',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% RECUPERACION',10,2,2,1,1) ||

                                   '</tr>';

    lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                   COB_PR_GENER_FILA_TABLA_HTML(p_cod_peri,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_imp_fact_neto,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_cob_31,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(lv_porc_recu,10,2,2,1,0) ||
                                  '</tr>';

   END IF;

   lv_cuer_mens := lv_cuer_mens || '</table>';

   lv_cuer_mens := lv_cuer_mens || '<br>';

   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || 'A continuacion se detalla el % de recuperacion de cobranza de sus zonas. ' ;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';

   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';

   IF lv_ind_most_fech_cier = '1' THEN

   lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('ZONA',2,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('VENTA A COBRAR',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('COBRANZA A 31 DIAS',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% RECUPERACION',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('INICIO FECHA CIERRE',10,2,2,1,1) ||
                                   '</tr>';
   ELSE

       lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('ZONA',5,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('VENTA A COBRAR',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('COBRANZA A 31 DIAS',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% RECUPERACION',10,2,2,1,1) ||
                                   '</tr>';

   END IF;

   FOR v_zona_regi IN c_zona_regi(p_cod_peri,p_cod_regi) LOOP

    IF lv_ind_most_fech_cier = '1' THEN

    lv_cuer_mens := lv_cuer_mens || '<tr width="80%"><td width="40%"><font face="Arial" size="2"><b>' || v_zona_regi.cod_zona ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.imp_fact_neto,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.cob_31,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.porc_recu,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.fec_inic_cier,10,2,2,1,0) ||
                                  '</tr>';
    ELSE

      lv_cuer_mens := lv_cuer_mens || '<tr width="80%"><td width="40%"><font face="Arial" size="2"><b>' || v_zona_regi.cod_zona ||
                                   COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.imp_fact_neto,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.cob_31,10,2,2,1,0) ||
                                   COB_PR_GENER_FILA_TABLA_HTML(v_zona_regi.porc_recu,10,2,2,1,0) ||
                                   '</tr>';

    END IF;

   END LOOP;

   lv_cuer_mens := lv_cuer_mens || '</table>';
   lv_cuer_mens := lv_cuer_mens || '<br>';
   lv_cuer_mens := lv_cuer_mens || '<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>';

   FIN_PKG_SEND_MAIL.SEND_MAIL('ssicc_generacion_reportes@belcorp.biz',
                            p_val_mail,
                            lv_subj_mens,
                            lv_cuer_mens,
                            lv_copi_repo,
                            NULL,'text/html');

 END COB_PR_GENER_INFOR_GEREN_REGIO;

 PROCEDURE COB_PR_GENER_INFOR_GEREN_ZONA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                       IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                       IN   zon_zona.cod_zona%TYPE,
  p_val_mail                       IN   mae_clien_comun.val_text_comu%TYPE)
 IS

  lv_copi_repo                     cob_param_gener.val_para%TYPE;
  lv_ind_modu_let                  cob_param_gener.val_para%TYPE;
  lv_cod_camp_let                  cob_param_gener.val_para%TYPE;
  lv_indi_tipo_adju                cob_param_gener.val_para%TYPE;
  lv_cod_peri_sigu                 seg_perio_corpo.cod_peri%TYPE;
  lv_subj_mens                     VARCHAR2(100);
  lv_cuer_mens                     CLOB;
  lv_clob_adju_deud                CLOB;
  lv_repo_name_deud                VARCHAR2(50);
  lv_clob_adju_tota                CLOB;
  lv_repo_name_tota                VARCHAR2(50);
  attachments                      FIN_PKG_SEND_MAIL.ARRAY_ATTACHMENTS := FIN_PKG_SEND_MAIL.ARRAY_ATTACHMENTS();

  CURSOR c_secc_zona(
   pc_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
   pc_cod_zona                     IN   zon_regio.cod_regi%TYPE)
  IS
   SELECT
   c.cod_peri,
   c.cod_zona,
   c.cod_secc,
   TO_CHAR(SUM(c.imp_fact_neto),'9,999,999,999') imp_fact_neto,
   TO_CHAR(SUM(c.cob_dias_31),'9,999,999,999') cob_31,
   CASE
    WHEN SUM(c.imp_fact_neto) > 0 THEN
     TO_CHAR(ROUND(SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100,2),'999.99')
    ELSE TO_CHAR('0.00')
   END porc_recu,
   TO_CHAR(SUM(c.imp_sald_pend_sac),'9,999,999,999') saldo,
   TO_CHAR(MIN(c.fec_cier_31),'DD/MM/YYYY') fec_inic_cier
  FROM
   cob_repor_estad_recup_cobra c
  WHERE c.cod_peri = pc_cod_peri
    AND c.cod_zona = pc_cod_zona
  GROUP BY c.cod_peri,c.cod_zona,c.cod_secc
  ORDER BY c.cod_secc ASC;

  CURSOR c_tram
  IS
  SELECT *
  FROM cob_param_tramo_cobra_ffvv;

  lv_imp_fact_neto               NUMBER(15,2);
  lv_cob_31                      NUMBER(15,2);
  lc_imp_fact_neto               VARCHAR2(15);
  lc_cob_31                      VARCHAR2(15);
  lv_imp_sald                    VARCHAR2(15);
  lv_imp_falt_tram               VARCHAR2(15);
  lv_porc_recu                   VARCHAR2(10);
  lv_fec_inic_cier               VARCHAR2(10);
  lv_cod_pais                    VARCHAR2(3);
  lv_ind_most_fech_cier          cob_param_gener.val_para%TYPE;
  lv_mens_adic                   cob_param_gener.val_para%TYPE;
 
 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_ind_most_fech_cier := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('MostrarFechaCierreReporteFFVV');
  lv_mens_adic := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('MensajeAdicionalFFVV');
  
  SELECT
   SUM(c.imp_fact_neto) imp_fact_neto,
   SUM(c.cob_dias_31) cob_31,
   CASE
    WHEN SUM(c.imp_fact_neto) > 0 THEN
     TO_CHAR(ROUND(SUM(c.cob_dias_31)/SUM(c.imp_fact_neto)*100,2),'999.99')
    ELSE TO_CHAR(0,'9.99')
   END porc_recu,
   TO_CHAR(SUM(c.imp_sald_pend_sac),'9,999,999,999') saldo,
   TO_CHAR(MIN(c.fec_cier_31),'DD/MM/YYYY') fec_inic_cier
  INTO
   lv_imp_fact_neto,
   lv_cob_31,
   lv_porc_recu,
   lv_imp_sald,
   lv_fec_inic_cier
  FROM
   cob_repor_estad_recup_cobra c
  WHERE c.cod_peri = p_cod_peri
     AND c.cod_regi = p_cod_regi
     AND c.cod_zona = p_cod_zona
  GROUP BY c.cod_peri,c.cod_regi,c.cod_zona
  ORDER BY c.cod_regi ASC,c.cod_zona ASC;

  lc_imp_fact_neto := TO_CHAR(lv_imp_fact_neto,'9,999,999,999');
  lc_cob_31 := TO_CHAR(lv_cob_31,'9,999,999,999');

  lv_copi_repo := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('EmailCopiaReporteFFVV');

  lv_cod_peri_sigu := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(p_cod_peri,1);

  lv_subj_mens := lv_cod_pais || ' Recuperacion de Cobranza C ' || p_cod_peri ||
                   ' Zona ' || p_cod_zona;

  lv_cuer_mens := lv_cuer_mens || '<html><body>';
  lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="0">';
  lv_cuer_mens := lv_cuer_mens || '<tr>';
  lv_cuer_mens := lv_cuer_mens || '<b>Estimada Gerente de Zona</b>';
  lv_cuer_mens := lv_cuer_mens || '</tr>';
  
  IF lv_mens_adic IS NOT NULL THEN
    lv_cuer_mens := lv_cuer_mens || '<tr>' || lv_mens_adic || '</tr>';    
  END IF;
  
  lv_cuer_mens := lv_cuer_mens || '<tr><td style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
  lv_cuer_mens := lv_cuer_mens || 'A continuacion se detalla el % de recuperacion de cobranza de tu zona. ' ;
  lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';
  lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';



   lv_cuer_mens := lv_cuer_mens || '<tr bgcolor="#AB82FF" width="100%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Periodo',6,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',15,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('($) Recuperacion a 31 dias',12,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('(%) Recuperacion',12,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo',11,2,2,1,1);

   IF lv_ind_most_fech_cier = '1' THEN
    lv_cuer_mens := lv_cuer_mens || COB_PR_GENER_FILA_TABLA_HTML('Fecha Inicio de Cierre',10,2,2,1,1);
   END IF;

   FOR v_tram IN c_tram LOOP

    lv_cuer_mens := lv_cuer_mens || COB_PR_GENER_FILA_TABLA_HTML(v_tram.des_tram,15,2,2,1,1);

   END LOOP;

   lv_cuer_mens := lv_cuer_mens || '</tr>';

   lv_cuer_mens := lv_cuer_mens || '<tr width="100%">' ||
                                  COB_PR_GENER_FILA_TABLA_HTML(p_cod_peri,6,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(lc_imp_fact_neto,12,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(lc_cob_31,15,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(lv_porc_recu,12,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(lv_imp_sald,11,2,2,1,0);

   IF lv_ind_most_fech_cier = '1' THEN
    lv_cuer_mens := lv_cuer_mens || COB_PR_GENER_FILA_TABLA_HTML(lv_fec_inic_cier,10,2,2,1,0);
  END IF;


   FOR v_tram IN c_tram LOOP

    lv_imp_falt_tram :=  TO_CHAR(ROUND(lv_imp_fact_neto*v_tram.val_porc_para/100,2) - lv_cob_31,'9,999,999,999');
    lv_cuer_mens := lv_cuer_mens || COB_PR_GENER_FILA_TABLA_HTML(lv_imp_falt_tram,15,2,2,1,0);

   END LOOP;

  lv_cuer_mens := lv_cuer_mens || '</tr>';

  lv_cuer_mens := lv_cuer_mens || '</table>';

  lv_cuer_mens := lv_cuer_mens || '<table border = 0><tr><td></td></tr>';
  lv_cuer_mens := lv_cuer_mens || '<tr><td> </td></tr>';
  lv_cuer_mens := lv_cuer_mens || '<tr>A continuacion se detalla la recuperacion por secciones</tr>';
  lv_cuer_mens := lv_cuer_mens || '<tr><td> </td></tr></table>';
  lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';
  lv_cuer_mens := lv_cuer_mens || '<tr bgcolor="#AB82FF" width><td colspan=5><center><b>Seguimiento para GZ</b></center></td></tr>';
  lv_cuer_mens := lv_cuer_mens || '<tr bgcolor="#AB82FF" width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Seccion',5,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Venta por Cobrar',25,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('($) Recuperacion a 31 dias',25,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('(%) Recuperacion',25,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('Saldo',25,2,2,1,1) ||
                                     '</tr>';

  FOR v_secc_zona IN c_secc_zona(p_cod_peri,p_cod_zona) LOOP

   lv_cuer_mens := lv_cuer_mens || '<tr width="80%">' ||
                 COB_PR_GENER_FILA_TABLA_HTML(v_secc_zona.cod_secc,5,2,2,1,0) ||
                 COB_PR_GENER_FILA_TABLA_HTML(v_secc_zona.imp_fact_neto,25,2,2,1,0) ||
                 COB_PR_GENER_FILA_TABLA_HTML(v_secc_zona.cob_31,25,2,2,1,0) ||
                 COB_PR_GENER_FILA_TABLA_HTML(v_secc_zona.porc_recu,25,2,2,1,0) ||
                 COB_PR_GENER_FILA_TABLA_HTML(v_secc_zona.saldo,25,2,2,1,0) || '</tr>';

  END LOOP;

  lv_cuer_mens := lv_cuer_mens || '</table>';
  lv_cuer_mens := lv_cuer_mens || '<br>';

  lv_cuer_mens := lv_cuer_mens || '<br>';
  lv_cuer_mens := lv_cuer_mens || '<font face="Arial" size="3"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>';

  lv_indi_tipo_adju := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('IndicadorTipoAdjuntoFFVV');

  IF lv_indi_tipo_adju = 'A' THEN

   lv_repo_name_deud := 'Detallado_Zona_' || p_cod_zona || '_Deudoras.html';
   COB_PKG_PROCE.COB_PR_GENER_REPOR_FFVV1_CLOB(p_cod_peri,p_cod_regi,p_cod_zona,lv_clob_adju_deud);

   attachments.delete();
   attachments.extend(1);
   attachments(1).attach_name := lv_repo_name_deud;
   attachments(1).data_type := 'text/plain';
   attachments(1).attach_content := lv_clob_adju_deud;

    DBMS_LOB.FREETEMPORARY(lv_clob_adju_deud);

   ELSE

    lv_repo_name_deud := 'Detallado_Zona_' || p_cod_zona || '_Deudoras.html';
    COB_PKG_PROCE.COB_PR_GENER_REPOR_GZDEU_CLOB(p_cod_peri,p_cod_regi,p_cod_zona,lv_clob_adju_deud);

    attachments.delete();
    attachments.extend(1);
    attachments(1).attach_name := lv_repo_name_deud;
    attachments(1).data_type := 'text/plain';
    attachments(1).attach_content := lv_clob_adju_deud;

    DBMS_LOB.FREETEMPORARY(lv_clob_adju_deud);

   lv_repo_name_tota := 'Detallado_Zona_' || p_cod_zona || '_Totales.html';
   cob_pkg_proce.COB_PR_GENER_REPOR_GZTOT_CLOB(p_cod_peri,p_cod_regi,p_cod_zona,lv_clob_adju_tota);

   attachments.extend(1);
   attachments(2).attach_name := lv_repo_name_tota;
   attachments(2).data_type := 'text/plain';
   attachments(2).attach_content := lv_clob_adju_tota;

    DBMS_LOB.FREETEMPORARY(lv_clob_adju_tota);

   END IF;

   FIN_PKG_SEND_MAIL.SEND_MAIL('ssicc_generacion_reportes@belcorp.biz',
                           'jflorencio@belcorp.biz',--p_val_mail,
                           lv_subj_mens,
                           lv_cuer_mens,
                           lv_copi_repo,
                           attachments,'text/html');

 END COB_PR_GENER_INFOR_GEREN_ZONA;

 PROCEDURE COB_PR_GENER_INFOR_SEGUI_COBRA(
  p_fec_proc                     IN    VARCHAR2)
 IS

  lv_subj_mens                     VARCHAR2(100);
  lv_cuer_mens                     CLOB;
  lv_clob_adju                     CLOB;
  lv_sql                           VARCHAR2(4000);
  lv_repo_name                     VARCHAR2(400);
  lv_fec_proc                      DATE;
  attachments                      FIN_PKG_SEND_MAIL.ARRAY_ATTACHMENTS := FIN_PKG_SEND_MAIL.ARRAY_ATTACHMENTS();
  lv_ind_asig                      NUMBER(12);
  lv_mail_orig                     VARCHAR2(50);

 CURSOR c_etap_cobr
 IS
  SELECT
   u.cod_usua_cobr,
   u.val_nomb_usua_cobr cobrador,
   m.val_mail,
   m.val_mail_copi,
   e.num_secu_etap,
   e.cod_etap_deud,
   e.val_desc etapa
  FROM
   cob_detal_asign_carte d,
   cob_usuar_cobra_pais u,
   cob_etapa_deuda_pais e,
   cob_envio_email_segui_cobra m
  WHERE d.cod_etap_deud = e.cod_etap_deud
    AND d.cod_usua_cobr = u.cod_usua_cobr
    AND u.ind_supe = 0
    AND d.cod_etap_deud = m.cod_etap_deud
    AND d.cod_usua_cobr = m.cod_usua_cobra
  GROUP BY u.cod_usua_cobr,u.val_nomb_usua_cobr,m.val_mail, m.val_mail_copi,e.num_secu_etap,e.cod_etap_deud,e.val_desc;

  CURSOR c_etap_deta(
   pc_cod_cobr                     IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
   pc_cod_etap_deud                IN   cob_etapa_deuda.cod_etap_deud%TYPE)
  IS
   SELECT
    u.val_nomb_usua_cobr cobrador,
    e.num_secu_etap,
    e.cod_etap_deud,
    e.val_desc etapa,
    d.cod_peri,
    TO_CHAR(SUM(d.imp_deud_asig),'999,999,999,999') cart_asig,
    TO_CHAR(SUM(d.imp_deud_canc),'999,999,999,999') valo_recu,
    TO_CHAR(ROUND(SUM(d.imp_deud_canc)*100/SUM(d.imp_deud_asig),1),'999.0') || '%'  porc_recu,
    TO_CHAR(SUM(d.imp_deud_pend),'999,999,999,999') sald_cobr,
    TO_CHAR(MIN(d.fec_cier),'DD/MM/YYYY') inic_cier
   FROM
    cob_detal_asign_carte d,
    cob_usuar_cobra_pais u,
    cob_etapa_deuda_pais e
   WHERE d.cod_etap_deud = e.cod_etap_deud
     AND d.cod_usua_cobr = u.cod_usua_cobr
     AND u.ind_supe = 0
     AND d.cod_usua_cobr = pc_cod_cobr
     AND e.cod_etap_deud = pc_cod_etap_deud
   GROUP BY u.val_nomb_usua_cobr,e.num_secu_etap,e.cod_etap_deud,e.val_desc,d.cod_peri
   ORDER BY cod_peri ASC;

 CURSOR c_cart_asig(
  pc_cod_cobr                     IN   cob_usuar_cobra_pais.cod_usua_cobr%TYPE,
  pc_cod_etap_deud                IN   cob_etapa_deuda.cod_etap_deud%TYPE)
 IS
 SELECT
    u.val_nomb_usua_cobr cobrador,
    e.num_secu_etap,
    e.cod_etap_deud,
    e.val_desc etapa,
    d.cod_peri,
    COUNT(*) cart_cant,
    TO_CHAR(SUM(d.imp_deud_asig),'999,999,999,999') cart_asig
   FROM
    cob_detal_asign_carte d,
    cob_usuar_cobra_pais u,
    cob_etapa_deuda_pais e
   WHERE d.cod_etap_deud = e.cod_etap_deud
     AND d.cod_usua_cobr = u.cod_usua_cobr
     AND u.ind_supe = 0
     AND d.fec_asig = lv_fec_proc
     AND d.cod_usua_cobr = pc_cod_cobr
     and e.cod_etap_deud = pc_cod_etap_deud
   GROUP BY u.val_nomb_usua_cobr,e.num_secu_etap,e.cod_etap_deud,e.val_desc,d.cod_peri
   ORDER BY cod_peri DESC;

 BEGIN

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

  lv_fec_proc :=TO_DATE(p_fec_proc,'DD/MM/YYYY');

  FOR v_etap_cobr IN c_etap_cobr LOOP

   lv_subj_mens := 'Seguimiento Etapa ' || v_etap_cobr.etapa || ' - ' || v_etap_cobr.cobrador;

   --lv_cuer_mens := '';
   lv_cuer_mens := lv_cuer_mens || '<html><body>';
   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="20" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr>';
   lv_cuer_mens := lv_cuer_mens || '<b><font color="03150E" face="Arial" size="4">Buenos dias</font></b>';
   lv_cuer_mens := lv_cuer_mens || '</tr>';
   lv_cuer_mens := lv_cuer_mens || '<tr><td><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || '   Adjunto encontraras tu efectividad de recaudo al dia de hoy en la etapa de ' || v_etap_cobr.etapa;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';
  -- lv_cuer_mens := lv_cuer_mens || '<br>';
  -- lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';

   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr bgcolor = "#0033CC" width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('PERIODO',5,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('CARTERA ASIGNADA',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('VALOR RECUPERADO',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('% RECUPERACION',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('SALDO POR COBRAR',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('DIA INICIO CIERRE',10,2,2,1,1) ||
                                   '</tr>';


   FOR v_etap_deta IN c_etap_deta(v_etap_cobr.cod_usua_cobr,v_etap_cobr.cod_etap_deud) LOOP

    lv_cuer_mens := lv_cuer_mens || '<tr bgcolor = "#E0F1F9">' ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.cod_peri ,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.cart_asig ,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.valo_recu,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.porc_recu,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.sald_cobr,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_etap_deta.inic_cier,10,2,2,1,0) ||
                                  '</tr>';
   END LOOP;

   lv_cuer_mens := lv_cuer_mens || '</table>';
   lv_cuer_mens := lv_cuer_mens || '<br>';


   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="0" cellspacing="15">';


   SELECT COUNT(*)
   INTO lv_ind_asig
   FROM cob_detal_asign_carte d
   WHERE d.fec_asig = trunc(SYSDATE)
    AND d.cod_usua_cobr = v_etap_cobr.cod_usua_cobr
    AND d.cod_etap_deud = v_etap_cobr.cod_etap_deud;

  IF lv_ind_asig > 0 THEN
   lv_cuer_mens := lv_cuer_mens || '<tr><td colspan=4><font color="#4188b2" face="Arial" size="4"><b>';
   lv_cuer_mens := lv_cuer_mens || 'Adicionalmente se les informa la asignacion del dia de hoy en la etapa ' || v_etap_cobr.etapa;
   lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table>';

   lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="1" cellpadding="0" cellspacing="0">';
   lv_cuer_mens := lv_cuer_mens || '<tr bgcolor = "#0033CC" width="80%">' ||
                                     COB_PR_GENER_FILA_TABLA_HTML('ETAPA',5,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('PERIODO',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('CANTIDAD',10,2,2,1,1) ||
                                     COB_PR_GENER_FILA_TABLA_HTML('CARTERA ASIGNADA',10,2,2,1,1) ||
                                   '</tr>';

   FOR v_cart_asig IN c_cart_asig(v_etap_cobr.cod_usua_cobr,v_etap_cobr.cod_etap_deud) LOOP

    lv_cuer_mens := lv_cuer_mens || '<tr bgcolor = "#E0F1F9">' ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_cart_asig.etapa ,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_cart_asig.cod_peri ,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_cart_asig.cart_cant,10,2,2,1,0) ||
                                  COB_PR_GENER_FILA_TABLA_HTML(v_cart_asig.cart_asig,10,2,2,1,0)  ||
                                  '</tr>';
   END LOOP;

  ELSE
    lv_cuer_mens := lv_cuer_mens || '<tr><td colspan=4 style="height: 172px; width: 256px;"><font color="#4188b2" face="Arial" size="4"><b>';
    lv_cuer_mens := lv_cuer_mens || 'Adicionalmente se les informa que el dia de hoy no se le asigno cartera';
    lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr>';

  END IF;

   lv_cuer_mens := lv_cuer_mens || '</table>';
   lv_cuer_mens := lv_cuer_mens || '<br>';

   lv_cuer_mens := lv_cuer_mens || '<font face="Arial" size="1"><br><b>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada.</b><br><br><br></font><br><br><br></body></html>';

   attachments.delete();

   SELECT z.val_sql
   INTO lv_sql
   FROM cob_repor_adjun_segui_cobra z
   WHERE z.num_orde = 1;

   lv_sql := REPLACE(lv_sql,'$$1',v_etap_cobr.cod_usua_cobr);
   lv_sql := REPLACE(lv_sql,'$$2',v_etap_cobr.cod_etap_deud);

   lv_repo_name := 'Reporte_Seguimiento_Campana.xls';

   BEGIN
    gen_pkg_xls.gen_pr_gener_file_excel_tclob(lv_sql,lv_repo_name,1000,lv_clob_adju);
   EXCEPTION
    WHEN OTHERS THEN
     lv_clob_adju := NULL;
   END;

   attachments.extend(1);
   attachments(1).attach_name := lv_repo_name;
   attachments(1).data_type := 'text/plain';
   attachments(1).attach_content := lv_clob_adju;

   SELECT z.val_sql
   INTO lv_sql
   FROM cob_repor_adjun_segui_cobra z
   WHERE z.num_orde = 2;

   lv_sql := REPLACE(lv_sql,'$$1',v_etap_cobr.cod_usua_cobr);
   lv_sql := REPLACE(lv_sql,'$$2',v_etap_cobr.cod_etap_deud);

   lv_repo_name := 'Reporte_Seguimiento_Region.xls';

   BEGIN

    gen_pkg_xls.gen_pr_gener_file_excel_tclob(lv_sql,lv_repo_name,1000,lv_clob_adju);

   EXCEPTION
    WHEN OTHERS THEN
     lv_clob_adju := NULL;
   END;

   attachments.extend(1);
   attachments(2).attach_name := lv_repo_name;
   attachments(2).data_type := 'text/plain';
   attachments(2).attach_content := lv_clob_adju;

   SELECT z.val_sql
   INTO lv_sql
   FROM cob_repor_adjun_segui_cobra z
   WHERE z.num_orde = 3;

   lv_sql := REPLACE(lv_sql,'$$1',v_etap_cobr.cod_usua_cobr);
   lv_sql := REPLACE(lv_sql,'$$2',v_etap_cobr.cod_etap_deud);

   lv_repo_name := 'Reporte_Asignacion_Hoy.xls';

   BEGIN

    gen_pkg_xls.gen_pr_gener_file_excel_tclob(lv_sql,lv_repo_name,1000,lv_clob_adju);

   EXCEPTION
    WHEN OTHERS THEN
     lv_clob_adju := NULL;
   END;

   attachments.extend(1);
   attachments(3).attach_name := lv_repo_name;
   attachments(3).data_type := 'text/plain';
   attachments(3).attach_content := lv_clob_adju;

   SELECT z.val_sql
   INTO lv_sql
   FROM cob_repor_adjun_segui_cobra z
   WHERE z.num_orde = 4;

   lv_sql := REPLACE(lv_sql,'$$1',v_etap_cobr.cod_usua_cobr);
   lv_sql := REPLACE(lv_sql,'$$2',v_etap_cobr.cod_etap_deud);

   lv_repo_name := 'Reporte_Asignacion_Por_Dias.xls';

   BEGIN

    gen_pkg_xls.gen_pr_gener_file_excel_tclob(lv_sql,lv_repo_name,1000,lv_clob_adju);

   EXCEPTION

    WHEN OTHERS THEN
     lv_clob_adju := NULL;
   END;

   attachments.extend(1);
   attachments(4).attach_name := lv_repo_name;
   attachments(4).data_type := 'text/plain';
   attachments(4).attach_content := lv_clob_adju;

   lv_mail_orig := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('EmailModuloCorporativo');

   IF lv_mail_orig IS NULL THEN
    lv_mail_orig := 'ssicc_generacion_reportes@belcorp.biz';
   END IF;

   FIN_PKG_SEND_MAIL.SEND_MAIL(lv_mail_orig,
                            v_etap_cobr.val_mail,
                            lv_subj_mens,
                            lv_cuer_mens,
                            v_etap_cobr.val_mail_copi,
                            attachments,'text/html');

   lv_subj_mens := '';
   lv_cuer_mens := '';

  END LOOP;


 END COB_PR_GENER_INFOR_SEGUI_COBRA;

 PROCEDURE COB_PR_ENVIO_CARTE_AUTOM(
  p_fec_proc                     IN    VARCHAR2 DEFAULT NULL,
  p_cod_usua                     IN    VARCHAR2 DEFAULT USER)
 IS

  lv_num_lote                      NUMBER(12);
  lv_cod_erro                      VARCHAR2(100);
  lv_cod_pais                      ccc_param_gener.val_para%TYPE;
  lv_cod_soci                      ccc_param_gener.val_para%TYPE;

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  COB_PKG_PROCE.COB_PR_ACTUA_CARTE(lv_cod_pais,lv_cod_soci,USER);
  COB_PKG_PROCE.COB_PR_ASIGN_CARTE_AUTOM(USER,lv_num_lote,lv_cod_erro);
  COMMIT;

  INT_PKG_COB.INT_PR_COB_GENER_INFOR_COBRA(p_fec_proc,p_cod_usua);
  --COB_PKG_PROCE.COB_PR_GENER_INFOR_SEGUI_COBRA(p_fec_proc);

 END COB_PR_ENVIO_CARTE_AUTOM;

 PROCEDURE COB_PR_ENVIO_CARTE_CORPO(
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_num_lote                      NUMBER(12);
  lv_cod_erro                      VARCHAR2(100);
  lv_cod_pais                      ccc_param_gener.val_para%TYPE;
  lv_cod_soci                      ccc_param_gener.val_para%TYPE;

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  COB_PKG_PROCE.COB_PR_ACTUA_CARTE(lv_cod_pais,lv_cod_soci,p_cod_usua);
  COB_PKG_PROCE.COB_PR_ASIGN_CARTE_AUTOM(p_cod_usua,lv_num_lote,lv_cod_erro);
  COMMIT;

  INT_PKG_COB.INT_PR_COB_GENER_INFCO_CORPO;

 END COB_PR_ENVIO_CARTE_CORPO;

 PROCEDURE COB_PR_ENVIO_SALDO_AUTOM
 IS

  lv_cod_pais                      ccc_param_gener.val_para%TYPE;
  lv_cod_soci                      ccc_param_gener.val_para%TYPE;

  CURSOR c_cobr_no_corp
  IS
   SELECT u.cod_usua_cobr
   FROM cob_usuar_cobra_pais u
   WHERE u.ind_inte_corp = 0
     AND u.ind_supe = 0
     AND u.ind_acti = 1;

  CURSOR c_cobr_corp
  IS
  SELECT u.cod_usua_cobr
  FROM cob_usuar_cobra_pais u
  WHERE u.ind_inte_corp = 1
  AND  u.ind_supe = 0
  AND u.ind_acti = 1;

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  COB_PKG_PROCE.COB_PR_ACTUA_CARTE(lv_cod_pais,lv_cod_soci,USER);
  COMMIT;

  FOR v_cobr_no_corp IN c_cobr_no_corp LOOP

   INT_PKG_COB.INT_PR_COB_GENER_INFOR_SALDO(
    v_cobr_no_corp.cod_usua_cobr,TO_CHAR(SYSDATE,'DD/MM/YYYY'),'MAKI');
  END LOOP;


  FOR v_cobr_corp IN c_cobr_corp LOOP

   INT_PKG_COB.INT_PR_COB_GENER_SALDO_COBRA(
       v_cobr_corp.cod_usua_cobr,TO_CHAR(SYSDATE,'DD/MM/YYYY'),'MAKI');
  END LOOP;

 END COB_PR_ENVIO_SALDO_AUTOM;

 PROCEDURE COB_PR_PROCE_DIARI
 IS

  lv_sql            VARCHAR2(250);


 CURSOR c_prog
 IS
 SELECT c.des_prog
 FROM cob_proce_diari c
 WHERE c.ind_acti = 1
 ORDER BY c.num_orde ASC;

 BEGIN

  FOR v_prog IN c_prog LOOP
   lv_sql := 'BEGIN ' || v_prog.des_prog || '; END;';
   EXECUTE IMMEDIATE lv_sql;
   COMMIT;
  END LOOP;

 END COB_PR_PROCE_DIARI;

 PROCEDURE COB_PR_GENER_CRONO_CARTE_AUTOM(
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_ante                 seg_perio_corpo.cod_peri%TYPE;
  lv_cod_erro                      VARCHAR2(4000);
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;
  lv_cant_vali                     NUMBER(12);

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := lv_cod_proc_gene_cron_auto;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cronograma de Cartera Automatico';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Determinando Campaña Actual --
  SELECT bcf.cod_peri
  INTO lv_cod_peri
  FROM bas_ctrl_fact bcf
  WHERE bcf.ind_camp_act = 1
   AND bcf.sta_camp = 0;

  gv_des_log:='Campaña Actual '  || lv_cod_peri ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  lv_cod_peri_ante := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);

  gv_des_log:='Campaña Anterior '  || lv_cod_peri_ante ;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  -- Validando si ya fue generado --
  SELECT COUNT(*)
  INTO lv_cant_vali
  FROM cob_crono_carte c
  WHERE c.cod_peri = lv_cod_peri_ante;

  IF lv_cant_vali = 0 THEN

   gv_des_log:='Incio Campaña de Generación : ' || lv_cod_peri_ante;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   COB_PR_GENER_CRONO_CARTE_CAMPA(lv_cod_peri_ante,p_cod_usua);

   gv_des_log:='Fin Generando Cronograma de Cartera';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='Fin del Proceso';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  ELSE

   gv_des_log:='No hay cronograma pendiente por generar';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='Fin del Proceso';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

  END IF;

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   gv_des_log:= ' *** Error ' || SQLERRM  ||
                ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                ' en el programa ' ||
                gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');

 END COB_PR_GENER_CRONO_CARTE_AUTOM;

 PROCEDURE COB_PR_GENER_CRONO_CARTE_CAMPA(
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE DEFAULT USER)
 IS

  lv_reg_cob_crono_carte         cob_crono_carte%ROWTYPE;
  lv_cuer_mens                     CLOB;
  lv_emai_orig                     ccc_proce_envio_email.val_emai_orig%TYPE;
  lv_emai_dest                     ccc_proce_envio_email.val_emai_dest%TYPE;
  lv_emai_copi                     ccc_proce_envio_email.val_copy%TYPE;
  lv_val_subj                      ccc_proce_envio_email.val_subj%TYPE;

  lv_cod_erro                      VARCHAR2(4000);
  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;
  lv_vali                          NUMBER(12):=0;
  e_no_exis_etap                   EXCEPTION;

 CURSOR c_para_asig
 IS
 SELECT
  a.cod_etap_deud,
  a.cod_regi,
  a.cod_zona,
  ed.val_edad_inic,
  ed.num_secu_etap,
  ed.num_dias_gest,
  ed.ind_gene_lune
 FROM
  cob_param_asign_zonas_cobra a,
  cob_etapa_deuda_pais ed
 WHERE ed.cod_etap_deud = a.cod_etap_deud
   AND ed.ind_gene_cron = 1
   AND a.ind_acti = 1
   AND (a.cod_etap_deud, a.cod_regi, a.cod_zona) NOT IN
    (SELECT c.cod_etap_deud,c.cod_regi,c.cod_zona
     FROM cob_crono_carte c
     WHERE c.cod_peri = p_cod_peri
       AND c.ind_gene_cart = 1
       AND c.cod_cart IS NOT NULL)
 GROUP BY 
  a.cod_etap_deud,
  a.cod_regi,
  a.cod_zona,
  ed.val_edad_inic,
  ed.num_secu_etap,
  ed.num_dias_gest,
  ed.ind_gene_lune 
 ORDER BY ed.num_secu_etap, cod_regi, cod_zona ASC;

 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := lv_cod_proc_gene_camp_auto;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Generacion Cronograma de Cartera Automatico';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  SELECT COUNT(*)
  INTO lv_vali 
  FROM cob_etapa_deuda_pais ed
  WHERE ed.ind_acti = 1
    AND ed.ind_gene_cron = 1;
  
  IF lv_vali = 0 THEN 
   RAISE e_no_exis_etap;
  END IF;  
    
  
  FOR v_para_asig IN c_para_asig LOOP

   gv_des_log:='  ------------------------------------';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='  Etapa ' || v_para_asig.cod_etap_deud;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='  Region ' || v_para_asig.cod_regi;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   gv_des_log:='  Zona ' || v_para_asig.cod_zona;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   DELETE FROM cob_crono_carte c
   WHERE c.cod_peri = p_cod_peri
     AND c.cod_etap_deud = v_para_asig.cod_etap_deud
     AND c.cod_regi = v_para_asig.cod_regi
     AND c.cod_zona =  v_para_asig.cod_zona;

   lv_reg_cob_crono_carte.cod_pais  := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
   lv_reg_cob_crono_carte.cod_soci  := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');
   lv_reg_cob_crono_carte.cod_peri  := p_cod_peri;
   lv_reg_cob_crono_carte.cod_etap_deud  := v_para_asig.cod_etap_deud;
   lv_reg_cob_crono_carte.cod_regi := v_para_asig.cod_regi;
   lv_reg_cob_crono_carte.cod_zona := v_para_asig.cod_zona;
   lv_reg_cob_crono_carte.ind_gene_cier  := 0;
   lv_reg_cob_crono_carte.cod_cart  := NULL;
   lv_reg_cob_crono_carte.usu_crea  := p_cod_usua;
   lv_reg_cob_crono_carte.fec_crea  := SYSDATE;
   lv_reg_cob_crono_carte.usu_modi  := p_cod_usua;
   lv_reg_cob_crono_carte.fec_modi  := SYSDATE;
   lv_reg_cob_crono_carte.fec_inic_fact := COB_PKG_PROCE.COB_FN_OBTIE_FECHA_FACTU_ZONAS(p_cod_peri,v_para_asig.cod_regi,v_para_asig.cod_zona);

   gv_des_log:='  Fecha Inicio Facturacion : ' || lv_reg_cob_crono_carte.fec_inic_fact;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

   IF lv_reg_cob_crono_carte.fec_inic_fact IS NOT NULL THEN

    lv_reg_cob_crono_carte.fec_gene_cart := COB_PKG_PROCE.COB_FN_OBTIE_FECHA_GENER_CARTE(v_para_asig.cod_etap_deud,p_cod_peri,v_para_asig.cod_regi,v_para_asig.cod_zona);

    gv_des_log:='  Fecha Generacion de Cartera : ' || lv_reg_cob_crono_carte.fec_gene_cart;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    lv_reg_cob_crono_carte.fec_inic_gest := lv_reg_cob_crono_carte.fec_gene_cart;
    lv_reg_cob_crono_carte.fec_cier := COB_PKG_PROCE.COB_FN_OBTIE_FECHA_CIERR_CARTE(v_para_asig.cod_etap_deud,p_cod_peri,v_para_asig.cod_regi,v_para_asig.cod_zona,lv_reg_cob_crono_carte.fec_gene_cart);

    gv_des_log:='  Fecha Cierre de Cartera : ' || lv_reg_cob_crono_carte.fec_cier;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

    lv_reg_cob_crono_carte.val_dias_plaz  := lv_reg_cob_crono_carte.fec_cier - lv_reg_cob_crono_carte.fec_gene_cart + 1;
   
    IF lv_reg_cob_crono_carte.fec_gene_cart = TRUNC(SYSDATE) THEN
     lv_reg_cob_crono_carte.ind_gene_cart := 3;
    ELSE
     lv_reg_cob_crono_carte.ind_gene_cart := 0;
    END IF;

    INSERT INTO cob_crono_carte VALUES lv_reg_cob_crono_carte;

   END IF;

   gv_des_log:='  ------------------------------------';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  END LOOP;

  lv_cuer_mens := lv_cuer_mens || '<html><body>';
  lv_cuer_mens := lv_cuer_mens || '<table style="text-align: left; width: 90%" border="0" cellpadding="20" cellspacing="0">';
  lv_cuer_mens := lv_cuer_mens || '<tr>';
  lv_cuer_mens := lv_cuer_mens || '<b><font color="03150E" face="Arial" size="4">Buenos dias</font></b>';
  lv_cuer_mens := lv_cuer_mens || '</tr>';
  lv_cuer_mens := lv_cuer_mens || '<tr><td><font color="#4188b2" face="Arial" size="4"><b>';
  lv_cuer_mens := lv_cuer_mens || '   Se le confirma que el cronograma de la campaña ' || p_cod_peri || ' ha sido generado correctamente';
  lv_cuer_mens := lv_cuer_mens || '</b></font></td></tr></table></body></html>';

  lv_val_subj := 'SSICC Cobranzas - Generacion Cronograma Cartera ' || p_cod_peri;

--  lv_emai_orig := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('');
  lv_emai_orig := 'lia.florencio@belcorp.biz';
  lv_emai_dest := 'jflorencio@belcorp.biz';

  FIN_PKG_SEND_MAIL.SEND_MAIL(lv_emai_orig,lv_emai_dest,lv_val_subj,lv_cuer_mens,lv_emai_copi,NULL,'text/html');
  
  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');
   
 EXCEPTION
 
  WHEN e_no_exis_etap THEN  
   gv_des_log:='No existen etapas por asignar';
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'9');
 END COB_PR_GENER_CRONO_CARTE_CAMPA;

 FUNCTION cob_fn_obtie_fecha_factu_zonas(
  p_cod_peri                   IN   cra_perio.oid_peri%TYPE,  
  p_cod_regi                   IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                   IN   zon_zona.cod_zona%TYPE)
 RETURN DATE
 IS

  lv_cod_pais                   seg_pais.cod_pais%TYPE;
  lv_oid_peri                   cra_perio.oid_peri%TYPE;  
  lv_cod_acti_fact              cra_activ.cod_acti%TYPE;
  lv_fec_fact                   DATE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri);
  lv_cod_acti_fact := NVL(COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoActividadFacturacion'),'FA');
    
  -- Si es a nivel de region busca la zona que factura mas tarde --
  IF p_cod_zona = 'T' THEN
 
   --dbms_output.put_line('Pais ' || lv_cod_pais);
   --dbms_output.put_line('Cod Acti ' || lv_cod_acti_fact);
   --dbms_output.put_line('Oid Peri ' ||lv_oid_peri);
   --dbms_output.put_line('Region ' || p_cod_regi);
   --dbms_output.put_line('Zona ' || p_cod_zona);
   
   SELECT MAX(cr.fec_inic)
   INTO lv_fec_fact
   FROM
    seg_pais sp,
    cra_crono cr,
    cra_activ ca,
    zon_zona zz,
    zon_regio zr
   WHERE cr.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND cr.cact_oid_acti = ca.oid_acti
     AND sp.oid_pais = ca.pais_oid_pais 
     AND sp.cod_pais = lv_cod_pais
     AND ca.cod_acti = lv_cod_acti_fact
     AND cr.perd_oid_peri = lv_oid_peri     
     AND zr.cod_regi = p_cod_regi;

  ELSE
   
   --dbms_output.put_line('Pais ' || lv_cod_pais);
   --dbms_output.put_line('Cod Acti ' || lv_cod_acti_fact);
   --dbms_output.put_line('Oid Peri ' ||lv_oid_peri);
   --dbms_output.put_line('Region ' || p_cod_regi);
   --dbms_output.put_line('Zona ' || p_cod_zona);
   
   -- Si es a nivel de zona busca especificamente la zona
   SELECT MAX(cr.fec_inic)
   INTO lv_fec_fact
   FROM
    seg_pais sp,
    cra_crono cr,
    cra_activ ca,
    zon_zona zz,
    zon_regio zr
   WHERE cr.zzon_oid_zona = zz.oid_zona
     AND zz.zorg_oid_regi = zr.oid_regi
     AND cr.cact_oid_acti = ca.oid_acti
     AND sp.oid_pais = ca.pais_oid_pais 
     AND sp.cod_pais = lv_cod_pais
     AND ca.cod_acti = lv_cod_acti_fact
     AND cr.perd_oid_peri = lv_oid_peri     
     AND zr.cod_regi = p_cod_regi
     AND zz.cod_zona = p_cod_zona;

  END IF;

  --dbms_output.put_line('Fecha Facturacion ' || lv_fec_fact);
  
  RETURN lv_fec_fact;

 EXCEPTION
 
  WHEN no_data_found THEN
   RETURN NULL;

  WHEN too_many_rows THEN
   RETURN NULL;

 END cob_fn_obtie_fecha_factu_zonas;

 FUNCTION COB_FN_OBTIE_FECHA_GENER_CARTE(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE
 IS
   
  lv_ind_etap_mini                NUMBER(1):=0;
  lv_fec_gene_cart                DATE;

 BEGIN

  -- Determinando Si Es la Primera Etapa de Deuda --
  lv_ind_etap_mini := cob_fn_valid_etapa_minim_deuda(p_cod_etap_deud);
 
  IF lv_ind_etap_mini = 1 THEN
  
   -- Asigando Etapa Inicial 
   -- dbms_output.put_line('Etapa Inicial ' || p_cod_etap_deud);   
   lv_fec_gene_cart := cob_fn_obtie_fecha_gener_etmin(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona);
   
  ELSE

   -- Asigando Etapa Normal 
   -- dbms_output.put_line('Etapa Normal ' || p_cod_etap_deud);
   lv_fec_gene_cart := cob_fn_obtie_fecha_gener_etnor(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona);
   
  END IF;
  
  lv_fec_gene_cart := cob_fn_obtie_fecha_gener_excep(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,lv_fec_gene_cart);
  
  --dbms_output.put_line('***   Fecha Generacion Cartera Excepcion ' || lv_fec_gene_cart);
  
  RETURN lv_fec_gene_cart;

 END COB_FN_OBTIE_FECHA_GENER_CARTE;

 FUNCTION cob_fn_valid_etapa_minim_deuda(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE)
 RETURN NUMBER
 IS
 
  lv_num_secu_etap_mini          cob_etapa_deuda_pais.num_secu_etap%TYPE;
  lv_cod_etap_deud_mini          cob_etapa_deuda_pais.cod_etap_deud%TYPE;
  lv_resu                        NUMBER(1):=0;
  
 BEGIN
   
  SELECT MIN(ed.num_secu_etap)
  INTO lv_num_secu_etap_mini
  FROM cob_etapa_deuda_pais ed
  WHERE ed.ind_gene_cron = 1;

  SELECT ed.cod_etap_deud
  INTO lv_cod_etap_deud_mini
  FROM cob_etapa_deuda_pais ed
  WHERE ed.num_secu_etap = lv_num_secu_etap_mini
    AND ed.ind_gene_cron = 1;
    
  IF p_cod_etap_deud = lv_cod_etap_deud_mini THEN
   lv_resu := 1;
  ELSE
   lv_resu := 0;  
  END IF;   
     
 RETURN lv_resu;
    
 END cob_fn_valid_etapa_minim_deuda;
 
 FUNCTION cob_fn_obtie_fecha_gener_etmin(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE  
 IS
 
  lv_val_edad_inic               cob_etapa_deuda_pais.val_edad_inic%TYPE;
  lv_fec_fact                    DATE;
  lv_fec_gene_cart               DATE;
  
 BEGIN
 
  SELECT ed.val_edad_inic
  INTO lv_val_edad_inic
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  lv_fec_fact := COB_FN_OBTIE_FECHA_FACTU_ZONAS(p_cod_peri,p_cod_regi,p_cod_zona);
  
  --dbms_output.put_line(' ***   Fecha Facturacion : ' || lv_fec_fact);
  --dbms_output.put_line(' ***   Edad Inicial : ' || lv_val_edad_inic);
      
  lv_fec_gene_cart := lv_fec_fact + lv_val_edad_inic;  
  
  --dbms_output.put_line(' ***   Fecha Generacion de Cartera : ' || lv_fec_gene_cart);
  
 RETURN lv_fec_gene_cart;
 
 END cob_fn_obtie_fecha_gener_etmin;
 
 FUNCTION cob_fn_obtie_fecha_gener_etnor(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE  
 IS
 
  
  lv_val_edad_inic               cob_etapa_deuda_pais.val_edad_inic%TYPE;
  lv_fec_cier_etap_ante          DATE;
  lv_fec_fact                    DATE;
  lv_fec_gene_cart               DATE;
  
 BEGIN
    
  lv_fec_cier_etap_ante := cob_fn_obtie_fecha_cierr_anter(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona);        
     
  IF lv_fec_cier_etap_ante IS NOT NULL THEN
   lv_fec_gene_cart := lv_fec_cier_etap_ante + 1;
  
  ELSE
    
   lv_fec_fact := COB_FN_OBTIE_FECHA_FACTU_ZONAS(p_cod_peri,p_cod_regi,p_cod_zona);       
   
   SELECT ed.val_edad_inic
   INTO lv_val_edad_inic
   FROM cob_etapa_deuda_pais ed
   WHERE ed.cod_etap_deud = p_cod_etap_deud;
   
   lv_fec_gene_cart := lv_fec_fact + lv_val_edad_inic;
   
  END IF;
   
  RETURN lv_fec_gene_cart;                                      
 END cob_fn_obtie_fecha_gener_etnor;
 
 FUNCTION cob_fn_obtie_fecha_cierr_anter(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE)
 RETURN DATE  
 IS
 
  lv_num_secu_etap               cob_etapa_deuda_pais.num_secu_etap%TYPE;
  lv_num_secu_etap_ante          cob_etapa_deuda_pais.num_secu_etap%TYPE;
  lv_val_edad_inic               cob_etapa_deuda_pais.val_edad_inic%TYPE;
  lv_fec_cier_etap_ante          DATE;
  
 BEGIN
 
  SELECT ed.num_secu_etap, ed.val_edad_inic
  INTO lv_num_secu_etap, lv_val_edad_inic
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  -- No es la primera Etapa --
  SELECT MAX(ed.num_secu_etap)
  INTO lv_num_secu_etap_ante
  FROM cob_etapa_deuda_pais ed
  WHERE ed.num_secu_etap < lv_num_secu_etap
    AND ed.ind_gene_cron = 1;
 
  -- Buscando el cierre en la etapa anterior al mismo nivel --
  SELECT MAX(c.fec_cier)
  INTO lv_fec_cier_etap_ante
  FROM
   cob_crono_carte c,
   cob_etapa_deuda_pais ed
   WHERE c.cod_etap_deud = ed.cod_etap_deud
     AND ed.num_secu_etap = lv_num_secu_etap_ante
     AND c.cod_peri = p_cod_peri
     AND c.cod_regi = p_cod_regi
     AND c.cod_zona = p_cod_zona
     AND ed.ind_gene_cron = 1;
 
  -- Si el resultado es null se busca a un nivel mas alto --
  IF lv_fec_cier_etap_ante IS NULL THEN
   
   SELECT MAX(c.fec_cier)
   INTO lv_fec_cier_etap_ante
   FROM
    cob_crono_carte c,
    cob_etapa_deuda_pais ed
    WHERE c.cod_etap_deud = ed.cod_etap_deud
      AND ed.num_secu_etap = lv_num_secu_etap_ante
       AND c.cod_peri = p_cod_peri
       AND c.cod_regi = p_cod_regi
       AND ed.ind_gene_cron = 1;

  END IF;
             
 RETURN lv_fec_cier_etap_ante;
 
 END cob_fn_obtie_fecha_cierr_anter;
 
 FUNCTION cob_fn_obtie_fecha_gener_excep(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                IN   DATE)
 RETURN DATE  
 IS
 
  lv_fec_gene_cart               DATE;
  lv_ind_gene_nolu               NUMBER(1);
  lv_vali                        NUMBER(2);
  
 BEGIN 
  
  EXECUTE IMMEDIATE 'alter session set nls_territory=SPAIN';
  
  lv_fec_gene_cart := p_fec_gene_cart;
     
  SELECT COUNT(*)
  INTO lv_vali  
  FROM cob_crono_carte_excep ex
  WHERE ex.cod_etap_deud = p_cod_etap_deud
    AND ex.cod_peri = p_cod_peri
    AND ex.cod_regi = p_cod_regi
    AND ex.cod_zona = p_cod_zona
    AND ex.tip_exce = 'GEN'
    AND ex.ind_acti = 1;
  
  IF lv_vali > 0 THEN
  
   SELECT ex.fec_exce
   INTO lv_fec_gene_cart  
   FROM cob_crono_carte_excep ex
   WHERE ex.cod_etap_deud = p_cod_etap_deud
     AND ex.cod_peri = p_cod_peri
     AND ex.cod_regi = p_cod_regi
     AND ex.cod_zona = p_cod_zona
     AND ex.tip_exce = 'GEN'
     AND ex.ind_acti = 1;
    
  END IF;
        
  -- Validacion cuando la fecha de generacion es sábado
  IF TO_CHAR(lv_fec_gene_cart,'d') = 6 THEN
   lv_fec_gene_cart := lv_fec_gene_cart + 2;
  END IF;
  
  -- Validacion cuando la fecha de generacion es domingo
  IF TO_CHAR(lv_fec_gene_cart,'d') = 7 THEN
   lv_fec_gene_cart := lv_fec_gene_cart + 1;
  END IF;
  
  -- Validacion la Fecha de Generacion Lunes
  SELECT ed.ind_fech_gene_nolu
  INTO lv_ind_gene_nolu
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  IF lv_ind_gene_nolu = 1 THEN
   IF TO_CHAR(lv_fec_gene_cart,'d') = 1 THEN
    lv_fec_gene_cart := lv_fec_gene_cart + 1;
   END IF;
  END IF;
  
 RETURN lv_fec_gene_cart;
  
 END cob_fn_obtie_fecha_gener_excep;
 
 FUNCTION cob_fn_obtie_fecha_cierr_excep(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_cier_cart                IN   DATE)
 RETURN DATE  
 IS
 
   lv_fec_cier_cart               DATE;
   lv_ind_cier_nolu               NUMBER(1);
   lv_vali                        NUMBER(2);
 
 BEGIN
  
  EXECUTE IMMEDIATE 'alter session set nls_territory=SPAIN';
  
  lv_fec_cier_cart := p_fec_cier_cart;
  
  SELECT COUNT(*)
  INTO lv_vali  
  FROM cob_crono_carte_excep ex
  WHERE ex.cod_etap_deud = p_cod_etap_deud
    AND ex.cod_peri = p_cod_peri
    AND ex.cod_regi = p_cod_regi
    AND ex.cod_zona = p_cod_zona
    AND ex.tip_exce = 'CIE'
    AND ex.ind_acti = 1;
  
  IF lv_vali > 0 THEN
  
   SELECT ex.fec_exce
   INTO lv_fec_cier_cart  
   FROM cob_crono_carte_excep ex
   WHERE ex.cod_etap_deud = p_cod_etap_deud
     AND ex.cod_peri = p_cod_peri
     AND ex.cod_regi = p_cod_regi
     AND ex.cod_zona = p_cod_zona
     AND ex.tip_exce = 'CIE'
     AND ex.ind_acti = 1;
    
  END IF;
     
  -- Validacion cuando la fecha de generacion es sábado
  IF TO_CHAR(lv_fec_cier_cart,'d') = 6 THEN
   lv_fec_cier_cart := lv_fec_cier_cart + 2;
  END IF;

  -- Validacion cuando la fecha de generacion es domingo
  IF TO_CHAR(lv_fec_cier_cart,'d') = 7 THEN
   lv_fec_cier_cart := lv_fec_cier_cart + 1;
  END IF;

  -- Validacion la Fecha de Generacion Lunes
  SELECT ed.ind_fech_cier_nolu
  INTO lv_ind_cier_nolu
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;
  
  IF lv_ind_cier_nolu = 1 THEN
   IF TO_CHAR(lv_fec_cier_cart,'d') = 1 THEN
    lv_fec_cier_cart := lv_fec_cier_cart + 1;
   END IF;
  END IF;
    
 RETURN lv_fec_cier_cart;
  
 END cob_fn_obtie_fecha_cierr_excep;
   
 FUNCTION COB_FN_OBTIE_FECHA_CIERR_CARTE(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_fec_gene_cart                IN   DATE)
 RETURN DATE
 IS

  lv_num_dias_gest               cob_etapa_deuda_pais.num_dias_gest%TYPE;
  lv_fec_cier                    DATE;
 BEGIN

  SELECT ed.num_dias_gest
  INTO lv_num_dias_gest
  FROM cob_etapa_deuda_pais ed
  WHERE ed.cod_etap_deud = p_cod_etap_deud;

  lv_fec_cier := p_fec_gene_cart + lv_num_dias_gest;

  --dbms_output.put_line('Fecha Cierre PRE ' || lv_fec_cier);
  
  lv_fec_cier := cob_fn_obtie_fecha_cierr_excep(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,lv_fec_cier);

  --dbms_output.put_line('Fecha Cierre POST ' || lv_fec_cier);
  
  RETURN lv_fec_cier;

 END COB_FN_OBTIE_FECHA_CIERR_CARTE;
 
 PROCEDURE COB_PR_ACTUA_CRONO_CARTE(
  p_cod_etap_deud                IN   cob_etapa_deuda_pais.cod_etap_deud%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_regi                     IN   zon_regio.cod_regi%TYPE,
  p_cod_zona                     IN   zon_zona.cod_zona%TYPE,
  p_tipo_exce                    IN   VARCHAR2,
  p_fec_exce                     IN   DATE,
  p_cod_usua                     IN   seg_usuar.use_usua%TYPE)
 IS
 
  lv_cod_pais                    seg_pais.cod_pais%TYPE;
  lv_cod_soci                    seg_socie.cod_soci%TYPE;     
  lv_reg_cob_crono_carte_excep   cob_crono_carte_excep%ROWTYPE;
  lv_fec_gene_cart               DATE;
  lv_fec_cier_cart               DATE;
  
 CURSOR c_etap_sigu(
  pc_cod_etap_deud   VARCHAR2,
  pc_cod_peri        VARCHAR2,
  pc_cod_regi        VARCHAR2,
  pc_cod_zona        VARCHAR2)
 IS
 SELECT
  c.cod_etap_deud,
  c.cod_peri,
  c.cod_regi,
  c.cod_zona 
 FROM 
  cob_etapa_deuda_pais ed,
  cob_crono_carte c
 WHERE c.cod_etap_deud = ed.cod_etap_deud 
   AND c.cod_peri = pc_cod_peri
   AND c.cod_regi = pc_cod_regi
   AND c.cod_zona = pc_cod_zona 
   AND ed.ind_gene_cron = 1 
   AND ed.num_secu_etap > (
    SELECT eda.num_secu_etap
    FROM cob_etapa_deuda_pais eda
    WHERE eda.cod_etap_deud = pc_cod_etap_deud)
  ORDER BY ed.num_secu_etap ASC;
  
 BEGIN
 
  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');
      
  lv_reg_cob_crono_carte_excep.cod_pais := lv_cod_pais;
  lv_reg_cob_crono_carte_excep.cod_soci := lv_cod_soci;
  lv_reg_cob_crono_carte_excep.cod_etap_deud := p_cod_etap_deud;
  lv_reg_cob_crono_carte_excep.cod_peri := p_cod_peri;
  lv_reg_cob_crono_carte_excep.cod_regi := p_cod_regi;
  lv_reg_cob_crono_carte_excep.cod_zona := p_cod_zona;
  lv_reg_cob_crono_carte_excep.tip_exce := p_tipo_exce;
  lv_reg_cob_crono_carte_excep.fec_exce := p_fec_exce;
  lv_reg_cob_crono_carte_excep.usu_crea := p_cod_usua;
  lv_reg_cob_crono_carte_excep.fec_crea := SYSDATE;
  lv_reg_cob_crono_carte_excep.usu_modi := p_cod_usua;
  lv_reg_cob_crono_carte_excep.fec_modi := SYSDATE; 
  lv_reg_cob_crono_carte_excep.Ind_acti := 1; 

  UPDATE cob_crono_carte_excep e
  SET e.ind_acti = 0
  WHERE e.cod_peri = p_cod_peri
    AND e.cod_etap_deud = p_cod_etap_deud
    AND e.cod_regi = p_cod_regi
    AND e.cod_zona = p_cod_zona
    AND e.tip_exce = p_tipo_exce
    AND e.ind_acti = 1;
 
  INSERT INTO cob_crono_carte_excep VALUES lv_reg_cob_crono_carte_excep;   
  
  IF p_tipo_exce = 'GEN' THEN
   
   UPDATE cob_crono_carte c
   SET 
    c.fec_gene_cart = p_fec_exce,
    c.fec_cier = COB_FN_OBTIE_FECHA_CIERR_CARTE(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona,p_fec_exce)
   WHERE c.cod_etap_deud = p_cod_etap_deud
     AND c.cod_peri = p_cod_peri
     AND c.cod_regi = p_cod_regi
     AND c.cod_zona = p_cod_zona; 
           
  END IF;

  IF p_tipo_exce = 'CIE' THEN
   
   UPDATE cob_crono_carte c
   SET 
    c.fec_cier = p_fec_exce
   WHERE c.cod_etap_deud = p_cod_etap_deud
     AND c.cod_peri = p_cod_peri
     AND c.cod_regi = p_cod_regi
     AND c.cod_zona = p_cod_zona; 
   
   UPDATE cob_detal_asign_carte d
   SET
    d.fec_cier = p_fec_exce
   WHERE d.cod_etap_deud = p_cod_etap_deud
     AND d.cod_peri = p_cod_peri
     AND d.cod_regi = p_cod_regi
     AND d.cod_zona = p_cod_zona;
  
   UPDATE cob_detal_asign_carte dm
   SET
    dm.fec_cier = p_fec_exce
   WHERE dm.cod_etap_deud = p_cod_etap_deud
     AND dm.cod_peri = p_cod_peri
     AND dm.cod_regi = p_cod_regi
     AND dm.cod_zona = p_cod_zona;
                  
  END IF;
 
  COMMIT;
 
  -- Regenerar Etapas Siguientes --
  --dbms_output.put_line('Regenerar Etapas Siguientes'); 
 
  FOR v_etap_sigu in c_etap_sigu(p_cod_etap_deud,p_cod_peri,p_cod_regi,p_cod_zona) LOOP
 
   --dbms_output.put_line(v_etap_sigu.cod_etap_deud);
   --dbms_output.put_line(v_etap_sigu.cod_peri);
   --dbms_output.put_line(v_etap_sigu.cod_regi);
   --dbms_output.put_line(v_etap_sigu.cod_zona);
    
   lv_fec_gene_cart := COB_FN_OBTIE_FECHA_GENER_CARTE(
                      v_etap_sigu.cod_etap_deud,
                      v_etap_sigu.cod_peri,
                      v_etap_sigu.cod_regi,
                      v_etap_sigu.cod_zona);
  
   --dbms_output.put_line('FG :' || lv_fec_gene_cart);
                      
   lv_fec_cier_cart := COB_FN_OBTIE_FECHA_CIERR_CARTE(
                      v_etap_sigu.cod_etap_deud,
                      v_etap_sigu.cod_peri,
                      v_etap_sigu.cod_regi,
                      v_etap_sigu.cod_zona,
                      lv_fec_gene_cart);                     

  --dbms_output.put_line('FC :' || lv_fec_cier_cart);
                            
   UPDATE cob_crono_carte c
   SET 
    c.fec_gene_cart = lv_fec_gene_cart,
    c.fec_cier = lv_fec_cier_cart   
   WHERE c.cod_etap_deud = v_etap_sigu.cod_etap_deud 
     AND c.cod_peri = v_etap_sigu.cod_peri
     AND c.cod_regi = v_etap_sigu.cod_regi
     AND c.cod_zona = v_etap_sigu.cod_zona;  
     
  END LOOP;
    
 END COB_PR_ACTUA_CRONO_CARTE;
 
 PROCEDURE COB_PR_VALID_MODIF_CRONO_MASIV(
  p_num_lote                     IN   cob_carga_modif_crono_carte.num_lote%TYPE,
  p_ind_erro                     OUT   NUMBER)       
 IS
 
  lv_vali                        NUMBER(5);
  
 BEGIN
   
  UPDATE cob_carga_modif_crono_carte c
  SET c.cod_erro = '01',
      c.val_mens_erro = 'Campaña No Existe' 
  WHERE c.num_lote = p_num_lote
    AND NOT EXISTS (
     SELECT NULL
     FROM cob_crono_carte cr
     WHERE cr.cod_peri = c.cod_peri);
  
  UPDATE cob_carga_modif_crono_carte c
  SET c.cod_erro = '02',
      c.val_mens_erro = 'Etapa de Deuda No Existe' 
  WHERE c.num_lote = p_num_lote
    AND NOT EXISTS (
     SELECT NULL
     FROM cob_crono_carte cr
     WHERE cr.cod_peri = c.cod_peri
       AND cr.cod_etap_deud = c.cod_etap_deud);
 
  UPDATE cob_carga_modif_crono_carte c
  SET c.cod_erro = '03',
      c.val_mens_erro = 'Region No Existe' 
  WHERE c.num_lote = p_num_lote
    AND NOT EXISTS (
     SELECT NULL
     FROM cob_crono_carte cr
     WHERE cr.cod_peri = c.cod_peri
       AND cr.cod_etap_deud = c.cod_etap_deud
       AND cr.cod_regi = c.cod_regi);
  
  UPDATE cob_carga_modif_crono_carte c
  SET c.cod_erro = '04',
      c.val_mens_erro = 'Zona No Existe' 
  WHERE c.num_lote = p_num_lote
    AND NOT EXISTS (
     SELECT NULL
     FROM cob_crono_carte cr
     WHERE cr.cod_peri = c.cod_peri
       AND cr.cod_etap_deud = c.cod_etap_deud
       AND cr.cod_regi = c.cod_regi
       AND cr.cod_zona = c.cod_zona);
  
  UPDATE cob_carga_modif_crono_carte c
  SET c.cod_erro = '05',
      c.val_mens_erro = 'Tipo de Cierre No Existe' 
  WHERE c.num_lote = p_num_lote
    AND c.tip_exce NOT IN ('GEN','CIE');
       
  SELECT COUNT(*)
  INTO lv_vali
  FROM cob_carga_modif_crono_carte c
  WHERE c.num_lote = p_num_lote
    AND c.cod_erro IS NOT NULL;
 
  IF lv_vali > 0 THEN  
   p_ind_erro := 1;
  ELSE
   p_ind_erro := 0;     
  END IF;
  
 END COB_PR_VALID_MODIF_CRONO_MASIV;
 
 PROCEDURE COB_PR_PROCE_MODIF_CRONO_MASIV(
  p_num_lote                     IN   cob_carga_modif_crono_carte.num_lote%TYPE)
 IS
 
 CURSOR c_cron_cart
 IS
  SELECT
   c.cod_peri,
   c.cod_etap_deud,
   c.cod_regi,
   c.cod_zona,
   c.tip_exce,
   c.fec_exce,
   c.cod_usua
  FROM cob_carga_modif_crono_carte c
  WHERE c.num_lote = p_num_lote;
   
 BEGIN
 
  FOR v_cron_cart IN c_cron_cart LOOP
    
   COB_PR_ACTUA_CRONO_CARTE(
    v_cron_cart.cod_etap_deud,
    v_cron_cart.cod_peri,
    v_cron_cart.cod_regi,
    v_cron_cart.Cod_zona,
    v_cron_cart.tip_exce,
    TO_DATE(v_cron_cart.fec_exce,'DD/MM/YYYY'),
    v_cron_cart.cod_usua);
    
  END LOOP;
  
  INSERT INTO cob_histo_modif_crono_carte
   SELECT *
   FROM cob_carga_modif_crono_carte c
   WHERE c.num_lote = p_num_lote; 
 
  DELETE FROM cob_carga_modif_crono_carte c
  WHERE c.num_lote = p_num_lote; 
      
 END COB_PR_PROCE_MODIF_CRONO_MASIV;
         
 FUNCTION cob_pr_gener_fila_tabla_html(
  p_valo                           IN   VARCHAR2,
  p_anch                           IN   VARCHAR2,
  P_font                           IN   NUMBER DEFAULT 1,
  p_size                           IN   NUMBER DEFAULT 2,
  p_ind_cent                       IN   NUMBER DEFAULT 0,
  p_ind_negr                       IN   NUMBER DEFAULT 0)
 RETURN VARCHAR2
 IS

  lv_resu                          VARCHAR2(250);
  lv_font                          VARCHAR2(20);

 BEGIN

  CASE
   WHEN p_font = 1 THEN lv_font := 'Arial';
   WHEN p_font = 2 THEN lv_font := 'Verdana';
  END CASE;

  SELECT '<td width="' || p_anch || '%">' ||
         '<font face="' || lv_font || '" size="' || p_size  || '">' ||
         DECODE(p_ind_cent,1,'<center>','') || DECODE(p_ind_negr,1,'<b>','') ||
         p_valo ||
         DECODE(p_ind_negr,1,'</b>','') || DECODE(p_ind_cent,1,'</center>','') ||
         '</font></td>'
  INTO  lv_resu
  FROM DUAL;

  RETURN lv_resu;

 END cob_pr_gener_fila_tabla_html;

/******************************************************************************
  Descripcion             : COB_PR_GENER_REPOR_CREDT
                                   Proceso que genera la data para el Reporte Histórico de Operación Crediticia
  Fecha Creacion      : 10/02/2014
  Parametros Entrada:
                          psMesAnnio  : mes y año del proceso
                          psFechaInicioMesAnnio: primer dia del mes del año
                          psFechaFinMesAnnio: ultimo dia del mes del año
  Autor             : Sebastian Guerra
 *******************************************************************************/
  PROCEDURE COB_PR_GENER_REPOR_CREDT(
      psMesAnnio                          VARCHAR2,
      psFechaInicioMesAnnio        VARCHAR2,
      psFechaFinMesAnnio            VARCHAR2
      )
  IS

  lv_oid_tipo_soli_pais   ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  valAcreedor                varchar2(50);
  lv_cod_unid_geog       varchar2(54);
  orde1                          varchar2(6);
  orde2                          varchar2(6);
  orde3                          varchar2(6);
  ln_dia_mora                number(8);

  CURSOR cur_base(lnOidTipoSoliPais NUMBER) IS

  SELECT   mcc.clie_oid_clie, ped.fec_fact, mcc.fec_venc, ped.oid_soli_cabe,
           ped.val_nume_soli, SUM (mcc.imp_movi) imp_movi, SUM (mcc.imp_pend) imp_pend
      FROM ccc_movim_cuent_corri mcc, ped_solic_cabec ped
     WHERE ped.perd_oid_peri = mcc.perd_oid_peri
       AND ped.clie_oid_clie = mcc.clie_oid_clie
       AND ped.oid_soli_cabe = mcc.soca_oid_soli_cabe
       AND ped.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais
       AND ped.grpr_oid_grup_proc = 5
       AND TO_CHAR (ped.fec_fact, 'mmyyyy') = psMesAnnio
  GROUP BY mcc.clie_oid_clie,
           ped.fec_fact,
           mcc.fec_venc,
           ped.oid_soli_cabe,
           mcc.tspa_oid_tipo_soli_pais,
           val_nume_soli
  UNION
  SELECT   mcc.clie_oid_clie, ped.fec_fact, mcc.fec_venc, ped.oid_soli_cabe,
           ped.val_nume_soli, SUM (mcc.imp_movi) imp_movi, SUM (mcc.imp_pend) imp_pend
      FROM ccc_movim_cuent_corri mcc, ped_solic_cabec ped
     WHERE ped.perd_oid_peri = mcc.perd_oid_peri
       AND ped.clie_oid_clie = mcc.clie_oid_clie
       AND ped.oid_soli_cabe = mcc.soca_oid_soli_cabe
       AND ped.tspa_oid_tipo_soli_pais = lnOidTipoSoliPais
       AND ped.grpr_oid_grup_proc = 5
       AND mcc.imp_pend > 0
       AND TRUNC (ped.fec_fact) >= TO_DATE ('01/01/2010', 'dd/mm/yyyy')
       AND TRUNC (ped.fec_fact) < TO_DATE (psFechaInicioMesAnnio, 'dd/mm/yyyy')
  GROUP BY mcc.clie_oid_clie,
           ped.fec_fact,
           mcc.fec_venc,
           ped.oid_soli_cabe,
           mcc.tspa_oid_tipo_soli_pais,
           val_nume_soli;

  TYPE rec_base IS RECORD(
      clie_oid_clie           ccc_movim_cuent_corri.clie_oid_clie%TYPE,
      fec_fact                ccc_movim_cuent_corri.fec_docu%TYPE,
      fec_venc               ccc_movim_cuent_corri.fec_venc%TYPE,
      oid_soli_cabe        ccc_movim_cuent_corri.soca_oid_soli_cabe%TYPE,
      val_nume_soli       ped_solic_cabec.val_nume_soli%TYPE,
      imp_movi               ccc_movim_cuent_corri.imp_movi%TYPE,
      imp_pend              ccc_movim_cuent_corri.imp_pend%TYPE
  );

  TYPE r_base  IS TABLE OF rec_base;
  record_base r_base;

  BEGIN
    lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais ('C1');
    valAcreedor              := 'SR00127';

    DELETE FROM cob_tmp_histo_opera_credi;

    OPEN cur_base(lv_oid_tipo_soli_pais);
    LOOP
       FETCH cur_base BULK COLLECT INTO record_base LIMIT W_FILAS;
       IF record_base.COUNT > 0 THEN
          FOR x IN record_base.FIRST .. record_base.LAST LOOP

            SELECT md.cod_unid_geog
              INTO lv_cod_unid_geog
              FROM mae_clien_direc md
             WHERE md.clie_oid_clie = record_base (x).clie_oid_clie
               AND ind_elim = 0
               AND ind_dire_ppal = 1;

            SELECT DISTINCT geo.orde_1
              INTO orde1
              FROM zon_valor_estru_geopo geo
             WHERE geo.orde_1 = SUBSTR (lv_cod_unid_geog, 1, 6)
               AND geo.orde_2 IS NULL
               AND geo.orde_3 IS NULL;

            SELECT DISTINCT geo.orde_2
              INTO orde2
              FROM zon_valor_estru_geopo geo
             WHERE geo.orde_1 = SUBSTR (lv_cod_unid_geog, 1, 6)
               AND geo.orde_2 = SUBSTR (lv_cod_unid_geog, 7, 6)
               AND geo.orde_3 IS NULL;

            SELECT DISTINCT geo.orde_3
              INTO orde3
              FROM zon_valor_estru_geopo geo
             WHERE geo.orde_1 = SUBSTR (lv_cod_unid_geog, 1, 6)
               AND geo.orde_2 = SUBSTR (lv_cod_unid_geog, 7, 6)
               AND geo.orde_3 = SUBSTR (lv_cod_unid_geog, 13, 6);

            SELECT TRUNC (SYSDATE) -
                         record_base (x).fec_fact -
                         (SELECT TO_NUMBER (a.val_para) FROM ccc_param_gener a WHERE a.cod_para = 'BURO_02')
             INTO ln_dia_mora
            FROM DUAL;

            IF record_base(x).imp_pend < 1 THEN
               ln_dia_mora := 0;
            END IF;

            IF ln_dia_mora < 1 THEN
               ln_dia_mora := 0;
            END IF;

            INSERT INTO cob_tmp_histo_opera_credi(
              clie_oid_clie,
              oid_soli_cabe,
              val_acre,
              fec_dato,
              tipo_suje,
              val_cedu,
              nom_suje,
              cod_clase,
              orde_1,
              orde_2,
              orde_3,
              val_sexo,
              ind_esta_civi,
              val_orig_ingr,
              val_nume_soli,
              imp_movi,
              imp_pend,
              fec_fact,
              fec_venc,
              fec_exig,
              val_plaz_oper,
              dias_pago,
              dias_moro,
              val_mnt_mora,
              val_mnt_inte_mora,
              val_xven_di30,
              val_xven_di90,
              val_xven_d180,
              val_xven_d360,
              val_xven_mayo_d360,
              val_venc_di30,
              val_venc_di90,
              val_venc_di180,
              val_venc_di360,
              val_venc_mayo_d360,
              val_dem_judi,
              val_cart_cast,
              num_cuot_cred,
              fec_canc,
              for_canc,
              cod_unid_geog)
            VALUES(
              record_base(x).clie_oid_clie,
              record_base(x).oid_soli_cabe,
              valAcreedor, --Codigo de identidad
              TO_DATE(psFechaFinMesAnnio, 'dd/mm/yyyy'), --Fecha de datos
              (SELECT (CASE mtd.val_sigl
                                 WHEN 'CCI' THEN 'C'
                                 WHEN 'DCZ' THEN 'C'
                                 WHEN 'RUC' THEN 'R'
                                 ELSE ''
                             END)
                FROM mae_clien_ident mcd, mae_clien mc, mae_tipo_docum mtd
               WHERE mcd.clie_oid_clie = mc.oid_clie
                 AND mcd.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
                 AND mcd.val_iden_docu_prin = 1
                 AND mc.oid_clie = record_base (x).clie_oid_clie), --Tipo de identificacion del sujeto
              (SELECT mcd.num_docu_iden
                  FROM mae_clien_ident mcd, mae_clien mc
                 WHERE mcd.clie_oid_clie = mc.oid_clie
                   AND mcd.val_iden_docu_prin = 1
                   AND mc.oid_clie = record_base (x).clie_oid_clie), --Identificacion del sujeto
              (SELECT TRIM ( mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' || mc.val_nom2 )
                  FROM mae_clien mc
                 WHERE mc.oid_clie = record_base (x).clie_oid_clie), --Nombres y apellidos del sujeto
              'N', --Clase del sujeto
              orde1, --Provincia
              orde2, --Canton
              orde3, --Parroquia
              (SELECT mc.cod_sexo
                  FROM mae_clien mc
                 WHERE mc.oid_clie = record_base (x).clie_oid_clie), --Sexo
              ' ', --Estado civil
              ' ', --Origen de ingresos
              record_base(x).val_nume_soli, --Numero de operacion
              record_base(x).imp_movi, --Valor de operacion
              record_base(x).imp_pend, --Saldo de operacion
              record_base(x).fec_fact, --Fecha de concesion
              record_base(x).fec_venc, --Fecha de vencimiento
              record_base(x).fec_venc + 1, --Fecha que es exigible
              21, --Plazo de operación
              21, --Periocidad del pago
              ln_dia_mora, --Dias de morosidad
              CASE
                  WHEN ln_dia_mora > 0 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Monto de morosidad
              0, --Monto de interes en mora
              (SELECT (CASE
                                  WHEN TRUNC (SYSDATE) - record_base (x).fec_fact < 30 THEN record_base (x).imp_movi
                                  ELSE 0
                              END)
                FROM DUAL), --Valor por vencer de 1 a 30 días
              0, --Valor por vencer de 31 a 90 días
              0, --Valor por vencer de 91 a 180 días
              0, --Valor por vencer de 181 a 360 días
              0, --Valor por vencer de más de 360 días
              CASE
                  WHEN ln_dia_mora BETWEEN 1 AND 30 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Valor vencido de 1 a 30 días
              CASE
                  WHEN ln_dia_mora BETWEEN 31 AND 90 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Valor vencido de 31 a 90 días
              CASE
                  WHEN ln_dia_mora BETWEEN 91 AND 180 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Valor vencido de 91 a 180 días
              CASE
                  WHEN ln_dia_mora BETWEEN 181 AND 360 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Valor vencido de 181 a 360 días
              CASE
                  WHEN ln_dia_mora > 360 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Valor vencido de más de 360 días
              0, --Valor de demanda judicial
              CASE
                  WHEN ln_dia_mora > 360 THEN record_base(x).imp_pend
                  ELSE 0
              END, --Cartera castigada
              record_base(x).imp_movi, --Cuota del crédito
              (SELECT  (CASE record_base(x).imp_pend
                                 WHEN 0
                                    THEN (SELECT MAX (mb.fec_pago)
                                                FROM ccc_movim_banca mb
                                               WHERE mb.val_esta_movi_pend = 'A'
                                                     AND mb.clie_oid_clie = record_base(x).clie_oid_clie)
                                 ELSE null
                              END)
                FROM DUAL), --Fecha de cancelación
              'E', --Forma de cancelación
              lv_cod_unid_geog);
          END LOOP;
       END IF;
       EXIT WHEN cur_base%NOTFOUND;
    END LOOP;
    CLOSE cur_base;

  EXCEPTION
  WHEN OTHERS THEN
      ln_sqlcode := sqlcode;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_GENER_REPOR_CREDT: '||ls_sqlerrm);
  END COB_PR_GENER_REPOR_CREDT;

/*********************************************************************************
  Descripcion           : Genera archivo TXT correspondiente al Reporte Histórico de Operación Crediticia
  Fecha Creacion    : 10/02/2014
  Autor                   : Sebastian Guerra
  Parametros :
              psCodigoPais : Codigo pais
              psNombreArchivo : Nombre del archivo
              psTitulo : Titulo del documento
              psDirectorio: Directorio en donde se encuentra el archivo
**********************************************************************************/
  PROCEDURE COB_PR_GENER_REPOR_CREDT_TXT(
      psCodigoPais                     VARCHAR2,
      psNombreArchivo              VARCHAR2,
      psTitulo                              VARCHAR2,
      psDirectorio              OUT  VARCHAR2
      )
  IS

  lsDirTempo             BAS_INTER.DIR_TEMP%TYPE;
  v_handle                UTL_FILE.FILE_TYPE;
  lsLinea                    VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT
            r.val_acre || '|' ||
            TO_CHAR (r.fec_dato, 'dd/mm/yyyy') || '|' ||
            r.tipo_suje || '|' ||
            RPAD (r.val_cedu, 13, ' ') || '|' ||
            RPAD (r.nom_suje, 100, ' ') || '|' ||
            r.cod_clase || '|' ||
            SUBSTR (r.orde_1, 5, 2) || '|' ||
            SUBSTR (r.orde_2, 5, 2) || '|' ||
            SUBSTR (r.orde_1, 5, 2) || SUBSTR (r.orde_2, 5, 2) || SUBSTR (r.orde_3, 3, 3) || '|' ||
            r.val_sexo || '|' ||
            r.ind_esta_civi || '|' ||
            r.val_orig_ingr || '|' ||
            RPAD (r.val_nume_soli, 32, ' ') || '|' ||
            LPAD (r.imp_movi, 15, ' ') || '|' ||
            LPAD (r.imp_pend, 15, ' ') || '|' ||
            TO_CHAR (r.fec_fact, 'dd/mm/yyyy') || '|' ||
            TO_CHAR (r.fec_venc, 'dd/mm/yyyy') || '|' ||
            TO_CHAR (r.fec_exig, 'dd/mm/yyyy') || '|' ||
            LPAD (r.val_plaz_oper, 5, ' ') || '|' ||
            LPAD (r.dias_pago, 5, ' ') || '|' ||
            LPAD (r.dias_moro, 5, ' ') || '|' ||
            LPAD (r.val_mnt_mora, 15, ' ') || '|' ||
            LPAD (r.val_mnt_inte_mora, 15, ' ') || '|' ||
            LPAD (r.val_xven_di30, 15, ' ') || '|' ||
            LPAD (r.val_xven_di90, 15, ' ') || '|' ||
            LPAD (r.val_xven_d180, 15, ' ') || '|' ||
            LPAD (r.val_xven_d360, 15, ' ') || '|' ||
            LPAD (r.val_xven_mayo_d360, 15, ' ') || '|' ||
            LPAD (r.val_venc_di30, 15, ' ') || '|' ||
            LPAD (r.val_venc_di90, 15, ' ') || '|' ||
            LPAD (r.val_venc_di180, 15, ' ') || '|' ||
            LPAD (r.val_venc_di360, 15, ' ') || '|' ||
            LPAD (r.val_venc_mayo_d360, 15, ' ') || '|' ||
            LPAD (r.val_dem_judi, 15, ' ') || '|' ||
            LPAD (r.val_cart_cast, 15, ' ') || '|' ||
            LPAD (r.num_cuot_cred, 15, ' ') || '|' ||
            TO_CHAR (r.fec_canc, 'dd/mm/yyyy') || '|' ||
            r.for_canc as campo
      FROM cob_tmp_histo_opera_credi r
  ORDER BY r.clie_oid_clie ASC,
                    r.fec_fact DESC;

  TYPE interfazTipo IS RECORD(
   campo VARCHAR2(4000)
  );

  TYPE interfazTab  IS TABLE OF interfazTipo ;
  interfazRecord interfazTab;

  lbAbrirUtlFile  BOOLEAN;

  BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, v_handle);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).campo;
          UTL_FILE.PUT_LINE (v_handle, lsLinea);
      END LOOP;
    END IF;
  EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(v_handle);
  END IF;

  RETURN;
  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := sqlcode;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_GENER_REPOR_CREDT_TXT: '||ls_sqlerrm);
  END COB_PR_GENER_REPOR_CREDT_TXT;

/***************************************************************************
Descripcion       : Inserta Data en Tabla COB_GESTI_COBRA_PAIS de tabla temporal

Fecha Creacion    : 14/05/2014
Autor             : Gonzalo Javier Huertas Agurto

***************************************************************************/
PROCEDURE COB_PR_INSER_CARGA_MASIV_GESTI(psCodigoPais                     VARCHAR2,
                                         psFlagIngresado                  OUT VARCHAR2)
IS
  W_FILAS             NUMBER := 5000 ;
  lnNumeroSecuencialEtapaPeriodo VARCHAR2(8);
  lnExisteRegistro    varchar2(2);
  lnContador NUMBER:=0;
  lnEtapaActual                 VARCHAR(6);
  lnPeriodoActual               VARCHAR(6);

  CURSOR c_interfaz IS
  SELECT tg.cod_peri || tg.cod_etap_deud || 'R' || tua.codigo_region || 'ZT' ||
       tua.codigo_zona COD_CART,
       tg.cod_clie,
       tg.num_orde,
       tg.TIP_ACCI_COBR,
       tg.cod_acci_cobr,
       TO_CHAR(tg.fec_gest,'DD/MM/YYYY'),
       TO_CHAR(SYSDATE, 'HH24:MI:SS') VAL_HORA_GEST,
       tg.observacion,
       tg.PPA_IMP_PAGO,
       tg.cod_etap_deud,
        (select MIN(uc.cod_usua_cobr)
      from COB_USUAR_COBRA_PAIS uc
     where cod_pais = psCodigoPais
       and VAL_NOMB_USUA_COBR = tg.cod_usua_cobr_pais)       ,
       tg.nva_direccion,
       tg.nva_telefono,
       tg.nva_tipo_telefono,
       tg.Cod_Peri,
       TG.FEC_COMP_PAGO
  FROM TMP_CARGA_MASIVA_GESTIONES tg,
       (SELECT clie.cod_clie codigo_consultora,
               zorg.cod_regi codigo_region,
               zzon.cod_zona codigo_zona,
               zscc.cod_secc codigo_seccion
          FROM mae_clien             clie,
               mae_clien_unida_admin cuad,
               zon_terri_admin       ztad,
               zon_terri             terr,
               zon_secci             zscc,
               zon_zona              zzon,
               zon_regio             zorg
         WHERE clie.oid_clie = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND zscc.zzon_oid_zona = zzon.oid_zona
           AND zzon.zorg_oid_regi = zorg.oid_regi
           AND ztad.terr_oid_terr = terr.oid_terr
           AND cuad.ind_acti = 1) tua
 where tg.cod_clie = tua.codigo_consultora order by tg.cod_etap_deud, tg.cod_peri;

TYPE interfazTipo IS RECORD (

 v_COD_CART           VARCHAR2(25),
 v_COD_CLIE           VARCHAR2(15),
 v_NUM_ORDE           NUMBER(12),
 v_TIP_ACCI_COBR      VARCHAR2(1),
 v_COD_ACCI_COBR      VARCHAR2(3),
 v_FEC_GEST           VARCHAR2(10),
 v_VAL_GEST           VARCHAR2(10),
 v_OBSERVACION        VARCHAR2(4000),
 v_PPA_IMP_PAGO       NUMBER(12,2),
 v_COD_ETAP_DEUD      VARCHAR2(4),
 v_COD_USUA_COBR_PAIS VARCHAR2(15),
 v_VAL_NUEV_DIRE      VARCHAR2(500),
 v_VAL_NUEV_TELE      VARCHAR2(100),
 v_VAL_TIPO_NUEV_TELE VARCHAR2(2),
 v_COD_PERI           VARCHAR2(10),
 v_FEC_COMP_PAGO      VARCHAR2(10)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;

BEGIN

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

   lnContador:=1;
   lnPeriodoActual:='XXXXXX';
   lnEtapaActual:='XXXXXX';
   psFlagIngresado:='0';

   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          SELECT NUM_SECU_ETAP
          INTO lnNumeroSecuencialEtapaPeriodo
          FROM COB_ETAPA_DEUDA_PAIS
          where COD_ETAP_DEUD=interfazRecord(x).v_COD_ETAP_DEUD
          AND COD_PAIS=psCodigoPais
          ORDER BY NUM_SECU_ETAP;

          if lnPeriodoActual<>interfazRecord(x).v_COD_PERI or lnEtapaActual<>interfazRecord(x).v_COD_ETAP_DEUD then
             lnPeriodoActual:=interfazRecord(x).v_COD_PERI;
             lnEtapaActual:=interfazRecord(x).v_COD_ETAP_DEUD;
             lnContador:=1;
          end if;

          select count(1)
            into lnExisteRegistro
            from COB_GESTI_COBRA_PAIS cgcp
           where cgcp.cod_clie=interfazRecord(x).v_COD_CLIE
             and cgcp.cod_acci_cobr=interfazRecord(x).v_COD_ACCI_COBR
             and trunc(cgcp.fec_gest)=TO_DATE(interfazRecord(x).v_FEC_GEST,'DD/MM/YYYY')
             and cgcp.num_orde=interfazRecord(x).v_NUM_ORDE;

          if lnExisteRegistro='0' then

          insert into COB_GESTI_COBRA_PAIS
          (COD_CART,
           COD_CLIE,
           TIP_ACCI_COBR,
           COD_ACCI_COBR,
           FEC_GEST,
           VAL_HORA_GEST,
           VAL_OBSE,
           FEC_ACCI_COBR,
           PPA_IMP_PAGO,
           NUM_ORDE,
           COD_USUA_COBR_PAIS,
           COD_ETAP_DEUD,
           COD_PERI,
           VAL_NUEV_DIRE,
           VAL_NUEV_TELE,
           VAL_TIPO_NUEV_TELE)
        values
          (interfazRecord(x).v_COD_CART||lnNumeroSecuencialEtapaPeriodo||LPAD(lnContador, 5, '0'),
           interfazRecord(x).v_COD_CLIE,
           interfazRecord(x).v_TIP_ACCI_COBR,
           interfazRecord(x).v_COD_ACCI_COBR,
           TO_DATE(interfazRecord(x).v_FEC_GEST,'dd/mm/yyyy'),
           interfazRecord(x).v_VAL_GEST,
           interfazRecord(x).v_OBSERVACION,
           TO_DATE(interfazRecord(x).v_FEC_COMP_PAGO,'dd/mm/yyyy'),
           interfazRecord(x).v_PPA_IMP_PAGO,
           interfazRecord(x).v_NUM_ORDE,
           interfazRecord(x).v_COD_USUA_COBR_PAIS,
           interfazRecord(x).v_COD_ETAP_DEUD,
           interfazRecord(x).v_COD_PERI,
           interfazRecord(x).v_VAL_NUEV_DIRE,
           interfazRecord(x).v_VAL_NUEV_TELE,
           interfazRecord(x).v_VAL_TIPO_NUEV_TELE);

           lnContador:=lnContador+1;
           ELSE
             psFlagIngresado:='1';
           end if;
      END LOOP;
    END IF;
    DELETE FROM TMP_CARGA_MASIVA_GESTIONES;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 RETURN;
 
 EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COB_PR_INSER_CARGA_MASIV_GESTI: '||ls_sqlerrm);
 
 END COB_PR_INSER_CARGA_MASIV_GESTI;
 
END COB_PKG_PROCE;
/
