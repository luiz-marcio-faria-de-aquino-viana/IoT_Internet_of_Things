/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 25/AGO/2022
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
 * AppMain.java
 */

package br.com.tlmv.emsdapp;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import br.com.tlmv.emsdapp.algor.EmergencySoundWiSARD;
import br.com.tlmv.emsdapp.builders.LoginBuilder;
import br.com.tlmv.emsdapp.builders.ModelUpdateBuilder;
import br.com.tlmv.emsdapp.builders.SendDataBuilder;
import br.com.tlmv.emsdapp.builders.events.ClientModelUpdateRequestEvent;
import br.com.tlmv.emsdapp.builders.events.ClientMultiRequestEvent;
import br.com.tlmv.emsdapp.builders.events.ClientWhatchdogRequestEvent;
import br.com.tlmv.emsdapp.builders.test.ClientSenderTest;
import br.com.tlmv.emsdapp.builders.test.ServerSenderTest;
import br.com.tlmv.emsdapp.dao.AudioDAO;
import br.com.tlmv.emsdapp.dao.ConstanteSistemaDAO;
import br.com.tlmv.emsdapp.dao.DAO;
import br.com.tlmv.emsdapp.dao.InstalacaoDAO;
import br.com.tlmv.emsdapp.dao.record.Audio;
import br.com.tlmv.emsdapp.dao.record.ConstanteSistema;
import br.com.tlmv.emsdapp.dao.record.Instalacao;
import br.com.tlmv.emsdapp.dao.record.Record;
import br.com.tlmv.emsdapp.data.ConnectionData;
import br.com.tlmv.emsdapp.utils.DateUtil;
import br.com.tlmv.emsdapp.vo.RecordVO;

public class AppMain {
//Private

	private static AppMain gApp = null;

	private AppContext context = null;
	
	private AppConfig config = null;
	
	private Locale localePtBr = new Locale(AppDefs.DEF_LANG_PT, AppDefs.DEF_COUNTRY_BR);

	private Locale localeEnUs = new Locale(AppDefs.DEF_LANG_EN, AppDefs.DEF_COUNTRY_US);
	
	private ConnectionData dbcon = null;
	
	private DAO dao = null;
	
	private Instalacao instalacao = null;
	
	private Hashtable<String,Record> mapConstanteSistema = null;
	
	private List<Audio> lsAudio = null; 
	
	// Parametros
	private Integer parmComandOptions = AppDefs.CMD_OPTION_NONE_VAL;

	private AppServerListener clientListener = null;
	
	private AppServerSender clientSender = null;

	private AppServerListener serverListener = null;
		
	private AppServerSender serverSender = null;
	
	private ClientMultiRequestEvent multiEvent = null;
	
	private ClientSenderTest clientTest = null;

	private ServerSenderTest serverTest = null;
	
	private EmergencySoundWiSARD emsTf = null;
	
	private String sessionUUID = null;
	private String modelUUID = null;
	
	private boolean bTestMode = false;
	
