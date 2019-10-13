package com.wardrobes.sisyphus.gallery

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.services.drive.DriveScopes
import java.io.*

interface PhotoProvider {

    fun getPhotos(): List<String>
}

private const val APPLICATION_NAME = "Google Drive API Java Quickstart"
private const val TOKENS_DIRECTORY_PATH = "tokens"
private const val CREDENTIALS_FILE_PATH = "/credentials.json"

class GoogleDrivePhotoProvider : PhotoProvider {

    private val jsonFactory = JacksonFactory.getDefaultInstance()
    private val scopes = listOf(DriveScopes.DRIVE_METADATA_READONLY)

    private fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
        val credentials = GoogleDrivePhotoProvider::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(credentials))
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        val flow = GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, jsonFactory, clientSecrets, scopes)
            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    override fun getPhotos(): List<String> {
        return GoogleNetHttpTransport.newTrustedTransport().let { Drive.Builder(it, jsonFactory, getCredentials(it)) }
            .setApplicationName(APPLICATION_NAME)
            .build()
            .files()
            .list()
            .setQ(" '1l5ttXXGmmcxhIwPuqdDMoJy4b7j3socl' in parents")
            .setFields("files(id)")
            .execute()
            .files
            ?.map { "https://drive.google.com/uc?export=download&id=${it.id}" }
            ?: emptyList()
    }
}