CREATE OR REPLACE PACKAGE "OCR_SOLIC_PEDIDOS" AS
  TYPE micursor IS REF CURSOR;

  /************************************************************************/
  /* Procedimiento  :  Modulo de Solicitudes de Pedidos                  */
  /* Descripcion    :                                                  */
  /* Creado         : Marco Silva                                         */
  /* Fecha      : 19/10/2005                                              */
  /************************************************************************/

  /************************************************************************/
  /* Procedimientos de la tabla CONSOLIDADO CABECERA                     */
  /*                                                                  */
  /************************************************************************/

  /********************************************************************************/
  /* Procedimiento :SP_OBTPK_SOLIC_CONSO_CABEC                                      */
  /* Parametros:                                            Descripcion:         */
  /*        p_COD_PAIS                      Codigo Pais                        */
  /*        p_COD_PERI                      Codigo Periodo                    */
  /*        p_COD_CLIE                      Codigo Cliente                    */
  /*        p_FEC_SOLI                      Fecha Solicitud                    */
  /* Creado    : Marco Silva Package                                            */
  /* Fecha     : 15/11/2006                                                 */
  /********************************************************************************/

  PROCEDURE sp_obtpk_solic_conso_cabec(
                                       
                                       p_cod_pais IN int_solic_conso_cabec.cod_pais%TYPE,
                                       p_cod_peri IN int_solic_conso_cabec.cod_peri%TYPE,
                                       p_cod_clie IN int_solic_conso_cabec.cod_clie%TYPE,
                                       p_fec_soli IN int_solic_conso_cabec.fec_soli%TYPE,
                                       cursorout  OUT micursor);

  /************************************************************************/
  /* Procedimientos :SP_OBT_SOLIC_CONSO_CABEC                               */
  /* Creado         : Marco Silva                                         */
  /* Fecha      : 15/11/2006                                              */
  /************************************************************************/

  PROCEDURE sp_obt_solic_conso_cabec(cursorout OUT micursor);

  /********************************************************************************/
  /* Procedimiento :SP_CREAR_SOLIC_CONSO_CABEC                                     */
  /* Parametros:                                            Descripcion:          */
  /*  p_COD_PAIS                     Codigo Pais                               */
  /*  p_COD_PERI                     Codigo Periodo                           */
  /*  p_COD_CLIE                     Codigo Cliente                           */
  /*  p_FEC_SOLI                     Fecha Solicitud                           */
  /*  p_NUM_CLIE                     Numero Clientes                           */
  /*  p_TIPO_SOLI                     Tipo Solicitud                         */
  /*  p_COD_SBAC                     Codigo Subacceso                         */
  /*  p_COD_ACCE                     Codigo Acceso                             */
  /*  p_TIP_DESP                     Tipo Despacho                             */
  /*  p_STA_PROC                     Status del proceso                       */
  /*  p_NOM_CLIE                     Nombre del Cliente                       */
  /*  p_COD_REGI                     Codigo Region                             */
  /*  p_DES_REGI                     Descripcion Region                       */
  /*  p_COD_ZONA                     Codigo Zona                               */
  /*  p_DES_ZONA                     Descripcion Zona                         */
  /*  p_COD_TERR                     Codigo Territorio                         */
  /*  p_VAL_MONT_PEDI                     VALOR MONTO DEL PEDIDO             */
  /*  p_TIP_ORDE                     TIPO DE ORDEN [N] : NORMAL; [E] : ESPECIAL; [P] : PRIMEROS PEDIDOS */
  /*  p_VAL_SALD_DEUD                     VALOR DEL SALDO DE LA DEUDA           */
  /*  p_IND_ERRO_RECH                     INDICADOR DE ERROR OCS RECHAZADA [0] : NO RECHAZADA; [1] : OCS RECHAZADA */
  /*  p_IND_ERRO_DEUD                     INDICADOR DE ERROR POR DEUDA.  [0] : SIN DEUDA PENDIENTE; [1] : CON DEUDA PENDIENTE */
  /*  p_IND_ERRO_MTMI                     INDICADOR DE ERROR POR MONTO MINIMO.  [0] : SIN ERROR POR MONTO MINIMO; [1] : CON ERROR POR MONTO MINIMO */
  /*  p_IND_ERRO_MTMA                     INDICADOR DE ERROR POR MONTO MAXIMO.  [0] : SIN ERROR;  [1] : CON ERROR POR MONTO MAXIMO */
  /*  p_IND_ERRO_UNMA                     INDICADOR DE ERROR POR UNIDADES MAXIMAS.  [0] : SIN ERROR;  [1] : CON ERROR POR UNIDADES MAXIMAS */
  /*  p_IND_ERROR_SGPE                     INDICADOR DE ERROR POR SEGUNDO PEDIDO.  [0] : SIN ERROR;  [1] : CON ERROR DEBIDO A QUE LA CONSULTORA YA CUENTA CON UN PEDIDO PROCESADO EN LA PRESENTE CAMPA?A */
  /*  p_IND_ERRO_NODE                     INDICADOR DE ERROR POR CABECERA SIN DETALLE.  [0] : SIN ERROR;  [1] : ORDEN SIN DETALLE */
  /*  p_IND_BLOQ_ADMI                     INDICADOR DE BLOQUEO ADMINISTRATIVO [0] : OCS SIN BLOQUEO;  [1] : OCS CON BLOQUEO */
  /*  p_IND_BLOQ_FINA                     INDICADOR DE BLOQUEO FINANCIERO [0] : OCS SIN BLOQUEO;  [1] : OCS CON BLOQUEO */
  /*  p_IND_OCS_PROC                     INDICADOR DE OCS PROCESADA.  [0] :  OCS PENDIENTE DE ENVIO; [1] : OCS ENVIADA A SICC PARA SU PROCSAMIENTO */
  /*  p_IND_OCS_BLOQ                     INDICADOR DE OCS BLOQUEADA.  [0] : OCS SIN BLOQUEO; [1] : OCS BLOQUEADA */
  /*  p_IND_ADMI_CART                     INDICADOR DE ADMINISTRACION DE CARTERA.  [0] : OCS SIN LEVANTAMIENTO DE DEUDA;  [1] : OCS CON LEVANTAMIENTO DE DEUDA */
  /*  p_IND_COMP_MONT                     INDICADOR DE COMPLEMENTO DE MONTO MINIMO.  [0] : OCS SIN COMPLEMENTO; [1] :  OCS HA SIDO COMPLETADA PARA COMPLETAR EL MONTO MINIMO DE ACEPTACION */
  /*  p_USU_DIGI                     USUARIO DIGITADOR                       */
  /*  p_FEC_DIGI                     FECHA DIGITACION                       */
  /*  p_USU_MODI                     USUARIO MODIFICADOR                     */
  /*  p_FEC_MODI                     FECHA MODIFICACION                     */
  /* Creado    : Marco Silva                                                     */
  /* Fecha     : 15/11/2006                                                      */
  /********************************************************************************/

  PROCEDURE sp_crear_solic_conso_cabec(
                                       
                                       p_cod_pais IN OUT int_solic_conso_cabec.cod_pais%TYPE,
                                       
                                       p_cod_peri IN OUT int_solic_conso_cabec.cod_peri%TYPE,
                                       
                                       p_cod_clie IN OUT int_solic_conso_cabec.cod_clie%TYPE,
                                       
                                       p_fec_soli IN OUT int_solic_conso_cabec.fec_soli%TYPE,
                                       
                                       p_num_clie IN int_solic_conso_cabec.num_clie%TYPE,
                                       
                                       p_tipo_soli IN int_solic_conso_cabec.tipo_soli%TYPE,
                                       
                                       p_cod_sbac IN int_solic_conso_cabec.cod_sbac%TYPE,
                                       
                                       p_cod_acce IN int_solic_conso_cabec.cod_acce%TYPE,
                                       
                                       p_tip_desp IN int_solic_conso_cabec.tip_desp%TYPE,
                                       
                                       p_sta_proc IN int_solic_conso_cabec.sta_proc%TYPE,
                                       
                                       p_nom_clie IN int_solic_conso_cabec.nom_clie%TYPE,
                                       
                                       p_cod_regi IN int_solic_conso_cabec.cod_regi%TYPE,
                                       
                                       p_des_regi IN int_solic_conso_cabec.des_regi%TYPE,
                                       
                                       p_cod_zona IN int_solic_conso_cabec.cod_zona%TYPE,
                                       
                                       p_des_zona IN int_solic_conso_cabec.des_zona%TYPE,
                                       
                                       p_cod_terr IN int_solic_conso_cabec.cod_terr%TYPE,
                                       
                                       p_val_mont_pedi IN int_solic_conso_cabec.val_mont_pedi%TYPE,
                                       
                                       p_tip_orde IN int_solic_conso_cabec.tip_orde%TYPE,
                                       
                                       p_val_sald_deud IN int_solic_conso_cabec.val_sald_deud%TYPE,
                                       
                                       p_ind_erro_rech IN int_solic_conso_cabec.ind_erro_rech%TYPE,
                                       
                                       p_ind_erro_deud IN int_solic_conso_cabec.ind_erro_deud%TYPE,
                                       
                                       p_ind_erro_mtmi IN int_solic_conso_cabec.ind_erro_mtmi%TYPE,
                                       
                                       p_ind_erro_mtma IN int_solic_conso_cabec.ind_erro_mtma%TYPE,
                                       
                                       p_ind_erro_unma IN int_solic_conso_cabec.ind_erro_unma%TYPE,
                                       
                                       p_ind_error_sgpe IN int_solic_conso_cabec.ind_error_sgpe%TYPE,
                                       
                                       p_ind_erro_node IN int_solic_conso_cabec.ind_erro_node%TYPE,
                                       
                                       p_ind_bloq_admi IN int_solic_conso_cabec.ind_bloq_admi%TYPE,
                                       
                                       p_ind_bloq_fina IN int_solic_conso_cabec.ind_bloq_fina%TYPE,
                                       
                                       p_ind_ocs_proc IN int_solic_conso_cabec.ind_ocs_proc%TYPE,
                                       
                                       p_ind_ocs_bloq IN int_solic_conso_cabec.ind_ocs_bloq%TYPE,
                                       
                                       p_ind_admi_cart IN int_solic_conso_cabec.ind_admi_cart%TYPE,
                                       
                                       p_ind_comp_mont IN int_solic_conso_cabec.ind_comp_mont%TYPE,
                                       
                                       p_usu_digi IN int_solic_conso_cabec.usu_digi%TYPE,
                                       
                                       p_fec_digi IN int_solic_conso_cabec.fec_digi%TYPE,
                                       
                                       p_usu_modi IN int_solic_conso_cabec.usu_modi%TYPE,
                                       
                                       p_fec_modi IN int_solic_conso_cabec.fec_modi%TYPE
                                       
                                       );

  /********************************************************************************/
  /* Procedimiento :SP_ACT_SOLIC_CONSO_CABEC                 */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_COD_CLIE                     Codigo Cliente */
  /*  p_FEC_SOLI                     Fecha Solicitud */
  /*  p_NUM_CLIE                     Numero Clientes */
  /*  p_TIPO_SOLI                     Tipo Solicitud */
  /*  p_COD_SBAC                     Codigo Subacceso */
  /*  p_COD_ACCE                     Codigo Acceso */
  /*  p_TIP_DESP                     Tipo Despacho */
  /*  p_STA_PROC                     Status del proceso */
  /*  p_NOM_CLIE                     Nombre del Cliente */
  /*  p_COD_REGI                     Codigo Region */
  /*  p_DES_REGI                     Descripcion Region */
  /*  p_COD_ZONA                     Codigo Zona */
  /*  p_DES_ZONA                     Descripcion Zona */
  /*  p_COD_TERR                     Codigo Territorio */
  /*  p_VAL_MONT_PEDI                     VALOR MONTO DEL PEDIDO */
  /*  p_TIP_ORDE                     TIPO DE ORDEN [N] : NORMAL; [E] : ESPECIAL; [P] : PRIMEROS PEDIDOS
  */
  /*  p_VAL_SALD_DEUD                     VALOR DEL SALDO DE LA DEUDA
  */
  /*  p_IND_ERRO_RECH                     INDICADOR DE ERROR OCS RECHAZADA [0] : NO RECHAZADA; [1] : OCS RECHAZADA */
  /*  p_IND_ERRO_DEUD                     INDICADOR DE ERROR POR DEUDA.  [0] : SIN DEUDA PENDIENTE; [1] : CON DEUDA PENDIENTE */
  /*  p_IND_ERRO_MTMI                     INDICADOR DE ERROR POR MONTO MINIMO.  [0] : SIN ERROR POR MONTO MINIMO; [1] : CON ERROR POR MONTO MINIMO */
  /*  p_IND_ERRO_MTMA                     INDICADOR DE ERROR POR MONTO MAXIMO.  [0] : SIN ERROR;  [1] : CON ERROR POR MONTO MAXIMO */
  /*  p_IND_ERRO_UNMA                     INDICADOR DE ERROR POR UNIDADES MAXIMAS.  [0] : SIN ERROR;  [1] : CON ERROR POR UNIDADES MAXIMAS */
  /*  p_IND_ERROR_SGPE                     INDICADOR DE ERROR POR SEGUNDO PEDIDO.  [0] : SIN ERROR;  [1] : CON ERROR DEBIDO A QUE LA CONSULTORA YA CUENTA CON UN PEDIDO PROCESADO EN LA PRESENTE CAMPA?A */
  /*  p_IND_ERRO_NODE                     INDICADOR DE ERROR POR CABECERA SIN DETALLE.  [0] : SIN ERROR;  [1] : ORDEN SIN DETALLE */
  /*  p_IND_BLOQ_ADMI                     INDICADOR DE BLOQUEO ADMINISTRATIVO [0] : OCS SIN BLOQUEO;  [1] : OCS CON BLOQUEO */
  /*  p_IND_BLOQ_FINA                     INDICADOR DE BLOQUEO FINANCIERO [0] : OCS SIN BLOQUEO;  [1] : OCS CON BLOQUEO */
  /*  p_IND_OCS_PROC                     INDICADOR DE OCS PROCESADA.  [0] :  OCS PENDIENTE DE ENVIO; [1] : OCS ENVIADA A SICC PARA SU PROCSAMIENTO */
  /*  p_IND_OCS_BLOQ                     INDICADOR DE OCS BLOQUEADA.  [0] : OCS SIN BLOQUEO; [1] : OCS BLOQUEADA */
  /*  p_IND_ADMI_CART                     INDICADOR DE ADMINISTRACION DE CARTERA.  [0] : OCS SIN LEVANTAMIENTO DE DEUDA;  [1] : OCS CON LEVANTAMIENTO DE DEUDA */
  /*  p_IND_COMP_MONT                     INDICADOR DE COMPLEMENTO DE MONTO MINIMO.  [0] : OCS SIN COMPLEMENTO; [1] :  OCS HA SIDO COMPLETADA PARA COMPLETAR EL MONTO MINIMO DE ACEPTACION */
  /*  p_USU_DIGI                     USUARIO DIGITADOR
  */
  /*  p_FEC_DIGI                     FECHA DIGITACION
  */
  /*  p_USU_MODI                     USUARIO MODIFICADOR
  */
  /*  p_FEC_MODI                     FECHA MODIFICACION
  */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/
  PROCEDURE sp_act_solic_conso_cabec(
                                     
                                     p_cod_pais IN OUT int_solic_conso_cabec.cod_pais%TYPE,
                                     
                                     p_cod_peri IN OUT int_solic_conso_cabec.cod_peri%TYPE,
                                     
                                     p_cod_clie IN OUT int_solic_conso_cabec.cod_clie%TYPE,
                                     
                                     p_fec_soli IN OUT int_solic_conso_cabec.fec_soli%TYPE,
                                     
                                     p_num_clie IN int_solic_conso_cabec.num_clie%TYPE,
                                     
                                     p_tipo_soli IN int_solic_conso_cabec.tipo_soli%TYPE,
                                     
                                     p_cod_sbac IN int_solic_conso_cabec.cod_sbac%TYPE,
                                     
                                     p_cod_acce IN int_solic_conso_cabec.cod_acce%TYPE,
                                     
                                     p_tip_desp IN int_solic_conso_cabec.tip_desp%TYPE,
                                     
                                     p_sta_proc IN int_solic_conso_cabec.sta_proc%TYPE,
                                     
                                     p_nom_clie IN int_solic_conso_cabec.nom_clie%TYPE,
                                     
                                     p_cod_regi IN int_solic_conso_cabec.cod_regi%TYPE,
                                     
                                     p_des_regi IN int_solic_conso_cabec.des_regi%TYPE,
                                     
                                     p_cod_zona IN int_solic_conso_cabec.cod_zona%TYPE,
                                     
                                     p_des_zona IN int_solic_conso_cabec.des_zona%TYPE,
                                     
                                     p_cod_terr IN int_solic_conso_cabec.cod_terr%TYPE,
                                     
                                     p_val_mont_pedi IN int_solic_conso_cabec.val_mont_pedi%TYPE,
                                     
                                     p_tip_orde IN int_solic_conso_cabec.tip_orde%TYPE,
                                     
                                     p_val_sald_deud IN int_solic_conso_cabec.val_sald_deud%TYPE,
                                     
                                     p_ind_erro_rech IN int_solic_conso_cabec.ind_erro_rech%TYPE,
                                     
                                     p_ind_erro_deud IN int_solic_conso_cabec.ind_erro_deud%TYPE,
                                     
                                     p_ind_erro_mtmi IN int_solic_conso_cabec.ind_erro_mtmi%TYPE,
                                     
                                     p_ind_erro_mtma IN int_solic_conso_cabec.ind_erro_mtma%TYPE,
                                     
                                     p_ind_erro_unma IN int_solic_conso_cabec.ind_erro_unma%TYPE,
                                     
                                     p_ind_error_sgpe IN int_solic_conso_cabec.ind_error_sgpe%TYPE,
                                     
                                     p_ind_erro_node IN int_solic_conso_cabec.ind_erro_node%TYPE,
                                     
                                     p_ind_bloq_admi IN int_solic_conso_cabec.ind_bloq_admi%TYPE,
                                     
                                     p_ind_bloq_fina IN int_solic_conso_cabec.ind_bloq_fina%TYPE,
                                     
                                     p_ind_ocs_proc IN int_solic_conso_cabec.ind_ocs_proc%TYPE,
                                     
                                     p_ind_ocs_bloq IN int_solic_conso_cabec.ind_ocs_bloq%TYPE,
                                     
                                     p_ind_admi_cart IN int_solic_conso_cabec.ind_admi_cart%TYPE,
                                     
                                     p_ind_comp_mont IN int_solic_conso_cabec.ind_comp_mont%TYPE,
                                     
                                     p_usu_digi IN int_solic_conso_cabec.usu_digi%TYPE,
                                     
                                     p_fec_digi IN int_solic_conso_cabec.fec_digi%TYPE,
                                     
                                     p_usu_modi IN int_solic_conso_cabec.usu_modi%TYPE,
                                     
                                     p_fec_modi IN int_solic_conso_cabec.fec_modi%TYPE
                                     
                                     );

  /********************************************************************************/
  /* Procedimiento :SP_ELI_SOLIC_CONSO_CABEC                  */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_COD_CLIE                     Codigo Cliente */
  /*  p_FEC_SOLI                     Fecha Solicitud */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/
  PROCEDURE sp_eli_solic_conso_cabec(
                                     
                                     p_cod_pais IN int_solic_conso_cabec.cod_pais%TYPE,
                                     
                                     p_cod_peri IN int_solic_conso_cabec.cod_peri%TYPE,
                                     
                                     p_cod_clie IN int_solic_conso_cabec.cod_clie%TYPE,
                                     
                                     p_fec_soli IN int_solic_conso_cabec.fec_soli%TYPE
                                     
                                     );
  /************************************************************************/
  /* Procedimientos de la tabla CONTROL FACTURACION                        */
  /*                                                                   */
  /************************************************************************/

  /********************************************************************************/
  /* Procedimiento :SP_OBTPK_CTRL_FACT*/
  /* Parametros:                                            Descripcion:   */
  /*        p_COD_PAIS                      Codigo Pais  */
  /*        p_COD_PERI                      Codigo Periodo  */
  /* Creado    : Marco Silva Package                                          */
  /* Fecha     : 15/11/2006       */
  /********************************************************************************/

  PROCEDURE sp_obtpk_ctrl_fact(
                               
                               p_cod_pais IN bas_ctrl_fact.cod_pais%TYPE,
                               p_cod_peri IN bas_ctrl_fact.cod_peri%TYPE,
                               cursorout  OUT micursor);

  /************************************************************************/
  /* Procedimientos :SP_OBT_CTRL_FACT         */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 15/11/2006                                          */
  /************************************************************************/

  PROCEDURE sp_obt_ctrl_fact(cursorout OUT micursor);

  /********************************************************************************/
  /* Procedimiento :SP_CREAR_CTRL_FACT   */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_FEC_PROC                     Fecha Proceso */
  /*  p_VAL_MNT_MIN_FACT                     Valor Monto Minimo Facturacion */
  /*  p_VAL_MNT_MIN_ACEPT                     Valor Monto Minimo Aceptacion */
  /*  p_VAL_MNT_MAX                     Valor Monto Maximo */
  /*  p_VAL_UNID_MAX                     Valor Unidades Maxima */
  /*  p_STA_CAMP                     Status de la Campanha: 0: Activa, 1: Cerrada */
  /*  p_USU_DIGI                      */
  /*  p_FEC_DIGI                      */
  /*  p_USU_MODI                      */
  /*  p_FEC_MODI                      */
  /* Creado    : Marco Silva                                                   */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_crear_ctrl_fact(
                               
                               p_cod_pais IN OUT bas_ctrl_fact.cod_pais%TYPE,
                               
                               p_cod_peri IN OUT bas_ctrl_fact.cod_peri%TYPE,
                               
                               p_fec_proc IN bas_ctrl_fact.fec_proc%TYPE,
                               
                               p_val_mnt_min_fact IN bas_ctrl_fact.val_mnt_min_fact%TYPE,
                               
                               p_val_mnt_min_acept IN bas_ctrl_fact.val_mnt_min_acept%TYPE,
                               
                               p_val_mnt_max IN bas_ctrl_fact.val_mnt_max%TYPE,
                               
                               p_val_unid_max IN bas_ctrl_fact.val_unid_max%TYPE,
                               
                               p_sta_camp IN bas_ctrl_fact.sta_camp%TYPE,
                               
                               p_usu_digi IN bas_ctrl_fact.usu_digi%TYPE,
                               
                               p_fec_digi IN bas_ctrl_fact.fec_digi%TYPE,
                               
                               p_usu_modi IN bas_ctrl_fact.usu_modi%TYPE,
                               
                               p_fec_modi IN bas_ctrl_fact.fec_modi%TYPE
                               
                               );

  /********************************************************************************/
  /* Procedimiento :SP_ACT_CTRL_FACT                 */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_FEC_PROC                     Fecha Proceso */
  /*  p_VAL_MNT_MIN_FACT                     Valor Monto Minimo Facturacion */
  /*  p_VAL_MNT_MIN_ACEPT                     Valor Monto Minimo Aceptacion */
  /*  p_VAL_MNT_MAX                     Valor Monto Maximo */
  /*  p_VAL_UNID_MAX                     Valor Unidades Maxima */
  /*  p_STA_CAMP                     Status de la Campanha: 0: Activa, 1: Cerrada */
  /*  p_USU_DIGI                      */
  /*  p_FEC_DIGI                      */
  /*  p_USU_MODI                      */
  /*  p_FEC_MODI                      */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/
  PROCEDURE sp_act_ctrl_fact(
                             
                             p_cod_pais IN OUT bas_ctrl_fact.cod_pais%TYPE,
                             
                             p_cod_peri IN OUT bas_ctrl_fact.cod_peri%TYPE,
                             
                             p_fec_proc IN bas_ctrl_fact.fec_proc%TYPE,
                             
                             p_val_mnt_min_fact IN bas_ctrl_fact.val_mnt_min_fact%TYPE,
                             
                             p_val_mnt_min_acept IN bas_ctrl_fact.val_mnt_min_acept%TYPE,
                             
                             p_val_mnt_max IN bas_ctrl_fact.val_mnt_max%TYPE,
                             
                             p_val_unid_max IN bas_ctrl_fact.val_unid_max%TYPE,
                             
                             p_sta_camp IN bas_ctrl_fact.sta_camp%TYPE,
                             
                             p_usu_digi IN bas_ctrl_fact.usu_digi%TYPE,
                             
                             p_fec_digi IN bas_ctrl_fact.fec_digi%TYPE,
                             
                             p_usu_modi IN bas_ctrl_fact.usu_modi%TYPE,
                             
                             p_fec_modi IN bas_ctrl_fact.fec_modi%TYPE
                             
                             );

  /********************************************************************************/
  /* Procedimiento :SP_ELI_CTRL_FACT                  */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/
  PROCEDURE sp_eli_ctrl_fact(
                             
                             p_cod_pais IN bas_ctrl_fact.cod_pais%TYPE,
                             
                             p_cod_peri IN bas_ctrl_fact.cod_peri%TYPE
                             
                             );

  /************************************************************************/
  /* Procedimientos de la tabla COD VENT AGREGAC                         */
  /*                                        */
  /************************************************************************/

  /********************************************************************************/
  /* Procedimiento :SP_OBTPK_COD_VENT_AGREG*/
  /* Parametros:                                            Descripcion:   */
  /*        p_COD_PAIS                      Codigo Pais  */
  /*        p_COD_PERI                      Codigo Periodo  */
  /*        p_COD_VENT                      Codigo de Venta  */
  /* Creado    : Marco Silva Package                                          */
  /* Fecha     : 15/11/2006       */
  /********************************************************************************/

  PROCEDURE sp_obtpk_cod_vent_agreg(
                                    
                                    p_cod_pais IN ped_cod_vent_agreg.cod_pais%TYPE,
                                    p_cod_peri IN ped_cod_vent_agreg.cod_peri%TYPE,
                                    p_cod_vent IN ped_cod_vent_agreg.cod_vent%TYPE,
                                    cursorout  OUT micursor);

  /************************************************************************/
  /* Procedimientos :SP_OBT_COD_VENT_AGREG         */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 15/11/2006                                          */
  /************************************************************************/

  PROCEDURE sp_obt_cod_vent_agreg(cursorout OUT micursor);

  /********************************************************************************/
  /* Procedimiento :SP_CREAR_COD_VENT_AGREG   */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_COD_VENT                     Codigo de Venta */
  /*  p_VAL_UNIT                     Valor Unitario */
  /*  p_COD_PROD                     codigo de producto */
  /*  p_DES_PROD                     Descripcion Producto */
  /*  p_NIV_PRIOR                      */
  /*  p_STA_REG                     Estado del Registro */
  /*  p_USU_DIGI                     Usuario Digitador */
  /*  p_FEC_DIGI                     Fecha Digitacion */
  /*  p_USU_MODI                     Usuario Modifica */
  /*  p_FEC_MODI                     Fecha Modificacion */
  /* Creado    : Marco Silva                                                   */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_crear_cod_vent_agreg(
                                    
                                    p_cod_pais IN OUT ped_cod_vent_agreg.cod_pais%TYPE,
                                    
                                    p_cod_peri IN OUT ped_cod_vent_agreg.cod_peri%TYPE,
                                    
                                    p_cod_vent IN OUT ped_cod_vent_agreg.cod_vent%TYPE,
                                    
                                    p_val_unit IN ped_cod_vent_agreg.val_unit%TYPE,
                                    
                                    p_cod_prod IN ped_cod_vent_agreg.cod_prod%TYPE,
                                    
                                    p_des_prod IN ped_cod_vent_agreg.des_prod%TYPE,
                                    
                                    p_niv_prior IN ped_cod_vent_agreg.niv_prior%TYPE,
                                    
                                    p_sta_reg IN ped_cod_vent_agreg.sta_reg%TYPE,
                                    
                                    p_usu_digi IN ped_cod_vent_agreg.usu_digi%TYPE,
                                    
                                    p_fec_digi IN ped_cod_vent_agreg.fec_digi%TYPE,
                                    
                                    p_usu_modi IN ped_cod_vent_agreg.usu_modi%TYPE,
                                    
                                    p_fec_modi IN ped_cod_vent_agreg.fec_modi%TYPE
                                    
                                    );

  /********************************************************************************/
  /* Procedimiento :SP_ACT_COD_VENT_AGREG                 */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_COD_VENT                     Codigo de Venta */
  /*  p_VAL_UNIT                     Valor Unitario */
  /*  p_COD_PROD                     codigo de producto */
  /*  p_DES_PROD                     Descripcion Producto */
  /*  p_NIV_PRIOR                      */
  /*  p_STA_REG                     Estado del Registro */
  /*  p_USU_DIGI                     Usuario Digitador */
  /*  p_FEC_DIGI                     Fecha Digitacion */
  /*  p_USU_MODI                     Usuario Modifica */
  /*  p_FEC_MODI                     Fecha Modificacion */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_act_cod_vent_agreg(
                                  
                                  p_cod_pais IN OUT ped_cod_vent_agreg.cod_pais%TYPE,
                                  
                                  p_cod_peri IN OUT ped_cod_vent_agreg.cod_peri%TYPE,
                                  
                                  p_cod_vent IN OUT ped_cod_vent_agreg.cod_vent%TYPE,
                                  
                                  p_val_unit IN ped_cod_vent_agreg.val_unit%TYPE,
                                  
                                  p_cod_prod IN ped_cod_vent_agreg.cod_prod%TYPE,
                                  
                                  p_des_prod IN ped_cod_vent_agreg.des_prod%TYPE,
                                  
                                  p_niv_prior IN ped_cod_vent_agreg.niv_prior%TYPE,
                                  
                                  p_sta_reg IN ped_cod_vent_agreg.sta_reg%TYPE,
                                  
                                  p_usu_digi IN ped_cod_vent_agreg.usu_digi%TYPE,
                                  
                                  p_fec_digi IN ped_cod_vent_agreg.fec_digi%TYPE,
                                  
                                  p_usu_modi IN ped_cod_vent_agreg.usu_modi%TYPE,
                                  
                                  p_fec_modi IN ped_cod_vent_agreg.fec_modi%TYPE
                                  
                                  );

  /********************************************************************************/
  /* Procedimiento :SP_ELI_COD_VENT_AGREG                  */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Codigo Pais */
  /*  p_COD_PERI                     Codigo Periodo */
  /*  p_COD_VENT                     Codigo de Venta */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_eli_cod_vent_agreg(
                                  
                                  p_cod_pais IN ped_cod_vent_agreg.cod_pais%TYPE,
                                  
                                  p_cod_peri IN ped_cod_vent_agreg.cod_peri%TYPE,
                                  
                                  p_cod_vent IN ped_cod_vent_agreg.cod_vent%TYPE
                                  
                                  );
  /************************************************************************/
  /* Procedimientos de la tabla CONSOLIDADO DETALLE                         */
  /*                                        */
  /************************************************************************/

  /********************************************************************************/
  /* Procedimiento :SP_OBTPK_SOLIC_CONSO_DETAL*/
  /* Parametros:                                            Descripcion:   */
  /*        p_COD_PAIS                      Pais  */
  /*        p_COD_PERI                      Campanha  */
  /*        p_COD_CLIE                      Clliente  */
  /*        p_COD_VENT                      Codigo Venta  */
  /*        p_TIP_POSIC                      Tipo Posicion, p.e: OC  */
  /* Creado    : Marco Silva Package                                          */
  /* Fecha     : 15/11/2006       */
  /********************************************************************************/

  PROCEDURE sp_obtpk_solic_conso_detal(
                                       
                                       p_cod_pais  IN int_solic_conso_detal.cod_pais%TYPE,
                                       p_cod_peri  IN int_solic_conso_detal.cod_peri%TYPE,
                                       p_cod_clie  IN int_solic_conso_detal.cod_clie%TYPE,
                                       p_cod_vent  IN int_solic_conso_detal.cod_vent%TYPE,
                                       p_tip_posic IN int_solic_conso_detal.tip_posic%TYPE,
                                       cursorout   OUT micursor);

  /************************************************************************/
  /* Procedimientos :SP_OBT_SOLIC_CONSO_DETAL         */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 15/11/2006                                          */
  /************************************************************************/

  PROCEDURE sp_obt_solic_conso_detal(cursorout OUT micursor);

  /********************************************************************************/
  /* Procedimiento :SP_CREAR_SOLIC_CONSO_DETAL   */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Pais */
  /*  p_COD_PERI                     Campanha */
  /*  p_COD_CLIE                     Clliente */
  /*  p_COD_VENT                     Codigo Venta */
  /*  p_TIP_POSIC                     Tipo Posicion, p.e: OC */
  /*  p_DES_PROD                     Descripcion de Producto */
  /*  p_VAL_UNID_DEM                     Unidades demandadas */
  /*  p_VAL_UNID_COMP                     Unidades completadas */
  /*  p_STA_PROC                     Estado Proceso */
  /*  p_IND_COMP_MTMI                     Indica Complemento para Monto Minimo de Pedido */
  /*  p_USU_DIGI                     usuairo digitador */
  /*  p_FEC_DIGI                     fecha digitacion */
  /*  p_USU_MODI                     usuario modificacion */
  /*  p_FEC_MODI                     fecha modificacion */
  /* Creado    : Marco Silva                                                   */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_crear_solic_conso_detal(
                                       
                                       p_cod_pais IN OUT int_solic_conso_detal.cod_pais%TYPE,
                                       
                                       p_cod_peri IN OUT int_solic_conso_detal.cod_peri%TYPE,
                                       
                                       p_cod_clie IN OUT int_solic_conso_detal.cod_clie%TYPE,
                                       
                                       p_cod_vent IN OUT int_solic_conso_detal.cod_vent%TYPE,
                                       
                                       p_tip_posic IN OUT int_solic_conso_detal.tip_posic%TYPE,
                                       
                                       p_des_prod IN int_solic_conso_detal.des_prod%TYPE,
                                       
                                       p_val_unid_dem IN int_solic_conso_detal.val_unid_dem%TYPE,
                                       
                                       p_val_unid_comp IN int_solic_conso_detal.val_unid_comp%TYPE,
                                       
                                       p_sta_proc IN int_solic_conso_detal.sta_proc%TYPE,
                                       
                                       p_ind_comp_mtmi IN int_solic_conso_detal.ind_comp_mtmi%TYPE,
                                       
                                       p_usu_digi IN int_solic_conso_detal.usu_digi%TYPE,
                                       
                                       p_fec_digi IN int_solic_conso_detal.fec_digi%TYPE,
                                       
                                       p_usu_modi IN int_solic_conso_detal.usu_modi%TYPE,
                                       
                                       p_fec_modi IN int_solic_conso_detal.fec_modi%TYPE
                                       
                                       );

  /********************************************************************************/
  /* Procedimiento :SP_ACT_SOLIC_CONSO_DETAL                 */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Pais */
  /*  p_COD_PERI                     Campanha */
  /*  p_COD_CLIE                     Clliente */
  /*  p_COD_VENT                     Codigo Venta */
  /*  p_TIP_POSIC                     Tipo Posicion, p.e: OC */
  /*  p_DES_PROD                     Descripcion de Producto */
  /*  p_VAL_UNID_DEM                     Unidades demandadas */
  /*  p_VAL_UNID_COMP                     Unidades completadas */
  /*  p_STA_PROC                     Estado Proceso */
  /*  p_IND_COMP_MTMI                     Indica Complemento para Monto Minimo de Pedido */
  /*  p_USU_DIGI                     usuairo digitador */
  /*  p_FEC_DIGI                     fecha digitacion */
  /*  p_USU_MODI                     usuario modificacion */
  /*  p_FEC_MODI                     fecha modificacion */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/

  PROCEDURE sp_act_solic_conso_detal(
                                     
                                     p_cod_pais IN OUT int_solic_conso_detal.cod_pais%TYPE,
                                     
                                     p_cod_peri IN OUT int_solic_conso_detal.cod_peri%TYPE,
                                     
                                     p_cod_clie IN OUT int_solic_conso_detal.cod_clie%TYPE,
                                     
                                     p_cod_vent IN OUT int_solic_conso_detal.cod_vent%TYPE,
                                     
                                     p_tip_posic IN OUT int_solic_conso_detal.tip_posic%TYPE,
                                     
                                     p_des_prod IN int_solic_conso_detal.des_prod%TYPE,
                                     
                                     p_val_unid_dem IN int_solic_conso_detal.val_unid_dem%TYPE,
                                     
                                     p_val_unid_comp IN int_solic_conso_detal.val_unid_comp%TYPE,
                                     
                                     p_sta_proc IN int_solic_conso_detal.sta_proc%TYPE,
                                     
                                     p_ind_comp_mtmi IN int_solic_conso_detal.ind_comp_mtmi%TYPE,
                                     
                                     p_usu_digi IN int_solic_conso_detal.usu_digi%TYPE,
                                     
                                     p_fec_digi IN int_solic_conso_detal.fec_digi%TYPE,
                                     
                                     p_usu_modi IN int_solic_conso_detal.usu_modi%TYPE,
                                     
                                     p_fec_modi IN int_solic_conso_detal.fec_modi%TYPE
                                     
                                     );

  /********************************************************************************/
  /* Procedimiento :SP_ELI_SOLIC_CONSO_DETAL                  */
  /* Parametros:                                            Descripcion:  */
  /*  p_COD_PAIS                     Pais */
  /*  p_COD_PERI                     Campanha */
  /*  p_COD_CLIE                     Clliente */
  /*  p_COD_VENT                     Codigo Venta */
  /*  p_TIP_POSIC                     Tipo Posicion, p.e: OC */
  /* Creado    : Marco Silva                                            */
  /* Fecha     : 15/11/2006                                            */
  /********************************************************************************/
  PROCEDURE sp_eli_solic_conso_detal(
                                     
                                     p_cod_pais IN int_solic_conso_detal.cod_pais%TYPE,
                                     
                                     p_cod_peri IN int_solic_conso_detal.cod_peri%TYPE,
                                     
                                     p_cod_clie IN int_solic_conso_detal.cod_clie%TYPE,
                                     
                                     p_cod_vent IN int_solic_conso_detal.cod_vent%TYPE,
                                     
                                     p_tip_posic IN int_solic_conso_detal.tip_posic%TYPE
                                     
                                     );

  FUNCTION gen_fn_campanha(psperioid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_desc_gener
  (
    pstablaoid    IN NUMBER,
    psdescripcion IN VARCHAR2
  ) RETURN VARCHAR2;

  FUNCTION gen_fn_marca(psperioid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_canal(psperioid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_acceso(psperioid IN NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_tipo_perio(psperioid IN NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_marca_prod(psproductooid IN NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_nego_prod(psproductooid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_codsap_prod(psproductooid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_desc_prod(psproductooid IN NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_REC_NUM_LOTE                              */
  /*******************************************/

  FUNCTION gen_fn_rec_num_lote(pslineaoperrecoid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_rec_tipo_oper(pstipooperoid IN NUMBER) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Obtiene la siguiente campanha del archivo de Ctrl de Fact */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_sgte_campa(pscodpais VARCHAR2) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Obtiene la siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_sgte_campa(pscodperiodo VARCHAR2) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Obtiene la anterior campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_ant_campa(pscodperiodo VARCHAR2) RETURN VARCHAR2;

  FUNCTION gen_fn_rec_cod_oper(pstipooperoid IN NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve Id de Posicion
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             pnOidPedCabec :
             pnOidProducto
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_rec_oid_posic
  (
    pnoidpedcabec NUMBER,
    pnoidproducto NUMBER
  ) RETURN NUMBER;

  /*****************************************/
  /*            GEN_FN_REC_COD_TIPO_ING                              */
  /*******************************************/

  FUNCTION gen_fn_rec_cod_tipo_ing(pstipoingoid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_rec_cod_almac(pstipooperoid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_rec_tipo_movi_almac(pstipooperoid IN NUMBER)
    RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_COD_VENTA                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_venta(psmatrfactoid IN NUMBER) RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_NUMLIN_OFER                               */
  /******************************************************************/

  FUNCTION gen_fn_numlin_ofer(psmatrfactoid IN NUMBER) RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_NUM_OFER                              */
  /******************************************************************/

  FUNCTION gen_fn_num_ofer(psmatrfactoid IN NUMBER) RETURN NUMBER;

  /******************************************************************/
  /*                   GEN_FN_COD_ESTR                              */
  /******************************************************************/

  FUNCTION gen_fn_cod_estr(psmatrfactoid IN NUMBER) RETURN VARCHAR2;

  /******************************************************************/
  /*                   GEN_FN_COD_TIPO_OFERT                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_tipo_ofert(pstipofertoid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_sub_acceso(pssbacoid IN NUMBER) RETURN VARCHAR2;

  FUNCTION gen_fn_des_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_COD_REG                              */
  /*******************************************/
  FUNCTION gen_fn_cod_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_OID_REG                              */
  /*******************************************/
  FUNCTION gen_fn_oid_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2;

  FUNCTION gen_fn_cod_clie(psclienteoid IN NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_COD_PAIS                              */
  /*******************************************/

  FUNCTION gen_fn_cod_pais(pspaisoid IN NUMBER) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Existe Solicitud Detalle                  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/
  FUNCTION gen_fn_exist_detal
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :Existe Segundo Pedido                                  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_exist_pedido
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER;
  /************************************************************************/
  /* Descripcion    :Existe Segundo Pedido                                  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_exist_pedido2
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :Indicador Reemplazo                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_ind_reemplazo
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :Indicador Rechazado                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_ind_rechaz
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2,
    psfecha      VARCHAR2
  ) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :Indicador GEN_FN_REMP_CUPON                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_remp_cupon
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcupon   VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el Sgte Numero de Lote por Periodo del Bas Ctrl
  Fecha Creacion     : 29/01/2008
  Parametros Entrada :
  Autor              : Mrco Silva
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_lote_perio
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /************************************************************************
   Descripcion    :Consolida las cabeceras de OCS
   Autor          : Jose Cairampoma G.
   Fecha          : 25/10/2011
  ************************************************************************/
  PROCEDURE ocr_pr_solic_conso_cabec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  );
  /**************************************************************************
    Descripcion       : Consolida las detalles de OCS
    Fecha Creacion    : 25/10/2011
    Parametros:
        psCodigoPais : Codigo de pais
        psCodigoPeriodo : Codigo de periodo
        psUsuario : Codigo de Usuario
    Autor             : Jose Cairampoma G.
  ***************************************************************************/
  PROCEDURE ocr_pr_solic_conso_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  );
  /**************************************************************************/
  /* Descripcion    :Actualiza Indicador Activo de Consultora               */
  /* Autor         : Marco Silva                                            */
  /* Fecha      : 23/04/2007                                                */
  /**************************************************************************/

  PROCEDURE ocr_pr_act_ind_cont_act(pscodigopais VARCHAR2);

  /************************************************************************/
  /* Descripcion    :Recepciona OCS  DETALLE               */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/
  /*
   PROCEDURE OCR_PR_SOLIC_CONSO_DETAL(psCodigoPais VARCHAR2, psCodigoPeriodo VARCHAR2, psFechaFact VARCHAR2, psCodigoUsuario VARCHAR2);
  */

  /**************************************************************************
  Descripcion       : OCR_PR_DIGIT_CABEC_DETAL
                      Inserta Cabeceras Detalles hacia temporales de Pedidos
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE ocr_pr_digit_cabec_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  );

  /**************************************************************************
  Descripcion       : OCR_PR_DIGIT_CONSO_DETAL
                      Consolida de Detalles de los Pedidos Digitados
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE ocr_pr_digit_conso_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve Id de Periodo
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de cra_Periodo
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_oid_cra_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de MATRIZ_FACT_CABEC
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_matr_fact_cab
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de MATR_FACT
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_matr_fact(pscodperiodo VARCHAR2) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de OFERT_DETAL
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO existe CODVENTA 1: Existe CODVENTA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_ofer_detal
  (
    pscodperiodo VARCHAR2,
    pscodventa   VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de OFERT_DETAL OPROTUNIDADES PRIVELEGE
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : -1 NO existe CODVENTA  Otor valor: Existe CODVENTA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_ofer_detal_pri
  (
    pscodperiodo  VARCHAR2,
    pscodproducto VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve Id de Producto
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             vsCodPais : Codigo de Canal
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_prod
  (
    pscodprod           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve Id de Pais
  Fecha Creacion     : 18/09/2006
  Parametros Entrada :
             vsCodPais : Codigo de Pais
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_pais
  (
    vscodpais           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_IND_DIG
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 es NO Diigtable  1: Es Digitable
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_ind_dig(pnoiddetaofert NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_PREC_CATA
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 es PRecio Catalago = 0  1: PRecio Catalago > 0
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_prec_cata(pnoiddetaofert NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_ESTR_INDIV
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO EStrategia Individual  1: SI EStrategia Individual
             pnOidDetaOfert : OId Detalle Oferta
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_estr_indiv(pnoiddetaofert NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_ESTR_COMP_FIJA
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO EStrategia COMP_FIJA  1: SI EStrategia COMP_FIJA
             pnOidDetaOfert : OId Detalle Oferta
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_estr_comp_fija(pnoiddetaofert NUMBER)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Existe oId de la tabla PED_COD_VENT_AGREG
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO Existe 1: Exitse
             psCodPeriodo : PEriodo
             psCodVenta:    Cod VEnta
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_exis_ped_cod_agreg
  (
    pscodigpais  VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodventa   VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Existe oId de la tabla GEN_FN_EXIS_CUP_DESP_PROD
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO Existe 1: Exitse
             psCodPeriodo : PEriodo
             psCodVenta:    Cod VEnta
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_exis_cup_desp_prod
  (
    pscodigpais   VARCHAR2,
    pscodprograma VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodventa    VARCHAR2,
    pscodnivel    VARCHAR2
  ) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :ACtualiza Prioridad PED_COD_VENT_AGREG               */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_act_cod_areg_prior
  (
    pscodigpais  VARCHAR2,
    pscodperiodo VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve Id de PRe_VARiante
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodVariante :
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_vari
  (
    pscodvariante       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve Id de PRogFidelizacion
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodVariante :
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_prog_fide
  (
    pscodprogfide       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /************************************************************************
   Descripcion    :Proceoo Monto Minimo Maximo
   Autor          :Jose Cairampoma
   Fecha          :14/02/2012
  ************************************************************************/
  PROCEDURE ocr_pr_comp_mont_minimo
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    psfechafact     IN VARCHAR2,
    psusuario       IN VARCHAR2
  );

  /************************************************************************/
  /* Descripcion    :Actualiza IND GP2 OCR_PR_ACTU_INDI_GP2              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/
  PROCEDURE ocr_pr_actu_indi_gp2
  (
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    p_fechafact     IN VARCHAR2
  );

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic para UPDATE Prog Fact              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_fec_soli
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN DATE;

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic GEN_FN_DEV_FECSOL_MTO_MIN             */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_fecsol_mto_min
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2,
    psfechafact  DATE
  ) RETURN DATE;

  /******************************************************************/
  /*                   GEN_FN_COD_CONS_PRIV                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_cons_priv
  (
    pscodigopais    IN VARCHAR2,
    pscodigotarjeta IN VARCHAR2
  ) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Recepciona OCR_PR_COMP_OPORT_PRIV              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_comp_oport_priv
  (
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    p_fechafact     IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  );

  /*****************************************/
  /*            GEN_FN_DEV_REC_ENTRE_MERCA                              */
  /*******************************************/
  FUNCTION gen_fn_dev_rec_entre_merca(psoidmerca NUMBER) RETURN VARCHAR2;

  /*****************************************/
  /*            GEN_FN_DEV_REC_PRECI_PERDI                              */
  /*******************************************/
  FUNCTION gen_fn_dev_rec_preci_perdi(psoidprecioper NUMBER) RETURN VARCHAR2;

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic para UPDATE Prog Fact              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_num_lote
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve Id de Cliente
  Fecha Creacion     : 30/01/2007
  Parametros Entrada :
             vsCodCliente : Codigo de Cliente
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cliente(vscodcliente VARCHAR2) RETURN NUMBER;

  /************************************************************************/
  /* Descripcion    :Migracion Historio Cabecera OCS                 */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_hist_solic_conso_cabec
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /************************************************************************/
  /* Descripcion    :Migracion Historico Detalle  OCS                 */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/
  PROCEDURE ocr_pr_hist_solic_conso_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /********************************************************************
  * Descripcion   : Genera un codigo de venta Ficticio, para generar *
  *                 el Reporte Listado de productos mas reclamados   *
  * Autor         : José A. Cairampoma Granados                      *                     *
  * Fecha         : 05/11/2007                                       *
  ********************************************************************/

  FUNCTION gen_fn_cod_venta_fict
  (
    psprdoid   IN NUMBER,
    psconcurso IN NUMBER,
    psnumprem  IN NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Funcion que verifica SI la consultora es Nueva
  Fecha Creacion    : 24/01/2008
  Autor             : Carlos Bazalar
  Parametros        :
              pnIdCliente    Id del Cliente
  Return
            1: Es Consultora Nueva
            0: No es Consultora Nueva
  ***************************************************************************/
  FUNCTION ocr_fn_verif_consu_nueva(pnidcliente NUMBER) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Funcion que devuelve la descripcion del producto
  Fecha Creacion    : 12/08/2008
  Autor             : Cristhian Roman
  Parametros        :
              psCodigoVenta,    psCodigoPeriodo
  Return
            descripcion del producto
  ***************************************************************************/
  FUNCTION gen_fn_dev_descr_produ
  (
    pscodigoventa   VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Actualiza la tabla de Matriz de Facturacion
  Fecha Creacion    : 17/11/2008
    Autor              : Jose Cairampoma
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoMarca    : Codigo Marca
              psCodigoCanal    : Codigo Canal
  ***************************************************************************/

  PROCEDURE ocr_pr_actua_matri_factu
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Actualiza indicadores del archivo de control en su forma contraria
  Fecha Creacion    : 28/01/2009
    Autor              : Rosalvina Ramirez
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_contra_indic
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Elimina Pedido
  Fecha Creacion    : 11/02/2009
    Autor              : Rosalvina Ramirez
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
              psCodigoCliente    : Codigo Cliente
              psnumeroLote       : Numero Lote
              psUsuario          : Usuario
  ***************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_digit
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumerolote    VARCHAR2,
    psusuario       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Anula Pedido STO
  Fecha Creacion    : 30/04/2009
  Autor             : Dennys Oliva Iriarte
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
              psCodigoCliente    : Codigo Cliente
              psnumeroLote       : Numero Lote
              psUsuario          : Usuario
  ***************************************************************************/
  PROCEDURE ocr_pr_anula_pedid_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumerolote    VARCHAR2,
    psusuario       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Regenera Plantillas
  Fecha Creacion    : 14/05/2009
  Autor             : Cristhian Roman
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPlantilla    : Codigo Plantilla
              psTipoSolicitud    : Tipo Solicitud
              psExclusionSolicitud       : Exclusion solicitud
              psCodigoRegion          : Region
        psGrupo         : Grupo proceso
        psIndicadorBorrado    : Indicador de borrado
  ***************************************************************************/
  PROCEDURE ocr_pr_regen_plant
  /*(
        psCodigoPais          VARCHAR2,
        psCodigoPlantilla     VARCHAR2,
        psTipoSolicitud     VARCHAR2,
        psExclusionSolicitud  VARCHAR2,
        psCodigoRegion        VARCHAR2,
    psGrupo       VARCHAR2,
    psIndicadorBorrado  VARCHAR2);*/
  (
    pscodigopais      VARCHAR2,
    pscodigoplantilla VARCHAR2,
    pstiposolicitud   VARCHAR2,
    pstipoexclu       VARCHAR2,
    psregion          VARCHAR2,
    psgrupoproceso    VARCHAR2,
    psindborrado      VARCHAR2
  );

  /************************************************************************
   Descripcion    :Recepciona OCR_PR_COMP_OPORT_PRIVI_STO
                  Proceso de oportunidades Privilege, Ejecutado desde
                  las validaciones de STO
   Autor         : Jose Cairampoma
   Fecha         : 17/11/2011
  ***********************************************************************/
  PROCEDURE ocr_pr_comp_oport_privi_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida los pedidos por cambio de codigo de consulora
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_pedid_cambi_codig
  (
    pscodigopais        VARCHAR2,
    pscodigocliente     VARCHAR2,
    pscodigoclienteante VARCHAR2,
    psnumerolote        VARCHAR2,
    pssecnumedocu       VARCHAR2,
    pscodigoperiodo     VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Elimina los cupones de un pedido
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_elimi_cupon_pedid(pnsecnumedocu NUMBER);

  /***************************************************************************
  Descripcion       : Proceso de actualizacion de codigo de consultora
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_codig_consu
  (
    pscodigopais        VARCHAR2,
    pscodigocliente     VARCHAR2,
    pscodigoclienteante VARCHAR2,
    pscodigoperiodo     VARCHAR2,
    psnumerolote        VARCHAR2,
    psnumerocliente     VARCHAR2,
    pstiposolicitud     VARCHAR2,
    pscodigosubacceso   VARCHAR2,
    pscodigoacceso      VARCHAR2,
    pstipodespacho      VARCHAR2,
    psestadoproceso     VARCHAR2,
    psmontopedido       VARCHAR2,
    pssecnumedocu       VARCHAR2,
    psusuario           VARCHAR2,
    pscodigozonaarribo  VARCHAR2,
    psoidmotiges        VARCHAR2,
    psvalobsegestion    VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Valida bloqueo digitacion de pedidos
  Autor             : Christian Gonzales
  Fecha Creacion    : 06/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_valid_bloqu_digit_pedid(p_cod_cliente IN VARCHAR2)
    RETURN VARCHAR2;

  /**************************************************************************
    Descripcion       : DEvuelve el grupo de proceso de un consolidado
    Autor             : Jose Luis Rodriguez
    Fecha Creacion    : 26/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_devue_grupo_proce(p_num_consolidado IN NUMBER)
    RETURN NUMBER;

  /**************************************************************************
    Descripcion       : DEvuelve oferta especial
    Autor             : Jorge Yepez
    Fecha Creacion    : 01/06/2011
  ***************************************************************************/
  FUNCTION ocr_fn_devue_ofer_espe
  (
    p_val_tota_paga_loca IN NUMBER,
    p_perio              IN NUMBER,
    p_estra              IN NUMBER,
    p_oidofer            IN NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
      Descripcion       : Consolida el pedido online
      Fecha Creacion    : 08/11/2011
      Parametros Entrada:
          psCodigoPais : Codigo de pais
          psCodigoPeriodo : Codigo de periodo
          psUsuario : Codigo de Usuario
          psnumerolotesto   Lote generado por STO,
          psindicadororigen Origen (DIGITADO ->G),
      Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_conso_pedid_onlin
  (
    pscodigopais      VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    pscodconsultora   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /**************************************************************************
      Descripcion       : Ejecuta Procesos Especiales para un solo pedido
      Fecha Creacion    : 19/12/2011
      Parametros Entrada:
      Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_proce_espec
  (
    pscodigopais  VARCHAR2,
    pnoidsolictud NUMBER,
    psusuario     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Proceso de actualizacion de codigo de consultora
  Fecha Creacion    : 06/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_peri_cdr(psoidperiodo NUMBER);

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV esta como Faltante Anunciado  */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_falta_anunc_clien
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER
  ) RETURN NUMBER;

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV es compuesto                  */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_precio_compu
  (
    p_oidperi  NUMBER,
    pscodventa VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV tiene limite de ventas        */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_limit_venta_clien
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER
  ) RETURN NUMBER;

END ocr_solic_pedidos;
/
CREATE OR REPLACE PACKAGE BODY "OCR_SOLIC_PEDIDOS" AS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  --   gn_lineaError  NUMBER(10);

  /************************************************************************/
  /* Procedimientos de la tabla  :INT_SOLIC_CONSO_CABEC                         */
  /************************************************************************/

  /************************************************************************/
  /* Procedimiento  :SP_OBTPK_SOLIC_CONSO_CABEC                          */
  /* Descripcion    :select_pk de la tabla INT_SOLIC_CONSO_CABEC                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obtpk_solic_conso_cabec(
                                       
                                       p_cod_pais IN int_solic_conso_cabec.cod_pais%TYPE,
                                       p_cod_peri IN int_solic_conso_cabec.cod_peri%TYPE,
                                       p_cod_clie IN int_solic_conso_cabec.cod_clie%TYPE,
                                       p_fec_soli IN int_solic_conso_cabec.fec_soli%TYPE,
                                       cursorout  OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT *
        FROM int_solic_conso_cabec t
       WHERE t.cod_pais = p_cod_pais
         AND t.cod_peri = p_cod_peri
         AND t.cod_clie = p_cod_clie
         AND t.fec_soli = p_fec_soli;
  END sp_obtpk_solic_conso_cabec;

  /************************************************************************/
  /* Procedimiento  :SP_OBT_SOLIC_CONSO_CABEC                          */
  /* Descripcion    :select de la tabla INT_SOLIC_CONSO_CABEC                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obt_solic_conso_cabec(cursorout OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT * FROM int_solic_conso_cabec t;
  END sp_obt_solic_conso_cabec;

  /************************************************************************/
  /* Procedimiento  :SP_CREAR_SOLIC_CONSO_CABEC                          */
  /* Descripcion    :Inserta data en la tabla INT_SOLIC_CONSO_CABEC                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_crear_solic_conso_cabec(
                                       
                                       p_cod_pais IN OUT int_solic_conso_cabec.cod_pais%TYPE,
                                       
                                       p_cod_peri IN OUT int_solic_conso_cabec.cod_peri%TYPE,
                                       
                                       p_cod_clie IN OUT int_solic_conso_cabec.cod_clie%TYPE,
                                       
                                       p_fec_soli IN OUT int_solic_conso_cabec.fec_soli%TYPE,
                                       
                                       p_num_clie IN int_solic_conso_cabec.num_clie%TYPE,
                                       
                                       p_tipo_soli IN int_solic_conso_cabec.tipo_soli%TYPE,
                                       
                                       p_cod_sbac IN int_solic_conso_cabec.cod_sbac%TYPE,
                                       
                                       p_cod_acce IN int_solic_conso_cabec.cod_acce%TYPE,
                                       
                                       p_tip_desp IN int_solic_conso_cabec.tip_desp%TYPE,
                                       
                                       p_sta_proc IN int_solic_conso_cabec.sta_proc%TYPE,
                                       
                                       p_nom_clie IN int_solic_conso_cabec.nom_clie%TYPE,
                                       
                                       p_cod_regi IN int_solic_conso_cabec.cod_regi%TYPE,
                                       
                                       p_des_regi IN int_solic_conso_cabec.des_regi%TYPE,
                                       
                                       p_cod_zona IN int_solic_conso_cabec.cod_zona%TYPE,
                                       
                                       p_des_zona IN int_solic_conso_cabec.des_zona%TYPE,
                                       
                                       p_cod_terr IN int_solic_conso_cabec.cod_terr%TYPE,
                                       
                                       p_val_mont_pedi IN int_solic_conso_cabec.val_mont_pedi%TYPE,
                                       
                                       p_tip_orde IN int_solic_conso_cabec.tip_orde%TYPE,
                                       
                                       p_val_sald_deud IN int_solic_conso_cabec.val_sald_deud%TYPE,
                                       
                                       p_ind_erro_rech IN int_solic_conso_cabec.ind_erro_rech%TYPE,
                                       
                                       p_ind_erro_deud IN int_solic_conso_cabec.ind_erro_deud%TYPE,
                                       
                                       p_ind_erro_mtmi IN int_solic_conso_cabec.ind_erro_mtmi%TYPE,
                                       
                                       p_ind_erro_mtma IN int_solic_conso_cabec.ind_erro_mtma%TYPE,
                                       
                                       p_ind_erro_unma IN int_solic_conso_cabec.ind_erro_unma%TYPE,
                                       
                                       p_ind_error_sgpe IN int_solic_conso_cabec.ind_error_sgpe%TYPE,
                                       
                                       p_ind_erro_node IN int_solic_conso_cabec.ind_erro_node%TYPE,
                                       
                                       p_ind_bloq_admi IN int_solic_conso_cabec.ind_bloq_admi%TYPE,
                                       
                                       p_ind_bloq_fina IN int_solic_conso_cabec.ind_bloq_fina%TYPE,
                                       
                                       p_ind_ocs_proc IN int_solic_conso_cabec.ind_ocs_proc%TYPE,
                                       
                                       p_ind_ocs_bloq IN int_solic_conso_cabec.ind_ocs_bloq%TYPE,
                                       
                                       p_ind_admi_cart IN int_solic_conso_cabec.ind_admi_cart%TYPE,
                                       
                                       p_ind_comp_mont IN int_solic_conso_cabec.ind_comp_mont%TYPE,
                                       
                                       p_usu_digi IN int_solic_conso_cabec.usu_digi%TYPE,
                                       
                                       p_fec_digi IN int_solic_conso_cabec.fec_digi%TYPE,
                                       
                                       p_usu_modi IN int_solic_conso_cabec.usu_modi%TYPE,
                                       
                                       p_fec_modi IN int_solic_conso_cabec.fec_modi%TYPE
                                       
                                       ) AS
  BEGIN
    INSERT INTO int_solic_conso_cabec
      (
       
       cod_pais,
       cod_peri,
       cod_clie,
       fec_soli,
       num_clie,
       tipo_soli,
       cod_sbac,
       cod_acce,
       tip_desp,
       sta_proc,
       nom_clie,
       cod_regi,
       des_regi,
       cod_zona,
       des_zona,
       cod_terr,
       val_mont_pedi,
       tip_orde,
       val_sald_deud,
       ind_erro_rech,
       ind_erro_deud,
       ind_erro_mtmi,
       ind_erro_mtma,
       ind_erro_unma,
       ind_error_sgpe,
       ind_erro_node,
       ind_bloq_admi,
       ind_bloq_fina,
       ind_ocs_proc,
       ind_ocs_bloq,
       ind_admi_cart,
       ind_comp_mont,
       usu_digi,
       fec_digi,
       usu_modi,
       fec_modi)
    VALUES
      (p_cod_pais,
       p_cod_peri,
       p_cod_clie,
       p_fec_soli,
       p_num_clie,
       p_tipo_soli,
       p_cod_sbac,
       p_cod_acce,
       p_tip_desp,
       p_sta_proc,
       p_nom_clie,
       p_cod_regi,
       p_des_regi,
       p_cod_zona,
       p_des_zona,
       p_cod_terr,
       p_val_mont_pedi,
       p_tip_orde,
       p_val_sald_deud,
       p_ind_erro_rech,
       p_ind_erro_deud,
       p_ind_erro_mtmi,
       p_ind_erro_mtma,
       p_ind_erro_unma,
       p_ind_error_sgpe,
       p_ind_erro_node,
       p_ind_bloq_admi,
       p_ind_bloq_fina,
       p_ind_ocs_proc,
       p_ind_ocs_bloq,
       p_ind_admi_cart,
       p_ind_comp_mont,
       p_usu_digi,
       p_fec_digi,
       p_usu_modi,
       p_fec_modi);
  
  END sp_crear_solic_conso_cabec;

  /************************************************************************/
  /* Procedimiento  :SP_ACT_SOLIC_CONSO_CABEC         */
  /* Descripcion    :Actualiza data en la tabla INT_SOLIC_CONSO_CABEC          */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_act_solic_conso_cabec(
                                     
                                     p_cod_pais IN OUT int_solic_conso_cabec.cod_pais%TYPE,
                                     
                                     p_cod_peri IN OUT int_solic_conso_cabec.cod_peri%TYPE,
                                     
                                     p_cod_clie IN OUT int_solic_conso_cabec.cod_clie%TYPE,
                                     
                                     p_fec_soli IN OUT int_solic_conso_cabec.fec_soli%TYPE,
                                     
                                     p_num_clie IN int_solic_conso_cabec.num_clie%TYPE,
                                     
                                     p_tipo_soli IN int_solic_conso_cabec.tipo_soli%TYPE,
                                     
                                     p_cod_sbac IN int_solic_conso_cabec.cod_sbac%TYPE,
                                     
                                     p_cod_acce IN int_solic_conso_cabec.cod_acce%TYPE,
                                     
                                     p_tip_desp IN int_solic_conso_cabec.tip_desp%TYPE,
                                     
                                     p_sta_proc IN int_solic_conso_cabec.sta_proc%TYPE,
                                     
                                     p_nom_clie IN int_solic_conso_cabec.nom_clie%TYPE,
                                     
                                     p_cod_regi IN int_solic_conso_cabec.cod_regi%TYPE,
                                     
                                     p_des_regi IN int_solic_conso_cabec.des_regi%TYPE,
                                     
                                     p_cod_zona IN int_solic_conso_cabec.cod_zona%TYPE,
                                     
                                     p_des_zona IN int_solic_conso_cabec.des_zona%TYPE,
                                     
                                     p_cod_terr IN int_solic_conso_cabec.cod_terr%TYPE,
                                     
                                     p_val_mont_pedi IN int_solic_conso_cabec.val_mont_pedi%TYPE,
                                     
                                     p_tip_orde IN int_solic_conso_cabec.tip_orde%TYPE,
                                     
                                     p_val_sald_deud IN int_solic_conso_cabec.val_sald_deud%TYPE,
                                     
                                     p_ind_erro_rech IN int_solic_conso_cabec.ind_erro_rech%TYPE,
                                     
                                     p_ind_erro_deud IN int_solic_conso_cabec.ind_erro_deud%TYPE,
                                     
                                     p_ind_erro_mtmi IN int_solic_conso_cabec.ind_erro_mtmi%TYPE,
                                     
                                     p_ind_erro_mtma IN int_solic_conso_cabec.ind_erro_mtma%TYPE,
                                     
                                     p_ind_erro_unma IN int_solic_conso_cabec.ind_erro_unma%TYPE,
                                     
                                     p_ind_error_sgpe IN int_solic_conso_cabec.ind_error_sgpe%TYPE,
                                     
                                     p_ind_erro_node IN int_solic_conso_cabec.ind_erro_node%TYPE,
                                     
                                     p_ind_bloq_admi IN int_solic_conso_cabec.ind_bloq_admi%TYPE,
                                     
                                     p_ind_bloq_fina IN int_solic_conso_cabec.ind_bloq_fina%TYPE,
                                     
                                     p_ind_ocs_proc IN int_solic_conso_cabec.ind_ocs_proc%TYPE,
                                     
                                     p_ind_ocs_bloq IN int_solic_conso_cabec.ind_ocs_bloq%TYPE,
                                     
                                     p_ind_admi_cart IN int_solic_conso_cabec.ind_admi_cart%TYPE,
                                     
                                     p_ind_comp_mont IN int_solic_conso_cabec.ind_comp_mont%TYPE,
                                     
                                     p_usu_digi IN int_solic_conso_cabec.usu_digi%TYPE,
                                     
                                     p_fec_digi IN int_solic_conso_cabec.fec_digi%TYPE,
                                     
                                     p_usu_modi IN int_solic_conso_cabec.usu_modi%TYPE,
                                     
                                     p_fec_modi IN int_solic_conso_cabec.fec_modi%TYPE
                                     
                                     )
  
   AS
  BEGIN
    UPDATE int_solic_conso_cabec t
       SET num_clie       = p_num_clie,
           tipo_soli      = p_tipo_soli,
           cod_sbac       = p_cod_sbac,
           cod_acce       = p_cod_acce,
           tip_desp       = p_tip_desp,
           sta_proc       = p_sta_proc,
           nom_clie       = p_nom_clie,
           cod_regi       = p_cod_regi,
           des_regi       = p_des_regi,
           cod_zona       = p_cod_zona,
           des_zona       = p_des_zona,
           cod_terr       = p_cod_terr,
           val_mont_pedi  = p_val_mont_pedi,
           tip_orde       = p_tip_orde,
           val_sald_deud  = p_val_sald_deud,
           ind_erro_rech  = p_ind_erro_rech,
           ind_erro_deud  = p_ind_erro_deud,
           ind_erro_mtmi  = p_ind_erro_mtmi,
           ind_erro_mtma  = p_ind_erro_mtma,
           ind_erro_unma  = p_ind_erro_unma,
           ind_error_sgpe = p_ind_error_sgpe,
           ind_erro_node  = p_ind_erro_node,
           ind_bloq_admi  = p_ind_bloq_admi,
           ind_bloq_fina  = p_ind_bloq_fina,
           ind_ocs_proc   = p_ind_ocs_proc,
           ind_ocs_bloq   = p_ind_ocs_bloq,
           ind_admi_cart  = p_ind_admi_cart,
           ind_comp_mont  = p_ind_comp_mont,
           usu_digi       = p_usu_digi,
           fec_digi       = p_fec_digi,
           usu_modi       = p_usu_modi,
           fec_modi       = p_fec_modi
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_clie = p_cod_clie
       AND t.fec_soli = p_fec_soli;
  END sp_act_solic_conso_cabec;

  /************************************************************************/
  /* Procedimiento  :SP_ELI_SOLIC_CONSO_CABEC                          */
  /* Descripcion    :Elimina data de la tabla INT_SOLIC_CONSO_CABEC                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_eli_solic_conso_cabec
  (
    p_cod_pais IN int_solic_conso_cabec.cod_pais%TYPE,
    p_cod_peri IN int_solic_conso_cabec.cod_peri%TYPE,
    p_cod_clie IN int_solic_conso_cabec.cod_clie%TYPE,
    p_fec_soli IN int_solic_conso_cabec.fec_soli%TYPE
  ) AS
  BEGIN
    DELETE FROM int_solic_conso_cabec t
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_clie = p_cod_clie
       AND t.fec_soli = p_fec_soli;
  END sp_eli_solic_conso_cabec;

  /************************************************************************/
  /* Procedimientos de la tabla  :BAS_CTRL_FACT                         */
  /************************************************************************/

  /************************************************************************/
  /* Procedimiento  :SP_OBTPK_CTRL_FACT                          */
  /* Descripcion    :select_pk de la tabla BAS_CTRL_FACT                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obtpk_ctrl_fact(
                               
                               p_cod_pais IN bas_ctrl_fact.cod_pais%TYPE,
                               p_cod_peri IN bas_ctrl_fact.cod_peri%TYPE,
                               cursorout  OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT *
        FROM bas_ctrl_fact t
       WHERE t.cod_pais = p_cod_pais
         AND t.cod_peri = p_cod_peri;
  END sp_obtpk_ctrl_fact;

  /************************************************************************/
  /* Procedimiento  :SP_OBT_CTRL_FACT                          */
  /* Descripcion    :select de la tabla BAS_CTRL_FACT                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obt_ctrl_fact(cursorout OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT * FROM bas_ctrl_fact t;
  END sp_obt_ctrl_fact;

  /************************************************************************/
  /* Procedimiento  :SP_CREAR_CTRL_FACT                          */
  /* Descripcion    :Inserta data en la tabla BAS_CTRL_FACT                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_crear_ctrl_fact(
                               
                               p_cod_pais IN OUT bas_ctrl_fact.cod_pais%TYPE,
                               
                               p_cod_peri IN OUT bas_ctrl_fact.cod_peri%TYPE,
                               
                               p_fec_proc IN bas_ctrl_fact.fec_proc%TYPE,
                               
                               p_val_mnt_min_fact IN bas_ctrl_fact.val_mnt_min_fact%TYPE,
                               
                               p_val_mnt_min_acept IN bas_ctrl_fact.val_mnt_min_acept%TYPE,
                               
                               p_val_mnt_max IN bas_ctrl_fact.val_mnt_max%TYPE,
                               
                               p_val_unid_max IN bas_ctrl_fact.val_unid_max%TYPE,
                               
                               p_sta_camp IN bas_ctrl_fact.sta_camp%TYPE,
                               
                               p_usu_digi IN bas_ctrl_fact.usu_digi%TYPE,
                               
                               p_fec_digi IN bas_ctrl_fact.fec_digi%TYPE,
                               
                               p_usu_modi IN bas_ctrl_fact.usu_modi%TYPE,
                               
                               p_fec_modi IN bas_ctrl_fact.fec_modi%TYPE
                               
                               ) AS
  BEGIN
    INSERT INTO bas_ctrl_fact
      (
       
       cod_pais,
       cod_peri,
       fec_proc,
       val_mnt_min_fact,
       val_mnt_min_acept,
       val_mnt_max,
       val_unid_max,
       sta_camp,
       usu_digi,
       fec_digi,
       usu_modi,
       fec_modi)
    VALUES
      (p_cod_pais,
       p_cod_peri,
       p_fec_proc,
       p_val_mnt_min_fact,
       p_val_mnt_min_acept,
       p_val_mnt_max,
       p_val_unid_max,
       p_sta_camp,
       p_usu_digi,
       p_fec_digi,
       p_usu_modi,
       p_fec_modi);
  
  END sp_crear_ctrl_fact;

  /************************************************************************/
  /* Procedimiento  :SP_ACT_CTRL_FACT         */
  /* Descripcion    :Actualiza data en la tabla BAS_CTRL_FACT          */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_act_ctrl_fact(
                             
                             p_cod_pais IN OUT bas_ctrl_fact.cod_pais%TYPE,
                             
                             p_cod_peri IN OUT bas_ctrl_fact.cod_peri%TYPE,
                             
                             p_fec_proc IN bas_ctrl_fact.fec_proc%TYPE,
                             
                             p_val_mnt_min_fact IN bas_ctrl_fact.val_mnt_min_fact%TYPE,
                             
                             p_val_mnt_min_acept IN bas_ctrl_fact.val_mnt_min_acept%TYPE,
                             
                             p_val_mnt_max IN bas_ctrl_fact.val_mnt_max%TYPE,
                             
                             p_val_unid_max IN bas_ctrl_fact.val_unid_max%TYPE,
                             
                             p_sta_camp IN bas_ctrl_fact.sta_camp%TYPE,
                             
                             p_usu_digi IN bas_ctrl_fact.usu_digi%TYPE,
                             
                             p_fec_digi IN bas_ctrl_fact.fec_digi%TYPE,
                             
                             p_usu_modi IN bas_ctrl_fact.usu_modi%TYPE,
                             
                             p_fec_modi IN bas_ctrl_fact.fec_modi%TYPE
                             
                             )
  
   AS
  BEGIN
    UPDATE bas_ctrl_fact t
       SET fec_proc          = p_fec_proc,
           val_mnt_min_fact  = p_val_mnt_min_fact,
           val_mnt_min_acept = p_val_mnt_min_acept,
           val_mnt_max       = p_val_mnt_max,
           val_unid_max      = p_val_unid_max,
           sta_camp          = p_sta_camp,
           usu_digi          = p_usu_digi,
           fec_digi          = p_fec_digi,
           usu_modi          = p_usu_modi,
           fec_modi          = p_fec_modi
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri;
  END sp_act_ctrl_fact;

  /************************************************************************/
  /* Procedimiento  :SP_ELI_CTRL_FACT                          */
  /* Descripcion    :Elimina data de la tabla BAS_CTRL_FACT                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_eli_ctrl_fact
  (
    p_cod_pais IN bas_ctrl_fact.cod_pais%TYPE,
    p_cod_peri IN bas_ctrl_fact.cod_peri%TYPE
  ) AS
  BEGIN
    DELETE FROM bas_ctrl_fact t
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri;
  END sp_eli_ctrl_fact;

  /************************************************************************/
  /* Procedimientos de la tabla  :PED_COD_VENT_AGREG                         */
  /************************************************************************/

  /************************************************************************/
  /* Procedimiento  :SP_OBTPK_COD_VENT_AGREG                          */
  /* Descripcion    :select_pk de la tabla PED_COD_VENT_AGREG                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obtpk_cod_vent_agreg(
                                    
                                    p_cod_pais IN ped_cod_vent_agreg.cod_pais%TYPE,
                                    p_cod_peri IN ped_cod_vent_agreg.cod_peri%TYPE,
                                    p_cod_vent IN ped_cod_vent_agreg.cod_vent%TYPE,
                                    cursorout  OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT *
        FROM ped_cod_vent_agreg t
       WHERE t.cod_pais = p_cod_pais
         AND t.cod_peri = p_cod_peri
         AND t.cod_vent = p_cod_vent;
  END sp_obtpk_cod_vent_agreg;

  /************************************************************************/
  /* Procedimiento  :SP_OBT_COD_VENT_AGREG                          */
  /* Descripcion    :select de la tabla PED_COD_VENT_AGREG                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obt_cod_vent_agreg(cursorout OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT * FROM ped_cod_vent_agreg t;
  END sp_obt_cod_vent_agreg;

  /************************************************************************/
  /* Procedimiento  :SP_CREAR_COD_VENT_AGREG                          */
  /* Descripcion    :Inserta data en la tabla PED_COD_VENT_AGREG                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_crear_cod_vent_agreg(
                                    
                                    p_cod_pais IN OUT ped_cod_vent_agreg.cod_pais%TYPE,
                                    
                                    p_cod_peri IN OUT ped_cod_vent_agreg.cod_peri%TYPE,
                                    
                                    p_cod_vent IN OUT ped_cod_vent_agreg.cod_vent%TYPE,
                                    
                                    p_val_unit IN ped_cod_vent_agreg.val_unit%TYPE,
                                    
                                    p_cod_prod IN ped_cod_vent_agreg.cod_prod%TYPE,
                                    
                                    p_des_prod IN ped_cod_vent_agreg.des_prod%TYPE,
                                    
                                    p_niv_prior IN ped_cod_vent_agreg.niv_prior%TYPE,
                                    
                                    p_sta_reg IN ped_cod_vent_agreg.sta_reg%TYPE,
                                    
                                    p_usu_digi IN ped_cod_vent_agreg.usu_digi%TYPE,
                                    
                                    p_fec_digi IN ped_cod_vent_agreg.fec_digi%TYPE,
                                    
                                    p_usu_modi IN ped_cod_vent_agreg.usu_modi%TYPE,
                                    
                                    p_fec_modi IN ped_cod_vent_agreg.fec_modi%TYPE
                                    
                                    ) AS
  BEGIN
    INSERT INTO ped_cod_vent_agreg
      (
       
       cod_pais,
       cod_peri,
       cod_vent,
       val_unit,
       cod_prod,
       des_prod,
       niv_prior,
       sta_reg,
       usu_digi,
       fec_digi,
       usu_modi,
       fec_modi)
    VALUES
      (p_cod_pais,
       p_cod_peri,
       p_cod_vent,
       p_val_unit,
       p_cod_prod,
       p_des_prod,
       p_niv_prior,
       p_sta_reg,
       p_usu_digi,
       p_fec_digi,
       p_usu_modi,
       p_fec_modi);
  
  END sp_crear_cod_vent_agreg;

  /************************************************************************/
  /* Procedimiento  :SP_ACT_COD_VENT_AGREG         */
  /* Descripcion    :Actualiza data en la tabla PED_COD_VENT_AGREG          */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_act_cod_vent_agreg(
                                  
                                  p_cod_pais IN OUT ped_cod_vent_agreg.cod_pais%TYPE,
                                  
                                  p_cod_peri IN OUT ped_cod_vent_agreg.cod_peri%TYPE,
                                  
                                  p_cod_vent IN OUT ped_cod_vent_agreg.cod_vent%TYPE,
                                  
                                  p_val_unit IN ped_cod_vent_agreg.val_unit%TYPE,
                                  
                                  p_cod_prod IN ped_cod_vent_agreg.cod_prod%TYPE,
                                  
                                  p_des_prod IN ped_cod_vent_agreg.des_prod%TYPE,
                                  
                                  p_niv_prior IN ped_cod_vent_agreg.niv_prior%TYPE,
                                  
                                  p_sta_reg IN ped_cod_vent_agreg.sta_reg%TYPE,
                                  
                                  p_usu_digi IN ped_cod_vent_agreg.usu_digi%TYPE,
                                  
                                  p_fec_digi IN ped_cod_vent_agreg.fec_digi%TYPE,
                                  
                                  p_usu_modi IN ped_cod_vent_agreg.usu_modi%TYPE,
                                  
                                  p_fec_modi IN ped_cod_vent_agreg.fec_modi%TYPE
                                  
                                  )
  
   AS
  BEGIN
    UPDATE ped_cod_vent_agreg t
       SET val_unit  = p_val_unit,
           cod_prod  = p_cod_prod,
           des_prod  = p_des_prod,
           niv_prior = p_niv_prior,
           sta_reg   = p_sta_reg,
           usu_digi  = p_usu_digi,
           fec_digi  = p_fec_digi,
           usu_modi  = p_usu_modi,
           fec_modi  = p_fec_modi
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_vent = p_cod_vent;
  END sp_act_cod_vent_agreg;

  /************************************************************************/
  /* Procedimiento  :SP_ELI_COD_VENT_AGREG                          */
  /* Descripcion    :Elimina data de la tabla PED_COD_VENT_AGREG                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_eli_cod_vent_agreg
  (
    p_cod_pais IN ped_cod_vent_agreg.cod_pais%TYPE,
    p_cod_peri IN ped_cod_vent_agreg.cod_peri%TYPE,
    p_cod_vent IN ped_cod_vent_agreg.cod_vent%TYPE
  ) AS
  BEGIN
    DELETE FROM ped_cod_vent_agreg t
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_vent = p_cod_vent;
  END sp_eli_cod_vent_agreg;

  /************************************************************************/
  /* Procedimientos de la tabla  :INT_SOLIC_CONSO_DETAL                         */
  /************************************************************************/

  /************************************************************************/
  /* Procedimiento  :SP_OBTPK_SOLIC_CONSO_DETAL                          */
  /* Descripcion    :select_pk de la tabla INT_SOLIC_CONSO_DETAL                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obtpk_solic_conso_detal(
                                       
                                       p_cod_pais  IN int_solic_conso_detal.cod_pais%TYPE,
                                       p_cod_peri  IN int_solic_conso_detal.cod_peri%TYPE,
                                       p_cod_clie  IN int_solic_conso_detal.cod_clie%TYPE,
                                       p_cod_vent  IN int_solic_conso_detal.cod_vent%TYPE,
                                       p_tip_posic IN int_solic_conso_detal.tip_posic%TYPE,
                                       cursorout   OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT *
        FROM int_solic_conso_detal t
       WHERE t.cod_pais = p_cod_pais
         AND t.cod_peri = p_cod_peri
         AND t.cod_clie = p_cod_clie
         AND t.cod_vent = p_cod_vent
         AND t.tip_posic = p_tip_posic;
  END sp_obtpk_solic_conso_detal;

  /************************************************************************/
  /* Procedimiento  :SP_OBT_SOLIC_CONSO_DETAL                          */
  /* Descripcion    :select de la tabla INT_SOLIC_CONSO_DETAL                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_obt_solic_conso_detal(cursorout OUT micursor) AS
  BEGIN
    OPEN cursorout FOR
      SELECT * FROM int_solic_conso_detal t;
  END sp_obt_solic_conso_detal;

  /************************************************************************/
  /* Procedimiento  :SP_CREAR_SOLIC_CONSO_DETAL                          */
  /* Descripcion    :Inserta data en la tabla INT_SOLIC_CONSO_DETAL                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_crear_solic_conso_detal(
                                       
                                       p_cod_pais IN OUT int_solic_conso_detal.cod_pais%TYPE,
                                       
                                       p_cod_peri IN OUT int_solic_conso_detal.cod_peri%TYPE,
                                       
                                       p_cod_clie IN OUT int_solic_conso_detal.cod_clie%TYPE,
                                       
                                       p_cod_vent IN OUT int_solic_conso_detal.cod_vent%TYPE,
                                       
                                       p_tip_posic IN OUT int_solic_conso_detal.tip_posic%TYPE,
                                       
                                       p_des_prod IN int_solic_conso_detal.des_prod%TYPE,
                                       
                                       p_val_unid_dem IN int_solic_conso_detal.val_unid_dem%TYPE,
                                       
                                       p_val_unid_comp IN int_solic_conso_detal.val_unid_comp%TYPE,
                                       
                                       p_sta_proc IN int_solic_conso_detal.sta_proc%TYPE,
                                       
                                       p_ind_comp_mtmi IN int_solic_conso_detal.ind_comp_mtmi%TYPE,
                                       
                                       p_usu_digi IN int_solic_conso_detal.usu_digi%TYPE,
                                       
                                       p_fec_digi IN int_solic_conso_detal.fec_digi%TYPE,
                                       
                                       p_usu_modi IN int_solic_conso_detal.usu_modi%TYPE,
                                       
                                       p_fec_modi IN int_solic_conso_detal.fec_modi%TYPE
                                       
                                       ) AS
  BEGIN
    INSERT INTO int_solic_conso_detal
      (
       
       cod_pais,
       cod_peri,
       cod_clie,
       cod_vent,
       tip_posic,
       des_prod,
       val_unid_dem,
       val_unid_comp,
       sta_proc,
       ind_comp_mtmi,
       usu_digi,
       fec_digi,
       usu_modi,
       fec_modi)
    VALUES
      (p_cod_pais,
       p_cod_peri,
       p_cod_clie,
       p_cod_vent,
       p_tip_posic,
       p_des_prod,
       p_val_unid_dem,
       p_val_unid_comp,
       p_sta_proc,
       p_ind_comp_mtmi,
       p_usu_digi,
       p_fec_digi,
       p_usu_modi,
       p_fec_modi);
  
  END sp_crear_solic_conso_detal;

  /************************************************************************/
  /* Procedimiento  :SP_ACT_SOLIC_CONSO_DETAL         */
  /* Descripcion    :Actualiza data en la tabla INT_SOLIC_CONSO_DETAL          */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_act_solic_conso_detal(
                                     
                                     p_cod_pais IN OUT int_solic_conso_detal.cod_pais%TYPE,
                                     
                                     p_cod_peri IN OUT int_solic_conso_detal.cod_peri%TYPE,
                                     
                                     p_cod_clie IN OUT int_solic_conso_detal.cod_clie%TYPE,
                                     
                                     p_cod_vent IN OUT int_solic_conso_detal.cod_vent%TYPE,
                                     
                                     p_tip_posic IN OUT int_solic_conso_detal.tip_posic%TYPE,
                                     
                                     p_des_prod IN int_solic_conso_detal.des_prod%TYPE,
                                     
                                     p_val_unid_dem IN int_solic_conso_detal.val_unid_dem%TYPE,
                                     
                                     p_val_unid_comp IN int_solic_conso_detal.val_unid_comp%TYPE,
                                     
                                     p_sta_proc IN int_solic_conso_detal.sta_proc%TYPE,
                                     
                                     p_ind_comp_mtmi IN int_solic_conso_detal.ind_comp_mtmi%TYPE,
                                     
                                     p_usu_digi IN int_solic_conso_detal.usu_digi%TYPE,
                                     
                                     p_fec_digi IN int_solic_conso_detal.fec_digi%TYPE,
                                     
                                     p_usu_modi IN int_solic_conso_detal.usu_modi%TYPE,
                                     
                                     p_fec_modi IN int_solic_conso_detal.fec_modi%TYPE
                                     
                                     )
  
   AS
  BEGIN
    UPDATE int_solic_conso_detal t
       SET des_prod      = p_des_prod,
           val_unid_dem  = p_val_unid_dem,
           val_unid_comp = p_val_unid_comp,
           sta_proc      = p_sta_proc,
           ind_comp_mtmi = p_ind_comp_mtmi,
           usu_digi      = p_usu_digi,
           fec_digi      = p_fec_digi,
           usu_modi      = p_usu_modi,
           fec_modi      = p_fec_modi
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_clie = p_cod_clie
       AND t.cod_vent = p_cod_vent
       AND t.tip_posic = p_tip_posic;
  END sp_act_solic_conso_detal;

  /************************************************************************/
  /* Procedimiento  :SP_ELI_SOLIC_CONSO_DETAL                          */
  /* Descripcion    :Elimina data de la tabla INT_SOLIC_CONSO_DETAL                  */
  /* Creado         : Marco Silva                                          */
  /* Fecha      : 19/10/2005                                          */
  /************************************************************************/

  PROCEDURE sp_eli_solic_conso_detal
  (
    p_cod_pais  IN int_solic_conso_detal.cod_pais%TYPE,
    p_cod_peri  IN int_solic_conso_detal.cod_peri%TYPE,
    p_cod_clie  IN int_solic_conso_detal.cod_clie%TYPE,
    p_cod_vent  IN int_solic_conso_detal.cod_vent%TYPE,
    p_tip_posic IN int_solic_conso_detal.tip_posic%TYPE
  ) AS
  BEGIN
    DELETE FROM int_solic_conso_detal t
     WHERE t.cod_pais = p_cod_pais
       AND t.cod_peri = p_cod_peri
       AND t.cod_clie = p_cod_clie
       AND t.cod_vent = p_cod_vent
       AND t.tip_posic = p_tip_posic;
  END sp_eli_solic_conso_detal;

  /***************************/
  /* deveueve campa?a          */
  /*******************************/

  FUNCTION gen_fn_campanha(psperioid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT percorp.cod_peri
              FROM seg_perio_corpo percorp
             WHERE percorp.oid_peri = percra.peri_oid_peri)
      INTO ls_valor
      FROM cra_perio percra
     WHERE percra.oid_peri = psperioid;
  
    RETURN ls_valor;
  END gen_fn_campanha;

  /***************************/
  /* deveueve DESCRIPCION         */
  /*******************************/

  FUNCTION gen_fn_desc_gener
  (
    pstablaoid    IN NUMBER,
    psdescripcion IN VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT gen.val_i18n
      INTO ls_valor
      FROM gen_i18n_sicc_comun gen,
           seg_idiom           idio
     WHERE gen.val_oid = pstablaoid
       AND gen.idio_oid_idio = idio.oid_idio
       AND gen.attr_enti = psdescripcion
       AND idio.cod_iso_idio = 'es';
  
    RETURN ls_valor;
  END gen_fn_desc_gener;

  /***************************/
  /* deveueve marca          */
  /*******************************/

  FUNCTION gen_fn_marca(psperioid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT marca.cod_marc
              FROM seg_marca marca
             WHERE marca.oid_marc = percra.marc_oid_marc)
      INTO ls_valor
      FROM cra_perio percra
     WHERE percra.oid_peri = psperioid;
  
    RETURN ls_valor;
  END gen_fn_marca;

  /***************************/
  /* deveueve marca          */
  /*******************************/

  FUNCTION gen_fn_canal(psperioid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT canal.cod_cana
              FROM seg_canal canal
             WHERE canal.oid_cana = percra.cana_oid_cana)
      INTO ls_valor
      FROM cra_perio percra
     WHERE percra.oid_peri = psperioid;
  
    RETURN ls_valor;
  END gen_fn_canal;

  /***************************/
  /* deveueve marca          */
  /*******************************/

  FUNCTION gen_fn_acceso(psperioid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT acceso.cod_acce
              FROM seg_acces acceso
             WHERE acceso.oid_acce = percra.acce_oid_acce)
      INTO ls_valor
      FROM cra_perio percra
     WHERE percra.oid_peri = psperioid;
  
    RETURN ls_valor;
  
  END gen_fn_acceso;

  /***************************/
  /* deveueve marca          */
  /*******************************/

  FUNCTION gen_fn_sub_acceso(pssbacoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT subacceso.cod_sbac
      INTO ls_valor
      FROM seg_subac subacceso
     WHERE subacceso.oid_sbac = pssbacoid;
  
    RETURN ls_valor;
  
  END gen_fn_sub_acceso;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_tipo_perio(psperioid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT tipe.cod_tipo_peri
              FROM seg_perio_corpo percorp,
                   seg_tipo_perio  tipe
             WHERE percorp.oid_peri = percra.peri_oid_peri
               AND tipe.oid_tipo_peri = percorp.tipe_oid_tipo_peri)
      INTO ls_valor
      FROM cra_perio percra
     WHERE percra.oid_peri = psperioid;
  
    RETURN ls_valor;
  
  END gen_fn_tipo_perio;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_marca_prod(psproductooid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT marka.cod_marc_prod
              FROM seg_marca_produ marka
             WHERE marka.oid_marc_prod = prod.mapr_oid_marc_prod)
      INTO ls_valor
      FROM mae_produ prod
     WHERE prod.oid_prod = psproductooid;
  
    RETURN ls_valor;
  
  END gen_fn_marca_prod;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_nego_prod(psproductooid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT neg.cod_nego
              FROM mae_negoc neg
             WHERE neg.oid_nego = prod.nego_oid_nego)
      INTO ls_valor
      FROM mae_produ prod
     WHERE prod.oid_prod = psproductooid;
  
    RETURN ls_valor;
  
  END gen_fn_nego_prod;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_codsap_prod(psproductooid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT prod.cod_sap
      INTO ls_valor
      FROM mae_produ prod
     WHERE prod.oid_prod = psproductooid;
  
    RETURN ls_valor;
  
  END gen_fn_codsap_prod;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_desc_prod(psproductooid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    /*    select PROD.DES_CORT into ls_valor
                from MAE_PRODU PROD
               where PROD.OID_PROD  = psProductoOid ;
    */
    SELECT gen.val_i18n
      INTO ls_valor
      FROM gen_i18n_sicc_pais gen,
           seg_idiom          idio
     WHERE gen.val_oid = psproductooid
       AND gen.idio_oid_idio = idio.oid_idio
       AND gen.attr_enti = 'MAE_PRODU'
       AND idio.cod_iso_idio = 'es';
  
    RETURN ls_valor;
  
  END gen_fn_desc_prod;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_rec_tipo_oper(pstipooperoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT top.val_tipo_oper
      INTO ls_valor
      FROM rec_tipos_opera top
     WHERE top.oid_tipo_oper = pstipooperoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_tipo_oper;

  /*****************************************/
  /*            GEN_FN_REC_NUM_LOTE                              */
  /*******************************************/

  FUNCTION gen_fn_rec_num_lote(pslineaoperrecoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT MAX(lin.num_lote)
      INTO ls_valor
      FROM int_rec_linea_opera_recla lin
     WHERE lin.oid_line_oper_recl = pslineaoperrecoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_num_lote;

  /************************************************************************/
  /* Descripcion    :Obtiene la siguiente campanha del archivo de Ctrl de Fact */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_sgte_campa(pscodpais VARCHAR2) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
    ls_temp   VARCHAR2(100);
    campanha  NUMBER;
    anho      NUMBER;
  BEGIN
    SELECT MAX(ctrl.cod_peri)
      INTO ls_temp
      FROM bas_ctrl_fact ctrl
     WHERE ctrl.cod_pais = pscodpais;
  
    IF ls_temp IS NULL THEN
      RETURN NULL;
    ELSE
      SELECT to_number(substr(ls_temp, 1, 4)) INTO anho FROM dual;
    
      SELECT MOD(to_number(substr(ls_temp, 5, 2)), 18) + 1
        INTO campanha
        FROM dual;
    
      IF campanha = 1 THEN
        anho := anho + 1;
      END IF;
    
      SELECT TRIM(to_char(anho, '0000')) || TRIM(to_char(campanha, '00'))
        INTO ls_result
        FROM dual;
    
      RETURN ls_result;
    END IF;
  
  END gen_fn_sgte_campa;

  /************************************************************************/
  /* Descripcion    :Obtiene la siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_sgte_campa(pscodperiodo VARCHAR2) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
    ls_temp   VARCHAR2(100);
    campanha  NUMBER;
    anho      NUMBER;
  BEGIN
    SELECT pscodperiodo INTO ls_temp FROM dual;
  
    IF ls_temp IS NULL THEN
      RETURN NULL;
    ELSE
      SELECT to_number(substr(ls_temp, 1, 4)) INTO anho FROM dual;
    
      SELECT MOD(to_number(substr(ls_temp, 5, 2)), 18) + 1
        INTO campanha
        FROM dual;
    
      IF campanha = 1 THEN
        anho := anho + 1;
      END IF;
    
      SELECT TRIM(to_char(anho, '0000')) || TRIM(to_char(campanha, '00'))
        INTO ls_result
        FROM dual;
    
      RETURN ls_result;
    END IF;
  
  END gen_fn_dev_sgte_campa;

  /************************************************************************/
  /* Descripcion    :Obtiene la anterior campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_ant_campa(pscodperiodo VARCHAR2) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
    ls_temp   VARCHAR2(100);
    campanha  NUMBER;
    anho      NUMBER;
  BEGIN
    SELECT pscodperiodo INTO ls_temp FROM dual;
  
    IF ls_temp IS NULL THEN
      RETURN NULL;
    ELSE
      SELECT to_number(substr(ls_temp, 1, 4)) INTO anho FROM dual;
    
      SELECT MOD(to_number(substr(ls_temp, 5, 2)) - 1, 18)
        INTO campanha
        FROM dual;
    
      IF campanha = 0 THEN
        anho     := anho - 1;
        campanha := 18;
      END IF;
    
      SELECT TRIM(to_char(anho, '0000')) || TRIM(to_char(campanha, '00'))
        INTO ls_result
        FROM dual;
    
      RETURN ls_result;
    END IF;
  
  END gen_fn_dev_ant_campa;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_rec_cod_oper(pstipooperoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT op.cod_oper
              FROM rec_opera op
             WHERE op.oid_oper = top.rope_oid_oper)
      INTO ls_valor
      FROM rec_tipos_opera top
     WHERE top.oid_tipo_oper = pstipooperoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_cod_oper;

  /**************************************************************************
  Descripcion        : Devuelve Id de Posicion
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             pnOidPedCabec :
             pnOidProducto
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_rec_oid_posic
  (
    pnoidpedcabec NUMBER,
    pnoidproducto NUMBER
  ) RETURN NUMBER IS
    ln_id_posic NUMBER;
  BEGIN
    /* Obteniendo id de PED_SOLIC_POSIC */
    SELECT pos.oid_soli_posi
      INTO ln_id_posic
      FROM ped_solic_cabec solcab,
           ped_solic_posic pos
     WHERE solcab.soca_oid_soli_cabe = pnoidpedcabec
       AND pos.soca_oid_soli_cabe = solcab.oid_soli_cabe
       AND pos.prod_oid_prod = pnoidproducto;
  
    RETURN ln_id_posic;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN - 1;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_REC_OID_POSIC: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_dev_rec_oid_posic;

  /*****************************************/
  /*            GEN_FN_DEV_REC_ENTRE_MERCA                              */
  /*******************************************/
  FUNCTION gen_fn_dev_rec_entre_merca(psoidmerca NUMBER) RETURN VARCHAR2 IS
    ln_cod_merca VARCHAR2(2) := ' ';
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.cod_indi
      INTO ln_cod_merca
      FROM rec_indic_entre_merca a
     WHERE a.oid_indi_entr_merc = psoidmerca;
  
    RETURN ln_cod_merca;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ' ';
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_REC_ENTRE_MERCA: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_dev_rec_entre_merca;

  /*****************************************/
  /*            GEN_FN_DEV_REC_PRECI_PERDI                              */
  /*******************************************/
  FUNCTION gen_fn_dev_rec_preci_perdi(psoidprecioper NUMBER) RETURN VARCHAR2 IS
    ls_cod_precio VARCHAR2(2) := ' ';
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.cod_prec_perd
      INTO ls_cod_precio
      FROM rec_preci_perdi a
     WHERE a.oid_prec_perd = psoidprecioper;
  
    RETURN ls_cod_precio;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ' ';
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_REC_PRECI_PERDI: ' ||
                                ls_sqlerrm);
      END IF;
    
  END gen_fn_dev_rec_preci_perdi;

  /*****************************************/
  /*            GEN_FN_REC_COD_TIPO_ING                              */
  /*******************************************/

  FUNCTION gen_fn_rec_cod_tipo_ing(pstipoingoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT tin.cod_tipo_ingr
      INTO ls_valor
      FROM rec_tipo_ingre tin
     WHERE tin.oid_tipo_ingr = pstipoingoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_cod_tipo_ing;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_rec_cod_almac(pstipooperoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT almac.cod_alma
              FROM rec_opera op,
                   bel_almac almac
             WHERE op.oid_oper = top.rope_oid_oper
               AND op.almc_oid_alma = almac.oid_alma)
      INTO ls_valor
      FROM rec_tipos_opera top
     WHERE top.oid_tipo_oper = pstipooperoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_cod_almac;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_rec_tipo_movi_almac(pstipooperoid IN NUMBER)
    RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT (SELECT tmalmac.cod_tipo_movi
              FROM rec_opera            op,
                   bel_tipo_movim_almac tmalmac
             WHERE op.oid_oper = top.rope_oid_oper
               AND op.tmal_oid_tipo_movi_alma = tmalmac.oid_tipo_movi_alma)
      INTO ls_valor
      FROM rec_tipos_opera top
     WHERE top.oid_tipo_oper = pstipooperoid;
  
    RETURN ls_valor;
  
  END gen_fn_rec_tipo_movi_almac;

  /*****************************************/
  /*            GEN_FN_DES_REG                              */
  /*******************************************/
  FUNCTION gen_fn_des_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT zreg.des_regi
      INTO ls_valor
      FROM mae_clien             cl,
           mae_clien_unida_admin cladm,
           zon_terri_admin       zadm,
           zon_secci             zsec,
           zon_zona              zzon,
           zon_regio             zreg,
           seg_pais              pa,
           zon_sub_geren_venta   zsgv
     WHERE (cl.oid_clie = cladm.clie_oid_clie)
       AND (zadm.oid_terr_admi = cladm.ztad_oid_terr_admi)
       AND (zsec.oid_secc = zadm.zscc_oid_secc)
       AND (zzon.oid_zona = zsec.zzon_oid_zona)
       AND (zreg.oid_regi = zzon.zorg_oid_regi)
       AND (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
       AND (pa.oid_pais = cl.pais_oid_pais)
       AND (pa.cod_pais = vchpais)
       AND cladm.ind_acti = 1
       AND zreg.ind_acti = 1
       AND zsec.ind_acti = 1
       AND zzon.ind_acti = 1
       AND cl.oid_clie = ncliente;
    RETURN ls_valor;
  END gen_fn_des_reg;

  /*****************************************/
  /*            GEN_FN_COD_REG                              */
  /*******************************************/
  FUNCTION gen_fn_cod_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT zreg.cod_regi
      INTO ls_valor
      FROM mae_clien             cl,
           mae_clien_unida_admin cladm,
           zon_terri_admin       zadm,
           zon_secci             zsec,
           zon_zona              zzon,
           zon_regio             zreg,
           seg_pais              pa,
           zon_sub_geren_venta   zsgv
     WHERE (cl.oid_clie = cladm.clie_oid_clie)
       AND (zadm.oid_terr_admi = cladm.ztad_oid_terr_admi)
       AND (zsec.oid_secc = zadm.zscc_oid_secc)
       AND (zzon.oid_zona = zsec.zzon_oid_zona)
       AND (zreg.oid_regi = zzon.zorg_oid_regi)
       AND (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
       AND (pa.oid_pais = cl.pais_oid_pais)
       AND (pa.cod_pais = vchpais)
       AND cladm.ind_acti = 1
       AND zreg.ind_acti = 1
       AND zsec.ind_acti = 1
       AND zzon.ind_acti = 1
       AND cl.oid_clie = ncliente;
    RETURN ls_valor;
  END gen_fn_cod_reg;

  /*****************************************/
  /*            GEN_FN_OID_REG                              */
  /*******************************************/
  FUNCTION gen_fn_oid_reg
  (
    ncliente IN NUMBER,
    vchpais  IN VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT zreg.oid_regi
      INTO ls_valor
      FROM mae_clien             cl,
           mae_clien_unida_admin cladm,
           zon_terri_admin       zadm,
           zon_secci             zsec,
           zon_zona              zzon,
           zon_regio             zreg,
           seg_pais              pa,
           zon_sub_geren_venta   zsgv
     WHERE (cl.oid_clie = cladm.clie_oid_clie)
       AND (zadm.oid_terr_admi = cladm.ztad_oid_terr_admi)
       AND (zsec.oid_secc = zadm.zscc_oid_secc)
       AND (zzon.oid_zona = zsec.zzon_oid_zona)
       AND (zreg.oid_regi = zzon.zorg_oid_regi)
       AND (zsgv.oid_subg_vent = zreg.zsgv_oid_subg_vent)
       AND (pa.oid_pais = cl.pais_oid_pais)
       AND (pa.cod_pais = vchpais)
       AND cladm.ind_acti = 1
       AND zreg.ind_acti = 1
       AND zsec.ind_acti = 1
       AND zzon.ind_acti = 1
       AND cl.oid_clie = ncliente;
    RETURN ls_valor;
  END gen_fn_oid_reg;

  /******************************************************************/
  /*                   GEN_FN_COD_VENTA                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_venta(psmatrfactoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
  
    SELECT (SELECT ofdet.val_codi_vent
              FROM pre_ofert_detal ofdet
             WHERE ofdet.oid_deta_ofer = mafac.ofde_oid_deta_ofer)
      INTO ls_valor
      FROM pre_matri_factu mafac
     WHERE mafac.oid_matr_fact = psmatrfactoid;
  
    RETURN ls_valor;
  
  END gen_fn_cod_venta;

  /******************************************************************/
  /*                   GEN_FN_NUMLIN_OFER                               */
  /******************************************************************/

  FUNCTION gen_fn_numlin_ofer(psmatrfactoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
  
    SELECT (SELECT ofdet.num_line_ofer
              FROM pre_ofert_detal ofdet
             WHERE ofdet.oid_deta_ofer = mafac.ofde_oid_deta_ofer)
      INTO ls_valor
      FROM pre_matri_factu mafac
     WHERE mafac.oid_matr_fact = psmatrfactoid;
  
    RETURN ls_valor;
  
  END gen_fn_numlin_ofer;

  /******************************************************************/
  /*                   GEN_FN_NUM_OFER                              */
  /******************************************************************/

  FUNCTION gen_fn_num_ofer(psmatrfactoid IN NUMBER) RETURN NUMBER IS
    ls_valor NUMBER;
  BEGIN
  
    SELECT (SELECT ofer.num_ofer
              FROM pre_ofert ofer
             WHERE ofer.oid_ofer = ofdet.ofer_oid_ofer)
      INTO ls_valor
      FROM pre_matri_factu mafac,
           pre_ofert_detal ofdet
     WHERE mafac.oid_matr_fact = psmatrfactoid
       AND ofdet.oid_deta_ofer = mafac.ofde_oid_deta_ofer;
  
    RETURN ls_valor;
  
  END gen_fn_num_ofer;

  /******************************************************************/
  /*                   GEN_FN_COD_ESTR                              */
  /******************************************************************/

  FUNCTION gen_fn_cod_estr(psmatrfactoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
  
    SELECT (SELECT est.cod_estr
              FROM pre_ofert ofer,
                   pre_estra est
             WHERE ofer.oid_ofer = ofdet.ofer_oid_ofer
               AND ofer.coes_oid_estr = est.oid_estr)
      INTO ls_valor
      FROM pre_matri_factu mafac,
           pre_ofert_detal ofdet
     WHERE mafac.oid_matr_fact = psmatrfactoid
       AND ofdet.oid_deta_ofer = mafac.ofde_oid_deta_ofer;
  
    RETURN ls_valor;
  
  END gen_fn_cod_estr;

  /******************************************************************/
  /*                   GEN_FN_COD_TIPO_OFERT                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_tipo_ofert(pstipofertoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
  
    SELECT tof.cod_tipo_ofer
      INTO ls_valor
      FROM pre_tipo_ofert tof
     WHERE tof.oid_tipo_ofer = pstipofertoid;
  
    RETURN ls_valor;
  
  END gen_fn_cod_tipo_ofert;

  /*****************************************/
  /*            GEN_FN_TIPO_PERIO                              */
  /*******************************************/

  FUNCTION gen_fn_cod_clie(psclienteoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT cli.cod_clie
      INTO ls_valor
      FROM mae_clien cli
     WHERE cli.oid_clie = psclienteoid;
  
    RETURN ls_valor;
  
  END gen_fn_cod_clie;

  /*****************************************/
  /*            GEN_FN_COD_PAIS                              */
  /*******************************************/

  FUNCTION gen_fn_cod_pais(pspaisoid IN NUMBER) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT p.cod_pais
      INTO ls_valor
      FROM seg_pais p
     WHERE p.oid_pais = pspaisoid;
  
    RETURN ls_valor;
  
  END gen_fn_cod_pais;

  /************************************************************************/
  /* Descripcion    :Existe Solicitud Detalle                  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_exist_detal
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER IS
    ls_count NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO ls_count
      FROM int_solic_posic pos
     WHERE pos.cod_pais = pscodpais
       AND pos.cam_soli = pscodperiodo
       AND pos.cod_clie = pscodcliente;
  
    IF ls_count > 0 THEN
      RETURN 0;
    ELSE
      RETURN 1; -- indicador de error a 1
    END IF;
  END gen_fn_exist_detal;

  /************************************************************************
  Descripcion    :Existe Segundo Pedido
  Autor         : Marco Silva
  Fecha      : 04/11/2005
  ************************************************************************/
  FUNCTION gen_fn_exist_pedido
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER IS
  
    ls_count NUMBER;
  
  BEGIN
  
    SELECT COUNT(1)
      INTO ls_count
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND cons.ind_ocs_proc = '1';
  
    IF ls_count > 0 THEN
      RETURN 1;
    ELSE
      RETURN 0;
    END IF;
  END gen_fn_exist_pedido;
  /************************************************************************
  Descripcion    :Existe Segundo Pedido
  Autor         : Marco Silva
  Fecha      : 04/11/2005
  ************************************************************************/
  FUNCTION gen_fn_exist_pedido2
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER IS
  
    ls_count NUMBER;
  
  BEGIN
  
    SELECT COUNT(1)
      INTO ls_count
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND cons.ind_proc_gp2 = '1';
  
    IF ls_count > 0 THEN
      RETURN 1;
    ELSE
      RETURN 0;
    END IF;
  END gen_fn_exist_pedido2;
  /************************************************************************/
  /* Descripcion    :Indicador Reemplazo                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_ind_reemplazo
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN NUMBER IS
    ls_count NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO ls_count
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND
          --         cons.ind_ocs_proc = '0' and
           cons.ind_proc_gp2 = '0'
       AND cons.ind_erro_remp = '0';
  
    IF ls_count > 0 THEN
      RETURN 1;
    ELSE
      RETURN 0; -- indicador de error a 1
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
    
  END gen_fn_ind_reemplazo;

  /************************************************************************/
  /* Descripcion    :Indicador Rechazado                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_ind_rechaz
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2,
    psfecha      VARCHAR2
  ) RETURN NUMBER IS
    ls_count NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO ls_count
      FROM int_solic_cabec soli
     WHERE soli.cod_pais = pscodpais
       AND soli.cam_soli = pscodperiodo
       AND soli.cod_clie = pscodcliente
       AND soli.fec_soli = to_char(psfecha, 'dd/mm/yyyy')
       AND soli.sta_proc = 'R';
  
    IF ls_count > 0 THEN
      RETURN 1; -- indicador de error a 1
    ELSE
      RETURN 0;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
    
  END gen_fn_ind_rechaz;

  /************************************************************************/
  /* Descripcion    :Indicador GEN_FN_REMP_CUPON                                   */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_remp_cupon
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcupon   VARCHAR2
  ) RETURN VARCHAR2 IS
    --   ls_count NUMBER;
    ls_codventa VARCHAR2(10);
  BEGIN
    -- Se optimizo el query 17Dic2007
    SELECT nvl((SELECT eq.cod_venta
                 FROM cup_equiv_matr eq
                WHERE eq.cod_pais = pscodpais
                  AND eq.cod_peri = pscodperiodo
                  AND eq.cod_cupon = pscodcupon),
               pscodcupon)
      INTO ls_codventa
      FROM dual;
  
    /*      select psCodCupon into ls_codventa from dual ;
    
       SELECT COUNT(*) INTO ls_count FROM cup_equiv_matr eq
       WHERE eq.cod_pais = psCodPais and
             eq.cod_peri = psCodPeriodo and
             eq.cod_cupon = psCodCupon ;
    
       IF ls_count > 0 THEN
                   SELECT eq.cod_venta INTO ls_codventa FROM cup_equiv_matr eq
                   WHERE eq.cod_pais = psCodPais and
                         eq.cod_peri = psCodPeriodo and
                         eq.cod_cupon = psCodCupon ;
    
       END IF;
    */
    RETURN ls_codventa;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN pscodcupon;
    
  END gen_fn_remp_cupon;

  /**************************************************************************/
  /* Descripcion    :Actualiza Indicador Activo de Consultora               */
  /* Autor         : Marco Silva                                            */
  /* Fecha      : 23/04/2007                                                */
  /**************************************************************************/

  PROCEDURE ocr_pr_act_ind_cont_act(pscodigopais VARCHAR2) AS
    CURSOR curconsolcab IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca
       WHERE ca.cod_pais = pscodigopais;
  
    CURSOR curupdconsolcab IS
      SELECT ca.cod_pais,
             ca.cod_peri,
             ca.cod_clie,
             ca.num_lote
        FROM int_solic_conso_cabec ca
       WHERE ca.cod_pais = pscodigopais
         AND EXISTS (SELECT NULL
                FROM mae_clien             cl,
                     mae_clien_datos_adici ad
               WHERE cl.cod_clie = ca.cod_clie
                 AND ad.clie_oid_clie = cl.oid_clie
                 AND ad.ind_acti = 0);
  
    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
  
    v_num_lote t_num_lote;
  
    v_cod_pais t_cod_pais;
    v_cod_peri t_cod_peri;
    v_cod_clie t_cod_clie;
  
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
  
    lnindactivosto sto_tipo_docum_digit.ind_sto_acti%TYPE;
  
  BEGIN
  
    /*update int_solic_conso_cabec sc
    set sc.ind_cont_act = 0
    where sc.cod_pais = psCodigoPais ;*/
  
    OPEN curconsolcab;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curconsolcab BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_lote LIMIT rows;
    
      EXIT WHEN v_row_count = curconsolcab%ROWCOUNT;
      v_row_count := curconsolcab%ROWCOUNT;
    
      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_clie.count
        UPDATE int_solic_conso_cabec consol
           SET consol.ind_cont_act = 0
         WHERE consol.cod_pais = v_cod_pais(i)
           AND consol.cod_peri = v_cod_peri(i)
           AND consol.cod_clie = v_cod_clie(i)
           AND consol.num_lote = v_num_lote(i);
    END LOOP;
    CLOSE curconsolcab;
  
    lnindactivosto := sto_pkg_gener.sto_fn_devue_indic_activ_sto(pscodigopais,
                                                                 'OCC');
  
    IF (lnindactivosto = '0') THEN
    
      OPEN curupdconsolcab;
      LOOP
        -- Bulk collect data into memory table - X rows at a time
        FETCH curupdconsolcab BULK COLLECT
          INTO v_cod_pais,
               v_cod_peri,
               v_cod_clie,
               v_num_lote LIMIT rows;
      
        EXIT WHEN v_row_count = curupdconsolcab%ROWCOUNT;
        v_row_count := curupdconsolcab%ROWCOUNT;
      
        -- Bulk bind of data in memory table...
        FORALL i IN 1 .. v_cod_clie.count
          UPDATE int_solic_conso_cabec consol
             SET consol.ind_cont_act = 1
           WHERE consol.cod_pais = v_cod_pais(i)
             AND consol.cod_peri = v_cod_peri(i)
             AND consol.cod_clie = v_cod_clie(i)
             AND consol.num_lote = v_num_lote(i);
      END LOOP;
      CLOSE curupdconsolcab;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACT_IND_CONT_ACT: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_act_ind_cont_act;

  /**************************************************************************
  Descripcion        : Devuelve el Sgte Numero de Lote por Periodo del Bas Ctrl
  Fecha Creacion     : 29/01/2008
  Parametros Entrada :
  Autor              : Mrco Silva
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_lote_perio
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) AS
    p_numero      NUMBER(10);
    p_numerofinal VARCHAR2(10);
  BEGIN
  
    SELECT nvl(MAX(c.num_lote), 0)
      INTO p_numero
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.cod_peri = pscodigoperiodo;
  
    SELECT substr(to_char((p_numero) + 100000001), 2)
      INTO p_numerofinal
      FROM dual;
  
    UPDATE bas_ctrl_fact ctr
       SET ctr.num_lote = p_numerofinal,
           ctr.usu_modi = USER,
           ctr.fec_modi = SYSDATE
     WHERE ctr.cod_pais = pscodigopais
       AND ctr.cod_peri = pscodigoperiodo;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACTUA_LOTE_PERIO: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_actua_lote_perio;

  /************************************************************************
   Descripcion    :Consolida las cabeceras de OCS
   Autor          : Jose Cairampoma G.
   Fecha          : 25/10/2011
  ************************************************************************/
  PROCEDURE ocr_pr_solic_conso_cabec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  ) AS
    CURSOR curinsconsolcab IS
      SELECT cab.cod_pais pais,
             cab.cam_soli periodo,
             cab.cod_clie cliente,
             cab.fec_soli fecsolic,
             cab.num_clie numcliente,
             cab.tip_soli tiposolic,
             cab.cod_suba suba,
             cab.cod_acce acc,
             'PD' tipodesp,
             nvl(cab.sta_proc, 'R') staproc,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'NOM_CLIE') nomclie,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_REGI') codregi,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'DES_REGI') desregi,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_ZONA') codzona,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'DES_ZONA') deszona,
             gen_pkg_gener.gen_fn_clien_datos(cab.cod_clie, 'COD_TERR') codterr,
             'N' tiporde,
             '' valmontpedi,
             '' valsalddeud,
             decode(cab.sta_proc, 'R', 1, 0) indrech,
             '0' ind_erro_deud,
             '0' ind_erro_mtmi,
             '0' ind_erro_mtma,
             '0' ind_erro_unma,
             gen_fn_exist_pedido(cab.cod_pais, cab.cam_soli, cab.cod_clie) ind_error_sgpe,
             '0' indnodetal,
             '0' ind_ocs_proc,
             '0' ind_bloq_admi,
             '0' ind_ocs_bloq,
             '0' ind_admi_cart,
             '0' ind_bloq_fina,
             '0' ind_comp_mont,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             pscodigousuario usu_modi,
             SYSDATE fec_modi,
             NULL cod_marc,
             NULL des_marc,
             NULL cod_cana,
             NULL des_cana,
             gen_fn_ind_reemplazo(cab.cod_pais, cab.cam_soli, cab.cod_clie) ind_erro_remp, -- Temporalmente trae INFO para IND_REEMPLAZO luego se setea a 'O'
             NULL usu_admi_cart,
             NULL fec_admi_cart,
             NULL obs_prub,
             '1' ind_orig_cabe,
             cab.num_lote,
             cab.fec_soli fec_prog_fact,
             '0' ind_proc_gp2,
             '0' ind_cup_prog,
             '0' ind_cup_desp,
             '0' ind_cup_cta,
             '0' ind_cont_act,
             '0' ind_anul,
             seq_docu_sto.nextval sec_nume_docu,
             cab.num_docu docu_num_docu,
             cab.cod_moti_rech cod_moti_rech,
             cab.cod_regi_arri cod_regi_arri,
             cab.cod_zona_arri cod_zona_arri,
             NULL oid_soli_cabe,
             NULL num_clien,
             NULL tspa_oid_tipo_soli_pais,
             NULL mone_oid_mone,
             NULL tids_oid_tipo_desp,
             NULL almc_oid_alma,
             NULL modu_oid_modu,
             NULL ticl_oid_tipo_clie,
             NULL taim_oid_tasa_impu,
             NULL perd_oid_peri,
             NULL clie_oid_clie,
             NULL clie_oid_clie_rece_fact,
             NULL clie_oid_clie_paga,
             NULL clie_oid_clie_dest,
             NULL cldi_oid_clie_dire,
             NULL tdoc_oid_tipo_docu,
             NULL soci_oid_soci,
             NULL sbac_oid_sbac,
             NULL terr_oid_terr,
             NULL zzon_oid_zona,
             NULL val_nume_soli,
             NULL val_usua,
             NULL val_tasa_impu,
             NULL fec_cron,
             NULL ind_perm_unio_sol,
             NULL val_tipo_camb,
             NULL num_docu_orig,
             NULL val_unid_dema_real_tota,
             NULL num_unid_por_aten_tota,
             NULL num_unid_aten_tota,
             NULL ind_oc,
             NULL ind_pedi_prue,
             1 ind_ts_no_conso,
             NULL val_glos_obse,
             NULL val_obse_revi,
             NULL val_impo_desc_3_tota_loca,
             NULL val_impo_dto_3_sin_imp_tot_loc,
             NULL pais_oid_pais,
             NULL tido_oid_tipo_docu,
             NULL vepo_oid_valo_estr_geop,
             NULL esso_oid_esta_soli,
             NULL copa_oid_para_gene,
             NULL grpr_oid_grup_proc,
             NULL sbti_oid_subt_clie,
             NULL acfi_oid_acce_fisi,
             NULL tspa_oid_tipo_soli_pais_cons,
             NULL fopa_oid_form_pago,
             NULL clso_oid_clas_soli,
             NULL ztad_oid_terr_admi,
             NULL oper_oid_oper,
             NULL proc_oid_proc,
             NULL soca_oid_docu_refe,
             NULL ind_inte_lari_gene,
             NULL fec_prog_fact_comp,
             NULL cact_oid_acti,
             NULL esta_oid_esta_clie,
             NULL ind_fac_refac,
             '1' ind_envi_sto,
             NULL soca_oid_soli_cabe_refe,
             NULL ind_val_mtmi,
             NULL ind_val_mtma,
             NULL niri_oid_nive_ries,
             NULL val_tota_paga_prom,
             decode(cab.ind_proc, 'O', '1', '0') ind_rece_ocr,
             decode(cab.ind_proc, 'W', '1', '0') ind_rece_web,
             decode(cab.ind_proc, 'D', '1', '0') ind_rece_dd,
             decode(cab.ind_proc, 'G', '1', '0') ind_rece_digi,
             decode(cab.ind_proc, 'C', '1', '0') ind_rece_cc,
             decode(cab.ind_proc, 'M', '1', '0') ind_rece_mens,
             NULL ind_vali_exis_cron,
             NULL ind_vali_venc_cron,
             NULL val_prec_flet,
             ind_docu_iden ind_docu_iden,
             decode(cab.ind_proc, 'L', '1', '0') ind_rece_onli,
             decode(cab.ind_proc, 'I', '1', '0') ind_rece_ivr,
             cab.val_mnto_flex,
             cab.ind_util_flex,
             cab.ind_acep_flex,
             cab.ind_vali_prol,
             NULL ind_erro_mmfc,
             NULL ind_ped_rec_anul,
             '0' ind_erro_desv,
             NULL ind_vali_prol_anul,
             NULL val_porc_desv,
             NULL val_prom_pedi,
             NULL val_mont_real,
             NULL VAL_RECL_PEND,
             NULL VAL_SALD_RECH,NULL
        FROM int_solic_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.num_lote_sto = psnumerolotesto
         AND cab.cam_soli = pscodigoperiodo;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_conso_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    rows NATURAL := 10000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    v_ind_actu_indi_prim_pedi bas_pais.ind_actu_indi_prim_pedi%TYPE;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
    lsindactuzonanullzonaarri bas_param_pais.val_para%TYPE := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                                                              'PED',
                                                                                              '016');
  
    lnoidperiodo cra_perio.oid_peri%TYPE;
  BEGIN
    SELECT ind_actu_indi_prim_pedi
      INTO v_ind_actu_indi_prim_pedi
      FROM bas_pais
     WHERE cod_pais = pscodigopais;
  
    SELECT MIN(c.oid_peri)
      INTO lnoidperiodo
      FROM cra_perio       c,
           seg_perio_corpo sp
     WHERE sp.oid_peri = c.peri_oid_peri
       AND cod_peri = pscodigoperiodo;
  
    OPEN curinsconsolcab;
    LOOP
    
      FETCH curinsconsolcab BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR i IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
        BEGIN
        
          sol_cab_tab(i).perd_oid_peri := lnoidperiodo;
        
          IF (sol_cab_tab(i).ind_erro_remp = '1') THEN
            UPDATE int_solic_conso_cabec consol
               SET consol.ind_erro_remp = '1'
             WHERE consol.cod_pais = sol_cab_tab(i).cod_pais
               AND consol.cod_peri = sol_cab_tab(i).cod_peri
               AND consol.cod_clie = sol_cab_tab(i).cod_clie
               AND consol.num_lote < sol_cab_tab(i).num_lote;
          END IF;
          IF v_ind_actu_indi_prim_pedi = 'S' THEN
            SELECT nvl(MIN('P'), sol_cab_tab(i).tip_orde)
              INTO sol_cab_tab(i).tip_orde
              FROM mae_clien_datos_adici cda,
                   mae_estat_clien       mec,
                   mae_clien             mc
             WHERE cda.esta_oid_esta_clie = mec.oid_esta_clie
               AND mc.oid_clie = cda.clie_oid_clie
               AND mc.cod_clie = sol_cab_tab(i).cod_clie
               AND mec.cod_esta_clie IN ('01', '07');
          END IF;
          sol_cab_tab(i).ind_erro_remp := '0';
        
          SELECT MIN(oid_clie),
                 MIN(nom_clie),
                 MIN(cod_regi),
                 MIN(des_regi),
                 MIN(cod_zona),
                 MIN(des_zona),
                 MIN(cod_terr)
            INTO sol_cab_tab(i).clie_oid_clie,
                 sol_cab_tab(i).nom_clie,
                 sol_cab_tab(i).cod_regi,
                 sol_cab_tab(i).des_regi,
                 sol_cab_tab(i).cod_zona,
                 sol_cab_tab(i).des_zona,
                 sol_cab_tab(i).cod_terr
            FROM v_mae_clie_unida_admin t
           WHERE cod_clie = sol_cab_tab(i).cod_clie;
        
          IF lsindactuzonanullzonaarri = 'S' THEN
            IF sol_cab_tab(i).cod_regi IS NULL AND sol_cab_tab(i)
               .cod_regi_arri IS NOT NULL THEN
              sol_cab_tab(i).cod_regi := sol_cab_tab(i).cod_regi_arri;
              sol_cab_tab(i).des_regi := 'Region de Arribo ' || sol_cab_tab(i)
                                        .cod_regi_arri;
            END IF;
            IF sol_cab_tab(i).cod_zona IS NULL AND sol_cab_tab(i)
               .cod_zona_arri IS NOT NULL THEN
              sol_cab_tab(i).cod_zona := sol_cab_tab(i).cod_zona_arri;
              sol_cab_tab(i).des_zona := 'Zona de Arribo ' || sol_cab_tab(i)
                                        .cod_zona_arri;
            END IF;
          END IF;
          INSERT INTO int_solic_conso_cabec VALUES sol_cab_tab (i);
        
          sto_tab(i).cod_pais := sol_cab_tab(i).cod_pais;
          sto_tab(i).cod_tipo_docu := pscodigotipodocumento;
          sto_tab(i).num_lote := sol_cab_tab(i).num_lote;
          sto_tab(i).sec_nume_docu := sol_cab_tab(i).sec_nume_docu;
          sto_tab(i).num_docu := sol_cab_tab(i).docu_num_docu;
          sto_tab(i).ind_envi := '0';
          sto_tab(i).ind_rech := '0';
          sto_tab(i).fec_digi := sol_cab_tab(i).fec_digi;
          sto_tab(i).usu_digi := sol_cab_tab(i).usu_digi;
          sto_tab(i).fec_modi := sol_cab_tab(i).fec_modi;
          sto_tab(i).usu_modi := sol_cab_tab(i).usu_modi;
          sto_tab(i).cod_zona := sol_cab_tab(i).cod_zona;
          sto_tab(i).cod_clie := sol_cab_tab(i).cod_clie;
          sto_tab(i).cod_regi := sol_cab_tab(i).cod_regi;
          sto_tab(i).cod_peri := sol_cab_tab(i).cod_peri;
          --sto_tab(i).COD_MOTI_RECH      :=
          --sto_tab(i).val_obse_rech_defi := sol_cab_tab(i).val_obse_rech_defi;
          sto_tab(i).ind_rece_ocr := sol_cab_tab(i).ind_rece_ocr;
          sto_tab(i).ind_rece_web := sol_cab_tab(i).ind_rece_web;
          sto_tab(i).ind_rece_dd := sol_cab_tab(i).ind_rece_dd;
          sto_tab(i).ind_rece_digi := sol_cab_tab(i).ind_rece_digi;
          sto_tab(i).ind_rece_cc := sol_cab_tab(i).ind_rece_cc;
          sto_tab(i).ind_rece_mens := sol_cab_tab(i).ind_rece_mens;
          sto_tab(i).ind_elim := '0';
          sto_tab(i).cod_zona_arri := sol_cab_tab(i).cod_zona_arri;
          sto_tab(i).ind_rece_onli := sol_cab_tab(i).ind_rece_onli;
          sto_tab(i).ind_rece_ivr := sol_cab_tab(i).ind_rece_ivr;
        
          INSERT INTO sto_docum_digit VALUES sto_tab (i);
        EXCEPTION
          WHEN dup_val_on_index THEN
            UPDATE int_solic_conso_cabec
               SET num_clie      = sol_cab_tab(i).num_clie,
                   tipo_soli     = sol_cab_tab(i).tipo_soli,
                   cod_sbac      = sol_cab_tab(i).cod_sbac,
                   cod_acce      = sol_cab_tab(i).cod_acce,
                   tip_desp      = sol_cab_tab(i).tip_desp,
                   sta_proc      = sol_cab_tab(i).sta_proc,
                   ind_erro_rech = sol_cab_tab(i).ind_erro_rech,
                   ind_bloq_fina = sol_cab_tab(i).ind_bloq_fina,
                   ind_bloq_admi = sol_cab_tab(i).ind_bloq_admi,
                   ind_erro_node = sol_cab_tab(i).ind_erro_node,
                   usu_modi      = sol_cab_tab(i).usu_modi,
                   fec_modi      = sol_cab_tab(i).fec_modi,
                   ind_orig_cabe = '1',
                   docu_num_docu = nvl(docu_num_docu,
                                       sol_cab_tab(i).docu_num_docu),
                   cod_moti_rech = sol_cab_tab(i).cod_moti_rech,
                   cod_regi_arri = sol_cab_tab(i).cod_regi_arri,
                   cod_zona_arri = sol_cab_tab(i).cod_zona_arri,
                   ind_erro_mtmi = '0',
                   ind_rece_ocr  = decode(ind_rece_ocr,
                                          '1',
                                          ind_rece_ocr,
                                          sol_cab_tab(i).ind_rece_ocr),
                   ind_rece_web  = decode(ind_rece_web,
                                          '1',
                                          ind_rece_web,
                                          sol_cab_tab(i).ind_rece_web),
                   ind_rece_dd   = decode(ind_rece_dd,
                                          '1',
                                          ind_rece_dd,
                                          sol_cab_tab(i).ind_rece_dd),
                   ind_rece_digi = decode(ind_rece_digi,
                                          '1',
                                          ind_rece_digi,
                                          sol_cab_tab(i).ind_rece_digi),
                   ind_rece_cc   = decode(ind_rece_cc,
                                          '1',
                                          ind_rece_cc,
                                          sol_cab_tab(i).ind_rece_cc),
                   ind_rece_mens = decode(ind_rece_mens,
                                          '1',
                                          ind_rece_mens,
                                          sol_cab_tab(i).ind_rece_mens),
                   ind_rece_onli = decode(ind_rece_onli,
                                          '1',
                                          ind_rece_onli,
                                          sol_cab_tab(i).ind_rece_onli),
                   ind_rece_ivr  = decode(ind_rece_ivr,
                                          '1',
                                          ind_rece_ivr,
                                          sol_cab_tab(i).ind_rece_ivr),
                   val_mnto_flex = CASE
                                     WHEN nvl(sol_cab_tab(i).val_mnto_flex, 0) >
                                          nvl(val_mnto_flex, 0) THEN
                                      nvl(sol_cab_tab(i).val_mnto_flex, 0)
                                     ELSE
                                      nvl(val_mnto_flex, 0)
                                   END,
                   ind_util_flex = nvl(sol_cab_tab(i).ind_util_flex,
                                       ind_util_flex),
                   ind_acep_flex = nvl(sol_cab_tab(i).ind_acep_flex,
                                       ind_acep_flex),
                   ind_vali_prol = nvl(sol_cab_tab(i).ind_vali_prol,
                                       ind_vali_prol)
             WHERE cod_pais = sol_cab_tab(i).cod_pais
               AND cod_peri = sol_cab_tab(i).cod_peri
               AND cod_clie = sol_cab_tab(i).cod_clie
               AND num_lote = sol_cab_tab(i).num_lote
            RETURNING sec_nume_docu INTO sol_cab_tab(i).sec_nume_docu;
          
            UPDATE sto_docum_digit
               SET ind_rece_ocr  = decode(ind_rece_ocr,
                                          '1',
                                          ind_rece_ocr,
                                          sol_cab_tab(i).ind_rece_ocr),
                   ind_rece_web  = decode(ind_rece_web,
                                          '1',
                                          ind_rece_web,
                                          sol_cab_tab(i).ind_rece_web),
                   ind_rece_dd   = decode(ind_rece_dd,
                                          '1',
                                          ind_rece_dd,
                                          sol_cab_tab(i).ind_rece_dd),
                   ind_rece_digi = decode(ind_rece_digi,
                                          '1',
                                          ind_rece_digi,
                                          sol_cab_tab(i).ind_rece_digi),
                   ind_rece_cc   = decode(ind_rece_cc,
                                          '1',
                                          ind_rece_cc,
                                          sol_cab_tab(i).ind_rece_cc),
                   ind_rece_mens = decode(ind_rece_mens,
                                          '1',
                                          ind_rece_mens,
                                          sol_cab_tab(i).ind_rece_mens),
                   ind_rece_onli = decode(ind_rece_onli,
                                          '1',
                                          ind_rece_onli,
                                          sol_cab_tab(i).ind_rece_onli),
                   ind_rece_ivr  = decode(ind_rece_ivr,
                                          '1',
                                          ind_rece_ivr,
                                          sol_cab_tab(i).ind_rece_ivr)
            
             WHERE sec_nume_docu = sol_cab_tab(i).sec_nume_docu
               AND num_lote = sol_cab_tab(i).num_lote;
        END;
      END LOOP;
    
    END LOOP;
    CLOSE curinsconsolcab;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_SOLIC_CONSO_CABEC: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_solic_conso_cabec;

  /**************************************************************************
   Descripcion       : Consolida las detalles de OCS
   Fecha Creacion    : 25/10/2011
   Parametros:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
   Autor             : Jose Cairampoma G.
  ***************************************************************************/
  PROCEDURE ocr_pr_solic_conso_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigoperiodo       VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumerolotesto       VARCHAR2
  ) AS
    CURSOR curinsconsoldet IS
      SELECT det.cod_pais,
             det.cam_soli,
             det.cod_clie,
             det.cod_prod,
             det.tip_posi,
             NULL des_prod,
             nvl(det.uni_dema, 0),
             det.sta_proc,
             '0' ind_comp_mtmi,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             NULL usu_modi,
             NULL fec_modi,
             '0' val_unid_comp,
             '' fec_sol,
             '0' indrech,
             det.num_lote,
             '0' ind_erro_sse,
             seq_docu_sto.nextval sec_nume_docu,
             num_docu docu_num_docu,
             det.cod_moti_rech cod_moti_rech,
             NULL oid_soli_posi,
             NULL soca_oid_soli_cabe,
             NULL cod_posi,
             nvl(det.uni_dema, 0) num_unid_dema,
             NULL num_unid_por_aten,
             NULL tpos_oid_tipo_posi,
             NULL prod_oid_prod,
             NULL fopa_oid_form_pago,
             NULL val_codi_vent,
             NULL espo_oid_esta_posi,
             NULL stpo_oid_subt_posi,
             NULL val_codi_vent_fict,
             NULL num_unid_dema_real,
             NULL val_prec_cata_unit_loca,
             NULL val_prec_cont_unit_loca,
             NULL val_prec_cata_unit_docu,
             NULL val_prec_conta_unit_docu,
             NULL val_porc_desc,
             NULL val_impo_desc_unit_docu,
             NULL ofde_oid_deta_ofer,
             NULL sopo_oid_soli_posi,
             NULL num_unid_compr,
             NULL ind_no_impr,
             NULL num_prem,
             NULL copa_oid_para_gral,
             NULL panp_oid_para_nive_prem,
             '1' ind_envi_sto,
             NULL val_fact_repe,
             NULL val_unid_dema_ante,
             decode(det.ind_proc, 'O', '1', '0') ind_rece_ocr,
             decode(det.ind_proc, 'W', '1', '0') ind_rece_web,
             decode(det.ind_proc, 'D', '1', '0') ind_rece_dd,
             NULL acce_oid_acce,
             decode(det.ind_proc, 'G', '1', '0') ind_rece_digi,
             decode(det.ind_proc, 'C', '1', '0') ind_rece_cc,
             decode(det.ind_proc, 'M', '1', '0') ind_rece_mens,
             NULL ind_limi_vent_foca,
             NULL val_unid_dema_lvfo,
             decode(det.ind_proc, 'L', '1', '0') ind_rece_onli,
             decode(det.ind_proc, 'I', '1', '0') ind_rece_ivr,
             cons.sec_nume_docu sec_nume_docu_cabe,
             NULL oid_alma,
             decode(det.ind_proc, 'C', nvl(det.uni_dema, 0), 0) val_unid_cc,
             decode(det.ind_proc, 'D', nvl(det.uni_dema, 0), 0) val_unid_dd,
             decode(det.ind_proc, 'G', nvl(det.uni_dema, 0), 0) val_unid_digi,
             decode(det.ind_proc, 'I', nvl(det.uni_dema, 0), 0) val_unid_ivr,
             decode(det.ind_proc, 'M', nvl(det.uni_dema, 0), 0) val_unid_mens,
             decode(det.ind_proc, 'O', nvl(det.uni_dema, 0), 0) val_unid_ocr,
             decode(det.ind_proc, 'L', nvl(det.uni_dema, 0), 0) val_unid_onli,
             decode(det.ind_proc, 'W', nvl(det.uni_dema, 0), 0) val_unid_web,
             det.cod_prod ind_recu_prol
        FROM int_solic_posic       det,
             int_solic_conso_cabec cons
       WHERE det.cod_pais = pscodigopais
         AND det.cam_soli = pscodigoperiodo
         AND det.sta_proc <> 'R'
         AND det.cod_pais = cons.cod_pais
         AND det.cam_soli = cons.cod_peri
         AND det.cod_clie = cons.cod_clie
         AND det.num_lote = cons.num_lote
         AND cons.ind_proc_gp2 = '0'
         AND det.num_lote_sto = psnumerolotesto;
  
    TYPE solic_det_tab_t IS TABLE OF int_solic_conso_detal%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_det_tab solic_det_tab_t; -- In-memory table
  
    rows NATURAL := 30000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    OPEN curinsconsoldet;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsoldet BULK COLLECT
        INTO sol_det_tab LIMIT rows;
      EXIT WHEN sol_det_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FOR i IN sol_det_tab.first .. sol_det_tab.last
      LOOP
        BEGIN
        
          INSERT INTO int_solic_conso_detal VALUES sol_det_tab (i);
        
          sto_tab(i).cod_pais := sol_det_tab(i).cod_pais;
          sto_tab(i).cod_tipo_docu := pscodigotipodocumento;
          sto_tab(i).num_lote := sol_det_tab(i).num_lote;
          sto_tab(i).sec_nume_docu := sol_det_tab(i).sec_nume_docu;
          sto_tab(i).num_docu := sol_det_tab(i).docu_num_docu;
          sto_tab(i).ind_envi := '0';
          sto_tab(i).ind_rech := '0';
          sto_tab(i).fec_digi := sol_det_tab(i).fec_digi;
          sto_tab(i).usu_digi := sol_det_tab(i).usu_digi;
          sto_tab(i).fec_modi := sol_det_tab(i).fec_modi;
          sto_tab(i).usu_modi := sol_det_tab(i).usu_modi;
          sto_tab(i).cod_clie := sol_det_tab(i).cod_clie;
          sto_tab(i).sec_nume_docu_cabe := sol_det_tab(i).sec_nume_docu_cabe;
          sto_tab(i).cod_peri := sol_det_tab(i).cod_peri;
          sto_tab(i).ind_rece_ocr := sol_det_tab(i).ind_rece_ocr;
          sto_tab(i).ind_rece_web := sol_det_tab(i).ind_rece_web;
          sto_tab(i).ind_rece_dd := sol_det_tab(i).ind_rece_dd;
          sto_tab(i).ind_rece_digi := sol_det_tab(i).ind_rece_digi;
          sto_tab(i).ind_rece_cc := sol_det_tab(i).ind_rece_cc;
          sto_tab(i).ind_rece_mens := sol_det_tab(i).ind_rece_mens;
          sto_tab(i).ind_elim := '0';
          sto_tab(i).ind_rece_onli := sol_det_tab(i).ind_rece_onli;
          sto_tab(i).ind_rece_ivr := sol_det_tab(i).ind_rece_ivr;
        
          SELECT cod_zona,
                 cod_regi,
                 cod_zona_arri
            INTO sto_tab(i).cod_zona,
                 sto_tab(i).cod_regi,
                 sto_tab(i).cod_zona_arri
            FROM int_solic_conso_cabec
           WHERE sec_nume_docu = sol_det_tab(i).sec_nume_docu_cabe
             AND num_lote = sol_det_tab(i).num_lote;
        
          INSERT INTO sto_docum_digit VALUES sto_tab (i);
        
        EXCEPTION
          WHEN dup_val_on_index THEN
            UPDATE int_solic_conso_detal a
               SET a.val_unid_dem = a.val_unid_dem + sol_det_tab(i)
                                   .val_unid_dem,
                   ind_rece_ocr   = decode(ind_rece_ocr,
                                           '1',
                                           ind_rece_ocr,
                                           sol_det_tab(i).ind_rece_ocr),
                   ind_rece_web   = decode(ind_rece_web,
                                           '1',
                                           ind_rece_web,
                                           sol_det_tab(i).ind_rece_web),
                   ind_rece_dd    = decode(ind_rece_dd,
                                           '1',
                                           ind_rece_dd,
                                           sol_det_tab(i).ind_rece_dd),
                   ind_rece_digi  = decode(ind_rece_digi,
                                           '1',
                                           ind_rece_digi,
                                           sol_det_tab(i).ind_rece_digi),
                   ind_rece_cc    = decode(ind_rece_cc,
                                           '1',
                                           ind_rece_cc,
                                           sol_det_tab(i).ind_rece_cc),
                   ind_rece_mens  = decode(ind_rece_mens,
                                           '1',
                                           ind_rece_mens,
                                           sol_det_tab(i).ind_rece_mens),
                   ind_rece_onli  = decode(ind_rece_onli,
                                           '1',
                                           ind_rece_onli,
                                           sol_det_tab(i).ind_rece_onli),
                   ind_rece_ivr   = decode(ind_rece_ivr,
                                           '1',
                                           ind_rece_onli,
                                           sol_det_tab(i).ind_rece_onli),
                   val_unid_cc    = val_unid_cc + sol_det_tab(i).val_unid_cc,
                   val_unid_dd    = val_unid_dd + sol_det_tab(i).val_unid_dd,
                   val_unid_digi  = val_unid_digi + sol_det_tab(i)
                                   .val_unid_digi,
                   val_unid_ivr   = val_unid_ivr + sol_det_tab(i)
                                   .val_unid_ivr,
                   val_unid_mens  = val_unid_mens + sol_det_tab(i)
                                   .val_unid_mens,
                   val_unid_ocr   = val_unid_ocr + sol_det_tab(i)
                                   .val_unid_ocr,
                   val_unid_onli  = val_unid_onli + sol_det_tab(i)
                                   .val_unid_onli,
                   val_unid_web   = val_unid_web + sol_det_tab(i)
                                   .val_unid_web
             WHERE cod_pais = sol_det_tab(i).cod_pais
               AND cod_peri = sol_det_tab(i).cod_peri
               AND cod_clie = sol_det_tab(i).cod_clie
               AND num_lote = sol_det_tab(i).num_lote
               AND cod_vent = sol_det_tab(i).cod_vent
               AND tip_posic = sol_det_tab(i).tip_posic
            RETURNING sec_nume_docu INTO sol_det_tab(i).sec_nume_docu;
          
            UPDATE sto_docum_digit
               SET ind_rece_ocr  = decode(ind_rece_ocr,
                                          '1',
                                          ind_rece_ocr,
                                          sol_det_tab(i).ind_rece_ocr),
                   ind_rece_web  = decode(ind_rece_web,
                                          '1',
                                          ind_rece_web,
                                          sol_det_tab(i).ind_rece_web),
                   ind_rece_dd   = decode(ind_rece_dd,
                                          '1',
                                          ind_rece_dd,
                                          sol_det_tab(i).ind_rece_dd),
                   ind_rece_digi = decode(ind_rece_digi,
                                          '1',
                                          ind_rece_digi,
                                          sol_det_tab(i).ind_rece_digi),
                   ind_rece_cc   = decode(ind_rece_cc,
                                          '1',
                                          ind_rece_cc,
                                          sol_det_tab(i).ind_rece_cc),
                   ind_rece_mens = decode(ind_rece_mens,
                                          '1',
                                          ind_rece_mens,
                                          sol_det_tab(i).ind_rece_mens),
                   ind_rece_onli = decode(ind_rece_onli,
                                          '1',
                                          ind_rece_onli,
                                          sol_det_tab(i).ind_rece_onli)
             WHERE sec_nume_docu = sol_det_tab(i).sec_nume_docu
               AND num_lote = sol_det_tab(i).num_lote;
        END;
      END LOOP;
    
    END LOOP;
    CLOSE curinsconsoldet;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_SOLIC_CONSO_DETAL: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_solic_conso_detal;

  /**************************************************************************
    Descripcion       : OCR_PR_DIGIT_CABEC_DETAL
                        Inserta Cabeceras Detalles hacia temporales de Pedidos
    Fecha Creacion    : 26/02/2008
    Parametros Entrada:
        psCodigoPais : Codigo de pais
        psCodigoPeriodo : Codigo de periodo
        psUsuario : Codigo de Usuario
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_digit_cabec_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  ) AS
    CURSOR curinsconsolcabec IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             0,
             'SOC',
             '000',
             '01',
             'N',
             cab.fec_soli,
             'A',
             seq_solic_cab.nextval,
             cab.num_lote,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             --'G' ind_proc,
             cab.ind_proc ind_proc,
             NULL         fec_proc,
             NULL         fec_fact,
             NULL         num_lote_dd,
             NULL         ind_clie_vali,
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
             NULL,NULL
        FROM ped_solic_digit_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cab.ind_ocs = '1'
         AND EXISTS (SELECT NULL
                FROM ped_solic_digit_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.ind_ocs_detal = '0' -- los detalles q aun no se envian
              );
  
    CURSOR curinsconsoldet IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             'OC',
             det.cod_vent, --  GEN_FN_REMP_CUPON(det.cod_pais, det.cod_peri, det.cod_vent )    ,
             det.val_unid_dem,
             'A' sta_proc,
             seq_solic_pos.nextval,
             det.num_lote,
             NULL,
             NULL,
             NULL,
             NULL,
             --'G' ind_proc,
             det.ind_proc ind_proc,
             NULL         cod_vent,
             NULL         num_lote_dd,
             NULL,
             NULL,
             NULL
        FROM ped_solic_digit_detal det
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo
         AND det.ind_ocs_detal = '0'
         AND EXISTS (SELECT NULL
                FROM ped_solic_digit_cabec cab
               WHERE cab.cod_pais = det.cod_pais
                 AND cab.cod_peri = det.cod_peri
                 AND cab.cod_clie = det.cod_clie
                 AND cab.num_lote = det.num_lote
                 AND cab.ind_ocs = '1');
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t; -- In-memory table
  
    TYPE solic_det_tab_t IS TABLE OF int_solic_posic%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_det_tab solic_det_tab_t; -- In-memory table
  
    --c1  rows NATURAL := 1000; -- Number of rows to process at a time
    rows NATURAL := 10000; --c1
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
    lsnumerolote bas_ctrl_fact.num_lote%TYPE;
  
  BEGIN
    /*Actualiza las cabeceras de los pedidos digitados que uan no fuereon enviados para el periodo*/
    UPDATE ped_solic_digit_cabec cab
       SET cab.fec_modi = SYSDATE,
           cab.ind_ocs  = '1'
     WHERE cab.cod_pais = pscodigopais
       AND cab.cod_peri = pscodigoperiodo
       AND cab.ind_ocs = '0';
  
    SELECT bas.num_lote
      INTO lsnumerolote
      FROM bas_ctrl_fact bas
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
    DELETE int_solic_cabec;
  
    DELETE int_solic_posic;
  
    OPEN curinsconsolcabec;
    LOOP
      FETCH curinsconsolcabec BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR j IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
      
        sol_cab_tab(j).num_lote := lsnumerolote;
      
      END LOOP;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_cabec VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcabec;
  
    OPEN curinsconsoldet;
    LOOP
    
      FETCH curinsconsoldet BULK COLLECT
        INTO sol_det_tab LIMIT rows;
      EXIT WHEN sol_det_tab.count = 0;
    
      FOR j IN sol_det_tab.first .. sol_det_tab.last
      LOOP
      
        UPDATE ped_solic_digit_detal de
           SET de.ind_ocs_detal = 1,
               de.usu_modi      = pscodigousuario,
               de.fec_modi      = SYSDATE
         WHERE de.cod_pais = sol_det_tab(j).cod_pais
           AND de.cod_peri = sol_det_tab(j).cam_soli
           AND de.cod_clie = sol_det_tab(j).cod_clie
           AND de.num_lote = sol_det_tab(j).num_lote
           AND de.cod_vent = sol_det_tab(j).cod_prod;
      
        sol_det_tab(j).num_lote := lsnumerolote;
      
      END LOOP;
    
      -- Bulk bind of data in memory table...
      FORALL j IN sol_det_tab.first .. sol_det_tab.last
        INSERT INTO int_solic_posic VALUES sol_det_tab (j);
    
    END LOOP;
    CLOSE curinsconsoldet;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_DIGIT_CABEC_DETAL: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_digit_cabec_detal;

  /**************************************************************************
  Descripcion       : OCR_PR_DIGIT_CONSO_DETAL
                      Consolida de Detalles de los Pedidos Digitados
  Fecha Creacion    : 26/02/2008
  Parametros Entrada:
      psCodigoPais : Codigo de pais
      psCodigoPeriodo : Codigo de periodo
      psUsuario : Codigo de Usuario
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE ocr_pr_digit_conso_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  ) AS
  
  BEGIN
    -- Carga Cabeceras y Detalles hacia temporales
    ocr_pr_digit_cabec_detal(pscodigopais,
                             pscodigoperiodo,
                             pscodigousuario);
    -- Consolidacion de Cabeceras y Detalles hacia tabla de Pedidos
    --OCR_PR_SOLIC_CONSO_CABEC(psCodigoPais , psCodigoUsuario ) ;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_DIGIT_CONSO_DETAL: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_digit_conso_detal;

  /**************************************************************************
  Descripcion        : Devuelve Id de Periodo
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo seg_perio_corpo.oid_peri%TYPE;
  BEGIN
    /* Obteniendo id de marca */
    SELECT a.oid_peri
      INTO ln_id_periodo
      FROM seg_perio_corpo a
     WHERE a.cod_peri = pscodperiodo;
    RETURN ln_id_periodo;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Marca respectivo con Codigo: ' ||
                      ln_id_periodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PERIO: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PERIO: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_perio;

  /**************************************************************************
  Descripcion        : Devuelve oId de cra_Periodo
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_oid_cra_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo NUMBER;
    ln_id         NUMBER;
  BEGIN
  
    ln_id := gen_fn_devuelve_id_perio(pscodperiodo, TRUE);
  
    SELECT a.oid_peri
      INTO ln_id_periodo
      FROM cra_perio a
     WHERE a.peri_oid_peri = ln_id;
  
    RETURN ln_id_periodo;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Marca respectivo con Codigo: ' ||
                      ln_id_periodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_OID_CRA_PERIO: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_OID_CRA_PERIO: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_oid_cra_perio;

  /**************************************************************************
  Descripcion        : Devuelve oId de MATRIZ_FACT_CABEC
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_matr_fact_cab
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo NUMBER;
    ln_id         NUMBER;
  BEGIN
    ln_id := gen_fn_devuelve_oid_cra_perio(pscodperiodo, TRUE);
    /* Obteniendo id de marca */
    SELECT a.oid_cabe
      INTO ln_id_periodo
      FROM pre_matri_factu_cabec a
     WHERE a.perd_oid_peri = ln_id;
  
    RETURN ln_id_periodo;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Marca respectivo con Codigo: ' ||
                      ln_id_periodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_MATR_FACT_CAB: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_MATR_FACT_CAB: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_dev_oid_matr_fact_cab;

  /**************************************************************************
  Descripcion        : Devuelve oId de MATR_FACT
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO existe CAMPANHA 1: Existe CAMPANHA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_matr_fact(pscodperiodo VARCHAR2) RETURN NUMBER IS
    ln_id_periodo NUMBER;
    ln_id         NUMBER;
  BEGIN
    ln_id := gen_fn_dev_oid_matr_fact_cab(pscodperiodo, TRUE);
  
    SELECT COUNT(a.oid_matr_fact)
      INTO ln_id_periodo
      FROM pre_matri_factu a
     WHERE a.mfca_oid_cabe = ln_id;
  
    RETURN ln_id_periodo;
  
  END gen_fn_dev_oid_matr_fact;

  /**************************************************************************
  Descripcion        : Devuelve oId de OFERT_DETAL
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : -1 NO existe CODVENTA  Otor valor: Existe CODVENTA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_ofer_detal
  (
    pscodperiodo VARCHAR2,
    pscodventa   VARCHAR2
  ) RETURN NUMBER IS
    ln_id_oferta NUMBER;
    ln_id        NUMBER;
  BEGIN
    ln_id := gen_fn_dev_oid_matr_fact_cab(pscodperiodo, TRUE);
  
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT matriz.ofde_oid_deta_ofer
      INTO ln_id_oferta
      FROM pre_matri_factu matriz
     WHERE matriz.mfca_oid_cabe = ln_id
       AND EXISTS
     (SELECT det.oid_deta_ofer
              FROM pre_ofert_detal det
             WHERE det.oid_deta_ofer = matriz.ofde_oid_deta_ofer
               AND det.val_codi_vent = pscodventa);
    RETURN ln_id_oferta;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN - 1;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_MATR_FACT_CAB: ' ||
                                ls_sqlerrm);
      END IF;
    
  END gen_fn_dev_oid_ofer_detal;

  /**************************************************************************
  Descripcion        : Devuelve oId de OFERT_DETAL OPROTUNIDADES PRIVELEGE
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : -1 NO existe CODVENTA  Otor valor: Existe CODVENTA
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_ofer_detal_pri
  (
    pscodperiodo  VARCHAR2,
    pscodproducto VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_codventa     VARCHAR2(100);
    ln_id_cabec     NUMBER;
    ln_id_prod      NUMBER;
    ln_id_vari      NUMBER;
    ln_id_progfidel NUMBER;
  
  BEGIN
    ln_id_cabec     := gen_fn_dev_oid_matr_fact_cab(pscodperiodo, TRUE);
    ln_id_prod      := gen_fn_dev_oid_prod(pscodproducto, TRUE);
    ln_id_vari      := gen_fn_dev_oid_vari('04', TRUE); -- 04 es Q Oportunidades
    ln_id_progfidel := gen_fn_dev_oid_prog_fide('CP', TRUE); -- Club Privelege
  
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT det.val_codi_vent
      INTO ls_codventa
      FROM pre_ofert_detal det,
           pre_ofert       ofe
     WHERE ofe.oid_ofer = det.ofer_oid_ofer
       AND ofe.mfca_oid_cabe = ln_id_cabec
       AND det.prod_oid_prod = ln_id_prod
       AND det.vari_oid_vari = ln_id_vari
       AND EXISTS (SELECT po.oid_prod
              FROM mae_produ po
             WHERE po.oid_prod = det.prod_oid_prod
               AND po.prfi_oid_prog_fide = ln_id_progfidel)
       AND det.prfi_oid_prog_fide = ln_id_progfidel;
  
    RETURN ls_codventa;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_MATR_FACT_CAB: ' ||
                                ls_sqlerrm);
      END IF;
    
  END gen_fn_dev_oid_ofer_detal_pri;

  /**************************************************************************
  Descripcion        : Devuelve Id de PRe_VARiante
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodVariante :
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_vari
  (
    pscodvariante       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_vari NUMBER;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_vari
      INTO ln_id_vari
      FROM pre_varia a
     WHERE a.cod_vari = pscodvariante;
    RETURN ln_id_vari;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID GEN_FN_DEV_OID_VARI: ' ||
                      ln_id_vari;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_VARI: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_VARI: ' || ls_sqlerrm);
      END IF;
  END gen_fn_dev_oid_vari;

  /**************************************************************************
  Descripcion        : Devuelve Id de PRogFidelizacion
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             psCodVariante :
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_prog_fide
  (
    pscodprogfide       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_prog NUMBER;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_prog_fide
      INTO ln_id_prog
      FROM mae_progr_fidel a
     WHERE a.cod_prog_fide = pscodprogfide;
    RETURN ln_id_prog;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID GEN_FN_DEV_OID_PROG_FIDE: ' ||
                      ln_id_prog;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_PROG_FIDE: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_PROG_FIDE: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_dev_oid_prog_fide;

  /**************************************************************************
  Descripcion        : Devuelve Id de Pais
  Fecha Creacion     : 18/09/2006
  Parametros Entrada :
             vsCodPais : Codigo de Pais
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_pais
  (
    vscodpais           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_idpais seg_pais.oid_pais%TYPE;
  BEGIN
    /* Obteniendo id del pais */
    SELECT a.oid_pais
      INTO ln_idpais
      FROM seg_pais a
     WHERE a.cod_pais = vscodpais;
    RETURN ln_idpais;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Pais respectivo con Codigo: ' ||
                      vscodpais;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PAIS: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PAIS: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_pais;

  /**************************************************************************
  Descripcion        : Devuelve Id de Producto
  Fecha Creacion     : 25/09/2006
  Parametros Entrada :
             vsCodPais : Codigo de Canal
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_oid_prod
  (
    pscodprod           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_prod NUMBER;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_prod
      INTO ln_id_prod
      FROM mae_produ a
     WHERE a.cod_sap = pscodprod;
    RETURN ln_id_prod;
  
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID GEN_FN_DEV_OID_PROD: ' ||
                      ln_id_prod;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEV_OID_PROD: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CANAL: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_dev_oid_prod;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_IND_DIG
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 es NO Diigtable  1: Es Digitable
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_ind_dig(pnoiddetaofert NUMBER) RETURN NUMBER IS
    ln_id_oferta NUMBER;
    ln_id        NUMBER;
  BEGIN
    ln_id := pnoiddetaofert; --GEN_FN_DEV_OID_OFER_DETAL(psCodPeriodo, psCodVenta)  ;
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT COUNT(det.oid_deta_ofer)
      INTO ln_id_oferta
      FROM pre_ofert_detal det
     WHERE det.oid_deta_ofer = ln_id
       AND det.ind_digi = 0;
  
    RETURN ln_id_oferta;
  
  END gen_fn_dev_ofer_ind_dig;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_PREC_CATA
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 es PRecio Catalago = 0  1: PRecio Catalago > 0
             psCodPeriodo : Codigo de Periodo
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_prec_cata(pnoiddetaofert NUMBER) RETURN NUMBER IS
    ln_id_oferta NUMBER;
    ln_id        NUMBER;
  BEGIN
    ln_id := pnoiddetaofert; --GEN_FN_DEV_OID_OFER_DETAL(psCodPeriodo, psCodVenta)  ;
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT COUNT(det.oid_deta_ofer)
      INTO ln_id_oferta
      FROM pre_ofert_detal det
     WHERE det.oid_deta_ofer = ln_id
       AND det.imp_prec_cata > 0;
  
    RETURN ln_id_oferta;
  
  END gen_fn_dev_ofer_prec_cata;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_ESTR_INDIV
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO EStrategia Individual  1: SI EStrategia Individual
             pnOidDetaOfert : OId Detalle Oferta
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_estr_indiv(pnoiddetaofert NUMBER) RETURN NUMBER IS
    ln_id_oferta NUMBER;
    ln_id        NUMBER;
  BEGIN
    ln_id := pnoiddetaofert; --GEN_FN_DEV_OID_OFER_DETAL(psCodPeriodo, psCodVenta)  ;
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT COUNT(det.oid_deta_ofer)
      INTO ln_id_oferta
      FROM pre_ofert_detal det
     WHERE det.oid_deta_ofer = ln_id
       AND EXISTS (SELECT ofer.oid_ofer
              FROM pre_ofert ofer,
                   pre_estra est
             WHERE ofer.oid_ofer = det.ofer_oid_ofer
               AND ofer.coes_oid_estr = est.oid_estr
               AND est.cod_estr = '001');
    RETURN ln_id_oferta;
  
  END gen_fn_dev_ofer_estr_indiv;

  /**************************************************************************
  Descripcion        : Devuelve oId de GEN_FN_DEV_OFER_ESTR_COMP_FIJA
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO EStrategia COMP_FIJA  1: SI EStrategia COMP_FIJA
             pnOidDetaOfert : OId Detalle Oferta
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_dev_ofer_estr_comp_fija(pnoiddetaofert NUMBER)
    RETURN NUMBER IS
    ln_id_oferta NUMBER;
    ln_id        NUMBER;
  BEGIN
    ln_id := pnoiddetaofert; --GEN_FN_DEV_OID_OFER_DETAL(psCodPeriodo, psCodVenta)  ;
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT COUNT(det.oid_deta_ofer)
      INTO ln_id_oferta
      FROM pre_ofert_detal det
     WHERE det.oid_deta_ofer = ln_id
       AND EXISTS
     (SELECT ofer.oid_ofer
              FROM pre_ofert ofer,
                   pre_estra est
             WHERE ofer.oid_ofer = det.ofer_oid_ofer
               AND ofer.coes_oid_estr = est.oid_estr
               AND (est.cod_estr = '001' OR est.cod_estr = '002') -- compuetsa fija 006
            );
    RETURN ln_id_oferta;
  
  END gen_fn_dev_ofer_estr_comp_fija;

  /**************************************************************************
  Descripcion        : Existe oId de la tabla PED_COD_VENT_AGREG
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO Existe 1: Exitse
             psCodPeriodo : PEriodo
             psCodVenta:    Cod VEnta
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_exis_ped_cod_agreg
  (
    pscodigpais  VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodventa   VARCHAR2
  ) RETURN NUMBER IS
    ln_id NUMBER;
  BEGIN
    -- OID Detalle Oferta pre_ofert_Detal
    SELECT COUNT(cods.cod_vent)
      INTO ln_id
      FROM ped_cod_vent_agreg cods
     WHERE cods.cod_pais = pscodigpais
       AND cods.cod_peri = pscodperiodo
       AND cods.cod_vent = pscodventa;
  
    RETURN ln_id;
  
  END gen_fn_exis_ped_cod_agreg;

  /**************************************************************************
  Descripcion        : Existe oId de la tabla GEN_FN_EXIS_CUP_DESP_PROD
  Fecha Creacion     : 25/09/2006
  Parametros Entrada : 0 NO Existe 1: Exitse
             psCodPeriodo : PEriodo
             psCodVenta:    Cod VEnta
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_exis_cup_desp_prod
  (
    pscodigpais   VARCHAR2,
    pscodprograma VARCHAR2,
    pscodperiodo  VARCHAR2,
    pscodventa    VARCHAR2,
    pscodnivel    VARCHAR2
  ) RETURN NUMBER IS
    ln_id NUMBER;
  BEGIN
    SELECT COUNT(cods.cod_venta)
      INTO ln_id
      FROM cup_desp_prod cods
     WHERE cods.cod_pais = pscodigpais
       AND cods.cod_peri = pscodperiodo
       AND cods.cod_prog = pscodprograma
       AND cods.cod_venta = pscodventa
       AND cods.cod_nivel = pscodnivel;
  
    RETURN ln_id;
  
  END gen_fn_exis_cup_desp_prod;

  /************************************************************************/
  /* Descripcion    :ACtualiza Prioridad PED_COD_VENT_AGREG               */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_act_cod_areg_prior
  (
    pscodigpais  VARCHAR2,
    pscodperiodo VARCHAR2
  ) AS
    CURSOR curagreg IS
      SELECT cod.cod_pais,
             cod.cod_peri,
             cod.cod_vent,
             cod.val_unit,
             cod.des_prod,
             cod.cod_prod,
             cod.niv_prior,
             cod.sta_reg,
             cod.usu_digi,
             cod.fec_digi,
             cod.usu_modi,
             cod.fec_modi
        FROM ped_cod_vent_agreg cod
       WHERE cod.cod_pais = pscodigpais
         AND cod.cod_peri = pscodperiodo
         AND cod.sta_reg = '1'
       ORDER BY cod.val_unit ASC;
  
    i          BINARY_INTEGER := 1;
    row_cursor curagreg%ROWTYPE;
  
  BEGIN
  
    UPDATE ped_cod_vent_agreg agreg
       SET agreg.niv_prior = 0
     WHERE agreg.cod_pais = pscodigpais
       AND agreg.cod_peri = pscodperiodo;
  
    OPEN curagreg; -- open the cursor
  
    LOOP
      FETCH curagreg
        INTO row_cursor;
    
      EXIT WHEN curagreg%NOTFOUND; -- exit condition
    
      UPDATE ped_cod_vent_agreg ag
         SET ag.niv_prior = i
       WHERE ag.cod_pais = row_cursor.cod_pais
         AND ag.cod_peri = row_cursor.cod_peri
         AND ag.cod_vent = row_cursor.cod_vent;
    
      i := i + 1;
    
    END LOOP;
    CLOSE curagreg;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACT_COD_AREG_PRIOR: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_act_cod_areg_prior;

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic para UPDATE Prog Fact              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_fec_soli
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN DATE IS
    ld_fec DATE;
  BEGIN
    SELECT MAX(cons.fec_soli)
      INTO ld_fec
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND cons.ind_ocs_proc = '1';
  
    RETURN ld_fec;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
    
  END gen_fn_dev_fec_soli;

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic para UPDATE Prog Fact              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_num_lote
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2
  ) RETURN VARCHAR2 IS
    ld_fec VARCHAR2(10);
  BEGIN
    SELECT MAX(cons.num_lote)
      INTO ld_fec
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND cons.ind_ocs_proc = '1';
  
    RETURN ld_fec;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
    
  END gen_fn_dev_num_lote;

  /************************************************************************/
  /* Descripcion    :Devuelve Fecha Solic GEN_FN_DEV_FECSOL_MTO_MIN             */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_fecsol_mto_min
  (
    pscodpais    VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodcliente VARCHAR2,
    psfechafact  DATE
  ) RETURN DATE IS
    ld_fec DATE;
  BEGIN
    SELECT cons.fec_soli
      INTO ld_fec
      FROM int_solic_conso_cabec cons
     WHERE cons.cod_pais = pscodpais
       AND cons.cod_peri = pscodperiodo
       AND cons.cod_clie = pscodcliente
       AND cons.fec_prog_fact = psfechafact;
  
    RETURN ld_fec;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
    
  END gen_fn_dev_fecsol_mto_min;

  /************************************************************************
   Descripcion :Actualiza IND GP2 OCR_PR_ACTU_INDI_GP2              
   Autor       : Marco Silva                                         
   Fecha       : 04/11/2006                                          
  ************************************************************************/
  PROCEDURE ocr_pr_actu_indi_gp2
  (
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    p_fechafact     IN VARCHAR2
  ) AS
  
    lsoid_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi sto_param_gener_occrr.val_param%TYPE;
  
    CURSOR curupdpedcabec(vnoidperiodo NUMBER) IS
      SELECT ped.clie_oid_clie      cliente,
             ped.oid_soli_cabe      oidcabec,
             ped.soca_oid_soli_cabe oidcons
        FROM ped_solic_cabec ped
       WHERE ped.perd_oid_peri = vnoidperiodo
         AND ped.fec_fact >= to_date(p_fechafact, 'DD/MM/YYYY')
         AND ped.grpr_oid_grup_proc = 5
         AND EXISTS
       (SELECT pts.oid_tipo_soli
                FROM ped_tipo_solic_pais ptsp,
                     ped_tipo_solic      pts
               WHERE ptsp.oid_tipo_soli_pais = ped.tspa_oid_tipo_soli_pais
                 AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 AND pts.cod_tipo_soli = 'SOC')
         AND ped.ind_oc = 1;
  
    TYPE t_clie_oid_clie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_oid_soli_cons IS TABLE OF ped_solic_cabec.soca_oid_soli_cabe%TYPE;
  
    v_clie_oid_clie t_clie_oid_clie;
    v_oid_soli_cabe t_oid_soli_cabe;
    v_oid_soli_cons t_oid_soli_cons;
  
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
  
    lnidperiodo cra_perio.oid_peri%TYPE;
  
    lsprol VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                               'STO_PROL');
  
    lsactuatelcob VARCHAR2(1) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                          'COB_ACTUA_TELEF'),
                                     'N');
  
    lsempnuevas VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                    'EMP_VINCU_NUEVAS');
  
  BEGIN
  
    lsoid_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                        'STO_MTMI_OID_CLAS');
  
    lsoid_tipo_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                             'STO_MTMA_OID_TICLAS');
  
    lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(p_codigoperiodo);
  
    OPEN curupdpedcabec(lnidperiodo);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curupdpedcabec BULK COLLECT
        INTO v_clie_oid_clie,
             v_oid_soli_cabe,
             v_oid_soli_cons LIMIT rows;
      EXIT WHEN v_row_count = curupdpedcabec%ROWCOUNT;
      v_row_count := curupdpedcabec%ROWCOUNT;
      FOR i IN v_clie_oid_clie.first .. v_clie_oid_clie.last
      LOOP
      
        UPDATE int_solic_conso_cabec consol
           SET consol.ind_proc_gp2       = 1,
               consol.val_tota_paga_prom =
               (SELECT SUM(val_prec_neto_tota_loca)
                  FROM ped_solic_posic,
                       pre_ofert_detal,
                       pre_tipo_ofert
                 WHERE soca_oid_soli_cabe = consol.soca_oid_soli_cabe_refe
                   AND ofde_oid_deta_ofer = oid_deta_ofer
                   AND tofe_oid_tipo_ofer = oid_tipo_ofer
                   AND val_form_vent = 1),
               fec_modi                  = SYSDATE
         WHERE consol.soca_oid_soli_cabe_refe = v_oid_soli_cabe(i);
      
        DELETE FROM mae_clien_clasi a
         WHERE a.clas_oid_clas = lsoid_clasi
           AND a.tccl_oid_tipo_clasi = lsoid_tipo_clasi
           AND a.ctsu_oid_clie_tipo_subt IN
               (SELECT oid_clie_tipo_subt
                  FROM mae_clien_tipo_subti
                 WHERE clie_oid_clie = v_clie_oid_clie(i));
      
        ped_pkg_cuadr_ofert.ped_pr_estadist(p_codigopais,
                                            v_oid_soli_cons(i));
      
      END LOOP;
    
    END LOOP;
    CLOSE curupdpedcabec;
  
    evi_pkg_ejecu_virtu.evi_pr_carga_resum_pre_factu;
  
    UPDATE ped_solic_posic
       SET espo_oid_esta_posi = 1
     WHERE oid_soli_posi IN
           (SELECT b.sopo_oid_soli_posi
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc = 5
               AND a.fec_fact = (SELECT z.fec_proc
                                   FROM bas_ctrl_fact z
                                  WHERE z.ind_camp_act = 1
                                    AND z.sta_camp = 0)
               AND a.tspa_oid_tipo_soli_pais =
                   (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
                      FROM ped_tipo_solic_pais a,
                           ped_tipo_solic      b
                     WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
                       AND b.cod_tipo_soli = 'SOC'
                       AND b.ind_cons = 0
                       AND b.ind_soli_nega = 0)
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND b.tpos_oid_tipo_posi = 2
               AND b.stpo_oid_subt_posi = 2);
  
    UPDATE gen_i18n_sicc_pais
       SET val_i18n = REPLACE(val_i18n, '$', '')
     WHERE attr_enti = 'MAE_PRODU'
       AND instr(val_i18n, '$') > 0;
  
    UPDATE ped_solic_cabec a
       SET a.ind_inte_lari_gene = 1
     WHERE nvl(a.ind_inte_lari_gene, 0) = 0
       AND a.perd_oid_peri = (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT tspa_oid_tipo_soli_pais
              FROM int_lar_tipo_solici_pedido_dis);
  
    IF nvl(lsprol, 'N') = 'S' THEN
    
      UPDATE ped_solic_cabec a
         SET a.esso_oid_esta_soli = 8
       WHERE a.ind_rece_onli = 1
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
         AND EXISTS (SELECT 1
                FROM fac_contr_cierr
               WHERE perd_oid_peri = a.perd_oid_peri
                 AND zzon_oid_zona = a.zzon_oid_zona);
    
      UPDATE int_solic_conso_cabec a
         SET a.ind_vali_prol      = 0,
             a.ind_vali_prol_anul = 1
       WHERE a.ind_vali_prol = 1
         AND nvl(a.ind_ocs_proc, 0) = 0;
    
    END IF;
  
    -- Genera los vinculos de empresarias a las consultoras
    -- nuevas y reactivadas
    IF nvl(lsempnuevas, 'N') = 'S' THEN
      BEGIN
        emp_pkg_proce.emp_pr_proce_vincu_nueva_react('001', 'SICC');
      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;
    END IF;
  
    ped_pkg_cuadr_ofert.ped_pr_actua_posic_mm(p_codigopais,
                                              lnidperiodo,
                                              to_date(p_fechafact,
                                                      'dd/mm/yyyy'));
    gen_pkg_gener.gen_pr_actua_estad_tabla;
    ped_pkg_cuadr_ofert.ped_pr_actua_pedid_web(p_codigopais,
                                               lnidperiodo,
                                               to_date(p_fechafact,
                                                       'dd/mm/yyyy'));
    fac_pkg_proc.fac_pr_genera_pup(p_codigoperiodo, p_fechafact);
  
    UPDATE int_solic_conso_cabec a
       SET a.ind_rece_ocr = 1
     WHERE a.ind_rece_ocr = 0
       AND EXISTS (SELECT 1
              FROM int_solic_conso_detal
             WHERE sec_nume_docu_cabe = a.sec_nume_docu
               AND ind_rece_ocr = 1);
  
    UPDATE int_solic_conso_cabec a
       SET a.ind_rece_web = 1
     WHERE a.ind_rece_web = 0
       AND EXISTS (SELECT 1
              FROM int_solic_conso_detal
             WHERE sec_nume_docu_cabe = a.sec_nume_docu
               AND ind_rece_web = 1);
  
    UPDATE int_solic_conso_cabec a
       SET a.ind_rece_dd = 1
     WHERE a.ind_rece_dd = 0
       AND EXISTS (SELECT 1
              FROM int_solic_conso_detal
             WHERE sec_nume_docu_cabe = a.sec_nume_docu
               AND ind_rece_dd = 1);
  
    UPDATE int_solic_conso_cabec a
       SET a.ind_rece_digi = 1
     WHERE a.ind_rece_digi = 0
       AND EXISTS (SELECT 1
              FROM int_solic_conso_detal
             WHERE sec_nume_docu_cabe = a.sec_nume_docu
               AND ind_rece_digi = 1);
  
    /*Borrando tablas temporales de Procesos diarios*/
    EXECUTE IMMEDIATE ('truncate table INT_SOLIC_CABEC');
    EXECUTE IMMEDIATE ('truncate table INT_SOLIC_POSIC');
    EXECUTE IMMEDIATE ('truncate table IVR_TMP_RECEP_PEDID');
    EXECUTE IMMEDIATE ('truncate table INT_OCR_CABEC_SERVI_POSTV');
    EXECUTE IMMEDIATE ('truncate table INT_OCR_DETAL_SERVI_POSTV');
  
    IF lsactuatelcob = 'S' THEN
    
      UPDATE cob_detal_asign_carte x
         SET num_tele_fijo =
             (SELECT val_text_comu
                FROM mae_clien_comun y
               WHERE y.clie_oid_clie = x.oid_clie
                 AND y.ticm_oid_tipo_comu = 1)
       WHERE fec_cier >= trunc(SYSDATE)
         AND (nvl(num_tele_fijo, 'XX') <>
             nvl((SELECT val_text_comu
                    FROM mae_clien_comun y
                   WHERE y.clie_oid_clie = x.oid_clie
                     AND y.ticm_oid_tipo_comu = 1),
                  'XX'));
    
      UPDATE cob_detal_asign_carte x
         SET num_tele_trab =
             (SELECT val_text_comu
                FROM mae_clien_comun y
               WHERE y.clie_oid_clie = x.oid_clie
                 AND y.ticm_oid_tipo_comu = 7)
       WHERE fec_cier >= trunc(SYSDATE)
         AND (nvl(num_tele_trab, 'XX') <>
             nvl((SELECT val_text_comu
                    FROM mae_clien_comun y
                   WHERE y.clie_oid_clie = x.oid_clie
                     AND y.ticm_oid_tipo_comu = 7),
                  'XX'));
    
      UPDATE cob_detal_asign_carte x
         SET num_tele_movi =
             (SELECT val_text_comu
                FROM mae_clien_comun y
               WHERE y.clie_oid_clie = x.oid_clie
                 AND y.ticm_oid_tipo_comu = 6)
       WHERE fec_cier >= trunc(SYSDATE)
         AND (nvl(num_tele_movi, 'XX') <>
             nvl((SELECT val_text_comu
                    FROM mae_clien_comun y
                   WHERE y.clie_oid_clie = x.oid_clie
                     AND y.ticm_oid_tipo_comu = 6),
                  'XX'));
    
    END IF;
  
    --Se actualiza informacion para SOA
    soa_pkg_proce.soa_pr_cierr_factu;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACTU_INDI_GP2: ' || ls_sqlerrm);
    
  END ocr_pr_actu_indi_gp2;

  /************************************************************************
   Descripcion    :Proceoo Monto Minimo Maximo
   Autor          :Jose Cairampoma
   Fecha          :14/02/2012
  ************************************************************************/
  PROCEDURE ocr_pr_comp_mont_minimo
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    psfechafact     IN VARCHAR2,
    psusuario       IN VARCHAR2
  ) AS
    CURSOR curpedidos(vnoidperiodo NUMBER) IS
      SELECT t.oid_soli_cabe
        FROM ped_solic_cabec       t,
             int_solic_conso_cabec c
       WHERE c.soca_oid_soli_cabe_refe = t.oid_soli_cabe
         AND t.perd_oid_peri = vnoidperiodo
         AND t.grpr_oid_grup_proc IN (1, 3)
         AND t.fec_prog_fact >= to_date(psfechafact, 'dd/mm/yyyy')
         --and (t.esso_oid_esta_soli in (2,3) or t.espe_oid_esta_pedi in (2,3))
         ;
  
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
  
    v_oid_soli_cabe t_oid_soli_cabe;
  
    rows        NATURAL := 1000;
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
  
    lnoidperiodo cra_perio.oid_peri%TYPE;
  
    lsejecuta VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_EJEC_PROC_ESPEC'),
                                  'S');
  
    lsmontoga VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_MONTO_GA');
  
    lsmontosobre VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_MONTO_SOBRE');
  
  BEGIN
  
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  
    IF lsejecuta = 'N' THEN
      RETURN;
    END IF;
  
    OPEN curpedidos(lnoidperiodo);
    LOOP
      FETCH curpedidos BULK COLLECT
        INTO v_oid_soli_cabe LIMIT rows;
      EXIT WHEN v_row_count = curpedidos%ROWCOUNT;
      v_row_count := curpedidos%ROWCOUNT;
    
      FOR i IN v_oid_soli_cabe.first .. v_oid_soli_cabe.last
      LOOP
        ocr_pr_proce_espec(pscodigopais, v_oid_soli_cabe(i), psusuario);
      
        IF lsmontoga IS NOT NULL THEN
          UPDATE ped_solic_cabec a
             SET a.val_tota_gast_admi = lsmontoga
           WHERE oid_soli_cabe = v_oid_soli_cabe(i);
        END IF;
      
        IF lsmontosobre IS NOT NULL THEN
          UPDATE ped_solic_cabec a
             SET a.val_tota_gast_admi2 = lsmontosobre
           WHERE oid_soli_cabe = v_oid_soli_cabe(i);
        END IF;
      
      END LOOP;
    
    END LOOP;
    CLOSE curpedidos;
    sto_pkg_proce_gener.sto_pr_rever_pedgp_error(lnoidperiodo, psfechafact);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_COMP_MONT_MINIMO: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_comp_mont_minimo;

  /******************************************************************/
  /*                   GEN_FN_COD_CONS_PRIV                               */
  /******************************************************************/

  FUNCTION gen_fn_cod_cons_priv
  (
    pscodigopais    IN VARCHAR2,
    pscodigotarjeta IN VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
  
    SELECT tar.cod_cons
      INTO ls_valor
      FROM pri_tarje tar
     WHERE tar.pais_cod_pais = pscodigopais
       AND tar.num_tarj = pscodigotarjeta;
  
    RETURN ls_valor;
  
  END gen_fn_cod_cons_priv;

  /*************************************************************************
   Descripcion    :Recepciona OCR_PR_COMP_OPORT_PRIV
   Autor         : Marco Silva
   Fecha      : 04/11/2006
  ****************************************************************************
  Proyecto Optimizaciòn  c1
  
  Descripcion       : Recepciona OCR_PR_COMP_OPORT_PRIV
  Fecha Modicicacion: 14/04/2011  16:30
  Autor             : Jorge Angulo   JANGULO
  
  ***************************************************************************/

  PROCEDURE ocr_pr_comp_oport_priv
  (
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    p_fechafact     IN VARCHAR2,
    pscodigousuario IN VARCHAR2
  ) AS
    CURSOR curinsoportun IS
      SELECT t.pais_cod_pais pais,
             p_codigoperiodo periodo,
             ocr_solic_pedidos.gen_fn_cod_cons_priv(t.pais_cod_pais,
                                                    t.tarj_num_tarj) cliente,
             to_date(p_fechafact, 'dd/mm/yyyy') fecsolic,
             ocr_solic_pedidos.gen_fn_dev_oid_ofer_detal_pri(p_codigoperiodo,
                                                             t.cod_prod) codventa,
             t.can_prem cantidad,
             t.tarj_num_tarj tarj,
             t.cor_prem cor
        FROM pri_premi t
       WHERE t.pais_cod_pais = p_codigopais
         AND t.ind_prem = 'Q'
         AND t.sta_prem <> 'F';
  
    TYPE t_cod_venta IS TABLE OF int_solic_posic.cod_prod%TYPE;
    TYPE t_cantidad IS TABLE OF NUMBER;
  
    TYPE t_tarjeta IS TABLE OF pri_premi.tarj_num_tarj%TYPE;
    TYPE t_core IS TABLE OF pri_premi.cor_prem%TYPE;
  
    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
  
    v_cod_venta t_cod_venta;
    n_cantidad  t_cantidad;
  
    n_tarjeta t_tarjeta;
    n_core    t_core;
  
    v_cod_pais t_cod_pais;
    v_cod_peri t_cod_peri;
    v_cod_clie t_cod_clie;
    v_fec_soli t_fec_soli;
  
    ln_seq NUMBER;
    --c1  rows NATURAL        := 1000;   -- Number of rows to process at a time
    rows         NATURAL := 10000; --c1
    i            BINARY_INTEGER := 0;
    v_row_count  NUMBER := 0;
    psnumerolote sto_tipo_docum_digit.num_lote%TYPE;
  
  BEGIN
  
    sto_pkg_gener.sto_pr_devue_lote(p_codigopais, 'OCC', psnumerolote);
  
    OPEN curinsoportun;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsoportun BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_fec_soli,
             v_cod_venta,
             n_cantidad,
             n_tarjeta,
             n_core LIMIT rows;
      EXIT WHEN v_row_count = curinsoportun%ROWCOUNT;
      v_row_count := curinsoportun%ROWCOUNT;
    
      FOR i IN v_cod_clie.first .. v_cod_clie.last
      LOOP
        -- Manipumate data in the memory table...
        --        dbms_output.put_line('linea ' || v_ind_erro_mtmi(i) || 'otro ' || n_monto(i) || 'cliente' || v_cod_clie(i) );
        IF (v_cod_venta(i) = '0') THEN
          UPDATE pri_premi pre
             SET pre.sta_prem = 'Z'
           WHERE pre.tarj_num_tarj = n_tarjeta(i)
             AND pre.pais_cod_pais = v_cod_pais(i)
             AND pre.cor_prem = n_core(i);
        ELSE
          BEGIN
            SELECT seq_solic_pos.nextval INTO ln_seq FROM dual;
            INSERT INTO int_solic_posic
              (oid_posic,
               cod_pais,
               cam_soli,
               cod_clie,
               tip_posi,
               cod_prod,
               uni_dema,
               sta_proc,
               num_lote,
               ind_proc,
               num_lote_sto)
            VALUES
              (ln_seq,
               v_cod_pais(i),
               v_cod_peri(i),
               v_cod_clie(i),
               'OC',
               v_cod_venta(i),
               n_cantidad(i),
               'O', -- las q fueron agragadas por oprotunidades es 'o'
               (SELECT bas.num_lote
                  FROM bas_ctrl_fact bas
                 WHERE bas.cod_pais = v_cod_pais(i)
                   AND bas.cod_peri = v_cod_peri(i)),
               'P', --Se coloca el tipo OCR
               psnumerolote);
          END;
        END IF;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinsoportun;
  
    ocr_pr_solic_conso_detal(p_codigopais,
                             'OCD',
                             p_codigoperiodo,
                             pscodigousuario,
                             psnumerolote);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_COMP_OPORT_PRIV: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_comp_oport_priv;

  /************************************************************************/
  /* Descripcion    :Recepciona OCR_PR_IND_MONT_MAXIMO              */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  /**************************************************************************
  Descripcion        : Devuelve Id de Cliente
  Fecha Creacion     : 30/01/2007
  Parametros Entrada :
             vsCodCliente : Codigo de Cliente
  Autor              : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cliente(vscodcliente VARCHAR2) RETURN NUMBER IS
    ln_id_cliente mae_clien.oid_clie%TYPE;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_clie
      INTO ln_id_cliente
      FROM mae_clien a
     WHERE a.cod_clie = vscodcliente;
    RETURN ln_id_cliente;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN - 1;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CLIENTE: ' ||
                                ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_cliente;

  /************************************************************************/
  /* Descripcion    :Migra hacia el Historico OCS  CABECERA               */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_hist_solic_conso_cabec
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) AS
    CURSOR curinsconsolcab IS
      SELECT cab.*
      
        FROM int_solic_conso_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_conso_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t; -- In-memory table
  
    TYPE t_rowid IS TABLE OF ROWID INDEX BY BINARY_INTEGER;
  
    v_rowid t_rowid;
  
    rows  NATURAL := 1000; -- Number of rows to process at a time
    i     BINARY_INTEGER := 0;
    total NUMBER := 0;
  
  BEGIN
  
    OPEN curinsconsolcab;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcab BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO ped_histo_solic_conso_cabec VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcab;
  
    SELECT COUNT(ca.cod_pais)
      INTO total
      FROM int_solic_conso_cabec ca
     WHERE ca.cod_pais = pscodigopais
       AND ca.cod_peri <> pscodigoperiodo;
  
    IF (total > 0) THEN
      BEGIN
      
        SELECT ROWID BULK COLLECT
          INTO v_rowid
          FROM int_solic_conso_cabec ca
         WHERE ca.cod_pais = pscodigopais
           AND ca.cod_peri = pscodigoperiodo
           FOR UPDATE NOWAIT;
      
        IF v_rowid.count > 0 THEN
          FORALL i IN v_rowid.first .. v_rowid.last
            DELETE FROM int_solic_conso_cabec cab
             WHERE cab.rowid = v_rowid(i);
        END IF;
      
      END;
    ELSE
      gen_pkg_gener.gen_pr_ejec_sql_dinam('truncate table int_solic_conso_cabec');
    
    END IF;
  
  END ocr_pr_hist_solic_conso_cabec;

  /************************************************************************/
  /* Descripcion    :Migra hacia el Historico OCS  DETALLE               */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2006                                          */
  /************************************************************************/

  PROCEDURE ocr_pr_hist_solic_conso_detal
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) AS
    CURSOR curinsconsolcab IS
      SELECT cab.*
      
        FROM int_solic_conso_detal cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_conso_detal%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t; -- In-memory table
  
    TYPE t_rowid IS TABLE OF ROWID INDEX BY BINARY_INTEGER;
    v_rowid t_rowid;
  
    rows  NATURAL := 1000; -- Number of rows to process at a time
    i     BINARY_INTEGER := 0;
    total NUMBER := 0;
  
  BEGIN
  
    OPEN curinsconsolcab;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcab BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      -- Bulk bind of data in memory table...
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO ped_histo_solic_conso_detal VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcab;
  
    SELECT COUNT(ca.cod_pais)
      INTO total
      FROM int_solic_conso_detal ca
     WHERE ca.cod_pais = pscodigopais
       AND ca.cod_peri <> pscodigoperiodo;
  
    IF (total > 0) THEN
      BEGIN
      
        SELECT ROWID BULK COLLECT
          INTO v_rowid
          FROM int_solic_conso_detal ca
         WHERE ca.cod_pais = pscodigopais
           AND ca.cod_peri = pscodigoperiodo
           FOR UPDATE NOWAIT;
      
        IF v_rowid.count > 0 THEN
          FORALL i IN v_rowid.first .. v_rowid.last
            DELETE FROM int_solic_conso_detal cab
             WHERE cab.rowid = v_rowid(i);
        END IF;
      
      END;
    ELSE
      gen_pkg_gener.gen_pr_ejec_sql_dinam('truncate table int_solic_conso_detal');
    
    END IF;
  
  END ocr_pr_hist_solic_conso_detal;

  /********************************************************************
  * Descripcion   : Genera un codigo de venta Ficticio, para generar *
  *                 el Reporte Listado de productos mas reclamados   *
  * Autor         : José A. Cairampoma Granados                      *                     *
  * Fecha         : 05/11/2007                                       *
  ********************************************************************/

  FUNCTION gen_fn_cod_venta_fict
  (
    psprdoid   IN NUMBER,
    psconcurso IN NUMBER,
    psnumprem  IN NUMBER
  ) RETURN VARCHAR2 IS
    ls_valor VARCHAR2(100);
  BEGIN
    SELECT i.cod_vent_fict
      INTO ls_valor
      FROM inc_artic_lote        i,
           inc_lote_premi_artic  l,
           inc_premi_artic       p,
           inc_param_nivel_premi n,
           inc_param_gener_premi g,
           inc_concu_param_gener c
     WHERE i.prod_oid_prod = psprdoid
       AND i.lopa_oid_lote_prem_arti = l.oid_lote_prem_arti
       AND l.prar_oid_prem_arti = p.oid_prem_arti
       AND l.num_prem = psnumprem
       AND p.panp_oid_para_nive_prem = n.oid_para_nive_prem
       AND n.pagp_oid_para_gene_prem = g.oid_para_gene_prem
       AND g.copa_oid_para_gral = c.oid_para_gral
       AND c.oid_para_gral = psconcurso;
    RETURN ls_valor;
  END gen_fn_cod_venta_fict;

  /***************************************************************************
  Descripcion          : Funcion que verifica SI la consultora es Nueva
  Fecha Creacion       : 24/01/2008
  Autor             : Carlos Bazalar
  Parametros        :
              pnIdCliente    Id del Cliente
  Return
            1: Es Consultora Nueva
            0: No es Consultora Nueva
  ***************************************************************************/
  FUNCTION ocr_fn_verif_consu_nueva(pnidcliente NUMBER) RETURN NUMBER IS
    lncodigo NUMBER;
  BEGIN
    SELECT a.clie_oid_clie
      INTO lncodigo
      FROM mae_clien_datos_adici a,
           mae_estat_clien       b
     WHERE a.clie_oid_clie = pnidcliente
       AND (b.cod_esta_clie = '01' OR b.cod_esta_clie = '07')
       AND b.oid_esta_clie = a.esta_oid_esta_clie;
    RETURN 1;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_FN_VERIF_CONSU_NUEVA: ' ||
                              ls_sqlerrm);
  END ocr_fn_verif_consu_nueva;
  /***************************************************************************
  Descripcion          : Funcion que devuelve la descripcion del producto
  Fecha Creacion       : 12/08/2008
  Autor             : Cristhian Roman
  Parametros        :
              psCodigoVenta,    psCodigoPeriodo
  Return
            descripcion del producto
  ***************************************************************************/
  FUNCTION gen_fn_dev_descr_produ
  (
    pscodigoventa   VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2
  
   IS
    lsdescripcion VARCHAR2(100);
    ln_id         NUMBER;
    ln_idproducto NUMBER;
  BEGIN
    ln_id := gen_fn_dev_oid_matr_fact_cab(pscodigoperiodo, TRUE);
  
    SELECT det.prod_oid_prod
      INTO ln_idproducto
      FROM pre_matri_factu matriz,
           pre_ofert_detal det
     WHERE matriz.mfca_oid_cabe = ln_id
       AND det.oid_deta_ofer = matriz.ofde_oid_deta_ofer
       AND det.val_codi_vent = pscodigoventa;
  
    lsdescripcion := gen_fn_desc_prod(ln_idproducto);
  
    RETURN lsdescripcion;
  END gen_fn_dev_descr_produ;

  /***************************************************************************
  Descripcion       : Actualiza la tabla de Matriz de Facturacion
  Fecha Creacion    : 17/11/2008
    Autor              : Jose Cairampoma
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoMarca    : Codigo Marca
              psCodigoCanal    : Codigo Canal
  ***************************************************************************/

  PROCEDURE ocr_pr_actua_matri_factu
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2
  ) IS
  
    ----------------
    CURSOR curprematri IS
      SELECT pais.cod_pais AS codpais,
             peri.cod_peri AS codperi,
             (SELECT DISTINCT cupo.cod_cupon
                FROM cup_equiv_matr cupo
               WHERE cupo.cod_pais = pais.cod_pais
                 AND cupo.cod_peri = peri.cod_peri
                 AND cupo.cod_venta = det.val_codi_vent) AS codcupon,
             '  ' AS codgrup,
             prod.cod_sap AS codprod,
             ofer.cod_tipo_ofer AS codtofe,
             round(det.imp_prec_cata / det.val_fact_repe, 2) AS precprod,
             prod.val_prec_cont AS preccont,
             det.num_pagi_cata AS pagcata,
             det.val_fact_repe AS factrep,
             (SELECT ptsp.num_unid_alar
                FROM ped_tipo_solic_pais ptsp,
                     ped_tipo_solic      pts
               WHERE ptsp.pais_oid_pais = pais.oid_pais
                 AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 AND pts.cod_tipo_soli = 'SOC') AS unidmax,
             ofer.ind_esta AS indestad,
             (SELECT grup.cod_grup_desc
                FROM mae_grupo_descu grup,
                     mae_negoc       nego,
                     mae_unida_negoc unid,
                     seg_marca_produ mpro
               WHERE grup.pais_cod_pais = pais.cod_pais
                 AND grup.cod_grup_arti = TRIM(prod.val_grup_arti)
                 AND nego.pais_oid_pais = pais.oid_pais
                 AND nego.oid_nego = prod.nego_oid_nego
                 AND unid.pais_oid_pais = pais.oid_pais
                 AND grup.cod_unid_nego = unid.cod_unid_nego
                 AND unid.oid_unid_nego = prod.uneg_oid_unid_nego
                 AND grup.cod_marc_prod = mpro.cod_marc_prod
                 AND mpro.oid_marc_prod = prod.mapr_oid_marc_prod) AS grupodesc,
             ofer.ind_comi AS indcomi,
             (SELECT nvl((SELECT (tof.ind_comi - 1)
                           FROM pre_tipo_ofert tof
                          WHERE tof.oid_tipo_ofer = ofer.oid_tipo_ofer
                            AND tof.cod_tipo_ofer IN
                                ('21', '23', '33', '53')
                            AND tof.ind_comi = 1),
                         1)
                FROM dual) AS indcomiadic,
             ocr_solic_pedidos.gen_fn_desc_prod(det.prod_oid_prod) AS desprod,
             (SELECT cata.cod_cata
                FROM pre_catal cata,
                     pre_ofert ofe
               WHERE cata.oid_cata = ofe.ocat_oid_cata
                 AND cata.pais_oid_pais = pais.oid_pais
                 AND det.ofer_oid_ofer = ofe.oid_ofer) AS codcata,
             1 AS indigi, -- Para que sean considerados por BizTalk y sean enviados a MyEBEL - PRE_OFERT_DETAL.IND_DIGI
             0 AS indmarclbel, -- Estos productos no son considerados para LBel - IND_MARC_LBEL
             mprod.cod_marc_prod
        FROM seg_pais        pais,
             pre_matri_factu matriz,
             pre_ofert_detal det,
             pre_tipo_ofert  ofer,
             mae_produ       prod,
             seg_marca_produ mprod,
             seg_perio_corpo peri
       WHERE pais.cod_pais = pscodigopais
         AND pais.oid_pais = prod.pais_oid_pais
         AND matriz.ofde_oid_deta_ofer = det.oid_deta_ofer
         AND det.tofe_oid_tipo_ofer = ofer.oid_tipo_ofer
         AND det.prod_oid_prod = prod.oid_prod
         AND prod.mapr_oid_marc_prod = mprod.oid_marc_prod
         AND EXISTS (SELECT NULL
                FROM cup_equiv_matr cupo
               WHERE cupo.cod_pais = pais.cod_pais
                 AND cupo.cod_peri = peri.cod_peri
                 AND cupo.cod_venta = det.val_codi_vent)
         AND EXISTS
       (SELECT cab.oid_cabe
                FROM pre_matri_factu_cabec cab
               WHERE EXISTS (SELECT NULL
                        FROM cra_perio       a,
                             seg_perio_corpo b,
                             seg_marca       marc,
                             seg_canal       cana
                       WHERE b.oid_peri = peri.oid_peri
                         AND a.peri_oid_peri = b.oid_peri
                         AND b.cod_peri IN
                             (SELECT a.cod_peri
                                FROM bas_ctrl_fact a
                               WHERE a.ind_camp_act = 1
                                 AND a.sta_camp = 0)
                         AND a.pais_oid_pais = pais.oid_pais
                         AND a.marc_oid_marc = marc.oid_marc
                         AND marc.cod_marc = pscodigomarca
                         AND a.cana_oid_cana = cana.oid_cana
                         AND cana.cod_cana = pscodigocanal
                         AND cab.perd_oid_peri = a.oid_peri)
                 AND matriz.mfca_oid_cabe = cab.oid_cabe);
  
    TYPE t_cod_pais IS TABLE OF ped_matri_factu.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF ped_matri_factu.cod_peri%TYPE;
    TYPE t_cod_vent IS TABLE OF ped_matri_factu.cod_vent%TYPE;
    TYPE t_cod_grup IS TABLE OF ped_matri_factu.cod_grup%TYPE;
    TYPE t_cod_prod IS TABLE OF ped_matri_factu.cod_prod%TYPE;
    TYPE t_cod_tofe IS TABLE OF ped_matri_factu.cod_tofe%TYPE;
    TYPE t_val_prec_prod IS TABLE OF ped_matri_factu.val_prec_prod%TYPE;
    TYPE t_val_prec_cont IS TABLE OF ped_matri_factu.val_prec_cont%TYPE;
    TYPE t_num_pag_cata IS TABLE OF ped_matri_factu.num_pag_cata%TYPE;
    TYPE t_val_fact_rep IS TABLE OF ped_matri_factu.val_fact_rep%TYPE;
    TYPE t_num_unid_alar IS TABLE OF ped_matri_factu.num_unid_alar%TYPE;
    TYPE t_ind_esta IS TABLE OF ped_matri_factu.ind_esta%TYPE;
    TYPE t_cod_grup_desc IS TABLE OF ped_matri_factu.cod_grup_desc%TYPE;
    TYPE t_ind_comi IS TABLE OF ped_matri_factu.ind_comi%TYPE;
    TYPE t_ind_comi_adic IS TABLE OF ped_matri_factu.ind_comi_adic%TYPE;
    TYPE t_des_prod IS TABLE OF ped_matri_factu.des_prod%TYPE;
    TYPE t_cod_cata IS TABLE OF ped_matri_factu.cod_cata%TYPE;
    TYPE t_ind_digi IS TABLE OF ped_matri_factu.ind_digi%TYPE;
    TYPE t_ind_marc_lbel IS TABLE OF ped_matri_factu.ind_marc_lbel%TYPE;
    TYPE t_cod_marc_prod IS TABLE OF ped_matri_factu.cod_marc_prod%TYPE;
  
    v_cod_pais      t_cod_pais;
    v_cod_peri      t_cod_peri;
    v_cod_vent      t_cod_vent;
    v_cod_grup      t_cod_grup;
    v_cod_prod      t_cod_prod;
    v_cod_tofe      t_cod_tofe;
    v_val_prec_prod t_val_prec_prod;
    v_val_prec_cont t_val_prec_cont;
    v_num_pag_cata  t_num_pag_cata;
    v_val_fact_rep  t_val_fact_rep;
    v_num_unid_alar t_num_unid_alar;
    v_ind_esta      t_ind_esta;
    v_cod_grup_desc t_cod_grup_desc;
    v_ind_comi      t_ind_comi;
    v_ind_comi_adic t_ind_comi_adic;
    v_des_prod      t_des_prod;
    v_cod_cata      t_cod_cata;
    v_ind_digi      t_ind_digi;
    v_ind_marc_lbel t_ind_marc_lbel;
    v_cod_marc_prod t_cod_marc_prod;
  
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    ----------------
  
  BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE ped_matri_factu';
    INSERT INTO ped_matri_factu
      (cod_pais,
       cod_peri,
       cod_vent,
       cod_grup,
       cod_prod,
       cod_tofe,
       val_prec_prod,
       val_prec_cont,
       num_pag_cata,
       val_fact_rep,
       num_unid_alar,
       ind_esta,
       cod_grup_desc,
       ind_comi,
       ind_comi_adic,
       des_prod,
       cod_cata,
       ind_digi,
       ind_marc_lbel,
       cod_marc_prod)
      SELECT pais.cod_pais codpais,
             peri.cod_peri codperi,
             det.val_codi_vent codvent,
             '  ' codgrup,
             prod.cod_sap codprod,
             ofer.cod_tipo_ofer codtofe,
             round(det.imp_prec_cata / det.val_fact_repe, 2) precprod,
             prod.val_prec_cont preccont,
             det.num_pagi_cata pagcata,
             det.val_fact_repe factrep,
             (SELECT ptsp.num_unid_alar
                FROM ped_tipo_solic_pais ptsp,
                     ped_tipo_solic      pts
               WHERE ptsp.pais_oid_pais = pais.oid_pais
                 AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 AND pts.cod_tipo_soli = 'SOC') unidmax,
             ofer.ind_esta indestad,
             (SELECT grup.cod_grup_desc
                FROM mae_grupo_descu grup,
                     mae_negoc       nego,
                     mae_unida_negoc unid,
                     seg_marca_produ mpro
               WHERE grup.pais_cod_pais = pais.cod_pais
                 AND grup.cod_grup_arti = TRIM(prod.val_grup_arti)
                 AND nego.pais_oid_pais = pais.oid_pais
                 AND nego.oid_nego = prod.nego_oid_nego
                 AND unid.pais_oid_pais = pais.oid_pais
                 AND grup.cod_unid_nego = unid.cod_unid_nego
                 AND unid.oid_unid_nego = prod.uneg_oid_unid_nego
                 AND grup.cod_marc_prod = mpro.cod_marc_prod
                 AND mpro.oid_marc_prod = prod.mapr_oid_marc_prod) AS grupodesc,
             ofer.ind_comi AS indcomi,
             (SELECT nvl((SELECT (tof.ind_comi - 1)
                           FROM pre_tipo_ofert tof
                          WHERE tof.oid_tipo_ofer = ofer.oid_tipo_ofer
                            AND tof.cod_tipo_ofer IN
                                ('21', '23', '33', '53')
                            AND tof.ind_comi = 1),
                         1)
                FROM dual) AS indcomiadic,
             ocr_solic_pedidos.gen_fn_desc_prod(det.prod_oid_prod) AS desprod,
             (SELECT cata.cod_cata
                FROM pre_catal cata,
                     pre_ofert ofe
               WHERE cata.oid_cata = ofe.ocat_oid_cata
                 AND cata.pais_oid_pais = pais.oid_pais
                 AND det.ofer_oid_ofer = ofe.oid_ofer) AS codcata,
             det.ind_digi AS indigi,
             (SELECT COUNT(DISTINCT pre_venta_exclu.ofer_oid_ofer)
                FROM pre_venta_exclu,
                     mae_tipo_clasi_clien,
                     mae_clasi
               WHERE det.ofer_oid_ofer = pre_venta_exclu.ofer_oid_ofer
                 AND pre_venta_exclu.clas_oid_clas = mae_clasi.oid_clas
                 AND pre_venta_exclu.tccl_oid_tipo_clas =
                     mae_tipo_clasi_clien.oid_tipo_clas
                 AND mae_tipo_clasi_clien.oid_tipo_clas =
                     mae_clasi.tccl_oid_tipo_clas
                 AND mae_clasi.cod_clas = '01'
                 AND mae_tipo_clasi_clien.cod_tipo_clas = '41') AS indmarclbel,
             mprod.cod_marc_prod
        FROM seg_pais        pais,
             pre_matri_factu matriz,
             pre_ofert_detal det,
             pre_tipo_ofert  ofer,
             mae_produ       prod,
             seg_marca_produ mprod,
             seg_perio_corpo peri
       WHERE pais.cod_pais = pscodigopais
         AND pais.oid_pais = prod.pais_oid_pais
         AND matriz.ofde_oid_deta_ofer = det.oid_deta_ofer
         AND det.tofe_oid_tipo_ofer = ofer.oid_tipo_ofer
         AND det.prod_oid_prod = prod.oid_prod
         AND prod.mapr_oid_marc_prod = mprod.oid_marc_prod
         AND det.ind_digi = 1
         AND EXISTS
       (SELECT cab.oid_cabe
                FROM pre_matri_factu_cabec cab
               WHERE EXISTS (SELECT NULL
                        FROM cra_perio       a,
                             seg_perio_corpo b,
                             seg_marca       marc,
                             seg_canal       cana
                       WHERE b.oid_peri = peri.oid_peri
                         AND a.peri_oid_peri = b.oid_peri
                         AND b.cod_peri IN
                             (SELECT a.cod_peri
                                FROM bas_ctrl_fact a
                               WHERE a.ind_camp_act = 1
                                 AND a.sta_camp = 0)
                         AND a.pais_oid_pais = pais.oid_pais
                         AND a.marc_oid_marc = marc.oid_marc
                         AND marc.cod_marc = pscodigomarca
                         AND a.cana_oid_cana = cana.oid_cana
                         AND cana.cod_cana = pscodigocanal
                         AND cab.perd_oid_peri = a.oid_peri)
                 AND matriz.mfca_oid_cabe = cab.oid_cabe)
      
      UNION ALL
      
      SELECT pais.cod_pais,
             perio.cod_peri,
             a.cod_vent_fict,
             NULL cod_grup,
             prod.cod_sap,
             '00' codtofe,
             0 precprod,
             a.imp_prec_publ preccont,
             0 pagcata,
             1 factrep,
             (SELECT ptsp.num_unid_alar
                FROM ped_tipo_solic_pais ptsp,
                     ped_tipo_solic      pts
               WHERE ptsp.pais_oid_pais = pais.oid_pais
                 AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 AND pts.cod_tipo_soli = 'SOC') AS unidmax,
             0 indestad,
             '00' grupodesc,
             0 indcomi,
             0 indcomiadic,
             (SELECT val_i18n
                FROM gen_i18n_sicc_pais
               WHERE attr_enti = 'MAE_PRODU'
                 AND val_oid = prod.oid_prod) desprod,
             0 cod_cata,
             1 ind_digi,
             0 indmarclbel,
             smp.cod_marc_prod
        FROM inc_artic_lote a,
             inc_lote_premi_artic b,
             inc_premi_artic c,
             inc_param_nivel_premi d,
             inc_param_gener_premi e,
             inc_concu_param_gener f,
             seg_pais pais,
             mae_produ prod,
             seg_marca_produ smp,
             (SELECT a.cod_peri,
                     b.oid_peri
                FROM seg_perio_corpo a,
                     cra_perio       b
               WHERE a.cod_peri IN (SELECT a.cod_peri
                                      FROM bas_ctrl_fact a
                                     WHERE a.ind_camp_act = 1
                                       AND a.sta_camp = 0)
                    --AND b.marc_oid_marc = oidmarca
                    --AND b.cana_oid_cana = oidcanal
                 AND a.oid_peri = b.peri_oid_peri) perio
       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
         AND a.prod_oid_prod = prod.oid_prod
         AND prod.pais_oid_pais = pais.oid_pais
         AND prod.mapr_oid_marc_prod = smp.oid_marc_prod
         AND b.prar_oid_prem_arti = c.oid_prem_arti
         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
         AND e.copa_oid_para_gral = f.oid_para_gral
            --AND f.ind_acti = 1
         AND d.val_nive_sele = 1
         AND (f.bcal_oid_base_calc IN (1, 2) OR
             (f.bcal_oid_base_calc = 4 AND e.tprm_oid_tipo_pion = 1))
         AND length(a.cod_vent_fict) = 5
            -- and e.ind_prem_elec=1
         AND f.perd_oid_peri_desd <= perio.oid_peri
         AND e.perd_oid_peri >= perio.oid_peri
      /*UNION ALL
      -- Agrega los Codigos de Cupones Programa Nuevas
      SELECT pais.cod_pais AS codpais,
             peri.cod_peri AS codperi,
             (SELECT cupo.cod_cupon
                FROM cup_equiv_matr cupo
               WHERE cupo.cod_pais = pais.cod_pais
                 AND cupo.cod_peri = peri.cod_peri
                 AND cupo.cod_venta = det.val_codi_vent) AS codcupon,
             '  ' AS codgrup,
             prod.cod_sap AS codprod,
             ofer.cod_tipo_ofer AS codtofe,
             round(det.imp_prec_cata / det.val_fact_repe, 2) AS precprod,
             prod.val_prec_cont AS preccont,
             det.num_pagi_cata AS pagcata,
             det.val_fact_repe AS factrep,
             (SELECT ptsp.num_unid_alar
                FROM ped_tipo_solic_pais ptsp,
                     ped_tipo_solic      pts
               WHERE ptsp.pais_oid_pais = pais.oid_pais
                 AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                 AND pts.cod_tipo_soli = 'SOC') AS unidmax,
             ofer.ind_esta AS indestad,
             (SELECT grup.cod_grup_desc
                FROM mae_grupo_descu grup,
                     mae_negoc       nego,
                     mae_unida_negoc unid,
                     seg_marca_produ mpro
               WHERE grup.pais_cod_pais = pais.cod_pais
                 AND grup.cod_grup_arti = TRIM(prod.val_grup_arti)
                 AND nego.pais_oid_pais = pais.oid_pais
                 AND nego.oid_nego = prod.nego_oid_nego
                 AND unid.pais_oid_pais = pais.oid_pais
                 AND grup.cod_unid_nego = unid.cod_unid_nego
                 AND unid.oid_unid_nego = prod.uneg_oid_unid_nego
                 AND grup.cod_marc_prod = mpro.cod_marc_prod
                 AND mpro.oid_marc_prod = prod.mapr_oid_marc_prod) AS grupodesc,
             ofer.ind_comi AS indcomi,
             (SELECT nvl((SELECT (tof.ind_comi - 1)
                           FROM pre_tipo_ofert tof
                          WHERE tof.oid_tipo_ofer =
                                ofer.oid_tipo_ofer
                            AND tof.cod_tipo_ofer IN
                                ('21', '23', '33', '53')
                            AND tof.ind_comi = 1), 1)
                FROM dual) AS indcomiadic,
             ocr_solic_pedidos.gen_fn_desc_prod(det.prod_oid_prod) AS desprod,
             (SELECT cata.cod_cata
                FROM pre_catal cata,
                     pre_ofert ofe
               WHERE cata.oid_cata = ofe.ocat_oid_cata
                 AND cata.pais_oid_pais = pais.oid_pais
                 AND det.ofer_oid_ofer = ofe.oid_ofer) AS codcata,
             1 AS indigi, -- Para que sean considerados por BizTalk y sean enviados a MyEBEL - PRE_OFERT_DETAL.IND_DIGI
             0 AS indmarclbel, -- Estos productos no son considerados para LBel - IND_MARC_LBEL
             mprod.cod_marc_prod
        FROM seg_pais        pais,
             pre_matri_factu matriz,
             pre_ofert_detal det,
             pre_tipo_ofert  ofer,
             mae_produ       prod,
             seg_marca_produ mprod,
             seg_perio_corpo peri
       WHERE pais.cod_pais = pscodigopais
         AND pais.oid_pais = prod.pais_oid_pais
         AND matriz.ofde_oid_deta_ofer = det.oid_deta_ofer
         AND det.tofe_oid_tipo_ofer = ofer.oid_tipo_ofer
         AND det.prod_oid_prod = prod.oid_prod
         AND prod.mapr_oid_marc_prod = mprod.oid_marc_prod
         AND EXISTS
       (SELECT NULL
                FROM cup_equiv_matr cupo
               WHERE cupo.cod_pais = pais.cod_pais
                 AND cupo.cod_peri = peri.cod_peri
                 AND cupo.cod_venta = det.val_codi_vent)
         AND EXISTS
       (SELECT cab.oid_cabe
                FROM pre_matri_factu_cabec cab
               WHERE EXISTS
               (SELECT NULL
                        FROM cra_perio       a,
                             seg_perio_corpo b,
                             seg_marca       marc,
                             seg_canal       cana
                       WHERE b.oid_peri = peri.oid_peri
                         AND a.peri_oid_peri = b.oid_peri
                         AND b.cod_peri IN (SELECT a.cod_peri
                                              FROM bas_ctrl_fact a
                                             WHERE a.ind_camp_act = 1
                                               AND a.sta_camp = 0)
                         AND a.pais_oid_pais = pais.oid_pais
                         AND a.marc_oid_marc = marc.oid_marc
                         AND marc.cod_marc = pscodigomarca
                         AND a.cana_oid_cana = cana.oid_cana
                         AND cana.cod_cana = pscodigocanal
                         AND cab.perd_oid_peri = a.oid_peri)
                 AND matriz.mfca_oid_cabe = cab.oid_cabe)*/
      ;
  
    --------------------------
    OPEN curprematri;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curprematri BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_vent,
             v_cod_grup,
             v_cod_prod,
             v_cod_tofe,
             v_val_prec_prod,
             v_val_prec_cont,
             v_num_pag_cata,
             v_val_fact_rep,
             v_num_unid_alar,
             v_ind_esta,
             v_cod_grup_desc,
             v_ind_comi,
             v_ind_comi_adic,
             v_des_prod,
             v_cod_cata,
             v_ind_digi,
             v_ind_marc_lbel,
             v_cod_marc_prod LIMIT rows;
    
      EXIT WHEN v_row_count = curprematri%ROWCOUNT;
      v_row_count := curprematri%ROWCOUNT;
    
      FOR i IN v_cod_pais.first .. v_cod_pais.last
      LOOP
        BEGIN
          INSERT INTO ped_matri_factu
            (cod_pais,
             cod_peri,
             cod_vent,
             cod_grup,
             cod_prod,
             cod_tofe,
             val_prec_prod,
             val_prec_cont,
             num_pag_cata,
             val_fact_rep,
             num_unid_alar,
             ind_esta,
             cod_grup_desc,
             ind_comi,
             ind_comi_adic,
             des_prod,
             cod_cata,
             ind_digi,
             ind_marc_lbel,
             cod_marc_prod)
          VALUES
            (v_cod_pais(i),
             v_cod_peri(i),
             v_cod_vent(i),
             v_cod_grup(i),
             v_cod_prod(i),
             v_cod_tofe(i),
             v_val_prec_prod(i),
             v_val_prec_cont(i),
             v_num_pag_cata(i),
             v_val_fact_rep(i),
             v_num_unid_alar(i),
             v_ind_esta(i),
             v_cod_grup_desc(i),
             v_ind_comi(i),
             v_ind_comi_adic(i),
             v_des_prod(i),
             v_cod_cata(i),
             v_ind_digi(i),
             v_ind_marc_lbel(i),
             v_cod_marc_prod(i));
        EXCEPTION
          WHEN dup_val_on_index THEN
            NULL;
        END;
      END LOOP;
    END LOOP;
    CLOSE curprematri;
    --------------------------
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACTUA_MATRI_FACTU: ' ||
                              ls_sqlerrm);
  END ocr_pr_actua_matri_factu;
  /***************************************************************************
  Descripcion       : Actualiza indicadores del archivo de control en su forma contraria
  Fecha Creacion    : 28/01/2009
    Autor              : Rosalvina Ramirez
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_contra_indic
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
    ls_sta_camp     bas_ctrl_fact.sta_camp%TYPE;
    ls_ind_camp_act bas_ctrl_fact.ind_camp_act%TYPE;
  
  BEGIN
    --Se cierran las otras campañas
    UPDATE bas_ctrl_fact
       SET ind_camp_act = '0',
           sta_camp     = '1'
     WHERE cod_pais = pscodigopais
       AND cod_peri != pscodigoperiodo;
  
    --Indicadores Contrarios
    SELECT decode(bas.ind_camp_act, '1', '0', '1'),
           decode(bas.sta_camp, '1', '0', '1')
      INTO ls_ind_camp_act,
           ls_sta_camp
      FROM bas_ctrl_fact bas
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
    IF (ls_ind_camp_act = '1') THEN
      IF (ls_sta_camp = '1') THEN
        ls_ind_camp_act := '0';
      END IF;
    END IF;
  
    --Actualiza Indicadores
    UPDATE bas_ctrl_fact bas
       SET bas.ind_camp_act = ls_ind_camp_act,
           bas.sta_camp     = ls_sta_camp
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'OCR_PR_ACTUA_CONTRA_INDIC: ' || ls_sqlerrm);
  END ocr_pr_actua_contra_indic;

  /***************************************************************************
  Descripcion       : Elimina Pedido
  Fecha Creacion    : 11/02/2009
    Autor              : Rosalvina Ramirez
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
              psCodigoCliente    : Codigo Cliente
              psnumeroLote       : Numero Lote
              psUsuario          : Usuario
  
  ***************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_digit
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumerolote    VARCHAR2,
    psusuario       VARCHAR2
  ) IS
  BEGIN
    --Actualizar  Indicadores de la cabecera
    /*UPDATE INT_SOLIC_CONSO_CABEC CA
    SET CA.IND_ERRO_RECH = '1', CA.IND_OCS_PROC = '0',  CA.IND_PROC_GP2='0', CA.USU_MODI=psUsuario, CA.FEC_MODI=sysdate
    WHERE CA.COD_PAIS = psCodigoPais
    AND CA.COD_PERI = psCodigoPeriodo
    AND CA.COD_CLIE = psCodigoCliente
    AND CA.NUM_LOTE = psNumeroLote;*/
  
    --Eliminar los pedidos de la tabla de digitacion detalle
    DELETE FROM ped_solic_digit_detal dd
     WHERE dd.cod_pais = pscodigopais
       AND dd.cod_peri = pscodigoperiodo
       AND dd.cod_clie = pscodigocliente
       AND dd.num_lote = psnumerolote;
  
    --Eliminar los pedidos de la tabla de digitacion cabecera
    DELETE FROM ped_solic_digit_cabec da
     WHERE da.cod_pais = pscodigopais
       AND da.cod_peri = pscodigoperiodo
       AND da.cod_clie = pscodigocliente
       AND da.num_lote = psnumerolote;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'OCR_PR_ELIMI_PEDID_DIGIT: ' || ls_sqlerrm);
  END ocr_pr_elimi_pedid_digit;

  /***************************************************************************
  Descripcion       : Anula Pedido STO
  Fecha Creacion    : 30/04/2009
  Autor             : Dennys Oliva Iriarte
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPeriodo    : Codigo Periodo
              psCodigoCliente    : Codigo Cliente
              psnumeroLote       : Numero Lote
              psUsuario          : Usuario
  ***************************************************************************/
  PROCEDURE ocr_pr_anula_pedid_sto
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2,
    psnumerolote    VARCHAR2,
    psusuario       VARCHAR2
  ) IS
    lsnumedocu        int_solic_conso_cabec.sec_nume_docu%TYPE;
    lstipodocu        sto_docum_digit.cod_tipo_docu%TYPE;
    lsindicadoractivo sto_tipo_docum_digit.ind_sto_acti%TYPE;
  BEGIN
    --Actualizar  Indicadores de la cabecera
    UPDATE int_solic_conso_cabec
       SET ind_ocs_proc = '0',
           ind_proc_gp2 = '0',
           ind_anul     = '1',
           usu_modi     = psusuario,
           fec_modi     = SYSDATE
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
       AND cod_clie = pscodigocliente
       AND num_lote = psnumerolote;
    SELECT a.sec_nume_docu,
           b.cod_tipo_docu,
           c.ind_sto_acti
      INTO lsnumedocu,
           lstipodocu,
           lsindicadoractivo
      FROM int_solic_conso_cabec a,
           sto_docum_digit       b,
           sto_tipo_docum_digit  c
     WHERE a.cod_pais = b.cod_pais
       AND a.num_lote = b.num_lote
       AND a.sec_nume_docu = b.sec_nume_docu
       AND c.cod_pais = a.cod_pais
       AND c.cod_tipo_docu = b.cod_tipo_docu
       AND a.cod_pais = pscodigopais
       AND a.cod_peri = pscodigoperiodo
       AND a.cod_clie = pscodigocliente
       AND a.num_lote = psnumerolote;
    -- Si esta habilitado STO
    IF lsindicadoractivo = '1' THEN
      -- Se baja a 0 el indicador en las cabeceras
      UPDATE sto_docum_digit
         SET ind_envi = '0',
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE cod_pais = pscodigopais
         AND cod_tipo_docu = lstipodocu
         AND num_lote = psnumerolote
         AND sec_nume_docu = lsnumedocu;
      -- Se baja a 0 el indicador en los detalles
      UPDATE sto_docum_digit
         SET ind_envi = '0',
             usu_modi = psusuario,
             fec_modi = SYSDATE
       WHERE cod_pais = pscodigopais
         AND cod_tipo_docu =
             sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                          lstipodocu)
         AND num_lote = psnumerolote
         AND sec_nume_docu_cabe = lsnumedocu;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'OCR_PR_ANULA_PEDID_STO: ' || ls_sqlerrm);
  END ocr_pr_anula_pedid_sto;
  /***************************************************************************
  Descripcion       : Regenera Plantillas
  Fecha Creacion    : 14/05/2009
  Autor             : Cristhian Roman
  Parametros        :
              psCodigoPais       : Codigo de Pais
              psCodigoPlantilla    : Codigo Plantilla
              psTipoSolicitud    : Tipo Solicitud
              psExclusionSolicitud       : Exclusion solicitud
              psCodigoRegion          : Region
              psGrupo                    : Grupo proceso
              psIndicadorBorrado        : Indicador de borrado
  ***************************************************************************/
  PROCEDURE ocr_pr_regen_plant(
                               
                               pscodigopais      VARCHAR2,
                               pscodigoplantilla VARCHAR2,
                               pstiposolicitud   VARCHAR2,
                               pstipoexclu       VARCHAR2,
                               psregion          VARCHAR2,
                               psgrupoproceso    VARCHAR2,
                               psindborrado      VARCHAR2) IS
    v_tiposoli NUMBER := 0;
    v_zona     NUMBER := 0;
    v_regio    NUMBER := 0;
    v_sec      NUMBER := 0;
    v_exclu    VARCHAR2(200);
    CURSOR c_tiposolicitud IS
      SELECT nvl(a.oid_tipo_soli_pais, 0)
        FROM ped_tipo_solic_pais a,
             ped_tipo_solic      b,
             gen_i18n_sicc_comun c
       WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
         AND b.oid_tipo_soli = c.val_oid
         AND c.attr_enti = 'PED_TIPO_SOLIC'
         AND upper(c.val_i18n) LIKE '%' || pstiposolicitud || '%'
         AND upper(c.val_i18n) NOT LIKE '%' || v_exclu || '%';
  
    CURSOR c_zona IS
      SELECT oid_zona,
             zorg_oid_regi
        FROM zon_zona
       WHERE ind_acti = 1 --;
         AND zorg_oid_regi IN
             (SELECT oid_regi
                FROM zon_regio
               WHERE cod_regi = decode(psregion, NULL, cod_regi, psregion))
       ORDER BY cod_zona;
  
  BEGIN
  
    IF pstipoexclu IS NULL THEN
      v_exclu := 'XXX';
    ELSE
      v_exclu := pstipoexclu;
    END IF;
  
    IF nvl(psindborrado, 0) = '1' THEN
    
      DELETE FROM ped_plant_monit_detal
       WHERE plsm_oid_plan_moni =
             (SELECT oid_plan_moni
                FROM ped_plant_monit
               WHERE cod_plan_moni = pscodigoplantilla);
      v_sec := 0;
    
    ELSE
    
      SELECT MAX(num_secu) + 1
        INTO v_sec
        FROM ped_plant_monit_detal
       WHERE plsm_oid_plan_moni =
             (SELECT oid_plan_moni
                FROM ped_plant_monit
               WHERE cod_plan_moni = pscodigoplantilla);
    
    END IF;
  
    OPEN c_tiposolicitud;
    LOOP
      FETCH c_tiposolicitud
        INTO v_tiposoli;
      EXIT WHEN c_tiposolicitud%NOTFOUND;
    
      OPEN c_zona;
      LOOP
        FETCH c_zona
          INTO v_zona,
               v_regio;
      
        EXIT WHEN c_zona%NOTFOUND;
        v_sec := v_sec + 1;
      
        INSERT INTO ped_plant_monit_detal
          (num_secu,
           zorg_oid_regi,
           tspa_oid_tipo_soli_pais,
           zzon_oid_zona,
           oid_plan_moni_deta,
           plsm_oid_plan_moni,
           grpr_oid_grup_proc)
        VALUES
          (v_sec,
           v_regio,
           v_tiposoli,
           v_zona,
           ped_pls2_seq.nextval,
           (SELECT oid_plan_moni
              FROM ped_plant_monit
             WHERE cod_plan_moni = pscodigoplantilla)
           --, 0
          ,
           psgrupoproceso);
      
      END LOOP;
      CLOSE c_zona;
    
    END LOOP;
    CLOSE c_tiposolicitud;
  
  END ocr_pr_regen_plant;

  /************************************************************************
   Descripcion    :Recepciona OCR_PR_COMP_OPORT_PRIVI_STO
                  Proceso de oportunidades Privilege, Ejecutado desde
                  las validaciones de STO
   Autor         : Jose Cairampoma
   Fecha         : 17/11/2011
  ***********************************************************************/
  PROCEDURE ocr_pr_comp_oport_privi_sto
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pscodigousuario IN VARCHAR2,
    pscodtipodocu   IN VARCHAR2,
    psnumeroproceso IN VARCHAR2
  ) AS
    CURSOR curinsoportun(psfechafact VARCHAR2) IS
      SELECT t.pais_cod_pais pais,
             pscodigoperiodo periodo,
             tar.cod_cons cliente,
             to_date(psfechafact, 'dd/mm/yyyy') fecsolic,
             gen_fn_dev_oid_ofer_detal_pri(pscodigoperiodo, t.cod_prod) codventa,
             SUM(t.can_prem) cantidad,
             t.tarj_num_tarj tarj,
             t.cor_prem cor
        FROM pri_premi             t,
             pri_tarje             tar,
             int_solic_conso_cabec cab,
             sto_proce_docum_digit tmp
       WHERE t.pais_cod_pais = pscodigopais
         AND tar.pais_cod_pais = t.pais_cod_pais
         AND tar.num_tarj = t.tarj_num_tarj --
         AND cab.cod_pais = t.pais_cod_pais
         AND cab.cod_clie = tar.cod_cons
         AND cab.cod_peri = pscodigoperiodo
         AND cab.ind_ocs_proc = '0'
         AND cab.ind_proc_gp2 = '0'
         AND cab.ind_erro_remp = '0'
         AND cab.ind_erro_rech = '0'
         AND cab.ind_error_sgpe = '0'
         AND tmp.num_lote = cab.num_lote
         AND tmp.sec_nume_docu = cab.sec_nume_docu
         AND tmp.num_proc = psnumeroproceso
         AND tmp.cod_tipo_docu = pscodtipodocu
         AND t.ind_prem = 'Q'
         AND t.sta_prem <> 'F'
         AND NOT EXISTS
       (SELECT NULL
                FROM int_solic_conso_detal detal
               WHERE detal.cod_pais = cab.cod_pais
                 AND detal.cod_peri = cab.cod_peri
                 AND detal.cod_clie = cab.cod_clie
                 AND detal.num_lote = cab.num_lote
                 AND detal.cod_vent =
                     gen_fn_dev_oid_ofer_detal_pri(pscodigoperiodo,
                                                   t.cod_prod))
       GROUP BY t.pais_cod_pais,
                tar.cod_cons,
                t.tarj_num_tarj,
                t.cor_prem,
                t.cod_prod;
  
    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
    TYPE t_cod_venta IS TABLE OF int_solic_posic.cod_prod%TYPE;
    TYPE t_cantidad IS TABLE OF pri_premi.can_prem%TYPE;
    TYPE t_tarjeta IS TABLE OF pri_premi.tarj_num_tarj%TYPE;
    TYPE t_core IS TABLE OF pri_premi.cor_prem%TYPE;
  
    v_cod_pais  t_cod_pais;
    v_cod_peri  t_cod_peri;
    v_cod_clie  t_cod_clie;
    v_fec_soli  t_fec_soli;
    v_cod_venta t_cod_venta;
    n_cantidad  t_cantidad;
    n_tarjeta   t_tarjeta;
    n_core      t_core;
  
    rows        NATURAL := 1000; -- Number of rows to process at a time
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
    d_fechafact VARCHAR2(10);
    v_num_lote  bas_ctrl_fact.num_lote%TYPE;
  
  BEGIN
    SELECT to_char(MAX(cb.fec_soli), 'dd/mm/yyyy')
      INTO d_fechafact
      FROM int_solic_cabec cb;
  
    SELECT bas.num_lote
      INTO v_num_lote
      FROM bas_ctrl_fact bas
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
    OPEN curinsoportun(d_fechafact);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsoportun BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_fec_soli,
             v_cod_venta,
             n_cantidad,
             n_tarjeta,
             n_core LIMIT rows;
      EXIT WHEN v_row_count = curinsoportun%ROWCOUNT;
      v_row_count := curinsoportun%ROWCOUNT;
    
      FOR i IN v_cod_clie.first .. v_cod_clie.last
      LOOP
        -- Manipumate data in the memory table...
        IF (v_cod_venta(i) = '0') THEN
          UPDATE pri_premi pre
             SET pre.sta_prem = 'Z'
           WHERE pre.tarj_num_tarj = n_tarjeta(i)
             AND pre.pais_cod_pais = v_cod_pais(i)
             AND pre.cor_prem = n_core(i);
        ELSE
          BEGIN
            INSERT INTO int_solic_conso_detal
              (cod_pais,
               cod_peri,
               cod_clie,
               cod_vent,
               tip_posic,
               des_prod,
               val_unid_dem,
               sta_proc,
               ind_comp_mtmi,
               usu_digi,
               fec_digi,
               val_unid_comp,
               fec_soli,
               ind_erro_rech,
               num_lote,
               ind_erro_sse)
            VALUES
              (v_cod_pais(i),
               v_cod_peri(i),
               v_cod_clie(i),
               v_cod_venta(i),
               'OC',
               (SELECT gen_fn_desc_prod(ofde.prod_oid_prod)
                  FROM pre_matri_factu matriz,
                       pre_ofert_detal ofde
                 WHERE matriz.ofde_oid_deta_ofer = ofde.oid_deta_ofer
                   AND ofde.val_codi_vent = v_cod_venta(i)
                   AND EXISTS
                 (SELECT cab.oid_cabe
                          FROM pre_matri_factu_cabec cab
                         WHERE EXISTS
                         (SELECT NULL
                                  FROM cra_perio       a,
                                       seg_perio_corpo b
                                 WHERE a.peri_oid_peri = b.oid_peri
                                   AND b.cod_peri = v_cod_peri(i)
                                   AND cab.perd_oid_peri = a.oid_peri)
                           AND matriz.mfca_oid_cabe = cab.oid_cabe)),
               n_cantidad(i),
               'O', -- las q fueron agragadas por oprotunidades es 'o',
               '0',
               pscodigousuario,
               SYSDATE,
               '0',
               '',
               '0',
               v_num_lote,
               '0');
          END;
        END IF;
      
      END LOOP;
    
    END LOOP;
    CLOSE curinsoportun;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_COMP_OPORT_PRIVI_STO: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_comp_oport_privi_sto;
  /***************************************************************************
  Descripcion       : Consolida los pedidos por cambio de codigo de consulora
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_pedid_cambi_codig
  (
    pscodigopais        VARCHAR2,
    pscodigocliente     VARCHAR2,
    pscodigoclienteante VARCHAR2,
    psnumerolote        VARCHAR2,
    pssecnumedocu       VARCHAR2,
    pscodigoperiodo     VARCHAR2
  ) IS
  
    TYPE t_num_lote IS TABLE OF sto_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_soca_oid_soli_cabe_refe IS TABLE OF int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
  
    v_num_lote                t_num_lote;
    v_sec_nume_docu           t_sec_nume_docu;
    v_clie_oid_clie           t_clie_oid_clie;
    v_soca_oid_soli_cabe_refe t_soca_oid_soli_cabe_refe;
  
    CURSOR curpedidosconso
    (
      vngp              NUMBER,
      vnnumerodias      NUMBER,
      vsindactuacliente VARCHAR2
    ) IS
      SELECT c.num_lote,
             c.sec_nume_docu,
             c.clie_oid_clie,
             c.soca_oid_soli_cabe_refe
        FROM int_solic_conso_cabec c
       WHERE c.ind_erro_rech = '0'
         AND nvl((SELECT p.grpr_oid_grup_proc
                   FROM ped_solic_cabec p
                  WHERE oid_soli_cabe = soca_oid_soli_cabe_refe),
                 0) <= vngp
         AND c.cod_peri = pscodigoperiodo
         AND c.cod_clie IN (pscodigocliente, pscodigoclienteante)
      UNION
      SELECT num_lote,
             sec_nume_docu,
             clie_oid_clie,
             soca_oid_soli_cabe_refe
        FROM (SELECT cab.sec_nume_docu,
                     cab.num_lote,
                     cab.cod_clie,
                     CASE
                     /*SI ES SOCN*/
                       WHEN cab.ind_docu_iden = '1' THEN
                        (SELECT MAX(c.cod_clie)
                           FROM mae_clien_ident       i,
                                mae_clien             c,
                                mae_clien_datos_adici a
                          WHERE c.oid_clie = i.clie_oid_clie
                            AND c.oid_clie = a.clie_oid_clie
                            AND i.tdoc_oid_tipo_docu = 2001
                            AND i.val_iden_docu_prin = 1
                            AND ltrim(i.num_docu_iden, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                     /*SI NO ES SOCN*/
                       WHEN cab.ind_docu_iden = '0' AND
                            vsindactuacliente = '1' THEN
                        (SELECT MAX(c.cod_clie)
                           FROM mae_clien_ident       i,
                                mae_clien             c,
                                mae_clien_datos_adici da
                          WHERE c.oid_clie = i.clie_oid_clie
                            AND i.clie_oid_clie = da.clie_oid_clie
                            AND i.tdoc_oid_tipo_docu = 2001
                            AND i.num_docu_iden = ltrim(cab.cod_clie, 'C')
                            AND i.val_iden_docu_prin = 1
                            AND i.fec_ulti_actu >=
                                trunc(SYSDATE) - vnnumerodias)
                       WHEN cab.ind_docu_iden = '0' AND
                            vsindactuacliente = '2' THEN
                        (SELECT b.cod_clie
                           FROM sto_docum_digit       b,
                                int_solic_conso_credi c
                          WHERE c.sec_nume_docu = b.sec_nume_docu
                            AND c.num_lote = b.num_lote
                            AND b.fec_modi >= trunc(SYSDATE) - vnnumerodias
                            AND b.ind_envi = '1'
                            AND c.num_docu = ltrim(cab.cod_clie, 'C'))
                       WHEN vsindactuacliente = '3' THEN
                        (SELECT b.cod_clie
                           FROM sto_docum_digit       b,
                                int_solic_conso_credi c
                          WHERE c.sec_nume_docu = b.sec_nume_docu
                            AND c.num_lote = b.num_lote
                            AND b.fec_modi >= trunc(SYSDATE) - vnnumerodias
                            AND b.ind_envi = '1'
                            AND b.cod_tipo_docu = 'SCC'
                            AND ltrim(c.num_docu, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                       WHEN cab.ind_docu_iden = '0' AND
                            vsindactuacliente = '4' THEN
                        (SELECT MAX(b.cod_clie)
                           FROM mae_clien_ident a,
                                mae_clien       b
                          WHERE b.oid_clie = a.clie_oid_clie
                            AND a.tdoc_oid_tipo_docu = 2001
                            AND a.val_iden_docu_prin = 1
                            AND a.fec_ulti_actu >=
                                trunc(SYSDATE) - vnnumerodias
                            AND ltrim(a.num_docu_iden, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                       ELSE
                        cab.cod_clie
                     END cod_clie_orig,
                     cod_peri,
                     clie_oid_clie,
                     soca_oid_soli_cabe_refe,
                     perd_oid_peri
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_clie LIKE 'C%'
                 AND pssecnumedocu != sec_nume_docu)
       WHERE cod_clie_orig = pscodigocliente;
  
    w_filas NUMBER := 5000;
  
    CURSOR curdetal
    (
      vsnumlote     VARCHAR2,
      vssecnumedocu NUMBER
    ) IS
      SELECT det.num_lote,
             det.sec_nume_docu,
             det.cod_vent,
             det.tip_posic,
             det.val_unid_dem
        FROM int_solic_conso_detal det
       WHERE det.sec_nume_docu_cabe = vssecnumedocu
         AND det.num_lote = vsnumlote;
  
    TYPE t_det_num_lote IS TABLE OF int_solic_conso_detal.num_lote%TYPE;
    TYPE t_det_sec_nume_docu IS TABLE OF int_solic_conso_detal.sec_nume_docu%TYPE;
    TYPE t_det_cod_vent IS TABLE OF int_solic_conso_detal.cod_vent%TYPE;
    TYPE t_det_tip_posic IS TABLE OF int_solic_conso_detal.tip_posic%TYPE;
    TYPE t_det_val_unid_dem IS TABLE OF int_solic_conso_detal.val_unid_dem%TYPE;
  
    v_det_num_lote      t_det_num_lote;
    v_det_sec_nume_docu t_det_sec_nume_docu;
    v_det_cod_vent      t_det_cod_vent;
    v_det_tip_posic     t_det_tip_posic;
    v_det_val_unid_dem  t_det_val_unid_dem;
  
    lngp NUMBER;
  
    lnnumerodias      sto_param_gener_occrr.val_param%TYPE;
    lsindactuacliente bas_pais.ind_actu_clie_cedu%TYPE;
  
    lnnumdetal NUMBER;
    lbactuunid BOOLEAN;
  
  BEGIN
  
    lnnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');
  
    SELECT val_para
      INTO lngp
      FROM bas_param_pais
     WHERE cod_pais = pscodigopais
       AND nom_para = 'nGPConsPedidos'
       AND cod_sist = 'OCR';
  
    /*CONSOLIDANDO PEDIDOS VALIDOS*/
    OPEN curpedidosconso(lngp, lnnumerodias, lsindactuacliente);
    LOOP
      FETCH curpedidosconso BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_clie_oid_clie,
             v_soca_oid_soli_cabe_refe LIMIT w_filas;
    
      IF v_num_lote.count > 0 THEN
      
        FOR i IN v_num_lote.first .. v_num_lote.last
        LOOP
          /*BORRANDO EXCEPCIONES*/
          DELETE FROM sto_detal_docum_excep sde
           WHERE sde.num_lote = v_num_lote(i)
             AND sde.sec_nume_docu = v_sec_nume_docu(i);
        
          DELETE FROM sto_detal_docum_excep sde
           WHERE sde.num_lote = v_num_lote(i)
             AND sde.sec_nume_docu_cabe = v_sec_nume_docu(i);
        
          ocr_elimi_cupon_pedid(v_sec_nume_docu(i));
        
          IF v_sec_nume_docu(i) != pssecnumedocu THEN
          
            IF v_soca_oid_soli_cabe_refe(i) IS NOT NULL THEN
            
              --ELIMINAMOS INFORMACION DE NUEVO MAV
              sto_pkg_proce_gener.sto_pr_elimi_mav_envio(v_soca_oid_soli_cabe_refe(i));
            
              DELETE FROM msg_buzon_mensa
               WHERE clie_oid_clie = v_clie_oid_clie(i)
                 AND ind_acti = 1
                 AND EXISTS (SELECT oid_mens
                        FROM msg_mensa
                       WHERE cod_mens LIKE 'MAV%'
                         AND mens_oid_mens = oid_mens);
            
              DELETE FROM ped_solic_mensa
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
              DELETE ped_solic_posic
               WHERE soca_oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
              DELETE ped_solic_cabec
               WHERE oid_soli_cabe = v_soca_oid_soli_cabe_refe(i);
            
              DELETE inc_premi_elegi p
               WHERE p.clie_oid_clie = v_clie_oid_clie(i)
                 AND EXISTS
               (SELECT 1
                        FROM int_solic_conso_detal det
                       WHERE det.sec_nume_docu = v_sec_nume_docu(i)
                         AND det.num_lote = v_num_lote(i)
                         AND p.num_prem = det.num_prem
                         AND p.copa_oid_para_gral = det.copa_oid_para_gral
                         AND p. panp_oid_para_nive_prem =
                             det.panp_oid_para_nive_prem);
            
            END IF;
          
            /*BORRANDO CABECERAS DE PEDIDOS A CONSOLIDAR*/
            DELETE FROM sto_audit_excep sae
             WHERE sae.num_lote = v_num_lote(i)
               AND sae.sec_nume_docu = v_sec_nume_docu(i);
          
            DELETE FROM sto_docum_digit sdd
             WHERE sdd.num_lote = v_num_lote(i)
               AND sdd.sec_nume_docu = v_sec_nume_docu(i);
          
            DELETE FROM int_solic_conso_cabec
             WHERE num_lote = v_num_lote(i)
               AND sec_nume_docu = v_sec_nume_docu(i);
          
            --BORRANDO ERRORES DE DETALLE
            DELETE FROM sto_audit_excep sae
             WHERE sae.num_lote = v_num_lote(i)
               AND sae.sec_nume_docu_cabe = v_sec_nume_docu(i);
          
            OPEN curdetal(v_num_lote(i), v_sec_nume_docu(i));
            LOOP
              FETCH curdetal BULK COLLECT
                INTO v_det_num_lote,
                     v_det_sec_nume_docu,
                     v_det_cod_vent,
                     v_det_tip_posic,
                     v_det_val_unid_dem LIMIT w_filas;
            
              EXIT WHEN curdetal%NOTFOUND;
              FOR i IN v_det_sec_nume_docu.first .. v_det_sec_nume_docu.last
              LOOP
              
                lbactuunid := TRUE;
                SELECT COUNT(1)
                  INTO lnnumdetal
                  FROM int_solic_conso_detal
                 WHERE sec_nume_docu_cabe = pssecnumedocu
                   AND cod_vent = v_det_cod_vent(i)
                   AND tip_posic = v_det_tip_posic(i);
              
                IF lnnumdetal = 0 THEN
                  BEGIN
                    /*COD_PAIS, COD_PERI, COD_CLIE, NUM_LOTE, COD_VENT, TIP_POSIC*/
                    UPDATE int_solic_conso_detal
                       SET cod_clie           = pscodigocliente,
                           num_lote           = psnumerolote,
                           sec_nume_docu_cabe = pssecnumedocu
                     WHERE sec_nume_docu_cabe = v_det_sec_nume_docu(i)
                       AND num_lote = v_det_num_lote(i);
                  
                    lbactuunid := FALSE;
                  EXCEPTION
                    WHEN dup_val_on_index THEN
                      lbactuunid := TRUE;
                  END;
                END IF;
              
                IF lbactuunid THEN
                  UPDATE int_solic_conso_detal
                     SET val_unid_dem = val_unid_dem + v_det_val_unid_dem(i)
                   WHERE sec_nume_docu_cabe = pssecnumedocu
                     AND num_lote = psnumerolote
                     AND cod_vent = v_det_cod_vent(i)
                     AND tip_posic = v_det_tip_posic(i);
                
                  DELETE int_solic_conso_detal
                   WHERE sec_nume_docu = v_det_sec_nume_docu(i)
                     AND num_lote = v_det_num_lote(i);
                
                  DELETE sto_docum_digit
                   WHERE sec_nume_docu = v_det_sec_nume_docu(i)
                     AND num_lote = v_det_num_lote(i);
                
                END IF;
              END LOOP;
            
            END LOOP;
            CLOSE curdetal;
          
          END IF;
        
        END LOOP;
      
      END IF;
      EXIT WHEN curpedidosconso%NOTFOUND;
    END LOOP;
    CLOSE curpedidosconso;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_PEDID_CAMBI_CODIG: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_conso_pedid_cambi_codig;
  /***************************************************************************
  Descripcion       : Elimina los cupones de un pedido
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_elimi_cupon_pedid(pnsecnumedocu NUMBER) IS
  
    CURSOR curcupones IS
      SELECT num_lote,
             sec_nume_docu
        FROM int_solic_conso_detal d
       WHERE d.sta_proc != 'A'
         AND d.sec_nume_docu_cabe = pnsecnumedocu;
  
    TYPE t_num_lote IS TABLE OF sto_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    w_filas         NUMBER := 5000;
  
  BEGIN
    /*BORRANDO CUPONES DE LA CONSULTORA*/
    OPEN curcupones;
    LOOP
      FETCH curcupones BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT w_filas;
    
      IF v_num_lote.count > 0 THEN
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE FROM sto_audit_excep sae
           WHERE sae.num_lote = v_num_lote(i)
             AND sae.sec_nume_docu = v_sec_nume_docu(i);
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE FROM sto_detal_docum_excep sde
           WHERE sde.num_lote = v_num_lote(i)
             AND sde.sec_nume_docu = v_sec_nume_docu(i);
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE FROM sto_docum_digit sdd
           WHERE sdd.num_lote = v_num_lote(i)
             AND sdd.sec_nume_docu = v_sec_nume_docu(i);
      
        FORALL i IN 1 .. v_num_lote.count
          DELETE FROM int_solic_conso_detal det
           WHERE det.num_lote = v_num_lote(i)
             AND det.sec_nume_docu = v_sec_nume_docu(i);
      
      END IF;
      EXIT WHEN curcupones%NOTFOUND;
    END LOOP;
    CLOSE curcupones;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_ELIMI_CUPON_PEDID: ' || ls_sqlerrm);
  END ocr_elimi_cupon_pedid;

  /***************************************************************************
  Descripcion       : Proceso de actualizacion de codigo de consultora
  Fecha Creacion    : 21/08/2013
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_codig_consu
  (
    pscodigopais        VARCHAR2,
    pscodigocliente     VARCHAR2,
    pscodigoclienteante VARCHAR2,
    pscodigoperiodo     VARCHAR2,
    psnumerolote        VARCHAR2,
    psnumerocliente     VARCHAR2,
    pstiposolicitud     VARCHAR2,
    pscodigosubacceso   VARCHAR2,
    pscodigoacceso      VARCHAR2,
    pstipodespacho      VARCHAR2,
    psestadoproceso     VARCHAR2,
    psmontopedido       VARCHAR2,
    pssecnumedocu       VARCHAR2,
    psusuario           VARCHAR2,
    pscodigozonaarribo  VARCHAR2,
    psoidmotiges        VARCHAR2,
    psvalobsegestion    VARCHAR2
  ) IS
  
    v_clie_oid_clie int_solic_conso_cabec.clie_oid_clie%TYPE;
    v_nom_clie      int_solic_conso_cabec.nom_clie%TYPE;
    v_cod_regi      int_solic_conso_cabec.cod_regi%TYPE;
    v_des_regi      int_solic_conso_cabec.des_regi%TYPE;
    v_cod_zona      int_solic_conso_cabec.cod_zona%TYPE;
    v_des_zona      int_solic_conso_cabec.des_zona%TYPE;
    v_cod_terr      int_solic_conso_cabec.cod_terr%TYPE;
  
    lstipodocumentocabecera sto_tipo_docum_digit.cod_tipo_docu%TYPE := 'OCC';
  
    lsvalidacionclientecambiado sto_param_gener_occrr.val_param%TYPE;
    lsmensajeclientecambiado    sto_param_gener_occrr.val_param%TYPE;
  
    lngp              NUMBER;
    ls_ind_error_sgpe int_solic_conso_cabec.ind_error_sgpe%TYPE;
  
  BEGIN
  
    SELECT val_para
      INTO lngp
      FROM bas_param_pais
     WHERE cod_pais = pscodigopais
       AND nom_para = 'nGPConsPedidos'
       AND cod_sist = 'OCR';
  
    lsmensajeclientecambiado    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        'STO_MENS_CLIE_CAMB');
    lsvalidacionclientecambiado := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        'STO_VAL_CLIE_CAMB');
  
    IF pscodigocliente != pscodigoclienteante THEN
    
      INSERT INTO ped_audit_conso_cabec
        (oid_auid_cons_cabe,
         sec_nume_docu,
         fec_soli,
         cod_peri,
         cod_clie_ante,
         cod_clie_nuev,
         fec_modi,
         usu_modi,
         num_lote)
        SELECT pacc_seq.nextval,
               sec_nume_docu,
               fec_soli,
               cod_peri,
               pscodigoclienteante,
               pscodigocliente,
               SYSDATE,
               psusuario,
               num_lote
          FROM int_solic_conso_cabec
         WHERE sec_nume_docu = pssecnumedocu
           AND num_lote = psnumerolote;
    
      ocr_pr_conso_pedid_cambi_codig(pscodigopais,
                                     pscodigocliente,
                                     pscodigoclienteante,
                                     psnumerolote,
                                     pssecnumedocu,
                                     pscodigoperiodo);
    
      INSERT INTO sto_detal_docum_excep
        (cod_pais,
         cod_tipo_docu,
         num_lote,
         cod_vali,
         sec_nume_docu,
         ind_apro,
         ind_gest,
         fec_digi,
         usu_digi,
         fec_modi,
         usu_modi,
         ind_leva_erro,
         cod_mens)
      VALUES
        (pscodigopais,
         lstipodocumentocabecera,
         psnumerolote, --v_lote, --psnumerolote,
         lsvalidacionclientecambiado,
         pssecnumedocu,
         '0',
         (SELECT ind_gest
            FROM sto_param_valid
           WHERE cod_pais = pscodigopais
             AND cod_tipo_docu = lstipodocumentocabecera
             AND cod_vali = lsvalidacionclientecambiado),
         SYSDATE,
         psusuario,
         SYSDATE,
         psusuario,
         '0',
         lsmensajeclientecambiado);
    
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
         cod_mens,
         sec_nume_docu_cabe,
         oid_audi_exce,
         cod_acci)
      VALUES
        (pscodigopais,
         lstipodocumentocabecera,
         psnumerolote, --v_lote, --psnumerolote,
         lsvalidacionclientecambiado,
         pssecnumedocu,
         to_char(SYSDATE, 'YYYYMMDDHHMI') + 5000,
         (SELECT ind_gest
            FROM sto_param_valid
           WHERE cod_pais = pscodigopais
             AND cod_tipo_docu = lstipodocumentocabecera
             AND cod_vali = lsvalidacionclientecambiado),
         SYSDATE,
         psusuario,
         lsmensajeclientecambiado,
         NULL,
         sto_auex_seq.nextval,
         NULL);
    END IF;
  
    SELECT MIN(oid_clie),
           MIN(nom_clie),
           MIN(cod_regi),
           MIN(des_regi),
           MIN(cod_zona),
           MIN(des_zona),
           MIN(cod_terr)
      INTO v_clie_oid_clie,
           v_nom_clie,
           v_cod_regi,
           v_des_regi,
           v_cod_zona,
           v_des_zona,
           v_cod_terr
      FROM v_mae_clie_unida_admin t
     WHERE cod_clie = pscodigocliente;
  
    ls_ind_error_sgpe := gen_fn_exist_pedido2(pscodigopais,
                                              pscodigoperiodo,
                                              pscodigocliente);
    /*ACTUALIZA EL LOTE DEL PEDIDO A CAMBIAR PARA CONSOLIDARLO CON
    LOS PEDIDOS VALIDOS*/
    UPDATE int_solic_conso_cabec
       SET clie_oid_clie  = v_clie_oid_clie,
           nom_clie       = v_nom_clie,
           cod_regi       = v_cod_regi,
           des_regi       = v_des_regi,
           cod_zona       = v_cod_zona,
           des_zona       = v_des_zona,
           cod_terr       = v_cod_terr,
           cod_clie       = pscodigocliente,
           num_clie       = psnumerocliente,
           tipo_soli      = pstiposolicitud,
           cod_sbac       = pscodigosubacceso,
           cod_acce       = pscodigoacceso,
           tip_desp       = pstipodespacho,
           sta_proc       = psestadoproceso,
           val_mont_pedi  = psmontopedido,
           cod_zona_arri  = pscodigozonaarribo,
           ind_erro_mtmi  = '0',
           ind_error_sgpe = ls_ind_error_sgpe
     WHERE sec_nume_docu = pssecnumedocu
       AND num_lote = psnumerolote;
  
    UPDATE int_solic_conso_detal
       SET cod_clie = pscodigocliente
     WHERE sec_nume_docu_cabe = pssecnumedocu
       AND num_lote = psnumerolote;
  
    UPDATE sto_docum_digit
       SET cod_clie      = pscodigocliente,
           cod_zona_arri = pscodigozonaarribo,
           oid_moti_gest = psoidmotiges,
           val_obse_gest = psvalobsegestion
     WHERE sec_nume_docu = pssecnumedocu
       AND num_lote = psnumerolote;
  
    UPDATE sto_docum_digit
       SET cod_clie      = pscodigocliente,
           cod_zona_arri = pscodigozonaarribo,
           oid_moti_gest = psoidmotiges,
           val_obse_gest = psvalobsegestion
     WHERE sec_nume_docu_cabe = pssecnumedocu
       AND num_lote = psnumerolote;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ACTUA_CODIG_CONSU: ' ||
                              ls_sqlerrm);
  END ocr_pr_actua_codig_consu;

  /**************************************************************************
  Descripcion       : Valida bloqueo digitacion de pedidos
  Autor             : Christian Gonzales komiya
  Fecha Creacion    : 06/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_valid_bloqu_digit_pedid(p_cod_cliente IN VARCHAR2)
    RETURN VARCHAR2 IS
    v_count_reg1  INT;
    v_count_reg2  INT;
    v_oid_cliente mae_clien.oid_clie%TYPE;
  BEGIN
  
    v_oid_cliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(p_cod_cliente);
  
    SELECT COUNT(1)
      INTO v_count_reg1
      FROM mae_clien_bloqu a
     WHERE a.fec_desb IS NULL
       AND a.clie_oid_clie = v_oid_cliente;
  
    SELECT COUNT(1)
      INTO v_count_reg2
      FROM mae_clien_bloqu       a,
           mae_accio_proce_bloqu b,
           mae_accio_bloqu       c,
           mae_proce_bloqu       d
     WHERE a.fec_desb IS NULL
       AND a.clie_oid_clie = v_oid_cliente
       AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
       AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
       AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
       AND d.cod_proc_bloq = 'FA'
       AND c.cod_acci_bloq = 'FS';
  
    IF v_count_reg1 = v_count_reg2 THEN
      RETURN 'S';
    END IF;
  
    RETURN 'N';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_FN_VALID_BLOQU_DIGIT_PEDID: ' ||
                              ls_sqlerrm);
    
  END ocr_fn_valid_bloqu_digit_pedid;

  /**************************************************************************
    Descripcion       : DEvuelve el grupo de proceso de un consolidado
    Autor             : Jose Luis Rodriguez
    Fecha Creacion    : 26/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_devue_grupo_proce(p_num_consolidado IN NUMBER)
    RETURN NUMBER IS
    vn_grupo NUMBER(12);
  BEGIN
    vn_grupo := 0;
  
    IF (p_num_consolidado = 0) THEN
      vn_grupo := -1;
    ELSE
    
      SELECT p.grpr_oid_grup_proc
        INTO vn_grupo
        FROM ped_solic_cabec p
       WHERE oid_soli_cabe = p_num_consolidado;
    
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
  END ocr_fn_devue_grupo_proce;

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

  /**************************************************************************
      Descripcion       : Consolida el pedido online
      Fecha Creacion    : 08/11/2011
      Parametros Entrada:
          psCodigoPais : Codigo de pais
          psCodigoPeriodo : Codigo de periodo
          psUsuario : Codigo de Usuario
          psnumerolotesto   Lote generado por STO,
          psindicadororigen Origen (DIGITADO ->G),
      Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_conso_pedid_onlin
  (
    pscodigopais      VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    pscodconsultora   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
  
    CURSOR curinsconsolcabec IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             0,
             'SOC',
             '000',
             '01',
             'N',
             cab.fec_soli,
             'A',
             seq_solic_cab.nextval,
             cab.num_lote,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             cab.ind_proc          ind_proc,
             NULL                  fec_proc,
             NULL                  fec_fact,
             NULL                  num_lote_dd,
             NULL                  ind_clie_vali,
             NULL                  ind_docu_iden,
             psnumerolotesto       num_lote_sto,
             NULL                  cod_inte,
             NULL                  num_lote_inte,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL
        FROM ped_solic_digit_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cab.ind_proc = psindicadororigen
         AND cab.cod_clie = pscodconsultora
         AND EXISTS (SELECT NULL
                FROM ped_solic_digit_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.ind_ocs_detal = '0'
                 AND det.ind_proc = psindicadororigen);
  
    CURSOR curinsconsoldet IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             'OC',
             det.cod_vent,
             det.val_unid_dem,
             'A' sta_proc,
             seq_solic_pos.nextval,
             det.num_lote,
             NULL,
             NULL,
             NULL,
             NULL,
             det.ind_proc ind_proc,
             NULL cod_vent,
             NULL num_lote_dd,
             psnumerolotesto num_lote_sto,
             NULL cod_inte,
             NULL num_lote_inte
        FROM ped_solic_digit_detal det
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo
         AND det.cod_clie = pscodconsultora
         AND det.ind_ocs_detal = '0'
         AND det.ind_proc = psindicadororigen;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    TYPE solic_det_tab_t IS TABLE OF int_solic_posic%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_det_tab solic_det_tab_t;
  
    rows NATURAL := 10000;
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
    lsnumerolote bas_ctrl_fact.num_lote%TYPE;
  
  BEGIN
  
    SELECT bas.num_lote
      INTO lsnumerolote
      FROM bas_ctrl_fact bas
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
    OPEN curinsconsolcabec;
    LOOP
    
      FETCH curinsconsolcabec BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR j IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
        sol_cab_tab(j).num_lote := lsnumerolote;
      END LOOP;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_cabec VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcabec;
  
    OPEN curinsconsoldet;
    LOOP
    
      FETCH curinsconsoldet BULK COLLECT
        INTO sol_det_tab LIMIT rows;
      EXIT WHEN sol_det_tab.count = 0;
    
      FOR j IN sol_det_tab.first .. sol_det_tab.last
      LOOP
        sol_det_tab(j).num_lote := lsnumerolote;
      END LOOP;
    
      FORALL j IN sol_det_tab.first .. sol_det_tab.last
        INSERT INTO int_solic_posic VALUES sol_det_tab (j);
    
    END LOOP;
    CLOSE curinsconsoldet;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_PEDID_ONLIN: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_pedid_onlin;

  /**************************************************************************
      Descripcion       : Ejecuta Procesos Especiales para un solo pedido
      Fecha Creacion    : 19/12/2011
      Parametros Entrada:
      Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_proce_espec
  (
    pscodigopais  VARCHAR2,
    pnoidsolictud NUMBER,
    psusuario     VARCHAR2
  ) IS
  
    v_grpr_oid_grup_proc ped_solic_cabec.grpr_oid_grup_proc%TYPE;
    v_clie_oid_clie      ped_solic_cabec.clie_oid_clie%TYPE;
    v_perd_oid_peri      ped_solic_cabec.clie_oid_clie%TYPE;
    v_fec_prog_fact      ped_solic_cabec.fec_prog_fact%TYPE;
    v_oid_soli_cabe      ped_solic_cabec.oid_soli_cabe%TYPE;
    v_esso_oid_esta_soli ped_solic_cabec.esso_oid_esta_soli%TYPE;
    v_espe_oid_esta_pedi ped_solic_cabec.espe_oid_esta_pedi%TYPE;
    v_val_tota_paga_loca ped_solic_cabec.val_tota_paga_loca%TYPE;
    v_num_lote           int_solic_conso_cabec.num_lote%TYPE;
    v_sec_nume_docu      int_solic_conso_cabec.sec_nume_docu%TYPE;
    v_cod_clie           int_solic_conso_cabec.cod_clie%TYPE;
    v_cod_peri           int_solic_conso_cabec.cod_peri%TYPE;
    v_niri_oid_nive_ries int_solic_conso_cabec.niri_oid_nive_ries%TYPE;
    v_ind_rece_onli      int_solic_conso_cabec.ind_rece_onli%TYPE;
    v_cod_vali           sto_param_valid.cod_vali%TYPE;
    v_cod_mens           sto_mensa_valid.cod_mens%TYPE;
    v_ind_hist_exce      sto_param_valid.ind_hist_exce%TYPE;
    v_ind_gest           sto_param_valid.ind_gest%TYPE;
  

    lsinforcuadre   VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_INFOR_CUADRE'),'N');
  
    lnoid_mens NUMBER := 0;
  
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
    --lsindlimiteventa   VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
    --                                                                          'STO_IND_LIMI_VENT_OE'),
    --                                     'S');
  
  
  
  
    lsoid_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    SELECT t.grpr_oid_grup_proc,
           t.clie_oid_clie,
           t.perd_oid_peri,
           t.fec_prog_fact,
           t.oid_soli_cabe,
           t.esso_oid_esta_soli,
           t.espe_oid_esta_pedi,
           t.val_tota_paga_loca,
           c.num_lote,
           c.sec_nume_docu,
           c.cod_clie,
           c.cod_peri,
           c.niri_oid_nive_ries,
           c.ind_rece_onli,
           NULL                 cod_vali,
           NULL                 cod_mens,
           NULL                 ind_hist_exce,
           NULL                 ind_gest
      INTO v_grpr_oid_grup_proc,
           v_clie_oid_clie,
           v_perd_oid_peri,
           v_fec_prog_fact,
           v_oid_soli_cabe,
           v_esso_oid_esta_soli,
           v_espe_oid_esta_pedi,
           v_val_tota_paga_loca,
           v_num_lote,
           v_sec_nume_docu,
           v_cod_clie,
           v_cod_peri,
           v_niri_oid_nive_ries,
           v_ind_rece_onli,
           v_cod_vali,
           v_cod_mens,
           v_ind_hist_exce,
           v_ind_gest
      FROM ped_solic_cabec       t,
           int_solic_conso_cabec c
     WHERE c.soca_oid_soli_cabe_refe = t.oid_soli_cabe
       AND t.oid_soli_cabe = pnoidsolictud;
  
    IF v_grpr_oid_grup_proc = 3 THEN
      UPDATE int_solic_conso_cabec consol
         SET consol.fec_prog_fact = v_fec_prog_fact
       WHERE consol.soca_oid_soli_cabe_refe = v_oid_soli_cabe;
    

    
  

    
      /*Ejecutando Stocks*/
      IF v_ind_rece_onli = '1' THEN
        ped_pkg_cuadr_ofert.ped_pr_stock_online(v_oid_soli_cabe);
        pq_plani.ped_pr_elimi_produ_alter_falta(v_oid_soli_cabe);
        pq_plani.ped_pr_gener_produ_alter_falta(v_oid_soli_cabe);
        UPDATE ped_solic_cabec
           SET fec_prog_fact = to_date('01/01/2000', 'dd/mm/yyyy')
         WHERE oid_soli_cabe = v_oid_soli_cabe;
      END IF;
    
    END IF;
  
    IF v_grpr_oid_grup_proc IN (1, 3) AND
       (v_esso_oid_esta_soli = 2 OR v_esso_oid_esta_soli = 3 OR
       v_espe_oid_esta_pedi = 3 OR v_espe_oid_esta_pedi = 2)
    
     THEN
    
      IF (v_esso_oid_esta_soli = 2 OR v_espe_oid_esta_pedi = 2) THEN
      
        lsoid_clasi      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_MTMI_OID_CLAS');
        lsoid_tipo_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_MTMA_OID_TICLAS');
      
        v_cod_vali := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_VAL_MON_MINI');
      
        IF ped_pkg_cuadr_ofert.ped_fn_devue_apro_gp2(v_oid_soli_cabe,
                                                     v_cod_vali,
                                                     pscodigopais) = 0 THEN
        
          DELETE FROM mae_clien_clasi a
           WHERE a.clas_oid_clas = lsoid_clasi
             AND a.tccl_oid_tipo_clasi = lsoid_tipo_clasi
             AND a.ctsu_oid_clie_tipo_subt IN
                 (SELECT b.oid_clie_tipo_subt
                    FROM mae_clien_tipo_subti b
                   WHERE b.clie_oid_clie = v_clie_oid_clie);
        
          UPDATE int_solic_conso_cabec consol
             SET consol.fec_prog_fact = v_fec_prog_fact,
                 consol.fec_modi      = SYSDATE,
                 consol.usu_modi      = psusuario,
                 consol.ind_val_mtmi  = '0',
                 consol.ind_erro_mtmi = '1',
                 consol.ind_ocs_proc  = '0',
                 consol.val_mont_pedi = v_val_tota_paga_loca
           WHERE consol.soca_oid_soli_cabe_refe = v_oid_soli_cabe;
        
        END IF;
      
      END IF;
    
      IF (v_esso_oid_esta_soli = 3 OR v_espe_oid_esta_pedi = 3) THEN
      
        v_cod_vali := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_VAL_MON_MAXI');
      
        IF ped_pkg_cuadr_ofert.ped_fn_devue_apro_gp2(v_oid_soli_cabe,
                                                     v_cod_vali,
                                                     pscodigopais) = 0 THEN
        
          UPDATE int_solic_conso_cabec consol
             SET consol.fec_prog_fact      = v_fec_prog_fact,
                 consol.fec_modi           = SYSDATE,
                 consol.usu_modi           = psusuario,
                 consol.ind_val_mtma       = '0',
                 consol.niri_oid_nive_ries = NULL,
                 consol.ind_erro_mtma      = '1',
                 consol.ind_ocs_proc       = '0',
                 consol.val_mont_pedi      = v_val_tota_paga_loca
           WHERE consol.soca_oid_soli_cabe_refe = v_oid_soli_cabe;
        
        END IF;
      
      END IF;
    
      IF ped_pkg_cuadr_ofert.ped_fn_devue_apro_gp2(v_oid_soli_cabe,
                                                   v_cod_vali,
                                                   pscodigopais) = 0 THEN
      
        v_cod_mens := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                             'OCC',
                                                             v_cod_vali);
        SELECT p.ind_hist_exce,
               ind_gest
          INTO v_ind_hist_exce,
               v_ind_gest
          FROM sto_param_valid p
         WHERE p.cod_pais = pscodigopais
           AND p.cod_tipo_docu = 'OCC'
           AND p.cod_vali = v_cod_vali;
      
        IF v_ind_hist_exce = '1' THEN
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
             cod_mens,
             sec_nume_docu_cabe,
             oid_audi_exce,
             cod_acci)
          VALUES
            (pscodigopais,
             'OCC',
             v_num_lote,
             v_cod_vali,
             v_sec_nume_docu,
             to_char(SYSDATE, 'YYYYMMDDHHMISS'),
             v_ind_gest,
             SYSDATE,
             psusuario,
             v_cod_mens,
             NULL,
             sto_auex_seq.nextval,
             'PE');
        END IF;
      
        DELETE sto_detal_docum_excep
         WHERE sec_nume_docu = v_sec_nume_docu
           AND num_lote = v_num_lote
           AND cod_vali = v_cod_vali
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
           v_num_lote,
           v_cod_vali,
           v_sec_nume_docu,
           v_ind_gest,
           SYSDATE,
           psusuario,
           SYSDATE,
           psusuario,
           v_cod_mens);
      
        UPDATE sto_docum_digit a
           SET a.ind_envi = '0',
               a.ind_rech = '0',
               a.fec_modi = SYSDATE,
               a.usu_modi = psusuario
         WHERE a.num_lote = v_num_lote
           AND a.sec_nume_docu = v_sec_nume_docu;
      
        UPDATE sto_docum_digit a
           SET a.ind_envi = '0',
               a.ind_rech = '0',
               a.fec_modi = SYSDATE,
               a.usu_modi = psusuario
         WHERE a.num_lote = v_num_lote
           AND a.sec_nume_docu_cabe = v_sec_nume_docu;
      
        ped_pkg_cuadr_ofert.ped_pr_retor_stock_solic(pnoidsolictud);
      
        if lsinforcuadre='S' then
              delete from TMP_CUADR_OFERT where sec_nume_docu=v_sec_nume_docu;
              
              Insert into TMP_CUADR_OFERT
                 (CLIE_OID_CLIE, 
                  PERD_OID_PERI, 
                  OID_SOLI_CABE, 
                  OID_SOLI_POSI, 
                  VAL_CODI_VENT, 
                  NUM_UNID, 
                  NUM_UNID_CUAD, 
                  VAL_ESTA_POSI, 
                  VAL_OBSE, 
                  IND_LIMI_VENT, 
                  COD_ESTA_POSI, 
                  OFDE_OID_DETA_OFER, 
                  OFER_OID_OFER, 
                  COES_OID_ESTR, 
                  OCAT_OID_CATA, 
                  NUM_PAGI_CATA, 
                  VAL_FACT_REPE, 
                  PRECIO_UNITARIO, 
                  VAL_PREC_CATA, 
                  GOFE_OID_GRUP_OFER, 
                  FOPA_OID_FORM_PAGO, 
                  SEC_NUME_DOCU, 
                  PROD_OID_PROD, 
                  VAL_HASH, 
                  TIP_PEDI)
                select      a.clie_oid_clie,
                              a.perd_oid_peri,
                              a.oid_soli_cabe,
                              b.oid_soli_posi,
                              b.val_codi_vent,
                              b.num_unid_dema,
                              b.num_unid_por_aten,
                              b.espo_oid_esta_posi,
                              b.val_obse,
                              b.ind_limi_vent,
                              b.espo_oid_esta_posi,
                              b.ofde_oid_deta_ofer,
                              NULL,
                              NULL,
                              NULL,
                              NULL,
                              NULL,
                              b.val_prec_cata_unit_loca,
                              b.val_prec_cata_unit_loca,
                              NULL,
                              NULL,
                              c.sec_nume_docu,
                              b.prod_oid_prod,
                              NULL,
                              1
                from ped_solic_cabec a, ped_solic_posic b, int_solic_conso_cabec c
                where a.oid_soli_cabe=b.soca_oid_soli_cabe
                and c.soca_oid_soli_cabe_refe=a.oid_soli_cabe
                and a.oid_soli_cabe=pnoidsolictud;

        end if;


        DELETE FROM car_soli_entr_bloq a
         WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE ped_solic_posic p
         WHERE soca_oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE FROM inc_solic_concu_recom
         WHERE soca_oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE FROM inc_solic_concu_punta
         WHERE soca_oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE FROM ped_solic_mensa
         WHERE soca_oid_soli_cabe = v_oid_soli_cabe;
      
        --ELIMINAMOS INFORMACION DE NUEVO MAV
        sto_pkg_proce_gener.sto_pr_elimi_mav_envio(v_oid_soli_cabe);
      
        DELETE FROM mav_solic_envio
         WHERE soca_oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE ped_solic_cabec p WHERE oid_soli_cabe = v_oid_soli_cabe;
      
        DELETE inc_premi_elegi x
         WHERE v_clie_oid_clie = x.clie_oid_clie
           AND EXISTS
         (SELECT 1
                  FROM int_solic_conso_detal det
                 WHERE v_cod_clie = det.cod_clie
                   AND v_num_lote = det.num_lote
                   AND v_cod_peri = det.cod_peri
                   AND det.copa_oid_para_gral = x.copa_oid_para_gral
                   AND det.panp_oid_para_nive_prem =
                       x.panp_oid_para_nive_prem
                   AND det.num_prem = x.num_prem);
      
        --Reversión Ped_solic_cabec_acum2
      
        /*SELECT NVL(COUNT(*),0), NVL(SUM( e.num_unid_dema_real * e.val_prec_cata_unit_loca ),0)
        INTO v_Detalles, v_MontoCatDeman
        FROM ped_solic_cabec a,
                 ped_solic_cabec b,
                 ped_tipo_solic_pais c,
                 ped_tipo_solic d,
                 ped_solic_posic e
        WHERE   a.clie_oid_clie          = v_clie_oid_clie
                     AND a.perd_oid_peri = v_perd_oid_peri
                     AND b.perd_oid_peri = a.perd_oid_peri
                     AND a.soca_oid_soli_cabe = b.oid_soli_cabe
                     AND e.soca_oid_soli_cabe = a.oid_soli_cabe
                     AND a.tspa_oid_tipo_soli_pais = c.oid_Tipo_Soli_Pais
                     AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                     AND d.COD_TIPO_SOLI = 'SOC'
                     AND a.fec_fact IS NOT NULL
                     AND e.espo_oid_esta_posi <> 2
                     AND b.esso_oid_esta_soli <> 4;
        
        if v_Detalles = 0 then
        
                  Delete from ped_solic_cabec_acum2 where clie_oid_clie = v_clie_oid_clie and perd_oid_peri = v_perd_oid_peri;
        
        else
        
                  Update ped_solic_cabec_acum2 set val_cant_pedi = 1, val_mont_tota = v_MontoCatDeman where clie_oid_clie = v_clie_oid_clie and perd_oid_peri = v_perd_oid_peri;
        
        end if;*/
        -------------------------------------------------
      END IF;
    
    END IF;
  
    UPDATE mae_clien_datos_adici a
       SET a.niri_oid_nive_ries = v_niri_oid_nive_ries
     WHERE a.clie_oid_clie = v_clie_oid_clie
       AND v_niri_oid_nive_ries IS NOT NULL;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_PROCE_ESPEC: ' || ls_sqlerrm);
  END ocr_pr_proce_espec;

  /***************************************************************************
  Descripcion       : Proceso de actualizacion de periodo de reclamos
  Fecha Creacion    : 29/03/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE ocr_pr_actua_peri_cdr(psoidperiodo NUMBER) IS
  
  BEGIN
  
    UPDATE ped_solic_cabec
       SET fec_prog_fact =
           (SELECT fec_fina FROM cra_perio WHERE oid_peri = psoidperiodo)
    ---select * from ped_solic_cabec
     WHERE oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec a,
                   cra_perio       b
             WHERE a.perd_oid_peri = b.oid_peri
               AND a.tspa_oid_tipo_soli_pais IN
                   (SELECT tsp.oid_tipo_soli_pais
                      FROM ped_tipo_solic_pais tsp,
                           ped_tipo_solic      ts,
                           v_gen_i18n_sicc     i
                     WHERE i.idio_oid_idio = 1
                       AND i.attr_enti = 'PED_TIPO_SOLIC'
                       AND i.val_oid = ts.oid_tipo_soli
                       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                       AND val_i18n LIKE 'R%ten%')
               AND a.fec_prog_fact <> b.fec_fina
               AND a.fec_fact IS NULL
               AND a.perd_oid_peri = psoidperiodo);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ocr_pr_actua_peri_cdr: ' || ls_sqlerrm);
    
  END ocr_pr_actua_peri_cdr;

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV esta como Faltante Anunciado  */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_falta_anunc_clien
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER
  ) RETURN NUMBER IS
  
    ls_count NUMBER;
  
  BEGIN
  
    SELECT COUNT(1)
      INTO ls_count
      FROM ped_gesti_stock      h,
           zon_zona             k,
           zon_regio            l,
           mae_clien_tipo_subti m,
           mae_clien_clasi      n,
           zon_terri_admin      q,
           zon_secci            r
     WHERE h.ofde_oid_deta_ofer = p_oidoferdetal
       AND h.ind_acti = 1
       AND h.val_limi_ctrl_vent = 0
       AND m.clie_oid_clie = p_oidclie
       AND q.oid_terr_admi = p_oidterradmin
       AND q.zscc_oid_secc = r.oid_secc
       AND r.zzon_oid_zona = k.oid_zona
       AND k.zorg_oid_regi = l.oid_regi
       AND k.ind_acti = 1
       AND l.ind_acti = 1
       AND n.ind_ppal = 1
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
  
    RETURN ls_count;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'gen_fn_devue_falta_anunc_clien: ' ||
                                ls_sqlerrm);
      END IF;
    
  END gen_fn_devue_falta_anunc_clien;

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV es compuesto                  */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_precio_compu
  (
    p_oidperi  NUMBER,
    pscodventa VARCHAR2
  ) RETURN NUMBER IS
  
    v_precio NUMBER := 0;
  
  BEGIN
  
    SELECT nvl(SUM(c.imp_prec_cata), 0)
      INTO v_precio
      FROM pre_matri_factu_cabec a,
           pre_ofert             b,
           pre_ofert_detal       c
     WHERE a.oid_cabe = b.mfca_oid_cabe
       AND b.oid_ofer = c.ofer_oid_ofer
       AND a.perd_oid_peri = p_oidperi
       AND b.coes_oid_estr IN
           (SELECT pe.oid_estr
              FROM pre_estra pe
             WHERE pe.cod_estr IN ('002', '006')) -- compuesta fija
       AND b.oid_ofer IN
           (SELECT c1.ofer_oid_ofer
              FROM pre_matri_factu_cabec a1,
                   pre_ofert             b1,
                   pre_ofert_detal       c1
             WHERE a1.oid_cabe = b1.mfca_oid_cabe
               AND b1.oid_ofer = c1.ofer_oid_ofer
               AND a1.perd_oid_peri = a.perd_oid_peri
               AND c1.val_codi_vent = pscodventa
               AND b1.coes_oid_estr IN
                   (SELECT pe.oid_estr
                      FROM pre_estra pe
                     WHERE pe.cod_estr IN ('002', '006')));
  
    RETURN v_precio;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'gen_fn_devue_precio_compu: ' || ls_sqlerrm);
      END IF;
    
  END gen_fn_devue_precio_compu;

  /**************************************************************************/
  /* Descripcion    : Valida si es que el CUV tiene limite de ventas        */
  /* Autor          : Aurelio Oviedo                                        */
  /* Fecha          : 21/03/2013                                            */
  /**************************************************************************/
  FUNCTION gen_fn_devue_limit_venta_clien
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER
  ) RETURN NUMBER IS
  
    v_limite NUMBER := 0;
  
  BEGIN
  
    SELECT DISTINCT unidades
      INTO v_limite
      FROM (SELECT h.val_limi_ctrl_vent AS unidades
              FROM ped_gesti_stock      h,
                   zon_zona             k,
                   zon_regio            l,
                   mae_clien_tipo_subti m,
                   mae_clien_clasi      n,
                   zon_terri_admin      q,
                   zon_secci            r
             WHERE h.ofde_oid_deta_ofer = p_oidoferdetal
               AND h.ind_acti = 1
               AND h.val_limi_ctrl_vent > 0
               AND m.clie_oid_clie = p_oidclie
               AND q.oid_terr_admi = p_oidterradmin
               AND q.zscc_oid_secc = r.oid_secc
               AND r.zzon_oid_zona = k.oid_zona
               AND k.zorg_oid_regi = l.oid_regi
               AND k.ind_acti = 1
               AND l.ind_acti = 1
               AND n.ind_ppal = 1
               AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
               AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie OR
                   h.ticl_oid_tipo_clie IS NULL)
               AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie OR
                   h.sbti_oid_subt_clie IS NULL)
               AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas OR
                   h.tccl_oid_tipo_clas IS NULL)
               AND (n.clas_oid_clas = h.clas_oid_clas OR
                   h.clas_oid_clas IS NULL)
               AND (l.oid_regi = h.zorg_oid_regi OR h.zorg_oid_regi IS NULL)
               AND (k.oid_zona = h.zzon_oid_zona OR h.zzon_oid_zona IS NULL)
            UNION
            SELECT h.val_unid AS unidades
              FROM ped_gesti_stock      h,
                   zon_zona             k,
                   zon_regio            l,
                   mae_clien_tipo_subti m,
                   mae_clien_clasi      n,
                   zon_terri_admin      q,
                   zon_secci            r
             WHERE h.ofde_oid_deta_ofer = p_oidoferdetal
               AND h.ind_acti = 1
               AND h.val_limi_ctrl_vent IS NULL
               AND h.val_unid >= 0
               AND m.clie_oid_clie = p_oidclie
               AND q.oid_terr_admi = p_oidterradmin
               AND q.zscc_oid_secc = r.oid_secc
               AND r.zzon_oid_zona = k.oid_zona
               AND k.zorg_oid_regi = l.oid_regi
               AND k.ind_acti = 1
               AND l.ind_acti = 1
               AND n.ind_ppal = 1
               AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
               AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie OR
                   h.ticl_oid_tipo_clie IS NULL)
               AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie OR
                   h.sbti_oid_subt_clie IS NULL)
               AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas OR
                   h.tccl_oid_tipo_clas IS NULL)
               AND (n.clas_oid_clas = h.clas_oid_clas OR
                   h.clas_oid_clas IS NULL)
               AND (l.oid_regi = h.zorg_oid_regi OR h.zorg_oid_regi IS NULL)
               AND (k.oid_zona = h.zzon_oid_zona OR h.zzon_oid_zona IS NULL));
  
    RETURN v_limite;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'gen_fn_devue_limit_venta_clien: ' ||
                                ls_sqlerrm);
      END IF;
    
  END gen_fn_devue_limit_venta_clien;

END ocr_solic_pedidos;
/
