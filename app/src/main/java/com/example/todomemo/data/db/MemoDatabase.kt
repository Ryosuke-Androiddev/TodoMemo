package com.example.todomemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todomemo.data.db.dao.MemoDao
import com.example.todomemo.data.db.model.MemoEntity
import com.example.todomemo.data.util.DataConstants.DATABASE_NAME

@Database(
    entities = [MemoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MemoDatabase: RoomDatabase() {

    abstract val memoDao: MemoDao

    companion object {

        // マルチスレッドで、参照されてもデータに整合性が取れるようにするアノテーション
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        // synchronized は、キャッシュからインスタンスを呼び出さずに、必ずメモリから呼び出す
        // スレッドセーフが保証されている
        // 2回、エビル演算子で確認しているのは、本当にデータベースのインスタンスがないかをメモリからも確かめたいから(synchronizedからの呼び出しで)
        // もし存在していれば、シングルトンでなくなるのでデータの整合性をとることができなくなる
        fun getDatabase(context: Context): MemoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): MemoDatabase {
            return Room.databaseBuilder(
                context,
                MemoDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}