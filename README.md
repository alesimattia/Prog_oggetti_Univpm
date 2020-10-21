# COVID19stats
Progetto per l'esame di Programmazione ad Oggetti - Ottobre 2020.
Repository: https://github.com/alesimattia/Prog_oggetti_Univpm

**Autori:** 
- Alesi Mattia - s1078873
- Incipini Marco - s

**Descrizione**
Il progetto ha lo scopo di analizzare e fornire delle statistiche sull'epidemia Coronavirus-19.
Le informazioni sono recuperate tramite una chiamata all'Api REST https://covid19-api.org/  che restituisce un dataset in formato Json.
Successivamente, i dati che sono in formato testuale, vengono modellati in un'apposita classe per poi essere manipolati e calcolare delle statistiche da restituire all'utente.  

---
## Diagramma delle Classi
![diag_classi](/diagramma_classi.png)

---
## Diagramma dei Casi d'Uso
![diag_casi_uso](/diagramma_casi_uso.png)

---
## Struttura del progetto e utilizzo
### Dataset
Ogni chiamata all'Api covid19-api.org restituisce un dataset con i seguenti campi:

- *Country*: nome dello stato di cui si vogliono ottenere informazioni -> corrisponde al campo *Slug* 
- *CountryCode*: codice univoco della nazione.
- *Cases*: numero di elementi per una determinata richiesta definita in &nbsp; *Status*
- *Status*: tipo di richiesta &nbsp; `[ contagi | guariti | decessi ]`
- *Date*: data a cui si riferisce il numero di casi mostrato sopra.

---
### Chiamate HTTP
Appena si avvia l'applicazione viene mostrato un riassunto della situazione attuale mondiale (GET).

**Tutte le richieste HTTP sono di tipo POST ed è quindi necessario specificare dei parametri nel RequestBody della richiesta.**
\
Si invia una richiesta personalizzata tramite l'url:  http://localhost:8080/{tipo}
dove il parametro ***tipo*** può essere tra i seguenti:
`[ contagi | guariti | decessi ]`
\
Riportiamo un esempio di body delle richieste, facendo notare che 
<u>è possibile aggiungere più paesi inserendoli in un vettore</u> come nel punto **1** :

**1.**  &nbsp; *Visualizzazione dei giorni con max e min di un {tipo} di dato per uno o più paesi:*	
>{ 
> "Country": "Gibraltar",
> "Slug": "gibraltar",
> "ISO2": "GI"
> }`

**Nota:** il campo *Slug* corrisponde ad una *"chiave primaria"* per l'Api covid19-api.org ed è quindi l'elemento con cui si selezionano effettivamente i paesi.
\
\
**2.**  &nbsp; *Filtraggio dei dati in base a determinati parametri:*
Si aggiunge nel RequestBody della richiesta, oltre ai paesi di interesse, anche dei parametri per ottenere il dominio dei dati desiderato.

>{
> "percentuale":  bool,
>"valoremax": int,
>"valoremin": int,
>"datamax": gg/mm/aaaa,
>"datamin": gg/mm/aaaa
>}
##### Descrizione dei parametri
* *{percentuale}* consente di mostrare i dati sotto forma di incremento percentuale invece che numero intero cumulativo.
* *{valoremax / valoremin}*: restrizione sul numero di casi ( cioè *{tipo}* ) ammissibili.
* *{valoremax / valoremin}*: restrizione sulla finestra temporale

**Nota:** settando un parametro a a *`null`* si disattiva il suo effetto.

---
### Statistiche
Per effettuare uno dei calcoli statistici si invia la richiesta utilizzando l'apposita url: http://localhost:8080/{tipo}/{statistica}
dove il parametro *statistica* può essere uno tra i seguenti:
`[ somma | media | max | min | contaGiorni ]`

In particolare:
- max/min: restituisce il paese (tra quelli elencati) con il numero massimo/minimo dell'attributo *{tipo} &nbsp; (contagi/guariti/decessi)*
- contaGiorni: restituisce il numero di giorni di cui si hanno informazioni per ogni paese. 
In altre parole, il numero di "record" (giorni) per ogni paese.

Il RequestBody va formattato come nei casi precedenti ed <u>è quindi possibile applicare dei filtri anche su tali dati</u>.
