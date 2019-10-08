package com.wardrobes.sisyphus.controller

import com.wardrobes.sisyphus.gallery.GoogleDrivePhotoProvider
import com.wardrobes.sisyphus.model.Attachment
import com.wardrobes.sisyphus.model.AttachmentRepository
import com.wardrobes.sisyphus.model.WardrobeRepository
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


@RestController
class AttachmentController(
        val wardrobeRepository: WardrobeRepository,
        val attachmentRepository: AttachmentRepository
) {

    @PostMapping("/gallery/{wardrobeId}/upload")
    @Throws(IOException::class)
    fun uploadPhoto(@RequestPart("file") file: MultipartFile, @PathVariable("wardrobeId") wardrobeId: Long) {
        if (!Files.exists(Paths.get(wardrobeId.toString()))) Files.createDirectory(Paths.get(wardrobeId.toString()))
        Files.write(Paths.get("$wardrobeId/${file.originalFilename}"), file.bytes)
        wardrobeRepository.findById(wardrobeId).get().also {
            attachmentRepository.save(Attachment(url = "/gallery/$wardrobeId/${file.originalFilename}", wardrobe = it))
        }
    }

    @GetMapping(value = ["/gallery/{wardrobeId}/{fileName:.+}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    @Throws(IOException::class)
    fun getImage(@PathVariable("wardrobeId") wardrobeId: Long, @PathVariable("fileName") fileName: String): ByteArray {
        return Files.readAllBytes(Paths.get("$wardrobeId/$fileName"))
    }

    @GetMapping("/gallery/{wardrobeId}")
    fun getPhotoUrls(@PathVariable("wardrobeId") wardrobeId: Long): List<String> {
        return GoogleDrivePhotoProvider().getPhotos()
    }

    @PostMapping("/model/{wardrobeId}")
    @Throws(IOException::class)
    fun uploadModel(@RequestPart("file") file: MultipartFile, @PathVariable("wardrobeId") wardrobeId: Long) {
        if (!Files.exists(Paths.get(wardrobeId.toString()))) Files.createDirectory(Paths.get(wardrobeId.toString()))
        Files.write(Paths.get("$wardrobeId/${file.originalFilename}"), file.bytes)
        wardrobeRepository.findById(wardrobeId).get().model3dUrl = "$wardrobeId/${file.originalFilename}"
    }

    @GetMapping(value = ["/model/{wardrobeId}"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    @ResponseBody
    @Throws(IOException::class)
    fun getModel(@PathVariable("wardrobeId") wardrobeId: Long): InputStreamResource {
//        val path = wardrobeRepository.findById(wardrobeId).get().model3dUrl
        val path = "wardrobe.obj"
        return InputStreamResource(FileInputStream(File(path)))
    }
}