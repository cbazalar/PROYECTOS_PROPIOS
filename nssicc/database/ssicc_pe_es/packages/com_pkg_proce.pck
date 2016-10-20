CREATE OR REPLACE PACKAGE "COM_PKG_PROCE" IS

  /* Declaracion de Variables */
  Ln_Sqlcode NUMBER(10);

  Ls_Sqlerrm VARCHAR2(150);

  Lntotalperiodos CONSTANT NUMERIC := 18;

  /* Niveles que puede alcanzar la ejecutiva de una seccion */
  EJECUTIVA_JUNIOR CONSTANT VARCHAR2(2) := 'EJ';

  EJECUTIVA_SENIOR CONSTANT VARCHAR2(2) := 'ES';

  EJECUTIVA_MASTER CONSTANT VARCHAR2(2) := 'EM';

  SIN_NIVEL CONSTANT VARCHAR2(2) := 'SN';

  TUTORA CONSTANT VARCHAR2(2) := 'TT';

  ASPIRANTE CONSTANT VARCHAR2(2) := 'AS';

  No_Encontrado VARCHAR2(2) := '--';
  COMODIN_NO VARCHAR2(2):='NO';
  COMODIN_SI VARCHAR2(2):='SI';

  w_Filas NUMBER := 1000;

  COMISION_INGRESO           VARCHAR2(2):= '01';
  COMISION_RECUPERACION      VARCHAR2(2):= '02';
  COMISION_COMERCIALIZACION  VARCHAR2(2):= '03';

  /***************************************************************************
  Descripcion       : Devuelve el codigo de calificacion de comision segun la
                      campaña procesada y tipo de comisionista
  Fecha Creacion    : 17/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_CALIF_COMIS(Pscodpais          VARCHAR2,
                                    Pscodmarca         VARCHAR2,
                                    Pscodcanal         VARCHAR2,
                                    Pstipocomisionista VARCHAR2,
                                    Pscampania         VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Procedimiemto que genera Actividad Final de Zonas por Periodo

  Fecha Creacion    : 16/01/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE COM_PR_CALC_VARI_NIVE_SECA(Pscodpais            VARCHAR2,
                                       Pscodmarca           VARCHAR2,
                                       Pscodcanal           VARCHAR2,
                                       Pscodperiodo         VARCHAR2,
                                       Pscodtipcomisionista VARCHAR2);

  /***************************************************************************
  Descripcion       :  Recupera BiCampaña si no encuentra retorna -1
  Parametros          Codigo Pais
                      Codigo Marca
                      Codigo Canal
                      Codigo Ejecutiva
                      Codigo Periodo

  Fecha Creacion    : 18/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_BI_CAMPA(Pscodpais         VARCHAR2,
                                 Pscodmarca        VARCHAR2,
                                 Pscodcanal        VARCHAR2,
                                 Pscodigoejecutiva VARCHAR2,
                                 Psocodigoperiodo  VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       :  Recupera BiCampaña si no encuentra retorna -1
  Parametros        :  Codigo Pais
                       Codigo Marca
                       Codigo Canal
                       Codigo Periodo
                       Codigo Region
                       Codigo Zona
                       Codigo Seccion

  Fecha Creacion    : 18/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_BI_CAMPA(Pscodpais       VARCHAR2,
                                 Pscodmarca      VARCHAR2,
                                 Pscodcanal      VARCHAR2,
                                 Pscodigoperiodo VARCHAR2,
                                 Pscodregion     VARCHAR2,
                                 Pscodzona       VARCHAR2,
                                 Pscodseccion    VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       :  Recupera la suma de simular territorios que pertenescan
                       al periodo anterior y sumar sus Ingresos.
  Parametros        :  oid Pais
                       oid periodo
                       oid periodo anterior
                       oid Seccion

  Fecha Creacion    : 18/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_SUMA_TERRI_SIMU(Psoidpais            NUMBER,
                                        Psoidperiodo         NUMBER,
                                        Psoidperiodoanterior NUMBER,
                                        Psoidseccion         NUMBER) RETURN NUMBER;

  /***************************************************************************
  Descripcion       :  Recurpera el Nivel que lo corresponde a la ejecutiva
                       si no tiene ningun nivel se le asignara SN=Sin nivel
  Parametros        :  Codigo Pais
                       Codigo de Calificacion
                       Numero de Pedios
                       Codigo Zona
                       Numero de Ingresos BiCampaña
                       Codigo Region
                       Codigo Zona

  Fecha Creacion    : 21/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_NIVEL_EJECU(Pscodpais             VARCHAR2,
                                    Pscodcalificacion     VARCHAR2,
                                    Psnumpedios           NUMBER,
                                    Psnumingrsosbicampana NUMBER,
                                    Pscodregion           VARCHAR2,
                                    Pscodzona             VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       :  Recurpera el Estado del Cliente, si no Error
       :
  Fecha Creacion    : 21/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_ESTAD_CLIET(Psestado VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       :  Recurpera numero de Campañas sin nivel en en el tramo
                       de lo contrario Error
  Parametros        : Codigo Pais
                      Codigo Marca
                      Codigo Canal
                      Codigo Periodo
                      Codigo Nivel
  Fecha Creacion    : 21/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_NUMER_CAMP_SNIVE(Pscodpais         VARCHAR2,
                                         Pscodmarca        VARCHAR2,
                                         Pscodcanal        VARCHAR2,
                                         Pscodperiodo      VARCHAR2,
                                         Lscodliderseccion VARCHAR2,
                                         Psnivel           VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Acumula las variables de venta y los ingresos bicampaña
                      en un tramo de campañas. Una vez que se tienes las variables
                      se obtiene el nivel correspondiente de acuerdo a la cantidad
                      de pedidos e ingresos bicampaña promedio
                      indica si se encontro erorres o no
  Fecha Creacion    : 18/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_CALIF_EJECU_TRAMO(Pscodpais          VARCHAR2,
                                           Pscodmarca         VARCHAR2,
                                           Pscodcanal         VARCHAR2,
                                           Pnanioinicial      NUMBER,
                                           Pscodtramo         VARCHAR2,
                                           Pstipocomisionista VARCHAR2);

  /***************************************************************************
  Descripcion       : Devuelve la campaña inicial y final de un determinado tramo
                      para un respectivo pais
  Fecha Creacion    : 17/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_DEVUE_CAMPA_TRAMO(Pscodpais        VARCHAR2,
                                     Pscodtramo       VARCHAR2,
                                     Pnanioinicial    NUMBER,
                                     Pscampaniainicio OUT VARCHAR2,
                                     Pscampaniafin    OUT VARCHAR2);

  /***************************************************************************
  Descripcion       : Devuelve el nivel que ha alcanzado la ejecutiva de acuerdo
                      al total de pedidos y total de ingresos bicampañas realizadas
                      y verificando si existe configurado nivel por region y zona,
                      region, o region y zona nulos
  Fecha Creacion    : 21/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_NIVEL_EJECU(Pscodpais         VARCHAR2,
                                    Pscodcalificacion VARCHAR2,
                                    Pnprombicampanas  NUMBER,
                                    Pnprompedidos     NUMBER,
                                    Pscodregion       VARCHAR2,
                                    Pscodzona         VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Devuelve el año y tramo anterior para un respectivo pais
  Fecha Creacion    : 22/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_DEVUE_TRAMO_ANTER(Pscodpais        VARCHAR2,
                                     Pscodtramo       VARCHAR2,
                                     Pnanioinicial    NUMBER,
                                     Pscodtramoant    OUT VARCHAR2,
                                     Pnanioinicialant OUT NUMBER);

  /***************************************************************************
  Descripcion       : Devuelve el año y tramo anterior para un respectivo pais
  Fecha Creacion    : 22/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_NIVEL_ANTER(Pscodpais          VARCHAR2,
                                    Pscodmarca         VARCHAR2,
                                    Pscodcanal         VARCHAR2,
                                    Pnanioinicial      NUMBER,
                                    Pscodtramo         VARCHAR2,
                                    Pstipocomisionista VARCHAR2,
                                    Pscodejecutiva     VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Recupera Numero de pedidos y el Codigo de sub Gerencia
                      si no encuentra retorna -1
  Parametros          Codigo Region
                      Codigo Zona
                      Codigo Seccion

  Fecha Creacion    : 18/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_NUMER_PEDID(Pscodregion         VARCHAR2,
                                    Pscodzona           VARCHAR2,
                                    Pscodseccion        VARCHAR2,
                                    Psnumeropedidos     OUT NUMBER,
                                    Pscodigosubgerencia OUT VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Recupera el Inicio de la Campaña de Ejecutivas
                      si no encuentra gerera error
  Parametros          Codigo Pais
                      Codigo TipoComisionista
                      Codigo Seccion

  Fecha Creacion    : 18/04/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_INICI_CAMPA(Pscodpais VARCHAR2, Pscodtipcomisionista VARCHAR2)
    RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Devuelve 1 si ha sido evaluado la ejecutiva en la campaña
                      seleccionada, 0 si no lo encontro
  Fecha Creacion    : 30/04/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_EVALU_CAMPA(Pscodpais          VARCHAR2,
                                    Pscodmarca         VARCHAR2,
                                    Pscodcanal         VARCHAR2,
                                    Pscampania         VARCHAR2,
                                    Pstipocomisionista VARCHAR2,
                                    Pscodejecutiva     VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Obtiene los ingresos, ingresos bicampañan y pedidos de las
                      ejecutivas de las campañas pertenecientes al tramo seleccionado.
                      Ademas trae informacion de la campaña anterior a la campaña
                      inicio del tramo
  Fecha Creacion    : 02/06/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_OBTIE_SEGUI_CALIF_CAMPA(Pscodpais          VARCHAR2,
                                           Pscodmarca         VARCHAR2,
                                           Pscodcanal         VARCHAR2,
                                           Pnanioinicial      NUMBER,
                                           Pscodtramo         VARCHAR2,
                                           Pstipocomisionista VARCHAR2);

  /***************************************************************************
  Descripcion       : Obtiene las ejecutivas que fueron evaluadas para una determinada
                      region, zon para un año y tramo seleccionado
  Fecha Creacion    : 05/06/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_OBTIE_EVALU_SECCI_TRAMO(Pscodpais          VARCHAR2,
                                           Pscodmarca         VARCHAR2,
                                           Pscodcanal         VARCHAR2,
                                           Pnanioinicial      NUMBER,
                                           Pscodtramo         VARCHAR2,
                                           Pstipocomisionista VARCHAR2);

  /***************************************************************************
  Descripcion       : Devuelve la cantidad de ejecutivas que obtuvieron un determinado
                      nivel para un año y tramo seleccionado, region y zona
  Fecha Creacion    : 09/06/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_TOTAL_EJECU_ZONA(Pscodpais     VARCHAR2,
                                         Pnanioinicial NUMBER,
                                         Pscodtramo    VARCHAR2,
                                         Pscodregion   VARCHAR2,
                                         Pscodzona     VARCHAR2,
                                         Pscodnivel    VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Obtiene los totales de las ejecutivas de los niveles:
                      Aspirante, Ejecutiva Junior, Ejecutiva Master, Ejecutiva
                      Senior, por region y zona
  Fecha Creacion    : 09/06/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE COM_PR_OBTIE_EVALU_ZONA_TRAMO(Pscodpais          VARCHAR2,
                                          Pscodmarca         VARCHAR2,
                                          Pscodcanal         VARCHAR2,
                                          Pnanioinicial      NUMBER,
                                          Pscodtramo         VARCHAR2,
                                          Pstipocomisionista VARCHAR2);

  /***************************************************************************
  Descripcion       : Devuelve la cantidad de ejecutivas que obtuvieron un determinado
                      nivel para un año y tramo seleccionado, y region
  Fecha Creacion    : 09/06/2008
  Autor             : Sergio Apaza
  ***************************************************************************/
  FUNCTION COM_FN_DEVUE_TOTAL_EJECU_REGIO(Pscodpais     VARCHAR2,
                                          Pnanioinicial NUMBER,
                                          Pscodtramo    VARCHAR2,
                                          Pscodregion   VARCHAR2,
                                          Pscodnivel    VARCHAR2) RETURN NUMBER;

  /**************************************************************************
     Descripcion       : Calcula Comision de Recuperación

     Fecha Creacion    : 15/04/2008
     Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIC_RECUP(Pscodpais             VARCHAR2,
                                     Pscodmarca            VARCHAR2,
                                     Pscodcanal            VARCHAR2,
                                     Pscodtipocomisionista VARCHAR2,
                                     Pscodcomision         VARCHAR2,
                                     Psaniotramo           VARCHAR2,
                                     Psnumtramo            VARCHAR2,
                                     Pscodperiodo          VARCHAR2,
                                     psCodUsuario          VARCHAR2);

/***************************************************************************
Descripcion       :Recurpera el Nivel de la ejecutiva
                   de no encotrar registros retorna AS = Aspiarante
Parametros        :
                   psCodPais             Cogigo Pais
                   lsCodLiderSeccion     Codigo Lider
                   psAnioTramo           Año del trbato
                   psNumTramo            Numero del tramo
                   psCodCanal            Codigo Canal
                   psCodMarca            Codigo Marca
                   psCodTipoComisionista Codigo Tipo Comisionista

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
  FUNCTION COM_FN_OBTIE_NIVEL_EJECU(Pscodpais             VARCHAR2,
                                    Lscodliderseccion     VARCHAR2,
                                    Psaniotramo           VARCHAR2,
                                    Psnumtramo            VARCHAR2,
                                    Pscodcanal            VARCHAR2,
                                    Pscodmarca            VARCHAR2,
                                    Pscodtipocomisionista VARCHAR2,
                                    psNumUsosComodin OUT NUMBER,
                                    psNumUsosComodinRecu OUT NUMBER) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       :Recurpera numero de Campañas sin nivel en en el tramo
                     de lo contrario Error
  Parametros        :
                     psCodPais      Cogigo Pais
                     psCodMarca     Codigo Marca
                     psCodCanal     Codigo Canal
                     psCodEjecutiva Codigo Ejecutiva
                     psCodPeriodo   Codigo Periodo

  Fecha Creacion    : 26/06/2008
  Autor             : Leonardo Lizana
  ***************************************************************************/
  FUNCTION COM_FN_OBTIE_NIVEL_CAMPA(Pscodpais      VARCHAR2,
                                          Pscodmarca     VARCHAR2,
                                          Pscodcanal     VARCHAR2,
                                          Pscodejecutiva VARCHAR2,
                                          Pscodperiodo   VARCHAR2,
                                          pnNumIngreCampa   OUT NUMBER
                                          ) RETURN VARCHAR2;

/***************************************************************************
Descripcion       :Recurpera numero de Campañas sin nivel en en el tramo
                   de lo contrario Error
Parametros        :
                   psCodCalificacion Código Calificacion

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_CALIF_COMOD( psCodCalifcacion VARCHAR2
                                   ) RETURN NUMBER;

/***************************************************************************
Descripcion       :Recurpera fecha de facturacion de la seccion de un periodo

Parametros        :
                   psOidPeriodo oid del periodo
                   psOidZona    oid de la Zona
                   psOidREgion  odi de la Region

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_FECHA_FACTU( pnIdPais NUMBER,
                                   psOidPeriodo NUMBER,
                                   psOidZona NUMBER,
                                   psOidRegion NUMBER) RETURN DATE;

/***************************************************************************
Descripcion       : verificar si la si exisiste pago
Parametros        :
                   subp_oid_subp_ulti    subp_oid_subp_ulti

Fecha Creacion    : 03/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_EXIST_PAGO(subp_oid_subp_ulti NUMBER
                           )RETURN BOOLEAN;

/***************************************************************************
Descripcion       : verificar si la si exisiste Reclamo
Parametros        :
                   subp_oid_subp_ulti    subp_oid_subp_ulti

Fecha Creacion    : 03/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_EXIST_RECLA(subp_oid_subp_ulti NUMBER
                           )RETURN BOOLEAN;

/***************************************************************************
Descripcion       : Obtiene el porcentaje de comision

Parametros        : psCodPais     Codigo de pais
                    psNivel       Codigo de Nivel
                    psCodPorce    Codigo de Porcentaje
                    psMontoPedido monto del pedido

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_PORCE_COMIS(psCodPais VARCHAR2,
                                  psNivel VARCHAR2,
                                  psCodPorc VARCHAR2,
                                  psMontoPedido NUMBER
                           )RETURN NUMBER;


/***************************************************************************
Descripcion       : Obtiene el codigo de porcentaje comision
                    del periodo de proceso

Parametros        :
                    psCodPais   Codigo Pais
                    psCodMarca  Codigo Marca
                    psCodCanal  Codigo Cana,
                    psCodCampania Codigo de la campaña de proceso

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_CODIG_PORCE_COMIS(psCodPais VARCHAR2,
                                        psCodMarca VARCHAR2,
                                        psCodCanal VARCHAR2,
                                        psCodTipoComisionista VARCHAR2,
                                        psCodCampania VARCHAR2
                           )RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Obtiene el la tolerancia de recuperacion de la comision

Parametros        :
                    psCodPais   Codigo Pais
                    psCodMarca  Codigo Marca
                    psCodCanal  Codigo Cana,
                    psCodComision Codigo de la comision

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_TOLER_RECUP(psCodPais VARCHAR2,
                                        psCodMarca VARCHAR2,
                                        psCodCanal VARCHAR2,
                                        psCodComision VARCHAR2
                           )RETURN NUMBER;



/***************************************************************************
Descripcion       : Retorna 1 si hay bono registrado para ese periodo, caso contrario 0
Parametros        :
                    psCodPais   	Codigo Pais
                    psCodPeriodo  Codigo Periodo
Fecha Creacion    : 09/06/2009
Autor             : Sergio Buchelli
***************************************************************************/
FUNCTION COM_FN_DEVUE_HAY_BONO(psCodPais 		VARCHAR2,
                               psCodPeriodo VARCHAR2
                           )RETURN NUMBER;


/***************************************************************************
Descripcion       : Ejecuta el proceso de reciperacion de comisiones perdidas de G.Z
Parametros        :  psCodPais  	 Codigo Pais,
                     psCodMarca 	 Codigo Marca,
				     psCodCanal 	 Codigo Canal,
				     psCodComision  Codigo Comision ,
				     psAnhoProceso   Anho proceso,
				     psRango         rango (01-06,07-12,13-18),
				     psTipoComision  tipo comiison 0:Recuperacion 1:Actividad
Fecha Creacion    : 18/06/2009
Autor             : Sergio Buchelli
***************************************************************************/
PROCEDURE COM_PR_COMIS_RECUP_PERDI(psCodPais  	 VARCHAR2,
                               	  psCodMarca 	 VARCHAR2,
								  psCodCanal 	 VARCHAR2,
								  psCodComision  VARCHAR2,
								  psAnhoProceso  VARCHAR2,
								  psRango        VARCHAR2,
								  psTipoComision VARCHAR2,--0:Recuperacion 1:Actividad
								  psUsuarioLogin Varchar2,
                  psTipoGerente VARCHAR2);

/***************************************************************************
  Descripcion       : Verifica el total de ingresos bicampañas realizadas
                      y verificando si existe configurado nivel por region y zona,
                      region, o region y zona nulos
  Fecha Creacion    : 19/04/2010
  Autor             : Jesse Rios
  ***************************************************************************/
  FUNCTION COM_FN_VERIF_NIVEL_INGRE(pscodpais         VARCHAR2,
                                    pscodcalificacion VARCHAR2,
                                    pnprombicampanas  NUMBER,
                                    pscodregion       VARCHAR2,
                                    pscodzona         VARCHAR2) RETURN NUMBER;


/***************************************************************************
  Descripcion       : Verifica el total de numero de pedidos realizados
                      y verificando si existe configurado nivel por region y zona,
                      region, o region y zona nulos
  Fecha Creacion    : 19/04/2010
  Autor             : Jesse Rios
  ***************************************************************************/
  FUNCTION COM_FN_VERIF_NIVEL_PEDID(pscodpais         VARCHAR2,
                                    pscodcalificacion VARCHAR2,
                                    pnPromPedidos     NUMBER,
                                    pscodregion       VARCHAR2,
                                    pscodzona         VARCHAR2) RETURN NUMBER;
/***************************************************************************
  Descripcion       : Graba en la tabla historia de comisiones para luego hacer
                      la eliminacion respectiva de las comisiones encontradas por
                      la busqueda en base a los criterios seleccionados en pantalla

  Fecha Creacion    : 27/09/2010
  Autor             : Jesse Rios
  ***************************************************************************/
   PROCEDURE COM_PR_ELIMI_COMIS_RECUP(psCodPais      VARCHAR2,
                                      psCodMarca     VARCHAR2,
                                      psCodCanal     VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      psCodPeriodo   VARCHAR2,
                                      psTipoComision VARCHAR2,
                                      psMensajeError OUT VARCHAR2);

/***************************************************************************
Descripcion       : Devuelve el Numero de comodin de recuperacion para
                    aspirantes segun la
                    campaña procesada y tipo de comisionista
Fecha Creacion    : 03/01/2013
Autor             : Ivan Tocto
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_COMOD_ASPIR(psCodPais             VARCHAR2,
                                        psCodMarca            VARCHAR2,
                                        psCodCanal            VARCHAR2,
                                        psTipoComisionista    VARCHAR2,
                                        psCampania            VARCHAR2)
RETURN VARCHAR2;


/***************************************************************************
  Descripcion       : Calculo de comision de venta
  Fecha Creacion    : 11/01/2013
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENT(psCodPais      VARCHAR2,
                                     psCodComision  VARCHAR2,
                                     psOidComision  VARCHAR2,
                                     psCodPeriodo   VARCHAR2);

/***************************************************************************
  Descripcion       : Insercion de comision de venta de Gerente de Region en una
                      tabla temporal
  Fecha Creacion    : 22/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT_REGIO (
           pscodPeri        varchar2,
           pscodRegi        VARCHAR2,
           pnvalCata        number ,
           pnvalCataEst     number);


/***************************************************************************
  Descripcion       : Insercion de comision de venta
  Fecha Creacion    : 21/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT ( pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2);

/***************************************************************************
  Descripcion       : Calculo de comision de Venta Neta Efectiva
  Fecha Creacion    : 23/01/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENTA_NETEF(psCodPais      VARCHAR2,
                                           psCodMarca     VARCHAR2,
                                           psCodCanal     VARCHAR2,
                                           psCodPeriodo   VARCHAR2,
                                           psCodComision  VARCHAR2,
                                           psUsuarioLogin VARCHAR2);

 /***************************************************************************
  Descripcion       : Calculo de comision de Venta Retail (Base de Comision 06)
  Fecha Creacion    : 01/10/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENTA_RETAI(
            psCodPais       VARCHAR2,
            psCodMarca      VARCHAR2,
            psCodCanal      VARCHAR2,
            psCodComision   VARCHAR2,
            psCodPeriodo    VARCHAR2,
            psUsuario       VARCHAR2
            );

/***************************************************************************
 Descripcion       : Genera información base de recuperación por consultora
 Fecha Creacion    : 30/03/2015
 Autor             : Carlos Mori
 Modificado por    : 
***************************************************************************/
PROCEDURE COM_PR_GENER_BASE_RECUP_COBRA
(
 psCodigoPais     VARCHAR2,
 psCampanaProceso VARCHAR2,
 psCampanaRecaudo VARCHAR2,
 psFechaProceso   VARCHAR2,
 psCodigoUsuario  VARCHAR2
);

END COM_PKG_PROCE;
/
create or replace package body "COM_PKG_PROCE" is

    lsIteracion1  VARCHAR2(1000);
    lsIteracion2  VARCHAR2(1000);
    lsIteracion3  VARCHAR2(1000);

    /***************************************************************************
    Descripcion       : Procedimiemto: Calcula Variables y nivel por seccion
                        campaña

    Fecha Creacion    : 15/04/2008
    Autor             : Leonardo Lizana Chauca
    ***************************************************************************/
    PROCEDURE COM_PR_CALC_VARI_NIVE_SECA(psCodPais            VARCHAR2,
                                         psCodMarca           VARCHAR2,
                                         psCodCanal           VARCHAR2,
                                         psCodPeriodo         VARCHAR2,
                                         psCodTipComisionista VARCHAR2
                                         ) IS

      CURSOR C_VNSC(psOidPeriodo NUMBER) is
          SELECT E.COD_REGI,
                 C.COD_SECC,
                 C.OID_SECC,
                 D.COD_ZONA,
                 SUM(A.NUM_ACTI_INIC) ACTI_INIC,
                 SUM(A.NUM_ACTI_FINA) ACTI_FINA,
                 SUM(A.NUM_INGR) INGRESOS,
                 SUM(A.NUM_REIN) REINGRESOS,
                 SUM(A.NUM_EGRE) EGRESOS
            FROM INT_FUENT_VENTAS_REAL A,
                 ZON_TERRI_ADMIN       B,
                 ZON_SECCI             C,
                 ZON_ZONA              D,
                 ZON_REGIO             E
           WHERE A.TERR_OID_TERR = B.TERR_OID_TERR
             and a.PERD_OID_PERI = psOidPeriodo
             AND (B.PERD_OID_PERI_INIC IS NULL OR psOidPeriodo >= B.PERD_OID_PERI_INIC)
             AND (psOidPeriodo <= B.PERD_OID_PERI_FINA OR
                 B.PERD_OID_PERI_FINA IS NULL)
             AND B.ZSCC_OID_SECC = C.OID_SECC
             AND C.ZZON_OID_ZONA = D.OID_ZONA
             AND D.ZORG_OID_REGI = E.OID_REGI
           GROUP BY E.COD_REGI, D.COD_ZONA,  C.OID_SECC,COD_SECC;

      lnIdMarca             NUMBER;
      lnIdCanal             NUMBER;
      lnIdPais              NUMBER;
      lnIdPeriodo           NUMBER;
      lsIngresoBiCampana    NUMBER;
      lsIngresoCampanaAnterior NUMBER;
      lnNumIngresos            NUMBER;
      lb_codPeriodoAnterior VARCHAR2(12);
      lnValor               NUMBER;
      ldFechaIniPeriodo     DATE;
      ldFechaFinPeriodo     DATE;
      lsMensaje             VARCHAR2(255);
      lsNomLiderSeccion     VARCHAR2(500):='';

      lsCodLiderSeccion     MAE_CLIEN.COD_CLIE%TYPE;
      lsCodCalificacion COM_COMIS_CALIF_CABEC.Cod_Cali%TYPE;
      lsNivel COM_NIVEL.COD_NIVE%TYPE;
      lnNumCampaniaSinNivel NUMBER;
      flag NUMBER;
      ln_NumePedidos NUMBER;
      ls_CodigoSubGerencia ZON_SUB_GEREN_VENTA.Cod_Subg_Vent%TYPE;
      lsInicioCampania VARCHAR2(6);
      lnIndPedidos NUMBER;
      lnIndIngresos NUMBER;

      lnIndPedidosOK VARCHAR2(1);
      lnIndIngresosOK VARCHAR2(1);

    BEGIN
      lsInicioCampania:=COM_FN_OBTIE_INICI_CAMPA(psCodPais,psCodTipComisionista);
      lnIdPais    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
      lnIdMarca   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
      lnIdCanal   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
      lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

      lb_codPeriodoAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo,
                                                                      lnIdPais,
                                                                      lnIdMarca,
                                                                      lnIdCanal,
                                                                      -1);


     INSERT  INTO COM_TMP_NUMER_PEDID
            SELECT V.COD_SUBG_VENT,
               G.COD_REGI,
               F.COD_ZONA,
               E.COD_SECC,
               COUNT(E.COD_SECC) NUM_CANT_PEDI
          FROM PED_SOLIC_CABEC     A,
               PED_SOLIC_CABEC     X,
               PED_TIPO_SOLIC_PAIS B,
               PED_TIPO_SOLIC      C,
               ZON_TERRI_ADMIN     D,
               ZON_SECCI           E,
               ZON_ZONA            F,
               ZON_REGIO           G,
               PED_ESTAD_SOLIC     H,
               ZON_SUB_GEREN_VENTA V
         WHERE A.PERD_OID_PERI = lnIdPeriodo
           AND A.FEC_FACT IS NOT NULL
           AND A.IND_TS_NO_CONSO = 1
           AND A.IND_PEDI_PRUE = 0
           AND A.IND_OC = 1
           AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
           AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
           AND C.IND_DEVO = 0
           AND C.IND_ANUL = 0
           AND A.SOCA_OID_SOLI_CABE = X.OID_SOLI_CABE
           AND X.ESSO_OID_ESTA_SOLI = H.OID_ESTA_SOLI
           AND H.COD_ESTA_SOLI <> 'AN'
           AND A.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
           AND D.ZSCC_OID_SECC = E.OID_SECC
           AND E.ZZON_OID_ZONA = F.OID_ZONA
           AND F.ZORG_OID_REGI = G.OID_REGI
           AND G.ZSGV_OID_SUBG_VENT = V.OID_SUBG_VENT

         GROUP BY V.COD_SUBG_VENT,
                  G.COD_REGI,
                  F.COD_ZONA,
                  E.COD_SECC;


     DELETE COM_HISTO_VARIA_EJCAM T WHERE T.COD_CAMP = psCodPeriodo;
     lsCodCalificacion :=COM_FN_DEVUE_CALIF_COMIS(psCodPais,psCodMarca,psCodCanal,psCodTipComisionista,psCodPeriodo);

      FOR fila IN C_VNSC(lnIdPeriodo) LOOP
               lnIndPedidosOK := NULL;
               lnIndIngresosOK := NULL;
               lsIngresoCampanaAnterior:=0;
               flag := COM_FN_OBTIE_NUMER_PEDID(fila.cod_regi,fila.cod_zona,fila.cod_secc, ln_NumePedidos, ls_CodigoSubGerencia);

               lsNomLiderSeccion := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                     lsCodLiderSeccion,
                                     psCodPeriodo,
                                     lnIdPais,
                                     ls_CodigoSubGerencia,
                                     fila.cod_regi,
                                     fila.cod_zona,
                                     fila.cod_secc);

      /* ingresos bi campaña, obteniendo datos de la campaña anterior*/
      IF(psCodPeriodo>=lsInicioCampania) THEN
          lsIngresoCampanaAnterior := COM_FN_OBTIE_BI_CAMPA(pscodPais,psCodMarca,psCodCanal,lsCodLiderSeccion,lb_codPeriodoAnterior);

          IF(lsIngresoCampanaAnterior <>-1) THEN
                           lsIngresoBiCampana := lsIngresoCampanaAnterior+fila.ingresos;
          ELSE
               lsIngresoCampanaAnterior := COM_FN_OBTIE_BI_CAMPA(psCodPais,psCodMarca,psCodCanal,lb_codPeriodoAnterior, fila.cod_regi, fila.cod_zona, fila.cod_secc);
               IF(lsIngresoCampanaAnterior<>-1) THEN
                     lsIngresoBiCampana := lsIngresoCampanaAnterior+fila.ingresos;
               ELSE
                   lnNumIngresos:= COM_FN_OBTIE_SUMA_TERRI_SIMU(lnIdPais,lnIdPeriodo,lnIdPeriodo-1,fila.oid_secc);
                   lsIngresoBiCampana := lnNumIngresos + fila.ingresos;

               END IF;
          END IF;
          lsNivel:= COM_FN_OBTIE_NIVEL_EJECU(psCodPais,lsCodCalificacion,ln_NumePedidos,lsIngresoBiCampana,fila.cod_regi,fila.cod_zona);
      ELSE
          lsIngresoBiCampana:=0;
      END IF;

      lnNumCampaniaSinNivel:=COM_FN_OBTIE_NUMER_CAMP_SNIVE(psCodPais,psCodMarca,PSCodCanal,psCodPeriodo,lsCodLiderSeccion,lsNivel);

      IF lsNivel IS NOT NULL AND lsNivel = 'SN' THEN

         lnIndPedidos := COM_FN_VERIF_NIVEL_PEDID(psCodPais,lsCodCalificacion,ln_NumePedidos,fila.cod_regi,fila.cod_zona);

         lnIndIngresos := COM_FN_VERIF_NIVEL_INGRE(psCodPais,lsCodCalificacion,lsIngresoBiCampana,fila.cod_regi,fila.cod_zona);

         IF lnIndPedidos >= 1 THEN
            lnIndPedidosOK := '1';
         ELSE
             lnIndPedidosOK := '0';
         END IF;

         IF lnIndIngresos >= 1 THEN
            lnIndIngresosOK := '1';
         ELSE
             lnIndIngresosOK := '0';
         END IF;
      END IF;

       INSERT INTO COM_HISTO_VARIA_EJCAM
        (COD_PAIS,
         COD_CALI,
         COD_CAMP,
         COD_REGI,
         COD_ZONA,
         COD_SECC,
         COD_MARC,
         COD_CANA,
         COD_EJEC,
         NUM_ACTI_INIC,
         NUM_ACTI_FINA,
         NUM_INGR_PERI,
         NUM_INGR_BICA,
         NUM_REIN,
         NUM_EGRE,
         NUM_PEDI,
         COD_NIVE,
         NUM_CAMP_SNIV,
         COD_TIPO_COMI,
         FEC_CALC,
         IND_PEDI_OK,
         IND_INGR_OK)
      VALUES
        (psCodPais,
         lsCodCalificacion,
         psCodPeriodo,
         fila.cod_regi,
         fila.cod_zona,
         fila.cod_secc,
         psCodMarca,
         psCodCanal,
         lsCodLiderSeccion,
         fila.acti_inic,
         fila.acti_fina,
         fila.ingresos,
         lsIngresoBiCampana,
         fila.reingresos,
         fila.egresos,
         ln_NumePedidos,
         lsNivel,
         lnNumCampaniaSinNivel,
         psCodTipComisionista,
         SYSDATE,
         lnIndPedidosOK,
         lnIndIngresosOK);

      END LOOP;
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,'ERROR COM_PR_CALC_VARI_NIVE_SECA: ' || ls_sqlerrm);
END COM_PR_CALC_VARI_NIVE_SECA;

