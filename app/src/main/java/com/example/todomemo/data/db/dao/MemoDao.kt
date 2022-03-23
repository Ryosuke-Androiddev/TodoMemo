package com.example.todomemo.data.db.dao

import androidx.room.*
import com.example.todomemo.data.db.model.MemoEntity

@Entity
interface MemoDao {

    @Query("SELECT * FROM memo_table")
    suspend fun getAllMemo(): List<MemoEntity>

    @Query("SELECT * FROM memo_table WHERE memoId = :memoId")
    suspend fun getMemoById(memoId: Int): MemoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createMemo(memoEntity: MemoEntity)

    // memoId(PrimaryKeyで判断される)
    @Update
    suspend fun updateMemo(memoEntity: MemoEntity)
}