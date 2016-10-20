CREATE OR REPLACE PACKAGE "BPS_PKG_PROCE" IS

/***************************************************************************
Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                    en el proceso de Actualizar Fuentas de Ventas Prevista
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_ELIMI_TABLA_VENTA_PREVI;

/***************************************************************************
Descripcion       : Valida la informacion cargada en las tablas de fuente de
                    ventas prevista segun reglas de negocio
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_VALID_ESTRU_VENTA_PREVI (
          psOidCodPais varchar2,
          psCodPeriIni varchar2,
          psCodPeriFin varchar2,
          psIndTipoFvp varchar2,
          psError OUT VARCHAR2);

/***************************************************************************
Descripcion       : Capia la data correcta de la tabla temporal
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_COPIA_ESTRU_VENTA_PREVI;

/***************************************************************************
Descripcion       : Calcula la carga de FVP Zona
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_CALCU_ESTRU_VENTA_PREVI(
         psIndTipoFvp varchar2);

/***************************************************************************
Descripcion       : Actualiza las Fuente de Venta Prevista del sicc y ssicc
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_ACTUA_ESTRU_VENTA_PREVI(
         psOidPais varchar2,
         psUsuario varchar2,
         psIndTipoFvp varchar2);

/***************************************************************************
Descripcion       : Funcion que valida el UK de la tabla INT_FUENT_VENTA_PREVI_SAP
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
FUNCTION BPS_FN_REPIT_REGIS_VENTA_PREVI(
         psCodALma varchar2,
         psCodPeri varchar2,
         psCodSoci varchar2,
         psCodZona varchar2) RETURN NUMBER;

/***************************************************************************
Descripcion       : Funcion que valida el UK de la tabla INT_FUENT_VENTA_PREVI_SAP
Fecha Creacion    : 18/11/2014
Autor             : Sebastian Guerra
***************************************************************************/
FUNCTION BPS_FN_REPIT_REGIS_VENTA_PREVI(
         psCodPeri varchar2,
         psCodZona varchar2,
         psCodSecc varchar2) RETURN NUMBER;
