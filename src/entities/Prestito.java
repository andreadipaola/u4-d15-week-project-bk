package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestiti")
@Getter
@NoArgsConstructor
@NamedQuery(name = "cercaPrestitiPerUtente", query = "SELECT pre FROM Prestito pre WHERE pre.utente = :tessera AND pre.dataRestituzioneEffettiva IS NULL")
@NamedQuery(name = "cercaPrestitiScaduti", query = "SELECT pre FROM Prestito pre WHERE pre.restituzioneEffettiva IS NULL AND pre.restituzionePrevista < CURRENT_DATE")
public class Prestito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "utente")
	private long utente;

	@Column(name = "elemento_prestato")
	private OperaLetteraria elementoPrestato;

	@Column(name = "data_inizio_prestito")
	private LocalDate dataInizioPrestito;

	@Column(name = "data_restituzione_prevista")
	private LocalDate dataRestituzionePrevista;

	@Column(name = "data_restituzione_effettiva")
	private LocalDate dataRestituzioneEffettiva;

	public LocalDate setRestituzionePrevista(LocalDate restituzionePrevista) {
		return this.dataRestituzionePrevista = this.dataInizioPrestito.plusDays(30);
	}

	public Prestito(long utente, OperaLetteraria elementoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
		super();
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataRestituzionePrevista;
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	@Override
	public String toString() {
		return "PRESTITO => Id: " + id + ", Tessera utente: " + utente + ", Elemnto prelevato: " + elementoPrestato
				+ ", Data prelievo:" + dataInizioPrestito + ", Data restituzione privista: " + dataRestituzionePrevista
				+ ", Data consegna effettiva: " + dataRestituzioneEffettiva;
	}

}
