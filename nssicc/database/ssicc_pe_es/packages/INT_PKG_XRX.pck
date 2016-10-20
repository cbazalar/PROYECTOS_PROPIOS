CREATE OR REPLACE PACKAGE INT_PKG_XRX IS

   gv_des_log                      VARCHAR2(200);
   gc_cod_modu                   CONSTANT CHAR(3):='XRX';

   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(1500);
   W_FILAS      NUMBER:=999;

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz
                      XEROX - Boleta de Venta Electronica
  Fecha Creacion    : 15/05/2012
  Autor             : Jorge Velásquez
***************************************************************************/
 PROCEDURE INT_PR_XRX_GENER_BOLE_VENT
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psfechaproceso    VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 15/05/2012
  Autor             : Jorge Velásquez
***************************************************************************/
 PROCEDURE INT_PR_XRX_GENER_NOTA_CRED
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psfechaproceso    VARCHAR2
  );


 /***************************************************************************
  Descripcion       : Procedimiento que pasa datos de la tabla PED_BOLET_ELECT
                      a la tabla PED_BOLET_ELECT_HISTO
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 19/09/2012

***************************************************************************/

 PROCEDURE INT_PR_XRX_MOV_NOTA_CRED_HIST(psnombrearchivo   VARCHAR2);


/***************************************************************************
  Descripcion       : Procedimiento que pasa datos de la tabla PED_BOLET_ELECT
                      a la tabla PED_BOLET_ELECT_HISTO
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 19/09/2012

***************************************************************************/

 PROCEDURE INT_PR_XRX_MOV_BOLET_CRED_HIST
 (
    psnombrearchivo   VARCHAR2
  );
END INT_PKG_XRX; 
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_XRX IS

