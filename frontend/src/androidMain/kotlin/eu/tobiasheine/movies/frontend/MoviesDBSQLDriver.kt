package eu.tobiasheine.movies.frontend

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import eu.tobiasheine.movies.frontend.db.MoviesDb

object MoviesDBSQLDriver {

    fun create(context: Context): SqlDriver = AndroidSqliteDriver(MoviesDb.Schema, context, "Movies.db")

}