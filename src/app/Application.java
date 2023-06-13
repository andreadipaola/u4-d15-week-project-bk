package app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.OperaLetterariaDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.Libro;
import entities.OperaLetteraria;
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

		// INSTANZIO I VARI DAO
		OperaLetterariaDao operaLetterariaDao = new OperaLetterariaDao(entityManager);
		UtenteDao utenteDao = new UtenteDao(entityManager);
		PrestitoDao prestitoDao = new PrestitoDao(entityManager);

		// CREO UN UTENTE
		Utente utente1 = new Utente("Mario", "Rossi", LocalDate.parse("1977-07-07"));
		utenteDao.salvaUtente(utente1);

		// CREO UN LIBRO
		Libro libro1 = new Libro("Le cronache del ghiaccio e del fuoco", 1999, 829, "George R.R. Martin", "Fantasy");
		operaLetterariaDao.salvaOpera(libro1);

		// CREO UNA RIVISTA
		Rivista rivista1 = new Rivista("Focus #123", 2017, 60, Periodicita.MENSILE);
		operaLetterariaDao.salvaOpera(rivista1);

		// CREO UNA LISTA DI OPERE LETTERARIE CHE COMPRENDE LIBRI E RIVISTE
		Set<OperaLetteraria> opereLetterarie = new HashSet<>();
		opereLetterarie.add(libro1);
		opereLetterarie.add(rivista1);

		// CREO UN PRESTITO PASSANDO UN UTENTE E UNA SERIE DI OPERE
		Prestito prestito1 = new Prestito(utente1, opereLetterarie, LocalDate.parse("2022-10-12"),
				LocalDate.parse("2022-12-10"));
		prestitoDao.salvaPrestito(prestito1);

		// RICHIAMO I VARI METODI A PARTIRE DAI DAO ISTANZIATI IN PRECEDENZA
		UUID isbn1 = UUID.fromString("4f0fd8f7-efe0-4f79-a7b0-125c0766b7a5");
		operaLetterariaDao.ottieniOperaDaIsbn(isbn1);
		operaLetterariaDao.ottieniOperaDaAnno(2000);
		operaLetterariaDao.ottieniOperaDaAutore("georg");
		operaLetterariaDao.ottieniOperaDaTitolo("fo");

		UUID isbn2 = UUID.fromString("3022e981-d1e5-41f5-9b70-b5fe44e98c16");
		operaLetterariaDao.ottieniOperaDaIsbnECancella(isbn2);

		UUID numeroTessera1 = UUID.fromString("64329e1f-9ab4-4196-8a2f-b826d318c3d9");
		prestitoDao.ottieniPrestitiDaUtente(numeroTessera1);

		entityManager.close();
		entityManagerFactory.close();

	}

}