/***************************************************************************
Descripcion       : Verifica campaña cerrada para un rango de campañas
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
FUNCTION BPS_FN_CAMPA_CERRA_VENTA_PREVI (
          psPais              varchar2,
          psPeriodoInicio     varchar2,
          psPeriodoFin        varchar2,
          psMarca             varchar2,
          psCanal             varchar2) RETURN VARCHAR2;
end BPS_PKG_PROCE;
/
create or replace package body "BPS_PKG_PROCE" is

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=5000;

NOMBRE_PAQUETE VARCHAR2(100):= 'BPS_PKG_PROCE';
PAGINA_ERROR_LOG NUMBER(12);
REGISTRO_ERROR_LOG NUMBER(12);
KEY_ERROR  VARCHAR2(4000);

/***************************************************************************
Descripcion       : Elimina la informacion de las tablas que seran utilizadas
                    en el proceso de Actualizar Fuentas de Ventas Prevista
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_ELIMI_TABLA_VENTA_PREVI
IS
BEGIN

  DELETE FROM BPS_VALID_VENTA_PREVI;
  DELETE FROM BPS_CARGA_VENTA_PREVI;
  DELETE FROM BPS_ERROR_VENTA_PREVI;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR BPS_PR_ELIMI_TABLA_VENTA_PREVI: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Valida la informacion cargada en las tablas de fuente de
                    ventas prevista segun reglas de negocio
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_VALID_ESTRU_VENTA_PREVI (
          psOidCodPais varchar2,
          psCodPeriIni varchar2,
          psCodPeriFin varchar2,
          psIndTipoFvp varchar2,
          psError OUT VARCHAR2)
IS
    CURSOR cursorEstruFVP IS
    SELECT *
    FROM   BPS_CARGA_VENTA_PREVI
    ORDER  BY VAL_FILA;

  TYPE tTablaEstruFVP  IS TABLE OF BPS_CARGA_VENTA_PREVI%ROWTYPE;
  tablaRegistroEstruFVP            tTablaEstruFVP;
  registroEstruFVP                 BPS_CARGA_VENTA_PREVI%ROWTYPE;

  lnNumCodigo                      NUMBER;
  lsCodigo                         VARCHAR2(50);
  lsEncontroErrores                NUMBER;
  lnFilRepetida                    NUMBER;
  lbExisteCodMarca                 BOOLEAN;
  lbExisteCodCanal                 BOOLEAN;
  lnFactor                         NUMBER;
  lnIdCanal                        NUMBER;
  lnIdMarca                        NUMBER;
BEGIN
  PAGINA_ERROR_LOG := 0;
  OPEN cursorEstruFVP;
  LOOP
    FETCH cursorEstruFVP BULK COLLECT INTO tablaRegistroEstruFVP LIMIT W_FILAS;
    PAGINA_ERROR_LOG := PAGINA_ERROR_LOG + 1;
    IF tablaRegistroEstruFVP.COUNT > 0 THEN
      REGISTRO_ERROR_LOG := 0;
      FOR x IN tablaRegistroEstruFVP.FIRST .. tablaRegistroEstruFVP.LAST LOOP
        registroEstruFVP := tablaRegistroEstruFVP(x);
        REGISTRO_ERROR_LOG := REGISTRO_ERROR_LOG + 1;
        KEY_ERROR := registroEstruFVP.VAL_FILA || registroEstruFVP.COD_SOCI ||
                     registroEstruFVP.COD_MARC || registroEstruFVP.COD_ALMA ||
                     registroEstruFVP.COD_CANA || registroEstruFVP.COD_PERI ||
                     registroEstruFVP.COD_REGI || registroEstruFVP.COD_ZONA;

        IF (psIndTipoFvp = '1') THEN
            --00) Verifica Unique constraint de INT_FUENT_VENTA_PREVI_SAP
            lnFilRepetida := BPS_FN_REPIT_REGIS_VENTA_PREVI(registroEstruFVP.cod_peri, registroEstruFVP.cod_zona, registroEstruFVP.cod_secc);
            IF ( (lnFilRepetida  > 0) and (lnFilRepetida <> registroEstruFVP.val_fila) ) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '00', 'Error UK - INT_FUENT_VENTA_PREVI_SAP, la fila ' || lnFilRepetida || ' y ' || registroEstruFVP.Val_Fila || ' almacenan el mismo periodo, zona y seccion');
            END IF;

           --01) El codigo pais no corresponda al pais que se esta procesando
           IF (registroEstruFVP.Cod_Pais IS NOT NULL) THEN
              BEGIN
                   IF (GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(registroEstruFVP.Cod_Pais) <> psOidCodPais) THEN
                      INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '01', 'Código de país errado');
                   END IF;
              EXCEPTION
               WHEN OTHERS THEN
                   INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '01', 'Código de país errado');
               END;
           END IF;

           --02) La campaña no corresponde al rango de campañas seleccionadas
           IF (registroEstruFVP.COD_PERI IS NOT NULL) THEN
               IF (registroEstruFVP.COD_PERI not between psCodPeriIni and psCodPeriFin) THEN
                   INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '02', 'Campaña no corresponde');
               END IF;
           END IF;

           --03) El codigo region no exista en ZON_REGIO
           IF (registroEstruFVP.COD_REGI IS NOT NULL) THEN
               BEGIN
                    select cod_regi into lsCodigo
                    from Zon_regio
                    where cod_regi = registroEstruFVP.COD_REGI;

               EXCEPTION
               WHEN no_data_found THEN
                   INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '03', 'Código de region errado');
               END;
           END IF;

           --04) El codigo zona no existe en ZON_ZONA para el pais, canal y marca, además no pertenece a la region
           IF ( registroEstruFVP.Cod_Zona IS NOT NULL and registroEstruFVP.Cod_Regi IS NOT NULL ) THEN
               BEGIN
                    select z.cod_zona into lsCodigo
                    from Zon_zona z, zon_regio r
                    where z.cod_zona = registroEstruFVP.Cod_Zona
                    and z.pais_oid_pais = psOidCodPais
                    and z.cana_oid_cana = 2001
                    and z.marc_oid_marc = 2003
                    and r.oid_regi = z.zorg_oid_regi
                    and r.cod_regi = registroEstruFVP.Cod_Regi;

               EXCEPTION
               WHEN no_data_found THEN
                   INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '04', 'Código zona no existe o no pertenece a la region');
               END;
           END IF;

           --05) El codigo seccion no existe en ZON_SECCI para el pais, canal y marca, además no pertenece a la zona
           IF ( registroEstruFVP.Cod_Secc IS NOT NULL ) THEN
               BEGIN
                    select s.cod_secc into lsCodigo
                    from zon_secci s, zon_zona z
                    where s.cod_secc = registroEstruFVP.Cod_Secc
                    and z.pais_oid_pais = psOidCodPais
                    and z.cana_oid_cana =2001
                    and z.marc_oid_marc = 2003
                    and z.oid_zona = s.zzon_oid_zona
                    and s.ind_acti = 1
                    and z.cod_zona = registroEstruFVP.Cod_Zona;

               EXCEPTION
               WHEN no_data_found THEN
                   INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '05', 'Código seccion no existe o no pertenece a la zona');
               END;
           END IF;

           --06) El resto de los campos no son numericos.
           BEGIN
              IF (registroEstruFVP.Ven_Neta_Cata IS NOT NULL) THEN
                IF(instr(registroEstruFVP.Ven_Neta_Cata,'E',1,1)>0)THEN
                   lnFactor:=to_number(substr(registroEstruFVP.ven_neta_cata,instr(registroEstruFVP.ven_neta_cata,'E',1,1)+1,length(registroEstruFVP.ven_neta_cata)),'99');
                   lnNumCodigo := to_number(substr(registroEstruFVP.ven_neta_cata,1,instr(registroEstruFVP.ven_neta_cata,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                ELSE
                lnNumCodigo := TO_NUMBER(registroEstruFVP.Ven_Neta_Cata,'9999999999.99');
              END IF;
              END IF;
           EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Venta Neta Catalogo');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Pedi IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.Val_Pedi,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.Val_Pedi,instr(registroEstruFVP.Val_Pedi,'E',1,1)+1,length(registroEstruFVP.Val_Pedi)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.Val_Pedi,1,instr(registroEstruFVP.Val_Pedi,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.Val_pedi,'.0')>0 AND instr(registroEstruFVP.Val_pedi,'.0')+1=length(registroEstruFVP.Val_pedi)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_pedi,1,instr(registroEstruFVP.Val_pedi,'.')-1),'999999999999');
                 ELSE
                     lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Pedi,'999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Pedidos');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Acti_Inic IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.Val_Acti_Inic,instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)+1,length(registroEstruFVP.Val_Acti_Inic)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.Val_Acti_Inic,1,instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.Val_Acti_Inic,'.0')>0 AND instr(registroEstruFVP.Val_Acti_Inic,'.0')+1=length(registroEstruFVP.Val_Acti_Inic)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Acti_Inic,1,instr(registroEstruFVP.Val_Acti_Inic,'.')-1),'9999999999999');
                 ELSE
                    lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Acti_Inic,'9999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Activas Iniciales');
            END;

            BEGIN
              IF (registroEstruFVP.VAL_INGR IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.VAL_INGR,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.VAL_INGR,instr(registroEstruFVP.VAL_INGR,'E',1,1)+1,length(registroEstruFVP.VAL_INGR)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.VAL_INGR,1,instr(registroEstruFVP.VAL_INGR,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.VAL_INGR,'.0')>0 AND instr(registroEstruFVP.VAL_INGR,'.0')+1=length(registroEstruFVP.VAL_INGR)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.VAL_INGR,1,instr(registroEstruFVP.VAL_INGR,'.')-1),'9999999999999');
                 ELSE
                    lnNumCodigo := TO_NUMBER(registroEstruFVP.VAL_INGR,'9999999999999');
                 END IF;
              END IF;
           EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Ingresos');
            END;

            BEGIN
              IF (registroEstruFVP.VAL_REIN IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.VAL_REIN,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.VAL_REIN,instr(registroEstruFVP.VAL_REIN,'E',1,1)+1,length(registroEstruFVP.VAL_REIN)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.VAL_REIN,1,instr(registroEstruFVP.VAL_REIN,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.VAL_REIN,'.0')>0 AND instr(registroEstruFVP.VAL_REIN,'.0')+1=length(registroEstruFVP.VAL_REIN)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.VAL_REIN,1,instr(registroEstruFVP.VAL_REIN,'.')-1),'9999999999999');
                 ELSE
                    lnNumCodigo := TO_NUMBER(registroEstruFVP.VAL_REIN,'9999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Reingresos');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Egre IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.Val_Egre,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.Val_Egre,instr(registroEstruFVP.Val_Egre,'E',1,1)+1,length(registroEstruFVP.Val_Egre)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.Val_Egre,1,instr(registroEstruFVP.Val_Egre,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.Val_Egre,'.0')>0 AND instr(registroEstruFVP.Val_Egre,'.0')+1=length(registroEstruFVP.Val_Egre)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Egre,1,instr(registroEstruFVP.Val_Egre,'.')-1),'9999999999999');
                 ELSE
                     lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Egre,'9999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Egresos');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Rent_Segp IS NOT NULL) THEN
                lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Segp,'999.99');
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Retencion 2do Pedido');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Rent_Terp IS NOT NULL) THEN
                lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Terp,'999.99');
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Retencion 3do Pedido');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Rent_Cuap IS NOT NULL) THEN
                lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Cuap,'999.99');
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Retencion 4do Pedido');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Unid IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.Val_Unid,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.Val_Unid,instr(registroEstruFVP.Val_Unid,'E',1,1)+1,length(registroEstruFVP.Val_Unid)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.Val_Unid,1,instr(registroEstruFVP.Val_Unid,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.Val_Unid,'.0')>0 AND instr(registroEstruFVP.Val_Unid,'.0')+1=length(registroEstruFVP.Val_Unid)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Unid,1,instr(registroEstruFVP.Val_Unid,'.')-1),'999999999999');
                 ELSE
                      lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Unid,'999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Unidades');
            END;

            BEGIN
              IF (registroEstruFVP.Val_Posi_Egre_Cam_Ant IS NOT NULL) THEN
                 IF (instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'E',1,1)>0) THEN
                    lnFactor:=to_number(substr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'E',1,1)+1,length(registroEstruFVP.Val_Posi_Egre_Cam_Ant)),'99');
                    lnNumCodigo := to_number(substr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,1,instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
                 ELSIF (instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'.0')>0 AND instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'.0')+1=length(registroEstruFVP.Val_Posi_Egre_Cam_Ant)) THEN
                    lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,1,instr(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'.')-1),'999999999999');
                 ELSE
                      lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Posi_Egre_Cam_Ant,'999999999999');
                 END IF;
              END IF;
            EXCEPTION
              WHEN VALUE_ERROR THEN
                INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '06', 'Dato no es numérico - Posible Egreso Campaña Anterior');
            END;

            --07) Dato obligatorio en nulo
            IF (registroEstruFVP.Cod_Pais IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Codigo pais');
            END IF;
            IF (registroEstruFVP.COD_PERI IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Campaña');
            END IF;
            IF (registroEstruFVP.Cod_Regi IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Codigo region');
            END IF;
            IF (registroEstruFVP.Cod_Zona IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Codigo zona');
            END IF;
            IF (registroEstruFVP.Ven_Neta_Cata IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Venta Neta Catalogo');
            END IF;
            IF (registroEstruFVP.Val_Pedi IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Pedidos');
            END IF;
            IF (registroEstruFVP.Val_Acti_Inic IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Activas Iniciales');
            END IF;
            IF (registroEstruFVP.VAL_INGR IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Ingresos');
            END IF;
            IF (registroEstruFVP.VAL_REIN IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Reingresos');
            END IF;
            IF (registroEstruFVP.Val_Egre IS NULL) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Dato oblligatorio en nulo - Egresos');
            END IF;           

        ELSE
        lnIdCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(registroEstruFVP.COD_CANA);
        lnIdMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(registroEstruFVP.COD_MARC);

        --00) Verifica Unique constraint de INT_FUENT_VENTA_PREVI_SAP
        lnFilRepetida := BPS_FN_REPIT_REGIS_VENTA_PREVI(registroEstruFVP.Cod_Alma, registroEstruFVP.cod_peri, registroEstruFVP.cod_soci, registroEstruFVP.cod_zona);
        IF ( (lnFilRepetida  > 0) and (lnFilRepetida <> registroEstruFVP.val_fila) ) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '00', 'Error UK - INT_FUENT_VENTA_PREVI_SAP, la fila ' || lnFilRepetida || ' y ' || registroEstruFVP.Val_Fila || ' almacenan el mismo codigo almacen, periodo, sociedad y zona');
        END IF;

       --01) El codigo pais no corresponda al pais que se esta procesando
       IF (registroEstruFVP.Cod_Pais IS NOT NULL) THEN
          BEGIN
               IF (GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(registroEstruFVP.Cod_Pais) <> psOidCodPais) THEN
                  INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '01', 'Código de país errado');
               END IF;
          EXCEPTION
           WHEN OTHERS THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '01', 'Código de país errado');
           END;
       END IF;

       --02) El codigo de sociedad no exista en SEG_SOCIE para el pais.
       IF (registroEstruFVP.Cod_Soci IS NOT NULL) THEN
           BEGIN
              select cod_soci into lsCodigo
              from Seg_Socie
              where pais_oid_pais = psOidCodPais
              and cod_soci = registroEstruFVP.Cod_Soci;

           EXCEPTION
           WHEN no_data_found THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '02', 'Código de sociedad errado');
           END;
       END IF;

       --03) El codigo marca no exista en SEG_MARCA.
       IF (registroEstruFVP.COD_MARC IS NOT NULL) THEN
           BEGIN
                lbExisteCodMarca := true;
                select cod_marc into lsCodigo
                  from Seg_Marca
                 where cod_marc = registroEstruFVP.COD_MARC;

           EXCEPTION
           WHEN no_data_found THEN
               lbExisteCodMarca := false;
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '03', 'Código de marca errado');
           END;
       END IF;

       --04) El codigo de almacen no exista en BEL_ALMAC para el pais
       IF (registroEstruFVP.Cod_Alma IS NOT NULL) THEN
           BEGIN
                select cod_alma into lsCodigo
                  from bel_almac
                 where pais_oid_pais = psOidCodPais
                   and cod_alma = registroEstruFVP.Cod_Alma;

           EXCEPTION
           WHEN no_data_found THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '04', 'Código de almacén errado');
           END;
       END IF;

       --05) El codigo de canal no exista en SEG_CANAL.
       IF (registroEstruFVP.COD_CANA IS NOT NULL) THEN
           BEGIN
                lbExisteCodCanal:=true;
                select cod_cana into lsCodigo
                  from seg_canal
                 where cod_cana = registroEstruFVP.COD_CANA;

           EXCEPTION
           WHEN no_data_found THEN
               lbExisteCodCanal:=false;
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '05', 'Código de canal errado');
           END;
       END IF;

       --06) La campaña no corresponde al rango de campañas seleccionadas
       IF (registroEstruFVP.COD_PERI IS NOT NULL) THEN
           IF (registroEstruFVP.COD_PERI not between psCodPeriIni and psCodPeriFin) THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.val_fila, '06', 'Campaña no corresponde');
           END IF;
       END IF;

       --07) El codigo region no existe en ZON_REGIO para el pais, canal y marca.
       IF ((registroEstruFVP.Cod_Regi IS NOT NULL) and (registroEstruFVP.COD_CANA IS NOT NULL) and (registroEstruFVP.COD_MARC IS NOT NULL)
       and (lbExisteCodCanal) and (lbExisteCodMarca)) THEN
           BEGIN
              select cod_regi into lsCodigo
              from zon_regio
              where cod_regi = registroEstruFVP.Cod_Regi
              and pais_oid_pais = psOidCodPais
              and cana_oid_cana = lnIdCanal
              and marc_oid_marc = lnIdMarca;

           EXCEPTION
           WHEN no_data_found THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '07', 'Código región no existe');
           END;
       END IF;

       --08) El codigo zona no existe en ZON_ZONA para el pais, canal y marca, además no pertenece a la region
       IF ((registroEstruFVP.Cod_Zona IS NOT NULL) and (registroEstruFVP.Cod_Regi IS NOT NULL) and (registroEstruFVP.COD_CANA IS NOT NULL) and (registroEstruFVP.COD_MARC IS NOT NULL)
          and (lbExisteCodCanal) and (lbExisteCodMarca)) THEN
           BEGIN
                select z.cod_zona into lsCodigo
                from Zon_zona z, zon_regio r
                where z.cod_zona = registroEstruFVP.Cod_Zona
                and z.pais_oid_pais = psOidCodPais
                and z.cana_oid_cana = lnIdCanal
                and z.marc_oid_marc = lnIdMarca
                and r.oid_regi = z.zorg_oid_regi
                and r.cod_regi = registroEstruFVP.Cod_Regi;

           EXCEPTION
           WHEN no_data_found THEN
               INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '08', 'Código zona no existe o no pertenece a la region');
           END;
       END IF;

       --09) El resto de los campos no son numericos.
       BEGIN
          IF (registroEstruFVP.Ven_Neta_Cata IS NOT NULL) THEN
            IF(instr(registroEstruFVP.Ven_Neta_Cata,'E',1,1)>0)THEN
               lnFactor:=to_number(substr(registroEstruFVP.ven_neta_cata,instr(registroEstruFVP.ven_neta_cata,'E',1,1)+1,length(registroEstruFVP.ven_neta_cata)),'99');
               lnNumCodigo := to_number(substr(registroEstruFVP.ven_neta_cata,1,instr(registroEstruFVP.ven_neta_cata,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
            ELSE
            lnNumCodigo := TO_NUMBER(registroEstruFVP.Ven_Neta_Cata,'9999999999.99');
          END IF;
          END IF;
       EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Venta Neta Catalogo');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Pedi IS NOT NULL) THEN
             IF (instr(registroEstruFVP.Val_Pedi,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Pedi,instr(registroEstruFVP.Val_Pedi,'E',1,1)+1,length(registroEstruFVP.Val_Pedi)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Pedi,1,instr(registroEstruFVP.Val_Pedi,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_pedi,'.0')>0 AND instr(registroEstruFVP.Val_pedi,'.0')+1=length(registroEstruFVP.Val_pedi)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_pedi,1,instr(registroEstruFVP.Val_pedi,'.')-1),'999999999999');
             ELSE
                 lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Pedi,'999999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Pedidos');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Acti_Inic IS NOT NULL) THEN
             IF (instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Acti_Inic,instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)+1,length(registroEstruFVP.Val_Acti_Inic)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Acti_Inic,1,instr(registroEstruFVP.Val_Acti_Inic,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Acti_Inic,'.0')>0 AND instr(registroEstruFVP.Val_Acti_Inic,'.0')+1=length(registroEstruFVP.Val_Acti_Inic)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Acti_Inic,1,instr(registroEstruFVP.Val_Acti_Inic,'.')-1),'9999999999999');
             ELSE
                lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Acti_Inic,'9999999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Activas Iniciales');
        END;

        BEGIN
          IF (registroEstruFVP.VAL_INGR IS NOT NULL) THEN
             IF (instr(registroEstruFVP.VAL_INGR,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.VAL_INGR,instr(registroEstruFVP.VAL_INGR,'E',1,1)+1,length(registroEstruFVP.VAL_INGR)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.VAL_INGR,1,instr(registroEstruFVP.VAL_INGR,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.VAL_INGR,'.0')>0 AND instr(registroEstruFVP.VAL_INGR,'.0')+1=length(registroEstruFVP.VAL_INGR)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.VAL_INGR,1,instr(registroEstruFVP.VAL_INGR,'.')-1),'9999999999999');
             ELSE
                lnNumCodigo := TO_NUMBER(registroEstruFVP.VAL_INGR,'9999999999999');
             END IF;
          END IF;
       EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Ingresos');
        END;

        BEGIN
          IF (registroEstruFVP.VAL_REIN IS NOT NULL) THEN
             IF (instr(registroEstruFVP.VAL_REIN,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.VAL_REIN,instr(registroEstruFVP.VAL_REIN,'E',1,1)+1,length(registroEstruFVP.VAL_REIN)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.VAL_REIN,1,instr(registroEstruFVP.VAL_REIN,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.VAL_REIN,'.0')>0 AND instr(registroEstruFVP.VAL_REIN,'.0')+1=length(registroEstruFVP.VAL_REIN)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.VAL_REIN,1,instr(registroEstruFVP.VAL_REIN,'.')-1),'9999999999999');
             ELSE
                lnNumCodigo := TO_NUMBER(registroEstruFVP.VAL_REIN,'9999999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Reingresos');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Egre IS NOT NULL) THEN
             IF (instr(registroEstruFVP.Val_Egre,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Egre,instr(registroEstruFVP.Val_Egre,'E',1,1)+1,length(registroEstruFVP.Val_Egre)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Egre,1,instr(registroEstruFVP.Val_Egre,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Egre,'.0')>0 AND instr(registroEstruFVP.Val_Egre,'.0')+1=length(registroEstruFVP.Val_Egre)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Egre,1,instr(registroEstruFVP.Val_Egre,'.')-1),'9999999999999');
             ELSE
                 lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Egre,'9999999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Egresos');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Cons_Priv IS NOT NULL) THEN
            IF (instr(registroEstruFVP.Val_Cons_Priv,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Cons_Priv,instr(registroEstruFVP.Val_Cons_Priv,'E',1,1)+1,length(registroEstruFVP.Val_Cons_Priv)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Cons_Priv,1,instr(registroEstruFVP.Val_Cons_Priv,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Cons_Priv,'.0')>0 AND instr(registroEstruFVP.Val_Cons_Priv,'.0')+1=length(registroEstruFVP.Val_Cons_Priv)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Cons_Priv,1,instr(registroEstruFVP.Val_Cons_Priv,'.')-1),'9999999999');
             ELSE
                 lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Cons_Priv,'9999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Consultoras Privilege');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Clie_Priv IS NOT NULL) THEN
            IF (instr(registroEstruFVP.Val_Clie_Priv,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Clie_Priv,instr(registroEstruFVP.Val_Clie_Priv,'E',1,1)+1,length(registroEstruFVP.Val_Clie_Priv)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Clie_Priv,1,instr(registroEstruFVP.Val_Clie_Priv,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Clie_Priv,'.0')>0 AND instr(registroEstruFVP.Val_Clie_Priv,'.0')+1=length(registroEstruFVP.Val_Clie_Priv)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Clie_Priv,1,instr(registroEstruFVP.Val_Clie_Priv,'.')-1),'9999999999');
             ELSE
                 lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Clie_Priv,'9999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Clientes Privilege');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Tran_Priv IS NOT NULL) THEN
           IF (instr(registroEstruFVP.Val_Tran_Priv,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Tran_Priv,instr(registroEstruFVP.Val_Tran_Priv,'E',1,1)+1,length(registroEstruFVP.Val_Tran_Priv)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Tran_Priv,1,instr(registroEstruFVP.Val_Tran_Priv,'E',1,1)-1),'9.9999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Tran_Priv,'.0')>0 AND instr(registroEstruFVP.Val_Tran_Priv,'.0')+1=length(registroEstruFVP.Val_Tran_Priv)) THEN
              lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Tran_Priv,1,instr(registroEstruFVP.Val_Tran_Priv,'.')-1),'9999999999');
           ELSE
               lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Tran_Priv,'9999999999');
           END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Transacciones Privilege');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Rent_Segp IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Segp,'999.99');
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Retencion 2do Pedido');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Rent_Terp IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Terp,'999.99');
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Retencion 3do Pedido');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Rent_Cuap IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Rent_Cuap,'999.99');
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Retencion 4do Pedido');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Unid IS NOT NULL) THEN
             IF (instr(registroEstruFVP.Val_Unid,'E',1,1)>0) THEN
                lnFactor:=to_number(substr(registroEstruFVP.Val_Unid,instr(registroEstruFVP.Val_Unid,'E',1,1)+1,length(registroEstruFVP.Val_Unid)),'99');
                lnNumCodigo := to_number(substr(registroEstruFVP.Val_Unid,1,instr(registroEstruFVP.Val_Unid,'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(registroEstruFVP.Val_Unid,'.0')>0 AND instr(registroEstruFVP.Val_Unid,'.0')+1=length(registroEstruFVP.Val_Unid)) THEN
                lnNumCodigo := TO_NUMBER(substr(registroEstruFVP.Val_Unid,1,instr(registroEstruFVP.Val_Unid,'.')-1),'999999999999');
             ELSE
                  lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Unid,'999999999999');
             END IF;
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - Unidades');
        END;

        BEGIN
          IF (registroEstruFVP.Val_Porc_Rent IS NOT NULL) THEN
            lnNumCodigo := TO_NUMBER(registroEstruFVP.Val_Porc_Rent,'999.99');
          END IF;
        EXCEPTION
          WHEN VALUE_ERROR THEN
            INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '09', 'Dato no es numérico - % Retencion Posibles Egresos');
        END;

        --10) Dato obligatorio en nulo
        IF (registroEstruFVP.Cod_Pais IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo pais');
        END IF;
        IF (registroEstruFVP.Cod_Soci IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo sociedad');
        END IF;
        IF (registroEstruFVP.COD_MARC IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo marca');
        END IF;
        IF (registroEstruFVP.Cod_Alma IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo almacen');
        END IF;
        IF (registroEstruFVP.COD_CANA IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo canal');
        END IF;
        IF (registroEstruFVP.COD_PERI IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Campaña');
        END IF;
        IF (registroEstruFVP.Cod_Regi IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo region');
        END IF;
        IF (registroEstruFVP.Cod_Zona IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Codigo zona');
        END IF;
        IF (registroEstruFVP.Ven_Neta_Cata IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Venta Neta Catalogo');
        END IF;
        IF (registroEstruFVP.Val_Pedi IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Pedidos');
        END IF;
        IF (registroEstruFVP.Val_Acti_Inic IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Activas Iniciales');
        END IF;
        IF (registroEstruFVP.VAL_INGR IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Ingresos');
        END IF;
        IF (registroEstruFVP.VAL_REIN IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Reingresos');
        END IF;
        IF (registroEstruFVP.Val_Egre IS NULL) THEN
           INSERT INTO BPS_ERROR_VENTA_PREVI(VAL_FILA,COD_ERRO,DES_ERRO) VALUES(registroEstruFVP.Val_Fila, '10', 'Dato oblligatorio en nulo - Egresos');
        END IF;
        END IF;

     END LOOP;
    END IF;

    EXIT WHEN cursorEstruFVP%NOTFOUND;
  END LOOP;
  CLOSE cursorEstruFVP;

  SELECT count(1)
  INTO  lsEncontroErrores
  FROM  BPS_ERROR_VENTA_PREVI;

  IF (lsEncontroErrores = 0) THEN
     BPS_PKG_PROCE.BPS_PR_COPIA_ESTRU_VENTA_PREVI;
  END IF;

  psError := lsEncontroErrores;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    GEN_PKG_GENER.GEN_PR_INSER_ERROR_BDATO_SSICC(NOMBRE_PAQUETE, 'BPS_PR_VALID_ESTRU_VENTA_PREVI',
          'cursorEstruFVP', PAGINA_ERROR_LOG, REGISTRO_ERROR_LOG, KEY_ERROR, ls_sqlerrm);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR BPS_PR_VALID_ESTRU_VENTA_PREVI: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Capia la data correcta de la tabla temporal
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_COPIA_ESTRU_VENTA_PREVI
IS
      CURSOR curEstructuraFVP
      IS
        SELECT  VAL_FILA        as  val_fila      ,
                COD_PAIS     as  cod_pais   ,
                COD_SOCI     as  cod_soci   ,
                COD_MARC     as  COD_MARC   ,
                COD_ALMA     as  cod_alma   ,
                COD_CANA     as  COD_CANA   ,
                COD_PERI     as  COD_PERI   ,
                COD_REGI     as  cod_regi   ,
                COD_ZONA     as  cod_zona   ,
                VEN_NETA_CATA   as  ven_neta_cata ,
                VAL_PEDI     as  val_pedi   ,
                VAL_ACTI_INIC   as  val_acti_inic ,
                VAL_INGR     as  VAL_INGR   ,
                VAL_REIN     as  VAL_REIN   ,
                VAL_EGRE     as  val_egre   ,
                VAL_CONS_PRIV   as  val_cons_priv ,
                VAL_CLIE_PRIV   as  val_clie_priv ,
                VAL_TRAN_PRIV   as  val_tran_priv ,
                VAL_RENT_SEGP   as  val_rent_segp ,
                VAL_RENT_TERP   as  val_rent_terp ,
                VAL_RENT_CUAP   as  val_rent_cuap ,
                VAL_UNID     as  val_unid   ,
                VAL_PORC_RENT   as  val_porc_rent ,
                COD_SECC   as  cod_secc ,
                VAL_POSI_EGRE_CAM_ANT   as  Val_Posi_Egre_Cam_Ant
        FROM BPS_CARGA_VENTA_PREVI;

 TYPE t_val_fila      is table of BPS_CARGA_VENTA_PREVI.val_fila%type;
 TYPE t_cod_pais   is table of BPS_CARGA_VENTA_PREVI.COD_PAIS%type;
 TYPE t_cod_soci   is table of BPS_CARGA_VENTA_PREVI.Cod_Soci%type;
 TYPE t_COD_MARC   is table of BPS_CARGA_VENTA_PREVI.COD_MARC%TYPE;
 TYPE t_cod_alma   is table of BPS_CARGA_VENTA_PREVI.Cod_Alma%TYPE;
 TYPE t_COD_CANA   is table of BPS_CARGA_VENTA_PREVI.COD_CANA%TYPE;
 TYPE t_COD_PERI   is table of BPS_CARGA_VENTA_PREVI.COD_PERI%TYPE;
 TYPE t_cod_regi   is table of BPS_CARGA_VENTA_PREVI.Cod_Regi%TYPE;
 TYPE t_cod_zona   is table of BPS_CARGA_VENTA_PREVI.Cod_Zona%TYPE;
 TYPE t_ven_neta_cata is table of BPS_CARGA_VENTA_PREVI.Ven_Neta_Cata%TYPE;
 TYPE t_val_pedi   is table of BPS_CARGA_VENTA_PREVI.Val_Pedi%TYPE;
 TYPE t_val_acti_inic is table of BPS_CARGA_VENTA_PREVI.Val_Acti_Inic%TYPE;
 TYPE t_VAL_INGR   is table of BPS_CARGA_VENTA_PREVI.VAL_INGR%TYPE;
 TYPE t_VAL_REIN   is table of BPS_CARGA_VENTA_PREVI.VAL_REIN%TYPE;
 TYPE t_val_egre   is table of BPS_CARGA_VENTA_PREVI.Val_Egre%TYPE;
 TYPE t_val_cons_priv is table of BPS_CARGA_VENTA_PREVI.Val_Cons_Priv%TYPE;
 TYPE t_val_clie_priv is table of BPS_CARGA_VENTA_PREVI.Val_Clie_Priv%TYPE;
 TYPE t_val_tran_priv is table of BPS_CARGA_VENTA_PREVI.Val_Tran_Priv%TYPE;
 TYPE t_val_rent_segp is table of BPS_CARGA_VENTA_PREVI.Val_Rent_Segp%TYPE;
 TYPE t_val_rent_terp is table of BPS_CARGA_VENTA_PREVI.Val_Rent_Terp%TYPE;
 TYPE t_val_rent_cuap is table of BPS_CARGA_VENTA_PREVI.Val_Rent_Cuap%TYPE;
 TYPE t_val_unid   is table of BPS_CARGA_VENTA_PREVI.Val_Unid%TYPE;
 TYPE t_val_porc_rent is table of BPS_CARGA_VENTA_PREVI.Val_Porc_Rent%TYPE;
 TYPE t_cod_secc is table of BPS_CARGA_VENTA_PREVI.Cod_secc%TYPE;
 TYPE t_val_posi_egre_cam_ant is table of BPS_CARGA_VENTA_PREVI.Val_Posi_Egre_Cam_Ant%TYPE;

v_val_fila        t_val_fila       ;
v_cod_pais     t_cod_pais    ;
v_cod_soci     t_cod_soci    ;
v_COD_MARC     t_COD_MARC    ;
v_cod_alma     t_cod_alma    ;
v_COD_CANA     t_COD_CANA    ;
v_COD_PERI     t_COD_PERI    ;
v_cod_regi     t_cod_regi    ;
v_cod_zona     t_cod_zona    ;
v_ven_neta_cata   t_ven_neta_cata  ;
v_val_pedi     t_val_pedi    ;
v_val_acti_inic   t_val_acti_inic  ;
v_VAL_INGR     t_VAL_INGR    ;
v_VAL_REIN     t_VAL_REIN    ;
v_val_egre     t_val_egre    ;
v_val_cons_priv   t_val_cons_priv  ;
v_val_clie_priv   t_val_clie_priv  ;
v_val_tran_priv   t_val_tran_priv  ;
v_val_rent_segp   t_val_rent_segp  ;
v_val_rent_terp   t_val_rent_terp  ;
v_val_rent_cuap   t_val_rent_cuap  ;
v_val_unid     t_val_unid    ;
v_val_porc_rent   t_val_porc_rent  ;
v_cod_secc   t_cod_secc  ;
v_val_posi_egre_cam_ant   t_val_posi_egre_cam_ant  ;

    i    BINARY_INTEGER := 0;
    v_row_count_ins      NUMBER := 0;
    lnVen_neta_cata NUMBER;
    lnVal_pedi      NUMBER;
    lnVal_acti_inic NUMBER;
    lnVAL_INGR      NUMBER;
    lnVAL_REIN      NUMBER;
    lnVal_egre      NUMBER;
    lnVal_cons_priv NUMBER;
    lnVal_clie_priv NUMBER;
    lnVal_tran_priv NUMBER;
    lnVal_unid      NUMBER;
    lnVal_posi_egre_cam_ant      NUMBER;
    lnFactor        NUMBER;

BEGIN

  delete from BPS_VALID_VENTA_PREVI;
  PAGINA_ERROR_LOG := 0;
  OPEN curEstructuraFVP;
  LOOP
    -- Bulk collect data into memory table - X rows at a time
    FETCH curEstructuraFVP BULK COLLECT INTO
                            v_val_fila      ,
                            v_cod_pais   ,
                            v_cod_soci   ,
                            v_COD_MARC   ,
                            v_cod_alma   ,
                            v_COD_CANA   ,
                            v_COD_PERI   ,
                            v_cod_regi   ,
                            v_cod_zona   ,
                            v_ven_neta_cata ,
                            v_val_pedi   ,
                            v_val_acti_inic ,
                            v_VAL_INGR   ,
                            v_VAL_REIN   ,
                            v_val_egre   ,
                            v_val_cons_priv ,
                            v_val_clie_priv ,
                            v_val_tran_priv ,
                            v_val_rent_segp ,
                            v_val_rent_terp ,
                            v_val_rent_cuap ,
                            v_val_unid   ,
                            v_val_porc_rent ,
                            v_cod_secc   ,
                            v_val_posi_egre_cam_ant
                            LIMIT W_FILAS;

    EXIT WHEN v_row_count_ins = curEstructuraFVP%ROWCOUNT;
    v_row_count_ins := curEstructuraFVP%ROWCOUNT;
    PAGINA_ERROR_LOG := PAGINA_ERROR_LOG + 1;
    REGISTRO_ERROR_LOG := 0;
    -- Bulk bind of data in memory table...
    FOR i IN v_val_fila.FIRST .. v_val_fila.LAST loop
        REGISTRO_ERROR_LOG := REGISTRO_ERROR_LOG + 1;
        KEY_ERROR := v_val_fila(i) || v_cod_soci(i) ||
                     v_COD_MARC(i) || v_cod_alma(i) ||
                     v_COD_CANA(i) || v_COD_PERI(i) ||
                     v_cod_regi(i) || v_cod_zona(i);

            IF(instr(v_Ven_Neta_Cata(i),'E',1,1)>0)THEN
               lnFactor:=to_number(substr(v_ven_neta_cata(i),instr(v_ven_neta_cata(i),'E',1,1)+1,length(v_ven_neta_cata(i))),'99');
               lnVen_neta_cata := to_number(substr(v_ven_neta_cata(i),1,instr(v_ven_neta_cata(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
            ELSE
                lnVen_neta_cata := TO_NUMBER(v_Ven_Neta_Cata(i),'9999999999.99');
            END IF;

          IF (v_Val_Pedi(i) IS NOT NULL) THEN
             IF (instr(v_Val_Pedi(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Pedi(i),instr(v_Val_Pedi(i),'E',1,1)+1,length(v_Val_Pedi(i))),'99');
                lnVal_pedi := to_number(substr(v_Val_Pedi(i),1,instr(v_Val_Pedi(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_pedi(i),'.0')>0 AND instr(v_Val_pedi(i),'.0')+1=length(v_Val_pedi(i))) THEN
                lnVal_pedi := TO_NUMBER(substr(v_Val_pedi(i),1,instr(v_Val_pedi(i),'.')-1),'999999999999');
             ELSE
                 lnVal_pedi := TO_NUMBER(v_Val_Pedi(i),'999999999999');
             END IF;
          END IF;

          IF (v_Val_Acti_Inic(i) IS NOT NULL) THEN
             IF (instr(v_Val_Acti_Inic(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Acti_Inic(i),instr(v_Val_Acti_Inic(i),'E',1,1)+1,length(v_Val_Acti_Inic(i))),'99');
                lnVal_acti_inic := to_number(substr(v_Val_Acti_Inic(i),1,instr(v_Val_Acti_Inic(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Acti_Inic(i),'.0')>0 AND instr(v_Val_Acti_Inic(i),'.0')+1=length(v_Val_Acti_Inic(i))) THEN
                lnVal_acti_inic := TO_NUMBER(substr(v_Val_Acti_Inic(i),1,instr(v_Val_Acti_Inic(i),'.')-1),'9999999999999');
             ELSE
                lnVal_acti_inic := TO_NUMBER(v_Val_Acti_Inic(i),'9999999999999');
             END IF;
          END IF;

          IF (v_VAL_INGR(i) IS NOT NULL) THEN
             IF (instr(v_VAL_INGR(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_VAL_INGR(i),instr(v_VAL_INGR(i),'E',1,1)+1,length(v_VAL_INGR(i))),'99');
                lnVAL_INGR := to_number(substr(v_VAL_INGR(i),1,instr(v_VAL_INGR(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_VAL_INGR(i),'.0')>0 AND instr(v_VAL_INGR(i),'.0')+1=length(v_VAL_INGR(i))) THEN
                lnVAL_INGR := TO_NUMBER(substr(v_VAL_INGR(i),1,instr(v_VAL_INGR(i),'.')-1),'9999999999999');
             ELSE
                lnVAL_INGR := TO_NUMBER(v_VAL_INGR(i),'9999999999999');
             END IF;
          END IF;

          IF (v_VAL_REIN(i) IS NOT NULL) THEN
             IF (instr(v_VAL_REIN(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_VAL_REIN(i),instr(v_VAL_REIN(i),'E',1,1)+1,length(v_VAL_REIN(i))),'99');
                lnVAL_REIN := to_number(substr(v_VAL_REIN(i),1,instr(v_VAL_REIN(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_VAL_REIN(i),'.0')>0 AND instr(v_VAL_REIN(i),'.0')+1=length(v_VAL_REIN(i))) THEN
                lnVAL_REIN := TO_NUMBER(substr(v_VAL_REIN(i),1,instr(v_VAL_REIN(i),'.')-1),'9999999999999');
             ELSE
                lnVAL_REIN := TO_NUMBER(v_VAL_REIN(i),'9999999999999');
             END IF;
          END IF;

          IF (v_Val_Egre(i) IS NOT NULL) THEN
             IF (instr(v_Val_Egre(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Egre(i),instr(v_Val_Egre(i),'E',1,1)+1,length(v_Val_Egre(i))),'99');
                lnVal_egre := to_number(substr(v_Val_Egre(i),1,instr(v_Val_Egre(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Egre(i),'.0')>0 AND instr(v_Val_Egre(i),'.0')+1=length(v_Val_Egre(i))) THEN
                lnVal_egre := TO_NUMBER(substr(v_Val_Egre(i),1,instr(v_Val_Egre(i),'.')-1),'9999999999999');
             ELSE
                 lnVal_egre := TO_NUMBER(v_Val_Egre(i),'9999999999999');
             END IF;
          END IF;

          IF (v_Val_Cons_Priv(i) IS NOT NULL) THEN
            IF (instr(v_Val_Cons_Priv(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Cons_Priv(i),instr(v_Val_Cons_Priv(i),'E',1,1)+1,length(v_Val_Cons_Priv(i))),'99');
                lnVal_cons_priv := to_number(substr(v_Val_Cons_Priv(i),1,instr(v_Val_Cons_Priv(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Cons_Priv(i),'.0')>0 AND instr(v_Val_Cons_Priv(i),'.0')+1=length(v_Val_Cons_Priv(i))) THEN
                lnVal_cons_priv := TO_NUMBER(substr(v_Val_Cons_Priv(i),1,instr(v_Val_Cons_Priv(i),'.')-1),'9999999999');
             ELSE
                 lnVal_cons_priv := TO_NUMBER(v_Val_Cons_Priv(i),'9999999999');
             END IF;
          END IF;

          IF (v_Val_Clie_Priv(i) IS NOT NULL) THEN
            IF (instr(v_Val_Clie_Priv(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Clie_Priv(i),instr(v_Val_Clie_Priv(i),'E',1,1)+1,length(v_Val_Clie_Priv(i))),'99');
                lnVal_clie_priv := to_number(substr(v_Val_Clie_Priv(i),1,instr(v_Val_Clie_Priv(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Clie_Priv(i),'.0')>0 AND instr(v_Val_Clie_Priv(i),'.0')+1=length(v_Val_Clie_Priv(i))) THEN
                lnVal_clie_priv := TO_NUMBER(substr(v_Val_Clie_Priv(i),1,instr(v_Val_Clie_Priv(i),'.')-1),'9999999999');
             ELSE
                 lnVal_clie_priv := TO_NUMBER(v_Val_Clie_Priv(i),'9999999999');
             END IF;
          END IF;

          IF (v_Val_Tran_Priv(i) IS NOT NULL) THEN
           IF (instr(v_Val_Tran_Priv(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Tran_Priv(i),instr(v_Val_Tran_Priv(i),'E',1,1)+1,length(v_Val_Tran_Priv(i))),'99');
                lnVal_tran_priv := to_number(substr(v_Val_Tran_Priv(i),1,instr(v_Val_Tran_Priv(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Tran_Priv(i),'.0')>0 AND instr(v_Val_Tran_Priv(i),'.0')+1=length(v_Val_Tran_Priv(i))) THEN
                lnVal_tran_priv := TO_NUMBER(substr(v_Val_Tran_Priv(i),1,instr(v_Val_Tran_Priv(i),'.')-1),'9999999999');
           ELSE
               lnVal_tran_priv := TO_NUMBER(v_Val_Tran_Priv(i),'9999999999');
           END IF;
          END IF;

          IF (v_Val_Unid(i) IS NOT NULL) THEN
             IF (instr(v_Val_Unid(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_Val_Unid(i),instr(v_Val_Unid(i),'E',1,1)+1,length(v_Val_Unid(i))),'99');
                lnVal_unid := to_number(substr(v_Val_Unid(i),1,instr(v_Val_Unid(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_Val_Unid(i),'.0')>0 AND instr(v_Val_Unid(i),'.0')+1=length(v_Val_Unid(i))) THEN
                lnVal_unid := TO_NUMBER(substr(v_Val_Unid(i),1,instr(v_Val_Unid(i),'.')-1),'999999999999');
             ELSE
                  lnVal_unid := TO_NUMBER(v_Val_Unid(i),'999999999999');
             END IF;
          END IF;

          IF (v_val_posi_egre_cam_ant(i) IS NOT NULL) THEN
             IF (instr(v_val_posi_egre_cam_ant(i),'E',1,1)>0) THEN
                lnFactor:=to_number(substr(v_val_posi_egre_cam_ant(i),instr(v_val_posi_egre_cam_ant(i),'E',1,1)+1,length(v_val_posi_egre_cam_ant(i))),'99');
                lnVal_posi_egre_cam_ant := to_number(substr(v_val_posi_egre_cam_ant(i),1,instr(v_val_posi_egre_cam_ant(i),'E',1,1)-1),'9.999999999999999')*POWER(10,lnFactor);
             ELSIF (instr(v_val_posi_egre_cam_ant(i),'.0')>0 AND instr(v_val_posi_egre_cam_ant(i),'.0')+1=length(v_val_posi_egre_cam_ant(i))) THEN
                lnVal_posi_egre_cam_ant := TO_NUMBER(substr(v_val_posi_egre_cam_ant(i),1,instr(v_val_posi_egre_cam_ant(i),'.')-1),'999999999999');
             ELSE
                  lnVal_posi_egre_cam_ant := TO_NUMBER(v_val_posi_egre_cam_ant(i),'999999999999');
             END IF;
          ELSE
             lnVal_posi_egre_cam_ant := TO_NUMBER(v_val_porc_rent(i),'999999999999.99'); -- Agregado,Revisar
          END IF;

    insert into BPS_VALID_VENTA_PREVI
      (VAL_FILA      ,
      COD_PAIS   ,
      COD_SOCI   ,
      COD_MARC   ,
      COD_ALMA   ,
      COD_CANA   ,
      COD_PERI   ,
      COD_REGI   ,
      COD_ZONA   ,
      VEN_NETA_CATA ,
      VAL_PEDI   ,
      VAL_ACTI_INIC ,
      VAL_INGR   ,
      VAL_REIN   ,
      VAL_EGRE   ,
      VAL_CONS_PRIV ,
      VAL_CLIE_PRIV ,
      VAL_TRAN_PRIV ,
      VAL_RENT_SEGP ,
      VAL_RENT_TERP ,
      VAL_RENT_CUAP ,
      VAL_UNID   ,
      VAL_PORC_RENT ,
      COD_SECC ,
      VAL_POSI_EGRE_CAM_ANT
       )
    values
      (v_val_fila(i)      ,
        v_cod_pais(i)   ,
        v_cod_soci(i)   ,
        v_COD_MARC(i)   ,
        v_cod_alma(i)   ,
        v_COD_CANA(i)   ,
        v_COD_PERI(i)   ,
        v_cod_regi(i)   ,
        v_cod_zona(i)   ,
        lnven_neta_cata ,
        lnval_pedi      ,
        lnval_acti_inic ,
        lnVAL_INGR ,
        lnVAL_REIN  ,
        lnval_egre   ,
        lnval_cons_priv ,
        lnval_clie_priv,
        lnval_tran_priv ,
        TO_NUMBER(v_val_rent_segp(i),'999.99') ,
        TO_NUMBER(v_val_rent_terp(i),'999.99') ,
        TO_NUMBER(v_val_rent_cuap(i),'999.99') ,
        lnval_unid   ,
        TO_NUMBER(v_val_porc_rent(i) ,'999.99'),
        v_cod_secc(i)   ,
        lnVal_posi_egre_cam_ant
       );
     END LOOP;
  END LOOP;
  CLOSE curEstructuraFVP;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    GEN_PKG_GENER.GEN_PR_INSER_ERROR_BDATO_SSICC(NOMBRE_PAQUETE, 'BPS_PR_COPIA_ESTRU_VENTA_PREVI',
          'cursorEstruFVP', PAGINA_ERROR_LOG, REGISTRO_ERROR_LOG, KEY_ERROR, ls_sqlerrm);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR BPS_PR_COPIA_ESTRU_VENTA_PREVI: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Calcula la carga de FVP Zona
Fecha Creacion    : 13/01/2008
Fecha Modificacion: 24/09/2015 - Carlos Mori ( Ajuste formulas estimados zonas )
Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_CALCU_ESTRU_VENTA_PREVI (
          psIndTipoFvp varchar2
)
IS
      CURSOR curEstructuraTMP_FVP
      IS
        SELECT  VAL_FILA        as  val_fila      ,
                COD_PAIS     as  cod_pais   ,
                COD_SOCI     as  cod_soci   ,
                COD_MARC     as  COD_MARC   ,
                COD_ALMA     as  cod_alma   ,
                COD_CANA     as  COD_CANA   ,
                COD_PERI     as  COD_PERI   ,
                COD_REGI     as  cod_regi   ,
                COD_ZONA     as  cod_zona   ,
                VEN_NETA_CATA   as  ven_neta_cata ,
                VAL_PEDI     as  val_pedi   ,
                VAL_ACTI_INIC   as  val_acti_inic ,
                VAL_INGR     as  VAL_INGR   ,
                VAL_REIN     as  VAL_REIN   ,
                VAL_EGRE     as  val_egre   ,
                VAL_CONS_PRIV   as  val_cons_priv ,
                VAL_CLIE_PRIV   as  val_clie_priv ,
                VAL_TRAN_PRIV   as  val_tran_priv ,
                VAL_RENT_SEGP   as  val_rent_segp ,
                VAL_RENT_TERP   as  val_rent_terp ,
                VAL_RENT_CUAP   as  val_rent_cuap ,
                VAL_UNID     as  val_unid   ,
                VAL_PORC_RENT   as  val_porc_rent ,
                COD_SECC as cod_secc ,
                VAL_POSI_EGRE_CAM_ANT as val_posi_egre_cam_ant
        FROM BPS_VALID_VENTA_PREVI;

 TYPE t_val_fila      is table of BPS_VALID_VENTA_PREVI.val_fila%type;
 TYPE t_cod_pais   is table of BPS_VALID_VENTA_PREVI.COD_PAIS%type;
 TYPE t_cod_soci   is table of BPS_VALID_VENTA_PREVI.Cod_Soci%type;
 TYPE t_COD_MARC   is table of BPS_VALID_VENTA_PREVI.COD_MARC%TYPE;
 TYPE t_cod_alma   is table of BPS_VALID_VENTA_PREVI.Cod_Alma%TYPE;
 TYPE t_COD_CANA   is table of BPS_VALID_VENTA_PREVI.COD_CANA%TYPE;
 TYPE t_COD_PERI   is table of BPS_VALID_VENTA_PREVI.COD_PERI%TYPE;
 TYPE t_cod_regi   is table of BPS_VALID_VENTA_PREVI.Cod_Regi%TYPE;
 TYPE t_cod_zona   is table of BPS_VALID_VENTA_PREVI.Cod_Zona%TYPE;
 TYPE t_ven_neta_cata is table of BPS_VALID_VENTA_PREVI.Ven_Neta_Cata%TYPE;
 TYPE t_val_pedi   is table of BPS_VALID_VENTA_PREVI.Val_Pedi%TYPE;
 TYPE t_val_acti_inic is table of BPS_VALID_VENTA_PREVI.Val_Acti_Inic%TYPE;
 TYPE t_VAL_INGR   is table of BPS_VALID_VENTA_PREVI.VAL_INGR%TYPE;
 TYPE t_VAL_REIN   is table of BPS_VALID_VENTA_PREVI.VAL_REIN%TYPE;
 TYPE t_val_egre   is table of BPS_VALID_VENTA_PREVI.Val_Egre%TYPE;
 TYPE t_val_cons_priv is table of BPS_VALID_VENTA_PREVI.Val_Cons_Priv%TYPE;
 TYPE t_val_clie_priv is table of BPS_VALID_VENTA_PREVI.Val_Clie_Priv%TYPE;
 TYPE t_val_tran_priv is table of BPS_VALID_VENTA_PREVI.Val_Tran_Priv%TYPE;
 TYPE t_val_rent_segp is table of BPS_VALID_VENTA_PREVI.Val_Rent_Segp%TYPE;
 TYPE t_val_rent_terp is table of BPS_VALID_VENTA_PREVI.Val_Rent_Terp%TYPE;
 TYPE t_val_rent_cuap is table of BPS_VALID_VENTA_PREVI.Val_Rent_Cuap%TYPE;
 TYPE t_val_unid   is table of BPS_VALID_VENTA_PREVI.Val_Unid%TYPE;
 TYPE t_val_porc_rent is table of BPS_VALID_VENTA_PREVI.Val_Porc_Rent%TYPE;
 TYPE t_cod_secc is table of BPS_VALID_VENTA_PREVI.Cod_Secc%TYPE;
 TYPE t_val_posi_egre_cam_ant is table of BPS_VALID_VENTA_PREVI.Val_Posi_Egre_Cam_Ant%TYPE;

v_val_fila        t_val_fila       ;
v_cod_pais     t_cod_pais    ;
v_cod_soci     t_cod_soci    ;
v_COD_MARC     t_COD_MARC    ;
v_cod_alma     t_cod_alma    ;
v_COD_CANA     t_COD_CANA    ;
v_COD_PERI     t_COD_PERI    ;
v_cod_regi     t_cod_regi    ;
v_cod_zona     t_cod_zona    ;
v_ven_neta_cata   t_ven_neta_cata  ;
v_val_pedi     t_val_pedi    ;
v_val_acti_inic   t_val_acti_inic  ;
v_VAL_INGR     t_VAL_INGR    ;
v_VAL_REIN     t_VAL_REIN    ;
v_val_egre     t_val_egre    ;
v_val_cons_priv   t_val_cons_priv  ;
v_val_clie_priv   t_val_clie_priv  ;
v_val_tran_priv   t_val_tran_priv  ;
v_val_rent_segp   t_val_rent_segp  ;
v_val_rent_terp   t_val_rent_terp  ;
v_val_rent_cuap   t_val_rent_cuap  ;
v_val_unid     t_val_unid    ;
v_val_porc_rent   t_val_porc_rent  ;
v_cod_secc   t_cod_secc  ;
v_val_posi_egre_cam_ant   t_val_posi_egre_cam_ant  ;

    i    BINARY_INTEGER := 0;
    v_row_count_ins      NUMBER := 0;
    lnActiFina           NUMBER;
    lnVAL_PUP                NUMBER;
    lnVAL_PPU                NUMBER;
    lnPorActividad       NUMBER;
    lnNumeClie        NUMBER;
    lnRezEntre           NUMBER;
    lnPorEgresos         NUMBER;
    lnRezoReci         NUMBER;
    lnRezoEntr         NUMBER;
    lnPosiEgre        NUMBER;
    lnActiLideres        NUMBER;
    lnPromSolePedi        NUMBER;
    lnCapitalizacion     NUMBER;
    lnValPorcRent        NUMBER;
    lsCodPeri            VARCHAR2(6);
    lsPeri               VARCHAR2(6);
    lnCont               NUMBER;
    lnSumActiIni         NUMBER;
    lnSumEgresos         NUMBER;
    lnSumActiIniArchi    NUMBER;
    lnSumActiIniTabla    NUMBER;
    lnSumEgresosArchi    NUMBER;
    lnSumEgresosTabla    NUMBER;
    lnPromActiIni        NUMBER;
    lnRentActiC18        NUMBER;
    lnRealNumeActiFinaC18 NUMBER; -- Nuevo
    lnOidPeriodoAnte      NUMBER; -- Nuevo
    lsUltiCampa          SEG_PERIO_CORPO.COD_PERI%TYPE;
    lsCodigoPais         SEG_PAIS.COD_PAIS%TYPE;
BEGIN

    -- Obtener Pais
    
    SELECT DISTINCT cod_pais
      INTO lsCodigoPais
      FROM BAS_CTRL_FACT;
    
    -- Obtener el valor de la ultima campania configurada para el pais
    
    BEGIN
       SELECT para.val_para
         INTO lsUltiCampa
         FROM BAS_PARAM_PAIS para
        WHERE para.cod_pais = lsCodigoPais
          AND para.cod_sist = 'GEN'
          AND UPPER(para.nom_para) = 'NUMCAMPANAS';
    EXCEPTION WHEN NO_DATA_FOUND THEN
       lsUltiCampa := '18';
    END;

     DELETE FROM BPS_ZONA_VENTA_PREVI;
     DELETE FROM BPS_SECCI_VENTA_PREVI;

  OPEN curEstructuraTMP_FVP;
  LOOP
    -- Bulk collect data into memory table - X rows at a time
    FETCH curEstructuraTMP_FVP BULK COLLECT INTO
                            v_val_fila      ,
                            v_cod_pais   ,
                            v_cod_soci   ,
                            v_COD_MARC   ,
                            v_cod_alma   ,
                            v_COD_CANA   ,
                            v_COD_PERI   ,
                            v_cod_regi   ,
                            v_cod_zona   ,
                            v_ven_neta_cata ,
                            v_val_pedi   ,
                            v_val_acti_inic ,
                            v_VAL_INGR   ,
                            v_VAL_REIN   ,
                            v_val_egre   ,
                            v_val_cons_priv ,
                            v_val_clie_priv ,
                            v_val_tran_priv ,
                            v_val_rent_segp ,
                            v_val_rent_terp ,
                            v_val_rent_cuap ,
                            v_val_unid   ,
                            v_val_porc_rent ,
                            v_cod_secc ,
                            v_val_posi_egre_cam_ant
                            LIMIT W_FILAS;

    EXIT WHEN v_row_count_ins = curEstructuraTMP_FVP%ROWCOUNT;
    v_row_count_ins := curEstructuraTMP_FVP%ROWCOUNT;

    -- Bulk bind of data in memory table...
    FOR i IN v_val_fila.FIRST .. v_val_fila.LAST loop

        lnActiFina :=  v_val_acti_inic(i) + v_VAL_INGR(i) + v_VAL_REIN(i) - v_val_egre(i);
        lnOidPeriodoAnte := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(TO_NUMBER(substr(v_COD_PERI(i),1,4))-1 || lsUltiCampa); 

        IF ((v_val_pedi(i) = 0) or (v_val_pedi(i) is NULL)) THEN
             lnVAL_PUP := 0;
        ELSE
             lnVAL_PUP := v_val_unid(i)/v_val_pedi(i);
        END IF;

        IF ((v_val_unid(i) = 0) or (v_val_unid(i) is NULL)) THEN
           lnVAL_PPU := 0;
        ELSE
            lnVAL_PPU := v_ven_neta_cata(i)/v_val_unid(i);
        END IF;

        IF(lnActiFina = 0 or lnActiFina IS NULL) THEN
              lnPorActividad:=0;
        ELSE
             lnPorActividad:=v_val_pedi(i)*100/round(lnActiFina,2);
        END IF;

            -- Obtener Numero Real Activas Finales C18 Año Anterior
            BEGIN
                SELECT SUM( NVL(fvrl.num_acti_fina,0) )
                  INTO lnRealNumeActiFinaC18
                  FROM int_fuent_ventas_real fvrl,
                       zon_terri_admin ztad,
                       zon_terri terr,
                       zon_secci zscc,
                       zon_zona zzon,
                       zon_regio zorg
                 WHERE fvrl.terr_oid_terr = ztad.terr_oid_terr
                   AND ztad.terr_oid_terr = terr.oid_terr
                   AND ztad.zscc_oid_secc = zscc.oid_secc
                   AND zscc.zzon_oid_zona = zzon.oid_zona
                   AND zzon.zorg_oid_regi = zorg.oid_regi
                   AND lnOidPeriodoAnte BETWEEN NVL(ztad.perd_oid_peri_inic,lnOidPeriodoAnte) AND NVL(ztad.perd_oid_peri_fina,lnOidPeriodoAnte)
                   AND fvrl.perd_oid_peri = lnOidPeriodoAnte
                   AND (
                        ( psIndTipoFvp = '1' AND
                         zorg.cod_regi || zzon.cod_zona || zscc.cod_secc = v_cod_regi(i) || v_cod_zona(i) || v_cod_secc(i)
                        ) OR
                        ( psIndTipoFvp = '2' AND
                         zorg.cod_regi || zzon.cod_zona = v_cod_regi(i) || v_cod_zona(i)
                        )
                       )
                 GROUP BY CASE WHEN psIndTipoFvp = '1' THEN zorg.cod_regi || zzon.cod_zona || zscc.cod_secc
                               WHEN psIndTipoFvp = '2' THEN zorg.cod_regi || zzon.cod_zona
                          END; -- Nuevo
            EXCEPTION
               WHEN NO_DATA_FOUND THEN
                   lnRealNumeActiFinaC18:=0;
            END;

            -- Calcular Estimado Retención Activas C18
        IF ( psIndTipoFvp = '1' ) THEN
            BEGIN
                SELECT --(lnActiFina*100)/DECODE(num_acfi,0,null,num_acfi)
                       (lnActiFina*100)/DECODE(lnRealNumeActiFinaC18,0,null,lnRealNumeActiFinaC18) -- Nuevo
                  INTO lnRentActiC18
                  FROM INT_SAB_VENTA_PREVI_SECCI
                 WHERE cod_zona = v_cod_zona(i)
                   AND cod_secc = v_cod_secc(i)
                   AND cod_peri = TO_NUMBER(substr(v_COD_PERI(i),1,4))-1 || lsUltiCampa;
            EXCEPTION
               WHEN no_data_found THEN
                   lnRentActiC18:=0;
            END;
        ELSE
        BEGIN
                select --(lnActiFina*100)/DECODE(num_acfi,0,null,num_acfi)
                       (lnActiFina*100)/DECODE(lnRealNumeActiFinaC18,0,null,lnRealNumeActiFinaC18) -- Nuevo
                  into lnRentActiC18
            from INT_SAB_VENTA_PREVI_ZONA
            where cod_zona = v_cod_zona(i)
            and cod_peri = TO_NUMBER(substr(v_COD_PERI(i),1,4))-1 || lsUltiCampa;
        EXCEPTION
           WHEN no_data_found THEN
               lnRentActiC18:=0;
        END;
        END IF;

        lnNumeClie := 0;

        BEGIN
          lnCont:=0;
          lnSumEgresos:=0;
          lnSumActiIni:=0;

          LOOP
            lsCodPeri:=CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(substr(v_COD_PERI(i),1,4)||'01',lnCont);
            lnSumEgresosArchi :=0;
            lnSumEgresosTabla :=0;
            lnSumActiIniArchi :=0;
            lnSumActiIniTabla :=0;

            BEGIN
                select distinct(COD_PERI) into lsPeri
                from BPS_VALID_VENTA_PREVI
                where COD_PERI = lsCodPeri;
            EXCEPTION
            WHEN no_data_found THEN
                     lsPeri:='0';
            END;

            IF (psIndTipoFvp = '1') THEN
                IF(lsPeri <> '0')THEN
                BEGIN
                    BEGIN
                            SELECT VAL_EGRE, VAL_ACTI_INIC
                              INTO lnSumEgresosArchi, lnSumActiIniArchi
                              FROM BPS_VALID_VENTA_PREVI
                             WHERE cod_zona = v_cod_zona(i)
                               AND cod_secc = v_cod_secc(i)
                               AND cod_peri = lsCodPeri;
                        EXCEPTION WHEN no_data_found THEN
                         lnSumEgresosArchi:=0;
                         lnSumActiIniArchi:=0;
                    END;
                END;
                ELSE
                BEGIN
                    BEGIN
                              SELECT num_egpu, num_acin
                                INTO lnSumEgresosTabla, lnSumActiIniTabla
                                FROM INT_SAB_VENTA_PREVI_SECCI
                               WHERE cod_zona = v_cod_zona(i)
                                 AND cod_secc = v_cod_secc(i)
                                 AND cod_peri = lsCodPeri;
                        EXCEPTION WHEN no_data_found THEN
                         lnSumEgresosTabla:=0;
                         lnSumActiIniTabla:=0;
                    END;
                END;
                END IF;
            ELSE
            IF(lsPeri <> '0')THEN
            BEGIN
                BEGIN
                    select VAL_EGRE into lnSumEgresosArchi
                    from BPS_VALID_VENTA_PREVI
                    where cod_zona = v_cod_zona(i)
                    and cod_peri = lsCodPeri;
                EXCEPTION
                WHEN no_data_found THEN
                     lnSumEgresosArchi:=0;
                END;

                BEGIN
                    select VAL_ACTI_INIC into lnSumActiIniArchi
                    from BPS_VALID_VENTA_PREVI
                    where cod_zona = v_cod_zona(i)
                    and cod_peri = lsCodPeri;
                EXCEPTION
                WHEN no_data_found THEN
                     lnSumActiIniArchi:=0;
                END;
            END;
            ELSE
            BEGIN
                BEGIN
                      select num_egpu into lnSumEgresosTabla
                      from INT_SAB_VENTA_PREVI_ZONA
                      where cod_zona = v_cod_zona(i)
                      and cod_peri = lsCodPeri;
                EXCEPTION
                WHEN no_data_found THEN
                     lnSumEgresosTabla:=0;
                END;

                BEGIN
                      select num_acin into lnSumActiIniTabla
                      from INT_SAB_VENTA_PREVI_ZONA
                      where cod_zona = v_cod_zona(i)
                      and cod_peri = lsCodPeri;
                EXCEPTION
                WHEN no_data_found THEN
                     lnSumActiIniTabla:=0;
                END;
            END;
            END IF;
            END IF;

            lnSumEgresos:= lnSumEgresos + lnSumEgresosArchi +  lnSumEgresosTabla;
            lnSumActiIni:= lnSumActiIni + lnSumActiIniArchi + lnSumActiIniTabla;
            lnCont:=lnCont+1;

          EXIT WHEN lsCodPeri = v_COD_PERI(i);
          END LOOP;
        END;

        IF(TO_NUMBER(substr(v_COD_PERI(i),5,2)) = 0)THEN
              lnPromActiIni:=0;
        ELSE
             lnPromActiIni:=lnSumActiIni/TO_NUMBER(substr(v_COD_PERI(i),5,2));
        END IF;

        IF(lnPromActiIni = 0)THEN
              lnRezEntre:=0;
        ELSE
             lnRezEntre:=(lnSumEgresos*100)/lnPromActiIni;
        END IF;

        IF ((v_val_acti_inic(i) = 0) or (v_val_acti_inic(i) is NULL)) THEN
            lnPorEgresos := 0;
        ELSE
            lnPorEgresos:=(v_val_egre(i)*100)/v_val_acti_inic(i);
        END IF;

        lnRezoReci:= 0;
        lnRezoEntr:=0;
        lnActiLideres:=0;
        lnPosiEgre := lnActiFina - v_val_pedi(i);

        IF(v_val_pedi(i) = 0)THEN
              lnPromSolePedi:=0;
        ELSE
             lnPromSolePedi := v_ven_neta_cata(i)/v_val_pedi(i);
        END IF;

        lnCapitalizacion :=  v_VAL_INGR(i) + v_VAL_REIN(i) - v_val_egre(i);
        /*v_val_porc_rent(i) := 1 - ( v_val_egre(i) / v_val_posi_egre_cam_ant(i) );*/

        IF (psIndTipoFvp = '1') THEN
          IF (v_val_posi_egre_cam_ant(i) IS NULL OR v_val_posi_egre_cam_ant(i)<=0 )THEN
            lnValPorcRent := 0;
            ELSE
              lnValPorcRent := 1 - ( v_val_egre(i) / v_val_posi_egre_cam_ant(i) );
            END IF;           
        ELSE
             --lnValPorcRent := 0;
             lnValPorcRent := v_val_porc_rent(i);
        END IF;

        IF (psIndTipoFvp = '1') THEN
            INSERT INTO BPS_SECCI_VENTA_PREVI
              (VAL_FILA      ,
              COD_PAIS   ,
              COD_SOCI   ,
              COD_MARC   ,
              COD_ALMA   ,
              COD_CANA   ,
              COD_PERI   ,
              COD_REGI   ,
              COD_ZONA   ,
              VEN_NETA_CATA ,
              VAL_PEDI   ,
              VAL_ACTI_INIC ,
              VAL_INGR   ,
              VAL_REIN   ,
              VAL_EGRE   ,
              VAL_CONS_PRIV ,
              VAL_CLIE_PRIV ,
              VAL_TRAN_PRIV ,
              VAL_RENT_SEGP ,
              VAL_RENT_TERP ,
              VAL_RENT_CUAP ,
              VAL_UNID   ,
              VAL_PORC_RENT ,
              FEC_CALC    ,
              FEC_ENVI    ,
              ACT_FINA    ,
              VAL_PUP       ,
              VAL_PPU       ,
              POR_ACTI    ,
              REN_ACTI    ,
              NUM_CLIE    ,
              POR_ROTA    ,
              POR_EGRE    ,
              REZ_RECI    ,
              REZ_ENTR    ,
              POS_EGRE    ,
              ACT_LIDE    ,
              PRO_SOLE_PEDI  ,
              COD_SECC  ,
              VAL_CAPI  ,
              VAL_PORC_RETE  ,
              VAL_POSI_EGRE_CAM_ANT,
              VAL_ACTI_FINA_C18 )
       VALUES ( v_val_fila(i),
                v_cod_pais(i)   ,
                v_cod_soci(i)   ,
                v_COD_MARC(i)   ,
                v_cod_alma(i)   ,
                v_COD_CANA(i)   ,
                v_COD_PERI(i)   ,
                v_cod_regi(i)   ,
                v_cod_zona(i)   ,
                v_ven_neta_cata(i),
                v_val_pedi(i),
                v_val_acti_inic(i),
                v_VAL_INGR(i),
                v_VAL_REIN(i),
                v_val_egre(i),
                v_val_cons_priv(i),
                v_val_clie_priv(i),
                v_val_tran_priv(i),
                v_val_rent_segp(i),
                v_val_rent_terp(i),
                v_val_rent_cuap(i),
                v_val_unid(i),
                lnValPorcRent,
                SYSDATE,
                NULL,
                lnActiFina,
                lnVAL_PUP,
                lnVAL_PPU,
                lnPorActividad,
                lnRentActiC18,
                lnNumeClie,
                lnRezEntre,
                lnPorEgresos,
                lnRezoReci,
                lnRezoEntr,
                lnPosiEgre,
                lnActiLideres,
                lnPromSolePedi,
                v_cod_secc(i),
                lnCapitalizacion,
                lnValPorcRent,
                v_val_posi_egre_cam_ant(i),
                lnRealNumeActiFinaC18 );
        ELSE
            INSERT INTO BPS_ZONA_VENTA_PREVI
          (VAL_FILA      ,
          COD_PAIS   ,
          COD_SOCI   ,
          COD_MARC   ,
          COD_ALMA   ,
          COD_CANA   ,
          COD_PERI   ,
          COD_REGI   ,
          COD_ZONA   ,
          VEN_NETA_CATA ,
          VAL_PEDI   ,
          VAL_ACTI_INIC ,
          VAL_INGR   ,
          VAL_REIN   ,
          VAL_EGRE   ,
          VAL_CONS_PRIV ,
          VAL_CLIE_PRIV ,
          VAL_TRAN_PRIV ,
          VAL_RENT_SEGP ,
          VAL_RENT_TERP ,
          VAL_RENT_CUAP ,
          VAL_UNID   ,
          VAL_PORC_RENT ,
          FEC_CALC    ,
          FEC_ENVI    ,
          ACT_FINA    ,
          VAL_PUP       ,
          VAL_PPU       ,
          POR_ACTI    ,
          REN_ACTI    ,
          NUM_CLIE    ,
          POR_ROTA    ,
          POR_EGRE    ,
          REZ_RECI    ,
          REZ_ENTR    ,
          POS_EGRE    ,
          ACT_LIDE    ,
          PRO_SOLE_PEDI,
          VAL_ACTI_FINA_C18 ) -- Nuevo
   values ( v_val_fila(i)      ,
            v_cod_pais(i)   ,
            v_cod_soci(i)   ,
            v_COD_MARC(i)   ,
            v_cod_alma(i)   ,
            v_COD_CANA(i)   ,
            v_COD_PERI(i)   ,
            v_cod_regi(i)   ,
            v_cod_zona(i)   ,
            v_ven_neta_cata(i),
            v_val_pedi(i),
            v_val_acti_inic(i),
            v_VAL_INGR(i),
            v_VAL_REIN(i),
            v_val_egre(i),
            v_val_cons_priv(i),
            v_val_clie_priv(i),
            v_val_tran_priv(i),
            v_val_rent_segp(i),
            v_val_rent_terp(i),
            v_val_rent_cuap(i),
            v_val_unid(i),
            lnValPorcRent,
            SYSDATE,
            NULL,
            lnActiFina,
            lnVAL_PUP,
            lnVAL_PPU,
            lnPorActividad,
            lnRentActiC18,
            0,
            lnRezEntre,
            lnPorEgresos,
            0,
            0,
            lnPosiEgre,
            lnActiLideres,
            lnPromSolePedi,
            lnRealNumeActiFinaC18 ); -- Nuevo
        END IF;
       END LOOP;
  END LOOP;
  CLOSE curEstructuraTMP_FVP;

  UPDATE BPS_VALID_VENTA_PREVI set fec_calc = sysdate;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR BPS_PR_CALCU_ESTRU_VENTA_PREVI: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Actualiza las Fuente de Venta Prevista del sicc y ssicc
