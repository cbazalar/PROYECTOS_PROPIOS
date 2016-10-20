CREATE OR REPLACE PACKAGE SGR_PKG_PROCE_FAMSE IS
   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;
  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Proceso que encarag d eejecutar la validacion y/o rechazo de una poliza
                    segun indicador accion
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
     psCodigoPais                 : codigo pais
     psCodigoPoliza               : codigo poliza
     psNumeroCertificado          : numero de poliza
     psCodigoCliente              : codigo cliente
     psTipoDocumentoIdentidad     : tipo doc identidad
     psNumeroDocumentoIdent       : numero doc identidad
     psIndicadorAccion            : indicador accion 0:validacion 1:rechazo
     psMensajeResultado           : cadena d eparametrso separados por coma
***************************************************************************/
PROCEDURE SGR_PR_VALID_INSCR_POLIZ
(
 psCodigoPais                 VARCHAR2,
 psCodigoPoliza               VARCHAR2,
 psNumeroCertificado          VARCHAR2,
 psCodigoCliente              VARCHAR2,
 psTipoDocumentoIdentidad     VARCHAR2,
 psNumeroDocumentoIdent       VARCHAR2,
 psIndicadorAccion            VARCHAR2,
 psCodigoMotivoRechazo        VARCHAR2,
 psLogin                      VARCHAR2,
 psIndicadorNuevo             VARCHAR2,
 psMensajeResultado           OUT VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que se encarga de indentificar las polizas vigentes
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_CONSU_POLIZ_VIGEN(psCodigoPais VARCHAR2,psUsuario VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que encarag de cancelar las supcriciones
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_CANCE_INSCR_POLIZ(psCodigoPais VARCHAR2,psUsuario VARCHAR2);


/***************************************************************************
Descripcion       : Proceso que encarag de retirar las supcriciones
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_RETIR_INSCR_POLIZ(psCodigoPais VARCHAR2,psUsuario VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que genera el reporte de Control de Abonos
Fecha Creacion    : 27/05/2011
Autor             : Jose Luis Rodriguez
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_REPOR_CONTR_ABONO(
  psCodigoPais   VARCHAR2,
  psFechaInicio  VARCHAR2,
  psFechaFin     VARCHAR2,
  psUsuario      VARCHAR2);

/********************************************************************************
Descripcion       : Proceso que se encarga de actualizar las coberturas de las pólizas activas
Fecha Creacion    : 31/05/2013
Fecha Modificacion: 
Autor             :  FFVV
Parametros        :
********************************************************************************/
PROCEDURE SGR_PR_GENER_HISTO_COBER(
  psCodigoPais VARCHAR2, 
  psCodigoPeriodo VARCHAR2, 
  psFechaFacturacion VARCHAR2, 
  psUsuario VARCHAR2,
  pnFrecuencia number );

/***************************************************************************
Descripcion       : Proceso que devuelve descripcion Estado Poliza
Fecha Creacion    : 25/06/2013
Autor             : Carlos Chata
Parametros        :  
                  psCodEstado Estado de Poliza
***************************************************************************/
FUNCTION SGR_PR_DESCR_ESTAD_POLIZ(psCodEstado VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Proceso que devuelve descripcion Origen Poliza
Fecha Creacion    : 25/06/2013
Autor             : Carlos Chata
Parametros        :  
                  psCodEstado Origen de Poliza
***************************************************************************/
FUNCTION SGR_PR_DESCR_ORIGE_POLIZ(psCodEstado VARCHAR2)
RETURN VARCHAR2 ;

/********************************************************************************
Descripcion       : Proceso que se encarga de actualizar las clasificaciones de polizas activas
Fecha Creacion    : 31/05/2013
Fecha Modificacion:
Autor             :  Juan Gutierrez - FFVV
Parametros        :  psCodigoPais = Codigo pais
                     psCodigoPeriodo = Periodo
                     psFechaFacturacion = Fecha de facturacion
                     psUsuario = usuario
********************************************************************************/
PROCEDURE SGR_PR_ACTUA_CLASI_POLIZ(psCodigoPais VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaFacturacion VARCHAR2,
                                   psUsuario VARCHAR2
                                  );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte de Solicitudes
Fecha Creacion    : 14/01/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE SGR_PR_REPOR_SOLI(
    psCodigoPais                     VARCHAR2,
    psCodigoRegion                   VARCHAR2,
    psCodigoZona                     VARCHAR2,
    psCodigoSeccion                  VARCHAR2,
    psCodigoPeriodoInicio            VARCHAR2,
    psCodigoPeriodoFin               VARCHAR2,
    psCodigoPoliza                   VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psTitulo                         VARCHAR2,
    psDirectorio                     OUT  VARCHAR2
    ) ;

END SGR_PKG_PROCE_FAMSE;


/
CREATE OR REPLACE PACKAGE BODY SGR_PKG_PROCE_FAMSE IS

/********************************************************************************************
Descripcion       : Proceso que encargs de ejecutar la validacion y/o rechazo de una poliza
                    segun indicador accion
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
     psCodigoPais                 : codigo pais
     psCodigoPoliza               : codigo poliza
     psNumeroCertificado          : numero de poliza
     psCodigoCliente              : codigo cliente
     psTipoDocumentoIdentidad     : tipo doc identidad
     psNumeroDocumentoIdent       : numero doc identidad
     psIndicadorAccion            : indicador accion 0:validacion 1:rechazo
     psMensajeResultado           : cadena d eparametrso separados por coma
*********************************************************************************************/
PROCEDURE SGR_PR_VALID_INSCR_POLIZ
(
 psCodigoPais                 VARCHAR2,
 psCodigoPoliza               VARCHAR2,
 psNumeroCertificado          VARCHAR2,
 psCodigoCliente              VARCHAR2,
 psTipoDocumentoIdentidad     VARCHAR2,
 psNumeroDocumentoIdent       VARCHAR2,
 psIndicadorAccion            VARCHAR2,
 psCodigoMotivoRechazo        VARCHAR2,
 psLogin                      VARCHAR2,
 psIndicadorNuevo             VARCHAR2,
 psMensajeResultado           OUT VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidCliente                MAE_CLIEN.OID_CLIE%TYPE:=0;
  lsPeriodoActual             SEG_PERIO_CORPO.COD_PERI%TYPE;

  lsCodigoCliente             MAE_CLIEN.COD_CLIE%TYPE;
  lnExisteCliente             NUMBER;

  indicadorRechazoSTO         VARCHAR2(1):=' ';
  campanhaRegistro            SEG_PERIO_CORPO.COD_PERI%TYPE:=' ';
  sexo                        VARCHAR2(1):=' ';
  estadoCivil                 VARCHAR2(2):=' ';
  fechaNacimiento             VARCHAR2(10):=' ';
  edadCliente                 NUMBER:=0;
  numeroCampanhasAnt          NUMBER:=0;
  motivoRechazo               VARCHAR2(100);

  regFamiliaSeguraPoliza      SGR_FAMSE_POLIZ%ROWTYPE;
--  lnNumerAnt                  NUMBER;
  lsCodigoRegion              ZON_REGIO.COD_REGI%TYPE;
  lsCodigoZona                ZON_ZONA.COD_ZONA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lnExisteRegionCerrada       NUMBER;
  lnExisteRegion              NUMBER;
  lnExisteDocPrincipal        NUMBER;
  lnCantidad                  NUMBER;
  lsPeriodoPrimerPedido       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnCampanhas                 NUMBER:=0;
  codigoMotivoRechazo         VARCHAR2(5);

  lnNumSec                    NUMBER;
  lnNumLote                   STO_TIPO_DOCUM_DIGIT.NUM_LOTE%TYPE;

  lnOidTipoDocumento          NUMBER;
  lnOidZona                   NUMBER;
  lnOidRegion                 NUMBER;

  lsDesMotivo                 VARCHAR2(100);
  lnlongitudDocumento         NUMBER;

  vsIndValidaTipodoc          BAS_PARAM_PAIS.VAL_PARA%TYPE;
BEGIN
  psMensajeResultado:='';
  indicadorRechazoSTO:='0';
  --Recuperamos el oid Pais, Canal
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  --Obtenemos el Periodo Actual
  lsPeriodoActual := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(lnOidPais, 'T', lnOidCanal);


  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsPeriodoActual);


   select  * INTO regFamiliaSeguraPoliza
   from  SGR_FAMSE_POLIZ
   where IND_ACTI='1'
   AND EST_REGI<>'9';


   select NVL(MAX(NUM_CAMP_ANTI),0) into numeroCampanhasAnt
   from SGR_FAMSE_PARAM
   where est_regi='1';

   select VAL_PARA
   into vsIndValidaTipodoc
   from bas_param_pais
   where cod_pais = psCodigoPais
   and cod_sist = 'SGR'
   and cod_para = '003'
   and nom_para = 'indValidaTipoDoc';

    lnCampanhas:=0;
   --validamos que el codigo cliente exista, debido a que viene error x lista d ebeneficiario y aun no se ha validado
   --codigo d ecliente
   codigoMotivoRechazo:=psCodigoMotivoRechazo;


      if(psIndicadorAccion = '0') then -- se realiza las validaciones

        select count(1) into lnExisteCliente
        from mae_clien
        where cod_clie=psCodigoCliente;

        if(lnExisteCliente>0)then

            lnOidCliente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente);
            --SE OBTIENE SEXO, ESTADO CIVIL ,FECHA NACIMIENTO
           begin
            SELECT NVL(A.COD_SEXO,' '),
                   NVL(C.COD_ESTA_CIVI,' '),
                   NVL(TO_CHAR(B.FEC_NACI,'dd/MM/yyyy'),' ') into  sexo,estadoCivil,fechaNacimiento
            FROM MAE_CLIEN A,
                 MAE_CLIEN_DATOS_ADICI B,
                 MAE_ESTAD_CIVIL C
            WHERE A.OID_CLIE = B.CLIE_OID_CLIE
             AND A.OID_CLIE = lnOidCliente
             AND B.ESCV_OID_ESTA_CIVI= C.OID_ESTA_CIVI(+);
           exception
            when others then
              sexo:=' ';
              estadoCivil:=' ';
              fechaNacimiento:=' ';
           end;
/*
           --SE VALIDA QUE LA REGION DEL CLIENTE TENGA POLIZA VIGENTE PARA SU REGION
            lsCodigoRegion:=GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(lnOidCliente,'COD_REGI');

            select count(1) into lnExisteRegion
            from SGR_FAMSE_poliz x, SGR_FAMSE_vigen_poliz y
            where x.cod_poli = psCodigoPoliza
             and x.est_regi='1'
             and x.cod_poli = y.poli_cod_poli
             and y.est_regi=x.est_regi
             and Y.CAM_VIGE= lsPeriodoActual
             and Y.COD_REGI = lsCodigoRegion;

            if(lnExisteRegion = 0) then
               --motivo de rechazo 06
                          indicadorRechazoSTO:='1';
                          codigoMotivoRechazo:='06';
                          select NVL(REPLACE(REPLACE(DES_MOTI_RECH,'{0}',lsPeriodoActual),'{1}',lsCodigoRegion),' ')
                                into motivoRechazo
                          from sto_recha_motiv
                          where COD_PAIS=psCodigoPais
                            and  cod_tipo_docu='FAS'
                            and COD_MODU='SGR'
                            and COD_MOTI_RECH= codigoMotivoRechazo;

                           psMensajeResultado:=
                                indicadorRechazoSTO ||','||
                                lnOidCliente        ||','||
                                campanhaRegistro    ||','||
                                sexo                ||','||
                                estadoCivil         ||','||
                                fechaNacimiento     ||','||
                                edadCliente         ||','||
                                lnCampanhas         ||','||
                                codigoMotivoRechazo ||','||
                                motivoRechazo;

                             return;
            end if;
            */
            --se obtine la longitud del documento
             select  X.VAL_LONG into lnlongitudDocumento
             from   MAE_TIPO_DOCUM X
             where X.COD_TIPO_DOCU = psTipoDocumentoIdentidad;
------------
            IF vsIndValidaTipodoc = '1' THEN
              --se valida que el tipo de documento y numero corresponda a un documento
            select count(1) INTO lnExisteDocPrincipal
            from mae_clien_ident a
            where a.CLIE_OID_CLIE = lnOidCliente
            --and a.VAL_IDEN_DOCU_PRIN=1 ya no se valida principal tan solo que existe tipo y numero registrado
            and a.TDOC_OID_TIPO_DOCU =(select  X.OID_TIPO_DOCU
                                       from   MAE_TIPO_DOCUM X
                                        where X.COD_TIPO_DOCU = psTipoDocumentoIdentidad)
            and  upper(a.NUM_DOCU_IDEN) = upper(psNumeroDocumentoIdent);
            --and   LPAD(upper(NUM_DOCU_IDEN),lnlongitudDocumento,'0') = LPAD(upper(psNumeroDocumentoIdent),lnlongitudDocumento,'0');
            ELSE
               lnExisteDocPrincipal := 1;
            END IF;
---------------
            if(lnExisteDocPrincipal >0) then
                IF psIndicadorNuevo = '1' THEN
              --CALCULO DE LA EDAD
                select to_number(to_char(sysdate,'yyyy')) -
                       NVL((SELECT to_number(to_char(FEC_NACI,'yyyy'))
                        from MAE_CLIEN_datos_adici
                        where clie_oid_clie= lnOidCliente),0) into edadCliente
                from dual;
                ELSE
                  edadCliente := regFamiliaSeguraPoliza.EDA_MINI_SEGU;
                END IF;
                --edad en rango d ela poliza
                if(edadCliente>=regFamiliaSeguraPoliza.EDA_MINI_SEGU and edadCliente<=regFamiliaSeguraPoliza.EDA_MAXI_SEGU)then

                     --SE VALIDAD NUMERO DE ANTIGUEDAD si es que existe >= 1

                     if(numeroCampanhasAnt >= 1) then
                        --se verifica si ya cerro peidio
                        lsCodigoRegion:=GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(lnOidCliente,'COD_REGI');

                        SELECT COUNT(1) INTO lnExisteRegionCerrada
                        FROM FAC_CONTR_CIERR A,
                             FAC_TIPOS_CIERR B,
                             ZON_REGIO Z
                        WHERE A.TCIE_OID_TIPO_CIER = B.OID_TIPO_CIER
                          AND B.COD_TIPO_CIER ='R'
                          AND A.ZORG_OID_REGI = Z.OID_REGI
                          AND Z.COD_REGI= lsCodigoRegion
                          AND A.PERD_OID_PERI = lnOidPeriodo;

                          --se verifica si paso peidido

                           SELECT COUNT(1)
                             INTO lnCantidad
                             FROM PED_SOLIC_CABEC sc,
                             PED_TIPO_SOLIC_PAIS tsp,
                             PED_TIPO_SOLIC ts
                             --MAE_CLIEN cli
                             WHERE sc.pais_oid_pais = lnOidPais
                             AND sc.perd_oid_peri = lnOidPeriodo
                             AND sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
                             AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                             --AND ts.cod_tipo_soli = 'SOC'
                             --AND sc.grpr_oid_grup_proc = 5
                             AND sc.fec_fact IS NOT NULL
                             AND sc.clie_oid_clie = lnOidCliente
                             --AND cli.cod_clie = psCodigoCliente
                             AND sc.IND_TS_NO_CONSO = 1
                             AND sc.IND_OC = 1
                             AND sc.IND_PEDI_PRUE = 0
                             AND ts.IND_DEVO=0
                             AND ts.IND_ANUL=0;


                          IF(lnExisteRegionCerrada > 0 OR lnCantidad >0) THEN
                            campanhaRegistro := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (lsPeriodoActual, lnOidPais,lnOidMarca,lnOidCanal,1);
                          ELSE
                            campanhaRegistro := lsPeriodoActual;
                          END IF;

                           begin
                                SELECT A.COD_PERI
                                INTO lsPeriodoPrimerPedido
                                FROM SEG_PERIO_CORPO A,
                                     CRA_PERIO B,
                                     SEG_CANAL C,
                                     SEG_MARCA D
                                 WHERE A.OID_PERI = B.PERI_OID_PERI
                                   AND B.OID_PERI = (SELECT x.PERD_OID_PERI
                                                     FROM MAE_CLIEN_PRIME_CONTA x
                                                     WHERE x.CLIE_OID_CLIE = lnOidCliente)
                                   AND B.CANA_OID_CANA = C.OID_CANA
                                   AND B.MARC_OID_MARC = D.OID_MARC
                                   AND C.COD_CANA = 'VD'
                                   AND D.COD_MARC = 'T';

                             lnCampanhas:=  VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsPeriodoPrimerPedido,
                                                                                 lsPeriodoActual,
                                                                                 lnOidPais,
                                                                                 lnOidMarca,
                                                                                 lnOidCanal);
                           exception
                             when others then
                                lnCampanhas:=0;
                           end;


                         IF(lnCampanhas < numeroCampanhasAnt)THEN

                          --motivo de rechazo 04
                          indicadorRechazoSTO:='1';
                          codigoMotivoRechazo:='04';
                          select NVL(DES_MOTI_RECH,' ') into motivoRechazo
                          from sto_recha_motiv
                          where COD_PAIS=psCodigoPais
                            and  cod_tipo_docu='FAS'
                            and COD_MODU='SGR'
                            and COD_MOTI_RECH= codigoMotivoRechazo;

                           psMensajeResultado:=
                                indicadorRechazoSTO||','||
                                lnOidCliente ||','||
                                campanhaRegistro||','||
                                sexo               ||','||
                                estadoCivil        ||','||
                                fechaNacimiento    ||','||
                                edadCliente        ||','||
                                lnCampanhas         ||','||
                                codigoMotivoRechazo ||','||
                                motivoRechazo;

                             return;
                         ELSE
                          --PASO TODAS LAS VALIDACIONES
                            indicadorRechazoSTO:='0';
                            codigoMotivoRechazo:=' ';
                            motivoRechazo:=' ';
                            psMensajeResultado:=
                                indicadorRechazoSTO||','||
                                lnOidCliente ||','||
                                campanhaRegistro||','||
                                sexo               ||','||
                                estadoCivil        ||','||
                                fechaNacimiento    ||','||
                                edadCliente        ||','||
                                lnCampanhas         ||','||
                                codigoMotivoRechazo ||','||
                                motivoRechazo;
                         END IF;

                     ELSE
                        --PASO TODAS LAS VALIDACIONES

                            begin
                                SELECT A.COD_PERI
                                INTO lsPeriodoPrimerPedido
                                FROM SEG_PERIO_CORPO A,
                                     CRA_PERIO B,
                                     SEG_CANAL C,
                                     SEG_MARCA D
                                 WHERE A.OID_PERI = B.PERI_OID_PERI
                                   AND B.OID_PERI = (SELECT x.PERD_OID_PERI
                                                     FROM MAE_CLIEN_PRIME_CONTA x
                                                     WHERE x.CLIE_OID_CLIE = lnOidCliente)
                                   AND B.CANA_OID_CANA = C.OID_CANA
                                   AND B.MARC_OID_MARC = D.OID_MARC
                                   AND C.COD_CANA = 'VD'
                                   AND D.COD_MARC = 'T';

                             lnCampanhas:=  VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsPeriodoPrimerPedido,
                                                                                 lsPeriodoActual,
                                                                                 lnOidPais,
                                                                                 lnOidMarca,
                                                                                 lnOidCanal);
                           exception
                             when others then
                                lnCampanhas:=0;
                           end;

                            campanhaRegistro := lsPeriodoActual;
                            indicadorRechazoSTO:='0';
                            codigoMotivoRechazo:=' ';
                            motivoRechazo:=' ';
                            psMensajeResultado:=
                                indicadorRechazoSTO||','||
                                lnOidCliente ||','||
                                campanhaRegistro||','||
                                sexo               ||','||
                                estadoCivil        ||','||
                                fechaNacimiento    ||','||
                                edadCliente        ||','||
                                lnCampanhas         ||','||
                                codigoMotivoRechazo ||','||
                                motivoRechazo;

                     end if;

                else
                      --motivo de rechazo 03
                       indicadorRechazoSTO:='1';
                       codigoMotivoRechazo:='03';
                      select NVL(DES_MOTI_RECH,' ') into motivoRechazo
                      from sto_recha_motiv
                      where COD_PAIS=psCodigoPais
                        and  cod_tipo_docu='FAS'
                        and COD_MODU='SGR'
                        and COD_MOTI_RECH= codigoMotivoRechazo;

                       psMensajeResultado:=
                            indicadorRechazoSTO||','||
                            lnOidCliente ||','||
                            campanhaRegistro||','||
                            sexo               ||','||
                            estadoCivil        ||','||
                            fechaNacimiento    ||','||
                            edadCliente        ||','||
                            lnCampanhas          ||','||
                            codigoMotivoRechazo ||','||
                            motivoRechazo;

                return;

                end if;

            ELSE
              --motivo de rechazo 02
              indicadorRechazoSTO:='1';
              codigoMotivoRechazo:='02';
              select NVL(DES_MOTI_RECH,' ') into motivoRechazo
              from sto_recha_motiv
              where COD_PAIS=psCodigoPais
                and  cod_tipo_docu='FAS'
                and COD_MODU='SGR'
                and COD_MOTI_RECH= codigoMotivoRechazo;

               psMensajeResultado:=
                    indicadorRechazoSTO||','||
                    lnOidCliente ||','||
                    campanhaRegistro||','||
                    sexo               ||','||
                    estadoCivil        ||','||
                    fechaNacimiento    ||','||
                    edadCliente        ||','||
                    lnCampanhas          ||','||
                    codigoMotivoRechazo ||','||
                    motivoRechazo;

                return;
            end if;


         else
          --motivo de rechazo 01
          indicadorRechazoSTO:='1';
          codigoMotivoRechazo:='01';
          select NVL(DES_MOTI_RECH,' ') into motivoRechazo
          from sto_recha_motiv
          where COD_PAIS=psCodigoPais
            and  cod_tipo_docu='FAS'
            and COD_MODU='SGR'
            and COD_MOTI_RECH= codigoMotivoRechazo;

            psMensajeResultado:=
                indicadorRechazoSTO||','||
                lnOidCliente ||','||
                campanhaRegistro||','||
                sexo               ||','||
                estadoCivil        ||','||
                fechaNacimiento    ||','||
                edadCliente        ||','||
                lnCampanhas         ||','||
                codigoMotivoRechazo ||','||
                motivoRechazo;

            return;

        end if;

       return ;
      end if;--fin de validaciones

  --se realiza  grabar los rechazos a STO
  if(psIndicadorAccion = '1') then
            indicadorRechazoSTO:='1';

           if(codigoMotivoRechazo ='0') then
                select count(1) into lnExisteCliente
                from mae_clien
                where cod_clie=psCodigoCliente;

                if(lnExisteCliente=0)then
                   codigoMotivoRechazo:='01';
                else
                   codigoMotivoRechazo:='05';
                end if;

           end if;

            if(codigoMotivoRechazo='01') then
              lsCodigoRegion:='';
              lsCodigoZona:='';
              lnCampanhas:=NULL;
              lnOidCliente:=null;
              lnOidTipoDocumento:=null;
              lnOidZona:=null;
              lnOidRegion:=null;
              fechaNacimiento:=null;
            else

              lnOidCliente := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente);
              lsCodigoRegion:=GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(lnOidCliente,'COD_REGI');
              lsCodigoZona:=GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(lnOidCliente,'COD_ZONA');

              select  X.OID_TIPO_DOCU  INTO lnOidTipoDocumento
              from   MAE_TIPO_DOCUM X
              where X.COD_TIPO_DOCU = psTipoDocumentoIdentidad;


              SELECT OID_ZONA INTO lnOidZona
              FROM ZON_ZONA
              WHERE COD_ZONA= lsCodigoZona;

              SELECT OID_REGI INTO lnOidRegion
              FROM ZON_REGIO
              WHERE COD_REGI = lsCodigoRegion;

            begin
              SELECT A.COD_PERI
                INTO lsPeriodoPrimerPedido
                FROM SEG_PERIO_CORPO A,
                     CRA_PERIO B,
                     SEG_CANAL C,
                     SEG_MARCA D
                 WHERE A.OID_PERI = B.PERI_OID_PERI
                   AND B.OID_PERI = (SELECT x.PERD_OID_PERI
                                     FROM MAE_CLIEN_PRIME_CONTA x
                                     WHERE x.CLIE_OID_CLIE = lnOidCliente)
                   AND B.CANA_OID_CANA = C.OID_CANA
                   AND B.MARC_OID_MARC = D.OID_MARC
                   AND C.COD_CANA = 'VD'
                   AND D.COD_MARC = 'T';
             exception
              when others then
                lsPeriodoPrimerPedido:=null;
             end;


                 if( lsPeriodoPrimerPedido!= null and length(lsPeriodoPrimerPedido)>0)then

                  lnCampanhas:= VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsPeriodoPrimerPedido,
                                                                     lsPeriodoActual,
                                                                     lnOidPais,
                                                                     lnOidMarca,
                                                                     lnOidCanal);
                 else
                   lnCampanhas:=0;
                 end if;

                --edad cliente
                select to_number(to_char(sysdate,'yyyy')) -
                       (SELECT to_number(to_char(FEC_NACI,'yyyy'))
                        from MAE_CLIEN_datos_adici
                        where clie_oid_clie= lnOidCliente) into edadCliente
                from dual;

                --SE OBTIENE SEXO, ESTADO CIVIL ,FECHA NACIMIENTO
               begin
                SELECT NVL(A.COD_SEXO,' '),
                   NVL(C.COD_ESTA_CIVI,' '),
                   NVL(TO_CHAR(B.FEC_NACI,'dd/MM/yyyy'),' ') into  sexo,estadoCivil,fechaNacimiento
                FROM MAE_CLIEN A,
                     MAE_CLIEN_DATOS_ADICI B,
                     MAE_ESTAD_CIVIL C
                WHERE A.OID_CLIE = B.CLIE_OID_CLIE
                 AND A.OID_CLIE = lnOidCliente
                 AND B.ESCV_OID_ESTA_CIVI= C.OID_ESTA_CIVI(+);
               exception
                when others then
                  sexo:=' ';
                  estadoCivil:=' ';
                  fechaNacimiento:=null;
               end;


            end if;

            SELECT seq_docu_sto.nextval INTO lnNumSec FROM DUAL;
            --SELECT NVL(MAX(NUM_LOTE),0) into lnNumLote FROM STO_TIPO_DOCUM_DIGIT WHERE COD_TIPO_DOCU='FAS';
            SELECT STO_PKG_GENER.sto_fn_devue_nume_lote(psCodigoPais,'FAS') into lnNumLote  FROM DUAL;


            if(codigoMotivoRechazo='06') then

            select NVL(REPLACE(REPLACE(DES_MOTI_RECH,'{0}',lsPeriodoActual),'{1}',lsCodigoRegion),' ')  into lsDesMotivo
             from sto_recha_motiv
             where COD_PAIS=psCodigoPais
               and  cod_tipo_docu='FAS'
               and COD_MODU='SGR'
               and COD_MOTI_RECH= codigoMotivoRechazo;

            else
             select DES_MOTI_RECH into lsDesMotivo
             from sto_recha_motiv
             where COD_PAIS=psCodigoPais
                and  cod_tipo_docu='FAS'
                and COD_MODU='SGR'
                and COD_MOTI_RECH= codigoMotivoRechazo;

            end if;


            INSERT INTO STO_DOCUM_DIGIT (
               COD_PAIS,
               COD_TIPO_DOCU,
               NUM_LOTE,
               SEC_NUME_DOCU,
               IND_RECH,
               FEC_DIGI,
               USU_DIGI,
               COD_ZONA,
               COD_CLIE,
               COD_REGI,
               COD_PERI,
               COD_MOTI_RECH,
               VAL_OBSE_RECH_DEFI,
               NUM_DOCU,
               IND_ENVI,
               IND_RECE_DIGI)
            VALUES ( psCodigoPais,
                'FAS' ,
                lnNumLote ,
                lnNumSec,
                '0',
                SYSDATE,
                psLogin,
                lsCodigoZona,
                psCodigoCliente,
                lsCodigoRegion,
                lsPeriodoActual,
                codigoMotivoRechazo,
                lsDesMotivo,
                psNumeroCertificado,
                '0',
                '1');


            --INSERTANDO EN EL CONSOLIDADO DE FAM SEG , solo se esta insertando los datos basicos en al aplicacion se actualiza los
            ---Benficiarios si es que hubieran
                INSERT INTO INT_SOLIC_CONSO_FAMIL_SEGUR (
                   COD_PAIS,
                   FEC_PROC,
                   CAM_PROC,
                   COD_CLIE,
                   TIP_DOCU_IDEN,
                   NUM_DOCU_IDEN,
                   CAM_INIC,
                   SEC_NUME_DOCU,
                   NUM_LOTE,
                   CAM_REGIS,
                   CLIE_OID_CLIE,
                   TDOC_OID_TIPO_DOCU,
                   ZZON_OID_ZONA,
                   ZORG_OID_REGI,
                   FEC_NACI,
                   COD_SEXO,
                   COD_ESTA_CIVI,
                   VAL_EDAD,
                   COD_ESTA_OCR,
                   MOT_RECH_OCR,
                   CAM_ANTI,
                   NUM_DOCU,
                   IND_ORIG)
                VALUES(psCodigoPais,
                       SYSDATE ,
                       lsPeriodoActual,
                       psCodigoCliente,
                       psTipoDocumentoIdentidad ,
                       psNumeroDocumentoIdent ,
                       lsPeriodoActual,
                       lnNumSec ,
                       lnNumLote ,
                       lsPeriodoActual,
                       lnOidCliente,
                       lnOidTipoDocumento,
                       lnOidZona,
                       lnOidRegion,
                       TO_DATE(fechaNacimiento,'dd/MM/yyyy'),
                       sexo,
                       estadoCivil,
                       edadCliente,
                       '',
                       '',
                       --(select DES_MOTI_RECH from sto_recha_motiv where COD_PAIS=psCodigoPais and  cod_tipo_docu='FAS' and COD_MOTI_RECH= codigoMotivoRechazo),
                       lnCampanhas,
                       psNumeroCertificado,
                       'C'
                    );

                STO_PKG_GENER.sto_pr_updat_nume_lote(psCodigoPais,'FAS');

                psMensajeResultado:=indicadorRechazoSTO ||','||
                                    lnNumSec ||','||
                                    lnNumLote;

   return;
  end if;--fin de rechazoz


EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_VALID_INSCR_POLIZ: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END SGR_PR_VALID_INSCR_POLIZ;


/***************************************************************************
Descripcion       : Proceso que encarga de Cancelar las Polizas
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_CANCE_INSCR_POLIZ(psCodigoPais VARCHAR2,psUsuario VARCHAR2)
IS
    rCampanaProceso          bas_ctrl_fact%rowtype;
lnoidPeriodo CRA_PERIO.Oid_Peri%TYPE;

CURSOR c_policanc( vnoidPeriodo NUMBER, vnflagCierre NUMBER )
IS
SELECT poli.poli_cod_poli,
       poli.num_poli,
       poli.cod_clie,
       poli.clie_oid_clie,
       clhe.esta_oid_esta_clie
  FROM sgr_famse_poliz_regis poli,
       sgr_famse_estat_anula_poliz anul,
       mae_clien clie,
       mae_clien_histo_estat clhe
 WHERE poli.est_poli IN ('1','3')
   AND poli.est_regi != '9'
   AND anul.est_regi != '9' -- Nuevo
   AND clhe.perd_oid_peri = vnoidPeriodo
   --
   AND poli.poli_cod_poli = anul.poli_cod_poli
   AND poli.cod_clie = clie.cod_clie
   AND clie.oid_clie = clhe.clie_oid_clie
   AND clhe.esta_oid_esta_clie = anul.esta_oid_esta_clie
   AND vnflagCierre != 0 -- Nuevo
     ;

TYPE policanctab IS TABLE OF c_policanc%ROWTYPE;
polcan policanctab;

lnflagCierre INTEGER;
lnoidPais SEG_PAIS.Oid_Pais%TYPE;

BEGIN
   -- Capturar el registro actual del control de facturación
   SELECT *
     INTO rCampanaProceso
    FROM bas_ctrl_fact b
    WHERE b.sta_camp=0
      AND b.ind_camp_act=1
      AND ROWNUM = 1
        ;
   -- Obtener el oid de la campaña actual de proceso
   lnoidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( rCampanaProceso.Cod_Peri );

   -- Obtener el oid del pais
   SELECT pais.oid_pais
     INTO lnoidPais
     FROM seg_pais pais
    WHERE pais.cod_pais = pscodigoPais
        ;

   -- Verificar cierre de campaña
   BEGIN
       SELECT COUNT(1)
         INTO lnflagCierre
         FROM fac_contr_cierr fcc,
              fac_tipos_cierr ftc
        WHERE fcc.perd_oid_peri = lnoidPeriodo
          AND fcc.pais_oid_pais = lnoidPais
          AND fcc.tcie_oid_tipo_cier = ftc.oid_tipo_cier
          AND fcc.val_resu_proc = 'OK'
          AND ftc.cod_tipo_cier = 'P'
        GROUP BY fcc.perd_oid_peri
            ;
   EXCEPTION WHEN OTHERS THEN
       lnflagCierre := 0;
   END; -- Nuevo

   -- Cancelar las pólizas
   OPEN c_policanc( lnoidPeriodo, lnflagCierre );
   LOOP
       FETCH c_policanc BULK COLLECT INTO polcan LIMIT W_FILAS;

       IF polcan.COUNT > 0 THEN
          FOR i IN polcan.FIRST .. polcan.LAST LOOP
              UPDATE sgr_famse_poliz_regis poli
                 SET poli.est_poli = '4',
                     poli.fec_canc = rCampanaProceso.Fec_Proc,
                     poli.moti_cod_moti_canc = CASE
                                                  WHEN polcan(i).esta_oid_esta_clie = 5 THEN '1' -- Para Egresos
                                                  WHEN polcan(i).esta_oid_esta_clie = 7 THEN '2' -- Para Retiros
                                               END,
                     poli.cam_canc = rCampanaProceso.Cod_Peri,
                     poli.usu_modi = psUsuario,
                     poli.fec_modi = SYSDATE
               WHERE poli.poli_cod_poli = polcan(i).poli_cod_poli
                 AND poli.cod_clie = polcan(i).cod_clie
                 AND poli.num_poli = polcan(i).num_poli
                   ;
          END LOOP;
       END IF;
       EXIT WHEN c_policanc%NOTFOUND;
   END LOOP ;
   CLOSE c_policanc;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_CANCE_INSCR_POLIZ: '||ls_sqlerrm);
END SGR_PR_CANCE_INSCR_POLIZ;

/********************************************************************************
Descripcion       : Proceso que se encarga de indentificar las Polizas Vigentes
Fecha Creacion    : 18/05/2011
Fecha Modificacion: 10/04/2013
Autor             : CSVD - FFVV 
Parametros        :
********************************************************************************/
PROCEDURE SGR_PR_CONSU_POLIZ_VIGEN(psCodigoPais VARCHAR2,psUsuario VARCHAR2)
IS

  CURSOR c_consolidados(psCodigoPeriodo NUMBER,pnOidPeriodo NUMBER,fecProc DATE) IS
  
                SELECT SC.OID_SOLI_CABE,
                       X.CLIE_OID_CLIE,
                       Y.COD_VENT,
                       X.POLI_COD_POLI,
                       X.COD_CLIE,
                       X.NUM_POLI
                FROM PED_SOLIC_CABEC SC,
                    PED_TIPO_SOLIC_PAIS TSP,
                    PED_TIPO_SOLIC TS,
                    SGR_FAMSE_POLIZ_REGIS X,
                    SGR_FAMSE_KITS_POLIZ Y
               WHERE SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                    AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
                    AND SC.FEC_FACT IS NULL
                    AND SC.FEC_PROG_FACT = fecProc
                    AND SC.grpr_oid_grup_proc = 3
                    AND SC.PERD_OID_PERI = pnOidPeriodo
                    AND SC.IND_TS_NO_CONSO=1
                    AND SC.IND_OC=1
                    AND SC.IND_PEDI_PRUE=0
                    AND TS.IND_DEVO=0
                    AND TS.IND_ANUL=0
                    AND X.CLIE_OID_CLIE= sc.CLIE_OID_CLIE
                    AND X.EST_POLI='1'
                    AND X.EST_REGI='1'
                    AND Y.POLI_COD_POLI(+) = X.POLI_COD_POLI
                    AND Y.CAM_PROC(+)=psCodigoPeriodo
                    AND Y.EST_REGI(+)='1';

