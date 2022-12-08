package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.model.entity.UserImage;
import com.brainstation.fantasyfootball.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/image")
public class UserImageController {
    @Autowired
    private UserImageService userImageService;


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, HttpServletRequest request) throws IOException {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/common/profilePage";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(file.getContentType());
        String fileType1="image/jpeg";
        String fileType2="image/png";
        System.out.println(file.getContentType().equals(fileType2));
        System.out.println(file.getContentType().equals(fileType1));
        if(file.getContentType().equals(fileType2) || file.getContentType().equals(fileType1)){
            UserImage image = new UserImage();
            image.setData(file.getBytes());
            image.setFileType(file.getContentType());
            image.setFileName(fileName);
            Principal principal = request.getUserPrincipal();
            image.setUsername(principal.getName());
            userImageService.saveImage(image);
            attributes.addFlashAttribute("message", "You Updated profile Picture ");

            return "redirect:/common/profilePage";
        }
        attributes.addFlashAttribute("message", "File Type Not Supported !");
        return "redirect:/common/profilePage";
    }

}
