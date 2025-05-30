package DatabaseConnection;
import Services.AuditService;

public class DatabaseInitialization extends ConnectionString {

    static AuditService auditService = new AuditService();

    public static String createDatabase = "CREATE DATABASE IF NOT EXISTS VENUE";

    public static String createVenueTable = "CREATE  TABLE IF NOT EXISTS Venue (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "name VARCHAR(50) NOT NULL,\n"
            + "address VARCHAR(50) NOT NULL\n"
            + ");";

    public static String createEventTable = "CREATE TABLE IF NOT EXISTS Event (\n"
            + "id INTEGER PRIMARY KEY ,\n"
            + "name VARCHAR(50) NOT NULL,\n"
            + "type VARCHAR(50) NOT NULL,\n"
            + "description VARCHAR(50) NOT NULL,\n"
            + "date DATE NOT NULL\n"
            + ");";

    public static String createEventTicketTable = "CREATE TABLE IF NOT EXISTS EventTicket (\n"
            + "eventId INTEGER,\n"
            + "ticketId INTEGER,\n"
            + "CONSTRAINT eventTicketId PRIMARY KEY (eventId, ticketId),\n"
            + "FOREIGN KEY (eventId) REFERENCES Event(id),\n"
            + "FOREIGN KEY (ticketId) REFERENCES Ticket(id)\n"
            + ");";

    public static String createArtistTable = "CREATE  TABLE IF NOT EXISTS Artist (\n"
            + "id INTEGER PRIMARY KEY ,\n"
            + "name VARCHAR(50) NOT NULL,\n"
            + "email VARCHAR(50) NOT NULL,\n"
            + "eventId INTEGER NOT NULL,\n "
            + "FOREIGN KEY (eventId) REFERENCES Event(id)\n"
            + ");";

    public static String createTicketTable = "CREATE  TABLE IF NOT EXISTS Ticket (\n"
            + "id INTEGER PRIMARY KEY ,\n"
            + "seatNumber INTEGER NOT NULL,\n"
            + "zone CHARACTER NOT NULL,\n"
            + "CONSTRAINT UniqueTicketId UNIQUE (seatNumber, zone)\n "
            + ");";

    public static String createStaffTable = "CREATE TABLE IF NOT EXISTS Staff (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "name VARCHAR(50) NOT NULL,\n"
            + "department VARCHAR(50) NOT NULL,\n"
            + "salary DOUBLE NOT NULL\n"
            + ");";

