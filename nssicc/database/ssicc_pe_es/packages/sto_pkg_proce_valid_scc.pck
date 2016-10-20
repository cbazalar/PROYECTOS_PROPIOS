CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_scc AS

  /* Declaracion de Variables */

  rows NUMBER := 5000;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_VALI_PAIS_OK
                    Procedimiento de Validacion de Pais Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008
  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TERR_UBIG_OK
                    Procedimiento de Validacion de Territorio de Ubigeo
          del cliente Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_terr_ubig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_UNI_ADMI_OK
                    Procedimiento de Validacion de Unidad Administrativa
          del cliente Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_uni_admi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_PERI_OK
                    Procedimiento de Validacion de Periodo
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_peri
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_NEG_PREM_OK
                    Procedimiento de Validacion de Negativa del Premio y Recomendante
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_neg_prem
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_POS_PREM_OK
                    Procedimiento de Validacion de Positiva del Premio y Recomendante
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_pos_prem
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_PREM_RECOM_OK
                    Procedimiento de Validacion del Premio de Recomendacion
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_prem_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DNI_CLIE_OK
                    Procedimiento de Validacion de DNI de nuevo Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dni_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_RUC_CLIE_OK
                    Procedimiento de Validacion de DNI de nuevo Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_ruc_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_EDAD_MINI_OK
                    Procedimiento de Validacion de edad minima Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_edad_mini
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_EDAD_MAXI_OK
                    Procedimiento de Validacion de edad maxima Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_edad_maxi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_CLIE_OK
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_tele_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
  /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_CLIE_DIFE
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 06/01/2016


  Autor             : Rosalvina Ramirez
  **************************************************************************/
  PROCEDURE sto_pr_scc_tele_clie_dife
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );  

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_POSI_OK
                    Procedimiento de Validacion de direccion positiva  de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_direc_posi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_NEGA_OK
                    Procedimiento de Validacion de direccion negativa de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_direc_nega
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_ZONA_ACTI_OK
                    Procedimiento de Validacion de zona activa de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_zona_acti
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DATO_POSI_OK
                    Procedimiento de Validacion positiva de datos del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dato_posi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DATO_NEGA_OK
                    Procedimiento de Validacion negativa de datos del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dato_nega
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_FIAD_OK
                    Procedimiento de Validacion de direccion  del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_direc_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_FIAD_OK
                    Procedimiento de Validacion de telefono del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_tele_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DNI_RUC_OK
                    Procedimiento de Validacion de DNI Y RUC  de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dni_ruc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_COD_CLIE_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_cod_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DOCU_RECHA_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/09/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_docu_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIRE2_FIAD_OK
                    Procedimiento de Validacion de la direccion del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/10/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dire2_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_COD_CLIE_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_cod_clie_dupl
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion OK de  Validacion del Codigo de Recomendante
  Fecha Creacion    : 08/01/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_scc_codig_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Indicadores Manuales
  Fecha Creacion    : 07/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_indic_manua
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion negativa de la direccion de entrega del cliente
  Fecha Creacion    : 08/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_direc_entre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion del telefono de entrega del cliente
  Fecha Creacion    : 08/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_telef_entre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Referencia familiar
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer_famil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Referencia no familiar
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer_nofam
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Referencia
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de tipo de documento
   Fecha Creacion    : 04/02/2010
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_tipo_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Validacion de Nueva en segundo dia
   Fecha Creacion    : 26/04/2010
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_nueva_sedia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion Tipo Via  de la Direccion del Cliente
  Fecha Creacion    : 02/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_tipvi_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de consultora Aval de consultora con deuda
  Fecha Creacion    : 11/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_avala_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de DNI Duplicado
  Fecha Creacion    : 14/10/2010
  Autor             : Jose Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_scc_dni_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
    Descripcion       : Validacion Nivel Educativo Estado Civil
    Fecha Creacion    : 09/09/2010
    Autor             : Jose Cairampma
  **************************************************************************/
  PROCEDURE sto_pr_scc_niedu_esciv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de zona de arribo
  Fecha Creacion    : 19/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_zonas_arribo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion Modulo diez del codigo de Cliente
  Fecha Creacion    : 08/11/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_modul_diez
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Numero de Cedula de las consultoras solo
                      acepte numeros y no acepte ceros a la izquierda.
  Fecha Creacion    : 16/03/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_cedul
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion del numero de DNI, solo acepta numeros
  Fecha Creacion    : 22/02/2012
  Autor             : Ivan Tocto
  **************************************************************************/
  PROCEDURE sto_pr_scc_dni_numer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion del numero de RUC, solo acepta numeros
  Fecha Creacion    : 22/02/2012
  Autor             : Ivan Tocto
  **************************************************************************/
  PROCEDURE sto_pr_scc_ruc_numer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_NUMER_DOCUM_MOD11
                    Procedimiento de documento de identidad con modulo 11
  Fecha Creacion    : 25/07/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_docum_mod11
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SCC_BOLET_ELECT
                       Procedimiento de Boletin Comercial
   Fecha Creacion    : 04/09/2012
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bolet_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Formato de Documento de Identidad.
  Fecha Creacion    : 12/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE sto_pr_scc_forma_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
    Descripcion       : STO_PR_SIM_CODIG_CLIEN
                        Procedimiento de Validacion del codigo del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_ANTIG_CLIEN
                        Procedimiento de Validacion de Antiguedad del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_antig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_EXIST_METRE
                        Procedimiento de Validacion de Meta Existente
                        ya registrada
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_exist_metre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_MONTO_MXMTA
                        Procedimiento de Validacion de Monto Maximo de Meta
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_monto_mxmta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CAMPA_INICI
                        Procedimiento de Validacion de Campa?a de Inicio
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_campa_inici
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_DATOS_OBLIG
                        Procedimiento de Validacion de Datos Obligatorios
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
    Descripcion       : STO_PR_SIM_INDIC_RECHA
                        Procedimiento de Validacion de Rechazo OCR
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_indic_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion         : STO_PR_SIM_ENVIO_SICC
                        Envio de SIM a SICC
  Fecha Creacion      : 03/03/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion de similitud de direcciones
  Fecha Creacion    : 03/05/2013
  Autor             : Aurelio Oviedo
  **************************************************************************/
  PROCEDURE sto_pr_scc_simil_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion           : Validacion de Datos Obligatorios
  Fecha Creacion    : 04/08/2013
  Autor                    : Sebastian Guerra
  **************************************************************************/
  PROCEDURE sto_pr_scc_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  PROCEDURE sto_pr_scc_clien_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion de referida en incobrable
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  PROCEDURE sto_pr_scc_refer_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de referida
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bloqu_refer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de numero de Ruc
  Fecha Creacion    : 14/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_ruc_mod11
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DOCU_RECHA_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/09/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_docu_recha_gis
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SCC_FORM_CEDU
                      Procedimiento de Validacion de cedula que cumpla
                      con el formato de 2 guiones
  Fecha Creacion    : 04/10/2013
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  **************************************************************************/
  PROCEDURE sto_pr_scc_form_cedu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion Lider Reconocimiento
  Fecha Creacion    : 15/10/2013
  **************************************************************************/
  PROCEDURE sto_pr_scc_lider_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
 /**************************************************************************
  Descripcion       : STO_PR_SCC_NOMB_APEL
                      Procedimiento de Validacion de primer nombre, primer apellido
  Fecha Creacion    : 26/01/2015
  --------------------------------------------------------------------------
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_nomb_apel
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
 /**************************************************************************
  Descripcion       : STO_PR_SCC_CEDU_REFE_NUEV
                      Procedimiento de Validacion de cedula de referida y nueva cliente
  Fecha Creacion    : 30/03/2015
  --------------------------------------------------------------------------
  Autor             : Aurelio Oviedo
  **************************************************************************/
  
  PROCEDURE STO_PR_SCC_CEDU_REFE_NUEV
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_cedu_iden
                      Procedimiento de Validacion de documento de identidad
  Fecha Creacion    : 27/05/2015
  --------------------------------------------------------------------------
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_cedu_iden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_nacio
                      Procedimiento de Validacion de nacionalidad
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_nacio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_esta_civil
                      Procedimiento de Validacion de tipo documento
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_esta_civil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ); 
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_sexo
                      Procedimiento de Validacion de tipo documento
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_sexo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_tipo_perso
                      Procedimiento de Validacion tipo de persona
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_tipo_perso
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_orig_ingre
                      Procedimiento de Validacion origen de ingreso
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_orige_ingre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );     
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_terri_corre
                      Procedimiento de Validacion codigo territorial corresponde
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_terri_corre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ); 
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_ddom_dent
                      Procedimiento de Validacion indicador direccion de domicilio 
                      igual direccion de entrega
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_scc_ddom_dent
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );       
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_dir_dom
                      Procedimiento de Validacion de barrio y referencia
                      igual direccion de entrega
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_scc_dir_dom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_email_clie
                      Procedimiento de Validacion de email
  Fecha Creacion    : 06/01/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/      
  
  PROCEDURE sto_pr_scc_email_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ); 
  
