package ep1_redes_servidor;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Adivinhacao {
	String jogar(Socket s) throws Exception{
		int tentativas = 0;
		Random ran = new Random();
        int objetivo = ran.nextInt(10);
		boolean vitoria = false;
		DataInputStream din = new DataInputStream(s.getInputStream());
	    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String msgin="",msgout="";
        msgout = " Bem-vindo ao incrivel Adivinhacao!!!! \r\n"+
        "*** Adivinhe qual numero estou pensando (entre 0 e 9), voce tem 3 chances : \n";
        dout.writeUTF(msgout);
        dout.flush();
        
        while(vitoria == false && tentativas < 3){
        	msgin = din.readUTF();
            int data1 = Integer.parseInt(msgin);
        	int jogada = data1;
        	
        	if (jogada >= 0 && jogada <= 9) {
        		if(jogada == objetivo){
            		msgout = "Parabens! Voce acertou! \r\n";
            		vitoria = true;
            		break;
            	} else if(jogada < objetivo){
            		msgout =  "O numero que estou pensando eh maior que o seu palpite! \r\n";
            		tentativas++;
            	} else{
            		msgout = "O numero que estou pensando eh menor que o seu palpite! \r\n";
            		tentativas++;
            	}
            	if (tentativas < 3) {
            		dout.writeUTF(msgout);
            		dout.flush();
            	}
        	} else {
        		msgout = "Numero Invalido, pressione ENTER para voltar ao menu anterior";
        		dout.writeUTF(msgout);
		    	dout.flush();
		    	din.readUTF();
		    	jogar(s);
        	}
        }
        
        if(tentativas == 3){
    		msgout = "Que pena! Suas chances acabaram!\nO numero era: "+objetivo+" \r\n";
    	}
        
        return msgout;
        
       
	}
}
