package isletimsistemleri;

public class FCFSList {

	//private DispatchList dl=Prosesler.dl;
	
//	public FCFSList(DispatchList dl)
//	{
//		this.dl=dl;
//	}
	
	Kuyruk kuyruk = new Kuyruk();

	void FCFS_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean FCFS_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

	int FCFS_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)
		DispatchList dl=Prosesler.dl;
		System.out.println("\nFCFS");
		int timer = 0;
		// Item item = kuyruk.Getir(0);
//		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
//		item.burstTime--;
//		timer++;
//		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
//		
//		if(item.burstTime<=0)
//		{
//			kuyruk.kuyruktanCikar();
//		}
		// while (kuyruk.kuyrukSize() != 0) {
		Item item = kuyruk.kuyruktanCikar();
		System.out.println((zaman + timer) + " sn proses basladi" + "(id:" + item.id + "  oncelik: " + item.oncelik
				+ "  kalan sure: " + item.burstTime + " sn)");
		while (item.burstTime != 0) {
			item.burstTime--;
			timer++;
			if (item.burstTime > 0)
			{
				System.out.println((zaman + timer) + " sn proses yurutuluyor" + "(id:" + item.id + "  oncelik: " + item.oncelik
						+ "  kalan sure: " + item.burstTime + " sn)");
				dl.TimeOut_Scanner(zaman+timer);
			}
			else
				System.out.println((zaman + timer) + " sn proses sonlandi" + "(id:" + item.id + "  oncelik: " + item.oncelik
						+ "  kalan sure: " + item.burstTime + " sn)");
		}
		// }
		return timer;
	}
}
