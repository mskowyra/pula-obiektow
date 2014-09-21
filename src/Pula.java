/*
 *   Maciej Skowyra PULA OBIEKTÓW
 *
 */
public class Pula {

	private static Pula instance = null;
	private static int rozmiar = 0;
	private static Zasob[] pula;
	
	
	private Pula(int ilosc) {
		Pula.rozmiar = ilosc;
		
		Pula.pula = new Zasob[ilosc];
		
		for(int i=0; i<ilosc; i++){
			Pula.pula[i] = new Zasob(i);
		}
	}
	
	public static Pula getInstance(int ilosc) throws Exception{
		if(ilosc != Pula.rozmiar && Pula.rozmiar != 0){
			throw new Exception("W systemie istnieje juz obiekt puli o innym rozmiarze");
		}
		
		if(Pula.instance == null){
			Pula.instance = new Pula(ilosc);
		}

		return Pula.instance;
	}
	
	
	//pobieranie zasobów
	public Zasob pobierzZasob() throws Exception{
		Zasob w=null;
		for(int i=0; i<Pula.rozmiar; i++){
			if(Pula.pula[i].czyWolny()){
				w = Pula.pula[i];
				break;
			}
		}
		
		if(w == null){
			throw new Exception("Brak wolnych zasobow! ");
		}
		
		w.zajmij();		
		return w;
	}
	
	public Zasob pobierzZasobKolejka() throws InterruptedException {
		Zasob w = null;
		
		do{
			for(int i=0; i<Pula.rozmiar; i++){
				if(Pula.pula[i].czyWolny()){
					w = Pula.pula[i];
					w.zajmij();
					break;
				}
			}
		}while(w==null);
			
		return w;
	}
	
}
