import java.io.*; 
/*
 *   Maciej Skowyra PULA OBIEKT�W
 *
 *   Wzorzec tworz�cy limitowana pul� obiekt�w,
 *   zapewniaj�cy dost�p do wolnego obiektu z puli 
 *   poprzez blokowanie obiektu z puli na czas u�ycia go przez obiekt zewn�trzny.
 *
 */

public class Main {

	public static void main(String[] args) throws IOException{
		int rozmiar = 10; //rozmiar zasobu
		
		BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in));
		int tryb;
		//menu
		System.out.println("Wybierz opcj� dzia�ania programu:");
		System.out.println("1 - Wyrzucanie wyjatku przy braku wolnego zasobu");
		System.out.println("2 - Kolejkowanie ��da� klient�w");
		//sprawdza co zosta�o wybrane, jesli nie 1 albo 2 to b��d
		do{
			tryb = Integer.parseInt(klaw.readLine()); 
			if(tryb!=1 && tryb!=2){
				System.out.println("B��dny wyb�r! Spr�buj jeszcze raz!");
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
					System.out.println("[ZASOB " + i + "] Zas�b pobrany poprawnie");
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
