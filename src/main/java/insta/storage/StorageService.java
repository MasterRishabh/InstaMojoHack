package insta.storage;

import org.json.JSONException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import entities.PanDetail;
import entities.UserDetails;
import exception.ValidationException;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file) throws ValidationException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    List<UserDetails> leaderBoard();

	List<PanDetail> getAllUnverifiedPan();

}
