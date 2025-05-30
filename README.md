# AOOP Java VenueManager - WORK IN PROGRESS

Programare avansata pe obiecte - proiect 
Fiecare student va lucra la un proiect individual. Proiectul este structurat în mai multe etape. 
Condiția de punctare a proiectelor: 

• să nu prezinte erori de compilare 
• să se implementeze cerințele date 

Termene de predare: 
• Etapa I: săptămâna 14 – 18 aprilie
• Etapa II: săptămâna 2 – 6 iunie

Etapa I 
1) Definirea sistemului 
Să se creeze o listă pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul 
sistemului și o listă cu cel puțin 8 tipuri de obiecte. 
2) Implementare 
Să se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct. 
Aplicația va conține: 
• clase simple cu atribute private / protected și metode de acces 
• cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (eg: List, Set, Map, 
etc.) dintre care cel puțin una sa fie sortată – se vor folosi array-uri uni/bidimensionale în cazul în care 
nu se parcurg colecțiile pana la data checkpoint-ului. 
• utilizare moștenire pentru crearea de clase adiționale și utilizarea lor în cadrul colecțiilor; 
• cel puțin o clasă serviciu care să expună operațiile sistemului 
• o clasa Main din care sunt făcute apeluri către servicii

Etapa II 
1) Extindeți proiectul din prima etapă prin realizarea persistenței utilizând o bază de date relațională
și JDBC. 
Să se realizeze servicii care sa expună operații de tip create, read, update și delete pentru cel puțin 4 
dintre clasele definite. Se vor realiza servicii singleton generice pentru scrierea și citirea din baza de 
date.
2) Realizarea unui serviciu de audit 
Se va realiza un serviciu care să scrie într-un fișier de tip CSV de fiecare dată când este executată una 
dintre acțiunile descrise în prima etapă. Structura fișierului: nume_actiune, timestamp
