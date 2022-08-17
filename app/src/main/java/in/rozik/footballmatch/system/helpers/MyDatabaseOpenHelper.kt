package `in`.rozik.footballmatch.system.helpers

import `in`.rozik.footballmatch.system.models.FavoriteEvent
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FootballMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            FavoriteEvent.tableName, true,
            FavoriteEvent.id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteEvent.idEvent to TEXT + UNIQUE,
            FavoriteEvent.heldDate to TEXT,
            FavoriteEvent.heldTime to TEXT,
            FavoriteEvent.homeTeamId to TEXT,
            FavoriteEvent.awayTeamId to TEXT,
            FavoriteEvent.homeTeam to TEXT,
            FavoriteEvent.awayTeam to TEXT,
            FavoriteEvent.homeScore to TEXT,
            FavoriteEvent.awayScore to TEXT,
            FavoriteEvent.homeShot to TEXT,
            FavoriteEvent.awayShot to TEXT,
            FavoriteEvent.homeRedCards to TEXT,
            FavoriteEvent.awayRedCards to TEXT,
            FavoriteEvent.homeYellowCards to TEXT,
            FavoriteEvent.awayYellowCards to TEXT,
            FavoriteEvent.homeLineupGoalkeeper to TEXT,
            FavoriteEvent.awayLineupGoalkeeper to TEXT,
            FavoriteEvent.homeLineupDefense to TEXT,
            FavoriteEvent.awayLineupDefense to TEXT,
            FavoriteEvent.homeLineupMidfield to TEXT,
            FavoriteEvent.homeGoalDetails to TEXT,
            FavoriteEvent.awayLineupMidfield to TEXT,
            FavoriteEvent.awayGoalDetails to TEXT
        )
        db.createTable(
            FavoriteTeam.tableName, true,
            FavoriteTeam.id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.idTeam to TEXT + UNIQUE,
            FavoriteTeam.name to TEXT,
            FavoriteTeam.badge to TEXT,
            FavoriteTeam.formedYear to TEXT,
            FavoriteTeam.stadium to TEXT,
            FavoriteTeam.stadiumThumb to TEXT,
            FavoriteTeam.stadiumDescription to TEXT,
            FavoriteTeam.website to TEXT,
            FavoriteTeam.descriptionEN to TEXT
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteEvent.tableName, true)
        db.dropTable(FavoriteTeam.tableName, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)