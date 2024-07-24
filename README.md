**Microservizio di generazione di reports**

Il presente microservizio è stato sviluppato con Spring Boot, utilizzando l'architettura Maven e Spring MVC. Il microservizio si connette a altri microservizi mediante chiamate API con RestTemplate per recuperare i vari dati.

**Funzionalità**

Il microservizio è in grado di generare report relativi a singoli oggetti o a tutti gli oggetti di una certa tipologia. Ecco alcuni esempi di endpoint disponibili:

* api/reports/{type}/{id}: Genera un report relativo a un oggetto specifico con id {id} della categoria {type}.
* api/reports/{type}: Genera un report relativo a tutti gli oggetti di tipo {type}.

**Requisiti per l'utilizzo**

Per utilizzare il presente microservizio è necessario autenticarsi mediante il microservizio di autenticazione e ottenere un token di accesso. Il token di accesso deve essere inserito nell'header della richiesta come Bearer Token.

**Struttura del progetto**

Il progetto è strutturato in base alle best practice Spring Boot, con il seguente layout:

* `src/main/java`: contiene la logica di business e i bean del microservizio
* `src/main/resources`: contiene i file di configurazione e le risorse del microservizio
* `pom.xml`: file di configurazione Maven per il progetto

**Come utilizzare il microservizio**

1. Autenticati con il microservizio di autenticazione e ottieni un token di accesso.
2. Inserisci il token di accesso nell'header della richiesta come Bearer Token.
3. Utilizza l'endpoint di generazione di report specifico per il tipo di oggetto che si vuole generare il report.


**Credits**

Il presente microservizio è rilasciato da Antonio Esposito seguito da EMM-Systems-Consuling
