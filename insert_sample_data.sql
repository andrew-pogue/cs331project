INSERT INTO plant (plant_ID, name, address, type, manager_name, manager_contact_number, number_units)
VALUES  (0, "Alpha", "314 Brook St, Woodside, NY 11377", NULL, "Angela Moss", "347-231-1399", 1),
        (1, "Bravo", "8 Delaware St, Westbury, NY 11590", NULL, "Edward Norton", "516-214-8363", 1),
        (2, "Charlie", "97 Deerfield Drive, Pittsburg, CA 94565", NULL, "Jordan Belfort", "925-203-1302", 3),
        (3, "Delta", "9045 Paris Hill St, Los Angeles, CA 90003", NULL, "Dolores Abernathy", "213-200-5296", 2),
        (4, "Echo", "56 Lake St, Houston, TX 77080", NULL, "Skyler White", "281-200-6080", 2),
        (5, "Foxtrot", "57 Prince Lane, Miami, FL 33165", NULL, "Barry Berkman", "305-200-3951", 2),
        (6, "Golf", "57 Crescent St, New York, NY 10034", NULL, "Patrick Bateman", "212-200-5152", 4);

INSERT INTO unit (unit_ID, plant_ID, address, type, manager_name, manager_contact_number, number_boilers)
VALUES  (0, 0, "132A", NULL, "Jane Doe", "012-345-6789", 1),
        (1, 1, "594I", NULL, "John Doe", "112-345-6789", 1),
        (2, 2, "132B", NULL, "Elize Geraldi", "486-216-2168", 2),
        (3, 2, "597D", NULL, "Kyle Smith", "112-345-6789", 1),
        (4, 2, "219S", NULL, "Alan Cossack", "597-556-4873", 1),
        (5, 5, "498W", NULL, "Sarah Yung", "154-546-4897", 2),
        (6, 5, "165P", NULL, "Soren Farmer", "458-673-1597", 2);

INSERT INTO boiler (boiler_ID, unit_ID, name, purchase_date, purchase_price, functionality_description, location, boiler_status, production_date)
VALUES  (0, 0, "A1", "1995-03-12", 100000, "", "SECTOR 1", "RENOVATION", "1996-03-12"),
        (1, 1, "B1", "1999-02-11", 160000, "", "SECTOR 1", "RENOVATION", "1999-06-12"),
        (2, 2, "C1", "2009-06-22", 200000, "", "SECTOR 1", "ACTIVE", "2009-10-12"),
        (3, 2, "C2", "2009-06-22", 200000, "", "SECTOR 1", "ACTIVE", "2009-10-12"),
        (4, 3, "C3", "2011-11-08", 300000, "", "SECTOR 2", "ACTIVE", "2012-08-04"),
        (5, 4, "C4", "2014-01-13", 300000, "", "SECTOR 2", "ACTIVE", "2014-07-26"),
        (42, 6, "E1", "2022-07-11", 500000, "", "SECTOR 2", "CONSTRUCTION", NULL);

INSERT INTO coal (coal_type, sulfur_content, unit_cost, price_per_unit_allowance, scrubber_rate, heat_rate, coal_burn_rate, emittion_rate)
VALUES  ("anthracite", "SO2", 98, 100, 70, 9, 4, 0.07),
        ("bituminous", "SO2", 100, 88, 80, 8, 3, 0.05),
        ("subbituminous", "SO2", 88, 72, 75, 7, 6, 0.04),
        ("lignite", "SO2", 95, 90, 90, 9, 5, 0.07),
        ("charcoal", "SO2", 97, 80, 60, 10, 5, 0.05),
        ("peat", "SO2", 110, 101, 0.5, 10, 5, 0.04),
        ("coke", "SO2", 105, 89, 85, 9, 4, 0.03);

INSERT INTO boileractivity (boiler_activity_ID, boiler_ID, coal_type, amount_coal_to_burn, sulfur_emitted, date, energy_generated)
VALUES  (1201, 0, "lignite", 30, 15, "2016-04-06", 160),
        (1206, 0, "lignite", 60, 28, "2016-04-06", 368),
        (1204, 3, "anthracite", 100, 180, "2016-04-06", 1200),
        (1220, 4, "subbituminous", 30, 19, "2016-04-07", 202),
        (1205, 2, "bituminous", 130, 100, "2016-04-06", 2105),
        (1221, 2, "bituminous", 130, 111, "2016-04-07", 1950),
        (1232, 5, "bituminous", 130, 105, "2016-04-08", 2000);