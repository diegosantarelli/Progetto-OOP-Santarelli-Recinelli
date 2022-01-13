# Progetto-OOP-Santarelli-Recinelli
# Introduzione

Il nostro programma permette all'utente di visualizzare tutte le informazioni attuali relative al vento (speed), le statistiche relative alle previsioni (media, varianza, massimi e minimi di temperatura reale e percepita) e per i successivi 5 giorni.
Viene richiesto l'inserimento della città ed il relativo Paese (per esempio Ancona, IT) necessari per mostrare le previsioni e le statistiche; nel caso in cui l'utente non specifichi città e Paese viene preso di default Ancona, IT.
Inoltre l'utente può decidere se calcolare le statistiche in base alla periodicità: giorno, ora di un giorno, fascia oraria, 5 giorni.

# Rotte
Ora illustriamo le rotte che l'utente può utilizzare su Postman, seguendo le istruzioni che forniamo di seguito.
Tutte le chiamate che l'utente effettua su Postman devono essere all'indirizzo `localhost:8080`
Le rotte sono le seguenti:
| N° | Tipo | Rotta | Descrizione |
|--|--|--|--|
| 1 | `GET` | `/APICall/{city}/{country}` | *Restituisce un JSONObject contenente le informazioni della città di Ancona attuali e per i successivi 5 giorni , relative all'id della città, latitudine, longitudine, vento e previsioni generali.*  |
| 2 | `GET` | `/JSONParsing/{city}/{country}` |*Restituisce un oggetto di tipo City contenente le informazioni attuali e per i successivi 5 giorni, relative al vento, previsioni generali, data e ora alle quali vengono effettute le previsioni.*|
| 3 | `GET` | `/JSONParsingWind/{city}/{country}` | *Restituisce un oggetto di tipo City contenente le informazioni attuali e per i successivi 5 giorni, relative al vento e alla data e l'ora alle quali vengono effettute le previsioni.* |
| 4 | `GET` | `/MyTimer/{city}/{country}` | *Restituisce una stringa costituita dal path del file, utilizzato poi per calcolare le statistiche, contenente tutte le informazioni attuali relative al vento e alle previsioni generali, aggiornato ogni ora.* |
| 5 | `GET` | `/JSONParsingStats/{city}/{country}` |*Restituisce un oggetto di tipo City contenente le informazioni attuali e per i successivi 5 giorni, relative alle temperature reali (e massima e minima), e quella percepita e alla data e l'ora alle quali vengono effettute le previsioni.* |
| 6 | `GET` | `/FilterDay/{city}/{country}/{day}` | *Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad un giorno scelto dall'utente.* |
| 7 | `GET` | `/Filter1Hour/{city}/{country}/{day}/{time}` | *Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad un'ora precisa di un giorno, entrambi scelti dall'utente.* |
| 8 | `GET` | `/FilterPerHours/{city}/{country}/{hours}` | *Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad una fascia oraria ricorrente in tutti i giorni, scelta dall'utente.* |
| 9 | `GET` | `/FilterPerWeek/{city}/{country}/{date1}/{date2}` | *Restituisce un JSONObject contenente le statistiche di una città filtrate relativamente ad una settimana con data di inizio e di fine entrambe scelte dall'utente.* |


## 1. /APICall/{city}/{country}
La seguente rotta restituisce un JSONObject contenente i  JSONArray e i JSONObject che riportano le informazioni sulle previsioni generali, la data e l'ora a cui le previsioni si riferiscono, l'id e le altre informazioni sulla città di Ancona. Nel caso quest'ultima e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

![APICall](https://i.ibb.co/sWS3RGy/Schermata-2022-01-13-alle-12-19-10.png)
.
.
.

![APICall](https://i.ibb.co/BPGgYpS/Schermata-2022-01-13-alle-12-33-46.png)
## 2. /JSONParsing/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "vector" contenente i JSONObject che riportano le informazioni sulla velocità del vento, la data e l'ora a cui le previsioni si riferiscono, le previsioni generali, temperature reali (e massima e minima), e quelle percepita . Nel caso la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

![JSONParsing](https://i.ibb.co/gFrBBkk/Schermata-2022-01-13-alle-12-35-17.png)
.
.
.

![JSONParsing](https://i.ibb.co/d0M9QW9/Schermata-2022-01-13-alle-12-37-40.png)
## 3. /JSONParsingWind/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "windVec" contenente i JSONObject che riportano le informazioni sulla velocità del vento e la data e l'ora a cui le previsioni si riferiscono. Nel caso la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

![JSONParsingWind](https://i.ibb.co/BVypcQm/Schermata-2022-01-13-alle-12-40-32.png)
.
.
.

![JSONParsingWind](https://i.ibb.co/zm4ww38/Schermata-2022-01-13-alle-12-41-38.png)
## 4. /MyTimer/{city}/{country}
La seguente rotta restituisce una stringa costituita dal path del file, il quale contiene le informazioni attuali e per i successivi 5 giorni, relative al vento e alle previsioni generali, aggiornato ogni ora.

![MyTimer](https://i.ibb.co/8mg1Pb7/Schermata-2022-01-13-alle-12-51-34.png)
## 5. /JSONParsingStats/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "vectorStats" contenente i JSONObject che riportano le informazioni sulla temperature reali (e massima e minima), e quelle percepita e la data e l'ora a cui le previsioni si riferiscono. Nel caso la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

![JSONParsingStats](https://i.ibb.co/QHDSRj7/Schermata-2022-01-13-alle-12-42-58.png)
.
.
.

![JSONParsingStats](https://i.ibb.co/5W4mHKy/Schermata-2022-01-13-alle-12-43-48.png)

## 6. /FilterDay/{city}/{country}/{day}

![FilterDay](https://i.ibb.co/TgVxSKm/Schermata-2022-01-13-alle-12-45-40.png)

## 7. /Filter1Hour/{city}/{country}/{day}/{time}

![Filter1Hour](https://i.ibb.co/sq7NgqV/Schermata-2022-01-13-alle-12-47-26.png)

## 8. /FilterPerHours/{city}/{country}/{hours}

![FilterPerHours](https://i.ibb.co/mTgHGDp/Schermata-2022-01-13-alle-12-48-29.png)

## 9./FilterPerWeek/{city}/{country}/{date1}/{date2}

![FilterPerWeek](https://i.ibb.co/yk6CJ7S/Schermata-2022-01-13-alle-12-49-28.png)
# Eccezioni
Le eccezioni vengono gestite tramite una classe chiamata WrongCityException che controlla che la città e il Paese inseriti dall'utente siano corrette, altrimenti stamperà a video il seguente messaggio: *"ERR: Città o Paese inseriti non corretti"*.
