import java.io.*; 
/*
 *   Maciej Skowyra PULA OBIEKTÓW
 *
 *   Wzorzec tworz¹cy limitowana pulê obiektów,
 *   zapewniaj¹cy dostêp do wolnego obiektu z puli 
 *   poprzez blokowanie obiektu z puli na czas u¿ycia go przez obiekt zewnêtrzny.
 *
 */

public class Main {

	public static void main(String[] args) throws IOException{
		int rozmiar = 10; //rozmiar zasobu
		
		BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in));
		int tryb;
		//menu
		System.out.println("Wybierz opcjê dzia³ania programu:");
		System.out.println("1 - Wyrzucanie wyjatku przy braku wolnego zasobu");
		System.out.println("2 - Kolejkowanie ¿¹dañ klientów");
		//sprawdza co zosta³o wybrane, jesli nie 1 albo 2 to b³¹d
		do{
			tryb = Integer.parseInt(klaw.readLine()); 
			if(tryb!=1 && tryb!=2){
				System.out.println("B³êdny wybór! Spróbuj jeszcze raz!");
			}
		}while(tryb!=1 && tryb!=2);
		
		Pula p;
		try{
			p = Pula.getInstance(rozmiar);
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
		
		if(tryb == 1){
			// wyjatek rzucany w przypadku braku wolnego zasobu
			Zasob tablicaZasobow[] = new Zasob[rozmiar+3];
			try{
				for(int i = 0 ; i<rozmiar+3; i++){
					System.out.println("[ZASOB " + i + "] Petla pobierajaca zasoby");
					tablicaZasobow[i] = p.pobierzZasob();
					System.out.println("[ZASOB " + i + "] Zasób pobrany poprawnie");
				}
			}
			catch(Exception e){
				System.out.println(e);
				return;
			}
		}
		else if(tryb == 2){
			// kolejkowanie
			Obiekt tablicaObiektow[] = new Obiekt[rozmiar+5];
			for(int i = 0; i<rozmiar+5; i++){
				tablicaObiektow[i] = new Obiekt(p, i);
				try{
					Thread.sleep(300);
				}
				catch(InterruptedException e){
					System.err.println(e);
					return;
				}
			}
		}
	}
}