/***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz
                      XEROX - Boleta de Venta Electronica
  Fecha Creacion    : 15/05/2012
  Autor             : Jorge Velásquez
***************************************************************************/
 PROCEDURE INT_PR_XRX_GENER_BOLE_VENT(
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psfechaproceso    VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;
   lsFlag           VARCHAR2(100);
   lscorrelativo    NUMBER(6):=0;

   CURSOR c_interfaz IS
        SELECT
        OID_BOLE_ELEC,
        COD_PERI,
        COD_CLIE,
        COD_TERR,
        TO_CHAR(FEC_PROC, 'yyyymmdd'),
        VAL_NUME_SOLI,
        VAL_CODI_VENT,
        VAL_DESC_PROD,
        NUM_UNID,
        VAL_PREC_CATA,
        NOM_ARCH_INTE,
        NUM_CORR_REGI
        FROM PED_BOLET_ELECT
        WHERE COD_PERI = pscodigoperiodo
        AND to_char(FEC_PROC, 'YYYYMMDD') = psfechaproceso;

   TYPE interfazrec IS RECORD(
     codigoCorrelativo        PED_BOLET_ELECT.OID_BOLE_ELEC%TYPE,
     campanyaProceso          PED_BOLET_ELECT.COD_PERI%TYPE,
     codigoConsultora         PED_BOLET_ELECT.COD_CLIE%TYPE,
     codigoTerritorio         PED_BOLET_ELECT.COD_TERR%TYPE,
     fechaProceso             VARCHAR2(10),
     numeroDespacho           PED_BOLET_ELECT.VAL_NUME_SOLI%TYPE,
     codigoCUV                PED_BOLET_ELECT.VAL_CODI_VENT%TYPE,
     descripcion              PED_BOLET_ELECT.VAL_DESC_PROD%TYPE,
     unidadAtendida           PED_BOLET_ELECT.NUM_UNID%TYPE,
     valorCatalogo            PED_BOLET_ELECT.VAL_PREC_CATA%TYPE,
     nombreArchivo            PED_BOLET_ELECT.NOM_ARCH_INTE%TYPE,
     numeroCorrelativo        PED_BOLET_ELECT.NUM_CORR_REGI%TYPE
   );
   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN

   lbabrirutlfile := TRUE;

   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_filas;

         /* Procedimiento inicial para generar interfaz */
         IF lbabrirutlfile THEN
           gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
           lbabrirutlfile := FALSE;
         END IF;

         IF interfazrecord.COUNT > 0 THEN
           FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
             lscorrelativo := lscorrelativo + 1;
             lslinea := lpad(lscorrelativo, 6, '0')                   || ';' ||
                        interfazrecord(x).campanyaProceso             || ';' ||
                        interfazrecord(x).codigoConsultora            || ';' ||
                        interfazrecord(x).codigoTerritorio            || ';' ||
                        interfazrecord(x).fechaProceso                || ';' ||
                        interfazrecord(x).numeroDespacho              || ';' ||
                        interfazrecord(x).codigoCUV                   || ';' ||
                        interfazrecord(x).descripcion                 || ';' ||
                        interfazrecord(x).unidadAtendida              || ';' ||
                        interfazrecord(x).valorCatalogo ;

                    UPDATE PED_BOLET_ELECT
                    SET NOM_ARCH_INTE = psnombrearchivo,
                    NUM_CORR_REGI = lpad(lscorrelativo, 6, '0')
                    WHERE  OID_BOLE_ELEC = interfazrecord(x).codigoCorrelativo;

             utl_file.put_line(v_handle,lslinea);
           END LOOP;
         END IF;

       EXIT WHEN c_interfaz%NOTFOUND;
     END LOOP;
   CLOSE c_interfaz;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);

     /* Procedimiento final para generar interfaz */
     gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);

    /* Archivo FLAG */
    SELECT   count(*)||';'||TRUNC(sum(trunc(VAL_PREC_CATA)))
    INTO lsFlag
    FROM PED_BOLET_ELECT
    WHERE COD_PERI = pscodigoperiodo
    AND to_char(FEC_PROC, 'YYYYMMDD') = psfechaproceso;

    v_Handle := UTL_FILE.FOPEN ('SICC_DIR', psnombrearchivo||'.FLG','W');
    UTL_FILE.PUT_LINE (v_Handle, lsFlag );

    utl_file.fclose(v_handle);
    /**/

   END IF;


   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_XRX_GENER_BOLE_VENT: '||ls_sqlerrm);
 END INT_PR_XRX_GENER_BOLE_VENT;

 /***************************************************************************
  Descripcion       : Procedimiento que genera la interfaz
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 15/05/2012
  Autor             : Jorge Velásquez
***************************************************************************/

 PROCEDURE INT_PR_XRX_GENER_NOTA_CRED(
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psfechaproceso    VARCHAR2
 )
 IS
   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo       bas_inter.dir_temp%TYPE;
   v_handle         utl_file.file_type;
   lslinea          VARCHAR2(1000);
   lsnombrearchivo  VARCHAR2(50);
   lbabrirutlfile   BOOLEAN;
   w_numFilas       NUMBER := 999;
   lsFlag           VARCHAR2(100);
   lnMontoTotal     NUMBER(9) := 0;
   lnCorrelativo    NUMBER(6) := 1;
   lsNombreArchivoFecha  VARCHAR2(50);
   lscorrelativo NUMBER(9) :=0;
   lsHoraMinuto VARCHAR2(6):='';
   lsContadorSegundos NUMBER(1):= 1 ;
   CURSOR c_interfaz IS
        SELECT
        OID_NOCR_ELEC,
        COD_PERI,
        COD_CLIE,
        COD_TERR,
        substr(VAL_NOMB,1,40) VAL_NOMB,
        NUM_DOCU_IDEN,
        VAL_DIRE,
        VAL_VILL,
        VAL_COMU,
        TO_CHAR(FEC_PROC, 'yyyymmdd'),
        VAL_NUME_SOLI,
        NUM_RECL,
        NUM_ABON,
        VAL_CODI_VENT,
        NUM_UNID,
        VAL_PREC_CATA,
        COD_PROD,
        DES_PROD,
        VAL_PREC_SIN_IMPU,
        VAL_IMPO_IMPU,
        VAL_PREC_TOTA,
        VAL_FOLI_REFE,
        TO_CHAR(VAL_FECH_REFE, 'yyyymmdd'),
        VAL_TIPO_REFE,
        VAL_MOTI,
        NOM_ARCH_INTE,
        NUM_CORR_REGI
        FROM PED_NOTA_CREDI_ELECT
       WHERE COD_PERI = pscodigoperiodo
        AND to_char(FEC_PROC, 'YYYYMMDD') = psfechaproceso;
   TYPE interfazrec IS RECORD(
     codigoConsecutivo        PED_NOTA_CREDI_ELECT.OID_NOCR_ELEC%TYPE,
     campanyaProceso          PED_NOTA_CREDI_ELECT.COD_PERI%TYPE,
     codigoConsultora         PED_NOTA_CREDI_ELECT.COD_CLIE%TYPE,
     codigoTerritorio         PED_NOTA_CREDI_ELECT.COD_TERR%TYPE,
     nombreConsultora         PED_NOTA_CREDI_ELECT.VAL_NOMB%TYPE,
     documentoIdentidad       PED_NOTA_CREDI_ELECT.NUM_DOCU_IDEN%TYPE,
     direccion                PED_NOTA_CREDI_ELECT.VAL_DIRE%TYPE,
     villa                    PED_NOTA_CREDI_ELECT.VAL_VILL%TYPE,
     comuna                   PED_NOTA_CREDI_ELECT.VAL_COMU%TYPE,
     fechaProceso             VARCHAR2(10),
     numeroDespacho           PED_NOTA_CREDI_ELECT.NUM_RECL%TYPE,
     numeroCdr                PED_NOTA_CREDI_ELECT.NUM_ABON%TYPE,
     numeroAbono              PED_NOTA_CREDI_ELECT.NUM_RECL%TYPE,
     codigoCuv                PED_NOTA_CREDI_ELECT.VAL_CODI_VENT%TYPE,
     unidades                 PED_NOTA_CREDI_ELECT.NUM_UNID%TYPE,
     valorCatalogo            PED_NOTA_CREDI_ELECT.VAL_PREC_CATA%TYPE,
     codigoBpcs               PED_NOTA_CREDI_ELECT.COD_PROD%TYPE,
     descripcionProducto      PED_NOTA_CREDI_ELECT.DES_PROD%TYPE,
     valorSinIVA              PED_NOTA_CREDI_ELECT.VAL_PREC_SIN_IMPU%TYPE,
     iva                      PED_NOTA_CREDI_ELECT.VAL_IMPO_IMPU%TYPE,
     valorCatalogoTotal       PED_NOTA_CREDI_ELECT.VAL_PREC_TOTA%TYPE,
     folioReferenciaPedido    PED_NOTA_CREDI_ELECT.VAL_FOLI_REFE%TYPE,
     fechaReferenciaPedido    VARCHAR2(10),
     tipoReferenciaPedido     PED_NOTA_CREDI_ELECT.VAL_TIPO_REFE%TYPE,
     tipoMotivo               PED_NOTA_CREDI_ELECT.VAL_MOTI%TYPE,
     nombreArchivo            PED_NOTA_CREDI_ELECT.NOM_ARCH_INTE%TYPE,
     numeroCorrelativo        PED_NOTA_CREDI_ELECT.NUM_CORR_REGI%TYPE
   );
   TYPE interfazrectab IS TABLE OF interfazrec;
   interfazrecord interfazrectab;

 BEGIN

   OPEN c_interfaz;
     LOOP
       FETCH c_interfaz BULK COLLECT
             INTO interfazrecord LIMIT w_numFilas;

        lsHoraMinuto := TO_CHAR(sysdate + (lsContadorSegundos) / 86400,'HH24MISS');

        lsNombreArchivoFecha := psnombrearchivo || '_' ||lsHoraMinuto;

        /* Procedimiento inicial para generar interfaz */
         gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, lsNombreArchivoFecha, FALSE,
                                                  lsdirtempo, lsnombrearchivo, v_handle);

         lnMontoTotal := 0;
         IF interfazrecord.COUNT > 0 THEN
           FOR x IN interfazrecord.FIRST .. interfazrecord.LAST LOOP
             lscorrelativo := lscorrelativo + 1;
             lslinea := lpad(lscorrelativo, 6, '0')                   || ';' ||
                        interfazrecord(x).campanyaProceso             || ';' ||
                        interfazrecord(x).codigoConsultora            || ';' ||
                        interfazrecord(x).codigoTerritorio            || ';' ||
                        interfazrecord(x).nombreConsultora            || ';' ||
                        interfazrecord(x).documentoIdentidad          || ';' ||
                        interfazrecord(x).direccion                   || ';' ||
                        interfazrecord(x).villa                       || ';' ||
                        interfazrecord(x).comuna                      || ';' ||
                        interfazrecord(x).fechaProceso                || ';' ||
                        interfazrecord(x).numeroDespacho              || ';' ||
                        interfazrecord(x).numeroCdr                   || ';' ||
                        interfazrecord(x).codigoCuv                   || ';' ||
                        interfazrecord(x).unidades                    || ';' ||
                        interfazrecord(x).valorCatalogo               || ';' ||
                        interfazrecord(x).codigoBpcs                  || ';' ||
                        interfazrecord(x).descripcionProducto         || ';' ||
                        interfazrecord(x).valorSinIVA                 || ';' ||
                        interfazrecord(x).iva                         || ';' ||
                        interfazrecord(x).valorCatalogoTotal          || ';' ||
                        interfazrecord(x).folioReferenciaPedido       || ';' ||
                        interfazrecord(x).fechaReferenciaPedido       || ';' ||
                        interfazrecord(x).tipoReferenciaPedido        || ';' ||
                        interfazrecord(x).tipoMotivo ;

                UPDATE PED_NOTA_CREDI_ELECT
                SET NOM_ARCH_INTE = lsNombreArchivoFecha,
                    NUM_CORR_REGI = lpad(lscorrelativo, 6, '0')
                WHERE OID_NOCR_ELEC = interfazrecord(x).codigoConsecutivo;

             utl_file.put_line(v_handle,lslinea);

             lnMontoTotal := lnMontoTotal + interfazrecord(x).valorCatalogo;
           END LOOP;

           utl_file.fclose(v_handle);

           /* Procedimiento final para generar interfaz */
           gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,lsNombreArchivoFecha,lsnombrearchivo);

            /* Archivo FLAG */
            lsFlag := interfazrecord.COUNT||';'||TRUNC(lnMontoTotal);

            v_Handle := UTL_FILE.FOPEN ('SICC_DIR', lsNombreArchivoFecha||'.FLG','W');
            UTL_FILE.PUT_LINE (v_Handle, lsFlag );

            utl_file.fclose(v_handle);

            lnCorrelativo := lnCorrelativo + 1;
            lsContadorSegundos := lsContadorSegundos + 1;
         END IF;

       EXIT WHEN c_interfaz%NOTFOUND;
     END LOOP;
   CLOSE c_interfaz;


   /*Archivo dummy para que el proceso no se caiga */
   gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
   utl_file.put_line(v_handle, '0');
   utl_file.fclose(v_handle);

   gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',lsdirtempo,psnombrearchivo,lsnombrearchivo);
   /**/

   RETURN;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_XRX_GENER_NOTA_CRED: '||ls_sqlerrm);
 END INT_PR_XRX_GENER_NOTA_CRED;


