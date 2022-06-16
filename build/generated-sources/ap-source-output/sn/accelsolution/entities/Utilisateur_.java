package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.AppelOffre;
import sn.accelsolution.entities.Assurer;
import sn.accelsolution.entities.Brouillard;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Conge;
import sn.accelsolution.entities.Contrat;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Demande;
import sn.accelsolution.entities.Detailleappeloffre;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Expressionbesoin;
import sn.accelsolution.entities.Indemnite;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Operation;
import sn.accelsolution.entities.Opportunite;
import sn.accelsolution.entities.Pret;
import sn.accelsolution.entities.Role;
import sn.accelsolution.entities.Salaire;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.UserIndemnite;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile ListAttribute<Utilisateur, Opportunite> opportuniteList;
    public static volatile ListAttribute<Utilisateur, Pret> pretList;
    public static volatile ListAttribute<Utilisateur, Stock> stockList;
    public static volatile SingularAttribute<Utilisateur, Integer> idUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> telUtilisateur;
    public static volatile ListAttribute<Utilisateur, Brouillard> brouillardList;
    public static volatile SingularAttribute<Utilisateur, String> etatpwd;
    public static volatile ListAttribute<Utilisateur, Management> managementList;
    public static volatile ListAttribute<Utilisateur, UserIndemnite> userIndemniteList;
    public static volatile ListAttribute<Utilisateur, Operation> operationList;
    public static volatile SingularAttribute<Utilisateur, String> passwordUtisateur;
    public static volatile ListAttribute<Utilisateur, Droitacces> droitaccesList;
    public static volatile ListAttribute<Utilisateur, Detailleappeloffre> detailleappeloffreList;
    public static volatile SingularAttribute<Utilisateur, Integer> mcreationpwd;
    public static volatile ListAttribute<Utilisateur, Salaire> salaireList;
    public static volatile ListAttribute<Utilisateur, Detaillechantier> detaillechantierList;
    public static volatile ListAttribute<Utilisateur, Assurer> assurerList;
    public static volatile SingularAttribute<Utilisateur, String> cni;
    public static volatile SingularAttribute<Utilisateur, String> dateNaissanceUtilisateur;
    public static volatile ListAttribute<Utilisateur, AppelOffre> appelOffreList;
    public static volatile SingularAttribute<Utilisateur, String> numeroSecuriteSociale;
    public static volatile SingularAttribute<Utilisateur, String> firstconnection;
    public static volatile ListAttribute<Utilisateur, Decomptep> decomptepList;
    public static volatile ListAttribute<Utilisateur, Notification> notificationList;
    public static volatile ListAttribute<Utilisateur, Conge> congeList;
    public static volatile ListAttribute<Utilisateur, Chantier> chantierList;
    public static volatile ListAttribute<Utilisateur, Newfacture> newfactureList;
    public static volatile SingularAttribute<Utilisateur, Integer> hcreationpwd;
    public static volatile ListAttribute<Utilisateur, Commande> commandeList;
    public static volatile SingularAttribute<Utilisateur, Role> idRole;
    public static volatile SingularAttribute<Utilisateur, String> situationfamille;
    public static volatile SingularAttribute<Utilisateur, String> nomUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> mailUtilisateur;
    public static volatile ListAttribute<Utilisateur, Entrepot> entrepotList;
    public static volatile ListAttribute<Utilisateur, Devis> devisList;
    public static volatile ListAttribute<Utilisateur, Indemnite> indemniteList;
    public static volatile SingularAttribute<Utilisateur, String> prenomUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> adresseUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> lieuNaissanceUtilisateur;
    public static volatile ListAttribute<Utilisateur, Sortiestock> sortiestockList;
    public static volatile ListAttribute<Utilisateur, Demande> demandeList;
    public static volatile ListAttribute<Utilisateur, Expressionbesoin> expressionbesoinList;
    public static volatile SingularAttribute<Utilisateur, String> fonction;
    public static volatile SingularAttribute<Utilisateur, String> etatcompte;
    public static volatile ListAttribute<Utilisateur, Contrat> contratList;
    public static volatile SingularAttribute<Utilisateur, Integer> screationpwd;

}