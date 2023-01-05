package isletimsistemleri;

public class FirstPriList {
	
//	private DispatchList dl;
//	
//	public FirstPriList(DispatchList dl)
//	{
//		this.dl=dl;
//	}
	
	
	SecondPriList spl = new SecondPriList();
	Kuyruk kuyruk = new Kuyruk();

	void FPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean FPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int FPL_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)
		DispatchList dl=Prosesler.dl;
		System.out.println("\nFirst Priority");
		int timer = 0;
		Item item = kuyruk.kuyruktanCikar();
		System.out.println((zaman + timer) + " sn proses basladi" + "(id:" + item.id + "  oncelik: " + item.oncelik
				+ "  kalan sure: " + item.burstTime + " sn)");
		item.burstTime--;
		timer++;
		//System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		if (item.burstTime > 0) 
		{
			//System.out.print("spl gonderildi => ");
			item.oncelik++;
			
			System.out.println((zaman + timer) + " sn proses askida" + "(id:" + item.id + "  oncelik: " + item.oncelik
					+ "  kalan sure: " + item.burstTime + " sn)");
			item.askiyaAlinma=zaman+timer;
			spl.SPL_add(item);
			
			//System.out.println(spl.kuyruk.kuyrukSize()); 
			//spl.SPL_execute();
		}
		else
		System.out.println((zaman + timer) + " sn proses sonlandi" + "(id:" + item.id + "  oncelik: " + item.oncelik
				+ "  kalan sure: " + item.burstTime + " sn)");
		dl.TimeOut_Scanner(zaman+timer);
		//System.out.println("--------------"+kuyruk.kuyrukSize()+"-----------");
		return timer;
	}
}
