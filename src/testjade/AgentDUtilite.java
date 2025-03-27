/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Roddier
 */
public class AgentDUtilite extends Agent {
    protected void setup(){
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
                    Integer sale = Integer.valueOf(msg.getContent());
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    
                    message.addReceiver(getAID("environnement"));
                    if(isUseful(sale, sale+2)){
                        message.setContent("Trash");
                        System.out.println(getLocalName() + " a détecté le sol pas assez sale ...");
                        System.out.println(getLocalName() + " tente de salir le sol ...");
                        send(message);
                        System.out.println(getLocalName() + " a détecté le sol pas assez sale ...");
                        System.out.println(getLocalName() + " tente de salir le sol ...");
                        send(message);
                    }else if (isUseful(sale, sale-2)){
                        message.setContent("Clean");
                        System.out.println(getLocalName() + " a détecté le sol trop sale ...");
                        System.out.println(getLocalName() + " tente de nettoyer le sol ...");
                        send(message);
                        System.out.println(getLocalName() + " a détecté le sol trop sale ...");
                        System.out.println(getLocalName() + " tente de nettoyer le sol ...");
                        send(message);
                    } //maintenant on suppose qu'il peut salir deux fois de suite ou nettoyer deux fois de suite
                    else if (isUseful(sale, sale-1)){
                        message.setContent("Clean");
                        System.out.println(getLocalName() + " a détecté le sol trop sale ...");
                        System.out.println(getLocalName() + " tente de nettoyer le sol ...");
                        send(message);
                    }else if (isUseful(sale, sale+1)){
                        message.setContent("Trash");
                        System.out.println(getLocalName() + " a détecté le sol pas assez sale ...");
                        System.out.println(getLocalName() + " tente de salir le sol ...");
                        send(message);
                    }
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
    
    private Boolean isUseful(Integer sale1, Integer sale2){
        if (sale2 > 1){
            return sale1 > sale2 && sale2 >= 1;
        }else if (sale1 < 1){
            return sale1 < sale2 && sale2 <= 1;
        }else {
            return sale2 == 1 && sale1 != 1;
        }
        
    }// cet agent trouve utile les actions qui rapprochent le degré de saleté de la valeur "1"
}
