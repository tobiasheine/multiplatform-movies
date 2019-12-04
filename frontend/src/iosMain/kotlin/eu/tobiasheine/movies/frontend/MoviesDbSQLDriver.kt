package eu.tobiasheine.movies.frontend

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import eu.tobiasheine.movies.frontend.db.MoviesDb

val moviesDbSQLDriver: SqlDriver = NativeSqliteDriver(MoviesDb.Schema, "Movies.db")