TYPE consolidadorecord IS RECORD (
    OID_SOLI_CABE           PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
    CLIE_OID_CLIE           SGR_FAMSE_POLIZ_REGIS.CLIE_OID_CLIE%TYPE,
    COD_VENT                SGR_FAMSE_KITS_POLIZ.COD_VENT%TYPE,
    POLI_COD_POLI           SGR_FAMSE_POLIZ_REGIS.POLI_COD_POLI%TYPE,
    COD_CLIE                SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    NUM_POLI                SGR_FAMSE_POLIZ_REGIS.NUM_POLI%TYPE
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;

lsCodigoPeriodo  SEG_PERIO_CORPO.COD_PERI%TYPE;
ldFechaProceso   DATE;
lnOidPeriodo     SEG_PERIO_CORPO.OID_PERI%TYPE;

regControl      BAS_CTRL_FACT%ROWTYPE;

lnOidProducto   PRE_OFERT_DETAL.PROD_OID_PROD%TYPE;
lnOidDetaOfer   PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;
lnPrecioPosic   PRE_OFERT_DETAL.IMP_PREC_POSI%TYPE;
  lsCodPoliActiva  SGR_FAMSE_POLIZ.COD_POLI%TYPE;
  lsIniDespachoKit BAS_PARAM_PAIS.VAL_PARA%TYPE;

BEGIN

  -- Obteniendo el codigo de la poliza activa
  SELECT a.cod_poli
    INTO lsCodPoliActiva
    FROM sgr_famse_poliz a
   WHERE a.ind_acti = '1'
     AND a.est_regi != '9';

  -- Obteniendo el valor del parametro valor Inicial Despacho Kit
  BEGIN
    SELECT val_para
      INTO lsIniDespachoKit
      FROM bas_param_pais
     WHERE cod_sist = 'SGR'
       AND cod_para = '006'
       AND nom_para = 'valInicialDespachoKit'
       AND ind_acti = '1';
  EXCEPTION 
    WHEN OTHERS THEN 
      lsIniDespachoKit := NULL;
  END;    

  -- En caso exista más de una póliza registrada para una misma consultora
  -- se toma la que tiene mayor número de póliza, y  se eliminan las otras
  UPDATE sgr_famse_poliz_regis
     SET est_regi = '9',
         usu_modi = psUsuario,
         fec_modi = SYSDATE
   WHERE num_poli IN
         (SELECT a.num_poli
            FROM sgr_famse_poliz_regis a
           WHERE a.est_regi = '1'
             AND a.est_poli = '1'
             AND a.cod_clie IN
                 (SELECT a2.cod_clie
                    FROM sgr_famse_poliz_regis a2
                   WHERE a2.est_regi = '1'
                     AND a2.est_poli = '1'
                   GROUP BY a2.cod_clie
                  HAVING COUNT(a2.cod_clie) >= 2)
             AND a.num_poli <> (SELECT MAX(a3.num_poli)
                                  FROM sgr_famse_poliz_regis a3
                                 WHERE a3.cod_clie = a.cod_clie
                                   AND a3.est_regi = '1'
                                   AND a3.est_poli = '1'))
     AND poli_cod_poli = lsCodPoliActiva;

  -- En caso la consultora del registro de póliza ya tenga una póliza anterior activa
  -- (EST_POLI = 3, EST_REGI = 1 y POLI_COD_POLI = [Código de Póliza Activa]),
  -- eliminar la póliza registrada (EST_REGI = '9')
  UPDATE sgr_famse_poliz_regis
     SET est_regi = '9',
         usu_modi = psUsuario,
         fec_modi = SYSDATE
   WHERE num_poli IN
         (SELECT a.num_poli
            FROM sgr_famse_poliz_regis a,
                 sgr_famse_poliz_regis a2
           WHERE a.cod_clie = a2.cod_clie
             AND a.est_poli = '1'
             AND a2.est_poli = '3'
             AND a.est_regi = '1'
             AND a2.est_regi = '1'
             AND a.poli_cod_poli = lsCodPoliActiva
             AND a2.poli_cod_poli = lsCodPoliActiva)
     AND poli_cod_poli = lsCodPoliActiva;

 -- Obtenemos parametros de campaña y fecha de proceso --
  SELECT *
    INTO regControl
    FROM bas_ctrl_fact
   WHERE sta_camp = 0
     AND ind_camp_act = 1;

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(regControl.cod_peri);

    -- Abrimos el cursor principal
  OPEN c_consolidados(regControl.cod_peri, lnOidPeriodo, regControl.fec_proc);
    LOOP
        FETCH c_consolidados BULK COLLECT
                        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN

          FOR i IN r_consolidado.FIRST..r_consolidado.LAST LOOP

           IF lsIniDespachoKit IS NULL OR (SUBSTR(r_consolidado(i).num_poli ,1, LENGTH(lsIniDespachoKit)) = lsIniDespachoKit) THEN
           
            IF(r_consolidado(i).cod_vent IS NOT NULL) THEN
              SELECT  b.prod_oid_prod ,
                      b.oid_deta_ofer ,
                      b.imp_prec_posi
                INTO  lnOidProducto,
                      lnOidDetaOfer,
                      lnPrecioPosic
                    FROM  pre_ofert a,
                          pre_ofert_detal b,
                          mae_produ c,
                          gen_i18n_sicc_pais d,
                          pre_tipo_ofert e,
                          cra_perio f,
                          seg_perio_corpo g,
                          pre_matri_factu_cabec h
               WHERE  a.oid_ofer = b.ofer_oid_ofer
                 AND  b.prod_oid_prod = c.oid_prod
                 AND  c.oid_prod = d.val_oid
                 AND  d.attr_enti ='MAE_PRODU'
                 AND  b.tofe_oid_tipo_ofer = e.oid_tipo_ofer
                 AND  a.mfca_oid_cabe = h.oid_cabe
                 AND  h.perd_oid_peri = f.oid_peri
                 AND  f.peri_oid_peri = g.oid_peri
                 AND  g.cod_peri = regControl.cod_peri
                 AND  b.val_codi_vent = r_consolidado(i).cod_vent;

    -- Cerramos el cursor
                   INSERT INTO PED_SOLIC_POSIC (
                        OID_SOLI_POSI,
                        COD_POSI,
                        NUM_UNID_DEMA,
                        NUM_UNID_POR_ATEN,
                        NUM_UNID_COMPR,
                        NUM_UNID_DEMA_REAL,
                        VAL_TASA_IMPU,
                        SOCA_OID_SOLI_CABE,
                        TPOS_OID_TIPO_POSI,
                        PROD_OID_PROD,
                        VAL_PREC_CATA_UNIT_LOCA,
                        VAL_PREC_CONT_UNIT_LOCA,
                        VAL_PREC_CATA_UNIT_DOCU,
                        VAL_PREC_CONTA_UNIT_DOCU,
                        OFDE_OID_DETA_OFER,
                        VAL_CODI_VENT,
                        ESPO_OID_ESTA_POSI,
                        STPO_OID_SUBT_POSI)
                VALUES ( PED_SOPO_SEQ.NEXTVAL,
                         (SELECT NVL(MAX(Z.COD_POSI),0) +1 FROM PED_SOLIC_POSIC Z WHERE Z.SOCA_OID_SOLI_CABE = r_consolidado(i).OID_SOLI_CABE),
                         1,
                         1,
                         1,
                         1,
                         0,
                         r_consolidado(i).OID_SOLI_CABE,
                         3,
                         lnOidProducto,
                         0,
                         lnPrecioPosic,
                         0,
                         lnPrecioPosic,
                         lnOidDetaOfer,
                         r_consolidado(i).COD_VENT,
                         4,
                         6 );
               END IF;
           END IF;

            UPDATE sgr_famse_poliz_regis X
               SET est_poli = '3', --ACTIVA
                   cam_acti = regControl.cod_peri,
                   fec_acti = regControl.fec_proc,
                   usu_modi = psUsuario,
                   fec_modi = SYSDATE
             WHERE poli_cod_poli = r_consolidado(i).poli_cod_poli
               AND cod_clie      = r_consolidado(i).cod_clie
               AND num_poli      = r_consolidado(i).num_poli
               AND est_poli     != '3';

            END LOOP;

        END IF;

        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;

END SGR_PR_CONSU_POLIZ_VIGEN;

/***********************************************************************************************
Descripcion       : Proceso que se encarga de retirar las suscripciones de las polizas vigentes
Fecha Creacion    : 18/05/2011
Autor             : Sergio Buchelli Silva
Parametros        :
************************************************************************************************/
PROCEDURE SGR_PR_RETIR_INSCR_POLIZ(psCodigoPais VARCHAR2,psUsuario VARCHAR2)
IS

     CURSOR c_consolidados is
                SELECT X.POLI_COD_POLI,
                       Y.COD_CLIE,
                       X.NUM_POLI,
                       X.EST_POLI,
                       Y.FEC_REGI,
                       Y.COD_TIPO_CANC
                FROM
                    SGR_FAMSE_POLIZ_REGIS X,
                    SGR_TMP_FAMSE_POLIZ_RETIR Y
                WHERE
                     X.NUM_POLI = Y.NUM_POLI
                    AND X.TIP_DOCU_IDEN = Y.TIP_DOCU_IDEN
                    AND X.NUM_DOCU_IDEN = Y.NUM_DOCU_IDEN
                    AND X.COD_CLIE= Y.COD_CLIE
                    AND X.EST_REGI='1';

     CURSOR c_cursorRetiroNoExistan is
                SELECT DISTINCT
                       NULL POLI_COD_POLI,
                       Y.COD_CLIE,
                       NULL NUM_POLI,
                       NULL EST_POLI,
                       NULL FEC_REGI,
                       NULL COD_TIPO_CANC
                FROM
                    SGR_TMP_FAMSE_POLIZ_RETIR Y
                WHERE
                      NOT EXISTS(SELECT NULL
                                       FROM SGR_FAMSE_POLIZ_REGIS Z
                                       WHERE Z.COD_CLIE = Y.COD_CLIE );





TYPE consolidadorecord IS RECORD (
    POLI_COD_POLI           SGR_FAMSE_POLIZ_REGIS.POLI_COD_POLI%TYPE,
    COD_CLIE                SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    NUM_POLI                SGR_FAMSE_POLIZ_REGIS.NUM_POLI%TYPE,
    EST_POLI                SGR_FAMSE_POLIZ_REGIS.EST_POLI%TYPE,
    FEC_REGI                SGR_TMP_FAMSE_POLIZ_RETIR.FEC_REGI%TYPE,
    COD_TIPO_CANC           SGR_TMP_FAMSE_POLIZ_RETIR.COD_TIPO_CANC%TYPE
);

TYPE consolidadotype IS TABLE OF consolidadorecord;
r_consolidado    consolidadotype;


BEGIN

    -- Abrimos el cursor principal
    OPEN c_consolidados;
    LOOP
        FETCH c_consolidados BULK COLLECT
                        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

                IF(r_consolidado(i).EST_POLI <> '4') THEN

                 IF(r_consolidado(i).COD_TIPO_CANC = 5)THEN

                      UPDATE SGR_FAMSE_POLIZ_REGIS X
                      SET EST_POLI = '4',
                          FEC_CANC = r_consolidado(i).FEC_REGI,
                          MOTI_COD_MOTI_CANC = '2',
                          USU_MODI = psUsuario,
                          FEC_MODI = SYSDATE
                      WHERE POLI_COD_POLI=  r_consolidado(i).POLI_COD_POLI
                       AND COD_CLIE=  r_consolidado(i).COD_CLIE
                       AND NUM_POLI=  r_consolidado(i).NUM_POLI;

                 ELSE
                     UPDATE SGR_FAMSE_POLIZ_REGIS X
                      SET EST_POLI = '4',
                          FEC_CANC = r_consolidado(i).FEC_REGI,
                          MOTI_COD_MOTI_CANC = '3' ,
                          USU_MODI = psUsuario,
                          FEC_MODI = SYSDATE
                      WHERE POLI_COD_POLI=  r_consolidado(i).POLI_COD_POLI
                       AND COD_CLIE=  r_consolidado(i).COD_CLIE
                       AND NUM_POLI=  r_consolidado(i).NUM_POLI;

                 END IF;

                ELSE
                  --GRABAR LOG ???
                  NULL;


                END IF;


            END LOOP;

        END IF;

        EXIT WHEN c_consolidados%NOTFOUND;
    END LOOP;


    -- GRABANDO EN EL ARCHIVO DE LOG LOQ NO EXISTE
    OPEN c_cursorRetiroNoExistan;
    LOOP
        FETCH c_cursorRetiroNoExistan BULK COLLECT
                        INTO r_consolidado LIMIT w_filas;

        IF  r_consolidado.COUNT > 0 THEN
            FOR i IN r_consolidado.FIRST..r_consolidado.LAST
            LOOP

             --GRABAR EL LOG
             NULL;

            END LOOP;

        END IF;

        EXIT WHEN c_cursorRetiroNoExistan%NOTFOUND;
    END LOOP;


END SGR_PR_RETIR_INSCR_POLIZ;

/***************************************************************************
Descripcion       : Proceso que genera el reporte de Control de Abonos
Fecha Creacion    : 27/05/2011
Autor             : Jose Luis Rodriguez
Parametros        :
***************************************************************************/
PROCEDURE SGR_PR_REPOR_CONTR_ABONO(psCodigoPais  VARCHAR2,
  psFechaInicio  VARCHAR2,
  psFechaFin     VARCHAR2,
                                   psUsuario     VARCHAR2) IS

BEGIN

 DELETE FROM sgr_tmp_contr_abono WHERE cod_usua = psUsuario;

 INSERT INTO sgr_tmp_contr_abono
    (num_secu, cod_clie, fec_pago, cod_regi, cod_zona, cod_peri, mon_pago, val_impo_poli, mon_pend, cod_usua)
    SELECT rownum,
           mc.cod_clie,
           abon.fec_pago,
          zr.cod_regi,
          zz.cod_zona,
           spc.cod_peri,
           abon.imp_pago,
           fse.val_impo_poli,
           abon.imp_pend,
           psUsuario
      FROM (SELECT mcc.perd_oid_peri, mcc.clie_oid_clie, mcc.fec_ulti_movi fec_pago, mcc.imp_pago, mcc.imp_pend
              FROM ccc_movim_cuent_corri mcc, ccc_proce cp, ccc_subpr CS, sgr_famse_param sp
             WHERE mcc.subp_oid_subp_crea = cs.oid_Subp
               AND cs.Ccpr_Oid_Proc = cp.oid_proc
               AND cp.cod_proc = sp.cod_proc
               AND cs.Cod_Subp = sp.cod_subp
               AND mcc.imp_paga > 0
               AND sp.est_regi = 1
            UNION ALL
            SELECT mcc.perd_oid_peri, his.clie_oid_clie, his.fec_movi fec_pago, his.imp_pago, his.imp_pen imp_pend
              FROM ccc_histo_movim_histo his, ccc_proce cp, ccc_subpr CS, ccc_movim_cuent_corri mcc, sgr_famse_param sp
             WHERE his.subp_oid_subp = cs.oid_Subp
               AND cs.Ccpr_Oid_Proc = cp.oid_proc
               AND cp.cod_proc = sp.cod_proc
               AND cs.Cod_Subp = sp.cod_subp
               AND sp.est_regi = 1
               AND mcc.oid_movi_cc = his.mvcc_oid_movi_cc) abon,
           mae_clien mc,
           cra_perio cp,
           seg_perio_corpo spc,
           mae_clien_unida_admin mcua,
           zon_terri_admin zta,
          zon_secci                               zs,
          zon_zona                                zz,
           zon_regio zr,
           ccc_histo_cargo_famil_segur fse
     WHERE abon.clie_oid_clie = mc.oid_clie
       AND abon.perd_oid_peri = cp.oid_peri
       AND cp.peri_oid_peri = spc.oid_peri
       AND mc.oid_clie = mcua.clie_oid_clie
       AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
       AND zta.zscc_oid_secc = zs.oid_secc
      AND zs.zzon_oid_zona = zz.oid_zona
      AND zz.zorg_oid_regi = zr.oid_regi
       AND fse.cod_clie = mc.cod_clie
       AND fse.cod_peri = spc.cod_peri
       AND mcua.ind_acti = 1
       AND trunc(abon.fec_pago) BETWEEN TO_DATE(psFechaInicio, 'DD/MM/YYYY') AND TO_DATE(psFechaFin, 'DD/MM/YYYY')
     ORDER BY spc.cod_peri, abon.fec_pago, zr.cod_regi, zz.cod_zona, mc.cod_clie;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_REPOR_CONTR_ABONO: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END SGR_PR_REPOR_CONTR_ABONO;


/********************************************************************************
Descripcion       : Proceso que se encarga de actualizar las coberturas de las pólizas activas
Fecha Creacion    : 31/05/2013
Fecha Modificacion: 
Autor             :  Juan Gutierrez - FFVV
Parametros        :  psCodigoPais = Código país
                     psCodigoPeriodo = Periodo
                     psFechaFacturación = Fecha de facturación
                     psUsuario = usuario
                     pnFrecuencia (1 :Diaria, 2: Cierre Region  3:Cierre Campaña
********************************************************************************/
PROCEDURE SGR_PR_GENER_HISTO_COBER(psCodigoPais VARCHAR2,
                                   psCodigoPeriodo VARCHAR2, 
                                   psFechaFacturacion VARCHAR2, 
                                   psUsuario VARCHAR2,
                                   pnFrecuencia number )
IS
   CURSOR c_consolidado(pnOidPeriodo NUMBER,pdFecCober DATE) IS      

              SELECT DISTINCT
                     fpr.POLI_COD_POLI,
                     fpr.COD_CLIE,
                     fpr.NUM_POLI,
                     psCodigoPeriodo,
                     soca.fec_fact,
                     sfp.ind_pegs
                FROM SGR_FAMSE_POLIZ_REGIS fpr,
                     ped_solic_cabec soca,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic tsol,
                     sgr_famse_poliz sfp
               WHERE fpr.EST_POLI = '3' -- Poliza Activa
                 AND fpr.EST_REGI != '9'
                 AND fpr.poli_cod_poli = sfp.cod_poli 
                 AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                 AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                 AND fpr.CLIE_OID_CLIE = soca.clie_oid_clie
                 AND soca.perd_oid_peri = pnOidPeriodo
                 AND soca.fec_fact = pdFecCober
                 AND tsol.cod_tipo_soli = 'SOC' -- Tipo de solicitud OC
                 AND soca.ind_oc = 1 -- Indicador de OC = 1
                 AND soca.grpr_oid_grup_proc = 5; -- gp5 pedido facturado

   CURSOR c_region( psCampCober VARCHAR2, pFecCober DATE) IS      
        
              SELECT DISTINCT
                     fpr.POLI_COD_POLI,
                     fpr.COD_CLIE,
                     fpr.NUM_POLI,
                     psCodigoPeriodo,
                     pFecCober,
                     sfp.ind_pegs
                FROM SGR_FAMSE_POLIZ_REGIS fpr,
                     mae_clien_datos_adici macd,
                     sgr_famse_poliz       sfp,
                     mae_clien_unida_admin cuad,
                     zon_terri_admin       ztad,
                     zon_secci             zscc,
                     zon_terri             terr,
                     zon_zona              zzon,
                     zon_regio             zorg,
                     fac_progr_cierr       fpci
               WHERE fpr.EST_POLI = '3'
                 AND fpr.EST_REGI <> '9'
                 AND macd.ESTA_OID_ESTA_CLIE = 4 --posible egreso
                 AND macd.IND_ACTI = 1 -- activa
                 AND sfp.ind_pegs = '1' -- IND PEGS ASEGURADORA
                 AND fpr.POLI_COD_POLI = sfp.cod_poli
                 AND fpr.CLIE_OID_CLIE = macd.clie_oid_clie
                 AND macd.clie_oid_clie = cuad.clie_oid_clie
                 AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                 AND ztad.zscc_oid_secc = zscc.oid_secc
                 AND ztad.terr_oid_terr = terr.oid_terr
                 AND zscc.zzon_oid_zona = zzon.oid_zona
                 AND zzon.zorg_oid_regi = zorg.oid_regi
                 AND cuad.ind_acti = 1
                 AND fpci.tip_cier = 'R'
                 AND fpci.est_cier = 'P'
                 AND fpci.cam_proc = psCampCober
                 AND fpci.cod_regi = zorg.cod_regi
                 AND fpci.fec_cier = pFecCober
				         AND fpci.est_regi = '1';       
   
   CURSOR c_campana( psCampCober VARCHAR2, pFecCober DATE) IS    

              SELECT DISTINCT
                     X.POLI_COD_POLI,
                     X.COD_CLIE,
                     X.NUM_POLI,
                     psCampCober,
                     pFecCober,
                     sfp.ind_pegs 
                FROM SGR_FAMSE_POLIZ_REGIS X,
                     mae_clien_datos_adici macd,
                     sgr_famse_poliz       sfp
               WHERE X.EST_POLI = '3'
                 AND X.EST_REGI != '9'
                 AND sfp.ind_pegs = '1'
                 AND sfp.cod_poli = X.poli_cod_poli  
                 AND X.CLIE_OID_CLIE = macd.clie_oid_clie
                 AND macd.ESTA_OID_ESTA_CLIE = 4 --posible egreso
                 AND macd.IND_ACTI = 1 ;-- activa
 

  lnIdPeriodo      CRA_PERIO.OID_PERI%TYPE;
  nFlag            NUMBER;
  nPeg             NUMBER;
  nCierreCamp      NUMBER;
  pFecCober        DATE;
  sIndPeg          VARCHAR2(1); 
  nOcu             NUMBER;
  
  TYPE consolidadorecord IS RECORD (
    POLI_COD_POLI     SGR_FAMSE_POLIZ_REGIS.POLI_COD_POLI%TYPE,              
    COD_CLIE          SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    POLR_NUM_POLI     SGR_FAMSE_POLIZ_REGIS.NUM_POLI%TYPE,
    CAM_COBE          VARCHAR2(6),
    FEC_COBE          ped_solic_cabec.fec_fact%TYPE ,  
    IND_PEGS          VARCHAR2(1)   
  );
  

  TYPE consolidadotype IS TABLE OF consolidadorecord;
  r_consolidado    consolidadotype;
  
  BEGIN

    -- Fecha Facturacion             
    SELECT TO_DATE(psFechaFacturacion,'DD/MM/YYYY') INTO pFecCober FROM dual ;

      -- Obtener ID Periodo --
    lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
 
    IF (pnFrecuencia = 1) THEN -- DIARIO  
  
      OPEN c_consolidado(lnIdPeriodo , pFecCober);
      LOOP
        FETCH c_consolidado BULK COLLECT
          INTO r_consolidado LIMIT w_filas;
            IF  r_consolidado.COUNT > 0 THEN
                FOR i IN r_consolidado.FIRST..r_consolidado.LAST LOOP
                    nPeg  :=0;
                    nFlag :=0;
                    sIndPeg :=   r_consolidado(i).IND_PEGS ;
                    
                    SELECT nvl(count(1),0)  
                    INTO nFlag
                    FROM SGR_FAMSE_HISTO_COBER_POLIZ P
                    WHERE P.POLI_COD_POLI = r_consolidado(i).POLI_COD_POLI
                    AND   P.COD_CLIE = r_consolidado(i).COD_CLIE
                    AND   P.POLR_NUM_POLI = r_consolidado(i).POLR_NUM_POLI
                    AND   P.CAM_COBE = r_consolidado(i).CAM_COBE;
                   
                    IF (nFlag > 0 AND sIndPeg ='1') THEN  --SI EXISTEN REGISTROS PREVIOS EN LA CAMPAÑA PARA UN CLIENTE 
                      
                     DELETE SGR_FAMSE_HISTO_COBER_POLIZ P        --SI ENCUENTRA PEG DESPUES DE CIERRE REGION  
                    WHERE P.POLI_COD_POLI = r_consolidado(i).POLI_COD_POLI
                    AND   P.COD_CLIE = r_consolidado(i).COD_CLIE
                    AND   P.POLR_NUM_POLI = r_consolidado(i).POLR_NUM_POLI
                    AND   P.CAM_COBE = r_consolidado(i).CAM_COBE
                     AND   P.IND_PEGS = 1;  
                     nPeg  := SQL%ROWCOUNT;
                   
                      END IF; 
                    
                    IF (nFlag = 0 or nFlag = nPeg) THEN -- CLIENTE NO COBERTURADO EN CAMPAÑA Y SE PROCEDERA A LA INSERSION DE SU COBERTURA
                    
                      INSERT INTO SGR_FAMSE_HISTO_COBER_POLIZ(
                      POLI_COD_POLI,
                      COD_CLIE,
                      POLR_NUM_POLI,
                      CAM_COBE,
                      FEC_COBE,
                      IND_PEGS,
                      USU_CREA,
                      FEC_CREA,
                      EST_REGI)
                      VALUES (
                      r_consolidado(i).POLI_COD_POLI ,
                      r_consolidado(i).COD_CLIE ,
                      r_consolidado(i).POLR_NUM_POLI,
                      r_consolidado(i).CAM_COBE ,
                      r_consolidado(i).FEC_COBE,
                      '0',
                      psUsuario,
                      TO_DATE(sysdate,'dd/MM/yyyy'),
                      '1');
                    
                    END IF;
                END LOOP;
            END IF;
            EXIT WHEN c_consolidado%NOTFOUND;
      END LOOP; 
      commit;
    END IF;
    
    IF (pnFrecuencia = 2) THEN -- cierre  REGION 
        
     OPEN c_region(psCodigoPeriodo, pFecCober);
      LOOP
        FETCH c_region BULK COLLECT
          INTO r_consolidado LIMIT w_filas;
            IF  r_consolidado.COUNT > 0 THEN
                FOR i IN r_consolidado.FIRST..r_consolidado.LAST LOOP
                  
                    nFlag :=0;
                    SELECT nvl(count(1),0) 
                    INTO nFlag
                    FROM SGR_FAMSE_HISTO_COBER_POLIZ P
                    WHERE P.POLI_COD_POLI = r_consolidado(i).POLI_COD_POLI
                    AND   P.COD_CLIE = r_consolidado(i).COD_CLIE
                    AND   P.POLR_NUM_POLI = r_consolidado(i).POLR_NUM_POLI
                    AND   P.CAM_COBE = r_consolidado(i).CAM_COBE;
               
                    IF (nFlag = 0) THEN
                      INSERT INTO SGR_FAMSE_HISTO_COBER_POLIZ(
                      POLI_COD_POLI,
                      COD_CLIE,
                      POLR_NUM_POLI,
                      CAM_COBE,
                      FEC_COBE,
                      IND_PEGS,
                      USU_CREA,
                      FEC_CREA,
                      EST_REGI)
                      VALUES (   
                      r_consolidado(i).POLI_COD_POLI ,
                      r_consolidado(i).COD_CLIE ,
                      r_consolidado(i).POLR_NUM_POLI,
                      r_consolidado(i).CAM_COBE ,
                      r_consolidado(i).FEC_COBE,
                      '1',
                      psUsuario,
                      TO_DATE(sysdate,'dd/MM/yyyy'),
                      '1');
                    END IF;  
                 END LOOP;
            END IF;
            EXIT WHEN c_region%NOTFOUND;
         END LOOP;
      COMMIT;
    END IF;

    IF (pnFrecuencia = 3) THEN -- CIERRE CAMPAÑA
       
       DELETE SGR_FAMSE_HISTO_COBER_POLIZ P
       WHERE P.IND_PEGS = '1' 
       AND P.CAM_COBE =  psCodigoPeriodo;
        
       OPEN c_campana(psCodigoPeriodo, pFecCober);
      LOOP
          FETCH c_campana BULK COLLECT
          INTO r_consolidado LIMIT w_filas;
            IF  r_consolidado.COUNT > 0 THEN
                FOR i IN r_consolidado.FIRST..r_consolidado.LAST LOOP
                   
                    nFlag :=0;
                    SELECT nvl(count(1),0) 
                    INTO nFlag
                    FROM SGR_FAMSE_HISTO_COBER_POLIZ P
                    WHERE P.POLI_COD_POLI = r_consolidado(i).POLI_COD_POLI
                    AND   P.COD_CLIE = r_consolidado(i).COD_CLIE
                    AND   P.POLR_NUM_POLI = r_consolidado(i).POLR_NUM_POLI
                    AND   P.CAM_COBE = r_consolidado(i).CAM_COBE;
               
                      IF (nFlag = 0) THEN
                      INSERT INTO SGR_FAMSE_HISTO_COBER_POLIZ(
                      POLI_COD_POLI,
                      COD_CLIE,
                      POLR_NUM_POLI,
                      CAM_COBE,
                      FEC_COBE,
                      IND_PEGS,
                      USU_CREA,
                      FEC_CREA,
                      EST_REGI)
                      VALUES(   
                      r_consolidado(i).POLI_COD_POLI ,
                      r_consolidado(i).COD_CLIE ,
                      r_consolidado(i).POLR_NUM_POLI,
                      r_consolidado(i).CAM_COBE ,
                      r_consolidado(i).FEC_COBE,
                      '1',
                      psUsuario,
                      TO_DATE(sysdate,'dd/MM/yyyy'),
                      '1');
                    END IF;  
                 END LOOP;
            END IF;
              EXIT WHEN c_campana%NOTFOUND;
      END LOOP;
      COMMIT;
    END IF;
       
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_GENER_HISTO_COBER: (' || ln_sqlcode || ')' || ls_sqlerrm);
    
  END SGR_PR_GENER_HISTO_COBER;

/***************************************************************************
Descripcion       : Proceso que devuelve descripcion Estado Poliza
Fecha Creacion    : 25/06/2013
Autor             : Carlos Chata
Parametros        :  
                  psCodEstado Estado de Poliza
***************************************************************************/
FUNCTION SGR_PR_DESCR_ESTAD_POLIZ(psCodEstado VARCHAR2)
RETURN VARCHAR2 
IS
BEGIN
  IF psCodEstado = '1' THEN
     RETURN 'ACEPTADA / REGISTRADA';
  END IF;
  IF psCodEstado = '2' THEN
     RETURN 'RECHAZADA';
  END IF;
  IF psCodEstado = '3' THEN
     RETURN 'ACTIVA';
  END IF;
  IF psCodEstado = '4' THEN
     RETURN 'CANCELADA';
  END IF;
  RETURN '';
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_DESCR_ESTAD_POLIZ: (' || ln_sqlcode || ')' || ls_sqlerrm);              
END SGR_PR_DESCR_ESTAD_POLIZ;

/***************************************************************************
Descripcion       : Proceso que devuelve descripcion Origen Poliza
Fecha Creacion    : 25/06/2013
Autor             : Carlos Chata
Parametros        :  
                  psCodEstado Origen de Poliza
***************************************************************************/
FUNCTION SGR_PR_DESCR_ORIGE_POLIZ(psCodEstado VARCHAR2)
RETURN VARCHAR2 
IS
BEGIN
  IF psCodEstado = 'C' THEN
     RETURN 'SISTEMA COMERCIAL';
  END IF;
  IF psCodEstado = 'W' THEN
     RETURN 'WEB';
  END IF;
  IF psCodEstado = 'O' THEN
     RETURN 'OCR';
  END IF;
  IF psCodEstado = 'B' THEN
     RETURN 'BLACKBERRY';
  END IF;
  RETURN '';
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_DESCR_ORIGE_POLIZ: (' || ln_sqlcode || ')' || ls_sqlerrm);              
END SGR_PR_DESCR_ORIGE_POLIZ;

/********************************************************************************
Descripcion       : Proceso que se encarga de actualizar las clasificaciones de polizas activas
Fecha Creacion    : 31/05/2013
Fecha Modificacion:
Autor             :  Juan Gutierrez - FFVV
Parametros        :  psCodigoPais = Codigo pais
                     psCodigoPeriodo = Periodo
                     psFechaFacturacion = Fecha de facturacion
                     psUsuario = usuario
********************************************************************************/
PROCEDURE SGR_PR_ACTUA_CLASI_POLIZ(psCodigoPais VARCHAR2,
                                   psCodigoPeriodo VARCHAR2,
                                   psFechaFacturacion VARCHAR2,
                                   psUsuario VARCHAR2
                                  )
IS



 lsNroSinClasi     NUMBER(10);
 lsNroAntiguas     NUMBER(10);
 lsNroCancelada    NUMBER(10);
 lsTipoConsultora  NUMBER(12);
 lsTClaFamPro      NUMBER(12);
 lsClaPrimPed      NUMBER(12);
 lsOidPeriodo      NUMBER(12);
 

 CURSOR c_nuevas (lsTipoConsultora VARCHAR2) IS

  SELECT DISTINCT
         fpr.clie_oid_clie,
         fpr.cod_clie,
         fpr.num_poli,
         mcts.oid_clie_tipo_subt,
         mcts.ticl_oid_tipo_clie,
         mcts.sbti_oid_subt_clie
    FROM sgr_famse_poliz_regis fpr,
         mae_clien_tipo_subti mcts,
         gen_i18n_sicc_comun gsub,
         (select distinct cts.clie_oid_clie 
          from mae_clien_tipo_subti cts,
               mae_clien_clasi ccli,
               (select VAL_OID from gen_i18n_sicc_comun
                where attr_enti = 'MAE_SUBTI_CLIEN'
                AND VAL_I18N IN ('Oficina','Negocio')) sbt,
               (select distinct val_oid from gen_i18n_sicc_comun
                where attr_enti = 'MAE_TIPO_CLASI_CLIEN'
                and val_i18n = 'Familia Protegida') tcf 
          where cts.ticl_oid_tipo_clie = lsTipoConsultora 
            and cts.sbti_oid_subt_clie = sbt.VAL_OID 
            and ccli.tccl_oid_tipo_clasi <> tcf.val_oid
            and ccli.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt ) cla
   WHERE fpr.est_regi <> 9
     AND fpr.est_poli = 1   
     AND fpr.clie_oid_clie = cla.clie_oid_clie
     AND mcts.clie_oid_clie = fpr.clie_oid_clie
     AND mcts.ticl_oid_tipo_clie = lsTipoConsultora
     AND mcts.sbti_oid_subt_clie = gsub.val_oid 
     AND gsub.attr_enti = 'MAE_SUBTI_CLIEN' 
     AND gsub.val_i18n IN ('Oficina','Negocio');
 
 TYPE nuevasrecord IS RECORD (
    CLIE_OID_CLIE            SGR_FAMSE_POLIZ_REGIS.clie_oid_clie%TYPE,
    COD_CLIE                 SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    POLR_NUM_POLI            SGR_FAMSE_POLIZ_REGIS.NUM_POLI%TYPE,
    OID_CLIE_TIPO_SUBT       NUMBER(12),
    TICL_OID_TIPO_CLIE       NUMBER(12),
    SBTI_OID_SUBT_CLIE       NUMBER(12)
  );

  TYPE nuevastype IS TABLE OF nuevasrecord;
  r_nuevas    nuevastype;


CURSOR c_antiguas (lsTipoConsultora VARCHAR2) IS

  SELECT  fpr.clie_oid_clie,
          fpr.cod_clie,
          ccli.oid_clie_clas,
          ccli.ctsu_oid_clie_tipo_subt ,
          ccli.tccl_oid_tipo_clasi ,
          (select mc.oid_clas 
           from mae_clasi mc ,
               (select val_oid from gen_i18n_sicc_comun
                where attr_enti = 'MAE_CLASI'
                and val_i18n = 'Antigua') cl
           where mc.tccl_oid_tipo_clas = ccli.tccl_oid_tipo_clasi
           and  mc.oid_clas = cl.val_oid ) oid_clas_anti
          
  FROM    sgr_famse_poliz_regis fpr,
          (select val_oid from gen_i18n_sicc_comun
           where attr_enti = 'MAE_TIPO_CLASI_CLIEN'
           and val_i18n = 'Familia Protegida') dtcc,
          (select val_oid from gen_i18n_sicc_comun
           where attr_enti = 'MAE_CLASI'
           and val_i18n = 'Primer Pedido') dcc,
          (select VAL_OID from gen_i18n_sicc_comun
           where attr_enti = 'MAE_SUBTI_CLIEN'
           AND VAL_I18N IN ('Oficina','Negocio')) sbt,
           mae_clien_clasi ccli ,
           mae_clien_tipo_subti cts
  WHERE    fpr.est_regi <> 9
  AND      fpr.est_poli = 3   
  AND      fpr.clie_oid_clie = cts.clie_oid_clie
  AND      ccli.clas_oid_clas = dcc.val_oid
  AND      ccli.tccl_oid_tipo_clasi = dtcc.val_oid 
  AND      ccli.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
  AND      cts.ticl_oid_tipo_clie = lsTipoConsultora 
  AND      cts.sbti_oid_subt_clie = sbt.VAL_OID;

 TYPE antiguasRecord IS RECORD (
    CLIE_OID_CLIE            SGR_FAMSE_POLIZ_REGIS.clie_oid_clie%TYPE,
    COD_CLIE                 SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    OID_CLIE_CLAS            NUMBER(12),
    CTSU_OID_CLIE_TIPO_SUBT  NUMBER(12),
    TCCL_OID_TIPO_CLASI      NUMBER(12),
    OID_CLAS_ANTI            NUMBER(12)                         
  );

  TYPE antiguastype IS TABLE OF antiguasRecord;
  r_antiguas    antiguastype;


CURSOR c_eliminar (lsTipoConsultora VARCHAR2) IS

  SELECT  fpr.clie_oid_clie,
          fpr.cod_clie,
          ccli.oid_clie_clas
  FROM    sgr_famse_poliz_regis fpr,
          (select val_oid from gen_i18n_sicc_comun
           where attr_enti = 'MAE_TIPO_CLASI_CLIEN'
           and val_i18n = 'Familia Protegida') dtcc,
          (select VAL_OID from gen_i18n_sicc_comun
           where attr_enti = 'MAE_SUBTI_CLIEN'
           AND VAL_I18N IN ('Oficina','Negocio')) sbt,
           mae_clien_clasi ccli ,
           mae_clien_tipo_subti cts
  WHERE    fpr.est_poli NOT IN (1,3)   
  AND      fpr.est_regi <> 9
  AND      fpr.clie_oid_clie = cts.clie_oid_clie
  AND      cts.ticl_oid_tipo_clie = lsTipoConsultora 
  AND      cts.sbti_oid_subt_clie = sbt.VAL_OID
  AND      ccli.tccl_oid_tipo_clasi = dtcc.val_oid 
  AND      ccli.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt;


 TYPE eliminarRecord IS RECORD (
    CLIE_OID_CLIE            SGR_FAMSE_POLIZ_REGIS.clie_oid_clie%TYPE,
    COD_CLIE                 SGR_FAMSE_POLIZ_REGIS.COD_CLIE%TYPE,
    OID_CLIE_CLAS            NUMBER(12)
                           
  );

  TYPE eliminartype IS TABLE OF eliminarRecord;
  r_eliminar    eliminartype;

BEGIN
    
  --  psCodigoPeriodo

  select cp.oid_peri 
  INTO  lsOidPeriodo 
  from  cra_perio cp,
        seg_perio_corpo spc
  where cp.peri_oid_peri =  spc.oid_peri
  and   spc.cod_peri = psCodigoPeriodo;


    SELECT VAL_OID 
    INTO lsTipoConsultora
    FROM gen_i18n_sicc_comun
    WHERE attr_enti = 'MAE_TIPO_CLIEN'
    AND VAL_I18N = 'Consultor(a)' ; 


 -- Identificar consultoras coberturadas sin clasificacion
    SELECT count(*) 
    INTO lsNroSinClasi
    FROM sgr_famse_poliz_regis fpr,
         (SELECT distinct cts.clie_oid_clie
          FROM
            (select DISTINCT VAL_OID from gen_i18n_sicc_comun
             where attr_enti = 'MAE_SUBTI_CLIEN'
             AND VAL_I18N IN ('Oficina','Negocio')) sbt,
            (select distinct val_oid from gen_i18n_sicc_comun
             where attr_enti = 'MAE_TIPO_CLASI_CLIEN'
             and val_i18n = 'Familia Protegida') tcf ,
             mae_clien_tipo_subti cts ,
             mae_clien_clasi ccli
             where cts.ticl_oid_tipo_clie = lsTipoConsultora
             and cts.sbti_oid_subt_clie = sbt.VAL_OID 
             and ccli.tccl_oid_tipo_clasi <> tcf.val_oid
             and ccli.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt) clas
    WHERE fpr.est_regi <> 9
    AND   fpr.est_poli = 1   
    AND   fpr.clie_oid_clie = clas.clie_oid_clie;

    IF (lsNroSinClasi > 0) THEN  -- CONSULTORAS SIN CLASFICACION SGR
       OPEN c_nuevas (lsTipoConsultora) ;
        LOOP
          FETCH c_nuevas BULK COLLECT
            INTO r_nuevas LIMIT w_filas;
              IF  r_nuevas.COUNT > 0 THEN
                
                  FOR i IN r_nuevas.FIRST..r_nuevas.LAST LOOP
                         
                   BEGIN    
                    SELECT tcc.oid_tipo_clas
                          ,c.oid_clas                      
                      INTO lsTClaFamPro, lsClaPrimPed 
                      FROM mae_tipo_clien tc
                          ,mae_subti_clien sc
                          ,mae_tipo_clasi_clien tcc
                          ,mae_clasi c
                          ,gen_i18n_sicc_comun des_tip_cla
                          ,gen_i18n_sicc_comun des_cla   
                     WHERE tc.oid_tipo_clie       = sc.ticl_oid_tipo_clie
                      -- AND tc.oid_tipo_clie       = lsTipoConsultora
                       AND tc.oid_tipo_clie       = r_nuevas(i).TICL_OID_TIPO_CLIE 
                       AND sc.oid_subt_clie       = r_nuevas(i).SBTI_OID_SUBT_CLIE
                       AND tcc.sbti_oid_subt_clie = sc.oid_subt_clie
                       AND tcc.oid_tipo_clas      = des_tip_cla.val_oid
                       AND des_tip_cla.attr_enti  = 'MAE_TIPO_CLASI_CLIEN'
                       AND des_tip_cla.val_i18n   = 'Familia Protegida'
                       AND tcc.oid_tipo_clas      = c.tccl_oid_tipo_clas
                       AND c.oid_clas             = des_cla.val_oid
                       AND des_cla.attr_enti      = 'MAE_CLASI'
                       AND des_cla.val_i18n       = 'Primer Pedido';  

                    EXCEPTION   
                    WHEN NO_DATA_FOUND THEN
                      exit;
                    END;   
                    
                    IF lsTClaFamPro IS NOT NULL AND lsClaPrimPed IS NOT NULL THEN
                    
                      INSERT INTO MAE_CLIEN_CLASI 
                      (OID_CLIE_CLAS,
                       CTSU_OID_CLIE_TIPO_SUBT,
                       CLAS_OID_CLAS,
                       PERD_OID_PERI,
                       TCCL_OID_TIPO_CLASI,
                       FEC_CLAS,
                       IND_PPAL,
                       FEC_ULTI_ACTU)
                      VALUES (
                       MAE_CLCL_SEQ.NEXTVAL,
                       r_nuevas(i).OID_CLIE_TIPO_SUBT ,
                       lsClaPrimPed,
                       lsOidPeriodo,
                       lsTClaFamPro,
                       TO_DATE(TO_CHAR(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY') , 
                       0,
                       SYSDATE); 
                       
                     END IF;  
                   END LOOP;
                END IF;
            EXIT WHEN c_nuevas%NOTFOUND;
         END LOOP;     
    END IF;

 
    
       OPEN c_antiguas (lsTipoConsultora);
        LOOP
          FETCH c_antiguas BULK COLLECT
            INTO r_antiguas LIMIT w_filas;
              IF  r_antiguas.COUNT > 0 THEN
                  FOR i IN r_antiguas.FIRST..r_antiguas.LAST LOOP
                      DELETE FROM MAE_CLIEN_CLASI 
                      WHERE OID_CLIE_CLAS = r_antiguas(i).CLIE_OID_CLIE ; 
                      
                      INSERT INTO MAE_CLIEN_CLASI 
                      (OID_CLIE_CLAS,
                       CTSU_OID_CLIE_TIPO_SUBT,
                       CLAS_OID_CLAS,
                       PERD_OID_PERI,
                       TCCL_OID_TIPO_CLASI,
                       FEC_CLAS,
                       IND_PPAL,
                       FEC_ULTI_ACTU)
                      VALUES (
                       MAE_CLCL_SEQ.NEXTVAL,
                       r_antiguas(i).CTSU_OID_CLIE_TIPO_SUBT,
                       r_antiguas(i).OID_CLAS_ANTI,
                       lsOidPeriodo,
                       r_antiguas(i).TCCL_OID_TIPO_CLASI,
                       TO_DATE(TO_CHAR(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY') , 
                       0,
                       SYSDATE); 
                   END LOOP;
                END IF;

         
            EXIT WHEN c_antiguas%NOTFOUND;
           
         END LOOP;     

    
    OPEN c_eliminar (lsTipoConsultora);
        LOOP
          FETCH c_eliminar BULK COLLECT
            INTO r_eliminar LIMIT w_filas;
              IF  r_eliminar.COUNT > 0 THEN
                  FOR i IN r_eliminar.FIRST..r_eliminar.LAST LOOP
                      DELETE FROM MAE_CLIEN_CLASI 
                      WHERE OID_CLIE_CLAS = r_eliminar(i).CLIE_OID_CLIE ; 
                     END LOOP;
                END IF;
               EXIT WHEN c_eliminar%NOTFOUND;
         END LOOP;           

END SGR_PR_ACTUA_CLASI_POLIZ;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte de Solicitudes
Fecha Creacion    : 14/01/2015
Autor             : Gonzalo Huertas
***************************************************************************/
PROCEDURE SGR_PR_REPOR_SOLI(
    psCodigoPais                     VARCHAR2,
    psCodigoRegion                   VARCHAR2,
    psCodigoZona                     VARCHAR2,
    psCodigoSeccion                  VARCHAR2,
    psCodigoPeriodoInicio            VARCHAR2,
    psCodigoPeriodoFin               VARCHAR2,
    psCodigoPoliza                   VARCHAR2,
    psNombreArchivo                  VARCHAR2,
    psTitulo                         VARCHAR2,
    psDirectorio                     OUT  VARCHAR2
    ) 
IS
  lsDirTempo                        BAS_INTER.DIR_TEMP%TYPE;
  w_filas                           NUMBER := 5000 ;
  v_handle                          UTL_FILE.FILE_TYPE;
  lsLinea                           VARCHAR2(4000);
  lsFlagRegiones                    VARCHAR2(1);

  CURSOR c_interfaz IS
  SELECT  DISTINCT
  fpr.cod_clie                                                      COD_CONSULTORA,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(fpr.cod_clie, 'NOM_CLIE')  NOM_COLSUTORA,
        fpr.fec_soli                                                      FEC_SOLICITUD,
        decode(fpr.est_poli, '3', 'ACTIVA','CANCELADA')                   EST_SOLICITUD,
        fpr.num_poli                                                      NUM_POLIZA,
        fpr.num_fami_coas                                                 NUM_COASEGURADO,
        fp.pre_poli                                                       PRE_POLIZA,
        zr.cod_regi                                                       COD_REGION,
        zr.des_regi                                                       DES_REGION,
        zz.cod_zona                                                       COD_ZONA,
        zz.des_zona                                                       DES_ZONA,
        hc.fec_inic_poli                                                  FEC_INICIO,
        hc.fec_fina_poli                                                  FEC_FIN,
        fpr.cam_proc                CAM_PROC,
        fpr.cam_regi                COD_CAMPANA,
        zs.cod_secc                COD_SECCION,
        zs.des_secci                                                      DES_SECCION,
        NULL MOT_RECHAZO,
        DECODE(fpr.IND_ORIG_REGI,'C','SSICC','O','OCR','W','WEB','B','BLACKBERRY') IND_ORIG,
  NVL((SELECT 
          MAX(TO_CHAR(FEC_FACT,'dd/MM/yyyy')) FEC_FACT          
        FROM
          PED_SOLIC_CABEC,
          MAE_CLIEN
        WHERE COD_CLIE = fpr.cod_clie 
          AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
          AND ind_oc=1
          AND val_tota_paga_loca>0
          AND FEC_FACT IS NOT NULL),'') FEC_FACT,
  NVL((SELECT MAX(sp.cod_peri)         
    FROM PED_SOLIC_CABEC,
         MAE_CLIEN,
         cra_perio cp,
         seg_perio_corpo sp      
    WHERE COD_CLIE = fpr.cod_clie 
      and sp.oid_peri = cp.peri_oid_peri
      and cp.oid_peri = PERD_OID_PERI
      AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
      AND ind_oc='1'
      AND val_tota_paga_loca > 0
      AND FEC_FACT IS NOT NULL),'') COD_ULT_CAMP,
  (SELECT b.val_i18n 
    FROM mae_estat_clien a, 
         gen_i18n_sicc_comun b,
         mae_clien_datos_adici c,
         mae_clien d
    WHERE a.oid_esta_clie = b.val_oid 
    AND b.attr_enti = 'MAE_ESTAT_CLIEN'
    and c.esta_oid_esta_clie = a.oid_esta_clie
    and c.clie_oid_clie = d.oid_clie
    and d.cod_clie = fpr.cod_clie) ESTATUS,
  (case when sysdate between hc.fec_inic_poli and hc.fec_fina_poli then 'SI' ELSE 'NO' END) VIGENCIA,
  fpr.cam_acti cam_acti,
  fpr.cam_canc cam_canc,
  null cam_rech,
  fp.cod_poli,
  fp.des_poli
  FROM  sgr_famse_poliz_regis  fpr,
        sgr_famse_poliz        fp,
        mae_clien_unida_admin  ua,
        zon_terri_admin        ta,
        zon_regio              zr,
        zon_zona               zz,
        zon_secci              zs,
        ccc_histo_cargo_famil_segur hc
 WHERE  fpr.poli_cod_poli = fp.cod_poli
   AND  fpr.cam_acti >= psCodigoPeriodoInicio 
   AND  fpr.cam_acti <= psCodigoPeriodoFin
   AND  fpr.est_poli ='3'
   AND  ua.ind_acti(+) = '1'
   AND  fpr.clie_oid_clie = ua.clie_oid_clie(+)
   AND  ta.oid_terr_admi(+) = ua.ztad_oid_terr_admi
   AND  ta.zscc_oid_secc = zs.oid_secc(+)
   AND  zz.oid_zona(+) = zs.zzon_oid_zona
   AND  zr.oid_regi(+) = zz.zorg_oid_regi
   AND  fpr.poli_cod_poli = hc.cod_poli (+)
   AND  fpr.cod_clie = hc.cod_clie (+)
   AND  fpr.num_poli = hc.num_poli (+)
   AND  fpr.est_regi <> '9'
   AND (hc.fec_inic_poli IS NULL OR hc.fec_inic_poli = (SELECT MAX(hc2.fec_inic_poli)
                                                          FROM ccc_histo_cargo_famil_segur hc2
                                                         WHERE hc2.num_poli(+) = fpr.num_poli
                                                          AND hc2.cod_clie(+) = fpr.cod_clie))
   AND  fp.ind_acti = 1
   AND  (psCodigoRegion is null or psCodigoRegion in ('Todos','') OR zr.cod_regi = DECODE(psCodigoRegion, null, zr.cod_regi, '', zr.cod_regi, 'Todos', zr.cod_regi, psCodigoRegion))
   AND  (psCodigoZona is null or psCodigoZona in ('Todos','') OR zz.cod_zona = DECODE(psCodigoZona, null, zz.cod_zona, '', zz.cod_zona, 'Todos', zz.cod_zona, psCodigoZona))
   AND  (psCodigoSeccion is null or psCodigoSeccion in ('Todos','') OR zs.cod_secc = DECODE(psCodigoSeccion, null, zs.cod_secc, '', zs.cod_secc, 'Todos', zs.cod_secc, psCodigoSeccion)  )
   AND  fp.cod_poli = NVL(psCodigoPoliza, fp.cod_poli)
UNION
SELECT  DISTINCT
      fpr.cod_clie                                                      COD_CONSULTORA,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(fpr.cod_clie, 'NOM_CLIE')  NOM_COLSUTORA,
        fpr.fec_soli                                                      FEC_SOLICITUD,
        decode(fpr.est_poli, '3', 'ACTIVA','CANCELADA')                   EST_SOLICITUD,
        fpr.num_poli                                                      NUM_POLIZA,
        fpr.num_fami_coas                                                 NUM_COASEGURADO,
        fp.pre_poli                                                       PRE_POLIZA,
        zr.cod_regi                                                       COD_REGION,
        zr.des_regi                                                       DES_REGION,
        zz.cod_zona                                                       COD_ZONA,
        zz.des_zona                                                       DES_ZONA,
        hc.fec_inic_poli                                                  FEC_INICIO,
        hc.fec_fina_poli                                                  FEC_FIN,
        fpr.cam_proc                CAM_PROC,
        fpr.cam_regi                COD_CAMPANA,
        zs.cod_secc                COD_SECCION,
        zs.des_secci                                                      DES_SECCION,
        NULL MOT_RECHAZO,
        DECODE(fpr.IND_ORIG_REGI,'C','SSICC','O','OCR','W','WEB','B','BLACKBERRY') IND_ORIG,
  NVL((SELECT 
          MAX(TO_CHAR(FEC_FACT,'dd/MM/yyyy')) FEC_FACT          
        FROM
          PED_SOLIC_CABEC,
          MAE_CLIEN
        WHERE COD_CLIE = fpr.cod_clie 
          AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
          AND ind_oc=1
          AND val_tota_paga_loca>0
          AND FEC_FACT IS NOT NULL),'') FEC_FACT,
  NVL((SELECT MAX(sp.cod_peri)         
    FROM PED_SOLIC_CABEC,
         MAE_CLIEN,
         cra_perio cp,
         seg_perio_corpo sp      
    WHERE COD_CLIE = fpr.cod_clie 
      and sp.oid_peri = cp.peri_oid_peri
      and cp.oid_peri = PERD_OID_PERI
      AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
      AND ind_oc='1'
      AND val_tota_paga_loca > 0
      AND FEC_FACT IS NOT NULL),'') COD_ULT_CAMP,
  (SELECT b.val_i18n 
    FROM mae_estat_clien a, 
         gen_i18n_sicc_comun b,
         mae_clien_datos_adici c,
         mae_clien d
    WHERE a.oid_esta_clie = b.val_oid 
    AND b.attr_enti = 'MAE_ESTAT_CLIEN'
    and c.esta_oid_esta_clie = a.oid_esta_clie
    and c.clie_oid_clie = d.oid_clie
    and d.cod_clie = fpr.cod_clie) ESTATUS,
  (case when sysdate between hc.fec_inic_poli and hc.fec_fina_poli then 'SI' ELSE 'NO' END) VIGENCIA,
  fpr.cam_acti cam_acti,
  fpr.cam_canc cam_canc,
    null cam_rech,
    fp.cod_poli,
  fp.des_poli
  FROM  sgr_famse_poliz_regis  fpr,
        sgr_famse_poliz        fp,
        mae_clien_unida_admin  ua,
        zon_terri_admin        ta,
        zon_regio              zr,
        zon_zona               zz,
        zon_secci              zs,
        ccc_histo_cargo_famil_segur hc
 WHERE  fpr.poli_cod_poli = fp.cod_poli
   AND  fpr.cam_canc >= psCodigoPeriodoInicio 
   AND  fpr.cam_canc <= psCodigoPeriodoFin
   AND  fpr.est_poli ='4'
   AND  ua.ind_acti(+) = '1'
   AND  fpr.clie_oid_clie = ua.clie_oid_clie(+)
   AND  ta.oid_terr_admi(+) = ua.ztad_oid_terr_admi
   AND  ta.zscc_oid_secc = zs.oid_secc(+)
   AND  zz.oid_zona(+) = zs.zzon_oid_zona
   AND  zr.oid_regi(+) = zz.zorg_oid_regi
   AND  fpr.poli_cod_poli = hc.cod_poli (+)
   AND  fpr.cod_clie = hc.cod_clie (+)
   AND  fpr.num_poli = hc.num_poli (+)
   AND  fpr.est_regi <> '9'
   AND (hc.fec_inic_poli IS NULL OR hc.fec_inic_poli = (SELECT MAX(hc2.fec_inic_poli)
                                                          FROM ccc_histo_cargo_famil_segur hc2
                                                         WHERE hc2.num_poli(+) = fpr.num_poli
                                                          AND hc2.cod_clie(+) = fpr.cod_clie))
   AND  fp.ind_acti = 1
   AND  (psCodigoRegion is null or psCodigoRegion in ('Todos','') OR zr.cod_regi = DECODE(psCodigoRegion, null, zr.cod_regi, '', zr.cod_regi, 'Todos', zr.cod_regi, psCodigoRegion))
   AND  (psCodigoZona is null or psCodigoZona in ('Todos','') OR zz.cod_zona = DECODE(psCodigoZona, null, zz.cod_zona, '', zz.cod_zona, 'Todos', zz.cod_zona, psCodigoZona))
   AND  (psCodigoSeccion is null or psCodigoSeccion in ('Todos','') OR zs.cod_secc = DECODE(psCodigoSeccion, null, zs.cod_secc, '', zs.cod_secc, 'Todos', zs.cod_secc, psCodigoSeccion)  )
   AND  fp.cod_poli = NVL(psCodigoPoliza, fp.cod_poli)
UNION
SELECT  
    fpr.cod_clie                                                      COD_CONSULTORA,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(fpr.cod_clie, 'NOM_CLIE')  NOM_COLSUTORA,
        fpr.fec_soli                                                      FEC_SOLICITUD,
        'REGISTRADA'                                                      EST_SOLICITUD,
        fpr.num_poli                                                      NUM_POLIZA,
        fpr.num_fami_coas                                                 NUM_COASEGURADO,
        fp.pre_poli                                                       PRE_POLIZA,
        zr.cod_regi                                                       COD_REGION,
        zr.des_regi                                                       DES_REGION,
        zz.cod_zona                                                       COD_ZONA,
        zz.des_zona                                                       DES_ZONA,
        NULL                                                              FEC_INICIO,
        NULL                                                              FEC_FIN,
        fpr.cam_proc                CAM_PROC,
        fpr.cam_regi                                                      COD_CAMPANA,
        zs.cod_secc                                                        COD_SECCION,
        zs.des_secci                                                      DES_SECCION,
        NULL MOT_RECHAZO,
        DECODE(fpr.IND_ORIG_REGI,'C','SSICC','O','OCR','W','WEB','B','BLACKBERRY') IND_ORIG,
      NVL((SELECT 
          MAX(TO_CHAR(FEC_FACT,'dd/MM/yyyy')) FEC_FACT          
        FROM
          PED_SOLIC_CABEC,
          MAE_CLIEN
        WHERE COD_CLIE = fpr.cod_clie 
          AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
          AND ind_oc=1
          AND val_tota_paga_loca>0
          AND FEC_FACT IS NOT NULL),'') FEC_FACT,
       NVL((SELECT MAX(sp.cod_peri)         
    FROM PED_SOLIC_CABEC,
         MAE_CLIEN,
         cra_perio cp,
         seg_perio_corpo sp      
    WHERE COD_CLIE = fpr.cod_clie 
      and sp.oid_peri = cp.peri_oid_peri
      and cp.oid_peri = PERD_OID_PERI
      AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
      AND ind_oc='1'
      AND val_tota_paga_loca > 0
      AND FEC_FACT IS NOT NULL),'') COD_ULT_CAMP,
  (SELECT b.val_i18n 
    FROM mae_estat_clien a, 
         gen_i18n_sicc_comun b,
         mae_clien_datos_adici c,
         mae_clien d
    WHERE a.oid_esta_clie = b.val_oid 
    AND b.attr_enti = 'MAE_ESTAT_CLIEN'
    and c.esta_oid_esta_clie = a.oid_esta_clie
    and c.clie_oid_clie = d.oid_clie
    and d.cod_clie = fpr.cod_clie) ESTATUS,
  'NO' AS VIGENCIA,
  fpr.cam_acti cam_acti,
  fpr.cam_canc cam_canc,
  null cam_rech,  
    fp.cod_poli,
  fp.des_poli
  FROM  sgr_famse_poliz_regis  fpr,
        sgr_famse_poliz        fp,
        mae_clien_unida_admin  ua,
        zon_terri_admin        ta,
        zon_regio              zr,
        zon_zona               zz,
        zon_secci              zs
 WHERE  fpr.poli_cod_poli = fp.cod_poli
   AND  fpr.cam_proc >= psCodigoPeriodoInicio 
   AND  fpr.cam_proc <= psCodigoPeriodoFin
   AND  fpr.est_poli = '1'
   AND  ua.ind_acti(+) = '1'
   AND  fpr.clie_oid_clie = ua.clie_oid_clie(+)
   AND  ta.oid_terr_admi(+) = ua.ztad_oid_terr_admi
   AND  ta.zscc_oid_secc = zs.oid_secc(+)
   AND  zz.oid_zona(+) = zs.zzon_oid_zona
   AND  zr.oid_regi(+) = zz.zorg_oid_regi
   AND  fpr.est_regi <> '9'
   AND  (psCodigoRegion is null or psCodigoRegion in ('Todos','') OR zr.cod_regi = DECODE(psCodigoRegion, null, zr.cod_regi, '', zr.cod_regi, 'Todos', zr.cod_regi, psCodigoRegion))
   AND  (psCodigoZona is null or psCodigoZona in ('Todos','') OR zz.cod_zona = DECODE(psCodigoZona, null, zz.cod_zona, '', zz.cod_zona, 'Todos', zz.cod_zona, psCodigoZona))
   AND  (psCodigoSeccion is null or psCodigoSeccion in ('Todos','') OR zs.cod_secc = DECODE(psCodigoSeccion, null, zs.cod_secc, '', zs.cod_secc, 'Todos', zs.cod_secc, psCodigoSeccion))
   AND   fp.ind_acti = 1
   AND  fp.cod_poli = NVL(psCodigoPoliza, fp.cod_poli)
UNION
SELECT  DISTINCT
        dd.cod_clie                                                      COD_CONSULTORA,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'NOM_CLIE')  NOM_COLSUTORA,
        TRUNC(fs.fec_proc)                                               FEC_SOLICITUD,
        'RECHAZADA'                                                      EST_SOLICITUD,
        fs.num_docu                                                      NUM_POLIZA,
        fs.can_coas                                                      NUM_COASEGURADO,
        (SELECT fp.pre_poli
           FROM sgr_famse_poliz fp
          WHERE fp.ind_acti = 1
            and fp.EST_REGI <>9)                                         PRE_POLIZA,
        (SELECT cod_regi from zon_regio where OID_REGI = fs.zorg_oid_regi)  COD_REGION,
        (SELECT des_regi from zon_regio where OID_REGI = fs.zorg_oid_regi)  DES_REGION,
        (SELECT cod_zona from zon_zona where OID_ZONA = fs.zzon_oid_zona)   COD_ZONA,
        (SELECT des_zona from zon_zona where OID_ZONA = fs.zzon_oid_zona)   DES_ZONA,
  NULL                     FEC_INICIO,
        NULL               FEC_FIN,
        fs.cam_proc                CAM_PROC,
        null     COD_CAMPANA,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'COD_SECC')  COD_SECCION,
        GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'DES_SECC')  DES_SECCION,
        TRIM(dd.val_obse_rech_defi)                                      MOT_RECHAZO,
  DECODE(dd.IND_RECE_DIGI,'1','SSICC','OCR')        IND_ORIG,
  NVL((SELECT 
          MAX(TO_CHAR(FEC_FACT,'dd/MM/yyyy')) FEC_FACT          
        FROM
          PED_SOLIC_CABEC,
          MAE_CLIEN
        WHERE COD_CLIE = fs.cod_clie 
          AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
          AND ind_oc=1
          AND val_tota_paga_loca>0
          AND FEC_FACT IS NOT NULL),'') FEC_FACT,
  NVL((SELECT MAX(sp.cod_peri)         
    FROM PED_SOLIC_CABEC,
         MAE_CLIEN,
         cra_perio cp,
         seg_perio_corpo sp      
    WHERE COD_CLIE = fs.cod_clie 
      and sp.oid_peri = cp.peri_oid_peri
      and cp.oid_peri = PERD_OID_PERI
      AND PED_SOLIC_CABEC.CLIE_OID_CLIE=OID_CLIE
      AND ind_oc='1'
      AND val_tota_paga_loca > 0
      AND FEC_FACT IS NOT NULL),'') COD_ULT_CAMP,
  (SELECT b.val_i18n 
    FROM mae_estat_clien a, 
         gen_i18n_sicc_comun b,
         mae_clien_datos_adici c,
         mae_clien d
    WHERE a.oid_esta_clie = b.val_oid 
    AND b.attr_enti = 'MAE_ESTAT_CLIEN'
    and c.esta_oid_esta_clie = a.oid_esta_clie
    and c.clie_oid_clie = d.oid_clie
    and d.cod_clie = fs.cod_clie) ESTATUS,
  'NO' AS VIGENCIA,
  NULL cam_acti,
  NULL cam_canc,
  fs.cam_proc cam_rech,
    ' ' cod_poli,
  ' ' des_poli
  FROM   (select cod_pais,num_lote,sec_nume_docu,cod_tipo_docu,ind_rech,cod_clie,cod_regi,cod_zona,IND_RECE_DIGI,val_obse_rech_defi
         from sto_docum_digit 
         where COD_PAIS = psCodigoPais 
         and cod_tipo_docu = 'FAS'
       union
         select cod_pais,num_lote,sec_nume_docu,cod_tipo_docu,ind_rech,cod_clie,cod_regi,cod_zona,IND_RECE_DIGI,val_obse_rech_defi
         from sto_histo_docum_digit
         where COD_PAIS = psCodigoPais 
         and cod_tipo_docu = 'FAS') dd,
        (select cod_pais,num_lote,sec_nume_docu,cam_proc,ZORG_OID_REGI,ZZON_OID_ZONA,NUM_DOCU,fec_proc,can_coas,COD_CLIE
         from int_solic_conso_famil_segur
         where COD_PAIS = psCodigoPais  
         union
         select cod_pais,num_lote,sec_nume_docu,cam_proc,ZORG_OID_REGI,ZZON_OID_ZONA,NUM_DOCU,fec_proc,can_coas,COD_CLIE
         from int_histo_conso_famil_segur
         where COD_PAIS = psCodigoPais) fs
 WHERE  dd.cod_pais = fs.cod_pais
   AND  dd.num_lote = fs.num_lote
   AND  dd.sec_nume_docu = fs.sec_nume_docu
   AND  dd.cod_tipo_docu = 'FAS'
   AND  dd.ind_rech = '1'  
   AND  fs.cod_pais = psCodigoPais
   AND  fs.cam_proc >= psCodigoPeriodoInicio
   AND  fs.cam_proc <= psCodigoPeriodoFin
   AND  (psCodigoRegion is null or psCodigoRegion in ('Todos','') OR fs.ZORG_OID_REGI = DECODE(psCodigoRegion, null, fs.ZORG_OID_REGI, '', fs.ZORG_OID_REGI, 'Todos', fs.ZORG_OID_REGI, GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais,'T','VD',psCodigoRegion)))
   AND  (psCodigoZona is null or psCodigoZona in ('Todos','') OR fs.ZZON_OID_ZONA = DECODE(psCodigoZona, null, fs.ZZON_OID_ZONA, '', fs.ZZON_OID_ZONA, 'Todos', fs.ZZON_OID_ZONA, GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA(psCodigoPais,'T','VD',psCodigoRegion,psCodigoZona)))
   AND  (psCodigoSeccion is null or psCodigoSeccion in ('Todos','') OR GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'COD_SECC') = DECODE(psCodigoSeccion, null, GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'COD_SECC'), 'Todos', GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(dd.cod_clie, 'COD_SECC'), psCodigoSeccion))
