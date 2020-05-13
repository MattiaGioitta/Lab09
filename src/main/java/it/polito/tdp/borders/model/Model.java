package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo;
	private Map<Integer,Country> idMap;
	private BordersDAO dao;
	private List<Country> visitati;

	public Model() {
		this.idMap = new HashMap<>();	
		this.dao = new BordersDAO();
		this.dao.loadAllCountries(idMap);
		
	}
	
	public void creaGrafo(int annoMinimo) {
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		List<Border> confini = dao.getCountryPairs(annoMinimo, idMap);		
		List<Country> paesi = this.getCountry(confini);
		Graphs.addAllVertices(this.grafo, paesi);//creo i vertici
		//creo gli archi
		
		for(Border b : confini) {
			DefaultEdge e = this.grafo.getEdge(b.getC1(), b.getC2());
			if(e == null) {
				Graphs.addEdgeWithVertices(this.grafo, b.getC1(), b.getC2());
			}
		}
		
	}

	private List<Country> getCountry(List<Border> confini) {
		List<Country> paesi = new ArrayList<>();
		for(Border b : confini) {
			if(!paesi.contains(b.getC1())) {
				paesi.add(b.getC1());
			}
			else if (!paesi.contains(b.getC2())) {
				paesi.add(b.getC2());
			}
		}
		return paesi;
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public List<Country> getCountries() {
		List<Country> paesi = new ArrayList<>();
		for(Country c : this.grafo.vertexSet()) {
			paesi.add(c);
		}
		return paesi;
	}
	public String statiConGrado() {
		String s = "";
		for(Country c : this.grafo.vertexSet()) {
			s+=c.getNome()+" "+this.grafo.degreeOf(c)+"\n";
		}
		return s;
	}

	public int getNumberOfConnectedComponents() {
		ConnectivityInspector<Country,DefaultEdge> ci = new ConnectivityInspector<>(this.grafo);		
		return ci.connectedSets().size();
	}
	
	public List<Country> trovaVicini(Country c){
		List<Country> vicini = new ArrayList<>();
		DepthFirstIterator<Country, DefaultEdge> dfi = new DepthFirstIterator<>(this.grafo,c);
		while(dfi.hasNext()) {
			vicini.add(dfi.next());
		}
		return vicini;
	}
	
	public List<Country> trovaViciniRicorsione(Country c){
		List<Country> parziale = new ArrayList<>();
		parziale.add(c);
		cerca(parziale);
		return parziale;
	}

	private void cerca(List<Country> parziale) {
        for(Country co : Graphs.neighborSetOf(grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(co)) {
			parziale.add(co);
			cerca(parziale);
			}
		}
		
	}

}
