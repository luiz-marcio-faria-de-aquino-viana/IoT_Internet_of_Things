/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 15/AGO/2022
 *
 * Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.
 * Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação
 * DRE: 120048833
 * CPF: 024.723.347-10
 * RG: 08855128-8 IFP-RJ
 * Registro: 2000103581 CREA-RJ
 * E-mail: luiz.marcio.viana@gmail.com
 *         lmarcio@cos.ufrj.br
 * Tel.: +55-21-99983-7207
 *
 * AppDefs.java
 */

package br.com.tlmv.emsdapp;

import java.util.Date;

public class AppDefs 
{
//Public
	
	//DEBUG_LEVEL
	//
	public static final int DEBUG_LEVEL00 										= 00;
	public static final int DEBUG_LEVEL01 										= 01;
	public static final int DEBUG_LEVEL02 										= 02;
	public static final int DEBUG_LEVEL03 										= 03;
	public static final int DEBUG_LEVEL04 										= 04;
	public static final int DEBUG_LEVEL05 										= 05;
	//
	public static final int DEBUG_LEVEL99 										= 99;
		
	public static final int DEBUG_LEVEL 										= AppDefs.DEBUG_LEVEL01;

	//RESULT_VALUES
	//
	public static final int RSOK = 0;
	public static final int RSERR = -1;
	
	//APPLICATION_NAME
	//
	public static final String APP_NAME 										= "EMSD: Emergency Sound Detector"; 
	
	public static final String APP_VERSAO 										= "1.0.20220923";

	public static final String APP_COPYRIGHT =
		"COPPE / UFRJ - Universidade Federal do Rio de Janeiro\n" +
		"PESC - Programa de Engenharia de Sistemas e Computacao\n" +
		"\n" +
		"Disciplina: CPS730 - Internet das Coisas\n" +
		"Professor: Claudio Miceli\n" +
		"\n" +
		"Trabalho Final - Emergency Sound Detection\n" +
		"Data: 23/SET/2022\n" +
		"\n" +
		"Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.\n" +
		"Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação\n" +
		"DRE: 120048833\n" +
		"CPF: 024.723.347-10\n" +
		"RG: 08855128-8 IFP-RJ\n" +
		"Registro: 2000103581 CREA-RJ\n" +
		"E-mail: luiz.marcio.viana@gmail.com\n" +
		"        lmarcio@cos.ufrj.br\n" +
		"Tel.: +55-21-99983-7207\n" +
		"\n";
	
	//ENVIROMENT_VARS
	//
	public static final String APP_HOME 									= "EMSD_HOME";
	
	//CONFIG_DIRS
	//
	//BASE_DIR
	public static final String APPL_DIR 									= "/Appl";
	public static final String BIN_DIR 										= "/Bin";
	public static final String CONFIG_DIR 									= "/Config";
	public static final String DATA_DIR 									= "/Data";
	public static final String DOCS_DIR 									= "/Docs";
	public static final String LIBS_DIR 									= "/Libs";
	public static final String LOG_DIR 										= "/Log";
	public static final String TEMP_DIR 									= "/Temp";
	public static final String TEMPLATES_DIR 								= "/Templates";
	public static final String TMP_DIR 										= "/Tmp";
	
	//DATA_DIR
	public static final String DATA_DATASET_DIR 							= "/Dataset";
	public static final String DATA_MESSAGES_DIR 							= "/Messages";
	public static final String DATA_MODELS_DIR 								= "/Models";
	public static final String DATA_SOUNDS_DIR 								= "/Sounds";
	
	//CONFIG_FILES
	//
	public static final String CONFIG_FILE									= "/emsd_appconfig.xml";
	
	//TRAIN_AND_TEST_SIZE
	//
	public static final int DEF_MODEL_TRAIN_SIZE							= 30;
	public static final int DEF_MODEL_TEST_SIZE								= 10;	
	
	//AUDIO_CLASSES_VAL
	//
	public static final int DEF_AUDIO_AIR_CONDITIONER_VAL					= 0;
	public static final int DEF_AUDIO_CAR_HORN_VAL							= 1;
	public static final int DEF_AUDIO_CHILDREN_PLAYING_VAL					= 2;
	public static final int DEF_AUDIO_DOG_PARK_VAL							= 3;
	public static final int DEF_AUDIO_DRILLING_VAL							= 4;
	public static final int DEF_AUDIO_ENGINE_IDLING_VAL						= 5;
	public static final int DEF_AUDIO_GUN_SHOT_VAL							= 6;
	public static final int DEF_AUDIO_JACKHAMMER_VAL						= 7;
	public static final int DEF_AUDIO_SIREN_VAL								= 8;
	public static final int DEF_AUDIO_STREET_MUSIC_VAL						= 9;

