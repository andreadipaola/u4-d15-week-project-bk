package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDao {
	private final EntityManager entityManager;

	public UtenteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void salvaUtente(Utente utente) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(utente);
		transaction.commit();
		log.info("Utente salvato correttamente!");
	}
}