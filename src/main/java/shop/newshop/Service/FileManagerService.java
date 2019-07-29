package shop.newshop.Service;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.net.util.Base64;
//import org.apache.logging.log4j.util.Strings;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileManagerService {
    public static final String UPLOAD_IMG_DIRECTORY = "D:/image_qlnv/";

    public static void setupDirectoryStorage(String directory) {
        File uploadRootDir = new File(directory);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
    }

    public static Result StorageFile(String uploadingDirectory, MultipartFile multipartFile,
                                     boolean isAllowOverRide,boolean isUniqueNameFile, String webContextPath) {
        Result result = new Result();
        boolean isSaveWebResourcePath = (webContextPath != null);
        try {
            String fileName = multipartFile.getOriginalFilename();
            setupDirectoryStorage(uploadingDirectory);
            String filePath;
            String webResourcePath = "";
            filePath = uploadingDirectory + fileName;
            if (isSaveWebResourcePath) {
                webResourcePath = webContextPath + fileName;
            }
            if (!isUniqueNameFile) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
                String nowFormat = sdf.format(new Date());
                String fileNameCustom = FilenameUtils.removeExtension(fileName) +
                        '_' + nowFormat + '.' + FilenameUtils.getExtension(fileName);
                filePath = uploadingDirectory + fileNameCustom;
                if (isSaveWebResourcePath) {
                    webResourcePath = webContextPath + fileNameCustom;
                }
            }
            File file = new File(filePath);
            if (file.exists() && !isAllowOverRide) {
                result.setSuccess(false);
                result.setResult(String.format(Result.EXISTED_FILE_ERROR, fileName));
                return result;
            }
            if (file.exists() && isAllowOverRide) {
                file.delete();
            }
            multipartFile.transferTo(file);
            result.setSuccess(true);
            result.setResult(filePath);
        } catch (Exception ex) {
            System.out.println("Storage: " + ex);
            result.setSuccess(false);
            result.setResult(Result.SERVER_ERROR);
        }
        return result;
    }

    public Result StorageFile(String uploadingDirectory, MultipartFile multipartFile, String base64Encode, boolean isAllowOverRide) {
        String fileName = multipartFile.getOriginalFilename();
        return StorageFile(uploadingDirectory, fileName, base64Encode, isAllowOverRide);
    }

    public static Result StorageFile(String uploadingDirectory, String fileName, String base64Encode, boolean isAllowOverRide) {
        Result result = new Result();
        try {
            byte[] imageByte = Base64.decodeBase64(base64Encode);
            setupDirectoryStorage(uploadingDirectory);
            String filePath = uploadingDirectory + fileName;
            File file = new File(filePath);
            if (file.exists() && !isAllowOverRide) {
                result.setSuccess(false);
                result.setResult(String.format(Result.EXISTED_FILE_ERROR, fileName));
                return result;
            }
            if (file.exists() && isAllowOverRide) {
                file.delete();
            }
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(imageByte);
            outputStream.close();
            result.setSuccess(true);
            result.setResult(filePath);
        } catch (Exception ex) {
            result.setSuccess(false);
            result.setResult(ex.toString());
        }
        return result;
    }

    //return isSuceess (true, false) and result(is error message if isSuccess = false, path file if isSuccess = true)
    public static class Result {
        private static final String EXISTED_FILE_ERROR = "File đã tồn tại";
        private static final String SERVER_ERROR = "Server xử lý thất bại";
        private boolean isSuccess;
        private String result;

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean success) {
            isSuccess = success;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public String getFileName(String path) {
        File file = new File(path);
        return getFileName(file);
    }

    public String getFileName(File file) {
        try {
            if (file == null || !file.exists()) {
                return null;
            }
            return file.getName();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String encodeFileToBase64Binary(File file) {
        try {
            if (file == null || !file.exists()) {
                return null;
            }
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
            return new String(encoded, StandardCharsets.US_ASCII);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static String encodeFileToBase64Binary(String path) {
        File file = new File(path);
        return encodeFileToBase64Binary(file);
    }

    public static String encodeImgFileToBase64BinarySrc(File file) {
        String beforeSrcString = "data:image/png;base64,";
        String notFoundImgBase64 = "iVBORw0KGgoAAAANSUhEUgAAAOMAAADXBAMAAAAaS/vEAAAAMFBMVEVYWFrz8/RQUFL39/j" +
                "9/f5VVVfl5ebBwcNfX2F+foCqq6xra21jY2WUlJbU1NUPDw+K67H0AAAHNElEQVR4nO2aj2sTVxzAj4OrnYoQLiiSDY6DkKbmhO" +
                "OOlE4qlDsaLPQ4DjordmMlR1vXKq4N63SzSEGdTMaWutrOVbYSxiwT1sjmxkS2QquDMrZrJnSyUppI/cH6N+y9y12SXpo2sS/" +
                "ZlPcBa3x5fR/v3Xvf95MgMBgMBoPBYDAYDAaDwWAwGAwGg8FgnhNan4WtKS/Nlc69LRnJLztLZw+zJWVMkEomtEWlq3TqsL" +
                "IoJS2UAo9Cubuk5tqBQEn3fFAKszwCZa+3hMCjnUeiVEv4BQor0So1Vq2wkv3w6pC3okr2Soer63emgkryrijQghKtoD" +
                "KQgqGV3stUTEl+1GiGcz5aOWUkPYLQS2o5lNQ6mfy8pWxhyqGsVvMz1TZaA+XBclQsdXc6Lw+0WMTLoCQTffl5pmwjbZ" +
                "RDGWnJK48atpXucijbw7AnOPJklRNlUHKNdK+zQLK8T7mfdyleRx5q1m4+dBS9kpziYe05OGwrg+fKoEyAR6l3lsjZ/T" +
                "JUjlCQhM8Sd2RSk1a9esqgNEObe0l1ZPqEt1sPemU6tDU7iqQ4wXxImI5aSe3irSCzFu2+BFKlCRW90uqBdI+jTCrwj" +
                "ijzp2DvQa601mEK6cwW+GvyAUuUQdkWtqJMXmynWJYlyqHk7D6fH9sJKxNqpS9nYCwAYmVOMH1UqFTEShjusqGtMspk" +
                "Rul2ds0yKffRGaU5Ta6AMjNiwNg+VBHlLj6rzO2aas4voFVmJxxru+aaUIRYuWbbSchMu/yPcxRolWo4V5mZdml/KD" +
                "mrBrTKI3yu0qWkd3rJ7UJuj0GrzCw9rAaUnnaBtSWcgJRFmbP0SNdsAyxZG2lcE4uQKsl511rgtIs05yA5NYtW2e9" +
                "QwmlXTYTPbUqIlYGwQwmmXeSd9PvN1ixSZW64s7qmwVn/DTrTSZEq9/NOJX1s3kqjH6llUFJTTqPLxQu2PDO1Ral" +
                "cb5c9O5hlVp1IKzaZr8yR24sGlEp7p6UA9sCCUukId3mvNY5cSVVt/JT2kI1SObyh0eVqYFAr7X26ggSnUSvb8sK" +
                "dA7oPtTKzHClIegsBodK3qfLgObRKuPuyCelZAkJlYjOjNYFHqNww3Fk124pUeZTeXCkaSJWbhLt0zcKJHjpl7nKk" +
                "ICGUSnKzcGeCdHeruFNwONFDpmzTizr6RrlT6T9RHAi3gKuLPPs+i7DFFn3r5sU+GH7uld1sKbyGQnl8rBRGECj/i5" +
                "swJYOV/1OlWDpbvBS3OFA6/2xJSWilXGmyLjY5DzgxGAwGk4VkCXjMCy8QUuYlQpZlzKNf+A/zI8zDwnSW9ZqfQaqZQ" +
                "nphDhhlWVUzvyyOHWPE2FWVujg2Q7SDP4T22eSnTDucS80Q1I8fq+1jZ8kjk9fZapg0qhIvwb8o/+R1krj4uUqMxX9N/" +
                "wSpTJFKn9KmKwSZACtxnzxBUNtEuW6Ik0RJjoIBtIXxS/FAUpbe3SdKgniIoWZFWfTU9MvyU/ZmC6PqfZcVpk03EoIULHT" +
                "MuY4y7IqS/bRBzQpLKhkJDop9HN/Z2QGVwekjfHyXtJIM7evscHUeA0rwVa9P6koqQ1NAmeobpg2gnN/dKTovWmykdE+0h" +
                "2mDnHfVM37xpDd2iBNG5+bgNMHd5+fjiVe17aIxd0GZ+5mgZpt3zk0n6khOnLCV3TVAWX9phC94Tp6vDC9xQbdBRgZDjK9" +
                "piLxwihPiZpuK0fVAmVpWmdRDr0/RVKgE7SfSzWiRNy2lq9lUsn4x73JQYeXbHl9INwL6eNNQVYgBzZUTbkzegEv4FcXP3" +
                "3Yb4FOv6lMIeBAfGh+/p/eBt++xlG/x30Il/Fy0Ul9sqWrQDa5ph9sAD3Fn9QnHy7LCgKd8qi/wP0FlwsNYSkGWR/UJgpyv" +
                "t5SeyElTSUYeFq/8W7ncoxu+ECjgfDN7WQpx/OnJM/AplxPH08pYr60Mrq6aysQBW3mzIWUqU8vFKx8EU1/rxlSwM9y7X/Fe" +
                "vBbihCjs4VrsYZXi+gFWY6TbrljQLlVQuBZ72Vb6msynrNGLf5e6kRRGdQNOnFtqJYM9HzKbD4gxsSVO4OORHtbv7iOySjK2l" +
                "w3oy7Mh8HNi2BMIu435A2ytWGTHNJWx4E7dSL1/+zcloB8fT9XBip2MgidZ0iJ8fLhp4X5jPFf5fdPCohQ93Hj9mhgd9rAJo" +
                "Nwz2a8UZ0wrb4YC7gXZYDkpOiKLSoiTZFmGb3CJnJLiHAhFrzCW8hfY4f1uSW5g/Lokg1dfz7whGwkRxCOmOGXtk+pBY9vjms" +
                "HxlRni6KCx76vd35z2rwwMDILos/gF8frKd+SVjq44zAmVF+D0nLrV0QW+vtV54k/tznuqf9W4NjBwpsCFhzzgSKKCMYFtZeHlIY" +
                "KsuedlKbZVA/2e0OCYAQaKS0OMmZNIDx8wZZqBP2eYdB5V83qty+zPALXOIKTllZZOyU/HYDAYDAaDwWAwGAwGg8G8+PwLqOVo/UbuX28AAAAASUVORK5CYII=";
        try {
            String base64Content = encodeFileToBase64Binary(file);
            if (base64Content == null) {
                return beforeSrcString + notFoundImgBase64;
            }
            return beforeSrcString + base64Content;
        } catch (Exception ex) {
            return beforeSrcString + notFoundImgBase64;
        }
    }

    public static String encodeImgFileToBase64BinarySrc(String path) {
        File file = new File(path);
        return encodeImgFileToBase64BinarySrc(file);
    }

}