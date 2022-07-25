@Database(
  entities = [
    (${EntityName}::class)
  ],
  version = 1
)

abstract class DB : RoomDatabase() {
    
    abstract fun ${entityName}Dao(): ${EntityName}Dao

    companion object {

        @Volatile
        private var INSTANCE: DB? = null

        private const val dbName = "${dbName}.db"

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(DB::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, DB::class.java, dbName)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
        }

        fun getInstance(): DB {
            if (INSTANCE == null) {
                Log.wtf("Room", "Room Database not initialized")
                throw(IllegalAccessError())
            } else
                return INSTANCE!!

        }

        private fun destroyInstance() {
            INSTANCE = null
        }
    }
}