Fecha Creacion    : 13/01/2008
Fecha Modificacion: 24/09/2015 - Carlos Mori ( Ajuste formulas estimados zonas )

Autor             : Rosalvina Ramirez
***************************************************************************/
PROCEDURE BPS_PR_ACTUA_ESTRU_VENTA_PREVI (
          psOidPais              varchar2,
          psUsuario              varchar2,
          psIndTipoFvp        varchar2)
IS

  CURSOR cursorTMP_FVP_Zona
  IS
SELECT DISTINCT (COD_PERI) as COD_PERI
  FROM BPS_ZONA_VENTA_PREVI
 WHERE fec_calc IS NOT NULL
   AND FEC_ENVI iS NULL;

  TYPE t_COD_PERI      is table of BPS_ZONA_VENTA_PREVI.COD_PERI%type;
  v_COD_PERI           t_COD_PERI;

  i    BINARY_INTEGER := 0;
  v_row_count_ins      NUMBER := 0;
  lnOidSoci            NUMBER;
  lnOidMone            NUMBER;
  lnOidNextPkZona      NUMBER;
  lnOidPeri            NUMBER;
  lnOidAlma            NUMBER;
  lnOidZona            NUMBER;

        CURSOR curEstructuraTMP_FVP_ZONA
      IS
        SELECT  VAL_FILA        as  val_fila      ,
                COD_PAIS     as  cod_pais   ,
                COD_SOCI     as  cod_soci   ,
                COD_MARC     as  COD_MARC   ,
                COD_ALMA     as  cod_alma   ,
                COD_CANA     as  COD_CANA   ,
                COD_PERI     as  COD_PERI   ,
                COD_REGI     as  cod_regi   ,
                COD_ZONA     as  cod_zona   ,
                VEN_NETA_CATA   as  ven_neta_cata ,
                VAL_PEDI     as  val_pedi   ,
                VAL_ACTI_INIC   as  val_acti_inic ,
                VAL_INGR     as  VAL_INGR   ,
                VAL_REIN     as  VAL_REIN   ,
                VAL_EGRE     as  val_egre   ,
                VAL_CONS_PRIV   as  val_cons_priv ,
                VAL_CLIE_PRIV   as  val_clie_priv ,
                VAL_TRAN_PRIV   as  val_tran_priv ,
                VAL_RENT_SEGP   as  val_rent_segp ,
                VAL_RENT_TERP   as  val_rent_terp ,
                VAL_RENT_CUAP   as  val_rent_cuap ,
                VAL_UNID     as  val_unid   ,
                VAL_PORC_RENT   as  val_porc_rent ,
                ACT_FINA     as  act_fina     ,
                VAL_PUP        as  VAL_PUP           ,
                VAL_PPU        as  VAL_PPU           ,
                POR_ACTI     as  POR_ACTI      ,
                REN_ACTI     as  REN_ACTI      ,
                NUM_CLIE     as  num_clie       ,
                POR_ROTA     as  por_rota       ,
                POR_EGRE     as  por_egre       ,
                REZ_RECI     as  rez_reci       ,
                REZ_ENTR     as  rez_entr       ,
                POS_EGRE     as  pos_egre       ,
                ACT_LIDE     as  act_lide       ,
                PRO_SOLE_PEDI   as  PRO_SOLE_PEDI,
                VAL_ACTI_FINA_C18 as val_acti_fina_c18
        FROM BPS_ZONA_VENTA_PREVI;

 TYPE t_val_fila      is table of BPS_ZONA_VENTA_PREVI.val_fila%type;
 TYPE t_cod_pais   is table of BPS_ZONA_VENTA_PREVI.COD_PAIS%type;
 TYPE t_cod_soci   is table of BPS_ZONA_VENTA_PREVI.Cod_Soci%type;
 TYPE t_COD_MARC   is table of BPS_ZONA_VENTA_PREVI.COD_MARC%TYPE;
 TYPE t_cod_alma   is table of BPS_ZONA_VENTA_PREVI.Cod_Alma%TYPE;
 TYPE t_COD_CANA   is table of BPS_ZONA_VENTA_PREVI.COD_CANA%TYPE;
 TYPE t_cod_regi   is table of BPS_ZONA_VENTA_PREVI.Cod_Regi%TYPE;
 TYPE t_cod_zona   is table of BPS_ZONA_VENTA_PREVI.Cod_Zona%TYPE;
 TYPE t_ven_neta_cata is table of BPS_ZONA_VENTA_PREVI.Ven_Neta_Cata%TYPE;
 TYPE t_val_pedi   is table of BPS_ZONA_VENTA_PREVI.Val_Pedi%TYPE;
 TYPE t_val_acti_inic is table of BPS_ZONA_VENTA_PREVI.Val_Acti_Inic%TYPE;
 TYPE t_VAL_INGR   is table of BPS_ZONA_VENTA_PREVI.VAL_INGR%TYPE;
 TYPE t_VAL_REIN   is table of BPS_ZONA_VENTA_PREVI.VAL_REIN%TYPE;
 TYPE t_val_egre   is table of BPS_ZONA_VENTA_PREVI.Val_Egre%TYPE;
 TYPE t_val_cons_priv is table of BPS_ZONA_VENTA_PREVI.Val_Cons_Priv%TYPE;
 TYPE t_val_clie_priv is table of BPS_ZONA_VENTA_PREVI.Val_Clie_Priv%TYPE;
 TYPE t_val_tran_priv is table of BPS_ZONA_VENTA_PREVI.Val_Tran_Priv%TYPE;
 TYPE t_val_rent_segp is table of BPS_ZONA_VENTA_PREVI.Val_Rent_Segp%TYPE;
 TYPE t_val_rent_terp is table of BPS_ZONA_VENTA_PREVI.Val_Rent_Terp%TYPE;
 TYPE t_val_rent_cuap is table of BPS_ZONA_VENTA_PREVI.Val_Rent_Cuap%TYPE;
 TYPE t_val_unid   is table of BPS_ZONA_VENTA_PREVI.Val_Unid%TYPE;
 TYPE t_val_porc_rent is table of BPS_ZONA_VENTA_PREVI.Val_Porc_Rent%TYPE;
 TYPE t_act_fina      is table of BPS_ZONA_VENTA_PREVI.Act_Fina%TYPE;
 TYPE t_VAL_PUP           is table of BPS_ZONA_VENTA_PREVI.VAL_PUP%TYPE;
 TYPE t_VAL_PPU           is table of BPS_ZONA_VENTA_PREVI.VAL_PPU%TYPE;
 TYPE t_POR_ACTI     is table of BPS_ZONA_VENTA_PREVI.POR_ACTI%TYPE;
 TYPE t_REN_ACTI     is table of BPS_ZONA_VENTA_PREVI.REN_ACTI%TYPE;
 TYPE t_num_clie      is table of BPS_ZONA_VENTA_PREVI.Num_Clie%TYPE;
 TYPE t_por_rota      is table of BPS_ZONA_VENTA_PREVI.Por_Rota%TYPE;
 TYPE t_por_egre      is table of BPS_ZONA_VENTA_PREVI.Por_Egre%TYPE;
 TYPE t_rez_reci      is table of BPS_ZONA_VENTA_PREVI.Rez_Reci%TYPE;
 TYPE t_rez_entr      is table of BPS_ZONA_VENTA_PREVI.Rez_Entr%TYPE;
 TYPE t_pos_egre      is table of BPS_ZONA_VENTA_PREVI.Por_Egre%TYPE;
 TYPE t_act_lide      is table of BPS_ZONA_VENTA_PREVI.Act_Lide%TYPE;
 TYPE t_PRO_SOLE_PEDI  is table of BPS_ZONA_VENTA_PREVI.PRO_SOLE_PEDI%TYPE;
 TYPE t_val_acti_fina_c18  is table of BPS_ZONA_VENTA_PREVI.VAL_ACTI_FINA_C18%TYPE;