	//AUDIO_CLASSES_STR
	//
	public static final String DEF_AUDIO_AIR_CONDITIONER_STR				= "air_conditioner";
	public static final String DEF_AUDIO_CAR_HORN_STR						= "car_horn";
	public static final String DEF_AUDIO_CHILDREN_PLAYING_STR				= "children_playing";
	public static final String DEF_AUDIO_DOG_PARK_STR						= "dog_bark";
	public static final String DEF_AUDIO_DRILLING_STR						= "drilling";
	public static final String DEF_AUDIO_ENGINE_IDLING_STR					= "engine_idling";
	public static final String DEF_AUDIO_GUN_SHOT_STR						= "gun_shot";
	public static final String DEF_AUDIO_JACKHAMMER_STR						= "jackhammer";
	public static final String DEF_AUDIO_SIREN_STR							= "siren";
	public static final String DEF_AUDIO_STREET_MUSIC_STR					= "street_music";
	
	//TEMPLATE_MESSAGE_TYPE_VAL
	//
	//REQUEST
	public static final int TEMPL_REQUEST_LOGIN_VAL 						= 1001;
	public static final int TEMPL_REQUEST_SENDDATA_VAL 						= 1002;
	public static final int TEMPL_REQUEST_MODELUPDATE_VAL 					= 1003;
	public static final int TEMPL_REQUEST_WHATCHDOG_VAL 					= 1004;
	//RESPONSE
	public static final int TEMPL_RESPONSE_LOGIN_VAL 						= 2001;
	public static final int TEMPL_RESPONSE_SENDDATA_VAL 					= 2002;
	public static final int TEMPL_RESPONSE_MODELUPDATE_VAL 					= 2003;
	public static final int TEMPL_RESPONSE_WHATCHDOG_VAL 					= 2004;

	//TEMPLATE_MESSAGE_TYPE_STR
	//
	//REQUEST
	public static final String TEMPL_REQUEST_LOGIN_STR 						= "REQMSG_LOGIN";
	public static final String TEMPL_REQUEST_SENDDATA_STR 					= "REQMSG_SENDDATA";
	public static final String TEMPL_REQUEST_MODELUPDATE_STR 				= "REQMSG_MODELUPDATE";
	public static final String TEMPL_REQUEST_WHATCHDOG_STR 					= "REQMSG_WATCHDOG";
	//RESPONSE
	public static final String TEMPL_RESPONSE_LOGIN_STR 					= "RESPMSG_LOGIN";
	public static final String TEMPL_RESPONSE_SENDDATA_STR 					= "RESPMSG_SENDDATA";
	public static final String TEMPL_RESPONSE_MODELUPDATE_STR 				= "RESPMSG_MODELUPDATE";
	public static final String TEMPL_RESPONSE_WHATCHDOG_STR 				= "RESPMSG_WHATCHDOG";

	//TEMPLATE_FILES
	//
	//REQUEST
	public static final String TEMPL_REQUEST_LOGIN_FILE 					= "LoginRequest.templ";
	public static final String TEMPL_REQUEST_SENDDATA_FILE 					= "SendDataRequest.templ";
	public static final String TEMPL_REQUEST_MODELUPDATE_FILE 				= "ModelUpdateRequest.templ";
	public static final String TEMPL_REQUEST_WHATCHDOG_FILE 				= "WatchdogRequest.templ";
	//RESPONSE
	public static final String TEMPL_RESPONSE_LOGIN_FILE 					= "LoginResponse.templ";
	public static final String TEMPL_RESPONSE_SENDDATA_FILE 				= "SendDataResponse.templ";
	public static final String TEMPL_RESPONSE_MODELUPDATE_FILE 				= "ModelUpdateResponse.templ";
	public static final String TEMPL_RESPONSE_WHATCHDOG_FILE 				= "WatchdogResponse.templ";
		
