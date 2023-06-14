package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Prestito;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrestitoDao {
	private final EntityManager entityManager;

	public PrestitoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void salvaPrestito(Prestito prestito) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(prestito);
		transaction.commit();
		log.info("Prestito salvato correttamente!");
	}

	public void ottieniPrestitiDaUtente(UUID numeroTessera) {
		try {
			TypedQuery<Prestito> query = entityManager.createNamedQuery("cercaPrestitiDaUtente", Prestito.class);
			query.setParameter("numeroTessera", numeroTessera);

			List<Prestito> prestiti = query.getResultList();

			System.out.println();
			System.out.println("Ricerca prestiti per tessera utente:");

			if (prestiti.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun prestito associato a questo numero di tessera.");
			} else {
				for (Prestito prestito : prestiti) {
					log.info(prestito.toString());
				}
			}
		} catch (Exception e) {
			log.error(
					"ATTENZIONE!!! C'è stato un errore nel reperire i prestiti effettuati dall'utente con tessera numero: "
							+ numeroTessera + ".");
		}
	}

	public void ottieniPrestitiScaduti() {
		try {
			Query query = entityManager.createNamedQuery("cercaPrestitiScaduti");

			List<Prestito> risultato = query.getResultList();

			System.out.println("Ricerca elementi non acnora restituiti:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (Prestito prestito : risultato) {
					log.info(prestito.toString());
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore nel reperire i prestiti non ancora restituiti.");
		}
	}

//	private Utente ottieniUtenteByNumeroTessera(UUID numeroTessera) {
//		Query query = entityManager.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera");
//		query.setParameter("numeroTessera", numeroTessera);
//		return (Utente) query.getSingleResult();
//	}

}
