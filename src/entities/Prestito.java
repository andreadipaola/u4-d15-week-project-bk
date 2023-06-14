package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestiti")
@Getter
@NoArgsConstructor
@NamedQuery(name = "cercaPrestitiDaUtente", query = "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL")
//@NamedQuery(name = "cercaPrestitiDaUtente", query = "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :utente")
@NamedQuery(name = "cercaPrestitiScaduti", query = "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE")
public class Prestito {

	@Id
	@GeneratedValue
	@Column(name = "id_prestito")
	private UUID idPrestito;

	@Column(name = "data_inizio_prestito")
	private LocalDate dataInizioPrestito;

	@Column(name = "data_restituzione_prevista")
	private LocalDate dataRestituzionePrevista;

	@Column(name = "data_restituzione_effettiva")
	private LocalDate dataRestituzioneEffettiva;

	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

//	@OneToMany(mappedBy = "prestito", cascade = CascadeType.ALL)/
	@OneToMany(mappedBy = "prestito")
	private List<OperaLetteraria> opereLetterarie;

	public Prestito(LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettiva, Utente utente,
			List<OperaLetteraria> opereLetterarie) {
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
		this.utente = utente;
		this.opereLetterarie = opereLetterarie;
	}

	@Override
	public String toString() {
		return "\n[PRESTITO] Id: " + idPrestito + ", Tessera utente: " + utente.getNumeroTessera() + ", Data prelievo: "
				+ dataInizioPrestito + ", Data restituzione privista: " + dataRestituzionePrevista
				+ ", Data consegna effettiva: " + dataRestituzioneEffettiva + ".\nOpere prese in prestito:\n"
				+ opereLetterarie;
	}

}