END sto_pkg_proce_valid_scc;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_scc IS
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  /* Constantes */
  cantidad_periodos CONSTANT NUMBER := 3;
  /**************************************************************************
  Descripcion       : STO_PR_SCC_VALI_PAIS_OK
                    Procedimiento de Validacion de Pais Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validapais IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             pais.oid_pais
        FROM seg_pais              pais,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pais.cod_pais = cons.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validapais;
    LOOP
      FETCH c_validapais BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_credi
             SET oid_pais = v_oid_pais(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_validapais%NOTFOUND;
    END LOOP;
    CLOSE c_validapais;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123, 'STO_PR_SCC_PAIS: ' || ls_sqlerrm);

  END sto_pr_scc_pais;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TERR_UBIG_OK
                    Procedimiento de Validacion de Territorio de Ubigeo
          del cliente Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_terr_ubig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_territorio IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.uni_admi,
             cons.oid_pais
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_credi.uni_admi%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_uni_admi      t_uni_admi;
    v_oid_pais      t_oid_pais;
    rows            NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    tmp BINARY_INTEGER := 0;

    ls_oid_zona            zon_zona.oid_zona%TYPE;
    ls_cod_regi            zon_regio.cod_regi%TYPE;
    existe                 BOOLEAN := TRUE;
    ls_oid_terri           zon_terri.oid_terr%TYPE;
    lsparageneestrugeo     sto_param_gener_occrr.val_param%TYPE;
    ls_vp_oid_estru_geo    zon_terri.vepo_oid_valo_estr_geop%TYPE;
    ls_order1              zon_valor_estru_geopo.orde_1%TYPE;
    ls_order2              zon_valor_estru_geopo.orde_2%TYPE;
    ls_order3              zon_valor_estru_geopo.orde_3%TYPE;
    ls_order4              zon_valor_estru_geopo.orde_4%TYPE;
    vsindicadorcopiafiador sto_param_gener_occrr.val_param%TYPE;
  BEGIN

    lsparageneestrugeo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_GENER_ESTR_GEO');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    SELECT val_param
      INTO vsindicadorcopiafiador
      FROM sto_param_gener_occrr
     WHERE cod_pais = pscodigopais
       AND cod_para = 'STO_GENER_GEO_FIAD';

    OPEN c_territorio;
    LOOP
      FETCH c_territorio BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_uni_admi,
             v_oid_pais LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          BEGIN
            SELECT terri.oid_terr
              INTO ls_oid_terri
              FROM zon_terri terri
             WHERE terri.cod_terr = to_number(substr(v_uni_admi(j), 8, 6))
               AND nvl(terri.ind_borr, 0) = 0
               AND terri.pais_oid_pais = v_oid_pais(j);
          EXCEPTION
            WHEN value_error OR invalid_number THEN
              ls_oid_terri := NULL;
            WHEN no_data_found THEN
              ls_oid_terri := NULL;
          END;

          IF (ls_oid_terri IS NOT NULL) THEN

            -- FORALL i IN 1 .. v_codpais.COUNT
            UPDATE int_solic_conso_credi
               SET oid_terr = ls_oid_terri
             WHERE sec_nume_docu = v_sec_nume_docu(j)
               AND num_lote = v_numlote(j);

            -- Actualizamos Documentos Validados OK

            existe := TRUE;
            IF (substr(v_uni_admi(j), 3, 4) IS NOT NULL) THEN

              BEGIN
                SELECT oid_zona
                  INTO ls_oid_zona
                  FROM zon_zona
                 WHERE cod_zona = substr(v_uni_admi(j), 3, 4);

                IF (ls_oid_zona IS NOT NULL AND ls_oid_terri IS NOT NULL) THEN

                  BEGIN
                    SELECT DISTINCT d.cod_regi
                      INTO ls_cod_regi
                      FROM zon_terri_admin a,
                           zon_secci       b,
                           zon_zona        c,
                           zon_regio       d
                     WHERE a.zscc_oid_secc = b.oid_secc
                       AND b.zzon_oid_zona = c.oid_zona
                       AND c.zorg_oid_regi = d.oid_regi
                       AND a.ind_borr = '0'
                       AND b.ind_acti = '1'
                       AND c.ind_acti = '1'
                       AND d.ind_acti = '1'
                       AND a.oid_terr_admi IN
                           (SELECT oid_terr_admi
                              FROM zon_terri_admin
                             WHERE terr_oid_terr = ls_oid_terri
                               AND pais_oid_pais = v_oid_pais(j))
                       AND c.oid_zona = ls_oid_zona;

                    UPDATE sto_docum_digit stod
                       SET stod.cod_regi           = ls_cod_regi,
                           stod.cod_zona           = substr(v_uni_admi(j),
                                                            3,
                                                            4),
                           stod.cod_ulti_vali_ejec = pscodigovalidactual,
                           stod.cod_ulti_vali_exit = pscodigovalidactual,
                           stod.usu_modi           = psusuario,
                           stod.fec_modi           = SYSDATE
                     WHERE stod.cod_pais = pscodigopais
                       AND stod.cod_tipo_docu = pscodigotipodoc
                       AND stod.num_lote = v_numlote(j)
                       AND stod.sec_nume_docu = v_sec_nume_docu(j);

                    IF (lsparageneestrugeo = '1') THEN
                      BEGIN
                        SELECT vepo_oid_valo_estr_geop
                          INTO ls_vp_oid_estru_geo
                          FROM zon_terri
                         WHERE pais_oid_pais = v_oid_pais(j)
                           AND oid_terr = ls_oid_terri
                           AND ind_borr = 0;

                      EXCEPTION
                        WHEN no_data_found THEN
                          ls_vp_oid_estru_geo := NULL;
                      END;

                      IF (ls_vp_oid_estru_geo IS NOT NULL) THEN

                        BEGIN
                          SELECT orde_1,
                                 orde_2,
                                 orde_3,
                                 orde_4
                            INTO ls_order1,
                                 ls_order2,
                                 ls_order3,
                                 ls_order4
                            FROM zon_valor_estru_geopo
                           WHERE ind_borr = 0
                             AND oid_valo_estr_geop = ls_vp_oid_estru_geo;

                        EXCEPTION
                          WHEN no_data_found THEN
                            ls_order1 := NULL;
                            ls_order2 := NULL;
                            ls_order3 := NULL;
                            ls_order4 := NULL;
                        END;

                        UPDATE int_solic_conso_credi c
                           SET c.cod_depa_clie = ls_order1,
                               c.cod_prov_clie = ls_order2,
                               c.cod_dist_clie = ls_order3,
                               -----------Fiador-----------
                               c.cod_depa_fiad = decode(vsindicadorcopiafiador,
                                                        1,
                                                        ls_order1,
                                                        NULL),
                               c.cod_prov_fiad = decode(vsindicadorcopiafiador,
                                                        1,
                                                        ls_order2,
                                                        NULL),
                               c.cod_dist_fiad = decode(vsindicadorcopiafiador,
                                                        1,
                                                        ls_order3,
                                                        NULL)
                         WHERE c.sec_nume_docu = v_sec_nume_docu(j)
                           AND c.num_lote = v_numlote(j);

                        SELECT COUNT(*)
                          INTO tmp
                          FROM zon_valor_estru_geopo
                         WHERE orde_1 = ls_order1
                           AND orde_2 = ls_order2
                           AND orde_3 = ls_order3
                           AND orde_4 = nvl((SELECT cod_sect_clie
                                              FROM int_solic_conso_credi
                                             WHERE sec_nume_docu =
                                                   v_sec_nume_docu(j)),
                                            'XXX');

                        IF tmp = 0 THEN

                          UPDATE int_solic_conso_credi c
                             SET c.cod_sect_clie = ls_order4,
                                 -----------Fiador-----------
                                 c.cod_sect_fiad = decode(vsindicadorcopiafiador,
                                                          1,
                                                          ls_order4,
                                                          NULL)
                           WHERE c.sec_nume_docu = v_sec_nume_docu(j)
                             AND c.num_lote = v_numlote(j);

                        END IF;

                      END IF;

                    END IF;
                  EXCEPTION
                    WHEN OTHERS THEN
                      existe := FALSE;

                  END;

                END IF;

              EXCEPTION
                WHEN OTHERS THEN
                  existe := FALSE;
              END;

            END IF;
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_territorio%NOTFOUND;
    END LOOP;
    CLOSE c_territorio;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_TERR_UBIG: ' || ls_sqlerrm);

  END sto_pr_scc_terr_ubig;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_UNI_ADMI_OK
                    Procedimiento de Validacion de Unidad Administrativa
          del cliente Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_uni_admi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_unidadadministativa IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             terri_admin.oid_terr_admi
        FROM zon_terri_admin       terri_admin,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND terri_admin.terr_oid_terr = cons.oid_terr
         AND terri_admin.pais_oid_pais = cons.oid_pais
         AND cons.uni_admi IS NOT NULL
         AND terri_admin.ind_borr = '0';

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_oid_terr_admi t_oid_terr_admi;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_unidadadministativa;
    LOOP
      FETCH c_unidadadministativa BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_terr_admi LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_credi
             SET oid_terr_admi = v_oid_terr_admi(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_unidadadministativa%NOTFOUND;
    END LOOP;
    CLOSE c_unidadadministativa;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_UNI_ADMI: ' || ls_sqlerrm);

  END sto_pr_scc_uni_admi;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_PERI_OK
                      Procedimiento de Validacion de Periodo
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_peri
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_periodo IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_terr_admi oid_peri
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_terr_admi t_oid_terr_admi;

    v_oid_peri_1    NUMBER := 0;
    v_oid_peri_act  NUMBER := 0;
    v_codperi_1     VARCHAR2(6) := '';
    pscodigoperiodo VARCHAR2(6) := '';

    rows NATURAL := 10000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_periodo;
    LOOP
      FETCH c_periodo BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_terr_admi LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FOR i IN 1 .. v_codpais.count
        LOOP

          SELECT MIN(cod_peri)
            INTO pscodigoperiodo
            FROM bas_ctrl_fact
           WHERE ind_camp_act = 1
             AND sta_camp = 0;

          v_codperi_1  := per_pkg_repor_perce.per_fn_obtie_perio(pscodigoperiodo,
                                                                 gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                                                                 2003,
                                                                 2001,
                                                                 1);
          v_oid_peri_1 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_codperi_1);

          SELECT nvl(MAX(perd_oid_peri), 0)
            INTO v_oid_peri_act
            FROM fac_contr_cierr
           WHERE tcie_oid_tipo_cier = 2
             AND perd_oid_peri =
                 gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo)
             AND zzon_oid_zona =
                 (SELECT c.oid_zona
                    FROM zon_terri_admin a,
                         zon_secci       b,
                         zon_zona        c
                   WHERE a.zscc_oid_secc = b.oid_secc
                     AND b.zzon_oid_zona = c.oid_zona
                     AND a.oid_terr_admi = v_oid_terr_admi(i));

          IF v_oid_peri_act = 0 THEN
            v_codperi_1  := pscodigoperiodo;
            v_oid_peri_1 := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_codperi_1);
          END IF;

          UPDATE int_solic_conso_credi
             SET oid_peri = v_oid_peri_1,
                 cod_peri = v_codperi_1
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

          UPDATE sto_docum_digit
             SET cod_peri = v_codperi_1
           WHERE sec_nume_docu = v_sec_nume_docu(i);

        END LOOP;

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_periodo%NOTFOUND;
    END LOOP;
    CLOSE c_periodo;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123, 'STO_PR_SCC_PERI: ' || ls_sqlerrm);

  END sto_pr_scc_peri;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_NEG_PREM_OK
                    Procedimiento de Validacion de Negativa del Premio y Recomendante
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_neg_prem
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_premio IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((cons.cod_clie_rete IS NULL AND cons.cod_prem IS NULL) OR
             (cons.cod_clie_rete IS NOT NULL AND cons.cod_prem IS NOT NULL) OR
             (cons.cod_clie_rete IS NOT NULL AND cons.cod_prem IS NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_premio;
    LOOP
      FETCH c_premio BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_premio%NOTFOUND;
    END LOOP;
    CLOSE c_premio;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_NEG_PREM: ' || ls_sqlerrm);

  END sto_pr_scc_neg_prem;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_POS_PREM_OK
                    Procedimiento de Validacion de Positiva del Premio y Recomendante
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_pos_prem
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_premio IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((cons.cod_clie_rete IS NOT NULL AND cons.cod_prem IS NOT NULL) OR
             (cons.cod_clie_rete IS NULL AND cons.cod_prem IS NULL) OR
             (cons.cod_clie_rete IS NULL AND cons.cod_prem IS NOT NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_premio;
    LOOP
      FETCH c_premio BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_premio%NOTFOUND;
    END LOOP;
    CLOSE c_premio;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_POS_PREM: ' || ls_sqlerrm);

  END sto_pr_scc_pos_prem;

  /**************************************************************************
  Descripcion       : SSTO_PR_SCC_PREM_RECOM_OK
                    Procedimiento de Validacion del Premio de Recomendacion
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_prem_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_premio IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             ipa.panp_oid_para_nive_prem,
             ilpa.num_prem,
             icpg.oid_para_gral
        FROM inc_artic_lote        ial,
             inc_lote_premi_artic  ilpa,
             inc_premi_artic       ipa,
             inc_param_nivel_premi ipnp,
             inc_param_gener_premi ipgp,
             inc_concu_param_gener icpg,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_prem IS NOT NULL
         AND ial.lopa_oid_lote_prem_arti = ilpa.oid_lote_prem_arti
         AND ilpa.prar_oid_prem_arti = ipa.oid_prem_arti
         AND ipa.panp_oid_para_nive_prem = ipnp.oid_para_nive_prem
         AND ipnp.pagp_oid_para_gene_prem = ipgp.oid_para_gene_prem
         AND ipgp.copa_oid_para_gral = icpg.oid_para_gral
         AND icpg.ind_acti = 1
         AND icpg.bcal_oid_base_calc = 4
         AND ial.cod_vent_fict = cons.cod_prem
      UNION
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             NULL               panp_oid_para_nive_prem,
             NULL               num_prem,
             NULL               oid_para_gral
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_prem IS NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    TYPE t_oid_para_nive_prem IS TABLE OF int_solic_conso_credi.oid_para_nive_prem%TYPE;
    TYPE t_num_premi IS TABLE OF int_solic_conso_credi.num_premi%TYPE;
    TYPE t_oid_para_gral IS TABLE OF int_solic_conso_credi.oid_para_gral%TYPE;
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_para_nive_prem t_oid_para_nive_prem;
    v_num_premi          t_num_premi;
    v_oid_para_gral      t_oid_para_gral;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_premio;
    LOOP
      FETCH c_premio BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_para_nive_prem,
             v_num_premi,
             v_oid_para_gral LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_credi
             SET oid_para_nive_prem = v_oid_para_nive_prem(i),
                 num_premi          = v_num_premi(i),
                 oid_para_gral      = v_oid_para_gral(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_premio%NOTFOUND;
    END LOOP;
    CLOSE c_premio;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_PREM_RECOM: ' || ls_sqlerrm);

  END sto_pr_scc_prem_recom;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DNI_CLIE_OK
                   Procedimiento de Validacion de DNI de nuevo Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/

  PROCEDURE sto_pr_scc_dni_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_dnicliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.num_docu,
             cons.sec_nume_docu,
             cons.tip_docu,
             cons.num_docu_iden,
             cons.fec_naci,
             cons.cod_clie_rete,
             cons.cod_prem,
             cons.oid_para_nive_prem,
             cons.oid_para_gral,
             cons.oid_peri,
             cons.oid_terr_admi,
             cons.num_premi,
             cons.val_ape1_fiad, -- posicion 32
             cons.val_ape2_fiad, -- posicion 33
             cons.val_nom1_fiad, -- posicion 34
             cons.val_nom2_fiad, -- posicion 35
             cons.val_tefl_fiad, --posicion 40
             cons.val_celu_fiad, --posicion 41
             cons.val_telf_trfi, --posicion 42
             cons.cod_docu_idfi, --posicion 31
             cons.cod_fiad, --posicion 68
             cons.tip_via_fiad, --posicion 54
             cons.val_nomb_vifi, --posicion 55
             cons.num_dire_fiad, --posicion 56
             cons.cod_depa_fiad, --posicion 57
             cons.cod_prov_fiad, --posicion 58
             cons.cod_dist_fiad, --posicion 59
             cons.cod_sect_fiad, --posicon 60
             cons.val_dire_fiad, --posicion 36
             cons.tip_docu_fiad, --posicion 30             cons.val_barr_fiad, --Posicion 37
             cons.val_barr_fiad, --Posicion 37
             cons.val_ciud_fiad, -- Posicion 38
             cons.val_depa_fiad, -- Posicion 39
             cons.val_tipo_vinc_fiad, --Posicion 95
             cons.val_nomb_empr_fiad, -- Posicion 92
             cons.val_dire_empr_fiad, -- Posicion 93
             cons.val_carg_fiad, -- Posicion 94
             cons.val_nom1_refe_fami_clie, --Posicion 78
             cons.val_ape1_refe_fami_clie, --Posicion 77
             cons.val_dire_refe_fami_clie, --Posicion 79
             cons.val_barr_refe_fami_clie, --Posicion  80
             cons.val_ciud_refe_fami_clie, --Posicion  82
             cons.val_depa_refe_fami_clie, --Posicion  83
             cons.val_tele_refe_fami_clie, --Posicion  84
             cons.val_celu_refe_fami_clie, --Posicion  85
             cons.val_tipo_vinc_refe_fami_clie, --Posicion 86
             cons.val_ape1_refe_nofa_clie, --Posicion 87
             cons.val_nom1_refe_nofa_clie, --Posicion 88
             cons.val_tele_refe_nofa_clie, --Posicion 89
             cons.val_celu_refe_nofa_clie, --Posicion 90
             cons.val_tipo_vinc_refe_nofa_clie, --Posicion 91
             cons.val_dire_refe_nofa_clie, --Posicion 104
             cons.val_ape1,
             cons.val_ape2,
             cons.val_nom1,
             cons.val_nom2,
             cons.val_dire_clie,
             cons.tip_via_clie,
             cons.val_nomb_vicl,
             cons.cod_depa_clie,
             cons.cod_prov_clie,
             cons.cod_dist_clie,
             cons.cod_sect_clie,
             cons.val_barr_clie,
             cons.num_dire_clie,
             cons.oid_terr,
             cons.val_dire_entre_clie,
             cons.val_telf_clie,
             cons.val_celu_clie,
             cons.val_telf_trab,
             cons.val_mail_clie,
             cons.val_tele_entre_clie,
             cons.val_celu_entre_clie,
             cons.num_ruc,
             cons.ind_requ_impr_bole,
             cons.oid_tipo_pers,
             cons.oid_orig_ingr,
             cons.dom_manz,
             cons.dom_etap,
             cons.dom_call_prin,
             cons.dom_call_secu,
             cons.dom_num,
             cons.dom_refe,
             cons.ent_manz,
             cons.ent_etap,
             cons.ent_call_prin,
             cons.ent_call_secu,
             cons.ent_num,
             cons.ent_refe,
             cons.val_barr_entre_clie,
             cons.cod_terr_corr,
             cons.val_obse,
             cons.cod_lide_reco
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_credi.num_docu%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_fec_naci IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
    TYPE t_cod_clie_rete IS TABLE OF int_solic_conso_credi.cod_clie_rete%TYPE;
    TYPE t_cod_prem IS TABLE OF int_solic_conso_credi.cod_prem%TYPE;
    TYPE t_oid_para_nive_prem IS TABLE OF int_solic_conso_credi.oid_para_nive_prem%TYPE;
    TYPE t_oid_para_gral IS TABLE OF int_solic_conso_credi.oid_para_gral%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_credi.oid_peri%TYPE;
    TYPE t_oidterradmi IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;
    TYPE t_num_premi IS TABLE OF int_solic_conso_credi.num_premi%TYPE;

    TYPE t_primerapellfiador IS TABLE OF int_solic_conso_credi.val_ape1_fiad%TYPE;
    TYPE t_secondapellfiador IS TABLE OF int_solic_conso_credi.val_ape2_fiad%TYPE;
    TYPE t_primernomfiador IS TABLE OF int_solic_conso_credi.val_nom1_fiad%TYPE;
    TYPE t_secondnomfiador IS TABLE OF int_solic_conso_credi.val_nom2_fiad%TYPE;
    TYPE t_valtelefiador IS TABLE OF int_solic_conso_credi.val_tefl_fiad%TYPE;
    TYPE t_valcelufiador IS TABLE OF int_solic_conso_credi.val_celu_fiad%TYPE;
    TYPE t_valtrabfiador IS TABLE OF int_solic_conso_credi.val_telf_trfi%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_cod_fiad IS TABLE OF int_solic_conso_credi.cod_fiad%TYPE;
    TYPE t_tip_via_fiad IS TABLE OF int_solic_conso_credi.tip_via_fiad%TYPE;
    TYPE t_val_nomb_vifi IS TABLE OF int_solic_conso_credi.val_nomb_vifi%TYPE;
    TYPE t_num_dire_fiad IS TABLE OF int_solic_conso_credi.num_dire_fiad%TYPE;
    TYPE t_cod_depa_fiad IS TABLE OF int_solic_conso_credi.cod_depa_fiad%TYPE;
    TYPE t_cod_prov_fiad IS TABLE OF int_solic_conso_credi.cod_prov_fiad%TYPE;
    TYPE t_cod_dist_fiad IS TABLE OF int_solic_conso_credi.cod_dist_fiad%TYPE;
    TYPE t_cod_sect_fiad IS TABLE OF int_solic_conso_credi.cod_sect_fiad%TYPE;
    TYPE t_val_dire_fiad IS TABLE OF int_solic_conso_credi.val_dire_fiad%TYPE;
    TYPE t_tip_docu_fiad IS TABLE OF int_solic_conso_credi.tip_docu_fiad%TYPE;
    TYPE t_val_barr_fiad IS TABLE OF int_solic_conso_credi.val_barr_fiad%TYPE;
    TYPE t_val_ciud_fiad IS TABLE OF int_solic_conso_credi.val_ciud_fiad%TYPE;
    TYPE t_val_depa_fiad IS TABLE OF int_solic_conso_credi.val_depa_fiad%TYPE;
    TYPE t_val_tipo_vinc_fiad IS TABLE OF int_solic_conso_credi.val_tipo_vinc_fiad%TYPE;
    TYPE t_val_nomb_empr_fiad IS TABLE OF int_solic_conso_credi.val_nomb_empr_fiad%TYPE;
    TYPE t_val_dire_empr_fiad IS TABLE OF int_solic_conso_credi.val_dire_empr_fiad%TYPE;
    TYPE t_val_carg_fiad IS TABLE OF int_solic_conso_credi.val_carg_fiad%TYPE;
    TYPE t_val_nomb_refe_fami IS TABLE OF int_solic_conso_credi.val_nom1_refe_fami_clie%TYPE;
    TYPE t_val_apel_refe_fami IS TABLE OF int_solic_conso_credi.val_ape1_refe_fami_clie%TYPE;
    TYPE t_val_dire_refe_fami IS TABLE OF int_solic_conso_credi.val_dire_refe_fami_clie%TYPE;
    TYPE t_val_barr_refe_fami IS TABLE OF int_solic_conso_credi.val_barr_refe_fami_clie%TYPE;
    TYPE t_val_ciud_refe_fami IS TABLE OF int_solic_conso_credi.val_ciud_refe_fami_clie%TYPE;
    TYPE t_val_depa_refe_fami IS TABLE OF int_solic_conso_credi.val_depa_refe_fami_clie%TYPE;
    TYPE t_val_tele_refe_fami IS TABLE OF int_solic_conso_credi.val_tele_refe_fami_clie%TYPE;
    TYPE t_val_celu_refe_fami IS TABLE OF int_solic_conso_credi.val_celu_refe_fami_clie%TYPE;
    TYPE t_val_tipo_vinc_refe_fami IS TABLE OF int_solic_conso_credi.val_tipo_vinc_refe_fami_clie%TYPE;
    TYPE t_val_ape1_refe_nofa IS TABLE OF int_solic_conso_credi.val_ape1_refe_nofa_clie%TYPE;
    TYPE t_val_nomb_refe_nofa IS TABLE OF int_solic_conso_credi.val_nom1_refe_nofa_clie%TYPE;
    TYPE t_val_tele_refe_nofa IS TABLE OF int_solic_conso_credi.val_tele_refe_nofa_clie%TYPE;
    TYPE t_val_celu_refe_nofa IS TABLE OF int_solic_conso_credi.val_celu_refe_nofa_clie%TYPE;
    TYPE t_val_tipo_vinc_refe_nofa IS TABLE OF int_solic_conso_credi.val_tipo_vinc_refe_nofa_clie%TYPE;
    TYPE t_val_dire_refe_nofa IS TABLE OF int_solic_conso_credi.val_dire_refe_nofa_clie%TYPE;

    TYPE t_val_ape1 IS TABLE OF int_solic_conso_credi.val_ape1%TYPE;
    TYPE t_val_ape2 IS TABLE OF int_solic_conso_credi.val_ape2%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_solic_conso_credi.val_nom1%TYPE;
    TYPE t_val_nom2 IS TABLE OF int_solic_conso_credi.val_nom2%TYPE;

    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_credi.val_dire_clie%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_credi.val_nomb_vicl%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_credi.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_credi.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_credi.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_credi.cod_sect_clie%TYPE;

    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_credi.val_barr_clie%TYPE;

    TYPE t_num_dire_clie IS TABLE OF int_solic_conso_credi.num_dire_clie%TYPE;
    TYPE t_oid_terr IS TABLE OF int_solic_conso_credi.oid_terr%TYPE;

    TYPE t_val_dire_entre_clie IS TABLE OF int_solic_conso_credi.val_dire_entre_clie%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_credi.val_telf_clie%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_credi.val_celu_clie%TYPE;
    TYPE t_val_telf_trab IS TABLE OF int_solic_conso_credi.val_telf_trab%TYPE;
    TYPE t_val_mail_clie IS TABLE OF int_solic_conso_credi.val_mail_clie%TYPE;
    TYPE t_val_tele_entre_clie IS TABLE OF int_solic_conso_credi.val_tele_entre_clie%TYPE;
    TYPE t_val_celu_entre_clie IS TABLE OF int_solic_conso_credi.val_celu_entre_clie%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_credi.num_ruc%TYPE;
    TYPE t_ind_requ_impr_bole IS TABLE OF int_solic_conso_credi.ind_requ_impr_bole%TYPE;
    TYPE t_oid_tipo_pers   IS TABLE OF int_solic_conso_credi.oid_tipo_pers%TYPE; 
    TYPE t_oid_orig_ingr   IS TABLE OF int_solic_conso_credi.oid_orig_ingr%TYPE; 
    TYPE t_dom_manz        IS TABLE OF int_solic_conso_credi.dom_manz%TYPE;      
    TYPE t_dom_etap        IS TABLE OF int_solic_conso_credi.dom_etap%TYPE;      
    TYPE t_dom_call_prin   IS TABLE OF int_solic_conso_credi.dom_call_prin%TYPE;
    TYPE t_dom_call_secu   IS TABLE OF int_solic_conso_credi.dom_call_secu%TYPE; 
    TYPE t_dom_num         IS TABLE OF int_solic_conso_credi.dom_num%TYPE;       
    TYPE t_dom_refe        IS TABLE OF int_solic_conso_credi.dom_refe%TYPE;      
    TYPE t_ent_manz        IS TABLE OF int_solic_conso_credi.ent_manz%TYPE;      
    TYPE t_ent_etap        IS TABLE OF int_solic_conso_credi.ent_etap%TYPE;      
    TYPE t_ent_call_prin   IS TABLE OF int_solic_conso_credi.ent_call_prin%TYPE;
    TYPE t_ent_call_secu   IS TABLE OF int_solic_conso_credi.ent_call_secu%TYPE; 
    TYPE t_ent_num         IS TABLE OF int_solic_conso_credi.ent_num%TYPE;       
    TYPE t_ent_refe        IS TABLE OF int_solic_conso_credi.ent_refe%TYPE;     
    TYPE t_val_barr_entre_clie        IS TABLE OF int_solic_conso_credi.val_barr_entre_clie%TYPE;  
    TYPE t_cod_terr_corr              IS TABLE OF int_solic_conso_credi.cod_terr_corr%TYPE;    
    TYPE t_val_obse                   IS TABLE OF int_solic_conso_credi.val_obse%TYPE;      
    TYPE t_cod_lide_reco              IS TABLE OF int_solic_conso_credi.cod_lide_reco%TYPE;       

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_numdocu            t_numdocu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_tip_docu           t_tip_docu;
    v_num_docu_iden      t_num_docu_iden;
    v_fec_naci           t_fec_naci;
    v_cod_clie_rete      t_cod_clie_rete;
    v_cod_prem           t_cod_prem;
    v_oid_para_nive_prem t_oid_para_nive_prem;
    v_oid_para_gral      t_oid_para_gral;
    v_oid_peri           t_oid_peri;
    v_oidterradmi        t_oidterradmi;
    v_num_premi          t_num_premi;

    v_primerapellfiador       t_primerapellfiador;
    v_secondapellfiador       t_secondapellfiador;
    v_primernomfiador         t_primernomfiador;
    v_secondnomfiador         t_secondnomfiador;
    v_valtelefiador           t_valtelefiador;
    v_valcelufiador           t_valcelufiador;
    v_valtrabfiador           t_valtrabfiador;
    v_cod_docu_idfi           t_cod_docu_idfi;
    v_cod_fiad                t_cod_fiad;
    v_tip_via_fiad            t_tip_via_fiad;
    v_val_nomb_vifi           t_val_nomb_vifi;
    v_num_dire_fiad           t_num_dire_fiad;
    v_cod_depa_fiad           t_cod_depa_fiad;
    v_cod_prov_fiad           t_cod_prov_fiad;
    v_cod_dist_fiad           t_cod_dist_fiad;
    v_cod_sect_fiad           t_cod_sect_fiad;
    v_val_dire_fiad           t_val_dire_fiad;
    v_tip_docu_fiad           t_tip_docu_fiad;
    v_val_barr_fiad           t_val_barr_fiad;
    v_val_ciud_fiad           t_val_ciud_fiad;
    v_val_depa_fiad           t_val_depa_fiad;
    v_val_tipo_vinc_fiad      t_val_tipo_vinc_fiad;
    v_val_nomb_empr_fiad      t_val_nomb_empr_fiad;
    v_val_dire_empr_fiad      t_val_dire_empr_fiad;
    v_val_carg_fiad           t_val_carg_fiad;
    v_val_nomb_refe_fami      t_val_nomb_refe_fami;
    v_val_apel_refe_fami      t_val_apel_refe_fami;
    v_val_dire_refe_fami      t_val_dire_refe_fami;
    v_val_barr_refe_fami      t_val_barr_refe_fami;
    v_val_ciud_refe_fami      t_val_ciud_refe_fami;
    v_val_depa_refe_fami      t_val_depa_refe_fami;
    v_val_tele_refe_fami      t_val_tele_refe_fami;
    v_val_celu_refe_fami      t_val_celu_refe_fami;
    v_val_tipo_vinc_refe_fami t_val_tipo_vinc_refe_fami;
    v_val_ape1_refe_nofa      t_val_ape1_refe_nofa;
    v_val_nomb_refe_nofa      t_val_nomb_refe_nofa;
    v_val_tele_refe_nofa      t_val_tele_refe_nofa;
    v_val_celu_refe_nofa      t_val_celu_refe_nofa;
    v_val_tipo_vinc_refe_nofa t_val_tipo_vinc_refe_nofa;
    v_val_dire_refe_nofa      t_val_dire_refe_nofa;

    v_val_ape1 t_val_ape1;
    v_val_ape2 t_val_ape2;
    v_val_nom1 t_val_nom1;
    v_val_nom2 t_val_nom2;

    v_val_dire_clie t_val_dire_clie;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_cod_depa_clie t_cod_depa_clie;
    v_cod_prov_clie t_cod_prov_clie;
    v_cod_dist_clie t_cod_dist_clie;

    v_cod_sect_clie t_cod_sect_clie;

    v_val_barr_clie       t_val_barr_clie;
    v_num_dire_clie       t_num_dire_clie;
    v_oid_terr            t_oid_terr;
    v_val_dire_entre_clie t_val_dire_entre_clie;
    v_val_telf_clie       t_val_telf_clie;
    v_val_celu_clie       t_val_celu_clie;
    v_val_telf_trab       t_val_telf_trab;
    v_val_mail_clie       t_val_mail_clie;
    v_val_tele_entre_clie t_val_tele_entre_clie;
    v_val_celu_entre_clie t_val_celu_entre_clie;
    v_num_ruc             t_num_ruc;
    v_ind_requ_impr_bole  t_ind_requ_impr_bole;
    v_oid_tipo_pers    t_oid_tipo_pers;
    v_oid_orig_ingr    t_oid_orig_ingr;
    v_dom_manz         t_dom_manz;     
    v_dom_etap         t_dom_etap;     
    v_dom_call_prin    t_dom_call_prin;
    v_dom_call_secu    t_dom_call_secu;
    v_dom_num          t_dom_num;      
    v_dom_refe         t_dom_refe;     
    v_ent_manz         t_ent_manz;     
    v_ent_etap         t_ent_etap;     
    v_ent_call_prin    t_ent_call_prin;
    v_ent_call_secu    t_ent_call_secu;
    v_ent_num          t_ent_num;      
    v_ent_refe         t_ent_refe;
    v_val_barr_entre_clie t_val_barr_entre_clie;  
    v_cod_terr_corr    t_cod_terr_corr;
    v_val_obse         t_val_obse;
    v_cod_lide_reco    t_cod_lide_reco;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    is_valid BOOLEAN := TRUE;

    ind_prin_dni        NUMBER;
    l_oid_esta_clie     mae_clien_datos_adici.esta_oid_esta_clie%TYPE;
    l_ind_acti          mae_clien_datos_adici.ind_acti%TYPE;
    l_fec_naci          mae_clien_datos_adici.fec_naci%TYPE;
    l_oid_clie          mae_clien_ident.clie_oid_clie%TYPE;
    l_cod_clie          mae_clien.cod_clie%TYPE;
    l_oid_terr_admi     mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
    l_sec_nume_docu     int_solic_conso_credi.sec_nume_docu%TYPE;
    lstm_poid_clie_rete mae_clien_ident.clie_oid_clie%TYPE;
    lntipo              NUMBER;
    tmp_cont            NUMBER;
    v_oidpais           NUMBER(12);

    l_oid_clie_rete mae_clien_ident.clie_oid_clie%TYPE;

    lnoidclientercdte mae_clien.oid_clie%TYPE;

    crea_sad VARCHAR2(1) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_CREA_SAD'),
                                'S');

    numero_cedula VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_NMCAR_CED');

    indrechsolcred sto_param_gener_occrr.val_param%TYPE := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                                    'STO_SCC_RECHA_SOLIC'),
                                                               'N');
                                                               
    lsparteDireccSCWeb VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_DIREC_WEB'),'N');                                                               
    
    lspartedireccscwebPos number(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                'STO_DIREC_WEB_POS'),
                                           0);

    oid_peri_ant             cra_perio.oid_peri%TYPE;
    oid_peri_sig             cra_perio.oid_peri%TYPE;
    tmp_oid_clie             mae_clien.oid_clie%TYPE;
    contador_mae_clien_direc NUMBER := 0;
    lnidpais                 NUMBER;
    lnidmarca                NUMBER;
    lnidcanal                NUMBER;
    lnpasopedido             NUMBER;
    contador_mae_clien       NUMBER := 0;
    oid_terri_admin_tmp      mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;
    oid_terr_temp            mae_clien_direc.terr_oid_terr%TYPE;

    lsdireccionentrega sto_param_gener_occrr.val_param%TYPE;
    lstelefonoentrega  sto_param_gener_occrr.val_param%TYPE;
    lscelularentrega   sto_param_gener_occrr.val_param%TYPE;
    lnoidbloqcast      sto_param_gener_occrr.val_param%TYPE;

    f_existe NUMBER := 0;
    lsCampNew         VARCHAR2(1);
    l_oid_lide_reco   NUMBER;
    isvalid           NUMBER;
    
    lsparametrotipovinculider VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                   'STO_TIPO_VINCU_LIDER');

  BEGIN
    --Recuperamos el oid Pais,Marca,Canal,Periodo
    v_oidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');

    lsdireccionentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'COD_TIPO_DIRE_ENTR');

    lstelefonoentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'COD_TIPO_TELE_ENTR');

    lscelularentrega := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'COD_TIPO_CELU_ENTR');

    lnoidbloqcast := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'OID_TIPO_BLOQ_CAST');
                                                             
   select val_para into lsCampNew 
    from bas_param_pais where cod_pais =pscodigopais
    and cod_para='055'
    and cod_sist='OCR';                                                             


    OPEN c_dnicliente;
    LOOP
      FETCH c_dnicliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_numdocu,
             v_sec_nume_docu,
             v_tip_docu,
             v_num_docu_iden,
             v_fec_naci,
             v_cod_clie_rete,
             v_cod_prem,
             v_oid_para_nive_prem,
             v_oid_para_gral,
             v_oid_peri,
             v_oidterradmi,
             v_num_premi,
             v_primerapellfiador,
             v_secondapellfiador,
             v_primernomfiador,
             v_secondnomfiador,
             v_valtelefiador,
             v_valcelufiador,
             v_valtrabfiador,
             v_cod_docu_idfi,
             v_cod_fiad,
             v_tip_via_fiad,
             v_val_nomb_vifi,
             v_num_dire_fiad,
             v_cod_depa_fiad,
             v_cod_prov_fiad,
             v_cod_dist_fiad,
             v_cod_sect_fiad,
             v_val_dire_fiad,
             v_tip_docu_fiad,
             v_val_barr_fiad,
             v_val_ciud_fiad,
             v_val_depa_fiad,
             v_val_tipo_vinc_fiad,
             v_val_nomb_empr_fiad,
             v_val_dire_empr_fiad,
             v_val_carg_fiad,
             v_val_nomb_refe_fami,
             v_val_apel_refe_fami,
             v_val_dire_refe_fami,
             v_val_barr_refe_fami,
             v_val_ciud_refe_fami,
             v_val_depa_refe_fami,
             v_val_tele_refe_fami,
             v_val_celu_refe_fami,
             v_val_tipo_vinc_refe_fami,
             v_val_ape1_refe_nofa,
             v_val_nomb_refe_nofa,
             v_val_tele_refe_nofa,
             v_val_celu_refe_nofa,
             v_val_tipo_vinc_refe_nofa,
             v_val_dire_refe_nofa,
             v_val_ape1,
             v_val_ape2,
             v_val_nom1,
             v_val_nom2,
             v_val_dire_clie,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_val_barr_clie,
             v_num_dire_clie,
             v_oid_terr,
             v_val_dire_entre_clie,
             v_val_telf_clie,
             v_val_celu_clie,
             v_val_telf_trab,
             v_val_mail_clie,
             v_val_tele_entre_clie,
             v_val_celu_entre_clie,
             v_num_ruc, 
             v_ind_requ_impr_bole, 
             v_oid_tipo_pers,  
             v_oid_orig_ingr,  
             v_dom_manz,       
             v_dom_etap,       
             v_dom_call_prin,  
             v_dom_call_secu,  
             v_dom_num,        
             v_dom_refe,       
             v_ent_manz,       
             v_ent_etap,       
             v_ent_call_prin,  
             v_ent_call_secu,  
             v_ent_num,        
             v_ent_refe,
             v_val_barr_entre_clie,
             v_cod_terr_corr,
             v_val_obse,
             v_cod_lide_reco  
             LIMIT rows;
             
      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          is_valid := TRUE;
          IF (v_tip_docu(i) IS NULL OR v_num_docu_iden(i) IS NULL) THEN
            is_valid := FALSE;
          ELSE
            SELECT COUNT(1)
              INTO lntipo
              FROM mae_tipo_docum
             WHERE cod_tipo_docu = v_tip_docu(i)
               AND ind_doc_iden_fisc = 1;

            IF (lntipo = 0) THEN
              is_valid := FALSE;
            ELSE
              IF (numero_cedula IS NOT NULL AND
                 length(v_num_docu_iden(i)) < numero_cedula) THEN
                is_valid := FALSE;
              ELSE
                BEGIN
                  SELECT a.clie_oid_clie,
                         b.esta_oid_esta_clie,
                         b.ind_acti,
                         b.fec_naci,
                         d.ztad_oid_terr_admi,
                         c.cod_clie
                    INTO l_oid_clie,
                         l_oid_esta_clie,
                         l_ind_acti,
                         l_fec_naci,
                         l_oid_terr_admi,
                         l_cod_clie
                    FROM mae_clien_ident       a,
                         mae_clien_datos_adici b,
                         mae_clien             c,
                         mae_clien_unida_admin d
                   WHERE a.clie_oid_clie = c.oid_clie(+)
                     AND a.clie_oid_clie = b.clie_oid_clie(+)
                     AND a.clie_oid_clie = d.clie_oid_clie(+)
                     AND 1 = d.ind_acti(+)
                     AND a.tdoc_oid_tipo_docu =
                         (SELECT oid_tipo_docu
                            FROM mae_tipo_docum
                           WHERE cod_tipo_docu = v_tip_docu(i))
                     AND a.num_docu_iden = v_num_docu_iden(i);

                  --La reactivacion de la consultora se debe dar si solo los 5 campos son distintos a nulo.
                  IF (l_oid_clie IS NOT NULL AND
                     l_oid_esta_clie IS NOT NULL AND
                     l_ind_acti IS NOT NULL AND l_fec_naci IS NOT NULL AND
                     l_oid_terr_admi IS NOT NULL 
                     AND l_oid_esta_clie in (1,7)  AND l_ind_acti = '0' 
                     AND to_char(l_fec_naci, 'dd/MM/YYYY') = to_char(v_fec_naci(i), 'dd/MM/YYYY') 
                     AND crea_sad = 'S' -- parametro para crear el documento de actualizacion de datos
                     ) THEN

                    is_valid := FALSE;

                    UPDATE mae_clien_datos_adici
                       SET ind_acti           = 1,
                           niri_oid_nive_ries = nvl((SELECT oid_nive_ries
                                                      FROM car_nivri_secci
                                                     WHERE oid_secc =
                                                           (SELECT zscc_oid_secc
                                                              FROM zon_terri_admin
                                                             WHERE oid_terr_admi =
                                                                   l_oid_terr_admi)),
                                                    4),
                           cam_acti = v_codperi(i),
                           fec_acti = sysdate,
                           usu_acti = 'REIN_'||psusuario
                     WHERE clie_oid_clie = l_oid_clie;

                    SELECT seq_docu_sto.nextval
                      INTO l_sec_nume_docu
                      FROM dual;

                    INSERT INTO int_solic_conso_actua_datos
                      (cod_pais,
                       cod_comp,
                       cod_clie,
                       num_docu,
                       fec_proc,
                       uni_admi,
                       cod_peri,
                       val_ape1,
                       val_ape2,
                       val_nom1,
                       val_nom2,
                       tip_docu,
                       num_docu_iden,
                       num_ruc,
                       val_dire_clie,
                       val_barr_clie,
                       val_ciud_clie,
                       val_depa_clie,
                       val_telf_clie,
                       val_celu_clie,
                       val_telf_trab,
                       val_mail_clie,
                       val_zona_arri,
                       ind_stat_proc,
                       ind_moti_rech,
                       tip_via_clie,
                       val_nomb_vicl,
                       num_dire_clie,
                       cod_depa_clie,
                       cod_prov_clie,
                       cod_dist_clie,
                       cod_sect_clie,
                       oid_pais,
                       oid_terr,
                       oid_terr_admi,
                       oid_peri,
                       num_lote,
                       sec_nume_docu,
                       val_codi_post_clie,
                       val_dire_entre_clie,
                       val_barr_entre_clie,
                       val_dele_entre_clie,
                       val_depa_entre_clie,
                       val_tele_entre_clie,
                       val_celu_entre_clie,
                       val_dele_clie,
                       ind_vali_dnin_clie,
                       docu_cod_tipo_docu,
                       ind_dire,
                       cod_naci,      
                       cod_terr_corr, 
                       ind_dird_dire, 
                       dom_manz,      
                       dom_etap,      
                       dom_call_prin, 
                       dom_call_secu, 
                       dom_num,       
                       dom_refe,      
                       ent_manz,      
                       ent_etap,      
                       ent_call_prin, 
                       ent_call_secu, 
                       ent_num,       
                       ent_refe)
                      (SELECT cod_pais,
                              cod_comp,
                              l_cod_clie,
                              num_docu,
                              fec_proc,
                              uni_admi,
                              cod_peri,
                              val_ape1,
                              val_ape2,
                              val_nom1,
                              val_nom2,
                              tip_docu,
                              num_docu_iden,
                              num_ruc,
                              val_dire_clie,
                              val_barr_clie,
                              val_ciud_clie,
                              val_depa_clie,
                              val_telf_clie,
                              val_celu_clie,
                              val_telf_trab,
                              val_mail_clie,
                              val_zona_arri,
                              ind_stat_proc,
                              ind_moti_rech,
                              tip_via_clie,
                              val_nomb_vicl,
                              num_dire_clie,
                              cod_depa_clie,
                              cod_prov_clie,
                              cod_dist_clie,
                              cod_sect_clie,
                              oid_pais,
                              oid_terr,
                              oid_terr_admi,
                              oid_peri,
                              num_lote,
                              l_sec_nume_docu AS sec_nume_docu,
                              val_codi_post_clie,
                              val_dire_entre_clie,
                              val_barr_entre_clie,
                              val_dele_entre_clie,
                              val_depa_entre_clie,
                              val_tele_entre_clie,
                              val_celu_entre_clie,
                              val_dele_clie,
                              '1',
                              'SAD',
                              '1',
                               cod_naci,      
                               cod_terr_corr, 
                               ind_dird_dire, 
                               dom_manz,      
                               dom_etap,      
                               dom_call_prin, 
                               dom_call_secu, 
                               dom_num,       
                               dom_refe,      
                               ent_manz,      
                               ent_etap,      
                               ent_call_prin, 
                               ent_call_secu, 
                               ent_num,       
                               ent_refe
                         FROM int_solic_conso_credi
                        WHERE cod_pais = v_codpais(i)
                          AND num_docu = v_numdocu(i)
                          AND num_lote = v_numlote(i)
                          AND sec_nume_docu = v_sec_nume_docu(i));

                    INSERT INTO sto_docum_digit
                      (cod_pais,
                       cod_tipo_docu,
                       num_lote,
                       sec_nume_docu,
                       num_docu,
                       cod_ulti_vali_ejec,
                       cod_ulti_vali_exit,
                       cod_ulti_vali_erro,
                       ind_envi,
                       ind_rech,
                       fec_digi,
                       usu_digi,
                       fec_modi,
                       usu_modi,
                       cod_zona,
                       cod_clie,
                       cod_regi,
                       cod_peri)
                    VALUES
                      (v_codpais(i),
                       'SAD',
                       v_numlote(i),
                       l_sec_nume_docu,
                       v_numdocu(i),
                       NULL,
                       NULL,
                       NULL,
                       0,
                       0,
                       SYSDATE,
                       psusuario,
                       SYSDATE,
                       psusuario,
                       NULL,
                       l_cod_clie,
                       NULL,
                       v_codperi(i));

                    --Insercion en MAE_CLIEN_VINCU
                    IF (v_cod_clie_rete(i) IS NOT NULL) THEN

                      DELETE FROM mae_clien_vincu
                       WHERE clie_oid_clie_vndo = l_oid_clie
                         AND tivc_oid_tipo_vinc = 9;

                      INSERT INTO mae_clien_vincu
                        (oid_clie_vinc,
                         fec_desd,
                         fec_hast,
                         clie_oid_clie_vnte,
                         clie_oid_clie_vndo,
                         tivc_oid_tipo_vinc,
                         ind_vinc_ppal,
                         fec_ulti_actu)
                      VALUES
                        (mae_cvin_seq.nextval,
                         trunc(SYSDATE),
                         trunc(SYSDATE) + 365,
                         (SELECT oid_clie
                            FROM mae_clien
                           WHERE cod_clie = v_cod_clie_rete(i)),
                         l_oid_clie,
                         9,
                         1,
                         SYSDATE);

                    END IF;

                    -- iNSERCION INC_CLIEN_RECTE

                    IF (v_num_premi(i) IS NOT NULL AND
                       v_cod_clie_rete(i) IS NOT NULL) THEN
                      BEGIN
                        SELECT a.oid_clie_rete
                          INTO lstm_poid_clie_rete
                          FROM inc_clien_recte a
                         WHERE clie_oid_clie =
                               (SELECT oid_clie
                                  FROM mae_clien
                                 WHERE cod_clie = v_cod_clie_rete(i))
                           AND copa_oid_para_gral = v_oid_para_gral(i);

                      EXCEPTION
                        WHEN no_data_found THEN
                          SELECT inc_clr3_seq.nextval
                            INTO lstm_poid_clie_rete
                            FROM dual;

                          INSERT INTO inc_clien_recte
                            (oid_clie_rete,
                             ind_fin_vinc,
                             clie_oid_clie,
                             copa_oid_para_gral,
                             ind_eval,
                             oid_modu,
                             fec_crea)
                          VALUES
                            (lstm_poid_clie_rete,
                             NULL,
                             (SELECT oid_clie
                                FROM mae_clien
                               WHERE cod_clie = v_cod_clie_rete(i)),
                             v_oid_para_gral(i),
                             NULL,
                             1,
                             SYSDATE);

                      END;

                      --Insercion en INC_CLIEN_RECDO
                      IF (v_cod_clie_rete(i) IS NOT NULL AND
                         lstm_poid_clie_rete IS NOT NULL) THEN

                        DELETE FROM inc_pedid_concu_recom
                         WHERE clre_oid_clie_redo IN
                               (SELECT oid_clie_redo
                                  FROM inc_clien_recdo
                                 WHERE clie_oid_clie = l_oid_clie);

                        DELETE FROM inc_clien_recdo
                         WHERE clie_oid_clie = l_oid_clie;
                        INSERT INTO inc_clien_recdo
                          (oid_clie_redo,
                           ind_efec,
                           num_prem,
                           clie_oid_clie,
                           clr3_oid_clie_rete,
                           perd_oid_peri,
                           panp_oid_para_nive_prem,
                           ind_eval,
                           oid_modu,
                           fec_crea)
                        VALUES
                          (inc_clre_seq.nextval,
                           NULL,
                           v_num_premi(i),
                           l_oid_clie,
                           lstm_poid_clie_rete,
                           v_oid_peri(i),
                           v_oid_para_nive_prem(i),
                           NULL,
                           1,
                           SYSDATE);
                      END IF;
                    END IF;

                    IF (v_num_premi(i) IS NULL AND
                       v_cod_clie_rete(i) IS NOT NULL) THEN
                      SELECT oid_clie
                        INTO lnoidclientercdte
                        FROM mae_clien
                       WHERE cod_clie = v_cod_clie_rete(i);

                      DELETE FROM inc_pedid_concu_recom
                       WHERE clre_oid_clie_redo IN
                             (SELECT oid_clie_redo
                                FROM inc_clien_recdo
                               WHERE clie_oid_clie = l_oid_clie);

                      DELETE FROM inc_clien_recdo
                       WHERE clie_oid_clie = l_oid_clie;

                      inc_pkg_proce_incen.inc_pr_inser_regis_recom(v_oidpais,
                                                                   l_oid_clie,
                                                                   v_oid_peri(i),
                                                                   lnoidclientercdte,
                                                                   1,'C');
                    END IF;

                    --Informacion de referencia

                    IF (v_cod_docu_idfi(i) IS NOT NULL) THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 3;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         3,
                         v_tip_docu_fiad(i),
                         v_cod_docu_idfi(i),
                         v_primerapellfiador(i),
                         v_secondapellfiador(i),
                         v_primernomfiador(i),
                         v_secondnomfiador(i),
                         v_val_dire_fiad(i),
                         v_val_barr_fiad(i),
                         v_val_ciud_fiad(i),
                         v_val_depa_fiad(i),
                         v_valtelefiador(i),
                         v_valcelufiador(i),
                         v_valtrabfiador(i),
                         v_tip_via_fiad(i),
                         v_val_nomb_vifi(i),
                         v_num_dire_fiad(i),
                         v_cod_depa_fiad(i),
                         v_cod_prov_fiad(i),
                         v_cod_dist_fiad(i),
                         v_cod_sect_fiad(i),
                         v_val_tipo_vinc_fiad(i),
                         v_val_nomb_empr_fiad(i),
                         v_val_dire_empr_fiad(i),
                         v_val_carg_fiad(i));
                    END IF;

                    --Informacion del Referencia Familiar
                    IF v_val_nomb_refe_fami(i) IS NOT NULL THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 1;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         1,
                         NULL,
                         NULL,
                         v_val_apel_refe_fami(i),
                         NULL,
                         v_val_nomb_refe_fami(i),
                         NULL,
                         v_val_dire_refe_fami(i),
                         v_val_barr_refe_fami(i),
                         v_val_ciud_refe_fami(i),
                         v_val_depa_refe_fami(i),
                         v_val_tele_refe_fami(i),
                         v_val_celu_refe_fami(i),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         v_val_tipo_vinc_refe_fami(i),
                         NULL,
                         NULL,
                         NULL);
                    END IF;

                    IF v_val_nomb_refe_nofa(i) IS NOT NULL THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 2;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         2,
                         NULL,
                         NULL,
                         v_val_ape1_refe_nofa(i),
                         NULL,
                         v_val_nomb_refe_nofa(i),
                         NULL,
                         v_val_dire_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL,
                         v_val_tele_refe_nofa(i),
                         v_val_celu_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         v_val_tipo_vinc_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL);
                    END IF;
                    
                    update mae_clien_datos_adici x set x.ind_impr_docu=
                    CASE WHEN nvl(v_num_ruc(i), '0') <> 0 THEN '0' WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tip_docu(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numdocu(i) IS NOT NULL THEN '0' ELSE
             decode(v_ind_requ_impr_bole(i), 'N', '0', '1') END,x.usu_carg=
             CASE WHEN nvl(v_num_ruc(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tip_docu(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numdocu(i) IS NOT NULL THEN NULL ELSE psusuario END,x.fec_carg=
             CASE WHEN nvl(v_num_ruc(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tip_docu(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numdocu(i) IS NOT NULL THEN NULL ELSE SYSDATE END,x.tip_carg=
             CASE WHEN nvl(v_num_ruc(i), '0') <> 0 THEN NULL WHEN EXISTS
             (SELECT 1
                FROM mae_tipo_docum x
               WHERE x.cod_tipo_docu = v_tip_docu(i)
                 AND val_sigl LIKE 'RUC%') AND
             v_numdocu(i) IS NOT NULL THEN NULL ELSE '2' END 
                    where x.clie_oid_clie=l_oid_clie;
                    
                    IF indrechsolcred = 'N' THEN
                      UPDATE sto_docum_digit
                         SET ind_rech = 1,
                         val_obse_rech_defi = nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_RECHA_SC_AD'),'')                         
                       WHERE sec_nume_docu = v_sec_nume_docu(i);
                       
                       UPDATE int_solic_conso_credi cons
                          SET cons.cod_clie = l_cod_clie
                        WHERE cons.sec_nume_docu = l_sec_nume_docu;
                    END IF;
                    is_valid := FALSE;

                    -- caso en que se actualizara en linea la informacion de la consultora
                  ELSIF (l_oid_clie IS NOT NULL AND
                        l_oid_esta_clie IS NOT NULL AND
                        l_ind_acti IS NOT NULL AND l_fec_naci IS NOT NULL AND
                        l_oid_terr_admi IS NOT NULL 
                        AND l_oid_esta_clie in (1,7)  AND l_ind_acti = '0'  
                        AND to_char(l_fec_naci, 'dd/MM/YYYY') = to_char(v_fec_naci(i), 'dd/MM/YYYY') 
                        AND crea_sad = 'L' -- parametro para actualizar en linea los datos
                        ) THEN
                    -- logica de actualizacion en linea de los datos de la consultora
                    is_valid := FALSE;

                    -- Se activa la consultora y le coloca el nivel de riesgo y auditoria de activacion
                    UPDATE mae_clien_datos_adici
                       SET ind_acti           = 1,
                           niri_oid_nive_ries = nvl((SELECT oid_nive_ries
                                                      FROM car_nivri_secci
                                                     WHERE oid_secc =
                                                           (SELECT zscc_oid_secc
                                                              FROM zon_terri_admin
                                                             WHERE oid_terr_admi =
                                                                   l_oid_terr_admi)),
                                                    4),
                           tpes_oid_tipo_pers = v_oid_tipo_pers(i),
                           orin_oid_orig_ingr = v_oid_orig_ingr(i),
                           cam_acti = v_codperi(i),
                           fec_acti = sysdate,
                           usu_acti = 'REIN_'||psusuario
                     WHERE clie_oid_clie = l_oid_clie;

                    -- Obtiene el oid de la consultora
                    SELECT oid_clie
                      INTO tmp_oid_clie
                      FROM mae_clien
                     WHERE cod_clie = l_cod_clie;

                    -- Desbloquea por Castigo
                    if lnoidbloqcast is not null then
                       update mae_clien_bloqu a set a.fec_desb=sysdate, a.obs_desb='Desbloqueo automático STO SC'
                       where a.clie_oid_clie=tmp_oid_clie and a.tibq_oid_tipo_bloq=lnoidbloqcast and a.fec_desb is null;
                    end if;
                    
                    -- Obtiene el indicador de si ya paso pedido
                    SELECT COUNT(1)
                      INTO lnpasopedido
                      FROM ped_solic_cabec a
                     WHERE a.ind_oc = 1
                       AND a.clie_oid_clie = tmp_oid_clie
                       AND a.val_tota_paga_loca > 0
                       AND a.perd_oid_peri = v_oid_peri(i);

                    -- (1) Actualiza los nombres y apellidos
                    UPDATE mae_clien a
                       SET a.val_ape1 = decode(v_val_ape1(i),
                                               NULL,
                                               a.val_ape1,
                                               v_val_ape1(i)),
                           a.val_ape2 = decode(v_val_ape2(i),
                                               NULL,
                                               a.val_ape2,
                                               v_val_ape2(i)),
                           a.val_nom1 = decode(v_val_nom1(i),
                                               NULL,
                                               a.val_nom1,
                                               v_val_nom1(i)),
                           a.val_nom2 = decode(v_val_nom2(i),
                                               NULL,
                                               a.val_nom2,
                                               v_val_nom2(i))
                     WHERE cod_clie = l_cod_clie;

                    -- (2) Actualizar Datos en MAE_CLIEN_UNIDA_ADMIN
                    IF (v_oidterradmi(i) IS NOT NULL) THEN
                      SELECT ztad_oid_terr_admi
                        INTO oid_terri_admin_tmp
                        FROM mae_clien_unida_admin
                       WHERE perd_oid_peri_fin IS NULL
                         AND clie_oid_clie = tmp_oid_clie
                         AND rownum = 1;

                      oid_peri_ant := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(per_pkg_repor_perce.per_fn_obtie_perio(v_codperi(i),
                                                                                                                        lnidpais,
                                                                                                                        lnidmarca,
                                                                                                                        lnidcanal,
                                                                                                                        -1),
                                                                                 lnidmarca,
                                                                                 lnidcanal);

                      oid_peri_sig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(per_pkg_repor_perce.per_fn_obtie_perio(v_codperi(i),
                                                                                                                        lnidpais,
                                                                                                                        lnidmarca,
                                                                                                                        lnidcanal,
                                                                                                                        1),
                                                                                 lnidmarca,
                                                                                 lnidcanal);

                      IF oid_terri_admin_tmp != v_oidterradmi(i) THEN

                        /*DELETE FROM mae_clien_unida_admin
                        WHERE clie_oid_clie = tmp_oid_clie
                          AND perd_oid_peri_ini = v_oid_peri(i);*/

                        UPDATE mae_clien_unida_admin
                           SET ind_acti          = decode(lnpasopedido,
                                                          0,
                                                          0,
                                                          1),
                               perd_oid_peri_fin = decode(lnpasopedido,
                                                          0,
                                                          oid_peri_ant,
                                                          v_oid_peri(i)),
                               fec_ulti_actu     = SYSDATE
                         WHERE ind_acti = 1
                           AND perd_oid_peri_fin IS NULL
                           AND clie_oid_clie = tmp_oid_clie;

                        SELECT COUNT(1)
                          INTO f_existe
                          FROM mae_clien_unida_admin ua
                         WHERE clie_oid_clie = tmp_oid_clie
                           AND perd_oid_peri_ini =
                               decode(lnpasopedido,
                                      0,
                                      v_oid_peri(i),
                                      oid_peri_sig);
                        IF f_existe = 0 THEN
                          --

                          INSERT INTO mae_clien_unida_admin
                            (oid_clie_unid_admi,
                             clie_oid_clie,
                             perd_oid_peri_ini,
                             perd_oid_peri_fin,
                             ztad_oid_terr_admi,
                             ind_acti,
                             fec_ulti_actu)
                          VALUES
                            (mae_cuad_seq.nextval,
                             tmp_oid_clie,
                             decode(lnpasopedido,
                                    0,
                                    v_oid_peri(i),
                                    oid_peri_sig),
                             NULL,
                             v_oidterradmi(i),
                             decode(lnpasopedido, 0, 1, 0),
                             SYSDATE);
                        ELSE
                          -- si ya existia la UA para la campa?a y la consultora
                          UPDATE mae_clien_unida_admin ua
                             SET ua.ind_acti           = decode(lnpasopedido,
                                                                0,
                                                                1,
                                                                0),
                                 ua.perd_oid_peri_fin  = NULL,
                                 ua.ztad_oid_terr_admi = v_oidterradmi(i)
                           WHERE clie_oid_clie = tmp_oid_clie
                             AND perd_oid_peri_ini =
                                 decode(lnpasopedido,
                                        0,
                                        v_oid_peri(i),
                                        oid_peri_sig);

                        END IF;

                      END IF;
                    END IF;

                    -- (3) Actualizar Datos en MAE_CLIEN_DIREC
                    IF ((v_val_dire_clie(i) IS NOT NULL OR
                       v_tip_via_clie(i) IS NOT NULL OR
                       v_val_nomb_vicl(i) IS NOT NULL OR
                       v_dom_manz(i)||v_dom_etap(i)||v_dom_call_prin(i)||v_dom_num(i)||v_dom_call_secu(i)||v_dom_refe(i)||v_val_barr_clie(i) IS NOT NULL) AND
                       (length(v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                                v_cod_dist_clie(i)) > 17)) THEN

                      BEGIN

                        SELECT terr_oid_terr
                          INTO oid_terr_temp
                          FROM mae_clien_direc
                         WHERE ind_elim = 0
                           AND clie_oid_clie = tmp_oid_clie
                           AND ind_dire_ppal = 1;

                      EXCEPTION
                        WHEN no_data_found THEN
                          oid_terr_temp := NULL;
                      END;

                      UPDATE mae_clien_direc
                         SET ind_elim      = 1,
                             fec_ulti_actu = SYSDATE
                       WHERE ind_elim = 0
                         AND clie_oid_clie = tmp_oid_clie;

                      INSERT INTO mae_clien_direc
                        (oid_clie_dire,
                         clie_oid_clie,
                         tidc_oid_tipo_dire,
                         tivi_oid_tipo_via,
                         terr_oid_terr,
                         zvia_oid_via,
                         num_ppal,
                         val_nomb_via,
                         val_cod_post,
                         val_inte,
                         val_manz,
                         val_lote,
                         val_km,
                         val_obse,
                         val_barr,
                         val_nomb_fich,
                         val_coor_x,
                         val_coor_y,
                         val_coor_z,
                         ind_dire_ppal,
                         ind_ctrl_inte_geor,
                         fec_ulti_actu,
                         cod_unid_geog,
                         ind_elim,
                         val_nom_manz,
                         val_eta_conj,
                         val_cal_prin,
                         val_cal_secu,
                         teco_cod_terr_corr,
                         num_ppri)
                      VALUES
                        (mae_cldi_seq.nextval,
                         tmp_oid_clie,
                         2001,
                         -- comment efernandezo (SELECT oid_tipo_via FROM seg_tipo_via WHERE to_number(cod_tipo_via) = v_tip_via_clie(i)),
                         (SELECT oid_tipo_via
                            FROM seg_tipo_via
                           WHERE cod_tipo_via = nvl(v_tip_via_clie(i), '99')),
                         nvl(v_oid_terr(i), oid_terr_temp),
                         NULL,
                         v_num_dire_clie(i),
                         --v_val_nomb_vicl(i),
                         decode(lsparteDireccSCWeb,'S',substr(v_val_dire_clie(i),1,lspartedireccscwebPos),v_val_nomb_vicl(i))||
                         decode( lsCampNew, '0', '' ,TRIM(v_dom_manz(i)) || ' '||TRIM(v_dom_etap(i)) || ' '||TRIM(v_dom_call_prin(i)) || ' '||
                                 TRIM(v_dom_num(i)) || ' '||TRIM(v_dom_call_secu(i))),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         /*v_val_dire_clie(i) ||
                         decode(v_val_barr_clie(i),
                                NULL,
                                NULL,
                                '-' || v_val_barr_clie(i)),*/
                         decode(lsparteDireccSCWeb,'S',substr(v_val_dire_clie(i),lspartedireccscwebPos+1),v_val_dire_clie(i)) ||
              decode(v_val_barr_clie(i),
                                NULL,
                                NULL,
                                '-' || v_val_barr_clie(i))||TRIM(v_dom_refe(i)),
                         decode(lsCampNew, '0','',v_val_barr_clie(i)),          
                         NULL,
                         0,
                         0,
                         0,
                         1,
                         'S',
                         SYSDATE,
                         v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                         v_cod_dist_clie(i) || v_cod_sect_clie(i),
                         0,
                         v_dom_manz(i),
                         v_dom_etap(i),
                         v_dom_call_prin(i),
                         v_dom_call_secu(i),
                         v_cod_terr_corr(i),
                         v_dom_num(i));
                    END IF;

                    IF (lsdireccionentrega IS NOT NULL) THEN

                      IF ((v_val_dire_entre_clie(i) IS NOT NULL OR 
                        v_ent_manz(i)||v_ent_etap(i)||v_ent_call_prin(i)||v_ent_num(i)||v_ent_call_secu(i)||v_ent_refe(i)||v_val_barr_entre_clie(i) IS NOT NULL) AND
                         (length(v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                                  v_cod_dist_clie(i)) > 17)) THEN

                        BEGIN

                          SELECT terr_oid_terr
                            INTO oid_terr_temp
                            FROM mae_clien_direc
                           WHERE tidc_oid_tipo_dire =
                                 (SELECT oid_tipo_dire
                                    FROM mae_tipo_direc
                                   WHERE cod_tipo_dire = lsdireccionentrega
                                     AND ind_elim = 0)
                             AND clie_oid_clie = tmp_oid_clie;

                        EXCEPTION
                          WHEN no_data_found THEN
                            oid_terr_temp := NULL;
                        END;

                        UPDATE mae_clien_direc
                           SET ind_elim      = 1,
                               ind_dire_ppal = 0,
                               fec_ulti_actu = SYSDATE
                         WHERE ind_elim = 0
                           AND clie_oid_clie = tmp_oid_clie
                           AND tidc_oid_tipo_dire =
                               (SELECT oid_tipo_dire
                                  FROM mae_tipo_direc
                                 WHERE cod_tipo_dire = lsdireccionentrega);

                        INSERT INTO mae_clien_direc
                          (oid_clie_dire,
                           clie_oid_clie,
                           tidc_oid_tipo_dire,
                           tivi_oid_tipo_via,
                           terr_oid_terr,
                           zvia_oid_via,
                           num_ppal,
                           val_nomb_via,
                           val_cod_post,
                           val_inte,
                           val_manz,
                           val_lote,
                           val_km,
                           val_obse,
                           val_barr,
                           val_nomb_fich,
                           val_coor_x,
                           val_coor_y,
                           val_coor_z,
                           ind_dire_ppal,
                           ind_ctrl_inte_geor,
                           fec_ulti_actu,
                           cod_unid_geog,
                           ind_elim,
                           val_nom_manz,
                           val_eta_conj,
                           val_cal_prin,
                           val_cal_secu,
                           teco_cod_terr_corr,
                           num_ppri)
                        VALUES
                          (mae_cldi_seq.nextval,
                           tmp_oid_clie,
                           (SELECT oid_tipo_dire
                              FROM mae_tipo_direc
                             WHERE cod_tipo_dire = lsdireccionentrega),
                           (SELECT oid_tipo_via
                              FROM seg_tipo_via
                             WHERE cod_tipo_via = '99'),
                           nvl(v_oid_terr(i), oid_terr_temp),
                           NULL,
                           NULL,
                           decode ( lsCampNew, '0','',TRIM(v_ent_manz(i)) || ' '||TRIM(v_ent_etap(i)) || ' '||TRIM(v_ent_call_prin(i)) || ' '||
                                   TRIM(v_ent_num(i)) || ' '||TRIM(v_ent_call_secu(i))),
                           --NULL,
                           NULL,
                           NULL,
                           NULL,
                           NULL,
                           NULL,
                           TRIM(v_val_dire_entre_clie(i)) ||decode(lsCampNew,'0','', v_ent_refe(i) || ' ') || TRIM(v_val_obse(i)),  
                           decode(lsCampNew, '0','',v_val_barr_entre_clie(i)),                            
                           NULL,
                           0,
                           0,
                           0,
                           0,
                           'S',
                           SYSDATE,
                           v_cod_depa_clie(i) || v_cod_prov_clie(i) ||
                           v_cod_dist_clie(i) || v_cod_sect_clie(i),
                           0,
                           v_dom_manz(i),
                           v_dom_etap(i),
                           v_dom_call_prin(i),
                           v_dom_call_secu(i),
                           v_cod_terr_corr(i),
                           v_ent_num(i));

                      END IF;

                    END IF;

                    -- (4) Actualiza datos en MAE_CLIEN_COMUN
                    IF (v_val_telf_clie(i) IS NOT NULL) THEN

                      contador_mae_clien := 0;
                      BEGIN
                        SELECT COUNT(1)
                          INTO contador_mae_clien
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 1
                           AND clie_oid_clie = tmp_oid_clie;

                        IF (contador_mae_clien > 0) THEN

                          UPDATE mae_clien_comun
                             SET val_text_comu = v_val_telf_clie(i),
                                 fec_ulti_actu = SYSDATE
                           WHERE ticm_oid_tipo_comu = 1
                             AND clie_oid_clie = tmp_oid_clie;
                        ELSE
                          UPDATE mae_clien_comun
                             SET ind_comu_ppal = '0',
                                 fec_ulti_actu = SYSDATE
                           WHERE clie_oid_clie = tmp_oid_clie;

                          INSERT INTO mae_clien_comun
                            (oid_clie_comu,
                             clie_oid_clie,
                             ticm_oid_tipo_comu,
                             val_dia_comu,
                             val_text_comu,
                             fec_hora_desd,
                             fec_hora_hast,
                             val_inte_comu,
                             ind_comu_ppal,
                             fec_ulti_actu)
                          VALUES
                            (mae_clco_seq.nextval,
                             tmp_oid_clie,
                             1,
                             'L',
                             v_val_telf_clie(i),
                             NULL,
                             NULL,
                             1,
                             1,
                             SYSDATE);
                        END IF;

                      END;

                    END IF;

                    IF (v_val_celu_clie(i) IS NOT NULL) THEN

                      contador_mae_clien := 0;
                      BEGIN
                        SELECT COUNT(1)
                          INTO contador_mae_clien
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 6
                           AND clie_oid_clie = tmp_oid_clie;

                        IF (contador_mae_clien > 0) THEN

                          UPDATE mae_clien_comun
                             SET val_text_comu = v_val_celu_clie(i),
                                 fec_ulti_actu = SYSDATE
                           WHERE ticm_oid_tipo_comu = 6
                             AND clie_oid_clie = tmp_oid_clie;
                        ELSE
                          INSERT INTO mae_clien_comun
                            (oid_clie_comu,
                             clie_oid_clie,
                             ticm_oid_tipo_comu,
                             val_dia_comu,
                             val_text_comu,
                             fec_hora_desd,
                             fec_hora_hast,
                             val_inte_comu,
                             ind_comu_ppal,
                             fec_ulti_actu)
                          VALUES
                            (mae_clco_seq.nextval,
                             tmp_oid_clie,
                             6,
                             'L',
                             v_val_celu_clie(i),
                             NULL,
                             NULL,
                             1,
                             0,
                             SYSDATE);

                        END IF;

                      END;
                    END IF;

                    IF (v_val_telf_trab(i) IS NOT NULL) THEN
                      contador_mae_clien := 0;

                      BEGIN
                        SELECT COUNT(1)
                          INTO contador_mae_clien
                          FROM mae_clien_comun
                         WHERE ticm_oid_tipo_comu = 7
                           AND clie_oid_clie = tmp_oid_clie;

                        IF (contador_mae_clien > 0) THEN

                          UPDATE mae_clien_comun
                             SET val_text_comu = v_val_telf_trab(i),
                                 fec_ulti_actu = SYSDATE
                           WHERE ticm_oid_tipo_comu = 7
                             AND clie_oid_clie = tmp_oid_clie;

                        ELSE
                          INSERT INTO mae_clien_comun
                            (oid_clie_comu,
                             clie_oid_clie,
                             ticm_oid_tipo_comu,
                             val_dia_comu,
                             val_text_comu,
                             fec_hora_desd,
                             fec_hora_hast,
                             val_inte_comu,
                             ind_comu_ppal,
                             fec_ulti_actu)
                          VALUES
                            (mae_clco_seq.nextval,
                             tmp_oid_clie,
                             7,
                             'L',
                             v_val_telf_trab(i),
                             NULL,
                             NULL,
                             1,
                             0,
                             SYSDATE);

                        END IF;

                      END;

                    END IF;

                    IF v_val_mail_clie(i) IS NOT NULL THEN

                      SELECT COUNT(1)
                        INTO tmp_cont
                        FROM mae_clien_comun
                       WHERE clie_oid_clie = tmp_oid_clie
                         AND ticm_oid_tipo_comu = 3;

                      IF tmp_cont <> 0 THEN
                        UPDATE mae_clien_comun
                           SET val_text_comu = v_val_mail_clie(i),
                               fec_ulti_actu = SYSDATE
                         WHERE ticm_oid_tipo_comu = 3
                           AND clie_oid_clie = tmp_oid_clie;
                      ELSE
                        INSERT INTO mae_clien_comun
                          (oid_clie_comu,
                           clie_oid_clie,
                           ticm_oid_tipo_comu,
                           val_dia_comu,
                           val_text_comu,
                           fec_hora_desd,
                           fec_hora_hast,
                           val_inte_comu,
                           ind_comu_ppal,
                           fec_ulti_actu)
                        VALUES
                          (mae_clco_seq.nextval,
                           tmp_oid_clie,
                           3,
                           'L',
                           v_val_mail_clie(i),
                           NULL,
                           NULL,
                           1,
                           0,
                           SYSDATE);
                      END IF;

                    END IF;

                    IF (lstelefonoentrega IS NOT NULL) THEN

                      IF (v_val_tele_entre_clie(i) IS NOT NULL) THEN

                        SELECT COUNT(1)
                          INTO tmp_cont
                          FROM mae_clien_comun
                         WHERE clie_oid_clie = tmp_oid_clie
                           AND ticm_oid_tipo_comu IN
                               (SELECT oid_tipo_comu
                                  FROM mae_tipo_comun
                                 WHERE cod_tipo_comu = lstelefonoentrega);

                        IF tmp_cont <> 0 THEN

                          UPDATE mae_clien_comun
                             SET val_text_comu = v_val_tele_entre_clie(i),
                                 fec_ulti_actu = SYSDATE
                           WHERE ticm_oid_tipo_comu IN
                                 (SELECT oid_tipo_comu
                                    FROM mae_tipo_comun
                                   WHERE cod_tipo_comu = lstelefonoentrega)
                             AND clie_oid_clie = tmp_oid_clie;

                        ELSE
                          INSERT INTO mae_clien_comun
                            (oid_clie_comu,
                             clie_oid_clie,
                             ticm_oid_tipo_comu,
                             val_dia_comu,
                             val_text_comu,
                             fec_hora_desd,
                             fec_hora_hast,
                             val_inte_comu,
                             ind_comu_ppal,
                             fec_ulti_actu)
                          VALUES
                            (mae_clco_seq.nextval,
                             tmp_oid_clie,
                             (SELECT oid_tipo_comu
                                FROM mae_tipo_comun
                               WHERE cod_tipo_comu = lstelefonoentrega),
                             'L',
                             v_val_tele_entre_clie(i),
                             NULL,
                             NULL,
                             1,
                             0,
                             SYSDATE);

                        END IF;

                      END IF;
                    END IF;

                    IF (lscelularentrega IS NOT NULL) THEN

                      IF v_val_celu_entre_clie(i) IS NOT NULL THEN

                        SELECT COUNT(1)
                          INTO tmp_cont
                          FROM mae_clien_comun
                         WHERE clie_oid_clie = tmp_oid_clie
                           AND ticm_oid_tipo_comu IN
                               (SELECT oid_tipo_comu
                                  FROM mae_tipo_comun
                                 WHERE cod_tipo_comu = lscelularentrega);

                        IF tmp_cont <> 0 THEN
                          UPDATE mae_clien_comun
                             SET val_text_comu = v_val_celu_entre_clie(i),
                                 fec_ulti_actu = SYSDATE
                           WHERE ticm_oid_tipo_comu IN
                                 (SELECT oid_tipo_comu
                                    FROM mae_tipo_comun
                                   WHERE cod_tipo_comu = lscelularentrega)
                             AND clie_oid_clie = tmp_oid_clie;
                        ELSE
                          INSERT INTO mae_clien_comun
                            (oid_clie_comu,
                             clie_oid_clie,
                             ticm_oid_tipo_comu,
                             val_dia_comu,
                             val_text_comu,
                             fec_hora_desd,
                             fec_hora_hast,
                             val_inte_comu,
                             ind_comu_ppal,
                             fec_ulti_actu)
                          VALUES
                            (mae_clco_seq.nextval,
                             tmp_oid_clie,
                             (SELECT oid_tipo_comu
                                FROM mae_tipo_comun
                               WHERE cod_tipo_comu = lscelularentrega),
                             'L',
                             v_val_celu_entre_clie(i),
                             NULL,
                             NULL,
                             1,
                             0,
                             SYSDATE);

                        END IF;

                      END IF;
                    END IF;

                    -- (5) -- Actualizar Datos en MAE_CLIEN_IDENT
                    IF v_num_ruc(i) IS NULL THEN

                      contador_mae_clien_direc := 0;

                      SELECT COUNT(1)
                        INTO contador_mae_clien_direc
                        FROM mae_clien_ident
                       WHERE clie_oid_clie = tmp_oid_clie
                         AND tdoc_oid_tipo_docu =
                             (SELECT oid_tipo_docu
                                FROM mae_tipo_docum
                               WHERE val_sigl = 'RUC')
                         AND val_iden_docu_prin = 1;

                      IF contador_mae_clien_direc = 0 THEN
                        ind_prin_dni := 1;
                      ELSE
                        ind_prin_dni := 0;
                      END IF;
                    ELSE
                      UPDATE mae_clien_ident
                         SET val_iden_docu_prin = 0
                       WHERE tdoc_oid_tipo_docu <>
                             (SELECT oid_tipo_docu
                                FROM mae_tipo_docum
                               WHERE val_sigl = 'RUC')
                         AND clie_oid_clie = tmp_oid_clie;
                      ind_prin_dni := 0;
                    END IF;

                    IF (v_tip_docu(i) IS NOT NULL AND
                       v_num_docu_iden(i) IS NOT NULL) THEN

                      contador_mae_clien_direc := 0;

                      BEGIN

                        SELECT COUNT(1)
                          INTO contador_mae_clien_direc
                          FROM mae_clien_ident
                         WHERE clie_oid_clie = tmp_oid_clie
                           AND tdoc_oid_tipo_docu =
                               (SELECT oid_tipo_docu
                                  FROM mae_tipo_docum
                                 WHERE cod_tipo_docu = v_tip_docu(i));

                        IF ind_prin_dni = 1 THEN
                          UPDATE mae_clien_ident
                             SET val_iden_docu_prin = 0
                           WHERE clie_oid_clie = tmp_oid_clie;
                        END IF;

                        IF (contador_mae_clien_direc = 0) THEN

                          INSERT INTO mae_clien_ident
                            (oid_clie_iden,
                             tdoc_oid_tipo_docu,
                             clie_oid_clie,
                             num_docu_iden,
                             val_iden_docu_prin,
                             val_iden_pers_empr,
                             fec_ulti_actu)
                          VALUES
                            (mae_clid_seq.nextval,
                             (SELECT oid_tipo_docu
                                FROM mae_tipo_docum
                               WHERE cod_tipo_docu = v_tip_docu(i)),
                             tmp_oid_clie,
                             v_num_docu_iden(i),
                             ind_prin_dni,
                             'P',
                             SYSDATE);

                        END IF;

                        IF (contador_mae_clien_direc > 0) THEN

                          UPDATE mae_clien_ident
                             SET num_docu_iden      = v_num_docu_iden(i),
                                 fec_ulti_actu      = SYSDATE,
                                 val_iden_docu_prin = ind_prin_dni
                           WHERE tdoc_oid_tipo_docu =
                                 (SELECT oid_tipo_docu
                                    FROM mae_tipo_docum
                                   WHERE cod_tipo_docu = v_tip_docu(i))
                             AND clie_oid_clie = tmp_oid_clie;
                        END IF;

                      END;

                    END IF;

                    IF (v_num_ruc(i) IS NOT NULL) THEN

                      contador_mae_clien_direc := 0;

                      SELECT COUNT(1)
                        INTO contador_mae_clien_direc
                        FROM mae_clien_ident
                       WHERE clie_oid_clie = tmp_oid_clie
                         AND tdoc_oid_tipo_docu =
                             (SELECT oid_tipo_docu
                                FROM mae_tipo_docum
                               WHERE val_sigl = 'RUC');

                      IF contador_mae_clien_direc = 0 THEN

                        INSERT INTO mae_clien_ident
                          (oid_clie_iden,
                           tdoc_oid_tipo_docu,
                           clie_oid_clie,
                           num_docu_iden,
                           val_iden_docu_prin,
                           val_iden_pers_empr,
                           fec_ulti_actu)
                        VALUES
                          (mae_clid_seq.nextval,
                           (SELECT oid_tipo_docu
                              FROM mae_tipo_docum
                             WHERE val_sigl = 'RUC'),
                           tmp_oid_clie,
                           v_num_ruc(i),
                           1,
                           'P',
                           SYSDATE);

                      ELSE

                        UPDATE mae_clien_ident
                           SET num_docu_iden      = v_num_ruc(i),
                               fec_ulti_actu      = SYSDATE,
                               val_iden_docu_prin = 1
                         WHERE tdoc_oid_tipo_docu =
                               (SELECT oid_tipo_docu
                                  FROM mae_tipo_docum
                                 WHERE val_sigl = 'RUC')
                           AND clie_oid_clie = tmp_oid_clie;

                      END IF;
                    END IF;

                    BEGIN
                      SELECT oid_clie
                        INTO l_oid_clie_rete
                        FROM mae_clien
                       WHERE cod_clie = v_cod_clie_rete(i);
                    EXCEPTION
                      WHEN OTHERS THEN
                        l_oid_clie_rete := NULL;
                    END;

                    isvalid := 0;
                    --Insercion en MAE_CLIEN_VINCU
                    IF (l_oid_clie_rete IS NOT NULL) THEN

                      isvalid := 1;
                      
                      DELETE FROM mae_clien_vincu
                       WHERE clie_oid_clie_vndo = l_oid_clie
                         AND tivc_oid_tipo_vinc = 9;

                      INSERT INTO mae_clien_vincu
                        (oid_clie_vinc,
                         fec_desd,
                         fec_hast,
                         clie_oid_clie_vnte,
                         clie_oid_clie_vndo,
                         tivc_oid_tipo_vinc,
                         ind_vinc_ppal,
                         fec_ulti_actu)
                      VALUES
                        (mae_cvin_seq.nextval,
                         trunc(SYSDATE),
                         trunc(SYSDATE) + 365,
                         --(SELECT oid_clie FROM mae_clien WHERE cod_clie = v_cod_clie_rete(i)),
                         l_oid_clie_rete,
                         l_oid_clie,
                         9,
                         1,
                         SYSDATE);

                    END IF;
                    
                    BEGIN
                      SELECT oid_clie
                        INTO l_oid_lide_reco
                        FROM mae_clien
                       WHERE cod_clie = v_cod_lide_reco(i);
                    EXCEPTION
                      WHEN OTHERS THEN
                        l_oid_lide_reco := NULL;
                    END;

                    --Insercion en MAE_CLIEN_VINCU
                    IF (l_oid_lide_reco IS NOT NULL) THEN

                      DELETE FROM mae_clien_vincu
                       WHERE clie_oid_clie_vndo = l_oid_clie
                         AND tivc_oid_tipo_vinc = lsparametrotipovinculider;

                      INSERT INTO mae_clien_vincu
                        (oid_clie_vinc,
                         fec_desd,
                         fec_hast,
                         clie_oid_clie_vnte,
                         clie_oid_clie_vndo,
                         tivc_oid_tipo_vinc,
                         ind_vinc_ppal,
                         fec_ulti_actu)
                      VALUES
                        (mae_cvin_seq.nextval,
                         trunc(SYSDATE),
                         trunc(SYSDATE) + 365,
                         --(SELECT oid_clie FROM mae_clien WHERE cod_clie = v_cod_clie_rete(i)),
                         (SELECT oid_clie
                              FROM mae_clien
                             WHERE cod_clie = v_cod_lide_reco(i)),
                         l_oid_clie,
                         lsparametrotipovinculider,
                         decode(isvalid, 1, 0, 1),
                         SYSDATE);

                    END IF;

                    -- iNSERCION INC_CLIEN_RECTE
                    IF (v_num_premi(i) IS NOT NULL AND
                       l_oid_clie_rete IS NOT NULL) THEN
                      BEGIN
                        SELECT a.oid_clie_rete
                          INTO lstm_poid_clie_rete
                          FROM inc_clien_recte a
                         WHERE clie_oid_clie = l_oid_clie_rete
                           AND copa_oid_para_gral = v_oid_para_gral(i);

                      EXCEPTION
                        WHEN no_data_found THEN
                          SELECT inc_clr3_seq.nextval
                            INTO lstm_poid_clie_rete
                            FROM dual;

                          INSERT INTO inc_clien_recte
                            (oid_clie_rete,
                             ind_fin_vinc,
                             clie_oid_clie,
                             copa_oid_para_gral,
                             ind_eval,
                             oid_modu,
                             fec_crea)
                          VALUES
                            (lstm_poid_clie_rete,
                             NULL,
                             --(SELECT oid_clie FROM mae_clien WHERE cod_clie = v_cod_clie_rete(i)),
                             l_oid_clie_rete,
                             v_oid_para_gral(i),
                             NULL,
                             1,
                             SYSDATE);

                      END;

                      --Insercion en INC_CLIEN_RECDO
                      IF (l_oid_clie_rete IS NOT NULL AND
                         lstm_poid_clie_rete IS NOT NULL) THEN

                        DELETE FROM inc_pedid_concu_recom
                         WHERE clre_oid_clie_redo IN
                               (SELECT oid_clie_redo
                                  FROM inc_clien_recdo
                                 WHERE clie_oid_clie = l_oid_clie);

                        DELETE FROM inc_clien_recdo
                         WHERE clie_oid_clie = l_oid_clie;
                        INSERT INTO inc_clien_recdo
                          (oid_clie_redo,
                           ind_efec,
                           num_prem,
                           clie_oid_clie,
                           clr3_oid_clie_rete,
                           perd_oid_peri,
                           panp_oid_para_nive_prem,
                           ind_eval,
                           oid_modu,
                           fec_crea)
                        VALUES
                          (inc_clre_seq.nextval,
                           NULL,
                           v_num_premi(i),
                           l_oid_clie,
                           lstm_poid_clie_rete,
                           v_oid_peri(i),
                           v_oid_para_nive_prem(i),
                           NULL,
                           1,
                           SYSDATE);
                      END IF;
                    END IF;

                    IF (v_num_premi(i) IS NULL AND
                       l_oid_clie_rete IS NOT NULL) THEN
                      SELECT oid_clie
                        INTO lnoidclientercdte
                        FROM mae_clien
                       WHERE cod_clie = v_cod_clie_rete(i);

                      DELETE FROM inc_pedid_concu_recom
                       WHERE clre_oid_clie_redo IN
                             (SELECT oid_clie_redo
                                FROM inc_clien_recdo
                               WHERE clie_oid_clie = l_oid_clie);

                      DELETE FROM inc_clien_recdo
                       WHERE clie_oid_clie = l_oid_clie;

                      inc_pkg_proce_incen.inc_pr_inser_regis_recom(v_oidpais,
                                                                   l_oid_clie,
                                                                   v_oid_peri(i),
                                                                   lnoidclientercdte,
                                                                   1,'C');
                    END IF;

                    --Informacion de referencia
                    IF (v_cod_docu_idfi(i) IS NOT NULL) THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 3;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         3,
                         v_tip_docu_fiad(i),
                         v_cod_docu_idfi(i),
                         v_primerapellfiador(i),
                         v_secondapellfiador(i),
                         v_primernomfiador(i),
                         v_secondnomfiador(i),
                         v_val_dire_fiad(i),
                         v_val_barr_fiad(i),
                         v_val_ciud_fiad(i),
                         v_val_depa_fiad(i),
                         v_valtelefiador(i),
                         v_valcelufiador(i),
                         v_valtrabfiador(i),
                         v_tip_via_fiad(i),
                         v_val_nomb_vifi(i),
                         v_num_dire_fiad(i),
                         v_cod_depa_fiad(i),
                         v_cod_prov_fiad(i),
                         v_cod_dist_fiad(i),
                         v_cod_sect_fiad(i),
                         v_val_tipo_vinc_fiad(i),
                         v_val_nomb_empr_fiad(i),
                         v_val_dire_empr_fiad(i),
                         v_val_carg_fiad(i));
                    END IF;

                    --Informacion del Referencia Familiar
                    IF v_val_nomb_refe_fami(i) IS NOT NULL THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 1;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         1,
                         NULL,
                         NULL,
                         v_val_apel_refe_fami(i),
                         NULL,
                         v_val_nomb_refe_fami(i),
                         NULL,
                         v_val_dire_refe_fami(i),
                         v_val_barr_refe_fami(i),
                         v_val_ciud_refe_fami(i),
                         v_val_depa_refe_fami(i),
                         v_val_tele_refe_fami(i),
                         v_val_celu_refe_fami(i),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         v_val_tipo_vinc_refe_fami(i),
                         NULL,
                         NULL,
                         NULL);
                    END IF;

                    IF v_val_nomb_refe_nofa(i) IS NOT NULL THEN

                      DELETE FROM mae_refer
                       WHERE cod_clie = l_cod_clie
                         AND tipo_refe = 2;

                      INSERT INTO mae_refer
                        (cod_clie,
                         tipo_refe,
                         tipo_docu_refe,
                         num_docu_refe,
                         val_ape1,
                         val_ape2,
                         val_nom1,
                         val_nom2,
                         val_dire,
                         val_barr,
                         val_ciud,
                         val_depa,
                         val_telf,
                         val_celu,
                         val_telf_trab,
                         tipo_via,
                         val_nom_via,
                         num_dire,
                         cod_depa,
                         cod_prov,
                         cod_dist,
                         cod_sect,
                         tipo_vincu,
                         nom_empre,
                         dir_empre,
                         carg_empre)
                      VALUES
                        (l_cod_clie,
                         2,
                         NULL,
                         NULL,
                         v_val_ape1_refe_nofa(i),
                         NULL,
                         v_val_nomb_refe_nofa(i),
                         NULL,
                         v_val_dire_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL,
                         v_val_tele_refe_nofa(i),
                         v_val_celu_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         v_val_tipo_vinc_refe_nofa(i),
                         NULL,
                         NULL,
                         NULL);
                    END IF;

                    -- Rechaza la solicitu de credito
                    IF indrechsolcred = 'N' THEN
                      UPDATE sto_docum_digit
                         SET ind_rech = 1,
                         val_obse_rech_defi = nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_RECHA_SC_AD'),'')
                       WHERE sec_nume_docu = v_sec_nume_docu(i);
                       
                       UPDATE int_solic_conso_credi cons
                          SET cons.cod_clie = l_cod_clie
                        WHERE cons.sec_nume_docu = l_sec_nume_docu;
                    END IF;
                    is_valid := FALSE;

                    -- ya no rechazaria, sino que pasaria la validacion con los datos actualizados
                    --is_valid := TRUE;

                  ELSE
                    -- El ingreso de una consultora nueva se debe dar si los 4 campos son nulos,
                    -- por ende se cumple la condicion de "No data found".
                    IF (l_oid_esta_clie IS NULL AND l_ind_acti IS NULL AND
                       l_fec_naci IS NULL AND l_oid_terr_admi IS NULL) THEN
                      is_valid := TRUE;
                    ELSE
                      is_valid := FALSE;
                    END IF;

                  END IF;
                EXCEPTION
                  WHEN no_data_found THEN
                    is_valid := TRUE;
                END;
              END IF;
            END IF;

          END IF;
          IF (is_valid) THEN
            -- Actualizamos Documentos Validados OK
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_dnicliente%NOTFOUND;
    END LOOP;
    CLOSE c_dnicliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DNI_CLIE: ' || ls_sqlerrm);

  END sto_pr_scc_dni_clie;
  
  /**************************************************************************
  Descripcion       : STO_PR_SCC_RUC_CLIE_OK
                    Procedimiento de Validacion de RUC de nuevo Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_ruc_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.num_ruc,
             cons.num_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_credi.num_ruc%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_credi.num_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_ruc       t_num_ruc;
    v_num_docu      t_num_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    numero   NUMBER := 0;
    existe   BOOLEAN := TRUE;
    contador NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_num_ruc,
             v_num_docu LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_num_ruc(j) IS NOT NULL) THEN

            contador := 0;

            SELECT COUNT(1)
              INTO contador
              FROM mae_tipo_docum
             WHERE val_sigl = 'RUC';

            IF (contador = 0) THEN

              v_num_ruc(j) := NULL;

              UPDATE int_solic_conso_credi
                 SET num_ruc  = NULL,
                     num_docu = v_num_docu(j)
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);

            END IF;
          END IF;

          IF (v_num_ruc(j) IS NOT NULL) THEN
            existe := FALSE;

            BEGIN

              numero := 0;

              SELECT COUNT(*)
                INTO numero
                FROM mae_clien_ident a
               WHERE a.tdoc_oid_tipo_docu =
                     (SELECT oid_tipo_docu
                        FROM mae_tipo_docum
                       WHERE val_sigl = 'RUC')
                 AND a.num_docu_iden = v_num_ruc(j);

              IF (numero > 0) THEN
                existe := FALSE;

              ELSE
                existe := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                existe := TRUE;

            END;

            IF (existe) THEN
              -- Actualizamos Documentos Validados OK

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);

            END IF;

          ELSE
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF; -- DIFERENRET NULL
        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_RUC_CLIE: ' || ls_sqlerrm);

  END sto_pr_scc_ruc_clie;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_EDAD_MINI_OK
                    Procedimiento de Validacion de edad minima de  Cliente SCC
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_edad_mini
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    edad_minima NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_MAYO_EDAD');

    CURSOR c_edadminima IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (months_between(cons.fec_proc, cons.fec_naci) / 12) >=
             edad_minima;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_edadminima;
    LOOP
      FETCH c_edadminima BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_edadminima%NOTFOUND;
    END LOOP;
    CLOSE c_edadminima;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_EDAD_MINI: ' || ls_sqlerrm);

  END sto_pr_scc_edad_mini;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_EDAD_MAXI_OK
                    Procedimiento de Validacion de edad maxima de  Cliente SCC
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_edad_maxi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    lnedadmaxima NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_MAXI_EDAD');
    CURSOR c_edadmaxima IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais

         AND (months_between(cons.fec_proc, cons.fec_naci) / 12) <
             lnedadmaxima;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_edadmaxima;
    LOOP
      FETCH c_edadmaxima BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_edadmaxima%NOTFOUND;
    END LOOP;
    CLOSE c_edadmaxima;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_EDAD_MAXI: ' || ls_sqlerrm);

  END sto_pr_scc_edad_maxi;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_CLIE_OK
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_tele_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_telefonocliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_celu_clie,
             cons.val_telf_trab,
             cons.val_telf_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_credi.val_celu_clie%TYPE;
    TYPE t_val_telf_trab IS TABLE OF int_solic_conso_credi.val_telf_trab%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_credi.val_telf_clie%TYPE;
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_val_celu_clie t_val_celu_clie;
    v_val_telf_trab t_val_telf_trab;
    v_val_telf_clie t_val_telf_clie;
    rows            NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_telefonocliente;
    LOOP
      FETCH c_telefonocliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_celu_clie,
             v_val_telf_trab,
             v_val_telf_clie

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            IF (v_val_celu_clie(j) IS NULL AND v_val_telf_clie(j) IS NULL) THEN

              numero := 1;

            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_telefonocliente%NOTFOUND;
    END LOOP;
    CLOSE c_telefonocliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_TELE_CLIE: ' || ls_sqlerrm);

  END sto_pr_scc_tele_clie;
  
 /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_CLIE_DIFE
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 06/01/2016


  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE sto_pr_scc_tele_clie_dife
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_telefonocliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_celu_clie,
             cons.val_telf_trab,
             cons.val_telf_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_credi.val_celu_clie%TYPE;
    TYPE t_val_telf_trab IS TABLE OF int_solic_conso_credi.val_telf_trab%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_credi.val_telf_clie%TYPE;
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_val_celu_clie t_val_celu_clie;
    v_val_telf_trab t_val_telf_trab;
    v_val_telf_clie t_val_telf_clie;
    rows            NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    telefo NUMBER:=0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_telefonocliente;
    LOOP
      FETCH c_telefonocliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_celu_clie,
             v_val_telf_trab,
             v_val_telf_clie

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            telefo := 0;
            
            IF ( (v_val_celu_clie(j) IS NULL) OR (v_val_telf_clie(j) IS NULL) ) THEN
                numero := 1;
                
            END IF;
            IF (v_val_celu_clie(j) = v_val_telf_clie(j)) THEN
                
               numero := 1;
            END IF;
                             
           SELECT count(*) INTO telefo 
           FROM MAE_CLIEN_COMUN WHERE TICM_OID_TIPO_COMU in ( 1,6)
            AND VAL_DIA_COMU='L' 
            AND VAL_TEXT_COMU IN (v_val_celu_clie(j),v_val_telf_clie(j)); 
                  
            IF (telefo > 0) THEN
                  
               numero := 1;
                     
            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_telefonocliente%NOTFOUND;
    END LOOP;
    CLOSE c_telefonocliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_TELE_CLIE_DIFE: ' || ls_sqlerrm);

  END sto_pr_scc_tele_clie_dife;  

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_POSI_OK
                    Procedimiento de Validacion de direccion positiva  de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_direc_posi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_direccion IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.val_dire_clie,
             cons.tip_via_clie,
             cons.val_nomb_vicl,
             cons.cod_depa_clie,
             cons.cod_prov_clie,
             cons.cod_dist_clie,
             cons.cod_sect_clie,
             cons.val_barr_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_credi.val_dire_clie%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_credi.val_nomb_vicl%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_credi.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_credi.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_credi.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_credi.cod_sect_clie%TYPE;
    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_credi.val_barr_clie%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_val_dire_clie t_val_dire_clie;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_cod_depa_clie t_cod_depa_clie;
    v_cod_prov_clie t_cod_prov_clie;
    v_cod_dist_clie t_cod_dist_clie;
    v_cod_sect_clie t_cod_sect_clie;
    v_val_barr_clie t_val_barr_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    contador  NUMBER := 0;
    regvalido BOOLEAN := TRUE;

    lsparagenedirec sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparagenedirec := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_VALI_DIREC');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_direccion;
    LOOP
      FETCH c_direccion BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_dire_clie,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_val_barr_clie

             LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          regvalido := TRUE;

          IF ((v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
             v_cod_dist_clie(j) || v_cod_sect_clie(j) IS NULL) OR
             (length(v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
                      v_cod_dist_clie(j) || v_cod_sect_clie(j)) < 18)) THEN

            regvalido := FALSE;

          ELSIF ((length(v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
                         v_cod_dist_clie(j) || v_cod_sect_clie(j)) = 24)) THEN

            SELECT COUNT(1)
              INTO contador
              FROM zon_valor_estru_geopo a
             WHERE a.orde_1 || a.orde_2 || a.orde_3 || a.orde_4 =
                   v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
                   v_cod_dist_clie(j) || v_cod_sect_clie(j);

            IF contador = 0 THEN
              regvalido := FALSE;
            END IF;

          ELSIF (length(v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
                        v_cod_dist_clie(j)) = 18) THEN

            SELECT COUNT(1)
              INTO contador
              FROM zon_valor_estru_geopo a
             WHERE a.orde_1 || a.orde_2 || a.orde_3 =
                   v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
                   v_cod_dist_clie(j);

            IF contador = 0 THEN
              regvalido := FALSE;
            END IF;

          ELSE

            regvalido := FALSE;

          END IF;

          IF (regvalido) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_direccion%NOTFOUND;
    END LOOP;
    CLOSE c_direccion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DIREC_POSI: ' || ls_sqlerrm);

  END sto_pr_scc_direc_posi;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_NEGA_OK
                   Procedimiento de Validacion de direccion negativa de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_direc_nega
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_direccion IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.val_dire_clie,
             cons.tip_via_clie,
             cons.val_nomb_vicl,
             cons.cod_depa_clie,
             cons.cod_prov_clie,
             cons.cod_dist_clie,
             cons.cod_sect_clie,
             cons.val_barr_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_credi.val_dire_clie%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_credi.val_nomb_vicl%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_credi.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_credi.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_credi.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_credi.cod_sect_clie%TYPE;
    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_credi.val_barr_clie%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_val_dire_clie t_val_dire_clie;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_cod_depa_clie t_cod_depa_clie;
    v_cod_prov_clie t_cod_prov_clie;
    v_cod_dist_clie t_cod_dist_clie;
    v_cod_sect_clie t_cod_sect_clie;
    v_val_barr_clie t_val_barr_clie;

    rows NATURAL := 1000;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_direccion;
    LOOP
      FETCH c_direccion BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_dire_clie,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_val_barr_clie

             LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            IF (v_val_dire_clie(j) IS NULL AND v_tip_via_clie(j) IS NULL AND
               v_val_nomb_vicl(j) IS NULL) THEN

              numero := 1;

            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_direccion%NOTFOUND;
    END LOOP;
    CLOSE c_direccion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DIREC_NEGA: ' || ls_sqlerrm);

  END sto_pr_scc_direc_nega;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_ZONA_ACTI_OK
                  Procedimiento de Validacion de zona activa de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_zona_acti
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_zonaactiva IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             zona.oid_zona
        FROM zon_terri_admin       zon_admin,
             zon_secci             zona_secc,
             zon_zona              zona,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND zon_admin.zscc_oid_secc = zona_secc.oid_secc
         AND zona_secc.zzon_oid_zona = zona.oid_zona
         AND zon_admin.oid_terr_admi = cons.oid_terr_admi
         AND zona.ind_acti = 1
         AND zona.ind_borr = 0;

    CURSOR c_zonaactivacol IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             zona.oid_zona
        FROM zon_zona              zona,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND zona.cod_zona = substr(cons.uni_admi, 3, 4)
         AND zona.ind_acti = 1
         AND zona.ind_borr = 0;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    TYPE t_oid_zona IS TABLE OF zon_zona.oid_zona%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_oid_zona      t_oid_zona;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j                 BINARY_INTEGER := 0;
    ls_zona_acti_colo VARCHAR2(3);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /* Funcionalidad para validar zonas activas antes de UA - Colombia */
    -- Obtiene el parametro STO_ZONA_ACTI_COLO
    ls_zona_acti_colo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_ZONA_ACTI_COLO');

    IF ls_zona_acti_colo = 'S' THEN

      OPEN c_zonaactivacol;
      LOOP
        FETCH c_zonaactivacol BULK COLLECT
          INTO v_codpais,
               v_codperi,
               v_codclie,
               v_numlote,
               v_sec_nume_docu,
               v_oid_zona

               LIMIT rows;

        IF v_codpais.count > 0 THEN

          -- Actualizamos Documentos Validados OK
          FORALL j IN 1 .. v_codpais.count
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
        END IF;
        EXIT WHEN c_zonaactivacol%NOTFOUND;
      END LOOP;
      CLOSE c_zonaactivacol;

    ELSE

      OPEN c_zonaactiva;
      LOOP
        FETCH c_zonaactiva BULK COLLECT
          INTO v_codpais,
               v_codperi,
               v_codclie,
               v_numlote,
               v_sec_nume_docu,
               v_oid_zona

               LIMIT rows;

        IF v_codpais.count > 0 THEN

          -- Actualizamos Documentos Validados OK
          FORALL j IN 1 .. v_codpais.count
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
        END IF;
        EXIT WHEN c_zonaactiva%NOTFOUND;
      END LOOP;
      CLOSE c_zonaactiva;

    END IF;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_ZONA_ACTI: ' || ls_sqlerrm);

  END sto_pr_scc_zona_acti;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DATO_POSI_OK
                 Procedimiento de Validacion positiva de datos del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_dato_posi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_datosfiador IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.cod_docu_idfi,
             cons.tip_docu_fiad,
             cons.val_nom1_fiad,
             cons.val_ape1_fiad
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_tip_docu_fiad IS TABLE OF int_solic_conso_credi.tip_docu_fiad%TYPE;
    TYPE t_val_nom1_fiad IS TABLE OF int_solic_conso_credi.val_nom1_fiad%TYPE;
    TYPE t_val_ape1_fiad IS TABLE OF int_solic_conso_credi.val_ape1_fiad%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_cod_docu_idfi t_cod_docu_idfi;
    v_tip_docu_fiad t_tip_docu_fiad;
    v_val_nom1_fiad t_val_nom1_fiad;

    v_val_ape1_fiad t_val_ape1_fiad;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_datosfiador;
    LOOP
      FETCH c_datosfiador BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_cod_docu_idfi,
             v_tip_docu_fiad,
             v_val_nom1_fiad,
             v_val_ape1_fiad

             LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN
            numero := 0;
            IF (v_cod_docu_idfi(j) IS NOT NULL) THEN

              IF (v_tip_docu_fiad(j) IS NULL OR v_val_nom1_fiad(j) IS NULL OR
                 v_val_ape1_fiad(j) IS NULL) THEN

                numero := 1;

              END IF;

            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_datosfiador%NOTFOUND;
    END LOOP;
    CLOSE c_datosfiador;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DATO_POSI: ' || ls_sqlerrm);

  END sto_pr_scc_dato_posi;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DATO_NEGA_OK
                  Procedimiento de Validacion negativa de datos del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_dato_nega
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_datosfiador IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.cod_docu_idfi,
             cons.tip_docu_fiad,
             cons.val_nom1_fiad,
             cons.val_nom2_fiad,
             cons.val_ape1_fiad,
             cons.val_ape2_fiad,
             cons.cod_depa_fiad,
             cons.cod_prov_fiad,
             cons.cod_dist_fiad,
             cons.cod_sect_fiad,
             cons.val_dire_fiad,
             cons.val_barr_fiad,
             cons.tip_via_fiad,
             cons.val_nomb_vifi,
             cons.num_dire_fiad,
             cons.val_tefl_fiad,
             cons.val_celu_fiad,
             cons.val_telf_trfi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_tip_docu_fiad IS TABLE OF int_solic_conso_credi.tip_docu_fiad%TYPE;
    TYPE t_val_nom1_fiad IS TABLE OF int_solic_conso_credi.val_nom1_fiad%TYPE;
    TYPE t_val_nom2_fiad IS TABLE OF int_solic_conso_credi.val_nom2_fiad%TYPE;
    TYPE t_val_ape1_fiad IS TABLE OF int_solic_conso_credi.val_ape1_fiad%TYPE;
    TYPE t_val_ape2_fiad IS TABLE OF int_solic_conso_credi.val_ape2_fiad%TYPE;
    TYPE t_cod_depa_fiad IS TABLE OF int_solic_conso_credi.cod_depa_fiad%TYPE;
    TYPE t_cod_prov_fiad IS TABLE OF int_solic_conso_credi.cod_prov_fiad%TYPE;
    TYPE t_cod_dist_fiad IS TABLE OF int_solic_conso_credi.cod_dist_fiad%TYPE;
    TYPE t_cod_sect_fiad IS TABLE OF int_solic_conso_credi.cod_sect_fiad%TYPE;
    TYPE t_val_dire_fiad IS TABLE OF int_solic_conso_credi.val_dire_fiad%TYPE;
    TYPE t_val_barr_fiad IS TABLE OF int_solic_conso_credi.val_barr_fiad%TYPE;
    TYPE t_tip_via_fiad IS TABLE OF int_solic_conso_credi.tip_via_fiad%TYPE;
    TYPE t_val_nomb_vifi IS TABLE OF int_solic_conso_credi.val_nomb_vifi%TYPE;
    TYPE t_num_dire_fiad IS TABLE OF int_solic_conso_credi.num_dire_fiad%TYPE;
    TYPE t_val_tefl_fiad IS TABLE OF int_solic_conso_credi.val_tefl_fiad%TYPE;
    TYPE t_val_celu_fiad IS TABLE OF int_solic_conso_credi.val_celu_fiad%TYPE;
    TYPE t_val_telf_trfi IS TABLE OF int_solic_conso_credi.val_telf_trfi%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_cod_docu_idfi t_cod_docu_idfi;
    v_tip_docu_fiad t_tip_docu_fiad;
    v_val_nom1_fiad t_val_nom1_fiad;
    v_val_nom2_fiad t_val_nom2_fiad;
    v_val_ape1_fiad t_val_ape1_fiad;
    v_val_ape2_fiad t_val_ape2_fiad;
    v_cod_depa_fiad t_cod_depa_fiad;
    v_cod_prov_fiad t_cod_prov_fiad;
    v_cod_dist_fiad t_cod_dist_fiad;
    v_cod_sect_fiad t_cod_sect_fiad;
    v_val_dire_fiad t_val_dire_fiad;
    v_val_barr_fiad t_val_barr_fiad;
    v_tip_via_fiad  t_tip_via_fiad;
    v_val_nomb_vifi t_val_nomb_vifi;
    v_num_dire_fiad t_num_dire_fiad;
    v_val_tefl_fiad t_val_tefl_fiad;
    v_val_celu_fiad t_val_celu_fiad;
    v_val_telf_trfi t_val_telf_trfi;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_datosfiador;
    LOOP
      FETCH c_datosfiador BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_cod_docu_idfi,
             v_tip_docu_fiad,
             v_val_nom1_fiad,
             v_val_nom2_fiad,
             v_val_ape1_fiad,
             v_val_ape2_fiad,
             v_cod_depa_fiad,
             v_cod_prov_fiad,
             v_cod_dist_fiad,
             v_cod_sect_fiad,
             v_val_dire_fiad,
             v_val_barr_fiad,
             v_tip_via_fiad,
             v_val_nomb_vifi,
             v_num_dire_fiad,
             v_val_tefl_fiad,
             v_val_celu_fiad,
             v_val_telf_trfi

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN
            numero := 0;
            IF (v_cod_docu_idfi(j) IS NULL) THEN

              IF ((v_tip_docu_fiad(j) IS NOT NULL OR
                 v_val_nom1_fiad(j) IS NOT NULL OR
                 v_val_nom2_fiad(j) IS NOT NULL OR
                 v_val_ape1_fiad(j) IS NOT NULL OR
                 v_val_ape2_fiad(j) IS NOT NULL) OR
                 (v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                 v_cod_dist_fiad(j) || v_cod_sect_fiad(j) IS NOT NULL) OR

                 v_val_dire_fiad(j) IS NOT NULL OR
                 v_tip_via_fiad(j) IS NOT NULL OR
                 v_val_nomb_vifi(j) IS NOT NULL OR
                 v_num_dire_fiad(j) IS NOT NULL OR
                 v_val_tefl_fiad(j) IS NOT NULL OR
                 v_val_celu_fiad(j) IS NOT NULL OR
                 v_val_telf_trfi(j) IS NOT NULL) THEN

                numero := 1;

              END IF;

            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_datosfiador%NOTFOUND;
    END LOOP;
    CLOSE c_datosfiador;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DATO_NEGA: ' || ls_sqlerrm);

  END sto_pr_scc_dato_nega;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIREC_FIAD_OK
                     Procedimiento de Validacion de direccion  del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_direc_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_direccionfiador IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.cod_docu_idfi,
             cons.val_dire_fiad,
             cons.val_barr_fiad,
             cons.tip_via_fiad,
             cons.val_nomb_vifi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_val_dire_fiad IS TABLE OF int_solic_conso_credi.val_dire_fiad%TYPE;
    TYPE t_val_barr_fiad IS TABLE OF int_solic_conso_credi.val_barr_fiad%TYPE;
    TYPE t_tip_via_fiad IS TABLE OF int_solic_conso_credi.tip_via_fiad%TYPE;
    TYPE t_val_nomb_vifi IS TABLE OF int_solic_conso_credi.val_nomb_vifi%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_cod_docu_idfi t_cod_docu_idfi;
    v_val_dire_fiad t_val_dire_fiad;
    v_val_barr_fiad t_val_barr_fiad;
    v_tip_via_fiad  t_tip_via_fiad;
    v_val_nomb_vifi t_val_nomb_vifi;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsCampNew         VARCHAR2(1);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
                                           
    select val_para into lsCampNew 
    from bas_param_pais where cod_pais =pscodigopais
    and cod_para='055'
    and cod_sist='OCR';                                           

    OPEN c_direccionfiador;
    LOOP
      FETCH c_direccionfiador BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_cod_docu_idfi,
             v_val_dire_fiad,
             v_val_barr_fiad,
             v_tip_via_fiad,
             v_val_nomb_vifi

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN
            numero := 0;
            IF (v_cod_docu_idfi(j) IS NOT NULL) THEN
               
               IF (lsCampNew = '0') THEN
                  IF ((v_val_dire_fiad(j) IS NULL) AND
                     (v_tip_via_fiad(j) IS NULL AND v_val_nomb_vifi(j) IS NULL)) THEN

                           numero := 1;
                  END IF;
               --Campos nuevos con el nuevo formato de dirección (EC)
               ELSE
                  IF (v_val_dire_fiad(j) IS NULL) THEN
                     numero := 1;
               END IF;
              
              END IF;
              
            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_direccionfiador%NOTFOUND;
    END LOOP;
    CLOSE c_direccionfiador;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DIREC_FIAD_OK: ' || ls_sqlerrm);

  END sto_pr_scc_direc_fiad;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_TELE_FIAD_OK
                    Procedimiento de Validacion de telefono del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/05/2008


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_tele_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_telefonofiador IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.cod_docu_idfi,
             cons.val_tefl_fiad,
             cons.val_celu_fiad
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;
    TYPE t_val_tefl_fiad IS TABLE OF int_solic_conso_credi.val_tefl_fiad%TYPE;
    TYPE t_val_celu_fiad IS TABLE OF int_solic_conso_credi.val_celu_fiad%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_cod_docu_idfi t_cod_docu_idfi;
    v_val_tefl_fiad t_val_tefl_fiad;
    v_val_celu_fiad t_val_celu_fiad;

    rows   NATURAL := 1000; -- Numero de filas a procesar cada vez
    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_telefonofiador;
    LOOP
      FETCH c_telefonofiador BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_cod_docu_idfi,
             v_val_tefl_fiad,
             v_val_celu_fiad

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            IF ((v_cod_docu_idfi(j) IS NOT NULL) AND
               (v_val_tefl_fiad(j) IS NULL AND v_val_celu_fiad(j) IS NULL)) THEN

              numero := 1;

            END IF;

            IF (numero > 0) THEN

              existe := FALSE;

            ELSE

              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_telefonofiador%NOTFOUND;
    END LOOP;
    CLOSE c_telefonofiador;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_TELE_FIAD: ' || ls_sqlerrm);

  END sto_pr_scc_tele_fiad;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DNI_RUC_OK
                    Procedimiento de Validacion de DNI Y RUC  de cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dni_ruc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_dnicliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.tip_docu,
             cons.num_docu_iden,
             cons.num_ruc
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_credi.num_ruc%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_tip_docu      t_tip_docu;
    v_num_docu_iden t_num_docu_iden;
    v_num_ruc       t_num_ruc;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dnicliente;
    LOOP
      FETCH c_dnicliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_tip_docu,
             v_num_docu_iden,
             v_num_ruc LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            IF (v_tip_docu(j) IS NULL AND v_num_docu_iden(j) IS NULL AND
               v_num_ruc(j) IS NULL) THEN

              numero := 1;
                
            END IF;

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_dnicliente%NOTFOUND;
    END LOOP;
    CLOSE c_dnicliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123, 'STO_PR_SCC_DNI_RUC: ' || ls_sqlerrm);

  END sto_pr_scc_dni_ruc;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_COD_CLIE_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_cod_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.oid_pais
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    numero NUMBER := 0;
    existe BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_codclie(j) IS NOT NULL) THEN

            existe := FALSE;
            BEGIN
              numero := 0;
              SELECT COUNT(*)
                INTO numero
                FROM mae_clien
               WHERE mae_clien.cod_clie = v_codclie(j)
                 AND mae_clien.pais_oid_pais = v_oid_pais(j);

              IF (numero > 0) THEN
                existe := FALSE;

              ELSE
                existe := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                existe := TRUE;

            END;

            IF (existe) THEN
              -- Actualizamos Documentos Validados OK

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);

            END IF;

            -- DIFERENRET NULL
          ELSE
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_COD_CLIE: ' || ls_sqlerrm);

  END sto_pr_scc_cod_clie;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DOCU_RECHA_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/09/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_docu_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validadocumento IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_stat_proc,
             cons.ind_moti_rech
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_ind_stat_proc IS TABLE OF int_solic_conso_credi.ind_stat_proc%TYPE;
    TYPE t_ind_moti_rech IS TABLE OF int_solic_conso_credi.ind_moti_rech%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_stat_proc t_ind_stat_proc;
    v_ind_moti_rech t_ind_moti_rech;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j         BINARY_INTEGER := 0;
    lsmensaje VARCHAR2(500);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validadocumento;
    LOOP
      FETCH c_validadocumento BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_ind_stat_proc,
             v_ind_moti_rech LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN


        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          IF v_ind_stat_proc(j) = '02' THEN  --AND v_ind_moti_rech(j) IN ('D', 'I','N','R') THEN
            /*REGISTRO CON ERROR*/
            SELECT v_ind_moti_rech(j) || ' ' || MIN(des_moti_rech)
              INTO lsmensaje
              FROM sto_recha_motiv
             WHERE cod_tipo_docu = 'SCC'
               AND cod_modu = 'OCR'
               AND cod_moti_rech = v_ind_moti_rech(j);

            sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                 v_numlote(j),
                                                 lsmensaje);

          ELSE
          UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP


          IF v_ind_stat_proc(j) = '02' AND v_ind_moti_rech(j) IN ('D', 'I','N','X') THEN
            /*REGISTRO CON ERROR*/
            SELECT v_ind_moti_rech(j) || ' ' || MIN(des_moti_rech)
              INTO lsmensaje
              FROM sto_recha_motiv
             WHERE cod_tipo_docu = 'SCC'
               AND cod_modu = 'OCR'
               AND cod_moti_rech = v_ind_moti_rech(j);

            sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                 v_numlote(j),
                                                 lsmensaje);

          ELSE
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF;


        END LOOP;

      END IF;


      EXIT WHEN c_validadocumento%NOTFOUND;
    END LOOP;
    CLOSE c_validadocumento;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DOCU_RECHA: ' || ls_sqlerrm);

  END sto_pr_scc_docu_recha;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DIRE2_FIAD_OK
                    Procedimiento de Validacion de la direccion del fiador
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 22/10/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_dire2_fiad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_direccionfiador IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.cod_depa_fiad,
             cons.cod_prov_fiad,
             cons.cod_dist_fiad,
             cons.cod_sect_fiad,
             cons.cod_docu_idfi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_depa_fiad IS TABLE OF int_solic_conso_credi.cod_depa_fiad%TYPE;
    TYPE t_cod_prov_fiad IS TABLE OF int_solic_conso_credi.cod_prov_fiad%TYPE;
    TYPE t_cod_dist_fiad IS TABLE OF int_solic_conso_credi.cod_dist_fiad%TYPE;
    TYPE t_cod_sect_fiad IS TABLE OF int_solic_conso_credi.cod_sect_fiad%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_cod_depa_fiad t_cod_depa_fiad;
    v_cod_prov_fiad t_cod_prov_fiad;
    v_cod_dist_fiad t_cod_dist_fiad;
    v_cod_sect_fiad t_cod_sect_fiad;
    v_cod_docu_idfi t_cod_docu_idfi;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    contador NUMBER := 0;

    regvalido BOOLEAN := TRUE;

    lsparagenedirec sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparagenedirec := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_VALI_DIREC');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_direccionfiador;
    LOOP
      FETCH c_direccionfiador BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_cod_depa_fiad,
             v_cod_prov_fiad,
             v_cod_dist_fiad,
             v_cod_sect_fiad,
             v_cod_docu_idfi

             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          IF (v_cod_docu_idfi(j) IS NOT NULL) THEN
            IF ((v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
               v_cod_dist_fiad(j) || v_cod_sect_fiad(j) IS NULL) OR
               (length(v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                        v_cod_dist_fiad(j) || v_cod_sect_fiad(j)) < 18)) THEN

              regvalido := FALSE;

            ELSIF ((length(v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                           v_cod_dist_fiad(j) || v_cod_sect_fiad(j)) = 24)) THEN

              SELECT COUNT(1)
                INTO contador
                FROM zon_valor_estru_geopo a
               WHERE a.orde_1 || a.orde_2 || a.orde_3 || a.orde_4 =
                     v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                     v_cod_dist_fiad(j) || v_cod_sect_fiad(j);

              IF contador = 0 THEN
                regvalido := FALSE;
              END IF;

            ELSIF (length(v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                          v_cod_dist_fiad(j)) = 18) THEN

              SELECT COUNT(1)
                INTO contador
                FROM zon_valor_estru_geopo a
               WHERE a.orde_1 || a.orde_2 || a.orde_3 =
                     v_cod_depa_fiad(j) || v_cod_prov_fiad(j) ||
                     v_cod_dist_fiad(j);

              IF contador = 0 THEN
                regvalido := FALSE;
              END IF;

            ELSE

              regvalido := FALSE;

            END IF;
          END IF;
          IF (regvalido) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_direccionfiador%NOTFOUND;
    END LOOP;
    CLOSE c_direccionfiador;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DIRE2_FIAD: ' || ls_sqlerrm);

  END sto_pr_scc_dire2_fiad;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_COD_CLIE_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/06/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_cod_clie_dupl
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.oid_pais
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS
       (SELECT 1
                FROM int_solic_conso_credi cons2,
                     sto_docum_digit       occ2
               WHERE cons2.sec_nume_docu = occ2.sec_nume_docu
                 AND cons2.num_lote = occ2.num_lote
                 AND cons2.cod_clie = cons.cod_clie
                 AND cons2.sec_nume_docu <> occ.sec_nume_docu
                 AND occ2.ind_envi = '0'
                 AND occ2.ind_rech = '0');

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_oid_pais      t_oid_pais;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_COD_CLIE_DUPL: ' || ls_sqlerrm);

  END sto_pr_scc_cod_clie_dupl;

  /***************************************************************************
  Descripcion       : Validacion de  Monto Maximo
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_scc_codig_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_clie_rete IS NULL
      UNION
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_clien             cl
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_clie_rete IS NOT NULL
         AND cl.cod_clie = cons.cod_clie_rete
         AND EXISTS (SELECT 1
                FROM mae_clien_unida_admin
               WHERE clie_oid_clie = cl.oid_clie);

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    CURSOR referidas_acc IS
      SELECT sc.sec_nume_docu,
             rf.cor_regi,
             /*(select cod_clie
              from mae_clien a1,
                   mae_clien_ident b1
             where a1.oid_clie = b1.clie_oid_clie
               and ltrim(rf.num_docu_recte,'0') = ltrim(b1.num_docu_iden,'0')
               and rownum=1)*/
             TRIM(rf.num_docu_recte)
        FROM int_acc_refer         rf,
             int_solic_conso_credi sc,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = sc.sec_nume_docu
         AND occ.num_lote = sc.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND rf.ind_proc = 'R'
         AND rf.est_regi = '1'
         AND ltrim(sc.num_docu_iden, '0') = ltrim(rf.num_docu_recda, '0');

    TYPE t_sec_nume_docu_acc IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
    TYPE t_cor_regi_acc IS TABLE OF int_acc_refer.cor_regi%TYPE;
    TYPE t_cod_clie_recte IS TABLE OF mae_clien.cod_clie%TYPE;

    v_sec_nume_docu_acc t_sec_nume_docu_acc;
    v_cor_regi_acc      t_cor_regi_acc;
    v_cod_clie_recte    t_cod_clie_recte;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;
    k       BINARY_INTEGER := 0;

    ls_actua_recom VARCHAR2(3);
    ls_actua_codig_ceros VARCHAR2(3);
    ls_refer_error VARCHAR2(3);
    ls_refer_acc   VARCHAR2(3);

    lv_camp_actual bas_ctrl_fact.cod_peri%TYPE;

  BEGIN

    /* Funcionalidad para reemplazar referidas por las ingresadas en ACC - Colombia */
    -- Obtiene el parametro STO_REFER_ACC
    ls_refer_acc := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_REFER_ACC');

    -- Evalua si esta activa la funcionalidad para el pais
    IF ls_refer_acc = 'S' THEN
      --
      -- Obtiene la campa?a de proceso
      BEGIN
        SELECT cf.cod_peri
          INTO lv_camp_actual
          FROM bas_ctrl_fact cf
         WHERE cf.sta_camp = '0'
           AND cf.ind_camp_act = '1';
      EXCEPTION
        WHEN OTHERS THEN
          lv_camp_actual := NULL;
      END;

      OPEN referidas_acc;
      LOOP
        FETCH referidas_acc BULK COLLECT
          INTO v_sec_nume_docu_acc,
               v_cor_regi_acc,
               v_cod_clie_recte LIMIT w_filas;

        IF v_sec_nume_docu_acc.count > 0 THEN
          -- reemplaza la referida por la que vino de ACC
          FORALL j IN 1 .. v_sec_nume_docu_acc.count
            UPDATE int_solic_conso_credi cc
               SET cc.cod_clie_rete = v_cod_clie_recte(j)
             WHERE cc.sec_nume_docu = v_sec_nume_docu_acc(j);

          -- marca el registro como procesado
          FORALL k IN 1 .. v_sec_nume_docu_acc.count
            UPDATE int_acc_refer acc
               SET acc.ind_proc = 'P',
                   acc.cam_proc = lv_camp_actual,
                   acc.usu_modi = psusuario,
                   acc.fec_modi = SYSDATE
             WHERE acc.cor_regi = v_cor_regi_acc(k);

        END IF;
        EXIT WHEN referidas_acc%NOTFOUND;

      END LOOP;
      CLOSE referidas_acc;

    END IF;
    ---- 0 ----

    ls_actua_recom := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_ACTU_RECOM_DNI');


    ls_actua_codig_ceros := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_ACTU_RECOM_CEROS');


    IF ls_actua_recom = 'S' THEN

      UPDATE int_solic_conso_credi a
         SET cod_clie_rete =
             (SELECT cod_clie
                FROM mae_clien       a1,
                     mae_clien_ident b1
               WHERE a1.oid_clie = b1.clie_oid_clie
                 AND ltrim(a.cod_clie_rete, '0') =
                     ltrim(b1.num_docu_iden, '0')
                 AND rownum = 1)
       WHERE NOT EXISTS
       (SELECT 1 FROM mae_clien WHERE a.cod_clie_rete = cod_clie)
         AND EXISTS (SELECT 1
                FROM mae_clien_ident x
               WHERE ltrim(a.cod_clie_rete, '0') =
                     ltrim(x.num_docu_iden, '0'))
         AND EXISTS
       (SELECT 1
                FROM sto_tmp_docum_digit occ
               WHERE occ.sec_nume_docu = a.sec_nume_docu);        

    END IF;

    IF ls_actua_codig_ceros = 'S' THEN

      UPDATE int_solic_conso_credi a
         SET cod_clie_rete =
              substr(cod_clie_rete,length(cod_clie_rete)-(select LON_CODI_CLIE from bas_pais where cod_pais=pscodigopais)+1)
       WHERE NOT EXISTS
       (SELECT 1 FROM mae_clien WHERE a.cod_clie_rete = cod_clie)
         AND EXISTS (SELECT 1 FROM mae_clien WHERE ltrim(a.cod_clie_rete,'0') = ltrim(cod_clie,'0'))
         AND EXISTS
       (SELECT 1
                FROM sto_tmp_docum_digit occ
               WHERE occ.sec_nume_docu = a.sec_nume_docu);

    END IF;





    /* Funcionalidad para dejar pasar solicitudes con error de referidas - Colombia */
    -- Obtiene el parametro
    ls_refer_error := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_REFER_ERROR');

    -- Evalua si esta activa la funcionalidad para el pais
    IF ls_refer_error = 'S' THEN
      -- si esta activa la funcionalidad, se coloca en null el codigo de recomendante
      -- a todos los registros con error de recomendante para que pase la validacion
      -- y se marca el registro para su gestion posterior
      UPDATE int_solic_conso_credi a
         SET a.cod_clie_rete = NULL,
             a.ind_erro_refe = 'S'
       WHERE NOT EXISTS
       (SELECT 1 FROM mae_clien WHERE a.cod_clie_rete = cod_clie)
         AND a.cod_clie_rete IS NOT NULL
         AND EXISTS
       (SELECT 1
                FROM sto_tmp_docum_digit occ
               WHERE occ.sec_nume_docu = a.sec_nume_docu);

    END IF;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SCC_CODIG_RECOM: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_codig_recom;

  /***************************************************************************
  Descripcion       : Validacion de Indicadores Manuales
  Fecha Creacion    : 07/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_indic_manua
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
            -- Cambio para que lea los indicadores de los checks en pantalla --
         AND (cons.ind_tele_ok <> '0' OR
             nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                  'OCR',
                                                  '042'),
                  '0') = '0')
         AND (cons.ind_situ_cred <> '0' OR
             nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                  'OCR',
                                                  '043'),
                  '0') = '0')
         AND (cons.ind_sin_sald_amba_marc <> '0' OR
             nvl(gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                  'OCR',
                                                  '044'),
                  '0') = '0');

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_scc_indic_manua: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_indic_manua;

  /***************************************************************************
  Descripcion       : Validacion negativa de la direccion de entrega del cliente
  Fecha Creacion    : 08/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_direc_entre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.val_dire_entre_clie IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_scc_direc_entre: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_direc_entre;

  /***************************************************************************
  Descripcion       : Validacion del telefono de entrega del cliente
  Fecha Creacion    : 08/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_telef_entre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.val_tele_entre_clie IS NOT NULL OR
             cons.val_celu_entre_clie IS NOT NULL);

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN

        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_scc_telef_entre: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_telef_entre;

  /***************************************************************************
  Descripcion       : Validacion de Referencia familiar
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer_famil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_ape1_refe_fami_clie,
             cons.val_nom1_refe_fami_clie,
             cons.val_tele_refe_fami_clie,
             cons.val_celu_refe_fami_clie,
             cons.val_dire_refe_fami_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_ape1_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_ape1_refe_fami_clie%TYPE;
    TYPE t_val_nom1_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_nom1_refe_fami_clie%TYPE;
    TYPE t_val_tele_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_tele_refe_fami_clie%TYPE;
    TYPE t_val_celu_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_celu_refe_fami_clie%TYPE;
    TYPE t_val_dire_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_dire_refe_fami_clie%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu           t_sec_nume_docu;
    v_val_ape1_refe_fami_clie t_val_ape1_refe_fami_clie;
    v_val_nom1_refe_fami_clie t_val_nom1_refe_fami_clie;
    v_val_tele_refe_fami_clie t_val_tele_refe_fami_clie;
    v_val_celu_refe_fami_clie t_val_celu_refe_fami_clie;
    v_val_dire_refe_fami_clie t_val_dire_refe_fami_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_ape1_refe_fami_clie,
             v_val_nom1_refe_fami_clie,
             v_val_tele_refe_fami_clie,
             v_val_celu_refe_fami_clie,
             v_val_dire_refe_fami_clie LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          IF ((v_val_ape1_refe_fami_clie(j) IS NOT NULL) OR
             (v_val_nom1_refe_fami_clie(j) IS NOT NULL) OR
             (v_val_tele_refe_fami_clie(j) IS NOT NULL) OR
             (v_val_celu_refe_fami_clie(j) IS NOT NULL)) THEN

            IF ((v_val_ape1_refe_fami_clie(j) IS NULL) OR
               (v_val_nom1_refe_fami_clie(j) IS NULL) OR
               ((v_val_tele_refe_fami_clie(j) IS NULL) AND
               (v_val_celu_refe_fami_clie(j) IS NULL)) OR
               (v_val_dire_refe_fami_clie(j) IS NULL)) THEN

              existe := FALSE;

            END IF;

            IF (existe) THEN
              -- Actualizamos Documentos Validados OK

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);

            END IF;

          END IF;
        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_REFER_FAMIL: ' || ls_sqlerrm);

  END sto_pr_scc_refer_famil;

  /***************************************************************************
  Descripcion       : Validacion de Referencia no familiar
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer_nofam
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_ape1_refe_nofa_clie,
             cons.val_nom1_refe_nofa_clie,
             cons.val_tele_refe_nofa_clie,
             cons.val_celu_refe_nofa_clie,
             cons.val_dire_refe_nofa_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_ape1_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_ape1_refe_nofa_clie%TYPE;
    TYPE t_val_nom1_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_nom1_refe_nofa_clie%TYPE;
    TYPE t_val_tele_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_tele_refe_nofa_clie%TYPE;
    TYPE t_val_celu_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_celu_refe_nofa_clie%TYPE;
    TYPE t_val_dire_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_dire_refe_nofa_clie%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu           t_sec_nume_docu;
    v_val_ape1_refe_nofa_clie t_val_ape1_refe_nofa_clie;
    v_val_nom1_refe_nofa_clie t_val_nom1_refe_nofa_clie;
    v_val_tele_refe_nofa_clie t_val_tele_refe_nofa_clie;
    v_val_celu_refe_nofa_clie t_val_celu_refe_nofa_clie;
    v_val_dire_refe_nofa_clie t_val_dire_refe_nofa_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_ape1_refe_nofa_clie,
             v_val_nom1_refe_nofa_clie,
             v_val_tele_refe_nofa_clie,
             v_val_celu_refe_nofa_clie,
             v_val_dire_refe_nofa_clie LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          IF ((v_val_ape1_refe_nofa_clie(j) IS NOT NULL) OR
             (v_val_nom1_refe_nofa_clie(j) IS NOT NULL) OR
             (v_val_tele_refe_nofa_clie(j) IS NOT NULL) OR
             (v_val_celu_refe_nofa_clie(j) IS NOT NULL)) THEN

            IF ((v_val_ape1_refe_nofa_clie(j) IS NULL) OR
               (v_val_nom1_refe_nofa_clie(j) IS NULL) OR
               ((v_val_tele_refe_nofa_clie(j) IS NULL) AND
               (v_val_celu_refe_nofa_clie(j) IS NULL))) OR
               (v_val_dire_refe_nofa_clie IS NULL) THEN

              existe := FALSE;

            END IF;

            IF (existe) THEN
              -- Actualizamos Documentos Validados OK

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);

            END IF;

          END IF;
        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_REFER_NOFAM: ' || ls_sqlerrm);

  END sto_pr_scc_refer_nofam;

  /***************************************************************************
  Descripcion       : Validacion de Referencia
   Fecha Creacion    : 23/11/2009
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_refer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_nom1_refe_fami_clie,
             cons.val_nom1_refe_nofa_clie

        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_nom1_refe_fami_clie IS TABLE OF int_solic_conso_credi.val_nom1_refe_fami_clie%TYPE;
    TYPE t_val_nom1_refe_nofa_clie IS TABLE OF int_solic_conso_credi.val_nom1_refe_nofa_clie%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu           t_sec_nume_docu;
    v_val_nom1_refe_fami_clie t_val_nom1_refe_fami_clie;
    v_val_nom1_refe_nofa_clie t_val_nom1_refe_nofa_clie;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_nom1_refe_fami_clie,
             v_val_nom1_refe_nofa_clie LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          IF ((v_val_nom1_refe_fami_clie(j) IS NULL) AND
             (v_val_nom1_refe_nofa_clie(j) IS NULL)) THEN

            existe := FALSE;
          END IF;

          IF (existe) THEN
            -- Actualizamos Documentos Validados OK

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123, 'STO_PR_SCC_REFER: ' || ls_sqlerrm);

  END sto_pr_scc_refer;

  /***************************************************************************
  Descripcion       : Validacion de tipo de documento
   Fecha Creacion    : 04/02/2010
   Autor             : Cristhian Roman
   ***************************************************************************/
  PROCEDURE sto_pr_scc_tipo_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_docu,
             cons.num_docu_iden,
             cons.fec_naci
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_fec_naci IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
       
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_tip_docu      t_tip_docu;
    v_num_docu_iden t_num_docu_iden;
    v_fec_naci      t_fec_naci;
    
    l_oid_esta_clie     mae_clien_datos_adici.esta_oid_esta_clie%TYPE;
    l_ind_acti          mae_clien_datos_adici.ind_acti%TYPE;
    l_fec_naci          mae_clien_datos_adici.fec_naci%TYPE;
    l_oid_clie          mae_clien_ident.clie_oid_clie%TYPE;
    l_cod_clie          mae_clien.cod_clie%TYPE;
    l_oid_terr_admi     mae_clien_unida_admin.ztad_oid_terr_admi%TYPE;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;
    
    numero integer:= 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_tip_docu,
             v_num_docu_iden, 
             v_fec_naci LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          IF ((v_tip_docu(j) IS NULL) OR (v_num_docu_iden(j) IS NULL)) THEN

            existe := FALSE;
           
          ELSE
            BEGIN
                  SELECT a.clie_oid_clie,
                         b.esta_oid_esta_clie,
                         b.ind_acti,
                         b.fec_naci,
                         d.ztad_oid_terr_admi,
                         c.cod_clie
                    INTO l_oid_clie,
                         l_oid_esta_clie,
                         l_ind_acti,
                         l_fec_naci,
                         l_oid_terr_admi,
                         l_cod_clie
                    FROM mae_clien_ident       a,
                         mae_clien_datos_adici b,
                         mae_clien             c,
                         mae_clien_unida_admin d
                   WHERE a.clie_oid_clie = c.oid_clie(+)
                     AND a.clie_oid_clie = b.clie_oid_clie(+)
                     AND a.clie_oid_clie = d.clie_oid_clie(+)
                     AND 1 = d.ind_acti(+)
                     AND a.num_docu_iden = v_num_docu_iden(j);

                  --En caso ser retirada
                  IF (l_oid_clie IS NOT NULL AND
                     l_oid_esta_clie IS NOT NULL AND
                     l_ind_acti IS NOT NULL AND l_fec_naci IS NOT NULL AND
                     l_oid_terr_admi IS NOT NULL 
                     AND l_oid_esta_clie in (1,7) 
                     ) THEN
                     
                    --Se queda en la validación cuando tienen diferentes tipos de documentos
                    select count(*) into numero
                    from mae_clien_ident mci, mae_tipo_docum mtd
                    where mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
                    and mci.num_docu_iden = v_num_docu_iden(j)
                    and mtd.cod_tipo_docu <> v_tip_docu(j);
                
                 END IF;
                     
              EXCEPTION
                WHEN OTHERS THEN
                  --En caso de ser nueva, valida solo por numero de documento
                select count(*) into numero
                from mae_clien_ident mci
                where mci.num_docu_iden = v_num_docu_iden(j);
                
              END;  
                
                IF numero > 0 then
                   existe:= FALSE;
                END IF;
          END IF;

          IF (existe) THEN
            -- Actualizamos Documentos Validados OK

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF; -- SI E SMAYOR Q 0
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_TIPO_DOCUM: ' || ls_sqlerrm);

  END sto_pr_scc_tipo_docum;

  /***************************************************************************
   Descripcion       : Validacion de Nueva en segundo dia
   Fecha Creacion    : 26/04/2010
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_nueva_sedia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_ruccliente IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_pais,
             cons.oid_peri,
             cons.oid_terr_admi,
             cons.fec_proc
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_credi.oid_pais%TYPE;
    TYPE t_oid_peri IS TABLE OF int_solic_conso_credi.oid_peri%TYPE;
    TYPE t_oid_terri_admi IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;
    TYPE t_fec_proc IS TABLE OF int_solic_conso_credi.fec_proc%TYPE;

    v_codpais        t_codpais;
    v_numlote        t_numlote;
    v_sec_nume_docu  t_sec_nume_docu;
    v_oid_pais       t_oid_pais;
    v_oid_peri       t_oid_peri;
    v_oid_terri_admi t_oid_terri_admi;
    v_fec_proc       t_fec_proc;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    existe       BOOLEAN := TRUE;
    tmpfechafact cra_crono.fec_inic%TYPE;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_ruccliente;
    LOOP
      FETCH c_ruccliente BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_oid_pais,
             v_oid_peri,
             v_oid_terri_admi,
             v_fec_proc LIMIT rows;
      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;
          BEGIN

            SELECT fec_inic
              INTO tmpfechafact
              FROM cra_crono
             WHERE cact_oid_acti = (SELECT oid_acti
                                      FROM cra_activ
                                     WHERE pais_oid_pais = v_oid_pais(j)
                                       AND cod_acti = 'FA')
               AND perd_oid_peri = v_oid_peri(j)
               AND zzon_oid_zona =
                   (SELECT a.oid_zona
                      FROM zon_zona        a,
                           zon_secci       b,
                           zon_terri_admin c
                     WHERE c.zscc_oid_secc = b.oid_secc
                       AND b.zzon_oid_zona = a.oid_zona
                       AND c.oid_terr_admi = v_oid_terri_admi(j));

            IF (tmpfechafact < v_fec_proc(j)) THEN
              existe := FALSE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;

          IF (existe) THEN
            -- Actualizamos Documentos Validados OK
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_ruccliente%NOTFOUND;
    END LOOP;
    CLOSE c_ruccliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_nueva_sedia: ' || ls_sqlerrm);

  END sto_pr_scc_nueva_sedia;

  /***************************************************************************
  Descripcion       : Validacion Tipo Via  de la Direccion del Cliente
  Fecha Creacion    : 02/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_tipvi_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_direccion IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_via_clie,
             cons.val_nomb_vicl
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_credi.val_nomb_vicl%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;

    rows   NATURAL := 1000;
    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    cont   NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_direccion;
    LOOP
      FETCH c_direccion BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_tip_via_clie,
             v_val_nomb_vicl LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;
          IF (v_tip_via_clie(j) IS NULL AND v_val_nomb_vicl(j) IS NULL) THEN
            existe := FALSE;
          END IF;
          SELECT COUNT(1)
            INTO cont
            FROM seg_tipo_via a
           WHERE a.cod_tipo_via = v_tip_via_clie(j);
          IF (cont = 0) THEN
            existe := FALSE;
          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_direccion%NOTFOUND;
    END LOOP;
    CLOSE c_direccion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_tipvi_direc: ' || ls_sqlerrm);

  END sto_pr_scc_tipvi_direc;

  /***************************************************************************
  Descripcion       : Validacion de consultora Aval de consultora con deuda
  Fecha Creacion    : 11/06/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_avala_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_direccion IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_docu_idfi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_docu_idfi IS TABLE OF int_solic_conso_credi.cod_docu_idfi%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_docu_idfi t_cod_docu_idfi;

    rows     NATURAL := 1000;
    j        BINARY_INTEGER := 0;
    is_valid BOOLEAN := TRUE;

    ls_dias_deuda  VARCHAR2(3);
    ls_monto_deuda VARCHAR2(3);
    ls_cont        NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    ls_dias_deuda  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_DIAS_DEUD_VENC');
    ls_monto_deuda := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_MONT_DEUD_VENC');

    OPEN c_direccion;
    LOOP
      FETCH c_direccion BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_cod_docu_idfi LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          is_valid := TRUE;

          SELECT COUNT(1)
            INTO ls_cont
            FROM (SELECT cod_clie,
                         (SELECT SUM(imp_pend)
                            FROM ccc_movim_cuent_corri
                           WHERE clie_oid_clie =
                                 (SELECT oid_clie
                                    FROM mae_clien
                                   WHERE cod_clie = a.cod_clie)
                             AND (SYSDATE - fec_venc) > ls_dias_deuda) deuda
                    FROM mae_refer a
                   WHERE a.tipo_refe = 3
                     AND lpad(a.num_docu_refe, 20, '0') =
                         lpad(v_cod_docu_idfi(j), 20, '0'))
           WHERE deuda > ls_monto_deuda;

          IF ls_cont != 0 THEN
            is_valid := FALSE;
          END IF;

          IF (is_valid) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_direccion%NOTFOUND;
    END LOOP;
    CLOSE c_direccion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_avala_deuda: ' || ls_sqlerrm);

  END sto_pr_scc_avala_deuda;

  /***************************************************************************
  Descripcion       : Validacion de DNI Duplicado
  Fecha Creacion    : 14/10/2010
  Autor             : Jose Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_scc_dni_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_dni IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND num_docu_iden IS NOT NULL
         AND cons.num_docu_iden IN
             (SELECT num_docu_iden
                FROM int_solic_conso_credi c,
                     sto_docum_digit       d
               WHERE d.sec_nume_docu = c.sec_nume_docu
                 AND d.num_lote = c.num_lote
                 AND d.cod_tipo_docu = pscodigotipodoc
                 AND d.cod_pais = pscodigopais
                 AND d.ind_rech = '0'
                 AND d.ind_envi = '0'
                 AND c. num_docu_iden IS NOT NULL
               GROUP BY c.num_docu_iden
              HAVING COUNT(1) = 1);

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    j       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dni;
    LOOP
      FETCH c_dni BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_dni%NOTFOUND;

    END LOOP;
    CLOSE c_dni;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SCC_DNI_DUPLI: ' || ls_sqlerrm);

  END sto_pr_scc_dni_dupli;

  /**************************************************************************
    Descripcion       : Validacion Nivel Educativo Estado Civil
    Fecha Creacion    : 09/09/2010
    Autor             : Jose Cairampma
  **************************************************************************/
  PROCEDURE sto_pr_scc_niedu_esciv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validacion IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.ind_nive_educ IS NULL OR
             cons.ind_nive_educ IN
             (SELECT cod_nive_estu FROM mae_nivel_estud))
         AND (cons.ind_esta_civi IS NULL OR
             cons.ind_esta_civi IN
             (SELECT cod_esta_civi FROM mae_estad_civil));

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_NIEDU_ESCIV: ' || ls_sqlerrm);

  END sto_pr_scc_niedu_esciv;

  /***************************************************************************
  Descripcion       : Validacion de zona de arribo
  Fecha Creacion    : 19/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_zonas_arribo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_direccion IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_zona_arri,
             cons.oid_terr_admi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_zona_arri IS TABLE OF int_solic_conso_credi.val_zona_arri%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_credi.oid_terr_admi%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_val_zona_arri t_val_zona_arri;
    v_oid_terr_admi t_oid_terr_admi;

    rows     NATURAL := 1000;
    j        BINARY_INTEGER := 0;
    is_valid BOOLEAN := TRUE;

    vcodzona zon_zona.cod_zona%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_direccion;
    LOOP
      FETCH c_direccion BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_val_zona_arri,
             v_oid_terr_admi LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          is_valid := TRUE;

          BEGIN
            SELECT c.cod_zona
              INTO vcodzona
              FROM zon_terri_admin a,
                   zon_secci       b,
                   zon_zona        c
             WHERE a.zscc_oid_secc = b.oid_secc
               AND b.zzon_oid_zona = c.oid_zona
               AND a.oid_terr_admi = v_oid_terr_admi(j);
          EXCEPTION
            WHEN no_data_found THEN
              vcodzona := NULL;
          END;

          IF v_val_zona_arri(j) != vcodzona THEN
            is_valid := FALSE;
          END IF;

          IF (is_valid) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_direccion%NOTFOUND;
    END LOOP;
    CLOSE c_direccion;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_zonas_arribo: ' || ls_sqlerrm);

  END sto_pr_scc_zonas_arribo;

  /***************************************************************************
  Descripcion       : Validacion Modulo diez del codigo de Cliente
  Fecha Creacion    : 08/11/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_modul_diez
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_codigo IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.num_docu_iden,
             ltrim(CONS.TIP_DOCU,'0')
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_docu_iden IS NOT NULL
         AND length(translate(TRIM(cons.num_docu_iden), ' 0123456789', ' ')) IS NULL
      /*AND sto_pkg_gener.sto_fn_valid_modul_diez(cons.num_docu_iden) = '1'*/
      ;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_numdocuiden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_tipdocu IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_numdocuiden   t_numdocuiden;
    v_tipdocu       t_tipdocu;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_codigo;
    LOOP
      FETCH c_codigo BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_numdocuiden,
             v_tipdocu LIMIT rows;
      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        --FORALL j IN 1 .. v_codpais.count
        FOR j IN 1 .. v_codpais.count
        LOOP
          IF sto_pkg_gener.sto_fn_valid_modul_diez(v_numdocuiden(j)) = '1' or v_tipdocu(j)<>'1' THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_codigo%NOTFOUND;
    END LOOP;
    CLOSE c_codigo;
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_modul_diez: ' || ls_sqlerrm);

  END sto_pr_scc_modul_diez;

  /***************************************************************************
  Descripcion       : Validacion de Numero de Cedula de las consultoras solo
                      acepte numeros y no acepte ceros a la izquierda.
  Fecha Creacion    : 16/03/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_cedul
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_dni IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND num_docu_iden IS NOT NULL
            -- Se valida que acepte solo numeros
         AND length(translate(TRIM(cons.num_docu_iden), ' 0123456789', ' ')) IS NULL
            -- Se valida que no se acpete ceros a la izauierda
         AND length(substr(cons.num_docu_iden,
                           0,
                           length(cons.num_docu_iden) -
                           length(ltrim(cons.num_docu_iden, '0')))) IS NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    j       BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dni;
    LOOP
      FETCH c_dni BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_dni%NOTFOUND;

    END LOOP;
    CLOSE c_dni;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_numer_cedul: ' || ls_sqlerrm);

  END sto_pr_scc_numer_cedul;

  /**************************************************************************
  Descripcion       : Validacion del numero de DNI, solo acepta numeros
  Fecha Creacion    : 22/02/2012
  Autor             : Ivan Tocto
  **************************************************************************/
  PROCEDURE sto_pr_scc_dni_numer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_dnicliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.tip_docu <> '01' OR
             (cons.tip_docu = '01' AND
             decode(length(translate(cons.num_docu_iden,
                                       '_0123456789',
                                       '_')),
                      NULL,
                      1,
                      0) = 1));

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dnicliente;
    LOOP
      FETCH c_dnicliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_dnicliente%NOTFOUND;
    END LOOP;
    CLOSE c_dnicliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DNI_NUMER: ' || ls_sqlerrm);

  END sto_pr_scc_dni_numer;

  /**************************************************************************
  Descripcion       : Validacion del numero de RUC, solo acepta numeros
  Fecha Creacion    : 22/02/2012
  Autor             : Ivan Tocto
  **************************************************************************/
  PROCEDURE sto_pr_scc_ruc_numer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_dnicliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.tip_docu <> '02' OR
             (cons.tip_docu = '02' AND
             decode(length(translate(cons.num_docu_iden,
                                       '_0123456789',
                                       '_')),
                      NULL,
                      1,
                      0) = 1))
         AND decode(length(translate(cons.num_ruc, '_0123456789', '_')),
                    NULL,
                    1,
                    0) = 1;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dnicliente;
    LOOP
      FETCH c_dnicliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
        END LOOP;
      END IF;
      EXIT WHEN c_dnicliente%NOTFOUND;
    END LOOP;
    CLOSE c_dnicliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_RUC_NUMER: ' || ls_sqlerrm);

  END sto_pr_scc_ruc_numer;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_NUMER_DOCUM_MOD11
                    Procedimiento de documento de identidad con modulo 11
  Fecha Creacion    : 25/07/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_docum_mod11
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validapais IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.num_docu_iden
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;

    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    v_num_docu_iden t_num_docu_iden;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    lbsolicitudvalida BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validapais;
    LOOP
      FETCH c_validapais BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_num_docu_iden LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          lbsolicitudvalida := TRUE;
          IF v_num_docu_iden(i) IS NOT NULL AND
             mae_pkg_proce_clien.mae_fn_valid_numer_docum_mod11(v_num_docu_iden(i)) = 0 THEN
            lbsolicitudvalida := FALSE;
          END IF;
          IF lbsolicitudvalida THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validapais%NOTFOUND;
    END LOOP;
    CLOSE c_validapais;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_NUMER_DOCUM_MOD11: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_numer_docum_mod11;

  /**************************************************************************
   Descripcion       : STO_PR_SCC_BOLET_ELECT
                       Procedimiento de Boletin Comercial
   Fecha Creacion    : 04/09/2012
   Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bolet_elect
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_boletin IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.val_esta_info_come
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_esta_info_come IS TABLE OF int_solic_conso_credi.val_esta_info_come%TYPE;

    v_num_lote           t_num_lote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_val_esta_info_come t_val_esta_info_come;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    lbflag BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_boletin;
    LOOP
      FETCH c_boletin BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_val_esta_info_come LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          lbflag := TRUE;

          -- Si el estado que devuelve el Web Service '07', '08', '09', '10', '11', '12'
          -- se considera a la consultora Roja y no pasa la validacion
          -- [Si es nulo, no pasa la validacion]

          IF to_number(nvl(v_val_esta_info_come(i), '0')) IN
             (0, 7, 8, 9, 10, 11, 12) THEN
            lbflag := FALSE;
          END IF;

          IF lbflag THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_boletin%NOTFOUND;
    END LOOP;
    CLOSE c_boletin;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SCC_BOLET_ELECT: ' ||
                              ls_sqlerrm);
  END sto_pr_scc_bolet_elect;

  /***************************************************************************
  Descripcion       : Validacion de Formato de Documento de Identidad.
  Fecha Creacion    : 12/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE sto_pr_scc_forma_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_dni IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND num_docu_iden IS NOT NULL
            -- Se valida que tenga dos guiones en el numero de documento
         AND decode(length(cons.num_docu_iden) -
                    length(REPLACE(cons.num_docu_iden, '-')),
                    2,
                    cons.num_docu_iden,
                    NULL) IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    j       BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_dni;
    LOOP
      FETCH c_dni BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_dni%NOTFOUND;

    END LOOP;
    CLOSE c_dni;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_format_docum: ' || ls_sqlerrm);

  END sto_pr_scc_forma_docum;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CODIG_CLIEN
                        Procedimiento de Validacion del codigo del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion no va por unificacion de la SC y Metas
                        ya que el codigo de consultora no existe todavia.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS
       (SELECT 1 FROM mae_clien cli WHERE cli.cod_clie = cons.cod_clie);

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_CODIG_CLIEN: ' ||
                              ls_sqlerrm);

  END sto_pr_sim_codig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_ANTIG_CLIEN
                        Procedimiento de Validacion de Antiguedad del Cliente
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion no va por unificacion de la SC y Metas
                        ya que todas son nuevas.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_antig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_clien             cl,
             mae_clien_datos_adici mc
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cl.oid_clie = mc.clie_oid_clie
         AND cl.cod_clie = cons.cod_clie
         AND mc.esta_oid_esta_clie IN (1, 2, 7, 8)
         AND mc.ind_acti = 1;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_ANTIG_CLIEN: ' ||
                              ls_sqlerrm);

  END sto_pr_sim_antig_clien;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_EXIST_METRE
                        Procedimiento de Validacion de Meta Existente
                        ya registrada
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion no va por unificacion de la SC y Metas
                        ya que no tenemos codigo de consultora y no se puede
                        verificar si la meta ya ha sido registrada.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_exist_metre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_meta
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_credi.tip_meta%TYPE;
    TYPE t_mntmaximo IS TABLE OF int_solic_conso_credi.val_mnto_meta%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tipmeta    t_tipmeta;
    v_mntomaximo t_mntmaximo;
    isvalid      BOOLEAN := TRUE;
    lntipometa   VARCHAR2(1);

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el Monto Maximo de la Meta
    --lsMontoMaximo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_MONT_MAXI_META');

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_tipmeta LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := TRUE;

          BEGIN
            SELECT a.cod_tipo_logr
              INTO lntipometa
              FROM nvs_tipo_logro a
             WHERE a.cod_tipo_logr = v_tipmeta(j);

            IF lntipometa IS NOT NULL THEN
              isvalid := TRUE;
            END IF;

          EXCEPTION
            WHEN OTHERS THEN
              isvalid := FALSE;
              --lnMontoMaximo := 0;
          END;

          -- Actualizamos Documentos Validados OK
          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_secnumdocu(j);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_TIPO_META: ' || ls_sqlerrm);

  END sto_pr_sim_exist_metre;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_MONTO_MXMTA
                        Procedimiento de Validacion de Monto Maximo y Minimo de Meta
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Solo aplica a las SC que ingresaron Tipo de Meta y Monto
                        estos datos ya no son obligatorios por la unificacion.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_monto_mxmta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_meta,
             cons.val_mnto_meta
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         --AND cons.tip_meta IS NOT NULL
         ;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_credi.tip_meta%TYPE;
    TYPE t_mntmeta IS TABLE OF int_solic_conso_credi.val_mnto_meta%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tipmeta    t_tipmeta;
    v_mntometa   t_mntmeta;

    isvalid       BOOLEAN := TRUE;
    lnmontomaximo NUMBER(12, 2);
    lnmontominimo NUMBER(12, 2);

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el Monto Maximo de la Meta
    --lsMontoMaximo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_MONT_MAXI_META');

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_tipmeta,
             v_mntometa LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := TRUE;

          BEGIN
            SELECT a.imp_maxi_logr,
                   a.imp_mini_logr
              INTO lnmontomaximo,
                   lnmontominimo
              FROM nvs_tipo_logro a
             WHERE a.cod_tipo_logr = v_tipmeta(j);

            lnmontomaximo := to_number(lnmontomaximo, '999999999');
            lnmontominimo := to_number(lnmontominimo, '999999999');
            v_mntometa(j) := to_number(v_mntometa(j), '999999999');

          EXCEPTION
            WHEN OTHERS THEN
              lnmontomaximo := 0;
              lnmontominimo := 0;
              --isvalid := FALSE;
              --lnMontoMaximo := 0;
          END;

            IF v_mntometa(j) < lnmontominimo OR
               v_mntometa(j) > lnmontomaximo THEN
              isvalid := FALSE;
            END IF;

            if v_mntometa(j) is null and v_tipmeta(j) is null then
               isvalid := TRUE;
            end if;

          -- Actualizamos Documentos Validados OK
          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_secnumdocu(j);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_MONTO_META: ' || ls_sqlerrm);

  END sto_pr_sim_monto_mxmta;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_CAMPA_INICI
                        Procedimiento de Validacion de Campa?a de Inicio
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion no va por unificacion de la SC y Metas
                        ya que la campa?a de incio es igual a la campa?a activa
                        y se graba en la recepcion.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_campa_inici
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.cod_pais,
             cons.cod_comp,
             cons.cod_clie,
             cons.num_docu,
             cons.num_lote,
             cons.sec_nume_docu,
             clad.esta_oid_esta_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_clien             cl,
             mae_clien_datos_adici clad
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cl.cod_clie = cons.cod_clie
         AND cl.oid_clie = clad.clie_oid_clie
         AND clad.ind_acti = 1;
    --         AND clad.esta_oid_esta_clie IN (1,2);

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_codcomp IS TABLE OF int_solic_conso_actua_datos.cod_comp%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_actua_datos.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_estaoidestaclie IS TABLE OF mae_clien_datos_adici.esta_oid_esta_clie%TYPE;

    v_codpais         t_codpais;
    v_codcomp         t_codcomp;
    v_numlote         t_numlote;
    v_secnumdocu      t_secnumdocu;
    v_codclie         t_codclie;
    v_numdocu         t_numdocu;
    v_estaoidestaclie t_estaoidestaclie;

    ls_codperio seg_perio_corpo.cod_peri%TYPE;
    ln_idperio  cra_perio.oid_peri%TYPE;
    i           BINARY_INTEGER := 0;
    isvalid     BOOLEAN := TRUE;

    ln_id_pais  seg_pais.oid_pais%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    ln_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais, TRUE);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T', TRUE);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD', TRUE);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codpais,
             v_codcomp,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_secnumdocu,
             v_estaoidestaclie LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR i IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          -- Obtenemos el Id Periodo
          BEGIN
            SELECT mpc.perd_oid_peri
              INTO ln_idperio
              FROM mae_clien_prime_conta mpc,
                   mae_clien             mc
             WHERE lpad(mc.cod_clie, 15, '0') = lpad(v_codclie(i), 15, '0')
               AND mc.oid_clie = mpc.clie_oid_clie;
          EXCEPTION
            WHEN no_data_found THEN
              isvalid := FALSE;
          END;
          -- Obtenemos el Cod Periodo
          BEGIN
            SELECT seg.cod_peri
              INTO ls_codperio
              FROM cra_perio       cra,
                   seg_perio_corpo seg
             WHERE cra.peri_oid_peri = seg.oid_peri
               AND cra.oid_peri = ln_idperio;
          EXCEPTION
            WHEN no_data_found THEN
              isvalid := FALSE;
          END;

          -- DOI 11-10-2011
          BEGIN
            -- Si no devuelve registros, que lo detenga la validacion,
            -- sino se cae al hacer el envio a SiCC
            IF per_pkg_repor_perce.per_fn_obtie_perio(ls_codperio,
                                                      ln_id_pais,
                                                      ln_id_marca,
                                                      ln_id_canal,
                                                      cantidad_periodos) IS NULL THEN
              isvalid := FALSE;
            END IF;
          EXCEPTION
            WHEN OTHERS THEN
              isvalid := FALSE;
          END;
          --

          -- Actualizamos CAMPOS ADICIONALES
          IF ls_codperio IS NOT NULL AND
             v_estaoidestaclie(i) IN (1, 2, 7, 8) THEN
            UPDATE int_solic_conso_credi dat
               SET dat.cod_camp_inic = ls_codperio
             WHERE dat.cod_pais = v_codpais(i)
               AND lpad(dat.cod_clie, 15, '0') =
                   lpad(v_codclie(i), 15, '0')
               AND dat.num_docu = v_numdocu(i)
               AND dat.num_lote = v_numlote(i);
          END IF;

          -- Actualizamos Documentos Validados OK

          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumdocu(i);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_CAMPA_INICI: ' ||
                              ls_sqlerrm);

  END sto_pr_sim_campa_inici;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_DATOS_OBLIG
                        Procedimiento de Validacion de Datos Obligatorios
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion solo aplica si llega Tipo de Meta o Monto
                        ya que los datos no son obligatorios por la unificacion.
  ***************************************************************************/
  PROCEDURE sto_pr_sim_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_meta,
             cons.val_mnto_meta,
             cons.des_meta
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    /*AND( cons.tip_meta is not null
    and cons.des_meta is not null
    and  cons.val_mnto_meta IS not NULL
    and to_number(cons.val_mnto_meta,'999999999') > 0) ; */

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_credi.tip_meta%TYPE;
    TYPE t_mntmeta IS TABLE OF int_solic_conso_credi.val_mnto_meta%TYPE;
    TYPE t_desmeta IS TABLE OF int_solic_conso_credi.des_meta%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_tipmeta    t_tipmeta;
    v_mntmeta    t_mntmeta;
    v_desmeta    t_desmeta;

    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    i       BINARY_INTEGER := 0;

    lsmetarechazasc VARCHAR2(1);
    lntipometa      VARCHAR2(1);
    lnmontomaximo   NUMBER(12, 2);
    lnmontominimo   NUMBER(12, 2);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el parametro si la Meta rechaza la SC
    lsmetarechazasc := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_META_RECHAZA_SC'),
                           'N');

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_tipmeta,
             v_mntmeta,
             v_desmeta LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := TRUE;

          IF lsmetarechazasc = 'S' THEN
            -- funcionada para los paises que tienen implementadas las validaciones de Metas
            -- y que rechazan la SC, se deben de gestionar para que se cree la nueva y la meta.

            v_mntmeta(j) := to_number(v_mntmeta(j), '999999999');

            IF TRIM(v_tipmeta(j)) IS NULL and nvl(v_mntmeta(j),0)>0  THEN
              isvalid := FALSE;
              -- adiciona mensajes de sto
              sto_pkg_gener.sto_pr_add_mensa_error(v_secnumdocu(j),
                                                   v_numlote(j),
                                                   'Tipo de Meta en Blanco');
            END IF;

            IF nvl(v_mntmeta(j),0)<=0 and TRIM(v_tipmeta(j)) IS not NULL THEN
              isvalid := FALSE;
              -- adiciona mensajes de sto
              sto_pkg_gener.sto_pr_add_mensa_error(v_secnumdocu(j),
                                                   v_numlote(j),
                                                   'Monto de Meta en Blanco o Cero');
            END IF;

            /*
            IF TRIM(v_desmeta(j)) IS NULL THEN
              isvalid := FALSE;
              -- adiciona mensajes de sto
              sto_pkg_gener.sto_pr_add_mensa_error(v_secnumdocu(j),
                                                   v_numlote(j),
                                                   'Descripcion de Meta en Blanco');
            END IF;*/

            if isvalid then
               update int_solic_conso_credi a
               set a.des_meta=(select b.des_tipo_logr from nvs_tipo_logro b where b.cod_tipo_logr=a.tip_meta)
               where a.sec_nume_docu=v_secnumdocu(j)
               and exists (select 1 from nvs_tipo_logro b where b.cod_tipo_logr=a.tip_meta)
               and a.tip_meta is not null;
            end if;

          ELSE
            -- funciona para los paises que la meta no rechaza la SC, se usa para completar la
            -- informacion de la meta, si tiene error se rechaza la meta y se gestiona en la pantalla de metas.

            -- valida tipo de meta
            IF TRIM(v_tipmeta(j)) IS NOT NULL THEN

              BEGIN
                SELECT a.cod_tipo_logr
                  INTO lntipometa
                  FROM nvs_tipo_logro a
                 WHERE a.cod_tipo_logr = v_tipmeta(j);

                IF lntipometa IS NOT NULL THEN
                  isvalid := TRUE;
                END IF;

              EXCEPTION
                WHEN OTHERS THEN
                  UPDATE int_solic_conso_credi
                     SET tip_meta = '99' -- error, va para gestion.
                   WHERE sec_nume_docu = v_secnumdocu(j)
                     AND num_lote = v_numlote(j);
              END;
            END IF;
            -- Valida monto de la meta
            IF lntipometa IS NOT NULL THEN

              BEGIN
                SELECT a.imp_maxi_logr,
                       a.imp_mini_logr
                  INTO lnmontomaximo,
                       lnmontominimo
                  FROM nvs_tipo_logro a
                 WHERE a.cod_tipo_logr = v_tipmeta(j);

                v_mntmeta(j) := nvl(v_mntmeta(j), 0);

                lnmontomaximo := to_number(lnmontomaximo, '999999999');
                lnmontominimo := to_number(lnmontominimo, '999999999');
                v_mntmeta(j) := to_number(v_mntmeta(j), '999999999');

              EXCEPTION
                WHEN OTHERS THEN
                  lnmontomaximo := 0;
                  lnmontominimo := 0;
              END;

              IF lnmontomaximo <> 0 AND lnmontominimo <> 0 THEN
                IF v_mntmeta(j) > lnmontomaximo THEN
                  v_mntmeta(j) := lnmontomaximo;
                END IF;
                IF v_mntmeta(j) < lnmontominimo THEN
                  v_mntmeta(j) := lnmontominimo;
                END IF;
                -- actualiza el monto de la meta
                UPDATE int_solic_conso_credi
                   SET val_mnto_meta = v_mntmeta(j)
                 WHERE sec_nume_docu = v_secnumdocu(j)
                   AND num_lote = v_numlote(j);

              END IF;

            END IF;
          END IF;
          -- Actualizamos Documentos Validados OK
          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_secnumdocu(j);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_DATOS_OBLIG: ' ||
                              ls_sqlerrm);

  END sto_pr_sim_datos_oblig;

  /**************************************************************************
    Descripcion       : STO_PR_SIM_INDIC_RECHA
                        Procedimiento de Validacion de Rechazo OCR
    Fecha Creacion    : 03/03/2011
    Autor             : Nicolas Lopez
    Observacion       : Esta validacion no va por unificacion de la SC y Metas
                        se toma el indicador general de la SC
  ***************************************************************************/
  PROCEDURE sto_pr_sim_indic_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_stat_proc = '01';

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    j            BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_numlote,
             v_secnumdocu LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_secnumdocu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_secnumdocu(j);
      END IF;
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_INDIC_RECHA: ' ||
                              ls_sqlerrm);

  END sto_pr_sim_indic_recha;

  /**************************************************************************
  Descripcion         : STO_PR_SIM_ENVIO_SICC
                        Envio de SIM a SICC
  Fecha Creacion      : 03/03/2011
  Parametros Entrada  :
  psCodigoPais        : Codigo de pais
  psCodigoTipoDoc     : Codigo de tipo doc
  psCodigoUltimaValid : Codigo de Ultima Validacion
  psUsuario           : Codigo de Usuario
  Autor               : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_sim_envio_sicc
  (
    pscodigopais            VARCHAR2,
    pscodigotipodoccabecera VARCHAR2,
    psusuario               VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
    CURSOR c_enviosim(tmp VARCHAR2) IS
      SELECT cons.cod_pais,
             cons.num_docu,
             cons.cod_clie, -- posicion 7
             cons.num_lote,
             cons.tip_meta, -- posicion 8
             cons.des_meta,
             cons.sec_nume_docu,
             cons.val_mnto_meta, -- posicion 9
             cons.cod_camp_inic, -- posicion 10
             cons.ind_orig
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoccabecera
         AND occ.cod_pais = pscodigopais
            AND
            (
            (
            nvl(cons.val_mnto_meta,0)>0 AND cons.tip_meta IS NOT NULL and tmp='S'
            )
            or
            tmp='N'
            )
            --  No haya sido ya enviada
         AND NOT EXISTS (SELECT 1
                FROM nvs_consu_logro ncl
               WHERE ncl.cod_clie = cons.cod_clie
                 AND ncl.cod_pais = cons.cod_pais);

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_credi.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_tipmeta IS TABLE OF int_solic_conso_credi.tip_meta %TYPE;
    TYPE t_desmeta IS TABLE OF int_solic_conso_credi.des_meta %TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_mntmeta IS TABLE OF int_solic_conso_credi.val_mnto_meta%TYPE;
    TYPE t_codcampinic IS TABLE OF int_solic_conso_credi.cod_camp_inic%TYPE;
    TYPE t_ind_orig IS TABLE OF int_solic_conso_credi.ind_orig%TYPE;

    v_codpais     t_codpais;
    v_num_docu    t_num_docu;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_tipmeta     t_tipmeta;
    v_desmeta     t_desmeta;
    v_secnumdocu  t_secnumdocu;
    v_mntmeta     t_mntmeta;
    v_codcampinic t_codcampinic;
    l_est_logr    VARCHAR2(1);
    v_ind_orig    t_ind_orig;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    ln_id_pais  seg_pais.oid_pais%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;

    isvalid BOOLEAN := TRUE;

    lsperiodoactivo int_solic_conso_credi.cod_camp_inic%TYPE;

    lsmetarechazasc VARCHAR2(15);

  BEGIN
    ln_id_pais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais, TRUE);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca('T', TRUE);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD', TRUE);

    lsmetarechazasc := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'STO_META_RECHAZA_SC'),
                           'N');


    SELECT c.cod_peri
      INTO lsperiodoactivo
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1'
       AND c.cod_pais = pscodigopais;

    /*   sto_pkg_gener.sto_pr_regis_docum_tempo_envio(pscodigopais,
    pscodigotipodoccabecera,
    psnumeroproceso);*/

    OPEN c_enviosim(lsmetarechazasc);
    LOOP
      FETCH c_enviosim BULK COLLECT
        INTO v_codpais,
             v_num_docu,
             v_codclie,
             v_numlote,
             v_tipmeta,
             v_desmeta,
             v_secnumdocu,
             v_mntmeta,
             v_codcampinic,
             v_ind_orig LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          isvalid := FALSE;
          IF v_tipmeta(i) IS NOT NULL OR v_mntmeta(i) IS NOT NULL OR
             v_desmeta(i) IS NOT NULL THEN
            isvalid := TRUE;
          END IF;

          l_est_logr := '1';
          IF v_tipmeta(i) IS NULL OR v_tipmeta(i) = '99' OR
             v_mntmeta(i) IS NULL OR v_mntmeta(i) = 0 OR
             v_desmeta(i) IS NULL THEN
            l_est_logr := '2';
          END IF;

          IF (isvalid) THEN

            INSERT INTO nvs_consu_logro
              (cod_pais,
               cod_clie,
               cod_tipo_logr,
               cod_medi_capt,
               imp_logr,
               cmp_inic,
               cmp_fina,
               ori_regi,
               des_larg,
               usu_modi,
               fec_modi,
               est_regi,
               est_logr)
            VALUES
              (v_codpais(i),
               v_codclie(i),
               nvl(v_tipmeta(i), '99'),
               NULL,
               nvl(to_number(v_mntmeta(i)), 0),
               nvl(v_codcampinic(i), lsperiodoactivo),
               per_pkg_repor_perce.per_fn_obtie_perio(nvl(v_codcampinic(i),
                                                          lsperiodoactivo),
                                                      ln_id_pais,
                                                      ln_id_marca,
                                                      ln_id_canal,
                                                      cantidad_periodos),
               v_ind_orig(i), -- 'O',
               v_desmeta(i),
               psusuario,
               SYSDATE,
               '1',
               l_est_logr);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_enviosim %NOTFOUND;
    END LOOP;

    CLOSE c_enviosim;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SIM_ENVIO_SICC: ' || ls_sqlerrm);
  END sto_pr_sim_envio_sicc;

  /**************************************************************************
  Descripcion       : Validacion de similitud de direcciones
  Fecha Creacion    : 03/05/2013
  Autor             : Aurelio Oviedo
  **************************************************************************/
  PROCEDURE sto_pr_scc_simil_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    lnporcsimilitud NUMBER;

    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_similituddirec(psporcsimilitud NUMBER) IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS (SELECT 1
                FROM mae_clien_direc x,
                     mae_clien_bloqu y
               WHERE x.clie_oid_clie = y.clie_oid_clie
                 AND y.fec_desb IS NULL
                 AND y.tibq_oid_tipo_bloq = 2
                 AND utl_match.edit_distance_similarity(x.val_nomb_via || '' ||
                                                        x.num_ppal || '' ||
                                                        x.val_obse,
                                                        cons.val_nomb_vicl || '' ||
                                                        cons.num_dire_clie || '' ||
                                                        cons.val_dire_clie) >
                     psporcsimilitud
                 AND substr(x.cod_unid_geog,1,12) = CONS.COD_DEPA_CLIE || CONS.COD_PROV_CLIE
                 );

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    SELECT to_number(val_para)
      INTO lnporcsimilitud
      FROM bas_param_pais
     WHERE cod_sist = 'STO'
       AND cod_pais = pscodigopais
       AND nom_para = 'porcSimilitudDirec';

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_similituddirec(lnporcsimilitud);
    LOOP
      FETCH c_similituddirec BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_similituddirec%NOTFOUND;
    END LOOP;
    CLOSE c_similituddirec;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_SIMIL_DIREC: ' || ls_sqlerrm);

  END sto_pr_scc_simil_direc;

  /**************************************************************************
  Descripcion           : Validacion de Datos Obligatorios
  Fecha Creacion    : 04/08/2013
  Autor                    : Sebastian Guerra
  **************************************************************************/
  PROCEDURE sto_pr_scc_datos_oblig
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_datosobligatorios IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   scc
       WHERE scc.sec_nume_docu = cons.sec_nume_docu
         AND scc.num_lote = cons.num_lote
         AND scc.cod_tipo_docu = pscodigotipodoc
         AND scc.cod_pais = pscodigopais
         AND (cons.val_nom1 IS NOT NULL AND cons.val_ape1 IS NOT NULL)
         AND (cons.val_celu_clie IS NOT NULL OR
             cons.val_telf_clie IS NOT NULL)
         AND cons.fec_naci IS NOT NULL
         AND cons.tip_docu IS NOT NULL
         AND cons.num_docu_iden IS NOT NULL
         AND cons.uni_admi IS NOT NULL
         AND cons.val_dire_clie IS NOT NULL
         AND (cons.val_barr_clie IS NOT NULL AND
             cons.val_ciud_clie IS NOT NULL AND
             cons.val_depa_clie IS NOT NULL);

    TYPE t_cod_pais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais     t_cod_pais;
    v_codperi     t_cod_peri;
    v_codclie     t_cod_clie;
    v_numlote     t_num_lote;
    v_secnumedocu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_datosobligatorios;
    LOOP
      FETCH c_datosobligatorios BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_secnumedocu LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          UPDATE sto_docum_digit scc
             SET -- Actualziamos Indicadores de Validacion
                 scc.cod_ulti_vali_ejec = pscodigovalidactual,
                 scc.cod_ulti_vali_exit = pscodigovalidactual,
                 scc.usu_modi           = psusuario,
                 scc.fec_modi           = SYSDATE
           WHERE scc.cod_pais = pscodigopais
             AND scc.cod_tipo_docu = pscodigotipodoc
             AND scc.num_lote = v_numlote(j)
             AND scc.sec_nume_docu = v_secnumedocu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_datosobligatorios%NOTFOUND;
    END LOOP;
    CLOSE c_datosobligatorios;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DATOS_OBLIG: ' || ls_sqlerrm);

  END sto_pr_scc_datos_oblig;

  /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  PROCEDURE sto_pr_scc_clien_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_clienincob(psmontomaxincobrable NUMBER) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS (SELECT 1
                            FROM ccc_consu_casti_cabec inco
                           WHERE cons.num_docu_iden = inco.num_docu_iden
                             AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE cons.num_docu_iden = inco.num_docu_iden
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable))
      /*UNION
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS (SELECT 1
                            FROM ccc_consu_casti_cabec inco
                           WHERE cons.cod_clie = inco.cod_clie
                             AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE cons.cod_clie = inco.cod_clie
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable))*/;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j                    BINARY_INTEGER := 0;
    lsmontomaxincobrable NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el parametro del monto maximo permitido para aprobar el incobrable
    lsmontomaxincobrable := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_MTO_MAX_INCO'),
                                0);

    OPEN c_clienincob(lsmontomaxincobrable);
    LOOP
      FETCH c_clienincob BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP

          UPDATE sto_docum_digit occ
             SET -- Actualizamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_clienincob%NOTFOUND;
    END LOOP;
    CLOSE c_clienincob;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_CLIEN_INCOB: ' || ls_sqlerrm);

  END sto_pr_scc_clien_incob;

  /**************************************************************************
  Descripcion       : Validacion de referida en incobrable
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  PROCEDURE sto_pr_scc_refer_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_referincob(psmontomaxincobrable NUMBER) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS (SELECT 1
                            FROM ccc_consu_casti_cabec inco
                           WHERE cons.cod_clie_rete = inco.num_docu_iden
                             AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE cons.cod_clie_rete = inco.num_docu_iden
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable))
      UNION
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS (SELECT 1
                            FROM ccc_consu_casti_cabec inco
                           WHERE cons.cod_clie_rete = inco.cod_clie
                             AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE cons.cod_clie_rete = inco.cod_clie
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable));

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j                    BINARY_INTEGER := 0;
    lsmontomaxincobrable NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Obtenemos el parametro del monto maximo permitido para aprobar el incobrable
    lsmontomaxincobrable := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_MTO_MAX_INCO'),
                                0);

    OPEN c_referincob(lsmontomaxincobrable);
    LOOP
      FETCH c_referincob BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP

          UPDATE sto_docum_digit occ
             SET -- Actualizamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_referincob%NOTFOUND;
    END LOOP;
    CLOSE c_referincob;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_REFER_INCOB: ' || ls_sqlerrm);

  END sto_pr_scc_refer_incob;

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_bloqueocliente IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.num_docu_iden
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    CURSOR c_motivosbloqueo
    (
      ln_oidclie     NUMBER,
      ln_indfacrefac VARCHAR2
    ) IS
      SELECT a.tibq_oid_tipo_bloq,
             bloq_desc.val_i18n
        FROM mae_clien_bloqu a,
             mae_accio_proce_bloqu b,
             mae_accio_bloqu c,
             mae_proce_bloqu d,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_BLOQU') bloq_desc
       WHERE a.fec_desb IS NULL
         AND a.clie_oid_clie = ln_oidclie
         AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
         AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
         AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
         AND d.cod_proc_bloq = ln_indfacrefac
         AND c.cod_acci_bloq = 'FN'
         AND a.tibq_oid_tipo_bloq = bloq_desc.val_oid;

    TYPE motbloqdiarectab IS TABLE OF c_motivosbloqueo%ROWTYPE INDEX BY BINARY_INTEGER;
    motbloqrecord motbloqdiarectab;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_numdocuiden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_numdocuiden   t_numdocuiden;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    contador NUMBER := 0;
    numero   NUMBER := 0;

    verifica BOOLEAN;
    loidclie NUMBER(12);

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_numdocuiden LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          verifica := TRUE;

          BEGIN

            SELECT iden.clie_oid_clie
              INTO loidclie
              FROM mae_clien_ident iden
             WHERE iden.num_docu_iden = v_numdocuiden(j)
               AND iden.val_iden_docu_prin = 1;

          EXCEPTION
            WHEN OTHERS THEN
              loidclie := 0;
          END;

          SELECT COUNT(*)
            INTO contador
            FROM mae_clien_bloqu
           WHERE fec_desb IS NULL
             AND clie_oid_clie = loidclie;

          IF contador > 0 AND loidclie <> 0 THEN

            SELECT COUNT(*)
              INTO numero
              FROM mae_clien_bloqu       a,
                   mae_accio_proce_bloqu b,
                   mae_accio_bloqu       c,
                   mae_proce_bloqu       d
             WHERE a.fec_desb IS NULL
               AND a.clie_oid_clie = loidclie
               AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
               AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
               AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
               AND d.cod_proc_bloq = 'FA'
               AND c.cod_acci_bloq = 'FS';

            IF (numero <> contador) THEN
              verifica := FALSE;

              OPEN c_motivosbloqueo(loidclie, 'FA');
              LOOP
                FETCH c_motivosbloqueo BULK COLLECT
                  INTO motbloqrecord LIMIT w_filas;
                IF motbloqrecord.count > 0 THEN
                  FOR i IN motbloqrecord.first .. motbloqrecord.last
                  LOOP
                    -- adiciona mensajes de sto
                    sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                         v_numlote(j),
                                                         'Motivo : ' || motbloqrecord(i)
                                                         .val_i18n);
                  END LOOP;
                END IF;
                EXIT WHEN c_motivosbloqueo%NOTFOUND;
              END LOOP;
              CLOSE c_motivosbloqueo;
            END IF;
          END IF;

          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_bloqueocliente%NOTFOUND;

    END LOOP;
    CLOSE c_bloqueocliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCC_BLOQU_CLIEN: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_bloqu_clien;

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de referida
  Fecha Creacion    : 07/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_bloqu_refer
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_bloqueocliente IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_clie_rete
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    CURSOR c_motivosbloqueo
    (
      ln_oidclie     NUMBER,
      ln_indfacrefac VARCHAR2
    ) IS
      SELECT a.tibq_oid_tipo_bloq,
             bloq_desc.val_i18n
        FROM mae_clien_bloqu a,
             mae_accio_proce_bloqu b,
             mae_accio_bloqu c,
             mae_proce_bloqu d,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_BLOQU') bloq_desc
       WHERE a.fec_desb IS NULL
         AND a.clie_oid_clie = ln_oidclie
         AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
         AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
         AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
         AND d.cod_proc_bloq = ln_indfacrefac
         AND c.cod_acci_bloq = 'FN'
         AND a.tibq_oid_tipo_bloq = bloq_desc.val_oid;

    TYPE motbloqdiarectab IS TABLE OF c_motivosbloqueo%ROWTYPE INDEX BY BINARY_INTEGER;
    motbloqrecord motbloqdiarectab;

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    TYPE t_codclierete IS TABLE OF int_solic_conso_credi.cod_clie_rete%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_codclierete   t_codclierete;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    contador NUMBER := 0;
    numero   NUMBER := 0;

    verifica BOOLEAN;
    loidclie NUMBER(12);

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_codclierete LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          verifica := TRUE;

          BEGIN

            SELECT mae.oid_clie
              INTO loidclie
              FROM mae_clien mae
             WHERE mae.cod_clie = v_codclierete(j);

          EXCEPTION
            WHEN OTHERS THEN
              loidclie := 0;
          END;

          SELECT COUNT(*)
            INTO contador
            FROM mae_clien_bloqu
           WHERE fec_desb IS NULL
             AND clie_oid_clie = loidclie;

          IF contador > 0 AND loidclie <> 0 THEN

            SELECT COUNT(*)
              INTO numero
              FROM mae_clien_bloqu       a,
                   mae_accio_proce_bloqu b,
                   mae_accio_bloqu       c,
                   mae_proce_bloqu       d
             WHERE a.fec_desb IS NULL
               AND a.clie_oid_clie = loidclie
               AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
               AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
               AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
               AND d.cod_proc_bloq = 'FA'
               AND c.cod_acci_bloq = 'FS';

            IF (numero <> contador) THEN
              verifica := FALSE;

              OPEN c_motivosbloqueo(loidclie, 'FA');
              LOOP
                FETCH c_motivosbloqueo BULK COLLECT
                  INTO motbloqrecord LIMIT w_filas;
                IF motbloqrecord.count > 0 THEN
                  FOR i IN motbloqrecord.first .. motbloqrecord.last
                  LOOP
                    -- adiciona mensajes de sto
                    sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                         v_numlote(j),
                                                         'Motivo : ' || motbloqrecord(i)
                                                         .val_i18n);
                  END LOOP;
                END IF;
                EXIT WHEN c_motivosbloqueo%NOTFOUND;
              END LOOP;
              CLOSE c_motivosbloqueo;
            END IF;
          END IF;

          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_bloqueocliente%NOTFOUND;

    END LOOP;
    CLOSE c_bloqueocliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR STO_PR_OCC_BLOQU_REFER: ' ||
                              ls_sqlerrm);

  END sto_pr_scc_bloqu_refer;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_NUMER_RUC_MOD11
                      Procedimiento de documento de identidad con modulo 11
  Fecha Creacion    : 14/08/2013
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_scc_numer_ruc_mod11
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validapais IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.num_ruc,
             mtd.val_sigl tip_docu,
             cons.num_docu_iden
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_tipo_docum mtd
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         and mtd.cod_tipo_docu(+)=cons.tip_docu
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_credi.num_ruc%TYPE;
    TYPE t_tip_docu IS TABLE OF mae_tipo_docum.val_sigl%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;

    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    v_num_ruc       t_num_ruc;
    v_tip_docu       t_tip_docu;
    v_num_docu_iden       t_num_docu_iden;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    lbsolicitudvalida BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validapais;
    LOOP
      FETCH c_validapais BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_num_ruc,
             v_tip_docu,
             v_num_docu_iden LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          lbsolicitudvalida := TRUE;
          IF v_num_ruc(i) IS NOT NULL AND
             mae_pkg_proce_clien.mae_fn_valid_numer_ruc_mod11(v_num_ruc(i)) = 0 THEN
            lbsolicitudvalida := FALSE;
          END IF;
          IF v_tip_docu(i) like 'RUC%' and v_num_docu_iden(i) IS NOT NULL AND
             mae_pkg_proce_clien.mae_fn_valid_numer_ruc_mod11(v_num_docu_iden(i)) = 0 THEN
            lbsolicitudvalida := FALSE;
          END IF;

          IF lbsolicitudvalida THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_validapais%NOTFOUND;
    END LOOP;
    CLOSE c_validapais;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_NUMER_RUC_MOD11: ' || ls_sqlerrm);

  END sto_pr_scc_numer_ruc_mod11;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_DOCU_RECHA_OK
                    Procedimiento de Validacion de CODIGO de cliente SCC
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 19/09/2008


  Autor             : Cristhian Roman
  **************************************************************************/
  PROCEDURE sto_pr_scc_docu_recha_gis
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validadocumento IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,

             cons.sec_nume_docu,
             cons.ind_stat_proc,
             cons.ind_moti_rech
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
            -- Para que considere los rechazos por georeferenciador
         AND ((cons.ind_stat_proc IN ('03') AND
             cons.ind_moti_rech NOT IN
             ('N', 'A', 'C', 'E', 'G', 'H', 'X', 'Z', 'Y')) OR
             (nvl(cons.ind_stat_proc, '01') != '03'));

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_ind_stat_proc IS TABLE OF int_solic_conso_credi.ind_stat_proc%TYPE;
    TYPE t_ind_moti_rech IS TABLE OF int_solic_conso_credi.ind_moti_rech%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_ind_stat_proc t_ind_stat_proc;
    v_ind_moti_rech t_ind_moti_rech;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validadocumento;
    LOOP
      FETCH c_validadocumento BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_ind_stat_proc,
             v_ind_moti_rech LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_validadocumento%NOTFOUND;
    END LOOP;
    CLOSE c_validadocumento;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_DOCU_RECHA_GIS: ' || ls_sqlerrm);

  END sto_pr_scc_docu_recha_gis;

  /**************************************************************************
  Descripcion       : STO_PR_SCC_FORM_CEDU
                      Procedimiento de Validacion de cedula que cumpla
                      con el formato de 2 guiones
  Fecha Creacion    : 04/10/2013
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  **************************************************************************/
  PROCEDURE sto_pr_scc_form_cedu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validadocumento IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
            -- Para que considere las cedulas que cumplan con la condicion de tener 2 guiones
         AND (length(cons.num_docu_iden) -
             length(REPLACE(cons.num_docu_iden, '-', ''))) = 2;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validadocumento;
    LOOP
      FETCH c_validadocumento BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_validadocumento%NOTFOUND;
    END LOOP;
    CLOSE c_validadocumento;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_FORM_CEDU: ' || ls_sqlerrm);

  END sto_pr_scc_form_cedu;
  /**************************************************************************
  Descripcion       : Validacion Lider Reconocimiento
  Fecha Creacion    : 15/10/2013
  **************************************************************************/
  PROCEDURE sto_pr_scc_lider_recom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validadocumento IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_lide_reco
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF sto_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
    TYPE t_cod_lide_reco IS TABLE OF int_solic_conso_credi.cod_lide_reco%TYPE;

    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_lide_reco t_cod_lide_reco;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    i BINARY_INTEGER := 0;

    isvalid BOOLEAN := TRUE;

    lnoidclielider NUMBER(12);
    lncodclielider VARCHAR2(15);
    lnoidzonalider NUMBER(12);
    lnoidzonaconsu NUMBER(12);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validadocumento;
    LOOP
      FETCH c_validadocumento BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_cod_lide_reco LIMIT rows;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          isvalid := TRUE;

          /*BEGIN

            SELECT zona.oid_zona
              INTO lnoidzonaconsu
              FROM zon_terri_admin       zon_admin,
                   zon_secci             zona_secc,
                   zon_zona              zona,
                   int_solic_conso_credi cons
             WHERE cons.sec_nume_docu = v_sec_nume_docu(i)
               AND zon_admin.zscc_oid_secc = zona_secc.oid_secc
               AND zona_secc.zzon_oid_zona = zona.oid_zona
               AND zon_admin.oid_terr_admi = cons.oid_terr_admi
               AND zona.ind_acti = 1
               AND zona.ind_borr = 0;

          EXCEPTION
            WHEN OTHERS THEN
              isvalid := FALSE;
              -- adiciona mensajes de sto
              sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                   v_num_lote(i),
                                                   'Codigo de zona de la consultora con error o no esta activo');
          END;*/

          IF v_cod_lide_reco(i) IS NULL THEN
            isvalid := FALSE;
            -- adiciona mensajes de sto
            sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                 v_num_lote(i),
                                                 'Codigo de Lider en blanco.');
          END IF;

          IF (isvalid) THEN

            BEGIN

              SELECT m.oid_clie--,
                     --a.zzon_oid_zona
                INTO lnoidclielider--,
                     --lnoidzonalider
                FROM --zon_secci a,
                     mae_clien m
               WHERE v_cod_lide_reco(i) = m.cod_clie
                 --AND a.clie_oid_clie = m.oid_clie
                 --AND a.ind_acti = 1
                 --AND a.ind_borr = 0
                 ;

              /*IF lnoidzonalider <> lnoidzonaconsu THEN
                isvalid := FALSE;
                -- adiciona mensajes de sto
                sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                     v_num_lote(i),
                                                     'Codigo de Zona de la Lider es diferente al de la Consultora.');
              END IF;*/

            EXCEPTION
              WHEN OTHERS THEN

              begin
              SELECT m.cod_clie--,
                     --a.zzon_oid_zona
                INTO lncodclielider--,
                     --lnoidzonalider
                FROM mae_clien_ident a,
                     mae_clien m
               WHERE ltrim(v_cod_lide_reco(i),'0') = ltrim(a.num_docu_iden,'0')
                 AND a.clie_oid_clie = m.oid_clie
                 --AND a.ind_acti = 1
                 --AND a.ind_borr = 0
                 ;

                 update int_solic_conso_credi a set a.cod_lide_reco=lncodclielider
                 where sec_nume_docu=v_sec_nume_docu(i);

                exception when others then
                isvalid := FALSE;
                -- adiciona mensajes de sto
                sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                     v_num_lote(i),
                                                     'Codigo de la Lider no existe o no esta activo');
                end;
            END;

          END IF;

          IF (isvalid) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_validadocumento%NOTFOUND;
    END LOOP;
    CLOSE c_validadocumento;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_LIDER_RECOM: ' || ls_sqlerrm);

  END sto_pr_scc_lider_recom;
  
 /**************************************************************************
  Descripcion       : STO_PR_SCC_NOMB_APEL
                      Procedimiento de Validacion de primer nombre, primer apellido
  Fecha Creacion    : 26/01/2015
  --------------------------------------------------------------------------
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_nomb_apel
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS

    CURSOR c_nombreApellido IS
      SELECT cons.cod_pais,
             cons.cod_clie,
             cons.num_lote,
             cons.val_ape1,
             cons.val_nom1,
             cons.fec_naci,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_valape1 IS TABLE OF int_solic_conso_credi.val_ape1%TYPE;
    TYPE t_valnom1 IS TABLE OF int_solic_conso_credi.val_nom1%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codclie t_codclie;
    v_numlote t_numlote;
    v_valape1 t_valape1;
    v_valnom1 t_valnom1;
    v_fecnaci t_fecnaci;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;
    contador NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_nombreApellido;
    LOOP
      FETCH c_nombreApellido BULK COLLECT
        INTO v_codpais,
             v_codclie,
             v_numlote,
             v_valape1,
             v_valnom1,
             v_fecnaci,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN
       
         FOR j IN v_codpais.first .. v_codpais.last
        LOOP
             --REALIZAMOS LA VALIDACION SOLICITADA  
              select count(*)
                into contador
                from mae_clien c, mae_clien_datos_adici a
               where c.oid_clie = a.clie_oid_clie
                 and trunc(a.fec_naci) = trunc(v_fecnaci(j))
                 and trim(c.val_ape1) = trim(v_valape1(j))
                 and trim(c.val_nom1) = trim(v_valnom1(j));
            
             if contador = 0 then
              UPDATE sto_docum_digit occ
                 SET -- Actualizamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
             end if;
        END LOOP;
      END IF;
      EXIT WHEN c_nombreApellido%NOTFOUND;
    END LOOP;
    CLOSE c_nombreApellido;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_nomb_apel: ' || ls_sqlerrm);

  END sto_pr_scc_nomb_apel;
  
 /**************************************************************************
  Descripcion       : STO_PR_SCC_CEDU_REFE_NUEV
                      Procedimiento de Validacion de cedula de referida y nueva cliente
  Fecha Creacion    : 30/03/2015
  --------------------------------------------------------------------------
  Autor             : Aurelio Oviedo
  **************************************************************************/
  
  PROCEDURE STO_PR_SCC_CEDU_REFE_NUEV
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
         
    CURSOR c_cedulaReferidaNueva IS
      SELECT cons.cod_pais,
             cons.cod_clie,
             cons.num_lote,
             cons.val_ape1,
             cons.val_nom1,
             cons.fec_naci,
             cons.sec_nume_docu,
             cons.cod_clie_rete,
             cons.num_docu_iden
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_valape1 IS TABLE OF int_solic_conso_credi.val_ape1%TYPE;
    TYPE t_valnom1 IS TABLE OF int_solic_conso_credi.val_nom1%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_clie_rete IS TABLE OF int_solic_conso_credi.cod_clie_rete%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;

    v_codpais t_codpais;
    v_codclie t_codclie;
    v_numlote t_numlote;
    v_valape1 t_valape1;
    v_valnom1 t_valnom1;
    v_fecnaci t_fecnaci;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_clie_rete t_cod_clie_rete;
    v_num_docu_iden t_num_docu_iden;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;
    contador NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cedulaReferidaNueva;
    LOOP
      FETCH c_cedulaReferidaNueva BULK COLLECT
        INTO v_codpais,
             v_codclie,
             v_numlote,
             v_valape1,
             v_valnom1,
             v_fecnaci,
             v_sec_nume_docu,
             v_cod_clie_rete,
             v_num_docu_iden

             LIMIT rows;

      IF v_codpais.count > 0 THEN
       
         FOR j IN v_codpais.first .. v_codpais.last
        LOOP
             --REALIZAMOS LA VALIDACION SOLICITADA  
              select count(1)
                into contador
                from int_solic_conso_credi iscc
               where iscc.cod_clie = v_codclie(j)
                 and iscc.cod_clie_rete = iscc.num_docu_iden
                 and iscc.cod_pais = v_codpais(j);
            
             if contador = 0 then
              UPDATE sto_docum_digit occ
                 SET -- Actualizamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
             end if;
        END LOOP;
      END IF;
      EXIT WHEN c_cedulaReferidaNueva%NOTFOUND;
    END LOOP;
    CLOSE c_cedulaReferidaNueva;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_CEDU_REFE_NUEV: ' || ls_sqlerrm);

  END STO_PR_SCC_CEDU_REFE_NUEV;
  
