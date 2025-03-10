import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
  List<Tabac> tabacs;
  List<Llumi> llumins;
  List<Paper> papers;
  public boolean tancat = false;

  public Estanc() {
    tabacs = new ArrayList<>();
    llumins = new ArrayList<>();
    papers = new ArrayList<>();
  }

  private void addTabac(Tabac tabac) {
    tabacs.add(tabac);
  }

  private void addLlumi(Llumi llumi) {
    llumins.add(llumi);
  }
  
  private void addPaper(Paper paper) {
    papers.add(paper);
  }

  public synchronized void nouSubministrament() {
    Random random = new Random();
    int choice = random.nextInt(3);

    switch (choice) {
      case 0:
        addTabac(new Tabac());
        System.out.println("Afegint Tabac");
        break;
      case 1:
        addLlumi(new Llumi());
        System.out.println("Afegint Llumí");
        break;
      case 2:
        addPaper(new Paper());
        System.out.println("Afegint Paper");
        break;
    }

    notifyAll(); // Notifiquem que s'han afegit nous productes
  }

  public synchronized Tabac venTabac() throws InterruptedException {
    while(tabacs.isEmpty()) {
      wait();
    }
    return tabacs.remove(0);
  }

  public synchronized Llumi venLlumi() throws InterruptedException {
    while(llumins.isEmpty()) {
      wait();
    }
    return llumins.remove(0);
  }

  public synchronized Paper venPaper() throws InterruptedException {
    while(papers.isEmpty()) {
      wait();
    }
    return papers.remove(0);
  }

  public void tancarEstanc() {
    tancat = true;
    notifyAll();
    this.interrupt();
    System.out.println("Estanc tancat");
  }

  @Override
  public void run() {
    while(!tancat) {
      nouSubministrament();
      try {
        Thread.sleep(500 + new Random().nextInt(501));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  

}