/***************************************************************************
  Descripcion       : Procedimiento que pasa datos de la tabla PED_BOLET_ELECT
                      a la tabla PED_BOLET_ELECT_HISTO
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 19/09/2012

***************************************************************************/

 PROCEDURE INT_PR_XRX_MOV_NOTA_CRED_HIST(psnombrearchivo   VARCHAR2)
 IS


 BEGIN

  INSERT INTO PED_NOTA_CREDI_ELECT_HISTO(oid_nocr_elec,
                                          oid_peri     ,
                                          cod_peri     ,
                                          oid_clie     ,
                                          cod_clie     ,
                                          cod_terr     ,
                                          val_nomb     ,
                                          num_docu_iden,
                                          val_dire     ,
                                          val_vill     ,
                                          val_comu     ,
                                          fec_proc     ,
                                          val_nume_soli,
                                          num_recl     ,
                                          num_abon     ,
                                          val_codi_vent,
                                          num_unid     ,
                                          val_prec_cata,
                                          cod_prod     ,
                                          des_prod     ,
                                          val_prec_sin_impu  ,
                                          val_impo_impu,
                                          val_prec_tota,
                                          val_foli_refe,
                                          val_fech_refe,
                                          val_tipo_refe,
                                          val_moti    ,
                                          val_foli    ,
                                          val_estr    ,
                                          oid_soli_posi_pedi,
                                          oid_soli_posi_recl,
                                          nom_arch_inte     ,
                                          num_corr_regi)  (SELECT oid_nocr_elec,
                                          oid_peri     ,
                                          cod_peri     ,
                                          oid_clie     ,
                                          cod_clie     ,
                                          cod_terr     ,
                                          val_nomb     ,
                                          num_docu_iden,
                                          val_dire     ,
                                          val_vill     ,
                                          val_comu     ,
                                          fec_proc     ,
                                          val_nume_soli,
                                          num_recl     ,
                                          num_abon     ,
                                          val_codi_vent,
                                          num_unid     ,
                                          val_prec_cata,
                                          cod_prod     ,
                                          des_prod     ,
                                          val_prec_sin_impu  ,
                                          val_impo_impu,
                                          val_prec_tota,
                                          val_foli_refe,
                                          val_fech_refe,
                                          val_tipo_refe,
                                          val_moti    ,
                                          val_foli    ,
                                          val_estr    ,
                                          oid_soli_posi_pedi,
                                          oid_soli_posi_recl,
                                          nom_arch_inte     ,
                                          num_corr_regi FROM PED_NOTA_CREDI_ELECT WHERE  nom_arch_inte = psnombrearchivo);

  delete from PED_NOTA_CREDI_ELECT WHERE  nom_arch_inte = psnombrearchivo;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_XRX_MOV_NOTA_CRED_HIST: '||ls_sqlerrm);
 END INT_PR_XRX_MOV_NOTA_CRED_HIST;


