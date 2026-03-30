# AskOmDch Testing

Automatizovani test projekat za e-commerce sajt [askomdch.com](https://askomdch.com), razvijen u Javi koristeći Selenium WebDriver i JUnit 5.

---

## Tehnologije

- **Java 21**
- **Selenium WebDriver 4.18.1**
- **WebDriverManager 5.7.0** — automatsko upravljanje ChromeDriver-om
- **JUnit 5 (Jupiter) 5.10.2**
- **Maven** — build i dependency management

---

## Struktura projekta

```
AskOmDchTesting/
├── src/
│   ├── main/java/com/askomdch/
│   │   ├── pages/              # Page Object klase
│   │   │   ├── BasePage.java
│   │   │   ├── AccountPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── LoginPage.java
│   │   │   └── RegisterPage.java
│   │   └── utils/
│   │       └── LoggerUtil.java # Utility za logovanje rezultata
│   └── test/java/com/askomdch/
│       └── tests/              # Test klase
├── test-report.txt             # Izlazni izveštaj posle pokretanja svih testova
└── pom.xml
```

---

## Test slučajevi

| Test klasa | Opis |
|---|---|
| `RegistrationTest` | Registracija novog korisnika |
| `LoginTest` | Prijava registrovanog korisnika |
| `LogoutTest` | Odjavljivanje sa naloga |
| `ProfileDataTest` | Provera da li podaci profila odgovaraju podacima registracije |
| `AddToCartTest` | Dodavanje 3 proizvoda iz različitih kategorija (Men, Women, Accessories) |
| `CartPriceTest` | Provera ispravnosti ukupne cene u korpi |
| `EmptyCartTest` | Uklanjanje svih proizvoda iz korpe i provera prazne korpe |
| `SearchTest` | Pretraga proizvoda po ključnoj reči |
| `SortByPriceTest` | Sortiranje proizvoda po ceni (uzlazno) u kategoriji Men |
| `PageLoadPerformanceTest` | Merenje vremena učitavanja 5 stranica |
| `CompanyInfoTest` | Prikupljanje podataka o kompaniji sa About stranice |

Svi testovi se mogu pokrenuti zajedno preko `DemoSuiteTest` suite klase.

---

## Preduslovi

- Java 21+
- Maven 3.6+
- Google Chrome browser (ChromeDriver se preuzima automatski)
- Aktivan internet konekcija (testovi se izvršavaju na live sajtu)

---

## Pokretanje testova

### Svi testovi
```bash
mvn test
```

### Jedan test
```bash
mvn test -Dtest=LoginTest
```

### Cela test suite
```bash
mvn test -Dtest=DemoSuiteTest
```

---

## Test podaci

Testovi koriste unapred definisanog test korisnika:

| Polje | Vrednost |
|---|---|
| Username | `testuser123` |
| Email | `testuser123@gmail.com` |
| Password | `Test1234!` |

> **Napomena:** `RegistrationTest` kreira ovog korisnika. Ako korisnik već postoji, test će pasti. Pre ponovnog pokretanja svih testova, korisnik treba biti obrisan sa sajta.

---

## Logovanje

Klasa `LoggerUtil` zapisuje rezultate svakog testa u fajl `test-report.txt` u root direktorijumu projekta. Format izveštaja:

```
[PASS] LoginTest — Prijava korisnika: testuser123
[FAIL] SomeTest  — Opis testa
[INFO] Dodatne informacije o testu
```
