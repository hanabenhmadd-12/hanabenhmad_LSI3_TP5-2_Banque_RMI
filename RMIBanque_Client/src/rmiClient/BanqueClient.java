package rmiClient;

import metier.Compte;
import rmiService.IBanque;

import javax.naming.InitialContext;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            // Recherche via JNDI (configuration lue dans jndi.properties)
            InitialContext ctx = new InitialContext();
            IBanque banque = (IBanque) ctx.lookup("BanqueService");

            System.out.println(banque.creerCompte(new Compte(1, 5000)));
            System.out.println(banque.creerCompte(new Compte(2, 12000)));
            System.out.println(banque.creerCompte(new Compte(1, 300)));
            System.out.println(banque.getInfoCompte(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}