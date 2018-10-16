package ep1_redes_servidor;
import java.io.*;
import java.net.*;
import java.util.Random;


public class PedraPapelTesoura {
	final int pedra = 1;
	final int papel = 2;
	final int tesoura = 3;
	
	String jogar(Socket s) throws Exception{
		String resultado = "";
		
		DataInputStream din = new DataInputStream(s.getInputStream());
	    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    String msgin="",msgout="";

        msgout = " Bem-vindo ao incrivel Pedra, Papel e Tesoura!!!! \r\n" + 
        		"*** Insira o numero correspondente ao que deseja jogar e pressione Enter : \n"
                + "1 - Pedra\n"
                + "2 - Papel\n"
                + "3 - Tesoura\n";
        dout.writeUTF(msgout);
        dout.flush();
        
        msgin = din.readUTF();
        int jogadaJogador=Integer.parseInt(msgin);
        
        String msgJogadaJogador = "Numero invalido!";

        switch(jogadaJogador){
        case 1:
        	msgJogadaJogador = "Pedra selecionado!";
            break;
        case 2:
        	msgJogadaJogador = "Papel selecionado!";
            break;
        case 3:
        	msgJogadaJogador = "Tesoura selecionado!";
            break;
        default:
        	msgout = "Numero Invalido, pressione ENTER para voltar ao menu anterior";
        	dout.writeUTF(msgout);
		    dout.flush();
		    din.readUTF();
		    jogar(s);
        }

        msgout = msgJogadaJogador+"\n";
        
        Random ran = new Random();
        int jogadaMaquina = ran.nextInt(3)+1;
        
        switch(jogadaJogador){
        	case pedra:
        		switch(jogadaMaquina){
        			case pedra:
        				resultado = "Joguei pedra!\n\"Empate!\"";
        				break;
        			case papel:
        				resultado = "Joguei papel!\n\"Derrota!\"";
        				break;
        			case tesoura:
        				resultado = "Joguei tesoura!\nVitoria!";
        				break;
        		};
        		break;
        	case papel:
        		switch(jogadaMaquina){
        			case pedra:
        				resultado = "Joguei pedra!\nVitoria!";
        				break;
        			case papel:
        				resultado = "Joguei papel!\nEmpate!";
        				break;
        			case tesoura:
        				resultado = "Joguei tesoura!\nDerrota!";
        				break;
        		};
        		break;
        	case tesoura:
        		switch(jogadaMaquina){
        			case pedra:
        				resultado = "Joguei pedra!\nDerrota!";
        				break;
        			case papel:
        				resultado = "Joguei papel!\nVitoria!";
        				break;
        			case tesoura:
        				resultado = "Joguei tesoura!\nEmpate!";
        				break;
        		}
        	break;
        } 
        msgout += resultado;
        
        return msgout;
        
       
	}

	
}