ORDER BY 27,8,10,16,14,15,4,5;

    TYPE interfazTipo IS RECORD (
        v_COD_CONSULTORA        VARCHAR2(15),
        v_NOM_COLSUTORA         varchar2(200),
        v_FEC_SOLICITUD         date,
        v_EST_SOLICITUD         varchar2(10),
        v_NUM_POLIZA            varchar2(20),
        v_NUM_COASEGURADO       number(5),
        v_PRE_POLIZA            number(12,2),
        v_COD_REGION            varchar2(2),
        v_DES_REGION            VARCHAR2(40),
        v_COD_ZONA              VARCHAR2(4),
        v_DES_ZONA              VARCHAR2(40),
        v_FEC_INICIO            date,
        v_FEC_FIN               date,
        v_CAM_PROC              VARCHAR2(6), 
        v_COD_CAMPANA           varchar2(6),
        v_COD_SECCION           varchar2(1),
        v_DES_SECCION           varchar2(40),
        v_MOT_RECHAZO           varchar2(200),
        v_IND_ORIG              varchar2(10),
        v_FEC_FACT              varchar2(10),
        v_COD_ULT_CAMP          varchar2(6),
        v_ESTATUS               varchar2(200),
        v_VIGENCIA              varchar2(2),
        v_CAM_ACTI              varchar2(6),
        v_CAM_CANC              varchar2(6),
        v_CAM_RECH              varchar2(6),
        v_COD_POLI              varchar2(5),
        v_DES_POLI              varchar2(1000)
    );

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
       
    lbAbrirUtlFile  BOOLEAN;