	private boolean loadInstalacao()
	{
		try {
			DAO dao = AppMain.getApp().getDAO();
			
			InstalacaoDAO id = new InstalacaoDAO(dao);
			instalacao = id.findInstalacaoByPk(AppDefs.DEF_INSTALACAO_PADRAO_ID);
			if(instalacao != null) 
				return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean loadAllConstanteSistema()
	{
		mapConstanteSistema = new Hashtable<String,Record>();
		
		try {
			DAO dao = AppMain.getApp().getDAO();
			
			ConstanteSistemaDAO csd = new ConstanteSistemaDAO(dao);
			List<ConstanteSistema> lsConstanteSistema = csd.findAllConstanteSistema();
			if(lsConstanteSistema != null) 
			{
				for(ConstanteSistema o : lsConstanteSistema)
					mapConstanteSistema.put(o.getConstanteSistemaId(), o);				
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean loadAllAudio()
	{
		try {
			DAO dao = AppMain.getApp().getDAO();
			
			AudioDAO ad = new AudioDAO(dao);
			lsAudio = ad.findAllAudio();
			if(lsAudio != null) 
				return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
				
	private boolean loadParams(String[] args)
	{
		boolean bResult = false;
		
		int n = 0;
		if(args.length > 0)
		{
			// Obtem Primeiro Parametro
			//
			String cmdParam = args[n++].toLowerCase();

			//COMMAND_LINE_OPTIONS
			//
			if( AppDefs.CMD_OPTION_HELP_KEY.equals(cmdParam) ) {
				parmComandOptions = AppDefs.CMD_OPTION_HELP_VAL;			
				
				showUsageMsg();		
				return false;
			}
			else {

				if( AppDefs.CMD_OPTION_CLIENT_KEY.equals(cmdParam) ) {
					parmComandOptions = AppDefs.CMD_OPTION_CLIENT_VAL;			
					bResult = true;
				}
				
				if( AppDefs.CMD_OPTION_SERVER_KEY.equals(cmdParam) ) {
					parmComandOptions = AppDefs.CMD_OPTION_SERVER_VAL;			
					bResult = true;
				}

				if( AppDefs.CMD_OPTION_CLIENT_SERVER_KEY.equals(cmdParam) ) {
					parmComandOptions = AppDefs.CMD_OPTION_CLIENT_SERVER_VAL;			
					bResult = true;
				}

				if( AppDefs.CMD_OPTION_TEST_KEY.equals(cmdParam) ) {
					parmComandOptions = AppDefs.CMD_OPTION_TEST_VAL;			
					bResult = true;
				}

				if( AppDefs.CMD_OPTION_TRAIN_KEY.equals(cmdParam) ) {
					parmComandOptions = AppDefs.CMD_OPTION_TRAIN_VAL;			
					bResult = true;
				}

			}
		}

		if( !bResult ) {
			String errmsg = AppError.ERR_INVALID_COMMAND_SWITCH;
			AppError.showCmdError(errmsg,  this.getClass());					
	
			showUsageMsg();
		}
		
		return bResult;
	}

	private boolean loadInitialData()
	{
		if( loadInstalacao() &&
			loadAllConstanteSistema() &&
			loadAllAudio() )
			return true;
		return false;
	}
	
//Public
	
	public boolean initContext()
	{
		boolean result = false;
		try {
			context = new AppContext();
			config = new AppConfig(context.getConfigFile());		
			
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean initDatabase()
	{
		try {
			Class.forName("org.postgresql.Driver");
			
			dao = new DAO(
				config.getDsName(),
				config.getDriver(),
				config.getUrl(),
				config.getUsername(),
				config.getPassword());
			
			return dao.open();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean testDatabase(
		String dsname,
		String driver,
		String url,
		String user,
		String pwd)
	{
		boolean result = false;
		
		DAO dao = new DAO(
			dsname,
			driver,
			url,
			user,
			pwd);

		if( dao.open() ) {
			dao.close();			
			result = true;
		}
		
		return result;
	}

	public void copyright()
	{
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		System.out.print(str); 
	}
	
	public void showUsageMsg()
	{
		String str = String.format("%s\n", 
			AppDefs.HLP_USAGE_INFO);
		System.out.print(str); 	
		System.exit(1);
	}

	public void init(String args[])
	{
		DateUtil.disableDaylightSaving();
		
		this.copyright();
	}
	
	public void doTrainTask()
	{
		bTestMode = true;
		
		//DO_TRAIN_TASK
		emsTf = new EmergencySoundWiSARD(bTestMode);
	}
	
	public void doTestTask()
	{
		bTestMode = false;
		
		//DO_TRAIN_TASK
		emsTf = new EmergencySoundWiSARD(bTestMode);
		String modelUUID = emsTf.saveModel();
		this.setModelUUID(modelUUID);

		//DO_SERVER_TASK
		String server_addr = instalacao.getNomeServidor();
		int server_inport = instalacao.getServidorPortaEntrada();
		int server_outport = instalacao.getServidorPortaSaida();
		
		serverListener = new AppServerListener(server_inport);
		serverListener.startThread();

		serverSender = new AppServerSender(server_addr, server_outport);
		serverSender.startThread();

		//DO_CLIENT_TASK
		int client_inport = instalacao.getClientePortaEntrada();
		int client_outport = instalacao.getClientePortaSaida();
		
		clientListener = new AppServerListener(client_inport);
		clientListener.startThread();

		clientSender = new AppServerSender(server_addr, client_outport);
		clientSender.startThread();
		
		//START_TEST
		//
		//LOGIN_REQUEST
		Integer remoteUnitId = AppDefs.DEF_REMOTEUNIT_PADRAO;
		String username = AppDefs.DEF_LOGIN_PADRAO;
		String password = AppDefs.DEF_PASSWORD_PADRAO;
				
		String xmlData = LoginBuilder.buildLoginRequestMsg(
			remoteUnitId,
			username,
			password);		

		clientSender.sendMessage(xmlData);		
		
		try {
			Thread.sleep(AppDefs.THREAD_MAX_SLEEP_TIME);
		}
		catch(Exception e) { }
		
		//MODELUPDATE_REQUEST
		String sessionUUID = AppMain.getApp().getSessionUUID();
				
		xmlData = ModelUpdateBuilder.buildModelUpdateRequestMsg(sessionUUID);		

		clientSender.sendMessage(xmlData);		
		
		try {
			Thread.sleep(AppDefs.THREAD_MAX_SLEEP_TIME_EX);
		}
		catch(Exception e) { }

		//MODELCLASSIFY_SOUND
		for(Audio oAudio : lsAudio) { 
			int result = emsTf.classify(oAudio);
			if(result == 1) {
				//MODELUPDATE_REQUEST
				sessionUUID = AppMain.getApp().getSessionUUID();
				
				String fileName = oAudio.getSliceFileName();
				String fileDataBase64 = AppDefs.DEF_ID_NONE_VALSTR;
				Long fileSize = -1L;
				Double latitude = 22.0 + (Math.random() * 5.0);
				Double longitude = 43.0 + (Math.random() * 5.0);
				
				xmlData = SendDataBuilder.buildSendDataRequestMsg(
					sessionUUID, 
					fileName, 
					fileSize, 
					fileDataBase64, 
					latitude, 
					longitude);		

				clientSender.sendMessage(xmlData);						
			}
			
			try {
				int elapsed_time = (int)Math.ceil(Math.random() * 10.0);
				System.out.println("*** Next audio in " + elapsed_time + " seconds...");

				int elapsed_time_mili = elapsed_time * 1000;
				Thread.sleep(elapsed_time_mili);
			}
			catch(Exception e) { }			
		}
		
		serverListener.waitThread();	
	}
		
	public void doClientServerTask()
	{
		bTestMode = false;
		
	}
	
	public void doClientTask()
	{
		bTestMode = false;
		
	}
	
	public void doServerTask()
	{
		bTestMode = false;
		
	}
	
	public void execute(String args[])
	{
		if( !initContext() ) {
			String errmsg = AppError.ERR_FALHA_INICIALIZACAO_CONTEXTO;
			AppError.showCmdError(errmsg, this.getClass());
		}
		
		if( !initDatabase() ) {
			String errmsg = AppError.ERR_FALHA_ABERTURA_BANCO_DADOS;
			AppError.showCmdError(errmsg, this.getClass());
		}
		
		if( !loadInitialData() ) {
			String errmsg = AppError.ERR_FALHA_LEITURA_DADOS_INICIAIS_BANCO_DADOS;
			AppError.showCmdError(errmsg, this.getClass());
		}
		
		if( loadParams(args) ) {
			if(	this.testParmComandOptions(AppDefs.CMD_OPTION_TEST_VAL) ) {
				doTestTask();
			}
			else if( this.testParmComandOptions(AppDefs.CMD_OPTION_CLIENT_SERVER_VAL) ) {
				doClientServerTask();										
			}
			else if( this.testParmComandOptions(AppDefs.CMD_OPTION_CLIENT_VAL) ) {
				doClientTask();							
			}
			else if( this.testParmComandOptions(AppDefs.CMD_OPTION_SERVER_VAL) ) {
				doServerTask();
			}
			else if( this.testParmComandOptions(AppDefs.CMD_OPTION_TRAIN_VAL) ) {
				doTrainTask();
			}
		}		
	}
	
	/* Getters/Setters */
	
	public static AppMain getApp()
	{
		return AppMain.gApp;
	}

	public AppContext getContext()
	{
		return context;
	}
	
	public AppConfig getConfig()
	{
		return config;
	}
	
	public Locale getPtBr()
	{
		return localePtBr;
	}

	public Locale getEnUs()
	{
		return localeEnUs;
	}

	public ConnectionData getDdCon() {
		return dbcon;
	}

	public DAO getDAO() {
		return dao;
	}

	public Instalacao getInstalacao() {
		return instalacao;
	}

	public Hashtable<String, Record> getMapConstanteSistema() {
		return mapConstanteSistema;
	}

	public Record getMapItemConstanteSistema(String key)
	{
		if( mapConstanteSistema.containsKey(key) )
		{
			Record item = mapConstanteSistema.get(key);
			return item;
		}
		return null;
	}
	
	public AppServerListener getClientListener() {
		return clientListener;
	}

	public void setClientListener(AppServerListener clientListener) {
		this.clientListener = clientListener;
	}

	public AppServerSender getClientSender() {
		return clientSender;
	}

	public void setClientSender(AppServerSender clientSender) {
		this.clientSender = clientSender;
	}

	public AppServerListener getServerListener() {
		return serverListener;
	}

	public void setServerListener(AppServerListener serverListener) {
		this.serverListener = serverListener;
	}

	public AppServerSender getServerSender() {
		return serverSender;
	}

	public void setServerSender(AppServerSender serverSender) {
		this.serverSender = serverSender;
	}

	public synchronized String getSessionUUID() {
		return sessionUUID;
	}

	public synchronized void setSessionUUID(String sessionUUID) {
		this.sessionUUID = sessionUUID;
	}

	public synchronized String getModelUUID() {
		return modelUUID;
	}

	public synchronized void setModelUUID(String modelUUID) {
		this.modelUUID = modelUUID;
	}

	public Integer getParmComandOptions() {
		return parmComandOptions;
	}

	public void setParmComandOptions(Integer parmComandOptions) {
		this.parmComandOptions = parmComandOptions;
	}

	public boolean testParmComandOptions(Integer parmCommandOptions) {
		if((this.parmComandOptions & parmComandOptions) == parmCommandOptions)
			return true;
		return false;
	}

	public EmergencySoundWiSARD getEmsTf() {
		return emsTf;
	}

	public void setEmsTf(EmergencySoundWiSARD emsTf) {
		this.emsTf = emsTf;
	}

	public boolean isTestMode() {
		return bTestMode;
	}

	public void setTestMode(boolean bTestMode) {
		this.bTestMode = bTestMode;
	}

	/* Main */

	public static void main(String[] args) 
	{
		gApp = new AppMain();
		gApp.init(args);
		gApp.execute(args);
	}
	
}