/***************************************************************************
Descripcion       : Devuelve el codigo de calificacion de comision segun la
                    campaña procesada y tipo de comisionista
Fecha Creacion    : 17/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_CALIF_COMIS
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   psTipoComisionista    VARCHAR2,
   psCampania            VARCHAR2) RETURN VARCHAR2
IS
  lsCodCalificacion      COM_COMIS_CALIF_CABEC.COD_CALI%TYPE;
BEGIN

  SELECT COD_CALI
    INTO lsCodCalificacion
    FROM COM_COMIS_CALIF_CABEC
   WHERE COD_PAIS = PSCODPAIS
     AND COD_MARC = PSCODMARCA
     AND COD_CANA = PSCODCANAL
     AND COD_TIPO_COMI = PSTIPOCOMISIONISTA
     AND PSCAMPANIA >= CAM_VIGE_DESD
     AND (CAM_VIGE_HAST IS NULL OR (CAM_VIGE_HAST >= PSCAMPANIA));

  RETURN lsCodCalificacion;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_CALIF_COMIS: '||ls_sqlerrm);
END;



/***************************************************************************
Descripcion       : Recupera BiCampaña si no encuentra retorna -1
Parametros          Codigo Pais
                    Codigo Marca
                    Codigo Canal
                    Codigo Ejecutiva
                    Codigo Periodo

Fecha Creacion    : 18/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_BI_CAMPA(psCodPais VARCHAR2,
                               psCodMarca VARCHAR2,
                               psCodCanal VARCHAR2,
                               psCodigoEjecutiva VARCHAR2,
                               psOCodigoPeriodo  VARCHAR2) RETURN NUMBER IS

  ls_NumIngresos COM_HISTO_VARIA_EJCAM.NUM_INGR_PERI%TYPE;
BEGIN
   SELECT A.Num_Ingr_Peri
    INTO ls_NumIngresos
    FROM COM_HISTO_VARIA_EJCAM A
   WHERE A.COD_PAIS = psCodPais
     AND A.COD_MARC = psCodMarca
     AND A.COD_CANA = psCodCanal
     AND A.COD_EJEC = psCodigoEjecutiva
     AND A.COD_CAMP = psOCodigoPeriodo;

    RETURN ls_NumIngresos;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN -1;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_BI_CAMPA: ' ||
                            psCodigoEjecutiva ||' - '|| psOCodigoPeriodo);
    RETURN '';
END COM_FN_OBTIE_BI_CAMPA;

/***************************************************************************
Descripcion       :  Recupera BiCampaña si no encuentra retorna -1
Parametros        :  Codigo Pais
                     Codigo Marca
                     Codigo Canal
                     Codigo Periodo
                     Codigo Region
                     Codigo Zona
                     Codigo Seccion

Fecha Creacion    : 18/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_BI_CAMPA( psCodPais VARCHAR2,
                                psCodMarca VARCHAR2,
                                psCodCanal VARCHAR2,
                                psCodigoPeriodo VARCHAR2,
                                psCodRegion VARCHAR2,
                                psCodZona VARCHAR2,
                                psCodSeccion VARCHAR2) RETURN NUMBER IS
 ls_NumIngresos COM_HISTO_VARIA_EJCAM.NUM_INGR_PERI%TYPE;
BEGIN
   SELECT A.Num_Ingr_Peri
    INTO ls_NumIngresos
    FROM COM_HISTO_VARIA_EJCAM A
   WHERE A.COD_CAMP = psCodigoPeriodo
         AND A.COD_PAIS = psCodPais
         AND A.COD_MARC = psCodMarca
         AND A.COD_CANA = psCodCanal
         AND A.COD_REGI = psCodRegion
         AND A.COD_ZONA = psCodZona
         AND A.COD_SECC = psCodSeccion;

    RETURN ls_NumIngresos;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN -1;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_BI_CAMPA: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_BI_CAMPA;

/***************************************************************************
Descripcion       :  Recupera la suma de simular territorios que pertenescan
                     al periodo anterior y sumar sus Ingresos.
Parametros        :  oid Pais
                     oid periodo
                     oid periodo anterior
                     oid Seccion

Fecha Creacion    : 18/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_SUMA_TERRI_SIMU( psOidPais NUMBER,
                                       psOidPeriodo NUMBER,
                                       psOidPeriodoAnterior NUMBER,
                                       psOidSeccion NUMBER
                                       ) RETURN NUMBER IS

CURSOR c_TERRI_SIMU(psOidPais NUMBER
                   ,psOidPeriodo NUMBER
                   ,psOidSeccion NUMBER) IS
   SELECT Z.*
          FROM ZON_TERRI_ADMIN Z
    WHERE (Z.PERD_OID_PERI_INIC IS NULL OR psOidPeriodo >= Z.PERD_OID_PERI_INIC)
      AND (psOidPeriodo <= Z.PERD_OID_PERI_FINA OR Z.PERD_OID_PERI_FINA IS NULL)
      AND Z.ZSCC_OID_SECC = psOidSeccion
      AND Z.PAIS_OID_PAIS = psOidPais;

  ln_sumIngresos NUMBER :=0;
  ln_numIngreso NUMBER :=0;
  lnOidStatusNuevas        MAE_ESTAT_CLIEN.Oid_Esta_Clie%TYPE;
  lnOidStatusRectivadas    MAE_ESTAT_CLIEN.Oid_Esta_Clie%TYPE;
BEGIN
        lnOidStatusNuevas:= COM_FN_OBTIE_ESTAD_CLIET('02');
        lnOidStatusRectivadas:= COM_FN_OBTIE_ESTAD_CLIET('08');
        FOR fila IN c_TERRI_SIMU(psOidPais, psOidPeriodo, psOidSeccion) LOOP
           BEGIN
                SELECT F.NUM_INGR
                INTO ln_numIngreso
                FROM  INT_FUENT_VENTAS_REAL F
                WHERE  F.PAIS_OID_PAIS =psOidPais
                       AND F.PERD_OID_PERI = psOidPeriodoAnterior
                       AND F.TERR_OID_TERR = fila.terr_oid_terr;

                ln_sumIngresos:=ln_sumIngresos+ln_numIngreso;

           EXCEPTION
             WHEN NO_DATA_FOUND THEN

               SELECT COUNT(1)
                 INTO ln_numIngreso
                 FROM MAE_CLIEN_UNIDA_ADMIN MC,
                      MAE_CLIEN_HISTO_ESTAT MH
                WHERE MC.ZTAD_OID_TERR_ADMI = fila.oid_terr_admi
                  AND (MC.PERD_OID_PERI_INI IS NULL OR psOidPeriodo >= MC.PERD_OID_PERI_INI)
                  AND (psOidPeriodo <= MC.PERD_OID_PERI_FIN OR MC.PERD_OID_PERI_FIN IS NULL)
                  AND MC.CLIE_OID_CLIE = MH.CLIE_OID_CLIE
                  AND MH.PERD_OID_PERI = psOidPeriodoAnterior
                  AND (MH.ESTA_OID_ESTA_CLIE = lnOidStatusNuevas OR MH.ESTA_OID_ESTA_CLIE = lnOidStatusRectivadas);

               ln_sumIngresos := ln_sumIngresos+ln_numIngreso;

         END;

        END LOOP;

    RETURN ln_sumIngresos;

EXCEPTION

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_SUMA_TERRI_SIMU: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_SUMA_TERRI_SIMU;

/***************************************************************************
Descripcion       :  Recurpera el Nivel que lo corresponde a la ejecutiva
                     si no tiene ningun nivel se le asignara SN=Sin nivel
Parametros        :  Codigo Pais
                     Codigo de Calificacion
                     Numero de Pedios
                     Codigo Zona
                     Numero de Ingresos BiCampaña
                     Codigo Region
                     Codigo Zona

Fecha Creacion    : 21/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_NIVEL_EJECU( psCodPais VARCHAR2,
                                   psCodCalificacion VARCHAR2,
                                   psNumPedios NUMBER,
                                   psNumIngrsosBiCampana NUMBER,
                                   psCodRegion VARCHAR2,
                                   psCodZona VARCHAR2
                                       ) RETURN VARCHAR2 IS

   CURSOR c_RegionAndZona is
   SELECT COD_REGI, COD_ZONA
     FROM COM_COMIS_CALIF_DETAL
     WHERE
          COD_REGI IS NOT NULL
          AND COD_ZONA IS NOT NULL
          AND COD_REGI=psCodRegion
          AND COD_ZONA = psCodZona;

   CURSOR c_Region is
   SELECT COD_REGI, COD_ZONA
     FROM COM_COMIS_CALIF_DETAL
     WHERE
          COD_REGI IS NOT NULL
          AND COD_ZONA IS NULL
          AND COD_REGI=psCodRegion;

   CURSOR c_NoRegionNiZona is
   SELECT COD_REGI, COD_ZONA
     FROM COM_COMIS_CALIF_DETAL
     WHERE
          COD_REGI IS NULL
          AND COD_ZONA IS NULL;


  ls_Nivel COM_COMIS_CALIF_DETAL.COD_NIVE%TYPE;

BEGIN
      FOR fila IN c_RegionAndZona LOOP
           BEGIN
                 SELECT COD_NIVE
                   INTO ls_Nivel
                   FROM COM_COMIS_CALIF_DETAL A
                  WHERE A.COD_PAIS = psCodPais
                    AND A.COD_CALI = psCodCalificacion
                    AND A.COD_REGI = psCodRegion
                    AND A.COD_ZONA = psCodZona
                    AND psNumPedios >= A.NUM_PEDI_DESD
                    AND psNumPedios <= A.NUM_PEDI_HAST
                    AND psNumIngrsosBiCampana >= A.NUM_INGR_DESD
                    AND psNumIngrsosBiCampana <= A.NUM_INGR_HAST;

                 RETURN ls_Nivel;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
               RETURN 'SN';
           END;
       END LOOP;

      FOR fila IN c_Region LOOP
          BEGIN
                 SELECT COD_NIVE
                   INTO ls_Nivel
                   FROM COM_COMIS_CALIF_DETAL A
                  WHERE A.COD_PAIS = psCodPais
                    AND A.COD_CALI = psCodCalificacion
                    AND A.COD_REGI = psCodRegion
                    AND A.COD_ZONA IS NULL
                    AND psNumPedios >= A.NUM_PEDI_DESD
                    AND psNumPedios <= A.NUM_PEDI_HAST
                    AND psNumIngrsosBiCampana >= A.NUM_INGR_DESD
                    AND psNumIngrsosBiCampana <= A.NUM_INGR_HAST;

                 RETURN ls_Nivel;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
               RETURN 'SN';
           END;
       END LOOP;

       FOR fila IN c_NoRegionNiZona LOOP
           BEGIN
           SELECT COD_NIVE
             INTO ls_Nivel
             FROM COM_COMIS_CALIF_DETAL A
            WHERE A.COD_PAIS = psCodPais
              AND A.COD_CALI = psCodCalificacion
                              AND A.COD_REGI IS NULL
                              AND A.COD_ZONA IS NULL
              AND psNumPedios >=A.NUM_PEDI_DESD
              AND psNumPedios <=A.NUM_PEDI_HAST
              AND psNumIngrsosBiCampana >= A.NUM_INGR_DESD
              AND psNumIngrsosBiCampana <= A.NUM_INGR_HAST;

              RETURN ls_Nivel;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
               RETURN 'SN';
           END;
       END LOOP;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
      RETURN 'SN';
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_NIVEL_EJECU: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_NIVEL_EJECU;

/***************************************************************************
Descripcion       :  Recurpera el Estado del Cliente, si no Error

Fecha Creacion    : 21/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_ESTAD_CLIET (psEstado VARCHAR2)RETURN NUMBER IS

  lnOidStatus MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE;


BEGIN

 SELECT T.OID_ESTA_CLIE
   INTO lnOidStatus
   FROM MAE_ESTAT_CLIEN T
  WHERE T.COD_ESTA_CLIE = psEstado;

 RETURN lnOidStatus;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_ESTAD_CLIET: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_ESTAD_CLIET;

/***************************************************************************
Descripcion       : Recurpera numero de Campañas sin nivel en en el tramo
                    de lo contrario Error
Parametros        : Codigo Pais
                    Codigo Marca
                    Codigo Canal
                    Codigo Periodo
                    Codigo Nivel

Fecha Creacion    : 21/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_NUMER_CAMP_SNIVE(psCodPais    VARCHAR2,
                                       psCodMarca   VARCHAR2,
                                       PSCodCanal   VARCHAR2,
                                       psCodPeriodo VARCHAR2,
                                       lsCodLiderSeccion VARCHAR2,
                                       psNivel      VARCHAR2
                                       ) RETURN NUMBER IS

  CURSOR c_TRAMO(psCodPais VARCHAR2) IS
    SELECT * FROM COM_TRAMO_EJECU;

  lsAnioCampania    VARCHAR(4);
  lsCampaniaProceso VARCHAR2(2);
  lnCampaniaInicio  NUMBER;
  lnCampaniaFin     NUMBER;
  lnNumCampaniasSinNivel NUMBER;

BEGIN

  lsAnioCampania    := SUBSTR(psCodPeriodo, 0, 4);
  lsCampaniaProceso := SUBSTR(psCodPeriodo, 5);
  IF (lsCampaniaProceso IS NOT NULL) THEN
    FOR fila IN c_TRAMO(psCodPais) LOOP

      IF (fila.cam_inic < fila.cam_fina) THEN
        IF (lsCampaniaProceso >= fila.cam_inic AND lsCampaniaProceso <= fila.cam_fina) THEN
          lnCampaniaInicio := lsAnioCampania || fila.cam_inic;
          lnCampaniaFin    := lsAnioCampania || lsCampaniaProceso;
          EXIT;
        END IF;
      ELSE
          IF(lsCampaniaProceso>=fila.cam_inic AND lsCampaniaProceso<=lnTotalPeriodos)THEN
            lnCampaniaInicio := lsAnioCampania || fila.cam_inic;
            lnCampaniaFin    := lsAnioCampania || lsCampaniaProceso;
          ELSE
            lnCampaniaInicio := TO_CHAR(TO_NUMBER(lsAnioCampania-1)) || fila.cam_inic;
            lnCampaniaFin    := lsAnioCampania || lsCampaniaProceso;
          END IF;


        EXIT;
      END IF;
    END LOOP;

    SELECT COUNT(1)
      INTO lnNumCampaniasSinNivel
      FROM COM_HISTO_VARIA_EJCAM VE
     WHERE VE.COD_CAMP >= lnCampaniaInicio
       AND VE.COD_CAMP < lnCampaniaFin
       AND VE.COD_NIVE ='SN'
       AND VE.COD_PAIS = psCodPais
       AND VE.COD_MARC = psCodMarca
       AND VE.COD_CANA = PSCodCanal
       AND VE.COD_EJEC = lsCodLiderSeccion;

    IF (psNivel = 'SN') THEN
       lnNumCampaniasSinNivel := lnNumCampaniasSinNivel +1;
    END IF;


  ELSE RAISE_APPLICATION_ERROR(-20123,
                                   'ERROR COM_FN_OBTIE_NUMER_CAMP_SNIVE: Número de Campaña ' ||
                                   lsAnioCampania || 'Null');
  END IF;

  RETURN lnNumCampaniasSinNivel;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_NUMER_CAMP_SNIVE: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_NUMER_CAMP_SNIVE;

/***************************************************************************
Descripcion       : Acumula las variables de venta y los ingresos bicampaña
                    en un tramo de campañas. Una vez que se tienes las variables
                    se obtiene el nivel correspondiente de acuerdo a la cantidad
                    de pedidos e ingresos bicampaña promedio
                    indica si se encontro erorres o no
Fecha Creacion    : 17/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_CALCU_CALIF_EJECU_TRAMO
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psTipoComisionista    VARCHAR2)
IS
  CURSOR cursorCampaniaTramo(oidPais NUMBER, oidMarca NUMBER, oidCanal NUMBER, campaniaInicio VARCHAR2, campaniaFin VARCHAR2) IS
    SELECT COR.COD_PERI, CRA.OID_PERI
      FROM SEG_PERIO_CORPO COR, CRA_PERIO CRA
     WHERE COD_PERI >= CAMPANIAINICIO
       AND COD_PERI <= CAMPANIAFIN
       AND CRA.PERI_OID_PERI = COR.OID_PERI
       AND CRA.PAIS_OID_PAIS = OIDPAIS
       AND CRA.MARC_OID_MARC = OIDMARCA
       AND CRA.CANA_OID_CANA = OIDCANAL;

  CURSOR cursorHistoEjecuCampania(oidPais NUMBER, oidPeriodo NUMBER) IS
    SELECT SUB.COD_SUBG_VENT, REG.COD_REGI, ZON.COD_ZONA, SEC.COD_SECC,
           COUNT(DISTINCT CONSO.OID_SOLI_CABE) TOTAL
      FROM PED_SOLIC_CABEC SC,
           ZON_TERRI_ADMIN ZTA,
           ZON_SECCI SEC,
           ZON_ZONA ZON,
           ZON_REGIO REG,
    	     ZON_SUB_GEREN_VENTA SUB,
           (SELECT S.OID_SOLI_CABE, S.VAL_NUME_SOLI, S.FEC_FACT
              FROM PED_SOLIC_CABEC S, PED_ESTAD_SOLIC E
             WHERE S.PAIS_OID_PAIS = oidPais
               AND S.PERD_OID_PERI = oidPeriodo
               AND S.IND_TS_NO_CONSO = 0
               AND S.IND_PEDI_PRUE = 0
               AND S.ESSO_OID_ESTA_SOLI = E.OID_ESTA_SOLI
               AND E.COD_ESTA_SOLI <> 'AN') CONSO,
           PED_TIPO_SOLIC TS,
           PED_TIPO_SOLIC_PAIS SP
     WHERE SC.SOCA_OID_SOLI_CABE = CONSO.OID_SOLI_CABE
       AND SC.PAIS_OID_PAIS = OIDPAIS
       AND SC.IND_OC = 1
       AND SC.IND_TS_NO_CONSO = 1
       AND SC.IND_PEDI_PRUE = 0
       AND SC.FEC_FACT IS NOT NULL
       AND SC.TSPA_OID_TIPO_SOLI_PAIS = SP.OID_TIPO_SOLI_PAIS
       AND SP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
       AND SC.PERD_OID_PERI = OIDPERIODO
       AND TS.IND_DEVO = 0
       AND TS.IND_ANUL = 0
       AND SC.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
       AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
       AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA
       AND ZON.ZORG_OID_REGI = REG.OID_REGI
       AND REG.ZSGV_OID_SUBG_VENT = SUB.OID_SUBG_VENT
    GROUP BY SUB.COD_SUBG_VENT, REG.COD_REGI, ZON.COD_ZONA, SEC.COD_SECC;

  CURSOR cursorHistoEjecuTramo(codPais VARCHAR2, codCalificacion VARCHAR2, anioInicial NUMBER,
                               codRango VARCHAR2, tipoComisionista VARCHAR2) IS
    SELECT   CAB.COD_PAIS, CAB.COD_CALI, CAB.NUM_ANIO_INIC, CAB.COD_RANG,
             CAB.COD_EJEC, CAB.COD_REGI, CAB.COD_ZONA,
             SUM (CASE
                     WHEN DET.NUM_PEDI_CAMP = 0
                        THEN 0
                     ELSE 1
                  END) TOTALCAMPANAS,
             SUM (CASE
                     WHEN DET.NUM_PEDI_CAMP = 0
                        THEN 0
                     ELSE DET.NUM_INGR_BICA
                  END
                 ) TOTALBICAMPANAS,
             SUM (DET.NUM_PEDI_CAMP) TOTALPEDIDOS
        FROM COM_HISTO_VARIA_EJETR_CABEC CAB, COM_HISTO_VARIA_EJETR_DETAL DET
       WHERE CAB.COD_PAIS = CODPAIS
         AND CAB.COD_CALI = CODCALIFICACION
         AND CAB.NUM_ANIO_INIC = ANIOINICIAL
         AND CAB.COD_RANG = CODRANGO
         AND CAB.COD_TIPO_COMI = TIPOCOMISIONISTA
         AND DET.COD_PAIS = CAB.COD_PAIS
         AND DET.COD_CALI = CAB.COD_CALI
         AND DET.NUM_ANIO_INIC = CAB.NUM_ANIO_INIC
         AND DET.COD_RANG = CAB.COD_RANG
         AND DET.COD_EJEC = CAB.COD_EJEC
    GROUP BY CAB.COD_PAIS,
             CAB.COD_CALI,
             CAB.NUM_ANIO_INIC,
             CAB.COD_RANG,
             CAB.COD_EJEC,
             CAB.COD_REGI,
             CAB.COD_ZONA;

  TYPE estruHistoEjecuCampania IS RECORD
  (
    COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
    COD_REGI               ZON_REGIO.COD_REGI%TYPE,
    COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
    COD_SECC               ZON_SECCI.COD_SECC%TYPE,
    TOTAL                  NUMBER
   );

  TYPE estruHistoEjecuCampaniaTab  IS TABLE OF estruHistoEjecuCampania;
  tablaHistoEjecuCampania          estruHistoEjecuCampaniaTab;
  registroHistoEjecuCampania       estruHistoEjecuCampania;

  TYPE estruHistoEjecuTramo IS RECORD
  (
    COD_PAIS               COM_HISTO_VARIA_EJETR_CABEC.COD_PAIS%TYPE,
    COD_CALI               COM_HISTO_VARIA_EJETR_CABEC.COD_CALI%TYPE,
    NUM_ANIO_INIC          COM_HISTO_VARIA_EJETR_CABEC.NUM_ANIO_INIC%TYPE,
    COD_RANG               COM_HISTO_VARIA_EJETR_CABEC.COD_RANG%TYPE,
    COD_EJEC               COM_HISTO_VARIA_EJETR_CABEC.COD_EJEC%TYPE,
    COD_REGI               COM_HISTO_VARIA_EJETR_CABEC.COD_REGI%TYPE,
    COD_ZONA               COM_HISTO_VARIA_EJETR_CABEC.COD_ZONA%TYPE,
    TOTALCAMPANAS          NUMBER,
    TOTALBICAMPANAS        NUMBER,
    TOTALPEDIDOS           NUMBER
   );

  TYPE estruHistoEjecuTramoTab  IS TABLE OF estruHistoEjecuTramo;
  tablaHistoEjecuTramo          estruHistoEjecuTramoTab;
  registroHistoEjecuTramo       estruHistoEjecuTramo;

  lsCampaniaInicio          VARCHAR2(6);
  lsCampaniaFin             VARCHAR2(6);
  ldFechaIniPeri            DATE;
  ldFechaFinPeri            DATE;

  lnIdPais                  SEG_PAIS.OID_PAIS%TYPE;
  lnIdMarca                 SEG_MARCA.OID_MARC%TYPE;
  lnIdCanal                 SEG_CANAL.OID_CANA%TYPE;

  lnIdPeriodo               CRA_PERIO.OID_PERI%TYPE;
  lsCodPeriodo              SEG_PERIO_CORPO.COD_PERI%TYPE;

  lsCodCalificacion         COM_COMIS_CALIF_CABEC.COD_CALI%TYPE;
  lsCodResponsable          COM_HISTO_VARIA_EJETR_CABEC.COD_EJEC%TYPE;
  lsResponsable             VARCHAR2(100);
  lsCodNivel                COM_HISTO_VARIA_EJETR_CABEC.COD_NIVE%TYPE;

  lnPromBiCampanas          COM_HISTO_VARIA_EJETR_CABEC.NUM_PROM_INGR_BICA%TYPE;
  lnPromPedidos             COM_HISTO_VARIA_EJETR_CABEC.NUM_PROM_PEDI%TYPE;
  lsMensaje                 VARCHAR2(250);
  lnResult                  NUMBER;

  lsCodTramoAnt             COM_TRAMO_EJECU.COD_RANG%TYPE;
  lnAnioInicialAnt          NUMBER;
  lsCodNivelAnt             COM_HISTO_VARIA_EJETR_CABEC.COD_NIVE%TYPE;
  lsTotalCampAspirante      NUMBER;

  lnNumCampAspirante        COM_TIPOS_COMIS.NUM_CAMP_ASPI%TYPE;
  lsCampPrimeEvaluacion     COM_TIPOS_COMIS.CAM_PRIM_EVAL%TYPE;
  lsUltimaCampTutoras       COM_TIPOS_COMIS.ULT_CAMP_TUTO%TYPE;
  lnNumTotaEjec             COM_RESUM_EVALU_TRAMO.NUM_TOTA_EJEC%TYPE;
BEGIN

  --RECUPERAMOS EL OID DEL PAIS, MARCA, CANAL
  lnIdPais   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
  lnIdMarca  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
  lnIdCanal  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);

  --RECUPERAMOS LA CAMPAÑA INICIAL Y FINAL DEL TRAMO SELECCIONADO
  COM_PR_DEVUE_CAMPA_TRAMO(psCodPais, psCodTramo, pnAnioInicial, lsCampaniaInicio, lsCampaniaFin);

  --OBTENEMOS CODIGO DE CALIFICACION CORRESPONDIENTE A LA PRIMERA CAMPAÑA DEL RANGO
  lsCodCalificacion := COM_FN_DEVUE_CALIF_COMIS(psCodPais, psCodMarca, psCodCanal, psTipoComisionista, lsCampaniaInicio);

  --RECUPERAMOS LA CANTIDAD DE CAMPAÑAS ASPIRANTES, PRIMERA CAMPAÑA DE EVALUACION,
  --ULTIMA CAMPAÑA DE EVALUACION DE LAS TUTORAS, RELACIONADAS AL TIPO DE COMISIONISTA
  SELECT NUM_CAMP_ASPI, CAM_PRIM_EVAL, ULT_CAMP_TUTO
  INTO   lnNumCampAspirante, lsCampPrimeEvaluacion, lsUltimaCampTutoras
  FROM   COM_TIPOS_COMIS a
  WHERE  COD_PAIS = psCodPais
    AND  COD_TIPO_COMI = psTipoComisionista;

  --BORRAMOS LOS REGISTROS DE LAS TABLAS CORRESPONDIENTE AL TRAMO SELECCIONADO
  DELETE FROM COM_HISTO_VARIA_EJETR_DETAL
  WHERE COD_PAIS = psCodPais
    AND NUM_ANIO_INIC = pnAnioInicial
    AND COD_RANG = psCodTramo;

  DELETE FROM COM_HISTO_VARIA_EJETR_CABEC
  WHERE COD_PAIS = psCodPais
    AND NUM_ANIO_INIC = pnAnioInicial
    AND COD_RANG = psCodTramo;

  DELETE FROM COM_RESUM_EVALU_TRAMO
  WHERE COD_PAIS = psCodPais
    AND NUM_ANIO_INIC = pnAnioInicial
    AND COD_RANG = psCodTramo;

  --INSERTAMOS LAS EJECUTIVAS QUE HAN SIDO EVALUADAS EN LAS CAMPAÑAS QUE CORRESPONDEN AL TRAMO SELECCIONADO
  INSERT INTO COM_HISTO_VARIA_EJETR_CABEC
    (COD_PAIS, COD_MARC, COD_CANA, COD_TIPO_COMI,
     COD_CALI, NUM_ANIO_INIC, COD_RANG, COD_EJEC,
     COD_REGI, COD_ZONA, COD_SECC, FEC_CALC)
  SELECT COD_PAIS, COD_MARC, COD_CANA, COD_TIPO_COMI,
         lsCodCalificacion, pnAnioInicial, psCodTramo, COD_EJEC,
         COD_REGI, COD_ZONA, COD_SECC, SYSDATE
   FROM COM_HISTO_VARIA_EJCAM
  WHERE COD_PAIS = psCodPais
    AND COD_MARC = psCodMarca
    AND COD_CANA = psCodCanal
    AND COD_CAMP = lsCampaniaFin
    AND COD_TIPO_COMI = psTipoComisionista
    AND COD_EJEC IS NOT NULL;

  --INSERTAMOS LAS CANTIDADES DE INGRESOS y BICAMPAÑA POR CAMPAÑA PARA CADA UNA DE LAS EJECUTIVAS
  --QUE HAN SIDO EVALUADAS EN LAS CAMPAÑAS QUE CORRESPONDEN AL TRAMO SELECCIONADO
  INSERT INTO COM_HISTO_VARIA_EJETR_DETAL
    (COD_PAIS, COD_CALI, NUM_ANIO_INIC,
     COD_RANG, COD_EJEC, CAM_PROC,
     NUM_INGR_CAMP, NUM_INGR_BICA, NUM_PEDI_CAMP)
  SELECT DISTINCT COD_PAIS, lsCodCalificacion, pnAnioInicial,
         psCodTramo, COD_EJEC, COD_CAMP,
         NUM_INGR_PERI, NUM_INGR_BICA, 0
   FROM COM_HISTO_VARIA_EJCAM
  WHERE COD_PAIS = psCodPais
    AND COD_MARC = psCodMarca
    AND COD_CANA = psCodCanal
    AND COD_CAMP >= lsCampaniaInicio
    AND COD_CAMP <= lsCampaniaFin
    AND COD_TIPO_COMI = psTipoComisionista
    AND COD_EJEC IS NOT NULL
    AND COD_EJEC IN (SELECT COD_EJEC
                     FROM   COM_HISTO_VARIA_EJETR_CABEC
                      WHERE COD_PAIS = psCodPais
                        AND COD_MARC = psCodMarca
                        AND COD_CANA = psCodCanal
                        AND NUM_ANIO_INIC = pnAnioInicial
                        AND COD_RANG = psCodTramo
                        AND COD_CALI = lsCodCalificacion
                        AND COD_TIPO_COMI = psTipoComisionista
                     );

  --RECORREMOS CADA UNA DE LAS CAMPAÑAS QUE COMPONEN EL TRAMO QUE SE ESTA EVALUANDO
  OPEN cursorCampaniaTramo(lnIdPais, lnIdMarca, lnIdCanal, lsCampaniaInicio, lsCampaniaFin);
  LOOP
    FETCH cursorCampaniaTramo INTO lsCodPeriodo, lnIdPeriodo;
    EXIT WHEN cursorCampaniaTramo%NOTFOUND;

    --OBTENEMOS LOS CONSOLIDADOS, AGRUPADO POR PERIODO Y SECCION
    OPEN cursorHistoEjecuCampania(lnIdPais, lnIdPeriodo);
    LOOP
      FETCH cursorHistoEjecuCampania BULK COLLECT INTO tablaHistoEjecuCampania LIMIT W_FILAS;
      IF tablaHistoEjecuCampania.COUNT > 0 THEN

        FOR x IN tablaHistoEjecuCampania.FIRST .. tablaHistoEjecuCampania.LAST LOOP
          registroHistoEjecuCampania := tablaHistoEjecuCampania(x);

          lsResponsable := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(lsCodResponsable,
                                         lsCodPeriodo, lnIdPais,
                                         registroHistoEjecuCampania.COD_SUBG_VENT,
                                         registroHistoEjecuCampania.COD_REGI,
                                         registroHistoEjecuCampania.COD_ZONA,
                                         registroHistoEjecuCampania.COD_SECC);

          --ENCONTRO RESPONSABLE DE LA SECCION PARA LA CAMPAÑA EN PROCESO, GRABAMOS NUMERO DE PEDIDOS
          IF (lsCodResponsable IS NOT NULL) THEN
            UPDATE COM_HISTO_VARIA_EJETR_DETAL
               SET NUM_PEDI_CAMP = registroHistoEjecuCampania.TOTAL
             WHERE COD_PAIS = psCodPais
               AND COD_CALI = lsCodCalificacion
               AND COD_EJEC = lsCodResponsable
               AND NUM_ANIO_INIC = pnAnioInicial
               AND COD_RANG = psCodTramo
               AND CAM_PROC = lsCodPeriodo;
          END IF;

        END LOOP;
      END IF;

      EXIT WHEN cursorHistoEjecuCampania%NOTFOUND;
    END LOOP;
    CLOSE cursorHistoEjecuCampania;

  END LOOP;
  CLOSE cursorCampaniaTramo;


  --RECORREMOS LAS EJECUTIVAS A EVALUARSE EN EL TRAMO EN PROCESO, CALCULAMOS
  --EL PROMEDIO DE PEDIDOS Y BICAMPANAS SOBRE LAS CAMPAÑAS DONDE HAYA HABIDO PEDIDO (NumeroPedidos>0)
  --LUEGO CALCULAMOS EL NIVEL QUE ALCANZO LA EJECUTIVA EN EL TRAMO
  OPEN cursorHistoEjecuTramo(psCodPais, lsCodCalificacion, pnAnioInicial, psCodTramo, psTipoComisionista);
  LOOP
    FETCH cursorHistoEjecuTramo BULK COLLECT INTO tablaHistoEjecuTramo LIMIT W_FILAS;
    IF tablaHistoEjecuTramo.COUNT > 0 THEN

      FOR x IN tablaHistoEjecuTramo.FIRST .. tablaHistoEjecuTramo.LAST LOOP
        registroHistoEjecuTramo := tablaHistoEjecuTramo(x);

        IF (registroHistoEjecuTramo.TOTALCAMPANAS > 0) THEN
          lnPromBiCampanas := round(registroHistoEjecuTramo.TOTALBICAMPANAS/registroHistoEjecuTramo.TOTALCAMPANAS);
          lnPromPedidos := round(registroHistoEjecuTramo.TOTALPEDIDOS/registroHistoEjecuTramo.TOTALCAMPANAS);
        ELSE
          lnPromBiCampanas := 0;
          lnPromPedidos := 0;
        END IF;

        lsCodNivel := COM_FN_DEVUE_NIVEL_EJECU(psCodPais, lsCodCalificacion, lnPromBiCampanas, lnPromPedidos,
                      registroHistoEjecuTramo.cod_regi, registroHistoEjecuTramo.cod_zona);

        IF (lsCodNivel = SIN_NIVEL) THEN
          --ULTIMA CAMPAÑA TRAMO = CAMPAÑA 1ERA EVALUACION, SERA (TUTORA)
          IF lsCampaniaFin = lsCampPrimeEvaluacion THEN
             lsCodNivel := TUTORA;
          ELSE
             --Recuperamos tramo anterior
             COM_PR_DEVUE_TRAMO_ANTER(psCodPais, psCodTramo, pnAnioInicial, lsCodTramoAnt, lnAnioInicialAnt);
             --Recuperamos el nivel de la ejecutiva del tramo anterior
             lsCodNivelAnt := COM_FN_DEVUE_NIVEL_ANTER(psCodPais, psCodMarca, psCodCanal, lnAnioInicialAnt,
                           lsCodTramoAnt, psTipoComisionista, registroHistoEjecuTramo.COD_EJEC);

             --EJECUTIVA NO ENCONTRADA EN EL TRAMO ANTERIOR, SERA (ASPIRANTE)
             IF (lsCodNivelAnt = NO_ENCONTRADO) THEN
               lsCodNivel := ASPIRANTE;
             ELSE
               IF (lsCodNivelAnt = TUTORA) THEN
                 --EJECUTIVA ES TUTORA EN EL TRAMO ANTERIOR, VERIFICAMOS SI CAMPAÑA ACTUAL <
                 --ULTIMA CAMPAÑA TUTORAS, SI ES ASI, SERA (TUTORA), SINO (SIN NIVEL)
                 IF (lsCampaniaFin <= lsUltimaCampTutoras) THEN
                   lsCodNivel := TUTORA;
                 ELSE
                   lsCodNivel := SIN_NIVEL;
                 END IF;
               ELSIF (lsCodNivelAnt = ASPIRANTE) THEN

                   --EN EL TRAMO ANTERIOR; LA EJECUTIVA FUE ASPIRANTE, RETROCEDEMOS DE 1 EN 1
                   --LOS TRAMOS Y OBTENER NIVEL HASTA NO ENCONTRAR A LA EJECUTIVA, CONTABILIZAMOS
                   --LA CANTIDAD DE REGISTROS CON NIVEL ASPIRANTE
                   lsTotalCampAspirante := 1;
                   LOOP
                      --Recuperamos tramo anterior
                      COM_PR_DEVUE_TRAMO_ANTER(psCodPais, lsCodTramoAnt, lnAnioInicialAnt, lsCodTramoAnt, lnAnioInicialAnt);

                      --Recuperamos el nivel de la ejecutiva del tramo anterior
                      lsCodNivelAnt := COM_FN_DEVUE_NIVEL_ANTER(psCodPais, psCodMarca, psCodCanal, lnAnioInicialAnt,
                           lsCodTramoAnt, psTipoComisionista, registroHistoEjecuTramo.cod_ejec);

                      IF (lsCodNivelAnt = ASPIRANTE) THEN
                        lsTotalCampAspirante := lsTotalCampAspirante + 1;
                      END IF;

                   		EXIT WHEN lsCodNivelAnt = NO_ENCONTRADO;
                   END LOOP;

                   --SI CANTIDAD REGISTRO ENCONTRADOS ASPIRANTES < NUMERO CAMPAÑAS ASPIRANTE, EL NIVEL
                   -- DE LA EJECUTIVA SERIA (ASPIRANTE), SI NO ES ASI SERIA (SIN NIVEL)
                   IF (lsTotalCampAspirante < lnNumCampAspirante) THEN
                     lsCodNivel := ASPIRANTE;
                   ELSE
                     lsCodNivel := SIN_NIVEL;
                   END IF;
               ELSE
                 lsCodNivel := SIN_NIVEL;
               END IF;
             END IF;
          END IF;
        END IF;

        UPDATE COM_HISTO_VARIA_EJETR_CABEC
           SET NUM_PROM_INGR_BICA = lnPromBiCampanas,
               NUM_PROM_PEDI = lnPromPedidos,
               COD_NIVE = lsCodNivel
         WHERE COD_PAIS = registroHistoEjecuTramo.cod_pais
           AND COD_CALI = registroHistoEjecuTramo.cod_cali
           AND COD_EJEC = registroHistoEjecuTramo.cod_ejec
           AND NUM_ANIO_INIC = registroHistoEjecuTramo.num_anio_inic
           AND COD_RANG = registroHistoEjecuTramo.cod_rang;

        --Grabamos la cantidad de ejecutivas por nivel y zona, de acuerdo a la zona en que estuvo la ejecutiva
        --en la ultima campaña del tramo
        BEGIN
          SELECT NUM_TOTA_EJEC
            INTO lnNumTotaEjec
            FROM COM_RESUM_EVALU_TRAMO
           WHERE COD_PAIS = registroHistoEjecuTramo.cod_pais
             AND NUM_ANIO_INIC = registroHistoEjecuTramo.num_anio_inic
             AND COD_RANG = registroHistoEjecuTramo.cod_rang
             AND COD_REGI = registroHistoEjecuTramo.cod_regi
             AND COD_ZONA = registroHistoEjecuTramo.cod_zona
             AND COD_NIVE =  lsCodNivel;

          UPDATE COM_RESUM_EVALU_TRAMO
             SET NUM_TOTA_EJEC = lnNumTotaEjec + 1
           WHERE COD_PAIS = registroHistoEjecuTramo.cod_pais
             AND NUM_ANIO_INIC = registroHistoEjecuTramo.num_anio_inic
             AND COD_RANG = registroHistoEjecuTramo.cod_rang
             AND COD_REGI = registroHistoEjecuTramo.cod_regi
             AND COD_ZONA = registroHistoEjecuTramo.cod_zona
             AND COD_NIVE =  lsCodNivel;

        EXCEPTION
       	  WHEN NO_DATA_FOUND THEN
            INSERT INTO COM_RESUM_EVALU_TRAMO
              (COD_PAIS, NUM_ANIO_INIC,
               COD_REGI, COD_ZONA,
               COD_NIVE, COD_RANG, NUM_TOTA_EJEC)
            VALUES
              (registroHistoEjecuTramo.cod_pais, registroHistoEjecuTramo.num_anio_inic,
               registroHistoEjecuTramo.cod_regi, registroHistoEjecuTramo.cod_zona,
               lsCodNivel, registroHistoEjecuTramo.cod_rang, 1);
        END;

      END LOOP;
    END IF;

    EXIT WHEN cursorHistoEjecuTramo%NOTFOUND;
  END LOOP;
  CLOSE cursorHistoEjecuTramo;

END COM_PR_CALCU_CALIF_EJECU_TRAMO;

/***************************************************************************
Descripcion       : Devuelve la campaña inicial y final de un determinado tramo
                    para un respectivo pais
Fecha Creacion    : 17/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_DEVUE_CAMPA_TRAMO
  (psCodPais             VARCHAR2,
   psCodTramo            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCampaniaInicio      OUT VARCHAR2,
   psCampaniaFin         OUT VARCHAR2)
IS

  lsCampaniaInicio          COM_TRAMO_EJECU.CAM_INIC%TYPE;
  lsCampaniaFin             COM_TRAMO_EJECU.CAM_INIC%TYPE;
BEGIN

  SELECT CAM_INIC, CAM_FINA
    INTO lsCampaniaInicio, lsCampaniaFin
    FROM COM_TRAMO_EJECU
   WHERE COD_PAIS = psCodPais
     AND COD_RANG = psCodTramo;

  IF (lsCampaniaInicio < lsCampaniaFin) THEN
    psCampaniaInicio := TO_CHAR(pnAnioInicial) || lsCampaniaInicio;
    psCampaniaFin := TO_CHAR(pnAnioInicial) || lsCampaniaFin;
  ELSE
    psCampaniaInicio := TO_CHAR(pnAnioInicial) || lsCampaniaInicio;
    psCampaniaFin := TO_CHAR(pnAnioInicial + 1) || lsCampaniaFin;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_DEVUE_CAMPA_TRAMO: '||ls_sqlerrm);
END COM_PR_DEVUE_CAMPA_TRAMO;

/***************************************************************************
Descripcion       : Devuelve el nivel que ha alcanzado la ejecutiva de acuerdo
                    al total de pedidos y total de ingresos bicampañas realizadas
                    y verificando si existe configurado nivel por region y zona,
                    region, o region y zona nulos
Fecha Creacion    : 21/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_NIVEL_EJECU
  (psCodPais             VARCHAR2,
   psCodCalificacion     VARCHAR2,
   pnPromBiCampanas      NUMBER,
   pnPromPedidos         NUMBER,
   psCodRegion           VARCHAR2,
   psCodZona             VARCHAR2) RETURN VARCHAR2
IS
  lsCodNivel             COM_NIVEL.COD_NIVE%TYPE;
  lnTotalNiveles         NUMBER;
BEGIN

  --VERIFICAMOS SI EXISTE CONFIGURADO NIVEL POR REGION/ZONA
  SELECT COUNT(COD_NIVE)
    INTO lnTotalNiveles
    FROM COM_COMIS_CALIF_DETAL a
   WHERE COD_PAIS = psCodPais
     AND COD_CALI = psCodCalificacion
     AND COD_REGI = psCodRegion
     AND COD_ZONA = psCodZona;

  IF (lnTotalNiveles > 0) THEN
    SELECT COD_NIVE
      INTO lsCodNivel
      FROM COM_COMIS_CALIF_DETAL a
     WHERE COD_PAIS = psCodPais
       AND COD_CALI = psCodCalificacion
       AND pnPromPedidos >= NUM_PEDI_DESD
       AND pnPromPedidos <= NUM_PEDI_HAST
       AND pnPromBiCampanas >= NUM_INGR_DESD
       AND pnPromBiCampanas <= NUM_INGR_HAST
       AND COD_REGI = psCodRegion
       AND COD_ZONA = psCodZona;

  ELSE
    --VERIFICAMOS SI EXISTE CONFIGURADO NIVEL POR REGION/ZONA
    SELECT COUNT(COD_NIVE)
      INTO lnTotalNiveles
      FROM COM_COMIS_CALIF_DETAL a
     WHERE COD_PAIS = psCodPais
       AND COD_CALI = psCodCalificacion
       AND COD_REGI = psCodRegion
       AND COD_ZONA IS NULL;

    IF (lnTotalNiveles > 0) THEN
  SELECT COD_NIVE
    INTO lsCodNivel
    FROM COM_COMIS_CALIF_DETAL a
   WHERE COD_PAIS = psCodPais
     AND COD_CALI = psCodCalificacion
         AND pnPromPedidos >= NUM_PEDI_DESD
         AND pnPromPedidos <= NUM_PEDI_HAST
         AND pnPromBiCampanas >= NUM_INGR_DESD
         AND pnPromBiCampanas <= NUM_INGR_HAST
         AND COD_REGI = psCodRegion
         AND COD_ZONA IS NULL;

    ELSE
        SELECT COD_NIVE
          INTO lsCodNivel
          FROM COM_COMIS_CALIF_DETAL a
         WHERE COD_PAIS = psCodPais
           AND COD_CALI = psCodCalificacion
           AND pnPromPedidos >= NUM_PEDI_DESD
     AND pnPromPedidos <= NUM_PEDI_HAST
           AND pnPromBiCampanas >= NUM_INGR_DESD
           AND pnPromBiCampanas <= NUM_INGR_HAST
           AND COD_REGI IS NULL
           AND COD_ZONA IS NULL;

    END IF;
  END IF;

  RETURN lsCodNivel;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN SIN_NIVEL;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_NIVEL_EJECU: '||ls_sqlerrm);
END COM_FN_DEVUE_NIVEL_EJECU;

/***************************************************************************
Descripcion       : Devuelve el nivel que tuvo la ejecutiva en el tram anterior
Fecha Creacion    : 22/04/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_DEVUE_TRAMO_ANTER
  (psCodPais             VARCHAR2,
   psCodTramo            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramoAnt         OUT VARCHAR2,
   pnAnioInicialAnt      OUT NUMBER)
IS
BEGIN

  BEGIN
    SELECT COD_RANG
     INTO  psCodTramoAnt
     FROM (SELECT COD_RANG
            FROM COM_TRAMO_EJECU
           WHERE COD_PAIS = psCodPais
             AND COD_RANG < psCodTramo
           ORDER BY COD_RANG DESC)
     WHERE ROWNUM = 1;

    pnAnioInicialAnt := pnAnioInicial;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      SELECT MAX(COD_RANG)
       INTO  psCodTramoAnt
       FROM  COM_TRAMO_EJECU
       WHERE COD_PAIS = psCodPais;

      pnAnioInicialAnt := pnAnioInicial - 1;
  END;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_DEVUE_TRAMO_ANTER: '||ls_sqlerrm);
END COM_PR_DEVUE_TRAMO_ANTER;

/***************************************************************************
Descripcion       : Devuelve el año y tramo anterior para un respectivo pais
Fecha Creacion    : 22/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_NIVEL_ANTER
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psTipoComisionista    VARCHAR2,
   psCodEjecutiva        VARCHAR2) RETURN VARCHAR2
IS
  lsCodNivel        VARCHAR2(2);
BEGIN

  BEGIN
    SELECT COD_NIVE
     INTO  lsCodNivel
     FROM  COM_HISTO_VARIA_EJETR_CABEC
     WHERE COD_PAIS = psCodPais
       AND COD_MARC = psCodMarca
       and COD_CANA = psCodCanal
       AND COD_EJEC = psCodEjecutiva
       AND NUM_ANIO_INIC = pnAnioInicial
       AND COD_RANG = psCodTramo
       AND COD_TIPO_COMI = psTipoComisionista;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      lsCodNivel := NO_ENCONTRADO;
  END;

  RETURN lsCodNivel;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_NIVEL_ANTER: '||ls_sqlerrm);
END COM_FN_DEVUE_NIVEL_ANTER;

/***************************************************************************
Descripcion       : Recupera Numero de pedidos y el Codigo de sub Gerencia
                    si no encuentra retorna -1
Parametros          Codigo Region
                    Codigo Zona
                    Codigo Seccion

Fecha Creacion    : 18/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_NUMER_PEDID(psCodRegion VARCHAR2,
                               psCodZona VARCHAR2,
                               psCodSeccion VARCHAR2,
                               psNumeroPedidos OUT NUMBER,
                               psCodigoSubGerencia OUT VARCHAR2) RETURN NUMBER IS

BEGIN
   SELECT A.NUM_CANT_PEDI, A.COD_SUBG_VENT
    INTO psNumeroPedidos, psCodigoSubGerencia
    FROM COM_TMP_NUMER_PEDID A
   WHERE A.COD_REGIO = psCodRegion
     AND A.COD_ZONA = psCodZona
     AND A.COD_SECC = psCodSeccion;

    RETURN 1;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN -1;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_BI_CAMPA: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_NUMER_PEDID;


/***************************************************************************
Descripcion       : Recupera el Inicio de la Campaña de Ejecutivas
                    si no encuentra gerera error
Parametros          Codigo Pais
                    Codigo TipoComisionista
                    Codigo Seccion

Fecha Creacion    : 18/04/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_INICI_CAMPA(psCodPais VARCHAR2,
                               psCodTipComisionista VARCHAR2
                               ) RETURN VARCHAR2 IS

lsCampaniaInicioEvaluacion COM_TIPOS_COMIS.CAM_INIC_EVAL%TYPE;
BEGIN
   SELECT A.CAM_INIC_EVAL
    INTO lsCampaniaInicioEvaluacion
    FROM COM_TIPOS_COMIS A
   WHERE a.cod_pais=psCodPais
     AND a.cod_tipo_comi = psCodTipComisionista;

    RETURN lsCampaniaInicioEvaluacion;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_INICI_CAMPA: ' ||
                            ls_sqlerrm);
    RETURN '';
END COM_FN_OBTIE_INICI_CAMPA;


/***************************************************************************
Descripcion       : Devuelve 1 si ha sido evaluado la ejecutiva en la campaña
                    seleccionada, 0 si no lo encontro
Fecha Creacion    : 30/04/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_EVALU_CAMPA
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   psCampania            VARCHAR2,
   psTipoComisionista    VARCHAR2,
   psCodEjecutiva        VARCHAR2) RETURN NUMBER
IS
  lnEncontrado        NUMBER;
BEGIN

   SELECT COUNT(COD_EJEC)
     INTO lnEncontrado
     FROM COM_HISTO_VARIA_EJCAM
    WHERE COD_PAIS = psCodPais
      AND COD_MARC = psCodMarca
      AND COD_CANA = psCodCanal
      AND COD_CAMP = psCampania
      AND COD_TIPO_COMI = psTipoComisionista
      AND COD_EJEC = psCodEjecutiva;

  RETURN lnEncontrado;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_EVALU_CAMPA: '||ls_sqlerrm);
END COM_FN_DEVUE_EVALU_CAMPA;


/***************************************************************************
Descripcion       : Obtiene los ingresos, ingresos bicampañan y pedidos de las
                    ejecutivas de las campañas pertenecientes al tramo seleccionado.
                    Ademas trae informacion de la campaña anterior a la campaña
                    inicio del tramo
Fecha Creacion    : 02/06/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_OBTIE_SEGUI_CALIF_CAMPA
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psTipoComisionista    VARCHAR2)
IS
  CURSOR cursorEjecutivas IS
    SELECT TMP.COD_EJEC, TMP.COD_CAMP
      FROM COM_TMP_SEGUI_CALIF_CAMPA TMP;

  CURSOR cursorEjecutivasCampana(campana VARCHAR2) IS
    SELECT COD_EJEC, NUM_INGR_PERI, NUM_INGR_BICA, NUM_PEDI
      FROM COM_HISTO_VARIA_EJCAM
     WHERE COD_PAIS = psCodPais
       AND COD_MARC = psCodMarca
       AND COD_CANA = psCodCanal
       AND COD_TIPO_COMI = psTipoComisionista
       AND COD_CAMP = campana
       AND COD_EJEC IS NOT NULL;

  CURSOR cursorCampanas(campaniaInicio VARCHAR2, campaniaFin VARCHAR2) IS
    SELECT COD_PERI FROM SEG_PERIO_CORPO
     WHERE COD_PERI >= campaniaInicio
       AND COD_PERI <= campaniaFin
     ORDER BY COD_PERI;

  lsCampaniaInicio          VARCHAR2(6);
  lsCampaniaFin             VARCHAR2(6);

  lnIdPais                  SEG_PAIS.OID_PAIS%TYPE;

  lsCodRegion               COM_TMP_SEGUI_CALIF_CAMPA.COD_REGI%TYPE;
  lsDesRegion               COM_TMP_SEGUI_CALIF_CAMPA.DES_REGI%TYPE;
  lsCodZona                 COM_TMP_SEGUI_CALIF_CAMPA.COD_ZONA%TYPE;
  lsCodSeccion              COM_TMP_SEGUI_CALIF_CAMPA.COD_SECC%TYPE;
  lsCodEjec                 COM_TMP_SEGUI_CALIF_CAMPA.COD_EJEC%TYPE;
  lsNomEjec                 COM_TMP_SEGUI_CALIF_CAMPA.NOM_EJEC%TYPE;
  lsRazonSocial             MAE_CLIEN_LIDER.VAL_RAZO_SOCI%TYPE;
  lsCodNivel                COM_TMP_SEGUI_CALIF_CAMPA.COD_NIVE%TYPE;
  lsDesNivel                COM_TMP_SEGUI_CALIF_CAMPA.DES_NIVE%TYPE;
  lsCampana                 COM_TMP_SEGUI_CALIF_CAMPA.COD_CAMP%TYPE;

  lnNumIngrCampana          COM_TMP_SEGUI_CALIF_CAMPA.NUM_INGR_CAMP_ANTE%TYPE;
  lnNumBicaCampana          COM_TMP_SEGUI_CALIF_CAMPA.NUM_BICA_CAMP_ANTE%TYPE;
  lnNumPediCampana          COM_TMP_SEGUI_CALIF_CAMPA.NUM_PEDI_CAMP_ANTE%TYPE;

  lsCodTramoAnt             COM_TRAMO_EJECU.COD_RANG%TYPE;
  lnAnioInicialAnt          NUMBER;
  lnContCampanas            NUMBER;
BEGIN

  --RECUPERAMOS EL OID DEL PAIS
  lnIdPais   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);

  --RECUPERAMOS LA CAMPAÑA INICIAL Y FINAL DEL TRAMO SELECCIONADO
  COM_PR_DEVUE_CAMPA_TRAMO(psCodPais, psCodTramo, pnAnioInicial, lsCampaniaInicio, lsCampaniaFin);

  --BORRAMOS LA TABLA TEMPORAL
  DELETE FROM COM_TMP_SEGUI_CALIF_CAMPA;

  --RECUPERAMOS LA CANTIDAD DE CAMPAÑAS ASPIRANTES, PRIMERA CAMPAÑA DE EVALUACION,
  --ULTIMA CAMPAÑA DE EVALUACION DE LAS TUTORAS, RELACIONADAS AL TIPO DE COMISIONISTA
  INSERT INTO COM_TMP_SEGUI_CALIF_CAMPA(COD_EJEC, COD_CAMP)
  SELECT  COD_EJEC, MAX(COD_CAMP)
    FROM COM_HISTO_VARIA_EJCAM CAM
  WHERE CAM.COD_PAIS = psCodPais
    AND COD_MARC = psCodMarca
    AND COD_CANA = psCodCanal
    AND CAM.COD_TIPO_COMI = psTipoComisionista
    AND CAM.COD_CAMP >= lsCampaniaInicio
    AND CAM.COD_CAMP <= lsCampaniaFin
    AND CAM.COD_EJEC IS NOT NULL
  GROUP BY COD_EJEC;

  --RECORREMOS CADA UNA DE LAS EJECUTIVAS EVALUADAS
  OPEN cursorEjecutivas;
  LOOP
    FETCH cursorEjecutivas INTO lsCodEjec, lsCampana;
    EXIT WHEN cursorEjecutivas%NOTFOUND;

    SELECT CAM.COD_REGI, (SELECT DES_REGI FROM ZON_REGIO WHERE COD_REGI = CAM.COD_REGI),
           CAM.COD_ZONA, CAM.COD_SECC, (SELECT VAL_NOM1 || ' ' || VAL_APE1 || ' ' || SUBSTR(VAL_APE2,1,1)
            FROM MAE_CLIEN WHERE COD_CLIE = CAM.COD_EJEC AND PAIS_OID_PAIS = lnIdPais),
           (SELECT VAL_RAZO_SOCI FROM MAE_CLIEN_LIDER WHERE COD_CLID = CAM.COD_EJEC AND COD_PAIS = psCodPais)
     INTO  lsCodRegion, lsDesRegion, lsCodZona, lsCodSeccion, lsNomEjec, lsRazonSocial
     FROM  COM_HISTO_VARIA_EJCAM CAM
		 WHERE CAM.COD_PAIS = psCodPais
       AND CAM.COD_MARC = psCodMarca
       AND CAM.COD_CANA = psCodCanal
		   AND CAM.COD_TIPO_COMI = psTipoComisionista
		   AND CAM.COD_CAMP = lsCampana
       AND CAM.COD_EJEC = lsCodEjec;

    --OBTENEMOS EL NIVEL DE LA EJECUTIVA DEL TRAMO ANTERIOR QUE FUE EVALUADA,
    --SI NO SE ENCUENTRA SE PONE COMO ASPIRANTE
     --Recuperamos tramo anterior
     COM_PR_DEVUE_TRAMO_ANTER(psCodPais, psCodTramo, pnAnioInicial, lsCodTramoAnt, lnAnioInicialAnt);

     --Recuperamos el nivel de la ejecutiva del tramo anterior
     lsCodNivel := COM_FN_DEVUE_NIVEL_ANTER(psCodPais, psCodMarca, psCodCanal, lnAnioInicialAnt,
                   lsCodTramoAnt, psTipoComisionista, lsCodEjec);

     --Ejecutiva no encontrada en el tramo anterior, SERA (ASPIRANTE)
     IF (lsCodNivel = NO_ENCONTRADO) THEN
       lsCodNivel := ASPIRANTE;
     END IF;

    SELECT DES_ABRE
      INTO lsDesNivel
      FROM COM_NIVEL
     WHERE COD_NIVE = lsCodNivel;

    UPDATE COM_TMP_SEGUI_CALIF_CAMPA
       SET COD_REGI = lsCodRegion,
           DES_REGI = lsDesRegion,
           COD_ZONA = lsCodZona,
           COD_SECC = lsCodSeccion,
           COD_NIVE =  lsCodNivel,
           DES_NIVE = lsDesNivel,
           NOM_EJEC = NVL(lsRazonSocial, '') || ' -- '  || lsNomEjec
     WHERE COD_EJEC = lsCodEjec;

    EXIT WHEN cursorEjecutivas%NOTFOUND;
  END LOOP;
  CLOSE cursorEjecutivas;

  --Recuperar la campaña anterior de la campaña inicial del tramo
  lsCampana := GEN_FN_CALCU_PERIO(lsCampaniaInicio, -1);

  --RECORREMOS LAS EJECUTIVAS DE LA CAMPAÑA ANTERIOR A LA CAMPAÑA INICIAL DEL TRAMO,
  --y RECUPERAMOS NUMERO DE INGRESOS, NUMERO DE INGRESOS BICAMPANA, NUMERO DE PEDIDOS
  OPEN cursorEjecutivasCampana(lsCampana);
  LOOP
    FETCH cursorEjecutivasCampana INTO lsCodEjec, lnNumIngrCampana, lnNumBicaCampana, lnNumPediCampana;
    EXIT WHEN cursorEjecutivasCampana%NOTFOUND;

    UPDATE COM_TMP_SEGUI_CALIF_CAMPA
       SET NUM_INGR_CAMP_ANTE = lnNumIngrCampana,
           NUM_BICA_CAMP_ANTE = lnNumBicaCampana,
           NUM_PEDI_CAMP_ANTE =  lnNumPediCampana
     WHERE COD_EJEC = lsCodEjec;

    EXIT WHEN cursorEjecutivasCampana%NOTFOUND;
  END LOOP;
  CLOSE cursorEjecutivasCampana;

  --RECORREMOS LAS EJECUTIVAS DE LAS CAMPAÑAS DEL TRAMO,
  --y RECUPERAMOS NUMERO DE INGRESOS, NUMERO DE INGRESOS BICAMPANA, NUMERO DE PEDIDOS
  lnContCampanas := 0;
  OPEN cursorCampanas(lsCampaniaInicio, lsCampaniaFin);
  LOOP
    FETCH cursorCampanas INTO lsCampana;
    EXIT WHEN cursorCampanas%NOTFOUND;

      lnContCampanas := lnContCampanas + 1;

      OPEN cursorEjecutivasCampana(lsCampana);
      LOOP
        FETCH cursorEjecutivasCampana INTO lsCodEjec, lnNumIngrCampana, lnNumBicaCampana, lnNumPediCampana;
        EXIT WHEN cursorEjecutivasCampana%NOTFOUND;

        IF (lnContCampanas = 1) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_PRIM = lnNumIngrCampana,
                 NUM_BICA_CAMP_PRIM = lnNumBicaCampana,
                 NUM_PEDI_CAMP_PRIM =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContCampanas = 2) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_SEGU = lnNumIngrCampana,
                 NUM_BICA_CAMP_SEGU = lnNumBicaCampana,
                 NUM_PEDI_CAMP_SEGU =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContCampanas = 3) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_TERC = lnNumIngrCampana,
                 NUM_BICA_CAMP_TERC = lnNumBicaCampana,
                 NUM_PEDI_CAMP_TERC =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContCampanas = 4) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_CUAR = lnNumIngrCampana,
                 NUM_BICA_CAMP_CUAR = lnNumBicaCampana,
                 NUM_PEDI_CAMP_CUAR =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContCampanas = 5) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_QUIN = lnNumIngrCampana,
                 NUM_BICA_CAMP_QUIN = lnNumBicaCampana,
                 NUM_PEDI_CAMP_QUIN =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContCampanas = 6) THEN
          UPDATE COM_TMP_SEGUI_CALIF_CAMPA
             SET NUM_INGR_CAMP_SEXT = lnNumIngrCampana,
                 NUM_BICA_CAMP_SEXT = lnNumBicaCampana,
                 NUM_PEDI_CAMP_SEXT =  lnNumPediCampana
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        EXIT WHEN cursorEjecutivasCampana%NOTFOUND;
      END LOOP;
      CLOSE cursorEjecutivasCampana;

    EXIT WHEN cursorCampanas%NOTFOUND;
  END LOOP;
  CLOSE cursorCampanas;

END COM_PR_OBTIE_SEGUI_CALIF_CAMPA;


/***************************************************************************
Descripcion       : Obtiene las ejecutivas que fueron evaluadas para una determinada
                    region, zon para un año y tramo seleccionado
Fecha Creacion    : 05/06/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_OBTIE_EVALU_SECCI_TRAMO
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psTipoComisionista    VARCHAR2)
IS
  CURSOR cursorEjecutivasTramo(codTramo VARCHAR2) IS
    SELECT COD_EJEC, NUM_PROM_INGR_BICA, NUM_PROM_PEDI, (SELECT DES_ABRE FROM COM_NIVEL WHERE COD_NIVE = CAM.COD_NIVE)
      FROM COM_HISTO_VARIA_EJETR_CABEC CAM
     WHERE CAM.COD_PAIS = psCodPais
       AND CAM.COD_MARC = psCodMarca
       AND CAM.COD_CANA = psCodCanal
       AND CAM.COD_TIPO_COMI = psTipoComisionista
       AND CAM.NUM_ANIO_INIC = pnAnioInicial
       AND CAM.COD_RANG = codTramo;

  CURSOR cursorTramos(codTramo VARCHAR2) IS
    SELECT COD_RANG FROM COM_TRAMO_EJECU
     WHERE COD_RANG <= codTramo
     ORDER BY COD_RANG;

  CURSOR cursorResumenes IS
    SELECT COD_REGI, COD_ZONA, COD_NIVE
      FROM COM_TMP_EVALU_RESUM_TRAMO
     ORDER BY COD_REGI, COD_ZONA;

  lnIdPais                  SEG_PAIS.OID_PAIS%TYPE;
  lsTramo                   COM_TRAMO_EJECU.COD_RANG%TYPE;

  lsCodEjec                 COM_TMP_EVALU_SECCI_TRAMO.COD_EJEC%TYPE;
  lnNumPromBica             COM_TMP_EVALU_SECCI_TRAMO.NUM_PROM_BICA_TRA1%TYPE;
  lnNumPromPedi             COM_TMP_EVALU_SECCI_TRAMO.NUM_PROM_PEDI_TRA1%TYPE;
  lsDesNivel                COM_TMP_EVALU_SECCI_TRAMO.DES_NIVE_TRA1%TYPE;
  lnContTramos              NUMBER;

  lsCodRegion               COM_TMP_EVALU_RESUM_TRAMO.COD_REGI%TYPE;
  lsCodZona                 COM_TMP_EVALU_RESUM_TRAMO.COD_ZONA%TYPE;
  lsCodNivel                COM_TMP_EVALU_RESUM_TRAMO.COD_NIVE%TYPE;
  ln_NumTotaEjec            COM_RESUM_EVALU_TRAMO.NUM_TOTA_EJEC%TYPE;
BEGIN

  --RECUPERAMOS EL OID DEL PAIS
  lnIdPais   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);

  --BORRAMOS LAS TABLAS TEMPORALES
  DELETE FROM COM_TMP_EVALU_SECCI_TRAMO;
  DELETE FROM COM_TMP_EVALU_RESUM_TRAMO;

  --RECUPERAMOS LA CANTIDAD DE CAMPAÑAS ASPIRANTES, PRIMERA CAMPAÑA DE EVALUACION,
  --ULTIMA CAMPAÑA DE EVALUACION DE LAS TUTORAS, RELACIONADAS AL TIPO DE COMISIONISTA
  INSERT INTO COM_TMP_EVALU_SECCI_TRAMO
    (COD_REGI , DES_REGI, COD_ZONA,
     COD_EJEC, NOM_EJEC, NUM_ANIO_INIC, COD_TRAM)
  SELECT CAM.COD_REGI, (SELECT DES_REGI FROM ZON_REGIO WHERE COD_REGI = CAM.COD_REGI), CAM.COD_ZONA, CAM.COD_EJEC,
         NVL((SELECT VAL_RAZO_SOCI FROM MAE_CLIEN_LIDER WHERE COD_CLID = CAM.COD_EJEC AND COD_PAIS = psCodPais), '') || ' -- ' ||
         (SELECT VAL_NOM1 || ' ' || VAL_APE1 || ' ' || SUBSTR(VAL_APE2,1,1)
          FROM MAE_CLIEN WHERE COD_CLIE = CAM.COD_EJEC AND PAIS_OID_PAIS = lnIdPais), pnAnioInicial, psCodTramo
  FROM  COM_HISTO_VARIA_EJETR_CABEC CAM
  WHERE CAM.COD_PAIS = psCodPais
    AND CAM.COD_MARC = psCodMarca
    AND CAM.COD_CANA = psCodCanal
    AND CAM.COD_TIPO_COMI = psTipoComisionista
    AND CAM.NUM_ANIO_INIC = pnAnioInicial
    AND CAM.COD_RANG = psCodTramo;


  --RECORREMOS LAS EJECUTIVAS DEL TRAMO EVALUADO,
  --y RECUPERAMOS PROMEDIO DE INGRESOS BICAMPANA, PROMEDIO DE PEDIDOS, NIVEL
  lnContTramos := 0;
  OPEN cursorTramos(psCodTramo);
  LOOP
    FETCH cursorTramos INTO lsTramo;
    EXIT WHEN cursorTramos%NOTFOUND;

      lnContTramos := lnContTramos + 1;

      OPEN cursorEjecutivasTramo(lsTramo);
      LOOP
        FETCH cursorEjecutivasTramo INTO lsCodEjec, lnNumPromBica, lnNumPromPedi, lsDesNivel;
        EXIT WHEN cursorEjecutivasTramo%NOTFOUND;

        IF (lnContTramos = 1) THEN
          UPDATE COM_TMP_EVALU_SECCI_TRAMO
             SET NUM_PROM_BICA_TRA1 = lnNumPromBica,
                 NUM_PROM_PEDI_TRA1 = lnNumPromPedi,
                 DES_NIVE_TRA1 =  lsDesNivel
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContTramos = 2) THEN
          UPDATE COM_TMP_EVALU_SECCI_TRAMO
             SET NUM_PROM_BICA_TRA2 = lnNumPromBica,
                 NUM_PROM_PEDI_TRA2 = lnNumPromPedi,
                 DES_NIVE_TRA2 =  lsDesNivel
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        IF (lnContTramos = 3) THEN
          UPDATE COM_TMP_EVALU_SECCI_TRAMO
             SET NUM_PROM_BICA_TRA3 = lnNumPromBica,
                 NUM_PROM_PEDI_TRA3 = lnNumPromPedi,
                 DES_NIVE_TRA3 =  lsDesNivel
           WHERE COD_EJEC = lsCodEjec;
        END IF;

        EXIT WHEN cursorEjecutivasTramo%NOTFOUND;
      END LOOP;
      CLOSE cursorEjecutivasTramo;

    EXIT WHEN cursorTramos%NOTFOUND;
  END LOOP;
  CLOSE cursorTramos;

  --CALCULAMOS LOS RESUMENES POR REGION Y ZONA, PARA LOS NIVELES DE EJECUTIVAS:
  --ASPIRANTE, EJECUTIVA JUNIOR, EJECUTIVA SENIOR, EJECUTIVA MASTES
  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_ZONA, ASPIRANTE, 'Total Aspirantes', 1
    FROM COM_TMP_EVALU_SECCI_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_ZONA, EJECUTIVA_JUNIOR, 'Total Ejecutivas Junior', 2
    FROM COM_TMP_EVALU_SECCI_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_ZONA, EJECUTIVA_SENIOR, 'Total Ejecutivas Senior', 3
    FROM COM_TMP_EVALU_SECCI_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_ZONA, EJECUTIVA_MASTER, 'Total Ejecutivas Master', 4
    FROM COM_TMP_EVALU_SECCI_TRAMO;


  --RECORREMOS LOS RESUMENES Y LO ACTUALIZAMOS CONTADO PARA CADA CODIGO DE NIVEL
  --LA CANTIDAD DE EJECUTIVAS QUE ALCANZARON DICHO NIVEL, PARA UNA DETERMINADA ZONA Y REGION
  OPEN cursorResumenes;
  LOOP
    FETCH cursorResumenes INTO lsCodRegion, lsCodZona, lsCodNivel;
    EXIT WHEN cursorResumenes%NOTFOUND;

    IF (lnContTramos >=1) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_ZONA(psCodPais, pnAnioInicial, '01', lsCodRegion, lsCodZona, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA1 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_ZONA = lsCodZona
         AND COD_NIVE = lsCodNivel;
    END IF;

    IF (lnContTramos >=2) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_ZONA(psCodPais, pnAnioInicial, '02', lsCodRegion, lsCodZona, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA2 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_ZONA = lsCodZona
         AND COD_NIVE = lsCodNivel;
    END IF;

    IF (lnContTramos >=3) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_ZONA(psCodPais, pnAnioInicial, '03', lsCodRegion, lsCodZona, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA3 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_ZONA = lsCodZona
         AND COD_NIVE = lsCodNivel;
    END IF;

  END LOOP;
  CLOSE cursorResumenes;

END COM_PR_OBTIE_EVALU_SECCI_TRAMO;


/***************************************************************************
Descripcion       : Devuelve la cantidad de ejecutivas que obtuvieron un determinado
                    nivel para un año y tramo seleccionado, region y zona
Fecha Creacion    : 09/06/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_TOTAL_EJECU_ZONA
  (psCodPais             VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psCodRegion           VARCHAR2,
   psCodZona             VARCHAR2,
   psCodNivel            VARCHAR2) RETURN NUMBER
IS
  ln_NumTotaEjec        NUMBER;
BEGIN

  BEGIN
    SELECT NUM_TOTA_EJEC
      INTO ln_NumTotaEjec
      FROM COM_RESUM_EVALU_TRAMO
     WHERE COD_PAIS = psCodPais
       AND NUM_ANIO_INIC = pnAnioInicial
       AND COD_RANG = psCodTramo
       AND COD_REGI = psCodRegion
       AND COD_ZONA = psCodZona
       AND COD_NIVE = psCodNivel;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      ln_NumTotaEjec := 0;
  END;

  RETURN ln_NumTotaEjec;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_TOTAL_EJECU_ZONA: '||ls_sqlerrm);
END COM_FN_DEVUE_TOTAL_EJECU_ZONA;


/***************************************************************************
Descripcion       : Obtiene los totales de las ejecutivas de los niveles:
                    Aspirante, Ejecutiva Junior, Ejecutiva Master, Ejecutiva
                    Senior, por region y zona
Fecha Creacion    : 09/06/2008
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE COM_PR_OBTIE_EVALU_ZONA_TRAMO
  (psCodPais             VARCHAR2,
   psCodMarca            VARCHAR2,
   psCodCanal            VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psTipoComisionista    VARCHAR2)
IS
  CURSOR cursorEjecutivasTramo(codTramo VARCHAR2) IS
    SELECT COD_REGI, COD_ZONA,
           SUM(CASE WHEN COD_NIVE = ASPIRANTE THEN 1 ELSE 0 END),
           SUM(CASE WHEN COD_NIVE = EJECUTIVA_JUNIOR THEN 1 ELSE 0 END),
           SUM(CASE WHEN COD_NIVE = EJECUTIVA_SENIOR THEN 1 ELSE 0 END),
           SUM(CASE WHEN COD_NIVE = EJECUTIVA_MASTER THEN 1 ELSE 0 END)
      FROM COM_HISTO_VARIA_EJETR_CABEC CAM
     WHERE CAM.COD_PAIS = psCodPais
       AND CAM.COD_MARC = psCodMarca
       AND CAM.COD_CANA = psCodCanal
       AND CAM.COD_TIPO_COMI = psTipoComisionista
       AND CAM.NUM_ANIO_INIC = pnAnioInicial
       AND CAM.COD_RANG = codTramo
       AND COD_EJEC IN (SELECT DISTINCT COD_EJEC FROM COM_TMP_EVALU_SECCI_TRAMO)
     GROUP BY COD_REGI, COD_ZONA;

  CURSOR cursorTramos(codTramo VARCHAR2) IS
    SELECT COD_RANG FROM COM_TRAMO_EJECU
     WHERE COD_RANG <= codTramo
     ORDER BY COD_RANG;

  CURSOR cursorResumenes IS
    SELECT COD_REGI, COD_ZONA, COD_NIVE
      FROM COM_TMP_EVALU_RESUM_TRAMO
     ORDER BY COD_REGI;

  lsTramo                   COM_TRAMO_EJECU.COD_RANG%TYPE;

  lnTotalAspirantes         COM_TMP_EVALU_ZONA_TRAMO.NUM_TOTA_ASPI_TRA1%TYPE;
  lnTotalEjecJunior         COM_TMP_EVALU_ZONA_TRAMO.NUM_TOTA_JUNI_TRA1%TYPE;
  lnTotalEjecSenior         COM_TMP_EVALU_ZONA_TRAMO.NUM_TOTA_SENI_TRA1%TYPE;
  lnTotalEjecMaster         COM_TMP_EVALU_ZONA_TRAMO.NUM_TOTA_MAST_TRA1%TYPE;
  lnContTramos              NUMBER;

  lsCodRegion               COM_TMP_EVALU_RESUM_TRAMO.COD_REGI%TYPE;
  lsCodZona                 COM_TMP_EVALU_RESUM_TRAMO.COD_ZONA%TYPE;
  lsCodNivel                COM_TMP_EVALU_RESUM_TRAMO.COD_NIVE%TYPE;
  ln_NumTotaEjec            COM_RESUM_EVALU_TRAMO.NUM_TOTA_EJEC%TYPE;
BEGIN

  --BORRAMOS LAS TABLAS TEMPORALES
  DELETE FROM COM_TMP_EVALU_SECCI_TRAMO;
  DELETE FROM COM_TMP_EVALU_ZONA_TRAMO;
  DELETE FROM COM_TMP_EVALU_RESUM_TRAMO;

  --RECUPERAMOS LA CANTIDAD DE CAMPAÑAS ASPIRANTES, PRIMERA CAMPAÑA DE EVALUACION,
  --ULTIMA CAMPAÑA DE EVALUACION DE LAS TUTORAS, RELACIONADAS AL TIPO DE COMISIONISTA
  INSERT INTO COM_TMP_EVALU_SECCI_TRAMO
    (COD_REGI , DES_REGI, COD_ZONA,
     COD_EJEC, NUM_ANIO_INIC, COD_TRAM)
  SELECT CAM.COD_REGI, (SELECT DES_REGI FROM ZON_REGIO WHERE COD_REGI = CAM.COD_REGI),
         CAM.COD_ZONA, CAM.COD_EJEC, pnAnioInicial, psCodTramo
  FROM  COM_HISTO_VARIA_EJETR_CABEC CAM
  WHERE CAM.COD_PAIS = psCodPais
    AND CAM.COD_MARC = psCodMarca
    AND CAM.COD_CANA = psCodCanal
    AND CAM.COD_TIPO_COMI = psTipoComisionista
    AND CAM.NUM_ANIO_INIC = pnAnioInicial
    AND CAM.COD_RANG = psCodTramo;

  INSERT INTO COM_TMP_EVALU_ZONA_TRAMO
    (COD_REGI ,DES_REGI, COD_ZONA,
     NUM_ANIO_INIC, COD_TRAM)
  SELECT DISTINCT COD_REGI, DES_REGI, COD_ZONA,
         NUM_ANIO_INIC, COD_TRAM
  FROM  COM_TMP_EVALU_SECCI_TRAMO;

  --RECORREMOS LAS EJECUTIVAS DEL TRAMO EVALUADO,
  --y RECUPERAMOS PROMEDIO DE INGRESOS BICAMPANA, PROMEDIO DE PEDIDOS, NIVEL
  lnContTramos := 0;
  OPEN cursorTramos(psCodTramo);
  LOOP
    FETCH cursorTramos INTO lsTramo;
    EXIT WHEN cursorTramos%NOTFOUND;

      lnContTramos := lnContTramos + 1;

      OPEN cursorEjecutivasTramo(lsTramo);
      LOOP
        FETCH cursorEjecutivasTramo INTO lsCodRegion, lsCodZona, lnTotalAspirantes, lnTotalEjecJunior, lnTotalEjecSenior, lnTotalEjecMaster;
        EXIT WHEN cursorEjecutivasTramo%NOTFOUND;

        IF (lnContTramos = 1) THEN
          UPDATE COM_TMP_EVALU_ZONA_TRAMO
             SET NUM_TOTA_ASPI_TRA1 = lnTotalAspirantes,
                 NUM_TOTA_JUNI_TRA1 = lnTotalEjecJunior,
                 NUM_TOTA_SENI_TRA1 =  lnTotalEjecSenior,
                 NUM_TOTA_MAST_TRA1 =  lnTotalEjecMaster
           WHERE COD_ZONA = lsCodZona
             AND COD_REGI = lsCodRegion;
        END IF;

        IF (lnContTramos = 2) THEN
          UPDATE COM_TMP_EVALU_ZONA_TRAMO
             SET NUM_TOTA_ASPI_TRA2 = lnTotalAspirantes,
                 NUM_TOTA_JUNI_TRA2 = lnTotalEjecJunior,
                 NUM_TOTA_SENI_TRA2 =  lnTotalEjecSenior,
                 NUM_TOTA_MAST_TRA2 =  lnTotalEjecMaster
           WHERE COD_ZONA = lsCodZona
             AND COD_REGI = lsCodRegion;
        END IF;

        IF (lnContTramos = 3) THEN
          UPDATE COM_TMP_EVALU_ZONA_TRAMO
             SET NUM_TOTA_ASPI_TRA3 = lnTotalAspirantes,
                 NUM_TOTA_JUNI_TRA3 = lnTotalEjecJunior,
                 NUM_TOTA_SENI_TRA3 =  lnTotalEjecSenior,
                 NUM_TOTA_MAST_TRA3 =  lnTotalEjecMaster
           WHERE COD_ZONA = lsCodZona
             AND COD_REGI = lsCodRegion;
        END IF;

        EXIT WHEN cursorEjecutivasTramo%NOTFOUND;
      END LOOP;
      CLOSE cursorEjecutivasTramo;

    EXIT WHEN cursorTramos%NOTFOUND;
  END LOOP;
  CLOSE cursorTramos;

  --CALCULAMOS LOS RESUMENES POR REGION Y ZONA, PARA LOS NIVELES DE EJECUTIVAS:
  --ASPIRANTE, EJECUTIVA JUNIOR, EJECUTIVA SENIOR, EJECUTIVA MASTES
  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_REGI, ASPIRANTE, 'Total Aspirantes', 1
    FROM COM_TMP_EVALU_ZONA_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_REGI, EJECUTIVA_JUNIOR, 'Total Ejecutivas Junior', 2
    FROM COM_TMP_EVALU_ZONA_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_REGI, EJECUTIVA_SENIOR, 'Total Ejecutivas Senior', 3
    FROM COM_TMP_EVALU_ZONA_TRAMO;

  INSERT INTO COM_TMP_EVALU_RESUM_TRAMO
    (COD_REGI, COD_ZONA, COD_NIVE, DES_NIVE, IND_ORDE)
  SELECT DISTINCT COD_REGI, COD_REGI, EJECUTIVA_MASTER, 'Total Ejecutivas Master', 4
    FROM COM_TMP_EVALU_ZONA_TRAMO;


  --RECORREMOS LOS RESUMENES Y LO ACTUALIZAMOS CONTADO PARA CADA CODIGO DE NIVEL
  --LA CANTIDAD DE EJECUTIVAS QUE ALCANZARON DICHO NIVEL, PARA UNA DETERMINADA ZONA Y REGION
  OPEN cursorResumenes;
  LOOP
    FETCH cursorResumenes INTO lsCodRegion, lsCodZona, lsCodNivel;
    EXIT WHEN cursorResumenes%NOTFOUND;

    IF (lnContTramos >=1) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_REGIO(psCodPais, pnAnioInicial, '01', lsCodRegion, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA1 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_NIVE = lsCodNivel;
    END IF;

    IF (lnContTramos >=2) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_REGIO(psCodPais, pnAnioInicial, '02', lsCodRegion, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA2 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_NIVE = lsCodNivel;
    END IF;

    IF (lnContTramos >=3) THEN
      ln_NumTotaEjec := COM_FN_DEVUE_TOTAL_EJECU_REGIO(psCodPais, pnAnioInicial, '03', lsCodRegion, lsCodNivel);

      UPDATE COM_TMP_EVALU_RESUM_TRAMO
         SET NUM_TOTA_EJEC_TRA3 = ln_NumTotaEjec
       WHERE COD_REGI = lsCodRegion
         AND COD_NIVE = lsCodNivel;
    END IF;

  END LOOP;
  CLOSE cursorResumenes;

END COM_PR_OBTIE_EVALU_ZONA_TRAMO;

/***************************************************************************
Descripcion       : Devuelve la cantidad de ejecutivas que obtuvieron un determinado
                    nivel para un año y tramo seleccionado, y region
Fecha Creacion    : 09/06/2008
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_TOTAL_EJECU_REGIO
  (psCodPais             VARCHAR2,
   pnAnioInicial         NUMBER,
   psCodTramo            VARCHAR2,
   psCodRegion           VARCHAR2,
   psCodNivel            VARCHAR2) RETURN NUMBER
IS
  ln_NumTotaEjec        NUMBER;
BEGIN

  BEGIN
    SELECT NVL(SUM(NUM_TOTA_EJEC), 0)
      INTO ln_NumTotaEjec
      FROM COM_RESUM_EVALU_TRAMO
     WHERE COD_PAIS = psCodPais
       AND NUM_ANIO_INIC = pnAnioInicial
       AND COD_RANG = psCodTramo
       AND COD_REGI = psCodRegion
       AND COD_NIVE = psCodNivel;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      ln_NumTotaEjec := 0;
  END;

  RETURN ln_NumTotaEjec;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_TOTAL_EJECU_REGIO: '||ls_sqlerrm);
END COM_FN_DEVUE_TOTAL_EJECU_REGIO;


/***************************************************************************
Descripcion       : Devuelve el Numero de comodin de recuperacion segun la
                    campaña procesada y tipo de comisionista
Fecha Creacion    : 03/05/2010
Autor             : Dennys Oliva Iriarte
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_COMOD_RECUP(psCodPais             VARCHAR2,
                                        psCodMarca            VARCHAR2,
                                        psCodCanal            VARCHAR2,
                                        psTipoComisionista    VARCHAR2,
                                        psCampania            VARCHAR2) RETURN VARCHAR2
IS
  lsNumeroComodinRecuperacion   COM_COMIS_CALIF_CABEC.Num_Como_Recu%TYPE;
BEGIN

  SELECT nvl(Num_Como_Recu,0)
    INTO lsNumeroComodinRecuperacion
    FROM COM_COMIS_CALIF_CABEC
   WHERE COD_PAIS = PSCODPAIS
     AND COD_MARC = PSCODMARCA
     AND COD_CANA = PSCODCANAL
     AND COD_TIPO_COMI = PSTIPOCOMISIONISTA
     AND PSCAMPANIA >= CAM_VIGE_DESD
     AND (CAM_VIGE_HAST IS NULL OR (CAM_VIGE_HAST >= PSCAMPANIA));
  RETURN lsNumeroComodinRecuperacion;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_NUMER_COMOD_RECUP: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       : Devuelve el Numero de comodin de recuperacion para
                    aspirantes segun la
                    campaña procesada y tipo de comisionista
Fecha Creacion    : 03/01/2013
Autor             : Ivan Tocto
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_COMOD_ASPIR(psCodPais             VARCHAR2,
                                        psCodMarca            VARCHAR2,
                                        psCodCanal            VARCHAR2,
                                        psTipoComisionista    VARCHAR2,
                                        psCampania            VARCHAR2)
RETURN VARCHAR2
IS
  lsNumeroComodinRecuperacion   COM_COMIS_CALIF_CABEC.Num_Como_Recu%TYPE;
BEGIN

  SELECT nvl(Num_Como_Recu_aspi,0)
    INTO lsNumeroComodinRecuperacion
    FROM COM_COMIS_CALIF_CABEC
   WHERE COD_PAIS = PSCODPAIS
     AND COD_MARC = PSCODMARCA
     AND COD_CANA = PSCODCANAL
     AND COD_TIPO_COMI = PSTIPOCOMISIONISTA
     AND PSCAMPANIA >= CAM_VIGE_DESD
     AND (CAM_VIGE_HAST IS NULL OR (CAM_VIGE_HAST >= PSCAMPANIA));
  RETURN lsNumeroComodinRecuperacion;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_NUMER_COMOD_ASPIR: '||ls_sqlerrm);
END;

/***************************************************************************
Descripcion       :Recurpera numero de Campañas sin nivel en en el tramo
                   de lo contrario Error
Parametros        :
                   psCodCalificacion Código Calificacion

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_CALIF_COMOD_RECUP(psCodCalifcacion VARCHAR2) RETURN NUMBER
IS
  lv_comodin_recu NUMBER;
BEGIN
    SELECT A.Num_Como_Recu
      INTO lv_comodin_recu
      FROM COM_COMIS_CALIF_CABEC A
     WHERE A.COD_CALI = psCodCalifcacion;
    RETURN lv_comodin_recu;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_CALIF_COMOD_RECUP: ' ||
                            ls_sqlerrm);
    RETURN '';

END COM_FN_OBTIE_CALIF_COMOD_RECUP;

 /**************************************************************************
    Descripcion       : Calcula Comision de Recuperación

    Fecha Creacion    : 15/04/2008
    Autor             : Leonardo Lizana Chauca
 ***************************************************************************/
    PROCEDURE COM_PR_CALCU_COMIC_RECUP(  psCodPais             VARCHAR2,
                                         psCodMarca            VARCHAR2,
                                         psCodCanal            VARCHAR2,
                                         psCodTipoComisionista VARCHAR2,
                                         psCodComision         VARCHAR2,
                                         psAnioTramo           VARCHAR2,
                                         psNumTramo            VARCHAR2,
                                         psCodPeriodo          VARCHAR2,
                                         pscodUsuario          VARCHAR2
                                         ) IS


    CURSOR C_VNSC(psOidPeriodo NUMBER) is
          SELECT C.COD_REGI,
                 C.OID_REGI,
                 D.COD_ZONA,
                 D.OID_ZONA,
                 E.COD_SECC,
                 E.OID_SECC,
                 F.COD_SUBG_VENT,

                 F.OID_SUBG_VENT,
                 A.NUM_DIAS_RECU_ASPI,
                 A.VAL_PORC_RECU_ASPI,
                 A.VAL_PORC_COMI_ASPI,
                 A.NUM_DIAS_RECU_EJEC,
                 A.VAL_PORC_RECU_EJEC,
                 B.COD_COMI
            FROM COM_DATOS_COMIS A,
                 COM_DATOS_COMIS_CLIEN B,
                 ZON_REGIO C,
                 ZON_ZONA D,
                 ZON_SECCI E,
                 ZON_SUB_GEREN_VENTA F
           WHERE A.COD_PAIS = B.COD_PAIS
             AND A.COD_COMI = B.COD_COMI
             AND B.COD_REGI = C.COD_REGI
             AND (psOidPeriodo >= C.PERD_OID_PERI_INIC OR C.PERD_OID_PERI_INIC IS NULL)
             AND (psOidPeriodo <= C.PERD_OID_PERI_FINA OR C.PERD_OID_PERI_FINA IS NULL)
             AND C.OID_REGI = D.ZORG_OID_REGI
             AND (psOidPeriodo >= D.PERD_OID_PERI_INIC OR D.PERD_OID_PERI_INIC IS NULL)
             AND (psOidPeriodo <= D.PERD_OID_PERI_FINA OR D.PERD_OID_PERI_FINA IS NULL)
             AND D.OID_ZONA = E.ZZON_OID_ZONA
             AND (psOidPeriodo >= E.PERD_OID_PERI_INIC OR E.PERD_OID_PERI_INIC IS NULL)
             AND (psOidPeriodo <= E.PERD_OID_PERI_FINA OR E.PERD_OID_PERI_FINA IS NULL)
             AND C.ZSGV_OID_SUBG_VENT = F.OID_SUBG_VENT
             AND A.COD_COMI = psCodComision;


      CURSOR C_CLIENTES_POR_SECCION(psOidSeccion NUMBER, psOidPeriodo NUMBER)  IS
        SELECT A.OID_TERR_ADMI,
              B.OID_CLIE_UNID_ADMI,
              A.ZSCC_OID_SECC,
              B.CLIE_OID_CLIE,
              D.COD_ZONA,
              D.OID_ZONA,
              C.OID_SECC,
              C.COD_SECC,
              D.ZORG_OID_REGI,
              E.COD_TERR

        FROM ZON_TERRI_ADMIN       A,
             MAE_CLIEN_UNIDA_ADMIN B,
             ZON_SECCI             C,
             ZON_ZONA              D,
             ZON_TERRI             E

        WHERE A.ZSCC_OID_SECC = psOidSeccion
          AND A.OID_TERR_ADMI = B.ZTAD_OID_TERR_ADMI
          AND (psOidPeriodo >= A.PERD_OID_PERI_INIC OR A.PERD_OID_PERI_INIC IS NULL)
          AND (psOidPeriodo <= A.PERD_OID_PERI_FINA OR A.PERD_OID_PERI_FINA IS NULL)

          AND (psOidPeriodo >= B.PERD_OID_PERI_INI OR B.PERD_OID_PERI_INI IS NULL)
          AND (psOidPeriodo <= B.PERD_OID_PERI_FIN OR B.PERD_OID_PERI_FIN IS NULL)

          AND A.OID_TERR_ADMI = B.ZTAD_OID_TERR_ADMI
          AND A.TERR_OID_TERR = E.OID_TERR
          AND A.ZSCC_OID_SECC = C.OID_SECC
     --     and B.CLIE_OID_CLIE = 48794796
          AND C.ZZON_OID_ZONA = D.OID_ZONA;



     TYPE clientesPorSeccionRec IS RECORD(
                  oid_terr_admi      ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE,
                  oid_clie_unid_admi MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE,
                  zscc_oid_secc      ZON_TERRI_ADMIN.ZSCC_OID_SECC%TYPE,
                  clie_oid_clie      MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE%TYPE,
                  cod_zona           ZON_ZONA.COD_ZONA%TYPE,
                  oid_zona           ZON_ZONA.OID_ZONA%TYPE,
                  oid_secc           ZON_SECCI.OID_SECC%TYPE,
                  cod_secc           ZON_SECCI.COD_SECC%TYPE,
                  zorg_oid_regi      ZON_ZONA.ZORG_OID_REGI%TYPE,
                  cod_terr           ZON_TERRI.COD_TERR%TYPE
                 );

     TYPE clientesPorSeccionTab IS TABLE OF clientesPorSeccionRec;
     clientesPorSeccionRecord clientesPorSeccionTab;


      CURSOR C_CLIENTE_CUENTA_CORRIENTE (psOidRegion NUMBER,psOidCliente NUMBER,  psOidPeriodoActual NUMBER, psOidPeriodoAnterior NUMBER) is
           SELECT CC.OID_MOVI_CC,
                  CC.PERD_OID_PERI,
                  CC.FEC_DOCU,
                  CC.FEC_VENC,
                  CC.IMP_MOVI,
                  CC.IMP_PAGO,
                  CC.SUBP_OID_SUBP_ULTI,
                  CC.VAL_ULTI_NUME_HIST,
                  CC.FEC_ULTI_MOVI,
                  CC.ZTAD_OID_TERR_ADMI,
                  CC.SOCA_OID_SOLI_CABE,

                  CONS.VAL_TASA_IMPU,
                  CONS.VAL_NUME_SOLI,
                  MC.COD_CLIE,
                  CONS.PERD_OID_PERI,
                  CC.SUBP_OID_SUBP_CREA

             FROM CCC_MOVIM_CUENT_CORRI CC,
                  PED_SOLIC_CABEC       CONS,
                  MAE_CLIEN             MC

            WHERE CC.ZORG_OID_REGI = psOidRegion
              AND CC.CLIE_OID_CLIE = psOidCliente
              AND (CC.PERD_OID_PERI = psOidPeriodoActual)
              AND CC.IMP_MOVI > 0
              AND CC.SOCA_OID_SOLI_CABE = CONS.OID_SOLI_CABE
              AND CC.CLIE_OID_CLIE = MC.OID_CLIE
              AND CC.SUBP_OID_SUBP_CREA IN  (SELECT CS.OID_SUBP
                            FROM CCC_PROCE CP,
                                 CCC_SUBPR CS,
                                 COM_COMIS_SUBPR_PAGO CCSP
                           WHERE     CP.OID_PROC = CS.CCPR_OID_PROC
                                 AND CCSP.COD_PROC = CP.COD_PROC
                                 AND CCSP.COD_SUBP = CS.COD_SUBP
                                 AND CCSP.COD_COMI = '02'
                                 AND CCSP.TIP_COMI = 'C'
                                 )
         UNION
           SELECT CC.OID_MOVI_CC,
                  CC.PERD_OID_PERI,
                  CC.FEC_DOCU,
                  CC.FEC_VENC,
                  CC.IMP_MOVI,
                  CC.IMP_PAGO,
                  CC.SUBP_OID_SUBP_ULTI,
                  CC.VAL_ULTI_NUME_HIST,
                  CC.FEC_ULTI_MOVI,
                  CC.ZTAD_OID_TERR_ADMI,
                  CC.SOCA_OID_SOLI_CABE,
                  0 VAL_TASA_IMPU,
                  CC.NUM_IDEN_CUOT VAL_NUME_SOLI,
                  MC.COD_CLIE,
                  NULL,
                  NULL

             FROM CCC_MOVIM_CUENT_CORRI CC,
                  CCC_SUBPR             CS,
                  CCC_PROCE             CP,
                  MAE_CLIEN             MC

            WHERE CC.ZORG_OID_REGI = psOidRegion
              AND CC.CLIE_OID_CLIE = psOidCliente
              AND (CC.PERD_OID_PERI = psOidPeriodoActual)
              AND CC.IMP_MOVI > 0
              AND CC.SOCA_OID_SOLI_CABE IS NULL
              AND CC.CLIE_OID_CLIE = MC.OID_CLIE
              AND CC.SUBP_OID_SUBP_CREA  IN  (SELECT CS.OID_SUBP
                            FROM CCC_PROCE CP,
                                 CCC_SUBPR CS,
                                 COM_COMIS_SUBPR_PAGO CCSP
                           WHERE     CP.OID_PROC = CS.CCPR_OID_PROC
                                 AND CCSP.COD_PROC = CP.COD_PROC
                                 AND CCSP.COD_SUBP = CS.COD_SUBP
                                 AND CCSP.COD_COMI = '02'
                                 AND CCSP.TIP_COMI = 'C'
                                 );

     TYPE ClienteCuentaCorrienteRec IS RECORD(
                oid_movi_cc           	 CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
                perd_oid_peri            CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI%TYPE,
                fec_docu                 CCC_MOVIM_CUENT_CORRI.FEC_DOCU%TYPE,
                fec_venc                 CCC_MOVIM_CUENT_CORRI.FEC_VENC%TYPE,
                imp_movi                 CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE,
                imp_pago                 CCC_MOVIM_CUENT_CORRI.IMP_PAGO%TYPE,
                subp_oid_subp_ulti       CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI%TYPE,
                val_ulti_nume_hist       CCC_MOVIM_CUENT_CORRI.Val_Ulti_Nume_Hist%TYPE,
                fec_ulti_movi            CCC_MOVIM_CUENT_CORRI.FEC_ULTI_MOVI%TYPE,
                ztad_oid_terr_admi       CCC_MOVIM_CUENT_CORRI.ZTAD_OID_TERR_ADMI%TYPE,
                soca_oid_soli_cabe       CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE%TYPE,
                val_tasa_impu           PED_SOLIC_CABEC.VAL_TASA_IMPU%TYPE,
                val_nume_soli           PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
                cod_Clie                MAE_CLIEN.COD_CLIE%TYPE,
                perd_oid_peri_pedi      PED_SOLIC_CABEC.Perd_Oid_Peri%TYPE,
                SUBP_OID_SUBP_CREA       CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA%TYPE
     );

     TYPE ClienteCuentaCorrienteTab IS TABLE OF ClienteCuentaCorrienteRec;
     ClienteCuentaCorrienteRecord ClienteCuentaCorrienteTab;

     CURSOR c_historicoCC (psOidMoviCC NUMBER) is
       SELECT  CCH.IMP_PAGO,
               CCH.SUBP_OID_SUBP,
               CCH.FEC_DOCU,
               CCH.FEC_MOVI
       FROM CCC_HISTO_MOVIM_CC CCH
       WHERE  CCH.MVCC_OID_MOVI_CC = PSOIDMOVICC;

     Cursor c_reclamoHistoCC(psOidClien number, psOidSocaCabe number, psOidSubpCrea number ) is
     SELECT CC.OID_MOVI_CC
     FROM CCC_MOVIM_CUENT_CORRI CC
     WHERE CC.CLIE_OID_CLIE =psOidClien
       AND CC.SOCA_OID_SOLI_CABE = psOidSocaCabe
       AND CC.IMP_MOVI > 0
       AND CC.SUBP_OID_SUBP_CREA = psOidSubpCrea;

	   /* Creando cursor para BONOS*/
	   --se obtine los bonos que pudira tener la seccion en el perido region zona seccion,
	   --y luego se actualizara la tabla de cabecera

	  CURSOR c_bonos IS
	  SELECT Y.COD_REGI,Y.COD_ZONA,Y.COD_SECC,SUM(Y.MON_BONO)
		FROM COM_COMIS_EJCAL_CABEC A,
 		    COM_BONOS_CABEC X , COM_BONOS_DETAL Y
        WHERE A.COD_PAIS = psCodPais
          AND A.COD_CAMP = psCodPeriodo
		  AND A.COD_COMI = psCodComision
          AND X.COD_PAIS =A.COD_PAIS
		  AND TO_NUMBER(A.COD_CAMP) >= TO_NUMBER(X.CAM_INIC)
		  AND (X.CAM_FINA IS NULL OR TO_NUMBER(A.COD_CAMP) <=TO_NUMBER(X.CAM_FINA))
		  AND Y.COD_REGI = A.COD_REGI
		  AND Y.COD_ZONA = A.COD_ZONA
		  AND Y.COD_SECC = A.COD_SECC
		  AND X.COD_PAIS = Y.COD_PAIS
		  AND X.COD_CONC = Y.COD_CONC
		  AND Y.IND_ACTI = '1'
		GROUP BY  Y.COD_REGI,Y.COD_ZONA,Y.COD_SECC;

	   TYPE bonosRec IS RECORD(
		 	 cod_regi	    ZON_REGIO.COD_REGI%TYPE,
			 cod_zona       ZON_ZONA.COD_ZONA%TYPE,
             cod_secc  		ZON_SECCI.COD_SECC%TYPE,
			 val_acum_bono	COM_BONOS_DETAL.MON_BONO%TYPE
         );
 	   TYPE BonosTab IS TABLE OF BonosRec;
	   BonosRecord BonosTab;
	  /*fin de declaracion para bonos*/

      lnIdMarca                     NUMBER;
      lnIdCanal                     NUMBER;
      lnIdPais                      NUMBER;
      lnIdPeriodo                   NUMBER;
      lb_codPeriodoAnterior         VARCHAR2(12);
      lnValor                       NUMBER;
      ldFechaIniPeriodo             DATE;
      ldFechaFinPeriodo             DATE;
      lsMensaje                     VARCHAR2(255);
      lsNomLiderSeccion             VARCHAR2(500):='';
      lv_NivelCampania              VARCHAR2(2);
      LnNumIngreCampa               NUMBER;
      lv_comodin                    NUMBER;

      lsCodLiderSeccion             MAE_CLIEN.COD_CLIE%TYPE;
      lv_codEjecTemp                MAE_CLIEN.COD_CLIE%TYPE;
      lv_codConsTemp                MAE_CLIEN.COD_CLIE%TYPE;
      lsCodCalificacion             COM_COMIS_CALIF_CABEC.Cod_Cali%TYPE;
      lsNivel                       COM_NIVEL.COD_NIVE%TYPE;
      lv_NivelTemp                  COM_NIVEL.COD_NIVE%TYPE;
      lv_codZona                    ZON_ZONA.COD_ZONA%TYPE;
      lv_NumUsosComodin             COM_HISTO_VARIA_EJETR_CABEC.NUM_USOS_COMO%TYPE;
      lnIdPeriodoAnterior           NUMBER;
      lb_codPeriodoSiguiente        VARCHAR2(12);
      lb_codPeriodoMasDos           VARCHAR2(12);
      lnIdPeriodoSiguiente          NUMBER;
      lnIdPeriodoMasDos             NUMBER;
      lv_fec_inic          DATE;
      lv_demandaAnticipada          BOOLEAN;
      lv_numDiasRecuperacion        NUMBER;
      lv_montoVentaNeta             NUMBER(11,2);
      lv_montoReclamo               NUMBER(11,2);
      esPago                        BOOLEAN;
      esReclamo                     BOOLEAN;
      lv_item                       NUMBER;
      lv_montoPago                  NUMBER(11,2);
      lv_sumatoriaReclamo           NUMBER(11,2);
      lv_valNumeSoliTemp            PED_SOLIC_CABEC.Val_Nume_Soli%TYPE;
      lv_sumatoriaPago              NUMBER(11,2);
      lv_sumatoriaReclamoHisto      NUMBER(11,2);
      lv_sumatoriaReclamoHistoTempo NUMBER(11,2);
      lv_procesar                   BOOLEAN;
      lv_itemNumeBoleta             NUMBER;
      lv_actualizar                 BOOLEAN;
      lv_fecDocu        DATE;
      lv_FechaLimite                DATE;
      lv_NumDiasDelPago             NUMBER;
      lv_montoDeCadaPedido          NUMBER;
      lv_porcentajeComision         NUMBER;
      lv_codPorce                   COM_PORCE_COMIS.COD_PORC%TYPE;
      lv_montoPagoDentroLimite      NUMBER(11,2);
      lv_porcentajeRecuperacion     NUMBER(11,2);
      lv_comisionEjecutivaTotal     NUMBER(11,2);
      lv_comisionAspiranteTotal     NUMBER(11,2);
      lv_montoPagoComision          NUMBER(11,2);
      lv_indComodin                 VARCHAR2(2);
      lv_secciones                  BOOLEAN;

      lv_diferenciaDias             NUMBER;
      lnContadorAspirante           NUMBER;
      lv_facturacionAdelantada      BOOLEAN;
      lv_FecUltiMovi                DATE;
      lnIdUnidadAdm                 MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE;
      lv_cabecera                   BOOLEAN;
      l_toleraciaRecuperacion       COM_DATOS_COMIS.VAL_TOLE_RECU%TYPE;
      l_rangoTolerancia             NUMBER(5,2);
	    lnIndicadorBono				NUMBER;

      ---
      lsNumeroComodinRecuperacion   COM_COMIS_CALIF_CABEC.Num_Como_Recu%TYPE;
      lsNumeroComodinAspirante      COM_COMIS_CALIF_CABEC.Num_Como_Recu_aspi%TYPE;
      l_PorcRecuperacionToleracia   COM_DATOS_COMIS.VAL_TOLE_RECU%TYPE;
      lv_indComodinRecuperacion     VARCHAR2(2);
      lv_NumUsosComodinRecuperacion COM_HISTO_VARIA_EJETR_CABEC.NUM_USOS_COMO_RECU%TYPE;
      ---
      vnOidTipoSoliPais PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;
      vnOidSoliCabe PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE%TYPE;
      vnPedidoNoAnulado NUMBER;
      vnIndicadorPasePedido COM_COMIS_CALIF_CABEC.IND_PEDI%TYPE;
      vnNumNivelCampania COM_NIVEL.NUM_NIVE%TYPE;
      vnNumNivelTramo COM_NIVEL.NUM_NIVE%TYPE;
      aaa number;

      ls_evalua_telecobro     BAS_PARAM_PAIS.VAL_PARA%type;
      ln_contadorTelecobro1   number(6);
      ln_contadorTelecobro2   number(6);
      W_TASA_IMPU             number(6);
      ls_campa_pedi           VARCHAR2(6);
      ldFechaTelecobro        date;
      lbGrabarPago            boolean;
      lnCantidadMininoNueva   NUMBER;

    BEGIN
      lv_sumatoriaReclamo:=0;
      lv_sumatoriaPago:=0;
      lnIdPais        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
      lnIdMarca       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
      lnIdCanal       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
      lnIdPeriodo     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

      lb_codPeriodoAnterior := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo,
                                                                      lnIdPais,
                                                                      lnIdMarca,
                                                                      lnIdCanal,
                                                                      -1);
      lb_codPeriodoSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo,
                                                                      lnIdPais,
                                                                      lnIdMarca,
                                                                      lnIdCanal,
                                                                      1);
     lb_codPeriodoMasDos     := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo,
                                                                      lnIdPais,
                                                                      lnIdMarca,
                                                                      lnIdCanal,
                                                                      2);
     lnIdPeriodoAnterior    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lb_codPeriodoAnterior,
                                                                      lnIdMarca,
                                                                      lnIdCanal
                                                                      );
     lnIdPeriodoSiguiente    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lb_codPeriodoSiguiente,
                                                                      lnIdMarca,
                                                                      lnIdCanal
                                                                      );
     lnIdPeriodoMasDos       := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lb_codPeriodoMasDos,
                                                                      lnIdMarca,
                                                                      lnIdCanal
                                                                      );
     lsCodCalificacion       :=COM_FN_DEVUE_CALIF_COMIS(psCodPais,
                                                  psCodMarca,
                                                  psCodCanal,
                                                  psCodTipoComisionista,
                                                  psCodPeriodo);
     --- Obtiene el numero de comodines de recuperacion
     lsNumeroComodinRecuperacion :=COM_FN_DEVUE_NUMER_COMOD_RECUP(psCodPais,
                                                                  psCodMarca,
                                                                  psCodCanal,
                                                                  psCodTipoComisionista,
                                                                  psCodPeriodo);
     lsNumeroComodinAspirante := COM_FN_DEVUE_NUMER_COMOD_ASPIR(psCodPais,
                                                                  psCodMarca,
                                                                  psCodCanal,
                                                                  psCodTipoComisionista,
                                                                  psCodPeriodo);
     ---
     lv_codPorce :=COM_FN_OBTIE_CODIG_PORCE_COMIS(psCodPais,psCodMarca,psCodCanal,psCodTipoComisionista,psCodPeriodo);

     SELECT IND_PEDI
     INTO vnIndicadorPasePedido
     FROM COM_COMIS_CALIF_CABEC
     WHERE COD_PAIS = psCodPais
     AND COD_MARC = psCodMarca
     AND COD_CANA = psCodCanal
     AND COD_TIPO_COMI = psCodTipoComisionista
     AND psCodPeriodo >= CAM_VIGE_DESD
     AND (CAM_VIGE_HAST IS NULL OR (CAM_VIGE_HAST >= psCodPeriodo));

     SELECT OID_TIPO_SOLI_PAIS
     INTO vnOidTipoSoliPais
     FROM PED_TIPO_SOLIC_PAIS
     WHERE PAIS_OID_PAIS = lnIdPais
     AND TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI
                               FROM PED_TIPO_SOLIC
                               WHERE COD_TIPO_SOLI = 'SOC');

     /* Obteniendo valor de telecobro */
    SELECT VAL_PARA
    INTO   ls_evalua_telecobro
    FROM   BAS_PARAM_PAIS
    WHERE  COD_PAIS = psCodPais
    AND    COD_SIST = 'COM'
    AND    COD_PARA = '001';


	/**sergio buchelli : Modificacion para bonos ***/
	--verifcacion si en la campanha de proceso hay bonos para pagar
	lnIndicadorBono := COM_FN_DEVUE_HAY_BONO(psCodPais,psCodPeriodo);
	/***/

      l_toleraciaRecuperacion      := COM_FN_OBTIE_TOLER_RECUP(psCodPais,psCodMarca,psCodCanal,psCodComision);

      IF lnValor = -1 THEN
        RAISE_APPLICATION_ERROR(-20123, lsMensaje);
      END IF;

      FOR fila IN C_VNSC(lnIdPeriodo) LOOP
          lsIteracion1 := 'COD_REGI: ' || fila.COD_REGI || ' COD_ZONA: '||fila.COD_ZONA || ' COD_SECC:'||fila.COD_SECC;
          lsNomLiderSeccion := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                               lsCodLiderSeccion,
                               psCodPeriodo,
                               lnIdPais,
                               fila.cod_subg_vent,
                               fila.COD_REGI,
                               fila.cod_zona,
                               fila.cod_secc);
         DBMS_OUTPUT.put_line('zona: '||fila.cod_zona || ' seccion: '||fila.cod_secc);
         IF lsCodLiderSeccion IS NOT NULL THEN

                lsNivel := COM_FN_OBTIE_NIVEL_EJECU(
                                     psCodPais,
                                     lsCodLiderSeccion,
                                     psAnioTramo,
                                     psNumTramo,
                                     psCodCanal,
                                     psCodMarca,
                                     psCodTipoComisionista,
                                     lv_NumUsosComodin,
                                     lv_NumUsosComodinRecuperacion);


                IF((lsNivel <> SIN_NIVEL AND lsNivel <> TUTORA))THEN
                       lv_item :=0;
                       lv_secciones:=false;
                       lv_cabecera:=true;
                       lv_indComodin := COMODIN_NO;
                       lv_indComodinRecuperacion:=COMODIN_NO;

                       OPEN C_CLIENTES_POR_SECCION(fila.oid_secc, lnIdPeriodo);
                          LOOP
                             FETCH C_CLIENTES_POR_SECCION BULK COLLECT INTO clientesPorSeccionRecord LIMIT W_FILAS;
                             IF clientesPorSeccionRecord.COUNT > 0 THEN
                                FOR i IN clientesPorSeccionRecord.FIRST .. clientesPorSeccionRecord.LAST LOOP

                                 /* lv_sumatoriaReclamo:=0;
                                  lv_sumatoriaPago:=0;*/
                                  lsIteracion2:= ' OID_TERR_ADMI: ' ||trim(clientesPorSeccionRecord(i).OID_TERR_ADMI) ||
                                                 ' OID_CLIE_UNID_ADMI:  ' ||trim(clientesPorSeccionRecord(i).OID_CLIE_UNID_ADMI) ||
                                                 ' CLIE_OID_CLIE:' ||trim(clientesPorSeccionRecord(i).CLIE_OID_CLIE);

                                  lnIdUnidadAdm := COM_PKG_REPOR.COM_FN_DEVUE_OID_UNADM_HISTO(clientesPorSeccionRecord(i).clie_oid_clie
                                                                                              ,clientesPorSeccionRecord(i).oid_terr_admi
                                                                                              ,psCodPeriodo);
                                  IF lnIdUnidadAdm != -1 THEN
                                        OPEN C_CLIENTE_CUENTA_CORRIENTE (clientesPorSeccionRecord(i).zorg_oid_regi, clientesPorSeccionRecord(i).clie_oid_clie,  lnIdPeriodo, lnIdPeriodoAnterior);
                                          LOOP
                                             FETCH C_CLIENTE_CUENTA_CORRIENTE BULK COLLECT INTO ClienteCuentaCorrienteRecord LIMIT W_FILAS;
                                             IF ClienteCuentaCorrienteRecord.COUNT > 0 THEN
                                                  FOR j IN ClienteCuentaCorrienteRecord.FIRST .. ClienteCuentaCorrienteRecord.LAST LOOP
                                                      lsIteracion3:=  ' oid_movi_cc: '||ClienteCuentaCorrienteRecord(j).oid_movi_cc||
                                                                      ' perd_oid_peri: '||ClienteCuentaCorrienteRecord(j).perd_oid_peri||
                                                                      ' fec_docu:'||ClienteCuentaCorrienteRecord(j).fec_docu||
                                                                      ' fec_venc:'||ClienteCuentaCorrienteRecord(j).fec_venc||
                                                                      ' imp_movi:'||ClienteCuentaCorrienteRecord(j).imp_movi||
                                                                      ' imp_pago:'||ClienteCuentaCorrienteRecord(j).imp_pago;

                                                   lv_facturacionAdelantada:=FALSE;
                                                   lv_procesar:=TRUE;
                                                   lbGrabarPago := true;
                                                   lv_sumatoriaReclamo:=0;
                                                   lv_sumatoriaPago:=0;

                                                   IF (clientesPorSeccionRecord(i).clie_oid_clie = 49044594) THEN
                                                       clientesPorSeccionRecord(i).clie_oid_clie := 49044594;
                                                   END IF;

                                                   /* Verificando tipo de cambio para SOCA_OID_SOLI_CABE = NULL */
                                                   BEGIN
                                                      ls_campa_pedi := GEN_PKG_GENER.gen_fn_devuelve_des_perio(ClienteCuentaCorrienteRecord(j).perd_oid_peri_pedi);
                                                   EXCEPTION
                                                   WHEN OTHERS THEN
                                                      ls_campa_pedi :=  psCodPeriodo;
                                                   END;
                                                   if ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE is null then
                                                       W_TASA_IMPU := COM_PKG_REPOR.COM_FN_DEVUE_TASA_IMPUE(psCodPais, ClienteCuentaCorrienteRecord(j).fec_docu);
                                                       IF W_TASA_IMPU < 0 THEN
                                                          W_TASA_IMPU := 0;
                                                       END IF;
                                                       ClienteCuentaCorrienteRecord(j).val_tasa_impu := W_TASA_IMPU;
                                                       ls_campa_pedi := psCodPeriodo;
                                                   ELSE
                                                      IF ClienteCuentaCorrienteRecord(j).perd_oid_peri = ClienteCuentaCorrienteRecord(j).perd_oid_peri_pedi THEN
                                                         ls_campa_pedi := psCodPeriodo;
                                                      END IF;
                                                   end if;

                                                    /* EVALUACION TELECOBRO */
                                                    ldFechaTelecobro := null;
                                                    IF ls_evalua_telecobro = '1' THEN
                                                            BEGIN
                                                              select X.FEC_ASIG
                                                              into  ldFechaTelecobro
                                                              from  COB_DETAL_MOVIM_CARTE X
                                                              where OID_CLIE = clientesPorSeccionRecord(i).CLIE_OID_CLIE
                                                              and   COD_PERI = psCodPeriodo
                                                              and   MVCC_OID_MOVI_CC = ClienteCuentaCorrienteRecord(j).oid_movi_cc
                                                              AND   COD_ETAP_DEUD = 'TEL';
                                                            EXCEPTION
                                                            WHEN NO_DATA_FOUND THEN
                                                                 lv_procesar := TRUE;
                                                                 ldFechaTelecobro := null;
                                                            END;

                                                            IF ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE IS NOT NULL THEN
                                                              IF (ClienteCuentaCorrienteRecord(j).PERD_OID_PERI_PEDI <> ClienteCuentaCorrienteRecord(j).PERD_OID_PERI) THEN
                                                                  ln_contadorTelecobro1 := 0;
                                                                  SELECT COUNT(1)
                                                                  into ln_contadorTelecobro1
                                                                  FROM CCC_MOVIM_CUENT_CORRI A,
                                                                       COB_DETAL_MOVIM_CARTE X

                                                                  WHERE A.CLIE_OID_CLIE = clientesPorSeccionRecord(i).CLIE_OID_CLIE
                                                                   AND A.SUBP_OID_SUBP_CREA = ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_CREA
                                                                   AND A.PERD_OID_PERI = lnIdPeriodoAnterior
                                                                   AND A.SOCA_OID_SOLI_CABE = ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE

                                                                   AND OID_CLIE = A.CLIE_OID_CLIE
                                                                   AND COD_PERI = lb_codPeriodoAnterior
                                                                   AND MVCC_OID_MOVI_CC = A.OID_MOVI_CC
                                                                   AND COD_ETAP_DEUD = 'TEL';

                                                                  IF ln_contadorTelecobro1 > 0  THEN
                                                                     lv_procesar := FALSE;
                                                                  END IF;

                                                               END IF;
                                                           END IF;
                                                    END IF;


                                                    IF lv_procesar THEN
                                                       -- VERIFICAR SI EL REGISTRO ESTA DENTRO DE LAS FECHAS PARA SU PROCESO  --
                                                       lv_fec_inic := COM_PKG_REPOR.COM_FN_MAXIM_FINIC_CRONO (lnIdPais, lnIdPeriodo, fila.oid_zona, 'FA');
                                                       IF lv_fec_inic IS NULL THEN
                                                          lv_procesar := FALSE;
                                                       END IF;
                                                       IF lv_procesar THEN
                                                          IF ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE IS NOT NULL THEN
                                                             IF (ClienteCuentaCorrienteRecord(j).perd_oid_peri = lnIdPeriodo) THEN
                                                                 IF ClienteCuentaCorrienteRecord(j).fec_docu < lv_fec_inic THEN
                                                                    lv_diferenciaDias:=ClienteCuentaCorrienteRecord(j).fec_ulti_movi - lv_fec_inic;
                                                                 ELSE
                                                                    lv_diferenciaDias:=ClienteCuentaCorrienteRecord(j).fec_ulti_movi -  ClienteCuentaCorrienteRecord(j).fec_docu;
                                                                 END IF;
                                                             ELSE
                                                                ClienteCuentaCorrienteRecord(j).fec_docu := lv_fec_inic;
                                                                lv_diferenciaDias:=ClienteCuentaCorrienteRecord(j).fec_ulti_movi -  ClienteCuentaCorrienteRecord(j).fec_docu;
                                                             END IF;
                                                          ELSE
                                                             lv_diferenciaDias:=ClienteCuentaCorrienteRecord(j).fec_ulti_movi -  ClienteCuentaCorrienteRecord(j).fec_docu;
                                                          END IF;
                                                       END IF;
                                                       lv_fecDocu := ClienteCuentaCorrienteRecord(j).fec_docu;
                                                    END IF;

                                                     IF(lv_procesar)THEN

                                                         IF(lv_cabecera)THEN
                                                                -- CREAR CABECERA --
                                                                 INSERT INTO COM_COMIS_EJCAL_CABEC
                                                                    (COD_PAIS,
                                                                   COD_COMI,
                                                                   COD_EJEC,
                                                                   COD_CAMP,
                                                                   COD_TIPO_COMI,
                                                                   COD_MARC,
                                                                   COD_CANA,
                                                                   COD_CALI,
                                                                   COD_ANIO_INIC,
                                                                   COD_RANG,
                                                                   COD_REGI,
                                                                   COD_ZONA,
                                                                   COD_SECC,
                                                                   COD_NIVE,
                                                                   VAL_MOTO_VETA,
                                                                   VAL_MOTO_PAGA,
                                                                   VAL_PORC_RECU,
                                                                   VAL_MOTO_COMI,
                                                                   IND_COMO,
                                                                   USU_EJEC,
                                                                   FEC_EJEC)
                                                                VALUES
                                                                  (psCodPais,
                                                                   psCodComision,
                                                                   lsCodLiderSeccion,
                                                                   psCodPeriodo,
                                                                   psCodTipoComisionista,
                                                                   psCodMarca,
                                                                   psCodCanal,
                                                                   lsCodCalificacion,
                                                                   psAnioTramo,
                                                                   psNumTramo,
                                                                   fila.cod_regi,
                                                                   fila.cod_zona,
                                                                   fila.cod_secc,
                                                                   lsNivel,
                                                                   0,
                                                                   0,
                                                                   0,
                                                                   0,
                                                                   0,
                                                                   pscodUsuario,
                                                                   SYSDATE);
                                                                -- FIN CREAR CABECERA --
                                                               lv_cabecera:=FALSE;
                                                       END IF;

                                                      lv_secciones:=true;
                                                      lv_montoVentaNeta := ClienteCuentaCorrienteRecord(j).IMP_MOVI/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                      esPago       := COM_FN_EXIST_PAGO(ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_ULTI);
                                                      esReclamo    := COM_FN_EXIST_RECLA(ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_ULTI);

                                                      lv_item:=lv_item+1;

                                                     -- OBTENER NUMERO DE DIAS DE RECUPERACION --
                                                     IF (lv_demandaAnticipada)THEN
                                                          SELECT ZDA.NUM_DIAS_TRA1
                                                            INTO lv_numDiasRecuperacion
                                                            FROM COM_ZONA_DEMAN_ANTIC ZDA
                                                           WHERE ZDA.COD_ZONA = fila.cod_zona;

                                                     ELSE
                                                            IF(lsNivel = ASPIRANTE)THEN
                                                                lv_numDiasRecuperacion :=fila.NUM_DIAS_RECU_ASPI;
                                                            ELSE
                                                                lv_numDiasRecuperacion :=fila.NUM_DIAS_RECU_EJEC;
                                                            END IF;
                                                      END IF;
                                                    -- FIN OBTENER NUMERO DE DIA DE RECUPERACION --

                                                     IF(esPago)THEN

                                                           /* Ver si se graba comisiones por Telecobro */
                                                           IF ls_evalua_telecobro = '1' AND ldFechaTelecobro IS NOT NULL  THEN
                                                              IF ldFechaTelecobro < ClienteCuentaCorrienteRecord(j).FEC_ULTI_MOVI THEN
                                                                 lbGrabarPago := false;
                                                              END IF;
                                                           END IF;

                                                           -- CALCULAR DIFERENCIA DE DIAS DE PAGO --
                                                           lv_FechaLimite    := lv_fecDocu + lv_numDiasRecuperacion;
                                                           lv_FechaLimite    := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(lv_FechaLimite);
                                                           lv_NumDiasDelPago := COM_PKG_REPOR.COM_FN_DEVUE_NUMER_DIAS(lv_FechaLimite, lv_numDiasRecuperacion, fila.cod_zona);
                                                          -- FIN CALCULAR DIFERENCIA DE DIAS DE PAGO --

                                                           lv_montoPago     := ClienteCuentaCorrienteRecord(j).IMP_PAGO/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                           lv_sumatoriaPago := lv_sumatoriaPago + lv_montoPago;
                                                           lv_montoReclamo  :=0;
                                                           lv_actualizar    :=false;
                                                           lv_FecUltiMovi   :=ClienteCuentaCorrienteRecord(j).Fec_Ulti_Movi;

                                                           if not lbGrabarPago then
                                                             lv_FecUltiMovi :=  null;
                                                             lv_montoPago   := 0.0;
                                                           end if;
                                                     ELSIF(esReclamo)THEN
                                                            lv_montoReclamo:= ClienteCuentaCorrienteRecord(j).IMP_PAGO/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                            lv_sumatoriaReclamo:=lv_sumatoriaReclamo+lv_montoReclamo;
                                                            lv_montoPago       :=0;
                                                            lv_FecUltiMovi     := NULL;
                                                            lv_diferenciaDias:=0;
                                                            lv_actualizar      :=true;
                                                            lv_NumDiasDelPago:=lv_numDiasRecuperacion;
                                                      ELSE
                                                            lv_montoPago     :=0;
                                                            lv_montoReclamo  :=0;
                                                            lv_FecUltiMovi   := NULL;
                                                            lv_NumDiasDelPago:=lv_numDiasRecuperacion;
                                                            lv_diferenciaDias:=0;
                                                            lv_actualizar   :=true;
                                                      END IF;

                                                      -- CREAR DETALLE --
                                                      INSERT INTO COM_COMIS_EJCAL_DETAL
                                                        (COD_PAIS,
                                                         COD_COMI,
                                                         COD_EJEC,
                                                         COD_CAMP,
                                                         NUM_ITEM,
                                                         COD_TERR,
                                                         COD_CONS,
                                                         NUM_BOLE,
                                                         FEC_EMIS,
                                                         VAL_MONT_VENE,
                                                         VAL_MONT_RENE,
                                                         FEC_PAGO,
                                                         VAL_DIFE_DIAS,
                                                         VAL_PORC_COMI,
                                                         VAL_MONT_COVE,
                                                         VAL_MONT_CORE,
                                                         VAL_MONT_PAGO,
                                                         NUM_DIAS_RECU,
                                                         USU_EJEC,
                                                         FEC_EJEC,
                                                         CAM_PEDI,
                                                         MON_PEDI)
                                                      VALUES
                                                        (psCodPais,
                                                         psCodComision,
                                                         lsCodLiderSeccion,
                                                         psCodPeriodo,
                                                         lv_item,
                                                         clientesPorSeccionRecord(i).cod_terr,
                                                         ClienteCuentaCorrienteRecord(j).cod_clie,
                                                         ClienteCuentaCorrienteRecord(j).val_nume_soli,
                                                         lv_fecDocu,
                                                         lv_montoVentaNeta,
                                                         lv_montoReclamo,
                                                         lv_FecUltiMovi,
                                                         lv_diferenciaDias,
                                                         0,
                                                         0,
                                                         0,
                                                         lv_montoPago,
                                                         lv_NumDiasDelPago,
                                                         pscodUsuario,
                                                         SYSDATE,
                                                         ls_campa_pedi,
                                                         0);

                                                     -- FIN CREAR DETALLE --

                                                  lv_itemNumeBoleta:=lv_item;
                                                   -- BUSCAR PAGOS Y RECLAMOS HISTORICOS --
                                                   IF(ClienteCuentaCorrienteRecord(j).VAL_ULTI_NUME_HIST>1)THEN
                                                           FOR fila3 IN c_historicoCC(ClienteCuentaCorrienteRecord(j).Oid_Movi_CC) LOOP
                                                                  lv_montoPago:=FILA3.IMP_PAGO/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                                  esPago      := COM_FN_EXIST_PAGO(FILA3.SUBP_OID_SUBP);
                                                                  esReclamo   := COM_FN_EXIST_RECLA(FILA3.Subp_Oid_Subp);

                                                                 IF(esPago)THEN

                                                                      /* Ver si se graba comisiones por Telecobro */
                                                                       IF ls_evalua_telecobro = '1' AND ldFechaTelecobro IS NOT NULL  THEN
                                                                          IF ldFechaTelecobro < fila3.FEC_MOVI THEN
                                                                             continue;
                                                                          END IF;
                                                                       END IF;
                                                                      -- CALCULAR DIFERENCIA DE DIAS DE PAGO --
                                                                       lv_FechaLimite    := lv_fecDocu + lv_numDiasRecuperacion;
                                                                       lv_FechaLimite    := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(lv_FechaLimite);
                                                                       lv_NumDiasDelPago := COM_PKG_REPOR.COM_FN_DEVUE_NUMER_DIAS(lv_FechaLimite, lv_numDiasRecuperacion, fila.cod_zona);

                                                                       /*IF(lv_facturacionAdelantada)THEN
                                                                         lv_diferenciaDias:=fila3.fec_movi-lv_fec_inic;
                                                                       ELSE
                                                                         lv_diferenciaDias:=fila3.fec_movi-lv_fecDocu;
                                                                       END IF;
                                                                       */

                                                                       IF ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE IS NOT NULL THEN
                                                                           IF (ClienteCuentaCorrienteRecord(j).perd_oid_peri = lnIdPeriodo) THEN
                                                                               IF fila3.fec_docu < lv_fec_inic THEN
                                                                                  lv_diferenciaDias:=fila3.fec_movi - lv_fec_inic;
                                                                               ELSE
                                                                                  lv_diferenciaDias:=fila3.fec_movi -  fila3.fec_docu;
                                                                               END IF;
                                                                           ELSE
                                                                              fila3.fec_docu := lv_fec_inic;
                                                                              lv_diferenciaDias:=fila3.fec_movi -  fila3.fec_docu;
                                                                           END IF;
                                                                        ELSE
                                                                           lv_diferenciaDias:=fila3.fec_movi -  fila3.fec_docu;
                                                                        END IF;

                                                                      -- FIN CALCULAR DIFERENCIA DE DIAS DE PAGO --

                                                                     lv_sumatoriaPago := lv_sumatoriaPago + lv_montoPago;

                                                                     IF(lv_actualizar) THEN
                                                                       UPDATE COM_COMIS_EJCAL_DETAL
                                                                         SET VAL_MONT_RENE = lv_sumatoriaReclamo,
                                                                             VAL_MONT_PAGO = lv_montoPago,
                                                                             FEC_PAGO      = fila3.fec_movi,
                                                                             VAL_DIFE_DIAS = lv_diferenciaDias,
                                                                             NUM_DIAS_RECU = lv_NumDiasDelPago
                                                                       WHERE COD_PAIS = psCodPais
                                                                         AND COD_CAMP = psCodPeriodo
                                                                         AND COD_COMI = psCodComision
                                                                         AND COD_EJEC = lsCodLiderSeccion
                                                                         AND COD_CONS = ClienteCuentaCorrienteRecord(j).cod_clie
                                                                         AND NUM_ITEM = lv_itemNumeBoleta;
                                                                         lv_actualizar:=false;

                                                                      ELSE
                                                                          lv_item :=lv_item+1;
                                                                          INSERT INTO COM_COMIS_EJCAL_DETAL
                                                                            (COD_PAIS,
                                                                             COD_COMI,
                                                                             COD_EJEC,
                                                                             COD_CAMP,
                                                                             NUM_ITEM,
                                                                             COD_TERR,
                                                                             COD_CONS,
                                                                             NUM_BOLE,
                                                                             FEC_EMIS,
                                                                             VAL_MONT_VENE,
                                                                             VAL_MONT_RENE,
                                                                             FEC_PAGO,
                                                                             VAL_DIFE_DIAS,
                                                                             VAL_PORC_COMI,
                                                                             VAL_MONT_COVE,
                                                                             VAL_MONT_CORE,
                                                                             VAL_MONT_PAGO,
                                                                             NUM_DIAS_RECU,
                                                                             USU_EJEC,
                                                                             FEC_EJEC,
                                                                             CAM_PEDI,
                                                                             MON_PEDI)
                                                                          VALUES
                                                                          (psCodPais,
                                                                           psCodComision,
                                                                           lsCodLiderSeccion,
                                                                           psCodPeriodo,
                                                                           lv_item,
                                                                           clientesPorSeccionRecord(i).cod_terr,
                                                                           ClienteCuentaCorrienteRecord(j).cod_clie,
                                                                           ClienteCuentaCorrienteRecord(j).val_nume_soli,
                                                                           lv_fecDocu,
                                                                           0,
                                                                           0,
                                                                           fila3.fec_movi,
                                                                           lv_diferenciaDias,
                                                                           0,
                                                                           0,
                                                                           0,
                                                                           lv_montoPago,
                                                                           lv_NumDiasDelPago,
                                                                           pscodUsuario,
                                                                           SYSDATE,
                                                                           ls_campa_pedi,
                                                                           0);
                                                                      END IF;

                                                                  END IF;

                                                                  IF(esReclamo)THEN
                                                                        lv_sumatoriaReclamo:= lv_sumatoriaReclamo + lv_montoPago;
                                                                  END IF;

                                                           END LOOP;

                                                 UPDATE COM_COMIS_EJCAL_DETAL
                                                   SET VAL_MONT_RENE = lv_sumatoriaReclamo
                                                 WHERE COD_PAIS = psCodPais
                                                   AND COD_COMI = psCodComision
                                                   AND COD_CAMP = psCodPeriodo
                                                   AND COD_EJEC = lsCodLiderSeccion
                                                   AND COD_CONS = ClienteCuentaCorrienteRecord(j).cod_clie
                                                   AND NUM_BOLE = ClienteCuentaCorrienteRecord(j).Val_Nume_Soli
                                                   AND NUM_ITEM = lv_itemNumeBoleta;

                                          END IF;
                                          -- FIN BUSCAR PAGOS Y RECLAMOS HISTORICOS --

                                          -- CALCULAR EL MONTO DE LA COMISION POR VENTA PARA EJECUTIVAS Y ASPIRANTES --

                                                 IF(lsNivel = EJECUTIVA_JUNIOR  OR lsNivel = EJECUTIVA_SENIOR
                                                      OR lsNivel = EJECUTIVA_MASTER)THEN

                                                         lv_sumatoriaPago := 0.0;
                                                         BEGIN
                                                           SELECT NVL(SUM(CC.IMP_MOVI),0.0)
                                                           INTO lv_sumatoriaPago
                                                           FROM CCC_MOVIM_CUENT_CORRI CC
                                                           WHERE CC.CLIE_OID_CLIE = clientesPorSeccionRecord(i).CLIE_OID_CLIE
                                                             AND CC.SOCA_OID_SOLI_CABE = ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE
                                                             AND CC.IMP_MOVI > 0
                                                             AND CC.SUBP_OID_SUBP_CREA IN (SELECT CS.OID_SUBP
                                                                      FROM CCC_PROCE CP,
                                                                           CCC_SUBPR CS,
                                                                           COM_COMIS_SUBPR_PAGO CCSP
                                                                     WHERE     CP.OID_PROC = CS.CCPR_OID_PROC
                                                                           AND CCSP.COD_PROC = CP.COD_PROC
                                                                           AND CCSP.COD_SUBP = CS.COD_SUBP
                                                                           AND CCSP.COD_COMI = '02'
                                                                           AND CCSP.TIP_COMI = 'C');
                                                             ---ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_CREA;
                                                         EXCEPTION
                                                         WHEN NO_DATA_FOUND THEN
                                                              lv_sumatoriaPago := 0.0;
                                                         END;

                                                         lv_sumatoriaReclamo := 0.0;
                                                         BEGIN
                                                           SELECT NVL(SUM(CC.IMP_PAGO),0.0)
                                                           INTO lv_sumatoriaReclamo
                                                           FROM CCC_MOVIM_CUENT_CORRI CC,
                                                                CCC_PROCE A,
                                                                CCC_SUBPR B,
                                                                COM_COMIS_SUBPR_PAGO C
                                                           WHERE CC.CLIE_OID_CLIE = clientesPorSeccionRecord(i).CLIE_OID_CLIE
                                                             AND CC.SOCA_OID_SOLI_CABE = ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE
                                                             AND CC.SUBP_OID_SUBP_CREA IN (SELECT CS.OID_SUBP
                                                                      FROM CCC_PROCE CP,
                                                                           CCC_SUBPR CS,
                                                                           COM_COMIS_SUBPR_PAGO CCSP
                                                                     WHERE     CP.OID_PROC = CS.CCPR_OID_PROC
                                                                           AND CCSP.COD_PROC = CP.COD_PROC
                                                                           AND CCSP.COD_SUBP = CS.COD_SUBP
                                                                           AND CCSP.COD_COMI = '02'
                                                                           AND CCSP.TIP_COMI = 'C')
                                                             ----= ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_CREA
                                                             AND CC.IMP_MOVI > 0
                                                             AND A.OID_PROC = B.CCPR_OID_PROC
                                                             AND B.OID_SUBP = CC.SUBP_OID_SUBP_ULTI
                                                             AND B.COD_SUBP = C.COD_SUBP
                                                             AND C.COD_PROC = A.COD_PROC
                                                             AND C.COD_SUBP = B.COD_SUBP
                                                             AND C.COD_COMI = '02'
                                                             AND C.TIP_COMI = 'R';
                                                         EXCEPTION
                                                         WHEN NO_DATA_FOUND THEN
                                                              lv_sumatoriaReclamo := 0.0;
                                                         END;


                                                         lv_sumatoriaReclamoHisto := 0.0;
                                                         for filaReclamohisto in c_reclamoHistoCC(clientesPorSeccionRecord(i).CLIE_OID_CLIE,
                                                                                 ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE,
                                                                                 ClienteCuentaCorrienteRecord(j).SUBP_OID_SUBP_CREA ) loop

                                                             BEGIN
                                                               lv_sumatoriaReclamoHistoTempo := 0;
                                                               lv_sumatoriaReclamoHisto := 0.0;
                                                               SELECT NVL(SUM(CC.IMP_PAGO),0.0)
                                                               INTO lv_sumatoriaReclamoHistoTempo
                                                               FROM  CCC_HISTO_MOVIM_CC CC,
                                                                    CCC_PROCE A,
                                                                    CCC_SUBPR B,
                                                                    COM_COMIS_SUBPR_PAGO C
                                                               WHERE CC.MVCC_OID_MOVI_CC in (  SELECT cc.oid_movi_cc
                                                                           FROM CCC_MOVIM_CUENT_CORRI CC
                                                                           WHERE CC.CLIE_OID_CLIE = clientesPorSeccionRecord(i).CLIE_OID_CLIE
                                                                           AND CC.SOCA_OID_SOLI_CABE = ClienteCuentaCorrienteRecord(j).SOCA_OID_SOLI_CABE
                                                                           AND CC.IMP_MOVI > 0
                                                                           AND CC.SUBP_OID_SUBP_CREA IN (SELECT CS.OID_SUBP
                                                                                         FROM CCC_PROCE CP,
                                                                                              CCC_SUBPR CS,
                                                                                              COM_COMIS_SUBPR_PAGO CCSP
                                                                                         WHERE     CP.OID_PROC = CS.CCPR_OID_PROC
                                                                                         AND CCSP.COD_PROC = CP.COD_PROC
                                                                                         AND CCSP.COD_SUBP = CS.COD_SUBP
                                                                                         AND CCSP.COD_COMI = '02'
                                                                                         AND CCSP.TIP_COMI = 'C')
                                                                  )
                                                               -----= filaReclamohisto.OID_MOVI_CC
                                                                 AND CC.IMP_MOVI > 0
                                                                 AND A.OID_PROC = B.CCPR_OID_PROC
                                                                 AND B.OID_SUBP = CC.SUBP_OID_SUBP
                                                                 AND B.COD_SUBP = C.COD_SUBP
                                                                 AND C.COD_PROC = A.COD_PROC
                                                                 AND C.COD_SUBP = B.COD_SUBP
                                                                 AND C.COD_COMI = '02'
                                                                 AND C.TIP_COMI = 'R';
                                                             EXCEPTION
                                                             WHEN NO_DATA_FOUND THEN
                                                                  lv_sumatoriaReclamoHistoTempo := 0.0;
                                                             END;
                                                             lv_sumatoriaReclamoHisto := lv_sumatoriaReclamoHisto +  lv_sumatoriaReclamoHistoTempo;
                                                         end loop;

                                                         lv_sumatoriaPago := lv_sumatoriaPago/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                         lv_sumatoriaReclamo := lv_sumatoriaReclamo/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);
                                                         lv_sumatoriaReclamoHisto := lv_sumatoriaReclamoHisto/(1+ ClienteCuentaCorrienteRecord(j).VAL_TASA_IMPU/100);


                                                         lv_montoDeCadaPedido := lv_sumatoriaPago - lv_sumatoriaReclamo - lv_sumatoriaReclamoHisto ;
                                                         lv_porcentajeComision:= COM_FN_OBTIE_PORCE_COMIS(psCodPais, lsNivel, lv_codPorce, lv_montoDeCadaPedido);

                                                         UPDATE COM_COMIS_EJCAL_DETAL
                                                           SET VAL_PORC_COMI = lv_porcentajeComision,
                                                               MON_PEDI = lv_montoDeCadaPedido,
                                                               VAL_MONT_COVE = CASE
                                                                                  WHEN VAL_DIFE_DIAS<= NUM_DIAS_RECU  THEN (VAL_MONT_PAGO)*lv_porcentajeComision/100
                                                                                  ELSE 0
                                                                               END
                                                         WHERE COD_PAIS = psCodPais
                                                           AND COD_COMI = psCodComision
                                                           AND COD_CAMP = psCodPeriodo
                                                           AND COD_EJEC = lsCodLiderSeccion
                                                           AND COD_CONS = ClienteCuentaCorrienteRecord(j).cod_clie
                                                           AND NUM_BOLE = ClienteCuentaCorrienteRecord(j).Val_Nume_Soli;


                                                  END IF;

                                                  IF(lsNivel=ASPIRANTE)THEN
                                                         UPDATE COM_COMIS_EJCAL_DETAL
                                                           SET VAL_PORC_COMI = fila.Val_Porc_Comi_Aspi,
                                                               VAL_MONT_CORE = CASE
                                                                                  WHEN VAL_DIFE_DIAS<= NUM_DIAS_RECU  THEN (VAL_MONT_PAGO)*fila.Val_Porc_Comi_Aspi/100
                                                                                  ELSE 0
                                                                               END
                                                         WHERE COD_PAIS = psCodPais
                                                           AND COD_COMI = psCodComision
                                                           AND COD_CAMP = psCodPeriodo
                                                           AND COD_EJEC = lsCodLiderSeccion
                                                           AND COD_CONS = ClienteCuentaCorrienteRecord(j).cod_clie
                                                           AND NUM_BOLE = ClienteCuentaCorrienteRecord(j).Val_Nume_Soli;
                                                  END IF;

                                       END IF; --FIN IF PROCESAR DOCUMENTO



                                                  END LOOP;
                                             END IF;
                                          EXIT WHEN C_CLIENTE_CUENTA_CORRIENTE%NOTFOUND;
                                          END LOOP;
                                        CLOSE C_CLIENTE_CUENTA_CORRIENTE;



                                   END IF;

                                END LOOP;
                             END IF;
                             EXIT WHEN C_CLIENTES_POR_SECCION%NOTFOUND;
                           END LOOP;
                       CLOSE C_CLIENTES_POR_SECCION;

                       -- CALCUALAR PORCENTAJE DE RECUPERACION Y TOTALES --
                       IF(lv_secciones)THEN

                               select COUNT(1)
                               INTO   vnPedidoNoAnulado
                               from ped_solic_cabec p,
                                    ped_solic_cabec cons
                               where p.clie_oid_clie = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lsCodLiderSeccion)
                               and p.pais_oid_pais = lnIdPais
                               and p.tspa_oid_tipo_soli_pais = vnOidTipoSoliPais
                               and p.fec_fact is not null
                               and p.perd_oid_peri =  lnIdPeriodo
                               and p.soca_oid_soli_cabe = cons.oid_soli_cabe
                               and cons.esso_oid_esta_soli <> 4;

                              SELECT
                                     B.MOTO_NETO_EJEC,
                                     B.MONTO_PAGO_DENTRO_LIMIT,
                                     ROUND((B.MONTO_PAGO_DENTRO_LIMIT / B.MOTO_NETO_EJEC)*100,2) PORCENTAJE_RECUPERACION,
                                     B.COMISION_EJEC_TOTAL,
                                     B.COMISION_ASPI_TOTAL
                                INTO  lv_montoVentaNeta,
                                      lv_montoPagoDentroLimite,
                                      lv_porcentajeRecuperacion,
                                      lv_comisionEjecutivaTotal,
                                      lv_comisionAspiranteTotal
                                FROM (SELECT A.COD_EJECT,
                                             SUM(A.MONTO_VENTA_NETA) MOTO_NETO_EJEC,
                                             SUM(A.MONTO_PAGADO_DENTRO_LIMITE) MONTO_PAGO_DENTRO_LIMIT,
                                             SUM(A.COMISION_EJEC) COMISION_EJEC_TOTAL,
                                             SUM(A.COMISION_ASPI) COMISION_ASPI_TOTAL
                                        FROM (SELECT CE.COD_EJEC COD_EJECT,
                                                     (CE.VAL_MONT_VENE -
                                                     CE.VAL_MONT_RENE) MONTO_VENTA_NETA,
                                                     CASE
                                                       WHEN CE.VAL_DIFE_DIAS <= CE.NUM_DIAS_RECU THEN
                                                            CE.VAL_MONT_PAGO
                                                       ELSE 0
                                                     END MONTO_PAGADO_DENTRO_LIMITE,
                                                     CE.VAL_MONT_COVE COMISION_EJEC,
                                                     CE.VAL_MONT_CORE COMISION_ASPI
                                                FROM COM_COMIS_EJCAL_DETAL CE
                                                WHERE CE.COD_CAMP=psCodPeriodo
                                                      AND CE.COD_EJEC=lsCodLiderSeccion) A
                                       GROUP BY A.COD_EJECT) B;
                             -- FIN CALCUALAR PORCENTAJE DE RECUPERACION Y TOTALES --

                             -- ACTUALIZAR CABECERA CON MONTOS TOTALES --
                             lv_montoPagoComision:=0;
                             lv_comodin:=COM_FN_OBTIE_CALIF_COMOD(lsCodCalificacion);
                             ---
                             --lv_indComodinRecuperacion := COM_FN_OBTIE_CALIF_COMOD_RECUP(lsCodCalificacion);
                             ---
                             IF (lsNivel = EJECUTIVA_JUNIOR  OR lsNivel = EJECUTIVA_SENIOR OR lsNivel = EJECUTIVA_MASTER) THEN
                                  lv_NivelCampania := COM_FN_OBTIE_NIVEL_CAMPA(psCodPais,
                                                                                     psCodMarca,
                                                                                     psCodCanal,
                                                                                     lsCodLiderSeccion,
                                                                                     psCodPeriodo,
                                                                                     LnNumIngreCampa
                                                                                     );
                                   SELECT NUM_NIVE
                                   INTO vnNumNivelCampania
                                   FROM COM_NIVEL
                                   WHERE COD_PAIS = psCodPais
                                   AND COD_NIVE = lv_NivelCampania;

                                   SELECT NUM_NIVE
                                   INTO vnNumNivelTramo
                                   FROM COM_NIVEL
                                   WHERE COD_PAIS = psCodPais
                                   AND COD_NIVE = lsNivel;

                                   /* OBTENIENDO CANTIDAD MINIMO NUEVA */
                                   lnCantidadMininoNueva := 0;
                                   BEGIN
                                     SELECT X.CAN_MINI_NUEV
                                     INTO lnCantidadMininoNueva
                                     FROM COM_MINIM_NUEVA X
                                     WHERE X.COD_REGI = fila.COD_REGI
                                       AND X.COD_ZONA = fila.cod_zona;
                                  EXCEPTION
                                  WHEN NO_DATA_FOUND THEN
                                       BEGIN
                                         SELECT X.CAN_MINI_NUEV
                                         INTO lnCantidadMininoNueva
                                         FROM COM_MINIM_NUEVA X
                                         WHERE X.COD_REGI = fila.COD_REGI
                                          and  x.cod_zona is null;
                                       EXCEPTION
                                       WHEN NO_DATA_FOUND THEN
                                            lnCantidadMininoNueva := 0;
                                       END;
                                  END;

                                  IF(lv_porcentajeRecuperacion >= fila.val_porc_recu_ejec)THEN
                                            IF (vnIndicadorPasePedido = 1 AND vnNumNivelCampania >= vnNumNivelTramo AND vnPedidoNoAnulado > 0 AND LnNumIngreCampa >= lnCantidadMininoNueva) THEN
                                                  lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                                  lv_indComodin:=COMODIN_NO;
                                            ELSIF (vnIndicadorPasePedido = 1 AND vnNumNivelCampania >= vnNumNivelTramo AND vnPedidoNoAnulado = 0 AND LnNumIngreCampa >= lnCantidadMininoNueva) THEN
                                                  IF (lv_NumUsosComodin >= lv_comodin) THEN
                                                     lv_montoPagoComision:=0;
                                                     lv_indComodin:=COMODIN_NO;
                                                  ELSE
                                                  lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                                  lv_indComodin:=COMODIN_SI;
                                                  UPDATE COM_HISTO_VARIA_EJETR_CABEC A
                                                   SET    A.NUM_USOS_COMO = NVL2(A.NUM_USOS_COMO,A.NUM_USOS_COMO+1,1)
                                                   WHERE  A.COD_PAIS = psCodPais
                                                         AND A.COD_MARC = psCodMarca
                                                         AND A.COD_CANA = psCodCanal
                                                         AND A.COD_RANG = psNumTramo
                                                         AND A.COD_TIPO_COMI = psCodTipoComisionista
                                                         AND A.NUM_ANIO_INIC = psAnioTramo
                                                         AND A.Cod_Ejec = lsCodLiderSeccion;
                                                  END IF;
                                            ELSIF(vnNumNivelCampania >= vnNumNivelTramo AND LnNumIngreCampa >= lnCantidadMininoNueva ) THEN
                                            lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                            lv_indComodin:=COMODIN_NO;
                                            ELSE
                                               IF (lv_NumUsosComodin >= lv_comodin) THEN
                                            lv_montoPagoComision:=0;
                                            lv_indComodin:=COMODIN_NO;
                                            ELSE
                                                lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                                lv_indComodin:=COMODIN_SI;

                                               UPDATE COM_HISTO_VARIA_EJETR_CABEC A
                                               SET    A.NUM_USOS_COMO = NVL2(A.NUM_USOS_COMO,A.NUM_USOS_COMO+1,1)
                                               WHERE  A.COD_PAIS = psCodPais
                                                     AND A.COD_MARC = psCodMarca
                                                     AND A.COD_CANA = psCodCanal
                                                     AND A.COD_RANG = psNumTramo
                                                     AND A.COD_TIPO_COMI = psCodTipoComisionista
                                                     AND A.NUM_ANIO_INIC = psAnioTramo
                                                     AND A.Cod_Ejec = lsCodLiderSeccion;
                                          END IF;
                                            END IF;
                                  ELSIF(l_toleraciaRecuperacion > 0) THEN
                                     --- Calculo punto 7
                                     l_PorcRecuperacionToleracia := fila.val_porc_recu_ejec - l_toleraciaRecuperacion;
                                     IF lv_porcentajeRecuperacion >= l_PorcRecuperacionToleracia THEN
                                        IF lv_NumUsosComodinRecuperacion >= lsNumeroComodinRecuperacion THEN
                                           lv_montoPagoComision:=0;
                                           lv_indComodinRecuperacion:=COMODIN_NO;
                                        ELSE
                                            IF (vnIndicadorPasePedido = 1 AND vnNumNivelCampania >= vnNumNivelTramo AND vnPedidoNoAnulado > 0 AND LnNumIngreCampa >= lnCantidadMininoNueva) THEN
                                                  lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                                lv_indComodinRecuperacion:=COMODIN_SI;

                                                UPDATE COM_HISTO_VARIA_EJETR_CABEC A
                                                   SET A.NUM_USOS_COMO_RECU = NVL2(A.NUM_USOS_COMO_RECU,A.NUM_USOS_COMO_RECU+1,1)
                                                 WHERE A.COD_PAIS = psCodPais
                                                   AND A.COD_MARC = psCodMarca
                                                   AND A.COD_CANA = psCodCanal
                                                   AND A.COD_RANG = psNumTramo
                                                   AND A.COD_TIPO_COMI = psCodTipoComisionista
                                                   AND A.NUM_ANIO_INIC = psAnioTramo
                                                   AND A.Cod_Ejec = lsCodLiderSeccion;
                                            ELSIF (vnIndicadorPasePedido = 1 AND vnNumNivelCampania >= vnNumNivelTramo AND vnPedidoNoAnulado = 0 AND LnNumIngreCampa >= lnCantidadMininoNueva) THEN
                                                  lv_montoPagoComision:=0;
                                                  lv_indComodinRecuperacion:=COMODIN_NO;
                                            ELSIF vnNumNivelCampania >= vnNumNivelTramo AND LnNumIngreCampa >= lnCantidadMininoNueva THEN
                                                lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                                lv_indComodinRecuperacion:=COMODIN_SI;

                                                UPDATE COM_HISTO_VARIA_EJETR_CABEC A
                                                   SET A.NUM_USOS_COMO_RECU = NVL2(A.NUM_USOS_COMO_RECU,A.NUM_USOS_COMO_RECU+1,1)
                                                 WHERE A.COD_PAIS = psCodPais
                                                   AND A.COD_MARC = psCodMarca
                                                   AND A.COD_CANA = psCodCanal
                                                   AND A.COD_RANG = psNumTramo
                                                   AND A.COD_TIPO_COMI = psCodTipoComisionista
                                                   AND A.NUM_ANIO_INIC = psAnioTramo
                                                   AND A.Cod_Ejec = lsCodLiderSeccion;
                                             ELSE
                                                lv_montoPagoComision:=0;
                                                lv_indComodinRecuperacion:=COMODIN_NO;
                                             END IF;
                                        END IF;
                                     ELSE
                                        lv_montoPagoComision:=0;
                                        lv_indComodinRecuperacion:=COMODIN_NO;
                                     END IF;
                                  ELSE
                                     lv_montoPagoComision:=0;
                                     lv_indComodinRecuperacion:=COMODIN_NO;
                                  END IF;
                                  END IF;
                                  --
                             END IF;
                             IF(lsNivel = ASPIRANTE)THEN
                                l_PorcRecuperacionToleracia := fila.val_porc_recu_aspi - l_toleraciaRecuperacion;
                                lv_montoPagoComision := 0;
                                lv_indComodinRecuperacion:=COMODIN_NO;
                                IF(lv_porcentajeRecuperacion>=fila.val_porc_recu_aspi)THEN
                                      lv_montoPagoComision:=lv_comisionAspiranteTotal;
                                      lv_indComodin:=COMODIN_NO;
                                ELSIF(l_toleraciaRecuperacion > 0) THEN
                                     IF lv_porcentajeRecuperacion >= l_PorcRecuperacionToleracia THEN
                                        BEGIN
                                          SELECT COUNT(1)
                                          INTO lnContadorAspirante
                                          FROM COM_COMIS_EJCAL_CABEC A
                                          WHERE A.COD_PAIS = psCodPais
                                            AND A.COD_COMI = psCodComision
                                            AND A.COD_EJEC = lsCodLiderSeccion
                                            AND A.COD_ANIO_INIC = psAnioTramo
                                            AND A.COD_RANG = psNumTramo
                                            AND A.COD_CAMP < psCodPeriodo
                                            AND A.IND_COMO_RECU = COMODIN_SI ;
                                       EXCEPTION
                                       WHEN NO_DATA_FOUND THEN
                                            lnContadorAspirante := 0;
                                       END;

                                       IF lnContadorAspirante >= lsNumeroComodinAspirante THEN
                                          lv_montoPagoComision:= 0;
                                       ELSE
                                           lv_montoPagoComision:=lv_comisionEjecutivaTotal;
                                            lv_indComodinRecuperacion:=COMODIN_SI;
                                       END IF;

                                    END IF;
                                END IF;
                             END IF;
                             UPDATE COM_COMIS_EJCAL_CABEC A
                                SET A.VAL_PORC_RECU = lv_porcentajeRecuperacion,
                                    A.VAL_MOTO_VETA = lv_montoVentaNeta,
                                    A.VAL_MOTO_PAGA = lv_montoPagoDentroLimite,
                                    A.VAL_MOTO_COMI = lv_montoPagoComision,
                                    A.IND_COMO = lv_indComodin,
                                    A.Ind_Como_Recu = lv_indComodinRecuperacion
                              WHERE A.COD_PAIS = psCodPais
                                AND A.COD_COMI = psCodComision
                                AND A.COD_EJEC = lsCodLiderSeccion
                                AND A.COD_CAMP = psCodPeriodo;
                            aaa:= SQL%rowcount;

                     -- FIN ACTUALIZAR CABECERA CON MONTOS TOTALES --
                 END IF;
       END IF;

      END LOOP;

	  --SB....SE TERMINA DE RECORRER POR SECCION , YA REGISTRO EN LA CABECERA DE COMISIONES CALCULADAS
	  --AHORA ACTUALIZAMOS EL BONO SI ESTE EXISTE
	  IF(lnIndicadorBono = 1) THEN

		 OPEN C_BONOS;
		  LOOP
		      FETCH C_BONOS BULK COLLECT INTO bonosRecord LIMIT W_FILAS;
		       IF bonosRecord.COUNT > 0 THEN
		          FOR i IN bonosRecord.FIRST .. bonosRecord.LAST LOOP

					  UPDATE COM_COMIS_EJCAL_CABEC A
                                SET A.VAL_BONO = bonosRecord(i).val_acum_bono
                              WHERE A.COD_PAIS = psCodPais
                                AND A.COD_COMI = psCodComision
								AND A.COD_REGI = bonosRecord(i).cod_regi
								AND A.COD_ZONA = bonosRecord(i).cod_zona
								AND A.COD_SECC = bonosRecord(i).cod_secc
                                AND A.COD_CAMP = psCodPeriodo
								/*AND A.VAL_MOTO_COMI >0*/;


		          END LOOP;
		       END IF;
		      EXIT WHEN C_BONOS%NOTFOUND;
		   END LOOP;
		 CLOSE C_BONOS;

	  END IF;


    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 250);
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR COM_PR_CALCU_COMIC_RECUP : ' ||
                                lsIteracion1 || lsIteracion2 ||lsIteracion3 ||
                                ls_sqlerrm);
    END COM_PR_CALCU_COMIC_RECUP;