/**************************************************************************
  Descripcion       : sto_pr_scc_cedu_iden
                      Procedimiento de Validacion de documento de identidad
  Fecha Creacion    : 27/05/2015
  --------------------------------------------------------------------------
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_cedu_iden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS

    CURSOR c_cedulaIdentidad IS
      SELECT cons.cod_pais,
             cons.cod_clie,
             cons.num_lote,
             cons.val_ape1,
             cons.val_nom1,
             cons.fec_naci,
             cons.tip_docu,
             cons.num_docu_iden,
             cons.sec_nume_docu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_valape1 IS TABLE OF int_solic_conso_credi.val_ape1%TYPE;
    TYPE t_valnom1 IS TABLE OF int_solic_conso_credi.val_nom1%TYPE;
    TYPE t_fecnaci IS TABLE OF int_solic_conso_credi.fec_naci%TYPE;
    TYPE t_tipdocu IS TABLE OF int_solic_conso_credi.tip_docu%TYPE;
    TYPE t_numdocuiden IS TABLE OF int_solic_conso_credi.num_docu_iden%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codclie t_codclie;
    v_numlote t_numlote;
    v_valape1 t_valape1;
    v_valnom1 t_valnom1;
    v_fecnaci t_fecnaci;
    v_tipdocu t_tipdocu;
    v_numdocuiden t_numdocuiden;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;
    isvalid BOOLEAN := TRUE;
    valida varchar2(200):='';

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cedulaIdentidad;
    LOOP
      FETCH c_cedulaIdentidad BULK COLLECT
        INTO v_codpais,
             v_codclie,
             v_numlote,
             v_valape1,
             v_valnom1,
             v_fecnaci,
             v_tipdocu,
             v_numdocuiden,
             v_sec_nume_docu

             LIMIT rows;

      IF v_codpais.count > 0 THEN
       
         FOR j IN v_codpais.first .. v_codpais.last
        LOOP
                   
          
             isvalid := TRUE;
             
             IF ( v_tipdocu(j) = '06' ) THEN
                 update int_solic_conso_actua_datos 
                        set tip_docu =  (select cod_tipo_docu from mae_tipo_docum where val_sigl='NIT')
                 where  sec_nume_docu = v_sec_nume_docu(j); 
             END IF;
             
             --REALIZAMOS LA VALIDACION SOLICITADA
             if v_tipdocu(j) ='01' then
               select mae_pkg_proce_clien.MAE_FN_VALID_CARNE_IDENT(v_numdocuiden(j))
                 into valida
                 from dual;
             end if;
            
              if  trim(valida) is not null then
                 isvalid:= FALSE;
             end if;
             
             IF (isvalid) THEN
               
              UPDATE sto_docum_digit occ
                 SET -- Actualizamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(j)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
             end if;
        END LOOP;
      END IF;
      EXIT WHEN c_cedulaIdentidad%NOTFOUND;
    END LOOP;
    CLOSE c_cedulaIdentidad;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_cedu_iden: ' || ls_sqlerrm);

  END sto_pr_scc_cedu_iden; 
   
/**************************************************************************
  Descripcion       : sto_pr_scc_nacio
                      Procedimiento de Validacion de nacionalidad
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_nacio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaNacio IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             nac.oid_naci
        FROM seg_nacio              nac,
             int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             gen_i18n_sicc_comun   gen
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND gen.attr_enti = 'SEG_NACIO'
         AND gen.val_oid = nac.oid_naci
         AND upper(substr(gen.val_i18n,1,4)) = upper(substr(cons.cod_naci,1,4));

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_oid_naci IS TABLE OF int_solic_conso_credi.oid_naci%TYPE;

    v_codpais t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_naci      t_oid_naci;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaNacio;
    LOOP
      FETCH c_validaNacio BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_oid_naci LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_credi
             SET oid_naci = v_oid_naci(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_validaNacio%NOTFOUND;
    END LOOP;
    CLOSE c_validaNacio;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_nacio: ' || ls_sqlerrm);

  END sto_pr_scc_nacio;   
  
/**************************************************************************
  Descripcion       : sto_pr_scc_esta_civil
                      Procedimiento de Validacion de estado civil
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
    
 PROCEDURE sto_pr_scc_esta_civil
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaEstaCivi IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_esta_civi
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_ind_esta_civi IS TABLE OF int_solic_conso_credi.ind_esta_civi%TYPE;

    v_codpais t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_esta_civi t_ind_esta_civi;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaEstaCivi;
    LOOP
      FETCH c_validaEstaCivi BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_ind_esta_civi LIMIT rows;

      IF v_codpais.count > 0 THEN
        
      FOR j IN v_codpais.first .. v_codpais.last
        LOOP
         
         existe := TRUE;

          IF ((v_ind_esta_civi(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
          IF (existe) THEN

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_validaEstaCivi%NOTFOUND;
    END LOOP;
    CLOSE c_validaEstaCivi;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_esta_civil: ' || ls_sqlerrm);

  END sto_pr_scc_esta_civil;
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_sexo
                      Procedimiento de Validacion de sexo
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
    
 PROCEDURE sto_pr_scc_sexo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaSexo IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_sexo
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_sexo IS TABLE OF int_solic_conso_credi.cod_sexo%TYPE;

    v_codpais t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_sexo      t_cod_sexo;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaSexo;
    LOOP
      FETCH c_validaSexo BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_cod_sexo LIMIT rows;

      IF v_codpais.count > 0 THEN
         
         existe := TRUE;
       FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          IF ((v_cod_sexo(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
          IF (existe) THEN

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
       END LOOP;
      END IF;
      EXIT WHEN c_validaSexo%NOTFOUND;
    END LOOP;
    CLOSE c_validaSexo;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_sexo: ' || ls_sqlerrm);

  END sto_pr_scc_sexo;
  
/**************************************************************************
  Descripcion       : sto_pr_scc_tipo_perso
                      Procedimiento de Validación Tipo de Persona
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_tipo_perso
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaTipoPers IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_tipo_pers,
             mtp.oid_tipo_pers
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_tipo_perso        mtp
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND mtp.cod_tipo_pers = cons.cod_tipo_pers;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_tipo_pers IS TABLE OF int_solic_conso_credi.cod_tipo_pers%TYPE;
    TYPE t_oid_tipo_pers IS TABLE OF mae_tipo_perso.oid_tipo_pers%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_tipo_pers t_cod_tipo_pers;
    v_oid_tipo_pers t_oid_tipo_pers;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaTipoPers;
    LOOP
      FETCH c_validaTipoPers BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_cod_tipo_pers,
             v_oid_tipo_pers LIMIT rows;

      IF v_codpais.count > 0 THEN

         existe := TRUE;
       FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          
          UPDATE int_solic_conso_credi
             SET oid_tipo_pers = v_oid_tipo_pers(j)
           WHERE sec_nume_docu = v_sec_nume_docu(j)
             AND num_lote = v_numlote(j);

          IF ((v_cod_tipo_pers(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
          IF (existe) THEN

              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
       END LOOP;

      END IF;
      EXIT WHEN c_validaTipoPers%NOTFOUND;
    END LOOP;
    CLOSE c_validaTipoPers;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_tipo_perso: ' || ls_sqlerrm);

  END sto_pr_scc_tipo_perso;   
  
/**************************************************************************
  Descripcion       : sto_pr_scc_orige_ingre
                      Procedimiento de Validación de origen de ingreso
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_scc_orige_ingre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaOriIngre IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_orig_ingr,
             moi.oid_orig_ingr
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ,
             mae_orig_ingre        moi
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND moi.cod_orig_ingr = cons.cod_orig_ingr;         

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_orig_ingr IS TABLE OF int_solic_conso_credi.cod_orig_ingr%TYPE;
    TYPE t_oid_orig_ingr IS TABLE OF mae_orig_ingre.oid_orig_ingr%TYPE;

    v_codpais       t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_orig_ingr      t_cod_orig_ingr;
    v_oid_orig_ingr t_oid_orig_ingr;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaOriIngre;
    LOOP
      FETCH c_validaOriIngre BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_cod_orig_ingr,
             v_oid_orig_ingr  LIMIT rows;

      IF v_codpais.count > 0 THEN

       FOR j IN v_codpais.first .. v_codpais.last
        LOOP

         UPDATE int_solic_conso_credi
             SET oid_orig_ingr = v_oid_orig_ingr(j)
           WHERE sec_nume_docu = v_sec_nume_docu(j)
             AND num_lote = v_numlote(j);

          IF ((v_cod_orig_ingr(j) IS NULL)) THEN
             existe := FALSE;
          END IF;

          IF (existe) THEN

          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
       END LOOP;

      END IF;
      EXIT WHEN c_validaOriIngre%NOTFOUND;
    END LOOP;
    CLOSE c_validaOriIngre;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_orige_ingre: ' || ls_sqlerrm);

  END sto_pr_scc_orige_ingre; 
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_terri_corre
                      Procedimiento de Validacion codigo territorial corresponde
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
PROCEDURE sto_pr_scc_terri_corre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaCodTerriCorr IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_terr_corr
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_cod_terr_corr IS TABLE OF int_solic_conso_credi.cod_terr_corr%TYPE;

    v_codpais t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_terr_corr t_cod_terr_corr;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaCodTerriCorr;
    LOOP
      FETCH c_validaCodTerriCorr BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_cod_terr_corr LIMIT rows;

      IF v_codpais.count > 0 THEN
       
       FOR j IN v_codpais.first .. v_codpais.last
        LOOP
         
         existe := TRUE;

          IF ((v_cod_terr_corr(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
          IF (existe) THEN
            
              UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_validaCodTerriCorr%NOTFOUND;
    END LOOP;
    CLOSE c_validaCodTerriCorr;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_terri_corre: ' || ls_sqlerrm);

  END sto_pr_scc_terri_corre;  
  
  /**************************************************************************
  Descripcion       : sto_pr_scc_ddom_dent
                      Procedimiento de Validacion indicador direccion de domicilio 
                      igual direccion de entrega
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
PROCEDURE sto_pr_scc_ddom_dent
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaDirDOmDirEnt IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_dird_dire,
             cons.ent_manz,             
             cons.ent_etap,             
             cons.ent_call_prin,        
             cons.ent_num,              
             cons.ent_call_secu,        
             cons.ent_refe,             
             cons.val_barr_entre_clie  
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais                IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote                 IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu          IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_ind_dird_dire          IS TABLE OF int_solic_conso_credi.ind_dird_dire%TYPE;
    TYPE t_ent_manz               IS TABLE OF int_solic_conso_credi.ent_manz%TYPE;           
    TYPE t_ent_etap               IS TABLE OF int_solic_conso_credi.ent_etap%TYPE;           
    TYPE t_ent_call_prin          IS TABLE OF int_solic_conso_credi.ent_call_prin%TYPE;      
    TYPE t_ent_num                IS TABLE OF int_solic_conso_credi.ent_num%TYPE;            
    TYPE t_ent_call_secu          IS TABLE OF int_solic_conso_credi.ent_call_secu%TYPE;      
    TYPE t_ent_refe               IS TABLE OF int_solic_conso_credi.ent_refe%TYPE;           
    TYPE t_val_barr_entre_clie    IS TABLE OF int_solic_conso_credi.val_barr_entre_clie%TYPE;    

    v_codpais                  t_codpais;
    v_numlote                  t_numlote;
    v_sec_nume_docu            t_sec_nume_docu;
    v_ind_dird_dire            t_ind_dird_dire;
    v_ent_manz						 		 t_ent_manz;            
    v_ent_etap                 t_ent_etap;           
    v_ent_call_prin            t_ent_call_prin;      
    v_ent_num                  t_ent_num;            
    v_ent_call_secu            t_ent_call_secu;      
    v_ent_refe                 t_ent_refe;           
    v_val_barr_entre_clie      t_val_barr_entre_clie;


    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaDirDOmDirEnt;
    LOOP
      FETCH c_validaDirDOmDirEnt BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_ind_dird_dire,
             v_ent_manz,						
             v_ent_etap,           
             v_ent_call_prin,      
             v_ent_num,            
             v_ent_call_secu,      
             v_ent_refe,           
             v_val_barr_entre_clie  LIMIT rows;

      IF v_codpais.count > 0 THEN
        
      FOR j IN v_codpais.first .. v_codpais.last
        LOOP  
         existe := TRUE;

          IF ((v_ind_dird_dire(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
        
          IF (existe) THEN
                 
             IF ( (v_ind_dird_dire(j)= 'S')  and 
                  ( v_ent_manz(j)||v_ent_etap(j)||v_ent_call_prin(j)||v_ent_num(j)||v_ent_call_secu(j)||v_ent_refe(j)||v_val_barr_entre_clie(j) IS  NULL) ) THEN

                UPDATE int_solic_conso_credi cons
                   SET cons.ent_manz = cons.dom_manz,
                       cons.ent_etap = cons.dom_etap,
                       cons.ent_call_prin = cons.dom_call_prin,
                       cons.ent_call_secu = cons.dom_call_secu,
                       cons.ent_num = cons.dom_num,
                       cons.ent_refe = cons.dom_refe,
                       cons.val_barr_entre_clie = cons.val_barr_clie
                 WHERE cons.num_lote = v_numlote(j)
                   AND cons.sec_nume_docu = v_sec_nume_docu(j);
             END IF;
                      
             UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_validaDirDOmDirEnt%NOTFOUND;
    END LOOP;
    CLOSE c_validaDirDOmDirEnt;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_ddom_dent: ' || ls_sqlerrm);

  END sto_pr_scc_ddom_dent;  
  
    /**************************************************************************
  Descripcion       : sto_pr_scc_dir_dom
                      Procedimiento de Validacion de barrio y referencia
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
PROCEDURE sto_pr_scc_dir_dom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validaDirDOm IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.dom_refe,
             cons.val_barr_clie,
             cons.dom_manz,
             cons.dom_etap,
             cons.dom_call_prin,
             cons.dom_num,
             cons.dom_call_secu
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_dom_refe IS TABLE OF int_solic_conso_credi.dom_refe%TYPE;
    TYPE t_val_barr_clie IS TABLE OF int_solic_conso_credi.val_barr_clie%TYPE;
    TYPE t_dom_manz       IS TABLE OF int_solic_conso_actua_datos.dom_manz%TYPE; 
    TYPE t_dom_etap       IS TABLE OF int_solic_conso_actua_datos.dom_etap%TYPE; 
    TYPE t_dom_call_prin  IS TABLE OF int_solic_conso_actua_datos.dom_call_prin%TYPE; 
    TYPE t_dom_num        IS TABLE OF int_solic_conso_actua_datos.dom_num%TYPE; 
    TYPE t_dom_call_secu  IS TABLE OF int_solic_conso_actua_datos.dom_call_secu%TYPE;         

    v_codpais t_codpais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_dom_refe t_dom_refe;
    v_val_barr_clie t_val_barr_clie;
    v_dom_manz      t_dom_manz;
    v_dom_etap      t_dom_etap;
    v_dom_call_prin t_dom_call_prin;
    v_dom_num       t_dom_num;
    v_dom_call_secu t_dom_call_secu;    

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;
    
    existe   BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validaDirDOm;
    LOOP
      FETCH c_validaDirDOm BULK COLLECT
        INTO v_codpais,
             v_numlote,
             v_sec_nume_docu,
             v_dom_refe,
             v_val_barr_clie,
             v_dom_manz,     
             v_dom_etap,     
             v_dom_call_prin,
             v_dom_num,      
             v_dom_call_secu LIMIT rows;

      IF v_codpais.count > 0 THEN
        
      FOR j IN v_codpais.first .. v_codpais.last
        LOOP  
         existe := TRUE;

          IF (v_dom_manz(j)||v_dom_etap(j)||v_dom_call_prin(j)||v_dom_num(j)||v_dom_call_secu(j)||v_dom_refe(j)||v_val_barr_clie(j) IS  NULL) THEN
             existe := FALSE;
          END IF;            
        
          IF (existe) THEN
                                     
             UPDATE sto_docum_digit occ
                 SET -- Actualziamos Indicadores de Validacion
                     occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_numlote(j)
                 AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_validaDirDOm%NOTFOUND;
    END LOOP;
    CLOSE c_validaDirDOm;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'sto_pr_scc_dir_dom: ' || ls_sqlerrm);

  END sto_pr_scc_dir_dom;

  /**************************************************************************
  Descripcion       : sto_pr_scc_email_clie
                      Procedimiento de Validacion de email
  Fecha Creacion    : 06/01/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/      
  
  PROCEDURE sto_pr_scc_email_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_emailcliente IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_mail_clie
        FROM int_solic_conso_credi cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_credi.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_credi.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_credi.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;
    TYPE t_val_mail_clie IS TABLE OF int_solic_conso_credi.val_mail_clie%TYPE;
    
    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_val_mail_clie t_val_mail_clie;
    rows            NATURAL := 1000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    email  NUMBER :=0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_emailcliente;
    LOOP
      FETCH c_emailcliente BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_val_mail_clie
             LIMIT rows;

      IF v_codpais.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := FALSE;

          BEGIN

            numero := 0;
            email  := 0;
            IF ( (instr(v_val_mail_clie(j),'@')=0) OR   (instr(v_val_mail_clie(j),'.')=0)
                 OR  (length(v_val_mail_clie(j))<7) ) THEN
                 
                     numero := 1;
            END IF;            
              
           SELECT count(*) INTO email 
           FROM MAE_CLIEN_COMUN WHERE TICM_OID_TIPO_COMU =3
            AND VAL_DIA_COMU='L' 
            AND VAL_TEXT_COMU = v_val_mail_clie(j); 
                  
            IF (email > 0) THEN
                    
               numero := 1;
                       
            END IF;                          

            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;
          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_emailcliente%NOTFOUND;
    END LOOP;
    CLOSE c_emailcliente;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'STO_PR_SCC_EMAIL_CLIE: ' || ls_sqlerrm);

  END sto_pr_scc_email_clie;
  
END sto_pkg_proce_valid_scc;
/