	//DEFAULT_RGB
	//
	public static final int DEF_AUDIO_LEVEL									= 1;	
	public static final int DEF_AUDIO_DEPTH									= 8;	
	public static final double DEF_AUDIO_MAX_VAL							= 256.0;
	public static final int DEF_AUDIO_MAX_LEN								= 4096;
	public static final double DEF_AUDIO_THRESHOLD_MIN						= 9.0;	
	public static final double DEF_AUDIO_THRESHOLD_MAX						= 12.0;	
	public static final double DEF_AUDIO_MAX_EXEC							= 50;	

	//APPLICATION PARAMS
	//
	public static final String APP_ICON 									= "/br/com/tlmv/emsdapp/res/emsdapp_48x48.png";
	
	//DEFAULT_LANG
	//
	public static final String DEF_LANG_PT = "Pt";	
	public static final String DEF_LANG_EN = "En";
	
	//DEFAULT_COUNTRY
	//
	public static final String DEF_COUNTRY_BR = "Br";
	public static final String DEF_COUNTRY_US = "Us";	
	
	public static final Integer THREAD_MAX_SLEEP_TIME_EX 					= 60 * 1000;	/* 60 segundo(s) */
	public static final Integer THREAD_MAX_SLEEP_TIME 						= 1 * 1000;		/* 1 segundo(s) */
	public static final Integer THREAD_MIN_SLEEP_TIME 						= 50;			/* 50 milisegundo(s) */
	
	//DEFAULT_INSTALATION
	//
	public static final int DEF_INSTALACAO_PADRAO_ID 						= 1;		
	public static final String DEF_INSTALACAO_PADRAO_ID_STR 				= "1";		
	
	//DEFAULT_USUARIO
	//
	public static final int DEF_USUARIO_PADRAO_ID 							= 2;		
	public static final String DEF_USUARIO_PADRAO_ID_STR 					= "2";		
	public static final String DEF_LOGIN_PADRAO 							= "lmarcio";
	public static final String DEF_PASSWORD_PADRAO 							= "lmarcio";
	public static final String DEF_NOME_USUARIO_PADRAO 						= "Luiz Marcio";
	public static final String DEF_SOBRENOME_USUARIO_PADRAO 				= "Faria de Aquino Viana";	
	public static final int DEF_REMOTEUNIT_PADRAO 							= 1001;	
	
	//SEQUENCE_DEFINITIONS
	//
	//USUSARIO
	public static final String DEF_SEQ_USUARIO								= "SEQ_USUARIO";
	public static final int DEF_SEQ_USUARIO_VALOR_INICIAL					= 100001;
	public static final int DEF_SEQ_USUARIO_VALOR_FINAL						= 999999;		
	//DEVICE
	public static final String DEF_SEQ_DEVICE								= "SEQ_DEVICE";
	public static final int DEF_SEQ_DEVICE_VALOR_INICIAL					= 100001;
	public static final int DEF_SEQ_DEVICE_VALOR_FINAL						= 999999;		
	
	//INET_ADDRESS_AND_PORT
	//
	public static final String DEF_SERVER_HOSTNAME 							= "emsdapp.tlmv.com.br";
	public static final int DEF_SERVER_PORT 								= 9990;		
	
	//REQUEST_METHOD
	//
	public static final String DEF_REQUEST_METHOD_GET 						= "GET";
	public static final String DEF_REQUEST_METHOD_POST 						= "POST";
	public static final String DEF_REQUEST_METHOD_PUT						= "PUT";
	public static final String DEF_REQUEST_METHOD_DELETE 					= "DELETE";
	
	//CONNECTION_TIMEOUT
	//
	public static final Integer DEF_CONNECTION_TIMEOUT 						= 30 * 1000;
	
	//CSV_DELIMS
	//
	public static final char CSV_DATADELIM 									= ',';	
	public static final char CSV_TXTDELIM									= '"';
	
	//SOCKET_DATAGRAM
	//
	public static final int SOCKET_INPORT 									= 9191;
	public static final int SOCKET_OUTPORT 									= 9192;

	//SOCKET_DATAGRAM
	//	
	public static int BUFFER_SIZE 											= 65536;

	//DATABASE
	//
	public static final String PG_DSNAME 									= "PG_EMSDDB";
	public static final String PG_DRIVER 									= "PostgreSQL";
	public static final String PG_URL 										= "jdbc:postgresql://localhost/emsddb";
	public static final String PG_USER 										= "postgres";
	public static final String PG_PWD 										= "sdbddedcf";
	
