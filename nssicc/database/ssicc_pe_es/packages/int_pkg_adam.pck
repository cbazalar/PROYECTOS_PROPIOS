CREATE OR REPLACE PACKAGE INT_PKG_ADAM IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/******************************************************************************
Descripcion       : Genera Interfaz de Envio de Gerentes Region y Zona (ADA-1)
Fecha Modificacion: 31-01-2013
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo Sistema
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Archivo
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ADA_ENVIO_GEREN_REZON
  (psCodigoPais 		  VARCHAR2,
   psCodigoSistema 		VARCHAR2,
   psCodigoInterfaz 	VARCHAR2,
   psNombreArchivo 		VARCHAR2);

END INT_PKG_ADAM;
/

CREATE OR REPLACE PACKAGE BODY INT_PKG_ADAM IS
/******************************************************************************
Descripcion       : Genera Interfaz de Envio de Gerentes Region y Zona (ADA-1)
Fecha Modificacion: 31-01-2013
Parametros:
 psCodigoPais     : Codigo de Pais
 psCodigoSistema  : Codigo Sistema
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Archivo
Autor: CSVD - FFVV
*******************************************************************************/
PROCEDURE INT_PR_ADA_ENVIO_GEREN_REZON
  (psCodigoPais 		  VARCHAR2,
   psCodigoSistema 		VARCHAR2,
   psCodigoInterfaz 	VARCHAR2,
   psNombreArchivo 		VARCHAR2)
IS
   CURSOR c_interfaz (vsCodigoSociedad VARCHAR2) IS

     WITH temp AS
     (
       (
        SELECT vsCodigoSociedad codigoSociedad,
      	   	   zr.cod_regi codigoRegion,
      	   	   '' codigoZona,
      	   	   substr((cli.val_ape1 || ' ' || cli.val_ape2 || ' ' || cli.val_nom1 || ' ' || cli.val_nom2),0,100) nombreGerente,
      	   	   cda.cod_cub codigoCUB,
      	   	   to_char(SYSDATE,'yyyymmdd') fechaSistema,
      	   	   'Directo' causaCambio,
               'GR' rol
      	  FROM zon_regio zr,
      	       mae_clien cli,
      		     mae_clien_datos_adici cda
         WHERE zr.ind_acti = '1'
      	   AND zr.ind_borr = '0'
      	   AND zr.clie_oid_clie = cli.oid_clie
      	   AND cli.oid_clie = cda.clie_oid_clie
       )
       UNION ALL
       (
        SELECT vsCodigoSociedad codigoSociedad,
        	   	 zr.cod_regi codigoRegion,
        	   	 zz.cod_zona codigoZona,
        	   	 substr((cli.val_ape1 || ' ' || cli.val_ape2 || ' ' || cli.val_nom1 || ' ' || cli.val_nom2),0,100) nombreGerente,
      	   	   cda.cod_cub codigoCUB,
        	   	 to_char(SYSDATE,'yyyymmdd') fechaSistema,
        	   	 'Directo' causaCambio,
               'GZ' rol
        	FROM zon_regio zr,
        		   zon_zona zz,
        	     mae_clien cli,
        		   mae_clien_datos_adici cda
         WHERE zr.ind_acti = '1'
        	 AND zr.ind_borr = '0'
           AND zz.ind_acti = '1'
        	 AND zz.ind_borr = '0'
        	 AND zr.oid_regi = zz.zorg_oid_regi
        	 AND zz.clie_oid_clie = cli.oid_clie
        	 AND cli.oid_clie = cda.clie_oid_clie
       )
     )
     SELECT * FROM temp ORDER BY codigoRegion, codigoZona ;


   TYPE interfazRec IS RECORD
	   (
    	 codigoSociedad       VARCHAR2(4),
       codigoRegion					zon_regio.cod_regi%TYPE,
    	 codigoZona	   	 	   	zon_zona.cod_zona%TYPE,
		   nombreGerente			  VARCHAR2(100),
    	 codigoCUB            mae_clien_datos_adici.cod_cub%TYPE,
       fechaSistema         VARCHAR2(8),
       causaCambio					VARCHAR2(10),
       rol                  VARCHAR2(4)
	   );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   W_FILAS             NUMBER := 1000 ;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lsCodigoSociedad    VARCHAR2(4);

BEGIN
    -- Obtenemos el valor parametirzado por pais para la Sociedad --
    SELECT val_pain INTO lsCodigoSociedad
      FROM bas_param_inter
     WHERE pais_cod_pais = psCodigoPais
       AND sist_cod_sist = psCodigoSistema
       AND inte_cod_inte = psCodigoInterfaz
       AND nom_pain = 'codigoSociedad';

    lbAbrirUtlFile := TRUE;

    /* GenerANDo Archivo de Texto (Detalle) */
        OPEN c_interfaz (lsCodigoSociedad);
	        LOOP
	           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                        /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                   GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz,
                                                          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                   lbAbrirUtlFile := FALSE;
                 END IF;

		           IF interfazRecord.COUNT > 0 THEN
		              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
		                  lsLinea :=  interfazRecord(x).codigoSociedad      ||';'||
						  		  	            interfazRecord(x).codigoRegion       	||';'||
						  		  	            interfazRecord(x).codigoZona          ||';'||
									                interfazRecord(x).nombreGerente			  ||';'||
									                interfazRecord(x).codigoCUB           ||';'||
									                interfazRecord(x).fechaSistema		 	  ||';'||
									                interfazRecord(x).causaCambio         ||';'||
                                  interfazRecord(x).rol;

						          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
		              END LOOP;
		           END IF;
	           EXIT WHEN c_interfaz%NOTFOUND;
	        END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
  	   utl_file.fclose(V_HANDLE);
  	   /* Procedimiento final para generar interfaz */
  	   GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
	  END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'INT_PR_ADA_ENVIO_GEREN_REZON: '||ls_sqlerrm);

END INT_PR_ADA_ENVIO_GEREN_REZON;

END INT_PKG_ADAM;
/

