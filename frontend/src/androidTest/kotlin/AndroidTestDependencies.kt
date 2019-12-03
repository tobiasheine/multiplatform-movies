import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import eu.tobiasheine.movies.frontend.db.MoviesDb

actual fun createInMemorySqlDriver(): SqlDriver =
    AndroidSqliteDriver(MoviesDb.Schema, ApplicationProvider.getApplicationContext())

actual typealias RunWith = org.junit.runner.RunWith
actual typealias Runner = org.junit.runner.Runner
actual typealias AndroidJUnit4 = androidx.test.ext.junit.runners.AndroidJUnit4