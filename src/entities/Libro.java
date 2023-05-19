package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@DiscriminatorValue("Libro")
@Table(name = "libri")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "cercaPerAutore", query = "SELECT lib FROM Libro lib WHERE lib.autore = :autore")
public class Libro extends OperaLetteraria {
	private String autore;
	private String genere;

	public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "LIBRO => ISBN: " + getIsbn() + ", Titolo: " + getTitolo() + ", autore: " + autore + ", genere: "
				+ genere + ", Anno di Pubblicazione: " + getAnnoPubblicazione() + ", Numero di Pagine: "
				+ getNumeroPagine();
	}

}