BEGIN

    SELECT DECODE(COUNT(*), 0, '0', '1')
       INTO lsFlagRegiones
     FROM LET_TEMPO_REPOR_RESUL_REGIO;

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, v_handle);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '"'||interfazRecord(x).v_COD_POLI||'"' ||'-'||       
              '"'||interfazRecord(x).v_DES_POLI||'"'||','||
              '"'||interfazRecord(x).v_DES_REGION||'"' ||','||
              '"'||interfazRecord(x).v_DES_ZONA||'"' ||','||                
              '"'||interfazRecord(x).v_DES_SECCION||'"' ||','||              
              '"'||interfazRecord(x).v_CAM_PROC||'"' ||','||                     
              '"'||interfazRecord(x).v_COD_CAMPANA||'"' ||','||    
              '"'||interfazRecord(x).v_CAM_ACTI||'"' ||','||       
              '"'||interfazRecord(x).v_CAM_CANC||'"' ||','||       
              '"'||interfazRecord(x).v_CAM_RECH||'"' ||','||
              '"'||interfazRecord(x).v_COD_CONSULTORA||'"' ||','|| 
              '"'||interfazRecord(x).v_NOM_COLSUTORA||'"' ||','||      
              '"'||to_char(interfazRecord(x).v_FEC_SOLICITUD,'dd/mm/yyyy')||'"' ||','||  
              '"'||interfazRecord(x).v_EST_SOLICITUD||'"' ||','||  
              '"'||interfazRecord(x).v_MOT_RECHAZO||'"' ||','||
              '"'||interfazRecord(x).v_NUM_POLIZA||'"' ||','||     
              '"'||interfazRecord(x).v_IND_ORIG||'"' ||','||                     
              '"'||interfazRecord(x).v_PRE_POLIZA||'"' ||','||              
              '"'||to_char(interfazRecord(x).v_FEC_INICIO,'dd/mm/yyyy')||'"' ||','||  
              '"'||to_char(interfazRecord(x).v_FEC_FIN,'dd/mm/yyyy')||'"' ||','||
              '"'||interfazRecord(x).v_VIGENCIA||'"' ||','||
              '"'||interfazRecord(x).v_COD_ULT_CAMP||'"' ||','||   
              '"'||interfazRecord(x).v_FEC_FACT||'"' ||','||       
              '"'||interfazRecord(x).v_ESTATUS||'"';                                         
            UTL_FILE.PUT_LINE (v_handle, lslinea );            
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SGR_PR_REPOR_SOLI: '||ls_sqlerrm);
END SGR_PR_REPOR_SOLI;

END SGR_PKG_PROCE_FAMSE;
/