/***************************************************************************
Descripcion       :Recurpera el Nivel de la ejecutiva
                   de no encotrar registros retorna AS = Aspiarante
Parametros        :
                   psCodPais             Cogigo Pais
                   lsCodLiderSeccion     Codigo Lider
                   psAnioTramo           Año del trbato
                   psNumTramo            Numero del tramo
                   psCodCanal            Codigo Canal
                   psCodMarca            Codigo Marca
                   psCodTipoComisionista Codigo Tipo Comisionista

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_NIVEL_EJECU( psCodPais VARCHAR2,
                                   lsCodLiderSeccion VARCHAR2,
                                   psAnioTramo VARCHAR2,
                                   psNumTramo VARCHAR2,
                                   psCodCanal VARCHAR2,
                                   psCodMarca VARCHAR2,
                                   psCodTipoComisionista VARCHAR2,
                                   psNumUsosComodin OUT NUMBER,
                                   psNumUsosComodinRecu OUT NUMBER
                                   ) RETURN VARCHAR2 IS

lv_nivel COM_NIVEL.COD_NIVE%TYPE;

BEGIN
     SELECT a.cod_nive, NVL(a.num_usos_como,0),NVL(a.num_usos_como_recu,0)
       INTO lv_nivel,psNumUsosComodin,psNumUsosComodinRecu
       FROM COM_HISTO_VARIA_EJETR_CABEC A
      WHERE A.COD_PAIS = psCodPais
        AND A.COD_MARC = psCodMarca
        AND A.COD_CANA = psCodCanal
        AND A.COD_EJEC = lsCodLiderSeccion
        AND A.NUM_ANIO_INIC = psAnioTramo
        AND A.COD_TIPO_COMI = psCodTipoComisionista
        AND A.COD_RANG = psNumTramo;
       RETURN  lv_nivel;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN ASPIRANTE;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_BI_CAMPA: ' ||
                            ls_sqlerrm);
    RETURN '';

END COM_FN_OBTIE_NIVEL_EJECU;

/***************************************************************************
Descripcion       :Recurpera numero de Campañas sin nivel en en el tramo
                   de lo contrario Error
Parametros        :
                   psCodPais      Cogigo Pais
                   psCodMarca     Codigo Marca
                   psCodCanal     Codigo Canal
                   psCodEjecutiva Codigo Ejecutiva
                   psCodPeriodo   Codigo Periodo

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_NIVEL_CAMPA(
                                   psCodPais      VARCHAR2,
                                   psCodMarca     VARCHAR2,
                                   psCodCanal     VARCHAR2,
                                   psCodEjecutiva VARCHAR2,
                                   psCodPeriodo   VARCHAR2,
                                   pnNumIngreCampa   OUT NUMBER
                                   ) RETURN VARCHAR2 IS

lv_NivelCampania  COM_HISTO_VARIA_EJCAM.COD_NIVE%TYPE;

BEGIN
      SELECT A.COD_NIVE, A.NUM_INGR_PERI
      INTO lv_NivelCampania, pnNumIngreCampa
        FROM COM_HISTO_VARIA_EJCAM A
       WHERE A.COD_PAIS = psCodPais
         AND A.COD_MARC = psCodMarca
         AND A.COD_CANA = psCodCanal
         AND A.COD_EJEC = psCodEjecutiva
         AND A.COD_CAMP = psCodPeriodo;
      RETURN lv_NivelCampania;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_NIVEL_CAMPA: ' ||
                            ls_sqlerrm||' - '|| psCodEjecutiva);
    RETURN '';

END COM_FN_OBTIE_NIVEL_CAMPA;

/***************************************************************************
Descripcion       :Recurpera numero de Campañas sin nivel en en el tramo
                   de lo contrario Error
Parametros        :
                   psCodCalificacion Código Calificacion

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_CALIF_COMOD( psCodCalifcacion VARCHAR2
                                   ) RETURN NUMBER IS


lv_comodin NUMBER;

BEGIN
      SELECT A.NUM_COMO
      INTO lv_comodin
        FROM COM_COMIS_CALIF_CABEC A
       WHERE A.COD_CALI = psCodCalifcacion;

       RETURN lv_comodin;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_CALIF_COMOD: ' ||
                            ls_sqlerrm);
    RETURN '';

END COM_FN_OBTIE_CALIF_COMOD;

/***************************************************************************
Descripcion       :Recurpera fecha de facturacion de la seccion de un periodo

Parametros        :
                   psOidPeriodo oid del periodo
                   psOidZona    oid de la Zona
                   psOidREgion  odi de la Region

Fecha Creacion    : 26/06/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_FECHA_FACTU( pnIdPais NUMBER,
                                   psOidPeriodo NUMBER,
                                   psOidZona NUMBER,
                                   psOidRegion NUMBER) RETURN DATE IS

 CURSOR c_region_zona is
        SELECT ZZ.OID_ZONA
         FROM ZON_REGIO ZR, ZON_ZONA ZZ
         WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
           AND ZR.OID_REGI = psOidRegion;


lv_fec_inic Date;
lv_exist BOOLEAN;

BEGIN
      SELECT A.Fec_Inic
        INTO lv_fec_inic
        FROM CRA_CRONO A, CRA_ACTIV B
       WHERE B.PAIS_OID_PAIS = pnIdPais
         AND A.ZZON_OID_ZONA = psOidZona
         AND A.PERD_OID_PERI = psOidPeriodo
         AND A.CACT_OID_ACTI = B.OID_ACTI
         AND B.COD_ACTI = 'FA';

       RETURN lv_fec_inic;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
         FOR fila IN c_region_zona LOOP
             BEGIN
                   SELECT A.Fec_Inic
                     INTO lv_fec_inic
                     FROM CRA_CRONO A, CRA_ACTIV B
                    WHERE B.PAIS_OID_PAIS = pnIdPais
                      AND A.ZZON_OID_ZONA = fila.oid_zona
                      AND A.PERD_OID_PERI = psOidPeriodo
                      AND A.CACT_OID_ACTI = B.OID_ACTI
                      AND B.COD_ACTI = 'FA';
                      RETURN lv_fec_inic;
             EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   lv_exist:=false;
              END;
         END LOOP;
          RAISE_APPLICATION_ERROR(-20123, 'No se encontro ninguna zona activa para esta Region '|| psOidRegion || psOidZona||psOidPeriodo);

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_CALIF_COMOD: ' ||
                            ls_sqlerrm);
    RETURN '';

END COM_FN_OBTIE_FECHA_FACTU;

/***************************************************************************
Descripcion       : verificar si la factura se pago
Parametros        :
                   subp_oid_subp_ulti    subp_oid_subp_ulti

Fecha Creacion    : 03/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_EXIST_PAGO(subp_oid_subp_ulti NUMBER
                           )RETURN BOOLEAN IS

  lv_cod_comi COM_NIVEL.COD_NIVE%TYPE;

BEGIN
  SELECT C.COD_COMI
    INTO lv_cod_comi
    FROM CCC_PROCE A,
         CCC_SUBPR B,
         COM_COMIS_SUBPR_PAGO C
   WHERE
         B.OID_SUBP = subp_oid_subp_ulti
         AND A.OID_PROC = B.CCPR_OID_PROC
         AND B.COD_SUBP = C.COD_SUBP
         AND C.COD_PROC = A.COD_PROC
         AND C.COD_SUBP = B.COD_SUBP
         AND C.COD_COMI = '02'
         AND C.TIP_COMI = 'P';
  RETURN TRUE;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN FALSE;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_EXIST_PAGO: ' || ls_sqlerrm);
    RETURN NULL;

END COM_FN_EXIST_PAGO;

/***************************************************************************
Descripcion       : verificar si la si exisiste pago
Parametros        :
                   subp_oid_subp_ulti    subp_oid_subp_ulti

Fecha Creacion    : 03/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_EXIST_RECLA(subp_oid_subp_ulti NUMBER
                           )RETURN BOOLEAN IS

  lv_cod_comi COM_NIVEL.COD_NIVE%TYPE;

BEGIN
  SELECT C.COD_COMI
    INTO lv_cod_comi
    FROM CCC_PROCE A, CCC_SUBPR B, COM_COMIS_SUBPR_PAGO C
   WHERE
         B.OID_SUBP = subp_oid_subp_ulti
         AND A.OID_PROC = B.CCPR_OID_PROC
         AND B.COD_SUBP = C.COD_SUBP
         AND C.COD_PROC = A.COD_PROC
         AND C.COD_SUBP = B.COD_SUBP
         AND C.COD_COMI = '02'
         AND C.TIP_COMI = 'R';
  RETURN TRUE;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN FALSE;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_EXIST_RECLA: ' || ls_sqlerrm);
    RETURN NULL;

END COM_FN_EXIST_RECLA;

/***************************************************************************
Descripcion       : Obtiene el porcentaje de comision

Parametros        : psCodPais     Codigo de pais
                    psNivel       Codigo de Nivel
                    psCodPorce    Codigo de Porcentaje
                    psMontoPedido monto del pedido

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_PORCE_COMIS(psCodPais VARCHAR2,
                                  psNivel VARCHAR2,
                                  psCodPorc VARCHAR2,
                                  psMontoPedido NUMBER
                           )RETURN NUMBER IS

lv_porcentajeComision NUMBER;

BEGIN
        SELECT A.VAL_PORC_COMI
          INTO lv_porcentajeComision
          FROM COM_PORCE_COMIS_DETAL A
         WHERE A.COD_PAIS = psCodPais
           AND A.COD_NIVE = psNivel
           AND A.COD_PORC = psCodPorc
           AND psMontoPedido >= A.IMP_MONT_DESD
           AND psMontoPedido <= A.IMP_MONT_HAST;

        RETURN lv_porcentajeComision;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 0;
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_PORCE_COMIS: ' || ls_sqlerrm);
    RETURN NULL;

END COM_FN_OBTIE_PORCE_COMIS;

/***************************************************************************
Descripcion       : Obtiene el codigo de porcentaje comision
                    del periodo de proceso

Parametros        :
                    psCodPais   Codigo Pais
                    psCodMarca  Codigo Marca
                    psCodCanal  Codigo Cana,
                    psCodCampania Codigo de la campaña de proceso

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_CODIG_PORCE_COMIS(psCodPais VARCHAR2,
                                        psCodMarca VARCHAR2,
                                        psCodCanal VARCHAR2,
                                        psCodTipoComisionista VARCHAR2,
                                        psCodCampania VARCHAR2
                           )RETURN VARCHAR2 IS

lv_codPorcentaje COM_PORCE_COMIS.COD_PORC%TYPE ;

BEGIN
        SELECT A.Cod_Porc
          INTO lv_codPorcentaje
          FROM COM_PORCE_COMIS A
         WHERE A.COD_PAIS = psCodPais
           AND A.COD_MARC = psCodMarca
           AND A.COD_CANA = psCodCanal
           AND A.COD_TIPO_COMI = psCodTipoComisionista
           AND psCodCampania>= A.CAM_DESD
           AND (psCodCampania<=A.CAM_HAST OR A.CAM_HAST IS NULL);


        RETURN lv_codPorcentaje;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_CODIG_PORCE_COMIS: ' || ls_sqlerrm);
    RETURN NULL;

END COM_FN_OBTIE_CODIG_PORCE_COMIS;

/***************************************************************************
Descripcion       : Obtiene el la tolerancia de recuperacion de la comision

Parametros        :
                    psCodPais   Codigo Pais
                    psCodMarca  Codigo Marca
                    psCodCanal  Codigo Cana,
                    psCodComision Codigo de la comision

Fecha Creacion    : 09/07/2008
Autor             : Leonardo Lizana
***************************************************************************/
FUNCTION COM_FN_OBTIE_TOLER_RECUP(psCodPais VARCHAR2,
                                        psCodMarca VARCHAR2,
                                        psCodCanal VARCHAR2,
                                        psCodComision VARCHAR2
                           )RETURN NUMBER IS

