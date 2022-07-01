package inf300;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application. Determinar que todos os serviços REST
 * de nosso sistema terão como path principal: store. (definição é feita através
 * da anotação @ApplicationPath). Application carrega todos os resources
 * (default) ou uma seleção
 *
 * @author 
 */
@ApplicationPath("store")
public class JAXRSConfiguration extends Application {
    
    

}
