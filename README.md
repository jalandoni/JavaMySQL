DATABASE : Account

Table : Account

Column : ID, int, 11, PRIMARY

Column : username, varchar, 32, UNIQUE

Column : Password, varchar, 32, INDEX

Table : Information

Column : ID, int, 11, PRIMARY

Column : ACC_ID, int, 11, INDEX

Column : firstname, varchar, 32, INDEX

Column : lastname, varchar, 32, INDEX

Column : age, int, 11, INDEX

Table : Schedule

Column : ID, int, 11, PRIMARY

Column : ACC_ID, int, 11, INDEX

Column : title, varchar, 32, INDEX

Column : unit, int, 11, INDEX

Column : schedule, varchar, 32, INDEX
