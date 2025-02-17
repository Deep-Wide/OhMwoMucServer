package net.ohmwomuc.core.file.repository;

import net.ohmwomuc.core.file.dto.FileInfo;

public interface FileRepository {
    void persistFileInfo(FileInfo.Domain fileInfo);

    FileInfo.Domain findByUniqueFileName(String uniqueFileName);
}