v_val_fila        t_val_fila       ;
v_cod_pais     t_cod_pais    ;
v_cod_soci     t_cod_soci    ;
v_COD_MARC     t_COD_MARC    ;
v_cod_alma     t_cod_alma    ;
v_COD_CANA     t_COD_CANA    ;
v_cod_regi     t_cod_regi    ;
v_cod_zona     t_cod_zona    ;
v_ven_neta_cata   t_ven_neta_cata  ;
v_val_pedi     t_val_pedi    ;
v_val_acti_inic   t_val_acti_inic  ;
v_VAL_INGR     t_VAL_INGR    ;
v_VAL_REIN     t_VAL_REIN    ;
v_val_egre     t_val_egre    ;
v_val_cons_priv   t_val_cons_priv  ;
v_val_clie_priv   t_val_clie_priv  ;
v_val_tran_priv   t_val_tran_priv  ;
v_val_rent_segp   t_val_rent_segp  ;
v_val_rent_terp   t_val_rent_terp  ;
v_val_rent_cuap   t_val_rent_cuap  ;
v_val_unid     t_val_unid    ;
v_val_porc_rent   t_val_porc_rent  ;
v_act_fina     t_act_fina     ;
v_VAL_PUP             t_VAL_PUP            ;
v_VAL_PPU             t_VAL_PPU            ;
v_POR_ACTI       t_POR_ACTI      ;
v_REN_ACTI       t_REN_ACTI      ;
v_num_clie        t_num_clie       ;
v_por_rota        t_por_rota       ;
v_por_egre        t_por_egre       ;
v_rez_reci        t_rez_reci       ;
v_rez_entr        t_rez_entr       ;
v_pos_egre        t_pos_egre       ;
v_act_lide        t_act_lide       ;
v_PRO_SOLE_PEDI    t_PRO_SOLE_PEDI   ;
v_val_acti_fina_c18 t_val_acti_fina_c18;

