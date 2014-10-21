import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Pendu {
	char[] alphabet="abcdefghijklmnopqrstuvwxyz".toCharArray();
	String[] mots;
	Random alea=new Random(0);

	public Pendu(String ficMots) throws IOException   {
		FileReader fr= new FileReader(ficMots);
		BufferedReader br= new BufferedReader(fr);
		String mot;
		List<String> lm=new ArrayList<String>();
		while((mot=br.readLine())!=null) {
			if (mot.length()>7) {
				lm.add(mot);
			}
		}
		br.close();
		mots=new String[lm.size()];
		mots=lm.toArray(mots);    	
	}

	private boolean contient(char[]tab,char c) {
		for (char elem:tab) {
			if (c==elem) {
				return true;
			}
		}
		return false;
	}

	private char[] choisit() {
		return mots[alea.nextInt(mots.length)].toCharArray();
	}

	public boolean isCaracteresAlphaString (char []mot){
		for (char c : mot) {
			if (contient(alphabet,c)) {
				return true;
			}
		}
		return false;
	}

	public void jouePendu() {
		char[] mot = choisit();
		char[] masque=new char[mot.length];
		Arrays.fill(masque,'_');
		int essais = 8;
		boolean mauvaisEssai;

		Scanner scan=new Scanner(System.in);

		while (essais>0) {
			System.out.println(new String(masque)+" -- "+essais+ "essay(s) resttant(s)" );
			char c = '0';
			while (!contient(alphabet,c)) {
				System.out.println("Letre (a-z ou Q pour kitter) ? ");
				c=scan.next().charAt(0);
				if (c=='Q') {
					System.out.println("Fin de paaartye");
					scan.close();
					System.exit(0);
				}
			}
			mauvaisEssai = true;
			for (int i=0;i<mot.length;i++) {
				if ((masque[i]=='_') && (mot[i]==c)) {
					masque[i] = mot[i];
					mauvaisEssai = false;
				}
			}
			if (mauvaisEssai){
				essais -= 1;
			}
			if (!contient(masque,'_')){
				System.out.println( new String(mot)+ " -- Bravo !\n");
				return;
			}
		}

		System.out.println(new String( mot)+ " -- Predu !\n");
	}



	public static void main(String[] args){
		try {
			Pendu p=new Pendu("/usr/share/dict/words");
			while (true) {
				p.jouePendu();
			}
		} catch (IOException ioe) {
			System.out.println("Le fichier dictionnaire n'est pas accessible...");
		}
	}
}
