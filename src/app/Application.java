package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

		// CREO DEGLI UTENTI CHE NON SONO ANCORA STATI ASSOCIATI AD ALCUN PRESTITO
		List<Prestito> prestiti = new ArrayList<>();
		Utente utente1 = new Utente("Mario", "Rossi", LocalDate.parse("1977-07-07"), prestiti);
		utenteDao.salvaUtente(utente1);
		Utente utente2 = new Utente("Luigi", "Verdi", LocalDate.parse("1988-08-08"), prestiti);
		utenteDao.salvaUtente(utente2);
		Utente utente3 = new Utente("Anna", "Neri", LocalDate.parse("1999-09-09"), prestiti);
		utenteDao.salvaUtente(utente3);

		// CREO DEI LIBRI CHE NON SONO ANCORA STATI ASSOCIATI AD ALCUN PRESTITO
		Libro libro1 = new Libro("Le cronache del ghiaccio e del fuoco", 1999, 829, "George R.R. Martin", "Fantasy",
				null);
		operaLetterariaDao.salvaOpera(libro1);
		Libro libro2 = new Libro("Harry Potter e la Camera dei Segreti", 2015, 366, "J.K. Rowling", "Fantasy", null);
		operaLetterariaDao.salvaOpera(libro2);
		Libro libro3 = new Libro("Hunger games", 2009, 370, "Suzanne Collins", "Fantasy", null);
		operaLetterariaDao.salvaOpera(libro3);

		// CREO DELLE RIVISTE CHE NON SONO ANCORA STATE ASSOCIATE AD ALCUN PRESTITO
		Rivista rivista1 = new Rivista("Focus #123", 2017, 60, Periodicita.MENSILE, null);
		operaLetterariaDao.salvaOpera(rivista1);
		Rivista rivista2 = new Rivista("Forbes #215", 2020, 45, Periodicita.MENSILE, null);
		operaLetterariaDao.salvaOpera(rivista2);
		Rivista rivista3 = new Rivista("Vogue #111", 2022, 30, Periodicita.SETTIMANALE, null);
		operaLetterariaDao.salvaOpera(rivista3);

		// ASSOCIO AD UNA LISTA DI OPERE LETTERARIE DEI LIBRI E DELLE RIVISTE
		List<OperaLetteraria> opereLetterarie = new ArrayList<>();
		opereLetterarie.add(libro1);
		opereLetterarie.add(rivista1);
		opereLetterarie.add(rivista2);
		opereLetterarie.add(libro2);
		opereLetterarie.add(libro3);

		// CREO UN PRESTITO CHE E'STATO RESTITUITO PER CUI SE SI UTILIZZASSE
		// L'ID DI QUESTO PRESTITO NEL METODO 6 NON TORNA NULLA
		Prestito prestito1 = new Prestito(LocalDate.parse("2022-10-12"), LocalDate.parse("2022-12-10"), utente1,
				opereLetterarie);
		prestitoDao.salvaPrestito(prestito1);
		for (OperaLetteraria opera : opereLetterarie) {
			opera.setPrestito(prestito1);
			operaLetterariaDao.salvaOpera(opera);
		}
		// CREO UN PRESTITO CHE E'STATO RESTITUITO PER CUI SE SI UTILIZZASSE
		// L'ID DI QUESTO PRESTITO NEL METODO 6 NON TORNA NULLA
		Prestito prestito2 = new Prestito(LocalDate.parse("2022-01-01"), LocalDate.parse("2022-04-10"), utente2,
				opereLetterarie);
		for (OperaLetteraria opera : opereLetterarie) {
			opera.setPrestito(prestito2);
			operaLetterariaDao.salvaOpera(opera);
		}

		// CREO UN PRESTITO CHE NON E'STATO ANCORA RESTITUITO PER CUI SE SI UTILIZZA
		// L'ID DI QUESTO PRESTITO NEL METODO 6 DEVE TORNARE I DATI DEL PRESTITO PIU' LE
		// OPERE PRESE IN PRESTITO
		Prestito prestito3 = new Prestito(LocalDate.parse("2022-05-12"), null, utente1, opereLetterarie);
		prestitoDao.salvaPrestito(prestito3);
		for (OperaLetteraria opera : opereLetterarie) {
			opera.setPrestito(prestito3);
			operaLetterariaDao.salvaOpera(opera);
		}

		// 1.RICERCA ELEMENTO PER ISBN - FUNZIONANTE
		UUID isbn1 = UUID.fromString("00ad3697-3d27-4084-85f4-b231c665de6a");
		operaLetterariaDao.ottieniOperaDaIsbn(isbn1);

		// 2.RICERCA ELEMENTO PER ANNO DI PUBBLICAZIONE - FUNZIONANTE
		operaLetterariaDao.ottieniOperaDaAnno(2017);

		// 3.RICERCA ELEMENTO PER AUTORE O PARTE DI ESSO - FUNZIONANTE
		operaLetterariaDao.ottieniOperaDaAutore("georg");

		// 4.RICERCA ELEMENTO PER TITOLO O PARTE DI ESSO - FUNZIONANTE
		operaLetterariaDao.ottieniOperaDaTitolo("fo");

		// 5.RICERCA ELEMENTO PER ISBN CON SUCCESSIVA ELIMINAZIONE - FUNZIONANTE
		UUID isbn2 = UUID.fromString("7792352d-7b8b-4b01-9073-569ea5319a93");
		operaLetterariaDao.ottieniOperaDaIsbnECancella(isbn2);

		// 6.DATA UNA TESSERA UTENTE OTTIENI PRESTITI IN CORSO - FUNZIONANTE (OLTRE AL
		// PRESTITO MOSTRA ANCHE LE OPERE PRESE IN PRESTITO)
		UUID numeroTessera1 = UUID.fromString("0245a4f5-74bb-43f5-91a4-c5f3f9209bc5");
		prestitoDao.ottieniPrestitiDaUtente(numeroTessera1);

		entityManager.close();
		entityManagerFactory.close();

	}

}
