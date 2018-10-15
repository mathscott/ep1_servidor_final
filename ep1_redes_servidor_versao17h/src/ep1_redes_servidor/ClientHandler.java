package ep1_redes_servidor;

import java.io.*;
import java.net.*;

class ClientHandler extends Thread  
{ 
    
    final DataInputStream din; 
    final DataOutputStream dout; 
    final Socket s; 
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream din, DataOutputStream dout)  
    { 
        this.s = s; 
        this.din = din; 
        this.dout = dout; 
    } 
  
    @Override
    public void run()  
    { 
        
        while (true)  
        { 
            try { 
            	DataInputStream din = new DataInputStream(s.getInputStream());
    			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    			String msgin = "", msgout = "";
    			boolean sair = false;

    			while (!msgin.equals("sairmodoselecao")) {
    				msgout = " Bem-vindo ao incrivel EP 1 de Redes!!!!\n "
    						+ "*** Insira o numero correspondente a aplicacao que deseja utilizar e pressione Enter : \n"
    						+ "1 - Jogo da Adivinhacao\n" + "2 - Calculadora\n" + "3 - Pedra-Papel-Tesoura\n";
    				dout.writeUTF(msgout);
    				dout.flush();

    				msgin = din.readUTF(); //espera resposta do cliente
    				int opcaoJogo = Integer.parseInt(msgin);
    				String respostaOpcaoJogo = "Numero invalido!";

    				switch (opcaoJogo) {
    				case 1:
    					respostaOpcaoJogo = "Jogo da Adivinhacao selecionado!";
    					break;
    				case 2:
    					respostaOpcaoJogo = "Calculadora selecionada!";
    					break;
    				case 3:
    					respostaOpcaoJogo = "Pedra-Papel-Tesoura selecionado!";
    					break;
    				}

    				while (!msgin.equals("99")) {
    					
    					if (opcaoJogo == 1) { //Jogo da adivinhacao
    						Adivinhacao jogo = new Adivinhacao();
    						msgout = jogo.jogar(s);
    						msgout += "\nInsira o numero correspondente a sua opcao:\n" + 
    								"1 - Jogar novamente\n" + 
    								"2 - Escolher outra aplicacao\n" + 
    								"3 - Encerrar conexao";
    						dout.writeUTF(msgout);
    						msgin = din.readUTF();
    						if (msgin.equals("1")) {
    							
    						} else if (msgin.equals("2")) {
    							msgin = "99";
    						} else if (msgin.equals("3")) {
    							s.close();
    						}
    						
    					} else if (opcaoJogo == 2) { //Calculdora
    						Calculadora aplicacao = new Calculadora();
    						msgout = aplicacao.calcular(s);
    						msgout += "\nInsira o numero correspondente a sua opcao:\n" + 
    								"1 - Calcular novamente\n" + 
    								"2 - Escolher outra aplicacao\n" + 
    								"3 - Encerrar conexao";
    						dout.writeUTF(msgout);
    						msgin = din.readUTF();
    						if (msgin.equals("1")) {
    							
    						} else if (msgin.equals("2")) {
    							msgin = "99";
    						} else if (msgin.equals("3")) {
    							s.close();
    						}
    						
    					} else if (opcaoJogo == 3) { //Pedra_papel_tesoura
    						PedraPapelTesoura jogo = new PedraPapelTesoura();
    						msgout = jogo.jogar(s);
    						msgout += "\nInsira o numero correspondente a sua opcao:\n" + 
    								"1 - Jogar novamente\n" + 
    								"2 - Escolher outra aplicacao\n" + 
    								"3 - Encerrar conexao";
    						dout.writeUTF(msgout);
    						dout.flush();
    						msgin = din.readUTF();
    						if (msgin.equals("1")) {
    							
    						} else if (msgin.equals("2")) {
    							msgin = "99";
    						} else if (msgin.equals("3")) {
    							s.close();
    						}
    						
    					} else {
    						msgout = "Numero invalido, pressiona ENTER para retornar";
    						dout.writeUTF(msgout);
    						dout.flush();
    						msgin = din.readUTF();
    						msgin = "99";
    					}
    				}
    			}

    			s.close();
                
            }catch (IOException e) { 
                e.printStackTrace(); 
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        } 
          
        
    } 
} 
