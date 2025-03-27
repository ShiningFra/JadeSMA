/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

/**
 *
 * @author Roddier
 */
public class AgentEnvironnement extends Agent {
    private Integer state;
    protected void setup() {
        state = 0;
        System.out.println(getLocalName() + " en fonctionnement.");
        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                state += new Random().nextInt(4);
                
            }
        });
        addBehaviour(new TickerBehaviour(this, 3000) {
            protected void onTick() {
                if(state > 0) {
                    System.out.println(getLocalName() + " : Le sol est sale ...");
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    message.setContent(state.toString());
                    // message.addReceiver(getAID("agentReactifSimple"));
                    message.addReceiver(getAID("agentDUtilite"));
                    send(message);
                } else {
                    System.out.println(getLocalName() + " : Le sol est propre ...");
                }
                System.out.println(getLocalName() + " : Degré de saleté : " + state);
            }
        });
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if(msg != null){
                if (msg.getContent().equals("Clean")) {
                    clean();
                } else if (msg.getContent().equals("Trash")){
                    trash();
                }} else {
                    block();
                }
            }
    });
    }
    public void clean(){
        state -= 1;
        if(state < 0){
            state = 0;
        }
        System.out.println("Nettoyage ...");
        System.out.println("Degré de saleté : " + state);
    }
    public void trash(){
        state -= 1;
        if(state < 0){
            state = 0;
        }
        System.out.println("Nettoyage ...");
        System.out.println("Degré de saleté : " + state);
    }
}