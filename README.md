# TicTacToe-Online
Student: Maciej Łuczak
Indeks: 145366
Grupa: I6.2 
Semest: V 2021/2022

1. Opis projektu z przedmiotu Sieci Komputerowe.
Projekt przedstawia implementacje dwuosobowej gry logicznej zwanej "kółko i krzyżyk" opartej na module sieciowej rozgrywki.
Interface gry (GUI), klient TCP oraz mechanizm rozpoznawania stanów gry, został napisany za pomocą języka JAVA, natomiast serwer wykorzystuje język programowania C++.


2. Opis protokołu komunikacyjnego.

    ## Serwer: 
   Zaimplementowany protokół sieciowy odpowiada modelowi serwer-klient TCP. Serwer korzysta z adresu IP komputera na którym jest uruchamiany z portem o kodzie 1234.
   Port serwera został zapisany w kodzie gry, zarówno po stronie serwera jak i klienta, natomiast klient podczas korzystania z modułu sieciowego,
   zostanie poproszony o podanie właściwego adresu IP serwera na który chce się podłączyć. Inicjacja serwera zaczyna się od bezargumentowego uruchomienia programu z poziomu terminala linuxa,
   od tego momentu serwer nasłuchuje, czekając na komunikat ze stronie klienta o chęci dołączenia do rozgrywki. Po wysłaniu automatycznego sygnału do serwera przez obu klientów, serwer dobiera oczekujących na rozgrywkę graczy do siebie przy czym rozporządza o symbolu danego gracza (o lub x) na podstawie chronologii połączenia z serwerem
   Po załączeniu rozgrywki dla obu graczy serwer przełącza się do nasłuchiwania komunikatów ze strony klientów. Na podstawie turowego schematu gry, gracz o symbolu "O" rozpoczyna rozgrywkę, komunikując serwer o wybranym przez siebie 1 z 9 możliwych pól. 
   Sygnał trafia do oponenta, klient przechwytuje wiadomość i przesyła ją do wbudowanego menagera rozgrywki który implementuje ruch gracza w czasie rzeczywistym na interaktywnym polu gry 3x3.
   Rozrgrywka jest kontynuowała w tym stanie aż nie zostanie rozpoznany zwięzca (bądź też rozpoznany stan remisu). Po zaprezentowaniu wników rozgrywki, serwer automatycznie zwalnia miejsce rozgrywających tym samym pozwalając na przejście do menu głównego.
    

    ## Klient:
    Po wprowadzeniu w interfejsie gry informacji o adresie serwera, wpisany adres jest poddany weryfikacji oraz sprawdzeniu czy istnieje z nim połączenie, kiedy system uzna połączenie za bezpieczne  następuje powiązanie sygnału z serwerem oraz wysłanie wiadomości przez klienta o gotowości do gry oczekując na przeciwnika. 

3. Opis wybranej zawartości projektu.

• `ServerTCP.cpp` - implementacja serwera w języku c+

Zawartość folderu TicTacToe-Online:

• image - folder zawierający graficzne uzupełnienie interejsu gry

• `ClientTCP.java` - Klasa implementująca model klienta

• `HomePage.java` - Klasa strony startowej gry

• `MainScreen.java` - Klasa rozszerzająca JFrame, Inicjalizuje strukture stron całego programu za pomocą CardLayout'u

• `GameBoard.java` -  Klasa implementująca "Runnable Interface", Inicjalizuje strukturę gry podczas rozgrywki budując pole gry 3x3 z pozostałych zaimplemntowanych komponentów. Zawiera system wykrywania stanów gry
• `GameBuffor.java` - Klasa implementująca menadżera gry, umozliwia komunikacje pomiędzy klasą klienta a pozostałymi komponentami, monitoruje właściwa pracą programu oraz pośrodeniczy podczas zarządzania zasobami.
• `GameField.java` - Klasa implementująca pojedyńcze pole na planszy (GameBoard) podczas rozgrywki. Zapomocą MouseListenera, przechwytuje sygnały gracza i wczasie rzeczywistym implementuje sygnały na responsywnym GUI gry.
...
Pozostałe klasy implementują strony bądź podstrony, pełnią funkcję wspomagającą pracę GUI bądź są rozbudowane o animacje, grafikę wyłącznie w celach estetycznych (nie mają wpływu na komunikację sieciową gry).

Serwer został napisany i testowany za pomoca VirtualBox'a na systemie Ubuntu 20.04 gdzie pomyślnie przeszedł wszelkie testy związane z połączeniem serwera z klientem systemu Windows10.

Kompilacja serwera: `g++ ServerTCP.cpp -o server -lpthread`
Uruchomienie serwera: `./server`
Uruchomienie Klienta: `Plik Main.java`

DODATKOWE ZAŁOŻENIE: `uruchamianie GUI powinno się odbyć przez wczytanie projektu o ścieżce "\TicTacToe-Online\src\com\company\..." wcelu poprawnym załadowaniu grafik do GUI`
