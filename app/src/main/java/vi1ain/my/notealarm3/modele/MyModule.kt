package vi1ain.my.notealarm3.modele

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vi1ain.my.notealarm3.data.NoteDao
import vi1ain.my.notealarm3.data.NoteDatabase
import vi1ain.my.notealarm3.data.NoteRepImpl
import vi1ain.my.notealarm3.data.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    @Provides
    @Singleton
    fun provideNoteDB(myApp:Application):NoteDatabase =
        Room.databaseBuilder(
            myApp,
            NoteDatabase::class.java, "database-name"
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository =
        NoteRepImpl(db.noteDao)
}