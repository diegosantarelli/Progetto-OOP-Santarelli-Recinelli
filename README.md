# Progetto-OOP-Santarelli-Recinelli
# Introduzione

Il nostro programma permette all'utente di visualizzare tutte le informazioni attuali relative al vento (speed), le statistiche relative alle previsioni (media, varianza, massimi e minimi di temperatura reale e percepita) e per i successivi 5 giorni.
Viene richiesto l'inserimento della città ed il relativo Paese (per esempio Ancona, IT) necessari per mostrare le previsioni e le statistiche; nel caso in cui l'utente non specifichi città e Paese viene preso di default Ancona, IT.
Inoltre l'utente può decidere se calcolare le statistiche in base alla periodicità: giorno, ora di un giorno, fascia oraria, 5 giorni.

# Rotte

Tutte le chiamate che l'utente effettua su Postman devono essere all'indirizzo `localhost:8080`
Le rotte sono le seguenti:
| N° | Tipo | Rotta | Descrizione |
|--|--|--|--|
| 1 | `GET` | `/APICall/{city}/{country}` |  |
| 2 | `GET` | `/JSONParsing/{city}/{country}` |  |
| 3 | `GET` | `/JSONParsingWind/{city}/{country}` | Restituisce un oggetto di tipo City contenente le informazioni  attuali relative al vento e per i successivi 5 giorni |
| 4 | `GET` | `/MyTimer/{city}/{country}` | Restituisce il path del file, utilizzato poi per calcolare le statistiche, contenente tutte le informazioni attuali relative al vento e previsioni aggiornato ogni ora |
| 5 | `GET` | `/JSONParsingStats/{city}/{country}` |  |
| 6 | `GET` | `/FilterDay/{city}/{country}/{day}` | Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad un giorno scelto dall'utente |
| 7 | `GET` | `/Filter1Hour/{city}/{country}/{day}/{time}` | Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad un'ora precisa di un giorno, entrambi scelti dall'utente |
| 8 | `GET` | `/FilterPerHours/{city}/{country}/{hours}` | Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad una fascia oraria ricorrente in tutti i giorni, scelta dall'utente |
| 9 | `GET` | `/FilterPerWeek/{city}/{country}/{date1}/{date2}` | Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad una settimana con data di inizio e di fine entrambe scelte dall'utente |