l_toleraciaRecuperacion COM_DATOS_COMIS.VAL_TOLE_RECU%TYPE ;

BEGIN
        SELECT A.VAL_TOLE_RECU
          INTO l_toleraciaRecuperacion
          FROM COM_DATOS_COMIS A
         WHERE A.COD_PAIS = psCodPais
           AND A.COD_MARC = psCodMarca
           AND A.COD_CANA = psCodCanal
           AND A.COD_COMI = psCodComision;

        RETURN l_toleraciaRecuperacion;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR COM_FN_OBTIE_TOLER_RECUP: ' || ls_sqlerrm);
    RETURN NULL;

END COM_FN_OBTIE_TOLER_RECUP;

/***************************************************************************
Descripcion       : Retorna 1 si hay bono registrado para ese periodo, caso contrario 0
Parametros        :
                    psCodPais   	Codigo Pais
                    psCodPeriodo  Codigo Periodo
Fecha Creacion    : 09/06/2009
Autor             : Sergio Buchelli
***************************************************************************/
FUNCTION COM_FN_DEVUE_HAY_BONO(psCodPais 		VARCHAR2,
                               psCodPeriodo VARCHAR2
                           )RETURN NUMBER IS
lnNumReg NUMBER;
BEGIN
        SELECT COUNT(1)
          INTO lnNumReg
          FROM COM_BONOS_CABEC A
         WHERE A.COD_PAIS = psCodPais
           AND TO_NUMBER(psCodPeriodo) >= TO_NUMBER(A.CAM_INIC)
		   AND ((TO_NUMBER(psCodPeriodo) <=TO_NUMBER(A.CAM_FINA)) OR A.CAM_FINA IS NULL);

		IF(lnNumReg>0) THEN
        	RETURN 1;
        ELSE
        	RETURN 0;
        END IF;

