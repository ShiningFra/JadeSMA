/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

/**
 *
 * @author Roddier
 */
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReactiveAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " est prêt.");

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                // Recevoir des messages
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println(getLocalName() + " a reçu : " + msg.getContent());
                    // Réagir au message
                    // Exemple : répondre
                    ACLMessage reply = msg.createReply();
                    reply.setContent("Message reçu !");
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }
}
