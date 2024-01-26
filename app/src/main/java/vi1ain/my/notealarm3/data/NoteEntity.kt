package vi1ain.my.notealarm3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val description:String,
    val timeOfCreation:String,
    val year:Int?=null,
    val month:Int?=null,
    val day:Int?=null,
    val hour:Int?=null,
    val minutes:Int?=null,
    val isCheck: Boolean,
)