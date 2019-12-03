import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import com.squareup.sqldelight.drivers.ios.wrapConnection
import eu.tobiasheine.movies.frontend.db.MoviesDb
import kotlin.reflect.KClass

actual fun createInMemorySqlDriver(): SqlDriver {
    val schema = MoviesDb.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "MoviesDbTest.db",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}

actual annotation class RunWith(actual val value: KClass<out Runner>)
actual abstract class Runner
actual class AndroidJUnit4 : Runner()