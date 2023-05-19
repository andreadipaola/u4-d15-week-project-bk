package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
	@Id
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroTessera;
	private String nome;
	private String cognome;
	@Column(name = "data_di_nascita")
	private LocalDate dataNascita;

	public Utente(String nome, String cognome, LocalDate dataNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	@Override
	public String toString() {
		return "UTENTE => Tessera numero: " + numeroTessera + ", Nome: " + nome + ", Cognome: " + cognome
				+ ", Data di nascita: " + dataNascita;
	}

}
