CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_sad AS
  -- Author  : PEEXTDOLIVA
  -- Created : 09/06/2008 08:53:00 a.m.
  -- Purpose : Procesos de Validaciones de Tipo Documento Actualizacion de Datos (SAD)

  /**************************************************************************
   Descripcion       : STO_PR_SAD_PAIS_OK
                     Procedimiento de Validacion de Pais Sin Error
                     segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008
   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_UBIGE_CLIEN_OK
                       Procedimiento de Validacion del Territorio del Ubigeo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_ubige_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_PERIO_OK
                     Procedimiento de Validacion del Periodo Sin Error
                     segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_perio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_NDIRE_CLIEN_OK
                       Procedimiento de Validacion Negativa de la direccion del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_ndire_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_CLIEN_OK
                       Procedimiento de Validacion de la zona activa del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
   Descripcion       : STO_PR_SAD_DIREC_CLIEN_OK
                       Procedimiento de Validacion de la direccion del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_direc_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
   Descripcion       : STO_PR_SAD_DOCU_RECHA_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009
   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_docu_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_CODIG_CLIEN_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009
   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_codig_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_UNIDA_ADMIN_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009
   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_SAD_VALID_CEDEX_CLIEN
                    Procedimiento de Validacion de cedula existe para otro Cliente
  Fecha Creacion    : 18/11/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_cedex_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_EXIST_DIREC_OK
                       Procedimiento de Existencia de Dirección del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 10/06/2010
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : Validacion de DNI y RUC Duplicado
   Fecha Creacion    : 05/07/2010
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_sad_dniru_dupli
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion de existencia de DNI en SICC
  Fecha Creacion    : 05/07/2010
  Autor             : Jose Cairampoma
  Modificado        : Nicolás López
  Fecha Mod.        : 11/01/2011
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_dni
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
     Descripcion       : Validacion de existencia de RUC en SICC
     Fecha Creacion    : 05/07/2010
     Autor             : Jose Cairampoma
     Modificado        : Nicolás López
     Fecha Mod.        : 11/01/2011
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_ruc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_ARRIB_CLIEN_OK
                       Procedimiento de Existencia de la zona de arribo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_arrib_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_BLOQU_CLIEN_OK
                       Procedimiento de Existencia de bloqueo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_DEUDA_CLIEN_OK
                       Procedimiento de Existencia de deuda del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_deuda_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_CAMBIO_ZONAS
                       Si es un cambio de zona, se debe generar un error
                       para que sea validado manualmente por la pantalla de
                       gestion de errores
   Fecha Creacion    : 05/09/2011
   Paremtros
            pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_cambio_zonas
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : sto_pr_sad_valid_autom
                       Procedimiento de envio de actualizacion automatica
                       segun secuencia de ejecucion
   Fecha Creacion    : 22/09/2011
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_sad_valid_autom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /**************************************************************************
  Descripcion       : STO_PR_SAD_NUMER_DOCUM_MOD11
                    Procedimiento de documento de identidad con modulo 11
  Fecha Creacion    : 25/07/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_sad_numer_docum_mod11
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
  PROCEDURE sto_pr_sad_forma_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
   Descripcion       : STO_PR_SAD_VALID_MANUA
                       Procedimiento de Validacion que detiene todas las solicitudes
                       para validacion manual

   Fecha Creacion    : 26/11/2013

   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_valid_manua
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_EXIST
                     Procedimiento de Validacion de Zona actual de 
                     Consultora es igual a zona de la UA
   Fecha Creacion    : 27/02/2015


   Autor             : Fernando Ochoa
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_exist
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
  
  PROCEDURE sto_pr_sad_cedu_iden
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );  

  /**************************************************************************
  Descripcion       : sto_pr_scc_tipo_docum
                      Procedimiento de Validacion de documento de identidad
  Fecha Creacion    : 07/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez
  **************************************************************************/  
  
  PROCEDURE sto_pr_sad_tipo_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
 
 /**************************************************************************
  Descripcion       : sto_pr_sad_nacio
                      Procedimiento de Validacion de nacionalidad
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_sad_nacio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
    /**************************************************************************
  Descripcion       : sto_pr_sad_terri_corre
                      Procedimiento de Validacion codigo territorial corresponde
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
  PROCEDURE sto_pr_sad_terri_corre
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ); 
  
  /**************************************************************************
  Descripcion       : sto_pr_sad_ddom_dent
                      Procedimiento de Validacion indicador direccion de domicilio 
                      igual direccion de entrega
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_sad_ddom_dent
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );   
  
    /**************************************************************************
  Descripcion       : sto_pr_sad_dir_dom
                      Procedimiento de Validacion de barrio y referencia
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_sad_dir_dom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ); 
  
   /**************************************************************************
  Descripcion       : sto_pr_sad_email_clie
                      Procedimiento de Validacion de email
  Fecha Creacion    : 06/01/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/      
  
  PROCEDURE sto_pr_sad_email_clie
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  
   /**************************************************************************
  Descripcion       : STO_PR_SAD_TELE_CLIE_DIFE
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 06/01/2016


  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE sto_pr_sad_tele_clie_dife
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_sad;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_sad AS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /**************************************************************************
   Descripcion       : STO_PR_SAD_PAIS_OK
                     Procedimiento de Validacion de Pais Sin Error
                     segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_pais
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
             pais.oid_pais
        FROM int_solic_conso_actua_datos cons,
             seg_pais                    pais,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pais.cod_pais IS NOT NULL
         AND pais.cod_pais = cons.cod_pais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_oidpais IS TABLE OF int_solic_conso_actua_datos.oid_pais%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_oidpais    t_oidpais;

    rows NATURAL := 1000;
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

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
             v_secnumdocu,
             v_oidpais LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_secnumdocu.count
          UPDATE int_solic_conso_actua_datos dat
             SET dat.oid_pais  = v_oidpais(i),
                 oid_terr      = NULL,
                 oid_terr_admi = NULL
           WHERE dat.sec_nume_docu = v_secnumdocu(i)
             AND dat.num_lote = v_numlote(i);

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_PAIS: ' || ls_sqlerrm);

  END sto_pr_sad_pais;

  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  /**************************************************************************
   Descripcion       : STO_PR_SAD_UBIGE_CLIEN_OK
                       Procedimiento de Validacion del Territorio del Ubigeo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_ubige_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.uni_admi,
             cons.oid_pais,
             cons.cod_sect_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.uni_admi IS NOT NULL;

    CURSOR c_actualizadatos2 IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.uni_admi IS NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_actua_datos.oid_pais%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_actua_datos.cod_sect_clie%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_uni_admi      t_uni_admi;
    v_oid_pais      t_oid_pais;
    v_cod_sect_clie t_cod_sect_clie;

    rows NATURAL := 1000;
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;

    ls_oid_zona         zon_zona.oid_zona%TYPE;
    ls_cod_regi         zon_regio.cod_regi%TYPE;
    existe              BOOLEAN := TRUE;
    ls_oid_terri        zon_terri.oid_terr%TYPE;
    lsparageneestrugeo  sto_param_gener_occrr.val_param%TYPE;
    ls_vp_oid_estru_geo zon_terri.vepo_oid_valo_estr_geop%TYPE;
    ls_order1           zon_valor_estru_geopo.orde_1%TYPE;
    ls_order2           zon_valor_estru_geopo.orde_2%TYPE;
    ls_order3           zon_valor_estru_geopo.orde_3%TYPE;
    ls_order4           zon_valor_estru_geopo.orde_4%TYPE;

  BEGIN

    lsparageneestrugeo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_GENER_ESTR_GEO');
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
             v_sec_nume_docu,
             v_uni_admi,
             v_oid_pais,
             v_cod_sect_clie LIMIT rows;
      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos CAMPOS ADICIONALES
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          BEGIN
            SELECT terri.oid_terr
              INTO ls_oid_terri
              FROM zon_terri terri
             WHERE terri.cod_terr = to_number(substr(v_uni_admi(i),
                                                     8,
                                                     6))
               AND nvl(terri.ind_borr,
                       0) = 0
               AND terri.pais_oid_pais = v_oid_pais(i);
          EXCEPTION
            WHEN value_error OR invalid_number THEN
              ls_oid_terri := NULL;
            WHEN no_data_found THEN
              ls_oid_terri := NULL;
          END;

          IF (ls_oid_terri IS NOT NULL) THEN

            --FORALL i IN 1 .. v_codpais.COUNT
            UPDATE int_solic_conso_actua_datos
               SET oid_terr = ls_oid_terri
             WHERE sec_nume_docu = v_sec_nume_docu(i)
               AND num_lote = v_numlote(i);

            -- Actualizamos Documentos Validados OK

            existe := TRUE;
            IF (substr(v_uni_admi(i),
                       3,
                       4) IS NOT NULL) THEN

              BEGIN
                SELECT oid_zona
                  INTO ls_oid_zona
                  FROM zon_zona
                 WHERE cod_zona = substr(v_uni_admi(i),
                                         3,
                                         4);

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
                               AND pais_oid_pais = v_oid_pais(i))
                       AND c.oid_zona = ls_oid_zona;

                    UPDATE sto_docum_digit stod
                       SET stod.cod_regi           = ls_cod_regi,
                           stod.cod_zona           = substr(v_uni_admi(i),
                                                            3,
                                                            4),
                           stod.cod_ulti_vali_ejec = pscodigovalidactual,
                           stod.cod_ulti_vali_exit = pscodigovalidactual,
                           stod.usu_modi           = psusuario,
                           stod.fec_modi           = SYSDATE
                     WHERE stod.cod_pais = pscodigopais
                       AND stod.cod_tipo_docu = pscodigotipodoc
                       AND stod.num_lote = v_numlote(i)
                       AND stod.sec_nume_docu = v_sec_nume_docu(i);

                    IF (lsparageneestrugeo = '1') THEN
                      BEGIN
                        SELECT vepo_oid_valo_estr_geop
                          INTO ls_vp_oid_estru_geo
                          FROM zon_terri
                         WHERE pais_oid_pais = v_oid_pais(i)
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

                        -- Si el centro poblado viene lleno, se debe mantener
                        IF v_cod_sect_clie(i) IS NOT NULL THEN
                          ls_order4 := v_cod_sect_clie(i);
                        END IF;

                        UPDATE int_solic_conso_actua_datos
                           SET cod_depa_clie = ls_order1,
                               cod_prov_clie = ls_order2,
                               cod_dist_clie = ls_order3,
                               cod_sect_clie = ls_order4
                         WHERE sec_nume_docu = v_sec_nume_docu(i)
                           AND num_lote = v_numlote(i);

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
      EXIT WHEN c_actualizadatos%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos;

    OPEN c_actualizadatos2;
    LOOP
      FETCH c_actualizadatos2 BULK COLLECT
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
      EXIT WHEN c_actualizadatos2%NOTFOUND;
    END LOOP;
    CLOSE c_actualizadatos2;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'STO_PR_SAB_UBIGE_CLIEN: ' || ls_sqlerrm);
  END sto_pr_sad_ubige_clien;

  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  /**************************************************************************
   Descripcion       : STO_PR_SAD_PERIO_OK
                     Procedimiento de Validacion del Periodo Sin Error
                     segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_perio
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
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

          SELECT nvl(MAX(perd_oid_peri),
                     0)
            INTO v_oid_peri_act
            FROM fac_contr_cierr
           WHERE tcie_oid_tipo_cier = 2
             AND perd_oid_peri = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo)
             AND zzon_oid_zona = (SELECT c.oid_zona
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

          UPDATE int_solic_conso_actua_datos
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_UBIGE_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_perio;

  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  /**************************************************************************
   Descripcion       : STO_PR_SAD_NDIRE_CLIEN_OK
                       Procedimiento de Validacion Negativa de la direccion del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_ndire_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2

  )

   IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.val_dire_clie,
             cons.tip_via_clie,
             cons.val_nomb_vicl,
             cons.cod_depa_clie,
             cons.cod_prov_clie,
             cons.cod_dist_clie,
             cons.cod_sect_clie,
             cons.uni_admi,
             ind_dire
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_clie%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_actua_datos.val_nomb_vicl%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_actua_datos.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_actua_datos.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_actua_datos.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_actua_datos.cod_sect_clie%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_ind_dire IS TABLE OF int_solic_conso_actua_datos.ind_dire%TYPE;

    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_val_dire_clie t_val_dire_clie;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_cod_depa_clie t_cod_depa_clie;
    v_cod_prov_clie t_cod_prov_clie;
    v_cod_dist_clie t_cod_dist_clie;
    v_cod_sect_clie t_cod_sect_clie;
    v_uni_admi      t_uni_admi;
    v_ind_dire      t_ind_dire;

    rows NATURAL := 1000;

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;

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
             v_secnumdocu,
             v_val_dire_clie,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_uni_admi,
             v_ind_dire LIMIT rows;
      IF v_secnumdocu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          existe := TRUE;

          -- Si esta activo l ckeck Direccion OK, no se evalua
          IF v_ind_dire(j) = '0' THEN
            IF (v_uni_admi(j) IS NOT NULL OR (v_cod_depa_clie(j) || v_cod_prov_clie(j) ||
               v_cod_dist_clie(j) || v_cod_sect_clie(j) IS NOT NULL)) THEN

              IF (v_val_dire_clie(j) IS NULL AND v_tip_via_clie(j) IS NULL AND
                 v_val_nomb_vicl(j) IS NULL) THEN

                existe := FALSE;

              END IF;

            END IF;
          END IF;

          IF (existe) THEN

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_NDIRE_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_ndire_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_CLIEN_OK
                       Procedimiento de Validacion de la zona activa del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.uni_admi,
             cons.oid_terr_admi
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF int_solic_conso_actua_datos.oid_terr_admi%TYPE;

    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_uni_admi      t_uni_admi;
    v_oid_terr_admi t_oid_terr_admi;
    lsoidzona       zon_zona.oid_zona%TYPE;
    rows            NATURAL := 1000;
    isvalid         BOOLEAN := TRUE;
    j               BINARY_INTEGER := 0;

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
             v_secnumdocu,
             v_uni_admi,
             v_oid_terr_admi LIMIT rows;
      IF v_secnumdocu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          isvalid := TRUE;
          -- Si la posicion 6 no es null
          IF v_uni_admi(j) IS NOT NULL THEN
            BEGIN
              SELECT c.oid_zona
                INTO lsoidzona
                FROM zon_terri_admin a,
                     zon_secci       b,
                     zon_zona        c
               WHERE a.zscc_oid_secc = b.oid_secc
                 AND b.zzon_oid_zona = c.oid_zona
                 AND a.oid_terr_admi = v_oid_terr_admi(j)
                 AND c.ind_acti = 1
                 AND c.ind_borr = 0;
            EXCEPTION
              WHEN no_data_found THEN
                isvalid := FALSE;
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_ZONA_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_zona_clien;

  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  /**************************************************************************
   Descripcion       : STO_PR_SAD_DIREC_CLIEN_OK
                       Procedimiento de Validacion positiva de la direccion del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 06/06/2008


   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_direc_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_actualizadatos IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.val_dire_clie, --21
             cons.cod_depa_clie, --50
             cons.cod_prov_clie, --51
             cons.cod_dist_clie, --52
             cons.cod_sect_clie, --53
             cons.val_dire_entre_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_clie%TYPE;
    TYPE t_cod_depa_clie IS TABLE OF int_solic_conso_actua_datos.cod_depa_clie%TYPE;
    TYPE t_cod_prov_clie IS TABLE OF int_solic_conso_actua_datos.cod_prov_clie%TYPE;
    TYPE t_cod_dist_clie IS TABLE OF int_solic_conso_actua_datos.cod_dist_clie%TYPE;
    TYPE t_cod_sect_clie IS TABLE OF int_solic_conso_actua_datos.cod_sect_clie%TYPE;
    TYPE t_val_dire_entre_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_entre_clie%TYPE;

    v_codclie             t_codclie;
    v_numlote             t_numlote;
    v_secnumdocu          t_secnumdocu;
    v_val_dire_clie       t_val_dire_clie;
    v_cod_depa_clie       t_cod_depa_clie;
    v_cod_prov_clie       t_cod_prov_clie;
    v_cod_dist_clie       t_cod_dist_clie;
    v_cod_sect_clie       t_cod_sect_clie;
    v_val_dire_entre_clie t_val_dire_entre_clie;
    rows                  NATURAL := 1000;

    j               BINARY_INTEGER := 0;
    contador        NUMBER := 0;
    regvalido       BOOLEAN := TRUE;
    lsparadiregeore sto_param_gener_occrr.val_param%TYPE;
    ls_order1       zon_valor_estru_geopo.orde_1%TYPE;
    ls_order2       zon_valor_estru_geopo.orde_2%TYPE;
    ls_order3       zon_valor_estru_geopo.orde_3%TYPE;
    ls_order4       zon_valor_estru_geopo.orde_4%TYPE;

  BEGIN

    lsparadiregeore := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_DIREC_SIN_GEORE');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_secnumdocu,
             v_val_dire_clie,
             v_cod_depa_clie,
             v_cod_prov_clie,
             v_cod_dist_clie,
             v_cod_sect_clie,
             v_val_dire_entre_clie LIMIT rows;
      IF v_secnumdocu.count > 0 THEN
        -- Actualizamos Documentos Validados OK

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          IF (v_val_dire_clie(j) IS NOT NULL OR v_val_dire_entre_clie(j) IS NOT NULL) THEN

            regvalido := TRUE;

            IF ((v_cod_depa_clie(j) IS NULL) AND (v_cod_prov_clie(j) IS NULL) AND
               (v_cod_dist_clie(j) IS NULL) AND (v_cod_sect_clie(j) IS NULL) AND
               (lsparadiregeore = '1')) THEN

              BEGIN

                SELECT orde_1,
                       orde_2,
                       orde_3,
                       orde_4
                  INTO ls_order1,
                       ls_order2,
                       ls_order3,
                       ls_order4
                  FROM zon_valor_estru_geopo a,
                       zon_terri             b,
                       mae_clien_direc       c
                 WHERE a.ind_borr = 0
                   AND b.ind_borr = 0
                   AND c.ind_elim = 0
                   AND c.ind_dire_ppal = 1
                   AND a.oid_valo_estr_geop = b.vepo_oid_valo_estr_geop
                   AND c.terr_oid_terr = b.oid_terr
                   AND c.clie_oid_clie =
                       (SELECT d.oid_clie FROM mae_clien d WHERE d.cod_clie = v_codclie(j));

              EXCEPTION
                WHEN no_data_found THEN
                  ls_order1 := NULL;
                  ls_order2 := NULL;
                  ls_order3 := NULL;
                  ls_order4 := NULL;
              END;

              UPDATE int_solic_conso_actua_datos dat
                 SET cod_depa_clie = ls_order1,
                     cod_prov_clie = ls_order2,
                     cod_dist_clie = ls_order3,
                     cod_sect_clie = ls_order4
               WHERE dat.sec_nume_docu = v_secnumdocu(j)
                 AND dat.num_lote = v_numlote(j);

              v_cod_depa_clie(j) := ls_order1;
              v_cod_prov_clie(j) := ls_order2;
              v_cod_dist_clie(j) := ls_order3;
              v_cod_sect_clie(j) := ls_order4;

            END IF;

            IF ((v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j) ||
               v_cod_sect_clie(j) IS NULL) OR
               (length(v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j) ||
                        v_cod_sect_clie(j)) < 18)) THEN

              regvalido := FALSE;

            ELSIF ((length(v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j) ||
                           v_cod_sect_clie(j)) = 24)) THEN

              SELECT COUNT(1)
                INTO contador
                FROM zon_valor_estru_geopo a
               WHERE a.orde_1 || a.orde_2 || a.orde_3 || a.orde_4 =
                     v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j) ||
                     v_cod_sect_clie(j);

              IF contador = 0 THEN
                regvalido := FALSE;
              END IF;
            ELSIF (length(v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j)) = 18) THEN

              SELECT COUNT(1)
                INTO contador
                FROM zon_valor_estru_geopo a
               WHERE a.orde_1 || a.orde_2 || a.orde_3 =
                     v_cod_depa_clie(j) || v_cod_prov_clie(j) || v_cod_dist_clie(j);

              IF contador = 0 THEN
                regvalido := FALSE;
              END IF;

            ELSE

              regvalido := FALSE;

            END IF;

          ELSE
            --regvalido := FALSE;
            regvalido := TRUE;
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_DIREC_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_direc_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_DOCU_RECHA_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009


   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_docu_recha
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
             cons.ind_stat_proc,
             cons.ind_moti_rech
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_ind_stat_proc IS TABLE OF int_solic_conso_actua_datos.ind_stat_proc%TYPE;
    TYPE t_ind_moti_rech IS TABLE OF int_solic_conso_actua_datos.ind_moti_rech%TYPE;

    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_ind_stat_proc t_ind_stat_proc;
    v_ind_moti_rech t_ind_moti_rech;

    rows NATURAL := 1000;

    j BINARY_INTEGER := 0;

    existe BOOLEAN := TRUE;

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
             v_secnumdocu,
             v_ind_stat_proc,
             v_ind_moti_rech LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          existe := TRUE;

          IF (v_ind_stat_proc(j) = '02' AND
             (v_ind_moti_rech(j) IN ('D',
                                      'I',
                                      'N'))) THEN

            existe := FALSE;

          END IF;

          IF (existe) THEN

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_DOCU_RECHA: ' || ls_sqlerrm);

  END sto_pr_sad_docu_recha;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_CODIG_CLIEN_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009


   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_codig_clien
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
             cons.cod_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_codclie    t_codclie;

    rows NATURAL := 1000;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN;

    lscodigocliente mae_clien.cod_clie%TYPE;
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
             v_secnumdocu,
             v_codclie LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          existe := TRUE;
          --Validar si el código de cliente entrante  ya existe en la tabla mae_clien_ident como cédula (DNI
          SELECT MAX(cod_clie)
            INTO lscodigocliente --v_codclie(j)
            FROM mae_clien       c,
                 mae_clien_ident i
           WHERE clie_oid_clie = oid_clie
             AND i.num_docu_iden = v_codclie(j);

          IF lscodigocliente IS NOT NULL THEN

            UPDATE sto_docum_digit
               SET cod_clie = lscodigocliente
             WHERE sec_nume_docu = v_secnumdocu(j)
               AND num_lote = v_numlote(j);

            UPDATE int_solic_conso_actua_datos
               SET cod_clie = lscodigocliente
             WHERE sec_nume_docu = v_secnumdocu(j)
               AND num_lote = v_numlote(j);

          ELSE
            --entonces validar la existencia del mismo en la tabla mae_clien
            SELECT MAX(cod_clie)
              INTO lscodigocliente --v_codclie(j)
              FROM mae_clien c
             WHERE c.cod_clie = v_codclie(j);

            IF lscodigocliente IS NULL THEN
              existe := FALSE;
            END IF;
          END IF;

          IF (existe) THEN
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_CODIG_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_codig_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_UNIDA_ADMIN_OK
                       Procedimiento de Validacion de la zona activa del Cliente
                       segun secuencia de ejecucion
   Fecha Creacion    : 19/01/2009


   Autor              : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_unida_admin
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
             cons.uni_admi,
             cons.oid_terr,
             cons.oid_pais
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_uniadmi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_oidterr IS TABLE OF int_solic_conso_actua_datos.oid_terr%TYPE;
    TYPE t_oidpais IS TABLE OF int_solic_conso_actua_datos.oid_pais%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_uniadmi    t_uniadmi;
    v_oidterr    t_oidterr;
    v_oidpais    t_oidpais;

    rows NATURAL := 1000;

    j               BINARY_INTEGER := 0;
    lsoidterrriadmi zon_terri_admin.oid_terr_admi%TYPE;
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
             v_secnumdocu,
             v_uniadmi,
             v_oidterr,
             v_oidpais LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          IF (v_uniadmi(j) IS NULL) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_secnumdocu(j);

          ELSE
            -- Actualizamos CAMPOS ADICIONALES
            BEGIN

              SELECT terri_admin.oid_terr_admi
                INTO lsoidterrriadmi
                FROM zon_terri_admin terri_admin
               WHERE terri_admin.terr_oid_terr = v_oidterr(j)
                 AND terri_admin.pais_oid_pais = v_oidpais(j)
                 AND terri_admin.ind_borr = '0';

            EXCEPTION
              WHEN no_data_found THEN
                lsoidterrriadmi := NULL;
            END;

            IF (lsoidterrriadmi IS NOT NULL) THEN

              UPDATE int_solic_conso_actua_datos dat
                 SET dat.oid_terr_admi = lsoidterrriadmi
               WHERE dat.sec_nume_docu = v_secnumdocu(j)
                 AND dat.num_lote = v_numlote(j);

              -- Actualizamos Documentos Validados OK

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_PAIS: ' || ls_sqlerrm);

  END sto_pr_sad_unida_admin;

  /**************************************************************************
  Descripcion       : STO_PR_SAD_VALID_CEDEX_CLIEN
                    Procedimiento de Validacion de cedula existe para otro Cliente
  Fecha Creacion    : 18/11/2009
  Parametros Entrada:


  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_sad_cedex_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.tip_docu,
             cons.num_docu_iden
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;

    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_tip_docu      t_tip_docu;
    v_num_docu_iden t_num_docu_iden;

    rows NATURAL := 1000;

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

    OPEN c_actualizadatos;
    LOOP
      FETCH c_actualizadatos BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_secnumdocu,
             v_tip_docu,
             v_num_docu_iden LIMIT rows;
      IF v_secnumdocu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          existe := TRUE;

          numero := 0;

          SELECT COUNT(*)
            INTO numero
            FROM (SELECT a.cod_clie
                    FROM mae_clien       a,
                         mae_clien_ident b
                   WHERE a.oid_clie = b.clie_oid_clie
                     AND b.num_docu_iden = v_num_docu_iden(j)
                     AND b.tdoc_oid_tipo_docu =
                         (SELECT oid_tipo_docu
                            FROM mae_tipo_docum c
                           WHERE c.cod_tipo_docu = v_tip_docu(j))) ant
           WHERE ant.cod_clie <> v_codclie(j);

          IF (numero = 0) THEN
            existe := FALSE;
          END IF;

          IF (existe) THEN
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_CEDEX_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_cedex_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_EXIST_DIREC_OK
                       Procedimiento de Existencia de Dirección del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 10/06/2010
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_direc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_actualizadatos IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.val_dire_clie,
             cons.tip_via_clie,
             cons.val_nomb_vicl,
             cons.ind_dire,
             cons.uni_admi
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_val_dire_clie IS TABLE OF int_solic_conso_actua_datos.val_dire_clie%TYPE;
    TYPE t_tip_via_clie IS TABLE OF int_solic_conso_credi.tip_via_clie%TYPE;
    TYPE t_val_nomb_vicl IS TABLE OF int_solic_conso_actua_datos.val_nomb_vicl%TYPE;
    TYPE t_ind_dire IS TABLE OF int_solic_conso_actua_datos.ind_dire%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;

    v_numlote       t_numlote;
    v_secnumdocu    t_secnumdocu;
    v_val_dire_clie t_val_dire_clie;
    v_tip_via_clie  t_tip_via_clie;
    v_val_nomb_vicl t_val_nomb_vicl;
    v_ind_dire      t_ind_dire;
    v_uni_admi      t_uni_admi;

    rows     NATURAL := 1000;
    j        BINARY_INTEGER := 0;
    is_valid BOOLEAN := TRUE;

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
             v_secnumdocu,
             v_val_dire_clie,
             v_tip_via_clie,
             v_val_nomb_vicl,
             v_ind_dire,
             v_uni_admi LIMIT rows;
      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP
          is_valid := TRUE;

          IF (v_val_dire_clie(j) IS NULL AND v_tip_via_clie(j) IS NULL and
             v_val_nomb_vicl(j) IS NULL AND v_uni_admi(j) is not null) THEN
            is_valid := FALSE;
          END IF;

          IF (is_valid) THEN
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_sad_exist_direc: ' || ls_sqlerrm);

  END sto_pr_sad_exist_direc;

  /**************************************************************************
   Descripcion       : Validacion de DNI y RUC Duplicado
   Fecha Creacion    : 05/07/2010
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_sad_dniru_dupli
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_docu_iden IS NULL
         AND cons.tip_docu IS NULL
      UNION ALL
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_docu_iden IS NOT NULL
         AND cons.tip_docu IS NOT NULL
         AND cons.num_docu_iden IN (SELECT num_docu_iden
                                      FROM int_solic_conso_actua_datos c,
                                           sto_docum_digit             d
                                     WHERE d.sec_nume_docu = c.sec_nume_docu
                                       AND d.num_lote = c.num_lote
                                       AND d.cod_tipo_docu = pscodigotipodoc
                                       AND d.cod_pais = pscodigopais
                                       AND d.ind_rech = '0'
                                       AND d.ind_envi = '0'
                                       AND c. num_docu_iden IS NOT NULL
                                       AND cons.tip_docu IS NOT NULL
                                     GROUP BY c.num_docu_iden
                                    HAVING COUNT(1) = 1)
      UNION ALL
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_ruc IS NULL
         AND cons.tip_docu IS NULL
      UNION ALL
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.num_ruc IS NOT NULL
         AND cons.tip_docu IS NOT NULL
         AND cons.num_ruc IN (SELECT c.num_ruc
                                FROM int_solic_conso_actua_datos c,
                                     sto_docum_digit             d
                               WHERE d.sec_nume_docu = c.sec_nume_docu
                                 AND d.num_lote = c.num_lote
                                 AND d.cod_tipo_docu = pscodigotipodoc
                                 AND d.cod_pais = pscodigopais
                                 AND d.ind_rech = '0'
                                 AND d.ind_envi = '0'
                                 AND c. num_ruc IS NOT NULL
                                 AND cons.tip_docu IS NOT NULL
                               GROUP BY c.num_ruc
                              HAVING COUNT(1) = 1);

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    rows NATURAL := 1000;

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_sad_dniru_dupli: ' || ls_sqlerrm);

  END sto_pr_sad_dniru_dupli;

  /**************************************************************************
  Descripcion       : Validacion de existencia de DNI en SICC
  Fecha Creacion    : 05/07/2010
  Autor             : Jose Cairampoma
  Modificado        : Nicolás López
  Fecha Mod.        : 11/01/2011
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_dni
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadni IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.cod_clie, --3
             cons.tip_docu, --16
             cons.num_docu_iden --17
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;

    v_numlote        t_numlote;
    v_secnumdocu     t_secnumdocu;
    v_cod_clie       t_cod_clie;
    v_tip_docu       t_tip_docu;
    v_num_docu_iden  t_num_docu_iden;
    ls_desctipodoc   mae_tipo_docum.val_sigl %TYPE;
    ln_contadortdoc  NUMBER(10);
    ln_contadordni   NUMBER(10);
    ln_oid_tipodocum mae_tipo_docum.oid_tipo_docu%TYPE;
    ls_num_docu_iden mae_clien_ident.num_docu_iden%TYPE;
    ls_cod_clie      mae_clien.cod_clie%TYPE;

    rows    NATURAL := 1000;
    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;

  BEGIN
    ln_contadordni := 0;
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizadni;
    LOOP
      FETCH c_actualizadni BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_cod_clie,
             v_tip_docu,
             v_num_docu_iden LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := FALSE;

          --Si 'Tipo Documento' no es nulo y 'Nro Documento Identidad' no es nulo
          IF ((v_tip_docu(j) IS NOT NULL) AND (v_num_docu_iden(j) IS NOT NULL)) THEN

            SELECT COUNT(1)
              INTO ln_contadortdoc
              FROM mae_tipo_docum mtdoc
             WHERE mtdoc.cod_tipo_docu = v_tip_docu(j);

            --Si existe 'Tipo Documento' en las tablas de SiCC
            IF (ln_contadortdoc > 0) THEN

              -- Se obtiene el Oid Tipo Documento
              BEGIN
                SELECT oid_tipo_docu
                  INTO ln_oid_tipodocum
                  FROM mae_tipo_docum
                 WHERE cod_tipo_docu = v_tip_docu(j);
              EXCEPTION
                WHEN no_data_found THEN
                  ln_oid_tipodocum := NULL;
              END;

              --DNI que ya existe en SiCC.
              SELECT COUNT(1)
                INTO ln_contadordni
                FROM mae_clien_ident a,
                     mae_clien       cl
               WHERE a.clie_oid_clie = cl.oid_clie
                 AND a.tdoc_oid_tipo_docu = (SELECT mt.oid_tipo_docu --ln_oid_TipoDocum
                                               FROM mae_tipo_docum mt
                                              WHERE mt.val_sigl = 'DNI')
                 AND ltrim(a.num_docu_iden,
                           '0') = ltrim(v_num_docu_iden(j),
                                        '0')
                 AND NOT EXISTS (SELECT 1
                        FROM mae_clien_ident mci,
                             mae_clien       mc
                       WHERE mci.clie_oid_clie = mc.oid_clie
                         AND ltrim(mci.num_docu_iden,
                                   '0') = ltrim(v_num_docu_iden(j),
                                                '0')
                         AND cod_clie = v_cod_clie(j));

              --- Si DNI Existe para otras consultoras
              IF (ln_contadordni != 0) THEN
                isvalid := FALSE;
              ELSE
                -- Si DNI No Existe para otras consultoras

                BEGIN
                  SELECT a.num_docu_iden,
                         cl.cod_clie
                    INTO ls_num_docu_iden,
                         ls_cod_clie
                    FROM mae_clien_ident a,
                         mae_clien       cl
                   WHERE a.clie_oid_clie = cl.oid_clie
                     AND a.tdoc_oid_tipo_docu = ln_oid_tipodocum
                     AND cl.cod_clie = v_cod_clie(j);
                EXCEPTION
                  WHEN no_data_found THEN
                    ls_num_docu_iden := NULL;
                    ls_cod_clie      := NULL;
                END;

                BEGIN
                  SELECT tdoc.val_sigl
                    INTO ls_desctipodoc
                    FROM mae_tipo_docum tdoc
                   WHERE tdoc.cod_tipo_docu = v_tip_docu(j);
                EXCEPTION
                  WHEN no_data_found THEN
                    ls_desctipodoc := NULL;
                END;

                IF (ltrim(ls_num_docu_iden,
                          '0') = ltrim(v_num_docu_iden(j),
                                        '0') AND ls_cod_clie = v_cod_clie(j)) THEN

                  isvalid := TRUE;

                  UPDATE int_solic_conso_actua_datos conso
                     SET conso.val_tipo_docu_anti = ls_desctipodoc,
                         conso.num_docu_iden_anti = NULL
                   WHERE conso.cod_clie = v_cod_clie(j)
                     AND conso.tip_docu = v_tip_docu(j)
                     AND conso.cod_pais = pscodigopais
                     AND ltrim(conso.num_docu_iden,
                               '0') = ltrim(v_num_docu_iden(j),
                                            '0')
                     AND conso.num_lote = v_numlote(j);

                ELSE

                  isvalid := TRUE;

                  IF (ltrim(ls_num_docu_iden,
                            '0') != ltrim(v_num_docu_iden(j),
                                           '0') AND ls_cod_clie = v_cod_clie(j)) THEN

                    UPDATE int_solic_conso_actua_datos conso
                       SET conso.val_tipo_docu_anti = ls_desctipodoc,
                           conso.num_docu_iden_anti = ls_num_docu_iden
                     WHERE conso.cod_clie = v_cod_clie(j)
                       AND conso.tip_docu = v_tip_docu(j)
                       AND conso.cod_pais = pscodigopais
                       AND ltrim(conso.num_docu_iden,
                                 '0') = ltrim(v_num_docu_iden(j),
                                              '0')
                       AND conso.num_lote = v_numlote(j);

                  ELSE

                    isvalid := FALSE;

                  END IF;

                END IF;

              END IF;

            ELSE
              isvalid := FALSE;
            END IF;

          ELSE

            IF ((v_tip_docu(j) IS NULL) AND (v_num_docu_iden(j) IS NULL)) THEN
              isvalid := TRUE;
            END IF;

            IF ((v_tip_docu(j) IS NULL) AND (v_num_docu_iden(j) IS NOT NULL)) THEN
              isvalid := FALSE;
            END IF;

            IF ((v_tip_docu(j) IS NOT NULL) AND (v_num_docu_iden(j) IS NULL)) THEN
               update int_solic_conso_actua_datos a set a.tip_docu=null where a.sec_nume_docu=v_secnumdocu(j); 
              isvalid := TRUE;
            END IF;

          END IF;

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

      EXIT WHEN c_actualizadni%NOTFOUND;
    END LOOP;

    CLOSE c_actualizadni;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_sad_exist_dni: ' || ls_sqlerrm);

  END sto_pr_sad_exist_dni;

  /**************************************************************************
   Descripcion       : Validacion de existencia de RUC en SICC
   Fecha Creacion    : 05/07/2010
   Autor             : Jose Cairampoma
   Modificado        : Nicolás López
   Fecha Mod.        : 01/02/2011
  ***************************************************************************/
  PROCEDURE sto_pr_sad_exist_ruc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizaruc IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.num_ruc,
             cons.cod_clie,
             cons.tip_docu, --16
             cons.num_docu_iden --17
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_actua_datos.num_ruc%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;

    v_numlote        t_numlote;
    v_secnumdocu     t_secnumdocu;
    v_num_ruc        t_num_ruc;
    v_cod_clie       t_cod_clie;
    v_tip_docu       t_tip_docu;
    v_num_docu_iden  t_num_docu_iden;
    ls_desctipodoc   mae_tipo_docum.val_sigl %TYPE;
    ln_contadorruc   NUMBER(10);
    ln_oid_tipodocum mae_tipo_docum.oid_tipo_docu%TYPE;
    ls_num_docu_iden mae_clien_ident.num_docu_iden%TYPE;
    ls_cod_clie      mae_clien.cod_clie%TYPE;

    rows    NATURAL := 1000;
    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;

  BEGIN
    ln_contadorruc := 0;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_actualizaruc;
    LOOP
      FETCH c_actualizaruc BULK COLLECT
        INTO v_numlote,
             v_secnumdocu,
             v_num_ruc,
             v_cod_clie,
             v_tip_docu,
             v_num_docu_iden LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          isvalid := FALSE;

          --Si 'Nro RUC' no es nulo
          IF (v_num_ruc(j) IS NOT NULL) THEN

            -- Se obtiene el Oid Tipo Documento
            BEGIN
              SELECT oid_tipo_docu
                INTO ln_oid_tipodocum
                FROM mae_tipo_docum
               WHERE cod_tipo_docu = v_tip_docu(j);
            EXCEPTION
              WHEN no_data_found THEN
                ln_oid_tipodocum := NULL;
            END;

            --RUC que ya existe en SiCC.
            SELECT COUNT(1)
              INTO ln_contadorruc
              FROM mae_clien_ident a,
                   mae_clien       cl
             WHERE a.clie_oid_clie = cl.oid_clie
               AND a.tdoc_oid_tipo_docu = (SELECT mt.oid_tipo_docu --ln_oid_TipoDocum
                                             FROM mae_tipo_docum mt
                                            WHERE mt.val_sigl = 'RUC')
               AND ltrim(a.num_docu_iden,
                         '0') = ltrim(v_num_ruc(j),
                                      '0')
               AND NOT EXISTS (SELECT 1
                      FROM mae_clien_ident mci,
                           mae_clien       mc
                     WHERE mci.clie_oid_clie = mc.oid_clie
                       AND ltrim(mci.num_docu_iden,
                                 '0') = ltrim(v_num_ruc(j),
                                              '0')
                       AND cod_clie = v_cod_clie(j));

            IF (ln_contadorruc != 0) THEN
              --- 1ero: Si el RUC existe ya para otra persona en el sistema SiCC (según tabla MAE_CLIEN_IDENT),
              --- pues da error y salta la validacion.
              isvalid := FALSE;
            ELSE
              -- Si RUC No Existe para otras consultoras

              BEGIN
                SELECT a.num_docu_iden,
                       cl.cod_clie
                  INTO ls_num_docu_iden,
                       ls_cod_clie
                  FROM mae_clien_ident a,
                       mae_clien       cl
                 WHERE a.clie_oid_clie = cl.oid_clie
                   AND a.tdoc_oid_tipo_docu = (SELECT mt.oid_tipo_docu --ln_oid_TipoDocum
                                                 FROM mae_tipo_docum mt
                                                WHERE mt.val_sigl = 'RUC')
                   AND cl.cod_clie = v_cod_clie(j);
              EXCEPTION
                WHEN no_data_found THEN
                  ls_num_docu_iden := NULL;
                  ls_cod_clie      := NULL;
              END;

              BEGIN
                IF (v_tip_docu(j) != '02') THEN
                  ls_desctipodoc := 'RUC';
                ELSE
                  SELECT tdoc.val_sigl
                    INTO ls_desctipodoc
                    FROM mae_tipo_docum tdoc
                   WHERE tdoc.cod_tipo_docu = v_tip_docu(j);
                END IF;
              EXCEPTION
                WHEN no_data_found THEN
                  ls_desctipodoc := 'RUC';
              END;

              IF (ls_num_docu_iden IS NOT NULL) THEN

                IF (ltrim(ls_num_docu_iden,
                          '0') = ltrim(v_num_ruc(j),
                                        '0') AND ls_cod_clie = v_cod_clie(j)) THEN

                  --- 2do: Si el RUC ya existe para el mismo cliente siendo actualizado,
                  --- no hay problema vuelve a actualizar el RUC del cliente, no salta la validacion.
                  isvalid := TRUE;

                  UPDATE int_solic_conso_actua_datos conso
                     SET conso.val_tipo_docu_anti = ls_desctipodoc,
                         conso.num_docu_nruc_anti = NULL
                   WHERE conso.cod_clie = v_cod_clie(j)
                        --AND conso.tip_docu           = v_tip_docu(j)
                     AND conso.cod_pais = pscodigopais
                     AND ltrim(conso.num_ruc,
                               '0') = ltrim(v_num_ruc(j),
                                            '0')
                     AND conso.num_lote = v_numlote(j);

                ELSE
                  IF (ltrim(ls_num_docu_iden,
                            '0') != ltrim(v_num_ruc(j),
                                           '0') AND ls_cod_clie = v_cod_clie(j)) THEN
                    --- 3ero: Si el RUC no existe para nadie ni para el cliente siendo actualizado,
                    --- entonces  no salta la validacion y se graba el RUC para el cliente
                    isvalid := TRUE;

                    UPDATE int_solic_conso_actua_datos conso
                       SET conso.val_tipo_docu_anti = ls_desctipodoc,
                           conso.num_docu_nruc_anti = ls_num_docu_iden
                     WHERE conso.cod_clie = v_cod_clie(j)
                          -- AND conso.tip_docu           = v_tip_docu(j)
                       AND conso.cod_pais = pscodigopais
                       AND ltrim(conso.num_ruc,
                                 '0') = ltrim(v_num_ruc(j),
                                              '0')
                       AND conso.num_lote = v_numlote(j);
                  ELSE
                    isvalid := TRUE;
                  END IF;
                END IF;

              ELSE
                isvalid := TRUE;
              END IF;

            END IF;

          ELSE
            --- Si el campo de RUC en la tabla INT_SOLIC_ACTUA_DATOS es nulo
            --- (no viaja dato en la interfaz, ni el usuario gestionando en STO ingresa un RUC),
            --- pues no salta la validacion.
            isvalid := TRUE;

          END IF;

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

      EXIT WHEN c_actualizaruc%NOTFOUND;
    END LOOP;

    CLOSE c_actualizaruc;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_sad_exist_ruc: ' || ls_sqlerrm);

  END sto_pr_sad_exist_ruc;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_ARRIB_CLIEN_OK
                       Procedimiento de Existencia de la zona de arribo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_arrib_clien
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.val_zona_arri = (SELECT DISTINCT c.cod_zona
                                     FROM zon_terri_admin a,
                                          zon_secci       b,
                                          zon_zona        c
                                    WHERE a.zscc_oid_secc = b.oid_secc
                                      AND b.zzon_oid_zona = c.oid_zona
                                      AND a.oid_terr_admi = cons.oid_terr_admi
                                      AND c.ind_acti = 1
                                      AND c.ind_borr = 0);

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    rows NATURAL := 1000;

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_ZONA_ARRIB_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_zona_arrib_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_BLOQU_CLIEN_OK
                       Procedimiento de Existencia de bloqueo del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.num_ruc,
             cons.cod_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_actua_datos.num_ruc%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_num_ruc    t_num_ruc;
    v_cod_clie   t_cod_clie;

    rows NATURAL := 1000;

    j      BINARY_INTEGER := 0;
    lscont NUMBER := 0;

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
             v_secnumdocu,
             v_num_ruc,
             v_cod_clie LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          SELECT COUNT(1)
            INTO lscont
            FROM mae_clien_bloqu       a,
                 mae_accio_proce_bloqu b,
                 mae_accio_bloqu       c,
                 mae_proce_bloqu       d
           WHERE a.fec_desb IS NULL
             AND a.clie_oid_clie =
                 (SELECT x.oid_clie FROM mae_clien x WHERE x.cod_clie = v_cod_clie(j))
             AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
             AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
             AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
             AND d.cod_proc_bloq = 'AD'
             AND c.cod_acci_bloq = 'DN';

          IF (lscont = 0) THEN
            --registros valido
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_BLOQU_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_bloqu_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_DEUDA_CLIEN_OK
                       Procedimiento de Existencia de deuda del Cliente Sin Error
                       segun secuencia de ejecucion
   Fecha Creacion    : 23/08/2010
   Paremtros
             pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psindrequisito        : indicador requisito,
            psindgestion          : indicador gestion
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_sad_deuda_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.num_ruc,
             cons.cod_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_num_ruc IS TABLE OF int_solic_conso_actua_datos.num_ruc%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_num_ruc    t_num_ruc;
    v_cod_clie   t_cod_clie;

    rows    NATURAL := 1000;
    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;

    lnsaldodeudor NUMBER := 0;
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
             v_secnumdocu,
             v_num_ruc,
             v_cod_clie LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          isvalid := TRUE;

          lnsaldodeudor := gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_cod_clie(j));

          IF (lnsaldodeudor = 0) THEN
            --valido
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_DEUDA_CLIEN: ' || ls_sqlerrm);

  END sto_pr_sad_deuda_clien;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_CAMBIO_ZONAS
                       Si es un cambio de zona, se debe generar un error
                       para que sea validado manualmente por la pantalla de
                       gestion de errores
   Fecha Creacion    : 05/09/2011
   Paremtros
            pscodigopais         : Pais
            pscodigotipodoc       : Tipo Documento
            pscodigovalidactual   : Codigo Validacion Actual
            pscodigovalidanterior : Codigo valido anterior,
            psusuario             : usuario
            psnumeroproceso       : numero proceso
   Autor              : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_cambio_zonas
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             cons.uni_admi,
             cons.cod_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ,
             sto_docum_digit             dd
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND dd.sec_nume_docu = occ.sec_nume_docu;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;

    vactiva mae_clien_datos_adici.ind_acti%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;
    v_uni_admi   t_uni_admi;
    v_cod_clie   t_cod_clie;

    rows NATURAL := 1000;
    j    BINARY_INTEGER := 0;

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
             v_secnumdocu,
             v_uni_admi,
             v_cod_clie LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP

          -- Obtiene el indicador de actividad de la consultora :  [1]: Activa, [0]: inactiva
          BEGIN
            SELECT d.ind_acti
              INTO vactiva
              FROM mae_clien_datos_adici d,
                   mae_estat_clien       e,
                   mae_clien             m
             WHERE d.clie_oid_clie = m.oid_clie
               AND e.oid_esta_clie(+) = d.esta_oid_esta_clie
               AND m.cod_clie = v_cod_clie(j);
          EXCEPTION
            WHEN OTHERS THEN
              vactiva := 0;
          END;

          IF vactiva = 0 OR
             (vactiva = 1 AND
             substr(v_uni_admi(j),
                     3,
                     4) = gen_pkg_gener.gen_fn_clien_datos(v_cod_clie(j),
                                                            'COD_ZONA')) THEN

            --Si no hay cambio de zona o la consultora no es ACTIVA, el registro se considera válido
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_CAMBIO_ZONAS: ' || ls_sqlerrm);

  END sto_pr_sad_cambio_zonas;

  /**************************************************************************
   Descripcion       : sto_pr_sad_valid_autom
                       Procedimiento de envio de actualizacion automatica
                       segun secuencia de ejecucion
   Fecha Creacion    : 22/09/2011
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_sad_valid_autom
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_actualizadatos IS
      SELECT cons.uni_admi,
             cons.val_celu_clie,
             cons.val_ape1,
             cons.tip_docu,
             occ.num_lote,
             occ.sec_nume_docu
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_secnumdocu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;

    TYPE t_uni_admi IS TABLE OF int_solic_conso_actua_datos.uni_admi%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_actua_datos.val_celu_clie%TYPE;
    TYPE t_val_ape1 IS TABLE OF int_solic_conso_actua_datos.val_ape1%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;

    v_numlote    t_numlote;
    v_secnumdocu t_secnumdocu;

    v_uni_admi      t_uni_admi;
    v_val_celu_clie t_val_celu_clie;
    v_val_ape1      t_val_ape1;
    v_tip_docu      t_tip_docu;

    rows    NATURAL := 1000;
    isvalid BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;

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
        INTO v_uni_admi,
             v_val_celu_clie,
             v_val_ape1,
             v_tip_docu,
             v_numlote,
             v_secnumdocu LIMIT rows;

      IF v_secnumdocu.count > 0 THEN

        FOR j IN v_secnumdocu.first .. v_secnumdocu.last
        LOOP
          isvalid := TRUE;
          /*
          Si en la unidad administrativa viene un dato de 6 caracteres,
             el campo celular viene lleno,
             el campo Apellido 1 viene vacio y
             el tipo de documento de identidad viene nulo
          */
          IF (length(v_uni_admi(j)) = 6 AND v_val_celu_clie(j) IS NOT NULL AND
             v_val_ape1(j) IS NULL AND v_tip_docu(j) IS NULL) THEN
            -- No pasa la validacion
            isvalid := FALSE;
          END IF;

          IF (isvalid) THEN
            --válido
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_VALID_AUTOM: ' || ls_sqlerrm);

  END sto_pr_sad_valid_autom;
  /**************************************************************************
  Descripcion       : STO_PR_SAD_NUMER_DOCUM_MOD11
                    Procedimiento de documento de identidad con modulo 11
  Fecha Creacion    : 25/07/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_sad_numer_docum_mod11
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;

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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'STO_PR_SAD_NUMER_DOCUM_MOD11: ' || ls_sqlerrm);

  END sto_pr_sad_numer_docum_mod11;

  /***************************************************************************
  Descripcion       : Validacion de Formato de Documento de Identidad.
  Fecha Creacion    : 12/03/2013
  Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE sto_pr_sad_forma_docum
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND num_docu_iden IS NOT NULL
         -- Se valida que tenga dos guiones en el numero de documento
         AND DECODE(LENGTH(cons.num_docu_iden) - LENGTH(REPLACE(cons.num_docu_iden, '-')) ,
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'sto_pr_scc_format_docum: ' || ls_sqlerrm);

  END sto_pr_sad_forma_docum;

  /**************************************************************************
   Descripcion       : STO_PR_SAD_VALID_MANUA
                       Procedimiento de Validacion que detiene todas las solicitudes
                       para validacion manual

   Fecha Creacion    : 26/11/2013

   Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_sad_valid_manua
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_sad_valid_manua: ' || ls_sqlerrm);

  END sto_pr_sad_valid_manua;
  
 ----------------------------------------------- 
  /**************************************************************************
   Descripcion       : STO_PR_SAD_ZONA_EXIST
                     Procedimiento de Validacion de Zona actual de 
                     Consultora es igual a zona de la UA
   Fecha Creacion    : 27/02/2015


   Autor             : Fernando Ochoa
  ***************************************************************************/
  PROCEDURE sto_pr_sad_zona_exist
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
             cons.uni_admi,
             substr(cons.uni_admi,3,4) zona ,
             substr(cons.uni_admi,7,1) seccion,
             cons.cod_clie
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit         occ
        WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;
         
     TYPE datosCampos IS RECORD(
       num_lote VARCHAR2(8),
       sec_nume_docu VARCHAR2(12),
       uni_admi VARCHAR2(13),
       zona VARCHAR2(4),
       seccion VARCHAR2(1),
       cod_clie VARCHAR2(15)
      );
      
      TYPE datosCamposTab  IS TABLE OF datosCampos ;
      datosCamposRecord datosCamposTab;
      
    rows NATURAL := 1000;
    isvalid BOOLEAN := TRUE;
    j    BINARY_INTEGER := 0;
    ls_cod_secc zon_secci.cod_secc%TYPE;
    ls_cod_zona zon_zona.cod_zona%TYPE;

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
        INTO datosCamposRecord LIMIT rows;
      IF datosCamposRecord.count > 0 THEN
    FOR j IN datosCamposRecord.FIRST .. datosCamposRecord.LAST 
		LOOP
			isvalid := TRUE;
			
			IF(datosCamposRecord(j).uni_admi IS NOT NULL) THEN
				BEGIN 
				 select cod_secc,
				         cod_zona
				  into ls_cod_secc,
					   ls_cod_zona
				  from mae_clien             a,
					   mae_clien_unida_admin b,
					   zon_terri_admin       c,
					   zon_secci             d,
					   zon_zona              e
				 where a.oid_clie = b.clie_oid_clie
				   and b.perd_oid_peri_fin is null
				   and b.ztad_oid_terr_admi = c.oid_terr_admi
				   and c.ZSCC_OID_SECC = d.oid_secc
				   and d.zzon_oid_zona = e.oid_zona
				   and a.cod_clie = datosCamposRecord(j).cod_clie;
				EXCEPTION
					WHEN no_data_found THEN
					  ls_cod_secc := NULL;
					  ls_cod_zona := NULL;
				END;
								
				IF( (ls_cod_zona = datosCamposRecord(j).zona) AND (ls_cod_secc !=datosCamposRecord(j).seccion)) THEN
					isvalid :=FALSE;
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
				 AND occ.num_lote = datosCamposRecord(j).num_lote
				 AND occ.sec_nume_docu = datosCamposRecord(j).sec_nume_docu;
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
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SAD_ZONA_EXIST: ' || ls_sqlerrm);

  END sto_pr_sad_zona_exist;

