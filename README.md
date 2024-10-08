# Kebapp - interaktywna mapa kebabów w Legnicy

![GitHub commit activity](https://img.shields.io/github/commit-activity/w/Pawel-Wyszynski/Kebapp-android)
![GitHub last commit](https://img.shields.io/github/last-commit/Pawel-Wyszynski/Kebapp-android)
![GitHub Created At](https://img.shields.io/github/created-at/Pawel-Wyszynski/Kebapp-android)
![GitHub top language](https://img.shields.io/github/languages/top/Pawel-Wyszynski/Kebapp-android)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues/Pawel-Wyszynski/Kebapp-android)
![GitHub Issues or Pull Requests](https://img.shields.io/github/issues-closed/Pawel-Wyszynski/Kebapp-android?color=%23008000)


<img src="https://i.imgur.com/e6Aom8n.jpeg" alt="Description" width="200"/>

# Wymagania funkcjonalne

## 1. Użytkownik może pobrać aplikację na systemy Android
## 2. Użytkownik aplikacji mobilnej może zobaczyć listę legnickich kebabów
- 2.1. Lista w formie tabeli jest paginowana, sortowalna i filtrowalna
- 2.2. Lista w formie mapy jest interaktywna
- 2.3. Kliknięcie na miejsce (zarówno na liście, jak i na mapie) otwiera informacje
szczegółowe o danym miejscu

## 3. Lista obejmuje kebaby obecnie działające, zamknięte oraz planowane
## 4. Każdy kebab powinien mieć przypisane informacje podstawowe:
- 4.1. nazwę
- 4.2. logo
- 4.3. adres
- 4.4. koordynaty geograficzne
- 4.5. rok otwarcia i zamknięcia (jeżeli informacje są znane)
## 5. Każdy kebab powinien mieć przypisane informacje dodatkowe:
- 5.1. godziny otwarcia w poszczególnych dniach tygodnia
- 5.2. dostępne rodzaje mięs
- 5.3. dostępne sosy
- 5.4. informację nt. statusu (punkt 3 OPZ)
- 5.5. informację czy kebab jest "kraftowy"
- 5.6. informację czy kebab jest ulokowany w nieruchomości, czy w "budzie"
- 5.7. informację czy kebab jest oddziałem sieci kebabów
- 5.8. informację jak można zamówić kebab (telefon, Pyszne, Glovo, Uber Eats,
własna aplikacja/strona)

## 6. Informacje z punktów 5.1-5.8 OPZ powinny być bazą dla filtrów z punktu 2.1
## 7. Informacje z punktów 4.1, 4.5, 8 OPZ powinny być bazą dla sortowań z punktu 2.1
## 8. Każdy kebab powinien mieć przypisane oceny z Google i Pyszne.pl, jeżeli mają tam konta
- 8.1. Informacje te są pobierane z właściwych serwisów przynajmniej raz dziennie
## 9. Każdy kebab powinien mieć przypisane linki do social media (Facebook, Instagram, własna strona www)
## 10. Użytkownik widzi liczbę kebabów obecnych na mapie w aplikacji mobilnej
## 11. Użytkownik może zalogować się w aplikacji mobilnej
- 11.1. Zalogowany użytkownik może dodawać kebaby do ulubionych
- 11.2. Zalogowany użytkownik może komentować kebaby
- 11.3. Zalogowany użytkownik może przesyłać administratorowi sugestię dotyczącą zmian danych
    - 11.3.1 Sugestia wysyłana jest w formie tekstu, który pełni rolę sugestii bądź prośby
    - 11.3.2 Admin może wyświetlić wszystkie sugestie
    - 11.3.3 Admin może zaakceptować sugestię, bądź ją usunąć
    - 11.3.4 Zaakceptowana sugestia zostaje automatycznie usunięta po 5 dniach od akceptacji
## 12. Administrator zarządza bazą kebabów poprzez dedykowany panel administracyjny
### 12.1. Konto administratora tworzone jest podczas instalacji systemu
### 12.2. Przy pierwszym logowaniu administrator musi stworzyć sobie hasło
## 13. Administrator widzi listę zgłoszonych sugestii i akceptuje je lub odrzuca
## 14. Aplikacja mobilna korzysta z REST API panelu administracyjnego, które jest opisane przez dokumentację OpenAPI wystawioną w endpoincie /api/documentation
## 15. System posiada zmigrowane dane nt. legnickich kebabów