	//COMMAND_LINE_OPTIONS: KEYS
	//
	public static final String CMD_OPTION_CLIENT_KEY 						= "-client";
	public static final String CMD_OPTION_SERVER_KEY 						= "-server";
	public static final String CMD_OPTION_CLIENT_SERVER_KEY 				= "-client_server";
	public static final String CMD_OPTION_TEST_KEY 							= "-test";
	public static final String CMD_OPTION_TRAIN_KEY 						= "-train";
	public static final String CMD_OPTION_HELP_KEY 							= "-help";
	
	//COMMAND_LINE_OPTIONS: VALUES
	//
	public static final int CMD_OPTION_NONE_VAL 							= 0;
	public static final int CMD_OPTION_CLIENT_VAL 							= 1;
	public static final int CMD_OPTION_SERVER_VAL 							= 2;
	public static final int CMD_OPTION_CLIENT_SERVER_VAL 					= 4;
	public static final int CMD_OPTION_TEST_VAL 							= 8;
	public static final int CMD_OPTION_TRAIN_VAL 							= 16;
	public static final int CMD_OPTION_HELP_VAL 							= 32;
		
	//USAGE_INFO
	//
	public static final String HLP_USAGE_INFO =
		    "\n" +
		    "AJUDA\n\n" +
			"Use: EMSD -client  		;; para executar em modo cliente\n" +
			" ou  EMSD -server  		;; para executar em modo servidor\n" +
			" ou  EMSD -client_server  	;; para executar em modo cliente servidor\n" +
			" ou  EMSD -test    		;; para executar em modo de teste\n" +
			" ou  EMSD -train    		;; para executar em modo de treinamento do modelo\n" +
			" ou  EMSD -help    		;; para obter ajuda\n";

	//TEST_EXECUTION
	//
	public static int DEF_MAX_SOUND_STEP									= 6000;
	public static int DEF_MAX_TEST_ITER										= 1;
	public static int DEF_MAX_TEST_DELAY_EX									= 5000;			
	public static int DEF_MAX_TEST_DELAY									= 1000;			
	public static int DEF_MIN_TEST_DELAY									= 50;			
	
	//EXECUTION_DELAY
	//
	public static int DEF_MODELUPDATE_STEP									= 60;			
	public static int DEF_WHATCHDOG_STEP									= 10;			
	
	//DATA_HORA_PADRAO
	//
	public static final String DEF_DATA_HORA_PADRAO 						= "1970-01-01T00:00:00";
	public static final String DEF_DATA_PADRAO 								= "1970-01-01";
	
	//CODIGO_PROJETO / NUMERO_PEDIDO
	//
	public static final String DEF_DEFAULT_REQUEST_IDENTIFIER 				= "YYYYMMDD_HHmmss_999999";					// 22 digits [0-9]
	public static final String DEF_DEFAULT_REMOTE_UNIT_IDENTIFIER 			= "HHHHHHHHHHHH";							// 12 hexadecimal digits [0-9][A-F]
	public static final String DEF_DEFAULT_SESSION_IDENTIFIER 				= "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";		// 32 hexadecimal digits [0-9][A-F]
	public static final String DEF_DEFAULT_FILE_IDENTIFIER 					= "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";		// 32 hexadecimal digits [0-9][A-F]
	public static final String DEF_DEFAULT_MODEL_IDENTIFIER 				= "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";		// 32 hexadecimal digits [0-9][A-F]
	
	//CONSTANTE_SISTEMA
	//		
	public static final String cServerHostname 								= "ServerHostname";
	public static final String cServerPort 									= "ServerPort";
	//
	public static final String cAnoBase 									= "AnoBase";
	public static final String cMaxAnos 									= "MaxAnos";
	//
	public static final String cMimeTypePadrao 								= "MimeTypePadrao";
	public static final String cMimeTypeTextXml 							= "MimeTypeTextXml";
	public static final String cMimeTypeSoundWave 							= "MimeTypeSoundWave";
	//
	public static final String cDefaultThumbnailMimeType 					= "DefaultThumbnailMimeType";
	//
	public static final String cNumMaxMensagens 							= "NumMaxMensagens";
	public static final String cEnableMessageQueue 							= "EnableMessageQueue";
	//
	public static final String cNumMaxItens 								= "NumMaxItens";
	//
	public static final String cDebugJavaMail 								= "DebugJavaMail";
	//
	public static final String cServidorPOP 								= "ServidorPOP";
	public static final String cPortaPOP 									= "PortaPOP";
	//
	public static final String cServidorSMTP 								= "ServidorSMTP";
	public static final String cPortaSMTP 									= "PortaSMTP";
	public static final String cUsuarioSMTP 								= "UsuarioSMTP";
	public static final String cSenhaSMTP 									= "SenhaSMTP";
	//
	public static final String cEmailAplicacao 								= "EmailAplicacao";
	//		
	public static final String cTempoMaxSessao 								= "TempoMaxSessao";
	public static final String cTimeoutSessao 								= "TimeoutSessao";
	//
	public static final String cVersao 										= "Versao";
	