/**************************************************************************
  Descripcion       : sto_pr_scc_cedu_iden
                      Procedimiento de Validacion de documento de identidad
  Fecha Creacion    : 27/05/2015
  --------------------------------------------------------------------------
  Autor             : Gonzalo Javier Huertas Agurto
  **************************************************************************/
  
  PROCEDURE sto_pr_sad_cedu_iden
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
        FROM int_solic_conso_actua_datos cons,
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
          
           IF ( v_tipdocu(j) = '06' ) THEN
             update int_solic_conso_actua_datos 
                    set tip_docu =  (select cod_tipo_docu from mae_tipo_docum where val_sigl='NIT')
             where  sec_nume_docu = v_sec_nume_docu(j); 
           END IF;
          
             isvalid := TRUE;
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
                   
              END IF;
              
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
                              'sto_pr_sad_cedu_iden: ' || ls_sqlerrm);

  END sto_pr_sad_cedu_iden; 
  
  /**************************************************************************
  Descripcion       : sto_pr_sad_tipo_docum
                      Procedimiento de Validacion de documento de identidad
  Fecha Creacion    : 07/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez
  **************************************************************************/ 
    
  PROCEDURE sto_pr_sad_tipo_docum
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
             cons.tip_docu,
             cons.num_docu_iden
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_tip_docu IS TABLE OF int_solic_conso_actua_datos.tip_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_solic_conso_actua_datos.num_docu_iden%TYPE;
       
    v_codpais t_codpais;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_tip_docu      t_tip_docu;
    v_num_docu_iden t_num_docu_iden;

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
             v_numlote,
             v_sec_nume_docu,
             v_tip_docu,
             v_num_docu_iden LIMIT rows;

      IF v_codpais.count > 0 THEN

        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          existe := TRUE;

          IF ((v_tip_docu(j) IS NULL) OR (v_num_docu_iden(j) IS NULL)) THEN

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

      raise_application_error(-20123,
                              'sto_pr_sad_tipo_docum: ' || ls_sqlerrm);

  END sto_pr_sad_tipo_docum;
  
