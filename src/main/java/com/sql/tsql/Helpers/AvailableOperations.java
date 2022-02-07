package com.sql.tsql.Helpers;

import java.util.HashMap;

public final class AvailableOperations {
    public static HashMap<String, String> withExample = new HashMap<>() {{
        put("CREATE TABLE", "CREATE TABLE table_name { id , name }");
        put("DELETE TABLE", "DELETE TABLE table_name");
    }};
}
