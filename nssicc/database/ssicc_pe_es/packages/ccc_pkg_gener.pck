CREATE OR REPLACE PACKAGE "CCC_PKG_GENER" is

 /* Declaracion de Variables */
 ln_sqlcode   NUMBER(10);
 ls_sqlerrm   VARCHAR2(150);
 W_FILAS      NUMBER:=1000;

 /**************************************************************************
  Descripcion       : Eliminar el Movimiento de Cuenta Corriente recibido por parametro
  Fecha Creacion    : 15/07/2003
  Parametros Entrada :
             Oid del Movimiento de Cuenta Corriente
 ***************************************************************************/
 PROCEDURE CCC_PR_ELIMI_MOVIM_CUENT_CORRI(
  p_oid_movi_cc                    IN   ccc_movim_cuent_corri.oid_movi_cc%TYPE);

 /**************************************************************************
  Descripcion       : Eliminar el Movimiento Bancario recibido por parametro
  Fecha Creacion    : 15/07/2003
  Parametros Entrada :
              Oid del Movimiento Bancario
 ***************************************************************************/
  PROCEDURE CCC_PR_ELIMI_MOVIM_BANCA(
  p_oid_movi_banc                  IN   ccc_movim_banca.oid_movi_banc%TYPE);

 /**************************************************************************
  Descripcion       : Obtiene numero de secuencia desde ccc_secue_pais para
                      asignar al campo NumCabec de ccc_cabec_carga_abono_direc

  Fecha Creacion    : 12/01/2009
  Parametros Entrada :
             Codigo de Pais
             A?o
             Mwes
  Parametros de Salida :
             Numero de secuencia disponible
 ***************************************************************************/
 PROCEDURE CCC_PR_OBTIE_NUMER_SECUE(
  p_cod_pais                       IN    seg_pais.cod_pais%TYPE,
  p_num_anio                       OUT   ccc_movim_cuent_corri.val_docu_anio%TYPE,
  p_num_mes                        OUT   ccc_movim_cuent_corri.val_docu_mes_seri%TYPE,
  p_num_secu                       OUT   ccc_secue_pais.num_secu%TYPE);

 /**************************************************************************
  Descripcion       : Actualiza en ccc_secue_pais el ultimo numero de secuencia
                      utilizado.

  Fecha Creacion    : 12/01/2009
  Parametros Entrada :
             Codigo de Pais
             A?o
             Mwes
             Ultimo numero de secuencia usado
 ***************************************************************************/
 PROCEDURE CCC_PR_LIBER_NUMER_SECUE(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_num_anio                       IN   ccc_secue_pais.num_anio%TYPE,
  p_num_mes                        IN   ccc_secue_pais.num_mes%TYPE,
  P_num_secu                       IN   ccc_secue_pais.num_secu%TYPE);

 /**************************************************************************

  Descripcion       : Actualiza en ccc_secue_pais el ultimo numero de secuencia
                      utilizado.

  Fecha Creacion    : 12/01/2009
  Parametros Entrada :
   Codigo de Pais
   A?o
   Mwes
   Ultimo numero de secuencia usado
 ***************************************************************************/
 PROCEDURE CCC_PR_LIBER_NUMER_SECUE(
  p_num_anio                       IN   ccc_secue_pais.num_anio%TYPE,
  p_num_mes                        IN   ccc_secue_pais.num_mes%TYPE,
  P_num_secu                       IN   ccc_secue_pais.num_secu%TYPE);

   /**************************************************************************
   Descripcion       : Inserta registro cabecera de cargo/abono segun Tipo Abono Subproceso
                      dado por parametro

   Fecha Creacion    : 12/01/2009
   Parametros Entrada :
              Oid del Pais,
             Oid de la  Sociedad,
            Oid del Proceso,
            Oide del Tipo Abono Subproceso
   Parametros de Salida:
              Oid del registro creado
   ***************************************************************************/
    PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais                                     IN         seg_pais.oid_pais%TYPE,
      p_oid_socie                                    IN        seg_socie.oid_soci%TYPE,
      p_oid_proc                                     IN         ccc_proce.oid_proc%TYPE,
      p_oid_tipo_abon_subp                   IN         ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_cabe_carg                            OUT      ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE,
      p_num_anio                                     OUT      ccc_cabec_carga_abono_direc.anio%TYPE,
      p_num_mes                                      OUT      ccc_cabec_carga_abono_direc.val_peri_mes%TYPE,
      p_num_docu                                     OUT      ccc_cabec_carga_abono_direc.num_cabe%TYPE);

   /**************************************************************************
   Descripcion       : Inserta registro cabecera de cargo/abono segun Tipo Abono Subproceso
                      dado por parametro

   Fecha Creacion    : 12/01/2009
   Parametros Entrada :
              Oid del Pais,
             Oid de la  Sociedad,
            Oid del Proceso,
            Oide del Tipo Abono Subproceso
   Parametros de Salida:
              Oid del registro creado
   ***************************************************************************/
   PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais                                        IN  seg_pais.oid_pais%TYPE,
      p_oid_socie                                       IN  seg_socie.oid_soci%TYPE,
      p_oid_proc                                        IN  ccc_proce.oid_proc%TYPE,
      p_oid_tipo_abon_subp                      IN  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_cabe_carg                              OUT ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE
      );

    /**************************************************************************
   Descripcion       : Inserta registro cabecera de cargo/abono segun Tipo Abono Subproceso
                      dado por parametro

   Fecha Creacion    : 12/01/2009
   Parametros Entrada :
              Oid del Pais,
              Oid de la  Sociedad,
              Oid del Proceso,
              Oide del Tipo Abono Subproceso
   Parametros de Salida:
              Oid del registro creado
   ***************************************************************************/
    PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais                            IN  seg_pais.oid_pais%TYPE,
      p_oid_socie                           IN  seg_socie.oid_soci%TYPE,
      p_oid_cod_proc                    IN  ccc_proce.oid_proc%TYPE,
      p_num_anio                            IN  ccc_movim_cuent_corri.val_docu_anio%TYPE,
      p_num_mes                             IN  ccc_movim_cuent_corri.val_docu_mes_seri%TYPE,
      p_num_secu                            IN  ccc_secue_pais.num_secu%TYPE,
      p_num_lote                            IN  per_solic_monet.num_lote%TYPE,
      p_oid_tipo_abon_subp           IN  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_cod_tipo_soli                      IN  per_solic_monet.cod_tipo_soli%TYPE,
      p_oid_cuen_corr_banc           IN  ccc_asign_subpr_tipo_solic.ccba_oid_cuen_corr_banc%TYPE,
      p_oid_sbac                              IN  ped_tipo_solic.sbac_oid_sbac%TYPE,
      p_oid_cabe_carg                    OUT ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE
      );

   /**************************************************************************
   Descripcion       : Procedimiento que te permite actualizar un movimiento
                       de cuenta corriente, luego de una aplicacion

   Fecha Creacion    : 12/01/2009
   Parametros Entrada :
               Oid Movimiento Cuenta Corriente
               Fecha Ultimo Movimiento
               Anio Cabecera de Aplicacion
               Mes Cabecera de Aplicacion
               Documento Cabecera de Aplicacion
               Oid Subproceso de Aplicacion
   ***************************************************************************/
   PROCEDURE CCC_PR_ACTUA_CUENT(
     p_oid_clie                   IN mae_clien.oid_clie%TYPE,
     p_oid_movi_carg          IN     ccc_movim_cuent_corri.oid_movi_cc%TYPE,
     p_fec_ulti_movi            IN   ccc_movim_cuent_corri.fec_ulti_movi%TYPE,
     p_oid_cabe_carg_apli      IN   ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE,
     p_val_ulti_docu_anio       IN  ccc_movim_cuent_corri.val_ulti_docu_anio%TYPE,
     p_val_ulti_docu_mes         IN  ccc_movim_cuent_corri.val_ulti_docu_mes_seri%TYPE,
     p_val_ulti_docu_nume        IN  ccc_movim_cuent_corri.val_ulti_docu_nume%TYPE,
     p_oid_subp_ulti              IN ccc_subpr.oid_subp%TYPE,
     p_oid_tcab_ulti              IN ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
     p_oid_marc_situ              IN ccc_marca_situa.oid_marc_situ%TYPE,
     p_imp_paga                   IN ccc_movim_cuent_corri.imp_paga%TYPE,
     p_imp_pend                   IN ccc_movim_cuent_corri.imp_pend%TYPE,
     p_imp_pago                   IN ccc_movim_cuent_corri.imp_pago%TYPE,
     p_oid_movi_abon              IN  ccc_movim_cuent_corri.oid_movi_cc%TYPE,
     p_ind_tipo_abon            IN  ccc_proce_subpr_creac_aplic.ind_abon_nomo%TYPE,
     p_cod_usu                    IN ccc_movim_cuent_corri.cod_usua%TYPE);

   /**************************************************************************
   Descripcion       : Obtiene parametria correspondiente a un Tipo de Solicitud

   Fecha Creacion    : 12/01/2009
   Parametros Entrada :
               Oid del Pais
              Codigo del Tipo de Solicitud
   Parametros de Salida :
             Parametria para el Tipo de solicitud
   ***************************************************************************/
   PROCEDURE CCC_PR_OBTIE_PARAM_TIPO_SOLIC(
      p_oid_pais                                          IN   seg_pais.oid_pais%TYPE,
      p_cod_tipo_soli                                  IN   ped_tipo_solic.cod_tipo_soli%TYPE,
      p_cod_proc                                        OUT  ccc_proce.cod_proc%TYPE,
      p_oid_proc                                         OUT  ccc_proce.oid_proc%TYPE,
      p_cod_subp                                        OUT  ccc_subpr.cod_subp%TYPE,
      p_oid_subp                                         OUT  ccc_subpr.oid_subp%TYPE,
      p_oid_cuen_corr_banc                      OUT  ccc_asign_subpr_tipo_solic.ccba_oid_cuen_corr_banc%TYPE,
      p_cod_tipo_carg_abon                      OUT  ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE,
      p_oid_tipo_carg_abon                       OUT  ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
      p_masi_oid_marc_sali                       OUT  ccc_marca_tipo_abono.masi_oid_marc_sali%TYPE,
      p_oid_cuen_cont                               OUT  ccc_tipo_abono_subpr.cuco_oid_cuen_cont%TYPE,
      p_oid_marc                                        OUT  ped_tipo_solic.marc_oid_marc%TYPE,
      p_oid_sbac                                         OUT  ped_tipo_solic.sbac_oid_sbac%TYPE,
      p_oid_medi_pago                               OUT  bel_forma_pago_detal.mpab_oid_medi_pago%TYPE,
      p_tsol_oid_tipo_cons                        OUT  ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE,
      p_oid_tipo_abon_subp                       OUT  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_ind_soli_nega                                  OUT  ped_tipo_solic.ind_soli_nega%TYPE     );

 /**************************************************************************
   Descripcion        : Registra un Lote Bancario en la entidad
                                    CCC_NUMER_LOTE_BANCA_RECEP
   Fecha Creacion     : 09/07/2009

   Autor              : Jorge Florencio
 ***************************************************************************/
 PROCEDURE CCC_PR_REGIS_LOTE_BANCA(
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_tipo_orig                  IN   ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE,
  p_num_lote                       IN   ccc_movim_banca.num_lote%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);

 PROCEDURE CCC_PR_GENER_CABEC_LOTE_BANCA(
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_tipo_orig                  IN   ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE,
  p_num_lote                       IN   ccc_movim_banca.num_lote%TYPE,
  p_num_lote_mult                  IN   ccc_movim_banca.num_lote%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE);
    /**************************************************************************
     Descripcion        : Proceso que obtiene la Parametria asociada a un
                                 Tipo Abono Subproceso
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
   PROCEDURE CCC_PR_OBTIE_PARAM_TASUB(
      p_oid_tipo_abon_subp                           IN      ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_proc                                             OUT   ccc_proce.oid_proc%TYPE,
      p_oid_subp                                             OUT   ccc_subpr.oid_subp%TYPE,
      p_oid_cuen_cont                                    OUT   ccc_tipo_abono_subpr.cuco_oid_cuen_cont%TYPE,
      p_indi_tipo_abon                                   OUT    ccc_subpr.val_indi_tipo_abon%TYPE,
      p_oid_marc_situ                                    OUT    ccc_marca_situa.oid_marc_situ%TYPE,
      p_oid_tipo_carg_abon                            OUT   ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
      p_val_indi_cons                                      OUT   ccc_subpr.val_indi_cons%TYPE);

 PROCEDURE CCC_PR_OBTIE_PARAM_FACTU(
  p_oid_peri                       OUT   cra_perio.oid_peri%TYPE,
  p_cod_peri                       OUT   seg_perio_corpo.cod_peri%TYPE,
  p_fec_fact                       OUT   bas_ctrl_fact.fec_proc%TYPE);



  /**************************************************************************
     Descripcion        : Devuelve el Valor del Parametro Alfanumerico Especificado.
     Fecha Creacion     : 31/08/2010

     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_PARAM_GENER(
      p_cod_para                   IN       ccc_param_gener.cod_para%TYPE)
   RETURN VARCHAR2;

   /**************************************************************************
     Descripcion        : Obtiene el numero de lote actual.
     Fecha Creacion     : 31/08/2010

     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_NUMER_LOTE
   RETURN NUMBER;

   /**************************************************************************
     Descripcion        : Devuelve el OID de un proceso de Cuenta Corriente
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
    FUNCTION CCC_FN_OBTIE_PARAM_OID_PROCE(
      p_cod_proc       IN    ccc_proce.cod_proc%TYPE)
   RETURN NUMBER;

   /**************************************************************************
     Descripcion        : Devuelve el OID de un subproceso de Cuenta Corriente
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_PARAM_OID_SUBPR(
      p_cod_proc       IN    ccc_proce.cod_proc%TYPE,
      p_cod_subp       IN    ccc_subpr.cod_subp%TYPE)
   RETURN NUMBER;

   /**************************************************************************
     Descripcion        : Devuelve el OID de un tipo cargo abono de Cuenta Corriente
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
    FUNCTION CCC_FN_OBTIE_PARAM_OID_TICAB
      (p_oid_subp     IN   ccc_subpr.oid_subp%TYPE)
    RETURN NUMBER;
      /**************************************************************************
     Descripcion        : Devuelve el OID de un tipo abono por subproceso
                                  de Cuenta Corriente
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_PARAM_OID_TASUB
     (p_oid_subp                                     IN      ccc_subpr.oid_subp%TYPE)
   RETURN NUMBER;
   /**************************************************************************
     Descripcion        : Devuelve el OID de la Subgerencia de Ventas
     Fecha Creacion     : 09/07/2009

     Autor              : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_OID_SUBGE_VENTA(
      p_oid_pais                                     IN      seg_pais.ind_sald_unic%TYPE,
      p_oid_marc                                    IN     seg_marca.oid_marc%TYPE,
      p_cod_sbac                                    IN     seg_subac.cod_sbac%TYPE)
   RETURN NUMBER;

 /**************************************************************************
  Descripcion        : Devuelve el Numero Identificador de Cuota disponible
                               para Generacion de Movimientos en Cuenta Corriente
  Fecha Creacion     : 09/07/2009

  Autor              : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
  p_cod_pais                       IN    seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN    seg_socie.cod_soci%TYPE,
  p_cod_suba                       IN    seg_subac.cod_sbac%TYPE)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion        : Devuelve el Numero Identificador de Cuota disponible
                               para Generacion de Movimientos en Cuenta Corriente
  Fecha Creacion     : 09/07/2009

  Autor              : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
  p_cod_suba                       IN   seg_subac.cod_sbac%TYPE)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion        : Devuelve el Numero Identificador de Cuota disponible
                          para Generacion de Movimientos en Cuenta Corriente
  Fecha Creacion     : 09/07/2009

  Autor              : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
  p_cod_pais                       IN    seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN    seg_socie.cod_soci%TYPE,
  p_cod_suba                       IN    seg_subac.cod_sbac%TYPE,
  p_can_iden                       IN    NUMBER)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                               separndo n Numeros para inserciones.
  Fecha Creacion     : 11/05/2009

  Autor              : Jorge Florencio
  ***************************************************************************/
 FUNCTION CCC_FN_DEVUE_SECUE_NSOLI(
  p_cod_pais                     IN   VARCHAR2,
  p_cod_oper                     IN   VARCHAR2,
  p_cod_acce                     IN   VARCHAR2,
  p_cod_suba                     IN   VARCHAR2)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_TOTAL(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo de las Campa?as Anteriores a la actual
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_ANTER(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo de las Campa?as Anteriores a la actual
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Devuelve el Saldo de las Campa?as Anteriores a la actual
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_VENTA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_CARTE(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_SALDO_ACUMU_CAMPA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Vencido  a una fecha ingresada por parametro
                                  (Saldo Comercial )
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_VENCI(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_fec_venc                     IN   DATE DEFAULT SYSDATE)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Vencido de una Consultora
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_PVENCE(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total (Saldo Contable) en base al
                                Movimiento de Cuenta Corriente
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total (Saldo Contable ) en base al
                                 Movimiento de Cuenta Corriente
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total (Saldo Contable ) en base al
                         Movimiento de Cuenta Corriente Historica
  Fecha Creacion    : 25/08/2011
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL_HISTO(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total (Saldo Contable ) en base al
                         Movimiento de Cuenta Corriente Historica
  Fecha Creacion    : 25/08/2011
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL_HISTO(
  p_cod_clie                IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_SALDO_PAGAR(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total a Pagar para EFTP
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER;

 /***************************************************************************
  Descripcion       : Devuelve el Saldo Total (Saldo Contable)  en base al
                                 Detalle de Cargos y Abonos Directos
  Fecha Creacion    : 25/02/2009
  Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_DETAL (
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_fecha                        IN   DATE DEFAULT SYSDATE)
 RETURN NUMBER;

 /***************************************************************************
   Descripcion       : Obtiene el Saldo Contable a una fecha determinada,
                                Considera esta fecha como fecha de proceso de los bancos
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_CONTA(
  p_oid_clie                              IN mae_clien.oid_clie%TYPE,
  p_fec_cont                            IN DATE)
 RETURN NUMBER;

 /***************************************************************************
   Descripcion       : Obtiene el Saldo Comercial a una fecha determinada.
                                 Considera esta fecha como fecha de los pagos de bancos
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_COMER(
  p_oid_clie                                IN mae_clien.oid_clie%TYPE,
  p_fec_pago                             IN DATE)
 RETURN NUMBER;

/***************************************************************************
   Descripcion       : Obtiene el Saldo en base a la fecha de facturacion del cronograma
                       para la zona determinada.
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_ZONAS_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE)
 RETURN NUMBER;

    /***************************************************************************
     Descripcion       : Devuelve el Codigo del Cargo y Abono para el pais, registrado
                                 en la entidad CCC_TIPO_CARGO_ABONO.
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_CODIG_CARGO_ABONO(
      p_oid_pais                            IN seg_pais.oid_pais%TYPE,
      p_oid_tipo_carg_abon         IN ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE)
   RETURN VARCHAR2;

    /***************************************************************************
     Descripcion       : Devuelve la descripcion del Cargo y Abono para el pais, registrado
                                 en la entidad CCC_TIPO_CARGO_ABONO.
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_DESCR_CARGO_ABONO(
      p_oid_tipo_carg_abon                  IN ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Obtiene el Oid de un  Codigo de Cuenta Corriente Bancaria
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
    FUNCTION CCC_FN_OBTIE_CODIG_BANCO_CCBAN(
      p_cod_cban                            IN ccc_cuent_corri_banca.cod_cc%TYPE)
   RETURN NUMBER;

   /***************************************************************************
     Descripcion       : Obtiene el Oid de un  Codigo de Cuenta Corriente Bancaria
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_OID_BANCO_CCBAN(
      p_cod_cban                            IN ccc_cuent_corri_banca.cod_cc%TYPE)
   RETURN NUMBER;

   /***************************************************************************
     Descripcion       : Obtiene la Fecha del Ultimo Cargo en Cuenta Corriente
                         anterior a la fecha enviada por Parametro
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_FECHA_ULTIM_CARGO(
      p_oid_clie                            IN mae_clien.oid_clie%TYPE,
      p_fec_refe                           IN DATE DEFAULT SYSDATE)
   RETURN VARCHAR2;

   /***************************************************************************
     Descripcion       : Obtiene la Campa?a del Ultimo Cargo en Cuenta Corriente
                         anterior a la fecha enviada por Parametro
    Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
   ***************************************************************************/
   FUNCTION CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(
      p_oid_clie                            IN mae_clien.oid_clie%TYPE,
      p_fec_refe                          IN DATE DEFAULT SYSDATE)
   RETURN VARCHAR2;

 /***************************************************************************
  Descripcion       :  Obtiene el Saldo Unico de una Consultora
  Fecha Creacion    : 15/07/2003
  Autor             : Jorge Florencio
 *****************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_UNICO(
  p_oid_clie mae_clien.oid_clie%TYPE)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente restando el valor en cupones
  Fecha Creacion : 15/07/2003
  Fecha Modificacion: 15/07/2003
  Parametros Entrada:
    ps_cod_clie : Codigo de cliente
  Autor : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_CALCU_VALOR_SALDO_DEUDO(
  p_cod_clie      IN   VARCHAR2)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion : Funcion que valida la existencia de un cupon
  Fecha Creacion : 15/07/2003
  Fecha Modificacion: 15/07/2003
  Parametros Entrada:
    p_cod_clie : Codigo de cliente
    p_fec_pago : Fecha de Pago
    p_imp_pago : Importe de Pago

  Autor : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_VALID_EXIST_CUPON(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE,
  p_fec_pago                     IN   int_solic_conso_cupon_pago.fec_proc%TYPE,
  p_imp_pago                     IN   VARCHAR2)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_MONTO_FLEXI_PEDID(
  p_oid_soli_cabe                       IN   ped_solic_cabec.oid_soli_cabe%TYPE)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion : Funcion para obtener el saldo Flexipago Anterior a la
                campa?a actual.
  Fecha Creacion : 15/07/2003
  Fecha Modificacion: 15/07/2003
  Parametros Entrada:
    p_oid_clie : Oid del Cliente
    p_cod_peri : Codigo de Periodo

  Autor : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SALDO_FLEXI_ANTER(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

 /**************************************************************************
  Descripcion : Funcion para obtener el monto Flexipago en la
                campa?a actual.
  Fecha Creacion : 15/07/2003
  Fecha Modificacion: 15/07/2003
  Parametros Entrada:
    p_oid_clie : Oid del Cliente
    p_cod_peri : Codigo de Periodo

  Autor : Jorge Florencio
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_MONTO_FLEXI_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_SALDO_PROXI_PEDID(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_ABONO_PEND(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER;

 FUNCTION CCC_FN_OBTIE_INTER_FLEXI_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER;

/***************************************************************************
  Descripcion       : Genera la data de ped_solic_cabec,ped_solic_posic y
                         las tablas de region zona y seccion para la busqueda de
                         Diferencia de Precios de Cuenta Corriente.
  Fecha Creacion    : 23/04/2010
  Autor             : Jesse Rios
 ***************************************************************************/
 PROCEDURE CCC_PR_GENER_DIFER_PRECIO(psPrecioCorrecto VARCHAR2,
                                       psCodigoVenta    VARCHAR2,
                                     pnOidPeriodo     NUMBER,
                                     psCodigoRegion   VARCHAR2,
                                     psCodigoZona     VARCHAR2,
                                     psCodigoConsultora VARCHAR2);

 FUNCTION CCC_FN_OBTIE_MTMIN_DEUDA_WEB(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_sal_deud_ante                IN   mae_clien.sal_deud_ante%TYPE DEFAULT NULL)
 RETURN NUMBER;

/***************************************************************************
  Descripcion       : Función que obtiene la sumatoria de todos los tipos de
                      documento del Resumen de Ventas del tipo Consolidación
                      de Factura Activa.
  Fecha Creacion    : 27/10/2015
  Autor             : Aurelio Oviedo
 ***************************************************************************/
FUNCTION CCC_FN_OBTIE_SUMAT_TIPO_DOCUM(
  p_fecha_desde                IN   VARCHAR2,
  p_fecha_hasta                IN   VARCHAR2)
 RETURN NUMBER;
 
/***************************************************************************
  Descripcion       : Función que obtiene el valor de las facturas anuladas 
                      del Resumen de Ventas del tipo Consolidación de Factura Activa.
  Fecha Creacion    : 27/10/2015
  Autor             : Aurelio Oviedo
 ***************************************************************************/
FUNCTION CCC_FN_OBTIE_VALOR_FACTU_ANULA(
  p_fecha_desde                IN   VARCHAR2,
  p_fecha_hasta                IN   VARCHAR2)
 RETURN NUMBER;

END CCC_PKG_GENER;
/
CREATE OR REPLACE PACKAGE BODY "CCC_PKG_GENER" is

  gc_cod_tipo_orig_digi           CONSTANT CHAR(3):='DIG';
  gc_cod_tipo_orig_exce           CONSTANT CHAR(3):='EXC';
  gc_cod_tipo_orig_regu_digi      CONSTANT CHAR(3):='REG';
  gc_cod_tipo_orig_regu_exce      CONSTANT CHAR(3):='REX';
  lv_no_data VARCHAR2(1):=' ';

 PROCEDURE CCC_PR_ELIMI_MOVIM_CUENT_CORRI(
  p_oid_movi_cc                    IN   ccc_movim_cuent_corri.oid_movi_cc%TYPE)
 IS
 BEGIN

  DELETE FROM ccc_detal_cargo_abono_direc det
  WHERE det.mvcc_oid_movi_cc = p_oid_movi_cc;

  DELETE FROM ccc_histo_movim_cc his
  WHERE his.mvcc_oid_movi_cc = p_oid_movi_cc;

  DELETE FROM ccc_movim_cuent_corri mcc
  WHERE mcc.oid_movi_cc = p_oid_movi_cc;

 END CCC_PR_ELIMI_MOVIM_CUENT_CORRI;

 PROCEDURE CCC_PR_ELIMI_MOVIM_BANCA(
  p_oid_movi_banc                  IN   ccc_movim_banca.oid_movi_banc%TYPE)
 IS
 BEGIN

  DELETE FROM ccc_detal_cargo_abono_direc det
  WHERE det.cmba_oid_movi_banc = p_oid_movi_banc;

  DELETE FROM ccc_movim_banca mb
  WHERE mb.oid_movi_banc = p_oid_movi_banc;

 END CCC_PR_ELIMI_MOVIM_BANCA;

 PROCEDURE CCC_PR_OBTIE_NUMER_SECUE(
       p_cod_pais        IN    seg_pais.cod_pais%TYPE,
       p_num_anio        OUT   ccc_movim_cuent_corri.val_docu_anio%TYPE,
       p_num_mes         OUT   ccc_movim_cuent_corri.val_docu_mes_seri%TYPE,
       p_num_secu        OUT   ccc_secue_pais.num_secu%TYPE)
   IS

      lv_num_secu          ccc_secue_pais.num_secu%TYPE;
      lv_docu_anio         ccc_movim_cuent_corri.val_docu_anio%TYPE;
      lv_docu_mes          ccc_movim_cuent_corri.val_docu_mes_seri%TYPE;

      lv_year              NUMBER(2);
      lv_mes               NUMBER(2);

   BEGIN
      lv_docu_anio  := to_char(SYSDATE, 'YY');
      lv_docu_mes   := to_char(SYSDATE, 'MM');

      lv_year       := lv_docu_anio;
      lv_mes        := lv_docu_mes;

      SELECT sec.num_secu
      INTO   lv_num_secu
      FROM   ccc_secue_pais sec
      WHERE sec.pais_oid_pais = ( SELECT  oid_pais  FROM seg_pais pa
                                     WHERE pa.cod_pais = p_cod_pais )
      AND  sec.num_anio = lv_year
      AND  sec.num_mes  = lv_mes
      FOR UPDATE;

      lv_num_secu := lv_num_secu + 1;
      p_num_secu  := lv_num_secu;
      p_num_anio  := lv_docu_anio;
      p_num_mes   := lv_docu_mes;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_OBTIE_NUMER_SECUE: ' ||
                               ls_sqlerrm);

 END CCC_PR_OBTIE_NUMER_SECUE;

   PROCEDURE CCC_PR_LIBER_NUMER_SECUE(
      p_cod_pais        seg_pais.cod_pais%TYPE,
      p_num_anio        ccc_secue_pais.num_anio%TYPE,
      p_num_mes         ccc_secue_pais.num_mes%TYPE,
      P_num_secu        ccc_secue_pais.num_secu%type
      )
   IS

   BEGIN

      UPDATE  ccc_secue_pais sec
      SET  sec.num_secu = p_num_secu
      WHERE sec.pais_oid_pais = ( SELECT  oid_pais  FROM seg_pais pa
                                     WHERE pa.cod_pais = p_cod_pais)
      AND  sec.num_anio = p_num_anio
      AND  sec.num_mes  = p_num_mes;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_LIBER_NUMER_SECUE: ' ||
                               ls_sqlerrm);

   END CCC_PR_LIBER_NUMER_SECUE ;

 PROCEDURE CCC_PR_LIBER_NUMER_SECUE(
  p_num_anio                       IN   ccc_secue_pais.num_anio%TYPE,
  p_num_mes                        IN   ccc_secue_pais.num_mes%TYPE,
  P_num_secu                       IN   ccc_secue_pais.num_secu%TYPE)
 IS

  BEGIN

   UPDATE  ccc_secue_pais sec
   SET  sec.num_secu = p_num_secu
   WHERE sec.num_anio = p_num_anio
     AND  sec.num_mes  = p_num_mes;

 EXCEPTION

 WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(SQLERRM, 1, 250);
  raise_application_error(-20123,
                              'ERROR CCC_PR_LIBER_NUMER_SECUE: ' ||
                               ls_sqlerrm);

 END CCC_PR_LIBER_NUMER_SECUE ;


   PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais                                     IN         seg_pais.oid_pais%TYPE,
      p_oid_socie                                    IN        seg_socie.oid_soci%TYPE,
      p_oid_proc                                     IN         ccc_proce.oid_proc%TYPE,
      p_oid_tipo_abon_subp                   IN         ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_cabe_carg                            OUT      ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE,
      p_num_anio                                     OUT      ccc_cabec_carga_abono_direc.anio%TYPE,
      p_num_mes                                      OUT      ccc_cabec_carga_abono_direc.val_peri_mes%TYPE,
      p_num_docu                                     OUT      ccc_cabec_carga_abono_direc.num_cabe%TYPE)
   IS
       reg_ccc_cabec_carga_abono CCC_CABEC_CARGA_ABONO_DIREC%ROWTYPE;

       lv_cod_pais            seg_pais.cod_pais%TYPE;

   BEGIN

       /*  Obtiene Codigo de Pais */
       SELECT pai.cod_pais
       INTO   lv_cod_pais
       FROM   seg_pais pai
       WHERE  pai.oid_pais = p_oid_pais;

       /*  obtiene numero de secuencia disponible para crear cabecera */
       ccc_pr_obtie_numer_secue( lv_cod_pais, p_num_anio, p_num_mes, p_num_docu);

       SELECT ccc_ccad_seq.NEXTVAL
       INTO p_oid_cabe_carg
       FROM dual;

         reg_ccc_cabec_carga_abono.oid_cabe_carg           := p_oid_cabe_carg;
         reg_ccc_cabec_carga_abono.pais_oid_pais           := p_oid_pais;
         reg_ccc_cabec_carga_abono.num_cabe                := p_num_docu;
         reg_ccc_cabec_carga_abono.anio                    := p_num_anio;
         reg_ccc_cabec_carga_abono.val_peri_mes            := p_num_mes;
         reg_ccc_cabec_carga_abono.soci_oid_soci           := p_oid_socie;
         reg_ccc_cabec_carga_abono.val_refe_exte           := NULL;
         reg_ccc_cabec_carga_abono.val_obse                := NULL;
         reg_ccc_cabec_carga_abono.ccpr_oid_proc           := p_oid_proc;
         reg_ccc_cabec_carga_abono.tasp_oid_tipo_abon_subp := p_oid_tipo_abon_subp;
         reg_ccc_cabec_carga_abono.ccba_oid_cuen_corr_banc := NULL;
         reg_ccc_cabec_carga_abono.sbac_oid_sbac           := NULL;
         reg_ccc_cabec_carga_abono.fec_docu                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.fec_valo                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.cod_usua                := USER;

         INSERT INTO CCC_CABEC_CARGA_ABONO_DIREC VALUES reg_ccc_cabec_carga_abono;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_CREA_CABEC_CARGO_ABONO: PARAMS => ' ||
                              'NUM_ANIO => ' || p_num_anio ||
                              'NUM_MES => '  || p_num_mes ||
                              'NUM_SECU => ' || p_num_docu ||
                              'ERROR => ' || ls_sqlerrm);

   END CCC_PR_CREA_CABEC_CARGO_ABONO;

   PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais                                        IN  seg_pais.oid_pais%TYPE,
      p_oid_socie                                       IN  seg_socie.oid_soci%TYPE,
      p_oid_proc                                        IN  ccc_proce.oid_proc%TYPE,
      p_oid_tipo_abon_subp                      IN  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_cabe_carg                               OUT ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE)
   IS
       reg_ccc_cabec_carga_abono CCC_CABEC_CARGA_ABONO_DIREC%ROWTYPE;

       lv_cod_pais            seg_pais.cod_pais%TYPE;
       lv_num_anio            ccc_movim_cuent_corri.val_docu_anio%TYPE;
       lv_num_mes             ccc_movim_cuent_corri.val_docu_mes_seri%TYPE;
       lv_num_secu            ccc_secue_pais.num_secu%TYPE;

   BEGIN

       /*  Obtiene Codigo de Pais */
       SELECT pai.cod_pais
       INTO   lv_cod_pais
       FROM   seg_pais pai
       WHERE  pai.oid_pais = p_oid_pais;

       /*  obtiene numero de secuencia disponible para crear cabecera */
       ccc_pr_obtie_numer_secue( lv_cod_pais, lv_num_anio, lv_num_mes, lv_num_secu);

       SELECT ccc_ccad_seq.NEXTVAL
       INTO p_oid_cabe_carg
       FROM dual;

         reg_ccc_cabec_carga_abono.Oid_Cabe_Carg           := p_oid_cabe_carg;
         reg_ccc_cabec_carga_abono.Pais_Oid_Pais           := p_oid_pais;
         reg_ccc_cabec_carga_abono.Num_Cabe                := lv_num_secu;
         reg_ccc_cabec_carga_abono.Anio                    := lv_num_anio;
         reg_ccc_cabec_carga_abono.Val_Peri_Mes            := lv_num_mes;
         reg_ccc_cabec_carga_abono.Soci_Oid_Soci           := p_oid_socie;
         reg_ccc_cabec_carga_abono.val_refe_exte           := NULL;
         reg_ccc_cabec_carga_abono.Val_obse                := NULL;
         reg_ccc_cabec_carga_abono.Ccpr_Oid_Proc           := p_oid_proc;
         reg_ccc_cabec_carga_abono.tasp_oid_tipo_abon_subp := p_oid_tipo_abon_subp;
         reg_ccc_cabec_carga_abono.ccba_oid_cuen_corr_banc := NULL;
         reg_ccc_cabec_carga_abono.sbac_oid_sbac           := NULL;
         reg_ccc_cabec_carga_abono.Fec_Docu                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.Fec_Valo                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.Cod_Usua                := USER;

         INSERT INTO CCC_CABEC_CARGA_ABONO_DIREC VALUES reg_ccc_cabec_carga_abono;

        EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_CREA_CABEC_CARGO_ABONO: PARAMS => ' ||
                              'NUM_ANIO => ' || lv_num_anio ||
                              'NUM_MES => '  || lv_num_mes ||
                              'NUM_SECU => ' || lv_num_secu ||
                              'ERROR => ' || ls_sqlerrm);

   END CCC_PR_CREA_CABEC_CARGO_ABONO;

   PROCEDURE CCC_PR_CREA_CABEC_CARGO_ABONO(
      p_oid_pais           IN  seg_pais.oid_pais%TYPE,
      p_oid_socie          IN  seg_socie.oid_soci%TYPE,
      p_oid_cod_proc       IN  ccc_proce.oid_proc%TYPE,
      p_num_anio           IN  ccc_movim_cuent_corri.val_docu_anio%TYPE,
      p_num_mes            IN  ccc_movim_cuent_corri.val_docu_mes_seri%TYPE,
      p_num_secu           IN  ccc_secue_pais.num_secu%TYPE,
      p_num_lote           IN  per_solic_monet.num_lote%TYPE,
      p_oid_tipo_abon_subp IN  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_cod_tipo_soli      IN  per_solic_monet.cod_tipo_soli%TYPE,
      p_oid_cuen_corr_banc IN  ccc_asign_subpr_tipo_solic.ccba_oid_cuen_corr_banc%TYPE,
      p_oid_sbac           IN  ped_tipo_solic.sbac_oid_sbac%TYPE,
      p_oid_cabe_carg      OUT ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE
      )
   IS
     reg_ccc_cabec_carga_abono CCC_CABEC_CARGA_ABONO_DIREC%ROWTYPE;
   BEGIN

         SELECT ccc_ccad_seq.NEXTVAL
         INTO p_oid_cabe_carg
         FROM dual;

         reg_ccc_cabec_carga_abono.Oid_Cabe_Carg           := p_oid_cabe_carg;
         reg_ccc_cabec_carga_abono.Pais_Oid_Pais           := p_oid_pais;
         reg_ccc_cabec_carga_abono.Num_Cabe                := p_num_secu;
         reg_ccc_cabec_carga_abono.Anio                    := p_num_anio;
         reg_ccc_cabec_carga_abono.Val_Peri_Mes            := p_num_mes;
         reg_ccc_cabec_carga_abono.Soci_Oid_Soci           := p_oid_socie;
         reg_ccc_cabec_carga_abono.val_refe_exte           := p_cod_tipo_soli;
         reg_ccc_cabec_carga_abono.Val_obse                := p_num_lote;
         reg_ccc_cabec_carga_abono.Ccpr_Oid_Proc           := p_oid_cod_proc;
         reg_ccc_cabec_carga_abono.tasp_oid_tipo_abon_subp := p_oid_tipo_abon_subp;
         reg_ccc_cabec_carga_abono.ccba_oid_cuen_corr_banc := p_oid_cuen_corr_banc;
         reg_ccc_cabec_carga_abono.sbac_oid_sbac           := p_oid_sbac;
         reg_ccc_cabec_carga_abono.Fec_Docu                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.Fec_Valo                := trunc(SYSDATE);
         reg_ccc_cabec_carga_abono.Cod_Usua                := USER;

         INSERT INTO CCC_CABEC_CARGA_ABONO_DIREC VALUES reg_ccc_cabec_carga_abono;

   END CCC_PR_CREA_CABEC_CARGO_ABONO;

   PROCEDURE CCC_PR_ACTUA_CUENT(
     p_oid_clie                   mae_clien.oid_clie%TYPE,
     p_oid_movi_carg              ccc_movim_cuent_corri.oid_movi_cc%TYPE,
     p_fec_ulti_movi              ccc_movim_cuent_corri.fec_ulti_movi%TYPE,
     p_oid_cabe_carg_apli         ccc_cabec_carga_abono_direc.oid_cabe_carg%TYPE,
     p_val_ulti_docu_anio         ccc_movim_cuent_corri.val_ulti_docu_anio%TYPE,
     p_val_ulti_docu_mes          ccc_movim_cuent_corri.val_ulti_docu_mes_seri%TYPE,
     p_val_ulti_docu_nume         ccc_movim_cuent_corri.val_ulti_docu_nume%TYPE,
     p_oid_subp_ulti              ccc_subpr.oid_subp%TYPE,
     p_oid_tcab_ulti              ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
     p_oid_marc_situ              ccc_marca_situa.oid_marc_situ%TYPE,
     p_imp_paga                   ccc_movim_cuent_corri.imp_paga%TYPE,
     p_imp_pend                   ccc_movim_cuent_corri.imp_pend%TYPE,
     p_imp_pago                   ccc_movim_cuent_corri.imp_pago%TYPE,
     p_oid_movi_abon              ccc_movim_cuent_corri.oid_movi_cc%TYPE,
     p_ind_tipo_abon            ccc_proce_subpr_creac_aplic.ind_abon_nomo%TYPE,
     p_cod_usu                    ccc_movim_cuent_corri.cod_usua%TYPE)
  IS
     lv_reg_hmcc                  ccc_histo_movim_cc%ROWTYPE;
     lv_reg_aplic_abon_cargo      ccc_aplic_abono_cargo%ROWTYPE;
  BEGIN

     SELECT ccc_hmcc_seq.nextval
     INTO lv_reg_hmcc.oid_hist_movi
     FROM dual;

     SELECT mcc.subp_oid_subp_ulti,
            mcc.tcab_oid_tcab_ulti,
            mcc.val_ulti_nume_hist,
            mcc.val_refe_nume_docu_exte,
            mcc.fec_ulti_movi,
            mcc.fec_venc,
            mcc.fec_valo,
            mcc.imp_movi,
            mcc.imp_paga,
            mcc.imp_pend,
            mcc.imp_divi,
            mcc.imp_paga_divi,
            mcc.val_ulti_docu_mes_seri,
            mcc.val_ulti_docu_anio,
            mcc.val_ulti_docu_nume,
            mcc.val_nume_lote_cont,
            mcc.fec_conta,
            mcc.val_obse,
            mcc.cuco_oid_cuen_cont_cuot,
            mcc.ticl_oid_tipo_clie,
            mcc.masi_oid_marc_situ,
            mcc.mone_oid_mone,
            mcc.mpab_oid_medi_pago,
            mcc.clie_oid_clie,
            mcc.ind_tipo_camb,
            mcc.cod_usua,
            mcc.imp_pago,
            mcc.imp_movi_cuen,
            mcc.imp_movi_divi,
            mcc.oid_movi_cc,
            mcc.fec_docu,
            mcc.ind_dto_carg_Apli
     INTO lv_reg_hmcc.subp_oid_subp,
          lv_reg_hmcc.tcab_oid_tipo_carg_abon,
          lv_reg_hmcc.num_hist,
          lv_reg_hmcc.val_refe_nume_docu_exte,
          lv_reg_hmcc.fec_movi,
          lv_reg_hmcc.fec_venc,
          lv_reg_hmcc.fec_valo,
          lv_reg_hmcc.imp,
          lv_reg_hmcc.imp_paga,
          lv_reg_hmcc.imp_pen,
          lv_reg_hmcc.imp_divi,
          lv_reg_hmcc.imp_paga_divi,
          lv_reg_hmcc.val_ulti_docu_mes_seri,
          lv_reg_hmcc.val_ulti_docu_anio,
          lv_reg_hmcc.val_ulti_docu_nume,
          lv_reg_hmcc.val_nume_lote_cont,
          lv_reg_hmcc.fec_cont,
          lv_reg_hmcc.val_obse,
          lv_reg_hmcc.cuco_oid_cuen_cont,
          lv_reg_hmcc.ticl_oid_tipo_clie,
          lv_reg_hmcc.masi_oid_marc_situ,
          lv_reg_hmcc.mone_oid_mone,
          lv_reg_hmcc.mpab_oid_medi_pago_teor,
          lv_reg_hmcc.clie_oid_clie,
          lv_reg_hmcc.val_tipo_camb,
          lv_reg_hmcc.cod_usua,
          lv_reg_hmcc.imp_pago,
          lv_reg_hmcc.imp_movi,
          lv_reg_hmcc.imp_movi_divi,
          lv_reg_hmcc.mvcc_oid_movi_cc,
          lv_reg_hmcc.fec_docu,
          lv_reg_hmcc.ind_desc_carg_apli
     FROM ccc_movim_cuent_corri mcc
     WHERE mcc.oid_movi_cc=p_oid_movi_carg;

     INSERT INTO CCC_HISTO_MOVIM_CC VALUES lv_reg_hmcc;

     UPDATE ccc_movim_cuent_corri mcc
     SET
         mcc.masi_oid_marc_situ= p_oid_marc_situ ,
          mcc.subp_oid_subp_ulti=p_oid_subp_ulti,
          mcc.tcab_oid_tcab_ulti=p_oid_tcab_ulti,
          mcc.fec_ulti_movi=p_fec_ulti_movi,
          mcc.imp_paga=p_imp_paga,
          mcc.imp_pend=p_imp_pend,
          mcc.imp_pago=p_imp_pago,
          mcc.val_ulti_docu_anio= p_val_ulti_docu_anio,
          mcc.val_ulti_docu_mes_seri= p_val_ulti_docu_mes,
          mcc.val_ulti_docu_nume= p_val_ulti_docu_nume,
          mcc.val_ulti_nume_hist = mcc.val_ulti_nume_hist + 1,
          mcc.cod_usua=p_cod_usu
     WHERE mcc.oid_movi_cc= p_oid_movi_carg;

     IF p_ind_tipo_abon=0 THEN
        lv_reg_aplic_abon_cargo.cmba_oid_movi_banc:=p_oid_movi_abon;
    ELSE
       lv_reg_aplic_abon_cargo.mvcc_oid_movi_abon:=p_oid_movi_abon;
     END IF;

     lv_reg_aplic_abon_cargo.ccad_oid_cabe_carg:=p_oid_cabe_carg_apli;
     lv_reg_aplic_abon_cargo.subp_oid_subp_apli:=p_oid_subp_ulti;
     lv_reg_aplic_abon_cargo.mvcc_oid_movi_carg:=p_oid_movi_carg;
     lv_reg_aplic_abon_cargo.clie_oid_clie := p_oid_clie;
     lv_reg_aplic_abon_cargo.imp_abon:=p_imp_pago;
     lv_reg_aplic_abon_cargo.fec_proc:=SYSDATE;
     lv_reg_aplic_abon_cargo.fec_apli:=p_fec_ulti_movi;

     lv_reg_aplic_abon_cargo.usu_crea := p_cod_usu;
     lv_reg_aplic_abon_cargo.fec_crea := SYSDATE;
     lv_reg_aplic_abon_cargo.usu_modi := p_cod_usu;
     lv_reg_aplic_abon_cargo.fec_modi := SYSDATE;

     INSERT INTO ccc_aplic_abono_cargo VALUES lv_reg_aplic_abon_cargo;

     EXCEPTION
        WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR CCC_PR_ACTUA_CUENT: ' ||
                               ls_sqlerrm);

   END CCC_PR_ACTUA_CUENT;

   PROCEDURE CCC_PR_OBTIE_PARAM_TIPO_SOLIC(
      p_oid_pais                                                      IN   seg_pais.oid_pais%TYPE,
      p_cod_tipo_soli                                              IN   ped_tipo_solic.cod_tipo_soli%TYPE,
      p_cod_proc                                                     OUT  ccc_proce.cod_proc%TYPE,
      p_oid_proc                                                      OUT  ccc_proce.oid_proc%TYPE,
      p_cod_subp                                                     OUT  ccc_subpr.cod_subp%TYPE,
      p_oid_subp                                                      OUT  ccc_subpr.oid_subp%TYPE,
      p_oid_cuen_corr_banc                                   OUT  ccc_asign_subpr_tipo_solic.ccba_oid_cuen_corr_banc%TYPE,
      p_cod_tipo_carg_abon                                   OUT  ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE,
      p_oid_tipo_carg_abon                                    OUT  ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
      p_masi_oid_marc_sali                                     OUT  ccc_marca_tipo_abono.masi_oid_marc_sali%TYPE,
      p_oid_cuen_cont                                             OUT  ccc_tipo_abono_subpr.cuco_oid_cuen_cont%TYPE,
      p_oid_marc                                                      OUT  ped_tipo_solic.marc_oid_marc%TYPE,
      p_oid_sbac                                                       OUT  ped_tipo_solic.sbac_oid_sbac%TYPE,
      p_oid_medi_pago                                             OUT  bel_forma_pago_detal.mpab_oid_medi_pago%TYPE,
      p_tsol_oid_tipo_cons                                      OUT  ped_tipo_solic_pais.tsol_oid_tipo_cons%TYPE,
      p_oid_tipo_abon_subp                                     OUT  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_ind_soli_nega                                                OUT  ped_tipo_solic.ind_soli_nega%TYPE     )
   IS

      lv_tsol_oid_tipo_cons  ped_tipo_solic_pais.tsol_oid_tipo_cons%type;

      lv_cod_proc            ccc_proce.cod_proc%TYPE;
      lv_oid_proc            ccc_proce.oid_proc%TYPE;
      lv_cod_subp            ccc_subpr.cod_subp%TYPE;
      lv_oid_subp            ccc_subpr.oid_subp%TYPE;
      lv_oid_cuen_corr_banc  ccc_asign_subpr_tipo_solic.ccba_oid_cuen_corr_banc%TYPE;
      lv_oid_cuen_cont       ccc_tipo_abono_subpr.cuco_oid_cuen_cont%TYPE;
      lv_oid_tipo_abon_subp  ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE;
      lv_cod_tipo_carg_abon  ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE;
      lv_oid_tipo_carg_abon  ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
      lv_masi_oid_marc_sali  ccc_marca_tipo_abono.masi_oid_marc_sali%TYPE;
      lv_oid_marc            ped_tipo_solic.marc_oid_marc%TYPE;
      lv_sbac_oid_sbac       ped_tipo_solic.sbac_oid_sbac%TYPE;
      lv_oid_medi_pago       bel_forma_pago_detal.mpab_oid_medi_pago%TYPE;
      lv_ind_soli_nega       ped_tipo_solic.ind_soli_nega%TYPE;

   BEGIN

      /* obtiene el oid del codigo del tipo de solicitud-pais */
      SELECT tsp.tsol_oid_tipo_cons, ts.marc_oid_marc, ts.sbac_oid_sbac, fpd.mpab_oid_medi_pago, ts.Ind_Soli_Nega
      INTO   lv_tsol_oid_tipo_cons, lv_oid_marc, lv_sbac_oid_sbac, lv_oid_medi_pago,  lv_ind_soli_nega
      FROM   ped_tipo_solic ts,
                   ped_tipo_solic_pais tsp,
                   bel_forma_pago_detal fpd
      WHERE  ts.cod_tipo_soli = p_cod_tipo_soli
      AND    tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
      AND    tsp.pais_oid_pais = p_oid_pais
      AND    tsp.fopa_oid_form_pago = fpd.fopa_oid_form_pago;


      /*  punto 2  */
      SELECT pro.cod_proc, pro.oid_proc, spr.cod_subp, spr.oid_subp, sts.ccba_oid_cuen_corr_banc
      INTO   lv_cod_proc,  lv_oid_proc,  lv_cod_subp,  lv_oid_subp,  lv_oid_cuen_corr_banc
      FROM   ccc_asign_subpr_tipo_solic sts,
             ccc_proce pro,
             ccc_subpr spr
      WHERE  sts.tspa_oid_tipo_soli_pais = lv_tsol_oid_tipo_cons
      AND    sts.subp_oid_subp_crea_cuot = spr.oid_subp
      AND    spr.ccpr_oid_proc = pro.oid_proc
      AND    pro.pais_oid_pais = p_oid_pais;


      /*  punto 3 */
      SELECT  absp.cuco_oid_cuen_cont, absp.oid_tipo_abon_subp
      INTO    lv_oid_cuen_cont, lv_oid_tipo_abon_subp
      FROM    ccc_tipo_abono_subpr absp
      WHERE   absp.subp_oid_subp = lv_oid_subp;


      /* punto 4 */
      /*   OJO  CORREGIR  AMBIGUEDAD   (rownum = 1 )
         EXISTE EN  OWN_PERU.CCC_MARCA_SITUA CON COD_MARC_SITU  = XX  QUE NO SE USA   */
      SELECT  tca.cod_tipo_carg_abon, tca.oid_tipo_carg_abon, mta.masi_oid_marc_sali
      INTO    lv_cod_tipo_carg_abon,  lv_oid_tipo_carg_abon,  lv_masi_oid_marc_sali
      FROM    ccc_marca_tipo_abono mta,
              ccc_tipo_abono_subpr spr,
              ccc_tipo_cargo_abono tca
      WHERE   mta.tasp_oid_tipo_abon_subp = spr.oid_tipo_abon_subp
      AND     spr.tcab_oid_tcab = tca.oid_tipo_carg_abon
      AND     mta.tasp_oid_tipo_abon_subp = lv_oid_tipo_abon_subp
      AND     spr.subp_oid_subp  =  lv_oid_subp
      AND   ROWNUM = 1;


      p_cod_proc            := lv_cod_proc;
      p_oid_proc            := lv_oid_proc;
      p_cod_subp            := lv_cod_subp;
      p_oid_subp            := lv_oid_subp;
      p_oid_cuen_corr_banc  := lv_oid_cuen_corr_banc;
      p_cod_tipo_carg_abon  := lv_cod_tipo_carg_abon;
      p_oid_tipo_carg_abon  := lv_oid_tipo_carg_abon;
      p_masi_oid_marc_sali  := lv_masi_oid_marc_sali;
      p_oid_cuen_cont       := lv_oid_cuen_cont;
      p_oid_marc            := lv_oid_marc;
      p_oid_sbac            := lv_sbac_oid_sbac;
      p_oid_medi_pago       := lv_oid_medi_pago;
      p_tsol_oid_tipo_cons  := lv_tsol_oid_tipo_cons;
      p_oid_tipo_abon_subp  := lv_oid_tipo_abon_subp;
      p_ind_soli_nega       := lv_ind_soli_nega;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_OBTIE_PARAM_TIPO_SOLIC: ' ||
                               ls_sqlerrm);
   END CCC_PR_OBTIE_PARAM_TIPO_SOLIC;

 PROCEDURE CCC_PR_REGIS_LOTE_BANCA(
  p_cod_cban                       IN   ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_tipo_orig                  IN   ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE,
  p_num_lote                       IN   ccc_movim_banca.num_lote%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE)
 IS

  lc_val_cod_iden_proc_ok          CONSTANT VARCHAR2(1):='T';
  lc_val_cod_iden_proc_erro        CONSTANT VARCHAR2(1):='I';
  lv_oid_subp_crea                 ccc_subpr.oid_subp%TYPE;
  lv_cod_proc_crea                 ccc_proce.cod_proc%TYPE;
  lv_cod_subp_crea                 ccc_subpr.cod_subp%TYPE;
  lv_ind_liqu                      NUMBER(1);

  reg_ccc_numer_lote_banca_recep   ccc_numer_lote_banca_recep%ROWTYPE;

 BEGIN

  ---------------------------------------


  -- Registarndo el Lote Bancario
  reg_ccc_numer_lote_banca_recep.cod_cuen_corr_banc := p_cod_cban;
  reg_ccc_numer_lote_banca_recep.cod_tipo_orig := p_cod_tipo_orig;
  reg_ccc_numer_lote_banca_recep.fec_proc := TRUNC(SYSDATE);
  reg_ccc_numer_lote_banca_recep.usu_crea := p_cod_usua;
  reg_ccc_numer_lote_banca_recep.fec_crea := SYSDATE;


       SELECT
          mb.num_lote,
          mb.subp_oid_marc_crea,
       	  MIN(mb.fec_Pago),
       	  MAX(mb.fec_Pago),
       	  COUNT(*),
       	  SUM(mb.imp_Pago),
       	  NVL((SELECT COUNT(1)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc = lc_val_cod_iden_proc_ok
           AND mbt.num_Lote = mb.num_Lote),0),
          NVL((SELECT SUM(mbt.imp_pago)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_ok
           AND mbt.num_Lote = mb.num_Lote),0),
       	  NVL((SELECT COUNT(1)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_erro
           AND mbt.num_Lote = mb.num_Lote),0),
       	  NVL((SELECT SUM(mbt.imp_pago)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_erro
           AND mbt.num_Lote = mb.num_Lote),0)
       INTO
          reg_ccc_numer_lote_banca_recep.num_lote,
          lv_oid_subp_crea,
          reg_ccc_numer_lote_banca_recep.fec_pago_mini,
          reg_ccc_numer_lote_banca_recep.fec_pago_maxi,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_tota,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_tota,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_ok,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_ok,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_erro,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_erro
       FROM ccc_movim_banca mb
 	   WHERE  mb.num_lote = p_num_lote
       GROUP BY mb.num_Lote, mb.subp_oid_marc_crea;

      -- Determinando el identificador de proceso.
       IF reg_ccc_numer_lote_banca_recep.val_cant_regi_ok> 0 THEN
          reg_ccc_numer_lote_banca_recep.cod_iden_proc:=lc_val_cod_iden_proc_ok;
       ELSE
           reg_ccc_numer_lote_banca_recep.cod_iden_proc:=lc_val_cod_iden_proc_erro;
       END IF;

       -- Determinando el Tipo de Lote
       BEGIN
          SELECT cp.cod_proc,cs.cod_subp
          INTO lv_cod_proc_crea , lv_cod_subp_crea
          FROM ccc_subpr cs,
                     ccc_proce cp
          WHERE cs.oid_subp = lv_oid_subp_crea;

          SELECT tmb.cod_tipo
          INTO reg_ccc_numer_lote_banca_recep.tipo_lote
          FROM ccc_tipos_lote_banca tmb
          WHERE tmb.cod_proce_crea=lv_cod_proc_crea
          AND tmb.cod_subp_crea = lv_cod_subp_crea;
        EXCEPTION
          WHEN OTHERS THEN
             reg_ccc_numer_lote_banca_recep.tipo_lote:= NULL;
        END;


       INSERT INTO ccc_numer_lote_banca_recep VALUES reg_ccc_numer_lote_banca_recep;

  CASE

   WHEN p_cod_tipo_orig = gc_cod_tipo_orig_digi THEN
    SELECT ccb.ind_liqu_auto_digi
    INTO lv_ind_liqu
    FROM ccc_cuent_corri_banca ccb
    WHERE ccb.cod_cc = p_cod_cban;

   WHEN p_cod_tipo_orig = gc_cod_tipo_orig_exce THEN
    SELECT ccb.ind_liqu_auto_exce
    INTO lv_ind_liqu
    FROM ccc_cuent_corri_banca ccb
    WHERE ccb.cod_cc = p_cod_cban;

   WHEN p_cod_tipo_orig = gc_cod_tipo_orig_regu_digi THEN
         BEGIN
    SELECT ccb.ind_liqu_auto_regu_digi
    INTO lv_ind_liqu
    FROM ccc_cuent_corri_banca ccb
    WHERE ccb.cod_cc = p_cod_cban;
           EXCEPTION
       WHEN OTHERS THEN

         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(sqlerrm,1,250);

         insert into sto_histo_proce_histo
         values ('COE','REG',p_num_lote,sysdate, sysdate,'S',ls_sqlerrm,p_cod_usua,0,0,'',p_cod_cban);

         delete ccc_numer_lote_banca_recep where num_lote = reg_ccc_numer_lote_banca_recep.num_lote;

         BEGIN

               FOR v_lote IN (SELECT ccb.cod_cc, mb.num_lote
                              FROM
                               ccc_movim_banca mb,
                               ccc_cuent_corri_banca ccb
                              WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
                                AND mb.fec_proc >= TRUNC(SYSDATE-7)
                                AND NOT EXISTS (
                                    SELECT NULL
                                    FROM ccc_numer_lote_banca_recep a
                                    WHERE a.num_lote = mb.num_lote)
                               GROUP BY ccb.cod_cc, mb.num_lote) LOOP
              CCC_PKG_GENER.CCC_PR_REGIS_LOTE_BANCA(v_lote.cod_cc,'REG',v_lote.num_lote,p_cod_usua);
               END LOOP;
          END;

       END;

   WHEN p_cod_tipo_orig = gc_cod_tipo_orig_regu_exce THEN
    SELECT ccb.ind_liqu_auto_regu_exce
    INTO lv_ind_liqu
    FROM ccc_cuent_corri_banca ccb
    WHERE ccb.cod_cc = p_cod_cban;

   ELSE

    lv_ind_liqu := 0;

  END CASE;


  IF lv_ind_liqu = 1 THEN

   CCC_PKG_PROCE.CCC_PR_LIQUI_LOTE_BANCA(p_num_lote,p_cod_usua);

  END IF;

 END CCC_PR_REGIS_LOTE_BANCA;

 PROCEDURE CCC_PR_GENER_CABEC_LOTE_BANCA(
  p_cod_cban                     ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_tipo_orig                ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE,
  p_num_lote                     ccc_movim_banca.num_lote%TYPE,
  p_num_lote_mult                ccc_movim_banca.num_lote%TYPE,
  p_cod_usua                     seg_usuar.use_usua%TYPE)
 IS

  lc_val_cod_iden_proc_ok                CONSTANT VARCHAR2(1):='T';
  lc_val_cod_iden_proc_erro             CONSTANT VARCHAR2(1):='I';
  lv_oid_subp_crea                              ccc_subpr.oid_subp%TYPE;
  lv_cod_proc_crea                              ccc_proce.cod_proc%TYPE;
  lv_cod_subp_crea                              ccc_subpr.cod_subp%TYPE;

  reg_ccc_numer_lote_banca_recep   ccc_numer_lote_banca_recep%ROWTYPE;

 BEGIN


  -- Registarndo el Lote Bancario
  reg_ccc_numer_lote_banca_recep.cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
  reg_ccc_numer_lote_banca_recep.cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoSociedad');
  reg_ccc_numer_lote_banca_recep.cod_cuen_corr_banc := p_cod_cban;
  reg_ccc_numer_lote_banca_recep.cod_tipo_orig := p_cod_tipo_orig;
  reg_ccc_numer_lote_banca_recep.fec_proc := SYSDATE;
  reg_ccc_numer_lote_banca_recep.usu_crea := p_cod_usua;
  reg_ccc_numer_lote_banca_recep.fec_crea := SYSDATE;
  reg_ccc_numer_lote_banca_recep.num_lote_mult := p_num_lote_mult;

       SELECT
          mb.num_lote,
          mb.subp_oid_marc_crea,
       	  MIN(mb.fec_Pago),
       	  MAX(mb.fec_Pago),
       	  COUNT(*),
       	  SUM(mb.imp_Pago),
       	  NVL((SELECT COUNT(1)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc = lc_val_cod_iden_proc_ok
           AND mbt.num_Lote = mb.num_Lote),0),
          NVL((SELECT SUM(mbt.imp_pago)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_ok
           AND mbt.num_Lote = mb.num_Lote),0),
       	  NVL((SELECT COUNT(1)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_erro
           AND mbt.num_Lote = mb.num_Lote),0),
       	  NVL((SELECT SUM(mbt.imp_pago)
           FROM ccc_movim_Banca mbt
           WHERE mbt.cod_iden_proc =  lc_val_cod_iden_proc_erro
           AND mbt.num_Lote = mb.num_Lote),0)
       INTO
          reg_ccc_numer_lote_banca_recep.num_lote,
          lv_oid_subp_crea,
          reg_ccc_numer_lote_banca_recep.fec_pago_mini,
          reg_ccc_numer_lote_banca_recep.fec_pago_maxi,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_tota,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_tota,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_ok,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_ok,
          reg_ccc_numer_lote_banca_recep.val_cant_regi_erro,
          reg_ccc_numer_lote_banca_recep.val_impo_regi_erro
       FROM ccc_movim_banca mb
 	   WHERE  mb.num_lote = p_num_lote
       GROUP BY mb.num_Lote, mb.subp_oid_marc_crea;

      -- Determinando el identificador de proceso.
       IF reg_ccc_numer_lote_banca_recep.val_cant_regi_ok> 0 THEN
          reg_ccc_numer_lote_banca_recep.cod_iden_proc:=lc_val_cod_iden_proc_ok;
       ELSE
           reg_ccc_numer_lote_banca_recep.cod_iden_proc:=lc_val_cod_iden_proc_erro;
       END IF;

       -- Determinando el Tipo de Lote
       BEGIN
          SELECT cp.cod_proc,cs.cod_subp
          INTO lv_cod_proc_crea , lv_cod_subp_crea
          FROM ccc_subpr cs,
                     ccc_proce cp
          WHERE cs.oid_subp = lv_oid_subp_crea;

          SELECT tmb.cod_tipo
          INTO reg_ccc_numer_lote_banca_recep.tipo_lote
          FROM ccc_tipos_lote_banca tmb
          WHERE tmb.cod_proce_crea=lv_cod_proc_crea
          AND tmb.cod_subp_crea = lv_cod_subp_crea;
        EXCEPTION
          WHEN OTHERS THEN
             reg_ccc_numer_lote_banca_recep.tipo_lote:= NULL;
        END;


       INSERT INTO ccc_numer_lote_banca_recep VALUES reg_ccc_numer_lote_banca_recep;

 END CCC_PR_GENER_CABEC_LOTE_BANCA;

   PROCEDURE CCC_PR_OBTIE_PARAM_TASUB(
      p_oid_tipo_abon_subp                           IN      ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE,
      p_oid_proc                                             OUT   ccc_proce.oid_proc%TYPE,
      p_oid_subp                                             OUT   ccc_subpr.oid_subp%TYPE,
      p_oid_cuen_cont                                    OUT   ccc_tipo_abono_subpr.cuco_oid_cuen_cont%TYPE,
      p_indi_tipo_abon                                   OUT    ccc_subpr.val_indi_tipo_abon%TYPE,
      p_oid_marc_situ                                    OUT    ccc_marca_situa.oid_marc_situ%TYPE,
      p_oid_tipo_carg_abon                            OUT   ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE,
      p_val_indi_cons                                      OUT   ccc_subpr.val_indi_cons%TYPE)
   IS

   BEGIN

      SELECT
         pro.oid_proc,
         sub.oid_subp,
         tas.cuco_oid_cuen_cont,
         sub.val_indi_tipo_abon,
         mta.masi_oid_marc_sali,
         tas.tcab_oid_tcab,
         sub.val_indi_cons
      INTO
         p_oid_proc,
         p_oid_subp,
         p_oid_cuen_cont,
         p_indi_tipo_abon,
         p_oid_marc_situ,
         p_oid_tipo_carg_abon,
         p_val_indi_cons
  FROM
   ccc_tipo_abono_subpr tas,
            ccc_subpr sub,
            ccc_proce pro,
            ccc_marca_tipo_abono mta
      WHERE  tas.oid_tipo_abon_subp = p_oid_tipo_abon_subp
        AND  tas.subp_oid_subp = sub.oid_subp
        AND  sub.ccpr_oid_proc = pro.oid_proc
        AND  tas.oid_tipo_abon_subp = mta.tasp_oid_tipo_abon_subp;

   END CCC_PR_OBTIE_PARAM_TASUB;

 PROCEDURE CCC_PR_OBTIE_PARAM_FACTU(
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

 END CCC_PR_OBTIE_PARAM_FACTU;

   FUNCTION CCC_FN_OBTIE_PARAM_GENER(
      p_cod_para                   IN       ccc_param_gener.cod_para%TYPE)
   RETURN VARCHAR2
   IS

      lv_val_para                  VARCHAR2(100);

   BEGIN

      SELECT g.val_para
      INTO lv_val_para
      FROM ccc_param_gener g
      WHERE g.cod_para =  p_cod_para;

   RETURN lv_val_para;

   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         RETURN NULL;

   END CCC_FN_OBTIE_PARAM_GENER;

   FUNCTION CCC_FN_OBTIE_NUMER_LOTE
  RETURN NUMBER
  IS
     lv_cont                    ccc_numer_lote.cont%TYPE;
     lv_val_cade_fech    ccc_numer_lote.val_cade_fech%TYPE;
     lv_num_secu            VARCHAR2(5);
     lv_num_lote             ccc_movim_banca.num_lote%TYPE;

  BEGIN

     lv_val_cade_fech:=to_char(trunc(SYSDATE),'YYYYMMDD');

     SELECT cnl.cont
     INTO lv_cont
     FROM fin_numer_lote cnl
     WHERE cnl.val_cade_fech=lv_val_cade_fech
     FOR UPDATE;

     UPDATE fin_numer_lote cnl
     SET cnl.cont=lv_cont+1
     WHERE cnl.val_cade_fech=lv_val_cade_fech;

     lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

     lv_num_lote := TO_NUMBER(concat(lv_val_cade_fech,lv_num_secu));

     RETURN lv_num_lote;

  EXCEPTION
    WHEN no_data_found THEN
        lv_cont:=501;
        lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

       INSERT INTO fin_numer_lote VALUES (to_char(trunc(SYSDATE),'YYYYMMDD'),lv_cont+1);

       lv_num_lote := concat(lv_val_cade_fech,lv_num_secu);

       RETURN lv_num_lote;
   END CCC_FN_OBTIE_NUMER_LOTE;

   FUNCTION CCC_FN_OBTIE_PARAM_OID_PROCE(
      p_cod_proc       IN    ccc_proce.cod_proc%TYPE)
   RETURN NUMBER
   IS
      lv_oid_proc                ccc_proce.oid_proc%TYPE;
   BEGIN
      SELECT cp.oid_proc
      INTO lv_oid_proc
      FROM ccc_proce cp
      WHERE cp.cod_proc=p_cod_proc;

      RETURN lv_oid_proc;

    EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_PARAM_OID_PROCE: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_PARAM_OID_PROCE;

   FUNCTION CCC_FN_OBTIE_PARAM_OID_SUBPR(
      p_cod_proc       IN    ccc_proce.cod_proc%TYPE,
      p_cod_subp       IN    ccc_subpr.cod_subp%TYPE)
   RETURN NUMBER
   IS
      lv_oid_subp      ccc_subpr.oid_subp%TYPE;
   BEGIN

      SELECT cs.oid_subp
      INTO lv_oid_subp
      FROM ccc_proce cp,
                 ccc_subpr cs
      WHERE cp.oid_proc=cs.ccpr_oid_proc
      AND cp.cod_proc=p_cod_proc
      AND cs.cod_subp=p_cod_subp;

       RETURN lv_oid_subp;
    EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_PARAM_OID_SUBPR: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_PARAM_OID_SUBPR;

   FUNCTION CCC_FN_OBTIE_PARAM_OID_TICAB
      (p_oid_subp     IN   ccc_subpr.oid_subp%TYPE)
    RETURN NUMBER
    IS
       lv_oid_tcab      ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
    BEGIN
      SELECT tas.tcab_oid_tcab
      INTO lv_oid_tcab
      FROM ccc_tipo_abono_subpr tas
      WHERE tas.subp_oid_subp=p_oid_subp;
     RETURN  lv_oid_tcab;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_PARAM_OID_TICAB: ' ||
                               ls_sqlerrm);
     END CCC_FN_OBTIE_PARAM_OID_TICAB;

   FUNCTION CCC_FN_OBTIE_PARAM_OID_TASUB
     (p_oid_subp                                     IN      ccc_subpr.oid_subp%TYPE)
   RETURN NUMBER
   IS
      lv_oid_tipo_abon_subp    ccc_tipo_abono_subpr.oid_tipo_abon_subp%TYPE;
   BEGIN

      SELECT tas.oid_tipo_abon_subp
      INTO lv_oid_tipo_abon_subp
      FROM ccc_tipo_abono_subpr tas
      WHERE tas.subp_oid_subp=p_oid_subp;

      RETURN lv_oid_tipo_abon_subp;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_PARAM_OID_TASUB: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_PARAM_OID_TASUB;

   FUNCTION CCC_FN_OBTIE_OID_SUBGE_VENTA(
      p_oid_pais                                     IN      seg_pais.ind_sald_unic%TYPE,
      p_oid_marc                                    IN     seg_marca.oid_marc%TYPE,
      p_cod_sbac                                    IN     seg_subac.cod_sbac%TYPE)
   RETURN NUMBER
   IS

      lv_oid_subg_vent                              zon_sub_geren_venta.oid_subg_vent%TYPE;

   BEGIN

      SELECT sgv.oid_subg_vent
      INTO lv_oid_subg_vent
      FROM zon_sub_geren_venta sgv,
                  ccc_numer_ident_cuota nic
      WHERE sgv.pais_oid_pais = p_oid_pais
           AND sgv.marc_oid_marc = p_oid_marc
           AND sgv.cod_subg_vent=nic.cod_subg_vent
           AND nic.cod_suba= p_cod_sbac ;

      RETURN lv_oid_subg_vent;

   END CCC_FN_OBTIE_OID_SUBGE_VENTA;

   FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
      p_cod_pais             IN    seg_pais.cod_pais%TYPE,
      p_cod_soci             IN   seg_socie.cod_soci%TYPE,
      p_cod_suba             IN   seg_subac.cod_sbac%TYPE)
   RETURN NUMBER
   IS
     lv_nume_iden_cuot       ccc_movim_cuent_corri.num_iden_cuot%TYPE;
   BEGIN

     SELECT nic.val_ulti_nume_iden_cuot
     INTO lv_nume_iden_cuot
     FROM ccc_numer_ident_cuota nic
     WHERE nic.cod_pais= p_cod_pais
     AND nic.cod_soci= p_cod_soci
     AND nic.cod_suba = p_cod_suba
     FOR UPDATE;

     UPDATE ccc_numer_ident_cuota nic
     SET nic.val_ulti_nume_iden_cuot=lv_nume_iden_cuot+1
     WHERE nic.cod_pais= p_cod_pais
     AND nic.cod_soci= p_cod_soci
     AND nic.cod_suba = p_cod_suba;

     RETURN lv_nume_iden_cuot;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_OBTIE_NUMER_IDENT_CUOTA: ' ||
                               ls_sqlerrm);

   END CCC_FN_OBTIE_NUMER_IDENT_CUOTA ;

 FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
  p_cod_suba             IN   seg_subac.cod_sbac%TYPE)
 RETURN NUMBER
 IS

  lv_nume_iden_cuot       ccc_movim_cuent_corri.num_iden_cuot%TYPE;

 BEGIN

  SELECT nic.val_ulti_nume_iden_cuot
  INTO lv_nume_iden_cuot
  FROM ccc_numer_ident_cuota nic
  WHERE nic.cod_suba = p_cod_suba
  FOR UPDATE;

  UPDATE ccc_numer_ident_cuota nic
  SET nic.val_ulti_nume_iden_cuot=lv_nume_iden_cuot + 1
  WHERE nic.cod_suba = p_cod_suba;

  RETURN lv_nume_iden_cuot;

 EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 250);
   raise_application_error(-20123,
                              'ERROR CCC_PR_OBTIE_NUMER_IDENT_CUOTA: ' ||
                               ls_sqlerrm);

 END CCC_FN_OBTIE_NUMER_IDENT_CUOTA ;

   FUNCTION CCC_FN_OBTIE_NUMER_IDENT_CUOTA(
      p_cod_pais             IN    seg_pais.cod_pais%TYPE,
      p_cod_soci             IN   seg_socie.cod_soci%TYPE,
      p_cod_suba             IN   seg_subac.cod_sbac%TYPE,
      p_can_iden             IN   NUMBER)
   RETURN NUMBER
   IS
     lv_nume_iden_cuot       ccc_movim_cuent_corri.num_iden_cuot%TYPE;
   BEGIN

     SELECT nic.val_ulti_nume_iden_cuot
     INTO lv_nume_iden_cuot
     FROM ccc_numer_ident_cuota nic
     WHERE nic.cod_pais= p_cod_pais
     AND nic.cod_soci= p_cod_soci
     AND nic.cod_suba = p_cod_suba
     FOR UPDATE;

     UPDATE ccc_numer_ident_cuota nic
     SET nic.val_ulti_nume_iden_cuot=lv_nume_iden_cuot+ p_can_iden +1
     WHERE nic.cod_pais= p_cod_pais
     AND nic.cod_soci= p_cod_soci
     AND nic.cod_suba = p_cod_suba;

     RETURN lv_nume_iden_cuot;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_PR_OBTIE_NUMER_IDENT_CUOTA: ' ||
                               ls_sqlerrm);

   END CCC_FN_OBTIE_NUMER_IDENT_CUOTA ;

   FUNCTION CCC_FN_DEVUE_SECUE_NSOLI(
      p_cod_pais      VARCHAR2,
      p_cod_oper     VARCHAR2,
      p_cod_acce    VARCHAR2,
      p_cod_suba    VARCHAR2)
   RETURN NUMBER
   IS

      lnsolicitudinicio NUMBER;

   BEGIN

      SELECT ns.val_ulti_nume_soli
      INTO lnsolicitudinicio
      FROM ped_numer_solic ns
      WHERE ns.val_oper = p_cod_oper
      AND ns.val_anio = to_char(SYSDATE, 'Y')
      AND ns.cod_acce = p_cod_acce
      AND ns.cod_suba = p_cod_suba
       AND ns.cod_pais = p_cod_pais
       FOR UPDATE;

    RETURN lnsolicitudinicio;

   EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_DEVUE_SECUE_NSOLI: ' ||
                               ls_sqlerrm);

   END CCC_FN_DEVUE_SECUE_NSOLI;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_TOTAL(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_ban                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT bcf.cod_peri
   INTO lv_cod_peri
   FROM bas_ctrl_fact bcf
   WHERE bcf.ind_camp_act = 1
     AND bcf.sta_camp = 0;

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri <= lv_cod_peri
     AND mcc.imp_pend <> 0;

  SELECT SUM(mb.imp_sald_pend)
  INTO lv_imp_ban
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie = p_oid_clie
    AND mb.cod_iden_proc = 'P'
    AND mb.imp_sald_pend > 0;


  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CAMPA_TOTAL;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_ANTER(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_ban                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT bcf.cod_peri
   INTO lv_cod_peri
   FROM bas_ctrl_fact bcf
   WHERE bcf.ind_camp_act = 1
     AND bcf.sta_camp = 0;

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri < lv_cod_peri
     AND mcc.imp_pend <> 0;

  SELECT SUM(mb.imp_sald_pend)
  INTO lv_imp_ban
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie = p_oid_clie
    AND mb.cod_iden_proc = 'P'
    AND mb.imp_sald_pend > 0;


  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  /*
  IF lv_imp_sald < 0 THEN
   lv_imp_sald := 0;
  END IF;
  */

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CAMPA_ANTER;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT MIN(spc.cod_peri)
   INTO lv_cod_peri
   FROM
      seg_perio_corpo spc,
      cra_perio cp
   WHERE spc.oid_peri = cp.peri_oid_peri
     AND cp.fec_inic <= trunc(SYSDATE)
     AND cp.fec_fina >= trunc(SYSDATE);

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri = lv_cod_peri
     AND mcc.imp_pend <> 0;

  lv_imp_sald:= NVL(lv_imp_mcc,0);

  IF lv_imp_sald < 0 THEN
     lv_imp_sald := 0;
  END IF;

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CAMPA;

 FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_VENTA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT MIN(spc.cod_peri)
   INTO lv_cod_peri
   FROM
      seg_perio_corpo spc,
      cra_perio cp
   WHERE spc.oid_peri = cp.peri_oid_peri
     AND cp.fec_inic <= trunc(SYSDATE)
     AND cp.fec_fina >= trunc(SYSDATE);

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc,
      cob_detal_movim_carte cob
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri = lv_cod_peri
     AND mcc.imp_pend <> 0
     AND mcc.oid_movi_cc = cob.mvcc_oid_movi_cc(+)
     AND cob.mvcc_oid_movi_cc IS NULL;

  lv_imp_sald:= NVL(lv_imp_mcc,0);

  IF lv_imp_sald < 0 THEN
     lv_imp_sald := 0;
  END IF;

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CAMPA_VENTA;

  FUNCTION CCC_FN_OBTIE_SALDO_CAMPA_CARTE(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT MIN(spc.cod_peri)
   INTO lv_cod_peri
   FROM
      seg_perio_corpo spc,
      cra_perio cp
   WHERE spc.oid_peri = cp.peri_oid_peri
     AND cp.fec_inic <= trunc(SYSDATE)
     AND cp.fec_fina >= trunc(SYSDATE);

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc,
      cob_detal_movim_carte cob
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri = lv_cod_peri
     AND mcc.imp_pend <> 0
     AND cob.mvcc_oid_movi_cc = mcc.oid_movi_cc;

  lv_imp_sald:= NVL(lv_imp_mcc,0);

  IF lv_imp_sald < 0 THEN
     lv_imp_sald := 0;
  END IF;

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CAMPA_CARTE;

 FUNCTION CCC_FN_OBTIE_SALDO_ACUMU_CAMPA(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_mcc                     NUMBER(12,2):=0;
  lv_imp_sald                    NUMBER(12,2):=0;

 BEGIN

  IF p_cod_peri IS NULL THEN

   SELECT MIN(spc.cod_peri)
   INTO lv_cod_peri
   FROM
      seg_perio_corpo spc,
      cra_perio cp
   WHERE spc.oid_peri = cp.peri_oid_peri
     AND cp.fec_inic <= trunc(SYSDATE)
     AND cp.fec_fina >= trunc(SYSDATE);

  ELSE

   lv_cod_peri := p_cod_peri;

  END IF;

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri <= lv_cod_peri
     AND mcc.imp_pend <> 0;

  lv_imp_sald:= NVL(lv_imp_mcc,0);

  IF lv_imp_sald < 0 THEN
     lv_imp_sald := 0;
  END IF;

 RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_ACUMU_CAMPA;

 FUNCTION CCC_FN_OBTIE_SALDO_VENCI(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_fec_venc                     IN   DATE DEFAULT SYSDATE)
 RETURN NUMBER
 IS

    lv_imp_mcc                    NUMBER(12,2);
    lv_imp_ban                    NUMBER(12,2);
    lv_imp_sald                   NUMBER(12,2);

 BEGIN

  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_mcc
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=p_oid_clie
    AND mcc.fec_venc<=TRUNC(p_fec_venc);

  SELECT SUM(mb.imp_sald_pend)
  INTO lv_imp_ban
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie=p_oid_clie
    AND mb.cod_iden_proc='P';

  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_VENCI;

 FUNCTION CCC_FN_OBTIE_SALDO_PVENCE(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_imp_mcc                    NUMBER(12,2);
  lv_imp_sald                   NUMBER(12,2);

 BEGIN

  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_mcc
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=p_oid_clie
    AND mcc.fec_venc > TRUNC(SYSDATE);


  lv_imp_sald:= NVL(lv_imp_mcc,0);

  RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_PVENCE;

 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_imp_mcc                      NUMBER(12,2);
  lv_imp_ban                      NUMBER(12,2);
  lv_imp_sald                     NUMBER(12,2);

 BEGIN

  BEGIN

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.clie_oid_clie=p_oid_clie
     AND mcc.imp_pend<>0;

  EXCEPTION
   WHEN NO_DATA_FOUND THEN
    lv_imp_mcc:=0;
  END;

  BEGIN

   SELECT SUM(mb.imp_sald_pend)
   INTO lv_imp_ban
   FROM ccc_movim_banca mb
   WHERE mb.clie_oid_clie=p_oid_clie
     AND mb.cod_iden_proc='P'
     AND mb.imp_sald_pend<>0;

  EXCEPTION
   WHEN NO_DATA_FOUND THEN
    lv_imp_ban:=0;
  END;

  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_TOTAL;

 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS
  v_oid_clie                     mae_clien.oid_clie%TYPE;
  v_imp_mcc                      NUMBER(12,2);
  v_imp_ban                      NUMBER(12,2);
  v_imp_sald                     NUMBER(12,2);
 BEGIN

  SELECT mc.oid_clie
  INTO v_oid_clie
  FROM mae_clien mc
  WHERE mc.cod_clie=p_cod_clie;

  SELECT SUM(mcc.imp_pend)
  INTO v_imp_mcc
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=v_oid_clie;

  SELECT SUM(mb.imp_sald_pend)
  INTO v_imp_ban
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie=v_oid_clie
    AND mb.cod_iden_proc='P';

  v_imp_sald:= NVL(v_imp_mcc,0) - NVL(v_imp_ban,0);

  RETURN v_imp_sald;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_TOTAL;

 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL_HISTO(
  p_oid_clie                    IN   mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_imp_mcc                    NUMBER(12,2);
  lv_imp_ban                    NUMBER(12,2);
  lv_imp_sald                   NUMBER(12,2);

 BEGIN

  BEGIN

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mcc
   FROM ccc_movim_cuent_histo mcc
   WHERE mcc.clie_oid_clie = p_oid_clie
     AND mcc.imp_pend <> 0;

  EXCEPTION
      WHEN NO_DATA_FOUND THEN
         lv_imp_mcc := 0;
  END;

  BEGIN

   SELECT SUM(mb.imp_sald_pend)
   INTO lv_imp_ban
   FROM ccc_movim_banca_histo mb
   WHERE mb.clie_oid_clie=p_oid_clie
     AND mb.cod_iden_proc = 'P'
     AND mb.imp_sald_pend <> 0;

  EXCEPTION
   WHEN NO_DATA_FOUND THEN
    lv_imp_ban:=0;

  END;

  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  RETURN lv_imp_sald;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_TOTAL_HISTO;

 FUNCTION CCC_FN_OBTIE_SALDO_TOTAL_HISTO(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE)
 RETURN NUMBER
 IS

  lv_oid_clie                   mae_clien.oid_clie%TYPE;
  lv_imp_mcc                    NUMBER(12,2);
  lv_imp_ban                    NUMBER(12,2);
  lv_imp_sald                   NUMBER(12,2);

 BEGIN

  SELECT mc.oid_clie
  INTO lv_oid_clie
  FROM mae_clien mc
  WHERE mc.cod_clie = p_cod_clie;

  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_mcc
  FROM ccc_movim_cuent_histo mcc
  WHERE mcc.clie_oid_clie = lv_oid_clie;

  SELECT SUM(mb.imp_sald_pend)
  INTO lv_imp_ban
  FROM ccc_movim_banca_histo mb
  WHERE mb.clie_oid_clie = lv_oid_clie
    AND mb.cod_iden_proc='P';

  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  RETURN lv_imp_sald;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_TOTAL_HISTO;

 FUNCTION CCC_FN_OBTIE_SALDO_PAGAR(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER
 IS

  lv_oid_peri_actu                 cra_perio.oid_peri%TYPE;
  lnSaldo                          NUMBER;
  lv_cier_zona                     varchar2(1);
  lv_fact                          varchar2(1);
  ln_oid_tipo_soli_pais_c1         number(4);
  lv_cod_peri                      varchar2(6);
  lv_fec_fact                      varchar2(10);

 BEGIN

 /*
  SELECT MAX(cra.OID_PERI)
  INTO lv_oid_peri_cier
  FROM FAC_CONTR_CIERR con,
       FAC_TIPOS_CIERR tip,
       CRA_PERIO cra,
       SEG_PERIO_CORPO per
  WHERE con.ZORG_OID_REGI = lnOidRegion
    AND con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
    AND tip.COD_TIPO_CIER = 'R'
    AND cra.OID_PERI = con.Perd_Oid_Peri
    AND per.OID_PERI = cra.PERI_OID_PERI;
  */

  -- Recuperamos la Campa?a Actual
  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri_actu,lv_cod_peri,lv_fec_fact);

     select tsp.oid_tipo_soli_pais
     INTO ln_oid_tipo_soli_pais_c1
     FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
    WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
      AND ts.cod_tipo_soli = 'C1';

   select decode(count(1),0,'N','S') into lv_fact
     from ped_solic_cabec psc
    where psc.tspa_oid_tipo_soli_pais = ln_oid_tipo_soli_pais_c1
      and psc.perd_oid_peri = lv_oid_peri_actu
      and psc.clie_oid_clie = p_oid_clie
      and psc.grpr_oid_grup_proc = 5;

  --Validamos si cerro la zona
   select decode(count(1),0,'N','S') into lv_cier_zona
     from fac_contr_cierr cc,
          FAC_TIPOS_CIERR tc,
          zon_regio zr,
          zon_zona zz
    where cc.tcie_oid_tipo_cier = tc.oid_tipo_cier
      and tc.cod_tipo_cier = 'Z'
      and cc.val_resu_proc = 'OK'
      and cc.zorg_oid_regi is null
      and cc.perd_oid_peri = lv_oid_peri_actu
      and cc.zzon_oid_zona = zz.oid_zona
      and zz.cod_zona = gen_pkg_gener.gen_fn_clien_datos(gen_pkg_gener.gen_fn_devuelve_cod_clie(p_oid_clie) ,'COD_ZONA');

  IF ( (lv_fact = 'S') OR ( lv_cier_zona = 'S')) THEN
      lnSaldo := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1));
  ELSE
     lnSaldo := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,lv_cod_peri);
  END IF;

  RETURN lnSaldo;

 EXCEPTION
  WHEN OTHERS THEN
    RETURN 0;

 END CCC_FN_OBTIE_SALDO_PAGAR;

 FUNCTION CCC_FN_OBTIE_SALDO_PAGAR_EFTPG(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER
 IS

  lv_oid_peri_actu                 cra_perio.oid_peri%TYPE;
  lnSaldo                          NUMBER;
  lv_cier_zona                     varchar2(1);
  lv_fact                          varchar2(1);
  ln_oid_tipo_soli_pais_c1         number(4);
  lv_cod_peri                      varchar2(6);
  lv_fec_fact                      varchar2(10);

 BEGIN

 /*
  SELECT MAX(cra.OID_PERI)
  INTO lv_oid_peri_cier
  FROM FAC_CONTR_CIERR con,
       FAC_TIPOS_CIERR tip,
       CRA_PERIO cra,
       SEG_PERIO_CORPO per
  WHERE con.ZORG_OID_REGI = lnOidRegion
    AND con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
    AND tip.COD_TIPO_CIER = 'R'
    AND cra.OID_PERI = con.Perd_Oid_Peri
    AND per.OID_PERI = cra.PERI_OID_PERI;
  */

  -- Recuperamos la Campa?a Actual
  FIN_PKG_GENER.FIN_PR_OBTIE_PARAM_FACTU(lv_oid_peri_actu,lv_cod_peri,lv_fec_fact);

     select tsp.oid_tipo_soli_pais
     INTO ln_oid_tipo_soli_pais_c1
     FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
    WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
      AND ts.cod_tipo_soli = 'C1';

   select decode(count(1),0,'N','S') into lv_fact
     from ped_solic_cabec psc
    where psc.tspa_oid_tipo_soli_pais = ln_oid_tipo_soli_pais_c1
      and psc.perd_oid_peri = lv_oid_peri_actu
      and psc.grpr_oid_grup_proc = 5;

  --Validamos si cerro la zona
   select decode(count(1),0,'N','S') into lv_cier_zona
     from fac_contr_cierr cc,
          FAC_TIPOS_CIERR tc,
          zon_regio zr,
          zon_zona zz
    where cc.tcie_oid_tipo_cier = tc.oid_tipo_cier
      and tc.cod_tipo_cier = 'R'
      and cc.val_resu_proc = 'OK'
      and zr.oid_regi = cc.zorg_oid_regi
      and cc.perd_oid_peri = lv_oid_peri_actu
      and zr.oid_regi = zz.zorg_oid_regi
      and zz.cod_zona = gen_pkg_gener.gen_fn_clien_datos(gen_pkg_gener.gen_fn_devuelve_cod_clie(p_oid_clie) ,'COD_ZONA');

  IF ( (lv_fact = 'S') OR ( lv_cier_zona = 'S')) THEN
      lnSaldo := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1));
  ELSE
     lnSaldo := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,lv_cod_peri);
  END IF;

  RETURN lnSaldo;

 EXCEPTION
  WHEN OTHERS THEN
    RETURN 0;

 END CCC_FN_OBTIE_SALDO_PAGAR_EFTPG;

 FUNCTION CCC_FN_OBTIE_SALDO_DETAL (
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_fecha                        IN   DATE DEFAULT SYSDATE)
 RETURN NUMBER
 IS
      v_saldo NUMBER(12,2);
   BEGIN
      SELECT
         SUM(
                CASE sub.val_indi_cons
                  WHEN 'H' THEN
                    det.imp*-1
                   ELSE
                    det.imp
                END)
   INTO v_saldo
   FROM ccc_detal_cargo_abono_direc det,
             ccc_tipo_abono_subpr tas,
             ccc_subpr sub
   WHERE det.clie_oid_clie=p_oid_clie
   AND det.fec_movi <= p_fecha
   AND det.tasp_oid_tipo_abon_subp=tas.oid_tipo_abon_subp
   AND tas.subp_oid_subp=sub.oid_subp
   AND sub.val_indi_cons<>'N';

  RETURN v_Saldo;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_DETAL;

 FUNCTION CCC_FN_OBTIE_SALDO_CONTA(
  p_oid_clie   mae_clien.oid_clie%TYPE,
  p_fec_cont DATE)
 RETURN NUMBER
 IS

      lv_imp_carg NUMBER(12,2);
      lv_imp_abon NUMBER(12,2);
      lv_imp_sald NUMBER(12,2);

 BEGIN

      SELECT SUM(mcc.imp_movi)
      INTO lv_imp_carg
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.clie_oid_clie=p_oid_clie
      AND mcc.fec_docu<=p_fec_cont;

      SELECT SUM(mb.imp_pago)
      INTO lv_imp_abon
      FROM ccc_movim_banca mb
      WHERE mb.clie_oid_clie=p_oid_clie
      AND mb.cod_iden_proc='P'
      AND mb.fec_proc<=p_fec_cont;

      lv_imp_sald:=NVL(lv_imp_carg,0) - NVL(lv_imp_abon,0);

      RETURN lv_imp_sald;
 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_CONTA;

 FUNCTION CCC_FN_OBTIE_SALDO_COMER(
      p_oid_clie   mae_clien.oid_clie%TYPE,
      p_fec_pago DATE)
   RETURN NUMBER
   IS
      lv_imp_carg NUMBER(12,2);
      lv_imp_abon NUMBER(12,2);
      lv_imp_sald NUMBER(12,2);

 BEGIN

  SELECT SUM(mcc.imp_movi)
  INTO lv_imp_carg
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=p_oid_clie
    AND mcc.fec_docu<=p_fec_pago;

  SELECT SUM(mb.imp_pago)
  INTO lv_imp_abon
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie=p_oid_clie
    AND mb.cod_iden_proc='P'
    AND mb.fec_pago<=p_fec_pago;

  lv_imp_sald:=NVL(lv_imp_carg,0) - NVL(lv_imp_abon,0);

  RETURN lv_imp_sald;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_SALDO_COMER;

 FUNCTION CCC_FN_OBTIE_SALDO_ZONAS_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_oid_zona                       IN   zon_zona.oid_zona%TYPE)
 RETURN NUMBER
 IS

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_peri                      seg_perio_corpo.cod_peri%TYPE;
  lv_cod_peri_ante                 seg_perio_corpo.cod_peri%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;
  lv_fec_fact                      cra_crono.fec_inic%TYPE;
  lv_oid_acti_fact                 cra_activ.oid_acti%TYPE;
  lv_imp_sald                      NUMBER(12,2);

 BEGIN

  lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');

  SELECT ca.oid_acti
  INTO lv_oid_acti_fact
  FROM
   cra_activ ca,
   seg_pais sp
  WHERE ca.pais_oid_pais = sp.oid_pais
    AND ca.cod_acti = 'FA'
    AND sp.cod_pais = lv_cod_pais;

  SELECT bcf.cod_peri, cp.oid_peri
  INTO lv_cod_peri, lv_oid_peri
  FROM
   bas_ctrl_fact bcf,
   cra_perio cp,
   seg_perio_corpo spc
  WHERE bcf.cod_peri = spc.cod_peri
    AND spc.oid_peri = cp.peri_oid_peri
    AND bcf.ind_camp_act = 1
    AND bcf.sta_camp = 0;

  SELECT MAX(cr.fec_inic)
  INTO lv_fec_fact
  FROM
   cra_crono cr
  WHERE cr.cact_oid_acti = lv_oid_acti_fact
    AND cr.zzon_oid_zona = p_oid_zona
    AND cr.perd_oid_peri = lv_oid_peri;

  IF lv_fec_fact >= TRUNC(SYSDATE) THEN
   --lv_cod_peri_ante := FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,-1);
   lv_imp_sald := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,lv_cod_peri);
  ELSE
   lv_imp_sald := CCC_FN_OBTIE_SALDO_CAMPA_ANTER(p_oid_clie,FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(lv_cod_peri,1));
  END IF;

  RETURN lv_imp_sald;

 END CCC_FN_OBTIE_SALDO_ZONAS_CAMPA;

   FUNCTION CCC_FN_OBTIE_CODIG_CARGO_ABONO(
      p_oid_pais seg_pais.oid_pais%TYPE,
      p_oid_tipo_carg_abon ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE)
      RETURN VARCHAR2
   IS
      v_cod_Tipo_Carg_Abon ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE;
   BEGIN
      SELECT tas.cod_tipo_carg_abon
      INTO v_cod_Tipo_Carg_Abon
      FROM ccc_tipo_cargo_abono tas
      WHERE tas.pais_oid_pais=p_oid_pais
      AND tas.oid_tipo_carg_abon=p_oid_tipo_carg_abon;

      RETURN v_cod_Tipo_Carg_Abon;
   END CCC_FN_OBTIE_CODIG_CARGO_ABONO;

   FUNCTION CCC_FN_OBTIE_DESCR_CARGO_ABONO(
      p_oid_tipo_carg_abon ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE)
     RETURN VARCHAR2
   IS
      v_des_tipo_carg_abon gen_i18n_sicc_pais.val_i18n%TYPE;
   BEGIN
      SELECT g.val_i18n
      INTO v_Des_Tipo_Carg_Abon
      FROM gen_i18n_sicc_pais g
      WHERE g.val_oid=p_oid_tipo_carg_abon
      AND g.attr_enti LIKE 'CCC_TIPO_CARGO_ABONO';

      RETURN v_Des_Tipo_Carg_Abon;
   END CCC_FN_OBTIE_DESCR_CARGO_ABONO;

   FUNCTION CCC_FN_OBTIE_CODIG_BANCO_CCBAN(
      p_cod_cban   ccc_cuent_corri_banca.cod_cc%TYPE
   )
   RETURN NUMBER
   IS
      lv_oid_ccba    ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
   BEGIN
      SELECT ccb.oid_cuen_corr_banc
      INTO lv_oid_ccba
      FROM ccc_cuent_corri_banca ccb
      WHERE ccb.cod_cc = p_cod_cban;

      RETURN lv_oid_ccba;

    EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_CODIG_BANCO_CCBAN: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_CODIG_BANCO_CCBAN;

   FUNCTION CCC_FN_OBTIE_OID_BANCO_CCBAN(
      p_cod_cban   ccc_cuent_corri_banca.cod_cc%TYPE
   )
   RETURN NUMBER
   IS
      lv_oid_ccba    ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;

   BEGIN

      SELECT ccb.oid_cuen_corr_banc
      INTO lv_oid_ccba
      FROM ccc_cuent_corri_banca ccb
      WHERE ccb.cod_cc = p_cod_cban;

      RETURN lv_oid_ccba;

    EXCEPTION
      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_OID_BANCO_CCBAN: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_OID_BANCO_CCBAN;

   FUNCTION CCC_FN_OBTIE_FECHA_ULTIM_CARGO(
      p_oid_clie                            IN mae_clien.oid_clie%TYPE,
      p_fec_refe                          IN DATE DEFAULT SYSDATE)
   RETURN VARCHAR2
   IS

      lv_fec_ulti_carg                   DATE;

   BEGIN

      SELECT MAX(mcc.fec_docu)
      INTO lv_fec_ulti_carg
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.clie_oid_clie=p_oid_clie
      AND mcc.imp_movi > 0
      AND mcc.fec_docu <= p_fec_refe;

      RETURN TO_CHAR(lv_fec_ulti_carg,'DD/MM/YYYY');

   EXCEPTION

     WHEN no_data_found THEN
         RETURN lv_no_data;

      WHEN too_many_rows THEN
         RETURN lv_no_data;

      WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(SQLERRM, 1, 250);
         raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_FECHA_ULTIM_CARGO: ' ||
                               ls_sqlerrm);
   END CCC_FN_OBTIE_FECHA_ULTIM_CARGO;

 FUNCTION CCC_FN_OBTIE_CAMPA_ULTIM_CARGO(
  p_oid_clie                            IN mae_clien.oid_clie%TYPE,
  p_fec_refe                          IN DATE DEFAULT SYSDATE)
 RETURN VARCHAR2
 IS

  lv_oid_peri                    cra_perio.oid_peri%TYPE;
  lv_cod_peri                   seg_perio_corpo.cod_peri%TYPE;

 BEGIN

  SELECT MAX(mcc.perd_oid_peri)
  INTO lv_oid_peri
  FROM
   ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=p_oid_clie
    AND mcc.imp_movi > 0
    AND mcc.fec_docu <= p_fec_refe;

  SELECT spc.cod_peri
  INTO lv_cod_peri
  FROM
   cra_perio cp,
   seg_perio_corpo spc
  WHERE cp.peri_oid_peri = spc.oid_peri
    AND cp.oid_peri =  lv_oid_peri;

  RETURN lv_cod_peri;

 EXCEPTION

  WHEN no_data_found THEN
   RETURN lv_no_data;

  WHEN too_many_rows THEN
   RETURN lv_no_data;

  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 250);
   raise_application_error(-20123,
                              'ERROR CCC_FN_OBTIE_CAMPA_ULTIM_CARGO: ' ||
                               ls_sqlerrm);

 END CCC_FN_OBTIE_CAMPA_ULTIM_CARGO;

 FUNCTION CCC_FN_OBTIE_SALDO_UNICO(
  p_oid_clie mae_clien.oid_clie%TYPE)
 RETURN NUMBER
 IS

  lv_imp_mcc    NUMBER(12,2);
  lv_imp_ban     NUMBER(12,2);
  lv_imp_sald    NUMBER(12,2);

 BEGIN


  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_mcc
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie=p_oid_clie;

  SELECT SUM(mb.imp_sald_pend)
  INTO lv_imp_ban
  FROM ccc_movim_banca mb
  WHERE mb.clie_oid_clie=p_oid_clie
    AND mb.cod_iden_proc='P';

  lv_imp_sald:= NVL(lv_imp_mcc,0) - NVL(lv_imp_ban,0);

  RETURN lv_imp_sald;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_UNICO;

  /**************************************************************************
 Descripcion : Calcula el saldo deudor del cliente restando el valor en cupones
 Fecha Creacion : 17/10/2006
 Fecha Modificacion: 26/12/2006
 Parametros Entrada:
 ps_cod_clie : Codigo de cliente
 Autor : Jorge Florencio
 Modificaciones : Se esta retornando el valor 0 cuando el saldo de la deuda
 es negativo. (Carlos Hurtado Ramirez - 26/12/2006)
 ***************************************************************************/
 FUNCTION CCC_FN_CALCU_VALOR_SALDO_DEUDO(
  p_cod_clie      IN   VARCHAR2)
 RETURN NUMBER
 IS

  lv_oid_clie                    mae_clien.oid_clie%TYPE;
  lv_cod_peri                    seg_perio_corpo.cod_peri%TYPE;
  lv_imp_sald                    NUMBER(12,2):=0;
  lv_imp_mvcc                    NUMBER(12,2):=0;
  lv_imp_banc                    NUMBER(12,2):=0;
  lv_imp_cupo                    NUMBER(12,2):=0;

 BEGIN

  -- Obtenemos el oid del cliente
  BEGIN

   SELECT mc.oid_clie
   INTO lv_oid_clie
   FROM mae_clien mc
   WHERE mc.cod_clie = p_cod_clie;

  EXCEPTION

   WHEN NO_DATA_FOUND THEN
   RETURN 0;

  END;

  -- Obtenemos la campa?a de facturacion activa
  SELECT bc.cod_peri
  INTO lv_cod_peri
  FROM bas_ctrl_fact bc
  WHERE bc.ind_camp_act = 1;

  BEGIN

   SELECT SUM(mcc.imp_pend)
   INTO lv_imp_mvcc
   FROM
      ccc_movim_cuent_corri mcc,
      cra_perio cp,
      seg_perio_corpo spc
   WHERE mcc.clie_oid_clie = lv_oid_clie
     AND mcc.perd_oid_peri = cp.oid_peri
     AND spc.oid_peri = cp.peri_oid_peri
     AND spc.cod_peri < lv_cod_peri
     AND mcc.imp_pend <> 0;

   SELECT SUM(mb.imp_sald_pend)
   INTO lv_imp_banc
   FROM ccc_movim_banca mb
   WHERE mb.clie_oid_clie = lv_oid_clie
     AND mb.cod_iden_proc = 'P'
     AND mb.imp_sald_pend > 0;


   lv_imp_sald:= NVL(lv_imp_mvcc,0) - NVL(lv_imp_banc,0);

  EXCEPTION
   WHEN NO_DATA_FOUND THEN
     lv_imp_sald := 0;
  END;

  -- Obtengo el valor en cupones del cliente
  BEGIN
   SELECT SUM(CCC_DETAL_CUPON_TRAMI_DEPUR.IMP_DETA)
   INTO lv_imp_cupo
   FROM
    CCC_DETAL_CUPON_TRAMI_DEPUR,
    CCC_SITUA_CUPON
   WHERE CCC_SITUA_CUPON.OID_SITU_CUPO = CCC_DETAL_CUPON_TRAMI_DEPUR.SICU_OID_SITU_CUPO
     AND CCC_SITUA_CUPON.COD_SITU_CUPO = 'T'
     AND CCC_DETAL_CUPON_TRAMI_DEPUR.CLIE_OID_CLIE = lv_oid_clie;

  EXCEPTION

   WHEN NO_DATA_FOUND THEN
   lv_imp_cupo := 0;

  END;

  lv_imp_sald := lv_imp_sald - nvl(lv_imp_cupo,0);

  -- Si el saldo de la deuda es negativo, devolvemos 0
  IF lv_imp_sald < 0 THEN
   lv_imp_sald := 0;
  END IF;

  RETURN lv_imp_sald;

 END CCC_FN_CALCU_VALOR_SALDO_DEUDO;

 FUNCTION CCC_FN_VALID_EXIST_CUPON(
  p_cod_clie                     IN   mae_clien.cod_clie%TYPE,
  p_fec_pago                     IN   int_solic_conso_cupon_pago.fec_proc%TYPE,
  p_imp_pago                     IN   VARCHAR2)
 RETURN NUMBER
 IS

  lv_cant_cupo                   NUMBER(12,2);
  lv_imp_pago                    NUMBER(12,2);

 BEGIN

  lv_imp_pago := to_number(p_imp_pago,'9999.99');

  SELECT NVL(COUNT(*),0)
  INTO lv_cant_cupo
  FROM int_solic_conso_cupon_pago cup
  WHERE cup.cod_clie = p_cod_clie
    AND cup.fec_proc = p_fec_pago
    AND cup.imp_valo = lv_imp_pago;

  lv_cant_cupo := NVL(lv_cant_cupo,0);

  RETURN lv_cant_cupo;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_VALID_EXIST_CUPON;

 FUNCTION CCC_FN_OBTIE_MONTO_FLEXI_PEDID(
  p_oid_soli_cabe                       IN   ped_solic_cabec.oid_soli_cabe%TYPE)
 RETURN NUMBER
 IS

 BEGIN

  RETURN 0;

 END CCC_FN_OBTIE_MONTO_FLEXI_PEDID;

 FUNCTION CCC_FN_OBTIE_SALDO_FLEXI_ANTER(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

  lv_imp_sald_pend                 NUMBER(12,2);
  lv_oid_peri                      NUMBER(12);

 BEGIN

  SELECT cp.oid_peri
  INTO lv_oid_peri
  FROM cra_perio cp,
       seg_perio_corpo spc
  WHERE cp.peri_oid_peri = spc.oid_peri
  AND spc.cod_peri = p_cod_peri;

  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_sald_pend
  FROM
   flx_cuota_flexi_factu_detal fde,
   ccc_movim_cuent_corri mcc
  WHERE fde.oid_movi_carg_flex = mcc.oid_movi_cc
    AND fde.Oid_Peri_Cuot_Flex < lv_oid_peri
    AND fde.oid_clie = p_oid_clie;

  RETURN lv_imp_sald_pend;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_FLEXI_ANTER;

 FUNCTION CCC_FN_OBTIE_MONTO_FLEXI_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  P_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

  lv_imp_mont_flex                 NUMBER(12,2):=0;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;

 BEGIN

  SELECT cp.oid_peri
  INTO lv_oid_peri
  FROM cra_perio cp,
       seg_perio_corpo spc
  WHERE cp.peri_oid_peri = spc.oid_peri
  AND spc.cod_peri = p_cod_peri;

  SELECT SUM(mcc.imp_pend)
  INTO lv_imp_mont_flex
  FROM
   flx_cuota_flexi_factu_detal fde,
   ccc_movim_cuent_corri mcc
  WHERE fde.oid_movi_carg_flex = mcc.oid_movi_cc
    AND fde.Oid_Peri_Cuot_Flex = lv_oid_peri
    AND fde.oid_clie = p_oid_clie;

  RETURN lv_imp_mont_flex;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_MONTO_FLEXI_CAMPA;

 FUNCTION CCC_FN_OBTIE_SALDO_PROXI_PEDID(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER
 IS

  lv_oid_peri_maxi                 cra_perio.oid_peri%TYPE;
  lv_oid_peri_actu                 cra_perio.oid_peri%TYPE;
  lnAbonoPendiente                 NUMBER;
  lnSaldo                          NUMBER;

 BEGIN

  --Validamos si la consultora ha pasado pedido en la campa?a Siguiente

  SELECT MAX(psc.perd_oid_peri)
    INTO lv_oid_peri_maxi
    FROM PED_SOLIC_CABEC psc,
         PED_TIPO_SOLIC_PAIS tsp,
         PED_TIPO_SOLIC ts,
         MAE_CLIEN cl
   WHERE psc.IND_OC = 1
     AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
     AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
     AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
     AND ts.IND_DEVO  = 0
     AND ts.IND_ANUL  = 0
     AND psc.IND_TS_NO_CONSO = 1
     AND psc.MODU_OID_MODU <> 15
     AND psc.CLIE_OID_CLIE = cl.Oid_Clie
     AND cl.oid_CLIE = p_oid_clie;


  -- Recuperamos la Campa?a Actual
  lv_oid_peri_actu := FIN_PKG_GENER.FIN_FN_OBTIE_PERIO_ACTU;

  IF lv_oid_peri_maxi < lv_oid_peri_actu THEN
   -- Recuperamos el Saldo del Cliente de acuerdo si paso pedido en la campa?a evaluada anteriormente
   SELECT NVL(SUM(ccc.IMP_PEND), 0)
   INTO lnSaldo
    FROM
     CCC_MOVIM_CUENT_CORRI ccc,
     MAE_CLIEN cli
   WHERE cli.oid_CLIE = p_oid_clie
     AND ccc.CLIE_OID_CLIE = cli.OID_CLIE
     AND ccc.perd_oid_peri < lv_oid_peri_actu
     AND ccc.IMP_PEND > 0;

  ELSE
      SELECT NVL(SUM(ccc.IMP_PEND), 0)
   INTO lnSaldo
    FROM
     CCC_MOVIM_CUENT_CORRI ccc,
     MAE_CLIEN cli
   WHERE cli.oid_CLIE = p_oid_clie
     AND ccc.CLIE_OID_CLIE = cli.OID_CLIE
     AND ccc.perd_oid_peri <= lv_oid_peri_actu
     AND ccc.IMP_PEND > 0;

  END IF;


   -- Recuperamos el abono pendiente
   lnAbonoPendiente := CCC_PKG_GENER.CCC_FN_OBTIE_ABONO_PEND(p_oid_clie);

  lnSaldo := lnSaldo - lnAbonoPendiente;

  RETURN lnSaldo;


 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_SALDO_PROXI_PEDID;

 FUNCTION CCC_FN_OBTIE_ABONO_PEND(
  p_oid_clie                  IN   NUMBER)
 RETURN NUMBER
 IS

  lnAbonoPendiente                 NUMBER;

 BEGIN

   -- Recuperamos el abono pendiente
   SELECT NVL( cli.val_recl_pend , 0)
   INTO lnAbonoPendiente
    FROM
     MAE_CLIEN cli
   WHERE cli.oid_CLIE = p_oid_clie;


  RETURN lnAbonoPendiente;


 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_ABONO_PEND;


 FUNCTION CCC_FN_OBTIE_INTER_FLEXI_CAMPA(
  p_oid_clie                       IN   mae_clien.oid_clie%TYPE,
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 RETURN NUMBER
 IS

  lv_imp_inte_flex                 NUMBER(12,2);
  lv_fec_fact                      bas_ctrl_fact.fec_proc%TYPE;
  lv_cod_peri                      bas_ctrl_fact.cod_peri%TYPE;
  lv_oid_peri                      cra_perio.oid_peri%TYPE;

 BEGIN

  -- Obtenemos la campa?a de facturacion activa
  SELECT bc.fec_proc, bc.cod_peri
  INTO lv_fec_fact , lv_cod_peri
  FROM bas_ctrl_fact bc
  WHERE bc.ind_camp_act = 1
  AND bc.sta_camp = 0;

  SELECT cp.oid_peri
  INTO lv_oid_peri
  FROM cra_perio cp,
       seg_perio_corpo spc
  WHERE cp.peri_oid_peri = spc.oid_peri
    AND spc.cod_peri = lv_cod_peri;

  SELECT SUM(mcc.imp_movi)
  INTO lv_imp_inte_flex
  FROM ccc_movim_cuent_corri mcc
  WHERE mcc.clie_oid_clie = p_oid_clie
  AND mcc.subp_oid_subp_crea = 204
  AND mcc.fec_docu = lv_fec_fact
  AND mcc.perd_oid_peri = lv_oid_peri;

  RETURN lv_imp_inte_flex;

 EXCEPTION
  WHEN OTHERS THEN
   RETURN 0;

 END CCC_FN_OBTIE_INTER_FLEXI_CAMPA;

 /***************************************************************************
  Descripcion       : Genera la data de ped_solic_cabec,ped_solic_posic y
                         las tablas de region zona y seccion para la busqueda de
                         Diferencia de Precios de Cuenta Corriente.
  Fecha Creacion    : 23/04/2010
  Autor             : Jesse Rios
 ***************************************************************************/
 PROCEDURE CCC_PR_GENER_DIFER_PRECIO(psPrecioCorrecto VARCHAR2,
                                       psCodigoVenta    VARCHAR2,
                                     pnOidPeriodo     NUMBER,
                                     psCodigoRegion   VARCHAR2,
                                     psCodigoZona     VARCHAR2,
                                     psCodigoConsultora VARCHAR2)IS

 CURSOR c_interfaz IS
     SELECT k.COD_CLIE COD_CLIE,
            TRIM(k.VAL_NOM1) || ' ' || TRIM(k.VAL_NOM2) || ' ' ||
            TRIM(k.VAL_APE1) || ' ' || TRIM(k.VAL_APE2) NOMBRE,
            a.TERR_OID_TERR TERR_OID_TERR,
  a.ZZON_OID_ZONA ZZON_OID_ZONA,
            a.ZTAD_OID_TERR_ADMI ZTAD_OID_TERR_ADMI,
            a.VEPO_OID_VALO_ESTR_GEOP VEPO_OID_VALO_ESTR_GEOP,
            a.clie_oid_clie CLIE_OID_CLIE,
            a.cldi_oid_clie_dire CLDI_OID_CLIE_DIRE,
            a.TDOC_OID_TIPO_DOCU TDOC_OID_TIPO_DOCU,
            a.TICL_OID_TIPO_CLIE TICL_OID_TIPO_CLIE,
            a.SBTI_OID_SUBT_CLIE SBTI_OID_SUBT_CLIE,
            a.prod_oid_prod PROD_OID_PROD,
            a.val_codi_vent VAL_CODI_VENT,
            d.COD_TERR TERRITORIO,
            e.COD_SECC SECCION,
            f.COD_ZONA ZONA,
            a.VAL_PORC_DESC VAL_PORC_DESC,
            a.NUM_UNID_ATEN NUM_UNID_ATEN,
            a.VAL_PREC_FACT_UNIT_LOCA VAL_PREC_FACT_UNIT_LOCA,
            a.VAL_PREC_FACT_TOTA_LOCA VAL_PREC_FACT_TOTA_LOCA,
            a.MON_FACT_REAL_UNIT,
            a.MON_FACT_REAL_TOTA,
            a.MON_A_CORR
      FROM CCC_GTT_BUSQU_DIFER_PRECI a,
           zon_terri_admin c,
           zon_terri d,
           zon_secci e,
           zon_zona  f,
            mae_clien                 k,
            zon_regio                 l
      WHERE a.ztad_oid_terr_admi = c.oid_terr_admi
      AND c.terr_oid_terr = d.oid_terr
      AND c.zscc_oid_secc = e.oid_secc
      AND e.zzon_oid_zona = f.oid_zona
        AND f.zorg_oid_regi = l.oid_regi
        AND a.clie_oid_clie = k.oid_clie
        AND l.cod_regi = NVL(psCodigoRegion, l.cod_regi)
        AND f.cod_zona = NVL(psCodigoZona, f.cod_zona)
        AND (k.cod_clie = NVL(psCodigoConsultora, k.cod_clie) OR
            k.cod_clie in
            (select regexp_substr(psCodigoConsultora, '[^,]+', 1, level)
                from dual
              connect by regexp_substr(psCodigoConsultora, '[^,]+', 1, level) is not null));

      TYPE interfazRec IS RECORD(

           COD_CLIE                    mae_clien.cod_clie%TYPE,
           NOMBRE                      VARCHAR2(100),
           TERR_OID_TERR               CCC_GTT_BUSQU_DIFER_PRECI.TERR_OID_TERR%TYPE,
           ZZON_OID_ZONA               CCC_GTT_BUSQU_DIFER_PRECI.ZZON_OID_ZONA%TYPE,
           ZTAD_OID_TERR_ADMI          CCC_GTT_BUSQU_DIFER_PRECI.ZTAD_OID_TERR_ADMI%TYPE,
           VEPO_OID_VALO_ESTR_GEOP     CCC_GTT_BUSQU_DIFER_PRECI.VEPO_OID_VALO_ESTR_GEOP%TYPE,
           CLIE_OID_CLIE               CCC_GTT_BUSQU_DIFER_PRECI.CLIE_OID_CLIE%TYPE,
           CLDI_OID_CLIE_DIRE          CCC_GTT_BUSQU_DIFER_PRECI.CLDI_OID_CLIE_DIRE%TYPE,
           TDOC_OID_TIPO_DOCU          CCC_GTT_BUSQU_DIFER_PRECI.TDOC_OID_TIPO_DOCU%TYPE,
           TICL_OID_TIPO_CLIE          CCC_GTT_BUSQU_DIFER_PRECI.TICL_OID_TIPO_CLIE%TYPE,
           SBTI_OID_SUBT_CLIE          CCC_GTT_BUSQU_DIFER_PRECI.SBTI_OID_SUBT_CLIE%TYPE,
           PROD_OID_PROD               CCC_GTT_BUSQU_DIFER_PRECI.PROD_OID_PROD%TYPE,
           VAL_CODI_VENT               CCC_GTT_BUSQU_DIFER_PRECI.VAL_CODI_VENT%TYPE,
           COD_TERR                    zon_terri.COD_TERR%TYPE,
           COD_SECC                    zon_secci.COD_SECC%TYPE,
           COD_ZONA                    zon_zona.COD_ZONA%TYPE,
           VAL_PORC_DESC               CCC_GTT_BUSQU_DIFER_PRECI.VAL_PORC_DESC%TYPE,
           NUM_UNID_ATEN               CCC_GTT_BUSQU_DIFER_PRECI.NUM_UNID_ATEN%TYPE,
           VAL_PREC_FACT_UNIT_LOCA     CCC_GTT_BUSQU_DIFER_PRECI.VAL_PREC_FACT_UNIT_LOCA%TYPE,
           VAL_PREC_FACT_TOTA_LOCA     CCC_GTT_BUSQU_DIFER_PRECI.VAL_PREC_FACT_TOTA_LOCA%TYPE,
           MON_FACT_REAL_UNIT          CCC_GTT_BUSQU_DIFER_PRECI.MON_FACT_REAL_UNIT%TYPE,
           MON_FACT_REAL_TOTA          CCC_GTT_BUSQU_DIFER_PRECI.MON_FACT_REAL_TOTA%TYPE,
           MON_A_CORR                  CCC_GTT_BUSQU_DIFER_PRECI.MON_A_CORR%TYPE
      );

      TYPE interfazRecCab IS TABLE OF interfazRec;

      interfazRecord interfazRecCab;

      vnPrecioCorrecto NUMBER(12,2);
      vsCodigoPeriodo varchar2(6);
      vsValorResultado ccc_audit_infor.val_resu%TYPE;
      vnTotalOK number;
      vnTotalError number;

 BEGIN

  EXECUTE IMMEDIATE 'TRUNCATE TABLE CCC_TEMPO_BUSQU_DIFER_PRECI';

  vnPrecioCorrecto := TO_NUMBER(psPrecioCorrecto,'999999999.99');

  INSERT INTO CCC_GTT_BUSQU_DIFER_PRECI
   SELECT
    a.TERR_OID_TERR  TERR_OID_TERR,
    a.ZZON_OID_ZONA ZZON_OID_ZONA,
    a.ZTAD_OID_TERR_ADMI ZTAD_OID_TERR_ADMI,
    a.VEPO_OID_VALO_ESTR_GEOP VEPO_OID_VALO_ESTR_GEOP
                , a.clie_oid_clie CLIE_OID_CLIE
                , a.cldi_oid_clie_dire CLDI_OID_CLIE_DIRE
                , a.TDOC_OID_TIPO_DOCU TDOC_OID_TIPO_DOCU
                , a.TICL_OID_TIPO_CLIE TICL_OID_TIPO_CLIE
                , a.SBTI_OID_SUBT_CLIE SBTI_OID_SUBT_CLIE
                , b.prod_oid_prod PROD_OID_PROD
                , b.val_codi_vent VAL_CODI_VENT
                , b.VAL_PORC_DESC
                , b.NUM_UNID_ATEN
                , b.VAL_PREC_FACT_UNIT_LOCA
                , b.VAL_PREC_FACT_TOTA_LOCA
                , vnPrecioCorrecto - (vnPrecioCorrecto * NVL (b.val_porc_desc, 0) / 100) MON_FACT_REAL_UNIT
                , (vnPrecioCorrecto - (vnPrecioCorrecto * NVL (b.val_porc_desc, 0) / 100)) * b.num_unid_aten MON_FACT_REAL_TOTA
                , ((vnPrecioCorrecto - (vnPrecioCorrecto * NVL (b.val_porc_desc, 0) / 100)) * b.num_unid_aten) - b.val_prec_fact_tota_loca MON_A_CORR
          FROM ped_solic_cabec a,
               ped_solic_posic b,
               (SELECT i.oid_tipo_soli_pais
                  FROM ped_tipo_solic_pais i, ped_tipo_solic j, ped_clase_solic l
                 WHERE i.tsol_oid_tipo_soli = j.oid_tipo_soli
                   AND j.clso_oid_clas_soli = l.oid_clas_soli
                   AND l.ind_orde_comp = 1
                   AND j.ind_soli_nega = 0) tipo_soli
         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
           AND a.perd_oid_peri = pnOidPeriodo
           AND a.tspa_oid_tipo_soli_pais = tipo_soli.oid_tipo_soli_pais
           AND b.val_codi_vent = psCodigoVenta
           AND b.espo_oid_esta_posi <> 2
           AND b.num_unid_aten > 0;

         SELECT a.cod_peri
         INTO vsCodigoPeriodo
         FROM seg_perio_corpo a,
         cra_perio b,
         seg_canal c,
         seg_marca d
         WHERE a.oid_peri = b.peri_oid_peri
         and b.CANA_OID_CANA = c.OID_CANA
         and b.MARC_OID_MARC = d.OID_MARC
         and c.COD_CANA = 'VD'
         and d.COD_MARC = 'T'
         and b.oid_peri = pnOidPeriodo;

        OPEN c_interfaz;

        LOOP

            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF interfazRecord.COUNT > 0 THEN

               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                   BEGIN
                     SELECT VAL_RESU
                     INTO vsValorResultado
                     FROM ccc_audit_infor
                     WHERE COD_PERI = vsCodigoPeriodo
                     AND cod_clie = interfazRecord(x).COD_CLIE;
                   EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                        vsValorResultado := null;
                   END;

                   IF vsValorResultado = 'OK' THEN
                      vnTotalOK := 1;
                   ELSE
                       vnTotalOK := 0;
                   END IF;

                   IF vsValorResultado IS NULL THEN
                      vnTotalError := 0;
                   ELSIF vsValorResultado = 'OK' THEN
                         vnTotalError := 0;
                   ELSE
                        vnTotalError := 1;
                   END IF;

                   INSERT INTO CCC_TEMPO_BUSQU_DIFER_PRECI(
                   COD_CLIE,
                   VAL_NOMB,
                   TERR_OID_TERR,
                   ZZON_OID_ZONA,
                   ZTAD_OID_TERR_ADMI,
                   VEPO_OID_VALO_ESTR_GEOP,
                   CLIE_OID_CLIE,
                   CLDI_OID_CLIE_DIRE,
                   TDOC_OID_TIPO_DOCU,
                   TICL_OID_TIPO_CLIE,
                   SBTI_OID_SUBT_CLIE,
                   PROD_OID_PROD,
                   VAL_CODI_VENT,
                   COD_TERR,
                   COD_SECC,
                   COD_ZONA,
                   VAL_PORC_DESC,
                   NUM_UNID_ATEN,
                   VAL_PREC_FACT_UNIT_LOCA,
                   VAL_PREC_FACT_TOTA_LOCA,
                   MON_FACT_REAL_UNIT,
                   MON_FACT_REAL_TOTA,
                   MON_A_CORR,
                   VAL_RESU,
                   TOT_CARG,
                   TOT_OK,
                   TOT_ERRO)
                   VALUES(
                   interfazRecord(x).COD_CLIE,
                   interfazRecord(x).NOMBRE,
                   interfazRecord(x).TERR_OID_TERR,
                   interfazRecord(x).ZZON_OID_ZONA,
                   interfazRecord(x).ZTAD_OID_TERR_ADMI,
                   interfazRecord(x).VEPO_OID_VALO_ESTR_GEOP,
                   interfazRecord(x).CLIE_OID_CLIE,
                   interfazRecord(x).CLDI_OID_CLIE_DIRE,
                   interfazRecord(x).TDOC_OID_TIPO_DOCU,
                   interfazRecord(x).TICL_OID_TIPO_CLIE,
                   interfazRecord(x).SBTI_OID_SUBT_CLIE,
                   interfazRecord(x).PROD_OID_PROD,
                   interfazRecord(x).VAL_CODI_VENT,
                   interfazRecord(x).COD_TERR,
                   interfazRecord(x).COD_SECC,
                   interfazRecord(x).COD_ZONA,
                   interfazRecord(x).VAL_PORC_DESC,
                   interfazRecord(x).NUM_UNID_ATEN,
                   interfazRecord(x).VAL_PREC_FACT_UNIT_LOCA,
                   interfazRecord(x).VAL_PREC_FACT_TOTA_LOCA,
                   interfazRecord(x).MON_FACT_REAL_UNIT,
                   interfazRecord(x).MON_FACT_REAL_TOTA,
                   interfazRecord(x).MON_A_CORR,
                   vsValorResultado,
                   1,
                   vnTotalOK,
                   vnTotalError
                   );
   END LOOP;

  END IF;

  EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;

  CLOSE c_interfaz;

 EXCEPTION

  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 250);
   raise_application_error(-20123,'ERROR CCC_PR_GENER_DIFER_PRECIO: ' || ls_sqlerrm);

 END CCC_PR_GENER_DIFER_PRECIO;

 FUNCTION CCC_FN_OBTIE_MTMIN_DEUDA_WEB(
  p_oid_clie                     IN   mae_clien.oid_clie%TYPE,
  p_sal_deud_ante                IN   mae_clien.sal_deud_ante%TYPE DEFAULT NULL)
 RETURN NUMBER
 IS

 BEGIN
/* IF p_sal_deud_ante < 100 THEN
    return p_sal_deud_ante;
 ELSE
   return 100;
 END IF;*/

 RETURN 0;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;

 END CCC_FN_OBTIE_MTMIN_DEUDA_WEB;
 
/***************************************************************************
  Descripcion       : Función que obtiene la sumatoria de todos los tipos de
                      documento del Resumen de Ventas del tipo Consolidación
                      de Factura Activa.
  Fecha Creacion    : 27/10/2015
  Autor             : Aurelio Oviedo
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_SUMAT_TIPO_DOCUM(
  p_fecha_desde                IN   VARCHAR2,
  p_fecha_hasta                IN   VARCHAR2)
 RETURN NUMBER
 IS
 
 lnSumaTotal            NUMBER(12,2);
 
 CURSOR c_interfaz IS
     SELECT *
       FROM (SELECT ftd.DES_TIPO_DOCU, 
                SUM(f.val_tota_paga_loca) TOTAL
               FROM fac_docum_conta_cabec f,
                    fac_tipo_docum ftd
              WHERE f.tido_oid_tipo_docu = ftd.oid_tipo_docu
                AND f.val_tota_paga_loca > 0
                AND f.tido_oid_tipo_docu in (1,9)
                AND f.fec_fact >= TO_DATE(p_fecha_desde, 'DD/MM/YYYY')
                AND f.fec_fact <= TO_DATE(p_fecha_hasta, 'DD/MM/YYYY')
              GROUP BY ftd.des_tipo_docu);

  TYPE interfazRec IS RECORD(
    DES_TIPO_DOCU               fac_tipo_docum.DES_TIPO_DOCU%TYPE,
    TOTAL                       fac_docum_conta_cabec.val_tota_paga_loca%TYPE
  );

  TYPE interfazRecCab IS TABLE OF interfazRec;
  interfazRecord interfazRecCab;
 
 BEGIN
    
    lnSumaTotal := 0;   
 
    OPEN c_interfaz;
        LOOP
        FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
            IF interfazRecord.COUNT > 0 THEN
                FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                   lnSumaTotal := lnSumaTotal + interfazRecord(x).TOTAL;

                END LOOP;
            END IF;
        EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
    CLOSE c_interfaz;
 
    RETURN lnSumaTotal;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;
       
 END CCC_FN_OBTIE_SUMAT_TIPO_DOCUM;

/***************************************************************************
  Descripcion       : Función que obtiene el valor de las facturas anuladas 
                      del Resumen de Ventas del tipo Consolidación de Factura Activa.
  Fecha Creacion    : 27/10/2015
  Autor             : Aurelio Oviedo
 ***************************************************************************/
 FUNCTION CCC_FN_OBTIE_VALOR_FACTU_ANULA(
  p_fecha_desde                IN   VARCHAR2,
  p_fecha_hasta                IN   VARCHAR2)
 RETURN NUMBER
 IS
 
 lnTipoDocumento                    VARCHAR2(20);
 lnValorFacturasAnuladas            NUMBER(12,2);
  
 BEGIN
    
    lnValorFacturasAnuladas := 0;
    
    SELECT 'Facturas Anuladas', SUM(f.val_tota_paga_loca)
      INTO lnTipoDocumento, lnValorFacturasAnuladas
      FROM fac_docum_conta_cabec f,
           fac_tipo_docum ftd
     WHERE f.tido_oid_tipo_docu = ftd.oid_tipo_docu
       AND f.val_tota_paga_loca > 0
       AND f.fec_fact >= TO_DATE(p_fecha_desde, 'DD/MM/YYYY')
       AND f.fec_fact <= TO_DATE(p_fecha_hasta, 'DD/MM/YYYY')
       AND f.tido_oid_tipo_docu in (1,9)
       AND EXISTS (SELECT NULL   
                     FROM ped_solic_cabec a,
                          ped_solic_cabec b,
                          ped_tipo_solic_pais   j,
                          ped_tipo_solic        k
                    WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
                      AND a.tspa_oid_tipo_soli_pais = j.oid_tipo_soli_pais
                      AND j.tsol_oid_tipo_soli = k.oid_tipo_soli
                      AND k.ind_anul = 1
                      AND b.soca_oid_docu_refe = f.soca_oid_soli_cabe
                      AND a.fec_fact >= TO_DATE(p_fecha_desde, 'DD/MM/YYYY')
                      AND a.fec_fact <= TO_DATE(p_fecha_hasta, 'DD/MM/YYYY'));   
  
    RETURN lnValorFacturasAnuladas;

 EXCEPTION
    WHEN OTHERS THEN
       RETURN 0;
       
 END CCC_FN_OBTIE_VALOR_FACTU_ANULA;

END CCC_PKG_GENER;
/
