package net.ohmwomuc.core.file.service;

import net.ohmwomuc.core.file.dto.FileInfo;
import net.ohmwomuc.core.security.dto.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<FileInfo.Response> uploadFiles(String uploadedDir, List<MultipartFile> multipartFiles, User.UserAccountInfo loginUser);

    Resource getFile(String uniqueFileName);
}