EXCEPTION
  WHEN OTHERS THEN
       RETURN 0;
END COM_FN_DEVUE_HAY_BONO;

/***************************************************************************
Descripcion       : Ejecuta el proceso de reciperacion de comisiones perdidas de G.Z
Parametros        :  psCodPais  	 Codigo Pais,
                     psCodMarca 	 Codigo Marca,
				     psCodCanal 	 Codigo Canal,
				     psCodComision  Codigo Comision ,
				     psAnhoProceso   Anho proceso,
				     psRango         rango (01-06,07-12,13-18),
				     psTipoComision  tipo comiison 0:Recuperacion 1:Actividad
Fecha Creacion    : 18/06/2009
Autor             : Sergio Buchelli
***************************************************************************/
PROCEDURE COM_PR_COMIS_RECUP_PERDI(psCodPais  	 VARCHAR2,
                               	  psCodMarca 	 VARCHAR2,
								  psCodCanal 	 VARCHAR2,
								  psCodComision  VARCHAR2,
								  psAnhoProceso  VARCHAR2,
								  psRango        VARCHAR2,
								  psTipoComision VARCHAR2,--0:Recuperacion 1:Actividad
								  psUsuarioLogin Varchar2,
                  psTipoGerente VARCHAR2) IS
  lsCodPeriInicial            seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriFinal              seg_perio_corpo.cod_peri%TYPE;

  regParamComiPerdidas        COM_COMIS_PARAM_PERDI%ROWTYPE;
  /*se obtine las zonas que se encuntran en las 6 campanhas y q exista importe en tramo1 =0*/
  CURSOR c_comisPerdidas(psCodPeriInicial VARCHAR2,psCodPeriFinal VARCHAR2) IS
		SELECT A.COD_COMI,
			   A.COD_REGI,
			   A.COD_ZONA,
			   NVL(ROUND(SUM(A.IMP_PAGO_ANTE_LIMI)/SUM(A.IMP_NETO_SIN_RECL),4)*100,0) AS PROM_POR_RECU,
			   NVL(ROUND(SUM(A.VAL_PORC_ACTI)/6,2),0) AS PROM_POR_ACTI
		FROM COM_COMIS_PERIO_CALCU_ZONA A
		WHERE A.COD_COMI=psCodComision
 			AND TO_NUMBER(A.PERI_COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicial) AND TO_NUMBER(psCodPeriFinal)
 			AND EXISTS(
			 	 		SELECT NULL
						FROM COM_COMIS_PERIO_CALCU_ZONA X
						WHERE X.COD_COMI=psCodComision
						 AND TO_NUMBER(X.PERI_COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicial) AND TO_NUMBER(psCodPeriFinal)
						  AND X.COD_ZONA=A.COD_ZONA
						   AND X.IMP_COMI_TRA1=0 )
		GROUP BY  A.COD_COMI,A.COD_REGI,A.COD_ZONA
		HAVING COUNT(1) = (TO_NUMBER(psCodPeriFinal)-TO_NUMBER(psCodPeriInicial) + 1)
		ORDER BY 1,2,3;

    /*se obtine las zonas que se encuntran en las 6 campanhas y q exista importe en tramo1 =0* para REGIONES */
  CURSOR c_comisPerdidas_region(psCodPeriInicialR VARCHAR2,psCodPeriFinalR VARCHAR2) IS
		SELECT A.COD_COMI,
			   A.COD_REGI,
			   NVL(ROUND(SUM(A.IMP_MONT_PAGA_TRA1)/SUM(A.IMP_MONT_VENT),4)*100,0) AS PROM_POR_RECU
		FROM COM_COMIS_PERIO_CALCU_REGIO A
		WHERE A.COD_COMI=psCodComision
 			AND TO_NUMBER(A.COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicialR) AND TO_NUMBER(psCodPeriFinalR)
 			AND EXISTS(
			 	 		SELECT NULL
						FROM COM_COMIS_PERIO_CALCU_REGIO X
						WHERE X.COD_COMI=psCodComision
						 AND TO_NUMBER(X.COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicialR) AND TO_NUMBER(psCodPeriFinalR)
						  AND X.COD_REGI=A.COD_REGI
						   AND X.IMP_MONT_COM1=0 )
		GROUP BY  A.COD_COMI,A.COD_REGI
		HAVING COUNT(1) = (TO_NUMBER(psCodPeriFinalR)-TO_NUMBER(psCodPeriInicialR) + 1)
		ORDER BY 1,2,3;
      --- Para zonas
	   TYPE comiPerdidasRec IS RECORD(
	     cod_comi	      COM_COMIS_PERIO_CALCU_ZONA.COD_COMI%TYPE,
		   cod_regi	      ZON_REGIO.COD_REGI%TYPE,
		   cod_zona       ZON_ZONA.COD_ZONA%TYPE,
       prom_por_recu  COM_COMIS_PERIO_CALCU_ZONA.POR_RECU%TYPE,
		   prom_por_acti  COM_COMIS_PERIO_CALCU_ZONA.VAL_PORC_ACTI%TYPE
     );

 	   TYPE ComiPerdidasTab IS TABLE OF comiPerdidasRec;
	   comiPerdidasRecord ComiPerdidasTab;

     -- Para Regiones

     TYPE comiPerdidasRegionRec IS RECORD(
	     cod_comi	      COM_COMIS_PERIO_CALCU_REGIO.COD_COMI%TYPE,
		   cod_regi	      ZON_REGIO.COD_REGI%TYPE,
		   prom_por_recu  COM_COMIS_PERIO_CALCU_REGIO.POR_RECU_TRA1%TYPE
     );

     TYPE ComiPerdidasRegionTab IS TABLE OF comiPerdidasRegionRec;
	   comiPerdidasRegionRecord ComiPerdidasRegionTab;

  /* se recuperan los periodos en que las zonas tiene importe cero*/
   CURSOR c_comisPerdidasTramo1(psCodigoRegion VARCHAR2 ,psCodigoZona VARCHAR2,psCodPeriInicial VARCHAR2, psCodPeriFinal VARCHAR2)
   IS
   SELECT A.*
	 FROM COM_COMIS_PERIO_CALCU_ZONA A
	 WHERE A.COD_COMI=psCodComision
	 AND TO_NUMBER(A.PERI_COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicial) AND TO_NUMBER(psCodPeriFinal)
	 AND A.COD_REGI = psCodigoRegion
	 AND A.COD_ZONA = psCodigoZona
	 AND A.IMP_COMI_TRA1=0;

   /* se recuperan los periodos en que las zonas tiene importe cero para Region*/
   CURSOR c_comisPerdidasTramo1_Region(psCodigoRegion VARCHAR2 ,psCodPeriInicial VARCHAR2, psCodPeriFinal VARCHAR2)
   IS
   SELECT A.*
	 FROM COM_COMIS_PERIO_CALCU_REGIO A
	 WHERE A.COD_COMI=psCodComision
	 AND TO_NUMBER(A.COD_PERI) BETWEEN TO_NUMBER(psCodPeriInicial) AND TO_NUMBER(psCodPeriFinal)
	 AND A.COD_REGI = psCodigoRegion
	 AND A.IMP_MONT_COM1=0;

