package isletimsistemleri;

public class SecondPriList {

//	public SecondPriList(DispatchList dl) {
//		this.dl = dl;
//	}

	Kuyruk kuyruk = new Kuyruk();
	RRList rr = new RRList();

	void SPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean SPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int SPL_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)
		DispatchList dl=Prosesler.dl;
		System.out.println("\nSecond Priority");
		int timer = 0;
		Item item = kuyruk.kuyruktanCikar();
		System.out.println((zaman + timer) + " sn proses basladi" + "(id:" + item.id + "  oncelik: " + item.oncelik
				+ "  kalan sure: " + item.burstTime + " sn)");
		item.burstTime--;
		timer++;
		if (item.burstTime > 0) {
			item.oncelik++;
			rr.RR_add(item);
			System.out.println((zaman + timer) + " sn proses askida" + "(id:" + item.id + "  oncelik: " + item.oncelik
					+ "  kalan sure: " + item.burstTime + " sn)");
			item.askiyaAlinma=zaman+timer;
			
		} else
			System.out.println((zaman + timer) + " sn proses sonlandi" + "(id:" + item.id + "  oncelik: " + item.oncelik
					+ "  kalan sure: " + item.burstTime + " sn)");
		dl.TimeOut_Scanner((zaman+timer));
		// System.out.println("--------------"+kuyruk.kuyrukSize()+"-----------");
		return timer;
	}
}
