package it.polito.tdp.borders.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
	    System.out.println("Creo il grafo relativo al 2000");
//		model.createGraph(2000);
	    model.creaGrafo(2006);
	    /*System.out.format("N.archi: %d e N.vertici: %d", model.nArchi(),model.nVertici());
		List<Country> countries = model.getCountries();
        System.out.format("\nTrovate %d nazioni\n", countries.size());
        System.out.println(model.statiConGrado());

    	System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));	*/
    	 
    	List<Country> vicini = model.trovaViciniRicorsione(model.getCountries().get(2));
    	//System.out.println(model.getCountries().get(2).getNome());
    	if(vicini.size() == 0)
    		System.out.println("vuoto");
    	for(Country c : vicini)
    		System.out.println(c.getNome());
    	System.out.println(vicini.size());
    	System.out.println("depth first iterator ---------------------------");
    	List<Country> vicini2 = model.trovaVicini(model.getCountries().get(2));
    	//System.out.println(model.getCountries().get(2).getNome());
    	if(vicini2.size() == 0)
    		System.out.println("vuoto");
    	for(Country c : vicini2)
    		System.out.println(c.getNome());
		System.out.println(vicini2.size());
	}

}
