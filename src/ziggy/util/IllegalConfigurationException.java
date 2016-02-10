package ziggy.util;

/**
 * @author AndreFRocha
 * Classe IllegalConfigurationException
 * Esta classe trata o lançamento da excepção de ma' configuração
 * do ficheiro de nivel.
 */

public class IllegalConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -8092760718261338800L;

/**
 * @param message - Mensagem a ser mostrada ao utilizador caso
 * o ficheiro for mal configurado
 */
	
public IllegalConfigurationException(String message) {
	//Envio da mensagem para a superclasse
    super(message);
    
  }

}
