package verwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.itextpdf.layout.Document;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

public class Auftraegedrucken {
	private JFrame frmAuftrgeDrucken;

	Document auftraegedocument;
	Document wuerstedocument;
	Document haehnchenPutedocument;
	Document EnteGansdocument;
	Document wilddocument;
	Document schweindocument;
	Document rindKalbdocument;
	Document lammdocument;
	Document aufschnittdocument;
	Document kuecheDocument;
	Document salatKaesedocument;
	Document vorbereitungdocument;
	Document holding;
	Auftrag_nach_Datum auftrag;

	String zubereitung1;
	JLabel lblNewLabel_1;
	Sortierung a;

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final Auftraegedrucken window = new Auftraegedrucken();
					window.frmAuftrgeDrucken.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Auftraegedrucken() throws IOException, InterruptedException {
		this.godmethode();
		this.setUpPDF();
		this.ladeAuftraege();
		this.initialize();
	}

	@SuppressWarnings("unchecked")
	public void godmethode() throws IOException {
		final BufferedReader ein = new BufferedReader(new FileReader("daten/Auftrag.txt"));
		String zeile = "";
		final ArrayList<String> liste = new ArrayList<String>();
		final ArrayList<Sortierung> sortliste = new ArrayList<Sortierung>();
		int counter = 0;
		while ((zeile = ein.readLine()) != null) {
			if (this.proof(zeile) && counter == 1) {
				counter = 0;
				this.a.zeilen = (ArrayList<String>) liste.clone();
				sortliste.add(this.a);
				liste.clear();
			}
			if (this.proof(zeile) && counter == 0) {
				this.a = new Sortierung();
				this.a.datum = zeile.substring(12, 23);
				liste.add(zeile);
				counter = 1;
			}
			if (!this.proof(zeile) && counter == 1) {
				liste.add(zeile);
			}
		}
		this.a.zeilen = liste;
		sortliste.add(this.a);
		Collections.sort(sortliste);

		final PrintWriter write = new PrintWriter(new FileWriter("daten/Auftrag.txt"));
		write.close();
		final PrintWriter write2 = new PrintWriter(new FileWriter("daten/Auftrag.txt", true));
		for (int jot = 0; jot < sortliste.size(); ++jot) {
			for (int ih = 0; ih < sortliste.get(jot).zeilen.size(); ++ih) {
				write2.println(sortliste.get(jot).zeilen.get(ih));
			}
		}
		write2.close();
	}