	//ID
	public static final int DEF_ID_NONE_VALINT 								= -1;
	public static final String DEF_ID_NONE_VALSTR 							= "-1";

	//XML_TAG
	//
	//CONFIG
	public static final String CFGTAG_CONFIGURACAO 								= "Configuracao";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION 			= "DatabaseConnection";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DSNAME 	= "Dsname";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DRIVER 	= "Driver";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_URL 		= "Url";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_USERNAME = "Username";
	public static final String CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_PASSWORD	= "Password";
	//REQUEST
	public static final String XMLTAG_REQUEST 									= "Request";
	public static final String XMLTAG_REQUEST_REQUEST_ID			 			= "RequestId";
	public static final String XMLTAG_REQUEST_COMMAND 							= "Command";
	public static final String XMLTAG_REQUEST_PARAMS						 	= "Params";
	public static final String XMLTAG_REQUEST_PARAMS_REMOTEUNIT_ID 				= "RemoteUnitId";
	public static final String XMLTAG_REQUEST_PARAMS_USERNAME 					= "Username";
	public static final String XMLTAG_REQUEST_PARAMS_PASSWORD					= "Password";
	public static final String XMLTAG_REQUEST_PARAMS_SESSION_UUID				= "SessionUUID";
	public static final String XMLTAG_REQUEST_PARAMS_FILE_NAME					= "FileName";
	public static final String XMLTAG_REQUEST_PARAMS_FILE_SIZE					= "FileSize";
	public static final String XMLTAG_REQUEST_PARAMS_FILE_DATA_BASE64			= "FileDataBase64";
	public static final String XMLTAG_REQUEST_POSITION							= "Position";
	public static final String XMLTAG_REQUEST_POSITION_LATITUDE					= "Latitude";
	public static final String XMLTAG_REQUEST_POSITION_LONGITUDE				= "Longitude";
	//RESPONSE
	public static final String XMLTAG_RESPONSE 									= "Response";
	public static final String XMLTAG_RESPONSE_RESPONSE_ID			 			= "ResponseId";
	public static final String XMLTAG_RESPONSE_RESPONSE 						= "Response";
	public static final String XMLTAG_RESPONSE_STATUS						 	= "Status";
	public static final String XMLTAG_RESPONSE_STATUS_STATUS_CODE 				= "StatusCode";
	public static final String XMLTAG_RESPONSE_STATUS_STATUS_MESSAGE 			= "StatusMessage";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA					= "ResponseData";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_SESSION_UUID		= "SessionUUID";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_UUID			= "ModelUUID";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_DATE_TIME	= "ModelDateTime";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_DATA_BASE64	= "ModelDataBase64";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_FILE_UUID			= "FileUUID";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_SESSION_DATE_TIME	= "SessionDateTime";
	public static final String XMLTAG_RESPONSE_RESPONSE_DATA_SESSION_TIME_MILI	= "SessionTimeMili";	

	//MESSAGE_TYPE
	//
	//REQUEST
	public static final int REQMSG_MINVAL	 									= 1001;
	public static final int REQMSG_MAXVAL 										= 1999;
	//REQUEST_MESSAGE
	public static final int REQMSG_LOGIN_VAL 									= 1001;
	public static final int REQMSG_SENDDATA_VAL 								= 1002;
	public static final int REQMSG_MODELUPDATE_VAL 								= 1003;
	public static final int REQMSG_WHATCHDOG_VAL 								= 1004;
	//RESPONSE
	public static final int RESPMSG_MINVAL	 									= 2001;
	public static final int RESPMSG_MAXVAL 										= 2999;
	//RESPONSE_MESSAGE
	public static final int RESPMSG_LOGIN_VAL 									= 2001;
	public static final int RESPMSG_SENDDATA_VAL 								= 2002;
	public static final int RESPMSG_MODELUPDATE_VAL 							= 2003;
	public static final int RESPMSG_WHATCHDOG_VAL 								= 2004;
	
