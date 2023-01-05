package isletimsistemleri;

public class FirstPriList {
	SecondPriList spl = new SecondPriList();
	Kuyruk kuyruk = new Kuyruk();

	void FPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean FPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int FPL_execute() {// maine gidecek olan timer(ekleme islemi olacak)
		System.out.println("First Priority");
		int timer = 0;
		Item item = kuyruk.kuyruktanCikar();
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		item.burstTime--;
		timer++;
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		if (item.burstTime > 0) 
		{
			System.out.print("spl gonderildi => ");
			item.oncelik++;
			System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
			spl.SPL_add(item);
			System.out.println(spl.kuyruk.kuyrukSize()); 
			//spl.SPL_execute();
		}
		//System.out.println("--------------"+kuyruk.kuyrukSize()+"-----------");
		return timer;
	}
}
