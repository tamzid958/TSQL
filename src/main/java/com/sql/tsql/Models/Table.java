package com.sql.tsql.Models;

import java.util.List;

public record Table(String name, List<String> columns) {
}
