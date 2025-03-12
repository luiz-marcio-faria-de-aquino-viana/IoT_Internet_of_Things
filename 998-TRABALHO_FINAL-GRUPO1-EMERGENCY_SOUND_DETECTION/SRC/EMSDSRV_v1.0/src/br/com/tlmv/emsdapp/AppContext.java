/*
* COPPE / UFRJ - Universidade Federal do Rio de Janeiro
* PESC - Programa de Engenharia de Sistemas e Computacao
*
* Disciplina: CPS730 - Internet das Coisas
* Professor: Claudio Miceli
*
* Trabalho Final - Emergency Sound Detection
* Data: 24/AGO/2022
*
* Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.
* Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação
* DRE: 120048833
* CPF: 024.723.347-10
* RG: 08855128-8 IFP-RJ
* Registro: 2000103581 CREA-RJ
* E-mail: luiz.marcio.viana@gmail.com
*
lmarcio@cos.ufrj.br
* Tel.: +55-21-99983-7207
*
* AppContext.java
*/

package br.com.tlmv.emsdapp;

public class AppContext 
{
//Private	
	//ENVIROMENT_VARS
	private String appHome = null;
	
	//BASE_DIR
	private String applDir = null;
	private String binDir = null;
	private String configDir = null;
	private String dataDir = null;
	private String docsDir = null;
	private String libsDir = null;
	private String logDir = null;
	private String tempDir = null;
	private String templatesDir = null;
	private String tmpDir = null;

	//DATA_DIR
	private String dataDatasetDir = null;
	private String dataMessagesDir = null;
	private String dataModelsDir = null;
	private String dataSoundsDir = null;

	//CONFIG_FILES
	//
	public String configFile = null;
	
	//TEMPLATE_FILES
	//
	//REQUEST
	public String templReqLoginFile = null;
	public String templReqSendDataFile = null;
	public String templReqModelUpdateFile = null;
	public String templReqWhatchdogFile = null;

	//RESPONSE
	public String templRespLoginFile = null;
	public String templRespSendDataFile = null;
	public String templRespModelUpdateFile = null;
	public String templRespWhatchdogFile = null;
		
//Public
	
	public AppContext()
	{
		init();
	}

	public void init()
	{
		//ENVIROMENT_VARS
		this.appHome = System.getenv(AppDefs.APP_HOME);
		
		//BASE_DIR
		this.applDir = this.appHome + AppDefs.APPL_DIR;
		this.binDir = this.appHome + AppDefs.BIN_DIR;
		this.configDir = this.appHome + AppDefs.CONFIG_DIR;
		this.dataDir = this.appHome + AppDefs.DATA_DIR;
		this.docsDir = this.appHome + AppDefs.DOCS_DIR;
		this.libsDir = this.appHome + AppDefs.LIBS_DIR;
		this.logDir = this.appHome + AppDefs.LOG_DIR;
		this.tempDir =  this.appHome + AppDefs.TEMP_DIR;
		this.templatesDir =  this.appHome + AppDefs.TEMPLATES_DIR;
		this.tmpDir =  this.appHome + AppDefs.TMP_DIR;

		//DATA_DIR
		this.dataDatasetDir = this.dataDir + AppDefs.DATA_DATASET_DIR;
		this.dataMessagesDir = this.dataDir + AppDefs.DATA_MESSAGES_DIR;
		this.dataModelsDir = this.dataDir + AppDefs.DATA_MODELS_DIR;
		this.dataSoundsDir = this.dataDir + AppDefs.DATA_SOUNDS_DIR;

		//CONFIG_FILES
		//
		this.configFile =  this.configDir + AppDefs.CONFIG_FILE;	
				
		//TEMPLATE_FILES
		//
		//REQUEST
		this.templReqLoginFile = this.templatesDir + "/" + AppDefs.TEMPL_REQUEST_LOGIN_FILE;
		this.templReqSendDataFile = this.templatesDir + "/" + AppDefs.TEMPL_REQUEST_SENDDATA_FILE;
		this.templReqModelUpdateFile = this.templatesDir + "/" + AppDefs.TEMPL_REQUEST_MODELUPDATE_FILE;
		this.templReqWhatchdogFile = this.templatesDir + "/" + AppDefs.TEMPL_REQUEST_WHATCHDOG_FILE;

		//RESPONSE
		this.templRespLoginFile = this.templatesDir + "/" + AppDefs.TEMPL_RESPONSE_LOGIN_FILE;
		this.templRespSendDataFile = this.templatesDir + "/" + AppDefs.TEMPL_RESPONSE_SENDDATA_FILE;
		this.templRespModelUpdateFile = this.templatesDir + "/" + AppDefs.TEMPL_RESPONSE_MODELUPDATE_FILE;
		this.templRespWhatchdogFile = this.templatesDir + "/" + AppDefs.TEMPL_RESPONSE_WHATCHDOG_FILE;
		
	}
	
