# NDSHF_SemArbeit_1
## Multiple-Choice Lern- und Prüfungsplattform 
<p>Wir erstellen ein Tool mit dem Multiple-Choice Fragen in eine Datenbank abgefüllt und verwalltet werden kann.
Das Tool stellt die eingetragenen Fragen in zufälliger Reihenfolge und die Antworten werden auf ihre Richtigkeit
bearbeitet.</p>
<p>Das Tool wird zwei Ebenen besitzen Ebene 1 => Lernmodus Ebene 2 => Prüfungsmodus</p>

### Lernmodus (Primärprojekt)
<p>Im Lernmodus soll die Antwort direkt auf ihre Richtigkeit geprüft werden und die Lösung zur Frage wird direkt
innerhalb der View mit Verweis auf die Referenzlösung dargestellt.</p>

### Prüfungsmodus (Optionalprojekt)
<p>Im Prüfungsmodus werden die Antworten erst am Ende auf Richtigkeit geprüft und es wird berechnet wieviele Prozent
der Prüfling korrekt beantwortet hat</p>

<div style="width: 70%; display: flex;">
<div style="float: left">
<p>

### Start GUI Struktur
- Titel der Mainview
- Button für Lernmodus
- Button für Fragenverwaltung</p>
</div>
<div style="float: left">
<p>

### Lernmodus GUI Struktur
- Titel für die Lernview
- ChoiceBox oder InputField für Anzahl Fragen 
- Label mit max. Anzahl Fragen möglich 
- Startbutton</p>
</div>
<div style="float: left">
<p>

### Lernmodus aktiv GUI Struktur
- Titel mit Frage
- Antwortoptionen per CheckBox
- Hinweistext zu Lösung (Grün/Rot)
- Button für Check/Next</p>
</div>
</div>
<div style="width: 70%; display: flex;">
<div style="float: left">
<p>

### Endview GUI Struktur

- Titel der Endview
- Button mit Ende
- Statisik der Max. und erreichten Punktz
- Textfeld für Name
- Anzeige der letzten 10 Lerndurchgänge
- Button Zurück/Home
- Button Exit

</p>
</div>
<div style="float: left">
<p>

### Verwaltungsmaske GUI Struktur
- Titel für View
- Fragen/Antworten hinzufügen
- Fragen/Antworten bearbeiten
- Fragen/Antworten löschen
- Highscore Tabelle zurücksetzen
</p>
</div>
</div>
<div style="width: 70%; display: flex;">
<div style="float: left">
<p>

### GUI für Fragen/Antworten hinzufügen
- Titel
- Textfeld für Frage
- Button für Antwort hinzufügen/Max. 4 Antworten möglich
- Textfeld für AntwortAuswahlfeld für Richtig/Falsch
- Button für weitere Frage hinzufügen
- Button für Speichern
</p>
</div>
<div style="float: left">
<p>

### GUI für Fragen/Antworten bearbeiten
- Titel
- Ausgabefeld der Frage
- Ausgabefeld der Antworten
- Auswahlfeld für TRUE/FALSE
- Button für Speichern
</p>
</div>
</div>

# Installation
## Hinweis
Für die Funktion wird eine lokale Installation der MariaDB vorausgesetzt. MariaDB kann unter MacOS mittels Homebrew installiert werden.

### Installation Datenbank unter MacOS
Eine Anleitung zur Installation der MariaDB mittels Homebrew findest du hier:
https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/

### Installation Datenbank unter Windows
EIne Anleitung zur Installation der MariaDB auf Windows Systemen findest du hier:
https://mariadb.com/kb/en/installing-mariadb-msi-packages-on-windows/
