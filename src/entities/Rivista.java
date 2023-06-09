package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "riviste")
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends OperaLetteraria {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita, Prestito prestito) {
		super(titolo, annoPubblicazione, numeroPagine, prestito);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "[RIVISTA] ISBN: " + getIsbn() + ", Titolo: " + getTitolo() + ", Anno di Pubblicazione: "
				+ getAnnoPubblicazione() + ", Numero di Pagine = " + getNumeroPagine() + ", periodicità = "
				+ periodicita;
	}

}
