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

public class AppError 
{
//Public
	//ERROS
	public static String ERR_FALHA_PROCESSAMENTO = "Falha no processamento";
	public static String ERR_FALHA_LEITURA_DADOS = "Falha na leitura dos dados";
	public static String ERR_INVALID_COMMAND_SWITCH = "Linha de comando invalida";
	public static String ERR_CAMPOS_OBRIGATORIOS = "Campos obrigatorios nao informados";
	public static String ERR_FALHA_ABERTURA_BANCO_DADOS = "Falha na abertura do banco de dados";
	public static String ERR_FALHA_LEITURA_DADOS_INICIAIS_BANCO_DADOS = "Falha na leitura dos dados iniciais do banco de dados";
	public static String ERR_CAMPOS_INVALIDOS = "Campos invalidos";
	public static String ERR_FALHA_VALIDACAO_DADOS = "Falha na validacao dos dados";
	public static String ERR_FALHA_INICIALIZACAO_CONTEXTO = "Falha na inicializacao do contexto e leitura do arquivo de configuracao";
	
	/* Methodes */

	//Console Output - MESSAGES
	
	public static void showCmdMessage(String msg, Class oClass)
	{
		String strmsg = String.format("MSG(%s): %s", oClass.getSimpleName(), msg);
		System.out.println(strmsg);
	}
	
	public static void showCmdWarn(int debugLevel, String msg, Class oClass)
	{
		if(debugLevel == AppDefs.DEBUG_LEVEL)
		{
			String warnmsg = String.format("WARN(%s): %s", oClass.getSimpleName(), msg);
			System.out.println(warnmsg);
		}
	}

	public static void showCmdError(String msg, Class oClass)
	{
		String errmsg = String.format("ERR(%s): %s", oClass, msg);
		System.out.println(errmsg);		
		//System.exit(1);
	}

	public static void showCmdFatalError(String msg, Class oClass)
	{
		String errmsg = String.format("FATALERR(%s): %s", oClass, msg);
		System.out.println(errmsg);		
		System.exit(1);
	}

}
