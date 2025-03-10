import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
  List<Tabac> tabacs;
  List<Llumi> llumins;
  List<Paper> papers;

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

  public void nouSubministrament() {
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
  }

  public Tabac venTabac() {
    if (tabacs.size() > 0) {
      Tabac tabac = tabacs.get(0);
      tabacs.remove(0);
      return tabac;
    }
    return null;
  }

  public Llumi venLlumi() {
    if (llumins.size() > 0) {
      Llumi llumi = llumins.get(0);
      llumins.remove(0);
      return llumi;
    }
    return null;
  }

  public Paper venPaper() {
    if (papers.size() > 0) {
      Paper paper = papers.get(0);
      papers.remove(0);
      return paper;
    }
    return null;
  }

  public void tancarEstanc() {
    System.out.println("Estanc tancat");
    System.exit(0);
  }

  @Override
  public void run() {
    while(true) {
      nouSubministrament();
      try {
        Thread.sleep(500 + new Random().nextInt(501));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  

}