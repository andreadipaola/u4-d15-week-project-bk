package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;

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
		System.out.println("Utente salvato correttamente!");
	}
}
