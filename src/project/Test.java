package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {
	public static Scanner scanner = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	public static boolean isKategorija(String kat) {
		//String kamion = null;
		//String automobil = null;
		//String kombi = null;
		//String sleper = null;
		try {
			if (kat.equalsIgnoreCase("kamion") || kat.equalsIgnoreCase("automobil") || kat.equalsIgnoreCase("kombi")
					|| kat.equalsIgnoreCase("sleper")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;

	}

	public static boolean isNumber(String id) {

		try {
			int broj = Integer.parseInt(id);
			if (broj < 1) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isDouble(String cenaRacuna) {
		try {
			double broj = Double.parseDouble(cenaRacuna);
			if (broj > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDate(String datum) {

		try {
			LocalDate.parse(datum, dtf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDateVeci(LocalDate datumPocetni, String datum) {

		LocalDate datumKrajnji = null;
		try {
			datumKrajnji = LocalDate.parse(datum, dtf);
			if (datumKrajnji.compareTo(datumPocetni) >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDuzinaParcele(String parcela) {

		if (parcela.length() == 4) {
			return true;
		}
		return false;

	}

	public static void unosAgencije(Agencija agencijaaaa) { // 1

		System.out.print("Naziv agencije:");
		String naziv = scanner.nextLine();
		agencijaaaa.setNaziv(naziv);
		System.out.print("Web adresa agencije:");
		String webAdresa = scanner.nextLine();
		agencijaaaa.setWebAdresa(webAdresa);
		System.out.print("Telefon agencije:");
		String telefon = scanner.nextLine();
		agencijaaaa.setTelefon(telefon);

		System.out.println("Podaci o agenciji su uspesno uneti.");

	}

	public static void unosPrevoza(Agencija agencijaaaa) { // 2

		int id = 0;
		String idString;
		double cena = 0.0;
		String cenaString;
		LocalDate datumPocetka = null;
		String datumPocetkaS;
		LocalDate datumKraja = null;
		String datumKrajaS;
		String kategorijaVozila;
		String imeNarucioca;
		String prezimeNarucioca;
		do {
			System.out.print("Identifikacioni broj: ");
			idString = scanner.nextLine();
		} while (!isNumber(idString));
		id = Integer.parseInt(idString);
		do {
			System.out.print("Cena prevoza: ");
			cenaString = scanner.nextLine();
		} while (!isNumber(cenaString));
		cena = Integer.parseInt(cenaString);
		do {
			System.out.print("Datum pocetka: ");
			datumPocetkaS = scanner.nextLine();
		} while (!isDate(datumPocetkaS));
		datumPocetka = LocalDate.parse(datumPocetkaS, dtf);
		do {
			System.out.print("Datum kraja: ");
			datumKrajaS = scanner.nextLine();
		} while (!isDate(datumKrajaS));
		datumKraja = LocalDate.parse(datumKrajaS, dtf);
		do {
			System.out.print("Kategorija vozila: ");
			kategorijaVozila = scanner.nextLine();
		} while (!isKategorija(kategorijaVozila));
		System.out.print("Ime narucioca: ");
		imeNarucioca = scanner.nextLine();
		System.out.print("Prezime narucioca: ");
		prezimeNarucioca = scanner.nextLine();
		Prevoz prevoz = new Prevoz(id, cena, datumPocetka, datumKraja, kategorijaVozila, imeNarucioca, prezimeNarucioca);
		boolean provera = agencijaaaa.unosPrevoza(prevoz);
		if (provera) {
			System.out.println("Prevoz je uspesno dodata.");
		} else {
			System.out.println("Prevoz nije uspesno dodata.");
		}
		agencijaaaa.unosPrevoza(prevoz);

	}

	public static void izmenaPrevoza(Agencija agencijaaaa) { // 4
		int id = 0;
		String idString;
		double cena = 0.0;
		String cenaString;
		LocalDate datumPocetka = null;
		String datumPocetkaS;
		LocalDate datumKraja = null;
		String datumKrajaS;
		String kategorijaVozila;
		String imeNarucioca;
		String prezimeNarucioca;
		do {
			System.out.print("Identifikacioni broj: ");
			idString = scanner.nextLine();
		} while (!isNumber(idString));
		id = Integer.parseInt(idString);
		do {
			System.out.print("Cena prevoza: ");
			cenaString = scanner.nextLine();
		} while (!isNumber(cenaString));
		cena = Integer.parseInt(cenaString);
		do {
			System.out.print("Datum pocetka: ");
			datumPocetkaS = scanner.nextLine();
		} while (!isDate(datumPocetkaS));
		datumPocetka = LocalDate.parse(datumPocetkaS, dtf);
		do {
			System.out.print("Datum kraja: ");
			datumKrajaS = scanner.nextLine();
		} while (!isDate(datumKrajaS));
		datumKraja = LocalDate.parse(datumKrajaS, dtf);
		do {
			System.out.print("Kategorija vozila: ");
			kategorijaVozila = scanner.nextLine();
		} while (!isKategorija(kategorijaVozila));
		System.out.print("Ime narucioca: ");
		imeNarucioca = scanner.nextLine();
		System.out.print("Prezime narucioca: ");
		prezimeNarucioca = scanner.nextLine();
		Prevoz prevoz = new Prevoz(id, cena, datumPocetka, datumKraja, kategorijaVozila, imeNarucioca,
				prezimeNarucioca);

		agencijaaaa.izmenaPrevoza(prevoz);
	}

	public static void brisanjePrevoza(Agencija agencijaaaa) { // 5
		String idString;
		int id;
		do {
			System.out.println("Identifikacioni broj: ");
			idString = scanner.nextLine();
		} while (!isNumber(idString));

		id = Integer.parseInt(idString);
		agencijaaaa.brisanjePrevoza(id);
	}

	public static void pretPrevVozilo(Agencija agencijaaaa) { // 6

		String kategorijaVozila;
		do {
			System.out.print("Kategorija vozila: ");
			kategorijaVozila = scanner.nextLine();
		} while (!isKategorija(kategorijaVozila));
		agencijaaaa.pretPrevVozilo(kategorijaVozila);
	}

	public static void pretPrevozMinMax(Agencija agencijaaaa) { // 7
		double minCena = 0;
		String minCenaString;
		double maxCena = 0;
		String maxCenaString;
		do {
			System.out.println("Minimalna cena prevoza:");
			minCenaString = scanner.nextLine();
		} while (!isDouble(minCenaString));
		minCena = Double.parseDouble(minCenaString);
		do {
			System.out.println("Minimalna cena prevoza:");
			maxCenaString = scanner.nextLine();
		} while (!isDouble(maxCenaString));
		maxCena = Double.parseDouble(maxCenaString);
		agencijaaaa.pretPrevozMinMax(minCena, maxCena);

	}

	public static void prosCenaPeriod(Agencija agencijaaaa) { // 8
		LocalDate pocetak = null;
		String pocetakString;
		LocalDate kraj = null;
		String krajString;
		do {
			System.out.println("Unesite datum pocetka: ");
			pocetakString = scanner.nextLine();
		} while (!isDate(pocetakString));
		pocetak = LocalDate.parse(pocetakString, dtf);

		do {
			System.out.println("Unesite datum kraja: ");
			krajString = scanner.nextLine();
		} while (!isDate(krajString));
		kraj = LocalDate.parse(krajString, dtf);
		agencijaaaa.prosCenaPeriod(pocetak, kraj);
	}

	public static void najskupljiPrevoz(Agencija agencijaaaa) { // 9
		agencijaaaa.pretraga9();
	}

	public static void main(String[] args) {
		Agencija agencijaaaa = new Agencija();
		agencijaaaa.load("prevozi.txt");
		String answer = null;

		do {

			System.out.println("Meni:");
			System.out.println("1. Unos podataka o agenciji");
			System.out.println("2. Unos podataka o novom prevozu");
			System.out.println("3. Ispis svih prevoza");
			System.out.println("4. Izmena prevoza");
			System.out.println("5. Brisanje prevoza");
			System.out.println("6. Pretraga prevoza za navedeno vozilo");
			System.out.println("7. Pretraga prevoza u zadatom opsegu min i max cena ");
			// da li ime ili prezime narucioca sadrzi zadati string
			System.out.println("8. Prikaz prosecne cene prevoza u zadatom vremenskom opsegu");
			System.out.println("9. Prikaz najskupljeg prevoza koji je jos u toku");
			System.out.println("10. Ispis podataka o agenciji");
			// ukupan broj prevoza, ukupna cena prevoza za svaku kategoriju
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unosAgencije(agencijaaaa);
				agencijaaaa.save("prevozi.txt");
				break;
			case "2":
				unosPrevoza(agencijaaaa);
				agencijaaaa.save("prevozi.txt");
				break;
			case "3":
				agencijaaaa.ispisPrevoza();
				break;
			case "4":
				izmenaPrevoza(agencijaaaa);
				agencijaaaa.save("prevozi.txt");
				break;
			case "5":
				brisanjePrevoza(agencijaaaa);
				agencijaaaa.save("prevozi.txt");
				break;
			case "6":
				pretPrevVozilo(agencijaaaa);
				break;
			case "7":
				pretPrevozMinMax(agencijaaaa);
				break;
			case "8":
				prosCenaPeriod(agencijaaaa);
				break;
			case "9":
				najskupljiPrevoz(agencijaaaa);
				break;
			case "10":
				System.out.println(agencijaaaa);
				break;
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));

		scanner.close();

	}

}