	//MESSAGE_TYPE_STR
	//
	//REQUEST_MESSAGE
	public static final String REQMSG_LOGIN_STR 								= "REQMSG_LOGIN";
	public static final String REQMSG_SENDDATA_STR 								= "REQMSG_SENDDATA";
	public static final String REQMSG_MODELUPDATE_STR 							= "REQMSG_MODELUPDATE";
	public static final String REQMSG_WHATCHDOG_STR 							= "REQMSG_WHATCHDOG";
	//RESPONSE_MESSAGE
	public static final String RESPMSG_LOGIN_STR 								= "RESPMSG_LOGIN";
	public static final String RESPMSG_SENDDATA_STR 							= "RESPMSG_SENDDATA";
	public static final String RESPMSG_MODELUPDATE_STR 							= "RESPMSG_MODELUPDATE";
	public static final String RESPMSG_WHATCHDOG_STR 							= "RESPMSG_WHATCHDOG";
	
	//MESSAGE_PARAMS: BASE (REQUEST / RESPONSE)
	//
	public static final String FLD_REQRESPMSG_BASE_MESSAGE_ID 					= "#TAG_MESSAGE_ID#";
	public static final String FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID 				= "#TAG_MESSAGE_TYPE_ID#";
	public static final String FLD_REQRESPMSG_BASE_MESSAGE_TYPE 				= "#TAG_MESSAGE_TYPE#";
	public static final String FLD_REQRESPMSG_BASE_STATUS_CODE 					= "#TAG_STATUS_CODE#";
	public static final String FLD_REQRESPMSG_BASE_STATUS_MESSAGE 				= "#TAG_STATUS_MESSAGE#";

	//MESSAGE_PARAMS: REQUEST
	//
	//TAG_REQ_LOGIN
	public static final String FLD_REQMSG_LOGIN_REMOTE_UNIT_ID					= "#TAG_REMOTE_UNIT_ID#";
	public static final String FLD_REQMSG_LOGIN_USERNAME 						= "#TAG_USERNAME#";
	public static final String FLD_REQMSG_LOGIN_PASSWORD 						= "#TAG_PASSWORD#";		
	//TAG_REQ_SENDDATA
	public static final String FLD_REQMSG_SENDDATA_SESSION_UUID 				= "#TAG_SESSION_UUID#";
	public static final String FLD_REQMSG_SENDDATA_FILE_NAME 					= "#TAG_FILE_NAME#";
	public static final String FLD_REQMSG_SENDDATA_FILE_SIZE 					= "#TAG_FILE_SIZE#";
	public static final String FLD_REQMSG_SENDDATA_FILE_DATA_BASE64 			= "#TAG_FILE_DATA_BASE64#";
	public static final String FLD_REQMSG_SENDDATA_LATITUDE	 					= "#TAG_LATITUDE#";
	public static final String FLD_REQMSG_SENDDATA_LONGITUDE 					= "#TAG_LONGITUDE#";
	//TAG_REQ_MODELUPDATE
	public static final String FLD_REQMSG_MODELUPDATE_SESSION_UUID 				= "#TAG_SESSION_UUID#";
	//TAG_REQ_WHATCHDOG
	public static final String FLD_REQMSG_WHATCHDOG_SESSION_UUID 				= "#TAG_SESSION_UUID#";

