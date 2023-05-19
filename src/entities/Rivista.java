package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import enums.Periodicita;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Rivista")
@Table(name = "riviste")
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends OperaLetteraria {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "ISBN: " + getIsbn() + ", Titolo: " + getTitolo() + ", Anno di Pubblicazione: " + getAnnoPubblicazione()
				+ ", Numero di Pagine = " + getNumeroPagine() + ", periodicità = " + periodicita + "(RIVISTA";
	}

}
