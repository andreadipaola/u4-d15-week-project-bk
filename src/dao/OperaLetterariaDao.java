package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Libro;
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
		System.out.println("Elemento salvato correttamente!");
	}

	public void ottieniOperaDaIsbn(long isbn) {
		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, isbn);
		if (operaTrovata != null) {
			System.out.println(operaTrovata);
		} else {
			System.out.println("Ci dispiace ma l'elemento con ISBN " + isbn + " non è stato trovato");
		}
	}

	public void ottieniOperaDaAnno(int anno) {
//		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, anno);
		try {
			Query query = entityManager.createNamedQuery("cercaPerAnno");
			query.setParameter("anno", anno);

			List<OperaLetteraria> risultato = query.getResultList();

			System.out.println("Ricerca elemento per anno di Pubblicazione:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (OperaLetteraria opera : risultato) {
					System.out.println(opera);
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaAutore(String autore) {
//		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, anno);
		try {
			Query query = entityManager.createNamedQuery("cercaPerAutore");
			query.setParameter("autore", autore);

			List<Libro> risultato = query.getResultList();

			System.out.println("Ricerca elemento per autore:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento in quell'anno.");
			} else {
				for (Libro libro : risultato) {
					System.out.println(libro);
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaTitolo(String titolo) {
//		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, anno);
		try {
			Query query = entityManager.createNamedQuery("cercaPerTitolo");
			query.setParameter("titolo", "%" + titolo + "%");

			List<OperaLetteraria> risultato = query.getResultList();

			System.out.println("Ricerca elemento per titolo:");

			if (risultato.isEmpty()) {
				log.error("Ci dispiace non abbiamo trovato alcun elemento con questo titolo.");
			} else {
				for (OperaLetteraria elemento : risultato) {
					System.out.println(elemento);
				}
			}
		} catch (Exception e) {
			log.error("ATTENZIONE!!! C'è stato un errore.");
		}
	}

	public void ottieniOperaDaIsbnECancella(long isbn) {
		OperaLetteraria operaTrovata = entityManager.find(OperaLetteraria.class, isbn);
		if (operaTrovata != null) {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(operaTrovata);
			transaction.commit();
			System.out.println("Elemento con ISBN " + isbn + " eliminato con successo!");
		} else {
			System.out.println("Ci dispiace ma l'elemento con ISBN " + isbn + " non è stato trovato");
		}
	}

}
