package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Demande;
import sn.accelsolution.entities.Rembourssement;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Pret.class)
public class Pret_ { 

    public static volatile SingularAttribute<Pret, Integer> perioderestante;
    public static volatile SingularAttribute<Pret, String> datePret;
    public static volatile SingularAttribute<Pret, String> motifPret;
    public static volatile SingularAttribute<Pret, String> montantR;
    public static volatile SingularAttribute<Pret, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Pret, String> etatPret;
    public static volatile SingularAttribute<Pret, Integer> suivi;
    public static volatile SingularAttribute<Pret, String> montantPret;
    public static volatile ListAttribute<Pret, Demande> demandeList;
    public static volatile SingularAttribute<Pret, String> premierpayement;
    public static volatile ListAttribute<Pret, Rembourssement> rembourssementList;
    public static volatile SingularAttribute<Pret, Integer> idPret;
    public static volatile SingularAttribute<Pret, String> cloture;
    public static volatile SingularAttribute<Pret, String> montanAp;

}