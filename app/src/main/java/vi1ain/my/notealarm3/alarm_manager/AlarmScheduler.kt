package vi1ain.my.notealarm3.alarm_manager

import vi1ain.my.notealarm3.data.NoteEntity
import vi1ain.my.notealarm3.data.NoteViewModel

interface AlarmScheduler {
    fun schedule (item:NoteEntity,noteViewModel: NoteViewModel)
    fun cansel (item:NoteEntity)
}