BEGIN

     IF ( psIndTipoFvp = '1' ) THEN
         DELETE FROM int_sab_venta_previ_secci WHERE (cod_peri, cod_regi) IN(
              SELECT DISTINCT cod_peri, cod_regi
                FROM bps_secci_venta_previ
               WHERE fec_calc IS NOT NULL
                 AND fec_envi IS NULL
         );

         DELETE FROM int_sab_venta_previ_zona WHERE (cod_peri, cod_regi) IN(
              SELECT DISTINCT cod_peri, cod_regi
                FROM bps_secci_venta_previ
               WHERE fec_calc IS NOT NULL
                 AND fec_envi IS NULL
         );

         DELETE FROM int_fuent_venta_previ_sap
          WHERE (perd_oid_peri, zorg_oid_regi) IN (
                                                   SELECT DISTINCT
                                                          ( SELECT b.oid_peri
                                               FROM seg_perio_corpo a,
                                                         cra_perio       b,
                                                         seg_canal       c,
                                                         seg_marca       d
                                                  WHERE a.oid_peri = b.peri_oid_peri
                                                   AND b.cana_oid_cana = c.oid_cana
                                                   AND b.marc_oid_marc = d.oid_marc
                                                   AND c.cod_cana = 'VD'
                                                    AND d.cod_marc = 'T'
                                                    AND a.cod_peri = e.cod_peri) perd_oid_peri,
                                            (SELECT a.oid_regi
                                               FROM zon_regio a
                                              WHERE a.pais_oid_pais = psOidPais
                                                AND a.marc_oid_marc = 2003
                                                AND a.cana_oid_cana = 2001
                                                 AND a.cod_regi = e.cod_regi) zorg_oid_regi
                FROM bps_secci_venta_previ e
               WHERE e.fec_calc IS NOT NULL
         );

          -- Graba estimados por sección
          INSERT INTO int_sab_venta_previ_secci
                      ( cor_vpzo,
             pais_cod_pais,
             cod_peri,
             cod_regi,
             cod_zona,
             num_acin,
             num_ingr,
             num_rein,
             num_egne,
             por_egne,
             num_egpu,
             por_egpu,
             val_capi,
             num_acfi,
             por_acti,
             num_pedi,
             num_unid,
             num_clie,
             pro_sope,
             val_vene,
             val_ppu,
             val_pup,
             usu_digi,
             fec_digi,
             usu_modi,
             fec_modi,
             est_vpzo,
             val_acti_lide,
             num_cons_clie_priv,
             num_clie_insc_priv,
             num_clie_tran_priv,
             val_rete_2ped,
             val_rete_3ped,
             val_rete_4ped,
             val_rete_acti,
             val_porc_rota,
             val_posi_egre,
             val_rete_posi_egre,
             val_porc_rete_posi_egre,
             val_posi_egre_cam_ant,
             cod_secc)
          SELECT ( select DECODE(COUNT(cor_vpzo), 0, '1',MAX(cor_vpzo))
                     from int_sab_venta_previ_secci
                 ) + rownum cor_vpzo,
            cod_pais,
            cod_peri,
            cod_regi,
            cod_zona,
            val_acti_inic,
            val_ingr,
            val_rein,
            0 val_egne,
            0 por_egne,
            val_egre, -- numero de egresos puros
            por_egre, --porcentaje de egresos puros
            val_capi, --valor de capitalizacion
            act_fina,
            por_acti,
            val_pedi,
            val_unid,
            num_clie,
            pro_sole_pedi,
            ven_neta_cata,
            val_ppu,
            val_pup,
            psusuario usu_digi,
            sysdate fec_digi,
            null usu_modi,
            null fec_modi,
            1 est_vpzo,
            act_lide,
            val_cons_priv,
            val_clie_priv,
            val_tran_priv,
            val_rent_segp,
            val_rent_terp,
            val_rent_cuap,
            ren_acti,
            por_rota,
            pos_egre,
            val_porc_rent,
            val_porc_rete,
            val_posi_egre_cam_ant,
            cod_secc
          FROM bps_secci_venta_previ;

          -- Graba estimados agrupados por zona
          INSERT INTO int_sab_venta_previ_zona
                 ( cor_vpzo,
             pais_cod_pais,
             cod_marc,
             cod_cana,
             cod_peri,
             cod_regi,
             cod_zona,
             num_acin,
             num_ingr,
             num_rein,
             num_egpu,
             num_acfi,
             num_pedi,
             num_unid,
             val_vene,
             val_rete_2ped,
             val_rete_3ped,
             val_rete_4ped,
             val_posi_egre_cam_ant,
             val_capi,
             val_rete_acti,
             val_porc_rota,
             val_porc_rete_posi_egre )
          SELECT (
                   SELECT DECODE(COUNT(cor_vpzo), 0, '1',MAX(cor_vpzo))
                     FROM int_sab_venta_previ_zona
                 ) + rownum cor_vpzo,
                 y.pais_cod_pais,
                 y.cod_marc,
                 y.cod_cana,
                 y.cod_peri,
                 y.cod_regi,
                 y.cod_zona,
                 y.num_acin,
                 y.num_ingr,
                 y.num_rein,
                 y.num_egpu,
                 y.num_acfi,
                 y.num_pedi,
                 y.num_unid,
                 y.val_vene,
                 y.val_rete_2ped,
                 y.val_rete_3ped,
                 y.val_rete_4ped,
                 y.val_posi_egre_cam_ant,
                 y.val_capi,
                 y.num_acfi * 100 / DECODE( NVL(y.val_acti_fina_c18,0), 0, null,y.val_acti_fina_c18 ) val_rete_acti, -- Nuevo
                 y.val_porc_rota,
                 1 - NVL( y.num_egpu,0 ) / DECODE( NVL(y.val_posi_egre_cam_ant,0), 0, NULL, y.val_posi_egre_cam_ant ) val_porc_rete_posi_egre -- Nuevo
            FROM ( SELECT cod_pais pais_cod_pais,
                          'T' cod_marc,
                          'VD' cod_cana,
                          prev.cod_peri,
                          prev.cod_regi,
                          prev.cod_zona,
                          SUM(prev.val_acti_inic) num_acin,
                          SUM(prev.val_ingr) num_ingr,
                          SUM(prev.val_rein) num_rein,
                          SUM(prev.val_egre) num_egpu,
                          SUM(prev.act_fina) num_acfi,
                          SUM(prev.val_pedi) num_pedi,
                          SUM(prev.val_unid) num_unid,
                          SUM(prev.ven_neta_cata) val_vene,
                          SUM(prev.val_rent_segp)/count(distinct prev.cod_secc) val_rete_2ped,
                          SUM(prev.val_rent_terp)/count(distinct prev.cod_secc) val_rete_3ped,
                          SUM(prev.val_rent_cuap)/count(distinct prev.cod_secc) val_rete_4ped,
                          SUM(prev.val_posi_egre_cam_ant) val_posi_egre_cam_ant,
                          SUM(prev.val_capi) val_capi,
                          SUM(prev.por_rota)/count(distinct prev.cod_secc) val_porc_rota,
                          SUM(prev.val_acti_fina_c18) val_acti_fina_c18
          FROM bps_secci_venta_previ prev
         GROUP BY prev.cod_pais,
                  prev.cod_peri,
                  prev.cod_regi,
                  prev.cod_zona ) y;

          UPDATE int_sab_venta_previ_zona
          SET por_egpu = CASE num_acin WHEN 0 THEN 0 ELSE ((num_egpu * 100)/num_acin) END,
                 por_acti = CASE num_acfi WHEN 0 THEN 0 ELSE ((num_pedi * 100)/num_acfi) END,
                 pro_sope = CASE num_pedi WHEN 0 THEN 0 ELSE (val_vene/num_pedi) END,
                 val_pup = CASE num_pedi WHEN 0 THEN 0 ELSE (num_unid/num_pedi) END,
                 val_ppu = CASE num_unid WHEN 0 THEN 0 ELSE (val_vene/num_unid) END,
                 val_posi_egre = CASE num_pedi WHEN 0 THEN 0 ELSE (num_acfi - num_pedi) END,
                 usu_digi = psusuario,
                 fec_digi = sysdate
          WHERE (cod_peri, cod_regi) IN (
              SELECT DISTINCT cod_peri, cod_regi
                FROM bps_secci_venta_previ
               WHERE fec_calc IS NOT NULL
                 AND fec_envi IS NULL
         );


          INSERT INTO int_fuent_venta_previ_sap
                      (
             oid_fuen_vent_prev,
             soci_oid_soci,
             zzon_oid_zona,
             perd_oid_peri,
             zorg_oid_regi,
             almc_oid_alma,
             mone_oid_mone,
             num_acti_inic,
             num_ingr,
             num_rein,
             num_egre,
             num_acti_fina,
             num_pedi,
             num_unid_vend,
             val_vent_neta_esta,
             val_rete_2do_pedi,
             val_rete_3er_pedi,
             val_rete_4to_pedi,
             val_porc_egre,
             val_pup,
             val_ppu,
             val_rete_acti,
             val_porc_rota,
             num_posi_egre,
             val_rete_posi_egre
          )
          SELECT int_fvps_seq.NEXTVAL,
                 y.soci_oid_soci,
                 y.zzon_oid_zona,
                 y.perd_oid_peri,
                 y.zorg_oid_regi,
                 y.almc_oid_alma,
                 y.mone_oid_mone,
                 y.num_acti_inic,
                 y.num_ingr,
                 y.num_rein,
                 y.num_egre,
                 y.num_acti_fina,
                 y.num_pedi,
                 y.num_unid_vend,
                 y.val_vent_neta_esta,
                 y.val_rete_2do_pedi,
                 y.val_rete_3er_pedi,
                 y.val_rete_4to_pedi,
                 y.val_porc_egre,
                 y.val_pup,
                 y.val_ppu,
                 (
                  SELECT pzon.val_rete_acti
                    FROM int_sab_venta_previ_zona pzon
                   WHERE pzon.pais_cod_pais = y.cod_pais
                     AND pzon.cod_peri = y.cod_peri
                     AND pzon.cod_regi = y.cod_regi
                     AND pzon.cod_zona = y.cod_zona
                 ) val_rete_acti,
                 y.val_porc_rota,
                 y.num_posi_egre,
                 (
                  SELECT pzon.val_porc_rete_posi_egre
                    FROM int_sab_venta_previ_zona pzon
                   WHERE pzon.pais_cod_pais = y.cod_pais
                     AND pzon.cod_peri = y.cod_peri
                     AND pzon.cod_regi = y.cod_regi
                     AND pzon.cod_zona = y.cod_zona
                 ) val_rete_posi_egre
            FROM ( SELECT prev.cod_pais, prev.cod_peri, prev.cod_regi, prev.cod_zona,
                         0 soci_oid_soci,
                         GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA( prev.cod_pais,'T','VD', prev.cod_regi, prev.cod_zona) zzon_oid_zona,
                         GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( prev.cod_peri ) perd_oid_peri,
                         GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION( prev.cod_pais,'T','VD', prev.cod_regi) zorg_oid_regi,
                         0 almc_oid_alma,
                         NULL mone_oid_mone,
                         SUM(prev.val_acti_inic) num_acti_inic,
                         SUM(prev.val_ingr) num_ingr,
                         SUM(prev.val_rein) num_rein,
                         SUM(prev.val_egre) num_egre,
                         SUM(prev.act_fina) num_acti_fina,
                         SUM(prev.val_pedi) num_pedi,
                         SUM(prev.val_unid) num_unid_vend,
                         SUM(prev.ven_neta_cata) val_vent_neta_esta,
                         SUM(prev.val_rent_segp)/count(distinct prev.cod_secc) val_rete_2do_pedi,
                         SUM(prev.val_rent_terp)/count(distinct prev.cod_secc) val_rete_3er_pedi,
                         SUM(prev.val_rent_cuap)/count(distinct prev.cod_secc) val_rete_4to_pedi,
                         SUM(prev.por_egre)* 100 / SUM(prev.val_acti_inic) val_porc_egre,
                         SUM(prev.val_unid) / SUM(prev.val_pedi)  val_pup,
                         SUM(prev.ven_neta_cata) / SUM(prev.val_unid) val_ppu,
                         SUM(prev.por_rota)/count(distinct prev.cod_secc) val_porc_rota,
                         SUM(prev.pos_egre) num_posi_egre
                    FROM bps_secci_venta_previ prev
                   GROUP BY prev.cod_peri,
                            prev.cod_zona,
                            prev.cod_regi,
                            prev.cod_pais ) y;
     ELSE

     OPEN cursorTMP_FVP_Zona;
      LOOP
          -- Bulk collect data into memory table - X rows at a time
          FETCH cursorTMP_FVP_Zona BULK COLLECT INTO
                            v_COD_PERI
                            LIMIT W_FILAS;

          EXIT WHEN v_row_count_ins = cursorTMP_FVP_Zona%ROWCOUNT;
          v_row_count_ins := cursorTMP_FVP_Zona%ROWCOUNT;

          FOR i IN v_COD_PERI.FIRST .. v_COD_PERI.LAST loop
              DELETE FROM INT_SAB_VENTA_PREVI_ZONA where cod_peri=v_COD_PERI(i);
              DELETE FROM INT_FUENT_VENTA_PREVI_SAP where perd_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(v_COD_PERI(i));
          END LOOP;

      END LOOP;
     CLOSE cursorTMP_FVP_Zona;

    i    := 0;
    v_row_count_ins  := 0;

      OPEN curEstructuraTMP_FVP_ZONA;
  LOOP
    -- Bulk collect data into memory table - X rows at a time
    FETCH curEstructuraTMP_FVP_ZONA BULK COLLECT INTO
                            v_val_fila      ,
                            v_cod_pais   ,
                            v_cod_soci   ,
                            v_COD_MARC   ,
                            v_cod_alma   ,
                            v_COD_CANA   ,
                            v_COD_PERI   ,
                            v_cod_regi   ,
                            v_cod_zona   ,
                            v_ven_neta_cata ,
                            v_val_pedi   ,
                            v_val_acti_inic ,
                            v_VAL_INGR   ,
                            v_VAL_REIN   ,
                            v_val_egre   ,
                            v_val_cons_priv ,
                            v_val_clie_priv ,
                            v_val_tran_priv ,
                            v_val_rent_segp ,
                            v_val_rent_terp ,
                            v_val_rent_cuap ,
                            v_val_unid   ,
                            v_val_porc_rent ,
                            v_act_fina    ,
                            v_VAL_PUP           ,
                            v_VAL_PPU           ,
                            v_POR_ACTI     ,
                            v_REN_ACTI     ,
                            v_num_clie      ,
                            v_por_rota      ,
                            v_por_egre      ,
                            v_rez_reci      ,
                            v_rez_entr      ,
                            v_pos_egre      ,
                            v_act_lide      ,
                            v_PRO_SOLE_PEDI ,
                            v_val_acti_fina_c18
                            LIMIT W_FILAS;

    EXIT WHEN v_row_count_ins = curEstructuraTMP_FVP_ZONA%ROWCOUNT;
    v_row_count_ins := curEstructuraTMP_FVP_ZONA%ROWCOUNT;

    -- Bulk bind of data in memory table...
    FOR i IN v_val_fila.FIRST .. v_val_fila.LAST loop

            select oid_soci into lnOidSoci from SEG_SOCIE S where s.cod_soci=v_cod_soci(i);

             lnOidPeri:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(v_COD_PERI(i));
             lnOidZona:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA(v_cod_pais(i),v_COD_MARC(i),v_COD_CANA(i),v_cod_regi(i),v_cod_zona(i));
             lnOidALma:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ALMAC(v_cod_alma(i),GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(v_cod_pais(i)));

            select MONE_OID_MONE into lnOidMone from SEG_PAIS where COD_PAIS=v_cod_pais(i);

            SELECT  DECODE(COUNT(COR_VPZO), 0, '1',MAX(COR_VPZO)+1) into lnOidNextPkZona FROM INT_SAB_VENTA_PREVI_ZONA;

            insert into INT_SAB_VENTA_PREVI_ZONA
              (COR_VPZO          ,
                PAIS_COD_PAIS     ,
                COD_SOCI          ,
                COD_MARC          ,
                COD_ALMA          ,
                COD_CANA          ,
                COD_PERI          ,
                COD_REGI          ,
                COD_ZONA          ,
                NUM_ACIN          ,
                NUM_INGR          ,
                NUM_REIN          ,
                NUM_EGNE          ,
                POR_EGNE          ,
                NUM_EGPU          ,--Numero de Egresos Puros
                POR_EGPU          ,--Porcentaje de Egresos Puros
                VAL_CAPI          ,--Valor de Capitalizacion
                NUM_ACFI          ,
                POR_ACTI          ,
                NUM_PEDI          ,
                NUM_UNID          ,
                NUM_CLIE          ,
                PRO_SOPE          ,
                VAL_VENE          ,
                VAL_PPU           ,
                VAL_PUP           ,
                USU_DIGI          ,
                FEC_DIGI          ,
                USU_MODI          ,
                FEC_MODI          ,
                EST_VPZO          ,
                VAL_ACTI_LIDE     ,
                NUM_CONS_CLIE_PRIV,
                NUM_CLIE_INSC_PRIV,
                NUM_CLIE_TRAN_PRIV,
                VAL_RETE_2PED     ,
                VAL_RETE_3PED     ,
                VAL_RETE_4PED     ,
                VAL_RETE_ACTI     ,
                VAL_PORC_ROTA     ,
                VAL_POSI_EGRE     ,
                VAL_RETE_POSI_EGRE
               )
            values
                (lnOidNextPkZona,
                v_cod_pais(i)   ,
                v_cod_soci(i)   ,
                v_COD_MARC(i)   ,
                v_cod_alma(i)   ,
                v_COD_CANA(i)   ,
                v_COD_PERI(i)   ,
                v_cod_regi(i)   ,
                v_cod_zona(i)   ,
                v_val_acti_inic(i),
                v_VAL_INGR(i),
                v_VAL_REIN(i),
                0,
                0,
                v_val_egre(i),-- Numero de Egresos Puros
                v_por_egre(i),--Porcentaje de Egresos Puros
                v_VAL_INGR(i)+v_VAL_REIN(i)-v_val_egre(i),--Valor de Capitalizacion
                v_act_fina(i),
                v_POR_ACTI(i),
                v_val_pedi(i),
                v_val_unid(i),
                v_num_clie(i),
                v_PRO_SOLE_PEDI(i),
                v_ven_neta_cata(i),
                v_VAL_PPU(i),
                v_VAL_PUP(i),
                psUsuario,
                sysdate,
                null,
                null,
                1,
                v_act_lide(i),
                v_val_cons_priv(i),
                v_val_clie_priv(i),
                v_val_tran_priv(i),
                v_val_rent_segp(i),
                v_val_rent_terp(i),
                v_val_rent_cuap(i),
                v_REN_ACTI(i),
                v_por_rota(i),
                v_pos_egre(i),
                v_val_porc_rent(i)
               );

             insert into INT_FUENT_VENTA_PREVI_SAP
             (OID_FUEN_VENT_PREV ,
               COD_FUEN_VENT_PREV ,
               FEC_ANYO_COME      ,
               NUM_ACTI_INIC      ,
               NUM_ACTI_FINA      ,
               NUM_INGR           ,
               NUM_REIN           ,
               NUM_EGRE           ,
               NUM_REZO_RECI      ,
               NUM_REZO_ENTR      ,
               NUM_ORDE           ,
               NUM_PEDI           ,
               NUM_UNID_VEND      ,
               NUM_CLIE           ,
               VAL_VENT_NETA_ESTA ,
               FEC_CIER           ,
               SOCI_OID_SOCI      ,
               MONE_OID_MONE      ,
               PERD_OID_PERI      ,
               ZORG_OID_REGI      ,
               ZZON_OID_ZONA      ,
               ALMC_OID_ALMA      ,
               VAL_ACTI_INFL_ZONA ,
               NUM_CONS_CLIE_PRIV ,
               NUM_CLIE_INSC_PRIV ,
               NUM_CLIE_TRAN_PRIV ,
               VAL_RETE_2DO_PEDI  ,
               VAL_RETE_3ER_PEDI  ,
               VAL_RETE_4TO_PEDI  ,
               VAL_RETE_ACTI      ,
               VAL_PORC_ROTA      ,
               NUM_POSI_EGRE      ,
               VAL_RETE_POSI_EGRE ,
               VAL_PORC_EGRE      ,
               VAL_PUP            ,
               VAL_PPU
             )VALUES
             (  INT_FVPS_SEQ.Nextval,
               null,
               substr(v_COD_PERI(i),1,4),
               v_val_acti_inic(i),
               v_act_fina(i),
               v_VAL_INGR(i),
               v_VAL_REIN(i),
               v_val_egre(i),
               v_rez_reci(i),
               v_rez_entr(i),
               null,
               v_val_pedi(i),
               v_val_unid(i),
               v_num_clie(i),
               v_ven_neta_cata(i),
               null,
               lnOidSoci, --SOCI_OID_SOCI
               lnOidMone,--MONE_OID_MONE
               lnOidPeri,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(v_cod_pais(i),v_COD_MARC(i),v_COD_CANA(i),v_cod_regi(i)),
               lnOidZona,
               lnOidAlma,
               v_act_lide(i) ,
               v_val_cons_priv(i),
               v_val_clie_priv(i),
               v_val_tran_priv(i),
               v_val_rent_segp(i),
               v_val_rent_terp(i),
               v_val_rent_cuap(i),
               v_ren_acti(i),
               v_por_rota(i),
               v_pos_egre(i),
               v_val_porc_rent(i),
               v_por_egre(i),
               v_VAL_PUP(i),
               v_VAL_PPU(i)
             );

        END LOOP;
  END LOOP;
  CLOSE curEstructuraTMP_FVP_ZONA;

         UPDATE bps_zona_venta_previ SET fec_envi = SYSDATE;
     END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR BPS_PR_ACTUA_ESTRU_VENTA_PREVI: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Funcion que valida el UK de la tabla INT_FUENT_VENTA_PREVI_SAP
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
FUNCTION BPS_FN_REPIT_REGIS_VENTA_PREVI(
         psCodALma varchar2,
         psCodPeri varchar2,
         psCodSoci varchar2,
         psCodZona varchar2)
             RETURN NUMBER IS

    CURSOR cursorEstruFVP IS
    SELECT *
    FROM   BPS_CARGA_VENTA_PREVI
    ORDER  BY VAL_FILA;

  TYPE tTablaEstruFVP  IS TABLE OF BPS_CARGA_VENTA_PREVI%ROWTYPE;
  tablaRegistroEstruFVP            tTablaEstruFVP;
  registroEstruFVP                 BPS_CARGA_VENTA_PREVI%ROWTYPE;
  BEGIN
         OPEN cursorEstruFVP;
          LOOP
            FETCH cursorEstruFVP BULK COLLECT INTO tablaRegistroEstruFVP LIMIT W_FILAS;
            IF tablaRegistroEstruFVP.COUNT > 0 THEN
              FOR x IN tablaRegistroEstruFVP.FIRST .. tablaRegistroEstruFVP.LAST LOOP
                  registroEstruFVP := tablaRegistroEstruFVP(x);

                  IF(   (registroEstruFVP.Cod_Alma = psCodALma)
                     and (registroEstruFVP.Cod_Peri = psCodPeri)
                     and (registroEstruFVP.Cod_Soci = psCodSoci)
                     and (registroEstruFVP.Cod_Zona = psCodZona)  )THEN
                     return registroEstruFVP.Val_Fila;
                  END IF;
              END LOOP;
            END IF;
         EXIT WHEN cursorEstruFVP%NOTFOUND;
         END LOOP;
         CLOSE cursorEstruFVP;

    RETURN 0;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR BPS_FN_REPIT_REGIS_VENTA_PREVI: ' ||
                                ls_sqlerrm);
      END IF;
  END BPS_FN_REPIT_REGIS_VENTA_PREVI;

