# Logica di Business
	- Scegliere tipo di implementazione per tipologia metodi di pagamento/luoghi
	- Scegliere quali variabili inizializzare in addConcerto e quali in updateMetodo
	
# Eccezioni
	- Correggere eccezione per le date (un numero che inizia con 0, non viene considerato valido)
	- aggiungere eccezione per gestire nella finestra di aggiunta concerto, l'evento di cliccare su aggiungi
		senza aver selezionato un luogo, oppure rendere il bottone non cliccabile.
		
# JavaFX
### Realizzare altre viste
		
### Metodi
	- Sistemare graficamente label per data di scadenza non corretta
	
### Concerti
	- Verificare perché non vengono viusalizzati nella lista i concerti creati 
	- Creare finestra infoConcerto
	- Sistemare graficamente label per data concerto non corretta
	** trovare un modo per convertire la stringa del luogo "id,tipo,nome,citta" nel corrispondete luogo
		e farlo anche con il tuor, nei metodi getTour e getLuogo dentro a concerto
	** aggiungere label di errore e completare l'updateSettoreAction e in generale il ModificaConcertiController