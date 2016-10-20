CREATE OR REPLACE PACKAGE FIN_PKG_GENER is

   TYPE r_cur_refe is REF CURSOR;
   TYPE t_tab_camp IS TABLE OF VARCHAR2(250) INDEX BY BINARY_INTEGER;

   TYPE error_rt IS RECORD (
    program_owner   all_objects.owner%TYPE,
    program_name    all_objects.object_name%TYPE,
    line_number     PLS_INTEGER);

 /***************************************************************************
   Descripcion       : Procedimiento ejecuta una sentencia DML y evita
                       el COMMIT implicito de la sentencia EXECUTE IMMEDIATE
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_EXECU_DINAM_SQL(
  p_val_sql                        IN   VARCHAR2);


/***************************************************************************
   Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_REGIS_PROCE(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                        IN   VARCHAR2,
  p_cod_proc_ejec                  OUT  fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_erro                       OUT VARCHAR2);

 /***************************************************************************
   Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_ACTUA_PROCE(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE);


 /***************************************************************************
     Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_FINAL_PROCE(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                   IN   fin_proce_ejecu.cod_esta_proc%TYPE);

 PROCEDURE FIN_PR_REGIS_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                      IN   VARCHAR2,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_nom_tabl                     IN   VARCHAR2,
  p_cod_proc_deta                OUT  VARCHAR2,
  p_cod_erro                     OUT  VARCHAR2);

  PROCEDURE FIN_PR_ACTUA_PROCE_DETAL(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE);

 PROCEDURE FIN_PR_FINAL_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                 IN   fin_proce_ejecu.cod_esta_proc%TYPE,
  p_num_rows                     IN   fin_proce_ejecu_detal.num_rows%TYPE);
      
 /***************************************************************************
   Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_REGIS_PROCE_LOG(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                        IN   VARCHAR2,
  p_cod_proc_ejec                  OUT  fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_erro                       OUT VARCHAR2);


   /***************************************************************************
     Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
     PROCEDURE FIN_PR_ACTUA_PROCE_LOG(
        p_cod_pais seg_pais.cod_pais%TYPE,
        p_cod_modu fin_modul.cod_modu%TYPE,
        p_cod_proc fin_proce_modul.cod_proc%TYPE,
        p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
        p_des_log fin_proce_ejecu.des_log%TYPE);


   /***************************************************************************
     Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
    PROCEDURE FIN_PR_FINAL_PROCE_LOG(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_cod_esta_fin fin_proce_ejecu.cod_esta_proc%TYPE);

   /***************************************************************************
     Descripcion       : Procedimiento que registra un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   PROCEDURE FIN_PR_REGIS_PROCE_EJEC(
      p_cod_pais IN                  seg_pais.cod_pais%TYPE,
      p_cod_soci IN                  seg_socie.cod_soci%TYPE,
      p_cod_modu IN                fin_modul.cod_modu%TYPE,
      p_cod_proc IN                 fin_proce_modul.cod_proc%TYPE,
      p_cod_usu IN                   VARCHAR2,
      p_cod_proc_ejec OUT     fin_proce_ejecu.cod_proc_ejec%TYPE);

  /***************************************************************************
     Descripcion       : Procedimiento que actualiza un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   PROCEDURE FIN_PR_ACTUA_LOG_PROCE_EJEC(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_des_log fin_proce_ejecu.des_log%TYPE);


   /***************************************************************************
     Descripcion       : Procedimiento que finaliza un proceso en el log
                                 de procesos financieros.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   PROCEDURE FIN_PR_FINAL_PROCE_EJEC(
      p_cod_pais                     seg_pais.cod_pais%TYPE,
      p_cod_soci                     seg_socie.cod_soci%TYPE,
      p_cod_modu                   fin_modul.cod_modu%TYPE,
      p_cod_proc                    fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec            fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_cod_esta_fin               fin_proce_ejecu.cod_esta_proc%TYPE);

   /***************************************************************************
     Descripcion       : Procedimiento que ejecuta Truncate Table sin  el efecto
                         del commit implicito.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   PROCEDURE FIN_PR_TRUNC_TABLE(
      p_nom_tabl IN VARCHAR2);

   /***************************************************************************
     Descripcion       : Funcion que obtiene el Indicador de Procesos en Ejecucion
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_INDIC_PROCE_EJECU(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE)
   RETURN NUMBER;

 PROCEDURE FIN_PR_OBTIE_PARAM_FACTU(
  p_oid_peri                       OUT   cra_perio.oid_peri%TYPE,
  p_cod_peri                       OUT   seg_perio_corpo.cod_peri%TYPE,
  p_fec_fact                       OUT   bas_ctrl_fact.fec_proc%TYPE);

   /***************************************************************************
     Descripcion       : Funcion que obtiene el Codigo de un Cliente
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_CODIG_CLIEN(
      p_oid_clie                     mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

      /***************************************************************************
     Descripcion       : Funcion que obtiene el OID de un Cliente
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_OID_CLIEN(
      p_cod_clie                     mae_clien.cod_clie%TYPE)
   RETURN NUMBER;

   /***************************************************************************
     Descripcion       : Funcion que obtiene el Digito de Control del
                                Codigo de Consultora
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
    FUNCTION FIN_FN_OBTIE_DIGIT_CONTRO(
      p_oid_clie   mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene los Nombres y Apellidos de un Cliente para un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_NOMBR_CLIEN(
      p_oid_clie                     mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene los Apellidos y Nombres de un Cliente para un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_APELL_CLIEN(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene la Fecha de Nacimiento de una Consultora
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
    FUNCTION FIN_FN_OBTIE_FECHA_NACIM_CLIEN(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;
   /***************************************************************************
     Descripcion       : Funcion que obtiene el Nombre de una Gerente de Region
                         para una Region dada.
     Fecha Creacion    : 07/07/2010
     Autor             : EL
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_NOMBR_GEREN_REGI(
      p_cod_regi                          zon_regio.cod_regi%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene el Nombre de una Gerente de Zona para
                                 una zona.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_NOMBR_GEREN_ZONA(
      p_cod_zona                          zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2;

      /******************************************************************************
     Descripcion       : Funcion que obtiene el nombre de la lider se una Seccion
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   ********************************************************************************/
   FUNCTION FIN_FN_OBTIE_NOMBR_LIDER_SECCI(
      p_cod_zona   zon_zona.cod_zona%TYPE,
      p_cod_secc   zon_secci.cod_secc%TYPE)
   RETURN VARCHAR2;

      /******************************************************************************
     Descripcion       : Funcion que obtiene el Codigo de un Documento de Identidad
                                 Principal de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   ********************************************************************************/
   FUNCTION FIN_FN_OBTIE_CODIG_DOCUM_IDENT(
       p_oid_pais                          seg_pais.oid_pais%TYPE,
      p_oid_clie                            mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

    /*********************************************************************************
     Descripcion       : Funcion que obtiene el Numero de un Documento de Identidad
                                 Principal de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   **********************************************************************************/
   FUNCTION FIN_FN_OBTIE_NUMER_DOCUM_IDENT(
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

    /*********************************************************************************
     Descripcion       : Funcion que obtiene la descripcion de un Documento de Identidad
                                 Principal de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   **********************************************************************************/
   FUNCTION FIN_FN_OBTIE_DESCR_DOCUM_IDENT(
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene el OID de la Moneda del Pais
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION  FIN_FN_OBTIE_OID_MONED(
     p_cod_pais                                      IN         seg_pais.cod_pais%TYPE)
   RETURN NUMBER;

   /***************************************************************************
     Descripcion       : Funcion que obtiene el OID del SubAcceso
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_OID_SUBAC(
     p_cod_sbac                                       IN      seg_subac.cod_sbac%TYPE)
   RETURN NUMBER;

    /***************************************************************************
     Descripcion       : Funcion que obtiene el OID del Tipo de Periodo.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_OID_TIPO_PERI(
      p_cod_peri                                     IN      seg_perio_corpo.cod_peri%TYPE)
   RETURN NUMBER;

    /***************************************************************************
     Descripcion       : Funcion que obtiene el OID de Periodo.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
    FUNCTION FIN_FN_OBTIE_OID_PERIO(
      p_cod_pais                    seg_pais.cod_pais%TYPE,
      p_cod_peri                    seg_perio_corpo.cod_peri%TYPE)
   RETURN cra_perio.oid_peri%TYPE;

       /***************************************************************************
     Descripcion       : Funcion que obtiene el OID de Periodo.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_OID_PERIO(
      p_cod_peri                                     IN      seg_perio_corpo.cod_peri%TYPE)
   RETURN cra_perio.oid_peri%TYPE;
    /***************************************************************************
     Descripcion       : Funcion que obtiene la descripcion del Pais.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_DESCR_PAIS(p_cod_pais seg_pais.cod_pais%TYPE)
   RETURN gen_i18n_sicc_comun.val_i18n%TYPE;

 /***************************************************************************
  Descripcion       : Funcion que obtiene la descripcion de una Sociedad .
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_DESCR_SOCIE(p_cod_soci seg_socie.cod_soci%TYPE)
 RETURN seg_socie.val_deno%TYPE;

 /***************************************************************************
  Descripcion       : Funcion que obtiene el codigo del Estatus Comercial de la Consulotra
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_CODIG_ESTAT_CLIEN(
    p_oid_clie   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2;

 /***************************************************************************
  Descripcion       : Funcion que obtiene la descripcion del Estatus Comercial
                                 de un cliente.
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(
    p_oid_clie                           mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2;

    /***************************************************************************
     Descripcion       : Funcion que obtiene el numero de telefono de un cliente,
                                 en base al tipo de telefono ('TF','TM','TT')
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_NUMER_TELEF_CLIEN(
      p_oid_clie                           mae_clien.oid_clie%TYPE,
      p_cod_tipo_tele                 mae_tipo_comun.cod_tipo_comu%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Funcion que obtiene el  codigo de un periodo.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_CODIG_PERIO(
      p_oid_peri                     cra_perio.oid_peri%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la descripcion del Distrito de la Direccion
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
    FUNCTION FIN_FN_OBTIE_DESCR_DISTR(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la direccion principal de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_DIREC_CLIEN(
      p_oid_clie                     mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2 ;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la referencian de la direccion principal
                                de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_DIREC_CLIEN_REFER(
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2 ;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la direccion principal incluyendo la referencia
                                de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_DIREC_COMPL_CLIEN(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2;

    /***************************************************************************
     Descripcion       : Funcion que obtiene el OID del Tipo de Solicitud
                                de un cliente.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_OID_SOLIC_PAIS(
  p_cod_tipo_soli                  IN   ped_tipo_solic.cod_tipo_soli%TYPE)
   RETURN ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;

    /***************************************************************************
     Descripcion       : Funcion que obtiene el numero de la boleta de despacho
                                de un cliente en una campa?a determinada.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_BOLET_DESPA_CAMPA(
      p_oid_peri                           cra_perio.oid_peri%TYPE,
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN ped_solic_cabec.val_nume_soli%TYPE;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la fecha de pedido de un cliente
                                 en una campa?a determinada.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
     FUNCTION FIN_FN_OBTIE_FECHA_PEDID_CAMPA(
      p_oid_peri                             cra_perio.oid_peri%TYPE,
      p_oid_clie                             mae_clien.oid_clie%TYPE)
   RETURN ped_solic_cabec.fec_fact%TYPE;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la campa?a del primer pedido para un
                                  cliente determinado.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_CAMPA_PRIME_PEDID(
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE;

    /***************************************************************************
     Descripcion       : Funcion que obtiene la campa?a del ultimo pedido para un
                                  cliente determinado.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(
      p_oid_clie                           mae_clien.oid_clie%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE;

   /***************************************************************************
     Descripcion       : Funcion que obtiene la campa?a del ultimo pedido para un
                                 cliente determinado, anterior a un periodo ingresado
                                 por parametro.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(
      p_oid_clie                           mae_clien.oid_clie%TYPE,
      p_oid_peri                           cra_perio.oid_peri%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE;

   /***************************************************************************
     Descripcion       : Funcion que obtiene la fecha del ultimo pedido para
                                 un cliente determinado.
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_FECHA_ULTIM_PEDID(
         p_oid_clie                        mae_clien.oid_clie%TYPE)
   RETURN DATE;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene el OID del periodo del ultimo pedido para
                                 un cliente determinado.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_OID_ULTIM_PEDID(
  p_oid_clie                         mae_clien.oid_clie%TYPE,
  p_oid_peri                         cra_perio.oid_peri%TYPE)
 RETURN cra_perio.oid_peri%TYPE;

 /*****************************************************************************
   Descripcion       : Procedimiento que obtiene la Fecha de Facturacion y
                       la Boleta de Despacho de una Campa?a
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 PROCEDURE FIN_PR_OBTIE_DATOS_FACTU_CAMPA(
  p_oid_clie                       IN   NUMBER,
  p_cod_peri                       IN   VARCHAR2,
  p_fec_fact                       OUT  DATE,
  p_val_nume_soli                  OUT  NUMBER);

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la fecha de facturacion para una zona
                                 en un periodo ingresado por parametro.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_FECHA_FACTU(
    p_cod_pais                   IN   seg_pais.cod_pais%TYPE,
    p_oid_zona                   IN   zon_zona.oid_zona%TYPE,
    p_oid_peri                   IN   cra_perio.oid_peri%TYPE)
 RETURN DATE;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la fecha de facturacion
                       para una consultora en un periodo ingresado por parametro.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_FECHA_FACTU_CLIEN(
    p_cod_pais                   IN   seg_pais.cod_pais%TYPE,
    p_cod_peri                   IN   seg_perio_corpo.cod_peri%TYPE,
    p_cod_clie                   IN   mae_clien.cod_clie%TYPE)
 RETURN DATE;

      /*****************************************************************************
     Descripcion       : Funcion que obtiene la fecha de cierre para la
                                 etapa de venta
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_FECHA_CIERR_VENTA(
      p_cod_pais                     seg_pais.cod_pais%TYPE,
      p_cod_soci                     seg_socie.cod_soci%TYPE,
      p_cod_peri                     seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                     zon_regio.cod_regi%TYPE,
      p_cod_zona                    zon_zona.cod_zona%TYPE)
   RETURN DATE;

   /*****************************************************************************
     Descripcion       : Funcion que obtiene el codigo de periodo un numero de periodos
                                 anterior al perido ingresado por parametro.
     Fecha Creacion    : 25/02/2009
     Autor             : Eduardo Lude?a
   *****************************************************************************/
  FUNCTION FIN_FN_CALCU_PERIO_NANTE(
     p_cod_peri                     seg_perio_corpo.cod_peri%TYPE,
     p_nro_periodos              NUMBER)
  RETURN seg_perio_corpo.cod_peri%TYPE;

     /*****************************************************************************
     Descripcion       : Funcion que Obtiene las Referencias de una Consultora, segun
                               el tipo de Referencia accediendo a la entidad MAE_REFER
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_REFER_CLIEN(
      p_cod_clie                    mae_clien.cod_clie%TYPE,
      p_tip_refe                    mae_refer.tipo_refe%TYPE)
   RETURN VARCHAR2;

   /*****************************************************************************
     Descripcion       : Funcion que Obtiene las Referencias de una Consultora segun
                                 la entidad MAE_OBSER
     Fecha Creacion    : 25/02/2009
     Autor             : Jorge Florencio
   *****************************************************************************/
   FUNCTION FIN_FN_OBTIE_REFER_OBSER_CLIEN(
      p_cod_clie                    mae_clien.cod_clie%TYPE)
   RETURN VARCHAR2;

   /*****************************************************************************
     Descripcion       : Funcion que verifica si  una fecha es determinada es dia
                       util (no sabado, Domingo o Feriado) de lo contrario devuelve
                       la fecha util mas proxima. Maneja un parametro adicional
                       que indica  si tambien se debe omitir  los Lunes.
                       Se apoya en la tabla de feriados COB_FERIA
     Fecha Creacion    : 04/08/2009
     Autor             : EL.
   *****************************************************************************/

   FUNCTION FIN_FN_OBTIE_FECHA_UTIL (
      p_str_fec                     VARCHAR2,
      p_acep_lune                   NUMBER)
   RETURN date;

   /*****************************************************************************
     Descripcion       : Funcion que obtiene periodo actual segun cronograma de
                       operaciones, si es mas de uno devuelve el mas antiguo.
     Fecha Creacion    : 11/09/2009
     Autor             : EL.
   *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_PERIO_ACTU
 RETURN NUMBER;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la descripcion desde GEN_I18N_SICC_COMUN
   Fecha Creacion    : 11/09/2009
   Autor             : EL.
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_DESCR_COMUN(
  p_nom_enti              gen_i18n_sicc_comun.attr_enti%TYPE,
  p_val_oid                 gen_i18n_sicc_comun.val_oid%TYPE)
 RETURN VARCHAR2;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la descripcion desde GEN_I18N_SICC_PAIS
   Fecha Creacion    : 11/09/2009
   Autor             : EL.
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_DESCR_PAIS(
  p_nom_enti              gen_i18n_sicc_comun.attr_enti%TYPE,
  p_val_oid                 gen_i18n_sicc_comun.val_oid%TYPE)
 RETURN VARCHAR2;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la descripcion del Tipo de Solicitud
   Fecha Creacion    : 15/07/2012
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_DESCR_TSOLI_PAIS(
  p_oid_tipo_soli_pais           IN   NUMBER)
 RETURN VARCHAR2;

 /*****************************************************************************
   Descripcion       : Funcion que obtiene la campa?a resultado de un
                       numero de campa?as anterior o posterior de acuerdo al
                       parametro enviado.
   Fecha Creacion    : 11/09/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_NSGTE_CAMPA(
    p_cod_peri                    IN   seg_perio_corpo.cod_peri%TYPE,
    p_num_camp                    IN   NUMBER)
 RETURN VARCHAR2;

 /*****************************************************************************
   Descripcion       : Funcion que indica si la consultora tiene el estatus
                       de retirada y su saldo actual es 0.
   Fecha Creacion    : 11/09/2009
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_RETIR_SINDE(
   p_oid_clie                    IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 /*****************************************************************************
   Descripcion       : Funcion que agrega una cantidad
                       de dias dias utiles a una fecha.
   Fecha Creacion    : 22/09/2010
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_PR_ADICI_DIAS_FECHA(
  start_date_in                IN   DATE,
  days_in                      IN   NUMBER)
 RETURN DATE;

 FUNCTION FIN_FN_OBTIE_LINEA_ERROR(
  backtrace_in                 IN VARCHAR2)
 RETURN error_rt;

 /*****************************************************************************
   Descripcion       : Funcion obtiene el codigo de periodo actual
   Fecha Creacion    : 22/09/2010
   Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION FIN_FN_OBTIE_CODIG_PERIO_ACTUA
 RETURN VARCHAR;

 FUNCTION FIN_FN_OBTIE_OID_ZONA_CONSU(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 FUNCTION FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
  p_oid_peri                       IN   cra_perio.oid_peri%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE,
  p_cod_acti                       IN   cra_activ.cod_acti%TYPE)
 RETURN DATE;

 FUNCTION FIN_FN_OBTIE_OBSER_BLOQU(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2;

 FUNCTION FIN_FN_OBTIE_MONTO_PEDID(
  p_oid_clie                       IN   NUMBER,
  p_cod_peri                       IN   VARCHAR2)
 RETURN NUMBER;

 FUNCTION FIN_FN_OBTIE_TEXTO_SINCA_ESPEC(
  p_val_text                       IN   VARCHAR2)
 RETURN VARCHAR2;

 FUNCTION FIN_FN_OBTIE_CODIG_POSTA(
  p_oid_clie                       IN   NUMBER)
 RETURN VARCHAR2;
 
 FUNCTION FIN_FN_OBTIE_NUMER_PEDID(
  p_oid_clie                       IN   NUMBER)
 RETURN NUMBER;
  
 FUNCTION fin_pr_gener_fila_tabla_html(
  p_valo                           IN   VARCHAR2,
  p_anch                           IN   VARCHAR2,
  P_font                           IN   NUMBER DEFAULT 1,
  p_size                           IN   NUMBER DEFAULT 2,
  p_ind_cent                       IN   NUMBER DEFAULT 0,
  p_ind_negr                       IN   NUMBER DEFAULT 0)
 RETURN VARCHAR2;

END FIN_PKG_GENER;
/
CREATE OR REPLACE PACKAGE BODY FIN_PKG_GENER IS

  lv_no_data                       VARCHAR2(1):=' ';
  lvn_no_data                      NUMBER(1):=0;
  gc_cod_esta_clie_reti            CONSTANT VARCHAR2(2):='07';

  -- Strings that delimit different parts of line in stack.
  gc_name_deli                     CONSTANT CHAR (1) := '"';
  gc_dot_deli                      CONSTANT CHAR (1) := '.';
  gc_line_deli_espa                CONSTANT CHAR (5) := 'linea';
  gc_line_deli_ingl                CONSTANT CHAR (4) := 'line';
  gc_eol_deli                      CONSTANT CHAR (1) := CHR (10);
  --

 PROCEDURE FIN_PR_EXECU_DINAM_SQL(
  p_val_sql                        IN       VARCHAR2)
 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

   EXECUTE IMMEDIATE p_val_sql;

   COMMIT;

 EXCEPTION

 WHEN OTHERS THEN
    ROLLBACK;

 END FIN_PR_EXECU_DINAM_SQL;

 PROCEDURE FIN_PR_REGIS_PROCE(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                      IN   VARCHAR2,
  p_cod_proc_ejec                OUT  fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_erro                     OUT  VARCHAR2)
 IS

  lv_reg_fin_proce_ejecu  fin_proce_ejecu%ROWTYPE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  lv_reg_fin_proce_ejecu.cod_modu:=p_cod_modu;
  lv_reg_fin_proce_ejecu.cod_proc:=p_cod_proc;

  SELECT fpm.des_proc
  INTO lv_reg_fin_proce_ejecu.des_proc
  FROM fin_proce_modul fpm
  WHERE fpm.cod_modu=p_cod_modu
  AND fpm.cod_proc=p_cod_proc;

  SELECT (1+ABS(MOD(dbms_random.random,1000000)))
  INTO lv_reg_fin_proce_ejecu.cod_proc_ejec
  FROM dual;

  p_cod_proc_ejec:= lv_reg_fin_proce_ejecu.cod_proc_ejec;
  lv_reg_fin_proce_ejecu.ind_ejec:='S';
  lv_reg_fin_proce_ejecu.fec_inic_proc:=SYSDATE;
  lv_reg_fin_proce_ejecu.fec_fina_proc:=NULL;
  lv_reg_fin_proce_ejecu.cod_esta_proc:=1;
  lv_reg_fin_proce_ejecu.des_log:=NULL;
  lv_reg_fin_proce_ejecu.num_refe:=NULL;
  lv_reg_fin_proce_ejecu.usu_proc:=p_cod_usu;

  INSERT INTO fin_proce_ejecu VALUES lv_reg_fin_proce_ejecu;

  COMMIT;

 EXCEPTION

  WHEN OTHERS THEN
   p_cod_erro:='errors.finanzas.proceso.no.registrado';

 END FIN_PR_REGIS_PROCE;

 PROCEDURE FIN_PR_ACTUA_PROCE(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE)

 IS

   PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu p
  SET p.des_log= p.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
  WHERE p.cod_modu=p_cod_modu
     AND p.cod_proc=p_cod_proc
  AND p.cod_proc_ejec=p_cod_proc_ejec;

  COMMIT;

 END FIN_PR_ACTUA_PROCE;

 PROCEDURE FIN_PR_FINAL_PROCE(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                 IN   fin_proce_ejecu.cod_esta_proc%TYPE)
 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu p
  SET
   p.fec_fina_proc=SYSDATE,
   p.ind_ejec='N',
   p.cod_esta_proc=p_cod_esta_fin
  WHERE p.cod_modu=p_cod_modu
    AND p.cod_proc=p_cod_proc
    AND p.cod_proc_ejec=p_cod_proc_ejec;

  COMMIT;

 END FIN_PR_FINAL_PROCE;
  
 PROCEDURE FIN_PR_REGIS_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                      IN   VARCHAR2,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_nom_tabl                     IN   VARCHAR2,
  p_cod_proc_deta                OUT  VARCHAR2,
  p_cod_erro                     OUT  VARCHAR2)
 IS

  lv_reg_fin_proce_ejecu  fin_proce_ejecu_detal%ROWTYPE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  lv_reg_fin_proce_ejecu.cod_modu:= p_cod_modu;
  lv_reg_fin_proce_ejecu.cod_proc:=p_cod_proc;

  SELECT fpm.des_proc
  INTO lv_reg_fin_proce_ejecu.des_proc
  FROM fin_proce_modul fpm
  WHERE fpm.cod_modu=p_cod_modu
  AND fpm.cod_proc=p_cod_proc;

  lv_reg_fin_proce_ejecu.cod_proc_ejec := p_cod_proc_ejec;   
  
  SELECT (1+ABS(MOD(dbms_random.random,1000000)))
  INTO lv_reg_fin_proce_ejecu.cod_proc_ejec_deta
  FROM dual;
  
  p_cod_proc_deta := lv_reg_fin_proce_ejecu.cod_proc_ejec_deta;
  
  lv_reg_fin_proce_ejecu.fec_inic_proc := SYSDATE;    
  lv_reg_fin_proce_ejecu.val_nomb_tabl := p_nom_tabl;  
  lv_reg_fin_proce_ejecu.des_log := NULL;

  INSERT INTO fin_proce_ejecu_detal VALUES lv_reg_fin_proce_ejecu;

  COMMIT;

 EXCEPTION

  WHEN OTHERS THEN
   p_cod_erro:='errors.finanzas.proceso.no.registrado';

 END FIN_PR_REGIS_PROCE_DETAL;
 
 PROCEDURE FIN_PR_ACTUA_PROCE_DETAL(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE)

 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu_detal p
  SET p.des_log= p.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
  WHERE p.cod_modu = p_cod_modu
    AND p.cod_proc = p_cod_proc
    AND p.cod_proc_ejec = p_cod_proc_ejec
    AND p.cod_proc_ejec_deta = p_cod_proc_deta;

  COMMIT;

 END FIN_PR_ACTUA_PROCE_DETAL;
 
 PROCEDURE FIN_PR_FINAL_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                 IN   fin_proce_ejecu.cod_esta_proc%TYPE,
  p_num_rows                     IN   fin_proce_ejecu_detal.num_rows%TYPE)
 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu_detal p
  SET
   p.fec_fina_proc= SYSDATE,
   p.num_rows = p_num_rows      
  WHERE p.cod_modu = p_cod_modu
    AND p.cod_proc = p_cod_proc
    AND p.cod_proc_ejec = p_cod_proc_ejec
    AND p.cod_proc_ejec_deta = p_cod_proc_deta;

  COMMIT;

 END FIN_PR_FINAL_PROCE_DETAL;
  
 PROCEDURE FIN_PR_REGIS_PROCE_LOG(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                        IN   VARCHAR2,
  p_cod_proc_ejec                  OUT  fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  v_reg_fin_proce_ejecu  fin_proce_ejecu%ROWTYPE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

      v_reg_fin_proce_ejecu.cod_pais:=p_cod_pais;
      v_reg_fin_proce_ejecu.cod_soci:='.';
      v_reg_fin_proce_ejecu.cod_modu:=p_cod_modu;
      v_reg_fin_proce_ejecu.cod_proc:=p_cod_proc;

      SELECT fpm.des_proc
      INTO v_reg_fin_proce_ejecu.des_proc
      FROM fin_proce_modul fpm
      WHERE fpm.cod_pais=p_cod_pais
      AND fpm.cod_modu=p_cod_modu
      AND fpm.cod_proc=p_cod_proc;

      SELECT (1+ABS(MOD(dbms_random.random,1000000)))
      INTO v_reg_fin_proce_ejecu.cod_proc_ejec
      FROM dual;

      p_cod_proc_ejec:=v_reg_fin_proce_ejecu.cod_proc_ejec;
      v_reg_fin_proce_ejecu.ind_ejec:='S';
      v_reg_fin_proce_ejecu.fec_inic_proc:=SYSDATE;
      v_reg_fin_proce_ejecu.fec_fina_proc:=NULL;
      v_reg_fin_proce_ejecu.cod_esta_proc:=1;
      v_reg_fin_proce_ejecu.des_log:=NULL;
      v_reg_fin_proce_ejecu.num_refe:=NULL;
      v_reg_fin_proce_ejecu.usu_proc:=p_cod_usu;

      INSERT INTO fin_proce_ejecu VALUES v_reg_fin_proce_ejecu;

      COMMIT;

  EXCEPTION
      WHEN OTHERS THEN
         p_cod_erro:='errors.finanzas.proceso.no.registrado';
  END  FIN_PR_REGIS_PROCE_LOG;

  PROCEDURE FIN_PR_ACTUA_PROCE_LOG(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_des_log fin_proce_ejecu.des_log%TYPE)
   IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN

      UPDATE fin_proce_ejecu p
      SET p.des_log= p.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
      WHERE p.cod_pais=p_cod_pais
      AND p.cod_modu=p_cod_modu
      AND p.cod_proc=p_cod_proc
      AND p.cod_proc_ejec=p_cod_proc_ejec;

      COMMIT;

   END  FIN_PR_ACTUA_PROCE_LOG;

 
 PROCEDURE FIN_PR_FINAL_PROCE_LOG(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_cod_esta_fin fin_proce_ejecu.cod_esta_proc%TYPE)
  IS
  PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN

      UPDATE fin_proce_ejecu p
      SET p.fec_fina_proc=SYSDATE,
             p.ind_ejec='N',
             p.cod_esta_proc=p_cod_esta_fin
       WHERE p.cod_pais=p_cod_pais
       AND p.cod_modu=p_cod_modu
       AND p.cod_proc=p_cod_proc
       AND p.cod_proc_ejec=p_cod_proc_ejec;

       COMMIT;

   END  FIN_PR_FINAL_PROCE_LOG;

   PROCEDURE FIN_PR_REGIS_PROCE_EJEC(
      p_cod_pais                   IN       seg_pais.cod_pais%TYPE,
      p_cod_soci                   IN       seg_socie.cod_soci%TYPE,
      p_cod_modu                 IN       fin_modul.cod_modu%TYPE,
      p_cod_proc                  IN        fin_proce_modul.cod_proc%TYPE,
      p_cod_usu                    IN        VARCHAR2,
      p_cod_proc_ejec         OUT     fin_proce_ejecu.cod_proc_ejec%TYPE)
   IS
     v_reg_fin_proce_ejecu  fin_proce_ejecu%ROWTYPE;

     PRAGMA AUTONOMOUS_TRANSACTION;

  BEGIN

      v_reg_fin_proce_ejecu.cod_pais:=p_cod_pais;
      v_reg_fin_proce_ejecu.cod_soci:=p_cod_soci;
      v_reg_fin_proce_ejecu.cod_modu:=p_cod_modu;
      v_reg_fin_proce_ejecu.cod_proc:=p_cod_proc;

      SELECT fpm.des_proc
      INTO v_reg_fin_proce_ejecu.des_proc
      FROM fin_proce_modul fpm
      WHERE fpm.cod_pais=p_cod_pais
       AND fpm.cod_soci=p_cod_soci
      AND fpm.cod_modu=p_cod_modu
      AND fpm.cod_proc=p_cod_proc;

      SELECT (1+ABS(MOD(dbms_random.random,1000000)))
      INTO v_reg_fin_proce_ejecu.cod_proc_ejec
      FROM dual;

      p_cod_proc_ejec:=v_reg_fin_proce_ejecu.cod_proc_ejec;
      v_reg_fin_proce_ejecu.ind_ejec:='S';
      v_reg_fin_proce_ejecu.fec_inic_proc:=SYSDATE;
      v_reg_fin_proce_ejecu.fec_fina_proc:=NULL;
      v_reg_fin_proce_ejecu.cod_esta_proc:=1;
      v_reg_fin_proce_ejecu.des_log:=NULL;
      v_reg_fin_proce_ejecu.num_refe:=NULL;
      v_reg_fin_proce_ejecu.usu_proc:=p_cod_usu;

      INSERT INTO fin_proce_ejecu VALUES v_reg_fin_proce_ejecu;

      COMMIT;

  END  FIN_PR_REGIS_PROCE_EJEC;



   PROCEDURE FIN_PR_ACTUA_LOG_PROCE_EJEC(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_des_log fin_proce_ejecu.des_log%TYPE)
   IS
   PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN

      UPDATE fin_proce_ejecu p
      SET p.des_log= p.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
      WHERE p.cod_pais=p_cod_pais
      AND p.cod_soci=p_cod_soci
      AND p.cod_modu=p_cod_modu
      AND p.cod_proc=p_cod_proc
      AND p.cod_proc_ejec=p_cod_proc_ejec;

      COMMIT;

   END  FIN_PR_ACTUA_LOG_PROCE_EJEC;

   PROCEDURE FIN_PR_FINAL_PROCE_EJEC(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE,
      p_cod_proc_ejec fin_proce_ejecu.cod_proc_ejec%TYPE,
      p_cod_esta_fin fin_proce_ejecu.cod_esta_proc%TYPE)
  IS
  PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN

      UPDATE fin_proce_ejecu p
      SET p.fec_fina_proc=SYSDATE,
             p.ind_ejec='N',
             p.cod_esta_proc=p_cod_esta_fin
       WHERE p.cod_pais=p_cod_pais
       AND p.cod_soci=p_cod_soci
       AND p.cod_modu=p_cod_modu
       AND p.cod_proc=p_cod_proc
       AND p.cod_proc_ejec=p_cod_proc_ejec;

       COMMIT;

   END  FIN_PR_FINAL_PROCE_EJEC;

   PROCEDURE FIN_PR_TRUNC_TABLE(
      p_nom_tabl IN VARCHAR2)
  IS
     PRAGMA AUTONOMOUS_TRANSACTION;
     v_sql VARCHAR2(100);
  BEGIN

     v_sql:='TRUNCATE TABLE '  || p_nom_tabl;
     EXECUTE IMMEDIATE(v_sql);

  END  FIN_PR_TRUNC_TABLE;
  
 
   FUNCTION FIN_FN_INDIC_PROCE_EJECU(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_modu fin_modul.cod_modu%TYPE,
      p_cod_proc fin_proce_modul.cod_proc%TYPE)
   RETURN NUMBER
   IS
      lv_ind_proc_ejec                         NUMBER(12);
   BEGIN

     SELECT COUNT(*)
     INTO lv_ind_proc_ejec
     FROM fin_proce_ejecu f
     WHERE f.cod_pais = p_cod_pais
     AND f.cod_soci = p_cod_soci
     AND f.cod_modu = p_cod_modu
     AND f.cod_proc = p_cod_proc
     AND f.ind_ejec='S'
     AND f.fec_fina_proc IS NULL;

     IF lv_ind_proc_ejec = 0 THEN
       RETURN 0;
     ELSE
        RETURN 1;
     END IF;

   END FIN_FN_INDIC_PROCE_EJECU;

 PROCEDURE FIN_PR_OBTIE_PARAM_FACTU(
  p_oid_peri                       OUT   cra_perio.oid_peri%TYPE,
  p_cod_peri                       OUT   seg_perio_corpo.cod_peri%TYPE,
  p_fec_fact                       OUT   bas_ctrl_fact.fec_proc%TYPE)
 IS

 BEGIN

   SELECT z.cod_peri,z.fec_proc
   INTO p_cod_peri, p_fec_fact
   FROM bas_ctrl_fact z
   WHERE z.ind_camp_act = 1
     AND z.sta_camp = 0;

   p_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri);

 END FIN_PR_OBTIE_PARAM_FACTU;

   FUNCTION FIN_FN_OBTIE_CODIG_CLIEN(
      p_oid_clie   mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_cod_clie                      mae_clien.cod_clie%TYPE;
   BEGIN

      SELECT TRIM(mc.cod_clie)
      INTO v_cod_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=p_oid_clie;

      RETURN v_cod_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CODIG_CLIEN;

   FUNCTION FIN_FN_OBTIE_OID_CLIEN(
      p_cod_clie   mae_clien.cod_clie%TYPE)
   RETURN NUMBER
   IS
      v_oid_clie mae_clien.oid_clie%TYPE;
   BEGIN

      SELECT mc.oid_clie
      INTO v_oid_clie
      FROM mae_clien mc
      WHERE mc.cod_clie=p_cod_clie;

      RETURN v_oid_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN NULL;
   END FIN_FN_OBTIE_OID_CLIEN;

   FUNCTION FIN_FN_OBTIE_DIGIT_CONTRO(
      p_oid_clie   mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_cod_digi_ctrl VARCHAR2(3);
   BEGIN

      SELECT TRIM(mc.cod_digi_ctrl)
      INTO v_cod_digi_ctrl
      FROM mae_clien mc
      WHERE mc.oid_clie=p_oid_clie;

      RETURN v_cod_digi_ctrl;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_DIGIT_CONTRO;

   FUNCTION FIN_FN_OBTIE_NOMBR_CLIEN(
      p_oid_clie   mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_nom_clie VARCHAR2(100);
   BEGIN

      SELECT
             TRIM(mc.val_nom1) || ' ' ||
             TRIM(mc.val_nom2) || ' ' ||
             TRIM(mc.val_ape1) || ' ' ||
             TRIM(mc.val_ape2)
      INTO v_nom_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=p_oid_clie;

      RETURN v_nom_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_NOMBR_CLIEN;

   FUNCTION FIN_FN_OBTIE_APELL_CLIEN(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_nom_clie VARCHAR2(100);
   BEGIN

      SELECT TRIM(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' || mc.val_nom2)
      INTO v_nom_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=p_oid_clie;

      RETURN v_nom_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_APELL_CLIEN;

   FUNCTION FIN_FN_OBTIE_FECHA_NACIM_CLIEN(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS

      v_fec_naci VARCHAR2(6);

   BEGIN

      SELECT to_char(mcda.fec_naci,'YYYYMM')
      INTO  v_fec_naci
      FROM mae_clien_datos_adici mcda
      WHERE mcda.clie_oid_clie=p_oid_clie;

  RETURN v_fec_naci;
  EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
  END FIN_FN_OBTIE_FECHA_NACIM_CLIEN;

  FUNCTION FIN_FN_OBTIE_NOMBR_GEREN_REGI(
      p_cod_regi   zon_regio.cod_regi%TYPE)
   RETURN VARCHAR2
   IS
      v_nom_clie  VARCHAR2(100);
      v_oid_clie    mae_clien.oid_clie%TYPE;
   BEGIN

      SELECT re.clie_oid_clie
      INTO v_oid_clie
      FROM zon_regio re
      WHERE re.cod_regi = p_cod_regi;

      SELECT TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2)
      INTO v_nom_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=v_oid_clie;

      RETURN v_nom_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_NOMBR_GEREN_REGI;

  FUNCTION FIN_FN_OBTIE_NOMBR_GEREN_ZONA(
      p_cod_zona   zon_zona.cod_zona%TYPE)
   RETURN VARCHAR2
   IS
      v_nom_clie  VARCHAR2(100);
      v_oid_clie    mae_clien.oid_clie%TYPE;
   BEGIN

      SELECT zz.clie_oid_clie
      INTO v_oid_clie
      FROM zon_zona zz
      WHERE zz.cod_zona=p_cod_zona;

      SELECT TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2)
      INTO v_nom_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=v_oid_clie;

      RETURN v_nom_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_NOMBR_GEREN_ZONA;

     FUNCTION FIN_FN_OBTIE_NOMBR_LIDER_SECCI(
      p_cod_zona   zon_zona.cod_zona%TYPE,
      p_cod_secc   zon_secci.cod_secc%TYPE)
   RETURN VARCHAR2
   IS
      v_nom_clie  VARCHAR2(100);
      v_oid_clie    mae_clien.oid_clie%TYPE;
   BEGIN

      SELECT zs.clie_oid_clie
      INTO v_oid_clie
      FROM zon_secci zs,
                 zon_zona zz
      WHERE  zs.zzon_oid_zona=zz.oid_zona
      AND zz.cod_zona=p_cod_zona
      AND zs.cod_secc=p_cod_secc;

      SELECT TRIM(mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2)
      INTO v_nom_clie
      FROM mae_clien mc
      WHERE mc.oid_clie=v_oid_clie;

      RETURN v_nom_clie;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_NOMBR_LIDER_SECCI;

   FUNCTION FIN_FN_OBTIE_CODIG_DOCUM_IDENT(
      p_oid_pais seg_pais.oid_pais%TYPE,
      p_oid_clie mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_oid_tipo_docu mae_tipo_docum.oid_tipo_docu%TYPE;
      v_Cod_Tipo_Docu mae_tipo_docum.cod_tipo_docu%TYPE;
   BEGIN

      SELECT mci.tdoc_oid_tipo_docu
      INTO v_oid_tipo_docu
      FROM mae_clien_ident mci
      WHERE mci.clie_oid_clie=p_oid_clie
      AND mci.val_iden_docu_prin=1;

      SELECT mtd.cod_tipo_docu
      INTO v_Cod_Tipo_Docu
      FROM mae_tipo_docum mtd
      WHERE mtd.pais_oid_pais=p_oid_pais
      AND mtd.oid_tipo_docu=v_oid_tipo_docu;

      RETURN v_Cod_Tipo_Docu;
    EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CODIG_DOCUM_IDENT;

   FUNCTION FIN_FN_OBTIE_NUMER_DOCUM_IDENT(
      p_oid_clie mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_num_Docu_iden mae_clien_ident.num_docu_iden%TYPE;
   BEGIN

      SELECT mci.num_docu_iden
      INTO v_num_Docu_iden
      FROM mae_clien_ident mci
      WHERE mci.clie_oid_clie=p_oid_clie
      AND mci.val_iden_docu_prin=1;

      RETURN v_num_Docu_iden;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
       WHEN too_many_rows THEN
         RETURN 'Inconsistencia de Datos';
   END FIN_FN_OBTIE_NUMER_DOCUM_IDENT;

   FUNCTION FIN_FN_OBTIE_DESCR_DOCUM_IDENT(
      p_oid_clie mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      v_oid_tipo_docu mae_tipo_docum.oid_tipo_docu%TYPE;
      v_Des_Tipo_docum_ident gen_i18n_sicc_pais.val_i18n%TYPE;
   BEGIN

      SELECT mci.tdoc_oid_tipo_docu
      INTO v_oid_tipo_docu
      FROM mae_clien_ident mci
      WHERE mci.clie_oid_clie=p_oid_clie
      AND mci.val_iden_docu_prin=1;

      SELECT g.val_i18n
      INTO v_Des_Tipo_docum_ident
      FROM gen_i18n_sicc_pais g
      WHERE g.val_oid=v_oid_tipo_docu
      AND g.attr_enti LIKE 'MAE_TIPO_DOCUM';

      RETURN v_Des_Tipo_docum_ident;
    EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_DESCR_DOCUM_IDENT;

  FUNCTION  FIN_FN_OBTIE_OID_MONED(
     p_cod_pais                                      IN         seg_pais.cod_pais%TYPE)
   RETURN NUMBER
   IS

      lv_oid_mone       seg_moned.oid_mone%TYPE;

   BEGIN

      SELECT sp.mone_oid_mone
      INTO   lv_oid_mone
      FROM  seg_pais sp
      WHERE sp.cod_pais=p_cod_pais;

      RETURN lv_oid_mone;

   END FIN_FN_OBTIE_OID_MONED;

   FUNCTION FIN_FN_OBTIE_OID_SUBAC(
     p_cod_sbac                                       IN      seg_subac.cod_sbac%TYPE)
   RETURN NUMBER
   IS

      lv_oid_suba                                    seg_subac.oid_sbac%TYPE;

   BEGIN

      SELECT ssu.oid_sbac
      INTO   lv_oid_suba
      FROM seg_subac ssu
      WHERE ssu.cod_sbac = p_cod_sbac;

      RETURN lv_oid_suba;

   END FIN_FN_OBTIE_OID_SUBAC;

   FUNCTION FIN_FN_OBTIE_OID_TIPO_PERI(
      p_cod_peri                                     IN      seg_perio_corpo.cod_peri%TYPE)
   RETURN NUMBER
   IS

      lv_oid_tipo_peri                               seg_perio_corpo.tipe_oid_tipo_peri%TYPE;

   BEGIN

      SELECT cor.TIPE_OID_TIPO_PERI
      INTO lv_oid_tipo_peri
      FROM seg_perio_corpo cor
      WHERE cor.cod_peri=p_cod_peri;

      RETURN lv_oid_tipo_peri;

   END FIN_FN_OBTIE_OID_TIPO_PERI;

   FUNCTION FIN_FN_OBTIE_OID_PERIO(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE)
   RETURN cra_perio.oid_peri%TYPE
   IS
      lv_oid_pais                seg_pais.oid_pais%TYPE;
      lv_oid_peri_corpo      cra_perio.oid_peri%TYPE;
      lv_oid_peri                cra_perio.oid_peri%TYPE;
    BEGIN

       SELECT sp.oid_pais
       INTO lv_oid_pais
       FROM seg_pais sp
       WHERE sp.cod_pais=p_cod_pais;

       SELECT spc.oid_peri
       INTO lv_oid_peri_corpo
       FROM seg_perio_corpo spc
       WHERE spc.cod_peri = p_cod_peri;

       SELECT cp.oid_peri
       INTO lv_oid_peri
       FROM cra_perio cp
       WHERE cp.pais_oid_pais = lv_oid_pais
       AND cp.peri_oid_peri = lv_oid_peri_corpo;

      RETURN lv_oid_peri;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lvn_no_data;
   END FIN_FN_OBTIE_OID_PERIO;

 FUNCTION FIN_FN_OBTIE_OID_PERIO(
      p_cod_peri                                     IN      seg_perio_corpo.cod_peri%TYPE)
 RETURN cra_perio.oid_peri%TYPE
 IS

  lv_oid_peri_corpo      cra_perio.oid_peri%TYPE;
  lv_oid_peri                cra_perio.oid_peri%TYPE;

 BEGIN

  SELECT spc.oid_peri
  INTO lv_oid_peri_corpo
  FROM seg_perio_corpo spc
  WHERE spc.cod_peri = p_cod_peri;

  SELECT cp.oid_peri
  INTO lv_oid_peri
  FROM cra_perio cp
  WHERE cp.peri_oid_peri = lv_oid_peri_corpo;

  RETURN lv_oid_peri;

 EXCEPTION

  WHEN no_data_found THEN
   RETURN lvn_no_data;

 END FIN_FN_OBTIE_OID_PERIO;

   FUNCTION FIN_FN_OBTIE_DESCR_PAIS(p_cod_pais seg_pais.cod_pais%TYPE)
   RETURN gen_i18n_sicc_comun.val_i18n%TYPE
   IS
   v_des_pais gen_i18n_sicc_comun.val_i18n%TYPE;
   BEGIN

      SELECT g.val_i18n
      INTO v_des_pais
      FROM gen_i18n_sicc_comun g,
               seg_pais p
      WHERE g.attr_enti LIKE '%SEG_PAIS%'
      AND g.val_oid=p.oid_pais
     AND p.cod_pais=p_cod_pais;

   RETURN v_des_pais;
   END FIN_FN_OBTIE_DESCR_PAIS;

   FUNCTION FIN_FN_OBTIE_DESCR_SOCIE(p_cod_soci seg_socie.cod_soci%TYPE)
   RETURN seg_socie.val_deno%TYPE
   IS
   v_des_soci seg_socie.val_deno%TYPE;
   BEGIN

      SELECT s.val_deno
      INTO v_des_soci
      FROM seg_socie s
      WHERE s.cod_soci=p_cod_soci;

   RETURN v_des_soci;
   END FIN_FN_OBTIE_DESCR_SOCIE;

 FUNCTION FIN_FN_OBTIE_CODIG_ESTAT_CLIEN(
    p_oid_clie   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS

    lv_cod_esta_clie             mae_estat_clien.cod_esta_clie%TYPE;

 BEGIN

    SELECT mec.cod_esta_clie
    INTO lv_cod_esta_clie
    FROM
       mae_estat_clien mec,
       mae_clien_datos_adici mcda
    WHERE mcda.clie_oid_clie = p_oid_clie
    AND mcda.esta_oid_esta_clie = mec.oid_esta_clie;

    RETURN lv_cod_esta_clie;

 EXCEPTION

      WHEN OTHERS THEN
         RETURN lv_no_data;

 END FIN_FN_OBTIE_CODIG_ESTAT_CLIEN;

 FUNCTION FIN_FN_OBTIE_DESCR_ESTAT_CLIEN(
    p_oid_clie   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS

    v_desc_esta_clie gen_i18n_sicc_comun.val_i18n%TYPE;

 BEGIN

    SELECT gen.val_i18n
    INTO v_desc_esta_clie
    FROM
       gen_i18n_sicc_comun gen,
       mae_clien_datos_adici mcda
    WHERE mcda.clie_oid_clie=p_oid_clie
    AND gen.val_oid=mcda.esta_oid_esta_clie
    AND gen.attr_enti LIKE 'MAE_ESTAT_CLIEN';

    RETURN v_desc_esta_clie;

 EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
 END FIN_FN_OBTIE_DESCR_ESTAT_CLIEN;

   FUNCTION FIN_FN_OBTIE_NUMER_TELEF_CLIEN(
      p_oid_clie            mae_clien.oid_clie%TYPE,
      p_cod_tipo_tele   mae_tipo_comun.cod_tipo_comu%TYPE)
   RETURN VARCHAR2
   IS
      v_num_tele_clie mae_clien_comun.val_text_comu%TYPE;
   BEGIN

      SELECT mcc.val_text_comu
      INTO v_num_tele_clie
      FROM mae_clien_comun mcc,
                 mae_tipo_comun mtc
      WHERE mcc.clie_oid_clie=p_oid_clie
      AND mcc.ticm_oid_tipo_comu=mtc.oid_tipo_comu
      AND mtc.cod_tipo_comu=p_cod_tipo_tele;

      RETURN v_num_tele_clie;
    EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_NUMER_TELEF_CLIEN;

   FUNCTION FIN_FN_OBTIE_CODIG_PERIO(
      p_oid_peri cra_perio.oid_peri%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
      v_cod_peri seg_perio_corpo.cod_peri%TYPE;
   BEGIN
      SELECT  spc.cod_peri
      INTO v_cod_peri
      FROM seg_perio_corpo spc,
                cra_perio cp
      WHERE spc.oid_peri=cp.peri_oid_peri
      AND cp.oid_peri=p_oid_peri;

      RETURN v_cod_peri;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CODIG_PERIO;

   FUNCTION FIN_FN_OBTIE_DESCR_DISTR(
      p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
   RETURN VARCHAR2
   IS
      lv_des_dist VARCHAR2(250);
   BEGIN

            SELECT zon_valor_estru_geopo.des_geog
            INTO lv_des_dist
            FROM   zon_valor_estru_geopo
            WHERE   zon_valor_estru_geopo.orde_1 =
               LTRIM(RTRIM(SUBSTR( (SELECT mae_clien_direc.cod_unid_geog
                                                           FROM  mae_clien_direc
                                                           WHERE (mae_clien_direc.clie_oid_clie = p_oid_clie)
                                                           AND ind_dire_ppal = 1
                                                           AND ind_elim = 0),1,6)))
              AND zon_valor_estru_geopo.orde_2 =
                 LTRIM(RTRIM(SUBSTR((SELECT mae_clien_direc.cod_unid_geog
                                                            FROM  mae_clien_direc
                                                            WHERE  (mae_clien_direc.clie_oid_clie = p_oid_clie)
                                                                        AND ind_dire_ppal = 1
                                                                        AND ind_elim = 0),7,6)))
            AND zon_valor_estru_geopo.orde_3 =
               LTRIM(RTRIM(SUBSTR((SELECT mae_clien_direc.cod_unid_geog
                                                          FROM  mae_clien_direc
                                                          WHERE  (mae_clien_direc.clie_oid_clie = p_oid_clie)
                                                                     AND ind_dire_ppal = 1
                                                                     AND ind_elim = 0),13,6)))
              AND zon_valor_estru_geopo.orde_4  IS NULL
            AND ROWNUM = 1;

   RETURN lv_des_dist;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_DESCR_DISTR;

 FUNCTION FIN_FN_OBTIE_DIREC_CLIEN(
      p_oid_clie   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS
 
  v_val_dire VARCHAR2(4000);
 
 BEGIN

      SELECT
         (NVL(TRIM(d.des_abrv_tipo_via),' ') ||' '||
          NVL(TRIM(a.val_nomb_via),' ') ) ||' '||
          TRIM(a.num_ppal) ||' '||
          TRIM(a.val_inte) ||' '||
          TRIM(a.val_manz) ||' '||
          TRIM(a.val_lote) ||' '||
          TRIM(a.val_Km)
     INTO v_val_dire
     FROM
        seg_tipo_via d,
        mae_clien_direc a
     WHERE a.clie_oid_clie = p_oid_clie
     AND d.oid_tipo_via = A.tivi_oid_tipo_VIa
     AND a.ind_dire_Ppal = 1
     AND a.ind_elim = 0;


      RETURN v_Val_dire;
 EXCEPTION
     WHEN no_data_found THEN
        RETURN lv_no_data;
     WHEN too_many_rows THEN
        RETURN 'Inconsistencia en los Datos';
 END FIN_FN_OBTIE_DIREC_CLIEN;

 FUNCTION FIN_FN_OBTIE_DIREC_CLIEN_REFER(
  p_oid_clie   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS
 
  v_val_refe                     mae_clien_direc.val_obse%TYPE;
     
 BEGIN

  SELECT NVL(TRIM(a.val_obse),' ')
  INTO v_val_refe
  FROM mae_clien_Direc a
  WHERE a.clie_oid_clie = p_oid_clie
    AND a.ind_dire_Ppal = 1
    AND a.ind_elim = 0;

  RETURN v_Val_refe;
  
 EXCEPTION
     
   WHEN no_data_found THEN
    RETURN lv_no_data;
    
   WHEN too_many_rows THEN
    RETURN 'Inconsistencia en los Datos';
    
 END FIN_FN_OBTIE_DIREC_CLIEN_REFER;

 FUNCTION FIN_FN_OBTIE_DIREC_COMPL_CLIEN(
  p_oid_clie                   IN       mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS
     
  v_val_dire VARCHAR2(4000);
  
 BEGIN
   
      SELECT
         TRIM((NVL(TRIM(d.des_abrv_tipo_via),' ') ||' '||
          NVL(TRIM(a.val_nomb_via),' ') ) ||' '||
          TRIM(a.num_ppal) ||' '||
          TRIM(a.val_inte) ||' '||
          TRIM(a.val_manz) ||' '||
          TRIM(a.val_lote) ||' '||
          TRIM(a.val_Km)) ||' '||
          NVL(TRIM(a.val_obse),' ')
     INTO v_val_dire
     FROM
        seg_tipo_via d,
        mae_clien_Direc a
     WHERE a.clie_oid_clie = p_oid_clie
     AND d.oid_tipo_via = a.tivi_oid_tipo_VIa
     AND a.ind_dire_ppal = 1
     AND a.ind_elim = 0;

   RETURN v_val_dire;
 END FIN_FN_OBTIE_DIREC_COMPL_CLIEN;

   FUNCTION FIN_FN_OBTIE_OID_SOLIC_PAIS(
  p_cod_tipo_soli                  IN   ped_tipo_solic.cod_tipo_soli%TYPE)
   RETURN ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE
   IS

  lv_oid_tipo_soli_pais            ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  lv_oid_tipo_soli                 ped_tipo_solic.oid_tipo_soli%TYPE;

   BEGIN

      SELECT pts.oid_tipo_soli
  INTO lv_oid_tipo_soli
      FROM ped_tipo_solic pts
      WHERE pts.cod_tipo_soli=p_cod_tipo_soli;

      SELECT ptsp.oid_tipo_soli_pais
  INTO lv_oid_tipo_soli_pais
      FROM ped_tipo_solic_pais ptsp
  WHERE ptsp.tsol_oid_tipo_soli = lv_oid_tipo_soli;

  RETURN lv_oid_tipo_soli_pais;

   EXCEPTION

      WHEN no_data_found THEN
         RETURN lvn_no_data;

   END FIN_FN_OBTIE_OID_SOLIC_PAIS;

   FUNCTION FIN_FN_OBTIE_BOLET_DESPA_CAMPA(
      p_oid_peri    cra_perio.oid_peri%TYPE,
      p_oid_clie     mae_clien.oid_clie%TYPE)
   RETURN ped_solic_cabec.val_nume_soli%TYPE
   IS
      v_soca_oid_soli_cabe     ped_solic_cabec.oid_soli_cabe%TYPE;
      v_val_nume_soli             ped_solic_cabec.val_nume_soli%TYPE;
      v_oid_tipo_soli_pais        ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
   BEGIN

      v_oid_tipo_soli_pais:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

      SELECT MAX(a.soca_oid_soli_cabe)
      INTO v_soca_oid_soli_cabe
      FROM ped_solic_cabec a,
                 ped_solic_cabec b
      WHERE a.soca_oid_soli_cabe=b.oid_soli_cabe
      AND a.clie_oid_clie = p_oid_clie
      AND b.clie_oid_clie = p_oid_clie
      AND a.esso_oid_esta_soli = 1
      AND b.esso_oid_esta_soli = 1
      AND a.tspa_oid_tipo_soli_pais = v_oid_tipo_soli_pais
      AND a.perd_oid_peri=p_oid_peri
      AND a.fec_fact IS NOT NULL;

      SELECT b.val_nume_soli
      INTO v_val_nume_soli
      FROM ped_solic_cabec b
      WHERE b.oid_soli_cabe=v_soca_oid_soli_cabe
      AND b.clie_oid_clie= p_oid_clie
      AND b.perd_oid_peri=p_oid_peri;

      RETURN v_val_nume_soli;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN NULL;
      WHEN too_many_rows THEN
        RETURN NULL;
   END FIN_FN_OBTIE_BOLET_DESPA_CAMPA;

   FUNCTION FIN_FN_OBTIE_FECHA_PEDID_CAMPA(
      p_oid_peri    cra_perio.oid_peri%TYPE,
      p_oid_clie     mae_clien.oid_clie%TYPE)
   RETURN ped_solic_cabec.fec_fact%TYPE
   IS
      v_fec_pedi                    ped_solic_cabec.fec_fact%TYPE;
      v_oid_tipo_soli_pais        ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
   BEGIN

      v_oid_tipo_soli_pais:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

      SELECT a.fec_fact
      INTO v_fec_pedi
      FROM ped_solic_cabec a,
                ped_solic_cabec b
      WHERE a.soca_oid_soli_cabe=b.oid_soli_cabe
      AND a.clie_oid_clie = p_oid_clie
      AND b.clie_oid_clie = p_oid_clie
      AND a.esso_oid_esta_soli = 1
      AND b.esso_oid_esta_soli = 1
      AND a.tspa_oid_tipo_soli_pais = v_oid_tipo_soli_pais
      AND a.perd_oid_peri=p_oid_peri
      AND a.fec_fact IS NOT NULL;

      RETURN v_fec_pedi;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN NULL;
      WHEN too_many_rows THEN
        RETURN NULL;
   END FIN_FN_OBTIE_FECHA_PEDID_CAMPA;

   FUNCTION FIN_FN_OBTIE_CAMPA_PRIME_PEDID(
      p_oid_clie    mae_clien.oid_clie%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
      v_camp_prim_pedi_cons   seg_perio_corpo.cod_peri%TYPE;
      v_oid_tipo_soli_pais        ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
      v_min_oid_peri              cra_perio.oid_peri%TYPE;
   BEGIN

      v_oid_tipo_soli_pais:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIs('C1');

      SELECT MIN(a.perd_oid_peri)
      INTO v_min_oid_peri
      FROM ped_solic_cabec a
      WHERE a.clie_oid_clie = p_oid_clie
      AND a.esso_oid_esta_soli = 1
      AND a.tspa_oid_tipo_soli_pais = v_oid_tipo_soli_pais
      AND a.fec_fact IS NOT NULL;

      v_camp_prim_pedi_cons:=FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(v_min_oid_peri);

   RETURN v_camp_prim_pedi_cons;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CAMPA_PRIME_PEDID;

   FUNCTION FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(
      p_oid_clie    mae_clien.oid_clie%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
      v_camp_ulti_pedi_cons   seg_perio_corpo.cod_peri%TYPE;
      v_max_oid_peri              cra_perio.oid_peri%TYPE;
   BEGIN

      SELECT MAX(a.perd_oid_peri)
      INTO v_max_oid_peri
      FROM ped_solic_cabec a
      WHERE a.clie_oid_clie = p_oid_clie
      AND a.ind_oc=1
      AND a.val_tota_paga_loca>0
      AND a.fec_fact IS NOT NULL;

      v_camp_ulti_pedi_cons:=FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(v_max_oid_peri);

   RETURN v_camp_ulti_pedi_cons;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
     WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CAMPA_ULTIM_PEDID;

   FUNCTION FIN_FN_OBTIE_CAMPA_ULTIM_PEDID(
      p_oid_clie    mae_clien.oid_clie%TYPE,
      p_oid_peri   cra_perio.oid_peri%TYPE)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
      v_camp_ulti_pedi_cons   seg_perio_corpo.cod_peri%TYPE;
      v_oid_tipo_soli_pais        ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
      v_max_oid_peri              cra_perio.oid_peri%TYPE;
   BEGIN

      v_oid_tipo_soli_pais:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIs('SOC');

      SELECT MAX(a.perd_oid_peri)
      INTO v_max_oid_peri
      FROM ped_solic_cabec a,
                ped_solic_Cabec b
      WHERE a.soca_oid_soli_cabe=b.oid_soli_cabe
      AND a.clie_oid_clie = p_oid_clie
      AND b.clie_oid_clie = p_oid_clie
      AND a.perd_oid_peri<= p_oid_peri
      AND a.esso_oid_esta_soli = 1
      AND b.esso_oid_esta_soli = 1
      AND a.tspa_oid_tipo_soli_pais = v_oid_tipo_soli_pais
      AND a.fec_fact IS NOT NULL;

      v_camp_ulti_pedi_cons:=FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(v_max_oid_peri);

   RETURN v_camp_ulti_pedi_cons;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_CAMPA_ULTIM_PEDID;

   FUNCTION FIN_FN_OBTIE_FECHA_ULTIM_PEDID(
         p_oid_clie    mae_clien.oid_clie%TYPE)
   RETURN DATE
   IS
     v_max_fech_fact DATE;
   BEGIN

          SELECT MAX(a.fec_fact)
          INTO v_max_fech_fact
          FROM ped_solic_cabec a
          WHERE a.clie_oid_clie = p_oid_clie
          AND a.ind_oc=1
          AND a.val_tota_paga_loca>0
          AND a.fec_fact IS NOT NULL;

   RETURN v_max_fech_fact;

   EXCEPTION
      WHEN no_data_found THEN
         RETURN lv_no_data;
      WHEN too_many_rows THEN
         RETURN lv_no_data;
   END FIN_FN_OBTIE_FECHA_ULTIM_PEDID;

   FUNCTION FIN_FN_OBTIE_OID_ULTIM_PEDID(
      p_oid_clie    mae_clien.oid_clie%TYPE,
      p_oid_peri   cra_perio.oid_peri%TYPE)
   RETURN cra_perio.oid_peri%TYPE
   IS
      v_oid_tipo_soli_pais        ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
      v_max_oid_peri              cra_perio.oid_peri%TYPE;
   BEGIN

      v_oid_tipo_soli_pais:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIs('SOC');

      SELECT MAX(a.perd_oid_peri)
      INTO v_max_oid_peri
      FROM ped_solic_cabec a
      WHERE a.clie_oid_clie = p_oid_clie
      AND a.perd_oid_peri<= p_oid_peri
      AND a.esso_oid_esta_soli = 1
      AND a.tspa_oid_tipo_soli_pais = v_oid_tipo_soli_pais
      AND a.fec_fact IS NOT NULL;

   RETURN v_max_oid_peri;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN p_oid_peri;
      WHEN too_many_rows THEN
         RETURN p_oid_peri;

   END FIN_FN_OBTIE_OID_ULTIM_PEDID;

 PROCEDURE FIN_PR_OBTIE_DATOS_FACTU_CAMPA(
  p_oid_clie                       IN   NUMBER,
  p_cod_peri                       IN   VARCHAR2,
  p_fec_fact                       OUT  DATE,
  p_val_nume_soli                  OUT  NUMBER)
 IS

  lv_oid_peri                      CRA_PERIO.OID_PERI%TYPE;
  lv_oid_soli_cabe                 PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE%TYPE;
  lv_fec_fact                      PED_SOLIC_CABEC.FEC_FACT%TYPE;
  lv_oid_tipo_soli                 ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;
  lv_val_nume_soli                 PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;


 BEGIN

  lv_oid_tipo_soli := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

  lv_oid_peri := fin_pkg_gener.FIN_FN_OBTIE_OID_PERIO(p_cod_peri);


  SELECT MIN(a.oid_soli_cabe)
  INTO lv_oid_soli_cabe
  FROM
     ped_solic_cabec a
  WHERE a.clie_oid_clie = p_oid_clie
    AND a.perd_oid_peri = lv_oid_peri
    AND a.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli
    AND a.ind_oc = 1
    AND a.fec_fact IS NOT NULL
    AND a.esso_oid_esta_soli <> 4;

  SELECT psc.fec_fact, psc.val_nume_soli
  INTO lv_fec_fact, lv_val_nume_soli
  FROM ped_solic_cabec psc
  WHERE psc.oid_soli_cabe = lv_oid_soli_cabe;


  p_fec_fact := lv_fec_fact;
  p_val_nume_soli  := lv_val_nume_soli;

 EXCEPTION
   WHEN NO_DATA_FOUND THEN

    p_fec_fact := NULL;
    p_val_nume_soli  := NULL;

 END FIN_PR_OBTIE_DATOS_FACTU_CAMPA;

 FUNCTION FIN_FN_OBTIE_FECHA_FACTU(
    p_cod_pais                   IN   seg_pais.cod_pais%TYPE,
    p_oid_zona                   IN   zon_zona.oid_zona%TYPE,
    p_oid_peri                   IN   cra_perio.oid_peri%TYPE)
 RETURN DATE
 IS

    v_oid_acti                   cra_activ.oid_acti%TYPE;
    v_fec_fact                   DATE;

 BEGIN

    SELECT ca.oid_acti
    INTO v_oid_Acti
    FROM
       cra_activ ca,
       seg_pais sp
    WHERE ca.pais_oid_pais=sp.oid_pais
      AND sp.cod_pais=p_cod_pais
      AND ca.cod_acti='FA';

    SELECT cr.fec_inic
    INTO v_fec_fact
    FROM cra_crono cr
    WHERE cr.zzon_oid_zona=p_oid_zona
      AND cr.perd_oid_peri=p_oid_peri
      AND cr.cact_oid_acti=v_oid_acti;

    RETURN v_fec_fact;

 EXCEPTION
    WHEN no_data_found THEN
       RETURN NULL;

    WHEN too_many_rows THEN
       RETURN NULL;

 END FIN_FN_OBTIE_FECHA_FACTU;

 FUNCTION FIN_FN_OBTIE_FECHA_FACTU_CLIEN(
    p_cod_pais                   IN   seg_pais.cod_pais%TYPE,
    p_cod_peri                   IN   seg_perio_corpo.cod_peri%TYPE,
    p_cod_clie                   IN   mae_clien.cod_clie%TYPE)
 RETURN DATE
 IS

    v_oid_acti                   cra_activ.oid_acti%TYPE;
    lv_oid_zona                  zon_zona.oid_zona%TYPE;
    lv_oid_peri                  cra_perio.oid_peri%TYPE;
    v_fec_fact                   DATE;

 BEGIN

    SELECT ca.oid_acti
    INTO v_oid_Acti
    FROM
       cra_activ ca,
       seg_pais sp
    WHERE ca.pais_oid_pais = sp.oid_pais
      AND sp.cod_pais = p_cod_pais
      AND ca.cod_acti = 'FA';

    SELECT cp.oid_peri
    INTO lv_oid_peri
    FROM
       seg_perio_corpo spc,
       cra_perio cp
    WHERE spc.oid_peri = cp.peri_oid_peri
      AND spc.cod_peri = p_cod_peri;


    SELECT zz.oid_zona
    INTO lv_oid_zona
    FROM
       mae_clien mc,
       mae_clien_unida_admin mcua,
       zon_terri_admin zta,
       zon_secci zs,
       zon_zona zz
   WHERE mc.oid_clie = mcua.clie_oid_clie
     AND zta.oid_terr_admi = mcua.ztad_oid_terr_admi
     AND mcua.ind_acti = 1
     AND zs.oid_secc = zta.zscc_oid_secc
     AND zz.oid_zona = zs.zzon_oid_zona
     AND mc.cod_clie = p_cod_clie;

    SELECT cr.fec_inic
    INTO v_fec_fact
    FROM cra_crono cr
    WHERE cr.zzon_oid_zona = lv_oid_zona
      AND cr.perd_oid_peri = lv_oid_peri
      AND cr.cact_oid_acti = v_oid_acti;

    RETURN v_fec_fact;

 EXCEPTION
    WHEN OTHERS THEN
          RETURN NULL;

 END FIN_FN_OBTIE_FECHA_FACTU_CLIEN;

     FUNCTION FIN_FN_OBTIE_FECHA_CIERR_VENTA(
      p_cod_pais                     seg_pais.cod_pais%TYPE,
      p_cod_soci                     seg_socie.cod_soci%TYPE,
      p_cod_peri                     seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi                     zon_regio.cod_regi%TYPE,
      p_cod_zona                    zon_zona.cod_zona%TYPE)
   RETURN DATE
   IS
     lv_fec_cier                      cob_crono_carte.fec_cier%TYPE;
   BEGIN

      SELECT c.fec_cier
      INTO lv_fec_cier
      FROM cob_crono_Carte c
      WHERE c.cod_pais=p_cod_pais
      AND c.cod_soci=p_cod_soci
      AND c.cod_peri=p_cod_peri
      AND c.cod_regi=p_cod_regi
      AND c.cod_zona=p_cod_zona
      AND c.cod_etap_deud = 'VEN';

      RETURN lv_fec_cier;
   EXCEPTION
      WHEN no_data_found THEN
         RETURN NULL;
      WHEN too_many_rows THEN
         RETURN NULL;
   END FIN_FN_OBTIE_FECHA_CIERR_VENTA;

   FUNCTION FIN_FN_CALCU_PERIO_NANTE(
      p_cod_peri                     seg_perio_corpo.cod_peri%TYPE,
      p_nro_periodos              NUMBER)
   RETURN seg_perio_corpo.cod_peri%TYPE
   IS
      lv_nro_periodos                      NUMBER(3);
      lv_num_anio                            NUMBER(4);
      lv_num_camp                          NUMBER(2);
      lv_num_camp_wrk                  NUMBER(9);
      lv_car_anio                             VARCHAR2(4);
      lv_car_camp                            VARCHAR2(2);
      lv_cod_peri_gen                      seg_perio_corpo.cod_peri%TYPE;
      lv_entero_wrk                         NUMBER(9);
      lv_resto_wrk                           NUMBER(9);

  BEGIN
      lv_nro_periodos := p_nro_periodos;
      lv_car_anio := substr(p_cod_peri, 1, 4);
      lv_car_camp := substr(p_cod_peri, 5, 2);
      lv_num_anio := lv_car_anio;
      lv_num_camp := lv_car_camp;

      IF  lv_nro_periodos > 18 THEN
          lv_entero_wrk := trunc(lv_nro_periodos / 18);
          lv_resto_wrk  := mod(lv_nro_periodos, 18);
          lv_num_anio := lv_num_anio - lv_entero_wrk;
          lv_nro_periodos := lv_resto_wrk;
      END IF;

      lv_num_camp_wrk := lv_num_camp - lv_nro_periodos;

      IF lv_num_camp_wrk <= 0 then
         lv_num_anio := lv_num_anio - 1 ;
         lv_num_camp_wrk := lv_num_camp_wrk + 18;
         lv_num_camp := lv_num_camp_wrk;
      ELSE
          lv_num_camp := lv_num_camp_wrk;
      END IF;
      lv_car_anio := TO_CHAR(lv_num_anio);
      lv_car_camp := lpad(TO_CHAR(lv_num_camp), 2, '0');
      lv_cod_peri_gen := CONCAT(lv_car_anio, lv_car_camp);

  RETURN lv_cod_peri_gen;
   END FIN_FN_CALCU_PERIO_NANTE;

   FUNCTION FIN_FN_OBTIE_EMAIL_ACTUA_CARTE(
      p_cod_pais                     seg_pais.cod_pais%TYPE,
      p_cod_soci                     seg_socie.cod_soci%TYPE)
   RETURN VARCHAR2
   IS
      CURSOR c_mail
      IS
        SELECT uc.val_mail
        FROM cob_usuar_cobra_pais uc
        WHERE uc.cod_pais = p_cod_pais
        AND uc.cod_soci = p_cod_soci
        AND uc.ind_acti=1
        AND uc.ind_mail_proc_actu=1;

        lv_list_mail  VARCHAR2(250):=NULL;
        lv_cont         NUMBER(1):=0;
   BEGIN
      FOR v_mail IN c_mail  LOOP

         IF lv_cont > 0 THEN
            lv_list_mail:=lv_list_mail || ',' || v_mail.val_mail;
         ELSE
            lv_list_mail:= v_mail.val_mail;
         END IF;

         lv_cont:= lv_cont + 1 ;
      END LOOP;
      RETURN lv_list_mail;

   END FIN_FN_OBTIE_EMAIL_ACTUA_CARTE;

 FUNCTION FIN_FN_OBTIE_REFER_CLIEN(
  p_cod_clie                    mae_clien.cod_clie%TYPE,
  p_tip_refe                    mae_refer.tipo_refe%TYPE)
 RETURN VARCHAR2
 IS

  CURSOR c_refe
  IS
      SELECT nom_clie ||
                     num_docu_refe ||
                     num_tele ||
                     val_dire  val_refe
      FROM
         (  SELECT
                'Nom : '||
                mr.val_nom1 || ' ' ||
                mr.val_nom2 || ' ' ||
                mr.val_ape1 || ' ' ||
                mr.val_ape2 nom_clie,
              CASE
               WHEN mr.num_docu_refe IS NULL THEN ''
               ELSE ' Doc : ' || mr.num_docu_refe
              END num_docu_refe,
              CASE
               WHEN LENGTH(TRIM(mr.val_telf) || TRIM(mr.val_telf_trab) ||  TRIM(mr.val_celu)) IS NULL THEN ''
               ELSE
             ' Tele : ' ||
             TRIM(mr.val_telf) || '-' ||
             TRIM(mr.val_telf_trab) || '-' ||
             TRIM(mr.val_celu)
             END num_tele,
             CASE
              WHEN
                LENGTH(TRIM(mr.val_depa) ||
               TRIM(mr.val_ciud) ||
               TRIM(mr.val_barr) ||
               TRIM(mr.val_dire)) IS NULL THEN ''
                ELSE
             ' Dire : ' ||
             TRIM(mr.val_depa) || ' ' ||
             TRIM(mr.val_ciud) || ' ' ||
             TRIM(mr.val_barr) || ' ' ||
             TRIM(mr.val_dire)
             END val_dire
        FROM mae_refer mr
        WHERE mr.cod_clie = p_cod_clie
        AND mr.tipo_refe=p_tip_refe);

      lv_refe  VARCHAR2(250):=' ';
  
 BEGIN

  FOR v_refe IN c_refe LOOP
  
   lv_refe:= v_refe.val_refe || '/';
  
  END LOOP;

  lv_refe := FIN_FN_OBTIE_TEXTO_SINCA_ESPEC(lv_refe);
  
  RETURN lv_refe;

 EXCEPTION
  
  WHEN no_data_found THEN
   RETURN lv_no_data;

  WHEN too_many_rows THEN
   RETURN lv_no_data;

  WHEN OTHERS THEN
   RETURN lv_no_data;
   
 END FIN_FN_OBTIE_REFER_CLIEN;
       
 FUNCTION FIN_FN_OBTIE_REFER_OBSER_CLIEN(
  p_cod_clie                    mae_clien.cod_clie%TYPE)
 RETURN VARCHAR2
 IS

 CURSOR c_refe_obse
 IS
  SELECT obser.val_text refe
  FROM 
   mae_clien_obser obser,
   mae_clien clien
  WHERE clien.oid_clie=obser.clie_oid_clie
    AND clien.cod_clie=p_cod_clie;

  lv_refe_obse            VARCHAR2(250);
 
 BEGIN

  FOR   v_refe_obse IN  c_refe_obse LOOP
   lv_refe_obse:= lv_refe_obse || ' / ' || v_refe_obse.refe;
  END LOOP;

  lv_refe_obse := FIN_FN_OBTIE_TEXTO_SINCA_ESPEC(lv_refe_obse);
  
  RETURN lv_refe_obse;

 EXCEPTION
 
  WHEN no_data_found THEN
   RETURN lv_no_data;

  WHEN too_many_rows THEN
   RETURN lv_no_data;

  WHEN OTHERS THEN
   RETURN lv_no_data;

 END FIN_FN_OBTIE_REFER_OBSER_CLIEN;

   FUNCTION FIN_FN_OBTIE_FECHA_UTIL(
      p_str_fec   VARCHAR2,
      p_acep_lune number )
   RETURN date
   IS
      lv_sec_dia      NUMBER(2);
      lv_feriado      NUMBER(3);
      lv_flag_salir   NUMBER(1);

      lv_fech         DATE;
      lv_fecha_util   DATE;
   BEGIN

      lv_fech := to_date(p_str_fec, 'DD/MM/YYYY');
      lv_fecha_util := lv_fech;
      lv_flag_salir := 1;

      LOOP
         lv_sec_dia := to_char(lv_fecha_util, 'd');
         CASE lv_sec_dia
            WHEN 7 THEN
               BEGIN
                  lv_fecha_util := lv_fecha_util + 2;
                  lv_flag_salir := 0;
                END;
             WHEN 1 THEN
                BEGIN
                   lv_fecha_util := lv_fecha_util + 1;
                   lv_flag_salir := 0;
                END;
             WHEN 2 THEN
                BEGIN
                   IF p_acep_lune = 1 THEN
                      lv_fecha_util := lv_fecha_util;
                    ELSE
                       lv_fecha_util := lv_fecha_util + 1;
                       lv_flag_salir := 0;
                    END IF;
                  END;
              ELSE   lv_fecha_util := lv_fecha_util;
         END CASE;

         SELECT  COUNT(*)
         INTO lv_feriado
         FROM cob_feria fe
         WHERE fe.fec_feri = lv_fecha_util;
          IF  lv_feriado > 0 THEN
             lv_fecha_util := lv_fecha_util + 1;
             lv_flag_salir := 0;
          ELSE
             IF lv_flag_salir = 0 THEN
                lv_flag_salir := 1;
             ELSE
                EXIT;
             END IF;
           END IF;
      END LOOP;

      RETURN lv_fecha_util;
   END FIN_FN_OBTIE_FECHA_UTIL;

 FUNCTION FIN_FN_OBTIE_PERIO_ACTU
 RETURN NUMBER
 IS

  lv_oid_peri     NUMBER(12);
  lv_fecha_sys   DATE;

 BEGIN

  lv_fecha_sys  := trunc(SYSDATE) ;

  SELECT  MIN(cp.oid_peri)
  INTO lv_oid_peri
  FROM  cra_perio cp
  WHERE lv_fecha_sys BETWEEN  cp.FEC_INIC AND  cp.FEC_FINA;

  RETURN lv_oid_peri;

 END FIN_FN_OBTIE_PERIO_ACTU;

 FUNCTION FIN_FN_OBTIE_DESCR_COMUN(
  p_nom_enti              gen_i18n_sicc_comun.attr_enti%TYPE,
  p_val_oid                 gen_i18n_sicc_comun.val_oid%TYPE)
 RETURN VARCHAR2
 IS

  lv_desc            gen_i18n_sicc_comun.val_i18n%TYPE;

 BEGIN

  SELECT gen.val_i18n
  INTO lv_desc
  FROM gen_i18n_sicc_comun gen
  WHERE gen.val_oid = p_val_oid
    AND gen.attr_enti = p_nom_enti;

  RETURN lv_desc;

 END FIN_FN_OBTIE_DESCR_COMUN;

 FUNCTION FIN_FN_OBTIE_DESCR_PAIS(
  p_nom_enti              gen_i18n_sicc_comun.attr_enti%TYPE,
  p_val_oid                 gen_i18n_sicc_comun.val_oid%TYPE)
 RETURN VARCHAR2
 IS

  lv_desc            gen_i18n_sicc_comun.val_i18n%TYPE;

 BEGIN

  SELECT gen.val_i18n
  INTO lv_desc
  FROM gen_i18n_sicc_pais gen
  WHERE gen.val_oid = p_val_oid
    AND gen.attr_enti = p_nom_enti;

  RETURN lv_desc;

 END FIN_FN_OBTIE_DESCR_PAIS;

 FUNCTION FIN_FN_OBTIE_DESCR_TSOLI_PAIS(
  p_oid_tipo_soli_pais           IN   NUMBER)
 RETURN VARCHAR2
 IS

  lv_desc_tipo_soli_pais         VARCHAR2(4000);

 BEGIN

  SELECT
   gen.val_i18n
  INTO
   lv_desc_tipo_soli_pais
  FROM
   ped_tipo_solic_pais ptsp,
   ped_tipo_solic pts,
   gen_i18n_sicc_comun gen
  WHERE ptsp.oid_tipo_soli_pais = p_oid_tipo_soli_pais
    AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
    AND gen.attr_enti = 'PED_TIPO_SOLIC'
    AND gen.val_oid = pts.oid_tipo_soli;

  RETURN lv_desc_tipo_soli_pais;

 EXCEPTION

  WHEN OTHERS THEN
   RETURN NULL;

 END FIN_FN_OBTIE_DESCR_TSOLI_PAIS;

 FUNCTION FIN_FN_OBTIE_NSGTE_CAMPA(
    p_cod_peri                    IN   seg_perio_corpo.cod_peri%TYPE,
    p_num_camp                    IN   NUMBER)
 RETURN VARCHAR2
 IS

 BEGIN
    RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL,
                                            p_cod_peri,
                                            p_num_camp);

 END FIN_FN_OBTIE_NSGTE_CAMPA;

 FUNCTION FIN_FN_OBTIE_RETIR_SINDE(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_imp_deud                      NUMBER(12,2);
  lv_cod_esta_clien                mae_estat_clien.cod_esta_clie%TYPE;
  lv_ind_retir_sinde               NUMBER(1):=0;

 BEGIN

  lv_cod_esta_clien := FIN_FN_OBTIE_CODIG_ESTAT_CLIEN(p_oid_clie);

  IF lv_cod_esta_clien = gc_cod_esta_clie_reti THEN

   lv_imp_deud := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_TOTAL(p_oid_clie);

   IF lv_imp_deud = 0 THEN

    lv_ind_retir_sinde := 1;

   ELSE

    lv_ind_retir_sinde := 0;

   END IF;

  ELSE

   lv_ind_retir_sinde := 0;

  END IF;

  RETURN lv_ind_retir_sinde;

 END FIN_FN_OBTIE_RETIR_SINDE;

 FUNCTION FIN_PR_ADICI_DIAS_FECHA(
  start_date_in                    IN   DATE,
  days_in                          IN   NUMBER)
 RETURN DATE
 IS

  v_counter                        NUMBER;
  v_new_date                       DATE;
  v_day_number                     NUMBER;

 BEGIN

  /* This routine will add a specified number of days (ie: days_in) to a date (ie: start_date). */
  /* It will skip all weekend days - Saturdays and Sundays */
  v_counter := 1;
  v_new_date := start_date_in;

  /* Loop to determine how many days to add */
  WHILE v_counter <= days_in
  LOOP

   /* Add a day */
   v_new_date := v_new_date + 1;
   v_day_number := to_char(v_new_date, 'd');

   /* Increment counter if day falls between Monday to Friday */
   IF v_day_number >= 2 AND v_day_number <= 6 THEN
    v_counter := v_counter + 1;
   END IF;

  END LOOP;

  RETURN v_new_date;

 EXCEPTION

  WHEN OTHERS THEN
   RETURN NULL;

 END FIN_PR_ADICI_DIAS_FECHA;

 FUNCTION FIN_FN_OBTIE_LINEA_ERROR (backtrace_in IN VARCHAR2)
 RETURN error_rt
 IS

  -- Lots of INSTRs to come; these variables keep track
  -- of the start and end points of various portions of the string.
  --l_at_loc           PLS_INTEGER;
  l_dot_loc          PLS_INTEGER;
  l_name_start_loc   PLS_INTEGER;
  l_name_end_loc     PLS_INTEGER;
  l_line_loc         PLS_INTEGER;
  l_eol_loc          PLS_INTEGER;

  retval             error_rt;

 BEGIN

  -- initialize_values
  l_name_start_loc := INSTR (backtrace_in, gc_name_deli, 1, 1);
  l_dot_loc := INSTR (backtrace_in, gc_dot_deli);
  l_name_end_loc := INSTR (backtrace_in, gc_name_deli, 1, 2);

  l_line_loc := INSTR (backtrace_in, gc_line_deli_espa);

  IF l_line_loc = 0 THEN
   l_line_loc := INSTR (backtrace_in, gc_line_deli_ingl);
  END IF;

  l_eol_loc := INSTR (backtrace_in, gc_eol_deli);

  IF l_eol_loc = 0 THEN
   l_eol_loc := LENGTH (backtrace_in) + 1;
  END IF;

  retval.program_owner :=
         SUBSTR (backtrace_in,
                 l_name_start_loc + 1,
                 l_dot_loc - l_name_start_loc - 1);

  retval.program_name :=
          SUBSTR (backtrace_in, l_dot_loc + 1, l_name_end_loc - l_dot_loc - 1);

  retval.line_number :=
             SUBSTR (backtrace_in, l_line_loc + 5, l_eol_loc - l_line_loc - 5);


  RETURN retval;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN retval;

 END FIN_FN_OBTIE_LINEA_ERROR;

 FUNCTION FIN_FN_OBTIE_CODIG_PERIO_ACTUA
 RETURN VARCHAR
 IS

  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  SELECT z.cod_peri
  INTO lv_cod_peri
  FROM bas_ctrl_fact z
  WHERE z.ind_camp_act = 1
    AND z.sta_camp = 0;

  RETURN lv_cod_peri;

 END FIN_FN_OBTIE_CODIG_PERIO_ACTUA;

 FUNCTION FIN_FN_OBTIE_OID_ZONA_CONSU(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_oid_zona                      zon_zona.oid_zona%TYPE;

 BEGIN

  SELECT MIN(zz.oid_zona)
  INTO lv_oid_zona
  FROM
   mae_clien_unida_admin mcua,
   zon_terri_admin zta,
   zon_secci zs,
   zon_zona zz
  WHERE mcua.clie_oid_clie = p_oid_clie
    AND mcua.ind_acti = 1
    AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
    AND zta.zscc_oid_secc = zs.oid_secc
    AND zs.zzon_oid_zona = zz.oid_zona;

  RETURN lv_oid_zona;

 END FIN_FN_OBTIE_OID_ZONA_CONSU;

 FUNCTION FIN_FN_OBTIE_FECHA_CRONO_ACTIV(
  p_oid_peri                       IN   cra_perio.oid_peri%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE,
  p_cod_acti                       IN   cra_activ.cod_acti%TYPE)
 RETURN DATE
 IS

  lf_fec_inic_acti                 DATE;

 BEGIN

  SELECT cr.fec_inic
  INTO lf_fec_inic_acti
  FROM
   cra_crono cr,
   cra_activ ca
  WHERE ca.oid_acti = cr.cact_oid_acti
    AND cr.perd_oid_peri = p_oid_peri
    AND cr.zzon_oid_zona = p_oid_zona
    AND ca.cod_acti = p_cod_acti;

  RETURN lf_fec_inic_acti;

 EXCEPTION

  WHEN no_data_found THEN
   RETURN TO_DATE(SYSDATE,'DD/MM/YYYY');

 END FIN_FN_OBTIE_FECHA_CRONO_ACTIV;

 FUNCTION FIN_FN_OBTIE_OBSER_BLOQU(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN VARCHAR2
 IS

  lv_oid_bloq                    mae_clien_bloqu.oid_bloq%TYPE;
  lv_des_bloq                    VARCHAR2(2000);

 BEGIN

  SELECT MIN(mcb.oid_bloq)
  INTO lv_oid_bloq
  FROM
   mae_clien_bloqu mcb
  WHERE mcb.clie_oid_clie = p_oid_clie
    AND mcb.fec_desb IS NULL;

  SELECT
   mcb.val_moti_bloq || ' - ' || mcb.obs_bloq
  INTO lv_des_bloq
  FROM
   mae_clien_bloqu mcb,
   mae_tipo_bloqu mtb
  WHERE mcb.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
    AND mcb.oid_bloq = lv_oid_bloq
    AND mcb.clie_oid_clie = p_oid_clie;

  RETURN lv_des_bloq;

 EXCEPTION

  WHEN OTHERS THEN
   RETURN ' ';

 END FIN_FN_OBTIE_OBSER_BLOQU;

 FUNCTION FIN_FN_OBTIE_MONTO_PEDID(
  p_oid_clie                       IN   NUMBER,
  p_cod_peri                       IN   VARCHAR2)
 RETURN NUMBER
 IS

  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_oid_soli_cabe                 ped_solic_cabec.oid_soli_cabe%TYPE;
  lv_soca_oid_soli_cabe            ped_solic_cabec.soca_oid_soli_cabe%TYPE;
  lv_mont_pedi                     ped_solic_cabec.val_tota_paga_loca%TYPE;
  lv_oid_tipo_soli                 ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;

 BEGIN

  lv_oid_tipo_soli := FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('SOC');

  lv_oid_peri := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_peri);

  SELECT MIN(a.oid_soli_cabe)
  INTO lv_oid_soli_cabe
  FROM
     ped_solic_cabec a
  WHERE a.clie_oid_clie = p_oid_clie
    AND a.perd_oid_peri = lv_oid_peri
    AND a.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli
    AND a.ind_oc = 1
    AND a.fec_fact IS NOT NULL
    AND a.esso_oid_esta_soli <> 4;

  SELECT psc.soca_oid_soli_cabe
  INTO lv_soca_oid_soli_cabe
  FROM ped_solic_cabec psc
  WHERE psc.oid_soli_cabe = lv_oid_soli_cabe;

  SELECT SUM(mcc.imp_movi)
  INTO lv_mont_pedi
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie = p_oid_clie
    AND mcc.perd_oid_peri = lv_oid_peri
    AND mcc.soca_oid_soli_cabe = lv_soca_oid_soli_cabe
    AND mcc.subp_oid_subp_crea = 2001
    AND mcc.imp_movi > 0;

  RETURN lv_mont_pedi;

 EXCEPTION
   WHEN NO_DATA_FOUND THEN
    RETURN 0;

 END FIN_FN_OBTIE_MONTO_PEDID;

 FUNCTION FIN_FN_OBTIE_TEXTO_SINCA_ESPEC(
  p_val_text                       IN   VARCHAR2)
 RETURN VARCHAR2
 IS
 
  lv_val_text                      VARCHAR2(4000);
  
 BEGIN
  
  --dato sin enter 
  lv_val_text := REPLACE(p_val_text,chr(13)||chr(10), ' ');
  
  --dato sin tabulado
  lv_val_text := REPLACE(lv_val_text,chr(9), ' ');
     
 RETURN lv_val_text;
           
 END FIN_FN_OBTIE_TEXTO_SINCA_ESPEC;
 
 FUNCTION FIN_FN_OBTIE_CODIG_POSTA(
  p_oid_clie                       IN   NUMBER)
 RETURN VARCHAR2
 IS
 
  lv_val_cod_post                  mae_clien_direc.val_cod_post%TYPE;
  
 BEGIN
 
  SELECT mcd.val_cod_post
  INTO lv_val_cod_post 
  FROM mae_clien_direc mcd
  WHERE mcd.clie_oid_clie = p_oid_clie
    AND mcd.ind_dire_ppal = 1;
  
  RETURN lv_val_cod_post;
    
 END FIN_FN_OBTIE_CODIG_POSTA;
 
 FUNCTION FIN_FN_OBTIE_NUMER_PEDID(
  p_oid_clie                       IN   NUMBER
 )
 RETURN NUMBER
 IS
 
  lv_val_nume_pedi                 NUMBER(12);
  
 BEGIN
 
  SELECT mce.val_mont_fact
  INTO lv_val_nume_pedi
  FROM mae_clien_estat mce  
  WHERE mce.oid_clie = p_oid_clie;
  
  RETURN lv_val_nume_pedi;
    
 END FIN_FN_OBTIE_NUMER_PEDID;
 
 FUNCTION fin_pr_gener_fila_tabla_html(
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

 END fin_pr_gener_fila_tabla_html;

END FIN_PKG_GENER;
/
