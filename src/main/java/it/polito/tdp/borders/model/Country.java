package it.polito.tdp.borders.model;

public class Country {
	
	private int id;
	private String nomeAbb;
	private String nome;
	
	
	/**
	 * @param id
	 * @param nomeAbb
	 * @param nome
	 */
	public Country(int id, String nomeAbb, String nome) {
		super();
		this.id = id;
		this.nomeAbb = nomeAbb;
		this.nome = nome;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nomeAbb
	 */
	public String getNomeAbb() {
		return nomeAbb;
	}
	/**
	 * @param nomeAbb the nomeAbb to set
	 */
	public void setNomeAbb(String nomeAbb) {
		this.nomeAbb = nomeAbb;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nome;
	}
	
	

}
