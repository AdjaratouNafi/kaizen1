/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.services;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.util.Privillege;

/**
 *
 * @author DV7
 */
@ManagedBean(name = "privillegeService")
@ApplicationScoped
public class PrivillegeService {

    public TreeNode createCheckboxDocuments() {

        TreeNode root = new CheckboxTreeNode(new Privillege("Files"), null);

        TreeNode accueil = new CheckboxTreeNode(new Privillege("Accueil"), root);
        TreeNode configuration = new CheckboxTreeNode(new Privillege("Configuration"), root);
        TreeNode personnel = new CheckboxTreeNode(new Privillege("Personnel"), root);
        TreeNode projets = new CheckboxTreeNode(new Privillege("Projets"), root);
        TreeNode approvisionnement = new CheckboxTreeNode(new Privillege("Approvisionnement"), root);
        TreeNode stock = new CheckboxTreeNode(new Privillege("Stock"), root);
        TreeNode opportunite = new CheckboxTreeNode(new Privillege("Opportunités"), root);

        // configuration' children definition.
        TreeNode role = new CheckboxTreeNode(new Privillege("Gestion des rôles"), configuration);
        TreeNode utilisateur = new CheckboxTreeNode(new Privillege("Gestion des utilisateurs"), configuration);

        // role' children definition.
        TreeNode creerrole = new CheckboxTreeNode(new Privillege("Creer"), role);
        TreeNode modifierrole = new CheckboxTreeNode(new Privillege("Modifier"), role);
        TreeNode suprimerrole = new CheckboxTreeNode(new Privillege("Supprimer"), role);
        TreeNode consulterrole = new CheckboxTreeNode(new Privillege("Consulter"), role);
        TreeNode imprimerrole = new CheckboxTreeNode(new Privillege("Imprimer"), role);
        TreeNode notifierrole = new CheckboxTreeNode(new Privillege("Notifier"), role);
        // role' children definition.
        TreeNode creeruser = new CheckboxTreeNode(new Privillege("Creer"), utilisateur);
        TreeNode modifieruser = new CheckboxTreeNode(new Privillege("Modifier"), utilisateur);
        TreeNode suprimeruser = new CheckboxTreeNode(new Privillege("Supprimer"), utilisateur);
        TreeNode consulteruser = new CheckboxTreeNode(new Privillege("Consulter"), utilisateur);
        TreeNode imprimeruser = new CheckboxTreeNode(new Privillege("Imprimer"), utilisateur);
        TreeNode notifieruser = new CheckboxTreeNode(new Privillege("Notifier"), utilisateur);

        // Personnel' children definition.
        TreeNode contrat = new CheckboxTreeNode(new Privillege("Contrats"), personnel);
        TreeNode salaire = new CheckboxTreeNode(new Privillege("Salaire"), personnel);
        TreeNode pret = new CheckboxTreeNode(new Privillege("Prêt"), personnel);
        TreeNode indemnites = new CheckboxTreeNode(new Privillege("Indemnités"), personnel);

        // Contrat' children definition.
        TreeNode recrutement = new CheckboxTreeNode(new Privillege("Recrutement"), contrat);
        TreeNode stage = new CheckboxTreeNode(new Privillege("Stage"), contrat);

        // recrutement' children definition.
        TreeNode creerrecrutement = new CheckboxTreeNode(new Privillege("Creer"), recrutement);
        TreeNode modifierrecrutement = new CheckboxTreeNode(new Privillege("Modifier"), recrutement);
        TreeNode suprimerrecrutement = new CheckboxTreeNode(new Privillege("Supprimer"), recrutement);
        TreeNode consulterrecrutement = new CheckboxTreeNode(new Privillege("Consulter"), recrutement);
        TreeNode imprimerrecrutement = new CheckboxTreeNode(new Privillege("Imprimer"), recrutement);
        TreeNode notifierrecrutement = new CheckboxTreeNode(new Privillege("Notifier"), recrutement);
        // stage' children definition.
        TreeNode creerstage = new CheckboxTreeNode(new Privillege("Creer"), stage);
        TreeNode modifierstage = new CheckboxTreeNode(new Privillege("Modifier"), stage);
        TreeNode suprimerstage = new CheckboxTreeNode(new Privillege("Supprimer"), stage);
        TreeNode consulterstage = new CheckboxTreeNode(new Privillege("Consulter"), stage);
        TreeNode imprimerstage = new CheckboxTreeNode(new Privillege("Imprimer"), stage);
        TreeNode notifierstage = new CheckboxTreeNode(new Privillege("Notifier"), stage);
        // salaire' children definition.
        TreeNode creersalaire = new CheckboxTreeNode(new Privillege("Creer"), salaire);
        TreeNode modifiersalaire = new CheckboxTreeNode(new Privillege("Modifier"), salaire);
        TreeNode suprimersalaire = new CheckboxTreeNode(new Privillege("Supprimer"), salaire);
        TreeNode consultersalaire = new CheckboxTreeNode(new Privillege("Consulter"), salaire);
        TreeNode imprimersalaire = new CheckboxTreeNode(new Privillege("Imprimer"), salaire);
        TreeNode notifierssalaire = new CheckboxTreeNode(new Privillege("Notifier"), salaire);
        // pret' children definition.
        TreeNode creerpret = new CheckboxTreeNode(new Privillege("Creer"), pret);
        TreeNode modifierpret = new CheckboxTreeNode(new Privillege("Modifier"), pret);
        TreeNode suprimerpret = new CheckboxTreeNode(new Privillege("Supprimer"), pret);
        TreeNode consulterpret = new CheckboxTreeNode(new Privillege("Consulter"), pret);
        TreeNode imprimerpret = new CheckboxTreeNode(new Privillege("Imprimer"), pret);
        TreeNode notifierspret = new CheckboxTreeNode(new Privillege("Notifier"), pret);
        // pret' children definition.
        TreeNode creerindemnites = new CheckboxTreeNode(new Privillege("Creer"), indemnites);
        TreeNode modifierindemnites = new CheckboxTreeNode(new Privillege("Modifier"), indemnites);
        TreeNode suprimerindemnites = new CheckboxTreeNode(new Privillege("Supprimer"), indemnites);
        TreeNode consulterindemnites = new CheckboxTreeNode(new Privillege("Consulter"), indemnites);
        TreeNode imprimerindemnites = new CheckboxTreeNode(new Privillege("Imprimer"), indemnites);
        TreeNode notifierindemnites = new CheckboxTreeNode(new Privillege("Notifier"), indemnites);

        // Projets' children definition.
        TreeNode gestion = new CheckboxTreeNode(new Privillege("Gestion"), projets);
        TreeNode chantiers = new CheckboxTreeNode(new Privillege("Chantiers"), projets);
        TreeNode prestataires = new CheckboxTreeNode(new Privillege("Prestataires"), projets);
        TreeNode projetmanagement = new CheckboxTreeNode(new Privillege("Project Management"), projets);

        // Gestion' children definition.
        TreeNode client = new CheckboxTreeNode(new Privillege("Clients"), gestion);
        TreeNode devis = new CheckboxTreeNode(new Privillege("Devis"), gestion);
        TreeNode marche = new CheckboxTreeNode(new Privillege("Marchés"), gestion);
        TreeNode facture = new CheckboxTreeNode(new Privillege("Factures"), gestion);
        TreeNode decompte = new CheckboxTreeNode(new Privillege("Decomptes"), gestion);

        // client' children definition.
        TreeNode creerclient = new CheckboxTreeNode(new Privillege("Creer"), client);
        TreeNode modifierclient = new CheckboxTreeNode(new Privillege("Modifier"), client);
        TreeNode suprimerclient = new CheckboxTreeNode(new Privillege("Supprimer"), client);
        TreeNode consulterclient = new CheckboxTreeNode(new Privillege("Consulter"), client);
        TreeNode imprimerclient = new CheckboxTreeNode(new Privillege("Imprimer"), client);
        TreeNode notifierclient = new CheckboxTreeNode(new Privillege("Notifier"), client);
        // marche' children definition.
        TreeNode creermarche = new CheckboxTreeNode(new Privillege("Creer"), marche);
        TreeNode modifiermarche = new CheckboxTreeNode(new Privillege("Modifier"), marche);
        TreeNode suprimermarche = new CheckboxTreeNode(new Privillege("Supprimer"), marche);
        TreeNode consultermarche = new CheckboxTreeNode(new Privillege("Consulter"), marche);
        TreeNode imprimermarche = new CheckboxTreeNode(new Privillege("Imprimer"), marche);
        TreeNode notifiermarche = new CheckboxTreeNode(new Privillege("Notifier"), marche);
        // devis' children definition.
        TreeNode creerdevis = new CheckboxTreeNode(new Privillege("Creer"), devis);
        TreeNode modifierdevis = new CheckboxTreeNode(new Privillege("Modifier"), devis);
        TreeNode suprimerdevis = new CheckboxTreeNode(new Privillege("Supprimer"), devis);
        TreeNode consulterdevis = new CheckboxTreeNode(new Privillege("Consulter"), devis);
        TreeNode imprimerdevis = new CheckboxTreeNode(new Privillege("Imprimer"), devis);
        TreeNode notifierdevis = new CheckboxTreeNode(new Privillege("Notifier"), devis);
        // facture' children definition.
        TreeNode creerfacture = new CheckboxTreeNode(new Privillege("Creer"), facture);
        TreeNode modifierfacture = new CheckboxTreeNode(new Privillege("Modifier"), facture);
        TreeNode suprimerfacture = new CheckboxTreeNode(new Privillege("Supprimer"), facture);
        TreeNode consulterfacture = new CheckboxTreeNode(new Privillege("Consulter"), facture);
        TreeNode imprimerfacture = new CheckboxTreeNode(new Privillege("Imprimer"), facture);
        TreeNode notifierfacture = new CheckboxTreeNode(new Privillege("Notifier"), facture);
        // decompte' children definition.
        TreeNode creerdecompte = new CheckboxTreeNode(new Privillege("Creer"), decompte);
        TreeNode modifierdecompte = new CheckboxTreeNode(new Privillege("Modifier"), decompte);
        TreeNode suprimerdecompte = new CheckboxTreeNode(new Privillege("Supprimer"), decompte);
        TreeNode consulterdecompte = new CheckboxTreeNode(new Privillege("Consulter"), decompte);
        TreeNode imprimerdecompte = new CheckboxTreeNode(new Privillege("Imprimer"), decompte);
        TreeNode notifierdecompte = new CheckboxTreeNode(new Privillege("Notifier"), decompte);
        // chantiers' children definition.
        TreeNode creerchantiers = new CheckboxTreeNode(new Privillege("Creer"), chantiers);
        TreeNode modifierchantiers = new CheckboxTreeNode(new Privillege("Modifier"), chantiers);
        TreeNode suprimerchantiers = new CheckboxTreeNode(new Privillege("Supprimer"), chantiers);
        TreeNode consulterchantiers = new CheckboxTreeNode(new Privillege("Consulter"), chantiers);
        TreeNode imprimerchantiers = new CheckboxTreeNode(new Privillege("Imprimer"), chantiers);
        TreeNode notifierchantiers = new CheckboxTreeNode(new Privillege("Notifier"), chantiers);

        // Prestataires' children definition.
        TreeNode listprestataire = new CheckboxTreeNode(new Privillege("Liste prestataires"), prestataires);
        TreeNode referentielmod = new CheckboxTreeNode(new Privillege("Referenciel Main-d'oeuvre"), prestataires);
        TreeNode acompte = new CheckboxTreeNode(new Privillege("Acomptes"), prestataires);

        // listprestataire' children definition.
        TreeNode creerlistprestataire = new CheckboxTreeNode(new Privillege("Creer"), listprestataire);
        TreeNode modifierlistprestataire = new CheckboxTreeNode(new Privillege("Modifier"), listprestataire);
        TreeNode suprimerlistprestataire = new CheckboxTreeNode(new Privillege("Supprimer"), listprestataire);
        TreeNode consulterlistprestataire = new CheckboxTreeNode(new Privillege("Consulter"), listprestataire);
        TreeNode imprimerlistprestataire = new CheckboxTreeNode(new Privillege("Imprimer"), listprestataire);
        TreeNode notifierlistprestataire = new CheckboxTreeNode(new Privillege("Notifier"), listprestataire);
        // referentielmod' children definition.
        TreeNode creerreferentielmod = new CheckboxTreeNode(new Privillege("Creer"), referentielmod);
        TreeNode modifierreferentielmod = new CheckboxTreeNode(new Privillege("Modifier"), referentielmod);
        TreeNode suprimerreferentielmod = new CheckboxTreeNode(new Privillege("Supprimer"), referentielmod);
        TreeNode consulterreferentielmod = new CheckboxTreeNode(new Privillege("Consulter"), referentielmod);
        TreeNode imprimerreferentielmod = new CheckboxTreeNode(new Privillege("Imprimer"), referentielmod);
        TreeNode notifierreferentielmod = new CheckboxTreeNode(new Privillege("Notifier"), referentielmod);
        // acompte' children definition.
        TreeNode creeracompte = new CheckboxTreeNode(new Privillege("Creer"), acompte);
        TreeNode modifieracompte = new CheckboxTreeNode(new Privillege("Modifier"), acompte);
        TreeNode suprimeracompte = new CheckboxTreeNode(new Privillege("Supprimer"), acompte);
        TreeNode consulteracompte = new CheckboxTreeNode(new Privillege("Consulter"), acompte);
        TreeNode imprimeracompte = new CheckboxTreeNode(new Privillege("Imprimer"), acompte);
        TreeNode notifieracompte = new CheckboxTreeNode(new Privillege("Notifier"), acompte);

        // Projetmanagement' children definition.
        TreeNode tache = new CheckboxTreeNode(new Privillege("Nouvelle tâche"), projetmanagement);
        TreeNode management = new CheckboxTreeNode(new Privillege("Management"), projetmanagement);

        // tache' children definition.
        TreeNode creertache = new CheckboxTreeNode(new Privillege("Creer"), tache);
        TreeNode modifietache = new CheckboxTreeNode(new Privillege("Modifier"), tache);
        TreeNode suprimertache = new CheckboxTreeNode(new Privillege("Supprimer"), tache);
        TreeNode consultertache = new CheckboxTreeNode(new Privillege("Consulter"), tache);
        TreeNode imprimertache = new CheckboxTreeNode(new Privillege("Imprimer"), tache);
        TreeNode notifiertache = new CheckboxTreeNode(new Privillege("Notifier"), tache);
        // management' children definition.
        TreeNode creermanagement = new CheckboxTreeNode(new Privillege("Creer"), management);
        TreeNode modifiermanagement = new CheckboxTreeNode(new Privillege("Modifier"), management);
        TreeNode suprimermanagement = new CheckboxTreeNode(new Privillege("Supprimer"), management);
        TreeNode consultermanagement = new CheckboxTreeNode(new Privillege("Consulter"), management);
        TreeNode imprimermanagement = new CheckboxTreeNode(new Privillege("Imprimer"), management);
        TreeNode notifiermanagement = new CheckboxTreeNode(new Privillege("Notifier"), management);

        // approvisionnement' children definition.
        TreeNode devise = new CheckboxTreeNode(new Privillege("Devises"), approvisionnement);
        TreeNode unite = new CheckboxTreeNode(new Privillege("Unite de mesure"), approvisionnement);
        TreeNode lottechnique = new CheckboxTreeNode(new Privillege("Lot technique"), approvisionnement);
        TreeNode corpsetat = new CheckboxTreeNode(new Privillege("Corps d'etat"), approvisionnement);
        TreeNode materiel = new CheckboxTreeNode(new Privillege("Materiels"), approvisionnement);
        TreeNode fournisseur = new CheckboxTreeNode(new Privillege("Fournisseurs"), approvisionnement);
        TreeNode prix = new CheckboxTreeNode(new Privillege("Referenciel de prix"), approvisionnement);
        TreeNode expression = new CheckboxTreeNode(new Privillege("Expressions des besoins"), approvisionnement);
        TreeNode boncommande = new CheckboxTreeNode(new Privillege("Bon de commande"), approvisionnement);
        TreeNode livraison = new CheckboxTreeNode(new Privillege("Livraison"), approvisionnement);

        // devise' children definition.
        TreeNode creerdevise = new CheckboxTreeNode(new Privillege("Creer"), devise);
        TreeNode modifierdevise = new CheckboxTreeNode(new Privillege("Modifier"), devise);
        TreeNode suprimerdevise = new CheckboxTreeNode(new Privillege("Supprimer"), devise);
        TreeNode consulterdevise = new CheckboxTreeNode(new Privillege("Consulter"), devise);
        TreeNode imprimerdevise = new CheckboxTreeNode(new Privillege("Imprimer"), devise);
        TreeNode notifierdevise = new CheckboxTreeNode(new Privillege("Notifier"), devise);
        // unite' children definition.
        TreeNode creerunite = new CheckboxTreeNode(new Privillege("Creer"), unite);
        TreeNode modifierunite = new CheckboxTreeNode(new Privillege("Modifier"), unite);
        TreeNode suprimerunite = new CheckboxTreeNode(new Privillege("Supprimer"), unite);
        TreeNode consulterunite = new CheckboxTreeNode(new Privillege("Consulter"), unite);
        TreeNode imprimerunite = new CheckboxTreeNode(new Privillege("Imprimer"), unite);
        TreeNode notifierunite = new CheckboxTreeNode(new Privillege("Notifier"), unite);
        // lottechnique' children definition.
        TreeNode creerlottechnique = new CheckboxTreeNode(new Privillege("Creer"), lottechnique);
        TreeNode modifierlottechnique = new CheckboxTreeNode(new Privillege("Modifier"), lottechnique);
        TreeNode suprimerlottechnique = new CheckboxTreeNode(new Privillege("Supprimer"), lottechnique);
        TreeNode consulterlottechnique = new CheckboxTreeNode(new Privillege("Consulter"), lottechnique);
        TreeNode imprimerlottechnique = new CheckboxTreeNode(new Privillege("Imprimer"), lottechnique);
        TreeNode notifierlottechnique = new CheckboxTreeNode(new Privillege("Notifier"), lottechnique);
        // corpsetat' children definition.
        TreeNode creercorpsetat = new CheckboxTreeNode(new Privillege("Creer"), corpsetat);
        TreeNode modifiercorpsetat = new CheckboxTreeNode(new Privillege("Modifier"), corpsetat);
        TreeNode suprimercorpsetat = new CheckboxTreeNode(new Privillege("Supprimer"), corpsetat);
        TreeNode consultercorpsetat = new CheckboxTreeNode(new Privillege("Consulter"), corpsetat);
        TreeNode imprimercorpsetat = new CheckboxTreeNode(new Privillege("Imprimer"), corpsetat);
        TreeNode notifiercorpsetat = new CheckboxTreeNode(new Privillege("Notifier"), corpsetat);
        // materiel' children definition.
        TreeNode creermateriel = new CheckboxTreeNode(new Privillege("Creer"), materiel);
        TreeNode modifiermateriel = new CheckboxTreeNode(new Privillege("Modifier"), materiel);
        TreeNode suprimermateriel = new CheckboxTreeNode(new Privillege("Supprimer"), materiel);
        TreeNode consultermateriel = new CheckboxTreeNode(new Privillege("Consulter"), materiel);
        TreeNode imprimermateriel = new CheckboxTreeNode(new Privillege("Imprimer"), materiel);
        TreeNode notifiermateriel = new CheckboxTreeNode(new Privillege("Notifier"), materiel);
        // prix' children definition.
        TreeNode creerprix = new CheckboxTreeNode(new Privillege("Creer"), prix);
        TreeNode modifierprix = new CheckboxTreeNode(new Privillege("Modifier"), prix);
        TreeNode suprimerprix = new CheckboxTreeNode(new Privillege("Supprimer"), prix);
        TreeNode consulterprix = new CheckboxTreeNode(new Privillege("Consulter"), prix);
        TreeNode imprimerprix = new CheckboxTreeNode(new Privillege("Imprimer"), prix);
        TreeNode notifierprix = new CheckboxTreeNode(new Privillege("Notifier"), prix);
        // expression' children definition.
        TreeNode creerexpression = new CheckboxTreeNode(new Privillege("Creer"), expression);
        TreeNode modifierexpression = new CheckboxTreeNode(new Privillege("Modifier"), expression);
        TreeNode suprimerexpression = new CheckboxTreeNode(new Privillege("Supprimer"), expression);
        TreeNode consulterexpression = new CheckboxTreeNode(new Privillege("Consulter"), expression);
        TreeNode imprimerexpression = new CheckboxTreeNode(new Privillege("Imprimer"), expression);
        TreeNode notifierexpression = new CheckboxTreeNode(new Privillege("Notifier"), expression);
        // boncommande' children definition.
        TreeNode creerboncommande = new CheckboxTreeNode(new Privillege("Creer"), boncommande);
        TreeNode modifierboncommande = new CheckboxTreeNode(new Privillege("Modifier"), boncommande);
        TreeNode suprimerboncommande = new CheckboxTreeNode(new Privillege("Supprimer"), boncommande);
        TreeNode consulterboncommande = new CheckboxTreeNode(new Privillege("Consulter"), boncommande);
        TreeNode imprimerboncommande = new CheckboxTreeNode(new Privillege("Imprimer"), boncommande);
        TreeNode notifierboncommande = new CheckboxTreeNode(new Privillege("Notifier"), boncommande);

        // Fournisseur' children definition.
        TreeNode listfournisseur = new CheckboxTreeNode(new Privillege("Liste fournisseurs"), fournisseur);
        TreeNode referentielfournisseur = new CheckboxTreeNode(new Privillege("Referenciel fournisseurs"), fournisseur);
        TreeNode acomptefournisseur = new CheckboxTreeNode(new Privillege("Acomptes fournisseur"), fournisseur);

        // listfournisseur' children definition.
        TreeNode creerlistfournisseur = new CheckboxTreeNode(new Privillege("Creer"), listfournisseur);
        TreeNode modifierlistfournisseur = new CheckboxTreeNode(new Privillege("Modifier"), listfournisseur);
        TreeNode suprimerlistfournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), listfournisseur);
        TreeNode consulterlistfournisseur = new CheckboxTreeNode(new Privillege("Consulter"), listfournisseur);
        TreeNode imprimerlistfournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), listfournisseur);
        TreeNode notifierlistfournisseur = new CheckboxTreeNode(new Privillege("Notifier"), listfournisseur);
        // referentielfournisseur' children definition.
        TreeNode creerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Creer"), referentielfournisseur);
        TreeNode modifierreferentielfournisseur = new CheckboxTreeNode(new Privillege("Modifier"), referentielfournisseur);
        TreeNode suprimerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), referentielfournisseur);
        TreeNode consulterreferentielfournisseur = new CheckboxTreeNode(new Privillege("Consulter"), referentielfournisseur);
        TreeNode imprimerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), referentielfournisseur);
        TreeNode notifierreferentielfournisseur = new CheckboxTreeNode(new Privillege("Notifier"), referentielfournisseur);
        // devise' children definition.
        TreeNode creeracomptefournisseur = new CheckboxTreeNode(new Privillege("Creer"), acomptefournisseur);
        TreeNode modifieracomptefournisseur = new CheckboxTreeNode(new Privillege("Modifier"), acomptefournisseur);
        TreeNode suprimeracomptefournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), acomptefournisseur);
        TreeNode consulteracomptefournisseur = new CheckboxTreeNode(new Privillege("Consulter"), acomptefournisseur);
        TreeNode imprimeracomptefournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), acomptefournisseur);
        TreeNode notifieracomptefournisseur = new CheckboxTreeNode(new Privillege("Notifier"), acomptefournisseur);

        // livraison' children definition.
        TreeNode approchantier = new CheckboxTreeNode(new Privillege("Approvisionnement chantier"), livraison);

        // approchantier' children definition.
        TreeNode creerapprochantier = new CheckboxTreeNode(new Privillege("Creer"), approchantier);
        TreeNode modifierapprochantier = new CheckboxTreeNode(new Privillege("Modifier"), approchantier);
        TreeNode suprimerapprochantier = new CheckboxTreeNode(new Privillege("Supprimer"), approchantier);
        TreeNode consulterapprochantier = new CheckboxTreeNode(new Privillege("Consulter"), approchantier);
        TreeNode imprimeraapprochantier = new CheckboxTreeNode(new Privillege("Imprimer"), approchantier);
        TreeNode notifierapprochantier = new CheckboxTreeNode(new Privillege("Notifier"), approchantier);

        // stock' children definition.
        TreeNode entrepot = new CheckboxTreeNode(new Privillege("Entrepots"), stock);
        TreeNode inventaire = new CheckboxTreeNode(new Privillege("Inventaire"), stock);
        TreeNode majours = new CheckboxTreeNode(new Privillege("Mise à jour"), stock);
        TreeNode sortiestock = new CheckboxTreeNode(new Privillege("Sortie de stock"), stock);
        TreeNode historique = new CheckboxTreeNode(new Privillege("Historique livraison"), stock);

        // entrepot' children definition.
        TreeNode creerentrepot = new CheckboxTreeNode(new Privillege("Creer"), entrepot);
        TreeNode modifierentrepot = new CheckboxTreeNode(new Privillege("Modifier"), entrepot);
        TreeNode suprimerentrepot = new CheckboxTreeNode(new Privillege("Supprimer"), entrepot);
        TreeNode consulterentrepot = new CheckboxTreeNode(new Privillege("Consulter"), entrepot);
        TreeNode imprimerentrepot = new CheckboxTreeNode(new Privillege("Imprimer"), entrepot);
        TreeNode notifierentrepot = new CheckboxTreeNode(new Privillege("Notifier"), entrepot);
        // inventaire' children definition.
        TreeNode creerinventaire = new CheckboxTreeNode(new Privillege("Creer"), inventaire);
        TreeNode modifierinventaire = new CheckboxTreeNode(new Privillege("Modifier"), inventaire);
        TreeNode suprimerinventaire = new CheckboxTreeNode(new Privillege("Supprimer"), inventaire);
        TreeNode consulterinventaire = new CheckboxTreeNode(new Privillege("Consulter"), inventaire);
        TreeNode imprimerinventaire = new CheckboxTreeNode(new Privillege("Imprimer"), inventaire);
        TreeNode notifierinventaire = new CheckboxTreeNode(new Privillege("Notifier"), inventaire);
        // majours' children definition.
        TreeNode creermajours = new CheckboxTreeNode(new Privillege("Creer"), majours);
        TreeNode modifiermajours = new CheckboxTreeNode(new Privillege("Modifier"), majours);
        TreeNode suprimermajours = new CheckboxTreeNode(new Privillege("Supprimer"), majours);
        TreeNode consultermajours = new CheckboxTreeNode(new Privillege("Consulter"), majours);
        TreeNode imprimermajours = new CheckboxTreeNode(new Privillege("Imprimer"), majours);
        TreeNode notifiermajours = new CheckboxTreeNode(new Privillege("Notifier"), majours);
        // sortiestock' children definition.
        TreeNode creersortiestock = new CheckboxTreeNode(new Privillege("Creer"), sortiestock);
        TreeNode modifiersortiestock = new CheckboxTreeNode(new Privillege("Modifier"), sortiestock);
        TreeNode suprimersortiestock = new CheckboxTreeNode(new Privillege("Supprimer"), sortiestock);
        TreeNode consultersortiestock = new CheckboxTreeNode(new Privillege("Consulter"), sortiestock);
        TreeNode imprimersortiestock = new CheckboxTreeNode(new Privillege("Imprimer"), sortiestock);
        TreeNode notifiersortiestock = new CheckboxTreeNode(new Privillege("Notifier"), sortiestock);
        // historique' children definition.
        TreeNode creerhistorique = new CheckboxTreeNode(new Privillege("Creer"), historique);
        TreeNode modifierhistorique = new CheckboxTreeNode(new Privillege("Modifier"), historique);
        TreeNode suprimerhistorique = new CheckboxTreeNode(new Privillege("Supprimer"), historique);
        TreeNode consulterhistorique = new CheckboxTreeNode(new Privillege("Consulter"), historique);
        TreeNode imprimerhistorique = new CheckboxTreeNode(new Privillege("Imprimer"), historique);
        TreeNode notifierhistorique = new CheckboxTreeNode(new Privillege("Notifier"), historique);

        // opportunite' children definition.
        TreeNode creeropportunite = new CheckboxTreeNode(new Privillege("Creer"), opportunite);
        TreeNode modifieropportunite = new CheckboxTreeNode(new Privillege("Modifier"), opportunite);
        TreeNode suprimeropportunite = new CheckboxTreeNode(new Privillege("Supprimer"), opportunite);
        TreeNode consulteropportunite = new CheckboxTreeNode(new Privillege("Consulter"), opportunite);
        TreeNode imprimeropportunite = new CheckboxTreeNode(new Privillege("Imprimer"), opportunite);
        TreeNode notifieropportunite = new CheckboxTreeNode(new Privillege("Notifier"), opportunite);

        return root;
    }

    public TreeNode getCheckboxDocuments(List<Droitacces> allAcces, List<Menu> allMenus, List<Actionmenu> allActionmenus) {

        TreeNode root = new CheckboxTreeNode(new Privillege("Files"), null);

        TreeNode accueil = new CheckboxTreeNode(new Privillege("Accueil"), root);
        accueil.setSelected(true);

        TreeNode configuration = new CheckboxTreeNode(new Privillege("Configuration"), root);
        TreeNode personnel = new CheckboxTreeNode(new Privillege("Personnel"), root);
        TreeNode projets = new CheckboxTreeNode(new Privillege("Projets"), root);
        TreeNode approvisionnement = new CheckboxTreeNode(new Privillege("Approvisionnement"), root);
        TreeNode stock = new CheckboxTreeNode(new Privillege("Stock"), root);
        TreeNode opportunite = new CheckboxTreeNode(new Privillege("Opportunités"), root);

        // configuration' children definition.
        TreeNode role = new CheckboxTreeNode(new Privillege("Gestion des rôles"), configuration);
        TreeNode utilisateur = new CheckboxTreeNode(new Privillege("Gestion des utilisateurs"), configuration);

        // role' children definition.
        TreeNode creerrole = new CheckboxTreeNode(new Privillege("Creer"), role);
        TreeNode modifierrole = new CheckboxTreeNode(new Privillege("Modifier"), role);
        TreeNode suprimerrole = new CheckboxTreeNode(new Privillege("Supprimer"), role);
        TreeNode consulterrole = new CheckboxTreeNode(new Privillege("Consulter"), role);
        TreeNode imprimerrole = new CheckboxTreeNode(new Privillege("Imprimer"), role);
        TreeNode notifierrole = new CheckboxTreeNode(new Privillege("Notifier"), role);

        // role' children definition.
        TreeNode creeruser = new CheckboxTreeNode(new Privillege("Creer"), utilisateur);
        TreeNode modifieruser = new CheckboxTreeNode(new Privillege("Modifier"), utilisateur);
        TreeNode suprimeruser = new CheckboxTreeNode(new Privillege("Supprimer"), utilisateur);
        TreeNode consulteruser = new CheckboxTreeNode(new Privillege("Consulter"), utilisateur);
        TreeNode imprimeruser = new CheckboxTreeNode(new Privillege("Imprimer"), utilisateur);
        TreeNode notifieruser = new CheckboxTreeNode(new Privillege("Notifier"), utilisateur);

        /*Configuration default selected*/
        int nbElementActionGestionRole = 0;
        int nbElementActionGestionUser = 0;
        for (Actionmenu actionmenu : allActionmenus) {
            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Gestion des rôles")) {
                nbElementActionGestionRole++;
            }
            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Gestion des utilisateurs")) {
                nbElementActionGestionUser++;
            }
        }
        if ((nbElementActionGestionRole == 4) && (nbElementActionGestionUser == 4)) {
            configuration.setSelected(true);
        } else if ((nbElementActionGestionRole < 4) && (nbElementActionGestionUser == 4)) {

            utilisateur.setSelected(true);

            for (Actionmenu actionmenu : allActionmenus) {

                if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Gestion des rôles")) {

                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                        creerrole.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                        modifierrole.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                        suprimerrole.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                        consulterrole.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                        imprimerrole.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                        notifierrole.setSelected(true);
                    }
                }
            }
        } else if ((nbElementActionGestionRole == 4) && (nbElementActionGestionUser < 4)) {

            role.setSelected(true);

            for (Actionmenu actionmenu : allActionmenus) {
                if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Gestion des utilisateurs")) {
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                        creeruser.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                        modifieruser.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                        suprimeruser.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                        consulteruser.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                        imprimeruser.setSelected(true);
                    }
                    if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                        notifieruser.setSelected(true);
                    }
                }
            }

        } else {
            System.out.println("Aucun element coché !!!");
        }

        /*Fin Configuration default selected*/
        // Personnel' children definition.
        TreeNode contrat = new CheckboxTreeNode(new Privillege("Contrats"), personnel);
        TreeNode salaire = new CheckboxTreeNode(new Privillege("Salaire"), personnel);
        TreeNode pret = new CheckboxTreeNode(new Privillege("Prêt"), personnel);
        TreeNode indemnites = new CheckboxTreeNode(new Privillege("Indemnités"), personnel);

        // Contrat' children definition.
        TreeNode recrutement = new CheckboxTreeNode(new Privillege("Recrutement"), contrat);
        TreeNode stage = new CheckboxTreeNode(new Privillege("Stage"), contrat);

        // recrutement' children definition.
        TreeNode creerrecrutement = new CheckboxTreeNode(new Privillege("Creer"), recrutement);
        TreeNode modifierrecrutement = new CheckboxTreeNode(new Privillege("Modifier"), recrutement);
        TreeNode suprimerrecrutement = new CheckboxTreeNode(new Privillege("Supprimer"), recrutement);
        TreeNode consulterrecrutement = new CheckboxTreeNode(new Privillege("Consulter"), recrutement);
        TreeNode imprimerrecrutement = new CheckboxTreeNode(new Privillege("Imprimer"), recrutement);
        TreeNode notifierrecrutement = new CheckboxTreeNode(new Privillege("Notifier"), recrutement);
        // stage' children definition.
        TreeNode creerstage = new CheckboxTreeNode(new Privillege("Creer"), stage);
        TreeNode modifierstage = new CheckboxTreeNode(new Privillege("Modifier"), stage);
        TreeNode suprimerstage = new CheckboxTreeNode(new Privillege("Supprimer"), stage);
        TreeNode consulterstage = new CheckboxTreeNode(new Privillege("Consulter"), stage);
        TreeNode imprimerstage = new CheckboxTreeNode(new Privillege("Imprimer"), stage);
        TreeNode notifierstage = new CheckboxTreeNode(new Privillege("Notifier"), stage);
        // salaire' children definition.
        TreeNode creersalaire = new CheckboxTreeNode(new Privillege("Creer"), salaire);
        TreeNode modifiersalaire = new CheckboxTreeNode(new Privillege("Modifier"), salaire);
        TreeNode suprimersalaire = new CheckboxTreeNode(new Privillege("Supprimer"), salaire);
        TreeNode consultersalaire = new CheckboxTreeNode(new Privillege("Consulter"), salaire);
        TreeNode imprimersalaire = new CheckboxTreeNode(new Privillege("Imprimer"), salaire);
        TreeNode notifierssalaire = new CheckboxTreeNode(new Privillege("Notifier"), salaire);
        // pret' children definition.
        TreeNode creerpret = new CheckboxTreeNode(new Privillege("Creer"), pret);
        TreeNode modifierpret = new CheckboxTreeNode(new Privillege("Modifier"), pret);
        TreeNode suprimerpret = new CheckboxTreeNode(new Privillege("Supprimer"), pret);
        TreeNode consulterpret = new CheckboxTreeNode(new Privillege("Consulter"), pret);
        TreeNode imprimerpret = new CheckboxTreeNode(new Privillege("Imprimer"), pret);
        TreeNode notifierspret = new CheckboxTreeNode(new Privillege("Notifier"), pret);
        // pret' children definition.
        TreeNode creerindemnites = new CheckboxTreeNode(new Privillege("Creer"), indemnites);
        TreeNode modifierindemnites = new CheckboxTreeNode(new Privillege("Modifier"), indemnites);
        TreeNode suprimerindemnites = new CheckboxTreeNode(new Privillege("Supprimer"), indemnites);
        TreeNode consulterindemnites = new CheckboxTreeNode(new Privillege("Consulter"), indemnites);
        TreeNode imprimerindemnites = new CheckboxTreeNode(new Privillege("Imprimer"), indemnites);
        TreeNode notifierindemnites = new CheckboxTreeNode(new Privillege("Notifier"), indemnites);

        /*Configuration personnel selected*/
        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Recrutement")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerstage.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierstage.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerstage.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterstage.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerstage.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierstage.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Stage")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerrecrutement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierrecrutement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerrecrutement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterrecrutement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerrecrutement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierrecrutement.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Salaire")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creersalaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiersalaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimersalaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultersalaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimersalaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierssalaire.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Prêt")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerpret.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierpret.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerpret.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterpret.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerpret.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierspret.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Indemnités")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerindemnites.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierindemnites.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerindemnites.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterindemnites.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerindemnites.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierindemnites.setSelected(true);
                }
            }
        }

        // Projets' children definition.
        TreeNode gestion = new CheckboxTreeNode(new Privillege("Gestion"), projets);
        TreeNode chantiers = new CheckboxTreeNode(new Privillege("Chantiers"), projets);
        TreeNode prestataires = new CheckboxTreeNode(new Privillege("Prestataires"), projets);
        TreeNode projetmanagement = new CheckboxTreeNode(new Privillege("Project Management"), projets);

        // Gestion' children definition.
        TreeNode client = new CheckboxTreeNode(new Privillege("Clients"), gestion);
        TreeNode devis = new CheckboxTreeNode(new Privillege("Devis"), gestion);
        TreeNode marche = new CheckboxTreeNode(new Privillege("Marchés"), gestion);
        TreeNode facture = new CheckboxTreeNode(new Privillege("Factures"), gestion);
        TreeNode decompte = new CheckboxTreeNode(new Privillege("Decomptes"), gestion);

        // client' children definition.
        TreeNode creerclient = new CheckboxTreeNode(new Privillege("Creer"), client);
        TreeNode modifierclient = new CheckboxTreeNode(new Privillege("Modifier"), client);
        TreeNode suprimerclient = new CheckboxTreeNode(new Privillege("Supprimer"), client);
        TreeNode consulterclient = new CheckboxTreeNode(new Privillege("Consulter"), client);
        TreeNode imprimerclient = new CheckboxTreeNode(new Privillege("Imprimer"), client);
        TreeNode notifierclient = new CheckboxTreeNode(new Privillege("Notifier"), client);
        // marche' children definition.
        TreeNode creermarche = new CheckboxTreeNode(new Privillege("Creer"), marche);
        TreeNode modifiermarche = new CheckboxTreeNode(new Privillege("Modifier"), marche);
        TreeNode suprimermarche = new CheckboxTreeNode(new Privillege("Supprimer"), marche);
        TreeNode consultermarche = new CheckboxTreeNode(new Privillege("Consulter"), marche);
        TreeNode imprimermarche = new CheckboxTreeNode(new Privillege("Imprimer"), marche);
        TreeNode notifiermarche = new CheckboxTreeNode(new Privillege("Notifier"), marche);
        // devis' children definition.
        TreeNode creerdevis = new CheckboxTreeNode(new Privillege("Creer"), devis);
        TreeNode modifierdevis = new CheckboxTreeNode(new Privillege("Modifier"), devis);
        TreeNode suprimerdevis = new CheckboxTreeNode(new Privillege("Supprimer"), devis);
        TreeNode consulterdevis = new CheckboxTreeNode(new Privillege("Consulter"), devis);
        TreeNode imprimerdevis = new CheckboxTreeNode(new Privillege("Imprimer"), devis);
        TreeNode notifierdevis = new CheckboxTreeNode(new Privillege("Notifier"), devis);
        // facture' children definition.
        TreeNode creerfacture = new CheckboxTreeNode(new Privillege("Creer"), facture);
        TreeNode modifierfacture = new CheckboxTreeNode(new Privillege("Modifier"), facture);
        TreeNode suprimerfacture = new CheckboxTreeNode(new Privillege("Supprimer"), facture);
        TreeNode consulterfacture = new CheckboxTreeNode(new Privillege("Consulter"), facture);
        TreeNode imprimerfacture = new CheckboxTreeNode(new Privillege("Imprimer"), facture);
        TreeNode notifierfacture = new CheckboxTreeNode(new Privillege("Notifier"), facture);
        // decompte' children definition.
        TreeNode creerdecompte = new CheckboxTreeNode(new Privillege("Creer"), decompte);
        TreeNode modifierdecompte = new CheckboxTreeNode(new Privillege("Modifier"), decompte);
        TreeNode suprimerdecompte = new CheckboxTreeNode(new Privillege("Supprimer"), decompte);
        TreeNode consulterdecompte = new CheckboxTreeNode(new Privillege("Consulter"), decompte);
        TreeNode imprimerdecompte = new CheckboxTreeNode(new Privillege("Imprimer"), decompte);
        TreeNode notifierdecompte = new CheckboxTreeNode(new Privillege("Notifier"), decompte);
        // chantiers' children definition.
        TreeNode creerchantiers = new CheckboxTreeNode(new Privillege("Creer"), chantiers);
        TreeNode modifierchantiers = new CheckboxTreeNode(new Privillege("Modifier"), chantiers);
        TreeNode suprimerchantiers = new CheckboxTreeNode(new Privillege("Supprimer"), chantiers);
        TreeNode consulterchantiers = new CheckboxTreeNode(new Privillege("Consulter"), chantiers);
        TreeNode imprimerchantiers = new CheckboxTreeNode(new Privillege("Imprimer"), chantiers);
        TreeNode notifierchantiers = new CheckboxTreeNode(new Privillege("Notifier"), chantiers);

        // Prestataires' children definition.
        TreeNode listprestataire = new CheckboxTreeNode(new Privillege("Liste prestataires"), prestataires);
        TreeNode referentielmod = new CheckboxTreeNode(new Privillege("Referenciel Main-d'oeuvre"), prestataires);
        TreeNode acompte = new CheckboxTreeNode(new Privillege("Acomptes"), prestataires);

        // listprestataire' children definition.
        TreeNode creerlistprestataire = new CheckboxTreeNode(new Privillege("Creer"), listprestataire);
        TreeNode modifierlistprestataire = new CheckboxTreeNode(new Privillege("Modifier"), listprestataire);
        TreeNode suprimerlistprestataire = new CheckboxTreeNode(new Privillege("Supprimer"), listprestataire);
        TreeNode consulterlistprestataire = new CheckboxTreeNode(new Privillege("Consulter"), listprestataire);
        TreeNode imprimerlistprestataire = new CheckboxTreeNode(new Privillege("Imprimer"), listprestataire);
        TreeNode notifierlistprestataire = new CheckboxTreeNode(new Privillege("Notifier"), listprestataire);
        // referentielmod' children definition.
        TreeNode creerreferentielmod = new CheckboxTreeNode(new Privillege("Creer"), referentielmod);
        TreeNode modifierreferentielmod = new CheckboxTreeNode(new Privillege("Modifier"), referentielmod);
        TreeNode suprimerreferentielmod = new CheckboxTreeNode(new Privillege("Supprimer"), referentielmod);
        TreeNode consulterreferentielmod = new CheckboxTreeNode(new Privillege("Consulter"), referentielmod);
        TreeNode imprimerreferentielmod = new CheckboxTreeNode(new Privillege("Imprimer"), referentielmod);
        TreeNode notifierreferentielmod = new CheckboxTreeNode(new Privillege("Notifier"), referentielmod);
        // acompte' children definition.
        TreeNode creeracompte = new CheckboxTreeNode(new Privillege("Creer"), acompte);
        TreeNode modifieracompte = new CheckboxTreeNode(new Privillege("Modifier"), acompte);
        TreeNode suprimeracompte = new CheckboxTreeNode(new Privillege("Supprimer"), acompte);
        TreeNode consulteracompte = new CheckboxTreeNode(new Privillege("Consulter"), acompte);
        TreeNode imprimeracompte = new CheckboxTreeNode(new Privillege("Imprimer"), acompte);
        TreeNode notifieracompte = new CheckboxTreeNode(new Privillege("Notifier"), acompte);

        // Projetmanagement' children definition.
        TreeNode tache = new CheckboxTreeNode(new Privillege("Nouvelle tâche"), projetmanagement);
        TreeNode management = new CheckboxTreeNode(new Privillege("Management"), projetmanagement);

        // tache' children definition.
        TreeNode creertache = new CheckboxTreeNode(new Privillege("Creer"), tache);
        TreeNode modifietache = new CheckboxTreeNode(new Privillege("Modifier"), tache);
        TreeNode suprimertache = new CheckboxTreeNode(new Privillege("Supprimer"), tache);
        TreeNode consultertache = new CheckboxTreeNode(new Privillege("Consulter"), tache);
        TreeNode imprimertache = new CheckboxTreeNode(new Privillege("Imprimer"), tache);
        TreeNode notifiertache = new CheckboxTreeNode(new Privillege("Notifier"), tache);
        // management' children definition.
        TreeNode creermanagement = new CheckboxTreeNode(new Privillege("Creer"), management);
        TreeNode modifiermanagement = new CheckboxTreeNode(new Privillege("Modifier"), management);
        TreeNode suprimermanagement = new CheckboxTreeNode(new Privillege("Supprimer"), management);
        TreeNode consultermanagement = new CheckboxTreeNode(new Privillege("Consulter"), management);
        TreeNode imprimermanagement = new CheckboxTreeNode(new Privillege("Imprimer"), management);
        TreeNode notifiermanagement = new CheckboxTreeNode(new Privillege("Notifier"), management);

        /*Configuration personnel projet*/
        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Clients")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerclient.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierclient.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerclient.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterclient.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerclient.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierclient.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Devis")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerdevis.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierdevis.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerdevis.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterdevis.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerdevis.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierdevis.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Marchés")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creermarche.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiermarche.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimermarche.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultermarche.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimermarche.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiermarche.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Factures")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerfacture.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierfacture.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerfacture.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterfacture.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerfacture.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierfacture.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Decomptes")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerdecompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierdecompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerdecompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterdecompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerdecompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierdecompte.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Chantiers")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerchantiers.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierchantiers.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerchantiers.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterchantiers.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerchantiers.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierchantiers.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Liste prestataires")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerlistprestataire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierlistprestataire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerlistprestataire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterlistprestataire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerlistprestataire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierlistprestataire.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Referenciel Main-d'oeuvre")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerreferentielmod.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierreferentielmod.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerreferentielmod.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterreferentielmod.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerreferentielmod.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierreferentielmod.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Acomptes")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creeracompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifieracompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimeracompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulteracompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimeracompte.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifieracompte.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Nouvelle tâche")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creertache.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifietache.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimertache.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultertache.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimertache.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiertache.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Management")) {
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creermanagement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiermanagement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimermanagement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultermanagement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimermanagement.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiermanagement.setSelected(true);
                }
            }
        }

        // approvisionnement' children definition.
        TreeNode devise = new CheckboxTreeNode(new Privillege("Devises"), approvisionnement);
        TreeNode unite = new CheckboxTreeNode(new Privillege("Unite de mesure"), approvisionnement);
        TreeNode lottechnique = new CheckboxTreeNode(new Privillege("Lot technique"), approvisionnement);
        TreeNode corpsetat = new CheckboxTreeNode(new Privillege("Corps d'etat"), approvisionnement);
        TreeNode materiel = new CheckboxTreeNode(new Privillege("Materiels"), approvisionnement);
        TreeNode fournisseur = new CheckboxTreeNode(new Privillege("Fournisseurs"), approvisionnement);
        TreeNode prix = new CheckboxTreeNode(new Privillege("Referenciel de prix"), approvisionnement);
        TreeNode expression = new CheckboxTreeNode(new Privillege("Expressions des besoins"), approvisionnement);
        TreeNode boncommande = new CheckboxTreeNode(new Privillege("Bon de commande"), approvisionnement);
        TreeNode livraison = new CheckboxTreeNode(new Privillege("Livraison"), approvisionnement);

        // devise' children definition.
        TreeNode creerdevise = new CheckboxTreeNode(new Privillege("Creer"), devise);
        TreeNode modifierdevise = new CheckboxTreeNode(new Privillege("Modifier"), devise);
        TreeNode suprimerdevise = new CheckboxTreeNode(new Privillege("Supprimer"), devise);
        TreeNode consulterdevise = new CheckboxTreeNode(new Privillege("Consulter"), devise);
        TreeNode imprimerdevise = new CheckboxTreeNode(new Privillege("Imprimer"), devise);
        TreeNode notifierdevise = new CheckboxTreeNode(new Privillege("Notifier"), devise);
        // unite' children definition.
        TreeNode creerunite = new CheckboxTreeNode(new Privillege("Creer"), unite);
        TreeNode modifierunite = new CheckboxTreeNode(new Privillege("Modifier"), unite);
        TreeNode suprimerunite = new CheckboxTreeNode(new Privillege("Supprimer"), unite);
        TreeNode consulterunite = new CheckboxTreeNode(new Privillege("Consulter"), unite);
        TreeNode imprimerunite = new CheckboxTreeNode(new Privillege("Imprimer"), unite);
        TreeNode notifierunite = new CheckboxTreeNode(new Privillege("Notifier"), unite);
        // lottechnique' children definition.
        TreeNode creerlottechnique = new CheckboxTreeNode(new Privillege("Creer"), lottechnique);
        TreeNode modifierlottechnique = new CheckboxTreeNode(new Privillege("Modifier"), lottechnique);
        TreeNode suprimerlottechnique = new CheckboxTreeNode(new Privillege("Supprimer"), lottechnique);
        TreeNode consulterlottechnique = new CheckboxTreeNode(new Privillege("Consulter"), lottechnique);
        TreeNode imprimerlottechnique = new CheckboxTreeNode(new Privillege("Imprimer"), lottechnique);
        TreeNode notifierlottechnique = new CheckboxTreeNode(new Privillege("Notifier"), lottechnique);
        // corpsetat' children definition.
        TreeNode creercorpsetat = new CheckboxTreeNode(new Privillege("Creer"), corpsetat);
        TreeNode modifiercorpsetat = new CheckboxTreeNode(new Privillege("Modifier"), corpsetat);
        TreeNode suprimercorpsetat = new CheckboxTreeNode(new Privillege("Supprimer"), corpsetat);
        TreeNode consultercorpsetat = new CheckboxTreeNode(new Privillege("Consulter"), corpsetat);
        TreeNode imprimercorpsetat = new CheckboxTreeNode(new Privillege("Imprimer"), corpsetat);
        TreeNode notifiercorpsetat = new CheckboxTreeNode(new Privillege("Notifier"), corpsetat);
        // materiel' children definition.
        TreeNode creermateriel = new CheckboxTreeNode(new Privillege("Creer"), materiel);
        TreeNode modifiermateriel = new CheckboxTreeNode(new Privillege("Modifier"), materiel);
        TreeNode suprimermateriel = new CheckboxTreeNode(new Privillege("Supprimer"), materiel);
        TreeNode consultermateriel = new CheckboxTreeNode(new Privillege("Consulter"), materiel);
        TreeNode imprimermateriel = new CheckboxTreeNode(new Privillege("Imprimer"), materiel);
        TreeNode notifiermateriel = new CheckboxTreeNode(new Privillege("Notifier"), materiel);
        // prix' children definition.
        TreeNode creerprix = new CheckboxTreeNode(new Privillege("Creer"), prix);
        TreeNode modifierprix = new CheckboxTreeNode(new Privillege("Modifier"), prix);
        TreeNode suprimerprix = new CheckboxTreeNode(new Privillege("Supprimer"), prix);
        TreeNode consulterprix = new CheckboxTreeNode(new Privillege("Consulter"), prix);
        TreeNode imprimerprix = new CheckboxTreeNode(new Privillege("Imprimer"), prix);
        TreeNode notifierprix = new CheckboxTreeNode(new Privillege("Notifier"), prix);
        // expression' children definition.
        TreeNode creerexpression = new CheckboxTreeNode(new Privillege("Creer"), expression);
        TreeNode modifierexpression = new CheckboxTreeNode(new Privillege("Modifier"), expression);
        TreeNode suprimerexpression = new CheckboxTreeNode(new Privillege("Supprimer"), expression);
        TreeNode consulterexpression = new CheckboxTreeNode(new Privillege("Consulter"), expression);
        TreeNode imprimerexpression = new CheckboxTreeNode(new Privillege("Imprimer"), expression);
        TreeNode notifierexpression = new CheckboxTreeNode(new Privillege("Notifier"), expression);
        // boncommande' children definition.
        TreeNode creerboncommande = new CheckboxTreeNode(new Privillege("Creer"), boncommande);
        TreeNode modifierboncommande = new CheckboxTreeNode(new Privillege("Modifier"), boncommande);
        TreeNode suprimerboncommande = new CheckboxTreeNode(new Privillege("Supprimer"), boncommande);
        TreeNode consulterboncommande = new CheckboxTreeNode(new Privillege("Consulter"), boncommande);
        TreeNode imprimerboncommande = new CheckboxTreeNode(new Privillege("Imprimer"), boncommande);
        TreeNode notifierboncommande = new CheckboxTreeNode(new Privillege("Notifier"), boncommande);

        // Fournisseur' children definition.
        TreeNode listfournisseur = new CheckboxTreeNode(new Privillege("Liste fournisseurs"), fournisseur);
        TreeNode referentielfournisseur = new CheckboxTreeNode(new Privillege("Referenciel fournisseurs"), fournisseur);
        TreeNode acomptefournisseur = new CheckboxTreeNode(new Privillege("Acomptes fournisseur"), fournisseur);

        // listfournisseur' children definition.
        TreeNode creerlistfournisseur = new CheckboxTreeNode(new Privillege("Creer"), listfournisseur);
        TreeNode modifierlistfournisseur = new CheckboxTreeNode(new Privillege("Modifier"), listfournisseur);
        TreeNode suprimerlistfournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), listfournisseur);
        TreeNode consulterlistfournisseur = new CheckboxTreeNode(new Privillege("Consulter"), listfournisseur);
        TreeNode imprimerlistfournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), listfournisseur);
        TreeNode notifierlistfournisseur = new CheckboxTreeNode(new Privillege("Notifier"), listfournisseur);
        // referentielfournisseur' children definition.
        TreeNode creerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Creer"), referentielfournisseur);
        TreeNode modifierreferentielfournisseur = new CheckboxTreeNode(new Privillege("Modifier"), referentielfournisseur);
        TreeNode suprimerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), referentielfournisseur);
        TreeNode consulterreferentielfournisseur = new CheckboxTreeNode(new Privillege("Consulter"), referentielfournisseur);
        TreeNode imprimerreferentielfournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), referentielfournisseur);
        TreeNode notifierreferentielfournisseur = new CheckboxTreeNode(new Privillege("Notifier"), referentielfournisseur);
        // devise' children definition.
        TreeNode creeracomptefournisseur = new CheckboxTreeNode(new Privillege("Creer"), acomptefournisseur);
        TreeNode modifieracomptefournisseur = new CheckboxTreeNode(new Privillege("Modifier"), acomptefournisseur);
        TreeNode suprimeracomptefournisseur = new CheckboxTreeNode(new Privillege("Supprimer"), acomptefournisseur);
        TreeNode consulteracomptefournisseur = new CheckboxTreeNode(new Privillege("Consulter"), acomptefournisseur);
        TreeNode imprimeracomptefournisseur = new CheckboxTreeNode(new Privillege("Imprimer"), acomptefournisseur);
        TreeNode notifieracomptefournisseur = new CheckboxTreeNode(new Privillege("Notifier"), acomptefournisseur);

        // livraison' children definition.
        TreeNode approchantier = new CheckboxTreeNode(new Privillege("Approvisionnement chantier"), livraison);

        // approchantier' children definition.
        TreeNode creerapprochantier = new CheckboxTreeNode(new Privillege("Creer"), approchantier);
        TreeNode modifierapprochantier = new CheckboxTreeNode(new Privillege("Modifier"), approchantier);
        TreeNode suprimerapprochantier = new CheckboxTreeNode(new Privillege("Supprimer"), approchantier);
        TreeNode consulterapprochantier = new CheckboxTreeNode(new Privillege("Consulter"), approchantier);
        TreeNode imprimeraapprochantier = new CheckboxTreeNode(new Privillege("Imprimer"), approchantier);
        TreeNode notifierapprochantier = new CheckboxTreeNode(new Privillege("Notifier"), approchantier);

        /*Configuration approvisionnement*/
        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Devises")) {
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerdevise.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierdevise.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerdevise.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterdevise.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerdevise.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierdevise.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Unite de mesure")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierunite.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Lot technique")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerlottechnique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierlottechnique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerlottechnique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterlottechnique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerlottechnique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierlottechnique.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Corps d'etat")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creercorpsetat.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiercorpsetat.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimercorpsetat.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultercorpsetat.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimercorpsetat.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiercorpsetat.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Materiels")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creermateriel.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiermateriel.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimermateriel.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultermateriel.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimermateriel.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiermateriel.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Liste fournisseurs")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerlistfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierlistfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerlistfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterlistfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerlistfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierlistfournisseur.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Referenciel fournisseurs")) {
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerreferentielfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierreferentielfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerreferentielfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterreferentielfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerreferentielfournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierreferentielfournisseur.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Acomptes fournisseur")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creeracomptefournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifieracomptefournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimeracomptefournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulteracomptefournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimeracomptefournisseur.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifieracomptefournisseur.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Referenciel de prix")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerprix.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierprix.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerprix.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterprix.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerprix.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierprix.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Expressions des besoins")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerexpression.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierexpression.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerexpression.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterexpression.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerexpression.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierexpression.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Bon de commande")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerboncommande.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierboncommande.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerboncommande.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterboncommande.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerboncommande.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierboncommande.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Approvisionnement chantier")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerapprochantier.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierapprochantier.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerapprochantier.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterapprochantier.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimeraapprochantier.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierapprochantier.setSelected(true);
                }
            }
        }

        // stock' children definition.
        TreeNode entrepot = new CheckboxTreeNode(new Privillege("Entrepots"), stock);
        TreeNode inventaire = new CheckboxTreeNode(new Privillege("Inventaire"), stock);
        TreeNode majours = new CheckboxTreeNode(new Privillege("Mise à jour"), stock);
        TreeNode sortiestock = new CheckboxTreeNode(new Privillege("Sortie de stock"), stock);
        TreeNode historique = new CheckboxTreeNode(new Privillege("Historique livraison"), stock);

        // entrepot' children definition.
        TreeNode creerentrepot = new CheckboxTreeNode(new Privillege("Creer"), entrepot);
        TreeNode modifierentrepot = new CheckboxTreeNode(new Privillege("Modifier"), entrepot);
        TreeNode suprimerentrepot = new CheckboxTreeNode(new Privillege("Supprimer"), entrepot);
        TreeNode consulterentrepot = new CheckboxTreeNode(new Privillege("Consulter"), entrepot);
        TreeNode imprimerentrepot = new CheckboxTreeNode(new Privillege("Imprimer"), entrepot);
        TreeNode notifierentrepot = new CheckboxTreeNode(new Privillege("Notifier"), entrepot);
        // inventaire' children definition.
        TreeNode creerinventaire = new CheckboxTreeNode(new Privillege("Creer"), inventaire);
        TreeNode modifierinventaire = new CheckboxTreeNode(new Privillege("Modifier"), inventaire);
        TreeNode suprimerinventaire = new CheckboxTreeNode(new Privillege("Supprimer"), inventaire);
        TreeNode consulterinventaire = new CheckboxTreeNode(new Privillege("Consulter"), inventaire);
        TreeNode imprimerinventaire = new CheckboxTreeNode(new Privillege("Imprimer"), inventaire);
        TreeNode notifierinventaire = new CheckboxTreeNode(new Privillege("Notifier"), inventaire);
        // majours' children definition.
        TreeNode creermajours = new CheckboxTreeNode(new Privillege("Creer"), majours);
        TreeNode modifiermajours = new CheckboxTreeNode(new Privillege("Modifier"), majours);
        TreeNode suprimermajours = new CheckboxTreeNode(new Privillege("Supprimer"), majours);
        TreeNode consultermajours = new CheckboxTreeNode(new Privillege("Consulter"), majours);
        TreeNode imprimermajours = new CheckboxTreeNode(new Privillege("Imprimer"), majours);
        TreeNode notifiermajours = new CheckboxTreeNode(new Privillege("Notifier"), majours);
        // sortiestock' children definition.
        TreeNode creersortiestock = new CheckboxTreeNode(new Privillege("Creer"), sortiestock);
        TreeNode modifiersortiestock = new CheckboxTreeNode(new Privillege("Modifier"), sortiestock);
        TreeNode suprimersortiestock = new CheckboxTreeNode(new Privillege("Supprimer"), sortiestock);
        TreeNode consultersortiestock = new CheckboxTreeNode(new Privillege("Consulter"), sortiestock);
        TreeNode imprimersortiestock = new CheckboxTreeNode(new Privillege("Imprimer"), sortiestock);
        TreeNode notifiersortiestock = new CheckboxTreeNode(new Privillege("Notifier"), sortiestock);
        // historique' children definition.
        TreeNode creerhistorique = new CheckboxTreeNode(new Privillege("Creer"), historique);
        TreeNode modifierhistorique = new CheckboxTreeNode(new Privillege("Modifier"), historique);
        TreeNode suprimerhistorique = new CheckboxTreeNode(new Privillege("Supprimer"), historique);
        TreeNode consulterhistorique = new CheckboxTreeNode(new Privillege("Consulter"), historique);
        TreeNode imprimerhistorique = new CheckboxTreeNode(new Privillege("Imprimer"), historique);
        TreeNode notifierhistorique = new CheckboxTreeNode(new Privillege("Notifier"), historique);

        // opportunite' children definition.
        TreeNode creeropportunite = new CheckboxTreeNode(new Privillege("Creer"), opportunite);
        TreeNode modifieropportunite = new CheckboxTreeNode(new Privillege("Modifier"), opportunite);
        TreeNode suprimeropportunite = new CheckboxTreeNode(new Privillege("Supprimer"), opportunite);
        TreeNode consulteropportunite = new CheckboxTreeNode(new Privillege("Consulter"), opportunite);
        TreeNode imprimeropportunite = new CheckboxTreeNode(new Privillege("Imprimer"), opportunite);
        TreeNode notifieropportunite = new CheckboxTreeNode(new Privillege("Notifier"), opportunite);

        /*Configuration stock*/
        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Entrepots")) {
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerentrepot.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierentrepot.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerentrepot.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterentrepot.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerentrepot.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierentrepot.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Inventaire")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerinventaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierinventaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerinventaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterinventaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerinventaire.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierinventaire.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Mise à jour")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creermajours.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiermajours.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimermajours.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultermajours.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimermajours.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiermajours.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Sortie de stock")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creersortiestock.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifiersortiestock.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimersortiestock.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consultersortiestock.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimersortiestock.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifiersortiestock.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Historique livraison")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creerhistorique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifierhistorique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimerhistorique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulterhistorique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimerhistorique.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifierhistorique.setSelected(true);
                }
            }
        }

        for (Actionmenu actionmenu : allActionmenus) {

            if (actionmenu.getIdMenu().getLibelemenu().equalsIgnoreCase("Opportunités")) {

                if (actionmenu.getLibelleaction().equalsIgnoreCase("Creer")) {
                    creeropportunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Modifier")) {
                    modifieropportunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Supprimer")) {
                    suprimeropportunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Consulter")) {
                    consulteropportunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Imprimer")) {
                    imprimeropportunite.setSelected(true);
                }
                if (actionmenu.getLibelleaction().equalsIgnoreCase("Notifier")) {
                    notifieropportunite.setSelected(true);
                }
            }
        }

        return root;
    }

}
