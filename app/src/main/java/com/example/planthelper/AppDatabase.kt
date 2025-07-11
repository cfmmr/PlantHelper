package com.example.planthelper
import androidx.room.*
import android.content.Context

@Entity(tableName = "planta")
data class Planta(@PrimaryKey(autoGenerate = true) val ID_especie: Int, val Nome: String, val Tipo: String, val Toxicidade: String, val Fertelizacao: String, val Propagacao: String, val Manutencao: String)
@Dao interface PlantaDao {
    @Query("SELECT * FROM planta") suspend fun getAll(): List<Planta>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(planta: Planta)
    @Update suspend fun update(planta: Planta)
    @Delete suspend fun delete(planta: Planta)
}
@Entity(tableName = "luminosidade") data class Luminosidade(@PrimaryKey val ID_lumin: Int, val Luz_solar: String, val Densidade: String, val Ambiente: String, val Temp_min: Int, val Temp_max: Int)
@Dao interface LuminosidadeDao {
    @Query("SELECT * FROM luminosidade") suspend fun getAll(): List<Luminosidade>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(lum: Luminosidade)
    @Update suspend fun update(lum: Luminosidade)
    @Delete suspend fun delete(lum: Luminosidade)
}
@Entity(tableName = "agua") data class Agua(@PrimaryKey val ID_agua: Int, val Humidade: Int, val Solo: String, val Quantidade: String, val Frequencia: Int)
@Dao interface AguaDao {
    @Query("SELECT * FROM agua") suspend fun getAll(): List<Agua>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(agua: Agua)
    @Update suspend fun update(agua: Agua)
    @Delete suspend fun delete(agua: Agua)
}
@Database(entities = [Planta::class, Luminosidade::class, Agua::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantaDao(): PlantaDao
    abstract fun luminosidadeDao(): LuminosidadeDao
    abstract fun aguaDao(): AguaDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) { INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "plantcare.db").build().also { INSTANCE = it } }
    }
}