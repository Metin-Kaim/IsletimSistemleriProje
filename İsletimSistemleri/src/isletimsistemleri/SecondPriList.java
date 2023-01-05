package isletimsistemleri;

public class SecondPriList {
	Kuyruk kuyruk = new Kuyruk();
	RRList rr=new RRList();

	void SPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean SPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int SPL_execute() {// maine gidecek olan timer(ekleme islemi olacak)
		System.out.println("Second Priority size:"+kuyruk.kuyrukSize());
		int timer = 0;
		Item item = kuyruk.kuyruktanCikar();
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		item.burstTime--;
		timer++;
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		if (item.burstTime > 0) {
			rr.RR_add(item);
		}
		//System.out.println("--------------"+kuyruk.kuyrukSize()+"-----------");
		return timer;
	}
}
