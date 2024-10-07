# Company Management Service

## Opis projektu

**Company Management Service** to prosty mikroserwis stworzony w Javie z użyciem frameworka Spring Boot. Aplikacja umożliwia zarządzanie informacjami o firmach, ich działach, zespołach, projektach oraz menedżerach. Użytkownicy mogą wykonywać operacje CRUD (tworzenie, odczyt, aktualizacja, usuwanie) na tych obiektach za pomocą RESTful API.

## Funkcjonalności

- **Zarządzanie firmami**: Dodawanie, edytowanie, usuwanie i wyświetlanie informacji o firmach.
- **Zarządzanie działami**: Każda firma może mieć wiele działów, które można zarządzać w podobny sposób.
- **Zarządzanie zespołami**: Każdy dział może mieć wiele zespołów.
- **Zarządzanie projektami**: Każdy zespół może mieć przypisane projekty, które również można edytować.
- **Zarządzanie menedżerami**: Projekty mogą mieć przypisanych menedżerów z danymi kontaktowymi.

## Technologie

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Maven**
- **MapStruct**
- **Docker**
- **Junit 5**
- **Mockito 5**

## Instalacja

Aby uruchomić aplikację lokalnie, wykonaj następujące kroki:

1. **Klonowanie repozytorium**:

   ```bash
   git clone https://github.com/yourusername/company-management-service.git
   cd company-management-service

2. **Instalacja PostgreSQL**:

    Upewnij się, że masz zainstalowany PostgreSQL na swoim komputerze. Możesz pobrać go z oficjalnej strony PostgreSQL.


3. **Utworzenie bazy danych**:

    Zaloguj się do PostgreSQL i utwórz bazę danych company_db:

   ```bash
   CREATE DATABASE company_db;
   ```

4. **Skonfiguruj plik application.properties**:

    Upewnij się, że plik src/main/resources/application.properties zawiera poprawne dane do połączenia z bazą danych:

   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/company_db
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. **Budowanie projektu**:

    Użyj Mavena, aby zbudować projekt:

   ```bash
   mvn clean install
   ```

## Uruchamianie aplikacji

Możesz uruchomić aplikację lokalnie za pomocą następującego polecenia:

   ```bash
   mvn spring-boot:run
   ```

Aplikacja domyślnie działa na porcie 8080.

## Uruchamianie aplikacji z użyciem Dockera

1. **Zainstaluj Docker**:
   - Upewnij się, że masz zainstalowany Docker Desktop na swoim komputerze. Możesz pobrać go z [oficjalnej strony Docker](https://www.docker.com/products/docker-desktop).

2. **Włącz WSL 2** (Windows Subsystem for Linux):
   - W przypadku systemu Windows 10 lub nowszego, włącz WSL 2. Możesz to zrobić, uruchamiając PowerShell jako administrator i wpisując:
     ```bash
     wsl --install
     ```
   - Upewnij się, że wirtualizacja jest włączona w BIOS oraz w systemie operacyjnym (w „Funkcjach systemu Windows”).

3. **Skonfiguruj plik `docker-compose.yml`**:
   - Upewnij się, że plik `docker-compose.yml` w głównym katalogu projektu jest poprawnie skonfigurowany, aby zawierał informacje o serwisach, takich jak PostgreSQL i aplikacja.

4. **Zbuduj obrazy Docker**:
   - Otwórz terminal w katalogu, w którym znajduje się plik `docker-compose.yml`.
   - Wpisz następujące polecenie, aby zbudować obrazy Docker:
     ```bash
     docker-compose up --build
     ```

5. **Uruchom serwisy**:
   - Po zakończeniu budowania obrazów, Docker uruchomi wszystkie zdefiniowane serwisy. Aplikacja powinna być dostępna pod adresem `http://localhost:8080`.

6. **Zarządzanie kontenerami**:
   - Możesz zatrzymać kontenery, używając polecenia:
     ```bash
     docker-compose down
     ```

Wszystkie zmiany wprowadzone w projekcie muszą być ponownie zbudowane w kontenerze, aby zostały odzwierciedlone. Użyj polecenia `docker-compose up --build`, aby zbudować kontenery z najnowszymi zmianami.


### Uwaga

- Zastąp `yourusername` w linku do repozytorium oraz `yourpassword` w sekcji konfiguracji odpowiednimi wartościami.
- Aby przetestować API, możesz użyć narzędzi takich jak Postman.
- W projekt przetestowano z użyciem testó jednostkowych, które dały 100% pokrycia kodu oraz z użyciem testów integracyjnych.