/***************************************************************************
Descripcion       : Funcion que valida el UK de la tabla INT_FUENT_VENTA_PREVI_SAP
Fecha Creacion    : 18/11/2014
Autor             : Sebastian Guerra
***************************************************************************/
FUNCTION BPS_FN_REPIT_REGIS_VENTA_PREVI(
         psCodPeri varchar2,
         psCodZona varchar2,
         psCodSecc varchar2)
             RETURN NUMBER IS

    CURSOR cursorEstruFVP IS
    SELECT *
    FROM   BPS_CARGA_VENTA_PREVI
    ORDER  BY VAL_FILA;

  TYPE tTablaEstruFVP  IS TABLE OF BPS_CARGA_VENTA_PREVI%ROWTYPE;
  tablaRegistroEstruFVP            tTablaEstruFVP;
  registroEstruFVP                 BPS_CARGA_VENTA_PREVI%ROWTYPE;
  BEGIN
         OPEN cursorEstruFVP;
          LOOP
            FETCH cursorEstruFVP BULK COLLECT INTO tablaRegistroEstruFVP LIMIT W_FILAS;
            IF tablaRegistroEstruFVP.COUNT > 0 THEN
              FOR x IN tablaRegistroEstruFVP.FIRST .. tablaRegistroEstruFVP.LAST LOOP
                  registroEstruFVP := tablaRegistroEstruFVP(x);

                  IF(   (registroEstruFVP.Cod_Peri = psCodPeri)
                     and (registroEstruFVP.Cod_Zona = psCodZona)
                     and (registroEstruFVP.Cod_Secc = psCodSecc)  )THEN
                     return registroEstruFVP.Val_Fila;
                  END IF;
              END LOOP;
            END IF;
         EXIT WHEN cursorEstruFVP%NOTFOUND;
         END LOOP;
         CLOSE cursorEstruFVP;

    RETURN 0;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR BPS_FN_REPIT_REGIS_VENTA_PREVI: ' ||
                                ls_sqlerrm);
      END IF;
  END BPS_FN_REPIT_REGIS_VENTA_PREVI;


