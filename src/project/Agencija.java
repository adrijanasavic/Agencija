package project;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Agencija {
	

	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Prevoz> lista;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");


	public Agencija() {
		super();
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.lista = new ArrayList<>();
	}

	public Agencija(String naziv, String webAdresa, String telefon, ArrayList<Prevoz> lista) {
		super();
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.lista = new ArrayList<>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWebAdresa() {
		return webAdresa;
	}

	public void setWebAdresa(String webAdresa) {
		this.webAdresa = webAdresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public ArrayList<Prevoz> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Prevoz> lista) {
		this.lista = lista;
	}

	public boolean unosPrevoza(Prevoz prevoz) { // 2 unos racuna
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getId() == prevoz.getId()) {
				return false;
			}
		}
		this.lista.add(prevoz);
		return true;
	}

	public void ispisPrevoza() { // 3 ispis prevoza

		System.out.printf("%15s %10s %25s %25s %20s %20s %20s \n", "Id", "Cena", "Datum pocetka", "Datum kraja",
				"Kategorija vozila", "Ime narucioca", "Prezime narucioca");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < this.lista.size(); i++) {
			System.out.println(this.lista.get(i));
		}
	}

	public void izmenaPrevoza(Prevoz prevoz) { // 4 izmena prevoza
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getId() == prevoz.getId()) {
				this.lista.set(i, prevoz);
				System.out.println("Prevoz je uspesno izmenjena.");
				return;
			}
		}
		System.out.println("Prevoz nije pronadjena.");
	}

	public void brisanjePrevoza(int id) { // 5 brisanje prevoza
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getId() == id) {
				this.lista.remove(i);
				System.out.println("Prevoz je uspesno obrisana.");
				return;
			}
		}
		System.out.println("Prevoz nije uspesno obrisana.");

	}

	public void pretPrevVozilo(String vozilo) { // 6 pretraga po vozilu
		System.out.printf("%15s %10s %25s %25s %20s %20s %20s \n", "Id", "Cena", "Datum pocetka", "Datum kraja",
				"Kategorija vozila", "Ime narucioca", "Prezime narucioca");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getKategorijaVozila().equalsIgnoreCase(vozilo)) {
				System.out.println(this.lista.get(i));
				
			}
		}
		System.out.println("Prevoz nije upronadjen.");

	}

	// Pretraga prevoza cija cena je u zadatom opsegu cene
	public void pretPrevozMinMax(Double minCena, Double maxCena) { // 7
		System.out.printf("%15s %10s %25s %25s %20s %20s %20s \n", "Id", "Cena", "Datum pocetka", "Datum kraja",
				"Kategorija vozila", "Ime narucioca", "Prezime narucioca");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getCena() >= minCena && this.lista.get(i).getCena() <= maxCena) {
				System.out.println(this.lista.get(i));
			}
		}

	}

	// Prikaz prosecne cene prevoza u zadatom periodu
	public void prosCenaPeriod(LocalDate pocetak, LocalDate kraj) { // 8
		double ukupnaCena = 0.0;
		int spisak = 0;
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getDatumPocetka().compareTo(pocetak) >= 0
					&& this.lista.get(i).getDatumKraja().compareTo(kraj) <= 0) {
				ukupnaCena += this.lista.get(i).getCena();
				spisak++;
			}
		}
		if (spisak > 0) {
			double prosecnaCena = ukupnaCena / spisak;
			System.out.println("Prosecna cena prevoza za zadati period je:" + prosecnaCena);
		} else {
			System.out.println("Nema prevoza za zadati period");
		}
	}

	public void pretraga9() {
		System.out.printf("%15s %10s %25s %25s %20s %20s %20s \n", "Id", "Cena", "Datum pocetka", "Datum kraja",
				"Kategorija vozila", "Ime narucioca", "Prezime narucioca");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------------------");
		LocalDate sada = LocalDate.now();
		Prevoz tekuci = null;
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getDatumPocetka().compareTo(sada) <= 0
					&& this.lista.get(i).getDatumKraja().compareTo(sada) >= 0) {
				if (tekuci == null) {
					tekuci = this.lista.get(i);

				} else {
					if (this.lista.get(i).getCena() > tekuci.getCena()) {
						tekuci = this.lista.get(i);
					}
				}
			}
		}

		if (tekuci != null) {
			System.out.println(tekuci);

		} else {
			System.out.println("Ne postoji tekuci prevoz.");

		}

	}

	public void save(String path) {

		ArrayList<String> lines = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		lines.add(this.naziv);
		lines.add(this.webAdresa);
		lines.add(this.telefon);

		for (int i = 0; i < this.lista.size(); i++) {

			Prevoz prevoz = this.lista.get(i);
			int id = prevoz.getId();
			double cena = prevoz.getCena();
			String datumPocetka = dtf.format(prevoz.getDatumPocetka());
			String datumKraja = dtf.format(prevoz.getDatumKraja());
			String kategorijaVozila = prevoz.getKategorijaVozila();
			String imeNarucioca = prevoz.getImeNarucioca();
			String prezimeNarucioca = prevoz.getPrezimeNarucioca();

			String line = id + ";" + cena + ";" + datumPocetka + ";" + datumKraja + ";" + kategorijaVozila + ";"
					+ imeNarucioca + ";" + prezimeNarucioca;
			lines.add(line);
		}
		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Desila se greska prilikom cuvanja podataka.");

		}
	}

	public void load(String path) {

		this.lista = new ArrayList<Prevoz>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			this.naziv = lines.get(0);
			this.webAdresa = lines.get(1);
			this.telefon = lines.get(1);

			for (int i = 3; i < lines.size(); i++) {
				String[] niz = lines.get(i).split(";");

				int id = Integer.parseInt(niz[0]);
				double cena = Double.parseDouble(niz[1]);
				LocalDate datumPocetka = LocalDate.parse(niz[2], dtf);
				LocalDate datumKraja = LocalDate.parse(niz[3], dtf);
				String kategorijaVozila = niz[4];
				String imeNarucioca = niz[5];
				String prezimeNarucioca = niz[6];

				Prevoz prevoz = new Prevoz(id, cena, datumPocetka, datumKraja, kategorijaVozila, imeNarucioca, prezimeNarucioca);
				this.lista.add(prevoz);

			}

		} catch (java.io.IOException e) {
			System.out.println("Desila se greska prilikom citanja fajla.");
		} catch (NumberFormatException e) {
			System.out.println("Desila se greska prilikom konverzije brojeva.");
		} catch (DateTimeParseException e) {
			System.out.println("Desila se greska prilikom konverzije datuma.");
		} catch (Exception e) {
			System.out.println("Desila se nepredvidjena greska.");

		}
	}

	public double ukupnaCena() {
		double cena = 0.0;
		for (int i = 0; i < this.lista.size(); i++) {
			cena += this.lista.get(i).getCena();

		}
		return cena;
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "Naziv katastra: " + this.naziv + "\n";
		temp += "Web adresa katastra: " + this.webAdresa + "\n";
		temp += "Ukupna cena prevoza: " + ukupnaCena() + "\n";
		temp += "Ukupan broj prevoza: " + this.lista.size() + "\n";

		return temp.trim();

	}

}
