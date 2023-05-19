package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
		System.out.println("Elemento salvato correttamente!");
	}

	public void ottieniPrestitiDaUtente(long utente) {
//		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, anno);
		try {
			Query query = entityManager.createNamedQuery("cercaPrestitiDaUtente");
			query.setParameter("utente", utente);

			List<Prestito> risultato = query.getResultList();

			System.out.println("Ricerca elementi per tessera utente:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (Prestito prestito : risultato) {
					System.out.println(prestito);
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniPrestitiScaduti() {
//		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, anno);
		try {
			Query query = entityManager.createNamedQuery("cercaPrestitiScaduti");

			List<Prestito> risultato = query.getResultList();

			System.out.println("Ricerca elementi non acnora restituiti:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (Prestito prestito : risultato) {
					System.out.println(prestito);
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

}
