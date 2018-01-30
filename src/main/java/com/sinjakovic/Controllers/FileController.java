package com.sinjakovic.Controllers;

import com.sinjakovic.models.Users.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import java.io.File;

/**
 * Created by Brandon on 5/2/2017.
 */
@RestController
@RequestMapping("/api/v1/avatar")
public class FileController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Boolean upload(@AuthenticationPrincipal User user, @RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) return false;

        try {
            byte[] content = file.getBytes();
            Path path = Paths.get("avatars/"+ user.getUsername());
            Files.write(path, content, StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getFile(@AuthenticationPrincipal User user){
        try {
            //get the users avatar picture
            Path path = Paths.get("avatars/" + user.getUsername());
            return Files.readAllBytes(path);
        } catch (IOException e) {
            try{
                //if something went wrong send the default avatar
                Path path = Paths.get("avatars/default");
                return Files.readAllBytes(path);

            } catch(IOException ex) {
                //this should not happen
                return null;
            }
        }
    }


//    public void uploadFile(CommonsMultipartFile[] files) throws IllegalStateException, IOException {
//        final String outputDirectory = "c://Temp/";
//
//        if(files != null && files.length > 0) {
//            for( CommonsMultipartFile file: files){
//                if(!file.getOriginalFilename().equals("")){
//                    file.transferTo(new File(outputDirectory + file.getOriginalFilename()));
//                }
//            }
//        } else {
//            throw new IllegalStateException("No files given:"+files);
//        }
//    }

}
