package utils;

public class utils {
    public static String createPsqlInsertRequest(String base, int columnCount, int valueRepeat){
        /*
        * The input of this function is :
        *   - String base : base of the sql request
        *       like : "INSERT INTO \"table\"(id, name, value) VALUE"
        *   - int columnCount : number of column in table
        *       like : 3, because there are id, name and value
        *   - int valueRepeat : number of the insert
        *       like : 2, for 2 insert
        * The result of this function is :
        *   INSERT INTO "table"(id, name, value) VALUE(?,?,?),(?,?,?)
        * */
        String result = base + (
                "(" + "?,".repeat(columnCount)
                        .substring(0,(2*columnCount)-1) + "),"
        ).repeat(valueRepeat);
        return result.substring(0, result.length()-1);
    }
}
