package com.example.serviceUploadeDownloadFile.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${filerepositoryfolder}")
    private String fileRepositoryFolder;

    /**
     * file per i controllo dello upload
     * @param file
     * @return new file name whit extension
     * @throws IOException if filder is not writable
     */
    public String upload(MultipartFile file) throws IOException {
        // Genera un nome di un file unico utilizando UUID
        String newFileName= UUID.randomUUID().toString();
        // Ottiene l'estensione del file originale
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // Concatenare il nome del singolo file con l'estensione per ottenere il nome file completo
        String completeFileName = newFileName + "." + extension;
        // Crea un oggetto File per la destinazione finale del file
        File folderDirection= new File(fileRepositoryFolder);
        // Controlla se il file di destinazione esiste già
        if(!folderDirection.exists()){
            // Genera un'eccezione se il file non esiste o non è valido
            throw new IOException("The file don't exist o don't is valid");
        }
        if(!folderDirection.isDirectory()){
            // Controlla se la destinazione finale non è una directory
            throw new IOException("The path given is not present");
        }
        // Crea un nuovo oggetto File per la destinazione del file finale (possibile correzione)
        File finalDestination = new File(fileRepositoryFolder + File.separator+ completeFileName);
        // Controlla se nella destinazione finale esiste già un file con lo stesso nome
        if(finalDestination.exists()){
            //Genera un'eccezione se esiste già un file con lo stesso nome
            throw new IOException("Exist a File whit the same Name");
        }
        // Trasferisci il file alla destinazione finale
        file.transferTo(finalDestination);
        // Restituisce il nome completo del file
        return completeFileName;
    }


    public byte[] download(String fileName, HttpServletResponse response) throws IOException {
        //prendiamo l'estensione del file
        String extension = FilenameUtils.getExtension(fileName);
        //effettuiamo un controllo sull'estensione per dare alla response il tipo corretto
        switch (extension) {
            case "jpg", "jpeg":
                //se jpg o jpeg setterà alla response il Media Type con valore JPEG
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                //se png setterà alla response il Media Type con valore PNG
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
            case "gif":
                //se gif setterà alla response il Media Type con valore GIF
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename = \"" + fileName + "\"");
        //dato il fileName lo recupera dalla directory
        File file = new File(fileRepositoryFolder+ File.separator + fileName);
        //se non esiste viene laciato un IOException
        if (!file.exists()) {
            throw new IOException("imagen don't exist");
        }
        //ritorna convertito in Byte il file desiderato
        return IOUtils.toByteArray(new FileInputStream(file));
    }


}
