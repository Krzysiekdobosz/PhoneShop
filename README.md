.
# Phone Store

## Opis projektu

Phone Store to aplikacja webowa służąca do zarządzania sklepem z telefonami. Umożliwia przeglądanie dostępnych modeli telefonów, dodawanie nowych produktów, zarządzanie zamówieniami oraz przeglądanie historii zakupów.

## Funkcje

- **Przeglądanie produktów**: Użytkownicy mogą przeglądać dostępne modele telefonów.
- **Dodawanie produktów**: Administratorzy mogą dodawać nowe modele telefonów do sklepu.
- **Zarządzanie zamówieniami**: Użytkownicy mogą składać zamówienia, a administratorzy mogą nimi zarządzać.
- **Historia zakupów**: Użytkownicy mogą przeglądać historię swoich zakupów.

## Jak uruchomić projekt

### Wymagania

- Java 11 lub nowsza
- Maven 3.6.3 lub nowszy
- Node.js 14 lub nowszy
- npm 6 lub nowszy

### Kroki

1. Sklonuj repozytorium:
    ```sh
    git clone https://github.com/Krzysiekdobosz/PhoneShop
    cd phone-store
    ```

2. Zbuduj backend:
    ```sh
    ./mvnw clean install
    ```

3. Zainstaluj zależności frontendowe:
    ```sh
    npm install
    ```

4. Uruchom aplikację:
    ```sh
    ./mvnw spring-boot:run
    ```

5. Otwórz przeglądarkę i przejdź do `http://localhost:8080`.

## Struktura projektu

- `src/main/java`: Kod źródłowy backendu.
- `src/main/resources`: Zasoby aplikacji (np. pliki konfiguracyjne).
- `src/test/java`: Testy jednostkowe.
- `target`: Katalog zbudowanych artefaktów.

## Autorzy

- Krzysztof Dobosz
- Dominik Bartoszuk

## Licencja

Ten projekt jest licencjonowany na warunkach licencji MIT.