/**************************************************************************
  Descripcion       : sto_pr_sad_nacio
                      Procedimiento de Validacion de nacionalidad
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/
  
   PROCEDURE sto_pr_sad_nacio
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
             int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ,
             gen_i18n_sicc_comun   gen
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND gen.attr_enti = 'SEG_NACIO'
         AND gen.val_oid = nac.oid_naci
        AND upper(substr(gen.val_i18n,1,4)) = upper(substr(cons.cod_naci,1,4));

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_oid_naci IS TABLE OF int_solic_conso_actua_datos.oid_naci%TYPE;

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
          UPDATE int_solic_conso_actua_datos
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
                              'sto_pr_sad_nacio: ' || ls_sqlerrm);

  END sto_pr_sad_nacio;  
  
  /**************************************************************************
  Descripcion       : sto_pr_sad_terri_corre
                      Procedimiento de Validacion codigo territorial corresponde
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_sad_terri_corre
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;         
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_cod_terr_corr IS TABLE OF int_solic_conso_actua_datos.cod_terr_corr%TYPE;

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
                              'sto_pr_sad_terri_corre: ' || ls_sqlerrm);

  END sto_pr_sad_terri_corre;
  
  /**************************************************************************
  Descripcion       : sto_pr_sad_ddom_dent
                      Procedimiento de Validacion indicador direccion de domicilio 
                      igual direccion de entrega
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
  PROCEDURE sto_pr_sad_ddom_dent
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais                IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;         
    TYPE t_numlote                IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu          IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_ind_dird_dire          IS TABLE OF int_solic_conso_actua_datos.ind_dird_dire%TYPE;
    TYPE t_ent_manz               IS TABLE OF int_solic_conso_actua_datos.ent_manz%TYPE;           
    TYPE t_ent_etap               IS TABLE OF int_solic_conso_actua_datos.ent_etap%TYPE;           
    TYPE t_ent_call_prin          IS TABLE OF int_solic_conso_actua_datos.ent_call_prin%TYPE;      
    TYPE t_ent_num                IS TABLE OF int_solic_conso_actua_datos.ent_num%TYPE;            
    TYPE t_ent_call_secu          IS TABLE OF int_solic_conso_actua_datos.ent_call_secu%TYPE;      
    TYPE t_ent_refe               IS TABLE OF int_solic_conso_actua_datos.ent_refe%TYPE;           
    TYPE t_val_barr_entre_clie    IS TABLE OF int_solic_conso_actua_datos.val_barr_entre_clie%TYPE;    

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
             v_val_barr_entre_clie LIMIT rows;

      IF v_codpais.count > 0 THEN
        
      FOR j IN v_codpais.first .. v_codpais.last
        LOOP  
         existe := TRUE;

          IF ((v_ind_dird_dire(j) IS NULL)) THEN
             existe := FALSE;
          END IF;  
          
        
          IF (existe) THEN
                 
             IF ( (v_ind_dird_dire(j)= 'S') and 
                  (v_ent_manz(j)||v_ent_etap(j)||v_ent_call_prin(j)||v_ent_num(j)||v_ent_call_secu(j)||v_ent_refe(j)||v_val_barr_entre_clie(j) IS  NULL)) THEN

                UPDATE int_solic_conso_actua_datos cons
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
                              'sto_pr_sad_ddom_dent: ' || ls_sqlerrm);

  END sto_pr_sad_ddom_dent;
  
    /**************************************************************************
  Descripcion       : sto_pr_sad_dir_dom
                      Procedimiento de Validacion de barrio y referencia
  Fecha Creacion    : 02/09/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/  
  
PROCEDURE sto_pr_sad_dir_dom
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais        IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;         
    TYPE t_numlote        IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu  IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_dom_refe       IS TABLE OF int_solic_conso_actua_datos.dom_refe%TYPE;
    TYPE t_val_barr_clie  IS TABLE OF int_solic_conso_actua_datos.val_barr_clie%TYPE;    
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

          IF ( v_dom_manz(j)||v_dom_etap(j)||v_dom_call_prin(j)||v_dom_num(j)||v_dom_call_secu(j)||v_dom_refe(j)||v_val_barr_clie(j) IS NULL) THEN
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
                              'sto_pr_sad_dir_dom: ' || ls_sqlerrm);

  END sto_pr_sad_dir_dom; 
  
 /**************************************************************************
  Descripcion       : sto_pr_sad_email_clie
                      Procedimiento de Validacion de email
  Fecha Creacion    : 06/01/2015
  --------------------------------------------------------------------------
  Autor             : Rosalvina Ramirez Guardia
  **************************************************************************/      
  
  PROCEDURE sto_pr_sad_email_clie
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_actua_datos.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_val_mail_clie IS TABLE OF int_solic_conso_actua_datos.val_mail_clie%TYPE;
    
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
                              'STO_PR_SAD_EMAIL_CLIE: ' || ls_sqlerrm);

  END sto_pr_sad_email_clie; 
  
 /**************************************************************************
  Descripcion       : STO_PR_SAD_TELE_CLIE_DIFE
                    Procedimiento de Validacion de telefono de Cliente
          Sin Error segun secuencia de ejecucion
  Fecha Creacion    : 06/01/2016


  Autor             : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE sto_pr_sad_tele_clie_dife
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
        FROM int_solic_conso_actua_datos cons,
             sto_tmp_docum_digit   occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_actua_datos.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_actua_datos.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_actua_datos.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_actua_datos.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_actua_datos.sec_nume_docu%TYPE;
    TYPE t_val_celu_clie IS TABLE OF int_solic_conso_actua_datos.val_celu_clie%TYPE;
    TYPE t_val_telf_trab IS TABLE OF int_solic_conso_actua_datos.val_telf_trab%TYPE;
    TYPE t_val_telf_clie IS TABLE OF int_solic_conso_actua_datos.val_telf_clie%TYPE;
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
                              'STO_PR_SAD_TELE_CLIE_DIFE: ' || ls_sqlerrm);

  END sto_pr_sad_tele_clie_dife;      
    
END sto_pkg_proce_valid_sad;
/