BEGIN

  --OBTENEMOS LOS PARAMETROS GENERALES DE COMISIONES PERDIDAS
   SELECT *
   INTO regParamComiPerdidas
   FROM  COM_COMIS_PARAM_PERDI A
   WHERE A.COD_PAIS = psCodPais
   AND   A.TIP_COMI = psTipoGerente;


 --obtenemos el perido inicial y final de acuerdo al anho que se quiere procesar
  VEN_PKG_REPOR.VEN_PR_DEVUE_RANGO_CODI_PERI(psRango,psAnhoProceso,lsCodPeriInicial,lsCodPeriFinal);
  -- Para Zonas
  IF psTipoGerente = 'Z' THEN

  -- SE RECORRE LAS ZONAS QUE SE ENCONTARON EN LAS 6 CAMPANHAS Y TIENEN POR LO MENOS EN UNA CAMPANHA IMPOR DE TRAMO1 EN CERO
  	OPEN C_COMISPERDIDAS(lsCodPeriInicial,lsCodPeriFinal);
		  LOOP
		      FETCH C_COMISPERDIDAS BULK COLLECT INTO comiPerdidasRecord LIMIT W_FILAS;
		       IF comiPerdidasRecord.COUNT > 0 THEN
		          FOR i IN comiPerdidasRecord.FIRST .. comiPerdidasRecord.LAST LOOP

					 -- se recorre las zonas obtiendo los periodos donde el tramo es cero
					IF(psTipoComision='0')THEN --recuperacion
					  FOR cursorComisPerdidas IN c_comisPerdidasTramo1(comiPerdidasRecord(i).cod_regi,
					  	  					  	 					   comiPerdidasRecord(i).cod_zona,
																	   lsCodPeriInicial,
																	   lsCodPeriFinal) LOOP

					    IF( comiPerdidasRecord(i).prom_por_recu >=  regParamComiPerdidas.VAL_PROM_RECU ) THEN

						   IF((regParamComiPerdidas.VAL_PORC_RECU_CAMP >0 AND
						   			cursorComisPerdidas.POR_RECU >= regParamComiPerdidas.VAL_PORC_RECU_CAMP)
							  OR(regParamComiPerdidas.VAL_PORC_RECU_CAMP=0)
							  OR(regParamComiPerdidas.VAL_PORC_RECU_CAMP IS NULL)) THEN
									--se calcula comision perdida
								UPDATE COM_COMIS_PERIO_CALCU_ZONA A
								SET A.VAL_COMI_PERD =(cursorComisPerdidas.IMP_PAGO_ANTE_LIMI*regParamComiPerdidas.VAL_PORC_COMI)/100,
									A.VAL_PROM_RECU = comiPerdidasRecord(i).prom_por_recu,
									A.USU_ACTU_COMI_PERD = psUsuarioLogin,
									A.FEC_ACTU_COMI_PERD = SYSDATE
								WHERE A.COD_COMI=cursorComisPerdidas.COD_COMI
								  AND A.PERI_COD_PERI=cursorComisPerdidas.PERI_COD_PERI
								    AND A.COD_REGI = cursorComisPerdidas.COD_REGI
									   AND A.COD_ZONA=cursorComisPerdidas.COD_ZONA;

						   END IF;

						 END IF;

					  END LOOP;--FIN DE RECORRER LOS PERIODOS DE TRAMO CERO
					END IF;--FIN DE RECUPERACION

					IF(psTipoComision='1')THEN --Actividad
					  FOR cursorComisPerdidas IN c_comisPerdidasTramo1(comiPerdidasRecord(i).cod_regi,
					  	  					  	 					   comiPerdidasRecord(i).cod_zona,
																	   lsCodPeriInicial,
																	   lsCodPeriFinal) LOOP

					     IF( comiPerdidasRecord(i).prom_por_acti >= regParamComiPerdidas.VAL_PROM_ACTI ) THEN

						   IF((regParamComiPerdidas.VAL_PORC_ACTI_CAMP >0 AND
						   			cursorComisPerdidas.VAL_PORC_ACTI >= regParamComiPerdidas.VAL_PORC_ACTI_CAMP)
							  OR (regParamComiPerdidas.VAL_PORC_ACTI_CAMP=0)
							  OR (regParamComiPerdidas.VAL_PORC_ACTI_CAMP IS NULL)) THEN
									--se calcula comision perdida
								UPDATE COM_COMIS_PERIO_CALCU_ZONA A
								SET A.VAL_COMI_PERD =(cursorComisPerdidas.IMP_PAGO_ANTE_LIMI*cursorComisPerdidas.VAL_PORC_COMI_TRA1)/100,
									A.VAL_PROM_ACTI = comiPerdidasRecord(i).prom_por_acti,
									A.USU_ACTU_COMI_PERD = psUsuarioLogin,
									A.FEC_ACTU_COMI_PERD = SYSDATE
								WHERE A.COD_COMI=cursorComisPerdidas.COD_COMI
								  AND A.PERI_COD_PERI=cursorComisPerdidas.PERI_COD_PERI
								    AND A.COD_REGI = cursorComisPerdidas.COD_REGI
									   AND A.COD_ZONA=cursorComisPerdidas.COD_ZONA;

						   END IF;

						 END IF;

					  END LOOP;--FIN DE RECORRER LOS PERIODOS DE TRAMO CERO
					END IF;--FIN DE Actividad

		          END LOOP;--FIN DE RECORRER LAS ZONAS QCUMPLEN LAS 6 CAMPANHAS Y EXISTEN TRAMOS CERO
		       END IF;
		      EXIT WHEN C_COMISPERDIDAS%NOTFOUND;
		   END LOOP;
		 CLOSE C_COMISPERDIDAS;
  END IF;


  -- Para Region
  IF psTipoGerente = 'R' THEN
    -- SE RECORRE LAS ZONAS QUE SE ENCONTARON EN LAS 6 CAMPANHAS Y TIENEN POR LO MENOS EN UNA CAMPANHA IMPOR DE TRAMO1 EN CERO
  	OPEN c_comisPerdidas_region(lsCodPeriInicial,lsCodPeriFinal);
    LOOP
        FETCH c_comisPerdidas_region BULK COLLECT INTO comiPerdidasRegionRecord LIMIT W_FILAS;

        IF comiPerdidasRegionRecord.COUNT > 0 THEN
           FOR i IN comiPerdidasRegionRecord.FIRST .. comiPerdidasRegionRecord.LAST LOOP
               -- se recorre las zonas obtiendo los periodos donde el tramo es cero
					     IF(psTipoComision='0')THEN --recuperacion
                  FOR cursorComisPerdidasRegion IN c_comisPerdidasTramo1_Region(comiPerdidasRegionRecord(i).cod_regi,
                                                                                lsCodPeriInicial,lsCodPeriFinal) LOOP
                      IF(comiPerdidasRegionRecord(i).prom_por_recu >=  regParamComiPerdidas.VAL_PROM_RECU) THEN
                          IF((regParamComiPerdidas.VAL_PORC_RECU_CAMP >0 AND cursorComisPerdidasRegion.POR_RECU_TRA1 >= regParamComiPerdidas.VAL_PORC_RECU_CAMP)
              							  OR(regParamComiPerdidas.VAL_PORC_RECU_CAMP=0)
              							  OR(regParamComiPerdidas.VAL_PORC_RECU_CAMP IS NULL)) THEN

                              --se calcula comision perdida
              								UPDATE COM_COMIS_PERIO_CALCU_REGIO A
              								SET A.VAL_COMI_PERD =(cursorComisPerdidasRegion.IMP_MONT_PAGA_TRA1*regParamComiPerdidas.VAL_PORC_COMI)/100,
              									  A.VAL_PROM_RECU = comiPerdidasRegionRecord(i).prom_por_recu
              									  --A.USU_ACTU_COMI_PERD = psUsuarioLogin
              									  --A.FEC_ACTU_COMI_PERD = SYSDATE
              								WHERE A.COD_COMI=cursorComisPerdidasRegion.COD_COMI
              								AND A.COD_PERI=cursorComisPerdidasRegion.COD_PERI
              								AND A.COD_REGI = cursorComisPerdidasRegion.COD_REGI;

                          END IF;
                      END IF;
                  END LOOP;
               END IF;
           END LOOP;
        END IF;

        EXIT WHEN c_comisPerdidas_region%NOTFOUND;
    END LOOP;
    CLOSE c_comisPerdidas_region;
  END IF;
  EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm, 1, 250);
  RAISE_APPLICATION_ERROR(-20123,'ERROR COM_PR_COMIS_RECUP_PERDI : ' || ls_sqlerrm);
  END COM_PR_COMIS_RECUP_PERDI;

  /***************************************************************************
  Descripcion       : Verifica el total de ingresos bicampañas realizadas
                      y verificando si existe configurado nivel por region y zona,
                      region, o region y zona nulos
  Fecha Creacion    : 19/04/2010
  Autor             : Jesse Rios
  ***************************************************************************/
  FUNCTION COM_FN_VERIF_NIVEL_INGRE(pscodpais         VARCHAR2,
                                          pscodcalificacion VARCHAR2,
                                          pnprombicampanas  NUMBER,
                                          pscodregion       VARCHAR2,
                                          pscodzona         VARCHAR2) RETURN NUMBER IS

   indContIngre NUMBER := 0;

   BEGIN

        SELECT COUNT(1)
        INTO indContIngre
        FROM com_comis_calif_detal a
        WHERE a.cod_pais = psCodPais
        AND cod_cali = pscodcalificacion
        AND cod_regi = pscodregion
        AND cod_zona = pscodzona
        AND a.num_ingr_desd <= pnprombicampanas;

        IF indContIngre = 0 THEN

            SELECT COUNT(1)
            INTO indContIngre
            FROM com_comis_calif_detal a
            WHERE a.cod_pais = psCodPais
            AND cod_cali = pscodcalificacion
            AND cod_regi = pscodregion
            AND cod_zona IS NULL
            AND a.num_ingr_desd <= pnprombicampanas;

        END IF;

        IF indContIngre = 0 THEN

            SELECT COUNT(1)
            INTO indContIngre
            FROM com_comis_calif_detal a
            WHERE a.cod_pais = psCodPais
            AND cod_cali = pscodcalificacion
            AND cod_regi IS NULL
            AND cod_zona IS NULL
            AND a.num_ingr_desd <= pnprombicampanas;

        END IF;

        RETURN indContIngre;
   EXCEPTION
   WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm, 1, 250);
   RAISE_APPLICATION_ERROR(-20123,'ERROR COM_FN_VERIF_NIVEL_INGRE: ' || ls_sqlerrm);

   RETURN 0;
  END COM_FN_VERIF_NIVEL_INGRE;

