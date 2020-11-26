package com.example.notesapp.di

import androidx.room.Room
import com.example.notesapp.App
import com.example.notesapp.data.NotesLocalSource
import com.example.notesapp.data.NotesLocalSourceImpl
import com.example.notesapp.data.NotesRemoteSource
import com.example.notesapp.data.NotesRemoteSourceImpl
import com.example.notesapp.domain.repository.NotesDataManager
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.repository.implementation.NotesRepositoryImpl
import com.example.notesapp.domain.usecase.*
import com.example.notesapp.domain.usecase.implementation.*
import com.example.notesapp.infrastructure.api.ApiConfig
import com.example.notesapp.infrastructure.api.NotesApiDefinition
import com.example.notesapp.infrastructure.db.AppDatabase
import com.example.notesapp.presentation.viewmodel.NoteDetailVM
import com.example.notesapp.presentation.viewmodel.NotesListVM
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author richardgross on 26/11/2020
 */


private val ApplicationModule = module {

}


private val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            AppDatabase::class.java,
            "notes"
        ).allowMainThreadQueries()
            .build()
    }
}

// TODO: 26/11/2020 remove this use NotesLocalSource and RemoteLocalSource instead
private val DataManagerModule = module {
    single { NotesDataManager(get(), get()) }
}

private val ApiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_ENDPOINT_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(ChuckInterceptor(App.instance))
                    .addInterceptor {
                        val req = it.request()
                        it.proceed(req.newBuilder().build())
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(NotesApiDefinition::class.java)
    }
}

private val ViewModelModule = module {
    viewModel { NotesListVM(get()) }
    viewModel { NoteDetailVM(get()) }
}

private val RepositoryModule = module {
    single<NotesRepository> { NotesRepositoryImpl(get(), get()) }
}

private val SourceModule = module {
    single<NotesLocalSource> { NotesLocalSourceImpl(get()) }
    single<NotesRemoteSource> { NotesRemoteSourceImpl(get()) }
}

private val UseCaseModule = module {
    single<CreateNoteUseCase> { CreateNoteUseCaseImpl(get()) }
    single<UpdateNoteUseCase> { UpdateNoteUseCaseImpl(get()) }
    single<DeleteNoteUseCase> { DeleteNoteUseCaseImpl(get()) }
    single<LoadNotesUseCase> { LoadNotesUseCaseImpl(get()) }
    single<LoadNoteDetailUseCase> { LoadNoteDetailUseCaseImpl(get()) }
}

val allKoinDependentModules =
    ApplicationModule + DatabaseModule + ApiModule + DataManagerModule + ViewModelModule + RepositoryModule + SourceModule + UseCaseModule

