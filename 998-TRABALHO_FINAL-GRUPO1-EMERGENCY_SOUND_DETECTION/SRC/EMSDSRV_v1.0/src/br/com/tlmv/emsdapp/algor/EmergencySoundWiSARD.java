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
 * EmergencySoundIC.java
 */

package br.com.tlmv.emsdapp.algor;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.com.tlmv.emsdapp.AppConfig;
import br.com.tlmv.emsdapp.AppContext;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.dao.AudioDAO;
import br.com.tlmv.emsdapp.dao.ConstanteSistemaDAO;
import br.com.tlmv.emsdapp.dao.DAO;
import br.com.tlmv.emsdapp.dao.record.Audio;
import br.com.tlmv.emsdapp.dao.record.ConstanteSistema;
import br.com.tlmv.emsdapp.dao.record.Record;
import br.com.tlmv.emsdapp.utils.AudioPatternUtil;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.SoundUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.UuidUtil;
import br.com.tlmv.emsdapp.vo.AudioPatternVO;
import br.com.tlmv.emsdapp.vo.SoundInfoVO;

public class EmergencySoundWiSARD 
{
//Private
	
	private int[][] arrAudioPattern = null;
	private int szArrAudioPattern = -1;
	
	private List<Audio> lsAllDataset = null;
	private int szLsAllDataset = -1;
			
	private List<Audio> lsDataset = null;
	private int szLsDataset = -1;

	private List<Audio> lsTrain = null;
	private int szLsTrain = -1;

	private List<Audio> lsTest = null;
	private int szLsTest = -1;
	
	private ArrayList<Audio> shuffleDataset(List<Audio> lsSrc, int startPos, int size)
	{
		ArrayList<Audio> lsDst = new ArrayList<Audio>(); 

		if(startPos < lsSrc.size())
		{
			int endPos = startPos + size;
			if(endPos > lsSrc.size())
				endPos = lsSrc.size();
			
			for(int i = startPos; i < endPos; i++) {
				Audio o = lsSrc.get(i);
				lsDst.add(o);
			}			
		}
		
		return lsDst;
	}
	
