CREATE OR REPLACE PROCEDURE DAT_ENVIA_CLIENTES(STR_SQL IN VARCHAR2, STR_PATH IN VARCHAR2, STR_NOMBRE IN VARCHAR2, COD_INTE IN VARCHAR2, NUM_LOTE IN VARCHAR2, DESCRIPCION IN VARCHAR2, OBSER IN VARCHAR2, PAIS IN NUMBER, CRTI_CONS IN VARCHAR2 , PERIODO IN NUMBER) AS

TYPE C_CUR IS REF CURSOR;
cur_test C_CUR;

oidconsultora varchar2(12);
val_nomb_via varchar2(60);
cod_tipo_via varchar2(2);
codigoconsultora varchar2(15);
codigoconsultorarecomendante varchar2(15);
codifoterritorio varchar2(6);
nombre1 varchar2(25);
nombre2 varchar2(25);
apellidopaterno varchar2(25);
apellidomaterno varchar2(25);
nse varchar2(3);
anicampaniaingreso varchar2(6);
fechanacimineto date;
estadocivil varchar2(4000);
aniocapanaultimopedido varchar2(6);
indicadorestrella varchar2(1);
codigoultimocursorecibido varchar2(2);
tipodocumentoidentidad varchar2(3);
numerodocumentoidentidad varchar2(30);
indicadoractiva varchar2(1);
estatusventavigente varchar2(4000);
telefono varchar2(100);
telefonotrabajo varchar2(100);
direccion varchar2(80);
nombredepartamento varchar2(40);
provincia varchar2(40);
distrito varchar2(40);
descripciontipocentropoblado varchar2(4000);
descripcioncentropoblado varchar2(40);
nombre1lider varchar2(25);
nombre2lider varchar2(25);
apellido1lider varchar2(25);
apellido2lider varchar2(25);
codigotipocentropoblado varchar2(12);
codigocentropoblado varchar2(6);
orden1 varchar2(6);
orden2 varchar2(6);
orden3 varchar2(6);
orden4 varchar2(6);
orden5 varchar2(6);
orden6 varchar2(6);
orden7 varchar2(6);
orden8 varchar2(6);
orden9 varchar2(6);
indicadorlider varchar2(1);
indicadorgerentezona varchar2(1);
indicadorgerenteregion varchar2(1);
codigofuenteingresos varchar(3);
codigoanterior varchar2(15);
flagtipoaccion varchar2(1);
flagduplacyzone varchar2(1);

out_file  utl_file.file_type;
strSalida varchar2(32000);

fec_inicio date;
fec_fin date;
cant_procesos number;

