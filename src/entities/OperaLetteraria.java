package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opera_letteraria")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "categoria", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "cercaPerAnno", query = "SELECT op FROM OperaLetteraria op WHERE op.annoPubblicazione = :anno")
@NamedQuery(name = "cercaPerTitolo", query = "SELECT op FROM ElementoBiblioteca op WHERE op.titolo LIKE :titolo")
public class OperaLetteraria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "isbn_seq")
	@SequenceGenerator(name = "isbn_seq", sequenceName = "isbn_seq")
	private long isbn;
	private String titolo;
	@Column(name = "anno_pubblicazione")
	private int annoPubblicazione;
	@Column(name = "numero_pagine")
	private int numeroPagine;

	public OperaLetteraria(String titolo, int annoPubblicazione, int numeroPagine) {
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

	@Override
	public String toString() {
		return "OPERA => ISBN: " + this.getIsbn() + ", Titolo: " + this.getTitolo() + ", Anno di Pubblicazione: "
				+ this.getAnnoPubblicazione() + ", Numero di Pagine: " + this.getNumeroPagine();
	}

}