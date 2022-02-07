package com.sql.tsql.Helpers;

import java.util.HashMap;

public final class AvailableOperations {
    public static HashMap<String, String> withExample = new HashMap<>() {{
        put("CREATE TABLE", "CREATE TABLE table_name { name , email }");
        put("DELETE TABLE", "DELETE TABLE table_name");
        put("UPDATE TABLE", "UPDATE TABLE table_name new_table_name");
        put("INSERT DATA", "INSERT TABLE { name , email } VALUES { john , john@gmail.com }");
        put("SHOW TABLES", "SHOW TABLES");
    }};
}
