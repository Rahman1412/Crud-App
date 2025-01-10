package com.example.crudapp.utils

import androidx.datastore.core.Serializer
import com.example.crudapp.util.UserPreferences
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserPreferences>{
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance();

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return UserPreferences.parseFrom(input);
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        t.writeTo(output)
    }

}