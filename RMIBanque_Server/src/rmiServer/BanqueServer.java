package rmiServer;

import rmiService.BanqueImpl;

import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            // Démarrer le registre RMI (le fournisseur JNDI s'y connecte)
            LocateRegistry.createRegistry(1099);
            System.out.println("RMIRegistry démarré sur le port 1099");

            BanqueImpl banque = new BanqueImpl();

            // Binding via JNDI (configuration lue dans jndi.properties)
            InitialContext ctx = new InitialContext();
            ctx.rebind("BanqueService", banque);
            System.out.println("Service 'BanqueService' enregistré via JNDI. Serveur prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}