/***************************************************************************
  Descripcion       : Procedimiento que pasa datos de la tabla PED_BOLET_ELECT
                      a la tabla PED_BOLET_ELECT_HISTO
                      XEROX - Nota de Crédito Electronica
  Fecha Creacion    : 19/09/2012

***************************************************************************/

 PROCEDURE INT_PR_XRX_MOV_BOLET_CRED_HIST (psnombrearchivo   VARCHAR2)
 IS


 BEGIN

          INSERT INTO PED_BOLET_ELECT_HISTO(OID_BOLE_ELEC,
                                    OID_PERI,
                                    COD_PERI,
                                    OID_CLIE,
                                    COD_CLIE,
                                    COD_TERR,
                                    FEC_PROC,
                                    VAL_NUME_SOLI,
                                    VAL_CODI_VENT,
                                    VAL_DESC_PROD,
                                    NUM_UNID,
                                    VAL_PREC_CATA,
                                    IND_RECL,
                                    OID_LINE_RECL,
                                    COD_ORIG,
                                    VAL_FOLI,
                                    VAL_TIDE,
                                    OID_SOLI_POSI_PEDI,
                                    NOM_ARCH_INTE,
                                    NUM_CORR_REGI,
                                    VAL_ESTR,
                                    COD_ZONA,
                                    COD_TIPO_DOCU)
                                    (SELECT OID_BOLE_ELEC,
                                    OID_PERI,
                                    COD_PERI,
                                    OID_CLIE,
                                    COD_CLIE,
                                    COD_TERR,
                                    FEC_PROC,
                                    VAL_NUME_SOLI,
                                    VAL_CODI_VENT,
                                    VAL_DESC_PROD,
                                    NUM_UNID,
                                    VAL_PREC_CATA,
                                    IND_RECL,
                                    OID_LINE_RECL,
                                    COD_ORIG,
                                    VAL_FOLI,
                                    VAL_TIDE,
                                    OID_SOLI_POSI_PEDI,
                                    NOM_ARCH_INTE,
                                    NUM_CORR_REGI,
                                    VAL_ESTR,
                                    COD_ZONA,
                                    COD_TIPO_DOCU FROM PED_BOLET_ELECT WHERE NOM_ARCH_INTE = psnombrearchivo
                                    );

 delete from PED_BOLET_ELECT WHERE NOM_ARCH_INTE = psnombrearchivo;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_XRX_MOV_BOLET_CRED_HIST: '||ls_sqlerrm);
 END INT_PR_XRX_MOV_BOLET_CRED_HIST;

END INT_PKG_XRX; 
/
