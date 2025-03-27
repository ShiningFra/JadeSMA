/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

/**
 *
 * @author Roddier
 */
public class AgentReactifEtat extends Agent {
    private Integer state = 2; // état interne de l'agent (dans le cas présent, le potentiel de nettoyage
    
    protected void setup() {
        System.out.println(getLocalName() + " prêt.");
        addBehaviour(new TickerBehaviour(this, 3000) {
            protected void onTick() {
                state +=  new Random().nextInt(3); // Le potentiel de nettoyage croît toutes les 3 secondes
                System.out.println(getLocalName() + " essaie de s'améliorer ...");
                System.out.println(getLocalName() + " : Potentiel actuel : " + state);
            }
        });
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    message.addReceiver(getAID("environnement"));
                    message.setContent("Clean");
                    Integer sale = Integer.parseInt(msg.getContent());
                    Integer x = Math.min(state, sale);
                    for(int i=0;i<x;i++){
                        System.out.println(getLocalName() + " a détecté le sol sale ...");
                        System.out.println(getLocalName() + " essaie de nettoyer ...");
                        send(message);
                        state--;
                        System.out.println(getLocalName() + " : Potentiel de nettoyage restant : " + state);
                    }
                } else {
                    block();
                }
            }
        });
    }
}