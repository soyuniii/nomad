package com.web2.global.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
    private final String bucket;

    public S3Service(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();  // 파일 이름을 고유하게 설정
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            // S3에 파일 업로드
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));  // 파일 공개 접근 허용

            // 업로드된 파일의 URL 반환
            return amazonS3.getUrl(bucket, fileName).toString();

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    public String uploadFileFromUrl(String photoUrl) throws IOException {
        try {
            //Google Places API로부터 받아온 URL
            URL url = new URL(photoUrl);

            // URL로부터 InputStream 열기
            InputStream inputStream = url.openStream();
            String fileName = UUID.randomUUID().toString() + ".jpg"; // 확장자는 필요에 따라 수정

            // ByteArrayOutputStream을 사용하여 InputStream을 읽고 크기 확인
            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileData = byteArrayOutputStream.toByteArray();

            // 파일 크기 계산
            long contentLength = fileData.length;
*/
            // S3에 업로드할 파일 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available()); // 실제 파일 크기 설정
            metadata.setContentType("image/jpeg"); // JPEG 이미지로 설정

            // S3에 파일 업로드

            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            // 업로드된 파일의 S3 URL 반환
            return amazonS3.getUrl(bucket, fileName).toString();
           /* String fileName = UUID.randomUUID().toString() + ".jpg"; //확장자는 필요에 따라 수정

            // S3에 업로드할 파일 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(photoUrl.length());
            metadata.setContentType(fileName);

            //???
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            // 업로드된 파일의 S3 URL 반환
            return amazonS3.getUrl(bucket, fileName).toString();

        } catch (IOException e) {
            throw new RuntimeException("URL에서 파일을 가져오는 중 오류 발생", e);
        }*/
        } catch (IOException e) {
            throw new RuntimeException("URL에서 파일을 가져오는 중 오류 발생", e);
        }
    }


    public void deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1); // URL에서 파일명 추출

        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } catch (AmazonServiceException e) {
            // 예외 처리
            System.err.println("S3 파일 삭제 오류: " + e.getErrorMessage());
        }
    }
}
