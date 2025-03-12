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
*         lmarcio@cos.ufrj.br
* Tel.: +55-21-99983-7207
*
* SoundUtil.java
*/

package br.com.tlmv.emsdapp.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundUtil 
{
//Private	
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;

//Public

    public void playSound(String filename)
    {
        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }

    public List<Byte> readSound(String filename)
    {
    	ArrayList<Byte> lsResult = new ArrayList<Byte>();
    	
        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        //int offset = 0;
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
                
		        for(int i = 0; i < nBytesRead; i++) {
		        	byte soundData = abData[i];
		        	lsResult.add(soundData);
			        //System.out.println("POS:" + offset + "; VALUE:" + soundData);	        	
			        //offset += 1;
		        }	        	                
            } 
            catch (IOException e) {
                e.printStackTrace();
            }

            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
        
        return lsResult;
    }
    
	public void debug(List<Byte> lsSoundData)
	{
        for(int i = 0; i < lsSoundData.size(); i++) {
        	byte soundData = lsSoundData.get(i);
	        System.out.println("POS:" + i + "; VALUE:" + soundData);
	        
	        if(i > 30) break;
        }	        	                
        System.out.print("\n\n");
	}
	
}
