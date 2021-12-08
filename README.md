# NDSHF_SemArbeit_1
## Multiple-Choice Lern- und Prüfungsplattform 
<p>Wir erstellen ein Tool mit dem Multiple-Choice Fragen in eine Datenbank abgefüllt und verwalltet werden kann.
Das Tool stellt die eingetragenen Fragen in zufälliger Reihenfolge und die Antworten werden auf ihre Richtigkeit
bearbeitet.</p>
<p>Das Tool wird zwei Ebenen besitzen Ebene 1 => Lernmodus Ebene 2 => Prüfungsmodus</p>

###Lernmodus (Primärprojekt)
<p>Im Lernmodus soll die Antwort direkt auf ihre Richtigkeit geprüft werden und die Lösung zur Frage wird direkt
innerhalb der View mit Verweis auf die Referenzlösung dargestellt.</p>

###Prüfungsmodus (Optionalprojekt)
<p>Im Prüfungsmodus werden die Antworten erst am Ende auf Richtigkeit geprüft und es wird berechnet wieviele Prozent
der Prüfling korrekt beantwortet hat</p>

###Start GUI Struktur
<ul>
<li>Titel der Mainview</li>
<li>Button für Lernmodus</li>
<li>Button für Fragenverwaltung</li>
</ul>

###Lernmodus GUI Struktur
<ul>
<li>Titel für die Lernview</li>
<li>ChoiceBox oder InputField für Anzahl Fragen</li>
<li>Label mit max. Anzahl Fragen möglich</li>
<li>Startbutton</li>
</ul>

###Lernmodus aktiv GUI Struktur
<ul>
<li>Titel mit Frage</li>
<li>Antwortoptionen per CheckBox</li>
<li>Hinweistext zu Lösung (Grün/Rot)</li>
<li>Button für Check/Next</li>
</ul>

###Endview GUI Struktur
<ul>
<li>Titel der Endview</li>
<li>Button mit Ende</li>
<li>Statisik der Max. und erreichten Punkte</li>
<li>Textfeld für Name</li>
<li>Anzeige der letzten 10 Lerndurchgänge</li>
<li>Button Zurück/Home</li>
<li>Button Exit</li>
</ul>

###Verwaltungsmaske GUI Struktur
<ul>
<li>Titel für View</li>
<li>Fragen/Antworten hinzufügen</li>
<li>Fragen/Antworten bearbeiten</li>
<li>Fragen/Antworten löschen</li>
<li>Highscore Tabelle zurücksetzen</li>
</ul>

####GUI für Fragen/Antworten hinzufügen
<ul>
<li>Titel</li>
<li>Textfeld für Frage</li>
<li>Button für Antwort hinzufügen/Max. 4 Antworten möglich</li>
<li>Textfeld für Antwort</li>
<li>Auswahlfeld für Richtig/Falsch</li>
<li>Button für weitere Frage hinzufügen</li>
<li>Button für Speichern</li>
</ul>

####GUI für Fragen/Antworten bearbeiten
<ul>
<li>Titel</li>
<li>Ausgabefeld der Frage</li>
<li>Ausgabefeld der Antworten</li>
<li>Auswahlfeld für TRUE/FALSE</li>
<li>Button für Speichern</li>
</ul>

