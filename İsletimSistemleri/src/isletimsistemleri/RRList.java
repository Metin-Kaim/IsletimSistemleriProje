package isletimsistemleri;

public class RRList {
	Kuyruk kuyruk = new Kuyruk();
	int sayac = 0;

	void RR_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean RR_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}
	Item cikanEleman=null;
	int RR_execute() {// maine gidecek olan timer(ekleme islemi olacak)
		System.out.println("Round Robin");
		System.out.println("RR Size: "+ kuyruk.kuyrukSize());
		int timer = 0;
		Item item = kuyruk.Getir(sayac);
		
		System.out.println("varis: " + item.varis + "  oncelik: " + item.oncelik + "  burst: " + item.burstTime);
		item.burstTime--;
		timer++;
		System.out.println("varis: " + item.varis + "  oncelik: " + item.oncelik + "  burst: " + item.burstTime);

		if (item.burstTime == 0) {
			
			kuyruk.kuyruktanCikar(sayac);
			//if(sayac==kuyruk.kuyrukSize()-1)
				if (sayac == kuyruk.kuyrukSize())
					sayac=0;
		}
		else
		{
			System.out.println("---------------------------------");
			System.out.println("sayac:" + sayac);
			
			sayac = (sayac + 1) % kuyruk.kuyrukSize();
		}
		
		
		
		
		
		
		System.out.println("sayac:" + sayac);
		//System.out.println("-------------------"+ kuyruk.kuyrukSize() +"--------------");
		return timer;
	}
}
