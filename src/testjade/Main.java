/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjade;

/**
 *
 * @author Roddier
 */

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;


// Agent réactif simple
/*class AgentReactifSimple extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + " prêt.");
        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println(getLocalName() + " réagit immédiatement.");
            }
        });
    }
}

// Agent réactif à états
class AgentReactifEtat extends Agent {
    private int etat = 0; // Stocke l'état interne
    
    protected void setup() {
        System.out.println(getLocalName() + " prêt.");
        addBehaviour(new TickerBehaviour(this, 3000) {
            protected void onTick() {
                etat += new Random().nextInt(3) - 1; // Évolue avec le temps
                System.out.println(getLocalName() + " état actuel: " + etat);
            }
        });
    }
}

// Agent d'environnement
class AgentEnvironnement extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + " en fonctionnement.");
        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                message.setContent("Changement d'environnement");
                message.addReceiver(getAID("agentReactifSimple"));
                message.addReceiver(getAID("agentReactifEtat"));
                send(message);
                System.out.println(getLocalName() + " envoie un changement d'environnement.");
            }
        });
    }
}*/

// Lancement des agents
public class Main {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        ContainerController cc = rt.createMainContainer(p);
        try {
            //AgentController a1 = cc.createNewAgent("agentReactifSimple", AgentReactifSimple.class.getName(), null);
            // AgentController a2 = cc.createNewAgent("agentReactifEtat", AgentReactifEtat.class.getName(), null);
            AgentController a3 = cc.createNewAgent("environnement", AgentEnvironnement.class.getName(), null);
            AgentController a4 = cc.createNewAgent("agentAButs", AgentAButs.class.getName(), null);
            // AgentController a5 = cc.createNewAgent("agentDUtilite", AgentDUtilite.class.getName(), null);
            
            // a1.start();
            // a2.start();
            a3.start();
            a4.start();
            // a5.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
