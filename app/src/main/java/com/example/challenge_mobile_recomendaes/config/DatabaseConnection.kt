import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {
    private const val URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"
    private const val USER = "rm551096"
    private const val PASSWORD = "130904"

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}
