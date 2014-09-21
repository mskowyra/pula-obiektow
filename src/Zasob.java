/*
 *   Maciej Skowyra PULA OBIEKTÓW
 *
 */
public class Zasob extends Thread {

	int id;
	boolean zajety;	
	
	public Zasob(int id) {
		this.id = id;
		this.zajety = false;
	}
	
	public boolean czyWolny(){
		return !this.zajety;
	}
	
	public void zajmij(){
		this.zajety = true;
	}
	
	public void zwolnij(){
		this.zajety = false;
	}
	
	public int pobierzId(){
		return this.id;
	}
	
}
