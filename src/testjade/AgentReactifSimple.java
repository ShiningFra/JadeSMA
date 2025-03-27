/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Roddier
 */
public class AgentReactifSimple extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + " prêt.");
        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println(getLocalName() + " actif.");
            }
        });
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if(msg != null) {
                    System.out.println(getLocalName() + " a détecté le sol sale ...");
                    System.out.println(getLocalName() + " tente de nettoyer le sol ...");
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    message.setContent("Clean");
                    message.addReceiver(getAID("environnement"));
                    send(message);
                }else {
                    block();
                }
            }
        });
        // écrivons une fonction pour percevoir
        addBehaviour(new TickerBehaviour(this, 3000) {
            protected void onTick() {
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                message.setContent("getState");
                message.addReceiver(getAID("environnement"));
                send(message);
            }
        });
    }
}