	/* Getters/Setters */
	
	public String getAppHome() {
		return appHome;
	}

	public void setAppHome(String appHome) {
		this.appHome = appHome;
	}

	public String getApplDir() {
		return applDir;
	}

	public void setApplDir(String applDir) {
		this.applDir = applDir;
	}

	public String getBinDir() {
		return binDir;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;
	}

	public String getConfigDir() {
		return configDir;
	}

	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getDocsDir() {
		return docsDir;
	}

	public void setDocsDir(String docsDir) {
		this.docsDir = docsDir;
	}

	public String getLibsDir() {
		return libsDir;
	}

	public void setLibsDir(String libsDir) {
		this.libsDir = libsDir;
	}

	public String getLogDir() {
		return logDir;
	}

	public void setLogDir(String logDir) {
		this.logDir = logDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	public String getTemplatesDir() {
		return templatesDir;
	}

	public void setTemplatesDir(String templatesDir) {
		this.templatesDir = templatesDir;
	}
		
	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getDataDatasetDir() {
		return dataDatasetDir;
	}

	public void setDataDatasetDir(String dataDatasetDir) {
		this.dataDatasetDir = dataDatasetDir;
	}

	public String getDataMessagesDir() {
		return dataMessagesDir;
	}

	public void setDataMessagesDir(String dataMessagesDir) {
		this.dataMessagesDir = dataMessagesDir;
	}

	public String getDataModelsDir() {
		return dataModelsDir;
	}

	public void setDataModelsDir(String dataModelsDir) {
		this.dataModelsDir = dataModelsDir;
	}

	public String getDataSoundsDir() {
		return dataSoundsDir;
	}

	public void setDataSoundsDir(String dataSoundsDir) {
		this.dataSoundsDir = dataSoundsDir;
	}

	public String getTemplReqLoginFile() {
		return templReqLoginFile;
	}

	public void setTemplReqLoginFile(String templReqLoginFile) {
		this.templReqLoginFile = templReqLoginFile;
	}

	public String getTemplReqSendDataFile() {
		return templReqSendDataFile;
	}

	public void setTemplReqSendDataFile(String templReqSendDataFile) {
		this.templReqSendDataFile = templReqSendDataFile;
	}

	public String getTemplReqModelUpdateFile() {
		return templReqModelUpdateFile;
	}

	public void setTemplReqModelUpdateFile(String templReqModelUpdateFile) {
		this.templReqModelUpdateFile = templReqModelUpdateFile;
	}

	public String getTemplReqWhatchdogFile() {
		return templReqWhatchdogFile;
	}

	public void setTemplReqWhatchdogFile(String templReqWhatchdogFile) {
		this.templReqWhatchdogFile = templReqWhatchdogFile;
	}

	public String getTemplRespLoginFile() {
		return templRespLoginFile;
	}

	public void setTemplRespLoginFile(String templRespLoginFile) {
		this.templRespLoginFile = templRespLoginFile;
	}

	public String getTemplRespSendDataFile() {
		return templRespSendDataFile;
	}

	public void setTemplRespSendDataFile(String templRespSendDataFile) {
		this.templRespSendDataFile = templRespSendDataFile;
	}

	public String getTemplRespModelUpdateFile() {
		return templRespModelUpdateFile;
	}

	public void setTemplRespModelUpdateFile(String templRespModelUpdateFile) {
		this.templRespModelUpdateFile = templRespModelUpdateFile;
	}

	public String getTemplRespWhatchdogFile() {
		return templRespWhatchdogFile;
	}

	public void setTemplRespWhatchdogFile(String templRespWhatchdogFile) {
		this.templRespWhatchdogFile = templRespWhatchdogFile;
	}
	
}