	private void loadAllAudio()
	{
		try {
			DAO dao = AppMain.getApp().getDAO();
			
			AudioDAO ad = new AudioDAO(dao);
			lsAllDataset = ad.findAllAudio();
			szLsAllDataset = lsAllDataset.size();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	private void loadAllAudioByClassId()
	{
		try {
			DAO dao = AppMain.getApp().getDAO();
			
			AudioDAO ad = new AudioDAO(dao);
			lsDataset = ad.findAudioByClassId(AppDefs.DEF_AUDIO_GUN_SHOT_VAL);
			szLsDataset = lsDataset.size();
			if(szLsDataset > 0) {
				szLsTrain = AppDefs.DEF_MODEL_TRAIN_SIZE;
				lsTrain = shuffleDataset(lsDataset, 0, szLsTrain);

				szLsTest = AppDefs.DEF_MODEL_TEST_SIZE;
				lsTest = shuffleDataset(lsDataset, szLsTrain, szLsTest);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//Public
	
	public EmergencySoundWiSARD(boolean bTestExec) 
	{
		init(bTestExec);
	}
	
	public void init(boolean bTestExec)
	{
		initAudioPattern();
		loadAllAudioByClassId();
		loadAllAudio();
		train();
		test();
		
		if( bTestExec ) {
			//classify();
			String modelUUID = saveModel();
			loadModel(modelUUID);
			classifyAll();			
		}
	}
	
	public void delay()
	{
		try {
			Thread.sleep(AppDefs.DEF_MAX_TEST_DELAY);
		}
		catch(Exception e) { }
	}

	/* TRAIN */
	
	public void initAudioPattern()
	{
		int szArrPattern = (int)Math.ceil(AppDefs.DEF_AUDIO_MAX_VAL / AppDefs.DEF_AUDIO_DEPTH) + 1;
		
		szArrAudioPattern = AppDefs.DEF_AUDIO_MAX_LEN;
		arrAudioPattern = new int[szArrAudioPattern][szArrPattern];		
		for(int i = 0; i < szArrAudioPattern; i++) {
			for(int j = 0; j < szArrPattern; j++) {			
				arrAudioPattern[i][j] = 0;
			}
		}
	}
	
	public void trainAudioPattern(List<Double> lsSoundData)
	{
		int cnt = (int)Math.min(AppDefs.DEF_AUDIO_MAX_LEN, lsSoundData.size());
		for(int i = 0; i < cnt; i++) {
			int soundVal = (int)Math.floor(lsSoundData.get(i));
			arrAudioPattern[i][soundVal] += 1;
		}		
	}

	public double testAudioPattern(List<Double> lsSoundData) {
		double score = 0.0;
		int n = 0;
		
		int cnt = (int)Math.min(AppDefs.DEF_AUDIO_MAX_LEN, lsSoundData.size());
		for(int i = 0; i < cnt; i++) {
			int soundVal = (int)Math.floor(lsSoundData.get(i));
			score += arrAudioPattern[i][soundVal];
			//if(score > 0)
			n += 1;
		}		
		score = score / n;
		System.out.println("Score: " + score);
				
		return score;
	}

	public int classifyAudioPattern(List<Double> lsSoundData, double thresholdMin, double thresholdMax) {
		double score = 0.0;
		int n = 0;
		
		int cnt = (int)Math.min(AppDefs.DEF_AUDIO_MAX_LEN, lsSoundData.size());
		for(int i = 0; i < cnt; i++) {
			int soundVal = (int)Math.floor(lsSoundData.get(i));
			score += arrAudioPattern[i][soundVal];
			//if(score > 0)
			n += 1;
		}		
		score = score / n;
		System.out.println("Score: " + score);
		
		if((score >= thresholdMin)&&(score <= thresholdMax))
			return 1;
		return 0;
	}

	public String saveAudioPattern(String modelUUID)
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 

		String modelFileName = modelUUID + ".model";
		String modelFile = context.getDataModelsDir() + "/" + modelFileName;
		
		int szArrPattern = (int)Math.ceil(AppDefs.DEF_AUDIO_MAX_VAL / AppDefs.DEF_AUDIO_DEPTH) + 1;

		String strData = "";
		for(int i = 0; i < AppDefs.DEF_AUDIO_MAX_LEN; i++) {
			for(int j = 0; j < szArrPattern; j++) {
				double soundFreq = arrAudioPattern[i][j];
				
				if(strData.length() > 0)
					strData = strData + "," + Double.toString(soundFreq);
				else
					strData = Double.toString(soundFreq);
			}
		}		
		
		File f = new File(modelFile);
			
		FileUtil.writeData(f, strData);
		return modelUUID;
	}

	public String loadAudioPattern(String modelUUID)
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 

		String modelFileName = modelUUID + ".model";
		String modelFile = context.getDataModelsDir() + "/" + modelFileName;
		
		String strData = FileUtil.readData(modelFile);
		String[] valData = StringUtil.split(strData, ',');
		int pos = 0;

		int szArrPattern = (int)Math.ceil(AppDefs.DEF_AUDIO_MAX_VAL / AppDefs.DEF_AUDIO_DEPTH) + 1;
		for(int i = 0; i < AppDefs.DEF_AUDIO_MAX_LEN; i++) {
			for(int j = 0; j < szArrPattern; j++) {
				arrAudioPattern[i][j] = (int)Double.parseDouble(valData[pos]);
				pos += 1;
			}
		}	
		return modelUUID;
	}

	/* Methodes */
	
	public void train()
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- TRAIN --\n");
		
		int cnt = 0;
		for(Audio o : lsTrain) {
			System.out.println(Integer.toString(cnt++) + " - " + o.getClass1() + " - " + o.getSliceFileName());

			String fileName = context.getDataDatasetDir() + "/audio/" + o.getSliceFileName();

			SoundUtil oSound = new SoundUtil();
			//oSound.playSound(fileName);
			List<Byte> lsSoundData = oSound.readSound(fileName);
			
			SoundInfoVO soundInfo = new SoundInfoVO();			
			List<Double> lsSoundData_BASE0 = soundInfo.convertToDouble(lsSoundData);
			List<Double> lsSoundData_SCALE = soundInfo.fixedToDouble();
			List<Double> lsSoundData_RESCALE8 = soundInfo.rescaleToDouble(AppDefs.DEF_AUDIO_DEPTH);

			trainAudioPattern(lsSoundData_RESCALE8);
			//debugTrainAudioPattern();
			
	        delay();
		}
		System.out.println("\n\n");
	}
	
	public void test()
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- TEST --\n");
		
		int cnt = 0;
		for(Audio o : lsTest) {
			System.out.println(Integer.toString(cnt++) + " - " + o.getClass1() + " - " + o.getSliceFileName());

			String fileName = context.getDataDatasetDir() + "/audio/" + o.getSliceFileName();

			SoundUtil oSound = new SoundUtil();
			//oSound.playSound(fileName);
			List<Byte> lsSoundData = oSound.readSound(fileName);
			
			SoundInfoVO soundInfo = new SoundInfoVO();			
			List<Double> lsSoundData_BASE0 = soundInfo.convertToDouble(lsSoundData);
			List<Double> lsSoundData_SCALE = soundInfo.fixedToDouble();
			List<Double> lsSoundData_RESCALE8 = soundInfo.rescaleToDouble(AppDefs.DEF_AUDIO_DEPTH);

			double score = testAudioPattern(lsSoundData_RESCALE8);
			//System.out.println("Score: " + score);
			
	        delay();
		}
		System.out.println("\n\n");
	}
	
	public Integer classify(Audio oAudio)
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- CLASSIFY ALL DATASET --\n");
		
		System.out.println(Integer.toString(oAudio.getAudioId()) + " - " + oAudio.getClass1() + " - " + oAudio.getSliceFileName());

		String fileName = context.getDataDatasetDir() + "/audio/" + oAudio.getSliceFileName();

		SoundUtil oSound = new SoundUtil();
		//oSound.playSound(fileName);
		List<Byte> lsSoundData = oSound.readSound(fileName);
		
		SoundInfoVO soundInfo = new SoundInfoVO();			
		List<Double> lsSoundData_BASE0 = soundInfo.convertToDouble(lsSoundData);
		List<Double> lsSoundData_SCALE = soundInfo.fixedToDouble();
		List<Double> lsSoundData_RESCALE8 = soundInfo.rescaleToDouble(AppDefs.DEF_AUDIO_DEPTH);

		int result = classifyAudioPattern(lsSoundData_RESCALE8, AppDefs.DEF_AUDIO_THRESHOLD_MIN, AppDefs.DEF_AUDIO_THRESHOLD_MAX);
		if(result == 0)
			System.out.println("[?]");
		else
			System.out.println("[GUN_SHOT]");
		
		System.out.println("\n");
		
		return result;
	}