/***************************************************************************
  Descripcion       : Verifica el total de numero de pedidos realizados
                      y verificando si existe configurado nivel por region y zona,
                      region, o region y zona nulos
  Fecha Creacion    : 19/04/2010
  Autor             : Jesse Rios
  ***************************************************************************/
  FUNCTION COM_FN_VERIF_NIVEL_PEDID(pscodpais         VARCHAR2,
                                          pscodcalificacion VARCHAR2,
                                          pnPromPedidos     NUMBER,
                                          pscodregion       VARCHAR2,
                                          pscodzona         VARCHAR2) RETURN NUMBER IS

   indContPedido NUMBER := 0;

  BEGIN
        SELECT COUNT(1)
        INTO indContPedido
        FROM com_comis_calif_detal a
        WHERE a.cod_pais = psCodPais
        AND cod_cali = pscodcalificacion
        AND cod_regi = pscodregion
        AND cod_zona = pscodzona
        AND a.NUM_PEDI_DESD <= pnPromPedidos;

        IF indContPedido = 0 THEN

            SELECT COUNT(1)
            INTO indContPedido
            FROM com_comis_calif_detal a
            WHERE a.cod_pais = psCodPais
            AND cod_cali = pscodcalificacion
            AND cod_regi = pscodregion
            AND cod_zona IS NULL
            AND a.NUM_PEDI_DESD <= pnPromPedidos;

        END IF;

        IF indContPedido = 0 THEN

            SELECT COUNT(1)
            INTO indContPedido
            FROM com_comis_calif_detal a
            WHERE a.cod_pais = psCodPais
            AND cod_cali = pscodcalificacion
            AND cod_regi IS NULL
            AND cod_zona IS NULL
            AND a.NUM_PEDI_DESD <= pnPromPedidos;

        END IF;

        RETURN indContPedido;
  EXCEPTION
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm, 1, 250);
  RAISE_APPLICATION_ERROR(-20123,'ERROR COM_FN_VERIF_NIVEL_PEDID: ' || ls_sqlerrm);

  RETURN 0;
  END COM_FN_VERIF_NIVEL_PEDID;

  /***************************************************************************
  Descripcion       : Graba en la tabla historia de comisiones para luego hacer
                      la eliminacion respectiva de las comisiones encontradas por
                      la busqueda en base a los criterios seleccionados en pantalla

  Fecha Creacion    : 27/09/2010
  Autor             : Jesse Rios
  ***************************************************************************/
   PROCEDURE COM_PR_ELIMI_COMIS_RECUP(psCodPais      VARCHAR2,
                                      psCodMarca     VARCHAR2,
                                      psCodCanal     VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      psCodPeriodo   VARCHAR2,
                                      psTipoComision VARCHAR2,
                                      psMensajeError OUT VARCHAR2)
   IS
     lsCodRang VARCHAR2(2);
     lsCodAnioInic VARCHAR2(4);
     lsMaxCamp VARCHAR2(6);
     lnIdMaxCamp NUMBER;
     lnIdPeriodoActual NUMBER;
     LE_EXISTE_PROC_CAMP_POST EXCEPTION;

   BEGIN
        SELECT T.COD_RANG , T.COD_ANIO_INIC
        INTO   lsCodRang,lsCodAnioInic
        FROM COM_COMIS_EJCAL_CABEC t
        WHERE T.COD_PAIS = psCodPais
        AND   T.COD_MARC = psCodMarca
        AND   T.COD_CANA = psCodCanal
        AND   T.COD_COMI = psCodComision
        AND   T.COD_TIPO_COMI = psTipoComision
        AND   T.COD_CAMP = psCodPeriodo
        AND ROWNUM = 1;

        --Obtenemos la campaña maxima de la comision
        SELECT MAX(T.COD_CAMP)
        INTO   lsMaxCamp
        FROM COM_COMIS_EJCAL_CABEC t
        WHERE T.COD_PAIS = psCodPais
        AND   T.COD_MARC = psCodMarca
        AND   T.COD_CANA = psCodCanal
        AND   T.COD_COMI = psCodComision
        AND   T.COD_TIPO_COMI = psTipoComision;
        -- --

        -- Obtenemos los OID's de las campañas  y las comparamos--
        lnIdMaxCamp := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsMaxCamp);
        lnIdPeriodoActual := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

        IF lnIdMaxCamp > lnIdPeriodoActual THEN
            RAISE LE_EXISTE_PROC_CAMP_POST;
        END IF;
        -- --

        UPDATE COM_HISTO_VARIA_EJETR_CABEC HI
        SET HI.NUM_USOS_COMO = hi.NUM_USOS_COMO - 1
        WHERE HI.COD_RANG = lsCodRang
        AND   HI.NUM_ANIO_INIC = lsCodAnioInic
        AND   HI.COD_EJEC IN ( SELECT CA.COD_EJEC
                               FROM COM_COMIS_EJCAL_CABEC CA
                               WHERE CA.COD_CAMP = psCodPeriodo
                               AND IND_COMO = 'SI'
                               AND ca.COD_COMI = psCodComision );

        UPDATE COM_HISTO_VARIA_EJETR_CABEC HI2
        SET HI2.NUM_USOS_COMO_RECU = HI2.NUM_USOS_COMO_RECU - 1
        WHERE HI2.COD_RANG = lsCodRang
        AND   HI2.NUM_ANIO_INIC = lsCodAnioInic
        AND   HI2.COD_EJEC IN ( SELECT CA.COD_EJEC
                                FROM COM_COMIS_EJCAL_CABEC CA
                                WHERE CA.COD_CAMP = psCodPeriodo
                                AND   CA.IND_COMO_RECU = 'SI'
                                AND   CA.COD_COMI = psCodComision);

        DELETE FROM  COM_COMIS_EJCAL_DETAL DE
        WHERE DE.COD_CAMP = psCodPeriodo
        AND   DE.COD_COMI = psCodComision;

        DELETE FROM COM_COMIS_EJCAL_CABEC  CA
        WHERE CA.COD_CAMP =  psCodPeriodo
        AND   CA.COD_COMI = psCodComision;


   EXCEPTION
   WHEN NO_DATA_FOUND THEN
        psMensajeError := 'procesoCOMEliminarComisionRecuperacionForm.noComisiones';
   WHEN LE_EXISTE_PROC_CAMP_POST THEN
        psMensajeError := 'procesoCOMEliminarComisionRecuperacionForm.existeCampanyaPosterior';
   WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm, 1, 250);
   RAISE_APPLICATION_ERROR(-20123,'ERROR COM_PR_ELIMI_COMIS_RECUP: ' || ls_sqlerrm);
   END COM_PR_ELIMI_COMIS_RECUP;


/***************************************************************************
  Descripcion       : Calculo de comision de venta
  Fecha Creacion    : 11/01/2013
  Autor             : Giovanni Ascarza
  ***************************************************************************/
   PROCEDURE COM_PR_CALCU_COMIS_VENT(psCodPais      VARCHAR2,
                                     psCodComision  VARCHAR2,
                                     psOidComision  VARCHAR2,
                                     psCodPeriodo   VARCHAR2) IS

  v_oid_camp varchar2(100);
  v_oid_sol_pais varchar2(100);
  lnContadorRegion number;
  TYPE  my_curs_type IS REF CURSOR;
  c1 my_curs_type;
  c2 my_curs_type;

    TYPE rec_result IS RECORD
     (
      oid_campana         varchar2(10),
      cod_regi            zon_regio.cod_regi%type,
      val_cata            PED_SOLIC_POSIC.val_prec_cata_tota_loca%type,
      val_cata_est        number(12,2)
      );

     TYPE r_result  IS TABLE OF rec_result ;
     v_result1 r_result;
     v_result2 r_result;
     lnIdPais  SEG_PAIS.OID_PAIS%TYPE;

   BEGIN

    lnIdPais  := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
    select SP.OID_TIPO_SOLI_PAIS
      into v_oid_sol_pais
      from PED_TIPO_SOLIC TS, PED_TIPO_SOLIC_PAIS SP
     WHERE TS.OID_TIPO_SOLI = SP.TSOL_OID_TIPO_SOLI
       AND TS.COD_TIPO_SOLI = 'SOC';

    v_oid_camp := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodPeriodo);
    lnContadorRegion := 0 ;
    BEGIN
      SELECT count(1)
      into lnContadorRegion
      FROM COM_COMIS_CLIEN X
      WHERE X.COMI_OID_COMI = psOidComision
       AND X.ZORG_OID_REGI IS NOT NULL;
    EXCEPTION
    WHEN OTHERS THEN
        lnContadorRegion := 0;
    END;

--    Caso 1
    IF lnContadorRegion = 0 THEN
      SELECT v_oid_camp oid_campana,
                     zr.COD_REGI,
               sum(sd.val_prec_neto_tota_loca) val_cata,
              (
                  select sum(er.val_vene)
                  from INT_SAB_VENTA_PREVI_ZONA er
                  where er.pais_cod_pais = psCodPais
                  and er.cod_peri = psCodPeriodo
                  and er.cod_regi = zr.cod_regi
                  group by er.cod_regi
              ) val_cata_est
        BULK COLLECT INTO v_result1
        FROM PED_SOLIC_CABEC sc, PED_SOLIC_POSIC sd,
             PRE_OFERT_DETAL PRE,
             PRE_TIPO_OFERT TIP,
             SEG_CANAL C,
             zon_zona zz, zon_regio zr
        WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
        AND sc.PERD_OID_PERI = v_oid_camp
        AND sc.FEC_FACT IS NOT NULL
        AND sc.CLIE_OID_CLIE IN(
                  SELECT A.CLIE_OID_CLIE
                  FROM PED_SOLIC_CABEC A
                  WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                  AND A.PERD_OID_PERI = v_oid_camp
                  AND A.FEC_FACT IS NOT NULL
                  GROUP BY A.CLIE_OID_CLIE
                  HAVING COUNT(*) > 1
              )
        and sc.OID_SOLI_CABE = sd.SOCA_OID_SOLI_CABE
        and sc.ZZON_OID_ZONA = zz.OID_ZONA
        and zz.ZORG_OID_REGI = zr.OID_REGI
        AND SD.OFDE_OID_DETA_OFER = PRE.OID_DETA_OFER
        AND PRE.TOFE_OID_TIPO_OFER = TIP.OID_TIPO_OFER
        AND TIP.CANA_OID_CANA = C.OID_CANA
        AND TIP.VAL_FORM_VENT = '1'
        AND C.COD_CANA = 'VD'
        group by zr.COD_REGI;
    ELSE
       SELECT v_oid_camp oid_campana,
                     zr.COD_REGI,
               sum(sd.val_prec_neto_tota_loca) val_cata,
              (
                  select sum(er.val_vene)
                  from INT_SAB_VENTA_PREVI_ZONA er
                  where er.pais_cod_pais = psCodPais
                  and er.cod_peri = psCodPeriodo
                  and er.cod_regi = zr.cod_regi
                  group by er.cod_regi
              ) val_cata_est
        BULK COLLECT INTO v_result1
        FROM PED_SOLIC_CABEC sc, PED_SOLIC_POSIC sd,
             PRE_OFERT_DETAL PRE,
             PRE_TIPO_OFERT TIP,
             SEG_CANAL C,
             zon_zona zz, zon_regio zr,
             COM_COMIS_CLIEN X
        WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
        AND sc.PERD_OID_PERI = v_oid_camp
        AND sc.FEC_FACT IS NOT NULL
        AND sc.CLIE_OID_CLIE IN(
                  SELECT A.CLIE_OID_CLIE
                  FROM PED_SOLIC_CABEC A
                  WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                  AND A.PERD_OID_PERI = v_oid_camp
                  AND A.FEC_FACT IS NOT NULL
                  GROUP BY A.CLIE_OID_CLIE
                  HAVING COUNT(*) > 1
              )
        and sc.OID_SOLI_CABE = sd.SOCA_OID_SOLI_CABE
        and sc.ZZON_OID_ZONA = zz.OID_ZONA
        and zz.ZORG_OID_REGI = zr.OID_REGI
        and X.COMI_OID_COMI = psOidComision
        AND X.ZORG_OID_REGI = zz.ZORG_OID_REGI
        AND SD.OFDE_OID_DETA_OFER = PRE.OID_DETA_OFER
        AND PRE.TOFE_OID_TIPO_OFER = TIP.OID_TIPO_OFER
        AND TIP.CANA_OID_CANA = C.OID_CANA
        AND TIP.VAL_FORM_VENT = '1'
        AND C.COD_CANA = 'VD'
        group by zr.COD_REGI;

    END IF;


 IF v_result1.count > 0 THEN
        FOR i IN v_result1.first .. v_result1.last
        LOOP

      COM_PR_INSER_COMIS_VENT_REGIO ( psCodPeriodo,
                                v_result1(i).cod_regi,
                                v_result1(i).val_cata,
                                v_result1(i).val_cata_est);
        END LOOP;
  END IF;

-- Caso 2
IF lnContadorRegion = 0 THEN
    SELECT v_oid_camp cod_campana,
           zr.COD_REGI,
           NVL((select SUM(ac.VAL_MONT_FORM_CATA)
                   from ped_solic_cabec_acum2 ac, ped_solic_cabec X, zon_zona Y, zon_regio Z
                   where ac.perd_oid_peri = v_oid_camp
                   and ac.clie_oid_clie = X.clie_oid_clie
                   and X.tspa_oid_tipo_soli_pais = v_oid_sol_pais
                   and X.zzon_oid_zona = Y.oid_zona
                   and X.perd_oid_peri = v_oid_camp
                   and X.fec_fact is not null
                   and y.zorg_oid_regi = Z.oid_regi
                   and Z.cod_regi = zr.COD_REGI
                   and X.clie_oid_clie IN(
                              SELECT A.CLIE_OID_CLIE
                              FROM PED_SOLIC_CABEC A
                              WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                              AND A.PERD_OID_PERI = v_oid_camp
                              AND A.FEC_FACT IS NOT NULL
                              GROUP BY A.CLIE_OID_CLIE
                              HAVING COUNT(*) = 1
                          )
           ), 0) VAL_CATA,
                    (
                        select sum(er.val_vene)
                        from INT_SAB_VENTA_PREVI_ZONA er
                        where er.pais_cod_pais = psCodPais
                        and er.cod_peri = psCodPeriodo
                        and er.cod_regi = zr.cod_regi
                        group by er.cod_regi
                    ) val_cata_est
       BULK COLLECT INTO v_result2
      FROM PED_SOLIC_CABEC sc, zon_zona zz, zon_regio zr
      WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
      AND sc.PERD_OID_PERI = v_oid_camp
      AND sc.FEC_FACT IS NOT NULL
      AND sc.CLIE_OID_CLIE IN(
                              SELECT A.CLIE_OID_CLIE
                              FROM PED_SOLIC_CABEC A
                              WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                              AND A.PERD_OID_PERI = v_oid_camp
                              AND A.FEC_FACT IS NOT NULL
                              GROUP BY A.CLIE_OID_CLIE
                              HAVING COUNT(*) = 1
                          )
    and sc.ZZON_OID_ZONA = zz.OID_ZONA
    and zz.ZORG_OID_REGI = zr.OID_REGI
    group by zr.COD_REGI;
ELSE
    SELECT v_oid_camp cod_campana,
           zr.COD_REGI,
           NVL((select SUM(ac.VAL_MONT_FORM_CATA)
                   from ped_solic_cabec_acum2 ac, ped_solic_cabec X, zon_zona Y, zon_regio Z
                   where ac.perd_oid_peri = v_oid_camp
                   and ac.clie_oid_clie = X.clie_oid_clie
                   and X.tspa_oid_tipo_soli_pais = v_oid_sol_pais
                   and X.zzon_oid_zona = Y.oid_zona
                   and X.perd_oid_peri = v_oid_camp
                   and X.fec_fact is not null
                   and y.zorg_oid_regi = Z.oid_regi
                   and Z.cod_regi = zr.COD_REGI
                   and X.clie_oid_clie IN(
                              SELECT A.CLIE_OID_CLIE
                              FROM PED_SOLIC_CABEC A
                              WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                              AND A.PERD_OID_PERI = v_oid_camp
                              AND A.FEC_FACT IS NOT NULL
                              GROUP BY A.CLIE_OID_CLIE
                              HAVING COUNT(*) = 1
                          )
           ), 0) VAL_CATA,
                    (
                        select sum(er.val_vene)
                        from INT_SAB_VENTA_PREVI_ZONA er
                        where er.pais_cod_pais = psCodPais
                        and er.cod_peri = psCodPeriodo
                        and er.cod_regi = zr.cod_regi
                        group by er.cod_regi
                    ) val_cata_est
       BULK COLLECT INTO v_result2
      FROM PED_SOLIC_CABEC sc, zon_zona zz, zon_regio zr,
           COM_COMIS_CLIEN X
      WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
      AND sc.PERD_OID_PERI = v_oid_camp
      AND sc.FEC_FACT IS NOT NULL
      AND sc.CLIE_OID_CLIE IN(
                              SELECT A.CLIE_OID_CLIE
                              FROM PED_SOLIC_CABEC A
                              WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                              AND A.PERD_OID_PERI = v_oid_camp
                              AND A.FEC_FACT IS NOT NULL
                              GROUP BY A.CLIE_OID_CLIE
                              HAVING COUNT(*) = 1
                          )
    and sc.ZZON_OID_ZONA = zz.OID_ZONA
    and zz.ZORG_OID_REGI = zr.OID_REGI
    and X.COMI_OID_COMI = psOidComision
    AND X.ZORG_OID_REGI = zz.ZORG_OID_REGI
    group by zr.COD_REGI;
END IF;

 IF v_result2.count > 0 THEN
        FOR i IN v_result2.first .. v_result2.last
        LOOP
           IF v_result2(i).cod_regi = '03' THEN
              v_result2(i).cod_regi := '03';
           END IF;
           COM_PR_INSER_COMIS_VENT_REGIO ( psCodPeriodo,
                                v_result2(i).cod_regi,
                                v_result2(i).val_cata,
                                v_result2(i).val_cata_est);

        END LOOP;
  END IF;

  COM_PR_INSER_COMIS_VENT ( lnIdPais,
                            psCodPeriodo,
                            psCodComision,
                            v_oid_camp);

 EXCEPTION
  WHEN OTHERS THEN

    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_CALCU_COMIS_VENT: '||ls_sqlerrm);

   END;

/***************************************************************************
  Descripcion       : Insercion de comision de venta de Gerente de Region en una
                      tabla temporal
  Fecha Creacion    : 22/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT_REGIO (
          pscodPeri        varchar2,
          pscodRegi        VARCHAR2,
          pnvalCata        number ,
          pnvalCataEst     number) is


begin
   BEGIN
      insert into COM_TEMPO_CALCU_VENTA_REGIO(
                  cod_peri,
                  cod_regi,
                  val_cata,
                  val_cata_est
                  )
                  values(
                  pscodPeri,
                  pscodRegi,
                  pnvalCata,
                  pnvalCataEst);
   EXCEPTION
   WHEN DUP_VAL_ON_INDEX THEN
        UPDATE COM_TEMPO_CALCU_VENTA_REGIO
        SET val_cata = val_cata + pnvalCata
        WHERE cod_peri = pscodPeri
        AND cod_regi =  pscodRegi;
   END;

EXCEPTION
  WHEN OTHERS THEN

    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_INSER_COMIS_VENT: '||ls_sqlerrm);

   END;


/***************************************************************************
  Descripcion       : Insercion de comision de venta
  Fecha Creacion    : 21/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT ( pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2) is


  v_por_comi           COM_COMIS_ESCAL.Por_Comi%type := 0;
  v_num_nive           COM_COMIS_ESCAL.num_nive%type;
  v_porcentaje         number(12,2);
  v_mon_fijo_comi      COM_COMIS.mon_fijo_comi%type := 0;
  v_comi_obtenida      COM_COMIS_PERIO_CALCU_REGIO.IMP_MONT_COM1%type := 0;

  v_gerente varchar2(100) := ' ';
  v_nombre_geren varchar2(100) := ' ';
  v_conta number(12,2) := 0;

  CURSOR C_TEMPORAL  IS
        SELECT X.COD_REGI,
               X.VAL_CATA,
               X.VAL_CATA_EST
        FROM COM_TEMPO_CALCU_VENTA_REGIO X;

  TYPE temporalRec IS RECORD(
                  codRegi         COM_TEMPO_CALCU_VENTA_REGIO.COD_REGI%TYPE,
                  valCata         COM_TEMPO_CALCU_VENTA_REGIO.VAL_CATA%TYPE,
                  valCataEst      COM_TEMPO_CALCU_VENTA_REGIO.VAL_CATA_EST%TYPE
                 );

  TYPE temporalTab IS TABLE OF temporalRec;
  temporalRecord temporalTab;

begin
   DELETE FROM COM_COMIS_PERIO_CALCU_REGIO
   WHERE COD_PERI = psCodPeriodo
     AND COD_COMI = psCodComision
     AND IND_COMI = COMISION_RECUPERACION;

  OPEN C_TEMPORAL;
  LOOP
     FETCH C_TEMPORAL BULK COLLECT INTO temporalRecord LIMIT W_FILAS;
     IF temporalRecord.COUNT > 0 THEN
        FOR i IN temporalRecord.FIRST .. temporalRecord.LAST LOOP
            v_porcentaje := (temporalRecord(i).valCata / temporalRecord(i).valCataEst)*100;
            if v_porcentaje != 0 then
                BEGIN
            select cce.POR_COMI, cce.num_nive
              into v_por_comi, v_num_nive
                    from com_comis com, COM_COMIS_COBRA cco, COM_COMIS_ESCAL cce
                   where com.OID_COMI = cco.COMI_OID_COMI
                     and cco.OID_COMI_COBR = cce.OID_COMI_COBR
                     and com.COD_COMI = psCodComision
                     and cce.POR_RECU_INIC <= v_porcentaje
                     and cce.POR_RECU_FINA > v_porcentaje;

                EXCEPTION
                WHEN OTHERS THEN
                  v_por_comi := 0;
            v_num_nive := 0;
                END;
            end if;

            BEGIN
                select nvl(mon_fijo_comi,0)
                  into v_mon_fijo_comi
                  from com_comis com
                 where com.COD_COMI = psCodComision;

            EXCEPTION
            WHEN OTHERS THEN
                v_mon_fijo_comi := 0;
            END;

            v_comi_obtenida := (v_mon_fijo_comi * v_por_comi)/100;
            v_nombre_geren := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                         v_gerente,
                                         psCodPeriodo,
                                         pnOidPais,
                                         '01',
                                         temporalRecord(i).codRegi,
                                         NULL,
                                         NULL);
            if v_gerente is not null then
                 insert into com_comis_perio_calcu_regio(
                  COD_PERI,
                  COD_COMI,
                  COD_REGI,
                  COD_LIDE_REGI,
                  NOM_LIDE_REGI,
                  MON_CATA_REAL,
                  MON_CATA_ESTI,
                  POR_MON_CATA,
                  NUM_ESCA,
                  IMP_MONT_COM1,
                  POR_COMI_TRA1,
                  FEC_CALC,
                  IND_COMI
                  )
                  values(
                  psCodPeriodo,
                  psCodComision,
                  temporalRecord(i).codRegi,
                  v_gerente,
                  v_nombre_geren,
                  temporalRecord(i).valCata,
                  temporalRecord(i).valCataEst,
                  v_porcentaje,
                  v_num_nive,
                  v_comi_obtenida,
                  v_por_comi,
                  SYSDATE,
                  COMISION_RECUPERACION);

            end if;


        END LOOP;
     END IF;

     EXIT WHEN C_TEMPORAL%NOTFOUND;
  END LOOP;
  CLOSE C_TEMPORAL;

 end COM_PR_INSER_COMIS_VENT;




/***************************************************************************
  Descripcion       : Calculo de comision de Venta Neta Efectiva
  Fecha Creacion    : 23/01/2013
  Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENTA_NETEF(psCodPais      VARCHAR2,
                                           psCodMarca     VARCHAR2,
                                           psCodCanal     VARCHAR2,
                                           psCodPeriodo   VARCHAR2,
                                           psCodComision  VARCHAR2,
                                           psUsuarioLogin VARCHAR2)
  IS

    TYPE tRegAcumulado IS RECORD (
        cod_regi        COB_REPOR_ESTAD_RECUP_COBRA.cod_regi%TYPE,
        cod_zona        COB_REPOR_ESTAD_RECUP_COBRA.cod_zona%TYPE,
        imp_fact_neto   COB_REPOR_ESTAD_RECUP_COBRA.imp_fact_neto%TYPE,
        imp_carg_frac   COB_REPOR_ESTAD_RECUP_COBRA.imp_carg_frac%TYPE,
        imp_abon_nmon   COB_REPOR_ESTAD_RECUP_COBRA.imp_abon_nmon%TYPE,
        cob_dias_vent   COB_REPOR_ESTAD_RECUP_COBRA.cob_dias_vent%TYPE,
        val_impue       NUMBER(12, 2),
        sal_consu       NUMBER(12, 2),
        cod_gezo        COM_COMIS_PERIO_CALCU_ZONA.COD_LIDE_ZONA%TYPE,
        nom_gezo        COM_COMIS_PERIO_CALCU_ZONA.NOM_LIDE_ZONA%TYPE
    );

    TYPE tTablaAcumulado IS TABLE OF tRegAcumulado;
    tablaAcumulado tTablaAcumulado;
    regAcumulado tRegAcumulado;

    CURSOR cAcumulado
    IS
        SELECT
        EC.COD_REGI,
        EC.COD_ZONA,
        SUM(EC.IMP_FACT_NETO) IMP_FACT_NETO,
        SUM(EC.IMP_CARG_FRAC) IMP_CARG_FRAC,
        0 IMP_ABON_NMON,
        SUM(EC.COB_DIAS_31) COB_DIAS_VENT, ----  Yo
        0 VAL_IMPUE,
        0 sal_consu,
        null cod_gezo,
        null nom_gezo
        FROM COB_REPOR_ESTAD_RECUP_COBRA EC
        WHERE EC.COD_PAIS = psCodPais
        AND EC.COD_PERI = psCodPeriodo
        GROUP BY EC.COD_REGI, EC.COD_ZONA;

    CURSOR cImpuesto(codZona VARCHAR2)
    IS
        SELECT
        CC.FEC_DOCU,
        EC.IMP_FACT_NETO,
        EC.IMP_ABON_NMON,
        COM_PKG_REPOR.COM_FN_DEVUE_TASA_IMPUE(psCodPais, CC.FEC_DOCU) VAL_IMPU
        FROM COB_REPOR_ESTAD_RECUP_COBRA EC, CCC_MOVIM_CUENT_CORRI CC
        WHERE EC.OID_MOVI_CC = CC.OID_MOVI_CC
        AND EC.OID_PERI = CC.PERD_OID_PERI
        AND EC.OID_CLIE = CC.CLIE_OID_CLIE
        AND EC.COD_PAIS = psCodPais
        AND EC.COD_ZONA = codZona
        AND EC.COD_PERI = psCodPeriodo;

   /* CURSOR cSaldoConsultora(codZona VARCHAR2, idCampanyaSaldo NUMBER, idCampanyaActual NUMBER, idPais NUMBER)
    IS
    SELECT
        CC.FEC_DOCU,
        CC.IMP_PEND,
        COM_PKG_REPOR.COM_FN_DEVUE_TASA_IMPUE(psCodPais, CC.FEC_DOCU) VAL_IMPU
        FROM MAE_CLIEN_UNIDA_ADMIN CUA, ZON_TERRI_ADMIN ZTA, ZON_SECCI ZNS, ZON_ZONA ZZN, CCC_MOVIM_CUENT_CORRI CC
        WHERE idCampanyaActual >= CUA.PERD_OID_PERI_INI
        AND (idCampanyaActual <= CUA.PERD_OID_PERI_FIN OR CUA.PERD_OID_PERI_FIN IS NULL)
        AND CUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
        AND ZTA.ZSCC_OID_SECC = ZNS.OID_SECC
        AND ZNS.ZZON_OID_ZONA = ZZN.OID_ZONA
        AND ZZN.COD_ZONA = codZona
        AND CUA.CLIE_OID_CLIE = CC.CLIE_OID_CLIE
        AND CC.PERD_OID_PERI = idCampanyaSaldo
        AND CC.IMP_PEND  > 0
        AND CC.IMP_MOVI > 0
        AND CC.SUBP_OID_SUBP_CREA IN(
                SELECT CS.OID_SUBP
                FROM CCC_PROCE CP, CCC_SUBPR CS, COM_COMIS_SUBPR_PAGO CCSP
                WHERE CP.OID_PROC = CS.CCPR_OID_PROC
                AND CCSP.COD_PROC = CP.COD_PROC
                AND CCSP.COD_SUBP = CS.COD_SUBP
                AND CCSP.COD_COMI = '02'
                AND CCSP.TIP_COMI = 'C'
                AND CP.PAIS_OID_PAIS = idPais);  */

   CURSOR cSaldoConsultora(codZona VARCHAR2, idCampanyaSaldo NUMBER , idPais NUMBER)
    IS
        SELECT
        CC.FEC_DOCU,
       (EC.IMP_FACT_NETO - EC.COB_DIAS_63 ) saldo_63,
        COM_PKG_REPOR.COM_FN_DEVUE_TASA_IMPUE(psCodPais, CC.FEC_DOCU) VAL_IMPU
        FROM COB_REPOR_ESTAD_RECUP_COBRA EC, CCC_MOVIM_CUENT_CORRI CC
        WHERE EC.OID_MOVI_CC = CC.OID_MOVI_CC
        AND EC.OID_PERI = CC.PERD_OID_PERI
        AND EC.OID_CLIE = CC.CLIE_OID_CLIE
        AND EC.COD_PAIS = psCodPais
        AND EC.COD_ZONA = codZona
        AND EC.COD_PERI = idCampanyaSaldo;

    lnIdPais NUMBER;
    lnIdMarca NUMBER;
    lnIdCanal NUMBER;
    lnIdPeriodo NUMBER;
    lsCampanyaSaldo VARCHAR2(8);
    lnImpuestoAcumulado NUMBER(12,2);
    lnSaldoAcumulado NUMBER(12,2);
    lnAbonoNoMoneAcumulado NUMBER(12,2);
    lsMensajeError VARCHAR2(200);
    impuestoNoConfiguradoException EXCEPTION;
    lsCodigoGZ COM_COMIS_PERIO_CALCU_ZONA.COD_LIDE_ZONA%TYPE;
    lsNombreGZ COM_COMIS_PERIO_CALCU_ZONA.NOM_LIDE_ZONA%TYPE;
    lnPorRecup NUMBER(12,2);
    lnPorComi NUMBER(12,2);
    lnPorComiAdic NUMBER(12,2);
    lnPorRecuInic NUMBER(12,2);
    lnPorRecuInic2 NUMBER(12,2);
    lnPorRecuFina NUMBER(12,2);
    lnVentNetaEfec NUMBER(12,2);
    lnDifVentNetaEfec NUMBER(12,2);
    lnComiAdic NUMBER(12,2);
    lnNivel NUMBER(2);
    lnMontoComision NUMBER(12,2);
    lnIdCampanyaSaldo NUMBER;

  BEGIN
    --
    lnIdPais        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
    lnIdMarca       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
    lnIdCanal       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
    lnIdPeriodo     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

    lsCampanyaSaldo := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo,
                                                                      lnIdPais,
                                                                      lnIdMarca,
                                                                      lnIdCanal,
                                                                      -3);
    lnIdCampanyaSaldo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCampanyaSaldo);

    /* Eliminamos los datos calculados anteriormente */
    delete from com_comis_perio_calcu_zona
    where peri_cod_peri = psCodPeriodo
    and cod_comi = psCodComision;
    /**/

    /* Recorriendo Lista de Acumulados por Zona */
    OPEN cAcumulado;
    LOOP
        FETCH cAcumulado BULK COLLECT INTO tablaAcumulado LIMIT W_FILAS;

            IF tablaAcumulado.COUNT > 0 THEN

                FOR y IN tablaAcumulado.FIRST .. tablaAcumulado.LAST LOOP

                    regAcumulado := tablaAcumulado(y);

                    /* Por cada registro calculamos el impuesto y acumularlo */
                    lnImpuestoAcumulado := 0;
                    lnAbonoNoMoneAcumulado := 0;
                    lsMensajeError := '';
                    FOR rImpuesto IN cImpuesto(regAcumulado.cod_zona) LOOP
                        --Verificamos si tiene configurado el impuesto en caso contrario de camcela el proceso
                        IF rImpuesto.val_impu > 0 THEN
                             lnImpuestoAcumulado := lnImpuestoAcumulado + (rImpuesto.imp_fact_neto  - (rImpuesto.imp_fact_neto / (1 + (rImpuesto.val_impu /100))) );
                            lnAbonoNoMoneAcumulado := lnAbonoNoMoneAcumulado + rImpuesto.imp_abon_nmon/(1 + (rImpuesto.val_impu /100));
                        ELSE
                            lsMensajeError := 'Impuesto no esta configurado para la fecha: ' || to_char(rImpuesto.fec_docu, 'dd/mm/yyyy');
                            EXIT;
                        END IF;

                    END LOOP;

                    IF LENGTH(lsMensajeError) > 0 THEN
                        EXIT;
                    ELSE
                        --Actualizamos el impuesto acumulado en memoria
                        regAcumulado.val_impue := lnImpuestoAcumulado;
                        regAcumulado.imp_abon_nmon := lnAbonoNoMoneAcumulado;
                    END IF;
                    /**/

                    /* Por cada registro calculamos el saldo de las consultoras y los acumulamos */
                    lnSaldoAcumulado := 0;
                    FOR rSaldo IN cSaldoConsultora(regAcumulado.cod_zona, lsCampanyaSaldo,  lnIdPais) LOOP
                        --Verificamos si tiene configurado el impuesto en caso contrario se camcela el proceso
                        IF rSaldo.val_impu > 0 THEN
                            lnSaldoAcumulado := lnSaldoAcumulado + (rSaldo.SALDO_63 / (1 + (rSaldo.val_impu/100)));
                        ELSE
                            lsMensajeError := 'Impuesto no esta configurado para la fecha: ' || to_char(rSaldo.fec_docu, 'dd/mm/yyyy');
                            EXIT;
                        END IF;

                    END LOOP;


                    IF LENGTH(lsMensajeError) > 0 THEN
                        EXIT;
                    ELSE
                        --Actualizamos el impuesto acumulado en memoria
                        regAcumulado.sal_consu := lnSaldoAcumulado;
                    END IF;
                    /* */

                    /* Obtenemos el codigo de la GZ */
                    BEGIN
                        select distinct(g.gere)
                        into lsCodigoGZ
                        from zon_histo_geren g
                        where g.UA = '01' || regAcumulado.cod_regi || regAcumulado.cod_zona
                        and lnIdPeriodo >= g.perd_oid_peri_desd
                        and (lnIdPeriodo <= g.perd_oid_peri_hast or g.perd_oid_peri_hast is null)
                        and g.pais_oid_pais = lnIdPais;

                        -- Obtenemos el nombre
                        select
                        val_ape1||' '||val_ape2||' '||val_nom1||' '||val_nom2
                        INTO lsNombreGZ
                        from mae_clien
                        where cod_clie = lsCodigoGZ
                        and pais_oid_pais = lnIdPais;

                    EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            lsCodigoGZ := '';
                            lsNombreGZ := '';
                    END;

                    regAcumulado.cod_gezo := lsCodigoGZ;
                    regAcumulado.nom_gezo := lsNombreGZ;
                    /* */

                    /* Calculamos la comision por cada zona que tiene gerente */
                    IF LENGTH(lsCodigoGZ) > 0 THEN
                        -- % recuperacion1 = Acumulado de recuperado /  ( Acumulado  Facturado  +  Acumulado cargo  fraccionado ) * 100
                        lnPorRecup := regAcumulado.cob_dias_vent/(regAcumulado.imp_fact_neto + regAcumulado.imp_carg_frac) * 100;

                        /* Comparar el porcentaje de recuperaci?n1  con cada registro de COM_COMIS_ESCALON
                        hasta que sea >=  VAL_POR_RECU_INI y <=  VAL_POR_RECU_FIN  */
                        BEGIN
                            select ce.POR_COMI, ce.COMI_ADI, ce.POR_RECU_INIC, ce.POR_RECU_FINA, ce.NUM_NIVE
                            into lnPorComi, lnPorComiAdic,lnPorRecuInic, lnPorRecuFina, lnNivel
                            from  com_comis cc, com_comis_cobra co, com_comis_escal ce
                            where cc.OID_COMI = co.comi_oid_comi
                            and co.OID_COMI_COBR = ce.OID_COMI_COBR
                            and cc.COD_COMI = psCodComision
                            and lnPorRecup >= ce.POR_RECU_INIC and lnPorRecup <= ce.POR_RECU_FINA;
                        EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                                lnPorComi := 0;
                                lnPorRecuInic := 0;
                                lnPorComiAdic := 0;
                                lnPorRecuFina := 0;
                                lnNivel := 0;
                        END;
                        BEGIN
                          select min( ce.POR_RECU_INIC)
                            into lnPorRecuInic2
                            from  com_comis cc, com_comis_cobra co, com_comis_escal ce
                            where cc.OID_COMI = co.comi_oid_comi
                            and co.OID_COMI_COBR = ce.OID_COMI_COBR
                            and cc.COD_COMI =  psCodComision
                            and ce.COMI_ADI > 0;
                        EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                                lnPorRecuInic2 := 0;
                        END;
                        /* */

                        /* Si el registro   recuperado tiene valor en VAL_POR_COMI, calcular
                        Monto Comision: Venta neta Efectiva =
                        */
                        lnVentNetaEfec := regAcumulado.imp_fact_neto + regAcumulado.imp_carg_frac - regAcumulado.val_impue - regAcumulado.imp_abon_nmon - regAcumulado.sal_consu;
                        lnMontoComision := 0;
                        lnDifVentNetaEfec := 0;
                        lnComiAdic := 0;
                        IF lnPorComi > 0 THEN
                            lnMontoComision :=  lnVentNetaEfec * lnPorComi/100;

                            /* Si Alcanza un % de recuperaci?n y este tiene % comisi?n adicional */
                                --Diferencia Venta Neta efectiva = Venta Neta Efectiva * ( ( Porcentaje recuperaci?n alcanzado -  VAL-POR-RECU-INI ) / 100)
                                --Comision adicional = Diferencia venta neta * % comisi?n adicional.

                            IF lnPorComiAdic > 0 THEN
                                lnDifVentNetaEfec := lnVentNetaEfec * ((lnPorRecup - lnPorRecuInic2)/100);
                                lnComiAdic := lnDifVentNetaEfec * lnPorComiAdic/100;
                            END IF;
                            /* */

                        END IF;
                        /* */

                        /* Grabar los resultados */
                        INSERT INTO com_comis_perio_calcu_zona
                        (
                            peri_cod_peri,
                            cod_comi,
                            cod_lide_zona,
                            nom_lide_zona,
                            cod_regi,
                            cod_zona,
                            por_recu,
                            imp_comi_tra1,
                            val_porc_comi_tra1,
                            imp_pago_ante_limi_cimp,
                            ven_neta_efec,
                            car_frac_cimp,
                            val_sald,
                            dif_vent_neta_efec,
                            com_adic,
                            imp_recl,
                            por_adic,
                            val_impu,
                            imp_neto_cimp,
                            por_recu_inic,
                            por_recu_fina,
                            num_nive,
                            fec_calc,
                            usu_modi)
                        VALUES(
                            psCodPeriodo,
                            psCodComision,
                            regAcumulado.cod_gezo,
                            regAcumulado.nom_gezo,
                            regAcumulado.cod_regi,
                            regAcumulado.cod_zona,
                            lnPorRecup,
                            lnMontoComision + lnComiAdic,
                            lnPorComi,
                            regAcumulado.cob_dias_vent,
                            lnVentNetaEfec,
                            regAcumulado.imp_carg_frac,
                            regAcumulado.sal_consu,
                            lnDifVentNetaEfec,
                            lnComiAdic,
                            regAcumulado.imp_abon_nmon,
                            lnPorComiAdic,
                            regAcumulado.val_impue,
                            regAcumulado.imp_fact_neto,
                            lnPorRecuInic,
                            lnPorRecuFina,
                            lnNivel,
                            sysdate,
                            psUsuarioLogin);
                        /* */

                    END IF;
                    /* */

                END LOOP;

            END IF;

    EXIT WHEN cAcumulado%NOTFOUND;
    END LOOP;
    CLOSE cAcumulado;

    IF LENGTH(lsMensajeError) > 0 THEN
        RAISE impuestoNoConfiguradoException;
    END IF;

  EXCEPTION
    WHEN impuestoNoConfiguradoException THEN
        RAISE_APPLICATION_ERROR(-20123, lsMensajeError);
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_CALCU_COMIS_VENTA_NETEF: '||ls_sqlerrm);

  END COM_PR_CALCU_COMIS_VENTA_NETEF;

  /***************************************************************************
  Descripcion       : Calculo de comision de Venta Retail (Base de Comision 06)
  Fecha Creacion    : 01/10/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENTA_RETAI(
            psCodPais       VARCHAR2,
            psCodMarca      VARCHAR2,
            psCodCanal      VARCHAR2,
            psCodComision   VARCHAR2,
            psCodPeriodo    VARCHAR2,
            psUsuario       VARCHAR2
            )IS
  /* Declaracion de Types */
  TYPE tRegClienteComisionRecupe IS RECORD (
     COD_CLIE               MAE_CLIEN.COD_CLIE%TYPE,
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE
   );

  TYPE tTablaClienteComisionRecupe IS TABLE OF tRegClienteComisionRecupe;

  /* Declaracion de Variables */
  lnIdPais                SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal               SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriActual          CRA_PERIO.OID_PERI%TYPE;

  lsCodPeriAnterior       VARCHAR2(6);
  lnPag                   NUMBER;
  lnIdZona                ZON_ZONA.OID_ZONA%TYPE;
  lnIdSeccion             ZON_SECCI.OID_SECC%TYPE;
  lnIdRegion              ZON_REGIO.OID_REGI%TYPE;

  lsCodLiderZona          MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderSeccion       MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderRegion        MAE_CLIEN.COD_CLIE%TYPE;
  lnIdEstado              MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE;
  lsCodPeriSiguiente      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodSeccion            ZON_SECCI.COD_SECC%TYPE;
  lsCodZona               ZON_ZONA.COD_ZONA%TYPE;
  lsCodRegion             ZON_REGIO.COD_REGI%TYPE;
  regRecuperacion         COM_REPOR_COMIS_RECUP%ROWTYPE;
  lv_procesar             BOOLEAN;

  lsNomLiderZona          VARCHAR2(500);
  lsNomLiderSeccion       VARCHAR2(500);
  lsNomLiderRegion        VARCHAR2(500);
  lnIdComision            NUMBER;
  ldFechaIniPeriodo       DATE;
  ldFechaFinPeriodo       DATE;
  ldFechaDesde            DATE;
  ldFechaHasta            DATE;
  ldFechaIngreso          DATE;
  lbInsertar              BOOLEAN;
  lbDemandaAnticipada     BOOLEAN;
  lnNumDiasDemandaTramo1   NUMBER;
  lnNumDiasDemandaTramo2   NUMBER;

  lnSumaComisionTramo1    NUMBER;
  lnSumaComisionTramo2    NUMBER;
  lnExisteRegion          NUMBER;
  lnIdUnidadAdm           NUMBER;
  lnIdEstadoPedido        NUMBER;
  lnIdPeriSaldo           number;
  lbInsertarZona          BOOLEAN;
  lsCodPlanilla           VARCHAR2(100);
  lnValor                 NUMBER;
  lsMensaje               VARCHAR2(255);
  W_FILAS                 NUMBER:=5000;
  regRegistro             tRegClienteComisionRecupe;
  tablaRegistro           tTablaClienteComisionRecupe;
  lnSumaRetail            NUMBER;
  lnRestaRetail           NUMBER;
  lnPorceComisRetail      NUMBER;

  CURSOR cursorComision(vnIdComision NUMBER)
  IS
  SELECT
     B.ZORG_OID_REGI, B.ZZON_OID_ZONA
  FROM
     COM_COMIS_CLIEN B
  WHERE B.COMI_OID_COMI = vnIdComision
    AND B.ZORG_OID_REGI IS NOT NULL;

  CURSOR cursorRegistro
  IS
  SELECT
    COD_CLIE,
    COD_REGI,
    COD_ZONA,
    COD_SUBG_VENT
  FROM
     COM_TEMPO_COMIS_RETAI A
  ORDER BY  A.COD_REGI, A.COD_ZONA, A.COD_CLIE ;

  TYPE tTablaCodCliente IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE;
  tablaRegistroLideres  tTablaCodCliente;
  lnX                   NUMBER;
  lnVentaRetail         NUMBER;

  CURSOR C_TEMPORAL  IS
    SELECT X.COD_REGI,
           X.COD_ZONA,
           X.VAL_VENT_RETA,
           X.COD_GERE_ZONA
    FROM COM_TEMPO_CALCU_VENTA_ZONA X;

  TYPE temporalRec IS RECORD(
      codRegi         COM_TEMPO_CALCU_VENTA_ZONA.COD_REGI%TYPE,
      codZona         COM_TEMPO_CALCU_VENTA_ZONA.COD_ZONA%TYPE,
      valVentReta     COM_TEMPO_CALCU_VENTA_ZONA.VAL_VENT_RETA%TYPE,
      codLiderZona    COM_TEMPO_CALCU_VENTA_ZONA.COD_GERE_ZONA%TYPE
   );

  TYPE temporalTab IS TABLE OF temporalRec;
  temporalRecord temporalTab;
  lnComision     NUMBER;
  lsCodEmpl      MAE_CLIEN_DATOS_ADICI.Cod_Empl%TYPE;