/***************************************************************************
Descripcion       : Verifica campaña cerrada para un rango de campañas
Fecha Creacion    : 13/01/2008
Autor             : Rosalvina Ramirez
***************************************************************************/
FUNCTION BPS_FN_CAMPA_CERRA_VENTA_PREVI (
          psPais              varchar2,
          psPeriodoInicio     varchar2,
          psPeriodoFin        varchar2,
          psMarca             varchar2,
          psCanal             varchar2)
RETURN VARCHAR2 IS
  lsCodigoPeriodo                      VARCHAR2(6);
  lsCampCerrada                        VARCHAR2(1);
  ldFecFina                            DATE;
  lnCont                               NUMBER;
BEGIN
     lsCampCerrada:='0';
     lnCont:=0;
     LOOP
         lsCodigoPeriodo:=CUP_PKG_PROG_NUEVAS.GEN_FN_DEV_NSGTE_CAMPA(psPeriodoInicio,lnCont);

        SELECT P.FEC_FINA into ldFecFina
          FROM CRA_PERIO P, SEG_PERIO_CORPO A, SEG_CANAL C, SEG_MARCA D
         WHERE A.COD_PERI =lsCodigoPeriodo
           AND A.OID_PERI = P.PERI_OID_PERI
           AND P.CANA_OID_CANA = C.OID_CANA
           AND P.MARC_OID_MARC = D.OID_MARC
           AND C.COD_CANA = psCanal
           AND D.COD_MARC = psMarca
           AND P.PAIS_OID_PAIS =
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psPais);

         IF(TRUNC(SYSDATE) > ldFecFina)THEN
            lsCampCerrada:='1';
         END IF;

         lnCont:=lnCont+1;

    EXIT WHEN lsCodigoPeriodo=psPeriodoFin;
    END LOOP;
    RETURN lsCampCerrada;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR BPS_FN_CAMPA_CERRA_VENTA_PREVI:' ||
                                ls_sqlerrm);
      END IF;
  END BPS_FN_CAMPA_CERRA_VENTA_PREVI;

END BPS_PKG_PROCE;
/
