/*
 * Available context bindings:
 *   COLUMNS     List<DataColumn>
 *   ROWS        Iterable<DataRow>
 *   OUT         { append() }
 *   FORMATTER   { format(row, col); formatValue(Object, col) }
 *   TRANSPOSED  Boolean
 * plus ALL_COLUMNS, TABLE, DIALECT
 *
 * where:
 *   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
 *   DataColumn  { columnNumber(), name() }
 */

NEWLINE = System.getProperty("line.separator")
INDENT = "    "
QUOTING = "'"
LBRACKET = "["
RBRACKET = "]"
VSEPARATOR =","
NOQUOTING = ['null', 'NULL', 'TRUE', 'true', 'FALSE', 'false']

OUT.append(LBRACKET)
OUT.append(NEWLINE)
ROWS.each { row ->
    OUT.append(INDENT)
        .append(LBRACKET)
        .append(NEWLINE)
    COLUMNS.each { column ->
        value = FORMATTER.format(row, column)
        def query = !value.isNumber() && !NOQUOTING.contains(value)
        OUT.append(INDENT)
            .append(INDENT)
            .append(QUOTING)
            .append(column.name())
            .append(QUOTING)
            .append(" => ")
            .append(query ? QUOTING : "")
            .append(value.replace(QUOTING, QUOTING + QUOTING))
            .append(query ? QUOTING : "")
            .append(VSEPARATOR)
            .append(NEWLINE)
    }
    OUT.append(INDENT)
        .append(RBRACKET)
        .append(VSEPARATOR)
        .append(NEWLINE)
}
OUT.append(RBRACKET)
