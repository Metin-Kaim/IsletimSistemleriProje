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

	Item cikanEleman = null;

	int RR_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)
		DispatchList dl=Prosesler.dl;
		System.out.println("\nRound Robin");
		//System.out.println("RR Size: " + kuyruk.kuyrukSize());
		int timer = 0;
		Item item = kuyruk.Getir(sayac);

		System.out.println((zaman + timer) + " sn proses basladi" + "(id:" + item.id + "  oncelik: " + item.oncelik
				+ "  kalan sure: " + item.burstTime + " sn)");
		item.burstTime--;
		timer++;
		//System.out.println("\nZaman "+ (zaman+timer)+" varis: " + item.varis + "  oncelik: " + item.oncelik + "  burst: " + item.burstTime);

		if (item.burstTime == 0) {
			System.out.println((zaman + timer) + " sn proses sonlandi" + "(id:" + item.id + "  oncelik: " + item.oncelik
					+ "  kalan sure: " + item.burstTime + " sn)");
			kuyruk.kuyruktanCikar(sayac);
			if (sayac == kuyruk.kuyrukSize())
				sayac = 0;
		} else {
			
			//System.out.println("---------------------------------");
			//System.out.println("sayac:" + sayac);
			System.out.println((zaman + timer) + " sn proses askida" + "(id:" + item.id + "  oncelik: " + item.oncelik
					+ "  kalan sure: " + item.burstTime + " sn)");
			item.askiyaAlinma=zaman+timer;
			dl.TimeOut_Scanner(zaman+timer);
			sayac = (sayac + 1) % kuyruk.kuyrukSize();
			
		}

		//System.out.println("sayac:" + sayac);
		// System.out.println("-------------------"+ kuyruk.kuyrukSize()
		// +"--------------");
		return timer;
	}
}