BEGIN
  cant_procesos := 0;
  SELECT sysdate into fec_inicio from dual;

  OPEN cur_test FOR STR_SQL;
  out_file := UTL_FILE.FOPEN(STR_PATH, STR_NOMBRE, 'W');

  LOOP
     FETCH cur_test INTO
     oidconsultora , val_nomb_via ,
   cod_tipo_via , codigoconsultora ,
   codifoterritorio , nombre1 ,
   nombre2 , apellidopaterno ,
   apellidomaterno , nse ,
   anicampaniaingreso ,fechanacimineto ,
   estadocivil , aniocapanaultimopedido ,
   indicadorestrella , codigoultimocursorecibido ,
   tipodocumentoidentidad , numerodocumentoidentidad ,
   indicadoractiva ,estatusventavigente ,
   telefono , telefonotrabajo ,
   direccion ,   nombredepartamento ,
   provincia,  distrito ,
   descripciontipocentropoblado , descripcioncentropoblado ,
   nombre1lider , nombre2lider ,
   apellido1lider ,apellido2lider ,
   codigotipocentropoblado , codigocentropoblado ,
   orden1, orden2 , orden3, orden4, orden5 , orden6 ,
   orden7 , orden8 , orden9 , indicadorlider ,
   indicadorgerentezona ,  indicadorgerenteregion , codigoconsultorarecomendante,
   codigofuenteingresos , codigoanterior ,flagtipoaccion, flagduplacyzone;
   EXIT WHEN cur_test%NOTFOUND;
   cant_procesos := cant_procesos + 1;
   strSalida:= substr(NVL(codigoconsultora,''),0,10)||CHR(9)||
      substr(NVL(codifoterritorio,''),0,10)||CHR(9)||
      substr(nombre1||' '||nombre2||' '||apellidopaterno||' '||apellidomaterno,0,45)||CHR(9)||
      substr(nombre1||' '||nombre2,0,15)||CHR(9)||
      substr(NVL(apellidopaterno,''),0,15)||CHR(9)||
      substr(NVL(apellidomaterno,''),0,15)||CHR(9)||
      substr(NVL(nse,''),0,15)||CHR(9)||
      substr(NVL(anicampaniaingreso,''),0,6)||CHR(9)||
      to_char(NVL(fechanacimineto,to_date('01/01/1900','dd/mm/yyyy')),'yyyymmdd')||CHR(9)||
      substr(NVL(estadocivil,''),0,15)||CHR(9)||
      --substr(ltrim(NVL(to_char(pq_apl_rep.fn_cu_calc_saldo_cta_cte(oidconsultora),'999999999990.99'),'')),0,15)||CHR(9)||
      substr(ltrim(NVL(to_char(CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_TOTAL(TO_NUMBER(oidconsultora)),'999999999990.99'),'')),0,15)||CHR(9)||
      substr(NVL(aniocapanaultimopedido,''),0,6)||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      indicadorestrella||CHR(9)||
      ''||CHR(9)||
      substr(NVL(codigoultimocursorecibido,''),0,5)||CHR(9)||
      substr(NVL(tipodocumentoidentidad,'0'),0,3)||CHR(9)||
      substr(NVL(numerodocumentoidentidad,'0'),0,18)||CHR(9)||
      indicadoractiva||CHR(9)||
      substr(NVL(estatusventavigente,' '),0,15)||CHR(9)||
      ''||CHR(9)||
      substr(NVL(telefono,''),0,15)||CHR(9)||
      substr(NVL(telefonotrabajo,''),0,15)||CHR(9)||
      substr(NVL(nombredepartamento,''),0,10)||CHR(9)||
      substr(NVL(provincia,''),0,10)||CHR(9)||
      substr(NVL(distrito,''),0,25)||CHR(9)||
      direccion||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      ''||CHR(9)||
      substr(NVL(descripciontipocentropoblado,''),0,25)||CHR(9)||
      substr(NVL(descripcioncentropoblado,''),0,25)||CHR(9)||
      substr(nombre1lider||' '||nombre2lider||' '||apellido1lider||' '||apellido2lider,0,55)||CHR(9)||
      substr(NVL(codigotipocentropoblado,''),0,2)||CHR(9)||
      substr(NVL(codigocentropoblado,''),0,6)||CHR(9)||
      substr(NVL(orden1,'')||NVL(orden2,'')||
         NVL(orden3,'')||NVL(orden4,'')||
         NVL(orden5,'')||NVL(orden6,'')||
         NVL(orden7,'')||NVL(orden8,'')||
         NVL(orden9,''),0,54)||CHR(9)||
      indicadorlider||CHR(9)||
      indicadorgerentezona||CHR(9)||
      indicadorgerenteregion||CHR(9)||
      codigoconsultorarecomendante||CHR(9)||
      substr(NVL(codigofuenteingresos,''),0,1)||CHR(9)||
      substr(NVL(codigoanterior,''),0,10)||CHR(9)||
      ''||CHR(9)||
      flagduplacyzone||CHR(9)||
      flagtipoaccion;
   utl_file.put_line(out_file,strSalida);
    END LOOP;
  CLOSE cur_test;
  utl_file.fclose(out_file);
  SELECT sysdate into fec_fin from dual;
  INSERT INTO INT_HISTO_LOTES ( COD_INTE, NUM_LOTE, VAL_DESC_LOTE, FEC_INIC_PROC, FEC_FIN_PROC,
  IND_LOG_ERRO, NUM_REGI_PROC, NUM_REGI_ERRO, VAL_OBSE, PAIS_OID_PAIS, VAL_CRIT_CONS, PERI_OID_PERI ) VALUES (
  COD_INTE, NUM_LOTE, DESCRIPCION, fec_inicio, fec_fin, 0, cant_procesos, 0, OBSER, PAIS, CRTI_CONS, PERIODO);
END;
/