BEGIN
  --DELETE FROM com_tempo_comis_recup;
  EXECUTE IMMEDIATE 'TRUNCATE TABLE COM_TEMPO_COMIS_RETAI';
  DELETE FROM COM_TEMPO_CALCU_VENTA_ZONA;

  /* Borrando informaci?n en la tabla COM_COMIS_PERI_CALCU_ZONA */
  DELETE FROM COM_COMIS_PERIO_CALCU_ZONA
  WHERE PERI_COD_PERI = psCodPeriodo
    AND COD_COMI = psCodComision;

  /* Obteniendo oids */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriActual := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  lnIdComision   := COM_PKG_REPOR.COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

  /* Obtenemos los valores COM_COMIS */
  SELECT NVL(A.Por_Comi_Retl, 0)
  INTO lnPorceComisRetail
  FROM COM_COMIS A
  WHERE A.OID_COMI = lnIdComision;

  /* Verificando que la Comision posea Regiones */
  SELECT COUNT(1)
  INTO lnExisteRegion
  FROM
     COM_COMIS A,
     COM_COMIS_CLIEN B
  WHERE  A.OID_COMI = lnIdComision
     AND A.OID_COMI = B.COMI_OID_COMI
     AND B.ZORG_OID_REGI IS NOT NULL;

  /* CAMBIOS:
     1. AL ENCONTRAR NULO SE LE ANTEPONE UN 0 PARA EVITAR NULO
     2. PARA EVITAR HACER UN CONTEO TOTAL Y GENERAR DEMORA,
        SOLO AHORA COMPROBAMOS SI TIENE 1 SOLO REGISTRO Y SERA MAS RAPIDO
  */
  IF lnExisteRegion > 0 THEN
    FOR curComis IN cursorComision(lnIdComision)  LOOP
      IF curComis.Zzon_Oid_Zona IS NULL THEN

        INSERT INTO COM_TEMPO_COMIS_RETAI(
              COD_CLIE,
              COD_REGI,
              COD_ZONA,
              COD_SUBG_VENT)
          SELECT DISTINCT
            C.VAL_CUEN_CONSU,
            C.COD_REGI,
            C.COD_ZONA,
            '01'
          FROM
             ret_venta_cabec C,
             ZON_REGIO I,
             COM_COMIS A,
             COM_COMIS_CLIEN B

          WHERE  A.OID_COMI = lnIdComision
             AND I.OID_REGI = curComis.Zorg_Oid_Regi
             AND A.OID_COMI = B.COMI_OID_COMI
             AND I.OID_REGI = B.ZORG_OID_REGI
             AND C.COD_REGI = I.COD_REGI
             AND (C.IND_ANUL <> 'A' OR C.IND_ANUL IS NULL)
             AND C.TIP_CLIE = 'CO'
             AND C.CAM_COMI = psCodPeriodo;
    ELSE
       INSERT INTO COM_TEMPO_COMIS_RETAI(
              COD_CLIE,
              COD_REGI,
              COD_ZONA,
              COD_SUBG_VENT)
          SELECT DISTINCT
            C.VAL_CUEN_CONSU,
            C.COD_REGI,
            C.COD_ZONA,
            '01'
          FROM
             ret_venta_cabec C,
             ZON_REGIO I,
             ZON_ZONA J,
             COM_COMIS A,
             COM_COMIS_CLIEN B

          WHERE  A.OID_COMI = lnIdComision
             AND I.OID_REGI = curComis.Zorg_Oid_Regi
             AND J.OID_ZONA = curComis.ZZON_OID_ZONA
             AND A.OID_COMI = B.COMI_OID_COMI
             AND I.OID_REGI = B.ZORG_OID_REGI
             AND J.OID_ZONA = B.ZZON_OID_ZONA
             AND C.COD_REGI = I.COD_REGI
             AND C.COD_ZONA = J.COD_ZONA
             AND (C.IND_ANUL <> 'A' OR C.IND_ANUL IS NULL)
             AND C.TIP_CLIE = 'CO'
             AND C.CAM_COMI = psCodPeriodo;
      END IF;
    END LOOP;
  ELSE
         INSERT INTO COM_TEMPO_COMIS_RETAI(
              COD_CLIE,
              COD_REGI,
              COD_ZONA,
              COD_SUBG_VENT)
          SELECT DISTINCT
            C.VAL_CUEN_CONSU,
            C.COD_REGI,
            C.COD_ZONA,
            '01'
          FROM
         ret_venta_cabec C
      WHERE (C.IND_ANUL <> 'A' OR C.IND_ANUL IS NULL)
         AND C.TIP_CLIE = 'CO'
         AND C.CAM_COMI = psCodPeriodo
         AND C.COD_REGI IS NOT NULL
         AND C.COD_ZONA IS NOT NULL;

  END IF;

  COMMIT;

  /* Inicio LOOP */
  lnPag := 0;
  lbInsertar := TRUE;
  lsCodZona := '-1';
  OPEN cursorRegistro;
  LOOP
      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      lnPag := lnPag + 1;
      IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro := tablaRegistro(x);
              IF (lsCodZona != regRegistro.cod_Zona) THEN
                  lbInsertar := TRUE;
                  lsNomLiderZona := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                            lsCodLiderZona,
                                            psCodPeriodo,
                                            lnIdPais,
                                            regRegistro.COD_SUBG_VENT,
                                            regRegistro.cod_Regi,
                                            regRegistro.cod_Zona,
                                            NULL);

                  lsCodZona := regRegistro.COD_ZONA;
                  IF lsCodLiderZona IS NULL THEN
                     lbInsertar := FALSE;
                  ELSE
                     BEGIN
                       SELECT A.COD_EMPL
                       INTO lsCodEmpl
                       FROM
                          MAE_CLIEN_DATOS_ADICI A,
                          MAE_CLIEN B
                       WHERE B.COD_CLIE =  lsCodLiderZona
                         AND B.PAIS_OID_PAIS = lnIdPais
                         AND A.CLIE_OID_CLIE = B.OID_CLIE;

                         IF lsCodEmpl IS NULL THEN
                            lbInsertar := FALSE;
             END IF;
                     EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                         lbInsertar := FALSE;
                     END;
                  END IF;
              END IF;

              IF lbInsertar THEN

                /* Obteniendo la Venta Retail*/
                lnSumaRetail := 0.00;
                BEGIN
                      select nvl(sum(retd.val_mont_cata * retd.uni_vend),0)
                      into lnSumaRetail
                      from ret_venta_cabec retc,
                           ret_venta_detal retd,
                           MAE_CLIEN A,
                           MAE_CLIEN_HISTO_ESTAT B,
                           MAE_ESTAT_CLIEN  C
                      where retc.cod_pais = psCodPais
                        and retc.cam_comi = psCodPeriodo
                        and retc.cod_regi = regRegistro.COD_REGI
                        and retc.cod_zona = regRegistro.COD_ZONA
                        AND retc.VAL_CUEN_CONSU = regRegistro.COD_CLIE
                        and retc.COD_PAIS = retd.COD_PAIS
                        and retc.COD_SBAC = retd.COD_SBAC
                        and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                        and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                        and retc.COD_TIPO_DOCU = 'F'
                        AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                        AND retc.TIP_CLIE = 'CO'
                        AND retc.VAL_CUEN_CONSU = a.cod_clie
                        AND retc.fec_envi = retd.fec_envi
                        AND A.OID_CLIE = B.CLIE_OID_CLIE
                        AND lnIdPeriActual >= B.PERD_OID_PERI
                        AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  lnIdPeriActual <= B.PERD_OID_PERI_PERI_FIN)
                        AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                        AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );

                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                     lnSumaRetail := 0.00;
                END;

                lnRestaRetail := 0.00;
                BEGIN
                      select nvl(sum(retd.val_mont_cata * retd.Uni_Devu),0)
                      into lnRestaRetail
                      from ret_venta_cabec retc,
                           ret_venta_detal retd,
                           MAE_CLIEN A,
                           MAE_CLIEN_HISTO_ESTAT B,
                           MAE_ESTAT_CLIEN  C
                      where retc.cod_pais = psCodPais
                        and retc.cam_comi = psCodPeriodo
                        and retc.cod_regi = regRegistro.COD_REGI
                        and retc.cod_zona = regRegistro.COD_ZONA
                        AND retc.VAL_CUEN_CONSU = regRegistro.COD_CLIE
                        and retc.COD_PAIS = retd.COD_PAIS
                        and retc.COD_SBAC = retd.COD_SBAC
                        and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                        and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                        AND retc.COD_TIPO_DOCU = 'N'
                        AND ( retd.TIPO_TRAN_RET = 'RR' OR retd.TIPO_TRAN_RET = 'RD' )
                        AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                        AND retc.TIP_CLIE = 'CO'
                        AND retc.VAL_CUEN_CONSU = a.cod_clie
                        AND retc.fec_envi = retd.fec_envi
                        AND A.OID_CLIE = B.CLIE_OID_CLIE
                        AND lnIdPeriActual >= B.PERD_OID_PERI
                        AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  lnIdPeriActual <= B.PERD_OID_PERI_PERI_FIN)
                        AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                        AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                     lnRestaRetail := 0.00;
                END;

                 /* Insertando en tabla Temporal */
                lnVentaRetail := lnSumaRetail - lnRestaRetail;
                BEGIN
                  insert into COM_TEMPO_CALCU_VENTA_ZONA(
                              cod_peri,
                              cod_regi,
                              cod_zona,
                                  val_vent_reta,
                                  cod_gere_zona
                              )
                              values(
                              psCodPeriodo,
                              regRegistro.COD_REGI,
                              regRegistro.COD_ZONA,
                                  lnVentaRetail,
                                  lsCodLiderZona );

               EXCEPTION
               WHEN DUP_VAL_ON_INDEX THEN
                    UPDATE COM_TEMPO_CALCU_VENTA_ZONA
                    SET val_vent_reta = val_vent_reta + lnVentaRetail
                    WHERE cod_peri = psCodPeriodo
                    AND cod_regi =  regRegistro.COD_REGI
                    AND cod_zona = regRegistro.COD_ZONA ;
               END;
             END IF;

         END LOOP;
      END IF;
      EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;

  /* Insertando en tabla COM_COMIS_PERIO_CALCU_ZONA */
  OPEN C_TEMPORAL;
  LOOP
     FETCH C_TEMPORAL BULK COLLECT INTO temporalRecord LIMIT W_FILAS;
     IF temporalRecord.COUNT > 0 THEN
        FOR i IN temporalRecord.FIRST .. temporalRecord.LAST LOOP
            lnComision := lnPorceComisRetail * temporalRecord(i).valVentReta / 100;

            INSERT INTO COM_COMIS_PERIO_CALCU_ZONA(
               PERI_COD_PERI,
               COD_COMI,
               COD_REGI,
               COD_ZONA,
               COD_LIDE_ZONA,
               IMP_NETO_CIMP,
               IMP_COMI_TRA1,
               FEC_CALC,
               USU_DIGI)
            VALUES(
               psCodPeriodo,
               psCodComision,
               temporalRecord(i).codRegi,
               temporalRecord(i).codZona,
               temporalRecord(i).codLiderZona,
               temporalRecord(i).valVentReta,
               lnComision,
               SYSDATE,
               psUsuario);

        END LOOP;
     END IF;

     EXIT WHEN C_TEMPORAL%NOTFOUND;
  END LOOP;
  CLOSE C_TEMPORAL;


END COM_PR_CALCU_COMIS_VENTA_RETAI;

/***************************************************************************
 Descripcion       : Genera información base de recuperación por consultora
 Fecha Creacion    : 30/03/2015
 Autor             : Carlos Mori
 Modificado por    : 
***************************************************************************/
PROCEDURE COM_PR_GENER_BASE_RECUP_COBRA
(
 psCodigoPais     VARCHAR2,
 psCampanaProceso VARCHAR2,
 psCampanaRecaudo VARCHAR2,
 psFechaProceso   VARCHAR2,
 psCodigoUsuario  VARCHAR2
)
IS

CURSOR c_Peri( vnIndCampaAnte NUMBER, vsCampanaAnterior VARCHAR2 )
IS
SELECT psCampanaProceso cod_peri_proc,
       GEN_FN_CALCU_PERIO ( psCampanaProceso, -( vnIndCampaAnte ) ) cod_peri_reca,
       psFechaProceso fec_fact
  FROM DUAL
UNION
SELECT GEN_FN_CALCU_PERIO ( psCampanaProceso, -( vnIndCampaAnte ) ) cod_peri_proc,
       GEN_FN_CALCU_PERIO ( psCampanaProceso, -( vnIndCampaAnte + 1 ) ) cod_peri_reca,
       psFechaProceso fec_fact
  FROM DUAL
 ORDER BY 1
    ;

TYPE c_Peri_t IS TABLE OF c_Peri%ROWTYPE INDEX BY BINARY_INTEGER;
RegPeri c_Peri_t;

-- Cursor base para ultimas transacciones

CURSOR c_Base( vnOidCampa NUMBER,
               vnDiasTramo NUMBER,
               vsCodiPeriAnte VARCHAR2,
               vnNumDiasTramo1 NUMBER,
               vnNumDiasPrevios NUMBER,
               vnOidPais NUMBER )
IS
WITH temp AS
       (
        SELECT mvcc.clie_oid_clie,
               clie.cod_clie
          FROM ccc_movim_cuent_corri mvcc,
               mae_clien clie,
               ccc_subpr subp,
               ccc_proce ccpr,
               com_comis_subpr_pago com
         WHERE 1=1
           AND mvcc.clie_oid_clie = clie.oid_clie
           AND mvcc.subp_oid_subp_crea = subp.oid_subp
           AND subp.ccpr_oid_proc = ccpr.oid_proc
           AND ccpr.cod_proc = com.cod_proc
           AND subp.cod_subp = com.cod_subp
           --
           AND com.cod_comi = '02'
           AND com.tip_comi = 'C'
           AND mvcc.perd_oid_peri = vnOidCampa
           AND mvcc.imp_movi > 0
         GROUP BY mvcc.clie_oid_clie, clie.cod_clie
       )
SELECT zorg.cod_regi,
       zzon.cod_zona,
       zscc.cod_secc,
       temp.clie_oid_clie,
       temp.cod_clie,
       clda.esta_oid_esta_clie,
       cier.fec_cier,
       COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(cier.fec_cier + vnNumDiasTramo1, zzon.cod_zona) + vnNumDiasPrevios,zzon.cod_zona ) fec_limi_abon,
       COM_PKG_REPOR.COM_FN_DEVUE_FECHA_HABIL(cier.fec_cier + vnNumDiasTramo1, zzon.cod_zona) fec_limi_abon_cons
  FROM temp,
       mae_clien_unida_admin cuad,
       zon_terri_admin ztad,
       zon_secci zscc,
       zon_zona zzon,
       zon_regio zorg,
       mae_clien_datos_adici clda,
       /*(
         SELECT pcie.cod_regi, pcie.fec_cier
           FROM fac_progr_cierr pcie
          WHERE pcie.cod_pais = psCodigoPais
            AND pcie.tip_cier = 'R'
            AND pcie.cam_proc = vsCodiPeriAnte
            AND pcie.est_cier = 'P'
       ) cier,*/
       (
        select zorg.cod_regi, MAX(cr.fec_inic) fec_cier
          from cra_activ ca,
               cra_crono cr,
               zon_zona zzon,
               zon_regio zorg
         where ca.oid_acti = cr.cact_oid_acti
           and cr.zzon_oid_zona = zzon.oid_zona
           and zzon.zorg_oid_regi = zorg.oid_regi
           and cr.perd_oid_peri = vnOidCampa
           and ca.cod_acti = 'FA'
           and ca.pais_oid_pais = vnOidPais
         group by zorg.cod_regi
       ) cier
 WHERE temp.clie_oid_clie = cuad.clie_oid_clie
   AND cuad.clie_oid_clie = clda.clie_oid_clie
   AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
   AND ztad.zscc_oid_secc = zscc.oid_secc
   AND zscc.zzon_oid_zona = zzon.oid_zona
   AND zzon.zorg_oid_regi = zorg.oid_regi
   AND vnOidCampa BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,vnOidCampa)
   AND zorg.cod_regi = cier.cod_regi(+)
   --AND zzon.oid_zona = cier.zzon_oid_zona(+)
   AND zorg.cod_regi NOT IN ('99','88')
     ;

TYPE c_Base_t IS TABLE OF c_Base%ROWTYPE INDEX BY BINARY_INTEGER;
RegBase c_Base_t;

CURSOR c_Movim( vnOidClie NUMBER, vnOidCampaFact NUMBER, vsTipoMovim VARCHAR2 ) IS
 SELECT mvcc.oid_movi_cc,
        ccpr.cod_proc,
        subp.cod_subp,
        com.tip_comi tip_comi_crea,
        com2.tip_comi tip_comi_ulti,
        mvcc.fec_docu,
        mvcc.fec_ulti_movi,
        mvcc.imp_movi,
        mvcc.imp_pago
   FROM ccc_movim_cuent_corri mvcc,
        ccc_subpr subp,
        ccc_proce ccpr,
        com_comis_subpr_pago com,
        ccc_subpr subp2,
        ccc_proce ccpr2,
        com_comis_subpr_pago com2
  WHERE mvcc.subp_oid_subp_crea = subp.oid_subp
    AND subp.ccpr_oid_proc = ccpr.oid_proc
    AND ccpr.cod_proc = com.cod_proc
    AND subp.cod_subp = com.cod_subp
    --
    AND mvcc.subp_oid_subp_ulti = subp2.oid_subp
    AND subp2.ccpr_oid_proc = ccpr2.oid_proc
    AND ccpr2.cod_proc = com2.cod_proc
    AND subp2.cod_subp = com2.cod_subp
    --
    AND com.cod_comi = '02'
    AND com2.cod_comi = '02'
    AND com.tip_comi = CASE WHEN vsTipoMovim = '*' THEN com.tip_comi ELSE vsTipoMovim END
    AND mvcc.clie_oid_clie = vnOidClie
    AND mvcc.perd_oid_peri = vnOidCampaFact
    AND mvcc.imp_movi > 0
      ;

TYPE c_Movim_t IS TABLE OF c_Movim%ROWTYPE INDEX BY BINARY_INTEGER;
regMovim c_Movim_t;

CURSOR c_Histo( vnOidMovi NUMBER, vdFechaLimite DATE, vsTipoMovim VARCHAR2 ) IS
    SELECT com.tip_comi,
           ccpr.cod_proc,
           subp.cod_subp,
           SUM( hmcc.imp_movi ) imp_movi,
           SUM( hmcc.imp_pago ) imp_pago
      FROM ccc_histo_movim_cc hmcc,
           ccc_subpr subp,
           ccc_proce ccpr,
           com_comis_subpr_pago com
     WHERE 1=1
       AND hmcc.subp_oid_subp = subp.oid_subp
       AND subp.ccpr_oid_proc = ccpr.oid_proc
       AND ccpr.cod_proc = com.cod_proc
       AND subp.cod_subp = com.cod_subp
       --
       AND com.cod_comi = '02'
       AND com.tip_comi = vsTipoMovim
       AND hmcc.mvcc_oid_movi_cc = vnOidMovi
       AND hmcc.fec_movi <= vdFechaLimite
     GROUP BY com.tip_comi,
              ccpr.cod_proc,
              subp.cod_subp
         ;

TYPE c_Histo_t IS TABLE OF c_Histo%ROWTYPE INDEX BY BINARY_INTEGER;
regHisto c_Histo_t;

-- Variables
lnOidCampa CRA_PERIO.Peri_Oid_Peri%TYPE;
w_Filas NUMBER(12):= 5000;
lnOk NUMBER(1):=0;

lnNumDiasPrevios NUMBER;
lnIndCampaAnte NUMBER;
lnSumaCargos NUMBER(12,2) :=0;
lnSumaAbonos NUMBER(12,2) :=0;
lnSumaCDRS   NUMBER(12,2) :=0;
lnPorcentaje NUMBER(12,2) :=0;
lnDiasTramo  NUMBER(12)   :=31;
lsCampanaRecaudo VARCHAR2(6);
lsCampanaAnterior VARCHAR2(6);
lsFechaProceso VARCHAR2(10);
lnNumDiasTramo1 NUMBER(12) :=0;
lnOidPais NUMBER(12);

BEGIN
-- Obtener oid del pais
   lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
-- Obtener parámetro de diferencia de días
    BEGIN
      SELECT VAL_PARA
        INTO lnIndCampaAnte
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = psCodigoPais
         AND COD_SIST = 'LET'
         AND UPPER(NOM_PARA) = 'INDCAMPAANTE';
    EXCEPTION WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20123, 'FALTA PARAMETRO PARA CAMPAÑA DE RECAUDO ANTERIOR');
    END;

-- Obtener parámetro de diferencia de días
    BEGIN
      SELECT VAL_PARA
        INTO lnNumDiasTramo1
        FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = psCodigoPais
         AND COD_SIST = 'COM'
         AND UPPER(NOM_PARA) = 'NUMDIASTRAMO1';
    EXCEPTION WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20123, 'FALTA PARAMETRO DE NUMERO DE DIAS TRAMO');
    END;

-- Calcula campaña recaudo
    IF psCampanaRecaudo IS NULL THEN
       lsCampanaRecaudo := GEN_FN_CALCU_PERIO ( psCampanaProceso, - lnIndCampaAnte );
       lsCampanaAnterior := GEN_FN_CALCU_PERIO ( psCampanaProceso, -( lnIndCampaAnte + 1 ) );
    ELSE
       lsCampanaRecaudo := psCampanaRecaudo;
       lsCampanaAnterior := psCampanaRecaudo;
    END IF;

-- Obtener numero de días previos para la fecha de proceso
   BEGIN
       SELECT TO_NUMBER(VAL_PARA)
       INTO lnNumDiasPrevios
       FROM BAS_PARAM_PAIS
       WHERE COD_PAIS = pscodigoPais
       AND COD_SIST = 'COM'
       AND UPPER(NOM_PARA) = 'NUMDIASPREVIOS'
       AND IND_ACTI = '1';
   EXCEPTION
       WHEN NO_DATA_FOUND THEN
           RAISE_APPLICATION_ERROR(-20123, 'FALTA PARAMETRO DE DIAS PREVIOS PARA PROCESO');
   END;

-- Se Procesan las campañas correspondientes
   OPEN c_Peri( lnIndCampaAnte,lsCampanaAnterior );
       LOOP
          FETCH c_Peri BULK COLLECT INTO regPeri;
          IF regPeri.COUNT > 0 THEN
             FOR p IN regPeri.FIRST .. regPeri.LAST LOOP

                 lnOidCampa := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(regPeri(p).cod_peri_reca); -- Obtiene oid campaña

-- Selecciona las consultoras a procesar
          OPEN c_Base( lnOidCampa, lnDiasTramo, regPeri(p).cod_peri_reca, lnNumDiasTramo1, lnNumDiasPrevios, lnOidPais );
       LOOP
          FETCH c_Base BULK COLLECT INTO regBase LIMIT w_filas;
          IF regBase.COUNT > 0 THEN
             FOR x IN regBase.FIRST .. regBase.LAST LOOP
-- Procesa los cargos
                 OPEN c_Movim( regBase(x).clie_oid_clie, lnOidCampa, 'C' );
                     LOOP
                        FETCH c_Movim BULK COLLECT INTO regMovim LIMIT w_filas;
                        IF regMovim.COUNT > 0 THEN
                           FOR y IN regMovim.FIRST .. regMovim.LAST LOOP

-- Se suman los cargos del transaccional
                               IF regMovim(y).tip_comi_crea = 'C' THEN
                               lnSumaCargos := lnSumaCargos + regMovim(y).imp_movi;
                               END IF;
-- Se suman los abonos del transaccional
                                             IF regMovim(y).tip_comi_ulti = 'P' AND regMovim(y).fec_ulti_movi <= regBase(x).fec_limi_abon THEN
                               lnSumaAbonos := lnSumaAbonos + regMovim(y).imp_pago;
                               END IF;
-- Se suman los cdrs del transaccional
                                             IF regMovim(y).tip_comi_ulti = 'R' AND regMovim(y).fec_ulti_movi <= regBase(x).fec_limi_abon THEN
                                  lnSumaCDRS := lnSumaCDRS + regMovim(y).imp_pago;
                               END IF;

-- Se suman los abonos de cargos del histórico
                                             OPEN c_Histo( regMovim(y).oid_movi_cc, /*regMovim(y).fec_docu + lnDiasTramo*/regBase(x).fec_limi_abon, 'P' );
                                   LOOP
                                      FETCH c_Histo BULK COLLECT INTO regHisto LIMIT w_filas;
                                      IF regHisto.COUNT > 0 THEN
                                         FOR z IN regHisto.FIRST .. regHisto.LAST LOOP
                                             lnSumaAbonos := lnSumaAbonos + regHisto(z).imp_pago;
                                         END LOOP;
                                      END IF;
                                      EXIT WHEN c_Histo%NOTFOUND;
                                   END LOOP;
                               CLOSE c_Histo;

-- Se suman los CDRS de cargos del histórico
                                             OPEN c_Histo( regMovim(y).oid_movi_cc, /*regMovim(y).fec_docu + lnDiasTramo*/regBase(x).fec_limi_abon, 'R' );
                                   LOOP
                                      FETCH c_Histo BULK COLLECT INTO regHisto LIMIT w_filas;
                                      IF regHisto.COUNT > 0 THEN
                                         FOR a IN regHisto.FIRST .. regHisto.LAST LOOP
                                             lnSumaCDRS := lnSumaCDRS + regHisto(a).imp_pago;
                                         END LOOP;
                                      END IF;
                                      EXIT WHEN c_Histo%NOTFOUND;
                                   END LOOP;
                               CLOSE c_Histo;

-- Actualiza valores de cargos y abonos
                               lnSumaCargos := lnSumaCargos - lnSumaCDRS;
                               lnSumaAbonos := lnSumaAbonos /*- CASE WHEN lnSumaAbonos >= lnSumaCDRS THEN lnSumaCDRS ELSE 0 END*/;

-- Se graba el registro de la consultora

                               lnPorcentaje := CASE WHEN lnSumaCargos > 0 THEN
                                                    ROUND((lnSumaAbonos/lnSumaCargos )*100,2)
                                               ELSE 0
                                               END;
                                             IF lnSumaCargos >= 0 THEN
                               BEGIN
                                   INSERT INTO COM_BASE_RECUP_CONSU
                                   SELECT regBase(x).cod_clie clie_cod_clie,
                                          regMovim(y).oid_movi_cc mvcc_oid_movi_cc,
                                                            regPeri(p).cod_peri_proc cam_proc,
                                                            regPeri(p).cod_peri_reca cam_reca,
                                          lnSumaCargos val_mont_carg,
                                          lnSumaAbonos val_mont_abon,
                                              lnSumaCDRS val_mont_cdrs,
                                          lnPorcentaje val_porc_recu,
                                          NULL val_nume_pedi,
                                          regBase(x).fec_limi_abon_cons fec_limi,
                                          LPAD(regBase(x).esta_oid_esta_clie,2,'0') cod_esta_clie,
                                          regBase(x).cod_regi cod_regi,
                                          regBase(x).cod_zona cod_zona,
                                          regBase(x).cod_secc cod_secc,
                                          psCodigoUsuario usu_crea,
                                          SYSDATE fec_crea,
                                          NULL usu_modi,
                                          NULL fec_modi
                                     FROM DUAL;
                               EXCEPTION WHEN dup_val_on_index THEN
                                   UPDATE COM_BASE_RECUP_CONSU brec
                                      SET brec.val_mont_carg = lnSumaCargos,
                                          brec.val_mont_abon = lnSumaAbonos,
                                              brec.val_mont_cdrs = lnSumaCDRS,
                                          brec.val_porc_recu = lnPorcentaje,
                                          brec.fec_limi = regBase(x).fec_limi_abon_cons,
                                          brec.cod_regi = regBase(x).cod_regi,
                                          brec.cod_zona = regBase(x).cod_zona,
                                          brec.cod_secc = regBase(x).cod_secc,
                                          brec.usu_modi = psCodigoUsuario,
                                          brec.fec_modi = SYSDATE
                                    WHERE 1=1
                                      AND brec.clie_cod_clie = regBase(x).cod_clie
                                                        AND brec.cam_proc = regPeri(p).cod_peri_proc
                                                        AND brec.cam_reca = regPeri(p).cod_peri_reca
                                      AND brec.mvcc_oid_movi_cc = regMovim(y).oid_movi_cc
                                                        AND TRUNC(brec.fec_limi) <= regBase(x).fec_limi_abon
                                        ;
                               END;
                               END IF;
-- Inicializar variables de acumulación
                              lnSumaCargos :=0;
                              lnSumaAbonos :=0;
                              lnSumaCDRS   :=0;
                              lnPorcentaje :=0;

                           END LOOP;
                        END IF;
                        EXIT WHEN c_Movim%NOTFOUND;
                     END LOOP;
                 CLOSE c_Movim;
             END LOOP;
          END IF;
          EXIT WHEN c_Base%NOTFOUND;
       END LOOP;
   CLOSE c_Base;
             END LOOP;
          END IF;
          EXIT WHEN c_Peri%NOTFOUND;
       END LOOP;
   CLOSE c_Peri;

  EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'COM_PR_GENER_BASE_RECUP_CONSU: '||ls_sqlerrm);
END COM_PR_GENER_BASE_RECUP_COBRA;

END COM_PKG_PROCE;
/