	private void setUpPDF() throws IOException, InterruptedException {
		auftraege_nach_tagen();
		final String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		final File dir = new File(fileName);
		System.out.println(dir.mkdir());
		this.lblNewLabel_1 = new JLabel("Im Ordner" + dir.getAbsolutePath());

		// Files
		final File auftraege = new File(String.valueOf(fileName) + "/Alle-Aufträge.pdf");
		final File vorbereitung = new File(String.valueOf(fileName) + "/Vorbereitung.pdf");
		final File wuerste = new File(String.valueOf(fileName) + "/Würste.pdf");
		final File haehnchenPute = new File(String.valueOf(fileName) + "/Hähnchen-Pute.pdf");
		final File enteGans = new File(String.valueOf(fileName) + "/Ente-Gans.pdf");
		final File wild = new File(String.valueOf(fileName) + "/Wild.pdf");
		final File schwein = new File(String.valueOf(fileName) + "/Schwein.pdf");
		final File rindKalb = new File(String.valueOf(fileName) + "/Rind-Kalb.pdf");
		final File lamm = new File(String.valueOf(fileName) + "/Lamm.pdf");
		final File aufschnitt = new File(String.valueOf(fileName) + "/Aufschnitt.pdf");
		final File kueche = new File(String.valueOf(fileName) + "/Küche.pdf");
		final File salatkaese = new File(String.valueOf(fileName) + "/Salat-Käse.pdf");

		// Writer
		final PdfWriter auftraegeWriter = new PdfWriter(auftraege);
		final PdfWriter vorbereitungWriter = new PdfWriter(vorbereitung);
		final PdfWriter wuersteWriter = new PdfWriter(wuerste);
		final PdfWriter haehnchenPuteWriter = new PdfWriter(haehnchenPute);
		final PdfWriter enteGansWriter = new PdfWriter(enteGans);
		final PdfWriter wildWriter = new PdfWriter(wild);
		final PdfWriter schweinWriter = new PdfWriter(schwein);
		final PdfWriter rindKalbWriter = new PdfWriter(rindKalb);
		final PdfWriter lammWriter = new PdfWriter(lamm);
		final PdfWriter aufschnittWriter = new PdfWriter(aufschnitt);
		final PdfWriter kuecheWriter = new PdfWriter(kueche);
		final PdfWriter salatkaeseWriter = new PdfWriter(salatkaese);

		// PDF Docs
		final PdfDocument auftraegepdfdoc = new PdfDocument(auftraegeWriter);
		final PdfDocument vorbereitungpdfdoc = new PdfDocument(vorbereitungWriter);
		final PdfDocument wuerstepdfdoc = new PdfDocument(wuersteWriter);
		final PdfDocument haehnchenPuteWriterpdfdoc = new PdfDocument(haehnchenPuteWriter);
		final PdfDocument enteGansWriterpdfdoc = new PdfDocument(enteGansWriter);
		final PdfDocument wildWriterpdfdoc = new PdfDocument(wildWriter);
		final PdfDocument schweinWriterpdfdoc = new PdfDocument(schweinWriter);
		final PdfDocument rindKalbWriterpdfdoc = new PdfDocument(rindKalbWriter);
		final PdfDocument lammWriterpdfdoc = new PdfDocument(lammWriter);
		final PdfDocument aufschnittWriterpdfdoc = new PdfDocument(aufschnittWriter);
		final PdfDocument kuecheWriterpdfdoc = new PdfDocument(kuecheWriter);
		final PdfDocument salatkaeseWriterpdfdoc = new PdfDocument(salatkaeseWriter);

		this.auftraegedocument = new Document(auftraegepdfdoc);
		this.vorbereitungdocument = new Document(vorbereitungpdfdoc);
		this.wuerstedocument = new Document(wuerstepdfdoc);
		this.haehnchenPutedocument = new Document(haehnchenPuteWriterpdfdoc);
		this.EnteGansdocument = new Document(enteGansWriterpdfdoc);
		this.wilddocument = new Document(wildWriterpdfdoc);
		this.schweindocument = new Document(schweinWriterpdfdoc);
		this.rindKalbdocument = new Document(rindKalbWriterpdfdoc);
		this.lammdocument = new Document(lammWriterpdfdoc);
		this.aufschnittdocument = new Document(aufschnittWriterpdfdoc);
		this.kuecheDocument = new Document(kuecheWriterpdfdoc);
		this.salatKaesedocument = new Document(salatkaeseWriterpdfdoc);

		auftraegepdfdoc.setDefaultPageSize(PageSize.A4);
		vorbereitungpdfdoc.setDefaultPageSize(PageSize.A4);
		wuerstepdfdoc.setDefaultPageSize(PageSize.A4);
		haehnchenPuteWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		enteGansWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		wildWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		schweinWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		rindKalbWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		lammWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		aufschnittWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		kuecheWriterpdfdoc.setDefaultPageSize(PageSize.A4);
		salatkaeseWriterpdfdoc.setDefaultPageSize(PageSize.A4);

		// this.auftraegedocument.add(new
		// Paragraph("auftraege").setBold().setFontSize(14.0f).setTextAlignment(TextAlignment.LEFT));
		this.vorbereitungdocument.add(new Paragraph(
				new Text("Vorbereitung").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.auftraegedocument.add(
				new Paragraph(new Text("Aufträge").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.wuerstedocument.add(
				new Paragraph(new Text("Würste").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.haehnchenPutedocument.add(new Paragraph(
				new Text("Hähnchen/Pute").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.EnteGansdocument.add(
				new Paragraph(new Text("Ente/Gans").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.wilddocument
				.add(new Paragraph(new Text("Wild").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.schweindocument.add(
				new Paragraph(new Text("Schwein").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.rindKalbdocument.add(
				new Paragraph(new Text("Rind/Kalb").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.lammdocument
				.add(new Paragraph(new Text("Lamm").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.aufschnittdocument.add(
				new Paragraph(new Text("Aufschnitt").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.kuecheDocument
				.add(new Paragraph(new Text("Küche").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));
		this.salatKaesedocument.add(
				new Paragraph(new Text("Salat/Käse").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))));

	}

	public String get_Datum_of_header(String header) {
		String speicher_Datum = "";
		for (int i = 0; i < header.length(); i++) {
			if (header.charAt(i) == '|') {
				speicher_Datum = header.substring(12, i);

				return speicher_Datum;
			}
		}
		return "";
	}

	public void auftraege_nach_tagen() throws IOException {

		final File auftragsdatei = new File("daten/Auftrag.txt");
		final BufferedReader ein = new BufferedReader(new FileReader(auftragsdatei));
		String speicher_Datum = "";
		String zeile = "";
		ArrayList<Auftrag_nach_Datum> auftraege = new ArrayList<>();

		int counter = 0;
		while ((zeile = ein.readLine()) != null) {

			if (this.proof(zeile) && counter == 0) {
				auftrag = new Auftrag_nach_Datum();
				auftrag.header = zeile;
				auftrag.datum = get_Datum_of_header(zeile);
				counter++;

			} else {
				counter = 1;
				if (this.proof(zeile)) {
					auftraege.add(new Auftrag_nach_Datum(auftrag.header, (ArrayList<String>) auftrag.zeilen.clone(),
							auftrag.datum));
					auftrag.datum = "";
					auftrag.header = "";
					auftrag.zeilen.clear();

					auftrag.header = zeile;
					auftrag.datum = get_Datum_of_header(zeile);

				} else {
					auftrag.zeilen.add(zeile);
				}
			}
		}
		ein.close();
		auftraege.add(auftrag);
		for (int k = 0; k < auftraege.size(); k++) {
			for (int c = 0; c < auftraege.get(k).zeilen.size(); c++) {
				auftraege.get(k).zeilen.set(c, auftraege.get(k).zeilen.get(c).replace("$", " "));
				auftraege.get(k).zeilen.set(c, auftraege.get(k).zeilen.get(c).replace("§", " "));

			}
		}
		Collections.sort(auftraege);
		final String folder_nach_aufträgen = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + " Alle Aufträge";
		final File dir = new File(folder_nach_aufträgen);
		System.out.println("creating directory:" + dir.mkdir());

		File alle_auftraege_nach_datum;
		PdfWriter alle_auftraege_nach_datum_Writer;
		PdfDocument auftraegepdfdoc;
		for (int i = 0; i < auftraege.size(); i++) {
			String name = "";
			int generalcounter = 0;
			int counter2 = 0;
			int counter3 = 0;
			for (int j = 0; j < auftraege.get(i).header.length(); j++) {
				if (auftraege.get(i).header.charAt(j) == '|' && generalcounter == 1) {
					counter2 = j - 1;
					break;
				}
				if (auftraege.get(i).header.charAt(j) == '|') {
					counter = j + 1;
					generalcounter = 1;
				}
			}
			name = auftraege.get(i).header.substring(counter, counter2);
			String auftragsnummer="";
			String datum="";
			for (int j =auftraege.get(i).header.length()-1; j > 0; j--) {
				if (auftraege.get(i).header.charAt(j) == ':') {
					auftragsnummer= auftraege.get(i).header.substring(j+1   ,  auftraege.get(i).header.length());
					break;
				}
				
			}
			int counterm=0;
			int counteri=0;
			for(int k=0; k < auftraege.get(i).header.length();k ++) {
				if(auftraege.get(i).header.charAt(k)=='m') {
					counterm=k+1;
					
					
				}
				if(auftraege.get(i).header.charAt(k)=='|') {
					counteri=k-1;
					
					break;
				}
				
				
				
				
			}
			datum=auftraege.get(i).header.substring(counterm,counteri);

			alle_auftraege_nach_datum = new File(String.valueOf(folder_nach_aufträgen) + "/" +datum +" "+ name+" NR "+auftragsnummer+ ".pdf");
			alle_auftraege_nach_datum_Writer = new PdfWriter(alle_auftraege_nach_datum);
			auftraegepdfdoc = new PdfDocument(alle_auftraege_nach_datum_Writer);
			holding = new Document(auftraegepdfdoc);
			holding.add(new Paragraph(auftraege.get(i).header).setBold().setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
			for (int j = 0; j < auftraege.get(i).zeilen.size(); j++) {
				holding.add(new Paragraph(auftraege.get(i).zeilen.get(j)).setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
			}
			holding.close();
			alle_auftraege_nach_datum_Writer.close();
			auftraegepdfdoc.close();

		}

//			if(i!=0) {
//			if(auftraege.get(i).datum.equals(auftraege.get(i-1).datum)) {
//				holding.add(new Paragraph(auftraege.get(i).header).setBold().setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
//				for(int j=0;j<auftraege.get(i).zeilen.size();j++) {	
//					holding.add(new Paragraph(auftraege.get(i).zeilen.get(j)).setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
//				}	
//				
//			}
//			else {
//				
//				alle_auftraege_nach_datum = new File(String.valueOf(folder_nach_aufträgen) + "/Für_den_"+auftraege.get(i).datum+".pdf");
//				alle_auftraege_nach_datum_Writer = new PdfWriter(alle_auftraege_nach_datum);
//				auftraegepdfdoc = new PdfDocument(alle_auftraege_nach_datum_Writer);
//				holding = new Document(auftraegepdfdoc);
//				holding.add(new Paragraph(auftraege.get(i).header).setBold().setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
//			for(int j=0;j<auftraege.get(i).zeilen.size();j++) {	
//				holding.add(new Paragraph(auftraege.get(i).zeilen.get(j)).setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
//			}
//			}}

	}

	

	public Auftraegedrucken(final boolean n) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final Auftraegedrucken window = new Auftraegedrucken();
					window.frmAuftrgeDrucken.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void ladeAuftraege() throws IOException {
		String dependency = "";
		String zeile2 = "";
		final ArrayList<Regel> regelliste = new ArrayList<Regel>();
		final BufferedReader ein2 = new BufferedReader(new FileReader("daten/Sortierregeln.txt"));
		while ((zeile2 = ein2.readLine()) != null) {
			for (int i = 0; i < zeile2.length(); ++i) {
				if (zeile2.charAt(i) == '$') {
					regelliste.add(new Regel(zeile2.substring(0, i), zeile2.substring(i, zeile2.length())));
				}
			}
		}

		final File auftragsdatei = new File("daten/Auftrag.txt");
		String zeile3 = "";
		final BufferedReader ein3 = new BufferedReader(new FileReader(auftragsdatei));
		final String anzahl = "";
		final String name = "";
		final String zubereitung = "";
		int counter = 0;
		String header = "";
		ArrayList<Integer> counts = new ArrayList<>();

		while ((zeile3 = ein3.readLine()) != null) {
			if (this.proof(zeile3)) {
				header = zeile3;
				counts.clear();
				for (int i = 0; i < 12; i++) {
					counts.add(0);
				}
				if (counter < 1) {
					++counter;
					this.auftraegedocument.add(
							new Paragraph(zeile3).setBold().setFontSize(12.0f).setTextAlignment(TextAlignment.CENTER));
				} else {

					this.auftraegedocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
					this.auftraegedocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f))
							.setTextAlignment(TextAlignment.CENTER));
				}
			}

			else {
				int counteri = 0;
				int counterj = 0;
				for (int j = 0; j < zeile3.length(); ++j) {
					if (zeile3.charAt(j) == '$') {
						counteri = j;
					}
					if (zeile3.charAt(j) == '§') {
						counterj = j;
					}
				}
				for (final Regel e : regelliste) {
					if (e.food.toLowerCase().equals((zeile3.substring(0, counteri).toLowerCase()))) {
						dependency = e.dependency.substring(1, e.dependency.length());
						break;
					}
				}

				if (zeile3.substring(counterj + 1, zeile3.length()).length() == 0) {
					this.zubereitung1 = "";
				} else {
					this.zubereitung1 = "| Zubereitung= " + zeile3.substring(counterj + 1, zeile3.length());
				}
				// "Wuerste", "Haehnchen/Pute", "Ente/Gans", "Wild", "Schwein", "Rind/Kalb",
				// "Lamm", "Aufschnitt","Kueche", "Salat/Kaese"
//              Document auftraegedocument;

//              Document wuerstedocument;
//              Document haehnchenPutedocument;
//              Document EnteGansdocument;
//              Document wilddocument;
//              Document schweindocument;
//              Document rindKalbdocument;
//              Document lammdocument;
//              Document aufschnittdocument;
//              Document kuecheDocument;
//              Document salatKaesedocument; 
				this.auftraegedocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
						+ "x " + zeile3.substring(0, counteri) + " " + this.zubereitung1).setFontSize(12.0f))
								.setTextAlignment(TextAlignment.LEFT));
				final String lowerCase;

				switch (dependency.toLowerCase()) {
				case "vorbereitung":
					if (counts.get(10) == 0) {
						this.vorbereitungdocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(10, 1);
					}
					this.vorbereitungdocument
							.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj)) + "x "
									+ zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
											.setTextAlignment(TextAlignment.LEFT));
					break;
				case "wuerste":
					if (counts.get(0) == 0) {
						this.wuerstedocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(0, 1);
					}
					this.wuerstedocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "haehnchen/pute":
					if (counts.get(1) == 0) {
						this.haehnchenPutedocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(1, 1);
					}
					this.haehnchenPutedocument
							.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj)) + "x "
									+ zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
											.setTextAlignment(TextAlignment.LEFT));
					break;

				case "ente/gans":
					if (counts.get(2) == 0) {
						this.EnteGansdocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(2, 1);
					}
					this.EnteGansdocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;

				case "wild":
					if (counts.get(3) == 0) {
						this.wilddocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(3, 1);
					}
					this.wilddocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj)) + "x "
							+ zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;

				case "schwein":
					if (counts.get(4) == 0) {
						this.schweindocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(4, 1);
					}
					this.schweindocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "rind/kalb":
					if (counts.get(5) == 0) {
						this.rindKalbdocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(5, 1);
					}
					this.rindKalbdocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "lamm":
					if (counts.get(6) == 0) {
						this.lammdocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(6, 1);
					}
					this.lammdocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj)) + "x "
							+ zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "aufschnitt":
					if (counts.get(7) == 0) {
						this.aufschnittdocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(7, 1);
					}
					this.aufschnittdocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "kueche":
					if (counts.get(8) == 0) {
						this.kuecheDocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(8, 1);
					}
					this.kuecheDocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;
				case "salat/kaese":
					if (counts.get(9) == 0) {
						this.salatKaesedocument.add((new Paragraph(header).setBold().setFontSize(12.0f))
								.setTextAlignment(TextAlignment.CENTER));
						counts.set(9, 1);
					}
					this.salatKaesedocument.add((new Paragraph(String.valueOf(zeile3.substring(counteri + 1, counterj))
							+ "x " + zeile3.substring(0, counteri) + this.zubereitung1).setFontSize(12.0f))
									.setTextAlignment(TextAlignment.LEFT));
					break;

				default:
					break;

				}
			}
		}
//        this.wuerstedocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.haehnchenPutedocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.EnteGansdocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.wilddocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.schweindocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.rindKalbdocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.lammdocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.aufschnittdocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.kuecheDocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));
//        this.salatKaesedocument.add((new Paragraph(zeile3).setBold().setFontSize(12.0f)).setTextAlignment(TextAlignment.CENTER));

		ein3.close();
		this.vorbereitungdocument.close();
		this.auftraegedocument.close();
		this.wuerstedocument.close();
		this.haehnchenPutedocument.close();
		this.EnteGansdocument.close();
		this.wilddocument.close();
		this.rindKalbdocument.close();
		this.lammdocument.close();
		this.aufschnittdocument.close();
		this.kuecheDocument.close();
		this.salatKaesedocument.close();
		this.schweindocument.close();

	}

	private void initialize() {
		(this.frmAuftrgeDrucken = new JFrame()).setTitle("Auftraege drucken");
		this.frmAuftrgeDrucken.setBounds(100, 100, 652, 187);
		this.frmAuftrgeDrucken.setDefaultCloseOperation(3);
		this.frmAuftrgeDrucken.getContentPane().setLayout(null);
		final JButton btnNewButton_1 = new JButton("zurueck");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				Auftraegedrucken.this.frmAuftrgeDrucken.setVisible(false);
				new VerwaltungsGui(true);
			}
		});
		btnNewButton_1.setBounds(10, 117, 89, 23);
		this.frmAuftrgeDrucken.getContentPane().add(btnNewButton_1);
		final JLabel lblNewLabel = new JLabel("PDFs wurden gespeichert");
		lblNewLabel.setBounds(10, 24, 335, 36);
		this.frmAuftrgeDrucken.getContentPane().add(lblNewLabel);
		this.lblNewLabel_1.setBounds(10, 71, 563, 36);
		this.frmAuftrgeDrucken.getContentPane().add(this.lblNewLabel_1);
	}

	public boolean proof(final String a) {
		int counter = 0;
		if (a.charAt(0) != 'A') {
			return false;
		}
		++counter;
		if (a.charAt(1) != 'u') {
			return false;
		}
		++counter;
		if (a.charAt(2) != 'f') {
			return false;
		}
		++counter;
		if (a.charAt(3) != 't') {
			return false;
		}
		++counter;
		if (a.charAt(4) != 'r') {
			return false;
		}
		++counter;
		if (a.charAt(5) != 'a') {
			return false;
		}
		++counter;
		if (a.charAt(6) != 'g') {
			return false;
		}
		++counter;
		if (a.charAt(7) != ' ') {
			return false;
		}
		++counter;
		if (a.charAt(8) != 'z') {
			return false;
		}
		++counter;
		if (a.charAt(9) != 'u') {
			return false;
		}
		++counter;
		if (a.charAt(10) == 'm') {
			++counter;
			return counter == 11;
		}
		return false;
	}
}
