import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass

expect fun createInMemorySqlDriver(): SqlDriver

expect annotation class RunWith(val value: KClass<out Runner>)
expect abstract class Runner
expect class AndroidJUnit4 : Runner

fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(context, block)

fun <T> suspendTest(block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(EmptyCoroutineContext, block)