    static{
        try{
            c.createStatement().executeUpdate(createDatabase);
            auditService.logAction("Created or checked database Venue");
            c.createStatement().executeUpdate(createVenueTable);
            auditService.logAction("Created or checked Venue Table");
            c.createStatement().executeUpdate(createEventTable);
            auditService.logAction("Created or checked Event Table");
            c.createStatement().executeUpdate(createArtistTable);
            auditService.logAction("Created or checked  Artist Table");
            c.createStatement().executeUpdate(createTicketTable);
            auditService.logAction("Created or checked Ticket Table");
            c.createStatement().executeUpdate(createEventTicketTable);
            auditService.logAction("Created or checked Event Ticket Table");
            c.createStatement().executeUpdate(createStaffTable);
            auditService.logAction("Created or checked Staff Table");
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }



    //apelate doar odata! insert urile initiale FARA STAFF
    /*
    public static String insertVenueTable = "INSERT INTO Venue VALUES "
            + "(1, 'La Maroquinerie', '23 rue Boyer 75020 Paris');\n";

    public static String insertEventTable1 = "INSERT INTO Event VALUES " +
            "(1, 'TWRP Concert', 'Funk Rock', 'The Longest Weekend European DLC Tour', DATE('2026-02-07'));\n";

    public static String insertEventTable2 = "INSERT INTO Event VALUES " +
            "(2, 'Ashnikko Live', 'Alt Pop', 'IttyBitty World Tour', DATE('2026-04-10'));\n";

    public static String insertEventTable3 = "INSERT INTO Event VALUES " +
            "(3, 'Will Wood Concert', 'Alternative Jazz', 'Front Street 10th Anniversary Tour', DATE('2025-10-05'));\n";

    public static String insertEventTable4 = "INSERT INTO Event VALUES " +
            "(4, 'TDeath Spells', 'Digital Hardcore', 'Summer 2026 Reunion', DATE('2026-07-29'));\n";


    public static String insertArtistTable1 = "INSERT INTO Artist VALUES " +
            "(1, 'Doctor Sung', 'doctorsung@twrp.ibs', 1);\n";

    public static String insertArtistTable2 = "INSERT INTO Artist VALUES " +
            "(2, 'Commander Meouch', 'commandermeouch@twrp.ibs', 1);\n";

    public static String insertArtistTable3 = "INSERT INTO Artist VALUES " +
            "(3, 'Lord Phobos', 'lordphobos@twrp.ibs', 1);\n";

    public static String insertArtistTable4 = "INSERT INTO Artist VALUES " +
            "(4, 'Havve Hogan', 'havvehogan@twrp.ibs', 1);\n";

    public static String insertArtistTable5 = "INSERT INTO Artist VALUES " +
            "(5, 'Ashnikko', 'ashnikko@ashnikko.com', 2);\n";

    public static String insertArtistTable6 = "INSERT INTO Artist VALUES " +
            "(6, 'Will Wood', 'willwood@willwood.com', 3);\n";

    public static String insertArtistTable7 = "INSERT INTO Artist VALUES " +
            "(7, 'Frank Iero', 'frankiero@deathspells.com', 4);\n";

    public static String insertArtistTable8 = "INSERT INTO Artist VALUES " +
            "(8, 'James Dewees', 'jamesdewees@deathspells.com', 4);\n";

    public static String insertTicketTable1 = "INSERT INTO Ticket VALUES (1, 1, 'V');\n";
    public static String insertTicketTable2 = "INSERT INTO Ticket VALUES (2, 2, 'V');\n";
    public static String insertTicketTable3 = "INSERT INTO Ticket VALUES (3, 3, 'V');\n";
    public static String insertTicketTable4 = "INSERT INTO Ticket VALUES (4, 4, 'V');\n";
    public static String insertTicketTable5 = "INSERT INTO Ticket VALUES (5, 5, 'V');\n";
    public static String insertTicketTable6 = "INSERT INTO Ticket VALUES (6, 6, 'V');\n";
    public static String insertTicketTable7 = "INSERT INTO Ticket VALUES (7, 7, 'V');\n";
    public static String insertTicketTable8 = "INSERT INTO Ticket VALUES (8, 8, 'V');\n";
    public static String insertTicketTable9 = "INSERT INTO Ticket VALUES (9, 9, 'V');\n";
    public static String insertTicketTable10 = "INSERT INTO Ticket VALUES (10, 10, 'V');\n";
    public static String insertTicketTable11 = "INSERT INTO Ticket VALUES (11, 11, 'V');\n";
    public static String insertTicketTable12 = "INSERT INTO Ticket VALUES (12, 12, 'V');\n";
    public static String insertTicketTable13 = "INSERT INTO Ticket VALUES (13, 13, 'V');\n";
    public static String insertTicketTable14 = "INSERT INTO Ticket VALUES (14, 14, 'V');\n";
    public static String insertTicketTable15 = "INSERT INTO Ticket VALUES (15, 15, 'V');\n";
    public static String insertTicketTable16 = "INSERT INTO Ticket VALUES (16, 16, 'V');\n";
    public static String insertTicketTable17 = "INSERT INTO Ticket VALUES (17, 17, 'V');\n";
    public static String insertTicketTable18 = "INSERT INTO Ticket VALUES (18, 18, 'V');\n";
    public static String insertTicketTable19 = "INSERT INTO Ticket VALUES (19, 19, 'V');\n";
    public static String insertTicketTable20 = "INSERT INTO Ticket VALUES (20, 20, 'V');\n";
    public static String insertTicketTable21 = "INSERT INTO Ticket VALUES (21, 21, 'V');\n";
    public static String insertTicketTable22 = "INSERT INTO Ticket VALUES (22, 22, 'V');\n";
    public static String insertTicketTable23 = "INSERT INTO Ticket VALUES (23, 23, 'V');\n";
    public static String insertTicketTable24 = "INSERT INTO Ticket VALUES (24, 24, 'V');\n";
    public static String insertTicketTable25 = "INSERT INTO Ticket VALUES (25, 25, 'V');\n";
    public static String insertTicketTable26 = "INSERT INTO Ticket VALUES (26, 26, 'V');\n";
    public static String insertTicketTable27 = "INSERT INTO Ticket VALUES (27, 27, 'V');\n";
    public static String insertTicketTable28 = "INSERT INTO Ticket VALUES (28, 28, 'V');\n";
    public static String insertTicketTable29 = "INSERT INTO Ticket VALUES (29, 29, 'V');\n";
    public static String insertTicketTable30 = "INSERT INTO Ticket VALUES (30, 30, 'V');\n";
    public static String insertTicketTable31 = "INSERT INTO Ticket VALUES (31, 1, 'A');\n";
    public static String insertTicketTable32 = "INSERT INTO Ticket VALUES (32, 2, 'A');\n";
    public static String insertTicketTable33 = "INSERT INTO Ticket VALUES (33, 3, 'A');\n";
    public static String insertTicketTable34 = "INSERT INTO Ticket VALUES (34, 4, 'A');\n";
    public static String insertTicketTable35 = "INSERT INTO Ticket VALUES (35, 5, 'A');\n";
    public static String insertTicketTable36 = "INSERT INTO Ticket VALUES (36, 6, 'A');\n";
    public static String insertTicketTable37 = "INSERT INTO Ticket VALUES (37, 7, 'A');\n";
    public static String insertTicketTable38 = "INSERT INTO Ticket VALUES (38, 8, 'A');\n";
    public static String insertTicketTable39 = "INSERT INTO Ticket VALUES (39, 9, 'A');\n";
    public static String insertTicketTable40 = "INSERT INTO Ticket VALUES (40, 10, 'A');\n";
    public static String insertTicketTable41 = "INSERT INTO Ticket VALUES (41, 11, 'A');\n";
    public static String insertTicketTable42 = "INSERT INTO Ticket VALUES (42, 12, 'A');\n";
    public static String insertTicketTable43 = "INSERT INTO Ticket VALUES (43, 13, 'A');\n";
    public static String insertTicketTable44 = "INSERT INTO Ticket VALUES (44, 14, 'A');\n";
    public static String insertTicketTable45 = "INSERT INTO Ticket VALUES (45, 15, 'A');\n";
    public static String insertTicketTable46 = "INSERT INTO Ticket VALUES (46, 16, 'A');\n";
    public static String insertTicketTable47 = "INSERT INTO Ticket VALUES (47, 17, 'A');\n";
    public static String insertTicketTable48 = "INSERT INTO Ticket VALUES (48, 18, 'A');\n";
    public static String insertTicketTable49 = "INSERT INTO Ticket VALUES (49, 19, 'A');\n";
    public static String insertTicketTable50 = "INSERT INTO Ticket VALUES (50, 20, 'A');\n";
    public static String insertTicketTable51 = "INSERT INTO Ticket VALUES (51, 21, 'A');\n";
    public static String insertTicketTable52 = "INSERT INTO Ticket VALUES (52, 22, 'A');\n";
    public static String insertTicketTable53 = "INSERT INTO Ticket VALUES (53, 23, 'A');\n";
    public static String insertTicketTable54 = "INSERT INTO Ticket VALUES (54, 24, 'A');\n";
    public static String insertTicketTable55 = "INSERT INTO Ticket VALUES (55, 25, 'A');\n";
    public static String insertTicketTable56 = "INSERT INTO Ticket VALUES (56, 26, 'A');\n";
    public static String insertTicketTable57 = "INSERT INTO Ticket VALUES (57, 27, 'A');\n";
    public static String insertTicketTable58 = "INSERT INTO Ticket VALUES (58, 28, 'A');\n";
    public static String insertTicketTable59 = "INSERT INTO Ticket VALUES (59, 29, 'A');\n";
    public static String insertTicketTable60 = "INSERT INTO Ticket VALUES (60, 30, 'A');\n";
    public static String insertTicketTable61 = "INSERT INTO Ticket VALUES (61, 31, 'A');\n";
    public static String insertTicketTable62 = "INSERT INTO Ticket VALUES (62, 32, 'A');\n";
    public static String insertTicketTable63 = "INSERT INTO Ticket VALUES (63, 33, 'A');\n";
    public static String insertTicketTable64 = "INSERT INTO Ticket VALUES (64, 34, 'A');\n";
    public static String insertTicketTable65 = "INSERT INTO Ticket VALUES (65, 35, 'A');\n";
    public static String insertTicketTable66 = "INSERT INTO Ticket VALUES (66, 36, 'A');\n";
    public static String insertTicketTable67 = "INSERT INTO Ticket VALUES (67, 37, 'A');\n";
    public static String insertTicketTable68 = "INSERT INTO Ticket VALUES (68, 38, 'A');\n";
    public static String insertTicketTable69 = "INSERT INTO Ticket VALUES (69, 39, 'A');\n";
    public static String insertTicketTable70 = "INSERT INTO Ticket VALUES (70, 40, 'A');\n";
    public static String insertTicketTable71 = "INSERT INTO Ticket VALUES (71, 41, 'A');\n";
    public static String insertTicketTable72 = "INSERT INTO Ticket VALUES (72, 42, 'A');\n";
    public static String insertTicketTable73 = "INSERT INTO Ticket VALUES (73, 43, 'A');\n";
    public static String insertTicketTable74 = "INSERT INTO Ticket VALUES (74, 44, 'A');\n";
    public static String insertTicketTable75 = "INSERT INTO Ticket VALUES (75, 45, 'A');\n";
    public static String insertTicketTable76 = "INSERT INTO Ticket VALUES (76, 46, 'A');\n";
    public static String insertTicketTable77 = "INSERT INTO Ticket VALUES (77, 47, 'A');\n";
    public static String insertTicketTable78 = "INSERT INTO Ticket VALUES (78, 48, 'A');\n";
    public static String insertTicketTable79 = "INSERT INTO Ticket VALUES (79, 49, 'A');\n";
    public static String insertTicketTable80 = "INSERT INTO Ticket VALUES (80, 50, 'A');\n";
    public static String insertTicketTable81 = "INSERT INTO Ticket VALUES (81, 51, 'A');\n";
    public static String insertTicketTable82 = "INSERT INTO Ticket VALUES (82, 52, 'A');\n";
    public static String insertTicketTable83 = "INSERT INTO Ticket VALUES (83, 53, 'A');\n";
    public static String insertTicketTable84 = "INSERT INTO Ticket VALUES (84, 54, 'A');\n";
    public static String insertTicketTable85 = "INSERT INTO Ticket VALUES (85, 55, 'A');\n";
    public static String insertTicketTable86 = "INSERT INTO Ticket VALUES (86, 56, 'A');\n";
    public static String insertTicketTable87 = "INSERT INTO Ticket VALUES (87, 57, 'A');\n";
    public static String insertTicketTable88 = "INSERT INTO Ticket VALUES (88, 58, 'A');\n";
    public static String insertTicketTable89 = "INSERT INTO Ticket VALUES (89, 59, 'A');\n";
    public static String insertTicketTable90 = "INSERT INTO Ticket VALUES (90, 60, 'A');\n";
    public static String insertTicketTable91 = "INSERT INTO Ticket VALUES (91, 61, 'A');\n";
    public static String insertTicketTable92 = "INSERT INTO Ticket VALUES (92, 62, 'A');\n";
    public static String insertTicketTable93 = "INSERT INTO Ticket VALUES (93, 63, 'A');\n";
    public static String insertTicketTable94 = "INSERT INTO Ticket VALUES (94, 64, 'A');\n";
    public static String insertTicketTable95 = "INSERT INTO Ticket VALUES (95, 65, 'A');\n";
    public static String insertTicketTable96 = "INSERT INTO Ticket VALUES (96, 66, 'A');\n";
    public static String insertTicketTable97 = "INSERT INTO Ticket VALUES (97, 67, 'A');\n";
    public static String insertTicketTable98 = "INSERT INTO Ticket VALUES (98, 68, 'A');\n";
    public static String insertTicketTable99 = "INSERT INTO Ticket VALUES (99, 69, 'A');\n";
    public static String insertTicketTable100 = "INSERT INTO Ticket VALUES (100, 70, 'A');\n";
    public static String insertTicketTable101 = "INSERT INTO Ticket VALUES (101, 71, 'A');\n";
    public static String insertTicketTable102 = "INSERT INTO Ticket VALUES (102, 72, 'A');\n";
    public static String insertTicketTable103 = "INSERT INTO Ticket VALUES (103, 73, 'A');\n";
    public static String insertTicketTable104 = "INSERT INTO Ticket VALUES (104, 74, 'A');\n";
    public static String insertTicketTable105 = "INSERT INTO Ticket VALUES (105, 75, 'A');\n";
    public static String insertTicketTable106 = "INSERT INTO Ticket VALUES (106, 76, 'A');\n";
    public static String insertTicketTable107 = "INSERT INTO Ticket VALUES (107, 77, 'A');\n";
    public static String insertTicketTable108 = "INSERT INTO Ticket VALUES (108, 78, 'A');\n";
    public static String insertTicketTable109 = "INSERT INTO Ticket VALUES (109, 79, 'A');\n";
    public static String insertTicketTable110 = "INSERT INTO Ticket VALUES (110, 80, 'A');\n";
    public static String insertTicketTable111 = "INSERT INTO Ticket VALUES (111, 81, 'A');\n";
    public static String insertTicketTable112 = "INSERT INTO Ticket VALUES (112, 82, 'A');\n";
    public static String insertTicketTable113 = "INSERT INTO Ticket VALUES (113, 83, 'A');\n";
    public static String insertTicketTable114 = "INSERT INTO Ticket VALUES (114, 84, 'A');\n";
    public static String insertTicketTable115 = "INSERT INTO Ticket VALUES (115, 85, 'A');\n";
    public static String insertTicketTable116 = "INSERT INTO Ticket VALUES (116, 86, 'A');\n";
    public static String insertTicketTable117 = "INSERT INTO Ticket VALUES (117, 87, 'A');\n";
    public static String insertTicketTable118 = "INSERT INTO Ticket VALUES (118, 88, 'A');\n";
    public static String insertTicketTable119 = "INSERT INTO Ticket VALUES (119, 89, 'A');\n";
    public static String insertTicketTable120 = "INSERT INTO Ticket VALUES (120, 90, 'A');\n";
    public static String insertTicketTable121 = "INSERT INTO Ticket VALUES (121, 91, 'A');\n";
    public static String insertTicketTable122 = "INSERT INTO Ticket VALUES (122, 92, 'A');\n";
    public static String insertTicketTable123 = "INSERT INTO Ticket VALUES (123, 93, 'A');\n";
    public static String insertTicketTable124 = "INSERT INTO Ticket VALUES (124, 94, 'A');\n";
    public static String insertTicketTable125 = "INSERT INTO Ticket VALUES (125, 95, 'A');\n";
    public static String insertTicketTable126 = "INSERT INTO Ticket VALUES (126, 96, 'A');\n";
    public static String insertTicketTable127 = "INSERT INTO Ticket VALUES (127, 97, 'A');\n";
    public static String insertTicketTable128 = "INSERT INTO Ticket VALUES (128, 98, 'A');\n";
    public static String insertTicketTable129 = "INSERT INTO Ticket VALUES (129, 99, 'A');\n";
    public static String insertTicketTable130 = "INSERT INTO Ticket VALUES (130, 100, 'A');\n";
    public static String insertTicketTable131 = "INSERT INTO Ticket VALUES (131, 1, 'B');\n";
    public static String insertTicketTable132 = "INSERT INTO Ticket VALUES (132, 2, 'B');\n";
    public static String insertTicketTable133 = "INSERT INTO Ticket VALUES (133, 3, 'B');\n";
    public static String insertTicketTable134 = "INSERT INTO Ticket VALUES (134, 4, 'B');\n";
    public static String insertTicketTable135 = "INSERT INTO Ticket VALUES (135, 5, 'B');\n";
    public static String insertTicketTable136 = "INSERT INTO Ticket VALUES (136, 6, 'B');\n";
    public static String insertTicketTable137 = "INSERT INTO Ticket VALUES (137, 7, 'B');\n";
    public static String insertTicketTable138 = "INSERT INTO Ticket VALUES (138, 8, 'B');\n";
    public static String insertTicketTable139 = "INSERT INTO Ticket VALUES (139, 9, 'B');\n";
    public static String insertTicketTable140 = "INSERT INTO Ticket VALUES (140, 10, 'B');\n";
    public static String insertTicketTable141 = "INSERT INTO Ticket VALUES (141, 11, 'B');\n";
    public static String insertTicketTable142 = "INSERT INTO Ticket VALUES (142, 12, 'B');\n";
    public static String insertTicketTable143 = "INSERT INTO Ticket VALUES (143, 13, 'B');\n";
    public static String insertTicketTable144 = "INSERT INTO Ticket VALUES (144, 14, 'B');\n";
    public static String insertTicketTable145 = "INSERT INTO Ticket VALUES (145, 15, 'B');\n";
    public static String insertTicketTable146 = "INSERT INTO Ticket VALUES (146, 16, 'B');\n";
    public static String insertTicketTable147 = "INSERT INTO Ticket VALUES (147, 17, 'B');\n";
    public static String insertTicketTable148 = "INSERT INTO Ticket VALUES (148, 18, 'B');\n";
    public static String insertTicketTable149 = "INSERT INTO Ticket VALUES (149, 19, 'B');\n";
    public static String insertTicketTable150 = "INSERT INTO Ticket VALUES (150, 20, 'B');\n";
    public static String insertTicketTable151 = "INSERT INTO Ticket VALUES (151, 21, 'B');\n";
    public static String insertTicketTable152 = "INSERT INTO Ticket VALUES (152, 22, 'B');\n";
    public static String insertTicketTable153 = "INSERT INTO Ticket VALUES (153, 23, 'B');\n";
    public static String insertTicketTable154 = "INSERT INTO Ticket VALUES (154, 24, 'B');\n";
    public static String insertTicketTable155 = "INSERT INTO Ticket VALUES (155, 25, 'B');\n";
    public static String insertTicketTable156 = "INSERT INTO Ticket VALUES (156, 26, 'B');\n";
    public static String insertTicketTable157 = "INSERT INTO Ticket VALUES (157, 27, 'B');\n";
    public static String insertTicketTable158 = "INSERT INTO Ticket VALUES (158, 28, 'B');\n";
    public static String insertTicketTable159 = "INSERT INTO Ticket VALUES (159, 29, 'B');\n";
    public static String insertTicketTable160 = "INSERT INTO Ticket VALUES (160, 30, 'B');\n";
    public static String insertTicketTable161 = "INSERT INTO Ticket VALUES (161, 31, 'B');\n";
    public static String insertTicketTable162 = "INSERT INTO Ticket VALUES (162, 32, 'B');\n";
    public static String insertTicketTable163 = "INSERT INTO Ticket VALUES (163, 33, 'B');\n";
    public static String insertTicketTable164 = "INSERT INTO Ticket VALUES (164, 34, 'B');\n";
    public static String insertTicketTable165 = "INSERT INTO Ticket VALUES (165, 35, 'B');\n";
    public static String insertTicketTable166 = "INSERT INTO Ticket VALUES (166, 36, 'B');\n";
    public static String insertTicketTable167 = "INSERT INTO Ticket VALUES (167, 37, 'B');\n";
    public static String insertTicketTable168 = "INSERT INTO Ticket VALUES (168, 38, 'B');\n";
    public static String insertTicketTable169 = "INSERT INTO Ticket VALUES (169, 39, 'B');\n";
    public static String insertTicketTable170 = "INSERT INTO Ticket VALUES (170, 40, 'B');\n";
    public static String insertTicketTable171 = "INSERT INTO Ticket VALUES (171, 41, 'B');\n";
    public static String insertTicketTable172 = "INSERT INTO Ticket VALUES (172, 42, 'B');\n";
    public static String insertTicketTable173 = "INSERT INTO Ticket VALUES (173, 43, 'B');\n";
    public static String insertTicketTable174 = "INSERT INTO Ticket VALUES (174, 44, 'B');\n";
    public static String insertTicketTable175 = "INSERT INTO Ticket VALUES (175, 45, 'B');\n";
    public static String insertTicketTable176 = "INSERT INTO Ticket VALUES (176, 46, 'B');\n";
    public static String insertTicketTable177 = "INSERT INTO Ticket VALUES (177, 47, 'B');\n";
    public static String insertTicketTable178 = "INSERT INTO Ticket VALUES (178, 48, 'B');\n";
    public static String insertTicketTable179 = "INSERT INTO Ticket VALUES (179, 49, 'B');\n";
    public static String insertTicketTable180 = "INSERT INTO Ticket VALUES (180, 50, 'B');\n";
    public static String insertTicketTable181 = "INSERT INTO Ticket VALUES (181, 1, 'C');\n";
    public static String insertTicketTable182 = "INSERT INTO Ticket VALUES (182, 2, 'C');\n";
    public static String insertTicketTable183 = "INSERT INTO Ticket VALUES (183, 3, 'C');\n";
    public static String insertTicketTable184 = "INSERT INTO Ticket VALUES (184, 4, 'C');\n";
    public static String insertTicketTable185 = "INSERT INTO Ticket VALUES (185, 5, 'C');\n";
    public static String insertTicketTable186 = "INSERT INTO Ticket VALUES (186, 6, 'C');\n";
    public static String insertTicketTable187 = "INSERT INTO Ticket VALUES (187, 7, 'C');\n";
    public static String insertTicketTable188 = "INSERT INTO Ticket VALUES (188, 8, 'C');\n";
    public static String insertTicketTable189 = "INSERT INTO Ticket VALUES (189, 9, 'C');\n";
    public static String insertTicketTable190 = "INSERT INTO Ticket VALUES (190, 10, 'C');\n";
    public static String insertTicketTable191 = "INSERT INTO Ticket VALUES (191, 11, 'C');\n";
    public static String insertTicketTable192 = "INSERT INTO Ticket VALUES (192, 12, 'C');\n";
    public static String insertTicketTable193 = "INSERT INTO Ticket VALUES (193, 13, 'C');\n";
    public static String insertTicketTable194 = "INSERT INTO Ticket VALUES (194, 14, 'C');\n";
    public static String insertTicketTable195 = "INSERT INTO Ticket VALUES (195, 15, 'C');\n";
    public static String insertTicketTable196 = "INSERT INTO Ticket VALUES (196, 16, 'C');\n";
    public static String insertTicketTable197 = "INSERT INTO Ticket VALUES (197, 17, 'C');\n";
    public static String insertTicketTable198 = "INSERT INTO Ticket VALUES (198, 18, 'C');\n";
    public static String insertTicketTable199 = "INSERT INTO Ticket VALUES (199, 19, 'C');\n";
    public static String insertTicketTable200 = "INSERT INTO Ticket VALUES (200, 20, 'C');\n";
    public static String insertTicketTable201 = "INSERT INTO Ticket VALUES (201, 21, 'C');\n";
    public static String insertTicketTable202 = "INSERT INTO Ticket VALUES (202, 22, 'C');\n";
    public static String insertTicketTable203 = "INSERT INTO Ticket VALUES (203, 23, 'C');\n";
    public static String insertTicketTable204 = "INSERT INTO Ticket VALUES (204, 24, 'C');\n";
    public static String insertTicketTable205 = "INSERT INTO Ticket VALUES (205, 25, 'C');\n";
    public static String insertTicketTable206 = "INSERT INTO Ticket VALUES (206, 26, 'C');\n";
    public static String insertTicketTable207 = "INSERT INTO Ticket VALUES (207, 27, 'C');\n";
    public static String insertTicketTable208 = "INSERT INTO Ticket VALUES (208, 28, 'C');\n";
    public static String insertTicketTable209 = "INSERT INTO Ticket VALUES (209, 29, 'C');\n";
    public static String insertTicketTable210 = "INSERT INTO Ticket VALUES (210, 30, 'C');\n";
    public static String insertTicketTable211 = "INSERT INTO Ticket VALUES (211, 31, 'C');\n";
    public static String insertTicketTable212 = "INSERT INTO Ticket VALUES (212, 32, 'C');\n";
    public static String insertTicketTable213 = "INSERT INTO Ticket VALUES (213, 33, 'C');\n";
    public static String insertTicketTable214 = "INSERT INTO Ticket VALUES (214, 34, 'C');\n";
    public static String insertTicketTable215 = "INSERT INTO Ticket VALUES (215, 35, 'C');\n";
    public static String insertTicketTable216 = "INSERT INTO Ticket VALUES (216, 36, 'C');\n";
    public static String insertTicketTable217 = "INSERT INTO Ticket VALUES (217, 37, 'C');\n";
    public static String insertTicketTable218 = "INSERT INTO Ticket VALUES (218, 38, 'C');\n";
    public static String insertTicketTable219 = "INSERT INTO Ticket VALUES (219, 39, 'C');\n";
    public static String insertTicketTable220 = "INSERT INTO Ticket VALUES (220, 40, 'C');\n";
    public static String insertTicketTable221 = "INSERT INTO Ticket VALUES (221, 41, 'C');\n";
    public static String insertTicketTable222 = "INSERT INTO Ticket VALUES (222, 42, 'C');\n";
    public static String insertTicketTable223 = "INSERT INTO Ticket VALUES (223, 43, 'C');\n";
    public static String insertTicketTable224 = "INSERT INTO Ticket VALUES (224, 44, 'C');\n";
    public static String insertTicketTable225 = "INSERT INTO Ticket VALUES (225, 45, 'C');\n";
    public static String insertTicketTable226 = "INSERT INTO Ticket VALUES (226, 46, 'C');\n";
    public static String insertTicketTable227 = "INSERT INTO Ticket VALUES (227, 47, 'C');\n";
    public static String insertTicketTable228 = "INSERT INTO Ticket VALUES (228, 48, 'C');\n";
    public static String insertTicketTable229 = "INSERT INTO Ticket VALUES (229, 49, 'C');\n";
    public static String insertTicketTable230 = "INSERT INTO Ticket VALUES (230, 50, 'C');\n";
    public static String insertTicketTable231 = "INSERT INTO Ticket VALUES (231, 1, 'D');\n";
    public static String insertTicketTable232 = "INSERT INTO Ticket VALUES (232, 2, 'D');\n";
    public static String insertTicketTable233 = "INSERT INTO Ticket VALUES (233, 3, 'D');\n";
    public static String insertTicketTable234 = "INSERT INTO Ticket VALUES (234, 4, 'D');\n";
    public static String insertTicketTable235 = "INSERT INTO Ticket VALUES (235, 5, 'D');\n";
    public static String insertTicketTable236 = "INSERT INTO Ticket VALUES (236, 6, 'D');\n";
    public static String insertTicketTable237 = "INSERT INTO Ticket VALUES (237, 7, 'D');\n";
    public static String insertTicketTable238 = "INSERT INTO Ticket VALUES (238, 8, 'D');\n";
    public static String insertTicketTable239 = "INSERT INTO Ticket VALUES (239, 9, 'D');\n";
    public static String insertTicketTable240 = "INSERT INTO Ticket VALUES (240, 10, 'D');\n";
    public static String insertTicketTable241 = "INSERT INTO Ticket VALUES (241, 11, 'D');\n";
    public static String insertTicketTable242 = "INSERT INTO Ticket VALUES (242, 12, 'D');\n";
    public static String insertTicketTable243 = "INSERT INTO Ticket VALUES (243, 13, 'D');\n";
    public static String insertTicketTable244 = "INSERT INTO Ticket VALUES (244, 14, 'D');\n";
    public static String insertTicketTable245 = "INSERT INTO Ticket VALUES (245, 15, 'D');\n";
    public static String insertTicketTable246 = "INSERT INTO Ticket VALUES (246, 16, 'D');\n";
    public static String insertTicketTable247 = "INSERT INTO Ticket VALUES (247, 17, 'D');\n";
    public static String insertTicketTable248 = "INSERT INTO Ticket VALUES (248, 18, 'D');\n";
    public static String insertTicketTable249 = "INSERT INTO Ticket VALUES (249, 19, 'D');\n";
    public static String insertTicketTable250 = "INSERT INTO Ticket VALUES (250, 20, 'D');\n";
    public static String insertTicketTable251 = "INSERT INTO Ticket VALUES (251, 21, 'D');\n";
    public static String insertTicketTable252 = "INSERT INTO Ticket VALUES (252, 22, 'D');\n";
    public static String insertTicketTable253 = "INSERT INTO Ticket VALUES (253, 23, 'D');\n";
    public static String insertTicketTable254 = "INSERT INTO Ticket VALUES (254, 24, 'D');\n";
    public static String insertTicketTable255 = "INSERT INTO Ticket VALUES (255, 25, 'D');\n";
    public static String insertTicketTable256 = "INSERT INTO Ticket VALUES (256, 26, 'D');\n";
    public static String insertTicketTable257 = "INSERT INTO Ticket VALUES (257, 27, 'D');\n";
    public static String insertTicketTable258 = "INSERT INTO Ticket VALUES (258, 28, 'D');\n";
    public static String insertTicketTable259 = "INSERT INTO Ticket VALUES (259, 29, 'D');\n";
    public static String insertTicketTable260 = "INSERT INTO Ticket VALUES (260, 30, 'D');\n";
    public static String insertTicketTable261 = "INSERT INTO Ticket VALUES (261, 31, 'D');\n";
    public static String insertTicketTable262 = "INSERT INTO Ticket VALUES (262, 32, 'D');\n";
    public static String insertTicketTable263 = "INSERT INTO Ticket VALUES (263, 33, 'D');\n";
    public static String insertTicketTable264 = "INSERT INTO Ticket VALUES (264, 34, 'D');\n";
    public static String insertTicketTable265 = "INSERT INTO Ticket VALUES (265, 35, 'D');\n";
    public static String insertTicketTable266 = "INSERT INTO Ticket VALUES (266, 36, 'D');\n";
    public static String insertTicketTable267 = "INSERT INTO Ticket VALUES (267, 37, 'D');\n";
    public static String insertTicketTable268 = "INSERT INTO Ticket VALUES (268, 38, 'D');\n";
    public static String insertTicketTable269 = "INSERT INTO Ticket VALUES (269, 39, 'D');\n";
    public static String insertTicketTable270 = "INSERT INTO Ticket VALUES (270, 40, 'D');\n";
    public static String insertTicketTable271 = "INSERT INTO Ticket VALUES (271, 41, 'D');\n";
    public static String insertTicketTable272 = "INSERT INTO Ticket VALUES (272, 42, 'D');\n";
    public static String insertTicketTable273 = "INSERT INTO Ticket VALUES (273, 43, 'D');\n";
    public static String insertTicketTable274 = "INSERT INTO Ticket VALUES (274, 44, 'D');\n";
    public static String insertTicketTable275 = "INSERT INTO Ticket VALUES (275, 45, 'D');\n";
    public static String insertTicketTable276 = "INSERT INTO Ticket VALUES (276, 46, 'D');\n";
    public static String insertTicketTable277 = "INSERT INTO Ticket VALUES (277, 47, 'D');\n";
    public static String insertTicketTable278 = "INSERT INTO Ticket VALUES (278, 48, 'D');\n";
    public static String insertTicketTable279 = "INSERT INTO Ticket VALUES (279, 49, 'D');\n";
    public static String insertTicketTable280 = "INSERT INTO Ticket VALUES (280, 50, 'D');\n";
    public static String insertTicketTable281 = "INSERT INTO Ticket VALUES (281, 51, 'D');\n";
    public static String insertTicketTable282 = "INSERT INTO Ticket VALUES (282, 52, 'D');\n";
    public static String insertTicketTable283 = "INSERT INTO Ticket VALUES (283, 53, 'D');\n";
    public static String insertTicketTable284 = "INSERT INTO Ticket VALUES (284, 54, 'D');\n";
    public static String insertTicketTable285 = "INSERT INTO Ticket VALUES (285, 55, 'D');\n";
    public static String insertTicketTable286 = "INSERT INTO Ticket VALUES (286, 56, 'D');\n";
    public static String insertTicketTable287 = "INSERT INTO Ticket VALUES (287, 57, 'D');\n";
    public static String insertTicketTable288 = "INSERT INTO Ticket VALUES (288, 58, 'D');\n";
    public static String insertTicketTable289 = "INSERT INTO Ticket VALUES (289, 59, 'D');\n";
    public static String insertTicketTable290 = "INSERT INTO Ticket VALUES (290, 60, 'D');\n";
    public static String insertTicketTable291 = "INSERT INTO Ticket VALUES (291, 61, 'D');\n";
    public static String insertTicketTable292 = "INSERT INTO Ticket VALUES (292, 62, 'D');\n";
    public static String insertTicketTable293 = "INSERT INTO Ticket VALUES (293, 63, 'D');\n";
    public static String insertTicketTable294 = "INSERT INTO Ticket VALUES (294, 64, 'D');\n";
    public static String insertTicketTable295 = "INSERT INTO Ticket VALUES (295, 65, 'D');\n";
    public static String insertTicketTable296 = "INSERT INTO Ticket VALUES (296, 66, 'D');\n";
    public static String insertTicketTable297 = "INSERT INTO Ticket VALUES (297, 67, 'D');\n";
    public static String insertTicketTable298 = "INSERT INTO Ticket VALUES (298, 68, 'D');\n";
    public static String insertTicketTable299 = "INSERT INTO Ticket VALUES (299, 69, 'D');\n";
    public static String insertTicketTable300 = "INSERT INTO Ticket VALUES (300, 70, 'D');\n";

    static{
        try{
            c.createStatement().executeUpdate(insertVenueTable);
            auditService.logAction("Inserted Venue Data");
            c.createStatement().executeUpdate(insertEventTable1);
            c.createStatement().executeUpdate(insertEventTable2);
            c.createStatement().executeUpdate(insertEventTable3);
            c.createStatement().executeUpdate(insertEventTable4);
            auditService.logAction("Inserted Event Data");
            c.createStatement().executeUpdate(insertArtistTable1);
            c.createStatement().executeUpdate(insertArtistTable2);
            c.createStatement().executeUpdate(insertArtistTable3);
            c.createStatement().executeUpdate(insertArtistTable4);
            c.createStatement().executeUpdate(insertArtistTable5);
            c.createStatement().executeUpdate(insertArtistTable6);
            c.createStatement().executeUpdate(insertArtistTable7);
            c.createStatement().executeUpdate(insertArtistTable8);
            auditService.logAction("Inserted Artist Data");
            c.createStatement().executeUpdate(insertTicketTable1);
            c.createStatement().executeUpdate(insertTicketTable2);
            c.createStatement().executeUpdate(insertTicketTable3);
            c.createStatement().executeUpdate(insertTicketTable4);
            c.createStatement().executeUpdate(insertTicketTable5);
            c.createStatement().executeUpdate(insertTicketTable6);
            c.createStatement().executeUpdate(insertTicketTable7);
            c.createStatement().executeUpdate(insertTicketTable8);
            c.createStatement().executeUpdate(insertTicketTable9);
            c.createStatement().executeUpdate(insertTicketTable10);
            c.createStatement().executeUpdate(insertTicketTable11);
            c.createStatement().executeUpdate(insertTicketTable12);
            c.createStatement().executeUpdate(insertTicketTable13);
            c.createStatement().executeUpdate(insertTicketTable14);
            c.createStatement().executeUpdate(insertTicketTable15);
            c.createStatement().executeUpdate(insertTicketTable16);
            c.createStatement().executeUpdate(insertTicketTable17);
            c.createStatement().executeUpdate(insertTicketTable18);
            c.createStatement().executeUpdate(insertTicketTable19);
            c.createStatement().executeUpdate(insertTicketTable20);
            c.createStatement().executeUpdate(insertTicketTable21);
            c.createStatement().executeUpdate(insertTicketTable22);
            c.createStatement().executeUpdate(insertTicketTable23);
            c.createStatement().executeUpdate(insertTicketTable24);
            c.createStatement().executeUpdate(insertTicketTable25);
            c.createStatement().executeUpdate(insertTicketTable26);
            c.createStatement().executeUpdate(insertTicketTable27);
            c.createStatement().executeUpdate(insertTicketTable28);
            c.createStatement().executeUpdate(insertTicketTable29);
            c.createStatement().executeUpdate(insertTicketTable30);
            c.createStatement().executeUpdate(insertTicketTable31);
            c.createStatement().executeUpdate(insertTicketTable32);
            c.createStatement().executeUpdate(insertTicketTable33);
            c.createStatement().executeUpdate(insertTicketTable34);
            c.createStatement().executeUpdate(insertTicketTable35);
            c.createStatement().executeUpdate(insertTicketTable36);
            c.createStatement().executeUpdate(insertTicketTable37);
            c.createStatement().executeUpdate(insertTicketTable38);
            c.createStatement().executeUpdate(insertTicketTable39);
            c.createStatement().executeUpdate(insertTicketTable40);
            c.createStatement().executeUpdate(insertTicketTable41);
            c.createStatement().executeUpdate(insertTicketTable42);
            c.createStatement().executeUpdate(insertTicketTable43);
            c.createStatement().executeUpdate(insertTicketTable44);
            c.createStatement().executeUpdate(insertTicketTable45);
            c.createStatement().executeUpdate(insertTicketTable46);
            c.createStatement().executeUpdate(insertTicketTable47);
            c.createStatement().executeUpdate(insertTicketTable48);
            c.createStatement().executeUpdate(insertTicketTable49);
            c.createStatement().executeUpdate(insertTicketTable50);
            c.createStatement().executeUpdate(insertTicketTable51);
            c.createStatement().executeUpdate(insertTicketTable52);
            c.createStatement().executeUpdate(insertTicketTable53);
            c.createStatement().executeUpdate(insertTicketTable54);
            c.createStatement().executeUpdate(insertTicketTable55);
            c.createStatement().executeUpdate(insertTicketTable56);
            c.createStatement().executeUpdate(insertTicketTable57);
            c.createStatement().executeUpdate(insertTicketTable58);
            c.createStatement().executeUpdate(insertTicketTable59);
            c.createStatement().executeUpdate(insertTicketTable60);
            c.createStatement().executeUpdate(insertTicketTable61);
            c.createStatement().executeUpdate(insertTicketTable62);
            c.createStatement().executeUpdate(insertTicketTable63);
            c.createStatement().executeUpdate(insertTicketTable64);
            c.createStatement().executeUpdate(insertTicketTable65);
            c.createStatement().executeUpdate(insertTicketTable66);
            c.createStatement().executeUpdate(insertTicketTable67);
            c.createStatement().executeUpdate(insertTicketTable68);
            c.createStatement().executeUpdate(insertTicketTable69);
            c.createStatement().executeUpdate(insertTicketTable70);
            c.createStatement().executeUpdate(insertTicketTable71);
            c.createStatement().executeUpdate(insertTicketTable72);
            c.createStatement().executeUpdate(insertTicketTable73);
            c.createStatement().executeUpdate(insertTicketTable74);
            c.createStatement().executeUpdate(insertTicketTable75);
            c.createStatement().executeUpdate(insertTicketTable76);
            c.createStatement().executeUpdate(insertTicketTable77);
            c.createStatement().executeUpdate(insertTicketTable78);
            c.createStatement().executeUpdate(insertTicketTable79);
            c.createStatement().executeUpdate(insertTicketTable80);
            c.createStatement().executeUpdate(insertTicketTable81);
            c.createStatement().executeUpdate(insertTicketTable82);
            c.createStatement().executeUpdate(insertTicketTable83);
            c.createStatement().executeUpdate(insertTicketTable84);
            c.createStatement().executeUpdate(insertTicketTable85);
            c.createStatement().executeUpdate(insertTicketTable86);
            c.createStatement().executeUpdate(insertTicketTable87);
            c.createStatement().executeUpdate(insertTicketTable88);
            c.createStatement().executeUpdate(insertTicketTable89);
            c.createStatement().executeUpdate(insertTicketTable90);
            c.createStatement().executeUpdate(insertTicketTable91);
            c.createStatement().executeUpdate(insertTicketTable92);
            c.createStatement().executeUpdate(insertTicketTable93);
            c.createStatement().executeUpdate(insertTicketTable94);
            c.createStatement().executeUpdate(insertTicketTable95);
            c.createStatement().executeUpdate(insertTicketTable96);
            c.createStatement().executeUpdate(insertTicketTable97);
            c.createStatement().executeUpdate(insertTicketTable98);
            c.createStatement().executeUpdate(insertTicketTable99);
            c.createStatement().executeUpdate(insertTicketTable100);
            c.createStatement().executeUpdate(insertTicketTable101);
            c.createStatement().executeUpdate(insertTicketTable102);
            c.createStatement().executeUpdate(insertTicketTable103);
            c.createStatement().executeUpdate(insertTicketTable104);
            c.createStatement().executeUpdate(insertTicketTable105);
            c.createStatement().executeUpdate(insertTicketTable106);
            c.createStatement().executeUpdate(insertTicketTable107);
            c.createStatement().executeUpdate(insertTicketTable108);
            c.createStatement().executeUpdate(insertTicketTable109);
            c.createStatement().executeUpdate(insertTicketTable110);
            c.createStatement().executeUpdate(insertTicketTable111);
            c.createStatement().executeUpdate(insertTicketTable112);
            c.createStatement().executeUpdate(insertTicketTable113);
            c.createStatement().executeUpdate(insertTicketTable114);
            c.createStatement().executeUpdate(insertTicketTable115);
            c.createStatement().executeUpdate(insertTicketTable116);
            c.createStatement().executeUpdate(insertTicketTable117);
            c.createStatement().executeUpdate(insertTicketTable118);
            c.createStatement().executeUpdate(insertTicketTable119);
            c.createStatement().executeUpdate(insertTicketTable120);
            c.createStatement().executeUpdate(insertTicketTable121);
            c.createStatement().executeUpdate(insertTicketTable122);
            c.createStatement().executeUpdate(insertTicketTable123);
            c.createStatement().executeUpdate(insertTicketTable124);
            c.createStatement().executeUpdate(insertTicketTable125);
            c.createStatement().executeUpdate(insertTicketTable126);
            c.createStatement().executeUpdate(insertTicketTable127);
            c.createStatement().executeUpdate(insertTicketTable128);
            c.createStatement().executeUpdate(insertTicketTable129);
            c.createStatement().executeUpdate(insertTicketTable130);
            c.createStatement().executeUpdate(insertTicketTable131);
            c.createStatement().executeUpdate(insertTicketTable132);
            c.createStatement().executeUpdate(insertTicketTable133);
            c.createStatement().executeUpdate(insertTicketTable134);
            c.createStatement().executeUpdate(insertTicketTable135);
            c.createStatement().executeUpdate(insertTicketTable136);
            c.createStatement().executeUpdate(insertTicketTable137);
            c.createStatement().executeUpdate(insertTicketTable138);
            c.createStatement().executeUpdate(insertTicketTable139);
            c.createStatement().executeUpdate(insertTicketTable140);
            c.createStatement().executeUpdate(insertTicketTable141);
            c.createStatement().executeUpdate(insertTicketTable142);
            c.createStatement().executeUpdate(insertTicketTable143);
            c.createStatement().executeUpdate(insertTicketTable144);
            c.createStatement().executeUpdate(insertTicketTable145);
            c.createStatement().executeUpdate(insertTicketTable146);
            c.createStatement().executeUpdate(insertTicketTable147);
            c.createStatement().executeUpdate(insertTicketTable148);
            c.createStatement().executeUpdate(insertTicketTable149);
            c.createStatement().executeUpdate(insertTicketTable150);
            c.createStatement().executeUpdate(insertTicketTable151);
            c.createStatement().executeUpdate(insertTicketTable152);
            c.createStatement().executeUpdate(insertTicketTable153);
            c.createStatement().executeUpdate(insertTicketTable154);
            c.createStatement().executeUpdate(insertTicketTable155);
            c.createStatement().executeUpdate(insertTicketTable156);
            c.createStatement().executeUpdate(insertTicketTable157);
            c.createStatement().executeUpdate(insertTicketTable158);
            c.createStatement().executeUpdate(insertTicketTable159);
            c.createStatement().executeUpdate(insertTicketTable160);
            c.createStatement().executeUpdate(insertTicketTable161);
            c.createStatement().executeUpdate(insertTicketTable162);
            c.createStatement().executeUpdate(insertTicketTable163);
            c.createStatement().executeUpdate(insertTicketTable164);
            c.createStatement().executeUpdate(insertTicketTable165);
            c.createStatement().executeUpdate(insertTicketTable166);
            c.createStatement().executeUpdate(insertTicketTable167);
            c.createStatement().executeUpdate(insertTicketTable168);
            c.createStatement().executeUpdate(insertTicketTable169);
            c.createStatement().executeUpdate(insertTicketTable170);
            c.createStatement().executeUpdate(insertTicketTable171);
            c.createStatement().executeUpdate(insertTicketTable172);
            c.createStatement().executeUpdate(insertTicketTable173);
            c.createStatement().executeUpdate(insertTicketTable174);
            c.createStatement().executeUpdate(insertTicketTable175);
            c.createStatement().executeUpdate(insertTicketTable176);
            c.createStatement().executeUpdate(insertTicketTable177);
            c.createStatement().executeUpdate(insertTicketTable178);
            c.createStatement().executeUpdate(insertTicketTable179);
            c.createStatement().executeUpdate(insertTicketTable180);
            c.createStatement().executeUpdate(insertTicketTable181);
            c.createStatement().executeUpdate(insertTicketTable182);
            c.createStatement().executeUpdate(insertTicketTable183);
            c.createStatement().executeUpdate(insertTicketTable184);
            c.createStatement().executeUpdate(insertTicketTable185);
            c.createStatement().executeUpdate(insertTicketTable186);
            c.createStatement().executeUpdate(insertTicketTable187);
            c.createStatement().executeUpdate(insertTicketTable188);
            c.createStatement().executeUpdate(insertTicketTable189);
            c.createStatement().executeUpdate(insertTicketTable190);
            c.createStatement().executeUpdate(insertTicketTable191);
            c.createStatement().executeUpdate(insertTicketTable192);
            c.createStatement().executeUpdate(insertTicketTable193);
            c.createStatement().executeUpdate(insertTicketTable194);
            c.createStatement().executeUpdate(insertTicketTable195);
            c.createStatement().executeUpdate(insertTicketTable196);
            c.createStatement().executeUpdate(insertTicketTable197);
            c.createStatement().executeUpdate(insertTicketTable198);
            c.createStatement().executeUpdate(insertTicketTable199);
            c.createStatement().executeUpdate(insertTicketTable200);
            c.createStatement().executeUpdate(insertTicketTable201);
            c.createStatement().executeUpdate(insertTicketTable202);
            c.createStatement().executeUpdate(insertTicketTable203);
            c.createStatement().executeUpdate(insertTicketTable204);
            c.createStatement().executeUpdate(insertTicketTable205);
            c.createStatement().executeUpdate(insertTicketTable206);
            c.createStatement().executeUpdate(insertTicketTable207);
            c.createStatement().executeUpdate(insertTicketTable208);
            c.createStatement().executeUpdate(insertTicketTable209);
            c.createStatement().executeUpdate(insertTicketTable210);
            c.createStatement().executeUpdate(insertTicketTable211);
            c.createStatement().executeUpdate(insertTicketTable212);
            c.createStatement().executeUpdate(insertTicketTable213);
            c.createStatement().executeUpdate(insertTicketTable214);
            c.createStatement().executeUpdate(insertTicketTable215);
            c.createStatement().executeUpdate(insertTicketTable216);
            c.createStatement().executeUpdate(insertTicketTable217);
            c.createStatement().executeUpdate(insertTicketTable218);
            c.createStatement().executeUpdate(insertTicketTable219);
            c.createStatement().executeUpdate(insertTicketTable220);
            c.createStatement().executeUpdate(insertTicketTable221);
            c.createStatement().executeUpdate(insertTicketTable222);
            c.createStatement().executeUpdate(insertTicketTable223);
            c.createStatement().executeUpdate(insertTicketTable224);
            c.createStatement().executeUpdate(insertTicketTable225);
            c.createStatement().executeUpdate(insertTicketTable226);
            c.createStatement().executeUpdate(insertTicketTable227);
            c.createStatement().executeUpdate(insertTicketTable228);
            c.createStatement().executeUpdate(insertTicketTable229);
            c.createStatement().executeUpdate(insertTicketTable230);
            c.createStatement().executeUpdate(insertTicketTable231);
            c.createStatement().executeUpdate(insertTicketTable232);
            c.createStatement().executeUpdate(insertTicketTable233);
            c.createStatement().executeUpdate(insertTicketTable234);
            c.createStatement().executeUpdate(insertTicketTable235);
            c.createStatement().executeUpdate(insertTicketTable236);
            c.createStatement().executeUpdate(insertTicketTable237);
            c.createStatement().executeUpdate(insertTicketTable238);
            c.createStatement().executeUpdate(insertTicketTable239);
            c.createStatement().executeUpdate(insertTicketTable240);
            c.createStatement().executeUpdate(insertTicketTable241);
            c.createStatement().executeUpdate(insertTicketTable242);
            c.createStatement().executeUpdate(insertTicketTable243);
            c.createStatement().executeUpdate(insertTicketTable244);
            c.createStatement().executeUpdate(insertTicketTable245);
            c.createStatement().executeUpdate(insertTicketTable246);
            c.createStatement().executeUpdate(insertTicketTable247);
            c.createStatement().executeUpdate(insertTicketTable248);
            c.createStatement().executeUpdate(insertTicketTable249);
            c.createStatement().executeUpdate(insertTicketTable250);
            c.createStatement().executeUpdate(insertTicketTable251);
            c.createStatement().executeUpdate(insertTicketTable252);
            c.createStatement().executeUpdate(insertTicketTable253);
            c.createStatement().executeUpdate(insertTicketTable254);
            c.createStatement().executeUpdate(insertTicketTable255);
            c.createStatement().executeUpdate(insertTicketTable256);
            c.createStatement().executeUpdate(insertTicketTable257);
            c.createStatement().executeUpdate(insertTicketTable258);
            c.createStatement().executeUpdate(insertTicketTable259);
            c.createStatement().executeUpdate(insertTicketTable260);
            c.createStatement().executeUpdate(insertTicketTable261);
            c.createStatement().executeUpdate(insertTicketTable262);
            c.createStatement().executeUpdate(insertTicketTable263);
            c.createStatement().executeUpdate(insertTicketTable264);
            c.createStatement().executeUpdate(insertTicketTable265);
            c.createStatement().executeUpdate(insertTicketTable266);
            c.createStatement().executeUpdate(insertTicketTable267);
            c.createStatement().executeUpdate(insertTicketTable268);
            c.createStatement().executeUpdate(insertTicketTable269);
            c.createStatement().executeUpdate(insertTicketTable270);
            c.createStatement().executeUpdate(insertTicketTable271);
            c.createStatement().executeUpdate(insertTicketTable272);
            c.createStatement().executeUpdate(insertTicketTable273);
            c.createStatement().executeUpdate(insertTicketTable274);
            c.createStatement().executeUpdate(insertTicketTable275);
            c.createStatement().executeUpdate(insertTicketTable276);
            c.createStatement().executeUpdate(insertTicketTable277);
            c.createStatement().executeUpdate(insertTicketTable278);
            c.createStatement().executeUpdate(insertTicketTable279);
            c.createStatement().executeUpdate(insertTicketTable280);
            c.createStatement().executeUpdate(insertTicketTable281);
            c.createStatement().executeUpdate(insertTicketTable282);
            c.createStatement().executeUpdate(insertTicketTable283);
            c.createStatement().executeUpdate(insertTicketTable284);
            c.createStatement().executeUpdate(insertTicketTable285);
            c.createStatement().executeUpdate(insertTicketTable286);
            c.createStatement().executeUpdate(insertTicketTable287);
            c.createStatement().executeUpdate(insertTicketTable288);
            c.createStatement().executeUpdate(insertTicketTable289);
            c.createStatement().executeUpdate(insertTicketTable290);
            c.createStatement().executeUpdate(insertTicketTable291);
            c.createStatement().executeUpdate(insertTicketTable292);
            c.createStatement().executeUpdate(insertTicketTable293);
            c.createStatement().executeUpdate(insertTicketTable294);
            c.createStatement().executeUpdate(insertTicketTable295);
            c.createStatement().executeUpdate(insertTicketTable296);
            c.createStatement().executeUpdate(insertTicketTable297);
            c.createStatement().executeUpdate(insertTicketTable298);
            c.createStatement().executeUpdate(insertTicketTable299);
            c.createStatement().executeUpdate(insertTicketTable300);
            auditService.logAction("Database Initialization for Venue, Events, Artists and Tickets Complete. Remember Staff!");
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
    */

//    public static String insertStaffTable1 = "INSERT INTO Staff VALUES "+
//            "(1, 'Bruce Wayne')"


}
