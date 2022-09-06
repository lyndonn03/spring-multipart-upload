package io.lpamintuan.springmultipartupload.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.lpamintuan.springmultipartupload.entities.UploadFileDetails;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFileDetails, UUID> {
}
