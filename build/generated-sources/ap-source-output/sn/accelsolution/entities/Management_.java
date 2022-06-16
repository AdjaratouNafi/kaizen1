package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Management.class)
public class Management_ { 

    public static volatile SingularAttribute<Management, Prestataireprim> idprestatairePrim;
    public static volatile SingularAttribute<Management, String> tache;
    public static volatile SingularAttribute<Management, Integer> idManagement;
    public static volatile SingularAttribute<Management, Marche> idMarche;
    public static volatile SingularAttribute<Management, String> typeTache;
    public static volatile SingularAttribute<Management, Integer> terminer;
    public static volatile SingularAttribute<Management, String> couleur;
    public static volatile SingularAttribute<Management, Prestataire> idPrestataire;
    public static volatile SingularAttribute<Management, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Management, String> etat;
    public static volatile SingularAttribute<Management, Chantier> idChantier;
    public static volatile SingularAttribute<Management, Integer> predecesseur;
    public static volatile SingularAttribute<Management, String> datedebut;
    public static volatile SingularAttribute<Management, String> duree;
    public static volatile SingularAttribute<Management, String> datefin;
    public static volatile SingularAttribute<Management, String> commentaire;

}