	//MESSAGE_PARAMS: RESPONSE
	//
	//TAG_RESP_LOGIN
	public static final String FLD_RESPMSG_LOGIN_SESSION_UUID 					= "#TAG_SESSION_UUID#";
	//TAG_RESP_SENDDATA
	public static final String FLD_RESPMSG_SENDDATA_SESSION_UUID 				= "#TAG_SESSION_UUID#";
	public static final String FLD_RESPMSG_SENDDATA_FILE_UUID 					= "#TAG_FILE_UUID#";
	//TAG_RESP_MODELUPDATE
	public static final String FLD_RESPMSG_MODELUPDATE_SESSION_UUID 			= "#TAG_SESSION_UUID#";
	public static final String FLD_RESPMSG_MODELUPDATE_MODEL_UUID 				= "#TAG_MODEL_UUID#";
	public static final String FLD_RESPMSG_MODELUPDATE_MODEL_DATETIME 			= "#TAG_MODEL_DATE_TIME#";
	public static final String FLD_RESPMSG_MODELUPDATE_MODEL_DATA_BASE64		= "#TAG_MODEL_DATA_BASE64#";
	//TAG_RESP_WHATCHDOG
	public static final String FLD_RESPMSG_WHATCHDOG_SESSION_UUID 				= "#TAG_SESSION_UUID#";
	public static final String FLD_RESPMSG_WHATCHDOG_SESSION_DATETIME 			= "#TAG_SESSION_DATE_TIME#";
	public static final String FLD_RESPMSG_WHATCHDOG_SESSION_TIME_MILI 			= "#TAG_SESSION_TIME_MILI#";
	
	//DATE_TIME
	//
	public static final Date DEF_MIN_DATE_TIME 									= new Date(0, 0, 1);
	public static final long DEF_MIN_DATE_TIME_MILI 							= AppDefs.DEF_MIN_DATE_TIME.getTime();
	//
	public static final long M01 												= 1L * 60L * 1000L; 
	public static final long M05 												= 5L * 60L * 1000L; 
	public static final long M15 												= 15L * 60L * 1000L; 
	public static final long M30 												= 30L * 60L * 1000L; 
	public static final long M45 												= 45L * 60L * 1000L; 
	public static final long M60 												= 60L * 60L * 1000L; 
	//	
	public static final long H01 												= 1L * 60L * 60L * 1000L; 
	public static final long H24 												= 24L * 60L * 60L * 1000L; 
	//
	public static final long D01 												= 1L * H24; 
	public static final long D02 												= 2L * H24; 
	public static final long D03 												= 3L * H24; 
	public static final long D04 												= 4L * H24; 
	public static final long D05 												= 5L * H24; 
	public static final long D06 												= 6L * H24; 
	public static final long D07 												= 7L * H24; 
	public static final long D08 												= 8L * H24; 
	public static final long D09 												= 9L * H24; 
	public static final long D10 												= 10L * H24; 
	public static final long D15 												= 15L * H24; 
	public static final long D20 												= 20L * H24; 
	public static final long D25 												= 25L * H24; 
	public static final long D30 												= 30L * H24; 
	public static final long D45 												= 45L * H24; 
	public static final long D60 												= 60L * H24; 
	public static final long D90 												= 90L * H24; 
	public static final long D180 												= 180L * H24; 
	
	//OPCAO_SIM/NAO
	//
	public static final String DEF_OPCAO_SIM 									= "S";
	public static final String DEF_OPCAO_NAO 									= "N";
		
	//OPCAO_SIM/NAO_NUMBER
	//
	public static final Integer DEF_OPCAO_SIM_NUM 								= 1;
	public static final Integer DEF_OPCAO_NAO_NUM 								= 2;
		
	//OPCAO_SIM/NAO_STRNUMBER
	//
	public static final String DEF_OPCAO_SIM_STRNUM 							= Integer.toString(AppDefs.DEF_OPCAO_SIM_NUM);
	public static final String DEF_OPCAO_NAO_STRNUM 							= Integer.toString(AppDefs.DEF_OPCAO_NAO_NUM);
		
	//FORMAT_MASK
	//
	public static final String DEF_TIME_MASC 									= "yHH:mm:ss";
	public static final String DEF_TIME_MASC_SIMPL 								= "HHmmss";
	public static final String DEF_DATE_MASC 									= "dd/MM/yyyy";
	public static final String DEF_DATE_MASC_INV 								= "yyyyMMdd";
	public static final String DEF_DATE_MASC_INV_SEP 							= "yyyy-MM-dd";
	public static final String DEF_DATETIME_MASC 								= "dd/MM/yyyy HH:mm:ss";
	public static final String DEF_DATETIME_MASC_INV 							= "yyyyMMdd_HHmmss";
	public static final String DEF_DATETIME_FILE_MASC 							= "yyyyMMdd_HHmmss";
	public static final String DEF_DATETIME_FILE_MASC_SIMPL 					= "yyyyMMddHHmmss";
	
