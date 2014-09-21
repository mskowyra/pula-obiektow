/*
 *   Maciej Skowyra PULA OBIEKTÓW
 *
 */
public class Obiekt extends Thread  {
//klasa uzywajaca zasobow z puli
	
	private boolean zmienna;
	private Zasob w;
	private Pula p;
	private int id;
	
	public Obiekt(Pula p, int i){
		this.p = p;
		this.id = i;
		this.start();
	}
	
	public void run(){
		try{
			do{
				this.w = this.p.pobierzZasobKolejka();
				if(this.w == null){
					System.out.println("[WATEK " + this.id + "] Brak wolnych zasobow, oczekuje...");
				}
			}while(this.w == null);
		}
		catch(InterruptedException e){
			System.out.println("[WATEK " + this.id + "] Przechwycono wyjatek typu InterruptedException");
			return;
		}
		//wykonywanie obliczen dla watku
		System.out.println("[WATEK " + this.id + "] Wykonywanie obliczen");

		for(int i = 0 ; i<10000; i++){
			for(int j = 0; j<10000; j++){
				if(this.zmienna){
					this.zmienna = false;
				}
				else{
					this.zmienna = true;
				}
			}
		}

		System.out.println("[WATEK " + this.id + "] Zakonczono wykonywanie obliczen, zwalnianie zasobow...");

		this.w.zwolnij();
	}
	
}
