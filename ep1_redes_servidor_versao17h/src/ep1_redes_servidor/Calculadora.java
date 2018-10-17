package ep1_redes_servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class Calculadora {
	final int adicao = 1;
	final int subtracao = 2;
	final int multiplicacao = 3;
	final int divisao = 4;
	
	String calcular(Socket s) throws Exception{
		DataInputStream din = new DataInputStream(s.getInputStream());
	    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String msgin="",msgout="";
	    msgout = " Bem-vindo a incrivel Calculadora!!!! \r\n" + 
	    		"*** Insira o numero correspondente a operacao que deseja realizar e pressione Enter : \n"
	            + "1 - Adicao\n"
	            + "2 - Subtracao\n"
	            + "3 - Multiplicacao\n"
	            + "4 - Divisao\n";
	    dout.writeUTF(msgout);
	    dout.flush();
	    msgin = din.readUTF();
	    int operacaoSelecionada=Integer.parseInt(msgin);
	    String resultado = "Numero invalido!";
	    int x;
	    int y;
	
	    switch(operacaoSelecionada){
		    case adicao:
		    	msgout = " Adicao selecionada!\n"
		    			+ "Insira o primeiro numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    x = Integer.parseInt(msgin);
			    msgout = "Insira o segundo numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    y = Integer.parseInt(msgin);
			    resultado = "O resultado de " + x + " + " + y + " = " + (x+y);
		        break;
		    case subtracao:
		    	msgout = " Subtracao selecionada!\n"
		    			+ "Insira o primeiro numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    x = Integer.parseInt(msgin);
			    msgout = "Insira o segundo numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    y = Integer.parseInt(msgin);
			    resultado = "O resultado da subtracao entre " + x + " e " + y + " eh " + (x-y);
		        break;
		    case multiplicacao:
		    	msgout = " Multiplicacao selecionada!\n"
		    			+ "Insira o primeiro numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    x = Integer.parseInt(msgin);
			    msgout = "Insira o segundo numero:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    y = Integer.parseInt(msgin);
			    resultado = "O resultado da multiplicacao entre " + x + " e " + y + " eh " + (x*y);
		        break;
		    case divisao:
		    	msgout = " Multiplicacao selecionada!\n"
		    			+ "Insira o dividendo:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    x = Integer.parseInt(msgin);
			    msgout = "Insira o divisor:\n";
			    dout.writeUTF(msgout);
			    dout.flush();
			    msgin = din.readUTF();
			    y = Integer.parseInt(msgin);
			    resultado = "O resultado da divisao de " + x + " por " + y + " eh " + (x/y);
		        break;
		     default:
		    	msgout = "Numero Invalido, pressione ENTER para voltar ao menu anterior";
	        	dout.writeUTF(msgout);
			    dout.flush();
			    din.readUTF();
			    calcular(s);
	    }
	   
	    msgout += resultado + "\n";
	    
	    return msgout;
	}
}