	//SEQ_INIT_VALUES
	//
	public static final int DEF_SEQ_ID 											=  -1;
	public static final String DEF_SEQ_NOME 									= "[NOME_SEQUENCIA]";
	public static final int DEF_SEQ_VALOR_INICIAL 								= 100001;
	public static final int DEF_SEQ_VALOR_FINAL 								= 999999;

	//SEQUENCIAS
	//
	public static final String DEF_SEQ_MESSAGE 									= "SEQ_MESSAGE";
	
	//ERRMSG_TYPE
	//
	public static final int DEF_ERRMSG_TYPE_NONE 								= 0;
	public static final int DEF_ERRMSG_TYPE_WARN 								= 1;
	public static final int DEF_ERRMSG_TYPE_ERROR 								= 2;

	//VALID_RESULT_CODE
	//
	public static final int RSCODE_NONE	 										= -1;
	//RSCODE: SUCESS
	public static final int RSCODE_SUCESS	 									= 0;
	//RSCODE: ERROR
	public static final int RSCODE_ERROR_INVALID_SESSION_UUID	 				= 101;
	public static final int RSCODE_ERROR_INVALID_USERNAME_PASSWORD	 			= 102;
	public static final int RSCODE_ERROR_INVALID_DEVICE_ID	 					= 103;

	//VALID_RESULT_MESSAGE
	//
	public static final String RSMSG_NONE 										= "?";
	//RSCODE: SUCESS
	public static final String RSMSG_SUCESS	 									= "Success";
	//RSCODE: ERROR
	public static final String RSMSG_ERROR_INVALID_SESSION_UUID	 				= "Invalid session";
	public static final String RSMSG_ERROR_INVALID_USERNAME_PASSWORD	 		= "Invalid username or password";
	public static final String RSMSG_ERROR_INVALID_DEVICE_ID	 				= "Invalid device";

	//VALID_EXTENSIONS
	//
	public static final String DEF_EXT_XML 										= ".xml";
	public static final String DEF_EXT_DOC 										= ".doc";
	public static final String DEF_EXT_DOCX 									= ".docx";
	public static final String DEF_EXT_XLS 										= ".xls";
	public static final String DEF_EXT_XLSX 									= ".xlsx";
	public static final String DEF_EXT_PDF 										= ".pdf";
	public static final String DEF_EXT_ZIP 										= ".zip";
	public static final String DEF_EXT_TXT 										= ".txt";
	public static final String DEF_EXT_WAV 										= ".wav";
	
	//VALID_FILE_TYPES
	//
	public static final String DEF_FILE_TYPE_XML 								= "XML";
	public static final String DEF_FILE_TYPE_DOC 								= "DOC";
	public static final String DEF_FILE_TYPE_DOCX 								= "DOCX";
	public static final String DEF_FILE_TYPE_XLS 								= "XLS";
	public static final String DEF_FILE_TYPE_XLSX 								= "XLSX";
	public static final String DEF_FILE_TYPE_PDF 								= "PDF";
	public static final String DEF_FILE_TYPE_ZIP 								= "ZIP";
	public static final String DEF_FILE_TYPE_TXT 								= "TXT";
	public static final String DEF_FILE_TYPE_WAV 								= "WAV";
	
	//VALID_MIME_TYPES
	//
	public static final String DEF_MIME_TYPE_XML 								= "text/xml";
	public static final String DEF_MIME_TYPE_DOC 								= "application/msword";
	public static final String DEF_MIME_TYPE_DOCX 								= "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public static final String DEF_MIME_TYPE_XLS 								= "application/vnd.ms-excel";
	public static final String DEF_MIME_TYPE_XLSX 								= "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String DEF_MIME_TYPE_PDF 								= "application/pdf";
	public static final String DEF_MIME_TYPE_ZIP 								= "application/zip";
	public static final String DEF_MIME_TYPE_TXT 								= "text/plain";
	public static final String DEF_MIME_TYPE_WAV 								= "audio/wav";
	
	//CharCode
	//
	public static final String defCharcodeUtf8 									= "UTF-8";
	public static final String defCharcodeISO8859_1 							= "ISO-8859-1";
	
	//SCHEMA_URI
	//
	//public static final String defXmlVersion 									= "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>";
	public static final String defXmlVersion									= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	//MONTH_DEFS
	//
	//COMPL
	public static final String[] LS_MONTHS_COMPL = {
		"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Dicember"	
	};
	//SIMPL
	public static final String[] LS_MONTHS_SIMPL = {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dic"	
	};
	
}
