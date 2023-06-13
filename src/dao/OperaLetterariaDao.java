package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.OperaLetteraria;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperaLetterariaDao {
	private final EntityManager entityManager;

	public OperaLetterariaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void salvaOpera(OperaLetteraria opera) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(opera);
		transaction.commit();
		log.info("Opera salvata correttamente!");
	}

	public void ottieniOperaDaIsbn(UUID isbn) {
		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, isbn);
		System.out.println();
		System.out.println("Ricerca elemento per ISBN:");
		if (operaTrovata != null) {
			log.info(operaTrovata.toString());
		} else {
			log.info("Ci dispiace ma l'elemento con ISBN " + isbn + " non è stato trovato");
		}
	}

	public void ottieniOperaDaAnno(int anno) {
		try {
			TypedQuery<OperaLetteraria> query = entityManager.createNamedQuery("cercaPerAnno", OperaLetteraria.class);
//			Query query = entityManager.createNamedQuery("cercaPerAnno");
			query.setParameter("anno", anno);

			List<OperaLetteraria> risultato = query.getResultList();
			System.out.println();
			System.out.println("Ricerca elemento per anno di Pubblicazione:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (OperaLetteraria opera : risultato) {
					log.info(opera.toString());
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaAutore(String autore) {
		try {
			TypedQuery<OperaLetteraria> query = entityManager.createNamedQuery("cercaPerAutore", OperaLetteraria.class);
//			Query query = entityManager.createNamedQuery("cercaPerAutore");
			query.setParameter("autore", "%" + autore.toLowerCase() + "%");

			List<OperaLetteraria> risultato = query.getResultList();
			System.out.println();
			System.out.println("Ricerca elemento per autore o parte di esso:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento per questo autore.");
			} else {
				for (OperaLetteraria opera : risultato) {
					log.info(opera.toString());
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaTitolo(String titolo) {
		try {
			TypedQuery<OperaLetteraria> query = entityManager.createNamedQuery("cercaPerTitolo", OperaLetteraria.class);
//			Query query = entityManager.createNamedQuery("cercaPerTitolo");
			query.setParameter("titolo", "%" + titolo.toLowerCase() + "%");

			List<OperaLetteraria> risultato = query.getResultList();
			System.out.println();
			System.out.println("Ricerca elemento per titolo o parte di esso:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento con questo titolo.");
			} else {
				for (OperaLetteraria elemento : risultato) {
					log.info(elemento.toString());
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaIsbnECancella(UUID isbn) {
		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, isbn);
		System.out.println();
		System.out.println("Ricerca elemento per ISBN con successiva eliminazione:");
		if (operaTrovata != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(operaTrovata);
			transaction.commit();
			log.info("Elemento con ISBN " + isbn + " eliminato con successo!");
		} else {
			log.info("Ci dispiace ma non è stato possibile trovare l'elemento con ISBN " + isbn);
		}
	}

}
