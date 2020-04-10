package it.polito.tdp.indovina.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
	}
	
	public void nuovaPartita() {
	   	//gestione partita nuova
    	this.segreto =(int) (Math.random()*NMAX)+1;
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	this.tentativi=new HashSet<Integer>();
	}
	
	
	
	
	public int tentativo(int tentativo) {
		//controllo se partita in corso 
		if(!inGioco) {
			throw new IllegalStateException("partita terminata\n");
		}
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("devi inserire tra 1 e "+NMAX+"\n");
		}
		//tentativo valido
		this.tentativiFatti ++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		if(tentativo<this.segreto) {
			return -1;
		}else {
			return 1;
		}
		
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX)
			return false;
		else {
			if(tentativi.contains(tentativo)) {
				return false;
			}else {
			return true;
			}
		}
			
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
	
	
	
}
