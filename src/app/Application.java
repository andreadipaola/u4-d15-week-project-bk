package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.OperaLetterariaDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import enums.Periodicita;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		log.info("Hello World!!");
		System.out.println();
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		OperaLetterariaDao operaDao = new OperaLetterariaDao(entityManager);
		UtenteDao utenteDao = new UtenteDao(entityManager);
		PrestitoDao prestitoDao = new PrestitoDao(entityManager);

		Libro libro = new Libro("Le cronache del ghiaccio e del fuoco", 1999, 829, "George R.R. Martin", "Fantasy");
		Rivista rivista = new Rivista("Focus #123", 2000, 60, Periodicita.MENSILE);
		Utente utente = new Utente("Mario", "Rossi", LocalDate.parse("1977-07-07"));
		Prestito prestito1 = new Prestito(1, libro, LocalDate.parse("2022-12-12"), LocalDate.parse("2023-01-10"),
				LocalDate.parse("2023-01-10"));

		operaDao.salvaOpera(rivista);
		operaDao.salvaOpera(libro);
		utenteDao.salvaUtente(utente);
		prestitoDao.salvaPrestito(prestito1);

		entityManager.close();
		entityManagerFactory.close();

	}

}