	public void classifyAll()
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- CLASSIFY ALL DATASET --\n");
		
		int cnt = 0;
		for(Audio o : lsAllDataset) {
			System.out.println(Integer.toString(cnt++) + " - " + o.getClass1() + " - " + o.getSliceFileName());

			String fileName = context.getDataDatasetDir() + "/audio/" + o.getSliceFileName();

			SoundUtil oSound = new SoundUtil();
			//oSound.playSound(fileName);
			List<Byte> lsSoundData = oSound.readSound(fileName);
			
			SoundInfoVO soundInfo = new SoundInfoVO();			
			List<Double> lsSoundData_BASE0 = soundInfo.convertToDouble(lsSoundData);
			List<Double> lsSoundData_SCALE = soundInfo.fixedToDouble();
			List<Double> lsSoundData_RESCALE8 = soundInfo.rescaleToDouble(AppDefs.DEF_AUDIO_DEPTH);

			int result = classifyAudioPattern(lsSoundData_RESCALE8, AppDefs.DEF_AUDIO_THRESHOLD_MIN, AppDefs.DEF_AUDIO_THRESHOLD_MAX);
			if(result == 0)
				System.out.println("[?]");
			else
				System.out.println("[GUN_SHOT]");
			
	        if(cnt >= AppDefs.DEF_AUDIO_MAX_EXEC) break;

	        delay();
		}
		System.out.println("\n\n");
	}

	public String saveModel()
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- SAVE MODEL --\n");
		
		String modelUUID = UuidUtil.modelUUID();
		saveAudioPattern(modelUUID);
		return modelUUID;
	}
	
	public String loadModel(String modelUUID)
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		System.out.println("\n-- LOAD MODEL --\n");
		
		loadAudioPattern(modelUUID);
		return modelUUID;
	}

	/* Getters/Setters */
		
//	public void debugAll()
//	{
//		debug("\n-- TRAIN --\n", lsTrain);
//		debug("\n-- TEST --\n", lsTest);						
//	}
//	
//	public void debug(String title, List<Audio> lsData)
//	{
//		AppContext context = AppMain.getApp().getContext(); 
//		
//		AppConfig config = AppMain.getApp().getConfig(); 
//		
//		System.out.println(title);
//		
//		int cnt = 0;
//		for(Audio o : lsData) {
//			System.out.println(Integer.toString(cnt++) + " - " + o.getClass1() + " - " + o.getSliceFileName());
//
//			String fileName = context.getDataDatasetDir() + "/audio/" + o.getSliceFileName();
//
//			SoundUtil oSound = new SoundUtil();
//			//oSound.playSound(fileName);
//			List<Byte> lsSoundData = oSound.readSound(fileName);
//			
//			SoundInfoVO soundInfo = new SoundInfoVO();			
//			List<Double> lsSoundData_BASE0 = soundInfo.convertToDouble(lsSoundData);
//			List<Double> lsSoundData_SCALE = soundInfo.fixedToDouble();
//			soundInfo.debug(lsSoundData_SCALE);
//			
//			List<Double> lsSoundData_RESCALE8 = soundInfo.rescaleToDouble(AppDefs.DEF_AUDIO_DEPTH);
//			soundInfo.debug(lsSoundData_RESCALE8);
//
//	        delay();
//		}
//		System.out.println("\n\n");
//	}
	
//	public void debugTrainAudioPattern()
//	{
//		System.out.println("\n-- TRAIN_AUDIO_PATTERN --\n");
//
//		int szArrPattern = (int)Math.ceil(AppDefs.DEF_AUDIO_MAX_VAL / AppDefs.DEF_AUDIO_DEPTH) + 1;
//		
//		for(int i = 0; i < AppDefs.DEF_AUDIO_MAX_LEN; i++) {
//			String strArr = "";
//			for(int j = 0; j < szArrPattern; j++) {
//				strArr += arrAudioPattern[i][j] + "; ";
//			}
//			System.out.println("\nLinha:" + i + "; ARRAY:" + strArr);
//		}		
//	}
	
	
	
}
