package entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opere_letterarie")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "categoria", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "cercaPerAnno", query = "SELECT opera FROM OperaLetteraria opera WHERE opera.annoPubblicazione = :anno")
@NamedQuery(name = "cercaPerTitolo", query = "SELECT opera FROM OperaLetteraria opera WHERE LOWER(opera.titolo) LIKE :titolo")
@NamedQuery(name = "cercaPerAutore", query = "SELECT opera FROM OperaLetteraria opera WHERE LOWER(opera.autore) LIKE :autore")
public abstract class OperaLetteraria {

	@Id
	@GeneratedValue
	private UUID isbn;
	private String titolo;
	@Column(name = "anno_pubblicazione")
	private int annoPubblicazione;
	@Column(name = "numero_pagine")
	private int numeroPagine;

	@ManyToOne
	@JoinColumn(name = "id_prestito")
	private Prestito prestito;

	public OperaLetteraria(String titolo, int annoPubblicazione, int numeroPagine) {
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

//	@Override
//	public String toString() {
//		return "OPERA: ISBN: " + this.getIsbn() + ", Titolo: " + this.getTitolo() + ", Anno di Pubblicazione: "
//				+ this.getAnnoPubblicazione() + ", Numero di Pagine: " + this.getNumeroPagine();
//	}

}