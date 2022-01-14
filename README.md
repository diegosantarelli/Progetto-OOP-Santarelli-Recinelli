# Progetto-OOP-Santarelli-Recinelli
# Introduzione
Il nostro programma permette all'utente di visualizzare tutte le informazioni attuali e per i successivi 5 giorni, relative al vento (speed), le statistiche relative alle previsioni (media, varianza, massimi e minimi di temperatura reale e percepita).
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
La seguente rotta restituisce un JSONObject contenente i  JSONArray e i JSONObject che riportano le informazioni sulle previsioni generali, la data e l'ora a cui le previsioni si riferiscono, l'id e le altre informazioni sulla città di Ancona. Nel caso in cui quest'ultima e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![APICall](https://i.ibb.co/sWS3RGy/Schermata-2022-01-13-alle-12-19-10.png)
.
.
.

![APICall](https://i.ibb.co/BPGgYpS/Schermata-2022-01-13-alle-12-33-46.png)
## 2. /JSONParsing/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "vector" contenente i JSONObject che riportano le informazioni sulla velocità del vento, la data e l'ora a cui le previsioni si riferiscono, le previsioni generali, temperature reali (e massima e minima), e quelle percepita . Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![JSONParsing](https://i.ibb.co/gFrBBkk/Schermata-2022-01-13-alle-12-35-17.png)
.
.
.

![JSONParsing](https://i.ibb.co/d0M9QW9/Schermata-2022-01-13-alle-12-37-40.png)
## 3. /JSONParsingWind/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "windVec" contenente i JSONObject che riportano le informazioni sulla velocità del vento e la data e l'ora a cui le previsioni si riferiscono. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![JSONParsingWind](https://i.ibb.co/BVypcQm/Schermata-2022-01-13-alle-12-40-32.png)
.
.
.

![JSONParsingWind](https://i.ibb.co/zm4ww38/Schermata-2022-01-13-alle-12-41-38.png)
## 4. /MyTimer/{city}/{country}
La seguente rotta restituisce una stringa costituita dal path del file, il quale contiene le informazioni attuali e per i successivi 5 giorni, relative al vento e alle previsioni generali, aggiornato ogni ora. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore. Nel caso in cui il path del file non sia scritto correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![MyTimer](https://i.ibb.co/8mg1Pb7/Schermata-2022-01-13-alle-12-51-34.png)
## 5. /JSONParsingStats/{city}/{country}
La seguente rotta restituisce un JSONArray chiamato "vectorStats" contenente i JSONObject che riportano le informazioni sulle temperature reali (e massima e minima), e quelle percepita e la data e l'ora a cui le previsioni si riferiscono. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![JSONParsingStats](https://i.ibb.co/QHDSRj7/Schermata-2022-01-13-alle-12-42-58.png)
.
.
.

![JSONParsingStats](https://i.ibb.co/5W4mHKy/Schermata-2022-01-13-alle-12-43-48.png)

## 6. /FilterDay/{city}/{country}/{day}
La seguente rotta restituisce un JSONObject contenente le informazioni sulle temperature reali (massima e minima), sulle temperature percepite (massima e minima), sulla media e varianza delle temperature reali e percepite. Tutte le informazioni si riferiscono al giorno scelto dall'utente. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![FilterDay](https://i.ibb.co/TgVxSKm/Schermata-2022-01-13-alle-12-45-40.png)

## 7. /Filter1Hour/{city}/{country}/{day}/{time}
La seguente rotta restituisce un JSONObject contenente le informazioni sulla temperatura reale (e massima e minima), sulla temperatura percepita e la data e l'ora a cui le previsioni si riferiscono. Tutte le informazioni si riferiscono all'ora e al giorno scelto dall'utente. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![Filter1Hour](https://i.ibb.co/sq7NgqV/Schermata-2022-01-13-alle-12-47-26.png)

## 8. /FilterPerHours/{city}/{country}/{hours}

La seguente rotta restituisce un JSONObject contenente le informazioni sulle temperature reali (massima e minima), sulle temperature percepite (massima e minima), sulla media e varianza delle temperature reali e percepite.  Tutte le informazioni si riferiscono all'ora scelta dall'utente. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![FilterPerHours](https://i.ibb.co/mTgHGDp/Schermata-2022-01-13-alle-12-48-29.png)

## 9./FilterPerWeek/{city}/{country}/{date1}/{date2}
La seguente rotta restituisce un JSONObject contenente le informazioni sulle temperature reali (massima e minima), sulle temperature percepite (massima e minima), sulla media e varianza delle temperature reali e percepite.  Tutte le informazioni si riferiscono alla data d'inizio e alla data di fine, scelte dall'utente. Nel caso in cui la città di Ancona e il suo Paese non siano scritti correttamente, riceverete un messaggio di errore.

Eccone un esempio:

![FilterPerWeek](https://i.ibb.co/yk6CJ7S/Schermata-2022-01-13-alle-12-49-28.png)
# Eccezioni
Le eccezioni vengono gestite tramite due classi:
 - WrongCityException: controlla che la città e il Paese
   inseriti dall'utente siano corrette, altrimenti stamperà a video il
   seguente messaggio: *"ERR: Città o Paese inseriti non corretti"*.

![WrongCityException](https://i.ibb.co/cbn4w71/Schermata-2022-01-14-alle-11-29-29.png)
 - WrongFileException: controlla che il path del file sia corretto, 
   altrimenti stamperà a video il seguente messaggio: *"ERR: File non esistente"*.

![WrongFileException](https://i.ibb.co/cQCfWGJ/Schermata-2022-01-14-alle-11-28-26.png)
# Struttura del progetto
Di seguito illustriamo la struttura del nostro progetto:


	PROGETTO-OOP-SANTARELLI-RECINELLI
	├── .mvn/wrapper
		├── MavenWrapperDownloader.java
		├── maven-wrapper.jar
		└── maven-wrapper.properties
	├── .settings
		├── org.eclipse.core.resources.prefs
		├── org.eclipse.jdt.core.prefs
		├── org.eclipse.m2e.core.prefs
		└── org.springframework.ide.eclipse.prefs
    ├── src
       ├── main
       │   ├── java
       │   │   ├── it.univpm.SantarelliRecinelli
       │   │   │        └──  ProvaSantarelliRecinelliApplication.java
	   |   |   ├── it.univpm.SantarelliRecinelli.controller
       │   │   │    	├── Controller.java
       │   │   │    	└── package-info.java
       │   │   ├── it.univpm.SantarelliRecinelli.exception
       │   │   │   		├── WrongCityException.java
       │   │   │   		└── WrongFileException.java
       │   │   ├── it.univpm.SantarelliRecinelli.model
       │   │   │   		├── City.java
       │   │   │   		├── Weather.java
       │   │   │   		├── WeatherStats.java
       │   │   │   		├── Wind.java
       │   │   │   		└── package-info.java
       │   │   ├── it.univpm.SantarelliRecinelli.service
       │   │   │   		├── APICall.java
       │   │   │   		└── CityReader.jav
       │   │   ├── it.univpm.SantarelliRecinelli.stats
       │   │   |   		├── FilterStats.java
       │   │   |   		└── package-info.java
       |   |   └── it.univpm.SantarelliRecinelli.timer
       │   │   		    ├── MyTimer.java
       |   |            └── package-info.java
       │   └── resources
	   |	   ├── APIForecastAncona.txt
	   |       ├── APIForecastEveryHour.txt
       │       └── application.properties
       └── test
            └── java
	            ├── it.univpm.SantarelliRecinelli
                      └── ProvaSantarelliRecinelliApplicationTests.java		
				├── it.univpm.SantarelliRecinelli.stats
					  └── FilterStatsTest.java	
				└──	it.univpm.SantarelliRecinelli.timer
				      └── MyTimerTest.java	
	├── .classpath
	├── .gitignore
	├── .project
	├── HELP.md
	├── README.md 
	├── mvnw
    ├── mvnw.cmd
    └── pom.xml                          
   
  

# Test
Le classi per i test realizzate nel nostro progetto sono le seguenti:
    
 - MyTimerTest: esegue il test dei metodi della classe MyTimer.
    Se il test va a buon fine, viene stampato:
    
    ![MyTimerTest](https://i.ibb.co/x2prC2S/Schermata-2022-01-14-alle-11-49-00.png)
 - FilterStatsTest: esegue il test dei metodi della classe FilterStats.
    Se il test va a buon fine, viene stampato:

![FilterStatsTest](https://i.ibb.co/Wk06rj1/Schermata-2022-01-14-alle-11-40-31.png)

# Autori

Il progetto è stato realizzato da: 

 - Simone Recinelli
 - Diego Santarelli
